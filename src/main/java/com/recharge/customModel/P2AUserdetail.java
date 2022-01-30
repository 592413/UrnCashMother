package com.recharge.customModel;

public class P2AUserdetail {
	
	private int id;
	private String BeneAccNo;
	private String BeneIFSC;
	private String RemName;
	private String RemMobile;
	private String RetailerCode;
	private boolean active;
	private String userId;
	private String name;
	private String mobile;
	
	
	public P2AUserdetail(int id, String beneAccNo, String beneIFSC, String remName, String remMobile,
			String retailerCode, boolean active, String userId, String name, String mobile) {
		super();
		this.id = id;
		BeneAccNo = beneAccNo;
		BeneIFSC = beneIFSC;
		RemName = remName;
		RemMobile = remMobile;
		RetailerCode = retailerCode;
		this.active = active;
		this.userId = userId;
		this.name = name;
		this.mobile = mobile;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
	
}
