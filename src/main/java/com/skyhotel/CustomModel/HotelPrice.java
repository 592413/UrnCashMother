package com.skyhotel.CustomModel;

public class HotelPrice {
	private double RoomPrice;
	private double Tax;
	private double ExtraGuestCharge;
	private double ChildCharge;
	private double OtherCharges;
	private double Discount;
	private double ServiceTax;
	private double totalprice;
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
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	
	

}
