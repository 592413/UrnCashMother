package com.skyflight.searchCustomModel;

import java.util.List;

public class SerachResults {

	private boolean IsLCC;
	private String AirlineId;
	private String AirlineName;
	private int stops;
	private int internationalroundstops;
	private String ResultIndex;
	private String totalduration;
	private int tripindi;
	private Fare Fare;
	private List<FareBreakdown> FareBreakdown;
	private List<FlightSegments> FlightSegments;
	private String totaldurationinternational;
	public boolean isIsLCC() {
		return IsLCC;
	}
	public void setIsLCC(boolean isLCC) {
		IsLCC = isLCC;
	}
	public String getAirlineId() {
		return AirlineId;
	}
	public void setAirlineId(String airlineId) {
		AirlineId = airlineId;
	}
	public Fare getFare() {
		return Fare;
	}
	public void setFare(Fare fare) {
		Fare = fare;
	}
	public List<FareBreakdown> getFareBreakdown() {
		return FareBreakdown;
	}
	public void setFareBreakdown(List<FareBreakdown> fareBreakdown) {
		FareBreakdown = fareBreakdown;
	}
	public List<FlightSegments> getFlightSegments() {
		return FlightSegments;
	}
	public void setFlightSegments(List<FlightSegments> flightSegments) {
		FlightSegments = flightSegments;
	}
	public String getResultIndex() {
		return ResultIndex;
	}
	public void setResultIndex(String resultIndex) {
		ResultIndex = resultIndex;
	}
	public String getTotalduration() {
		return totalduration;
	}
	public void setTotalduration(String totalduration) {
		this.totalduration = totalduration;
	}
	public int getTripindi() {
		return tripindi;
	}
	public void setTripindi(int tripindi) {
		this.tripindi = tripindi;
	}
	public String getAirlineName() {
		return AirlineName;
	}
	public void setAirlineName(String airlineName) {
		AirlineName = airlineName;
	}
	public int getStops() {
		return stops;
	}
	public void setStops(int stops) {
		this.stops = stops;
	}
	public String getTotaldurationinternational() {
		return totaldurationinternational;
	}
	public void setTotaldurationinternational(String totaldurationinternational) {
		this.totaldurationinternational = totaldurationinternational;
	}
	public int getInternationalroundstops() {
		return internationalroundstops;
	}
	public void setInternationalroundstops(int internationalroundstops) {
		this.internationalroundstops = internationalroundstops;
	}
	
	
	
	
}
