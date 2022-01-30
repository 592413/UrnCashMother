package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.InstantPayLog;



public interface InstantPayLogDao {


	public boolean insertInstantPayLog(InstantPayLog InstantPayLog);

	public boolean updateInstantPayLog(InstantPayLog InstantPayLog);

	public List<InstantPayLog> getInstantPayLogByNamedQuery(String query, Map<String, Object> param);

	public InstantPayLog getInstantPayLogById(int Id);






}
