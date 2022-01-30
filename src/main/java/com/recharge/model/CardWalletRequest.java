package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "cardwalletrequest")
public class CardWalletRequest {

	@Id
	private int id;
	private String cardnumber;//1
	private String cardholdernme;//2
	private String userId;//3
	private double amount;//4
	private double charge;//5
	private String status;//6
	private String remarks;//7
	private String date;//8
	private String time;//9
	private String  applicationid;
		
	



	public CardWalletRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CardWalletRequest(String cardnumber, String cardholdernme, String userId, double amount, double charge,
			String status, String remarks, String date, String time,String applicationid) {
		super();
		this.cardnumber = cardnumber;//1
		this.cardholdernme = cardholdernme;
		this.userId = userId;
		this.amount = amount;
		this.charge = charge;
		this.status = status;
		this.remarks = remarks;
		this.date = date;
		this.time = time;
		this.applicationid = applicationid;
	}
	
	

	public String getApplicationid() {
		return applicationid;
	}

	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}

	public String getCardholdernme() {
		return cardholdernme;
	}

	public void setCardholdernme(String cardholdernme) {
		this.cardholdernme = cardholdernme;
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

}
