package com.recharge.servicedao;

import java.util.List;
import java.util.Map;


import com.recharge.model.PanApplication;

public interface PanApplicationDao {




	public List<PanApplication> getAllPanApplication();

	public PanApplication getPanApplicationById(String id);

	public boolean insertPanApplication(PanApplication PanApplication);

	public boolean updatePanApplication(PanApplication PanApplication);

	public void deletePanApplication(int id);

	public List<PanApplication> getPanApplicationByNamedQuery(String query, Map<String, Object> param);






}
