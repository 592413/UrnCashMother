package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Apitrace;

public interface ApitraceDao {
	public List<Apitrace> getAllApitrace();

	public Apitrace getApitraceById(String id);

	public boolean insertApitrace(Apitrace apitrace);

	public boolean updateApitrace(Apitrace apitrace);

	public void deleteApitrace(int id);

	public List<Apitrace> getApitraceByNamedQuery(String query, Map<String, Object> param);
}
