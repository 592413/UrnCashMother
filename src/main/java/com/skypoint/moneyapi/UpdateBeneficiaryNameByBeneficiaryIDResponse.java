
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdateBeneficiaryNameByBeneficiaryIDResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateBeneficiaryNameByBeneficiaryIDResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UpdateBeneficiaryNameByBeneficiaryIDResult" type="{http://moneyapi.skypoint.com/}updateStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateBeneficiaryNameByBeneficiaryIDResponse", propOrder = {
    "updateBeneficiaryNameByBeneficiaryIDResult"
})
public class UpdateBeneficiaryNameByBeneficiaryIDResponse {

    @XmlElement(name = "UpdateBeneficiaryNameByBeneficiaryIDResult")
    protected UpdateStatus updateBeneficiaryNameByBeneficiaryIDResult;

    /**
     * Gets the value of the updateBeneficiaryNameByBeneficiaryIDResult property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateStatus }
     *     
     */
    public UpdateStatus getUpdateBeneficiaryNameByBeneficiaryIDResult() {
        return updateBeneficiaryNameByBeneficiaryIDResult;
    }

    /**
     * Sets the value of the updateBeneficiaryNameByBeneficiaryIDResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateStatus }
     *     
     */
    public void setUpdateBeneficiaryNameByBeneficiaryIDResult(UpdateStatus value) {
        this.updateBeneficiaryNameByBeneficiaryIDResult = value;
    }

}
