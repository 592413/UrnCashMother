package com.recharge.yesbankmodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="yesbankaepsresponse")
@Entity
@NamedQueries({
@NamedQuery(name="getyesbankaepsresponseforsettlement",query="From YesBankAEPSResponse where processingCode=:processingCode and date between :start_date and :end_date and agentcode=:agentcode and statusCode=:statusCode"),
@NamedQuery(name="getyesbankaepsresponsebyusername",query="From YesBankAEPSResponse where  date between :start_date and :end_date and username=:username"),
@NamedQuery(name="getyesbankaepsresponsebyadmin",query="From YesBankAEPSResponse where  date between :start_date and :end_date"),
@NamedQuery(name="getyesbankaepsresponsebyrefId",query="From YesBankAEPSResponse where referenceNo=:referenceNo"),
@NamedQuery(name="getyesbankaepsresponsebyrefIdandagentcode",query="From YesBankAEPSResponse where referenceNo=:referenceNo and agentcode=:agentcode")
})
public class YesBankAEPSResponse {
	
	@Id
	private int id;
	private String STAN;
	private String RRN;
	private String Aadhar;
	private String IIN;
	private String TxnAmount;
	private String ResponseCode;
	private String AccountType;
	private String BalanceType;
	private String CurrancyCode;
	private String BalanceIndicator;
	private String BalanceAmount;
	private String AccountTypeLedger;
	private String BalanceTypeLedger;
	private String CurrancyCodeLedger;
	private String BalanceIndicatorLedger;
	private String BalanceAmountLedger;
	private String AccountTypeActual;
	private String BalanceTypeActual;
	private String CurrancyCodeActual;
	private String BalanceIndicatorActual;
	private String BalanceAmountActual;
	private String Status;
	private String referenceNo;
	private double txnCharge;
	private double paidAmount;
	private String date;
	private String time;
	private String agentcode;
	private String username;
	private String processingCode;
	@Transient
	private String RESP_CODE;
	
	
	private String RESPONSE;
	
	@Transient
	private String name;
	
	@Transient
	private String mobile;
	
	
	
	
	
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




