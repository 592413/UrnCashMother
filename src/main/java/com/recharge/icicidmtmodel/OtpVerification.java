package com.recharge.icicidmtmodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="otpverfication")
@NamedQueries({
	 @NamedQuery(name="getOtpbyMobile",
	 query="From OtpVerification where mobile=:mobile and date=:date and status=:status"),
	 @NamedQuery(name="checkOtpbyMobile",
	 query="From OtpVerification where mobile=:mobile and date=:date and status=:status and otp=:otp")})
public class OtpVerification {
	@Id
	private int id;
	private String mobile;
	private String otp;
	private String date;
	private long time;
	private String status;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public OtpVerification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OtpVerification(String mobile, String otp, String date, long time,String status) {
		super();
		this.mobile = mobile;
		this.otp = otp;
		this.date = date;
		this.time = time;
		this.status = status;
	}
	
	
	
	

}
