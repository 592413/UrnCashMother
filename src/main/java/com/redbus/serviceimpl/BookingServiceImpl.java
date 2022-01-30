package com.redbus.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.recharge.model.User;
import com.redbus.model.AvailableTrips;
import com.redbus.model.BlockRequest;
import com.redbus.model.BoardingDroppingTimes;
import com.redbus.model.FareDetails;
import com.redbus.model.Ticket;
import com.redbus.model.TripDetails;
import com.redbus.service.BookingService;
import com.redbus.util.Constants;
import com.redbus.util.WebServiceClient;
import com.redbus.util.WebServiceResponseParser;
import com.sun.jersey.api.client.ClientResponse;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {
	private static final Logger log = Logger.getLogger(BookingServiceImpl.class);
	private final Gson gson=new Gson();
	
	@Override
	public Map<String, Object> getAvailiableTrips(Map<String, String> request, User userDetails) {
		List<AvailableTrips>  availableTrips = new ArrayList<AvailableTrips>();		
		Map<String, Object> responseData = new HashMap<String, Object>();
		try{
			String url = Constants.BASE_URL+Constants.GET_AVAILABLE_TRIPS;
			url=url.replace("{1}",request.get("source"));
			url=url.replace("{2}",request.get("destination"));
			url=url.replace("{3}",request.get("doj"));
			ClientResponse response=WebServiceClient.callGet(url);
		    String responseString = response.getEntity(String.class);
		    log.warn("rest api is : "+url);
		    if(response.getStatus()==200){		
		    	 if(responseString != null && !responseString.isEmpty()){
		    		 availableTrips = WebServiceResponseParser.getAvailableTripsParser(responseString);
		    	 }
		    	 responseData.put("status", "Success");
		    }else{
		    	responseData.put("status", responseString);
		    }
		    responseData.put("responseCode", response.getStatus());
		     responseData.put("responseData",availableTrips);
		  
			return responseData;
		}catch(Exception ex)
		{
			log.error("Exception",ex);
			return null;
		}
	}

	@Override
	public Map<String, Object> getTripDetails(Map<String, String> request, User userDetails) {
		TripDetails  tripDetails = new TripDetails();
		Map<String, Object> responseData = new HashMap<String, Object>();
		try{
			String url = Constants.BASE_URL+Constants.GET_TRIP_DETAILS;
			url=url.replace("{1}",request.get("id"));			
			ClientResponse response=WebServiceClient.callGet(url);
		    String responseString = response.getEntity(String.class);
		    log.warn("rest api is : "+url);
		    if(response.getStatus()==200){
		    	tripDetails = gson.fromJson(responseString, TripDetails.class);
		    	responseData.put("status", "Success");
		    }else{
		    	responseData.put("status", responseString);
		    }
		    responseData.put("responseCode", response.getStatus());
		    responseData.put("responseData",tripDetails);
			return responseData;
		}catch(Exception ex)
		{
			log.error("Exception",ex);
			return null;
		}
	}

	@Override
	public Map<String, Object> getTripDetailsV2(Map<String, String> request, User userDetails) {
		TripDetails  tripDetails = new TripDetails();
		Map<String, Object> responseData = new HashMap<String, Object>();
		try{
			String url = Constants.BASE_URL+Constants.GET_TRIP_DETAILS_V2;						
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(request));
		    String responseString = response.getEntity(String.class);
		    log.warn("rest api is : "+url);
		    if(response.getStatus()==200){
		    	tripDetails = gson.fromJson(responseString, TripDetails.class);
		    	responseData.put("status", "Success");
		    }else{
		    	responseData.put("status", responseString);
		    }
		    responseData.put("responseCode", response.getStatus());
		    responseData.put("responseData",tripDetails);
			return responseData;
		}catch(Exception ex)
		{
			log.error("Exception",ex);
			return null;
		}
	}

	@Override
	public Map<String, Object> blockTicket(BlockRequest request, User userDetails) {
		Map<String, Object> responseData = new HashMap<String, Object>();
		try{
			String url = Constants.BASE_URL+Constants.BLOCK_TICKET;						
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(request));
		    String responseString = response.getEntity(String.class);
		    log.warn("rest api is : "+url);
		    if(response.getStatus()==200){
		    	 responseData.put("responseData",responseString);
		    	responseData.put("status", "Success");
		    }else{
		    	 responseData.put("responseData","");
		    	 responseData.put("status", responseString);
		    }
		   responseData.put("responseCode", response.getStatus());
		   return responseData;
		}catch(Exception ex)
		{
			log.error("Exception",ex);
			return null;
		}
	}

	@Override
	public Map<String, Object> getFareBreakup(Map<String, String> request, User userDetails) {
		FareDetails  fareDetails = new FareDetails();
		Map<String, Object> responseData = new HashMap<String, Object>();
		try{
			String url = Constants.BASE_URL+Constants.GET_RTC_FARE_BREAKUP;	
			url=url.replace("{1}",request.get("blockKey"));		
			ClientResponse response=WebServiceClient.callGet(url);
		    String responseString = response.getEntity(String.class);
		    log.warn("rest api is : "+url);
		    if(response.getStatus()==200){
		    	fareDetails = gson.fromJson(responseString, FareDetails.class);
		    	responseData.put("status", "Success");
		    }else{
		    	responseData.put("status", responseString);
		    }
		    responseData.put("responseCode", response.getStatus());
		    responseData.put("responseData",fareDetails);
			return responseData;
		}catch(Exception ex)
		{
			log.error("Exception",ex);
			return null;
		}
	}

	@Override
	public Map<String, Object> confirmBooking(Map<String, String> request, User userDetails) {
		Map<String, Object> responseData = new HashMap<String, Object>();
		try{
			String url = Constants.BASE_URL+Constants.CONFIRM_TICKET;	
			url=url.replace("{1}",request.get("blockKey"));		
			ClientResponse response=WebServiceClient.callPost(url, gson.toJson(request));
		    String responseString = response.getEntity(String.class);
		    log.warn("rest api is : "+url);
		    if(response.getStatus()==200){
		    	 responseData.put("responseData",responseString);
		    	responseData.put("status", "Success");
		    }else{
		    	 responseData.put("responseData","");
		    	 responseData.put("status", responseString);
		    }
		    responseData.put("responseCode", response.getStatus());
		   return responseData;
		}catch(Exception ex)
		{
			log.error("Exception",ex);
			return null;
		}
	}

	@Override
	public Map<String, Object> getTicketDetails(Map<String, String> request, User userDetails) {
		Ticket  ticketDetails = new Ticket();
		Map<String, Object> responseData = new HashMap<String, Object>();
		try{
			String url = Constants.BASE_URL+Constants.GET_TICKET_DETAILS;
			url=url.replace("{1}",request.get("tin"));			
			ClientResponse response=WebServiceClient.callGet(url);
		    String responseString = response.getEntity(String.class);
		    log.warn("rest api is : "+url);
		    if(response.getStatus()==200){
		    	 if(responseString != null && !responseString.isEmpty()){
		    		 ticketDetails = WebServiceResponseParser.getTicketDetailsParser(responseString);
		    	 }
		    	//ticketDetails = gson.fromJson(responseString, Ticket.class);
		    	responseData.put("status", "Success");
		    }else{
		    	responseData.put("status", responseString);
		    }
		    responseData.put("responseCode", response.getStatus());
		    responseData.put("responseData",ticketDetails);
			return responseData;
		}catch(Exception ex)
		{
			log.error("Exception",ex);
			return null;
		}
	}

	@Override
	public Map<String, Object> getBookedTicket(Map<String, String> request, User userDetails) {
		Ticket  ticketDetails = new Ticket();
		Map<String, Object> responseData = new HashMap<String, Object>();
		try{
			String url = Constants.BASE_URL+Constants.CHECK_BOOKED_TICKET_DETAILS;
			url=url.replace("{1}",request.get("blockKey"));			
			ClientResponse response=WebServiceClient.callGet(url);
		    String responseString = response.getEntity(String.class);
		    log.warn("rest api is : "+url);
		    if(response.getStatus()==200){
		    	 if(responseString != null && !responseString.isEmpty()){
		    		 ticketDetails = WebServiceResponseParser.getTicketDetailsParser(responseString);
		    	 }
		    	//ticketDetails = gson.fromJson(responseString, Ticket.class);
		    	responseData.put("status", "Success");
		    }else{
		    	responseData.put("status", responseString);
		    }
		    responseData.put("responseCode", response.getStatus());
		    responseData.put("responseData",ticketDetails);
			return responseData;
		}catch(Exception ex)
		{
			log.error("Exception",ex);
			return null;
		}
	}

	@Override
	public Map<String, Object> getBoardingPointDetails(Map<String, String> request, User userDetails) {
		BoardingDroppingTimes  boardingDetails = new BoardingDroppingTimes();
		Map<String, Object> responseData = new HashMap<String, Object>();
		try{
			String url = Constants.BASE_URL+Constants.GET_BORDING_PONT_DETAILS;
			url=url.replace("{1}",request.get("id"));	
			url=url.replace("{2}",request.get("tripId"));
			ClientResponse response=WebServiceClient.callGet(url);
		    String responseString = response.getEntity(String.class);
		    log.warn("rest api is : "+url);
		    if(response.getStatus()==200){
		    	 if(responseString != null && !responseString.isEmpty()){
		    		 boardingDetails = WebServiceResponseParser.getBoardingPointDetailsParser(responseString);
		    	 }		    
		    	responseData.put("status", "Success");
		    }else{
		    	responseData.put("status", responseString);
		    }
		    responseData.put("responseCode", response.getStatus());
		    responseData.put("responseData",boardingDetails);
			return responseData;
		}catch(Exception ex)
		{
			log.error("Exception",ex);
			return null;
		}
	}
	
	

}
