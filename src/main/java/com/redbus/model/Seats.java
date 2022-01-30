package com.redbus.model;

import java.io.Serializable;

public class Seats implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 	private String name;
	    private int row;
	    private int column;
	    private int zIndex;
	    private int length;
	    private int width;
	    private double fare;
	    private double baseFare;
	    private double serviceTaxPercentage;
	    private double serviceTaxAbsolute;
	    private double commission;
	    private String available;
	    private String ladiesSeat;
	    private double operatorServiceChargePercent;
	    private double operatorServiceChargeAbsolute;
	    private double markupFareAbsolute;
	    private double markupFarePercentage;
	    private double levyFare;
	    private double srtFee;
	    private double tollFee;
	    private double childFare;
	    private double bankTrexAmt;
	    private double bookingFee;
	    private double concession;
	    private String malesSeat;
	    private String doubleBirth;
	    private String reservedForSocialDistancing;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getColumn() {
			return column;
		}
		public void setColumn(int column) {
			this.column = column;
		}
		public int getzIndex() {
			return zIndex;
		}
		public void setzIndex(int zIndex) {
			this.zIndex = zIndex;
		}
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}
		public double getFare() {
			return fare;
		}
		public void setFare(double fare) {
			this.fare = fare;
		}
		public double getBaseFare() {
			return baseFare;
		}
		public void setBaseFare(double baseFare) {
			this.baseFare = baseFare;
		}
		public double getServiceTaxPercentage() {
			return serviceTaxPercentage;
		}
		public void setServiceTaxPercentage(double serviceTaxPercentage) {
			this.serviceTaxPercentage = serviceTaxPercentage;
		}
		public double getServiceTaxAbsolute() {
			return serviceTaxAbsolute;
		}
		public void setServiceTaxAbsolute(double serviceTaxAbsolute) {
			this.serviceTaxAbsolute = serviceTaxAbsolute;
		}
		public double getCommission() {
			return commission;
		}
		public void setCommission(double commission) {
			this.commission = commission;
		}
		public String getAvailable() {
			return available;
		}
		public void setAvailable(String available) {
			this.available = available;
		}
		public String getLadiesSeat() {
			return ladiesSeat;
		}
		public void setLadiesSeat(String ladiesSeat) {
			this.ladiesSeat = ladiesSeat;
		}
		public double getOperatorServiceChargePercent() {
			return operatorServiceChargePercent;
		}
		public void setOperatorServiceChargePercent(double operatorServiceChargePercent) {
			this.operatorServiceChargePercent = operatorServiceChargePercent;
		}
		public double getOperatorServiceChargeAbsolute() {
			return operatorServiceChargeAbsolute;
		}
		public void setOperatorServiceChargeAbsolute(double operatorServiceChargeAbsolute) {
			this.operatorServiceChargeAbsolute = operatorServiceChargeAbsolute;
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
		public double getLevyFare() {
			return levyFare;
		}
		public void setLevyFare(double levyFare) {
			this.levyFare = levyFare;
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
		public double getChildFare() {
			return childFare;
		}
		public void setChildFare(double childFare) {
			this.childFare = childFare;
		}
		public double getBankTrexAmt() {
			return bankTrexAmt;
		}
		public void setBankTrexAmt(double bankTrexAmt) {
			this.bankTrexAmt = bankTrexAmt;
		}
		public double getBookingFee() {
			return bookingFee;
		}
		public void setBookingFee(double bookingFee) {
			this.bookingFee = bookingFee;
		}
		public double getConcession() {
			return concession;
		}
		public void setConcession(double concession) {
			this.concession = concession;
		}
		public String getMalesSeat() {
			return malesSeat;
		}
		public void setMalesSeat(String malesSeat) {
			this.malesSeat = malesSeat;
		}		
		public String getDoubleBirth() {
			return doubleBirth;
		}
		public void setDoubleBirth(String doubleBirth) {
			this.doubleBirth = doubleBirth;
		}
		public String getReservedForSocialDistancing() {
			return reservedForSocialDistancing;
		}
		public void setReservedForSocialDistancing(String reservedForSocialDistancing) {
			this.reservedForSocialDistancing = reservedForSocialDistancing;
		}
		@Override
		public String toString() {
			return "Seats [name=" + name + ", row=" + row + ", column=" + column + ", zIndex=" + zIndex + ", length="
					+ length + ", width=" + width + ", fare=" + fare + ", baseFare=" + baseFare
					+ ", serviceTaxPercentage=" + serviceTaxPercentage + ", serviceTaxAbsolute=" + serviceTaxAbsolute
					+ ", commission=" + commission + ", available=" + available + ", ladiesSeat=" + ladiesSeat
					+ ", operatorServiceChargePercent=" + operatorServiceChargePercent
					+ ", operatorServiceChargeAbsolute=" + operatorServiceChargeAbsolute + ", markupFareAbsolute="
					+ markupFareAbsolute + ", markupFarePercentage=" + markupFarePercentage + ", levyFare=" + levyFare
					+ ", srtFee=" + srtFee + ", tollFee=" + tollFee + ", childFare=" + childFare + ", bankTrexAmt="
					+ bankTrexAmt + ", bookingFee=" + bookingFee + ", concession=" + concession + ", malesSeat="
					+ malesSeat + ", doubleBirth=" + doubleBirth + ", reservedForSocialDistancing="
					+ reservedForSocialDistancing + "]";
		}
		
	    
	    
	    
	    
	    
	    
}
