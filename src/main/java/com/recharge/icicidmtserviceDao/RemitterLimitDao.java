package com.recharge.icicidmtserviceDao;

import java.util.List;
import java.util.Map;

import com.recharge.icicidmtmodel.RemitterLimit;



public interface RemitterLimitDao {



	public List<RemitterLimit> getRemitterLimit();

	public RemitterLimit getRemitterLimitById(String id);

	public boolean insertRemitterLimit(RemitterLimit RemitterLimit);

	public boolean updateRemitterLimit(RemitterLimit RemitterLimit);

	public void deleteRemitterLimit(int id);

	public List<RemitterLimit> getRemitterLimitByNamedQuery(String query, Map<String, Object> param);




}
