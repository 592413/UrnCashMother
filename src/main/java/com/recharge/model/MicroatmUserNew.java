package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "microatmusernew")
@NamedQueries({ @NamedQuery(name = "getmicroatmnewUserbyUserid", query = "from MicroatmUserNew As ca where ca.userId=:username")})
public class MicroatmUserNew {
	@Id
	private int id;
	private String microuser;
	private String micropassword;
	private String mid;
	private String userId;
	
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
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	
	public MicroatmUserNew(String microuser, String micropassword, String mid, String userId) {
		super();
		this.microuser = microuser;
		this.micropassword = micropassword;
		this.mid = mid;
		this.userId = userId;
	}
	public MicroatmUserNew() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
