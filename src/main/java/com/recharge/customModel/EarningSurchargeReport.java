package com.recharge.customModel;

public class EarningSurchargeReport {
	private Integer id;
	private Integer roleId;
	private String wlId;
	private String userId;
	private Double comm;
	private Double charge;
	private String narration;
	private String date;
	private String time;
	private String name;
	private String mobile;

	public EarningSurchargeReport() {
	}

	public EarningSurchargeReport(Integer id, Integer roleId, String wlId, String userId, Double comm, Double charge,
			String narration, String date, String time, String name, String mobile) {
		this.id = id;
		this.roleId = roleId;
		this.wlId = wlId;
		this.userId = userId;
		this.comm = comm;
		this.charge = charge;
		this.narration = narration;
		this.date = date;
		this.time = time;
		this.name = name;
		this.mobile = mobile;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getWlId() {
		return this.wlId;
	}

	public void setWlId(String wlId) {
		this.wlId = wlId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getComm() {
		return this.comm;
	}

	public void setComm(Double comm) {
		this.comm = comm;
	}

	public Double getCharge() {
		return this.charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public String getNarration() {
		return this.narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
