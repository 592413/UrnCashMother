
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubmitPaymentDetailsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubmitPaymentDetailsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SubmitPaymentDetailsResult" type="{http://moneyapi.skypoint.com/}submitPaymentDetailsResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubmitPaymentDetailsResponse", propOrder = {
    "submitPaymentDetailsResult"
})
public class SubmitPaymentDetailsResponse {

    @XmlElement(name = "SubmitPaymentDetailsResult")
    protected SubmitPaymentDetailsResult submitPaymentDetailsResult;

    /**
     * Gets the value of the submitPaymentDetailsResult property.
     * 
     * @return
     *     possible object is
     *     {@link SubmitPaymentDetailsResult }
     *     
     */
    public SubmitPaymentDetailsResult getSubmitPaymentDetailsResult() {
        return submitPaymentDetailsResult;
    }

    /**
     * Sets the value of the submitPaymentDetailsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubmitPaymentDetailsResult }
     *     
     */
    public void setSubmitPaymentDetailsResult(SubmitPaymentDetailsResult value) {
        this.submitPaymentDetailsResult = value;
    }

}
