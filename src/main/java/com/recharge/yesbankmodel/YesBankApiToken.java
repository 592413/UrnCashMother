package com.recharge.yesbankmodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="yesbankapitoken")
public class YesBankApiToken {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String token;
	
	
	public YesBankApiToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public YesBankApiToken(String token) {
		super();
		this.token = token;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	

}
