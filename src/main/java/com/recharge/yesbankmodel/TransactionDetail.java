package com.recharge.yesbankmodel;

public class TransactionDetail {
	
	@Override
	public String toString() {
		return "TransactionDetail [REQUEST_REFERENCE_NO=" + REQUEST_REFERENCE_NO + ", TRANSFER_AMOUNT="
				+ TRANSFER_AMOUNT + ", PAID_AMOUNT=" + PAID_AMOUNT + ", RESPONSE_REFERENCE_NO=" + RESPONSE_REFERENCE_NO
				+ "]";
	}
	private String REQUEST_REFERENCE_NO;
	private double TRANSFER_AMOUNT;
	private String UNIQUE_RESPONSENO;
	private String BANK_REFERENCE_NO;
	private double TRANSACTIONN_FEE; 
	private String TXN_BENENAME;
	private double PAID_AMOUNT; 
	private String RESPONSE_REFERENCE_NO;
	
	
	
	public String getUNIQUE_RESPONSENO() {
		return UNIQUE_RESPONSENO;
	}
	public void setUNIQUE_RESPONSENO(String uNIQUE_RESPONSENO) {
		UNIQUE_RESPONSENO = uNIQUE_RESPONSENO;
	}
	public String getBANK_REFERENCE_NO() {
		return BANK_REFERENCE_NO;
	}
	public void setBANK_REFERENCE_NO(String bANK_REFERENCE_NO) {
		BANK_REFERENCE_NO = bANK_REFERENCE_NO;
	}
	public double getTRANSACTIONN_FEE() {
		return TRANSACTIONN_FEE;
	}
	public void setTRANSACTIONN_FEE(double tRANSACTIONN_FEE) {
		TRANSACTIONN_FEE = tRANSACTIONN_FEE;
	}
	public String getTXN_BENENAME() {
		return TXN_BENENAME;
	}
	public void setTXN_BENENAME(String tXN_BENENAME) {
		TXN_BENENAME = tXN_BENENAME;
	}
	
	
	public double getPAID_AMOUNT() {
		return PAID_AMOUNT;
	}
	public void setPAID_AMOUNT(double pAID_AMOUNT) {
		PAID_AMOUNT = pAID_AMOUNT;
	}
	public String getREQUEST_REFERENCE_NO() {
		return REQUEST_REFERENCE_NO;
	}
	public void setREQUEST_REFERENCE_NO(String rEQUEST_REFERENCE_NO) {
		REQUEST_REFERENCE_NO = rEQUEST_REFERENCE_NO;
	}
	
	public double getTRANSFER_AMOUNT() {
		return TRANSFER_AMOUNT;
	}
	public void setTRANSFER_AMOUNT(double tRANSFER_AMOUNT) {
		TRANSFER_AMOUNT = tRANSFER_AMOUNT;
	}
	public String getRESPONSE_REFERENCE_NO() {
		return RESPONSE_REFERENCE_NO;
	}
	public void setRESPONSE_REFERENCE_NO(String rESPONSE_REFERENCE_NO) {
		RESPONSE_REFERENCE_NO = rESPONSE_REFERENCE_NO;
	}
	
	

}
