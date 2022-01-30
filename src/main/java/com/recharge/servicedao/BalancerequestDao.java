package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Balancerequest;

public interface BalancerequestDao {
	public List<Balancerequest> getAllBalanceRequest();

	public Balancerequest getBalanceRequestByRequestId(Integer requestId);

	public boolean insertBalanceRequest(Balancerequest balancerequest);

	public boolean updateBalanceRequest(Balancerequest balancerequest);

	public void deleteBalanceRequest(Integer requestId);

	public List<Balancerequest> getBalanceRequestByNamedQuery(String query, Map<String, Object> param);
}
