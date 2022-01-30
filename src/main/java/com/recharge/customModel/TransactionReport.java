package com.recharge.customModel;

public class TransactionReport implements java.io.Serializable {
	private Integer transId;
	private String userId;
	private Double openingBal;
	private Double credit;
	private Double debit;
	private Double closingBal;
	private String narration;
	private String remarks;
	private String date;
	private String time;
	private Double adopbal;
	private Double adclbal;
	private String wlId;
	private String name;
	private String firmName;
	
	public TransactionReport() {
	}
	public TransactionReport(Integer transId, String userId, Double openingBal, Double credit, Double debit,
			Double closingBal, String narration, String remarks, String date, String time, Double adopbal,
			Double adclbal, String wlId, String name, String firmName) {
		this.transId = transId;
		this.userId = userId;
		this.openingBal = openingBal;
		this.credit = credit;
		this.debit = debit;
		this.closingBal = closingBal;
		this.narration = narration;
		this.remarks = remarks;
		this.date = date;
		this.time = time;
		this.adopbal = adopbal;
		this.adclbal = adclbal;
		this.wlId = wlId;
		this.name = name;
		this.firmName = firmName;
	}
	public Integer getTransId() {
		return transId;
	}
	public void setTransId(Integer transId) {
		this.transId = transId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Double getOpeningBal() {
		return openingBal;
	}
	public void setOpeningBal(Double openingBal) {
		this.openingBal = openingBal;
	}
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	public Double getDebit() {
		return debit;
	}
	public void setDebit(Double debit) {
		this.debit = debit;
	}
	public Double getClosingBal() {
		return closingBal;
	}
	public void setClosingBal(Double closingBal) {
		this.closingBal = closingBal;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public Double getAdopbal() {
		return adopbal;
	}
	public void setAdopbal(Double adopbal) {
		this.adopbal = adopbal;
	}
	public Double getAdclbal() {
		return adclbal;
	}
	public void setAdclbal(Double adclbal) {
		this.adclbal = adclbal;
	}
	public String getWlId() {
		return wlId;
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

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	
	
}
