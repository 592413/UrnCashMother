package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Impscommission;

public interface ImpscommissionDao {
	public List<Impscommission> getAllImpscommission();

	public Impscommission getImpscommissionById(Integer Id);

	public boolean insertImpscommission(Impscommission impscommission);

	public boolean updateImpscommission(Impscommission impscommission);

	public void deleteImpscommission(int id);

	public List<Impscommission> getImpscommissionByNamedQuery(String query, Map<String, Object> param);
}
