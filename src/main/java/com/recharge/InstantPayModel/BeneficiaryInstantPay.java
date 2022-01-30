package com.recharge.InstantPayModel;

public class BeneficiaryInstantPay {
	
	@Override
	public String toString() {
		return "BeneficiaryInstantPay [id=" + id + ", name=" + name + ", mobile=" + mobile + ", account=" + account
				+ ", bank=" + bank + ", status=" + status + ", last_success_date=" + last_success_date
				+ ", last_success_name=" + last_success_name + ", last_success_imps=" + last_success_imps + ", ifsc="
				+ ifsc + ", imps=" + imps + "]";
	}
	private String id;
	private String name;
	private String mobile;
	private String account;
	private String bank;
	private String status;
	private String last_success_date;
	private String last_success_name;
	private String last_success_imps;
	private String ifsc;
	private String imps;
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLast_success_date() {
		return last_success_date;
	}
	public void setLast_success_date(String last_success_date) {
		this.last_success_date = last_success_date;
	}
	public String getLast_success_name() {
		return last_success_name;
	}
	public void setLast_success_name(String last_success_name) {
		this.last_success_name = last_success_name;
	}
	public String getLast_success_imps() {
		return last_success_imps;
	}
	public void setLast_success_imps(String last_success_imps) {
		this.last_success_imps = last_success_imps;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getImps() {
		return imps;
	}
	public void setImps(String imps) {
		this.imps = imps;
	}
	

}
