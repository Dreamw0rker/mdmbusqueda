
package mx.com.mypo.bpd.caf.catalogoproductos;

import java.math.BigInteger;
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
 *         &lt;element name="claveProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PMR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="presentacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="presentacionEmpaque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="unidadMedida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provedor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "claveProducto",
    "descripcion",
    "partida",
    "pmr",
    "presentacion",
    "presentacionEmpaque",
    "cantidad",
    "unidadMedida",
    "provedor",
    "categoria"
})
public class Item {

    protected String claveProducto;
    protected String descripcion;
    protected String partida;
    @XmlElement(name = "PMR")
    protected String pmr;
    protected String presentacion;
    protected String presentacionEmpaque;
    protected BigInteger cantidad;
    protected String unidadMedida;
    protected String provedor;
    protected String categoria;

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
     * Gets the value of the cantidad property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCantidad() {
        return cantidad;
    }

    /**
     * Sets the value of the cantidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCantidad(BigInteger value) {
        this.cantidad = value;
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

    /**
     * Gets the value of the provedor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvedor() {
        return provedor;
    }

    /**
     * Sets the value of the provedor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvedor(String value) {
        this.provedor = value;
    }

    /**
     * Gets the value of the categoria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Sets the value of the categoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoria(String value) {
        this.categoria = value;
    }

}
