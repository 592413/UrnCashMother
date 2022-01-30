package com.skyflight.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="gstflight")
public class GstFlight implements Serializable{
	@Id
	private String userId;
	private String companyNAme;
	private String companyAddress;
	private String gst;
	private String pan;
	
	public GstFlight() {
		super();
	}
	public GstFlight(String userId, String companyNAme, String companyAddress, String gst, String pan) {
		super();
		this.userId = userId;
		this.companyNAme = companyNAme;
		this.companyAddress = companyAddress;
		this.gst = gst;
		this.pan = pan;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCompanyNAme() {
		return companyNAme;
	}
	public void setCompanyNAme(String companyNAme) {
		this.companyNAme = companyNAme;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	
	

}
