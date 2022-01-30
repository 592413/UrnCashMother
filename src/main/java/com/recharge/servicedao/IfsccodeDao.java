package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Api;
import com.recharge.model.Ifsccode;

public interface IfsccodeDao {
	public List<Ifsccode> getAllIfsccode();

	public Ifsccode getIfsccodeById(String id);

	public boolean insertIfsccode(Ifsccode ifsccode);

	public boolean updateIfsccode(Ifsccode ifsccode);

	public void deleteIfsccode(int id);

	public List<Ifsccode> getIfsccodeByNamedQuery(String query, Map<String, Object> param);
}
