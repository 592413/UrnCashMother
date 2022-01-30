package com.skyflight.taxCustomModel;

public class FareBreakdownTax {
	private String Currency;
	private String TripIndicator;
	private String PassengerType;
	private String PassengerCount;
	private double BaseFare;
	private double GrossAmount;
	private double Commission;
	private double YQ;
	private double Tax;
	private double AdditionalTxnFeeOfrd;
	private double AdditionalTxnFeePub;
	private double TransactionFee;
	private double portalcomm;
	
	
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getPassengerType() {
		return PassengerType;
	}
	public void setPassengerType(String passengerType) {
		PassengerType = passengerType;
	}
	public String getPassengerCount() {
		return PassengerCount;
	}
	public void setPassengerCount(String passengerCount) {
		PassengerCount = passengerCount;
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
	public double getCommission() {
		return Commission;
	}
	public void setCommission(double commission) {
		Commission = commission;
	}
	public double getYQ() {
		return YQ;
	}
	public void setYQ(double yQ) {
		YQ = yQ;
	}
	public double getTax() {
		return Tax;
	}
	public void setTax(double tax) {
		Tax = tax;
	}
	public double getAdditionalTxnFeeOfrd() {
		return AdditionalTxnFeeOfrd;
	}
	public void setAdditionalTxnFeeOfrd(double additionalTxnFeeOfrd) {
		AdditionalTxnFeeOfrd = additionalTxnFeeOfrd;
	}
	public double getAdditionalTxnFeePub() {
		return AdditionalTxnFeePub;
	}
	public void setAdditionalTxnFeePub(double additionalTxnFeePub) {
		AdditionalTxnFeePub = additionalTxnFeePub;
	}
	public double getTransactionFee() {
		return TransactionFee;
	}
	public void setTransactionFee(double transactionFee) {
		TransactionFee = transactionFee;
	}
	public String getTripIndicator() {
		return TripIndicator;
	}
	public void setTripIndicator(String tripIndicator) {
		TripIndicator = tripIndicator;
	}
	public double getPortalcomm() {
		return portalcomm;
	}
	public void setPortalcomm(double portalcomm) {
		this.portalcomm = portalcomm;
	}
	

}
