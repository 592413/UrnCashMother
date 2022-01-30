package com.skyhotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="hotelpassenger")
public class HotelLeadPassenger {
@Id	
private int id;
private String bookingid;
private String name;
private String mobile;
private String email;
private String roomcode;
private boolean leadpassenger;
private int age;
private String paxtype;



public HotelLeadPassenger() {
	super();
	// TODO Auto-generated constructor stub
}
public HotelLeadPassenger(String bookingid, String name, String mobile, String email,String roomcode,boolean leadpassenger,int age,String paxtype) {
	super();
	this.bookingid = bookingid;
	this.name = name;
	this.mobile = mobile;
	this.email = email;
	this.roomcode = roomcode;
	this.leadpassenger=leadpassenger;
	this.age=age;
	this.paxtype=paxtype;
}


public String getRoomcode() {
	return roomcode;
}
public void setRoomcode(String roomcode) {
	this.roomcode = roomcode;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getBookingid() {
	return bookingid;
}
public void setBookingid(String bookingid) {
	this.bookingid = bookingid;
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public boolean isLeadpassenger() {
	return leadpassenger;
}
public void setLeadpassenger(boolean leadpassenger) {
	this.leadpassenger = leadpassenger;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getPaxtype() {
	return paxtype;
}
public void setPaxrtype(String paxtype) {
	this.paxtype = paxtype;
}



}
