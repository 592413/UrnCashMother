package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.ResponseCounter;

public interface ResponseCounterDao {

	public boolean insertResponseCounter(ResponseCounter ResponseCounter);

	public boolean updateResponseCounter(ResponseCounter ResponseCounter);

	public List<ResponseCounter> getResponseCounterByNamedQuery(String query, Map<String, Object> param);

}
