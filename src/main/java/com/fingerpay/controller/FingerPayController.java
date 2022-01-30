package com.fingerpay.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fingerpay.businessDao.FingerPayService;
import com.recharge.model.User;



@RestController
public class FingerPayController {

	@Autowired FingerPayService fingerpayservice;
	@Autowired HttpSession session;
	
	@RequestMapping(value="/encorepayaeps",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> encorepayaeps(@RequestBody Map<String, String> request) {
		final Logger log = Logger.getLogger(FingerPayController.class);
		log.warn("encorepayaeps:::::::::::::::::::::::::" + request);
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
				if (session.getAttribute("UserDetails") == null) {
					returnData.put("nextPage", "home");
					returnData.put("message", "Session Expire! Please Login Again.");
					returnData.put("status", "0");
				}else {
			User user = (User) session.getAttribute("UserDetails");
			returnData = fingerpayservice.encorepayaeps(request,user);
				}
		} catch (Exception e) {
			log.error("searchAvailability:::::::::::::" + e);
		}

		return returnData;
	}
	
	@RequestMapping(value="/ministatementicici",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> ministatement(@RequestBody Map<String, String> request) {
		final Logger log = Logger.getLogger(FingerPayController.class);
		log.warn("encorepayaeps:::::::::::::::::::::::::" + request);
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
				if (session.getAttribute("UserDetails") == null) {
					returnData.put("nextPage", "home");
					returnData.put("message", "Session Expire! Please Login Again.");
					returnData.put("status", "0");
				}else {
			User user = (User) session.getAttribute("UserDetails");
			returnData = fingerpayservice.ministatement(request,user);
				}
		} catch (Exception e) {
			log.error("searchAvailability:::::::::::::" + e);
		}

		return returnData;
	}
	
	@RequestMapping(value="/encorepayaepsweb",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> encorepayaepsweb(@RequestBody Map<String, String> request) {
		final Logger log = Logger.getLogger(FingerPayController.class);
		log.warn("encorepayaeps:::::::::::::::::::::::::" + request);
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
				if (session.getAttribute("UserDetails") == null) {
					returnData.put("nextPage", "home");
					returnData.put("message", "Session Expire! Please Login Again.");
					returnData.put("status", "0");
				}else {
			User user = (User) session.getAttribute("UserDetails");
			returnData = fingerpayservice.encorepayaepseb(request,user);
				}
		} catch (Exception e) {
			log.error("searchAvailability:::::::::::::" + e);
		}

		return returnData;
	}
	
	@RequestMapping(value="/encorepayaadharweb",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> encorepayaadharweb(@RequestBody Map<String, String> request) {
		final Logger log = Logger.getLogger(FingerPayController.class);
		log.warn("encorepayaeps:::::::::::::::::::::::::" + request);
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
				if (session.getAttribute("UserDetails") == null) {
					returnData.put("nextPage", "home");
					returnData.put("message", "Session Expire! Please Login Again.");
					returnData.put("status", "0");
				}else {
			User user = (User) session.getAttribute("UserDetails");
			returnData = fingerpayservice.encorepayaadharweb(request,user);
				}
		} catch (Exception e) {
			log.error("searchAvailability:::::::::::::" + e);
		}

		return returnData;
	}
	
	@RequestMapping(value = "/getICICIBankAEPSReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getICICIBankAEPSReport(@RequestBody Map<String, String> request){
		final Logger log = Logger.getLogger(FingerPayController.class);
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");		
				returnData = fingerpayservice.getICICIBankAEPSReport(request,userDetails);
			}
		}catch (Exception e) {
			log.error("getICICIBankAEPSReport :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}
		return returnData;
	}

}
