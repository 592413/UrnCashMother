package com.skyflight.servicedao;

import java.util.List;
import java.util.Map;

import com.skyflight.model.GstFlight;

public interface GstFlightDao {

	boolean insertGstFlight(GstFlight GstFlight);

	boolean updateGstFlight(GstFlight GstFlight);

	List<GstFlight> getGstFlightLogByNamedQuery(String query, Map<String, Object> param);

	GstFlight getGstFlightByUserId(String userId);

}
