package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Apisetting;

public interface ApisettingDao {
	public List<Apisetting> getAllApisetting();

	public Apisetting getApisettingById(String id);

	public boolean insertApisetting(Apisetting apisetting);

	public boolean updateApisetting(Apisetting apisetting);

	public void deleteApisetting(int id);

	public List<Apisetting> getApisettingByNamedQuery(String query, Map<String, Object> param);
}
