package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="microatmresponsenew")
@NamedQueries({
@NamedQuery(name="getMicroAtmResponseNewByusername",query="From MicroAtmResponseNew where date between :start_date and :end_date and username=:username")

})
public class MicroAtmResponseNew {
	@Id
	private int id;
	private String cardno;
	private String date;
	private String time;
	private String bankName;
	private String cardtype;
	private String rrn;
	private String referenceno;
	private String username;
	private double amount;
	private double balanceAmount;
	private String type;
	private String  status;
	private String remarks;
	
	@Transient
	private String name;
	
	@Transient
	private String mobile;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
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
	
	public String getRrn() {
		return rrn;
	}
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	public String getReferenceno() {
		return referenceno;
	}
	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	public double getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	
	
	
	public MicroAtmResponseNew(String cardno, String date, String time, String bankName, String cardtype, String rrn,
			String referenceno, String username, double amount, double balanceAmount, String type, String status,
			String remarks) {
		super();
		this.cardno = cardno;
		this.date = date;
		this.time = time;
		this.bankName = bankName;
		this.cardtype = cardtype;
		this.rrn = rrn;
		this.referenceno = referenceno;
		this.username = username;
		this.amount = amount;
		this.balanceAmount = balanceAmount;
		this.type = type;
		this.status = status;
		this.remarks = remarks;
	}
	public MicroAtmResponseNew() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
