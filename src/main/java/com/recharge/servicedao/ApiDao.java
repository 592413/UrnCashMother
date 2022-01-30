package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Api;

public interface ApiDao {
	public List<Api> getAllApi();

	public Api getApiByApId(int apiId);

	public boolean insertApi(Api api);

	public boolean updateApi(Api api);

	public void deleteApi(int apiId);

	public List<Api> getApiByNamedQuery(String query, Map<String, Object> param);
}
