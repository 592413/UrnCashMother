package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="irctcuser")
@NamedQueries({
	@NamedQuery(name = "getIRCTCUserbyagentcode", query = "from IRCTCUser  where username=:username"),
	@NamedQuery(name = "getIRCTCUserbyirctcid", query = "from IRCTCUser  where irctcid=:irctcid")
	
	
})
public class IRCTCUser {
	@Id
	private int id;
	private String username;
	private String irctcid;
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
	public IRCTCUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getIrctcid() {
		return irctcid;
	}
	public void setIrctcid(String irctcid) {
		this.irctcid = irctcid;
	}
	public IRCTCUser(String username, String irctcid) {
		super();
		this.username = username;
		this.irctcid = irctcid;
	}
	
	
	
	
	

}
