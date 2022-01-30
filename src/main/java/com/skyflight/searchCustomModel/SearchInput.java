package com.skyflight.searchCustomModel;

import java.util.List;


public class SearchInput {
	
	
	private String ClassType;
	private int AdultCount;
	private int ChildCount;
	private int InfantCount;
	private String AirlineCode;
	private String TripType;
	
	private List<TripDetails> TripDetails;



	public SearchInput(String classType, int adultCount, int childCount, int infantCount, String airlineCode,
			String tripType, List<com.skyflight.searchCustomModel.TripDetails> tripDetails) {
		super();
		ClassType = classType;
		AdultCount = adultCount;
		ChildCount = childCount;
		InfantCount = infantCount;
		AirlineCode = airlineCode;
		TripType = tripType;
		TripDetails = tripDetails;
	}



	public String getClassType() {
		return ClassType;
	}


	public void setClassType(String classType) {
		ClassType = classType;
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

	public String getAirlineCode() {
		return AirlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		AirlineCode = airlineCode;
	}

	public String getTripType() {
		return TripType;
	}

	public void setTripType(String tripType) {
		TripType = tripType;
	}

	public List<TripDetails> getTripDetails() {
		return TripDetails;
	}

	public void setTripDetails(List<TripDetails> tripDetails) {
		TripDetails = tripDetails;
	}
	
	
	

	
}
