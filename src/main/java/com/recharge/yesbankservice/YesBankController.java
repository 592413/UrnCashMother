package com.recharge.yesbankservice;

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
import org.springframework.web.servlet.ModelAndView;

import com.recharge.businessdao.GlobalCommandCenter;
import com.recharge.controller.RestAuthenticationConroller;
import com.recharge.model.AEPSUserMap;
import com.recharge.model.User;
import com.recharge.servicedao.AEPSUserMapDao;
import com.recharge.yesbankservicedao.YesBankApiTokenDao;

@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class YesBankController {
	private static final Logger logger_log = Logger.getLogger(YesBankController.class);
	@Autowired YesBankApiTokenDao yesbanktokendao;
	@Autowired YesBankBusiness yesbankservice;
	@Autowired HttpSession session;
	@Autowired AEPSUserMapDao aepsuserdao;
	
	@RequestMapping(value = "/getyesbankdetails", method = RequestMethod.POST)
	public Map<String, Object> getyesbankdetails(){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				returnData = yesbankservice.getyesbankdetails();
			}
		}catch (Exception e) {
			logger_log.error("updateNews :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/getDeviceByDeviceType", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getDeviceByDeviceType(@RequestBody Map<String,Object> request){
		Map<String, Object> returnData = new HashMap<String,Object>();
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = yesbankservice.getDeviceByDeviceType(request,userDetails);				
			}
		}catch (Exception e) {
			logger_log.error("getUserByUserType :::::::::::: "+e);	
			String msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
					+ "<label for='email_address_2'>Select User </label>"
				+ "</div>"
				+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
				+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
				+ "<label for='email_address_2' class='danger'>Technical Error! Try After Sometime. </label>"
				+ "</div>"
				+ "</div>";
			returnData.put("message", msg);		
			returnData.put("status", "0");
		}
		return returnData;
	}
	@RequestMapping(value = "/Customerregister", method = RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> Customerregister(@RequestBody Map<String, Object> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User user = (User)session.getAttribute("UserDetails");
				returnData = yesbankservice.Customerregister(request,user);
			}
		}catch (Exception e) {
			logger_log.error("searchCustomer::::::::::"+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/getYesBankAEPSReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getYesBankAEPSReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");		
				returnData = yesbankservice.getYesBankAEPSReport(request,userDetails);
			}
		}catch (Exception e) {
			logger_log.error("editProfile :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}

	
	@RequestMapping(value = "/searchCustomer", method = RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> searchCustomer(@RequestBody Map<String, Object> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User user = (User)session.getAttribute("UserDetails");
				returnData = yesbankservice.searchCustomer(request,user);
				System.out.println("searchCustomerreturn:::::::::::::::::"+returnData);
			}
		}catch (Exception e) {
			logger_log.error("searchCustomer::::::::::"+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	
	@RequestMapping(value = "/checkuserYesBank", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> checkuserYesBank(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
			returnData = yesbankservice.checkuserYesBank(request,userDetails);
			}
		} catch (Exception e) {
			logger_log.error("checkuserYesBank:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		//System.out.println(returnData);
		return returnData;
	}
	
	/*@RequestMapping(value = "/checkuserYesBank", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> checkuserYesBank(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
			returnData = yesbankservice.checkuserYesBank(request,userDetails);
			}
		} catch (Exception e) {
			logger_log.error("checkuserDoopme :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		//System.out.println(returnData);
		return returnData;
	}*/
	
	@RequestMapping(value = "/yesBankRemitterRegister", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> yesBankRemitterRegister(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
			returnData = yesbankservice.yesBankRemitterRegister(request,userDetails);
			}
		} catch (Exception e) {
			logger_log.error("checkuserDoopme :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		//System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/yesBankNewBeneficiary", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> yesBankNewBeneficiary(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
			returnData = yesbankservice.yesBankNewBeneficiary(request,userDetails);
			}
		} catch (Exception e) {
			logger_log.error("yesBankNewBeneficiary :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/deleteyesBankbene", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> deleteyesBankbene(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
			returnData = yesbankservice.deleteyesBankbene(request,userDetails);
			}
		} catch (Exception e) {
			logger_log.error("deleteyesBankbene :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/VerifyDeleteYesBane", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> VerifyDeleteBane(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
			returnData = yesbankservice.VerifyDeleteYesBane(request,userDetails);
			}
		} catch (Exception e) {
			logger_log.error("deleteyesBankbene :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/yesBankMoneytransfer", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> yesBankMoneytransfer(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				System.out.println("Impscontroller::::::::::::::");
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = yesbankservice.yesBankMoneytransfer(request,userDetails);
				
			}
		} catch (Exception e) {
			logger_log.error("yesBankMoneytransfer :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/checkImpsStatus", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> checkImpsStatus(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User)session.getAttribute("UserDetails");
				returnData = yesbankservice.checkImpsStatus(request,userDetails);
				
			}
		} catch (Exception e) {
			logger_log.error("yesBankMoneytransfer :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/REFUNDImpsStatus", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> REFUNDImpsStatus(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User)session.getAttribute("UserDetails");
				returnData = yesbankservice.REFUNDImpsStatus(request,userDetails);
				
			}
		} catch (Exception e) {
			logger_log.error("REFUNDImpsStatus :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/refundOTP", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> refundOTP(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User)session.getAttribute("UserDetails");
				returnData = yesbankservice.refundOTP(request,userDetails);
				
			}
		} catch (Exception e) {
			logger_log.error("yesBankMoneytransfer :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	
	
	@RequestMapping(value = "/yesBankValidateBeneficiary", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> yesBankValidateBeneficiary(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				System.out.println("Impscontroller::::::::::::::");
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = yesbankservice.yesBankValidateBeneficiary(request,userDetails);
				
			}
		} catch (Exception e) {
			logger_log.error("instantPayMoneytransfer :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/yesBankVerifyBeneficiary", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> yesBankVerifyBeneficiary(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				System.out.println("Impscontroller::::::::::::::");
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = yesbankservice.yesBankVerifyBeneficiary(request,userDetails);
				
			}
		} catch (Exception e) {
			logger_log.error("instantPayMoneytransfer :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}

	
	@RequestMapping(value = "/remitterverify", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> remitterverify(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				System.out.println("Impscontroller::::::::::::::");
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = yesbankservice.remitterverify(request,userDetails);
				
			}
		} catch (Exception e) {
			logger_log.error("instantPayMoneytransfer :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/verifyremitter", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> verifyremitter(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				System.out.println("Impscontroller::::::::::::::");
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = yesbankservice.verifyremitter(request,userDetails);
				
			}
		} catch (Exception e) {
			logger_log.error("instantPayMoneytransfer :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}




	
	@RequestMapping(value = "/viewYesBankbene", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> viewYesBankbene(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				
			returnData = yesbankservice.viewYesBankbene();
			}
		} catch (Exception e) {
			logger_log.error("checkuserDoopme :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/yesBankTransaction", method = RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> yesBankTransaction(@RequestBody Map<String, Object> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User user = (User)session.getAttribute("UserDetails");
				returnData = yesbankservice.yesBankTransaction(request,user);
				System.out.println("yesBankTransaction:::::::::::::::::"+returnData);
			}
		}catch (Exception e) {
			logger_log.error("searchCustomer::::::::::"+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/aepstransaction", method = RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> aepstransaction(@RequestBody Map<String, Object> request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();	
		//ModelAndView mv=new ModelAndView("AepsForm");
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User user = (User)session.getAttribute("UserDetails");
				param.put("username",user.getUserName());
				param.put("api","YesBank");
				List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
				if(!list2.isEmpty()){
				String url = yesbankservice.aepstransaction(request,user);
				// mv.addObject("url",url);
				/*page="AepsForm";
				User user = (User)session.getAttribute("UserDetails");*/
				if(!url.equals("0")){
					//session.setAttribute("url", url);
					returnData.put("nextPage", "AepsForm");
					returnData.put("status", "1");
					System.out.println("searchCustomerreturn:::::::::::::::::"+url);
				}else{
					returnData.put("message", "Please Try Again.");		
					returnData.put("status", "0");
				}
				
			}else{
				returnData.put("message", "You donot have agentId.");		
				returnData.put("status", "0");
			}
			}
		}catch (Exception e) {
			logger_log.error("aepstransaction::::::::::"+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		
		return returnData;
	}
	
	@RequestMapping(value = "/aepsstatuscheck",method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> aepsstatuscheck(@RequestBody Map<String, Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			 returnData = yesbankservice.aepsstatuscheck(request);
		} catch (Exception e) {
			logger_log.error("aepsstatuscheck:::::::::: " + e);
		}

		return returnData;
	}

}
