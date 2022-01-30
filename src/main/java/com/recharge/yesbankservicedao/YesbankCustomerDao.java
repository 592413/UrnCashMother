package com.recharge.yesbankservicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.AEPSUserMap;
import com.recharge.yesbankmodel.YesbankCustomer;



public interface YesbankCustomerDao {
	
	public List<YesbankCustomer> getYesbankCustomer();

	public YesbankCustomer getYesbankCustomerById(String id);

	public boolean insertYesbankCustomer(YesbankCustomer YesbankCustomer);

	public boolean updateYesbankCustomer(YesbankCustomer YesbankCustomer);
	
	public List<YesbankCustomer> getYesbankCustomerByNamedQuery(String query, Map<String, Object> param);
	
}
