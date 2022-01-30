package com.recharge.icicidmtmodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name="remitter")
 @NamedQueries({
 @NamedQuery(name="getRembyMobile",
 query="From Remitter where remmobile=:remmobile") })
 
public class Remitter {
	@Id
	private int id;
	private String remmobile;
	private String name;
	private String lastname;
	private String kycstatus;
	private boolean otpverified;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRemmobile() {
		return remmobile;
	}
	public void setRemmobile(String remmobile) {
		this.remmobile = remmobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKycstatus() {
		return kycstatus;
	}
	public void setKycstatus(String kycstatus) {
		this.kycstatus = kycstatus;
	}
	
	
	
	public boolean isOtpverified() {
		return otpverified;
	}
	public void setOtpverified(boolean otpverified) {
		this.otpverified = otpverified;
	}
	public Remitter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Remitter(String remmobile, String name, String lastname, String kycstatus, boolean otpverified) {
		super();
		this.remmobile = remmobile;
		this.name = name;
		this.lastname = lastname;
		this.kycstatus = kycstatus;
		this.otpverified = otpverified;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	

	

}
