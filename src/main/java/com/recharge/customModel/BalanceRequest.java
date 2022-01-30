package com.recharge.customModel;

public class BalanceRequest implements java.io.Serializable {

	private Integer requestId;
	private String wlId;
	private String userName;
	private String requestUser;
	private String accName;
	private String bankName;
	private String branchName;
	private String ifsccode;
	private String accountNo;
	private String paymentMode;
	private String bankTransactionId;
	private String remarks;
	private String paymentDate;
	private Double currentBalance;
	private Double amount;
	private String date;
	private String time;
	private String status;
	private String name;
	private String mobile;

	public BalanceRequest() {
	}

	public BalanceRequest(Integer requestId,String wlId, String userName, String requestUser, String accName, String bankName,
			String branchName, String ifsccode, String accountNo, String paymentMode, String bankTransactionId,
			String remarks, String paymentDate, Double currentBalance, Double amount, String date, String time,
			String status, String name, String mobile) {
		this.requestId = requestId;
		this.wlId = wlId;
		this.userName = userName;
		this.requestUser = requestUser;
		this.accName = accName;
		this.bankName = bankName;
		this.branchName = branchName;
		this.ifsccode = ifsccode;
		this.accountNo = accountNo;
		this.paymentMode = paymentMode;
		this.bankTransactionId = bankTransactionId;
		this.remarks = remarks;
		this.paymentDate = paymentDate;
		this.currentBalance = currentBalance;
		this.amount = amount;
		this.date = date;
		this.time = time;
		this.status = status;
		this.name = name;
		this.mobile = mobile;
	}

	public Integer getRequestId() {
		return this.requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getWlId() {
		return this.wlId;
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

	public String getRequestUser() {
		return this.requestUser;
	}

	public void setRequestUser(String requestUser) {
		this.requestUser = requestUser;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getIfsccode() {
		return this.ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getBankTransactionId() {
		return this.bankTransactionId;
	}

	public void setBankTransactionId(String bankTransactionId) {
		this.bankTransactionId = bankTransactionId;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Double getCurrentBalance() {
		return this.currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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
