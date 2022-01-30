package com.skyflight.searchCustomModel;

public class TripDetails {
	private String Origin;
	private String Destination;
	private String TravelDate;
	
	
	public TripDetails(String origin, String destination, String travelDate) {
		
		Origin = origin;
		Destination = destination;
		TravelDate = travelDate;
	}
	public String getOrigin() {
		return Origin;
	}
	public void setOrigin(String origin) {
		Origin = origin;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public String getTravelDate() {
		return TravelDate;
	}
	public void setTravelDate(String travelDate) {
		TravelDate = travelDate;
	}
	
	
	
}
