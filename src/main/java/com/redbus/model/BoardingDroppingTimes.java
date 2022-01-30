package com.redbus.model;

import java.io.Serializable;

public class BoardingDroppingTimes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long bpId;	
	private String bpName;	
	private String contactNumber;	
	private String location;		
	private String address;	
	private String landmark;		
	private String prime;		
	private long time;
	private String rbMasterId;
	public long getBpId() {
		return bpId;
	}
	public void setBpId(long bpId) {
		this.bpId = bpId;
	}
	public String getBpName() {
		return bpName;
	}
	public void setBpName(String bpName) {
		this.bpName = bpName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getPrime() {
		return prime;
	}
	public void setPrime(String prime) {
		this.prime = prime;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}	
	public String getRbMasterId() {
		return rbMasterId;
	}
	public void setRbMasterId(String rbMasterId) {
		this.rbMasterId = rbMasterId;
	}
	@Override
	public String toString() {
		return "BoardingDroppingTimes [bpId=" + bpId + ", bpName=" + bpName + ", contactNumber=" + contactNumber
				+ ", location=" + location + ", address=" + address + ", landmark=" + landmark + ", prime=" + prime
				+ ", time=" + time + ", rbMasterId=" + rbMasterId + "]";
	}
	
	
}
