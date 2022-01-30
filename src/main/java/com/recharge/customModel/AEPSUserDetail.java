package com.recharge.customModel;

public class AEPSUserDetail {

	private String userId;
	private String password;
	private Integer roleId;
	
	private String userName;
	private String name;
	private String firmName;
	private String address;
	private String city;
	private String pinCode;
	
	
	private String mobile;
	private String state;
	private String uplineId;
	private String email;
	private String country;
	private String createdDate;
	private String createdTime;
	private Double balance;
	private String status;
	private String delFlag;
	private String wlId;
	private String tranPin;
	private double lockedAmount;
//	private String allotNo;
	private  String api;
	private  String agentcode;
	
	private double settlementbalance;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getWlId() {
		return wlId;
	}
	public void setWlId(String wlId) {
		this.wlId = wlId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUplineId() {
		return uplineId;
	}
	public void setUplineId(String uplineId) {
		this.uplineId = uplineId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getTranPin() {
		return tranPin;
	}
	public void setTranPin(String tranPin) {
		this.tranPin = tranPin;
	}
	public double getLockedAmount() {
		return lockedAmount;
	}
	public void setLockedAmount(double lockedAmount) {
		this.lockedAmount = lockedAmount;
	}
	
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public String getAgentcode() {
		return agentcode;
	}
	public void setAgentcode(String agentcode) {
		this.agentcode = agentcode;
	}
	
	
	
	public double getSettlementbalance() {
		return settlementbalance;
	}
	public void setSettlementbalance(double settlementbalance) {
		this.settlementbalance = settlementbalance;
	}
	
	public AEPSUserDetail(String userId, String password, Integer roleId, String userName, String name, String firmName,
			String address, String city, String pinCode, String mobile, String state, String uplineId, String email,
			String country, String createdDate, String createdTime, Double balance, String status, String delFlag,
			String wlId, String tranPin, double lockedAmount, String api, String agentcode) {
		super();
		this.userId = userId;
		this.password = password;
		this.roleId = roleId;
		this.userName = userName;
		this.name = name;
		this.firmName = firmName;
		this.address = address;
		this.city = city;
		this.pinCode = pinCode;
		this.mobile = mobile;
		this.state = state;
		this.uplineId = uplineId;
		this.email = email;
		this.country = country;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
		this.balance = balance;
		this.status = status;
		this.delFlag = delFlag;
		this.wlId = wlId;
		this.tranPin = tranPin;
		this.lockedAmount = lockedAmount;
		this.api = api;
		this.agentcode = agentcode;
	}
	public AEPSUserDetail(String userId, String password, Integer roleId, String userName, String name, String firmName,
			String address, String city, String pinCode, String mobile, String state, String uplineId, String email,
			String country, String createdDate, String createdTime, Double balance, String status, String delFlag,
			String wlId, String tranPin, double lockedAmount, String api, String agentcode, double settlementbalance) {
		super();
		this.userId = userId;
		this.password = password;
		this.roleId = roleId;
		this.userName = userName;
		this.name = name;
		this.firmName = firmName;
		this.address = address;
		this.city = city;
		this.pinCode = pinCode;
		this.mobile = mobile;
		this.state = state;
		this.uplineId = uplineId;
		this.email = email;
		this.country = country;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
		this.balance = balance;
		this.status = status;
		this.delFlag = delFlag;
		this.wlId = wlId;
		this.tranPin = tranPin;
		this.lockedAmount = lockedAmount;
		this.api = api;
		this.agentcode = agentcode;
		this.settlementbalance = settlementbalance;
	}
	
	
}
