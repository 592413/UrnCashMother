package com.skyflight.BookingCustomModel;

import com.skyflight.model.Authentication;

public class CancelRequest {
	private Authentication Authentication;
	private String TrackId;
	private String BookingId;
	private String RequestType;
	private String PNR;
	private String TicketId;
	
/*	public CancelRequest(com.skyflight.model.Authentication authentication, String trackId, String bookingId,
			String requestType, String pNR) {
		super();
		Authentication = authentication;
		TrackId = trackId;
		BookingId = bookingId;
		RequestType = requestType;
		PNR = pNR;
	}
	
	public CancelRequest(com.skyflight.model.Authentication authentication, String trackId, String bookingId,
			String requestType, String pNR, String ticketId) {
		super();
		Authentication = authentication;
		TrackId = trackId;
		BookingId = bookingId;
		RequestType = requestType;
		PNR = pNR;
		TicketId = ticketId;
	}*/

	public Authentication getAuthentication() {
		return Authentication;
	}

	public void setAuthentication(Authentication authentication) {
		Authentication = authentication;
	}

	public String getTrackId() {
		return TrackId;
	}

	public void setTrackId(String trackId) {
		TrackId = trackId;
	}

	public String getBookingId() {
		return BookingId;
	}

	public void setBookingId(String bookingId) {
		BookingId = bookingId;
	}

	public String getRequestType() {
		return RequestType;
	}

	public void setRequestType(String requestType) {
		RequestType = requestType;
	}

	public String getPNR() {
		return PNR;
	}

	public void setPNR(String pNR) {
		PNR = pNR;
	}

	public String getTicketId() {
		return TicketId;
	}

	public void setTicketId(String ticketId) {
		TicketId = ticketId;
	}
	

	

}
