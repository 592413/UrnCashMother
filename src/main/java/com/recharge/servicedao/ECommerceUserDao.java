package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.ECommerceUser;



public interface ECommerceUserDao {

	



	public List<ECommerceUser> getAllECommerceUser();

	public ECommerceUser getECommerceUserMapById(String id);

	public boolean insertECommerceUser(ECommerceUser ECommerceUser);

	public boolean updateECommerceUser(ECommerceUser ECommerceUser);

	public void deleteECommerceUser(int id);

	public List<ECommerceUser> getECommerceUserByNamedQuery(String query, Map<String, Object> param);







}
