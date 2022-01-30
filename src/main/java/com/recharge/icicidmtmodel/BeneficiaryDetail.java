package com.recharge.icicidmtmodel;

public class BeneficiaryDetail {
	private int id;
	private String name;
	private String mobile;
	private String account;
	private String ifsc;
	private String remmobile;
	private boolean verified;
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
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	
	
	public BeneficiaryDetail(int id, String name, String mobile, String account, String ifsc, String remmobile,boolean isVerified) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.account = account;
		this.ifsc = ifsc;
		this.remmobile = remmobile;
		this.verified = isVerified;
	}
	
	
	
	

}
