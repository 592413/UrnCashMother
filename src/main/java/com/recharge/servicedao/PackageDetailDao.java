package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Api;
import com.recharge.model.PackageDetail;

public interface PackageDetailDao {
	public List<PackageDetail> getAllPackage();

	public PackageDetail getPackageByApId(int id);

	public boolean insertPackageDetail(PackageDetail pck);

	public boolean updatePackage(PackageDetail pck);

	public boolean deletePackageDetail(String packageId);
	
	public PackageDetail  getLastPackage();

	public List<PackageDetail> getPackageDetailByNamedQuery(String query, Map<String, Object> param);

	

}
