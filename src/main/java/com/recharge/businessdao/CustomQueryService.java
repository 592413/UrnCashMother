package com.recharge.businessdao;

import java.util.List;
import java.util.Map;

public interface CustomQueryService {
	public List<Object> getDataByQueryUsingCustomQuery(String sql,Map<String,String>param);

}
