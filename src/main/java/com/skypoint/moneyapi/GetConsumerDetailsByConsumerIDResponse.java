
package com.skypoint.moneyapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetConsumerDetailsByConsumerIDResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetConsumerDetailsByConsumerIDResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetConsumerDetailsByConsumerIDResult" type="{http://moneyapi.skypoint.com/}consumerDetailResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetConsumerDetailsByConsumerIDResponse", propOrder = {
    "getConsumerDetailsByConsumerIDResult"
})
public class GetConsumerDetailsByConsumerIDResponse {

    @XmlElement(name = "GetConsumerDetailsByConsumerIDResult")
    protected ConsumerDetailResult getConsumerDetailsByConsumerIDResult;

    /**
     * Gets the value of the getConsumerDetailsByConsumerIDResult property.
     * 
     * @return
     *     possible object is
     *     {@link ConsumerDetailResult }
     *     
     */
    public ConsumerDetailResult getGetConsumerDetailsByConsumerIDResult() {
        return getConsumerDetailsByConsumerIDResult;
    }

    /**
     * Sets the value of the getConsumerDetailsByConsumerIDResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsumerDetailResult }
     *     
     */
    public void setGetConsumerDetailsByConsumerIDResult(ConsumerDetailResult value) {
        this.getConsumerDetailsByConsumerIDResult = value;
    }

}
