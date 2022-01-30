package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.User;
import com.recharge.model.Utility;

public interface UtilityDao {
	public List<Utility> getAllUtility();

	public User getUtilityByUtilityId(Integer utilityId);

	public boolean insertUtility(Utility utility);

	public boolean updateUtility(Utility utility);

	public void deleteUtility(Integer utilityId);

	public List<Utility> getUtilityByNamedQuery(String query, Map<String, Object> param);
}
