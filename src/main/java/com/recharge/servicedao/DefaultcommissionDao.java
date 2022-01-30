package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Defaultcommission;

public interface DefaultcommissionDao {
	public List<Defaultcommission> getAllBalanceTransfer();

	public Defaultcommission getDefaultCommissionByChargeId(Integer commissionId);

	public boolean insertDefaultCommission(Defaultcommission defaultcommission);

	public boolean updateDefaultCommission(Defaultcommission defaultcommission);

	public void deleteDefaultCommission(Integer commissionId);

	public List<Defaultcommission> getDefaultCommissionByNamedQuery(String query, Map<String, Object> param);
}
