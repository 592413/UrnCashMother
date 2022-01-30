package com.skyflight.businessdao;

import java.util.List;
import java.util.Map;

import com.recharge.model.User;
import com.skyflight.searchCustomModel.AirSearchAvailability;
import com.skyflight.searchCustomModel.CalendarFareRes;
public interface FlightSearchDao {

	AirSearchAvailability searchFlight(Map<String, String> request, User userDetails);

	Map<String, Object> getTax(Map<String, Object> request, User userDetails);

	List<String> getAirportcodes(String term);

	public Map<String, Object> getMealAndBaggageInfo(Map<String, Object> request, User userDetails);

	public Map<String, Object> getSeatRequest(Map<String, Object> request, User userDetails);

	CalendarFareRes fetchcalenderfare(Map<String, String> request, User userDetails);

	public AirSearchAvailability fetchcalenderflight(Map<String, Object> request, User userDetails);

}
