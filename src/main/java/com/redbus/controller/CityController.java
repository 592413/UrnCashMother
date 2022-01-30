package com.redbus.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.recharge.model.User;
import com.redbus.service.CityService;

@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class CityController {

	private static final Logger log = Logger.getLogger(CityController.class);
	@Autowired HttpSession session;
	@Autowired CityService cityService;
	
	@RequestMapping(value="/getCities",method = RequestMethod.POST)
	public Map<String,Object> getCities(){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			/*if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {*/
				returnData=cityService.getCities();
				
			//}
			
		}catch(Exception e){
			log.error("get cities in red bus:::::::::::::"+e);
			}
		return returnData;
		
	}
	
	@RequestMapping(value="/getAliases",method = RequestMethod.POST)
	public Map<String,Object> getAliases(){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			/*if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {*/
				returnData=cityService.getAliases();
				
			//}
			
		}catch(Exception e){
			log.error("get cities in red bus:::::::::::::"+e);
			}
		return returnData;
		
	}
	
	
	
	
}
