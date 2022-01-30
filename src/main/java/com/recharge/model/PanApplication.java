package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="panapplication")
@NamedQueries({
	@NamedQuery(name = "getPanapplicationbyusername", query = "from PanApplication  where referenceno=:referenceno")
	
})
public class PanApplication {
	@Id
	private int id;
	private String username;
	private String uti_tid;
	private String pan_applicationid;
	private double op_bal;
	private double cl_bal;
	private double amount;
	private double charge;
	private String date;
	private String time;
	private String referenceno;
	private String status;
	
	
	
	
	public PanApplication() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public PanApplication(String username, String uti_tid, String pan_applicationid, double op_bal, double cl_bal,
			double amount, double charge, String date, String time, String referenceno, String status) {
		super();
		this.username = username;
		this.uti_tid = uti_tid;
		this.pan_applicationid = pan_applicationid;
		this.op_bal = op_bal;
		this.cl_bal = cl_bal;
		this.amount = amount;
		this.charge = charge;
		this.date = date;
		this.time = time;
		this.referenceno = referenceno;
		this.status = status;
	}


	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getUti_tid() {
		return uti_tid;
	}
	public void setUti_tid(String uti_tid) {
		this.uti_tid = uti_tid;
	}
	
	public String getPan_applicationid() {
		return pan_applicationid;
	}
	public void setPan_applicationid(String pan_applicationid) {
		this.pan_applicationid = pan_applicationid;
	}
	public double getOp_bal() {
		return op_bal;
	}
	public void setOp_bal(double op_bal) {
		this.op_bal = op_bal;
	}
	public double getCl_bal() {
		return cl_bal;
	}
	public void setCl_bal(double cl_bal) {
		this.cl_bal = cl_bal;
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
	public String getReferenceno() {
		return referenceno;
	}
	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}
	
	
	

}
