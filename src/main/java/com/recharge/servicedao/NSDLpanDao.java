package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.NSDLPan;

public interface NSDLpanDao {

	public NSDLPan getNSDLPanById(int id);

	public boolean insertNSDLPan(NSDLPan NSDLPan);

	public boolean updateNSDLPan(NSDLPan NSDLPan);

	public List<NSDLPan> getNSDLPanByNamedQuery(String query, Map<String, Object> param);

}
