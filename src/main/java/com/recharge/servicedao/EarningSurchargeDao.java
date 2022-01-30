package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.EarningSurcharge;

public interface EarningSurchargeDao {
	public List<EarningSurcharge> getAllEarningSurcharge();

	public EarningSurcharge getEarningSurchargeById(String Id);

	public boolean insertEarningSurcharge(EarningSurcharge earningSurcharge);

	public boolean updateEarningSurcharge(EarningSurcharge earningSurcharge);

	public void deleteEarningSurcharge(int Id);

	public List<EarningSurcharge> getEarningSurchargeByNamedQuery(String query, Map<String, Object> param);
}
