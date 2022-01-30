package com.recharge.yesbankmodel;

public class BeneficiaryYesBank {

	private int id;
	private String mobile;
	private String name;
	private String account;
	private String bank;
	private String ifsc;
	private boolean otpverified;
	public boolean isOtpverified() {
		return otpverified;
	}
	public void setOtpverified(boolean otpverified) {
		this.otpverified = otpverified;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	@Override
	public String toString() {
		return "BeneficiaryYesBank [id=" + id + ", mobile=" + mobile + ", name=" + name + ", account=" + account
				+ ", bank=" + bank + ", ifsc=" + ifsc + "]";
	}
	
	
	
}
