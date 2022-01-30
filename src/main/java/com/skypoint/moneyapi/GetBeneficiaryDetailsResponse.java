
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetBeneficiaryDetailsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetBeneficiaryDetailsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BeneficiaryResult" type="{http://moneyapi.skypoint.com/}beneficiaryDetails" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetBeneficiaryDetailsResponse", propOrder = {
    "beneficiaryResult"
})
public class GetBeneficiaryDetailsResponse {

    @XmlElement(name = "BeneficiaryResult")
    protected BeneficiaryDetails beneficiaryResult;

    /**
     * Gets the value of the beneficiaryResult property.
     * 
     * @return
     *     possible object is
     *     {@link BeneficiaryDetails }
     *     
     */
    public BeneficiaryDetails getBeneficiaryResult() {
        return beneficiaryResult;
    }

    /**
     * Sets the value of the beneficiaryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BeneficiaryDetails }
     *     
     */
    public void setBeneficiaryResult(BeneficiaryDetails value) {
        this.beneficiaryResult = value;
    }

}
