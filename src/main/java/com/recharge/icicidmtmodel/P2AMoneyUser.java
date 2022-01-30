package com.recharge.icicidmtmodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "p2amoneyuser")
@NamedQueries({ @NamedQuery(name = "getP2AUserbyuserId", query = "from P2AMoneyUser  where userId= :userId")})
public class P2AMoneyUser {
	@Id
	private int id;
	private String BeneAccNo;
	private String BeneIFSC;
	private String RemName;
	private String RemMobile;
	private String RetailerCode;
	private boolean active;
	private String userId;
	
	
	
	public P2AMoneyUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public P2AMoneyUser(String beneAccNo, String beneIFSC, String remName, String remMobile, String retailerCode,
			boolean active, String userId) {
		super();
		BeneAccNo = beneAccNo;
		BeneIFSC = beneIFSC;
		RemName = remName;
		RemMobile = remMobile;
		RetailerCode = retailerCode;
		this.active = active;
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBeneAccNo() {
		return BeneAccNo;
	}
	public void setBeneAccNo(String beneAccNo) {
		BeneAccNo = beneAccNo;
	}
	public String getBeneIFSC() {
		return BeneIFSC;
	}
	public void setBeneIFSC(String beneIFSC) {
		BeneIFSC = beneIFSC;
	}
	public String getRemName() {
		return RemName;
	}
	public void setRemName(String remName) {
		RemName = remName;
	}
	public String getRemMobile() {
		return RemMobile;
	}
	public void setRemMobile(String remMobile) {
		RemMobile = remMobile;
	}
	public String getRetailerCode() {
		return RetailerCode;
	}
	public void setRetailerCode(String retailerCode) {
		RetailerCode = retailerCode;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
