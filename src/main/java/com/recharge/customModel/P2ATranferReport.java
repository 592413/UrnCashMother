package com.recharge.customModel;

public class P2ATranferReport {

	private int id;
	private String userId;
	private String BankRRN;
	private String TranRefNo;
	private double amount;
	private String date;
	private String time;
	private String status;
	private String BeneAccNo;
	private String BeneIFSC;
	private String  RemName;
	private String RemMobile;
	private String RetailerCode;
	private String uname;
	private String mobile;
	
	
	public P2ATranferReport(int id, String userId, String bankRRN, String tranRefNo, double amount, String date,
			String time, String status, String beneAccNo, String beneIFSC, String remName, String remMobile,
			String retailerCode, String uname, String mobile) {
		super();
		this.id = id;
		this.userId = userId;
		BankRRN = bankRRN;
		TranRefNo = tranRefNo;
		this.amount = amount;
		this.date = date;
		this.time = time;
		this.status = status;
		BeneAccNo = beneAccNo;
		BeneIFSC = beneIFSC;
		RemName = remName;
		RemMobile = remMobile;
		RetailerCode = retailerCode;
		this.uname = uname;
		this.mobile = mobile;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBankRRN() {
		return BankRRN;
	}
	public void setBankRRN(String bankRRN) {
		BankRRN = bankRRN;
	}
	public String getTranRefNo() {
		return TranRefNo;
	}
	public void setTranRefNo(String tranRefNo) {
		TranRefNo = tranRefNo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
	
}
