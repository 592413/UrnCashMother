package com.skyhotel.ServiceDao;

import java.util.List;
import java.util.Map;

import com.skyhotel.model.HotelLeadPassenger;


public interface HotelLeadPassengerDao {

	public boolean insertHotelLeadPassenger(HotelLeadPassenger HotelLeadPassenger);

	public boolean updateHotelLeadPassenger(HotelLeadPassenger HotelLeadPassenger);

	public List<HotelLeadPassenger> getHotelLeadPassengerByNamedQuery(String query, Map<String, Object> param);

	



}
