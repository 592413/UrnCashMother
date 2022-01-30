
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CheckTransactionStatusResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckTransactionStatusResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CheckTransactionStatusResult" type="{http://moneyapi.skypoint.com/}getTransactionStatusResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckTransactionStatusResponse", propOrder = {
    "checkTransactionStatusResult"
})
public class CheckTransactionStatusResponse {

    @XmlElement(name = "CheckTransactionStatusResult")
    protected GetTransactionStatusResult checkTransactionStatusResult;

    /**
     * Gets the value of the checkTransactionStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetTransactionStatusResult }
     *     
     */
    public GetTransactionStatusResult getCheckTransactionStatusResult() {
        return checkTransactionStatusResult;
    }

    /**
     * Sets the value of the checkTransactionStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetTransactionStatusResult }
     *     
     */
    public void setCheckTransactionStatusResult(GetTransactionStatusResult value) {
        this.checkTransactionStatusResult = value;
    }

}
