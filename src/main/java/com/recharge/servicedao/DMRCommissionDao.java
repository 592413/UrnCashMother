package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.DMRCommission;

public interface DMRCommissionDao {

	public List<DMRCommission> getAllDMRCommission();

	public DMRCommission getDMRCommissionById(String id);

	public boolean insertDMRCommission(DMRCommission AEPSCommission);

	public boolean updateDMRCommission(DMRCommission AEPSCommission);

	public List<DMRCommission> getDMRCommissionByNamedQuery(String query, Map<String, Object> param);

}
