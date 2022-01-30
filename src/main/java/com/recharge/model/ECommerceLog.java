package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="ecommercelog")
@Entity
public class ECommerceLog {

	@Id
	private int id;
	private String username;
	private double amount;
	private String date;
	private String time;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ECommerceLog(String username, double amount, String date, String time, String status) {
		super();
		this.username = username;
		this.amount = amount;
		this.date = date;
		this.time = time;
		this.status = status;
	}
	public ECommerceLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	


}
