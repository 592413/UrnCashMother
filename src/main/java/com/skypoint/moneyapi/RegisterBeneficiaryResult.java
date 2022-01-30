
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registerBeneficiaryResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registerBeneficiaryResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RegisteredBeneficiaryID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RegistrationStatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registerBeneficiaryResult", propOrder = {
    "errorMessage",
    "registeredBeneficiaryID",
    "registrationStatus"
})
public class RegisterBeneficiaryResult {

    @XmlElement(name = "ErrorMessage")
    protected String errorMessage;
    @XmlElement(name = "RegisteredBeneficiaryID")
    protected int registeredBeneficiaryID;
    @XmlElement(name = "RegistrationStatus")
    protected boolean registrationStatus;

    /**
     * Gets the value of the errorMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

    /**
     * Gets the value of the registeredBeneficiaryID property.
     * 
     */
    public int getRegisteredBeneficiaryID() {
        return registeredBeneficiaryID;
    }

    /**
     * Sets the value of the registeredBeneficiaryID property.
     * 
     */
    public void setRegisteredBeneficiaryID(int value) {
        this.registeredBeneficiaryID = value;
    }

    /**
     * Gets the value of the registrationStatus property.
     * 
     */
    public boolean isRegistrationStatus() {
        return registrationStatus;
    }

    /**
     * Sets the value of the registrationStatus property.
     * 
     */
    public void setRegistrationStatus(boolean value) {
        this.registrationStatus = value;
    }

}
