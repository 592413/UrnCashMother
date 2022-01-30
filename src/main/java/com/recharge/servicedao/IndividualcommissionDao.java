package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Individualcommission;

public interface IndividualcommissionDao {
	public List<Individualcommission> getAllIndividualCommission();

	public Individualcommission getIndividualCommissionByChargeId(Integer commissionId);

	public boolean insertIndividualCommission(Individualcommission individualcommission);

	public boolean updateIndividualCommission(Individualcommission individualcommission);

	public void deleteIndividualCommission(Integer commissionId);

	public List<Individualcommission> getIndividualCommissionByNamedQuery(String query, Map<String, Object> param);
}
