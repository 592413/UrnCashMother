package com.bankopen.model;

public class PaymentCustom {
	private String bene_account_number;
	private String ifsc_code;
	private String recepient_name;
	private String email_id;
	private String mobile_number;
	private String debit_account_number;
	private String transaction_types_id;
	private String amount;
	private String merchant_ref_id;
	private String purpose;
	
	
	
	
	public PaymentCustom(String bene_account_number, String ifsc_code, String recepient_name, String email_id,
			String mobile_number, String debit_account_number, String transaction_types_id, String amount,
			String merchant_ref_id, String purpose) {
		super();
		this.bene_account_number = bene_account_number;
		this.ifsc_code = ifsc_code;
		this.recepient_name = recepient_name;
		this.email_id = email_id;
		this.mobile_number = mobile_number;
		this.debit_account_number = debit_account_number;
		this.transaction_types_id = transaction_types_id;
		this.amount = amount;
		this.merchant_ref_id = merchant_ref_id;
		this.purpose = purpose;
	}
	public String getBene_account_number() {
		return bene_account_number;
	}
	public void setBene_account_number(String bene_account_number) {
		this.bene_account_number = bene_account_number;
	}
	public String getIfsc_code() {
		return ifsc_code;
	}
	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}
	public String getRecepient_name() {
		return recepient_name;
	}
	public void setRecepient_name(String recepient_name) {
		this.recepient_name = recepient_name;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getDebit_account_number() {
		return debit_account_number;
	}
	public void setDebit_account_number(String debit_account_number) {
		this.debit_account_number = debit_account_number;
	}
	public String getTransaction_types_id() {
		return transaction_types_id;
	}
	public void setTransaction_types_id(String transaction_types_id) {
		this.transaction_types_id = transaction_types_id;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getMerchant_ref_id() {
		return merchant_ref_id;
	}
	public void setMerchant_ref_id(String merchant_ref_id) {
		this.merchant_ref_id = merchant_ref_id;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	
	

}
