package com.recharge.icicidmtserviceDao;

import java.util.List;
import java.util.Map;

import com.recharge.icicidmtmodel.P2AMoneyUser;



public interface P2AMoneyUserDao {
	public List<P2AMoneyUser> getAllP2AMoneyUser();

	public P2AMoneyUser getP2AMoneyUserById(int id);

	public boolean insertP2AMoneyUser(P2AMoneyUser pmu);

	public boolean updateP2AMoneyUser(P2AMoneyUser pmu);

	public boolean deleteP2AMoneyUser(int id);

	public List<P2AMoneyUser> getP2AMoneyUserByNamedQuery(String query, Map<String, Object> param);

	public boolean deleteP2AMoneyUser(String id);

}
