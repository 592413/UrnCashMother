package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.InstantPayAepsResponse;



public interface InstantPayAepsResponseDao {

	public boolean insertInstantPayAepsResponse(InstantPayAepsResponse InstantPayAepsResponse);

	public boolean updateInstantPayAepsResponse(InstantPayAepsResponse InstantPayAepsResponse);

	public List<InstantPayAepsResponse> getInstantPayAepsResponseByNamedQuery(String query, Map<String, Object> param);

	public InstantPayAepsResponse getInstantPayAepsResponseById(int Id);


}
