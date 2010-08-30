package mx.com.mypo.bpd.caf.catalogoproductos;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.assa.mdm.ejb.BuscadorLocal;
import com.sap.engine.services.webservices.annotations.SrPublication;
import com.sap.engine.services.webservices.espbase.configuration.ann.dt.AuthenticationDT;
import com.sap.engine.services.webservices.espbase.configuration.ann.dt.AuthenticationEnumsAuthenticationLevel;
import com.sap.engine.services.webservices.espbase.configuration.ann.dt.RelMessagingNW05DTOperation;
import com.sap.engine.services.webservices.espbase.configuration.ann.dt.SessionHandlingDT;
import com.sap.engine.services.webservices.espbase.configuration.ann.dt.TransportGuaranteeDT;
import com.sap.engine.services.webservices.espbase.configuration.ann.dt.TransportGuaranteeEnumsLevel;
import com.sap.mdm.data.Record;
import com.sap.tc.logging.Location;

@SrPublication(location="/mx/com/mypo/bpd/caf/catalogoproductos/BusquedaProductosServiceImplBean.classifications")
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
		List<Record> products = buscador.findProducts(busquedaProductosRequest.getDescripcion());
		loc.debugT("Products found: " + products.size());
		return new BusquedaProductos();
	}
}