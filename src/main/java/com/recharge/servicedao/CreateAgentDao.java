package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.CreateAgent;

public interface CreateAgentDao {

	public boolean insertagent(CreateAgent creatagent);

	public boolean updateagent(CreateAgent creatagent);

	public List<CreateAgent> getagentDetailsByNamedQuery(String query, Map<String, Object> param);

	public CreateAgent getagentById(Integer id);

}
