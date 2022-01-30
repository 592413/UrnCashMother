package com.skyflight.BookingCustomModel;

import java.util.List;

public class AirBookResponse {
	private int ResponseStatus;
	private String TrackId;
	private List<TicketDetails> TicketDetails;
	private String ErrorCode;
	private String ErrorMessage;
	
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
	public List<TicketDetails> getTicketDetails() {
		return TicketDetails;
	}
	public void setTicketDetails(List<TicketDetails> ticketDetails) {
		TicketDetails = ticketDetails;
	}
	public String getErrorCode() {
		return ErrorCode;
	}
	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	
	

}
