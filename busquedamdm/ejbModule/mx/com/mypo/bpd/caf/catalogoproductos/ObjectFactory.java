
package mx.com.mypo.bpd.caf.catalogoproductos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.com.mypo.bpd.caf.catalogoproductos package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BusquedaProductosResponse_QNAME = new QName("http://mypo.com.mx/BPD/CAF/CatalogoProductos", "BusquedaProductosResponse");
    private final static QName _BusquedaProductosRequest_QNAME = new QName("http://mypo.com.mx/BPD/CAF/CatalogoProductos", "BusquedaProductosRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.com.mypo.bpd.caf.catalogoproductos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExchangeFaultData }
     * 
     */
    public ExchangeFaultData createExchangeFaultData() {
        return new ExchangeFaultData();
    }

    /**
     * Create an instance of {@link ExchangeLogData }
     * 
     */
    public ExchangeLogData createExchangeLogData() {
        return new ExchangeLogData();
    }

    /**
     * Create an instance of {@link SubItem }
     * 
     */
    public SubItem createSubItem() {
        return new SubItem();
    }

    /**
     * Create an instance of {@link BusquedaProductos }
     * 
     */
    public BusquedaProductos createBusquedaProductos() {
        return new BusquedaProductos();
    }

    /**
     * Create an instance of {@link BusquedaProductosFault }
     * 
     */
    public BusquedaProductosFault createBusquedaProductosFault() {
        return new BusquedaProductosFault();
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link BusquedaProductosQuery }
     * 
     */
    public BusquedaProductosQuery createBusquedaProductosQuery() {
        return new BusquedaProductosQuery();
    }

    /**
     * Create an instance of {@link BusquedaProductos.Productos }
     * 
     */
    public BusquedaProductos.Productos createBusquedaProductosProductos() {
        return new BusquedaProductos.Productos();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusquedaProductos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mypo.com.mx/BPD/CAF/CatalogoProductos", name = "BusquedaProductosResponse")
    public JAXBElement<BusquedaProductos> createBusquedaProductosResponse(BusquedaProductos value) {
        return new JAXBElement<BusquedaProductos>(_BusquedaProductosResponse_QNAME, BusquedaProductos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusquedaProductosQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mypo.com.mx/BPD/CAF/CatalogoProductos", name = "BusquedaProductosRequest")
    public JAXBElement<BusquedaProductosQuery> createBusquedaProductosRequest(BusquedaProductosQuery value) {
        return new JAXBElement<BusquedaProductosQuery>(_BusquedaProductosRequest_QNAME, BusquedaProductosQuery.class, null, value);
    }

}
