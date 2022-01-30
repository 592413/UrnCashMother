
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegisterBeneficiaryResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegisterBeneficiaryResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RegisterBeneficiaryResult" type="{http://moneyapi.skypoint.com/}registerBeneficiaryResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegisterBeneficiaryResponse", propOrder = {
    "registerBeneficiaryResult"
})
public class RegisterBeneficiaryResponse {

    @XmlElement(name = "RegisterBeneficiaryResult")
    protected RegisterBeneficiaryResult registerBeneficiaryResult;

    /**
     * Gets the value of the registerBeneficiaryResult property.
     * 
     * @return
     *     possible object is
     *     {@link RegisterBeneficiaryResult }
     *     
     */
    public RegisterBeneficiaryResult getRegisterBeneficiaryResult() {
        return registerBeneficiaryResult;
    }

    /**
     * Sets the value of the registerBeneficiaryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegisterBeneficiaryResult }
     *     
     */
    public void setRegisterBeneficiaryResult(RegisterBeneficiaryResult value) {
        this.registerBeneficiaryResult = value;
    }

}
