package com.recharge.customModel;

import java.io.Serializable;

public class InsuranceReport implements Serializable{
	private Integer id;
	private String userId;
	private String insTid;
	private String operatorCode;
	private String policyHolderName;
	private String policyNumber;
	private String dob;
	private Double amount;
	private String custMobile;
	private String wlId;
	private String status;
	private String recieptNumber;
	private String attachedFlag;
	private String submitDate;
	private String submitTime;
	private String name;
	private String mobile;
	private String serviceProvider;

	public InsuranceReport() {
	}

	public InsuranceReport(Integer id, String userId, String insTid, String operatorCode, String policyHolderName,
			String policyNumber, String dob, Double amount, String custMobile, String wlId, String status,
			String recieptNumber, String attachedFlag, String submitDate, String submitTime, String name,
			String mobile, String serviceProvider) {
		this.id = id;
		this.userId = userId;
		this.insTid = insTid;
		this.operatorCode = operatorCode;
		this.policyHolderName = policyHolderName;
		this.policyNumber = policyNumber;
		this.dob = dob;
		this.amount = amount;
		this.custMobile = custMobile;
		this.wlId = wlId;
		this.status = status;
		this.recieptNumber = recieptNumber;
		this.attachedFlag = attachedFlag;
		this.submitDate = submitDate;
		this.submitTime = submitTime;
		this.name = name;
		this.mobile = mobile;
		this.serviceProvider = serviceProvider;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInsTid() {
		return insTid;
	}

	public void setInsTid(String insTid) {
		this.insTid = insTid;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getPolicyHolderName() {
		return policyHolderName;
	}

	public void setPolicyHolderName(String policyHolderName) {
		this.policyHolderName = policyHolderName;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public String getWlId() {
		return wlId;
	}

	public void setWlId(String wlId) {
		this.wlId = wlId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRecieptNumber() {
		return recieptNumber;
	}

	public void setRecieptNumber(String recieptNumber) {
		this.recieptNumber = recieptNumber;
	}

	public String getAttachedFlag() {
		return attachedFlag;
	}

	public void setAttachedFlag(String attachedFlag) {
		this.attachedFlag = attachedFlag;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
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

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

}
