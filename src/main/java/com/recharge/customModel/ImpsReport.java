package com.recharge.customModel;

import java.io.Serializable;

public class ImpsReport  implements Serializable{
	
	private int id;
	private String username;
	private String account_no;
	private String bene_name;
	private String bene_mobile;
	private double op_bal;
	private double amount;
	private double charge;
	private double cl_bal;
	private String date;
	private String time;
	private String recieptId;
	private String status;
	private String banktransactionId;
	private double commission;
	private String transfertype;
	private String remmobile;
	private double paidamnt;
	private double gst;
	private String uname;
	private String usermobile;
	
	public ImpsReport() {
		super();
	}
	
	
	public ImpsReport(int id, String username, String account_no, String bene_name, String bene_mobile, double op_bal,
			double amount, double charge, double cl_bal, String date, String time, String recieptId, String status,
			String banktransactionId, double commission, String transfertype, String remmobile, double paidamnt,
			double gst, String uname, String usermobile) {
		super();
		this.id = id;
		this.username = username;
		this.account_no = account_no;
		this.bene_name = bene_name;
		this.bene_mobile = bene_mobile;
		this.op_bal = op_bal;
		this.amount = amount;
		this.charge = charge;
		this.cl_bal = cl_bal;
		this.date = date;
		this.time = time;
		this.recieptId = recieptId;
		this.status = status;
		this.banktransactionId = banktransactionId;
		this.commission = commission;
		this.transfertype = transfertype;
		this.remmobile = remmobile;
		this.paidamnt = paidamnt;
		this.gst = gst;
		this.uname = uname;
		this.usermobile = usermobile;
	}






	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getBene_name() {
		return bene_name;
	}
	public void setBene_name(String bene_name) {
		this.bene_name = bene_name;
	}
	public String getBene_mobile() {
		return bene_mobile;
	}
	public void setBene_mobile(String bene_mobile) {
		this.bene_mobile = bene_mobile;
	}
	public double getOp_bal() {
		return op_bal;
	}
	public void setOp_bal(double op_bal) {
		this.op_bal = op_bal;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	public double getCl_bal() {
		return cl_bal;
	}
	public void setCl_bal(double cl_bal) {
		this.cl_bal = cl_bal;
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
	public String getRecieptId() {
		return recieptId;
	}
	public void setRecieptId(String recieptId) {
		this.recieptId = recieptId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBanktransactionId() {
		return banktransactionId;
	}
	public void setBanktransactionId(String banktransactionId) {
		this.banktransactionId = banktransactionId;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUsermobile() {
		return usermobile;
	}
	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}

	



	public double getCommission() {
		return commission;
	}



	public void setCommission(double commission) {
		this.commission = commission;
	}



	public String getTransfertype() {
		return transfertype;
	}



	public void setTransfertype(String transfertype) {
		this.transfertype = transfertype;
	}



	public String getRemmobile() {
		return remmobile;
	}



	public void setRemmobile(String remmobile) {
		this.remmobile = remmobile;
	}



	public double getPaidamnt() {
		return paidamnt;
	}



	public void setPaidamnt(double paidamnt) {
		this.paidamnt = paidamnt;
	}


	public double getGst() {
		return gst;
	}


	public void setGst(double gst) {
		this.gst = gst;
	}



	
	
	

}
