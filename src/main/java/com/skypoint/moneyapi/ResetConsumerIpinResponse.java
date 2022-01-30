
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResetConsumerIpinResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResetConsumerIpinResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResetConsumerIpinResult" type="{http://moneyapi.skypoint.com/}updateStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResetConsumerIpinResponse", propOrder = {
    "resetConsumerIpinResult"
})
public class ResetConsumerIpinResponse {

    @XmlElement(name = "ResetConsumerIpinResult")
    protected UpdateStatus resetConsumerIpinResult;

    /**
     * Gets the value of the resetConsumerIpinResult property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateStatus }
     *     
     */
    public UpdateStatus getResetConsumerIpinResult() {
        return resetConsumerIpinResult;
    }

    /**
     * Sets the value of the resetConsumerIpinResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateStatus }
     *     
     */
    public void setResetConsumerIpinResult(UpdateStatus value) {
        this.resetConsumerIpinResult = value;
    }

}
