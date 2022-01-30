
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegisterCustomerResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegisterCustomerResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RegisterCustomerResult" type="{http://moneyapi.skypoint.com/}registerCustomerResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegisterCustomerResponse", propOrder = {
    "registerCustomerResult"
})
public class RegisterCustomerResponse {

    @XmlElement(name = "RegisterCustomerResult")
    protected RegisterCustomerResult registerCustomerResult;

    /**
     * Gets the value of the registerCustomerResult property.
     * 
     * @return
     *     possible object is
     *     {@link RegisterCustomerResult }
     *     
     */
    public RegisterCustomerResult getRegisterCustomerResult() {
        return registerCustomerResult;
    }

    /**
     * Sets the value of the registerCustomerResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegisterCustomerResult }
     *     
     */
    public void setRegisterCustomerResult(RegisterCustomerResult value) {
        this.registerCustomerResult = value;
    }

}
