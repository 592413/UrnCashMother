package com.skyhotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="hotelcancellationdetail")
public class HotelCancellation {
@Id	
private int id;
private String BookingId;
private String ChangeRequestId;
private double CancellationCharge;
private double RefundAmount;
private String status;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getBookingId() {
	return BookingId;
}
public void setBookingId(String bookingId) {
	BookingId = bookingId;
}
public String getChangeRequestId() {
	return ChangeRequestId;
}
public void setChangeRequestId(String changeRequestId) {
	ChangeRequestId = changeRequestId;
}
public double getCancellationCharge() {
	return CancellationCharge;
}
public void setCancellationCharge(double cancellationCharge) {
	CancellationCharge = cancellationCharge;
}
public double getRefundAmount() {
	return RefundAmount;
}
public void setRefundAmount(double refundAmount) {
	RefundAmount = refundAmount;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public HotelCancellation(String bookingId, String changeRequestId, double cancellationCharge, double refundAmount,
		String status) {
	super();
	BookingId = bookingId;
	ChangeRequestId = changeRequestId;
	CancellationCharge = cancellationCharge;
	RefundAmount = refundAmount;
	this.status = status;
}
public HotelCancellation() {
	super();
	// TODO Auto-generated constructor stub
}





}
