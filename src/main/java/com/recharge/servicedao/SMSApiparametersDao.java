package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.SMSApiparameters;



public interface SMSApiparametersDao {

	public List<SMSApiparameters> getAllSMSApiparameters();

	public SMSApiparameters getSMSApiparametersById(Integer id);

	public boolean insertSMSApiparameters(SMSApiparameters params);

	public boolean updateSMSApiparameters(SMSApiparameters params);

	public void deleteSMSApiparameters(Integer id);

	public List<SMSApiparameters> getSMSApiparametersByNamedQuery(String query, Map<String, Object> param);

}
