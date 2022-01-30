package com.redbus.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.recharge.model.User;
import com.redbus.model.CancellationData;
import com.redbus.model.CancellationRequest;
import com.redbus.model.CancellationResponse;
import com.redbus.model.FareDetails;
import com.redbus.model.TripDetails;
import com.redbus.service.CancellationService;
import com.redbus.util.Constants;
import com.redbus.util.WebServiceClient;
import com.redbus.util.WebServiceResponseParser;
import com.sun.jersey.api.client.ClientResponse;

@Service("cancellationService")
public class CancellationServiceImpl implements CancellationService{
	private static final Logger log = Logger.getLogger(CancellationServiceImpl.class);
	private final Gson gson=new Gson();
	
	@Override
	public Map<String, Object> getCancellationData(Map<String, String> request, User userDetails) {
		CancellationData cancelData = new CancellationData();
		Map<String, Object> responseData = new HashMap<String, Object>();
		try{
			String url = Constants.BASE_URL+Constants.CANCELLATION_DATA;
			url=url.replace("{1}",request.get("tin"));			
			ClientResponse response=WebServiceClient.callGet(url);
		    String responseString = response.getEntity(String.class);
		    log.warn("rest api is : "+url);
		    if(response.getStatus()==200){
		    	 if(responseString != null && !responseString.isEmpty()){
		    		 cancelData = WebServiceResponseParser.getCancellationDataParser(responseString);
		    	 }
		    	//cancelData = gson.fromJson(responseString, CancellationData.class);
		    	responseData.put("status", "Success");
		    }else{
		    	responseData.put("status", responseString);
		    }
		    responseData.put("responseCode", response.getStatus());
		    responseData.put("responseData",cancelData);
			return responseData;
		}catch(Exception ex)
		{
			log.error("Exception",ex);
			return null;
		}
	}

	@Override
	public Map<String, Object> doCancelTicket(CancellationRequest request, User userDetails) {
		CancellationResponse  cancellationResponse = new CancellationResponse();
		Map<String, Object> responseData = new HashMap<String, Object>();
		try{
			String url = Constants.BASE_URL+Constants.CANCEL_TICKET;						
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(request));
		    String responseString = response.getEntity(String.class);
		    log.warn("rest api is : "+url);
		    if(response.getStatus()==200){
		    	cancellationResponse = gson.fromJson(responseString, CancellationResponse.class);
		    	responseData.put("status", "Success");
		    }else{
		    	responseData.put("status", responseString);
		    }
		    responseData.put("responseCode", response.getStatus());
		    responseData.put("responseData",cancellationResponse);
			return responseData;
		}catch(Exception ex)
		{
			log.error("Exception",ex);
			return null;
		}
	}

	@Override
	public Map<String, Object> getCancellationInfo(Map<String, String> request, User userDetails) {
		Map<String, Object> responseData = new HashMap<String, Object>();
		try{
			String url = Constants.BASE_URL + Constants.GET_BUS_CANCELLATION_INFO;
			if(!request.get("from").isEmpty() && !request.get("to").isEmpty()){
				url=url.replace("{1}",request.get("from"));	
				url=url.replace("{2}",request.get("to"));	
			}else{
				url = Constants.BASE_URL+Constants.GET_BUS_CANCELLATION_INFO_WITHOUT_DATE_RANGE;
			}								
			ClientResponse response=WebServiceClient.callGet(url);
		    String responseString = response.getEntity(String.class);
		    log.warn("rest api is : "+url);
		    if(response.getStatus()==200){
		    	responseData.put("status", "Success");
		    }else{
		    	responseData.put("status", responseString);
		    }
		    responseData.put("responseCode", response.getStatus());
		    responseData.put("responseData",responseString);
			return responseData;
		}catch(Exception ex)
		{
			log.error("Exception",ex);
			return null;
		}
	}
	
	
}
