package com.skyflight.SeatCustomModel;

import java.util.List;

import com.skyflight.model.Authentication;

public class SeatRequest {
	private Authentication Authentication;
	private String TrackId;
	private String TripType;
	private boolean isDomestic;
	private SeatMapInput SeatMapInput;
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
	public String getTripType() {
		return TripType;
	}
	public void setTripType(String tripType) {
		TripType = tripType;
	}
	public boolean isDomestic() {
		return isDomestic;
	}
	public void setDomestic(boolean isDomestic) {
		this.isDomestic = isDomestic;
	}
	public SeatMapInput getSeatMapInput() {
		return SeatMapInput;
	}
	public void setSeatMapInput(SeatMapInput seatMapInput) {
		SeatMapInput = seatMapInput;
	}
	

	

}
