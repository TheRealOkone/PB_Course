
package PB.clients;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Serv" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateResponse", propOrder = {
    "serv"
})
public class UpdateResponse {

    @XmlElement(name = "Serv")
    protected byte[] serv;

    /**
     * Gets the value of the serv property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getServ() {
        return serv;
    }

    /**
     * Sets the value of the serv property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setServ(byte[] value) {
        this.serv = value;
    }

}
