package com.recharge.icicidmtmodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="impssettlement")
@NamedQueries({
	@NamedQuery(name="getImpsSettlementTranRefNo",query="From ImpsSettlement where TranRefNo=:TranRefNo and userId=:userId")
})
public class ImpsSettlement {
	@Id
	private int id;
	private String userId;
	private String BankRRN;
	private String TranRefNo;
	private double amount;
	private String date;
	private String time;
	private String status;
	
	
	public ImpsSettlement() {
		super();
	}
	public ImpsSettlement(String userId, String bankRRN, String tranRefNo, double amount, String date, String time,
			String status) {
		super();
		this.userId = userId;
		BankRRN = bankRRN;
		TranRefNo = tranRefNo;
		this.amount = amount;
		this.date = date;
		this.time = time;
		this.status = status;
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
	public String getBankRRN() {
		return BankRRN;
	}
	public void setBankRRN(String bankRRN) {
		BankRRN = bankRRN;
	}
	public String getTranRefNo() {
		return TranRefNo;
	}
	public void setTranRefNo(String tranRefNo) {
		TranRefNo = tranRefNo;
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
	
	
	

}
