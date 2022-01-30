package com.recharge.servicedao;

import java.util.List;
import java.util.Map;


import com.recharge.model.AEPSLog;

public interface AEPSLogDao {

	public boolean insertAEPSLog(AEPSLog AEPSLog);

	public boolean updateAEPSLog(AEPSLog AEPSLog);

	public List<AEPSLog> getAEPSLogByNamedQuery(String query, Map<String, Object> param);

	public AEPSLog getAEPSLogById(int Id);





}
