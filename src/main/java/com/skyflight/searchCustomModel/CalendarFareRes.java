package com.skyflight.searchCustomModel;

import java.util.List;

public class CalendarFareRes {
	private int ResponseStatus;
	private String TrackId;
	private String Origin;
	private String Destination;
	private List<CalendarFareResults> CalendarFareResults;
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
	public List<CalendarFareResults> getCalendarFareResults() {
		return CalendarFareResults;
	}
	public void setCalendarFareResults(List<CalendarFareResults> calendarFareResults) {
		CalendarFareResults = calendarFareResults;
	}
	
	
	

}
