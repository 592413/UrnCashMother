package com.skyflight.businessdao;

import java.util.List;
import java.util.Map;

import com.recharge.model.User;
import com.skyflight.BookingCustomModel.BookingRequest;
import com.skyflight.model.Flightbooking;




public interface FlightBookingImplDao {

	public Map<String, Object> Flightbookrequest(User userDetails, Map<String, Object> request);

	public Map<String, Object> viewBooikngReport(User userDetails, Map<String, String> request);

	public Map<String, Object> ViewTicket(User userDetails, Map<String, Object> request);

	public Map<String, Object> cancelTicket(Map<String, Object> request, User user);

	public Map<String, Object> partialCancel(Map<String, Object> request, User user);

	public Map<String, Object> Flightbookrequestandroid(User userDetails, BookingRequest request, double total);

	public Map<String, Object> donetransactionfess(Map<String, Object> request, User user);

	Map<String, Object> cancelTicketapp(Map<String, String> request, User user);
	

	


}
