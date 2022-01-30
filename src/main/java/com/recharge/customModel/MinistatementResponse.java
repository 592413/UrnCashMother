package com.recharge.customModel;

import java.util.List;

public class MinistatementResponse {
	private boolean status;
	private int statusCode;
	private String message;
	private String terminalId;
	private String  TransactionTime;
	private String transactionStatus;
	private double balanceAmount;
	private String bankRRN;
	private String merchantTxnId;
	private String TransactionId;
	private String bank;
	private String aadhar;
	private List<Ministatement> Ministatement;
	
	
	public MinistatementResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getTransactionTime() {
		return TransactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		TransactionTime = transactionTime;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public double getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public String getBankRRN() {
		return bankRRN;
	}
	public void setBankRRN(String bankRRN) {
		this.bankRRN = bankRRN;
	}
	public String getMerchantTxnId() {
		return merchantTxnId;
	}
	public void setMerchantTxnId(String merchantTxnId) {
		this.merchantTxnId = merchantTxnId;
	}
	public String getTransactionId() {
		return TransactionId;
	}
	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}
	public List<Ministatement> getMinistatement() {
		return Ministatement;
	}
	
	
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	public void setMinistatement(List<Ministatement> ministatement) {
		Ministatement = ministatement;
	}
	public MinistatementResponse(boolean status, int statusCode, String message) {
		super();
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
	}
	@Override
	public String toString() {
		return "MinistatementResponse [status=" + status + ", statusCode=" + statusCode + ", message=" + message
				+ ", terminalId=" + terminalId + ", TransactionTime=" + TransactionTime + ", transactionStatus="
				+ transactionStatus + ", balanceAmount=" + balanceAmount + ", bankRRN=" + bankRRN + ", merchantTxnId="
				+ merchantTxnId + ", TransactionId=" + TransactionId + ", Ministatement=" + Ministatement + "]";
	}
	
	
	
}
