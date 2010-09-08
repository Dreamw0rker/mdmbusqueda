package mx.com.mypo.bpd.caf.catalogoproductos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductos.Productos;

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
	@EJB(name="Buscador")
	private BuscadorLocal buscador;
	
	private Location loc = Location.getLocation(this.getClass());

	@RelMessagingNW05DTOperation(enableWSRM=false)
	public  mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductos buscarProductos(mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosQuery busquedaProductosRequest)throws mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosFault_Exception {
		List<SubItem> products = new ArrayList<SubItem>();
		Map<Product, String> paramsBuscar = assembleSearchParameters(busquedaProductosRequest);
		try {
			products = buscador.findProducts(paramsBuscar, busquedaProductosRequest.getPartida());
		} catch (MdmException e) {
			handleException(e, "MDM Exception");
		} catch (Exception e) {
			handleException(e, "Normal Exception");
		}
		loc.debugT("Products found: " + products.size());
		BusquedaProductos busquedaProductos = new BusquedaProductos();
		Productos productos = new BusquedaProductos.Productos();
		List<SubItem> listaProductos = productos.getListaProductos();
		busquedaProductos.setProductos(productos);
		for (SubItem subItem : products) {
			listaProductos.add(subItem);
		}
		
		return busquedaProductos;
	}

	private Map<Product, String> assembleSearchParameters(BusquedaProductosQuery busquedaProductosRequest) {
		Map<Product, String> paramsBuscar = new HashMap<Product, String>();
		addParameterIfNecessary(paramsBuscar, 
				busquedaProductosRequest.getDescripcion(), Product.FIELD_DESC);
		addParameterIfNecessary(paramsBuscar, 
				busquedaProductosRequest.getClave(), Product.FIELD_NUMERO_MATERIAL);
		addParameterIfNecessary(paramsBuscar, 
				busquedaProductosRequest.getEmpaque(), Product.FIELD_EMPAQUE);
		return paramsBuscar;
	}

	private void addParameterIfNecessary(Map<Product, String> paramsBuscar, String valor, Product param) {
		if (valor != null) {
			paramsBuscar.put(param, valor);
		}
	}

	private void handleException(Exception e, String type) throws BusquedaProductosFault_Exception {
		BusquedaProductosFault faultInfo = new BusquedaProductosFault();
		ExchangeFaultData faultData = new ExchangeFaultData();
		faultData.setFaultText(e.getMessage());
		StackTraceElement[] stackTrace = e.getStackTrace();
		List<ExchangeLogData> faultDetails = faultData.getFaultDetail();
		faultDetails.add( addFaultDetail(stackTrace, 0));
		faultDetails.add( addFaultDetail(stackTrace, 1));
		faultInfo.setStandard(faultData );
		throw new BusquedaProductosFault_Exception(type, faultInfo);
	}

	private ExchangeLogData addFaultDetail(StackTraceElement[] stackTrace, int stackNr) {
		ExchangeLogData faultDetail = new ExchangeLogData();
		faultDetail.setText(stackTrace[stackNr].toString());
		return faultDetail;
	}
}