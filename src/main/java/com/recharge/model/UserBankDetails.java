package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="userbankdetail")
@NamedQueries({
	
	@NamedQuery(name="getBankdetailsbyusername",query="From UserBankDetails where username=:username")
	
})
public class UserBankDetails  {
	@Id
	private int id;
	private String mobile;
	private String email;
	private String name;
	private String branch;
	private String account;
	private String ifsc;
	private String bank;
	private String username;
	private String status;
	
	public UserBankDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public UserBankDetails(String mobile, String email, String name, String branch, String account, String ifsc,
			String bank, String username,String status) {
		super();
		this.mobile = mobile;
		this.email = email;
		this.name = name;
		this.branch = branch;
		this.account = account;
		this.ifsc = ifsc;
		this.bank = bank;
		this.username = username;
		this.status = status;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	

}
