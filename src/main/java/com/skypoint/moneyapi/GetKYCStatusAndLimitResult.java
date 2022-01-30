
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getKYCStatusAndLimitResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getKYCStatusAndLimitResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="availableLimit" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="consumerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fullKYC" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getKYCStatusAndLimitResult", propOrder = {
    "availableLimit",
    "consumerId",
    "fullKYC"
})
public class GetKYCStatusAndLimitResult {

    protected double availableLimit;
    protected int consumerId;
    protected boolean fullKYC;

    /**
     * Gets the value of the availableLimit property.
     * 
     */
    public double getAvailableLimit() {
        return availableLimit;
    }

    /**
     * Sets the value of the availableLimit property.
     * 
     */
    public void setAvailableLimit(double value) {
        this.availableLimit = value;
    }

    /**
     * Gets the value of the consumerId property.
     * 
     */
    public int getConsumerId() {
        return consumerId;
    }

    /**
     * Sets the value of the consumerId property.
     * 
     */
    public void setConsumerId(int value) {
        this.consumerId = value;
    }

    /**
     * Gets the value of the fullKYC property.
     * 
     */
    public boolean isFullKYC() {
        return fullKYC;
    }

    /**
     * Sets the value of the fullKYC property.
     * 
     */
    public void setFullKYC(boolean value) {
        this.fullKYC = value;
    }

}
