package com.recharge.customModel;

public class ComplainDetails implements java.io.Serializable {
	private Integer id;
	private String subject;
	private String userId;
	private String complain;
	private String status;
	private String adminMessage;
	private String wlId;
	private String complainType;
	private String date;
	private String time;
	private String mobile;
	private String name;
	private String firmName;

	public ComplainDetails() {
	}

	public ComplainDetails(Integer id, String subject, String userId, String complain, String status,
			String adminMessage, String wlId, String complainType, String date, String time, String mobile, String name,
			String firmName) {
		this.id = id;
		this.subject = subject;
		this.userId = userId;
		this.complain = complain;
		this.status = status;
		this.adminMessage = adminMessage;
		this.wlId = wlId;
		this.complainType = complainType;
		this.date = date;
		this.time = time;
		this.mobile = mobile;
		this.name = name;
		this.firmName = firmName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getComplain() {
		return complain;
	}

	public void setComplain(String complain) {
		this.complain = complain;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdminMessage() {
		return adminMessage;
	}

	public void setAdminMessage(String adminMessage) {
		this.adminMessage = adminMessage;
	}

	public String getWlId() {
		return wlId;
	}

	public void setWlId(String wlId) {
		this.wlId = wlId;
	}

	public String getComplainType() {
		return complainType;
	}

	public void setComplainType(String complainType) {
		this.complainType = complainType;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

}
