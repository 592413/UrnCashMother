package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.AEPSCommission;



public interface AEPSCommissionDao {



	public List<AEPSCommission> getAllAEPSCommission();

	public AEPSCommission getAEPSCommissionById(String id);

	public boolean insertAEPSCommission(AEPSCommission AEPSCommission);

	public boolean updateAEPSCommission(AEPSCommission AEPSCommission);

	public void deleteAEPSCommission(int id);

	public List<AEPSCommission> getAEPSCommissionByNamedQuery(String query, Map<String, Object> param);





}
