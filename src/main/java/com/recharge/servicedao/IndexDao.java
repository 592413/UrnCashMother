package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Indexx;

public interface IndexDao {

	public boolean insertIndex(Indexx index);

	public List<Indexx> getIndexxByNamedQuery(String query, Map<String, Object> param);


}
