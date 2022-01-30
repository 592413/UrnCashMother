package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="ecommerceuser")
@NamedQueries({
	@NamedQuery(name = "getECommerceUser", query = "from ECommerceUser  where username=:username "),
	@NamedQuery(name = "getECommerceUserbyecommerceid", query = "from ECommerceUser  where ecommerceid=:ecommerceid")
	
	
})
public class ECommerceUser {
	@Id
	private int id;
	private String username;
	private String ecommerceid;
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
	public String getEcommerceid() {
		return ecommerceid;
	}
	public void setEcommerceid(String ecommerceid) {
		this.ecommerceid = ecommerceid;
	}
	
	
	public ECommerceUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ECommerceUser(String username, String ecommerceid) {
		super();
		this.username = username;
		this.ecommerceid = ecommerceid;
	}
	
	
	
	
	

}
