package com.recharge.servicedao;

import java.util.List;
import java.util.Map;


import com.recharge.model.PackageWiseChargeComm;

public interface PackageWiseChargeCommDao {

	public boolean insertPackageWiseChargeComm(PackageWiseChargeComm pck);

	public boolean updatePackageWiseChargeComm(PackageWiseChargeComm pck);

	public List<PackageWiseChargeComm> getPackageDetailByNamedQuery(String query, Map<String, Object> param);
	
	public boolean updatePackagewiseCommByOperator(Map<String, String> request);

	public boolean deletePackageWiseChargeComm(String packageId);


}
