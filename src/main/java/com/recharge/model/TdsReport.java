package com.recharge.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tdsreport")
public class TdsReport implements Serializable{
	@Id
	private int id;
	private String userId;
	private double amount;
	private double tds;
	private String naration;
	private String date;
	private String time;
	public TdsReport() {
		super();
	}
	
	
	
	public TdsReport(String userId, double amount, double tds, String naration, String date, String time) {
		super();
		this.userId = userId;
		this.amount = amount;
		this.tds = tds;
		this.naration = naration;
		this.date = date;
		this.time = time;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public double getTds() {
		return tds;
	}
	public void setTds(double tds) {
		this.tds = tds;
	}
	public String getNaration() {
		return naration;
	}
	public void setNaration(String naration) {
		this.naration = naration;
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
