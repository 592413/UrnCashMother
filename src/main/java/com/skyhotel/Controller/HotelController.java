package com.skyhotel.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.recharge.model.User;
import com.skyhotel.BusinessDao.HotelBusinessDao;
import com.skyhotel.CustomModel.HotelBookResponse;
import com.skyhotel.CustomModel.HotelDetailsOutput;
import com.skyhotel.CustomModel.RoomBlockOutput;

@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class HotelController {
	private static final Logger log = Logger.getLogger(HotelController.class);
	@Autowired HttpSession session;
	@Autowired HotelBusinessDao HotelBusinessDao;
	

	@RequestMapping(value = "/hotel")
	public ModelAndView hotelSearch() {
		ModelAndView mv = new ModelAndView("hotel/hotelSearch");
		return mv;
	//	return "hotel/hotelSearch";
	}
	
	

	@RequestMapping(value="/SaveHotelMarkUpData",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String,Object> SaveHotelMarkUpData(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else{
				User user = (User) session.getAttribute("UserDetails");
				returnData =  HotelBusinessDao.SaveHotelMarkUpData(request,user);
			}
			
		}catch(Exception e){
			log.error("saveMarkUpData:::::::::::::"+e);
		}
		return returnData;
		
	}
	

	@RequestMapping(value="/DestinationCities",method=RequestMethod.POST,headers="content-type=application/json")
	public List<String> getCity(@RequestBody Map<String, Object> request){
		List<String> cities=new ArrayList<>();
		if(request.get("term").toString().length()>2){
		cities=HotelBusinessDao.getDestinationCities(request.get("term").toString());
		}
		return cities;
	}
	
	/*@RequestMapping(value="/searchHotal",method=RequestMethod.POST)
	public Map<String,Object> searchHotal(){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "login");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				Map<String, String> request = new HashMap<String, String>();
				request.put("checkin", "2020-04-24");
				request.put("checkout", "2020-04-26");
				SearchResult output=HotelBusinessDao.searchHotal(request);
				if(output.getResponseStatus()==1){
					returnData.put("status", "1");
				}else{
					returnData.put("status", "0");	
				}
				returnData.put("output", output);
			}
			
		}catch(Exception e){
			returnData.put("status", "0");
			log.error("searchHotal:::::::::::::"+e);
		}
		
		return returnData;
	}*/
	
	@RequestMapping(value="/searchHotal",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String,Object> searchHotal(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "login");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData=HotelBusinessDao.searchHotal(request,userDetails);
				
			}
			
		}catch(Exception e){
			returnData.put("status", "0");
			log.error("searchHotal:::::::::::::"+e);
		}
		
		return returnData;
	}
	
	@RequestMapping(value="/searchHotalDetails",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String,Object> searchHotalDetails(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "login");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				HotelDetailsOutput output=HotelBusinessDao.searchHotalDetails(request,userDetails);
				if(output.getResponseStatus()==1){
					returnData.put("status", "1");
				}else{
					returnData.put("status", "0");	
				}
				returnData.put("output", output);
			}
			
		}catch(Exception e){
			returnData.put("status", "0");
			log.error("searchHotalDetails:::::::::::::"+e);
		}
		
		return returnData;
	}
	
	
	@RequestMapping(value="/getroomblock",method=RequestMethod.POST)
	public Map<String,Object> getroomblock(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "login");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				RoomBlockOutput output=new RoomBlockOutput(); 
				output=HotelBusinessDao.getroomblock(request);
				if(output.getResponseStatus()==1){
					if(output.getAvailabilityType().equalsIgnoreCase("Confirm")){
						returnData.put("status", "1");
					}else{
						returnData.put("status", "2");
					}
										
					
				}else{
					returnData.put("status", "0");	
				}
				returnData.put("output", output);
			}
			
		}catch(Exception e){
			returnData.put("status", "0");
			log.error("searchHotalDetails:::::::::::::"+e);
		}
		
		return returnData;
	}

	@RequestMapping(value="/getroombook",method=RequestMethod.POST)
	public Map<String,Object> getroombook(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "login");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				
				User user=(User) session.getAttribute("UserDetails");
				
				returnData=HotelBusinessDao.getroombook(request,user);
			
			}
			
		}catch(Exception e){
			returnData.put("status", "0");
			log.error("getroombook:::::::::::::"+e);
		}
		
		return returnData;
	}
	
	@RequestMapping(value="/viewHotelBooikngReport",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String,Object> viewHotelBooikngReport(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData=HotelBusinessDao.viewHotelBooikngReport(userDetails,request);
			}
			
		}catch(Exception e){
			log.error("viewHotelBooikngReport:::::::::::::"+e);
			}
		return returnData;
	}
}
