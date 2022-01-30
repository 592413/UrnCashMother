package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.SettlementCharge;



public interface SettlementChargeDao {

	public List<SettlementCharge> getAllSettlementCharge();

	public SettlementCharge getSettlementChargeById(Integer id);

	public boolean insertSettlementCharge(SettlementCharge SettlementCharge);

	public boolean updateSettlementCharge(SettlementCharge SettlementCharge);

	public void deleteSettlementCharge(Integer id);

	public List<SettlementCharge> getSettlementChargeByNamedQuery(String query, Map<String, Object> param);




}
