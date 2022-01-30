
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetBeneficiaryStatusResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetBeneficiaryStatusResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetBeneficiaryStatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetBeneficiaryStatusResponse", propOrder = {
    "getBeneficiaryStatus"
})
public class GetBeneficiaryStatusResponse {

    @XmlElement(name = "GetBeneficiaryStatus")
    protected boolean getBeneficiaryStatus;

    /**
     * Gets the value of the getBeneficiaryStatus property.
     * 
     */
    public boolean isGetBeneficiaryStatus() {
        return getBeneficiaryStatus;
    }

    /**
     * Sets the value of the getBeneficiaryStatus property.
     * 
     */
    public void setGetBeneficiaryStatus(boolean value) {
        this.getBeneficiaryStatus = value;
    }

}
