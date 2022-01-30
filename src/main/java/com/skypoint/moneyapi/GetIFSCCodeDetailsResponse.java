
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetIFSCCodeDetailsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetIFSCCodeDetailsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetIFSCCodeDetailsResult" type="{http://moneyapi.skypoint.com/}bankDetails" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetIFSCCodeDetailsResponse", propOrder = {
    "getIFSCCodeDetailsResult"
})
public class GetIFSCCodeDetailsResponse {

    @XmlElement(name = "GetIFSCCodeDetailsResult")
    protected BankDetails getIFSCCodeDetailsResult;

    /**
     * Gets the value of the getIFSCCodeDetailsResult property.
     * 
     * @return
     *     possible object is
     *     {@link BankDetails }
     *     
     */
    public BankDetails getGetIFSCCodeDetailsResult() {
        return getIFSCCodeDetailsResult;
    }

    /**
     * Sets the value of the getIFSCCodeDetailsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankDetails }
     *     
     */
    public void setGetIFSCCodeDetailsResult(BankDetails value) {
        this.getIFSCCodeDetailsResult = value;
    }

}
