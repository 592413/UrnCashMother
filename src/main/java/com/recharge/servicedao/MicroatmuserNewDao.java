package com.recharge.servicedao;

import java.util.List;
import java.util.Map;


import com.recharge.model.MicroatmUserNew;

public interface MicroatmuserNewDao {
	public boolean insertMicroatmUserNew(MicroatmUserNew MicroatmUserNew);

	public boolean updateMicroatmUserNew(MicroatmUserNew MicroatmUserNew);

	public List<MicroatmUserNew> getMicroatmUserNewByNamedQuery(String query, Map<String, Object> param);

	public MicroatmUserNew getMicroatmUserNewById(int Id);

}
