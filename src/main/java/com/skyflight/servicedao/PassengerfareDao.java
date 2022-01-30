package com.skyflight.servicedao;

import java.util.List;
import java.util.Map;

import com.skyflight.model.Passengerfare;

public interface PassengerfareDao {


	

	public List<Passengerfare> getAllPassengerfare();

	public Passengerfare getPassengerfareById(String apiId);

	public boolean insertPassengerfare(Passengerfare pf);

	public boolean updatePassengerfare(Passengerfare pf);

	public void deletePassengerfare(int pfId);

	public List<Passengerfare> getPassengerfareByNamedQuery(String query, Map<String, Object> param);






}
