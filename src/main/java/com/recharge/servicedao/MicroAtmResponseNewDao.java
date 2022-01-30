package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.MicroAtmResponse;
import com.recharge.model.MicroAtmResponseNew;

public interface MicroAtmResponseNewDao {

	public boolean insertMicroAtmResponseNew(MicroAtmResponseNew MicroAtmResponseNew);

	public boolean updateMicroAtmResponseNew(MicroAtmResponseNew MicroAtmResponseNew);

	public List<MicroAtmResponseNew> getMicroAtmResponseNewByNamedQuery(String query, Map<String, Object> param);

	public MicroAtmResponseNew getMicroAtmResponseNewById(int Id);






}
