package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Chargeset;

public interface ChargesetDao {
	public List<Chargeset> getAllBalanceTransfer();

	public Chargeset getChargeSetByChargeId(Integer chargeId);

	public boolean insertChargeSet(Chargeset chargeset);

	public boolean updateChargeSet(Chargeset chargeset);

	public void deleteChargeSet(Integer chargeId);

	public List<Chargeset> getChargeSetByNamedQuery(String query, Map<String, Object> param);
}
