package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.AEPSUserMap;



public interface AEPSUserMapDao {


	public List<AEPSUserMap> getAllAEPSUserMap();

	public AEPSUserMap getAEPSUserMapById(String id);

	public boolean insertAEPSUserMap(AEPSUserMap AEPSUser);

	public boolean updateAEPSUserMap(AEPSUserMap AEPSUser);

	public void deleteAEPSUserMap(int id);

	public List<AEPSUserMap> getAEPSUserMapByNamedQuery(String query, Map<String, Object> param);




}
