package com.recharge.customModel;

import java.io.Serializable;

public class ResellerDetails implements Serializable {

	private String userId;
	private String password;
	private Integer roleId;
	private String userName;
	private String name;
	private String firmName;
	private String address;
	private String city;
	private String pinCode;
	private String state;
	private String country;
	private String mobile;
	private String uplineId;
	private String email;
	private String createdDate;
	private String createdTime;
	private Double balance;
	private String status;
	private String delFlag;
	private String wlId;
	
	
	private String tranPin;
	private double lockedAmount;
	private String poweredBy;
	private String poweredByLink;
	private String pageTitle;

	public ResellerDetails() {
	}

	
	public ResellerDetails(String userId, String password, Integer roleId, String userName, String name,
			String firmName, String address, String city, String pinCode, String state, String country, String mobile,
			String uplineId, String email, String createdDate, String createdTime, Double balance, String status,
			String delFlag, String wlId, String tranPin, double lockedAmount, String poweredBy, String poweredByLink,
			String pageTitle) {
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
		this.state = state;
		this.country = country;
		this.mobile = mobile;
		this.uplineId = uplineId;
		this.email = email;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
		this.balance = balance;
		this.status = status;
		this.delFlag = delFlag;
		this.wlId = wlId;
		this.tranPin = tranPin;
		this.lockedAmount = lockedAmount;
		this.poweredBy = poweredBy;
		this.poweredByLink = poweredByLink;
		this.pageTitle = pageTitle;
	}


	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return this.roleId;
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
		return this.userName;
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
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPinCode() {
		return this.pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUplineId() {
		return this.uplineId;
	}

	public void setUplineId(String uplineId) {
		this.uplineId = uplineId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPoweredBy() {
		return poweredBy;
	}

	public void setPoweredBy(String poweredBy) {
		this.poweredBy = poweredBy;
	}

	public String getPoweredByLink() {
		return poweredByLink;
	}

	public void setPoweredByLink(String poweredByLink) {
		this.poweredByLink = poweredByLink;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

}
