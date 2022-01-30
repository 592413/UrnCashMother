
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResendOTPResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResendOTPResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResendOTPResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResendOTPResponse", propOrder = {
    "resendOTPResult"
})
public class ResendOTPResponse {

    @XmlElement(name = "ResendOTPResult")
    protected boolean resendOTPResult;

    /**
     * Gets the value of the resendOTPResult property.
     * 
     */
    public boolean isResendOTPResult() {
        return resendOTPResult;
    }

    /**
     * Sets the value of the resendOTPResult property.
     * 
     */
    public void setResendOTPResult(boolean value) {
        this.resendOTPResult = value;
    }

}
