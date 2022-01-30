package com.skyflight.taxCustomModel;

import java.util.List;

import com.skyflight.model.Authentication;

public class UpdatePriceInputRequest {
	private Authentication Authentication;
	private String TrackId;
	private String TripType;
	private boolean isDomestic;
	private List<TaxInput> TaxInput;
	
	
	


	public UpdatePriceInputRequest(com.skyflight.model.Authentication authentication, String trackId, String tripType,
			boolean isDomestic, List<com.skyflight.taxCustomModel.TaxInput> taxInput) {
		super();
		Authentication = authentication;
		TrackId = trackId;
		TripType = tripType;
		this.isDomestic = isDomestic;
		TaxInput = taxInput;
	}
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
	public List<TaxInput> getTaxInput() {
		return TaxInput;
	}
	public void setTaxInput(List<TaxInput> taxInput) {
		TaxInput = taxInput;
	}
	
	
	
}
