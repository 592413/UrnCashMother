package com.skyflight.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.recharge.model.User;
import com.skyflight.businessdao.FlightSearchDao;
import com.skyflight.searchCustomModel.AirSearchAvailability;
import com.skyflight.searchCustomModel.CalendarFareRes;
import com.skyflight.taxCustomModel.AirTaxResponse;

@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class SkyFlightSearchController {
	private static final Logger log = Logger.getLogger(SkyFlightSearchController.class);
	
	@Autowired HttpSession session;
	@Autowired FlightSearchDao FlightSearchDao;
	
	
	@RequestMapping("/flightSourceDestination")
	public @ResponseBody List<String> flightSourceDestination(@RequestParam String term) {
		List<String> airportlist = new ArrayList<String>();
		try {
			System.out.println("term::"+term);
			if(term.length()>0){
			airportlist = FlightSearchDao.getAirportcodes(term);	
			}

		} catch (Exception e) {
			log.error("flightSourceDestination :::::::::: " + e);
			
		}
		return airportlist;
	}
	

	@RequestMapping(value="/searchFlight",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String,Object> searchFlight(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "login");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				//if(userDetails.getUserName().equalsIgnoreCase("Y7F077")){
				if(!request.get("source").contains(",")){
					List<String> airportlist =FlightSearchDao.getAirportcodes(request.get("source"));
					request.put("source", airportlist.get(0));
				}
				if(!request.get("destination").contains(",")){
					List<String> airportlist =FlightSearchDao.getAirportcodes(request.get("destination"));
					request.put("destination", airportlist.get(0));
				}
				request.put("sources", "WEB");
				AirSearchAvailability output = FlightSearchDao.searchFlight(request,userDetails);
			//  System.out.println("searchFlight:"+output);
				
			    if(output.getResponseStatus()!=0){
			    returnData.put("status", output.getResponseStatus());	
				returnData.put("UserTrackId",output.getTrackId());
				returnData.put("minval", output.getMinvalue());	
				returnData.put("maxval",output.getMaxvalue());
				returnData.put("report",output.getSerachResults());
				//request.put("adult", Integer.parseInt(request.get("adult").toString()));
				returnData.put("requestDetails",request);
				String type=request.get("type").toString(); 
					 returnData.put("type",request.get("type"));
					 returnData.put("isDomestic",output.isDomestic());
					// returnData.put("returnreport", output.getSerachResults());
				 /*}else{
					 type="O";  
					 returnData.put("type","O");
				 }*/
				
			    }else{
			     returnData.put("status",output.getResponseStatus());
			     returnData.put("message",output.getErrormessage());
			    }
				
			/*}else{
			     returnData.put("status","0");
			     returnData.put("message","Access Denied");
			    }*/
				
			}
			log.error("searchFlight:::::::::::::"+returnData);
			
		}catch (Exception e) {
			log.error("searchFlight:::::::::::::"+e);
		}
		return returnData;
	}
	
	
	@RequestMapping(value="/fetchcalenderfare",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String,Object> fetchcalenderfare(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "login");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				
				CalendarFareRes output = FlightSearchDao.fetchcalenderfare(request,userDetails);
			//  System.out.println("searchFlight:"+output);
				
			    if(output.getResponseStatus()!=0){
			    returnData.put("status", output.getResponseStatus());	
				returnData.put("UserTrackId",output.getTrackId());
				String type=request.get("type"); 
					 returnData.put("type",request.get("type"));
					 returnData.put("CalendarFareResult", output.getCalendarFareResults());
				 /*}else{
					 type="O";  
					 returnData.put("type","O");
				 }*/
				
			    }else{
			     returnData.put("status",output.getResponseStatus());
			     returnData.put("message","OOPS!! No Data Found");
			    }
				
			}
			log.error("fetchcalenderfare:::::::::::::"+returnData);
			
		}catch (Exception e) {
			log.error("fetchcalenderfare:::::::::::::"+e);
		}
		return returnData;
	}
	
	
	@RequestMapping(value="/fetchcalenderflight",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String,Object> fetchcalenderflight(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "login");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				System.out.println(request);
				request.put("sources", "WEB");
				AirSearchAvailability output = FlightSearchDao.fetchcalenderflight(request,userDetails);
			    if(output.getResponseStatus()!=0){
			    	String []  travdt=request.get("depart").toString().split("T");
			    	request.put("depart", travdt[0]);
				    returnData.put("status", output.getResponseStatus());	
					returnData.put("UserTrackId",output.getTrackId());
					returnData.put("minval", output.getMinvalue());	
					returnData.put("maxval",output.getMaxvalue());
					returnData.put("report",output.getSerachResults());
					returnData.put("requestDetails",request);
						 returnData.put("type",request.get("type"));
					
					
				    }else{
				     returnData.put("status",output.getResponseStatus());
				     returnData.put("message","OOPS!! No Data Found");
				    }
				
			}
			log.error("fetchcalenderfare:::::::::::::"+returnData);
			
		}catch (Exception e) {
			log.error("fetchcalenderfare:::::::::::::"+e);
		}
		return returnData;
	}
	
	
	@RequestMapping(value="/GetTax",method=RequestMethod.POST)
	public Map<String,Object> GetTax(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "login");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				System.out.println(request);
				returnData = FlightSearchDao.getTax(request,userDetails);
				
				
			}
			log.error("searchFlight:::::::::::::"+returnData);
			
		}catch (Exception e) {
			log.error("searchFlight:::::::::::::"+e);
		}
		return returnData;
	}
	
	
	@RequestMapping(value="/getTaxroundtrip",method=RequestMethod.POST)
	public Map<String,Object> getTaxroundtrip(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "login");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				System.out.println(request);
				returnData = FlightSearchDao.getTax(request,userDetails);
				
				
			}
			log.error("getTaxroundtrip:::::::::::::"+returnData);
			
		}catch (Exception e) {
			log.error("getTaxroundtrip:::::::::::::"+e);
		}
		return returnData;
	}
	
	@RequestMapping(value="/getMealAndBaggageInfo",method=RequestMethod.POST)
	public Map<String,Object> getMealAndBaggageInfo(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "login");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				System.out.println("getMealAndBaggageInfo::"+request);
				returnData = FlightSearchDao.getMealAndBaggageInfo(request,userDetails);
				
				
			}
			log.error("getMealAndBaggageInfo:::::::::::::"+returnData);
			
		}catch (Exception e) {
			log.error("getMealAndBaggageInfo:::::::::::::"+e);
		}
		return returnData;
	}
	
	@RequestMapping(value="/getSeatRequest",method=RequestMethod.POST)
	public Map<String,Object> getSeatRequest(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "login");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				System.out.println("getSeatRequest:request:"+request);
				returnData = FlightSearchDao.getSeatRequest(request,userDetails);
				
				
			}
			log.error("getSeatRequest:::::::::::::"+returnData);
			
		}catch (Exception e) {
			log.error("getSeatRequest:::::::::::::"+e);
		}
		return returnData;
	}
	
}
