package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Operator;

public interface OperatorDao {
	public List<Operator> getAllOperator();

	public Operator getOperatorByOperatorId(Integer operatorId);

	public Integer insertOperator(Operator operator);

	public boolean updateOperator(Operator operator);

	public void deleteOperator(Integer operatorId);

	public List<Operator> getOperatorByNamedQuery(String query, Map<String, Object> param);

	public List<Operator> getAllOpeartor();
}
