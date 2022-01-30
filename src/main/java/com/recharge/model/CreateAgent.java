package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "createagent")

@NamedQueries({ @NamedQuery(name = "getagentdetailsById", query = "from CreateAgent As ca where ca.requestId= :requestId")})
public class CreateAgent implements java.io.Serializable{
	
	private Integer id;
	private String UMobileNo;
	private String Password;
	private String psaname;
	private String location;
	private String pincode;
	private String phone1;
	private String phone2;
	private String emailid;
	private String pan;
	private String dob;
	private String aadhaar;
	private String date;
	private String time;
	private String panuser_id;
	private String panpassword;
	private String requestId;
	private String state;
	private String status;
	private String userId;
	private String district;
	public CreateAgent() {
		super();
	}
	public CreateAgent(String uMobileNo, String password, String psaname, String location, String pincode,
			String phone1, String phone2, String emailid, String pan, String dob, String aadhaar, String date,
			String time, String panuser_id, String panpassword, String requestId, String state, String status, String userId, String district) {
		
		UMobileNo = uMobileNo;
		Password = password;
		this.psaname = psaname;
		this.location = location;
		this.pincode = pincode;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.emailid = emailid;
		this.pan = pan;
		this.dob = dob;
		this.aadhaar = aadhaar;
		this.date = date;
		this.time = time;
		this.panuser_id = panuser_id;
		this.panpassword = panpassword;
		this.requestId = requestId;
		this.state = state;
		this.status = status;
		this.userId = userId;
		this.district = district;
	}
	
	
	public CreateAgent(Integer id, String uMobileNo, String password, String psaname, String location, String pincode,
			String phone1, String phone2, String emailid, String pan, String dob, String aadhaar, String date,
			String time, String panuser_id, String panpassword, String requestId, String state, String status,
			String userId, String district) {
		
		this.id = id;
		UMobileNo = uMobileNo;
		Password = password;
		this.psaname = psaname;
		this.location = location;
		this.pincode = pincode;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.emailid = emailid;
		this.pan = pan;
		this.dob = dob;
		this.aadhaar = aadhaar;
		this.date = date;
		this.time = time;
		this.panuser_id = panuser_id;
		this.panpassword = panpassword;
		this.requestId = requestId;
		this.state = state;
		this.status = status;
		this.userId = userId;
		this.district = district;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUMobileNo() {
		return UMobileNo;
	}
	public void setUMobileNo(String uMobileNo) {
		UMobileNo = uMobileNo;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getPsaname() {
		return psaname;
	}
	public void setPsaname(String psaname) {
		this.psaname = psaname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
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
	public String getPanuser_id() {
		return panuser_id;
	}
	public void setPanuser_id(String panuser_id) {
		this.panuser_id = panuser_id;
	}
	public String getPanpassword() {
		return panpassword;
	}
	public void setPanpassword(String panpassword) {
		this.panpassword = panpassword;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	

}
