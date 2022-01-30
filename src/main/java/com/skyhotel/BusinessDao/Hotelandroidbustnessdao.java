package com.skyhotel.BusinessDao;

import java.util.Map;

public interface Hotelandroidbustnessdao {


	public Map<String, Object> hotelcity(String request);

	public Map<String, Object> searchHotalapps(String request);

	public Map<String, Object> HOtelDetails(String request);

	public Map<String, Object> HOtelBlockRoom(String request);

	public Map<String, Object> HOtelBookRoom(String request);

}
