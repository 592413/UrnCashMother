package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.InsuranceRegister;
import com.recharge.model.User;

public interface InsuranceRegisterDao {

	public boolean insertInsuranceRegister(InsuranceRegister InsuranceRegister);

	public List<InsuranceRegister> getInsuranceRegisterByNamedQuery(String query, Map<String, Object> param);

	public InsuranceRegister getInsuranceRegisterByUserId(String userId);

}
