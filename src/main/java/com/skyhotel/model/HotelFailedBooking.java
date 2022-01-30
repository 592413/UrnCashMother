package com.skyhotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="hotelfailedbooking")
@Entity
public class HotelFailedBooking {
@Id	
private int id;
private String HotelId;
private String CheckIn_date;
private String name;
private String email;
private String mobile;
private int roomcode;
private String reason;
private String booking_date;



public HotelFailedBooking() {
	super();
	// TODO Auto-generated constructor stub
}
public HotelFailedBooking(String hotelId, String checkIn_date, String name, String email, String mobile, int roomcode,
		String reason, String booking_date) {
	super();
	HotelId = hotelId;
	CheckIn_date = checkIn_date;
	this.name = name;
	this.email = email;
	this.mobile = mobile;
	this.roomcode = roomcode;
	this.reason = reason;
	this.booking_date = booking_date;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getHotelId() {
	return HotelId;
}
public void setHotelId(String hotelId) {
	HotelId = hotelId;
}
public String getCheckIn_date() {
	return CheckIn_date;
}
public void setCheckIn_date(String checkIn_date) {
	CheckIn_date = checkIn_date;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public int getRoomcode() {
	return roomcode;
}
public void setRoomcode(int roomcode) {
	this.roomcode = roomcode;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public String getBooking_date() {
	return booking_date;
}
public void setBooking_date(String booking_date) {
	this.booking_date = booking_date;
}



}
