package com.skyflight.android;

import java.util.Map;

public interface FlightBusinessDao {

	public Map<String, Object> flightsearchAndroid(String request);

	public Map<String, Object> flightairport(String request);


	public Map<String, Object> flightCalenderSearchAndroid(String request);

	public Map<String, Object> flightTaxAndroid(String request);

	public Map<String, Object> flightBookTicketAndroid(String request);

	public Map<String, Object> flightBookTicketReportAndroid(String request);

	public Map<String, Object> flightTicketCancelAndroid(String request);

}
