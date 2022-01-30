package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="rblresponse")
@NamedQueries({
@NamedQuery(name="getpaymonkresponseforsettlement",query="From PaymonkResponse where processingCode=:processingCode and date between :start_date and :end_date and agentcode=:agentcode and statusCode=:statusCode"),
@NamedQuery(name="getpaymonkresponsebyagentId",query="From PaymonkResponse where  date between :start_date and :end_date and agentcode=:agentcode"),
@NamedQuery(name="getpaymonkresponsebyadmin",query="From PaymonkResponse where  date between :start_date and :end_date"),
@NamedQuery(name="getpaymonkresponsebyrefId",query="From PaymonkResponse where referenceNo=:referenceNo"),
@NamedQuery(name="getpaymonkresponsebyrefIdandagentcode",query="From PaymonkResponse where referenceNo=:referenceNo and agentcode=:agentcode")
})
public class PaymonkResponse {
	@Override
	public String toString() {
		return "PaymonkResponse [id=" + id + ", payerId=" + payerId + ", payertype=" + payertype + ", payeeId="
				+ payeeId + ", payeetype=" + payeetype + ", txnType=" + txnType + ", orderId=" + orderId + ", amount="
				+ amount + ", txnId=" + txnId + ", balance=" + balance + ", orderStatus=" + orderStatus
				+ ", paymentStatus=" + paymentStatus + ", requestId=" + requestId + ", stan=" + stan + ", rrn=" + rrn
				+ ", bankAuth=" + bankAuth + ", processingCode=" + processingCode + ", accountBalance=" + accountBalance
				+ ", bankResponseCode=" + bankResponseCode + ", bankResponseMsg=" + bankResponseMsg + ", terminalId="
				+ terminalId + ", agentId=" + agentId + ", aadharNumber=" + aadharNumber + ", dateTime=" + dateTime
				+ ", statusCode=" + statusCode + ", statusMessage=" + statusMessage + ", commissionAmt=" + commissionAmt
				+ ", gstAmt=" + gstAmt + ", tdsAmt=" + tdsAmt + ", date=" + date + ", time=" + time + ", referenceNo="
				+ referenceNo + ", agentcode=" + agentcode + "]";
	}
	@Id
	private int id;
	private String payerId;
	private String payertype;
	private String payeeId;
	private String payeetype;
	private String txnType;
	private String orderId;
	private double amount;
	private String txnId;
	private String balance;
	private String orderStatus;
	private String paymentStatus;
	private String requestId;
	private String stan;
	private String rrn;
	private String bankAuth;
	private String processingCode;
	private String accountBalance;
	private String bankResponseCode;
	private String bankResponseMsg;
	private String terminalId;
	private String agentId;
	private String aadharNumber;
	private String dateTime;
	private String statusCode;
	private String statusMessage;
	private double commissionAmt;
	private double gstAmt;
	private double tdsAmt;
	private String date;
	private String time;
	private String referenceNo;
	private String agentcode;
	
	@Transient
	private String name;
	
	@Transient
	private String username;
	@Transient
	private String mobile;
	
	@Transient
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getCommissionAmt() {
		return commissionAmt;
	}
	public void setCommissionAmt(double commissionAmt) {
		this.commissionAmt = commissionAmt;
	}
	public double getGstAmt() {
		return gstAmt;
	}
	public void setGstAmt(double gstAmt) {
		this.gstAmt = gstAmt;
	}
	public double getTdsAmt() {
		return tdsAmt;
	}
	public void setTdsAmt(double tdsAmt) {
		this.tdsAmt = tdsAmt;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public PaymonkResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getAgentcode() {
		return agentcode;
	}
	public void setAgentcode(String agentcode) {
		this.agentcode = agentcode;
	}
	public PaymonkResponse(String payerId, String payertype, String payeeId, String payeetype, String txnType,
			String orderId, double amount, String txnId, String balance, String orderStatus, String paymentStatus,
			String requestId, String stan, String rrn, String bankAuth, String processingCode, String accountBalance,
			String bankResponseCode, String bankResponseMsg, String terminalId, String agentId, String aadharNumber,
			String dateTime, String statusCode, String statusMessage, double commissionAmt, double gstAmt,
			double tdsAmt, String date, String time, String referenceNo, String agentcode) {
		super();
		this.payerId = payerId;
		this.payertype = payertype;
		this.payeeId = payeeId;
		this.payeetype = payeetype;
		this.txnType = txnType;
		this.orderId = orderId;
		this.amount = amount;
		this.txnId = txnId;
		this.balance = balance;
		this.orderStatus = orderStatus;
		this.paymentStatus = paymentStatus;
		this.requestId = requestId;
		this.stan = stan;
		this.rrn = rrn;
		this.bankAuth = bankAuth;
		this.processingCode = processingCode;
		this.accountBalance = accountBalance;
		this.bankResponseCode = bankResponseCode;
		this.bankResponseMsg = bankResponseMsg;
		this.terminalId = terminalId;
		this.agentId = agentId;
		this.aadharNumber = aadharNumber;
		this.dateTime = dateTime;
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.commissionAmt = commissionAmt;
		this.gstAmt = gstAmt;
		this.tdsAmt = tdsAmt;
		this.date = date;
		this.time = time;
		this.referenceNo = referenceNo;
		this.agentcode = agentcode;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPayerId() {
		return payerId;
	}
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	public String getPayertype() {
		return payertype;
	}
	public void setPayertype(String payertype) {
		this.payertype = payertype;
	}
	public String getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}
	public String getPayeetype() {
		return payeetype;
	}
	public void setPayeetype(String payeetype) {
		this.payeetype = payeetype;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getStan() {
		return stan;
	}
	public void setStan(String stan) {
		this.stan = stan;
	}
	public String getRrn() {
		return rrn;
	}
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	public String getBankAuth() {
		return bankAuth;
	}
	public void setBankAuth(String bankAuth) {
		this.bankAuth = bankAuth;
	}
	public String getProcessingCode() {
		return processingCode;
	}
	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}
	public String getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getBankResponseCode() {
		return bankResponseCode;
	}
	public void setBankResponseCode(String bankResponseCode) {
		this.bankResponseCode = bankResponseCode;
	}
	public String getBankResponseMsg() {
		return bankResponseMsg;
	}
	public void setBankResponseMsg(String bankResponseMsg) {
		this.bankResponseMsg = bankResponseMsg;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getdateTime() {
		return dateTime;
	}
	public void setdateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	
	
	
	

}
