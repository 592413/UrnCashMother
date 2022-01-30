package com.encore.moneyapi;

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


@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class EncoreMoneyTransferController {
	@Autowired HttpSession session;
	@Autowired EncoreMoneyBusiness encoremoneyservice;
	private static final Logger logger_log = Logger.getLogger(EncoreMoneyTransferController.class);
	
	
	@RequestMapping(value = "/checkuserEncore", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> checkuserEncore(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
			    returnData =encoremoneyservice.checkuserEncore(request);
			    session.setAttribute("checkRemitterMobile",request.get("mobile"));
			}
		} catch (Exception e) {
			logger_log.error("checkuserYesBank:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		//System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/remmiterRegisterEncore", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> remmiterRegisterEncore(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
			    returnData =encoremoneyservice.remmiterRegisterEncore(request);
			    session.setAttribute("checkRemitterMobile",request.get("mobile"));
			}
		} catch (Exception e) {
			logger_log.error("remmiterRegisterEncore:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		//System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/addBeneficiaryEncore", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> addBeneficiaryEncore(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				String mobile=(String)session.getAttribute("checkRemitterMobile");
				request.put("mobile",mobile);
			    returnData =encoremoneyservice.addBeneficiaryEncore(request);
			    
			}
		} catch (Exception e) {
			logger_log.error("remmiterRegisterEncore:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		//System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/ValidateBeneficiaryEncore", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> ValidateBeneficiaryEncore(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User user=(User)session.getAttribute("UserDetails");
				String mobile=(String)session.getAttribute("checkRemitterMobile");
				request.put("checkRemitterMobile",mobile);
			    returnData =encoremoneyservice.ValidateBeneficiaryEncore(request,user);
			    
			}
		} catch (Exception e) {
			logger_log.error("remmiterRegisterEncore:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		//System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/deleteBeneficiaryEncore", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> deleteBeneficiaryEncore(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				String mobile=(String)session.getAttribute("checkRemitterMobile");
				request.put("mobile",mobile);
			    returnData =encoremoneyservice.deleteBeneficiaryEncore(request);
			    returnData.put("mobile",mobile);
			    
			}
		} catch (Exception e) {
			logger_log.error("remmiterRegisterEncore:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		//System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/impsMoneyTransferEncore", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> impsMoneyTransferEncore(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				String mobile=(String)session.getAttribute("checkRemitterMobile");
				request.put("mobile",mobile);
				User user=(User)session.getAttribute("UserDetails");
			    returnData =encoremoneyservice.impsMoneyTransferEncore(request,user);
			    
			}
		} catch (Exception e) {
			logger_log.error("remmiterRegisterEncore:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		//System.out.println(returnData);
		return returnData;
	}
	

}
