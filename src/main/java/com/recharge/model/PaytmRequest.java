package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name="paytmrequest")
@NamedQueries({

	@NamedQuery(name="getPaytmRequestbyorderid",query="From PaytmRequest where order_id=:order_id and status=:status")
})
public class PaytmRequest {
	@Id
	private int id;
	private String order_id;
	private double amount; 
	private String status;
	private String userId;
	private String date;
	private String time;
	private String transactionid;
	private String bank_ref_num;
	
	
	
	
	public PaytmRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	
	public PaytmRequest(String order_id, double amount, String status, String userId, String date, String time,
			String transactionid, String bank_ref_num) {
		super();
		this.order_id = order_id;
		this.amount = amount;
		this.status = status;
		this.userId = userId;
		this.date = date;
		this.time = time;
		this.transactionid = transactionid;
		this.bank_ref_num = bank_ref_num;
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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


	public String getTransactionid() {
		return transactionid;
	}


	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}


	public String getBank_ref_num() {
		return bank_ref_num;
	}


	public void setBank_ref_num(String bank_ref_num) {
		this.bank_ref_num = bank_ref_num;
	}
	
	
	
	
	
	
	

}
