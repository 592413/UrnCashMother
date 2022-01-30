package com.recharge.yesbankmodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="yesbankcustomer")
@NamedQueries({
	@NamedQuery(name="getyesbankcustomerbyMobile",query="From YesbankCustomer where mobile=:mobile ")
	
})
public class YesbankCustomer {
	@Id
	private int id;
	private String mobile;
	private String name;
	
	
	public YesbankCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public YesbankCustomer(String mobile, String name) {
		super();
		this.mobile = mobile;
		this.name = name;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
