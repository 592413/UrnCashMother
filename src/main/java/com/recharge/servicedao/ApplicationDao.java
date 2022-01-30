package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Ezpayapplicationform;


public interface ApplicationDao {
	public List<Ezpayapplicationform> getAllEzpayapplicationform();

	public Ezpayapplicationform getEzpayapplicationformMapById(String id);

	public boolean insertEzpayapplicationform(Ezpayapplicationform Ezpayapplicationform);

	public boolean updateEzpayapplicationform(Ezpayapplicationform Ezpayapplicationform);

	public void deleteEzpayapplicationform(int id);

	public List<Ezpayapplicationform> getEzpayapplicationformByNamedQuery(String query, Map<String, Object> param);
		
	

}
