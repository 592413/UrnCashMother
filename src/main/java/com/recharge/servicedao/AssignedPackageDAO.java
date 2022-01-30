package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.AssignedPackage;


public interface AssignedPackageDAO {

	public List<AssignedPackage> getAllAssignedPackage();

	public AssignedPackage getAssignedPackageById(Integer id);
	
	public boolean insertAssignedPackage(AssignedPackage asp);

	public boolean updateAssignedPackage(AssignedPackage asp);

	

	public List<AssignedPackage> getAssignedPackageByNamedQuery(String query, Map<String, Object> param);

	public boolean deleteAssignPackageById(String packageId);

}
