
package com.skypoint.moneyapi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BeneficiaryListResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BeneficiaryListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BeneficiaryListResult" type="{http://moneyapi.skypoint.com/}beneficiaryListResult" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BeneficiaryListResponse", propOrder = {
    "beneficiaryListResult"
})
public class BeneficiaryListResponse {

    @XmlElement(name = "BeneficiaryListResult")
    protected List<BeneficiaryListResult> beneficiaryListResult;

    /**
     * Gets the value of the beneficiaryListResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the beneficiaryListResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBeneficiaryListResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BeneficiaryListResult }
     * 
     * 
     */
    public List<BeneficiaryListResult> getBeneficiaryListResult() {
        if (beneficiaryListResult == null) {
            beneficiaryListResult = new ArrayList<BeneficiaryListResult>();
        }
        return this.beneficiaryListResult;
    }

}
