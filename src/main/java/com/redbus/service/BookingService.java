package com.redbus.service;

import java.util.Map;

import com.recharge.model.User;
import com.redbus.model.BlockRequest;

public interface BookingService {

	Map<String, Object> getAvailiableTrips(Map<String, String> request, User userDetails);

	Map<String, Object> getTripDetails(Map<String, String> request, User userDetails);

	Map<String, Object> getTripDetailsV2(Map<String, String> request, User userDetails);

	Map<String, Object> blockTicket(BlockRequest request, User userDetails);

	Map<String, Object> getFareBreakup(Map<String, String> request, User userDetails);

	Map<String, Object> confirmBooking(Map<String, String> request, User userDetails);

	Map<String, Object> getTicketDetails(Map<String, String> request, User userDetails);

	Map<String, Object> getBookedTicket(Map<String, String> request, User userDetails);

	Map<String, Object> getBoardingPointDetails(Map<String, String> request, User userDetails);

}
