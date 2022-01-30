
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConfirmPaymentDetailsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConfirmPaymentDetailsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConfirmPaymentDetailsResult" type="{http://moneyapi.skypoint.com/}getTransactionStatusResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfirmPaymentDetailsResponse", propOrder = {
    "confirmPaymentDetailsResult"
})
public class ConfirmPaymentDetailsResponse {

    @XmlElement(name = "ConfirmPaymentDetailsResult")
    protected GetTransactionStatusResult confirmPaymentDetailsResult;

    /**
     * Gets the value of the confirmPaymentDetailsResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetTransactionStatusResult }
     *     
     */
    public GetTransactionStatusResult getConfirmPaymentDetailsResult() {
        return confirmPaymentDetailsResult;
    }

    /**
     * Sets the value of the confirmPaymentDetailsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetTransactionStatusResult }
     *     
     */
    public void setConfirmPaymentDetailsResult(GetTransactionStatusResult value) {
        this.confirmPaymentDetailsResult = value;
    }

}
