package com.skyhotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hoteldetail")
public class HotelDetail {
    @Id
	private int id;
	private String HotelName;
	private String  city;
	private String  email;
	private String  mobile;
	private String address;
	private String pincode;
	private String star;
	private String country;
	private String booking_id;
	
	
	
	
	public HotelDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HotelDetail(String hotelName, String city, String email, String mobile, String address, String pincode,
			String star, String country, String booking_id) {
		super();
		HotelName = hotelName;
		this.city = city;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.pincode = pincode;
		this.star = star;
		this.country = country;
		this.booking_id = booking_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHotelName() {
		return HotelName;
	}
	public void setHotelName(String hotelName) {
		HotelName = hotelName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}
	
	
	
	
}
