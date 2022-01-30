package com.skyflight.MealBaggagesCustomModel;

import java.util.List;

public class SSRSegments {
 
	private String TripIndicator;
	private String SegmentIndicator;
	private String FlightId;
	private String AirlineCode;
	private List<Meals> Meals;
	private List<Baggages> Baggages;
	
	
	public String getTripIndicator() {
		return TripIndicator;
	}
	public void setTripIndicator(String tripIndicator) {
		TripIndicator = tripIndicator;
	}
	public String getSegmentIndicator() {
		return SegmentIndicator;
	}
	public void setSegmentIndicator(String segmentIndicator) {
		SegmentIndicator = segmentIndicator;
	}
	public String getFlightId() {
		return FlightId;
	}
	public void setFlightId(String flightId) {
		FlightId = flightId;
	}
	public String getAirlineCode() {
		return AirlineCode;
	}
	public void setAirlineCode(String airlineCode) {
		AirlineCode = airlineCode;
	}
	public List<Meals> getMeals() {
		return Meals;
	}
	public void setMeals(List<Meals> meals) {
		Meals = meals;
	}
	public List<Baggages> getBaggages() {
		return Baggages;
	}
	public void setBaggages(List<Baggages> baggages) {
		Baggages = baggages;
	}
	
	
	
}
