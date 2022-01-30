package com.recharge.icicidmtmodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="beneficiary")
@NamedQueries({
	 @NamedQuery(name="getBenebyMobile",
	 query="From Beneficiary where remmobile=:remmobile"),
	 @NamedQuery(name="getBenebyBeneMobile",
	 query="From Beneficiary where mobile=:mobile"),
	 @NamedQuery(name="getBenebyMobileandRem",
	 query="From Beneficiary where mobile=:mobile and remmobile=:remmobile"),
	 @NamedQuery(name="getBenebyaccountandRem",
	 query="From Beneficiary where account=:account and remmobile=:remmobile")
})
public class Beneficiary {
	@Id
	private int id;
	private String name;
	private String mobile;
	private String account;
	private String ifsc;
	private String remmobile;
	private boolean isVerified;
	private String razorPayContactId;
	private String razorPayFundAccountId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getRemmobile() {
		return remmobile;
	}
	public void setRemmobile(String remmobile) {
		this.remmobile = remmobile;
	}
	
	
	public boolean isVerified() {
		return isVerified;
	}
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	
	
	public String getRazorPayContactId() {
		return razorPayContactId;
	}
	public void setRazorPayContactId(String razorPayContactId) {
		this.razorPayContactId = razorPayContactId;
	}
	public String getRazorPayFundAccountId() {
		return razorPayFundAccountId;
	}
	public void setRazorPayFundAccountId(String razorPayFundAccountId) {
		this.razorPayFundAccountId = razorPayFundAccountId;
	}
	public Beneficiary(String name, String mobile, String account, String ifsc, String remmobile,boolean isVerified) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.account = account;
		this.ifsc = ifsc;
		this.remmobile = remmobile;
		this.isVerified = isVerified;
	}
	public Beneficiary() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
