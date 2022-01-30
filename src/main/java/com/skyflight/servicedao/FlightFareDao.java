package com.skyflight.servicedao;

import java.util.List;
import java.util.Map;

import com.skyflight.model.FlightFare;

public interface FlightFareDao {

	public boolean insertFlightFare(FlightFare fb);

	public List<FlightFare> getFlightFareByNamedQuery(String query, Map<String, Object> param);

	FlightFare getFlightFareByApId(int Id);

	boolean updateFlightFare(FlightFare fb);

}
