package mx.com.mypo.bpd.caf.catalogoproductos;

/**
 * Exception class for service fault.
 */
@javax.xml.ws.WebFault(name = "BusquedaProductosFault", targetNamespace = "http://mypo.com.mx/BPD/CAF/CatalogoProductos", faultBean = "mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosFault")
public class BusquedaProductosFault_Exception extends java.lang.Exception {

  private mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosFault _BusquedaProductosFault_Exception;

  public BusquedaProductosFault_Exception(String message, mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosFault faultInfo){
    super(message);
    this._BusquedaProductosFault_Exception = faultInfo;
  }

  public BusquedaProductosFault_Exception(String message, mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosFault faultInfo, Throwable cause){
    super(message, cause);
    this._BusquedaProductosFault_Exception = faultInfo;
  }

  public mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosFault getFaultInfo(){
    return this._BusquedaProductosFault_Exception;
  }

}
