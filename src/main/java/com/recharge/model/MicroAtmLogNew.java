package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name="microatmlognew")
@NamedQueries({

	@NamedQuery(name="getNewLogByrefno",query="From MicroAtmLogNew where referenceno=:referenceno"),
	@NamedQuery(name="getNewLogByrefnostatus",query="From MicroAtmLogNew where referenceno=:referenceno and status=:status")
})
public class MicroAtmLogNew {
	@Id	
	private int id;	
	private String referenceno;
	private String agentcode;
	private String status;
	private String type;
	private String userId;
	private String date;
	private String time;
	private String amount;
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

	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public MicroAtmLogNew() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MicroAtmLogNew(String referenceno, String agentcode, String status, String type, String userId, String date,
			String time, String amount) {
		super();
		this.referenceno = referenceno;
		this.agentcode = agentcode;
		this.status = status;
		this.type = type;
		this.userId = userId;
		this.date = date;
		this.time = time;
		this.amount = amount;
	}



}
