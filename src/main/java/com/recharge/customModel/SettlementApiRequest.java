package com.recharge.customModel;

public class SettlementApiRequest {
	private String apiuser;
	private String agentId;
	private double amount;
	private String type;
	private String bank;
	private String mobile;
	private String email;
	private String name;
	private String branch;
	private String account;
	private String ifsc;
	private double settlementcharge;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getApiuser() {
		return apiuser;
	}
	public void setApiuser(String apiuser) {
		this.apiuser = apiuser;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public double getSettlementcharge() {
		return settlementcharge;
	}
	public void setSettlementcharge(double settlementcharge) {
		this.settlementcharge = settlementcharge;
	}
	
	public SettlementApiRequest(String apiuser, String agentId, double amount, String type, String bank, String mobile,
			String email, String name, String branch, String account, String ifsc, double settlementcharge) {
		super();
		this.apiuser = apiuser;
		this.agentId = agentId;
		this.amount = amount;
		this.type = type;
		this.bank = bank;
		this.mobile = mobile;
		this.email = email;
		this.name = name;
		this.branch = branch;
		this.account = account;
		this.ifsc = ifsc;
		this.settlementcharge = settlementcharge;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	
}
