
package mx.com.mypo.bpd.caf.catalogoproductos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Item complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantidadMaxima" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cantidadMinima" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="claveProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PMR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="presentacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="presentacionEmpaque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sancion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoContrato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unidadMedida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Item", propOrder = {
    "cantidadMaxima",
    "cantidadMinima",
    "claveProducto",
    "descripcion",
    "partida",
    "pmr",
    "presentacion",
    "presentacionEmpaque",
    "sancion",
    "tipoContrato",
    "unidadMedida"
})
public class Item {

    protected String cantidadMaxima;
    protected String cantidadMinima;
    protected String claveProducto;
    protected String descripcion;
    protected String partida;
    @XmlElement(name = "PMR")
    protected String pmr;
    protected String presentacion;
    protected String presentacionEmpaque;
    protected String sancion;
    protected String tipoContrato;
    protected String unidadMedida;

    /**
     * Gets the value of the cantidadMaxima property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantidadMaxima() {
        return cantidadMaxima;
    }

    /**
     * Sets the value of the cantidadMaxima property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantidadMaxima(String value) {
        this.cantidadMaxima = value;
    }

    /**
     * Gets the value of the cantidadMinima property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantidadMinima() {
        return cantidadMinima;
    }

    /**
     * Sets the value of the cantidadMinima property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantidadMinima(String value) {
        this.cantidadMinima = value;
    }

    /**
     * Gets the value of the claveProducto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveProducto() {
        return claveProducto;
    }

    /**
     * Sets the value of the claveProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveProducto(String value) {
        this.claveProducto = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the partida property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartida() {
        return partida;
    }

    /**
     * Sets the value of the partida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartida(String value) {
        this.partida = value;
    }

    /**
     * Gets the value of the pmr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPMR() {
        return pmr;
    }

    /**
     * Sets the value of the pmr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPMR(String value) {
        this.pmr = value;
    }

    /**
     * Gets the value of the presentacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPresentacion() {
        return presentacion;
    }

    /**
     * Sets the value of the presentacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPresentacion(String value) {
        this.presentacion = value;
    }

    /**
     * Gets the value of the presentacionEmpaque property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPresentacionEmpaque() {
        return presentacionEmpaque;
    }

    /**
     * Sets the value of the presentacionEmpaque property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPresentacionEmpaque(String value) {
        this.presentacionEmpaque = value;
    }

    /**
     * Gets the value of the sancion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSancion() {
        return sancion;
    }

    /**
     * Sets the value of the sancion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSancion(String value) {
        this.sancion = value;
    }

    /**
     * Gets the value of the tipoContrato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoContrato() {
        return tipoContrato;
    }

    /**
     * Sets the value of the tipoContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoContrato(String value) {
        this.tipoContrato = value;
    }

    /**
     * Gets the value of the unidadMedida property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadMedida() {
        return unidadMedida;
    }

    /**
     * Sets the value of the unidadMedida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadMedida(String value) {
        this.unidadMedida = value;
    }

}
