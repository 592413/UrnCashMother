package com.skyhotel.model;

public class HOtelPrice {
	private String bookingid;
	private double price;
	private double adminmarkup;
	private double usermarkup;
	
	
	public HOtelPrice() {
		super();
	}
	
	
	
	public HOtelPrice(String bookingid, double price, double adminmarkup, double usermarkup) {
		super();
		this.bookingid = bookingid;
		this.price = price;
		this.adminmarkup = adminmarkup;
		this.usermarkup = usermarkup;
	}



	public String getBookingid() {
		return bookingid;
	}
	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAdminmarkup() {
		return adminmarkup;
	}
	public void setAdminmarkup(double adminmarkup) {
		this.adminmarkup = adminmarkup;
	}
	public double getUsermarkup() {
		return usermarkup;
	}
	public void setUsermarkup(double usermarkup) {
		this.usermarkup = usermarkup;
	}
	
	
	

}
