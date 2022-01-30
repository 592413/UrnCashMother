package com.recharge.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="ackslipattchment")
public class AckSlipAttchment implements Serializable{
	
	@Id
	private String ackno;
	private byte[] bytesackno;
	private String acktype;
	public AckSlipAttchment() {
		super();
	}
	public AckSlipAttchment(String ackno, byte[] bytesackno, String acktype) {
		super();
		this.ackno = ackno;
		this.bytesackno = bytesackno;
		this.acktype = acktype;
	}
	public String getAckno() {
		return ackno;
	}
	public void setAckno(String ackno) {
		this.ackno = ackno;
	}
	public byte[] getBytesackno() {
		return bytesackno;
	}
	public void setBytesackno(byte[] bytesackno) {
		this.bytesackno = bytesackno;
	}
	public String getAcktype() {
		return acktype;
	}
	public void setAcktype(String acktype) {
		this.acktype = acktype;
	}
	
	
	

}
