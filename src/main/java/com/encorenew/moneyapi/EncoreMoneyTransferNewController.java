package com.encorenew.moneyapi;

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

import com.encore.moneyapi.EncoreMoneyBusiness;
import com.encore.moneyapi.EncoreMoneyTransferController;
import com.recharge.model.User;

@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class EncoreMoneyTransferNewController {
	@Autowired HttpSession session;
	@Autowired EncoreMoneyBusinessNew encoremoneyservicenew;
	private static final Logger logger_log = Logger.getLogger(EncoreMoneyTransferNewController.class);
	
	
	@RequestMapping(value = "/checkuserEncoreNew", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> checkuserEncore(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
			    returnData =encoremoneyservicenew.checkuserEncoreNew(request);
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
	
	@RequestMapping(value = "/remmiterRegisterEncoreNew", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> remmiterRegisterEncore(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
			    returnData =encoremoneyservicenew.remmiterRegisterEncoreNew(request);
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
	
	
	@RequestMapping(value = "/addBeneficiaryEncoreNew", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> addBeneficiaryEncore(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				String mobile=(String)session.getAttribute("checkRemitterMobile");
				request.put("mobile",mobile);
			    returnData =encoremoneyservicenew.addBeneficiaryEncoreNew(request);
			    
			    
			}
		} catch (Exception e) {
			logger_log.error("remmiterRegisterEncore:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		//System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/ValidateBeneficiaryEncoreNew", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> ValidateBeneficiaryEncore(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User user=(User)session.getAttribute("UserDetails");
				String mobile=(String)session.getAttribute("checkRemitterMobile");
				request.put("mobile",mobile);
			    returnData =encoremoneyservicenew.ValidateBeneficiaryEncoreNew(request,user);
			    
			}
		} catch (Exception e) {
			logger_log.error("remmiterRegisterEncore:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		//System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/resendOtp", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> resendOtp(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				
				String mobile=(String)session.getAttribute("checkRemitterMobile");
				request.put("mobile",mobile);
			    returnData =encoremoneyservicenew.resendOtp(request);
			    
			}
		} catch (Exception e) {
			logger_log.error("remmiterRegisterEncore:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		//System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/deleteBeneficiaryEncoreNew", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> deleteBeneficiaryEncore(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				String mobile=(String)session.getAttribute("checkRemitterMobile");
				request.put("mobile",mobile);
			    returnData =encoremoneyservicenew.deleteBeneficiaryEncoreNew(request);
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
	
	
	@RequestMapping(value = "/OTPVERIFICATION", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> OTPVERIFICATION(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				String mobile=(String)session.getAttribute("checkRemitterMobile");
				request.put("mobile",mobile);
			    returnData =encoremoneyservicenew.OTPVERIFICATION(request);
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
	
	@RequestMapping(value = "/impsMoneyTransferEncoreNew", method = RequestMethod.POST, headers = "content-type=application/json")
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
			    returnData =encoremoneyservicenew.impsMoneyTransferEncoreNew(request,user);
			    
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
