package com.recharge.customModel;

import java.io.Serializable;

public class ViewAssignPackage implements Serializable{
	private int id;
	private String package_id;
	private String serviceType;
	private String owner;
	private String package_name;
	private String user_id;
	private String user_name;
	private String mobile;
	private String role_id;
	public ViewAssignPackage() {
		super();
	}
	public ViewAssignPackage(int id, String package_id, String serviceType, String owner, String package_name,
			String user_id, String user_name, String mobile, String role_id) {
		super();
		this.id = id;
		this.package_id = package_id;
		this.serviceType = serviceType;
		this.owner = owner;
		this.package_name = package_name;
		this.user_id = user_id;
		this.user_name = user_name;
		this.mobile = mobile;
		this.role_id = role_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPackage_id() {
		return package_id;
	}
	public void setPackage_id(String package_id) {
		this.package_id = package_id;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	

}
