
package mx.com.mypo.bpd.caf.catalogoproductos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BusquedaProductosQuery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusquedaProductosQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sustanciaActiva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formaFarmaceutica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="concentracion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="empaque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusquedaProductosQuery", propOrder = {
    "clave",
    "sustanciaActiva",
    "formaFarmaceutica",
    "descripcion",
    "concentracion",
    "empaque"
})
public class BusquedaProductosQuery {

    protected String clave;
    protected String sustanciaActiva;
    protected String formaFarmaceutica;
    protected String descripcion;
    protected String concentracion;
    protected String empaque;

    /**
     * Gets the value of the clave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClave() {
        return clave;
    }

    /**
     * Sets the value of the clave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClave(String value) {
        this.clave = value;
    }

    /**
     * Gets the value of the sustanciaActiva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSustanciaActiva() {
        return sustanciaActiva;
    }

    /**
     * Sets the value of the sustanciaActiva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSustanciaActiva(String value) {
        this.sustanciaActiva = value;
    }

    /**
     * Gets the value of the formaFarmaceutica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    /**
     * Sets the value of the formaFarmaceutica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormaFarmaceutica(String value) {
        this.formaFarmaceutica = value;
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
     * Gets the value of the concentracion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConcentracion() {
        return concentracion;
    }

    /**
     * Sets the value of the concentracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConcentracion(String value) {
        this.concentracion = value;
    }

    /**
     * Gets the value of the empaque property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmpaque() {
        return empaque;
    }

    /**
     * Sets the value of the empaque property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmpaque(String value) {
        this.empaque = value;
    }

}
