package com.redbus.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.recharge.model.User;
import com.redbus.model.BlockRequest;
import com.redbus.service.BookingService;

@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class BookingController {
	private static final Logger log = Logger.getLogger(BookingController.class);
	@Autowired HttpSession session;
	@Autowired BookingService bookingService;
	
	@RequestMapping(value="/getAvailableTrips",method = RequestMethod.POST,headers = "content-type=application/json")
	public Map<String, Object> getAvailiableTrips(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			/*if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {*/
			    User userDetails = (User) session.getAttribute("UserDetails");
				returnData=bookingService.getAvailiableTrips(request,userDetails);
				
			//}
			
		}catch(Exception e){
			log.error("get available trips in red bus:::::::::::::"+e);
			}
		return returnData;
			
	}
	
	@RequestMapping(value="/getTripDetails",method = RequestMethod.POST,headers = "content-type=application/json")
	public Map<String, Object> getTripDetails(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			/*if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {*/
			    User userDetails = (User) session.getAttribute("UserDetails");
				returnData=bookingService.getTripDetails(request,userDetails);
				
			//}
			
		}catch(Exception e){
			log.error("get trip details  in red bus:::::::::::::"+e);
			}
		return returnData;
			
	}
	
	@RequestMapping(value="/getTripDetailsV2",method = RequestMethod.POST,headers = "content-type=application/json")
	public Map<String, Object> getTripDetailsV2(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			/*if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {*/
			    User userDetails = (User) session.getAttribute("UserDetails");
				returnData=bookingService.getTripDetailsV2(request,userDetails);
				
			//}
			
		}catch(Exception e){
			log.error("gettrip details v2 in red bus:::::::::::::"+e);
			}
		return returnData;
			
	}
	
	@RequestMapping(value="/blockTicket",method = RequestMethod.POST,headers = "content-type=application/json")
	public Map<String, Object> blockTicket(@RequestBody BlockRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			/*if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {*/
			    User userDetails = (User) session.getAttribute("UserDetails");
				returnData=bookingService.blockTicket(request,userDetails);
				
			//}
			
		}catch(Exception e){
			log.error("block ticket  in red bus:::::::::::::"+e);
			}
		return returnData;
			
	}
	
	@RequestMapping(value="/getFareBreakup",method = RequestMethod.POST,headers = "content-type=application/json")
	public Map<String, Object> getFareBreakup(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			/*if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {*/
			    User userDetails = (User) session.getAttribute("UserDetails");
				returnData=bookingService.getFareBreakup(request,userDetails);
				
			//}
			
		}catch(Exception e){
			log.error("block ticket  in red bus:::::::::::::"+e);
			}
		return returnData;
			
	}
	@RequestMapping(value="/confirmBooking",method = RequestMethod.POST,headers = "content-type=application/json")
	public Map<String, Object> confirmBooking(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			/*if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {*/
			    User userDetails = (User) session.getAttribute("UserDetails");
				returnData=bookingService.confirmBooking(request,userDetails);
				
			//}
			
		}catch(Exception e){
			log.error("block ticket  in red bus:::::::::::::"+e);
			}
		return returnData;
			
	}
	
	@RequestMapping(value="/getTicket",method = RequestMethod.POST,headers = "content-type=application/json")
	public Map<String, Object> getTicketDetails(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			/*if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {*/
			    User userDetails = (User) session.getAttribute("UserDetails");
				returnData=bookingService.getTicketDetails(request,userDetails);
				
			//}
			
		}catch(Exception e){
			log.error("get ticket details in red bus:::::::::::::"+e);
			}
		return returnData;
			
	}
	@RequestMapping(value="/getBookedTicket",method = RequestMethod.POST,headers = "content-type=application/json")
	public Map<String, Object> getBookedTicket(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			/*if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {*/
			    User userDetails = (User) session.getAttribute("UserDetails");
				returnData=bookingService.getBookedTicket(request,userDetails);
				
			//}
			
		}catch(Exception e){
			log.error("get booked ticket details in red bus:::::::::::::"+e);
			}
		return returnData;
			
	}
	
	
	@RequestMapping(value="/getBoardingPointDetails",method = RequestMethod.POST,headers = "content-type=application/json")
	public Map<String, Object> getBoardingPointDetails(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			/*if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {*/
			    User userDetails = (User) session.getAttribute("UserDetails");
				returnData=bookingService.getBoardingPointDetails(request,userDetails);
				
			//}
			
		}catch(Exception e){
			log.error("get booked ticket details in red bus:::::::::::::"+e);
			}
		return returnData;
			
	}
	
}
