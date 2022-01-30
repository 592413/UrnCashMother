package com.skyflight.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.User;
import com.skyflight.BookingCustomModel.BookingRequest;
import com.skyflight.model.Flightbooking;




public interface FlightBookingDao {
	

	public List<Flightbooking> getAllFlightbooking();

	public Flightbooking getFlightBookingById(String apiId);

	public boolean insertFlightbooking(Flightbooking fb);

	public boolean updateFlightbooking(Flightbooking fb);
	
	public boolean updateFlightBookingbyAirlinePNR(String PNR,String type); 

	public void deleteFlightbooking(int fbId);

	public List<Flightbooking> getFlightBookingByNamedQuery(String query, Map<String, Object> param);

	


}
