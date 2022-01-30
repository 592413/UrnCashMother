package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Reseller;

public interface ResellerDao {
	public List<Reseller> getAllReseller();

	public Reseller getResellerById(String id);

	public boolean insertReseller(Reseller reseller);

	public boolean updateReseller(Reseller reseller);

	public void deleteReseller(int id);

	public List<Reseller> getResellerByNamedQuery(String query, Map<String, Object> param);
}
