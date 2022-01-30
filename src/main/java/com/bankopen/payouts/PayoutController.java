package com.bankopen.payouts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.bankopen.model.OpenPayout;
import com.recharge.model.User;

@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class PayoutController {
	private static final Logger logger_log = Logger.getLogger(PayoutController.class);
	
	@Autowired HttpSession session;
	@Autowired PayoutBusinessDao PayoutBusinessDao;
	
	
	@RequestMapping(value = "/initiatePayout", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> initiatePayout(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				request.put("source", "WEB");
				returnData = PayoutBusinessDao.initiatePayout(request, userDetails);
				
			}
		}catch (Exception e) {
			logger_log.error("initiatePayout :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/getPayoutReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getPayoutReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				List<OpenPayout> list = PayoutBusinessDao.getPayoutReport(request, userDetails);		
				if(!list.isEmpty()) {
					returnData.put("report", list);
					returnData.put("status", "1");					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Records Found!");
				}
			}
		} catch (Exception e) {
			logger_log.error("getPayoutReport  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	
	@RequestMapping(value = "/getPayoutStatus", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getPayoutStatus(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = PayoutBusinessDao.getPayoutStatus(request, userDetails);
			}
		}catch (Exception e) {
			logger_log.error("getPayoutStatus :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/payoutOtp", method = RequestMethod.POST)
	public Map<String, Object> payoutOtp(){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = PayoutBusinessDao.payoutOtp( userDetails);
			}
		}catch (Exception e) {
			logger_log.error("payoutOtp :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}

	//DMT
	
	
	/*@RequestMapping(value = "/checkuserOPEN", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> checkuserOPEN(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();		
		try {			
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				request.put("source", "WEB");
				returnData = PayoutBusinessDao.checkuserOPEN(request, userDetails);
				
			}
			
		}catch (Exception e) {
			logger_log.error("checkuserOPEN:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/RemitterRegisterOPEN", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> RemitterRegisterOPEN(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();		
		try {			
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				request.put("source", "WEB");
				returnData = PayoutBusinessDao.RemitterRegisterOPEN(request, userDetails);
				
			}
			
		}catch (Exception e) {
			logger_log.error("RemitterRegisterOPEN:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/otpverifyOPEN", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> otpverifyOPEN(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();		
		try {			
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				request.put("source", "WEB");
				returnData = PayoutBusinessDao.otpverifyOPEN(request, userDetails);
				
			}
			
		}catch (Exception e) {
			logger_log.error("otpverifyOPEN:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/BeneficiaryRegistrationOPEN", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> BeneficiaryRegistrationOPEN(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();		
		try {			
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				request.put("source", "WEB");
				returnData = PayoutBusinessDao.BeneficiaryRegistrationOPEN(request, userDetails);
				
			}
			
		}catch (Exception e) {
			logger_log.error("BeneficiaryRegistrationOPEN:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/validateBeneOPEN", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> validateBeneOPEN(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();		
		try {			
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				request.put("source", "WEB");
				returnData = PayoutBusinessDao.validateBeneOPEN(request, userDetails);
				
			}
			
		}catch (Exception e) {
			logger_log.error("validateBeneOPEN:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/deletebeneOPEN", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> deletebeneOPEN(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();		
		try {			
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				request.put("source", "WEB");
				returnData = PayoutBusinessDao.deletebeneOPEN(request, userDetails);
				
			}
			
		}catch (Exception e) {
			logger_log.error("deletebeneOPEN:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	*/
	@RequestMapping(value = "/MoneytransferOPEN", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> MoneytransferOPEN(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();		
		try {			
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				request.put("source", "WEB");
				returnData = PayoutBusinessDao.MoneytransferOPEN(request, userDetails);
				
			}
			
		}catch (Exception e) {
			logger_log.error("MoneytransferOPEN:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
}
