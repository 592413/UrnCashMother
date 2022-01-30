package com.redbus.model;

import java.io.Serializable;
import java.util.List;

public class FareDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double bankTrexAmt;
	private double baseFare;
	private double bookingFee;
	private double childFare;
	private double gst;
	private double levyFare;
	private double markupFareAbsolute;
	private double markupFarePercentage;
	private double opFare;
	private double operatorServiceChargeAbsolute;
	private double operatorServiceChargePercentage;
	private double serviceCharge;
	private double serviceTaxAbsolute;
	private double serviceTaxPercentage;
	private double srtFee;
	private double tollFee;
	private double totalFare;
	private double convenienceFee;
	private double otherCharges;
	private double previousFare;
	private double reservationCharges;
	private double updatedFare;
	private double asnFare;
	private double reservationFee;
	private List<FareBreakup> fareBreakup;
	public double getBankTrexAmt() {
		return bankTrexAmt;
	}
	public void setBankTrexAmt(double bankTrexAmt) {
		this.bankTrexAmt = bankTrexAmt;
	}
	public double getBaseFare() {
		return baseFare;
	}
	public void setBaseFare(double baseFare) {
		this.baseFare = baseFare;
	}
	public double getBookingFee() {
		return bookingFee;
	}
	public void setBookingFee(double bookingFee) {
		this.bookingFee = bookingFee;
	}
	public double getChildFare() {
		return childFare;
	}
	public void setChildFare(double childFare) {
		this.childFare = childFare;
	}
	public double getGst() {
		return gst;
	}
	public void setGst(double gst) {
		this.gst = gst;
	}
	public double getLevyFare() {
		return levyFare;
	}
	public void setLevyFare(double levyFare) {
		this.levyFare = levyFare;
	}
	public double getMarkupFareAbsolute() {
		return markupFareAbsolute;
	}
	public void setMarkupFareAbsolute(double markupFareAbsolute) {
		this.markupFareAbsolute = markupFareAbsolute;
	}
	public double getMarkupFarePercentage() {
		return markupFarePercentage;
	}
	public void setMarkupFarePercentage(double markupFarePercentage) {
		this.markupFarePercentage = markupFarePercentage;
	}
	public double getOpFare() {
		return opFare;
	}
	public void setOpFare(double opFare) {
		this.opFare = opFare;
	}
	public double getOperatorServiceChargeAbsolute() {
		return operatorServiceChargeAbsolute;
	}
	public void setOperatorServiceChargeAbsolute(double operatorServiceChargeAbsolute) {
		this.operatorServiceChargeAbsolute = operatorServiceChargeAbsolute;
	}
	public double getOperatorServiceChargePercentage() {
		return operatorServiceChargePercentage;
	}
	public void setOperatorServiceChargePercentage(double operatorServiceChargePercentage) {
		this.operatorServiceChargePercentage = operatorServiceChargePercentage;
	}
	public double getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public double getServiceTaxAbsolute() {
		return serviceTaxAbsolute;
	}
	public void setServiceTaxAbsolute(double serviceTaxAbsolute) {
		this.serviceTaxAbsolute = serviceTaxAbsolute;
	}
	public double getServiceTaxPercentage() {
		return serviceTaxPercentage;
	}
	public void setServiceTaxPercentage(double serviceTaxPercentage) {
		this.serviceTaxPercentage = serviceTaxPercentage;
	}
	public double getSrtFee() {
		return srtFee;
	}
	public void setSrtFee(double srtFee) {
		this.srtFee = srtFee;
	}
	public double getTollFee() {
		return tollFee;
	}
	public void setTollFee(double tollFee) {
		this.tollFee = tollFee;
	}
	public double getTotalFare() {
		return totalFare;
	}
	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}
	public double getConvenienceFee() {
		return convenienceFee;
	}
	public void setConvenienceFee(double convenienceFee) {
		this.convenienceFee = convenienceFee;
	}
	public double getOtherCharges() {
		return otherCharges;
	}
	public void setOtherCharges(double otherCharges) {
		this.otherCharges = otherCharges;
	}
	public double getPreviousFare() {
		return previousFare;
	}
	public void setPreviousFare(double previousFare) {
		this.previousFare = previousFare;
	}
	public double getReservationCharges() {
		return reservationCharges;
	}
	public void setReservationCharges(double reservationCharges) {
		this.reservationCharges = reservationCharges;
	}
	public double getUpdatedFare() {
		return updatedFare;
	}
	public void setUpdatedFare(double updatedFare) {
		this.updatedFare = updatedFare;
	}
	
	public List<FareBreakup> getFareBreakup() {
		return fareBreakup;
	}
	public void setFareBreakup(List<FareBreakup> fareBreakup) {
		this.fareBreakup = fareBreakup;
	}
	
	public double getAsnFare() {
		return asnFare;
	}
	public void setAsnFare(double asnFare) {
		this.asnFare = asnFare;
	}
	public double getReservationFee() {
		return reservationFee;
	}
	public void setReservationFee(double reservationFee) {
		this.reservationFee = reservationFee;
	}
	@Override
	public String toString() {
		return "FareDetails [bankTrexAmt=" + bankTrexAmt + ", baseFare=" + baseFare + ", bookingFee=" + bookingFee
				+ ", childFare=" + childFare + ", gst=" + gst + ", levyFare=" + levyFare + ", markupFareAbsolute="
				+ markupFareAbsolute + ", markupFarePercentage=" + markupFarePercentage + ", opFare=" + opFare
				+ ", operatorServiceChargeAbsolute=" + operatorServiceChargeAbsolute
				+ ", operatorServiceChargePercentage=" + operatorServiceChargePercentage + ", serviceCharge="
				+ serviceCharge + ", serviceTaxAbsolute=" + serviceTaxAbsolute + ", serviceTaxPercentage="
				+ serviceTaxPercentage + ", srtFee=" + srtFee + ", tollFee=" + tollFee + ", totalFare=" + totalFare
				+ ", convenienceFee=" + convenienceFee + ", otherCharges=" + otherCharges + ", previousFare="
				+ previousFare + ", reservationCharges=" + reservationCharges + ", updatedFare=" + updatedFare
				+ ", asnFare=" + asnFare + ", reservationFee=" + reservationFee + ", fareBreakup=" + fareBreakup + "]";
	}
	
	
	
	
	
	

	
}
