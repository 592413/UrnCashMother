package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Signinhistory;

public interface SigninhistoryDao {
	public List<Signinhistory> getAllSigninhistory();

	public Signinhistory getSigninhistoryById(Integer id);

	public boolean insertSigninhistory(Signinhistory signinhistory);

	public boolean updateSigninhistory(Signinhistory signinhistory);

	public void deleteSigninhistory(Integer id);

	public List<Signinhistory> getSigninhistoryByNamedQuery(String query, Map<String, Object> param);
}
