package com.skyhotel.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class HOtelRooms {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String bookingid;
	private String AdultCount;
	private String ChildCount;
	private String RoomCode;
	private String RoomTypeCode;
	private String RoomTypeName;
	private String RatePlanCode;
	private double RoomPrice;
	private double Tax;
	private double ExtraGuestCharge;
	private double ChildCharge;
	private double OtherCharges;
	private double Discount;
	private double ServiceTax;
	private String CancellationPolicy;
	
	
	
	public HOtelRooms() {
		super();
	}
	
	
	
	public HOtelRooms(String bookingid, String adultCount, String childCount, String roomCode, String roomTypeCode,
			String roomTypeName, String ratePlanCode, double roomPrice, double tax, double extraGuestCharge,
			double childCharge, double otherCharges, double discount, double serviceTax, String cancellationPolicy) {
		super();
		this.bookingid = bookingid;
		AdultCount = adultCount;
		ChildCount = childCount;
		RoomCode = roomCode;
		RoomTypeCode = roomTypeCode;
		RoomTypeName = roomTypeName;
		RatePlanCode = ratePlanCode;
		RoomPrice = roomPrice;
		Tax = tax;
		ExtraGuestCharge = extraGuestCharge;
		ChildCharge = childCharge;
		OtherCharges = otherCharges;
		Discount = discount;
		ServiceTax = serviceTax;
		CancellationPolicy = cancellationPolicy;
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
	public String getAdultCount() {
		return AdultCount;
	}
	public void setAdultCount(String adultCount) {
		AdultCount = adultCount;
	}
	public String getChildCount() {
		return ChildCount;
	}
	public void setChildCount(String childCount) {
		ChildCount = childCount;
	}
	public String getRoomCode() {
		return RoomCode;
	}
	public void setRoomCode(String roomCode) {
		RoomCode = roomCode;
	}
	public String getRoomTypeCode() {
		return RoomTypeCode;
	}
	public void setRoomTypeCode(String roomTypeCode) {
		RoomTypeCode = roomTypeCode;
	}
	public String getRoomTypeName() {
		return RoomTypeName;
	}
	public void setRoomTypeName(String roomTypeName) {
		RoomTypeName = roomTypeName;
	}
	public String getRatePlanCode() {
		return RatePlanCode;
	}
	public void setRatePlanCode(String ratePlanCode) {
		RatePlanCode = ratePlanCode;
	}
	public double getRoomPrice() {
		return RoomPrice;
	}
	public void setRoomPrice(double roomPrice) {
		RoomPrice = roomPrice;
	}
	public double getTax() {
		return Tax;
	}
	public void setTax(double tax) {
		Tax = tax;
	}
	public double getExtraGuestCharge() {
		return ExtraGuestCharge;
	}
	public void setExtraGuestCharge(double extraGuestCharge) {
		ExtraGuestCharge = extraGuestCharge;
	}
	public double getChildCharge() {
		return ChildCharge;
	}
	public void setChildCharge(double childCharge) {
		ChildCharge = childCharge;
	}
	public double getOtherCharges() {
		return OtherCharges;
	}
	public void setOtherCharges(double otherCharges) {
		OtherCharges = otherCharges;
	}
	public double getDiscount() {
		return Discount;
	}
	public void setDiscount(double discount) {
		Discount = discount;
	}
	public double getServiceTax() {
		return ServiceTax;
	}
	public void setServiceTax(double serviceTax) {
		ServiceTax = serviceTax;
	}
	public String getCancellationPolicy() {
		return CancellationPolicy;
	}
	public void setCancellationPolicy(String cancellationPolicy) {
		CancellationPolicy = cancellationPolicy;
	}
	
	
	
	

}
