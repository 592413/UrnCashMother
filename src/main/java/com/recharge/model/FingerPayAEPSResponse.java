package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="fingerpayaepsresponse")
@NamedQueries({
@NamedQuery(name="getFingerPayReportByusername",query="From FingerPayAEPSResponse where date between :start_date and :end_date and username=:username")

})
public class FingerPayAEPSResponse {
    @Id
	private int id;
	private String terminalId;
	private String date;
	private String time;
	private String bankRRN;
	private String transactionType;
	private String EncoreTransactionId;
	private String merchantTxnId;
	private double transactionAmount;
	private double balanceAmount;
	private String Status;
	private String username;
	
	
	@Transient
	private String name;
	
	@Transient
	private String mobile;
	
	
	public FingerPayAEPSResponse(String terminalId, String date, String time, String bankRRN, String transactionType,
			String encoreTransactionId, String merchantTxnId, double transactionAmount, double balanceAmount,
			String status, String username) {
		super();
		this.terminalId = terminalId;
		this.date = date;
		this.time = time;
		this.bankRRN = bankRRN;
		this.transactionType = transactionType;
		EncoreTransactionId = encoreTransactionId;
		this.merchantTxnId = merchantTxnId;
		this.transactionAmount = transactionAmount;
		this.balanceAmount = balanceAmount;
		Status = status;
		this.username = username;
	}
	public FingerPayAEPSResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
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
	
	public String getBankRRN() {
		return bankRRN;
	}
	public void setBankRRN(String bankRRN) {
		this.bankRRN = bankRRN;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getEncoreTransactionId() {
		return EncoreTransactionId;
	}
	public void setEncoreTransactionId(String encoreTransactionId) {
		EncoreTransactionId = encoreTransactionId;
	}
	public String getMerchantTxnId() {
		return merchantTxnId;
	}
	public void setMerchantTxnId(String merchantTxnId) {
		this.merchantTxnId = merchantTxnId;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public double getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
