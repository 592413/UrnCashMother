package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Insurance;

public interface InsuranceDao {
	public List<Insurance> getAllInsurance();

	public Insurance getInsuranceById(String id);

	public boolean insertInsurance(Insurance insurance);

	public boolean updateInsurance(Insurance insurance);

	public void deleteInsurance(int id);

	public List<Insurance> getInsuranceByNamedQuery(String query, Map<String, Object> param);
}
