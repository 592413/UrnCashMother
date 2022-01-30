package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.AEPSBankDetail;


public interface AEPSBankDetailDao {

	public List<AEPSBankDetail> getAllAEPSBankDetail();

	public List<AEPSBankDetail> getAEPSBankDetailByNamedQuery(String query, Map<String, Object> param);
}
