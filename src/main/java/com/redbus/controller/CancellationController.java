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
import com.redbus.model.CancellationRequest;
import com.redbus.service.CancellationService;

@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class CancellationController {
	private static final Logger log = Logger.getLogger(BookingController.class);
	@Autowired HttpSession session;
	@Autowired CancellationService cancellationService;
	
	@RequestMapping(value="/getCancellationData",method = RequestMethod.POST,headers = "content-type=application/json")
	public Map<String, Object> getCancellationData(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			/*if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {*/
			    User userDetails = (User) session.getAttribute("UserDetails");
				returnData=cancellationService.getCancellationData(request,userDetails);
				
			//}
			
		}catch(Exception e){
			log.error("get cancellation data in red bus:::::::::::::"+e);
			}
		return returnData;
			
	}
	
	@RequestMapping(value="/cancelBusTicket",method = RequestMethod.POST,headers = "content-type=application/json")
	public Map<String, Object> doCancelTicket(@RequestBody CancellationRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			/*if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {*/
			    User userDetails = (User) session.getAttribute("UserDetails");
				returnData=cancellationService.doCancelTicket(request,userDetails);
				
			//}
			
		}catch(Exception e){
			log.error("do cancel ticket in red bus:::::::::::::"+e);
			}
		return returnData;
			
	}
	
	@RequestMapping(value="/cancellationInfo",method = RequestMethod.POST,headers = "content-type=application/json")
	public Map<String, Object> getCancellationInfo(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			/*if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {*/
			    User userDetails = (User) session.getAttribute("UserDetails");
				returnData=cancellationService.getCancellationInfo(request,userDetails);
				
			//}
			
		}catch(Exception e){
			log.error("get Cancellation Info in red bus:::::::::::::"+e);
			}
		return returnData;
			
	}
}
