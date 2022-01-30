package com.skyflight.servicedao;

import java.util.List;
import java.util.Map;

import com.skyflight.model.Airline;



public interface AirlineDao {

	public Airline getAirlineByAirlineCode(String AirlineCode);

	public Integer insertAirline(Airline airline);

	public boolean updateAirline(Airline airline);

	public void deleteAirline(Integer airlineId);

	public List<Airline> getAirlineByNamedQuery(String query, Map<String, Object> param);

	public List<Airline> getAllAirline();

}
