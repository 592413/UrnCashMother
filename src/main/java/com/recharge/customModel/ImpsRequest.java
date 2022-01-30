package com.recharge.customModel;

public class ImpsRequest {
	private String BeneAccNo;
	private String BeneIFSC;
	private String Amount;
	private String RemName;
	private String RemMobile;
	private String RetailerCode;
	private String TranRefNo;
	private String AID;
	private String Username;
	private String Password;
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
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
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
	
	public String getTranRefNo() {
		return TranRefNo;
	}
	public void setTranRefNo(String tranRefNo) {
		TranRefNo = tranRefNo;
	}
	
	
	public String getAID() {
		return AID;
	}
	public void setAID(String aID) {
		AID = aID;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public ImpsRequest(String beneAccNo, String beneIFSC, String amount, String remName, String remMobile,
			String retailerCode, String tranRefNo, String aID, String username, String password) {
		super();
		BeneAccNo = beneAccNo;
		BeneIFSC = beneIFSC;
		Amount = amount;
		RemName = remName;
		RemMobile = remMobile;
		RetailerCode = retailerCode;
	
		TranRefNo = tranRefNo;
		AID = aID;
		Username = username;
		Password = password;
	}
	
	
}
