package com.redbus.model;

import java.io.Serializable;

public class PassengerGSTDetails  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String registrationName;
	private String gstId;
    private String address;
    private String emailId;
	public String getRegistrationName() {
		return registrationName;
	}
	public void setRegistrationName(String registrationName) {
		this.registrationName = registrationName;
	}
	public String getGstId() {
		return gstId;
	}
	public void setGstId(String gstId) {
		this.gstId = gstId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Override
	public String toString() {
		return "PassengerGSTDetails [registrationName=" + registrationName + ", gstId=" + gstId + ", address=" + address
				+ ", emailId=" + emailId + "]";
	}
    
    
}
