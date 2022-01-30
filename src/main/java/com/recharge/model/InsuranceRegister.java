package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="insuranceregister")
public class InsuranceRegister {
	@Id
	private String userId;
	private String fname;
	private String lastname;
	private String mobile;
	private String email;
	public InsuranceRegister() {
		super();
	}
	public InsuranceRegister(String userId, String fname, String lastname, String mobile, String email) {
		super();
		this.userId = userId;
		this.fname = fname;
		this.lastname = lastname;
		this.mobile = mobile;
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
