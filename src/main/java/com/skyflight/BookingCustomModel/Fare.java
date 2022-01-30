package com.skyflight.BookingCustomModel;

public class Fare {

	private String TripIndicator;
	private String PassengerType;
	private double BaseFare;
	private double GrossAmount;
	private double Tax;
	private double TransactionFee;
	private double YQTax;
	private double AdditionalTxnFeeOfrd;
	private double AdditionalTxnFeePub;
	private double AirTransFee;
	private double OtherCharges;
	public String getTripIndicator() {
		return TripIndicator;
	}
	public void setTripIndicator(String tripIndicator) {
		TripIndicator = tripIndicator;
	}
	public String getPassengerType() {
		return PassengerType;
	}
	public void setPassengerType(String passengerType) {
		PassengerType = passengerType;
	}
	public double getBaseFare() {
		return BaseFare;
	}
	public void setBaseFare(double baseFare) {
		BaseFare = baseFare;
	}
	public double getGrossAmount() {
		return GrossAmount;
	}
	public void setGrossAmount(double grossAmount) {
		GrossAmount = grossAmount;
	}
	public double getTax() {
		return Tax;
	}
	public void setTax(double tax) {
		Tax = tax;
	}
	public double getTransactionFee() {
		return TransactionFee;
	}
	public void setTransactionFee(double transactionFee) {
		TransactionFee = transactionFee;
	}
	public double getYQTax() {
		return YQTax;
	}
	public void setYQTax(double yQTax) {
		YQTax = yQTax;
	}
	public double getAdditionalTxnFeeOfrd() {
		return AdditionalTxnFeeOfrd;
	}
	public void setAdditionalTxnFeeOfrd(double additionalTxnFeeOfrd) {
		AdditionalTxnFeeOfrd = additionalTxnFeeOfrd;
	}
	public double getAirTransFee() {
		return AirTransFee;
	}
	public void setAirTransFee(double airTransFee) {
		AirTransFee = airTransFee;
	}
	public double getOtherCharges() {
		return OtherCharges;
	}
	public void setOtherCharges(double otherCharges) {
		OtherCharges = otherCharges;
	}
	public double getAdditionalTxnFeePub() {
		return AdditionalTxnFeePub;
	}
	public void setAdditionalTxnFeePub(double additionalTxnFeePub) {
		AdditionalTxnFeePub = additionalTxnFeePub;
	}
	
	
	
}
