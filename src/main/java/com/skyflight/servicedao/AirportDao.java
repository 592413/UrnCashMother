package com.skyflight.servicedao;

import java.util.List;
import java.util.Map;

import com.skyflight.model.Airport_code;


public interface AirportDao {
	public List<Airport_code>  getCities(String key);
	public List<Airport_code> getAirportcodeByNamedQuery(String query, Map<String, Object> param);
	public List<Airport_code> getAllAirport_code();

}
