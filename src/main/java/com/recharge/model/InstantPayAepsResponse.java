package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name="instantpayaepsresponse")
@NamedQueries({

	@NamedQuery(name="getinstantpayaepsreportByadmin",query="From InstantPayAepsResponse where date between :start_date and :end_date"),
	@NamedQuery(name="getinstantpayaepsreportByusername",query="From InstantPayAepsResponse where date between :start_date and :end_date and username=:username")
})
public class InstantPayAepsResponse {
	
	@Id
	private int id;
	private String ipay_id;
	private String agent_id;
	private String opr_id;
	private String status;
	private String res_code;
	private String res_msg;
	private double amount;
	private String type;
	private String username;
	private String agentcode;
	private String date;
	private String time;
	
	
	
	
	public InstantPayAepsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InstantPayAepsResponse(String ipay_id, String agent_id, String opr_id, String status, String res_code,
			String res_msg, double amount, String type, String username, String agentcode,String date,String time) {
		super();
		this.ipay_id = ipay_id;
		this.agent_id = agent_id;
		this.opr_id = opr_id;
		this.status = status;
		this.res_code = res_code;
		this.res_msg = res_msg;
		this.amount = amount;
		this.type = type;
		this.username = username;
		this.agentcode = agentcode;
		this.date = date;
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIpay_id() {
		return ipay_id;
	}
	public void setIpay_id(String ipay_id) {
		this.ipay_id = ipay_id;
	}
	public String getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}
	public String getOpr_id() {
		return opr_id;
	}
	public void setOpr_id(String opr_id) {
		this.opr_id = opr_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRes_code() {
		return res_code;
	}
	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}
	public String getRes_msg() {
		return res_msg;
	}
	public void setRes_msg(String res_msg) {
		this.res_msg = res_msg;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAgentcode() {
		return agentcode;
	}
	public void setAgentcode(String agentcode) {
		this.agentcode = agentcode;
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
