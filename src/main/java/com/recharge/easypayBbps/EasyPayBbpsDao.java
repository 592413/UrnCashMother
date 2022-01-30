package com.recharge.easypayBbps;

import java.util.Map;

import com.recharge.model.User;

public interface EasyPayBbpsDao {

	public Map<String, Object> eBillFetch(Map<String, String> request, User userDetails);

	public Map<String, Object> bbpsBillPAy(Map<String, String> request, User userDetails);

}
