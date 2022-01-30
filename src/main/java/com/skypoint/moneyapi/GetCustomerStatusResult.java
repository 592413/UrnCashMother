
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getCustomerStatusResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getCustomerStatusResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CustomerID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IsIMPSServiceAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IsRegistered" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCustomerStatusResult", propOrder = {
    "customerID",
    "isIMPSServiceAllowed",
    "isRegistered"
})
public class GetCustomerStatusResult {

    @XmlElement(name = "CustomerID")
    protected int customerID;
    @XmlElement(name = "IsIMPSServiceAllowed")
    protected boolean isIMPSServiceAllowed;
    @XmlElement(name = "IsRegistered")
    protected boolean isRegistered;

    /**
     * Gets the value of the customerID property.
     * 
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Sets the value of the customerID property.
     * 
     */
    public void setCustomerID(int value) {
        this.customerID = value;
    }

    /**
     * Gets the value of the isIMPSServiceAllowed property.
     * 
     */
    public boolean isIsIMPSServiceAllowed() {
        return isIMPSServiceAllowed;
    }

    /**
     * Sets the value of the isIMPSServiceAllowed property.
     * 
     */
    public void setIsIMPSServiceAllowed(boolean value) {
        this.isIMPSServiceAllowed = value;
    }

    /**
     * Gets the value of the isRegistered property.
     * 
     */
    public boolean isIsRegistered() {
        return isRegistered;
    }

    /**
     * Sets the value of the isRegistered property.
     * 
     */
    public void setIsRegistered(boolean value) {
        this.isRegistered = value;
    }

}
