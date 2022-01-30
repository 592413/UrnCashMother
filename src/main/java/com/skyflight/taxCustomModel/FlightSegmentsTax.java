package com.skyflight.taxCustomModel;

public class FlightSegmentsTax {
	private String ResultIndex;
	private String TripIndicator;
	private String SegmentIndicator;
	private String FlightId;
	private String ClassCode;
	private String AirlineCode;
	private String SupplierId;
	
	
	
	public FlightSegmentsTax() {
		super();
	}
	public FlightSegmentsTax(String resultIndex, String tripIndicator, String segmentIndicator, String flightId,
			String classCode, String airlineCode, String supplierId) {
		super();
		ResultIndex = resultIndex;
		TripIndicator = tripIndicator;
		SegmentIndicator = segmentIndicator;
		FlightId = flightId;
		ClassCode = classCode;
		AirlineCode = airlineCode;
		SupplierId = supplierId;
	}
	public String getResultIndex() {
		return ResultIndex;
	}
	public void setResultIndex(String resultIndex) {
		ResultIndex = resultIndex;
	}
	public String getTripIndicator() {
		return TripIndicator;
	}
	public void setTripIndicator(String tripIndicator) {
		TripIndicator = tripIndicator;
	}
	public String getSegmentIndicator() {
		return SegmentIndicator;
	}
	public void setSegmentIndicator(String segmentIndicator) {
		SegmentIndicator = segmentIndicator;
	}
	public String getFlightId() {
		return FlightId;
	}
	public void setFlightId(String flightId) {
		FlightId = flightId;
	}
	public String getClassCode() {
		return ClassCode;
	}
	public void setClassCode(String classCode) {
		ClassCode = classCode;
	}
	public String getAirlineCode() {
		return AirlineCode;
	}
	public void setAirlineCode(String airlineCode) {
		AirlineCode = airlineCode;
	}
	public String getSupplierId() {
		return SupplierId;
	}
	public void setSupplierId(String supplierId) {
		SupplierId = supplierId;
	}
	
	
	

}
