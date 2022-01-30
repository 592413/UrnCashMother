package com.recharge.customModel;

public class EncoreAEPSResponse {
	private boolean status;
	private int statusCode;
	private String message;
	private String terminalId;
	private String date;
	private String time;
	private String transactionStatus;
	private String bankRRN;
	private String transactionType;
	private String EncoreTransactionId;
	private String merchantTxnId;
	private String transactionAmount;
	private String balanceAmount;
	
	
	public EncoreAEPSResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EncoreAEPSResponse(boolean status, int statusCode, String message) {
		super();
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
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
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
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
	@Override
	public String toString() {
		return "EncoreAEPSResponse [status=" + status + ", statusCode=" + statusCode + ", message=" + message
				+ ", terminalId=" + terminalId + ", date=" + date + ", time=" + time + ", transactionStatus="
				+ transactionStatus + ", bankRRN=" + bankRRN + ", transactionType=" + transactionType
				+ ", EncoreTransactionId=" + EncoreTransactionId + ", merchantTxnId=" + merchantTxnId
				+ ", transactionAmount=" + transactionAmount + ", balanceAmount=" + balanceAmount + "]";
	}
	
	

}
