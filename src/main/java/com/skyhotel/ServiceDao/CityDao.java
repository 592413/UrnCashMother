package com.skyhotel.ServiceDao;

import java.util.List;

import com.skyhotel.model.City;


public interface CityDao {
	public boolean insertCity(City city);
	
	public List<City> getCity(String key);

	public List<City> getAllCity();
	
	

}
