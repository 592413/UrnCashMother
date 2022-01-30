package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

public interface CustomQueryDao {
	List<Object> getQueryResultFromCustomQuery(String sql,Map<String,String>parameters);
}
