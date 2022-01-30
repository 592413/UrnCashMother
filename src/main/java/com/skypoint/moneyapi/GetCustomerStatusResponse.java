
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetCustomerStatusResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetCustomerStatusResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetCustomerStatusResult" type="{http://moneyapi.skypoint.com/}getCustomerStatusResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetCustomerStatusResponse", propOrder = {
    "getCustomerStatusResult"
})
public class GetCustomerStatusResponse {

    @XmlElement(name = "GetCustomerStatusResult")
    protected GetCustomerStatusResult getCustomerStatusResult;

    /**
     * Gets the value of the getCustomerStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetCustomerStatusResult }
     *     
     */
    public GetCustomerStatusResult getGetCustomerStatusResult() {
        return getCustomerStatusResult;
    }

    /**
     * Sets the value of the getCustomerStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCustomerStatusResult }
     *     
     */
    public void setGetCustomerStatusResult(GetCustomerStatusResult value) {
        this.getCustomerStatusResult = value;
    }

}
