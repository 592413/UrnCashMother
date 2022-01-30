package com.skyflight.MealBaggagesCustomModel;

import java.util.List;

public class SSRRequest {
	private List<SSRFlightSegments> SSRFlightSegments;
	private int AdultCount;
	private int ChildCount;
	private int InfantCount;
	private String SupplierId;
	
	public SSRRequest(List<SSRFlightSegments> sSRFlightSegments, int adultCount,
			int childCount, int infantCount, String supplierId) {
		super();
		SSRFlightSegments = sSRFlightSegments;
		AdultCount = adultCount;
		ChildCount = childCount;
		InfantCount = infantCount;
		SupplierId = supplierId;
	}
	public List<SSRFlightSegments> getSSRFlightSegments() {
		return SSRFlightSegments;
	}
	public void setSSRFlightSegments(List<SSRFlightSegments> sSRFlightSegments) {
		SSRFlightSegments = sSRFlightSegments;
	}
	public int getAdultCount() {
		return AdultCount;
	}
	public void setAdultCount(int adultCount) {
		AdultCount = adultCount;
	}
	public int getChildCount() {
		return ChildCount;
	}
	public void setChildCount(int childCount) {
		ChildCount = childCount;
	}
	public int getInfantCount() {
		return InfantCount;
	}
	public void setInfantCount(int infantCount) {
		InfantCount = infantCount;
	}
	public String getSupplierId() {
		return SupplierId;
	}
	public void setSupplierId(String supplierId) {
		SupplierId = supplierId;
	}
	
	
	

}
