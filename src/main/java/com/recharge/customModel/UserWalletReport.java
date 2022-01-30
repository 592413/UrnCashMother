package com.recharge.customModel;

public class UserWalletReport {
	private int id;
	private String cardnumber;
	private String cardholdernme;
	private String userId;
	private double amount;
	private double charge;
	private String status;
	private String remarks;
	private String date;
	private String time;
	private String uname;
	private String umobile;
	private String cardreferencenumber;
	private String applicantmobileno;
	public String getUmobile() {
		return umobile;
	}
	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}
	private double actualwallet;
	
	public UserWalletReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserWalletReport(int id,String cardnumber, String cardholdernme, String userId, double amount, double charge,
			String status, String remarks, String date, String time, String uname,String umobile,String cardreferencenumber,String applicantmobileno) {
		super();
		this.id = id;
		this.cardnumber = cardnumber;
		this.cardholdernme = cardholdernme;
		this.userId = userId;
		this.amount = amount;
		this.charge = charge;
		this.status = status;
		this.remarks = remarks;
		this.date = date;
		this.time = time;
		this.uname = uname;
		this.umobile = umobile;
		this.cardreferencenumber = cardreferencenumber;
		this.applicantmobileno = applicantmobileno;
	}
	
	
	public String getCardreferencenumber() {
		return cardreferencenumber;
	}
	public void setCardreferencenumber(String cardreferencenumber) {
		this.cardreferencenumber = cardreferencenumber;
	}
	public String getApplicantmobileno() {
		return applicantmobileno;
	}
	public void setApplicantmobileno(String applicantmobileno) {
		this.applicantmobileno = applicantmobileno;
	}
	public double getActualwallet() {
		return actualwallet;
	}
	public void setActualwallet(double actualwallet) {
		this.actualwallet = actualwallet;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public String getCardholdernme() {
		return cardholdernme;
	}
	public void setCardholdernme(String cardholdernme) {
		this.cardholdernme = cardholdernme;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	
}
