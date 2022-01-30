package com.recharge.icicidmtservicce;

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

import com.bankopen.payouts.PayoutBusinessDao;
import com.recharge.customModel.P2ATranferReport;
import com.recharge.model.User;


@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class ICICIDmtcontroller {
	private static final Logger logger_log = Logger.getLogger(ICICIDmtcontroller.class);
	@Autowired HttpSession session;
	@Autowired ICICIDmtService icicidmtService;
	@Autowired PayoutBusinessDao PayoutBusinessDao;
	
	@RequestMapping(value = "/p2amoneytransfer", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> p2amoneytransfer(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
			//	returnData = icicidmtService.p2amoneytransferNew(request, userDetails);
			//	returnData = icicidmtService.p2amoneytransferPaytm(request, userDetails);
				
				returnData = PayoutBusinessDao.initiatePayout(request, userDetails);
			}
		}catch (Exception e) {
			logger_log.error("updateNews :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/updatep2astatus", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> updatep2astatus(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = icicidmtService.updatep2astatus(request, userDetails);
			}
		}catch (Exception e) {
			logger_log.error("updateNews :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/getP2AViewUser", method = RequestMethod.POST)
	public Map<String, Object> getP2AViewUser(){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
			returnData.put("nextPage", "home");
			returnData.put("message", "Session Expire! Please Login Again.");
			returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");	
			returnData = icicidmtService.getP2AViewUser(userDetails);
			}
		}catch (Exception e) {
			logger_log.error("updateNews :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/getp2aReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getp2aReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				List<P2ATranferReport> list = icicidmtService.getp2aReport(request, userDetails);		
				if(!list.isEmpty()) {
					returnData.put("report", list);
					returnData.put("status", "1");					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Records Found!");
				}
			}
		} catch (Exception e) {
			logger_log.error("getp2aReport:::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	@RequestMapping(value = "/changeP2AStatus", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> changeP2AStatus(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				System.out.println("AdminController changeStatus:::::::::::");
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = icicidmtService.changeP2AStatus(request, userDetails);		
				System.out.println("AdminController changeStatus::::EXIT:::::::");
			}
		}catch (Exception e) {
			logger_log.error("updateNews :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/p2aregistration", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> p2aregistration(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = icicidmtService.p2aregistration(request, userDetails);
			}
		}catch (Exception e) {
			logger_log.error("updateNews :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	//Paytm
	
	
	@RequestMapping(value = "/checkuserOPEN", method = RequestMethod.POST, headers = "content-type=application/json")
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
				returnData = icicidmtService.checkuserOPEN(request, userDetails);
				
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
				returnData = icicidmtService.RemitterRegisterOPEN(request, userDetails);
				
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
				returnData = icicidmtService.otpverifyOPEN(request, userDetails);
				
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
				returnData = icicidmtService.BeneficiaryRegistrationOPEN(request, userDetails);
				
			}
			
		}catch (Exception e) {
			logger_log.error("BeneficiaryRegistrationOPEN:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/BeneficiaryRegistrationRazor", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> BeneficiaryRegistrationRazor(@RequestBody Map<String,String>  request){
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
				returnData = icicidmtService.BeneficiaryRegistrationRazorpay(request, userDetails);
				
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
				returnData = icicidmtService.validateBeneOPEN(request, userDetails);
				
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
				returnData = icicidmtService.deletebeneOPEN(request, userDetails);
				
			}
			
		}catch (Exception e) {
			logger_log.error("deletebeneOPEN:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/MoneytransferPAYTM", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> MoneytransferPAYTM(@RequestBody Map<String,String>  request){
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
				returnData = icicidmtService.MoneytransferOPEN(request, userDetails);
				
			}
			
		}catch (Exception e) {
			logger_log.error("MoneytransferPAYTM:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/MoneytransferRazorPayNew", method = RequestMethod.POST, headers = "content-type=application/json")
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
				returnData = icicidmtService.MoneytransferRazorPay(request, userDetails);
				
			}
			
		}catch (Exception e) {
			logger_log.error("MoneytransferPAYTM:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}

	@RequestMapping(value = "/getPayoutStatuscheck", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getPayoutStatuscheck(@RequestBody Map<String,String>  request){
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
				returnData = icicidmtService.getPayoutStatuscheck(request, userDetails);
				
			}
			
		}catch (Exception e) {
			logger_log.error("getPayoutStatuscheck:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/getPayoutStatuscheckdmt", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getPayoutStatuscheckdmt(@RequestBody Map<String,String>  request){
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
				returnData = icicidmtService.getPayoutStatuscheckdmt(request, userDetails);
				
			}
			
		}catch (Exception e) {
			logger_log.error("getPayoutStatuscheckdmt:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/razorPayStatusWebhook", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> razorPayStatusWebhook(@RequestBody String  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();		
		
		logger_log.warn("razorPayStatusWebhook:::::" + request);
		try {
			returnData = icicidmtService.razorPayStatusWebhook(request);

		} catch (Exception e) {
			logger_log.error("paytmfinalDMTres::::::::::::::::::::::::::" + e);
		}
		return returnData;
		
	}

}
