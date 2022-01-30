package com.skyflight.SeatCustomModel;

import java.util.List;

public class SeatFlightSegments {
	private String AirCraftType;
	private String AirlineCode;
	private String FlightNumber;
	private String Origin;
	private String Destination;
	private List<SeatLayoutDetail> SeatLayoutDetail;
	
	
	public String getAirCraftType() {
		return AirCraftType;
	}
	public void setAirCraftType(String airCraftType) {
		AirCraftType = airCraftType;
	}
	public String getAirlineCode() {
		return AirlineCode;
	}
	public void setAirlineCode(String airlineCode) {
		AirlineCode = airlineCode;
	}
	public String getFlightNumber() {
		return FlightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		FlightNumber = flightNumber;
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
	public List<SeatLayoutDetail> getSeatLayoutDetail() {
		return SeatLayoutDetail;
	}
	public void setSeatLayoutDetail(List<SeatLayoutDetail> seatLayoutDetail) {
		SeatLayoutDetail = seatLayoutDetail;
	}
	
	
	

}
