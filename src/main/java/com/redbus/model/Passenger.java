package com.redbus.model;

import java.io.Serializable;

public class Passenger implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    private long mobile;
    private String title;
    private String email;
    private long age;
    private String gender;
    private String idType;
    private String idNumber;
    private String address;
    private String primary;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getAge() {
		return age;
	}
	public void setAge(long age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrimary() {
		return primary;
	}
	public void setPrimary(String primary) {
		this.primary = primary;
	}
	@Override
	public String toString() {
		return "Passenger [name=" + name + ", mobile=" + mobile + ", title=" + title + ", email=" + email + ", age="
				+ age + ", gender=" + gender + ", idType=" + idType + ", idNumber=" + idNumber + ", address=" + address
				+ ", primary=" + primary + "]";
	}
   
    

}
