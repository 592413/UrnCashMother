package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.SettlementBalance;



public interface SettlementBalanceDao {

	public List<SettlementBalance> getAllSettlementBalance();

	public SettlementBalance getSettlementBalanceById(Integer id);

	public boolean insertSettlementBalance(SettlementBalance SettlementBalance);

	public boolean updateSettlementBalance(SettlementBalance SettlementBalance);

	public void deleteSettlementBalance(Integer id);

	public List<SettlementBalance> getSettlementBalanceByNamedQuery(String query, Map<String, Object> param);


}
