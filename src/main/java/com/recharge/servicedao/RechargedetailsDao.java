package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Rechargedetails;

public interface RechargedetailsDao {
	public List<Rechargedetails> getAllRechargeDetails();

	public Rechargedetails getRechargeDetailsById(Integer Id);

	public boolean insertRechargeDetails(Rechargedetails rechargedetails);

	public boolean updateRechargeDetails(Rechargedetails rechargedetails);

	public void deleteRechargeDetails(Integer rechargeId);

	public List<Rechargedetails> getRechargeDetailsByNamedQuery(String query, Map<String, Object> param);
}
