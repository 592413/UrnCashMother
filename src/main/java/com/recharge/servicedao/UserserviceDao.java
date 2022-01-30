package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Userservice;

public interface UserserviceDao {
	public List<Userservice> getAllUserService();

	public Userservice getUserServiceByUserId(String id);

	public Integer insertUserService(Userservice userservice);

	public boolean updateUserService(Userservice userservice);

	public void deleteUserService(Integer id);

	public List<Userservice> getUserServiceByNamedQuery(String query, Map<String, Object> param);
}
