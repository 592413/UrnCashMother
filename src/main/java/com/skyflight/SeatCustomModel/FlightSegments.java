package com.skyflight.SeatCustomModel;

public class FlightSegments {
	private String ResultIndex;
	private String TripIndicator;
	private String SegmentIndicator;
	private String FlightId;
	private String AirlineCode;
	private String ClassCode;
	private String AirCraftType;
	private String SupplierId;
	
	
	
	public FlightSegments(String resultIndex, String tripIndicator, String segmentIndicator, String flightId,
			String airlineCode, String classCode, String airCraftType, String supplierId) {
		super();
		ResultIndex = resultIndex;
		TripIndicator = tripIndicator;
		SegmentIndicator = segmentIndicator;
		FlightId = flightId;
		AirlineCode = airlineCode;
		ClassCode = classCode;
		AirCraftType = airCraftType;
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
	public String getAirlineCode() {
		return AirlineCode;
	}
	public void setAirlineCode(String airlineCode) {
		AirlineCode = airlineCode;
	}
	public String getClassCode() {
		return ClassCode;
	}
	public void setClassCode(String classCode) {
		ClassCode = classCode;
	}
	public String getAirCraftType() {
		return AirCraftType;
	}
	public void setAirCraftType(String airCraftType) {
		AirCraftType = airCraftType;
	}
	public String getSupplierId() {
		return SupplierId;
	}
	public void setSupplierId(String supplierId) {
		SupplierId = supplierId;
	}
	
	

}
