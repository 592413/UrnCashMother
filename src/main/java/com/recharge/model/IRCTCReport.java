package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="irctcreport")
public class IRCTCReport {
	@Id
	private int id;
	private String WalletCode;
	private String UserID;
	private String TranID;
	private String currencyType;
	private String appCode;
	private String pymtMode;
	private String TranDate;
	private double TranAmount;
	private String BalanceRequestResponse;
	private String CoRelationID;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWalletCode() {
		return WalletCode;
	}
	public void setWalletCode(String walletCode) {
		WalletCode = walletCode;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getTranID() {
		return TranID;
	}
	public void setTranID(String tranID) {
		TranID = tranID;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getPymtMode() {
		return pymtMode;
	}
	public void setPymtMode(String pymtMode) {
		this.pymtMode = pymtMode;
	}
	public String getTranDate() {
		return TranDate;
	}
	public void setTranDate(String tranDate) {
		TranDate = tranDate;
	}
	public double getTranAmount() {
		return TranAmount;
	}
	public void setTranAmount(double tranAmount) {
		TranAmount = tranAmount;
	}
	public String getBalanceRequestResponse() {
		return BalanceRequestResponse;
	}
	public void setBalanceRequestResponse(String balanceRequestResponse) {
		BalanceRequestResponse = balanceRequestResponse;
	}
	public String getCoRelationID() {
		return CoRelationID;
	}
	public void setCoRelationID(String coRelationID) {
		CoRelationID = coRelationID;
	}
	public IRCTCReport(String walletCode, String userID, String tranID, String currencyType, String appCode,
			String pymtMode, String tranDate, double tranAmount, String balanceRequestResponse, String coRelationID) {
		super();
		WalletCode = walletCode;
		UserID = userID;
		TranID = tranID;
		this.currencyType = currencyType;
		this.appCode = appCode;
		this.pymtMode = pymtMode;
		TranDate = tranDate;
		TranAmount = tranAmount;
		BalanceRequestResponse = balanceRequestResponse;
		CoRelationID = coRelationID;
	}
	public IRCTCReport() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
	

}
