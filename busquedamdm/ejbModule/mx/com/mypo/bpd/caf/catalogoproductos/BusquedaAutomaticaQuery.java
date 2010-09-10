
package mx.com.mypo.bpd.caf.catalogoproductos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BusquedaAutomaticaQuery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusquedaAutomaticaQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="criterias" type="{http://mypo.com.mx/BPD/CAF/CatalogoProductos}BusquedaAutomaticaCriteria" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusquedaAutomaticaQuery", propOrder = {
    "criterias"
})
public class BusquedaAutomaticaQuery {

    @XmlElement(required = true)
    protected List<BusquedaAutomaticaCriteria> criterias;

    /**
     * Gets the value of the criterias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the criterias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCriterias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BusquedaAutomaticaCriteria }
     * 
     * 
     */
    public List<BusquedaAutomaticaCriteria> getCriterias() {
        if (criterias == null) {
            criterias = new ArrayList<BusquedaAutomaticaCriteria>();
        }
        return this.criterias;
    }

}
