
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetTransactionStatusResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetTransactionStatusResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetTransactionStatusResultResult" type="{http://moneyapi.skypoint.com/}getTransactionStatusResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetTransactionStatusResponse", propOrder = {
    "getTransactionStatusResultResult"
})
public class GetTransactionStatusResponse {

    @XmlElement(name = "GetTransactionStatusResultResult")
    protected GetTransactionStatusResult getTransactionStatusResultResult;

    /**
     * Gets the value of the getTransactionStatusResultResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetTransactionStatusResult }
     *     
     */
    public GetTransactionStatusResult getGetTransactionStatusResultResult() {
        return getTransactionStatusResultResult;
    }

    /**
     * Sets the value of the getTransactionStatusResultResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetTransactionStatusResult }
     *     
     */
    public void setGetTransactionStatusResultResult(GetTransactionStatusResult value) {
        this.getTransactionStatusResultResult = value;
    }

}
