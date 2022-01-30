package com.bankopen.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="openpayout")
@NamedQueries({
	
	@NamedQuery(name ="getOpenPayoutbyref", query="From OpenPayout where refid=:refid")
})
public class OpenPayout implements Serializable{
	@Id
	private int id;
	private String userId;
	private String beneaccountno;
	private String ifsccode;
	private String reciptname;
	private String emailid;
	private String mobileno;
	private String debitaccountno;
	private String trantype;
	private String amount;
	private String refid;
	private String remark;
	private String status;
	private String date;
	private String time;
	private String source;
	private String wild;
	private String rnn;
	
	@Transient private String name;
	@Transient private String mobile;
	
	public OpenPayout() {
		super();
	}
	
	
	public OpenPayout(String userId, String beneaccountno, String ifsccode, String reciptname, String emailid,
			String mobileno, String debitaccountno, String trantype, String amount, String refid, String remark,
			String status, String date, String time, String source, String wild,String rnn) {
		super();
		this.userId = userId;
		this.beneaccountno = beneaccountno;
		this.ifsccode = ifsccode;
		this.reciptname = reciptname;
		this.emailid = emailid;
		this.mobileno = mobileno;
		this.debitaccountno = debitaccountno;
		this.trantype = trantype;
		this.amount = amount;
		this.refid = refid;
		this.remark = remark;
		this.status = status;
		this.date = date;
		this.time = time;
		this.source = source;
		this.wild = wild;
		this.rnn = rnn;
		
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBeneaccountno() {
		return beneaccountno;
	}
	public void setBeneaccountno(String beneaccountno) {
		this.beneaccountno = beneaccountno;
	}
	public String getIfsccode() {
		return ifsccode;
	}
	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}
	public String getReciptname() {
		return reciptname;
	}
	public void setReciptname(String reciptname) {
		this.reciptname = reciptname;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getDebitaccountno() {
		return debitaccountno;
	}
	public void setDebitaccountno(String debitaccountno) {
		this.debitaccountno = debitaccountno;
	}
	public String getTrantype() {
		return trantype;
	}
	public void setTrantype(String trantype) {
		this.trantype = trantype;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRefid() {
		return refid;
	}
	public void setRefid(String refid) {
		this.refid = refid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getWild() {
		return wild;
	}
	public void setWild(String wild) {
		this.wild = wild;
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


	public String getRnn() {
		return rnn;
	}


	public void setRnn(String rnn) {
		this.rnn = rnn;
	}


	
	
	
	
	
}
