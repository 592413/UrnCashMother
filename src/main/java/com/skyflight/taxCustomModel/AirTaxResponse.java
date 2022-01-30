package com.skyflight.taxCustomModel;

import java.util.List;

public class AirTaxResponse {

	private int ResponseStatus;
	private String TrackId;
	private List<FareBreakdownTax> FareBreakdown;
	private List<FareRuledetail> FareRuledetail;
	private double adminmarkup;
	private double usermarkup;
	private double totaladult;
	private double totalchild;
	private double totalinfant;
	private double totaltax;
	private double totalgross;
	private String Errormessage;
	private String Errorcode;
	public int getResponseStatus() {
		return ResponseStatus;
	}
	public void setResponseStatus(int responseStatus) {
		ResponseStatus = responseStatus;
	}
	public String getTrackId() {
		return TrackId;
	}
	public void setTrackId(String trackId) {
		TrackId = trackId;
	}
	public List<FareBreakdownTax> getFareBreakdown() {
		return FareBreakdown;
	}
	public void setFareBreakdown(List<FareBreakdownTax> fareBreakdown) {
		FareBreakdown = fareBreakdown;
	}
	public List<FareRuledetail> getFareRuledetail() {
		return FareRuledetail;
	}
	public void setFareRuledetail(List<FareRuledetail> fareRuledetail) {
		FareRuledetail = fareRuledetail;
	}
	public String getErrormessage() {
		return Errormessage;
	}
	public void setErrormessage(String errormessage) {
		Errormessage = errormessage;
	}
	public String getErrorcode() {
		return Errorcode;
	}
	public void setErrorcode(String errorcode) {
		Errorcode = errorcode;
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
	public double getTotaladult() {
		return totaladult;
	}
	public void setTotaladult(double totaladult) {
		this.totaladult = totaladult;
	}
	public double getTotalchild() {
		return totalchild;
	}
	public void setTotalchild(double totalchild) {
		this.totalchild = totalchild;
	}
	public double getTotalinfant() {
		return totalinfant;
	}
	public void setTotalinfant(double totalinfant) {
		this.totalinfant = totalinfant;
	}
	public double getTotaltax() {
		return totaltax;
	}
	public void setTotaltax(double totaltax) {
		this.totaltax = totaltax;
	}
	public double getTotalgross() {
		return totalgross;
	}
	public void setTotalgross(double totalgross) {
		this.totalgross = totalgross;
	}
	
	
	
}
