package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.ECommerceLog;


public interface ECommerceLogDao {


	public boolean insertECommerceLog(ECommerceLog ECommerceLog);

	public boolean updateECommerceLog(ECommerceLog ECommerceLog);

	public List<ECommerceLog> getECommerceLogByNamedQuery(String query, Map<String, Object> param);

	public ECommerceLog getECommerceLogById(int Id);






}
