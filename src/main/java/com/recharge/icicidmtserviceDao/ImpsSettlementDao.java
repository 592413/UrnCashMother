package com.recharge.icicidmtserviceDao;

import java.util.List;
import java.util.Map;

import com.recharge.icicidmtmodel.ImpsSettlement;
import com.recharge.model.AEPSUserMap;

public interface ImpsSettlementDao {


	public List<ImpsSettlement> getAllImpsSettlement();

	public ImpsSettlement getImpsSettlementById(String id);

	public boolean insertImpsSettlement(ImpsSettlement ImpsSettlement);

	public boolean updateImpsSettlement(ImpsSettlement ImpsSettlement);

	public boolean deleteImpsSettlement(int id);

	public List<ImpsSettlement> getImpsSettlementByNamedQuery(String query, Map<String, Object> param);




}
