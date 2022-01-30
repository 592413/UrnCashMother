package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name="instantpaylog")
@NamedQueries({

	@NamedQuery(name="getInstantPayLogByrefnoandagentcode",query="From InstantPayLog where referenceno=:referenceno and agentcode=:agentcode"),
	@NamedQuery(name="getInstantPayLogBystatus",query="From InstantPayLog where status=:status  and date between :start_date and :end_date"),
	@NamedQuery(name="getInstantPayLogByrefno",query="From InstantPayLog where referenceno=:referenceno")
})
public class InstantPayLog {
	@Id
	private int id;
	private String referenceno;
	private String agentcode;
	private String status;
	private String type;
	private String apiremarks;
	private String date;
	private String time;
	private double amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReferenceno() {
		return referenceno;
	}
	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}
	public String getAgentcode() {
		return agentcode;
	}
	public void setAgentcode(String agentcode) {
		this.agentcode = agentcode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getApiremarks() {
		return apiremarks;
	}
	public void setApiremarks(String apiremarks) {
		this.apiremarks = apiremarks;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public InstantPayLog(String referenceno, String agentcode, String status, String type, String apiremarks,
			String date, String time, double amount) {
		super();
		this.referenceno = referenceno;
		this.agentcode = agentcode;
		this.status = status;
		this.type = type;
		this.apiremarks = apiremarks;
		this.date = date;
		this.time = time;
		this.amount = amount;
	}
	public InstantPayLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
