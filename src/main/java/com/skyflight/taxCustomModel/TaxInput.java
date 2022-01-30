package com.skyflight.taxCustomModel;

import java.util.List;

public class TaxInput {

	private List<FlightSegmentsTax> FlightSegments;
	private String BaseFare;
	
	
	
	
	public TaxInput() {
		super();
	}
	public TaxInput(List<FlightSegmentsTax> flightSegments, String baseFare) {
		super();
		FlightSegments = flightSegments;
		BaseFare = baseFare;
	}
	public List<FlightSegmentsTax> getFlightSegments() {
		return FlightSegments;
	}
	public void setFlightSegments(List<FlightSegmentsTax> flightSegments) {
		FlightSegments = flightSegments;
	}
	public String getBaseFare() {
		return BaseFare;
	}
	public void setBaseFare(String baseFare) {
		BaseFare = baseFare;
	}
	
	
}
