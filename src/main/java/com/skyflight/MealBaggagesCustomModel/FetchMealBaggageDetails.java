package com.skyflight.MealBaggagesCustomModel;

import java.util.List;

import com.skyflight.model.Authentication;

public class FetchMealBaggageDetails {
	private Authentication Authentication;
	private String TrackId;
	private String TripType;
	private boolean isDomestic;
	private SSRRequest SSRRequest;
	
	
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
	public SSRRequest getSSRRequest() {
		return SSRRequest;
	}
	public void setSSRRequest(SSRRequest sSRRequest) {
		SSRRequest = sSRRequest;
	}
	
	
	
	
}
