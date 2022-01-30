package com.skyflight.SeatCustomModel;

import java.util.List;

public class SeatMapInput {
	private List<FlightSegments> FlightSegments;
	private List<SeatPassengerDetails> PassengerDetails;
	
	
	public List<FlightSegments> getFlightSegments() {
		return FlightSegments;
	}
	public void setFlightSegments(List<FlightSegments> flightSegments) {
		FlightSegments = flightSegments;
	}
	public List<SeatPassengerDetails> getPassengerDetails() {
		return PassengerDetails;
	}
	public void setPassengerDetails(List<SeatPassengerDetails> passengerDetails) {
		PassengerDetails = passengerDetails;
	}
	
	
	

}
