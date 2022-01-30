
package com.skypoint.moneyapi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubmitPaymentIPINResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubmitPaymentIPINResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SubmitPaymentIPINResult" type="{http://moneyapi.skypoint.com/}getTransactionStatusResult" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubmitPaymentIPINResponse", propOrder = {
    "submitPaymentIPINResult"
})
public class SubmitPaymentIPINResponse {

    @XmlElement(name = "SubmitPaymentIPINResult")
    protected List<GetTransactionStatusResult> submitPaymentIPINResult;

    /**
     * Gets the value of the submitPaymentIPINResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the submitPaymentIPINResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubmitPaymentIPINResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetTransactionStatusResult }
     * 
     * 
     */
    public List<GetTransactionStatusResult> getSubmitPaymentIPINResult() {
        if (submitPaymentIPINResult == null) {
            submitPaymentIPINResult = new ArrayList<GetTransactionStatusResult>();
        }
        return this.submitPaymentIPINResult;
    }

}
