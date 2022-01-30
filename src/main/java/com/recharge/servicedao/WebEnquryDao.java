package com.recharge.servicedao;


import java.util.List;
import java.util.Map;

import com.recharge.model.webenquery;

public interface WebEnquryDao {
	
	public boolean insertquery(webenquery webenquery);

	public List<webenquery> getUserByNamedQuery(String query, Map<String, Object> param);

}
