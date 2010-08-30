
package mx.com.mypo.bpd.caf.catalogoproductos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BusquedaProductos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusquedaProductos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productos" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="listaProductos" type="{http://mypo.com.mx/BPD/CAF/CatalogoProductos}SubItem" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusquedaProductos", propOrder = {
    "productos"
})
public class BusquedaProductos {

    protected BusquedaProductos.Productos productos;

    /**
     * Gets the value of the productos property.
     * 
     * @return
     *     possible object is
     *     {@link BusquedaProductos.Productos }
     *     
     */
    public BusquedaProductos.Productos getProductos() {
        return productos;
    }

    /**
     * Sets the value of the productos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusquedaProductos.Productos }
     *     
     */
    public void setProductos(BusquedaProductos.Productos value) {
        this.productos = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="listaProductos" type="{http://mypo.com.mx/BPD/CAF/CatalogoProductos}SubItem" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "listaProductos"
    })
    public static class Productos {

        @XmlElement(required = true)
        protected List<SubItem> listaProductos;

        /**
         * Gets the value of the listaProductos property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the listaProductos property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getListaProductos().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SubItem }
         * 
         * 
         */
        public List<SubItem> getListaProductos() {
            if (listaProductos == null) {
                listaProductos = new ArrayList<SubItem>();
            }
            return this.listaProductos;
        }

    }

}
