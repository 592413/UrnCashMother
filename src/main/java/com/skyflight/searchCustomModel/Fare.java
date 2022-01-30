package com.skyflight.searchCustomModel;

public class Fare {

	private String Currency;
	private int BaseFare;
	private int Tax;
	private int YQTax;
	private int OtherCharges;
	private int ServiceCharge;
	private int Commission;
	private int GrossAmount;
	private double TotalAmount;
	private double MarkUp;
	private double USerMarkUp;
	private double portalcharge;
	private double portalcommission;
	
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public int getBaseFare() {
		return BaseFare;
	}
	public void setBaseFare(int baseFare) {
		BaseFare = baseFare;
	}
	public int getTax() {
		return Tax;
	}
	public void setTax(int tax) {
		Tax = tax;
	}
	public int getYQTax() {
		return YQTax;
	}
	public void setYQTax(int yQTax) {
		YQTax = yQTax;
	}
	public int getOtherCharges() {
		return OtherCharges;
	}
	public void setOtherCharges(int otherCharges) {
		OtherCharges = otherCharges;
	}
	public int getServiceCharge() {
		return ServiceCharge;
	}
	public void setServiceCharge(int serviceCharge) {
		ServiceCharge = serviceCharge;
	}
	public int getCommission() {
		return Commission;
	}
	public void setCommission(int commission) {
		Commission = commission;
	}
	public int getGrossAmount() {
		return GrossAmount;
	}
	public void setGrossAmount(int grossAmount) {
		GrossAmount = grossAmount;
	}
	public double getTotalAmount() {
		return TotalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		TotalAmount = totalAmount;
	}
	public double getMarkUp() {
		return MarkUp;
	}
	public void setMarkUp(double markUp) {
		MarkUp = markUp;
	}
	public double getUSerMarkUp() {
		return USerMarkUp;
	}
	public void setUSerMarkUp(double uSerMarkUp) {
		USerMarkUp = uSerMarkUp;
	}
	public double getPortalcharge() {
		return portalcharge;
	}
	public void setPortalcharge(double portalcharge) {
		this.portalcharge = portalcharge;
	}
	public double getPortalcommission() {
		return portalcommission;
	}
	public void setPortalcommission(double portalcommission) {
		this.portalcommission = portalcommission;
	}
	
	
}
