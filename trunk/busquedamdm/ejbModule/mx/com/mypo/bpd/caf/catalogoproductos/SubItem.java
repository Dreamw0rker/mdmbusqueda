
package mx.com.mypo.bpd.caf.catalogoproductos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="itemPadre" type="{http://mypo.com.mx/BPD/CAF/CatalogoProductos}Item"/>
 *         &lt;element name="subItems" type="{http://mypo.com.mx/BPD/CAF/CatalogoProductos}Item" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubItem", propOrder = {
    "itemPadre",
    "subItems"
})
public class SubItem {

    @XmlElement(required = true)
    protected Item itemPadre;
    protected List<Item> subItems;

    /**
     * Gets the value of the itemPadre property.
     * 
     * @return
     *     possible object is
     *     {@link Item }
     *     
     */
    public Item getItemPadre() {
        return itemPadre;
    }

    /**
     * Sets the value of the itemPadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link Item }
     *     
     */
    public void setItemPadre(Item value) {
        this.itemPadre = value;
    }

    /**
     * Gets the value of the subItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Item }
     * 
     * 
     */
    public List<Item> getSubItems() {
        if (subItems == null) {
            subItems = new ArrayList<Item>();
        }
        return this.subItems;
    }

}
