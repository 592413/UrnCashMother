package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="aepslog")
@NamedQueries({

	@NamedQuery(name="getAEPSLogByrefnoandagentcode",query="From AEPSLog where referenceno=:referenceno and agentcode=:agentcode"),
	@NamedQuery(name="getAEPSLogByrefno",query="From AEPSLog where referenceno=:referenceno"),
	@NamedQuery(name="getAEPSLogByrefnomobile",query="From AEPSLog where referenceno=:referenceno and customermob=:mobile"),
	@NamedQuery(name="getAEPSLogBytypeandstatus",query="From AEPSLog where type=:type and apiremarks=:apiremarks and date between :start_date and :end_date"),
	@NamedQuery(name="getAEPSLogBystatus",query="From AEPSLog where status=:status  and date between :start_date and :end_date"),
	@NamedQuery(name="getAEPSLogBystatususer",query="From AEPSLog where status=:status  and date between :start_date and :end_date and userId=:userId order by id desc")
})
public class AEPSLog {
	@Id
	private int id;
	private String referenceno;
	private String agentcode;
	private String status;
	private String type;
	private String userId;
	private String date;
	private String time;
	private String processingCode;
	private String customermob;
	private String remark;
	private Double amount;
	@Transient
	private  String aepsapiuser;
	
	public String getAepsapiuser() {
		return aepsapiuser;
	}
	public void setAepsapiuser(String aepsapiuser) {
		this.aepsapiuser = aepsapiuser;
	}
	public AEPSLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AEPSLog(String referenceno, String agentcode, String status, String type, String userId,
			String date,String time,String processingCode,String customermob,String remark,Double amount) {
		super();
		this.referenceno = referenceno;
		this.agentcode = agentcode;
		this.status = status;
		this.type = type;
		this.userId = userId;
		this.date = date;
		this.time = time;
		this.processingCode = processingCode;
		this.customermob = customermob;
		this.remark = remark;
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReferenceno() {
		return referenceno;
	}
	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}
	public String getAgentcode() {
		return agentcode;
	}
	public void setAgentcode(String agentcode) {
		this.agentcode = agentcode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getProcessingCode() {
		return processingCode;
	}
	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}
	public String getCustomermob() {
		return customermob;
	}
	public void setCustomermob(String customermob) {
		this.customermob = customermob;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	
}
