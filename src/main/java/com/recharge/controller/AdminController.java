package com.recharge.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.recharge.businessdao.AdminService;
import com.recharge.businessdao.AuthenticationCommandCenter;
import com.recharge.businessdao.GlobalCommandCenter;

import com.recharge.customModel.RechargeReport;
import com.recharge.model.NSDLPanAttachment;
import com.recharge.model.Operator;
import com.recharge.model.User;
import com.recharge.model.webenquery;
import com.recharge.servicedao.NSDLPanAttachmentDao;
import com.recharge.utill.UtilityClass;

@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class AdminController {
	private static final Logger logger_log = Logger.getLogger(RestAuthenticationConroller.class);
	@Autowired AuthenticationCommandCenter authenticationCommandCenter;
	@Autowired GlobalCommandCenter globalCommandCenter;
	@Autowired AdminService adminService;	
	@Autowired HttpSession session;
	@Autowired NSDLPanAttachmentDao NSDLPanAttachmentDao;
	
	@RequestMapping(value = "/updateNews", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> updateNews(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.updateNews(request, userDetails);
			}
		}catch (Exception e) {
			logger_log.error("updateNews :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/updateResellerdetails", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> updateResellerdetails(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.updateResellerdetails(request, userDetails);
			
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("updateResellerdetails :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");	
			returnData.put("status", "0");
		}
		return returnData;
	}
	@RequestMapping(value ="/aepsSettleToBank",method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> aepsSettleToBank(@RequestBody Map<String,String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try{
		returnData =adminService.aepsSettleToBank(request);
		}catch (Exception e) {
			logger_log.error("aepsSettleToBank:::::::::: "+e);
		}
		
		return returnData;
	}
	
	@RequestMapping(value ="/aepsUseradd",method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> aepsUseradd(@RequestBody Map<String,String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try{
		returnData =adminService.aepsUseradd(request);
		}catch (Exception e) {
			logger_log.error("aepsUseradd:::::::::: "+e);
		}
		
		return returnData;
	}
	
	@RequestMapping(value ="/addRailUser",method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> addRailUser(@RequestBody Map<String,String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try{
		returnData =adminService.addRailUser(request);
		}catch (Exception e) {
			logger_log.error("aepsUseradd:::::::::: "+e);
		}
		
		return returnData;
	}
	
	@RequestMapping(value ="/addECommerceUser",method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> addECommerceUser(@RequestBody Map<String,String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try{
		returnData =adminService.addECommerceUser(request);
		}catch (Exception e) {
			logger_log.error("aepsUseradd:::::::::: "+e);
		}
		
		return returnData;
	}
	
	@RequestMapping(value = "/getSettlementBalance", method = RequestMethod.POST)
	public Map<String, Object> getSettlementBalance(){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.getSettlementBalance(userDetails);
			}
		}catch (Exception e) {
			logger_log.error("updateNews :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/addApiParameters", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> addApiParameters(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.addApiParameters(request);
			}
		}catch (Exception e) {
			logger_log.error("updateNews :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/getAEPSViewUser", method = RequestMethod.POST)
	public Map<String, Object> getAEPSViewUser(){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.getAEPSViewUser(userDetails);
			}
		}catch (Exception e) {
			logger_log.error("updateNews :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
/*	@RequestMapping(value = "/getP2AViewUser", method = RequestMethod.POST)
	public Map<String, Object> getP2AViewUser(){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				
				returnData = adminService.getP2AViewUser();
			}
		}catch (Exception e) {
			logger_log.error("updateNews :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}*/
	
	
	
	@RequestMapping(value = "/addApi", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> addApi(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.addApi(request);
			}
		}catch (Exception e) {
			logger_log.error("addApi:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/addsmsApi", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> addsmsApi(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.addsmsApi(request,userDetails);
			}
		}catch (Exception e) {
			logger_log.error("addApi:::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	@RequestMapping(value = "/addOperator", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String,Object>  addOperator(@RequestBody Map<String,String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				returnData = adminService.addOperator(request);
			}
		}catch (Exception e) {
			logger_log.error("addOperator :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/insertBankDetails", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> insertBankDetails(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.insertBankDetails(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("updateNews :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}	
	@RequestMapping(value = "/signInByAdmin", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> signInByAdmin(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.signInByAdmin(request, userDetails);
				if(returnData.get("status").equals("1")){
					User user = globalCommandCenter.getUserByUserId(request.get("userId"));
					if(user != null){
						user.setPassword(null);
						user.setTranPin(null);
						session.setAttribute("UserDetails", user);
					} else {
						session.setAttribute("UserDetails", userDetails);
						returnData.put("message", "Failed to Sign In.");		
						returnData.put("status", "0");
					}
				} else {
					session.setAttribute("UserDetails", userDetails);
					returnData.put("message", "Failed to Sign In.");		
					returnData.put("status", "0");
				}
			}
		}catch (Exception e) {
			logger_log.error("signInByAdmin :::::::::: "+e);			
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/sendPassword", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> sendPassword(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.sendPassword(request, userDetails);
			}
		}catch (Exception e) {
			logger_log.error("sendPassword :::::::::: "+e);			
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}		
		return returnData;
	}
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> deleteUser(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.deleteUser(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("deleteUser :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
	}
		
	@RequestMapping(value = "/updateBankDetails", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> updateBankDetails(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.insertBankDetails(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("updateBankDetails :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/deleteBankDetails", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> deleteBankDetails(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.deleteBankDetails(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("deleteBankDetails :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
	}
	@RequestMapping(value = "/revertUserBalance", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> revertUserBalance(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.revertUserBalance(request, userDetails);				
			}
		} catch (Exception e) {
			logger_log.error("revertUserBalance :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/apiSwitching", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> apiSwitching(@RequestBody Operator[]  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");			
				returnData = adminService.apiSwitching(request, userDetails);				
			}
		} catch (Exception e) {
			logger_log.error("apiSwitching ::::::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/viewComplain", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> viewComplain(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.viewComplain(request, userDetails,"ALL");
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("viewComplain :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	
	@RequestMapping(value = "/viewLatesComplain", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> viewLatesComplain(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.viewComplain(request, userDetails,"LATEST");
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("viewLatesComplain :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	
	@RequestMapping(value = "/viewPendingComplain", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> viewPendingComplain(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.viewComplain(request, userDetails,"PENDING");
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("viewPendingComplain :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/updateComplainDetails", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> updateComplainDetails(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.updateComplainDetails(request, userDetails);
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("updateComplainDetails :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/userMapping", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> userMapping(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.userMapping(request, userDetails);
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("userMapping :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/setUserLockedAmount", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> setUserLockedAmount(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.setUserLockedAmount(request, userDetails);
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("setUserLockedAmount :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	@RequestMapping(value = "/showpop", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> showpop(@RequestBody Map<String,String>  request){
		System.out.println("Admin Controller:::::::::::::::::::::::::");
		Map<String, Object> returnData = new HashMap<String, Object>();
	//	User userDetails = (User) session.getAttribute("UserDetails");
		System.out.println( "oid::::::::::::"+request.get("oid"));
		returnData.put("oid", request.get("oid"));
		
		return returnData;	
	}


	
	@RequestMapping(value = "/getUserDetailsForEdit", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getUserDetailsForEdit(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.getUserDetailsForEdit(request, userDetails);
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("getUserDetailsForEdit :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/editProfileForUser", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> editProfileForUser(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");		
				returnData = globalCommandCenter.editProfileForUser(request,userDetails);
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
	
	@RequestMapping(value = "/viewPendingBalanceRequest", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> viewPendingBalanceRequest(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.viewBalanceRequest(request, userDetails,"PENDING");
				//System.out.println(returnData);
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("viewPendingBalanceRequest :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/viewBalanceRequest", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> viewBalanceRequest(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.viewBalanceRequest(request, userDetails,"ALL");
				//System.out.println(returnData);
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("viewBalanceRequest :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/updateBalanceRequest", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> updateBalanceRequest(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.updateBalanceRequest(request, userDetails);
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("updateBalanceRequest :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/updateUtilityReport", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> updateUtilityReport(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.updateUtilityReport(request, userDetails);
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("updateUtilityReport :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	
	@RequestMapping(value = "/updateInsuranceReport", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> updateInsuranceReport(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.updateInsuranceReport(request, userDetails);
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("updateInsuranceReport :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/updateRechargeReport", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> updateRechargeReport(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.updateRechargeReport(request, userDetails);
			
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("updateRechargeReport :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");	
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/updateRemark", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> updateRemark(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				//System.out.println("Admin controllre::::::::::::::::::::::::::::");
				returnData = globalCommandCenter.updateRemark(request, userDetails);
				//System.out.println("Admin controller return::::::::::::::::::::::::::::");
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("updateRemark :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	
	@RequestMapping(value = "/addReseller", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addReseller(@RequestParam(value = "addReseller") String userInfo,
			@RequestParam(value = "file") MultipartFile file) {
		Map<String, Object> returnData = new HashMap<String, Object>();						
			
			try {
				if (session.getAttribute("UserDetails") == null) {
					returnData.put("nextPage", "home");
					returnData.put("message", "Session Expire! Please Login Again.");
					returnData.put("status", "0");
				} else {
					if(file != null){
					User userDetails = (User) session.getAttribute("UserDetails");
					JSONObject resellerDetails = new JSONObject(userInfo);
					Map<String, String> inputData = new HashMap<String, String>();
					inputData = UtilityClass.toMap(resellerDetails);	
					inputData.put("uplineId", userDetails.getUserId());
					inputData.put("userType", "2");
					byte[] bytes = file.getBytes();
					returnData = adminService.addReseller(inputData, userDetails,bytes);	
					} else {						
						returnData.put("message", "Insert Logo.");
						returnData.put("status", "0");
					}
				}
			}
		 catch (Exception e) {
				session.invalidate();
				logger_log.error("addReseller :::::::::: "+e);
				returnData.put("nextPage", "home");
				returnData.put("message", "Exception! Please try fresh login.");			
				returnData.put("status", "0");
			}
		returnData.put("nextPage", "home");
		return returnData;

	}
	
	@RequestMapping(value = "/addIndex", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addIndex(@RequestParam(value = "addIndex") String userInfo,
			@RequestParam(value = "blogimage") MultipartFile file) {
		Map<String, Object> returnData = new HashMap<String, Object>();						
			
			try {
				if (session.getAttribute("UserDetails") == null) {
					returnData.put("nextPage", "home");
					returnData.put("message", "Session Expire! Please Login Again.");
					returnData.put("status", "0");
				} else {
					if(file != null){
					User userDetails = (User) session.getAttribute("UserDetails");
					JSONObject resellerDetails = new JSONObject(userInfo);
					Map<String, String> inputData = new HashMap<String, String>();
					inputData = UtilityClass.toMap(resellerDetails);	
					byte[] bytes = file.getBytes();
					returnData = adminService.addIndex(inputData,bytes);	
					} else {						
						returnData.put("message", "Insert Logo.");
						returnData.put("status", "0");
					}
				}
			}
		 catch (Exception e) {
				session.invalidate();
				logger_log.error("addIndex :::::::::: "+e);
				returnData.put("nextPage", "home");
				returnData.put("message", "Exception! Please try fresh login.");			
				returnData.put("status", "0");
			}
		returnData.put("nextPage", "home");
		return returnData;

	}
	
	@RequestMapping(value = "/fetchDomain", method = RequestMethod.POST)
	public Map<String, Object> fetchDomain(){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				returnData = adminService.fetchDomain();
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("fetchDomain :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;	
	}

	@RequestMapping(value = "/setResellerTheme", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> setResellerTheme(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.setResellerTheme(request, userDetails);
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("setResellerTheme :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/getResellerDetails", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getResellerDetails(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.getResellerDetails(request, userDetails);
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("getResellerDetails :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "/setResellerLogo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setResellerLogo(@RequestParam(value = "resellerLogo") String userInfo,
			@RequestParam(value = "file") MultipartFile file) {
		Map<String, Object> returnData = new HashMap<String, Object>();						
			
			try {
				if (session.getAttribute("UserDetails") == null) {
					returnData.put("nextPage", "home");
					returnData.put("message", "Session Expire! Please Login Again.");
					returnData.put("status", "0");
				} else {
					if(file != null){
					User userDetails = (User) session.getAttribute("UserDetails");
					JSONObject resellerDetails = new JSONObject(userInfo);
					Map<String, String> inputData = new HashMap<String, String>();
					inputData = UtilityClass.toMap(resellerDetails);					
					byte[] bytes = file.getBytes();
					returnData = adminService.setResellerLogo(inputData, userDetails,bytes);	
					} else {						
						returnData.put("message", "Insert Logo.");
						returnData.put("status", "0");
					}
				}
			}
		 catch (Exception e) {
				session.invalidate();
				logger_log.error("addReseller :::::::::: "+e);
				returnData.put("nextPage", "home");
				returnData.put("message", "Exception! Please try fresh login.");			
				returnData.put("status", "0");
			}
		returnData.put("nextPage", "home");
		return returnData;

	}
	
	@RequestMapping(value = "/viewWebEnquery", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> viewWebEnquery(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {		
				User userDetails = (User) session.getAttribute("UserDetails");
				List<webenquery> list  = adminService.viewWebEnquery(request,userDetails);
				if(!list.isEmpty()) {
					returnData.put("report", list);
					returnData.put("status", "1");					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Records Found!");
				}
			}
		} catch (Exception e) {
			logger_log.error("viewWebEnquery  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;
	
	}
	
	@RequestMapping(value = "/updateImpsReport", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> updateImpsReport(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.updateImpsReport(request, userDetails);
			
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("updateImpsReport :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");	
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/agentstatusupdate", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> agentstatusupdate(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.agentstatusupdate(request, userDetails);
			
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("agentstatusupdate :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");	
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/couponstatusupdate", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> couponstatusupdate(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.couponstatusupdate(request, userDetails);
			
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("couponstatusupdate :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");	
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/getNSDLAttachmentDetails", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getNSDLAttachmentDetails(@RequestBody Map<String,String>  requests){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				
				//String filepath="C:/Users/Prateeti/git/safegoal/webapp/assets/doc/";
				String filepath="http://doc.safegoal.co.in/";
                String filename=requests.get("invoiceno")+"idproof.pdf";
                String fileurl=filepath+filename;
                returnData.put("status","1");
                returnData.put("filepath",filepath);
                returnData.put("filename",filename);
                returnData.put("fileurl",fileurl);
               /* Path file = Paths.get(filepath, filename);
                if (Files.exists(file))
                {
                	response.setContentType("application/pdf");
                    response.addHeader("Content-Disposition", "attachment; filename="+filename);
                    try
                    {
                        Files.copy(file, response.getOutputStream());
                        response.getOutputStream().flush();
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }*/
				//returnData = adminService.getNSDLAttachmentDetails(requests);
			}
		}catch (Exception e) {
			logger_log.error("getNSDLAttachmentDetails :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/changeStatus", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> changeStatus(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				System.out.println("AdminController changeStatus:::::::::::");
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.changeStatus(request, userDetails);
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
	
	@RequestMapping(value = "/searchackno", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> searchackno(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.searchackno(request, userDetails);
			}
		}catch (Exception e) {
			logger_log.error("searchackno :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/fetchnonpkguser", method = RequestMethod.POST)
	public Map<String, Object> fetchnonpkguser(){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.fetchnonpkguser( userDetails);
			}
		}catch (Exception e) {
			logger_log.error("fetchnonpkguser :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/fetchoutletid", method = RequestMethod.POST)
	public Map<String, Object> fetchoutletid(){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				Client client = null;
				String rechargerl ="https://www.instantpay.in/ws/outlet/registrationOTP";
				 MultivaluedMap<String, Object> headers= new MultivaluedHashMap<>();
					headers.add("Content-Type", "application/json");
					
					 String bodyjson="{\"token\": \"efe56f6c213c70557249ef89105c880e\",\"request\": {\"mobile\": \"9903712171\"}}";
					 
					 client=ClientBuilder.newClient();
						//String i = new Gson().toJson(bodyjson);
						Response response1=client.target(rechargerl).request(MediaType.APPLICATION_JSON).headers(headers).post(Entity.entity(bodyjson, MediaType.APPLICATION_JSON));
						logger_log.warn("instantpay OTP :: "+response1);	
						   String output =response1.readEntity(String.class);
						   logger_log.warn("instantpay OTP :: "+output);	
			}
		}catch (Exception e) {
			logger_log.error("fetchoutletid :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/fetchoutletidotp", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> fetchoutletidotp(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				Client client = null;
				String rechargerl ="https://www.instantpay.in/ws/outlet/registration";
				 MultivaluedMap<String, Object> headers= new MultivaluedHashMap<>();
					headers.add("Content-Type", "application/json");
					
					 String bodyjson="{\"token\": \"efe56f6c213c70557249ef89105c880e\",\"request\": {\"mobile\": \"9903712171\",\"otp\": \""+request.get("otp")+"\",\"email\": \"sa.b@instantpay.in\",\"company\": \"instantpay\",\"name\": \"shweta\",\"pan\": \"AAQFB1771R\",\"address\": \"mohan cooperative\",\"pincode\": \"201301\"}}";
					 
					 client=ClientBuilder.newClient();
						//String i = new Gson().toJson(bodyjson);
						Response response1=client.target(rechargerl).request(MediaType.APPLICATION_JSON).headers(headers).post(Entity.entity(bodyjson, MediaType.APPLICATION_JSON));
						logger_log.warn("instantpay OTP final:: "+response1);	
						   String output =response1.readEntity(String.class);
						   logger_log.warn("instantpay OTP final:: "+output);	
			}
		}catch (Exception e) {
			logger_log.error("fetchoutletidotp :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value ="/getaepslogreport",method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getaepslogreport(@RequestBody Map<String,String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
		returnData =adminService.getaepslogreport(request,userDetails);
			}
		}catch (Exception e) {
			logger_log.error("getaepslogreport:::::::::: "+e);
		}
		
		return returnData;
	}
	
	
	@RequestMapping(value = "/updatebankt", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> updatebankt(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.updatebankt(request, userDetails);
			}
		}catch (Exception e) {
			logger_log.error("updatebankt :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	/*
	@RequestMapping(value = "/insertApiRefillTransaction", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getApiRefillTransaction(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = adminService.getApiRefillTransaction(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error(" inside walletRefillRequest controller " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	*/
}
