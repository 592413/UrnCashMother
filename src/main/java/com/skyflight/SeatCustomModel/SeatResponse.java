package com.skyflight.SeatCustomModel;

import java.util.List;

public class SeatResponse {
	private int ResponseStatus;
	private String TrackId;
	private List<SeatFlightSegments> FlightSegments;
	
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
	public List<SeatFlightSegments> getFlightSegments() {
		return FlightSegments;
	}
	public void setFlightSegments(List<SeatFlightSegments> flightSegments) {
		FlightSegments = flightSegments;
	}
	
	
	

}
