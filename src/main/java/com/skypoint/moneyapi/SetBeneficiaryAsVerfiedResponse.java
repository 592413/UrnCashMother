
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SetBeneficiaryAsVerfiedResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SetBeneficiaryAsVerfiedResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SetBeneficiaryAsVerfiedResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetBeneficiaryAsVerfiedResponse", propOrder = {
    "setBeneficiaryAsVerfiedResult"
})
public class SetBeneficiaryAsVerfiedResponse {

    @XmlElement(name = "SetBeneficiaryAsVerfiedResult")
    protected boolean setBeneficiaryAsVerfiedResult;

    /**
     * Gets the value of the setBeneficiaryAsVerfiedResult property.
     * 
     */
    public boolean isSetBeneficiaryAsVerfiedResult() {
        return setBeneficiaryAsVerfiedResult;
    }

    /**
     * Sets the value of the setBeneficiaryAsVerfiedResult property.
     * 
     */
    public void setSetBeneficiaryAsVerfiedResult(boolean value) {
        this.setBeneficiaryAsVerfiedResult = value;
    }

}
