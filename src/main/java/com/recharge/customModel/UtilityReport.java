package com.recharge.customModel;

import java.io.Serializable;

public class UtilityReport implements Serializable{

	private Integer utilityId;
	private String transactionId;
	private Integer operatorId;
	private String userName;
	private Integer serviceType;
	private String serviceName;
	private String customerName;
	private String customerMobile;
	private String accountNo;
	private String billDueDate;
	private Double amount;
	private String submitDate;
	private String submitTime;
	private String status;
	private String wlId;
	private String name;
	private String mobile;
	
	public UtilityReport() {
	}

	
	public UtilityReport(Integer utilityId, String transactionId, Integer operatorId, String userName,
			Integer serviceType, String serviceName, String customerName, String customerMobile, String accountNo,
			String billDueDate, Double amount, String submitDate, String submitTime, String status, String wlId,
			String name, String mobile) {
		this.utilityId = utilityId;
		this.transactionId = transactionId;
		this.operatorId = operatorId;
		this.userName = userName;
		this.serviceType = serviceType;
		this.serviceName = serviceName;
		this.customerName = customerName;
		this.customerMobile = customerMobile;
		this.accountNo = accountNo;
		this.billDueDate = billDueDate;
		this.amount = amount;
		this.submitDate = submitDate;
		this.submitTime = submitTime;
		this.status = status;
		this.wlId = wlId;
		this.name = name;
		this.mobile = mobile;
	}


	public Integer getUtilityId() {
		return this.utilityId;
	}

	public void setUtilityId(Integer utilityId) {
		this.utilityId = utilityId;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return this.customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBillDueDate() {
		return this.billDueDate;
	}

	public void setBillDueDate(String billDueDate) {
		this.billDueDate = billDueDate;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getSubmitDate() {
		return this.submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
	
	public String getWlId() {
		return this.wlId;
	}

	public void setWlId(String wlId) {
		this.wlId = wlId;
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
