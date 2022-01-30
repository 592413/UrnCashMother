package com.skyflight.controller;

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
import com.skyflight.businessdao.FlightMarkupDao;
@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class SkyflightMarkupController {
	private static final Logger log = Logger.getLogger(SkyflightMarkupController.class);
	@Autowired HttpSession session;
	@Autowired FlightMarkupDao FlightMarkupDao;
	
	@RequestMapping(value="/saveMarkUpData",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String,Object> saveMarkUpData(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else{
				User user = (User) session.getAttribute("UserDetails");
				returnData =  FlightMarkupDao.saveMarkUpData(request,user);
			}
			
		}catch(Exception e){
			log.error("saveMarkUpData:::::::::::::"+e);
		}
		return returnData;
		
	}
	
	@RequestMapping(value="/showAllDomesticMarkup",method=RequestMethod.POST)
	public Map<String,Object> showAllDomesticMarkup(){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User user = (User) session.getAttribute("UserDetails");
				returnData = FlightMarkupDao.showAllDomesticMarkup(user);
			}
			log.warn("showAllDomesticMarkup:::::::::::::"+returnData);
		}catch (Exception e) {
			log.error("showAllDomesticMarkup:::::::::::::"+e);
		}
		return returnData;
	}

	@RequestMapping(value="/savesingleData",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String,Object> savesingleData(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else{
				User user = (User) session.getAttribute("UserDetails");
				returnData =  FlightMarkupDao.savesingleData(request,user);
			}
			
		}catch(Exception e){
			log.error("savesingleData:::::::::::::"+e);
		}
		return returnData;
		
	}
}
