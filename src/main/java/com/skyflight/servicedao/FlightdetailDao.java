package com.skyflight.servicedao;

import java.util.List;
import java.util.Map;

import com.skyflight.model.Flightdetail;




public interface FlightdetailDao {

	

	public List<Flightdetail> getAllFlightdetail();

	public Flightdetail getFlightdetailById(String apiId);

	public boolean insertFlightdetail(Flightdetail fb);

	public boolean updateFlightdetail(Flightdetail fb);

	public void deleteFlightdetail(int fbId);

	public List<Flightdetail> getFlightdetailByNamedQuery(String query, Map<String, Object> param);




}
