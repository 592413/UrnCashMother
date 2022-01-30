package com.recharge.servicedao;

import java.util.List;
import java.util.Map;


import com.recharge.model.MicroAtmResponse;

public interface MicroAtmResponseDao {

	public boolean insertMicroAtmResponse(MicroAtmResponse MicroAtmResponse);

	public boolean updateMicroAtmResponse(MicroAtmResponse MicroAtmResponse);

	public List<MicroAtmResponse> getMicroAtmResponseByNamedQuery(String query, Map<String, Object> param);

	public MicroAtmResponse getMicroAtmResponseById(int Id);






}
