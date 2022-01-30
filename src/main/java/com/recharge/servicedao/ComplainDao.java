package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Complain;

public interface ComplainDao {
	public List<Complain> getAllComplain();

	public Complain getComplainById(Integer id);

	public boolean insertComplain(Complain complain);

	public boolean updateComplain(Complain complain);

	public void deleteComplain(Integer id);

	public List<Complain> getComplainByNamedQuery(String query, Map<String, Object> param);
}
