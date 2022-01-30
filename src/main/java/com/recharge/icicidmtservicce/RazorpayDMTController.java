package com.recharge.icicidmtservicce;

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
public class RazorpayDMTController {
	private static final Logger logger_log = Logger.getLogger(RazorpayDMTController.class);
	@Autowired HttpSession session;
	@Autowired RazorpayDMTService razorpayDmtService;
	
	
	@RequestMapping(value = "/checkUserRazorPay", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> checkuserRazorPay(@RequestBody Map<String,String>  request){
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
				returnData = razorpayDmtService.checkuserRazorpayNew(request);
				
			}
			
		}catch (Exception e) {
			logger_log.error("checkuserRazorPay:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/RemitterRegisterRazorPay", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> RemitterRegisterRazorPay(@RequestBody Map<String,String>  request){
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
				returnData = razorpayDmtService.remmiterRegisterRazorpayNew(request) ;
				
			}
			
		}catch (Exception e) {
			logger_log.error("RemitterRegisterRazorPay:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	
		return returnData;
	}
	
	@RequestMapping(value = "/otpverifyRazorpay", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> otpverifyRazorpay(@RequestBody Map<String,String>  request){
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
				returnData = razorpayDmtService.otpVerifyRazorpay(request);
				
			}
			
		}catch (Exception e) {
			logger_log.error("otpverifyRazorpay:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	
	@RequestMapping(value = "/BeneficiaryRegistrationRazorPay", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> BeneficiaryRegistrationRazorPay(@RequestBody Map<String,String>  request){
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
				returnData = razorpayDmtService.addBeneficiaryRazorPay(request);
				
			}
			
		}catch (Exception e) {
			logger_log.error("BeneficiaryRegistrationRazorPay:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/MoneytransferRazorPay", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> MoneytransferRazorPay(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();		
		try {			
	//		{id=22867, name=Bappaditya Dutta, mobile=9903420886, account=50100004875642, ifsc=HDFC0000545, remmobile=9903420886, isVerified=false, limit=200000, custtype=NON-KYC, accountType=Savings, amount=10, sendType=2}
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				request.put("source", "WEB");
				returnData = razorpayDmtService.moneyTransferRazorpay(request, userDetails);
				
			}
			
		}catch (Exception e) {
			logger_log.error("MoneytransferPAYTM:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/checkBeneficiaryEncore", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> checkBeneficiaryEncore(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();		
		try {			
	//		{id=22867, name=Bappaditya Dutta, mobile=9903420886, account=50100004875642, ifsc=HDFC0000545, remmobile=9903420886, isVerified=false, limit=200000, custtype=NON-KYC, accountType=Savings, amount=10, sendType=2}
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				request.put("source", "WEB");
				returnData = razorpayDmtService.validatemoneyTransferRazorpay(request, userDetails);
				
			}
			
		}catch (Exception e) {
			logger_log.error("checkBeneficiaryEncore:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/deleteBeneficiaryRazorpay", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> deleteBeneficiaryRazorpay(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				String mobile=(String)session.getAttribute("checkRemitterMobile");
				//request.put("mobile",mobile);
			    returnData =razorpayDmtService.deleteBeneficiaryrazorpay(request);
			    
			    
			}
		} catch (Exception e) {
			logger_log.error("deleteBeneficiaryRazorpay:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		//System.out.println(returnData);
		return returnData;
	}

}
