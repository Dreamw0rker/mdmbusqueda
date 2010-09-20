package mx.com.mypo.bpd.caf.catalogoproductos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductos.Productos;

import com.assa.mdm.data.Atributo;
import com.assa.mdm.data.ItemFactory;
import com.assa.mdm.data.Product;
import com.assa.mdm.ejb.BuscadorLocal;
import com.sap.engine.services.webservices.espbase.configuration.ann.dt.AuthenticationDT;
import com.sap.engine.services.webservices.espbase.configuration.ann.dt.AuthenticationEnumsAuthenticationLevel;
import com.sap.engine.services.webservices.espbase.configuration.ann.dt.RelMessagingNW05DTOperation;
import com.sap.engine.services.webservices.espbase.configuration.ann.dt.SessionHandlingDT;
import com.sap.engine.services.webservices.espbase.configuration.ann.dt.TransportGuaranteeDT;
import com.sap.engine.services.webservices.espbase.configuration.ann.dt.TransportGuaranteeEnumsLevel;
import com.sap.mdm.MdmException;
import com.sap.tc.logging.Location;

//@SrPublication(location="/mx/com/mypo/bpd/caf/catalogoproductos/BusquedaProductosServiceImplBean.classifications")
@SessionHandlingDT(enableSession=false)
@AuthenticationDT(authenticationLevel=AuthenticationEnumsAuthenticationLevel.BASIC)
@TransportGuaranteeDT(level=TransportGuaranteeEnumsLevel.NONE)
@WebService(portName="BusquedaProductosService_Port", serviceName="BusquedaProductosService_Service", endpointInterface="mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosService", targetNamespace="http://mypo.com.mx/BPD/CAF/CatalogoProductos", wsdlLocation="META-INF/wsdl/mx/com/mypo/bpd/caf/catalogoproductos/BusquedaProductosService/BusquedaProductosService.wsdl")
@Stateless
public class BusquedaProductosServiceImplBean {

	@EJB(name = "Buscador")
	private BuscadorLocal buscador;
	
	private ItemFactory itemFactory = new ItemFactory();

	private Location loc = Location.getLocation(this.getClass());

	@RelMessagingNW05DTOperation(enableWSRM=false)
	public mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductos buscarProductos(
			mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosQuery busquedaProductosRequest)
			throws mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosFault_Exception {
		List<SubItem> products = new ArrayList<SubItem>();
		Map<Product, String> paramsProduct = assembleSearchParameters(busquedaProductosRequest);
		Map<Atributo, String> paramsAtributos = assembleAttributeParameters(busquedaProductosRequest);
		products = executeBusqueda(paramsProduct, paramsAtributos);
		loc.debugT("Products found: " + products.size());

		DatosEntrada datosEntrada = new DatosEntrada(busquedaProductosRequest.getPartida(), 
				busquedaProductosRequest.getUnidadMedida(), busquedaProductosRequest.getCantidad());
		addInputParameters(products, datosEntrada);
		return mapToBusquedaProductos(products);
	}

	private void addInputParameters(List<SubItem> products, DatosEntrada datosEntrada) {
		for (SubItem subItem : products) {
			itemFactory.addDatosEntrada(subItem, datosEntrada);
		}
	}

	private List<SubItem> executeBusqueda(Map<Product, String> paramsBuscar, Map<Atributo, String> paramsAtributos)
			throws BusquedaProductosFault_Exception {
		try {
			return buscador.findProducts(paramsBuscar, paramsAtributos);
		} catch (MdmException e) {
			handleException(e, "MDM Exception");
		} catch (Exception e) {
			handleException(e, "Normal Exception");
		}
		return null;
	}

	private BusquedaProductos mapToBusquedaProductos(List<SubItem> products) {
		BusquedaProductos busquedaProductos = new BusquedaProductos();
		Productos productos = new BusquedaProductos.Productos();
		List<SubItem> listaProductos = productos.getListaProductos();
		busquedaProductos.setProductos(productos);
		for (SubItem subItem : products) {
			listaProductos.add(subItem);
		}
		return busquedaProductos;
	}

