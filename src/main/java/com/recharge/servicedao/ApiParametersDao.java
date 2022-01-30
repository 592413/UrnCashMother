package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.ApiParameters;



public interface ApiParametersDao {

	public List<ApiParameters> getAllApiParameters();

	public ApiParameters getApiParametersByApId(String apiId);

	public boolean insertApiParameters(ApiParameters api);

	public boolean updateApiParameters(ApiParameters api);

	public void deleteApiParameters(int apiId);

	public List<ApiParameters> getApiParametersByNamedQuery(String query, Map<String, Object> param);


}
