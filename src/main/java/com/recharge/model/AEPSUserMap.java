package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="aepsusermap")
@NamedQueries({
	@NamedQuery(name = "getUsernamebyagentcode", query = "from AEPSUserMap  where agentcode=:agentcode and api=:api"),
	@NamedQuery(name = "getAEPSbyusernameandapi", query = "from AEPSUserMap  where username=:username and api=:api"),
	@NamedQuery(name = "getAEPSbyusername", query = "from AEPSUserMap  where username=:username")
	
	
})
public class AEPSUserMap {
	
	
	@Id
	private int id;
	private  String username;
	private  String api;
	private  String agentcode;
	
	
	public AEPSUserMap() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public AEPSUserMap(String username, String api, String agentcode) {
		super();
		this.username = username;
		this.api = api;
		this.agentcode = agentcode;
	}



	public String getAgentcode() {
		return agentcode;
	}

	public void setAgentcode(String agentcode) {
		this.agentcode = agentcode;
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
	
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	
	
	@Override
	public String toString() {
		return "AEPSUserMap [id=" + id + ", username=" + username + ", api=" + api + ", agentcode=" + agentcode + "]";
	}
	
	

}