	@RelMessagingNW05DTOperation(enableWSRM=false)
	public mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductos buscarProductosAuto(
			mx.com.mypo.bpd.caf.catalogoproductos.BusquedaAutomaticaQuery busquedaAutomaticaRequest)
			throws mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosFault_Exception {
		List<SubItem> products = new ArrayList<SubItem>();
		List<BusquedaAutomaticaCriteria> criterias = busquedaAutomaticaRequest.getCriterias();
		for (BusquedaAutomaticaCriteria busquedaCriteria : criterias) {
			Map<Product, String> paramsBuscar = assembleSearchParameters(busquedaCriteria);
			List<SubItem> subitems = executeBusqueda(paramsBuscar);
			DatosEntrada datosEntrada = new DatosEntrada(busquedaCriteria.getPartida(), 
					busquedaCriteria.getUnidadMedida(), busquedaCriteria.getCantidad());
			addInputParameters(subitems, datosEntrada);
			products.addAll(subitems);
		}

		return mapToBusquedaProductos(products);
	}

	private List<SubItem> executeBusqueda(Map<Product, String> paramsBuscar) throws BusquedaProductosFault_Exception {
		return executeBusqueda(paramsBuscar, new HashMap<Atributo, String>());
	}

	private Map<Product, String> assembleSearchParameters(BusquedaAutomaticaCriteria busquedaAutomaticaCriteria) {
		Map<Product, String> paramsBuscar = new HashMap<Product, String>();
		addParameterIfNecessary(paramsBuscar, busquedaAutomaticaCriteria.getDescripcion(), Product.FIELD_DESC);
		addParameterIfNecessary(paramsBuscar, busquedaAutomaticaCriteria.getPresentacion(), Product.FIELD_EMPAQUE);
		return paramsBuscar;
	}

	private Map<Product, String> assembleSearchParameters(BusquedaProductosQuery busquedaProductosRequest) {
		Map<Product, String> paramsBuscar = new HashMap<Product, String>();
		addParameterIfNecessary(paramsBuscar, busquedaProductosRequest.getDescripcion(), Product.FIELD_DESC);
		addParameterIfNecessary(paramsBuscar, busquedaProductosRequest.getClave(),
				Product.FIELD_NUMERO_MATERIAL);
		addParameterIfNecessary(paramsBuscar, busquedaProductosRequest.getEmpaque(), Product.FIELD_EMPAQUE);
		return paramsBuscar;
	}
	
	private Map<Atributo, String> assembleAttributeParameters(BusquedaProductosQuery busquedaProductosRequest) {
		Map<Atributo, String> atributos = new HashMap<Atributo, String>();
		addParameterIfNecessary(atributos, busquedaProductosRequest.getSustanciaActiva(), 
				Atributo.SUBSTANCIA_ACTIVA);
		addParameterIfNecessary(atributos, busquedaProductosRequest.getConcentracion(), Atributo.CONCENTRACION);
		return atributos;
	}

	@SuppressWarnings("unchecked")
	private void addParameterIfNecessary(Map map, String valor, Object atributo) {
		if (valor != null) {
			map.put(atributo, valor);
		}
		
	}

	private void handleException(Exception e, String type) throws BusquedaProductosFault_Exception {
		BusquedaProductosFault faultInfo = new BusquedaProductosFault();
		ExchangeFaultData faultData = new ExchangeFaultData();
		faultData.setFaultText(e.getMessage());
		StackTraceElement[] stackTrace = e.getStackTrace();
		List<ExchangeLogData> faultDetails = faultData.getFaultDetail();
		faultDetails.add(addFaultDetail(stackTrace, 0));
		faultDetails.add(addFaultDetail(stackTrace, 1));
		faultInfo.setStandard(faultData);
		throw new BusquedaProductosFault_Exception(type, faultInfo);
	}

	private ExchangeLogData addFaultDetail(StackTraceElement[] stackTrace, int stackNr) {
		ExchangeLogData faultDetail = new ExchangeLogData();
		faultDetail.setText(stackTrace[stackNr].toString());
		return faultDetail;
	}
}