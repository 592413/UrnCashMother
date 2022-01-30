package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.MicroAtmLog;



public interface MicroAtmLogDao {

	public boolean insertMicroAtmLog(MicroAtmLog MicroAtmLog);

	public boolean updateMicroAtmLog(MicroAtmLog MicroAtmLog);

	public List<MicroAtmLog> getMicroAtmLogByNamedQuery(String query, Map<String, Object> param);

	public MicroAtmLog getMicroAtmLogById(int Id);





}
