package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Service;

public interface ServiceDao {
	public List<Service> getAllService();

	public Service getServiceByServiceType(Integer id);

	public Integer insertService(Service service);

	public boolean updateService(Service service);

	public void deleteService(Integer serviceType);

	public List<Service> getServiceByNamedQuery(String query, Map<String, Object> param);
}
