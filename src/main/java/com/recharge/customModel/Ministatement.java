package com.recharge.customModel;

public class Ministatement {
	private String date;
	private String txnType;
	private String amount;
	private String narration;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	public Ministatement(String date, String txnType, String amount, String narration) {
		super();
		this.date = date;
		this.txnType = txnType;
		this.amount = amount;
		this.narration = narration;
	}
	@Override
	public String toString() {
		return "Ministatement [date=" + date + ", txnType=" + txnType + ", amount=" + amount + ", narration="
				+ narration + "]";
	}
	
	
}
