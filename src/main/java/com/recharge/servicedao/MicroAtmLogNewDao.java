package com.recharge.servicedao;

import java.util.List;
import java.util.Map;


import com.recharge.model.MicroAtmLogNew;

public interface MicroAtmLogNewDao {


	public boolean insertMicroAtmLogNew(MicroAtmLogNew MicroAtmLogNew);

	public boolean updateMicroAtmLogNew(MicroAtmLogNew MicroAtmLogNew);

	public List<MicroAtmLogNew> getMicroAtmLogNewByNamedQuery(String query, Map<String, Object> param);

	public MicroAtmLogNew getMicroAtmLogNewById(int Id);






}
