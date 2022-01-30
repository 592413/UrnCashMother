package com.recharge.customModel;

public class ImpsResponse {
	private String bene_mobile;
	private String bene_id;
	private double gst;
	private double charge;
	private double amount;
	private String bene_account;
	private String bene_ifsc;
	private String remitter_Name;
	private String  mobile;
	private String clientId;
	private String bankTransactionId;
	private String date;
	private String  time;
	private String status;
	private String message;
	
	
	
	
	public ImpsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}




	public ImpsResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}




	public ImpsResponse(String bene_mobile, String bene_id, double gst, double charge, double amount,
			String bene_account, String bene_ifsc, String remitter_Name, String mobile, String clientId,
			String bankTransactionId, String date, String time,String status,String message) {
		super();
		this.bene_mobile = bene_mobile;
		this.bene_id = bene_id;
		this.gst = gst;
		this.charge = charge;
		this.amount = amount;
		this.bene_account = bene_account;
		this.bene_ifsc = bene_ifsc;
		this.remitter_Name = remitter_Name;
		this.mobile = mobile;
		this.clientId = clientId;
		this.bankTransactionId = bankTransactionId;
		this.date = date;
		this.time = time;
		this.status = status;
		this.message = message;
	}
	
	
	
	
	public String getMessage() {
		return message;
	}




	public void setMessage(String message) {
		this.message = message;
	}




	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getBene_mobile() {
		return bene_mobile;
	}
	public void setBene_mobile(String bene_mobile) {
		this.bene_mobile = bene_mobile;
	}
	public String getBene_id() {
		return bene_id;
	}
	public void setBene_id(String bene_id) {
		this.bene_id = bene_id;
	}
	public double getGst() {
		return gst;
	}
	public void setGst(double gst) {
		this.gst = gst;
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getBene_account() {
		return bene_account;
	}
	public void setBene_account(String bene_account) {
		this.bene_account = bene_account;
	}
	public String getBene_ifsc() {
		return bene_ifsc;
	}
	public void setBene_ifsc(String bene_ifsc) {
		this.bene_ifsc = bene_ifsc;
	}
	public String getRemitter_Name() {
		return remitter_Name;
	}
	public void setRemitter_Name(String remitter_Name) {
		this.remitter_Name = remitter_Name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getBankTransactionId() {
		return bankTransactionId;
	}
	public void setBankTransactionId(String bankTransactionId) {
		this.bankTransactionId = bankTransactionId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
	
}
