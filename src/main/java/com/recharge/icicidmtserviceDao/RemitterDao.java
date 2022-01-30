package com.recharge.icicidmtserviceDao;

import java.util.List;
import java.util.Map;

import com.recharge.icicidmtmodel.Remitter;


public interface RemitterDao {

	public boolean insertRemitter(Remitter Remitter);

	public boolean updateRemitter(Remitter Remitter);

	public boolean deleteRemitter(int id);

	public List<Remitter> getRemitterByNamedQuery(String query, Map<String, Object> param);





}
