package com.recharge.controller;

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

import com.recharge.Imps.ImpsDao;
import com.recharge.Imps.MoneyApiDao;
import com.recharge.model.Imps_BankDetails;
import com.recharge.model.User;
import com.recharge.servicedao.ImpsBankDao;
import com.recharge.utill.GenerateRandomNumber;

@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class ImpsController {
	private static final Logger logger_log = Logger.getLogger(ImpsController.class);
	@Autowired ImpsDao impsDao;
	@Autowired HttpSession session;
	@Autowired ImpsBankDao impsdao;
	
	@RequestMapping("/IMPS")
	public ModelAndView doopmeIMPS(){
		ModelAndView mv = new ModelAndView("IMPS");
		return mv;
		
	}
	

	@RequestMapping(value = "/checkuserDoopme", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> checkuserDoopme(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = impsDao.checkuserDoopme(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("checkuserDoopme :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/doopmeRemitterRegister", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> doopmeRemitterRegister(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = impsDao.doopmeRemitterRegister(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("doopmeRemitterRegister :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/doopmeNewBeneficiary", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> doopmeNewBeneficiary(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = impsDao.doopmeNewBeneficiary(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("doopmeNewBeneficiary :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/fetchbank", method = RequestMethod.POST)
	public Map<String, Object> fetchbank(){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = impsDao.fetchbank();
		
		} catch (Exception e) {
			logger_log.error("fetchbank :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/doopmeVerifyBeneficiary", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> doopmeVerifyBeneficiary(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = impsDao.doopmeVerifyBeneficiary(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("doopmeVerifyBeneficiary :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/viewdoopmebene", method = RequestMethod.POST)
	public Map<String, Object> viewdoopmebene(){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = impsDao.viewdoopmebene(userDetails);
			}
		} catch (Exception e) {
			logger_log.error("viewdoopmebene :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/doopmeMoneytransfer", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> doopmeMoneytransfer(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				System.out.println("Impscontroller::::::::::::::");
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = impsDao.doopmeMoneytransfer(request,userDetails);
				System.out.println("Impscontroller::::::::::::::");
			}
		} catch (Exception e) {
			logger_log.error("doopmeMoneytransfer :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/doopmeValidateBeneficiary", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> doopmeValidateBeneficiary(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = impsDao.doopmeValidateBeneficiary(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("doopmeValidateBeneficiary :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/deletedoopmebene", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> deletedoopmebene(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = impsDao.deletedoopmebene(request,userDetails);
			}
		} catch (Exception e) {
			logger_log.error("deletedoopmebene :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/VerifyDeleteBane", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> VerifyDeleteBane(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = impsDao.VerifyDeleteBane(request,userDetails);
			}
		} catch (Exception e) {
			logger_log.error("VerifyDeleteBane :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}

}
