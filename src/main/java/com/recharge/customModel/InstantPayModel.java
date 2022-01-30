package com.recharge.customModel;

public class InstantPayModel {
	@Override
	public String toString() {
		return "InstantPayModel [ipay_errorcode=" + ipay_errorcode + ", ipay_errordesc=" + ipay_errordesc + ", ipay_id="
				+ ipay_id + ", agent_id=" + agent_id + ", status=" + status + "]";
	}
	private String ipay_errorcode; 
	private String ipay_errordesc; 
	private String ipay_id; 
	private String agent_id; 
	private String status;
	
	
	public String getIpay_errorcode() {
		return ipay_errorcode;
	}
	public void setIpay_errorcode(String ipay_errorcode) {
		this.ipay_errorcode = ipay_errorcode;
	}
	
	public String getIpay_errordesc() {
		return ipay_errordesc;
	}
	public void setIpay_errordesc(String ipay_errordesc) {
		this.ipay_errordesc = ipay_errordesc;
	}
	public String getIpay_id() {
		return ipay_id;
	}
	public void setIpay_id(String ipay_id) {
		this.ipay_id = ipay_id;
	}
	public String getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	} 
	

}
