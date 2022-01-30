package com.recharge.icicidmtmodel;

import java.util.ArrayList;
import java.util.List;


public class RemitterDetail {
	private int id;
	private String remmobile;
	private String name;
	private String kycstatus;
	private boolean otpverified;
	private List<BeneficiaryDetail> beneficiaryList=new ArrayList<BeneficiaryDetail>();
	//private double Limit;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRemmobile() {
		return remmobile;
	}
	public void setRemmobile(String remmobile) {
		this.remmobile = remmobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKycstatus() {
		return kycstatus;
	}
	public void setKycstatus(String kycstatus) {
		this.kycstatus = kycstatus;
	}
	public boolean isOtpverified() {
		return otpverified;
	}
	public void setOtpverified(boolean otpverified) {
		this.otpverified = otpverified;
	}
	
public List<BeneficiaryDetail> getBeneficiaryList() {
		return beneficiaryList;
	}
	public void setBeneficiaryList(List<BeneficiaryDetail> beneficiaryList) {
		this.beneficiaryList = beneficiaryList;
	}
	//	public double getLimit() {
//		return Limit;
//	}
//	public void setLimit(double limit) {
//		Limit = limit;
//	}
	public RemitterDetail(int id, String remmobile, String name, String kycstatus, boolean otpverified,
			List<BeneficiaryDetail> beneficiaryList) {
		super();
		this.id = id;
		this.remmobile = remmobile;
		this.name = name;
		this.kycstatus = kycstatus;
		this.otpverified = otpverified;
		beneficiaryList = beneficiaryList;
		//Limit = limit;
	}
	

	
	
	
	
	
}
