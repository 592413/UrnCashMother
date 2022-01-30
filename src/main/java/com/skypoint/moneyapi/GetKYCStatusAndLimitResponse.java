
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getKYCStatusAndLimitResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getKYCStatusAndLimitResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetKYCStatusAndLimitResult" type="{http://moneyapi.skypoint.com/}getKYCStatusAndLimitResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getKYCStatusAndLimitResponse", propOrder = {
    "getKYCStatusAndLimitResult"
})
public class GetKYCStatusAndLimitResponse {

    @XmlElement(name = "GetKYCStatusAndLimitResult")
    protected GetKYCStatusAndLimitResult getKYCStatusAndLimitResult;

    /**
     * Gets the value of the getKYCStatusAndLimitResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetKYCStatusAndLimitResult }
     *     
     */
    public GetKYCStatusAndLimitResult getGetKYCStatusAndLimitResult() {
        return getKYCStatusAndLimitResult;
    }

    /**
     * Sets the value of the getKYCStatusAndLimitResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetKYCStatusAndLimitResult }
     *     
     */
    public void setGetKYCStatusAndLimitResult(GetKYCStatusAndLimitResult value) {
        this.getKYCStatusAndLimitResult = value;
    }

}
