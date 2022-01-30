package com.skyflight.businessdao;

import java.util.List;
import java.util.Map;

import com.skyflight.searchCustomModel.FlightMarkUp;

public interface CustomQueryServiceFlight {

	public List<FlightMarkUp> getDomesticFlightMarkupByUsername(String query, Map<String, String> parameters);

}
