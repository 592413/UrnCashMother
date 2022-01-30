package com.recharge.servicedao;

import java.util.List;
import java.util.Map;
import com.recharge.model.Impsverification;

public interface ImpsverificationDao {
	public List<Impsverification> getAllImpsverification();

	public Impsverification getImpsverificationById(String Id);

	public boolean insertImpsverification(Impsverification impsverification);

	public boolean updateImpsverification(Impsverification impsverification);

	public void deleteImpsverification(int id);

	public List<Impsverification> getImpsverificationByNamedQuery(String query, Map<String, Object> param);
}
