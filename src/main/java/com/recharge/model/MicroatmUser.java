package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "microatmuser")
@NamedQueries({ @NamedQuery(name = "getmicroatmUserbyUserid", query = "from MicroatmUser As ca where ca.userId=:userId")})
public class MicroatmUser {
	@Id
	private int id;
	private String microuser;
	private String micropassword;
	private String company;
	private String mid;
	private String tid;
	private String userId;
	private String status;
	private String remarks;
	private String date;
	private String time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMicrouser() {
		return microuser;
	}
	public void setMicrouser(String microuser) {
		this.microuser = microuser;
	}
	public String getMicropassword() {
		return micropassword;
	}
	public void setMicropassword(String micropassword) {
		this.micropassword = micropassword;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
	public MicroatmUser(String microuser, String micropassword, String company, String mid, String tid, String userId,
			String status, String remarks, String date, String time) {
		super();
		this.microuser = microuser;
		this.micropassword = micropassword;
		this.company = company;
		this.mid = mid;
		this.tid = tid;
		this.userId = userId;
		this.status = status;
		this.remarks = remarks;
		this.date = date;
		this.time = time;
	}
	public MicroatmUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}


	


