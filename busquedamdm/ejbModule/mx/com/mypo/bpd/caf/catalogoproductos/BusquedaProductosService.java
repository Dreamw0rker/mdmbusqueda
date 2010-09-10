package mx.com.mypo.bpd.caf.catalogoproductos;

/**
 * Service Endpoint Interface (generated by SAP WSDL to Java generator).
 */
@javax.jws.WebService(name = "BusquedaProductosService", targetNamespace = "http://mypo.com.mx/BPD/CAF/CatalogoProductos")
@javax.jws.soap.SOAPBinding(parameterStyle = javax.jws.soap.SOAPBinding.ParameterStyle.BARE, style = javax.jws.soap.SOAPBinding.Style.DOCUMENT, use = javax.jws.soap.SOAPBinding.Use.LITERAL)
public interface BusquedaProductosService {

  /**
   * Java representation of web method [buscarProductosAuto].
   */
  @javax.jws.WebMethod(operationName = "buscarProductosAuto", action = "http://sap.com/xi/WebService/soap1.1")
  @javax.jws.WebResult(name = "BusquedaProductosResponse", targetNamespace = "http://mypo.com.mx/BPD/CAF/CatalogoProductos", partName = "BusquedaProductosResponse")
  public mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductos buscarProductosAuto(@javax.jws.WebParam(name = "BusquedaAutomaticaRequest", targetNamespace = "http://mypo.com.mx/BPD/CAF/CatalogoProductos", partName = "BusquedaAutomaticaRequest") mx.com.mypo.bpd.caf.catalogoproductos.BusquedaAutomaticaQuery busquedaAutomaticaRequest) throws mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosFault_Exception;

  /**
   * Java representation of web method [buscarProductos].
   */
  @javax.jws.WebMethod(operationName = "buscarProductos", action = "http://sap.com/xi/WebService/soap1.1")
  @javax.jws.WebResult(name = "BusquedaProductosResponse", targetNamespace = "http://mypo.com.mx/BPD/CAF/CatalogoProductos", partName = "BusquedaProductosResponse")
  public mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductos buscarProductos(@javax.jws.WebParam(name = "BusquedaProductosRequest", targetNamespace = "http://mypo.com.mx/BPD/CAF/CatalogoProductos", partName = "BusquedaProductosRequest") mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosQuery busquedaProductosRequest) throws mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosFault_Exception;

}