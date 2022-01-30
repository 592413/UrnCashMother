package com.recharge.customModel;

public class EncoreAEPSRequest {
private String date;
private String time;
private String BiometricData;
private String aadhar;
private String iin;
private String order_id;
private String latitude;
private String longitude;
private String mobile;
private String amount;
private String type;
private String deviceIMEI;
private String Username;
private String Password;
private String AID;
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
public String getBiometricData() {
	return BiometricData;
}
public void setBiometricData(String biometricData) {
	BiometricData = biometricData;
}
public String getAadhar() {
	return aadhar;
}
public void setAadhar(String aadhar) {
	this.aadhar = aadhar;
}
public String getIin() {
	return iin;
}
public void setIin(String iin) {
	this.iin = iin;
}
public String getOrder_id() {
	return order_id;
}
public void setOrder_id(String order_id) {
	this.order_id = order_id;
}
public String getLatitude() {
	return latitude;
}
public void setLatitude(String latitude) {
	this.latitude = latitude;
}
public String getLongitude() {
	return longitude;
}
public void setLongitude(String longitude) {
	this.longitude = longitude;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getDeviceIMEI() {
	return deviceIMEI;
}
public void setDeviceIMEI(String deviceIMEI) {
	this.deviceIMEI = deviceIMEI;
}
public String getUsername() {
	return Username;
}
public void setUsername(String username) {
	Username = username;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}
public String getAID() {
	return AID;
}
public void setAID(String aID) {
	AID = aID;
}

public EncoreAEPSRequest(String date, String time, String biometricData, String aadhar, String iin, String order_id,
		String latitude, String longitude, String mobile, String deviceIMEI,
		String username, String password, String aID) {
	super();
	this.date = date;
	this.time = time;
	BiometricData = biometricData;
	this.aadhar = aadhar;
	this.iin = iin;
	this.order_id = order_id;
	this.latitude = latitude;
	this.longitude = longitude;
	this.mobile = mobile;
	this.deviceIMEI = deviceIMEI;
	Username = username;
	Password = password;
	AID = aID;
}
public EncoreAEPSRequest(String date, String time, String biometricData, String aadhar, String iin, String order_id,
		String latitude, String longitude, String mobile, String amount, String type, String deviceIMEI,
		String username, String password, String aID) {
	super();
	this.date = date;
	this.time = time;
	BiometricData = biometricData;
	this.aadhar = aadhar;
	this.iin = iin;
	this.order_id = order_id;
	this.latitude = latitude;
	this.longitude = longitude;
	this.mobile = mobile;
	this.amount = amount;
	this.type = type;
	this.deviceIMEI = deviceIMEI;
	Username = username;
	Password = password;
	AID = aID;
}

public EncoreAEPSRequest(String date, String time, String biometricData, String aadhar, String iin, String order_id,
		String latitude, String longitude, String mobile, String amount, String deviceIMEI,
		String username, String password, String aID) {
	super();
	this.date = date;
	this.time = time;
	BiometricData = biometricData;
	this.aadhar = aadhar;
	this.iin = iin;
	this.order_id = order_id;
	this.latitude = latitude;
	this.longitude = longitude;
	this.mobile = mobile;
	this.amount = amount;
	this.deviceIMEI = deviceIMEI;
	Username = username;
	Password = password;
	AID = aID;
}




}
