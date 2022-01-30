package com.recharge.customModel;

import java.io.Serializable;

public class RechargeReport implements Serializable {
	
	private Integer id;
	private String userId;
	private String mobile;
	private Integer operatorId;
	private Double openBal;
	private Double amount;
	private Double charge;
	private Double comm;
	private Double closeBal;
	private String tid;
	private String oid;
	private String ptid;
	private String status;
	private String validation;
	private String source;
	private Integer apiId;
	private Integer roleId;
	private String wlId;
	private String date;
	private String time;
	private String ip;
	private String name;
	private String firmName;
	private String usermobile;
	private String serviceProvider;
	private String apiName;
	private String whiteLebel;

	public RechargeReport() {
	}

	public RechargeReport(Integer id, String userId, String mobile, Integer operatorId, Double openBal, Double amount,
			Double charge, Double comm, Double closeBal, String tid, String oid, String ptid, String status,
			String validation, String source, Integer apiId, Integer roleId, String wlId, String date, String time, String ip,
			String name, String firmName,String usermobile, String serviceProvider, String apiName,String whiteLebel) {
		this.id = id;
		this.userId = userId;
		this.mobile = mobile;
		this.operatorId = operatorId;
		this.openBal = openBal;
		this.amount = amount;
		this.charge = charge;
		this.comm = comm;
		this.closeBal = closeBal;
		this.tid = tid;
		this.oid = oid;
		this.ptid = ptid;
		this.status = status;
		this.validation = validation;
		this.source = source;
		this.apiId = apiId;
		this.roleId = roleId;
		this.wlId = wlId;
		this.date = date;
		this.time = time;
		this.ip = ip;
		this.name = name;
		this.firmName = firmName;
		this.usermobile = usermobile;
		this.serviceProvider = serviceProvider;
		this.apiName = apiName;
		this.whiteLebel=whiteLebel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Double getOpenBal() {
		return openBal;
	}

	public void setOpenBal(Double openBal) {
		this.openBal = openBal;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public Double getComm() {
		return comm;
	}

	public void setComm(Double comm) {
		this.comm = comm;
	}

	public Double getCloseBal() {
		return closeBal;
	}

	public void setCloseBal(Double closeBal) {
		this.closeBal = closeBal;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getPtid() {
		return ptid;
	}

	public void setPtid(String ptid) {
		this.ptid = ptid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getApiId() {
		return apiId;
	}

	public void setApiId(Integer apiId) {
		this.apiId = apiId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getWlId() {
		return wlId;
	}

	public void setWlId(String wlId) {
		this.wlId = wlId;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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
	
	public String getUserMobile() {
		return usermobile;
	}

	public void setUserMobile(String usermobile) {
		this.usermobile = usermobile;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getWhiteLebel() {
		return whiteLebel;
	}

	public void setWhiteLebel(String whiteLebel) {
		this.whiteLebel = whiteLebel;
	}
	
	

}
