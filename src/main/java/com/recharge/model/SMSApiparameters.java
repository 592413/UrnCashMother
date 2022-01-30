package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="smsapiparameters")
public class SMSApiparameters {
	
	@Id
	private int id;
	private String baseurl;
	private String username;
	private String password;
	private String destination;
	private String source;
	private String message;
	private String staticparameters;
	private String staticparametervalues;
	
	
	public SMSApiparameters() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SMSApiparameters(String baseurl, String username, String password, String destination, String source,
			String message, String staticparameters, String staticparametervalues) {
		super();
		this.baseurl = baseurl;
		this.username = username;
		this.password = password;
		this.destination = destination;
		this.source = source;
		this.message = message;
		this.staticparameters = staticparameters;
		this.staticparametervalues = staticparametervalues;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBaseurl() {
		return baseurl;
	}
	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStaticparameters() {
		return staticparameters;
	}
	public void setStaticparameters(String staticparameters) {
		this.staticparameters = staticparameters;
	}
	public String getStaticparametervalues() {
		return staticparametervalues;
	}
	public void setStaticparametervalues(String staticparametervalues) {
		this.staticparametervalues = staticparametervalues;
	}
	
	
	
	
	

}