	public YesBankAEPSResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	public String getProcessingCode() {
		return processingCode;
	}




	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}




	public YesBankAEPSResponse(String sTAN, String rRN, String aadhar, String iIN, String txnAmount,
			String responseCode, String accountType, String balanceType, String currancyCode, String balanceIndicator,
			String balanceAmount, String accountTypeLedger, String balanceTypeLedger, String currancyCodeLedger,
			String balanceIndicatorLedger, String balanceAmountLedger, String accountTypeActual,
			String balanceTypeActual, String currancyCodeActual, String balanceIndicatorActual,
			String balanceAmountActual, String status, String referenceNo, double txnCharge, double paidAmount,
			String date, String time, String agentcode, String username) {
		super();
		STAN = sTAN;
		RRN = rRN;
		Aadhar = aadhar;
		IIN = iIN;
		TxnAmount = txnAmount;
		ResponseCode = responseCode;
		AccountType = accountType;
		BalanceType = balanceType;
		CurrancyCode = currancyCode;
		BalanceIndicator = balanceIndicator;
		BalanceAmount = balanceAmount;
		AccountTypeLedger = accountTypeLedger;
		BalanceTypeLedger = balanceTypeLedger;
		CurrancyCodeLedger = currancyCodeLedger;
		BalanceIndicatorLedger = balanceIndicatorLedger;
		BalanceAmountLedger = balanceAmountLedger;
		AccountTypeActual = accountTypeActual;
		BalanceTypeActual = balanceTypeActual;
		CurrancyCodeActual = currancyCodeActual;
		BalanceIndicatorActual = balanceIndicatorActual;
		BalanceAmountActual = balanceAmountActual;
		Status = status;
		this.referenceNo = referenceNo;
		this.txnCharge = txnCharge;
		this.paidAmount = paidAmount;
		this.date = date;
		this.time = time;
		this.agentcode = agentcode;
		this.username = username;
		
	}




	public YesBankAEPSResponse(String sTAN, String rRN, String aadhar, String iIN, String txnAmount,
			String responseCode, String accountType, String balanceType, String currancyCode, String balanceIndicator,
			String balanceAmount, String accountTypeLedger, String balanceTypeLedger, String currancyCodeLedger,
			String balanceIndicatorLedger, String balanceAmountLedger, String accountTypeActual,
			String balanceTypeActual, String currancyCodeActual, String balanceIndicatorActual,
			String balanceAmountActual, String status, String referenceNo, double txnCharge, double paidAmount,
			String date, String time, String agentcode, String username, String processingCode,
			String rESPONSE) {
		super();
		STAN = sTAN;
		RRN = rRN;
		Aadhar = aadhar;
		IIN = iIN;
		TxnAmount = txnAmount;
		ResponseCode = responseCode;
		AccountType = accountType;
		BalanceType = balanceType;
		CurrancyCode = currancyCode;
		BalanceIndicator = balanceIndicator;
		BalanceAmount = balanceAmount;
		AccountTypeLedger = accountTypeLedger;
		BalanceTypeLedger = balanceTypeLedger;
		CurrancyCodeLedger = currancyCodeLedger;
		BalanceIndicatorLedger = balanceIndicatorLedger;
		BalanceAmountLedger = balanceAmountLedger;
		AccountTypeActual = accountTypeActual;
		BalanceTypeActual = balanceTypeActual;
		CurrancyCodeActual = currancyCodeActual;
		BalanceIndicatorActual = balanceIndicatorActual;
		BalanceAmountActual = balanceAmountActual;
		Status = status;
		this.referenceNo = referenceNo;
		this.txnCharge = txnCharge;
		this.paidAmount = paidAmount;
		this.date = date;
		this.time = time;
		this.agentcode = agentcode;
		this.username = username;
		this.processingCode = processingCode;
		RESPONSE = rESPONSE;
		
	}




	public String getReferenceNo() {
		return referenceNo;
	}




	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}




	public String getRESP_CODE() {
		return RESP_CODE;
	}


	public void setRESP_CODE(String rESP_CODE) {
		RESP_CODE = rESP_CODE;
	}


	public String getRESPONSE() {
		return RESPONSE;
	}


	public void setRESPONSE(String rESPONSE) {
		RESPONSE = rESPONSE;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSTAN() {
		return STAN;
	}
	public void setSTAN(String sTAN) {
		STAN = sTAN;
	}
	public String getRRN() {
		return RRN;
	}
	public void setRRN(String rRN) {
		RRN = rRN;
	}
	public String getAadhar() {
		return Aadhar;
	}
	public void setAadhar(String aadhar) {
		Aadhar = aadhar;
	}
	public String getIIN() {
		return IIN;
	}
	public void setIIN(String iIN) {
		IIN = iIN;
	}
	public String getTxnAmount() {
		return TxnAmount;
	}
	public void setTxnAmount(String txnAmount) {
		TxnAmount = txnAmount;
	}
	public String getResponseCode() {
		return ResponseCode;
	}
	public void setResponseCode(String responseCode) {
		ResponseCode = responseCode;
	}
	public String getAccountType() {
		return AccountType;
	}
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	public String getBalanceType() {
		return BalanceType;
	}
	public void setBalanceType(String balanceType) {
		BalanceType = balanceType;
	}
	public String getCurrancyCode() {
		return CurrancyCode;
	}
	public void setCurrancyCode(String currancyCode) {
		CurrancyCode = currancyCode;
	}
	public String getBalanceIndicator() {
		return BalanceIndicator;
	}
	public void setBalanceIndicator(String balanceIndicator) {
		BalanceIndicator = balanceIndicator;
	}
	public String getBalanceAmount() {
		return BalanceAmount;
	}
	public void setBalanceAmount(String balanceAmount) {
		BalanceAmount = balanceAmount;
	}
	public String getAccountTypeLedger() {
		return AccountTypeLedger;
	}
	public void setAccountTypeLedger(String accountTypeLedger) {
		AccountTypeLedger = accountTypeLedger;
	}
	public String getBalanceTypeLedger() {
		return BalanceTypeLedger;
	}
	public void setBalanceTypeLedger(String balanceTypeLedger) {
		BalanceTypeLedger = balanceTypeLedger;
	}
	public String getCurrancyCodeLedger() {
		return CurrancyCodeLedger;
	}
	public void setCurrancyCodeLedger(String currancyCodeLedger) {
		CurrancyCodeLedger = currancyCodeLedger;
	}
	public String getBalanceIndicatorLedger() {
		return BalanceIndicatorLedger;
	}
	public void setBalanceIndicatorLedger(String balanceIndicatorLedger) {
		BalanceIndicatorLedger = balanceIndicatorLedger;
	}
	public String getBalanceAmountLedger() {
		return BalanceAmountLedger;
	}
	public void setBalanceAmountLedger(String balanceAmountLedger) {
		BalanceAmountLedger = balanceAmountLedger;
	}
	public String getAccountTypeActual() {
		return AccountTypeActual;
	}
	public void setAccountTypeActual(String accountTypeActual) {
		AccountTypeActual = accountTypeActual;
	}
	public String getBalanceTypeActual() {
		return BalanceTypeActual;
	}
	public void setBalanceTypeActual(String balanceTypeActual) {
		BalanceTypeActual = balanceTypeActual;
	}
	public String getCurrancyCodeActual() {
		return CurrancyCodeActual;
	}
	public void setCurrancyCodeActual(String currancyCodeActual) {
		CurrancyCodeActual = currancyCodeActual;
	}
	public String getBalanceIndicatorActual() {
		return BalanceIndicatorActual;
	}
	public void setBalanceIndicatorActual(String balanceIndicatorActual) {
		BalanceIndicatorActual = balanceIndicatorActual;
	}
	public String getBalanceAmountActual() {
		return BalanceAmountActual;
	}
	public void setBalanceAmountActual(String balanceAmountActual) {
		BalanceAmountActual = balanceAmountActual;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	public double getTxnCharge() {
		return txnCharge;
	}
	public void setTxnCharge(double txnCharge) {
		this.txnCharge = txnCharge;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
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
	public String getAgentcode() {
		return agentcode;
	}
	public void setAgentcode(String agentcode) {
		this.agentcode = agentcode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}




	@Override
	public String toString() {
		return "YesBankAEPSResponse [id=" + id + ", STAN=" + STAN + ", RRN=" + RRN + ", Aadhar=" + Aadhar + ", IIN="
				+ IIN + ", TxnAmount=" + TxnAmount + ", ResponseCode=" + ResponseCode + ", AccountType=" + AccountType
				+ ", BalanceType=" + BalanceType + ", CurrancyCode=" + CurrancyCode + ", BalanceIndicator="
				+ BalanceIndicator + ", BalanceAmount=" + BalanceAmount + ", AccountTypeLedger=" + AccountTypeLedger
				+ ", BalanceTypeLedger=" + BalanceTypeLedger + ", CurrancyCodeLedger=" + CurrancyCodeLedger
				+ ", BalanceIndicatorLedger=" + BalanceIndicatorLedger + ", BalanceAmountLedger=" + BalanceAmountLedger
				+ ", AccountTypeActual=" + AccountTypeActual + ", BalanceTypeActual=" + BalanceTypeActual
				+ ", CurrancyCodeActual=" + CurrancyCodeActual + ", BalanceIndicatorActual=" + BalanceIndicatorActual
				+ ", BalanceAmountActual=" + BalanceAmountActual + ", Status=" + Status + ", referenceNo=" + referenceNo
				+ ", txnCharge=" + txnCharge + ", paidAmount=" + paidAmount + ", date=" + date + ", time=" + time
				+ ", agentcode=" + agentcode + ", username=" + username + ", RESP_CODE=" + RESP_CODE + ", RESPONSE="
				+ RESPONSE + "]";
	}
	
	
	
	

}
