package com.redbus.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.redbus.model.Cities;
import com.redbus.model.CommonData;
import com.redbus.service.CityService;
import com.redbus.util.Constants;
import com.redbus.util.WebServiceClient;
import com.sun.jersey.api.client.ClientResponse;

@Service("cityService")
public class CityServiceImpl implements CityService {
	private static final Logger log = Logger.getLogger(CityServiceImpl.class);
	private final Gson gson=new Gson();
	
	@Override
	public Map<String, Object> getCities() {
		CommonData data = new CommonData();
		Map<String, Object> responseData = new HashMap<String, Object>();
		try{
			String url = Constants.BASE_URL+Constants.GET_CITIES;
			ClientResponse response=WebServiceClient.callGet(url);
		    String responseString = response.getEntity(String.class);
		    log.warn("rest api is : "+url);
		    data = gson.fromJson(responseString, CommonData.class);
		    responseData.put("responseData",data.getCities());
		    responseData.put("responseCode", response.getStatus());
			return responseData;
		}catch(Exception ex)
		{
			log.error("Exception",ex);
			return null;
		}
	}

	@Override
	public Map<String, Object> getAliases() {
		List<Cities>  aliases = new ArrayList<Cities>();
		Map<String, Object> responseData = new HashMap<String, Object>();
		try{
			String url = Constants.BASE_URL+Constants.GET_ALIASES;
			ClientResponse response=WebServiceClient.callGet(url);
		    String responseString = response.getEntity(String.class);
		    log.warn("rest api is : "+url);
		    aliases = gson.fromJson(responseString, new TypeToken<List<Cities>>() {}.getType());
		    responseData.put("responseData",aliases);
		    responseData.put("responseCode", response.getStatus());
			return responseData;
		}catch(Exception ex)
		{
			log.error("Exception",ex);
			return null;
		}
	}

}
