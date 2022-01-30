package com.skyflight.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="cancellationdetail")
public class CancellationDetail implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String booking_id;
	private String PNR;
	private String trackid;
	private String ticketid;
	private String changerequestid;
	private String refundamount;
	private String cancellationcharge;
	private String status;
	
	
	@Transient private int ResponseStatus;
public CancellationDetail() {
	super();
}


public CancellationDetail(String booking_id, String pNR, String trackid, String ticketid, String changerequestid,
		String refundamount, String cancellationcharge, String status) {
	super();
	this.booking_id = booking_id;
	PNR = pNR;
	this.trackid = trackid;
	this.ticketid = ticketid;
	this.changerequestid = changerequestid;
	this.refundamount = refundamount;
	this.cancellationcharge = cancellationcharge;
	this.status = status;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}


public String getBooking_id() {
	return booking_id;
}


public void setBooking_id(String booking_id) {
	this.booking_id = booking_id;
}


public String getPNR() {
	return PNR;
}


public void setPNR(String pNR) {
	PNR = pNR;
}


public String getTrackid() {
	return trackid;
}


public void setTrackid(String trackid) {
	this.trackid = trackid;
}


public String getTicketid() {
	return ticketid;
}


public void setTicketid(String ticketid) {
	this.ticketid = ticketid;
}


public String getChangerequestid() {
	return changerequestid;
}


public void setChangerequestid(String changerequestid) {
	this.changerequestid = changerequestid;
}


public String getRefundamount() {
	return refundamount;
}


public void setRefundamount(String refundamount) {
	this.refundamount = refundamount;
}


public String getCancellationcharge() {
	return cancellationcharge;
}


public void setCancellationcharge(String cancellationcharge) {
	this.cancellationcharge = cancellationcharge;
}


public String getStatus() {
	return status;
}


public void setStatus(String status) {
	this.status = status;
}


public int getResponseStatus() {
	return ResponseStatus;
}


public void setResponseStatus(int responseStatus) {
	ResponseStatus = responseStatus;
}




}
