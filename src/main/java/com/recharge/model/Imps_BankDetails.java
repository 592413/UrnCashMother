package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "imps_bankdetails")
@NamedQueries({

	@NamedQuery(name="getBankDetailsByIFSC",query="From Imps_BankDetails where IFSC_CODE=:IFSC_CODE")
})
public class Imps_BankDetails implements java.io.Serializable {
	
	private Integer Bank_id;
	private String Bank_Name;
	private String IFSC_CODE;
	private String Account_NO_Limit;
	private String Bank_Type;
	private String Account_Verification;
	private String Status;
	public Imps_BankDetails() {
		super();
	}
	public Imps_BankDetails(Integer bank_id, String bank_Name, String iFSC_CODE, String account_NO_Limit,
			String bank_Type, String account_Verification, String status) {
		
		Bank_id = bank_id;
		Bank_Name = bank_Name;
		IFSC_CODE = iFSC_CODE;
		Account_NO_Limit = account_NO_Limit;
		Bank_Type = bank_Type;
		Account_Verification = account_Verification;
		Status = status;
	}
	@Id
	public Integer getBank_id() {
		return Bank_id;
	}
	public void setBank_id(Integer bank_id) {
		Bank_id = bank_id;
	}
	public String getBank_Name() {
		return Bank_Name;
	}
	public void setBank_Name(String bank_Name) {
		Bank_Name = bank_Name;
	}
	public String getIFSC_CODE() {
		return IFSC_CODE;
	}
	public void setIFSC_CODE(String iFSC_CODE) {
		IFSC_CODE = iFSC_CODE;
	}
	public String getAccount_NO_Limit() {
		return Account_NO_Limit;
	}
	public void setAccount_NO_Limit(String account_NO_Limit) {
		Account_NO_Limit = account_NO_Limit;
	}
	public String getBank_Type() {
		return Bank_Type;
	}
	public void setBank_Type(String bank_Type) {
		Bank_Type = bank_Type;
	}
	public String getAccount_Verification() {
		return Account_Verification;
	}
	public void setAccount_Verification(String account_Verification) {
		Account_Verification = account_Verification;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	

}
