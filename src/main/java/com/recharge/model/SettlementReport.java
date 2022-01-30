package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="settlementreport")


@NamedQueries({ 
	
	@NamedQuery(name = "getSettlementreportbyusername", query = "from SettlementReport where username=:username and date between :start_date and :end_date"),
	@NamedQuery(name = "getSettlementreportbyadmin", query = "from SettlementReport where type=:type and date between :start_date and :end_date"),
	@NamedQuery(name = "getSettlementreportbyadminwthOttype", query = "from SettlementReport where date between :start_date and :end_date")})
public class SettlementReport {
	@Id
	private int id;
	private String username;
	private double settleopbal;
	private double settleclbal;
	private double amount;
	private String date; 
	private String time;
	private String type;
	private String status;
	private String remarks;
	
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
	@Transient
	private String name;
	@Transient
	private String mobile;
	
	@Transient
	private String branch;
	@Transient
	private String account;
	@Transient
	private String ifsc;
	@Transient
	private String bank;
	
	
	
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
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
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	public SettlementReport(String username, double settleopbal, double settleclbal, double amount, String date,
			String time, String type, String status, String remarks) {
		super();
		this.username = username;
		this.settleopbal = settleopbal;
		this.settleclbal = settleclbal;
		this.amount = amount;
		this.date = date;
		this.time = time;
		this.type = type;
		this.status = status;
		this.remarks = remarks;
	}
	public SettlementReport() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public double getSettleopbal() {
		return settleopbal;
	}
	public void setSettleopbal(double settleopbal) {
		this.settleopbal = settleopbal;
	}
	public double getSettleclbal() {
		return settleclbal;
	}
	public void setSettleclbal(double settleclbal) {
		this.settleclbal = settleclbal;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
	

}
