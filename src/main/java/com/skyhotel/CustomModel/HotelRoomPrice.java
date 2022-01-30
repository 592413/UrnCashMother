package com.skyhotel.CustomModel;

public class HotelRoomPrice {
	private double RoomPrice;
	private double Tax;
	private double ExtraGuestCharge;
	private double ChildCharge;
	private double OtherCharges;
	private double Discount;
	private double ServiceTax;
	private double total;
	private String CurrencyCode;
	
	
	
	
	public HotelRoomPrice() {
		super();
	}
	
	
	
	
	public HotelRoomPrice(double roomPrice, double tax, double extraGuestCharge, double childCharge,
			double otherCharges, double discount, double serviceTax, String currencyCode) {
		super();
		RoomPrice = roomPrice;
		Tax = tax;
		ExtraGuestCharge = extraGuestCharge;
		ChildCharge = childCharge;
		OtherCharges = otherCharges;
		Discount = discount;
		ServiceTax = serviceTax;
		CurrencyCode = currencyCode;
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getCurrencyCode() {
		return CurrencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}
	
	
	

}
