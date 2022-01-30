package com.recharge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;

import com.recharge.businessdao.AuthenticationCommandCenter;
import com.recharge.businessdao.GlobalCommandCenter;
import com.recharge.customModel.DefaultCharge;
import com.recharge.customModel.DefaultCommission;
import com.recharge.customModel.IndividualCharge;
import com.recharge.customModel.IndividualCommission;
import com.recharge.customModel.Notification;
import com.recharge.customModel.RechargeReport;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.model.Api;
import com.recharge.model.Impscommission;
import com.recharge.model.News;
import com.recharge.model.Operator;
import com.recharge.model.Reseller;
import com.recharge.model.User;
import com.recharge.utill.UtilityClass;


@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class CommonController {
	private static final Logger logger_log = Logger.getLogger(RestAuthenticationConroller.class);
	@Autowired AuthenticationCommandCenter authenticationCommandCenter;
	@Autowired GlobalCommandCenter globalCommandCenter;		
	//@Autowired HttpServletRequest httpRequest;
	@Autowired HttpSession session;

	@RequestMapping("userRegistration")
	public ModelAndView registar(){
		ModelAndView mv = new ModelAndView("registration");
		return mv;
		
	}
	
	@RequestMapping(value = "/getDashBoardDetails", method = RequestMethod.POST)
	public Map<String, Object> getUserDetails(){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			User userDetails = (User) session.getAttribute("UserDetails");			
			if (userDetails != null) {
				userDetails.setPassword(null);
				userDetails.setStatus(null);
				userDetails.setDelFlag(null);
				returnData.put("userDetails", userDetails);
				if(userDetails.getRoleId()==1 || userDetails.getRoleId()==2){
				List<Operator> oplist = globalCommandCenter.getAllOperator();
				if(!oplist.isEmpty()) {					
					returnData.put("operator", oplist);
				}	
				List<RechargeReport> list  = globalCommandCenter.getDashBoardRechargeReport(userDetails);
				returnData.put("rechargeReport", list);
				}
				if (userDetails.getRoleId() != 5 || userDetails.getRoleId() != 100) {
					if(userDetails.getRoleId()==1){
					List<Api> apiList = globalCommandCenter.getAllApi();
					if (!apiList.isEmpty()) {
						returnData.put("api", apiList);
					}	
				}
					Notification notification = globalCommandCenter.getNotification(userDetails);
					if (notification != null) {
						returnData.put("notification", notification);
					}
				}
				
				Reseller reseller = globalCommandCenter.getResellerByWlId(userDetails.getWlId());
				returnData.put("reseller", reseller);
				
				News news = globalCommandCenter.getNews(userDetails.getWlId());
				Map<String, Object> totalData = globalCommandCenter.getTotalUserBalanceRechargeStatus(userDetails);
				
				
				returnData.put("news", news);
				returnData.put("totalData", totalData);
				
				returnData.put("status", "1");
			}else {
				session.invalidate();
				returnData.put("message", "Session Expire! Please Login.");
				returnData.put("nextPage", "home");
				returnData.put("status", "0");
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("getUserDetails :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/addmicroatmusernew",method = RequestMethod.POST, headers = "content-type=application/json")	
	public Map<String, Object> addmicroatmuser(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");				
				returnData = globalCommandCenter.addmicroatmusernew(request, userDetails);				
			}
		} catch (Exception e) {
			logger_log.error("addUser :::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/getUserByUserTypelatest", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getUserByUserTypelatest(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				if(request.containsKey("mappuserType")){
					request.put("userType", request.get("mappuserType"));
				}
				if(request.containsKey("userMappingTo")){
					request.put("userType", request.get("userMappingTo"));
				}
				returnData = globalCommandCenter.getUserByUserTypelatest(request, userDetails);				
			}
		}catch (Exception e) {
			logger_log.error("getUserByUserTypelatest :::::::::::: "+e);	
			
			returnData.put("message", e);		
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	
	@RequestMapping(value = "/fetchOperator", method = RequestMethod.POST)
	public Map<String,Object>  fetchOperator(){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			User userDetails = (User) session.getAttribute("UserDetails");
			if (userDetails != null) {
				List<Operator> oplist = globalCommandCenter.getAllOperator();
				if(!oplist.isEmpty()) {					
					returnData.put("operator", oplist);
					returnData.put("status", "1");
				}
			}else {
				session.invalidate();
				returnData.put("message", "Session Expire! Please Login.");
				returnData.put("nextPage", "home");
				returnData.put("status", "0");
			}
		}catch(Exception e){
			session.invalidate();
			logger_log.error("fetchOperator :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
		
		
	}
	
	@RequestMapping(value = "/getRBLAEPSReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getRBLAEPSReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");		
				returnData = globalCommandCenter.getRBLAEPSReport(request,userDetails);
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
	
	
	
	
	@RequestMapping(value = "/packageCreate", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> packiageCreate(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");		
				returnData = globalCommandCenter.packageCreate(request,userDetails);
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
	
	@RequestMapping(value = "/assignPackage", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String,Object>  assignPackage(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		if(session.getAttribute("UserDetails") == null) {
			returnData.put("nextPage", "home");
			returnData.put("message", "Session Expire! Please Login Again.");
			returnData.put("status", "0");
		}else {
			User userDetails = (User) session.getAttribute("UserDetails");	
			/*
			 * System.out.println("package:::::::::::::"+request.get("userType"));
			 * System.out.println("userId:::::::::::"+request.get("userId"));
			 * System.out.println("userType::::::::::"+request.get("package"));
			 */
			returnData = globalCommandCenter.assignPackage(request,request.get("userId"),userDetails);
		}
		return returnData;
		
	}
	
	@RequestMapping(value = "/viewAssignPackage", method = RequestMethod.POST)
	public Map<String,Object>  viewAssignPackage(){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		User userDetails = (User) session.getAttribute("UserDetails");
		if(session.getAttribute("UserDetails") == null) {
			returnData.put("nextPage", "home");
			returnData.put("message", "Session Expire! Please Login Again.");
			returnData.put("status", "0");
		}else {
			returnData = globalCommandCenter.viewAssignPackage(userDetails);
		}
		return returnData;
	}
	
	@RequestMapping(value = "/assignEditPackage", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String,Object>  assignEditPackage(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		if(session.getAttribute("UserDetails") == null) {
			returnData.put("nextPage", "home");
			returnData.put("message", "Session Expire! Please Login Again.");
			returnData.put("status", "0");
		}else {
		
			returnData = globalCommandCenter.assignEditPackage(request,request.get("user_id"));
		}
		return returnData;
	}
	
	@RequestMapping(value = "/assignedPackage", method = RequestMethod.POST)
	public Map<String,Object>  assignedPackage(){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		User userDetails = (User) session.getAttribute("UserDetails");
		if(session.getAttribute("UserDetails") == null) {
			returnData.put("nextPage", "home");
			returnData.put("message", "Session Expire! Please Login Again.");
			returnData.put("status", "0");
		}else {
			returnData = globalCommandCenter.assignedPackage(userDetails);
		}
		return returnData;
		
	}
	@RequestMapping(value = "/updatemyPackage", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String,Object>  updatemyPackage(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		
		if(session.getAttribute("UserDetails") == null) {
			returnData.put("nextPage", "home");
			returnData.put("message", "Session Expire! Please Login Again.");
			returnData.put("status", "0");
		}else {
			User userDetails = (User) session.getAttribute("UserDetails");
			returnData = globalCommandCenter.updatemyPackage(request,userDetails);
		}
		return returnData;
		
	}
	
	@RequestMapping(value = "/getRBLSETTLEMENTReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getRBLSETTLEMENTReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");		
				returnData = globalCommandCenter.getRBLSETTLEMENTReport(request,userDetails);
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
	
	@RequestMapping(value = "/setImpscharge", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String,Object> setIMPScharge(@RequestBody Impscommission[]  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData= globalCommandCenter.setIMPScharge(request, userDetails);
			}
				
			}catch (Exception e) {
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
		
		
		return returnData;
		
	}
	
	@RequestMapping(value = "/getIMPScharge", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String,Object> getIMPScharge(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.getIMPScharge(request, userDetails);
			}
				
			}catch (Exception e) {
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
		
		
		return returnData;
		
	}
	@RequestMapping(value="/getImpsReport",method = RequestMethod.POST,headers = "content-type=application/json")
	public Map<String, Object> getImpsReport(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData= globalCommandCenter.getImpsReport(request, userDetails);
			}

		} catch (Exception e) {
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}

		return returnData;
	}
	
	@RequestMapping(value = "/editProfile", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> editProfile(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");		
				returnData = globalCommandCenter.editProfile(request,userDetails);
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
	
	@RequestMapping(value = "/changePassWord", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> changePass(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.changePass(request, userDetails);
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
	
	@RequestMapping(value = "/getNamebyUserId", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getUserNamebyUserId(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {	
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "2");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.getNamebyUserId(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("getNamebyUserId :::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("name", "No User Found");
			returnData.put("status", "2");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/checkUniqueUser", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> checkUniqueUser(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> param1 = new HashMap<String, String>();	
		try {	
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				if (UtilityClass.checkStringIsNull(request.get("username"))) {
					logger_log.warn("username : " + request.get("username"));
					boolean flag = false;
					if(UtilityClass.isValidMobileNumber(request.get("username"))) {
						flag = true;
					}else {
						flag = UtilityClass.isValidEmailAddress(request.get("username"));
					}						
					if (flag) {
						param1.put("mobile", request.get("username"));
						param1.put("email", request.get("username"));
						String username = authenticationCommandCenter.getUserNamebyMobileEmail(CustomSqlQuery.getUserIdByMobileEmail, param1);
						if (username != null) {
							returnData.put("message", "User Already exist with Same details.");
							returnData.put("status", "1");
						} else {
							returnData.put("message", "Unique Details!");							
							returnData.put("status", "0");
						}
					}
				}else {
					returnData.put("message", "Enter Mobile and email properly.");
					returnData.put("status", "0");
				}
			}
		} catch (Exception e) {
			logger_log.error("checkUniqueUser :::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("name", "Invalid Details");
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/addUser",method = RequestMethod.POST, headers = "content-type=application/json")	
	public Map<String, Object> addUser(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");				
				returnData = globalCommandCenter.addUser(request, userDetails);				
			}
		} catch (Exception e) {
			logger_log.error("addUser :::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	@RequestMapping(value = "/microatmUser",method = RequestMethod.POST, headers = "content-type=application/json")	
	public Map<String, Object> microatmUser(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");				
				returnData = globalCommandCenter.microatmUser(request, userDetails);				
			}
		} catch (Exception e) {
			logger_log.error("microatmUser :::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/applicationform",method = RequestMethod.POST)	
	public Map<String, Object> applicationform(@RequestParam(value = "applicationdetails") String applicationdetails,
			@RequestParam(value = "panfile") MultipartFile panfile,@RequestParam(value = "photofile") MultipartFile photofile,@RequestParam(value = "aadharfile") MultipartFile aadharfile,@RequestParam(value = "voterfile") MultipartFile voterfile) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				System.out.println(":::"+applicationdetails);
				JSONObject aadhardetail = new JSONObject(applicationdetails);
				Map<String, String> inputData = new HashMap<String, String>();
				inputData = UtilityClass.toMap(aadhardetail);	
				User userDetails = (User) session.getAttribute("UserDetails");				
				returnData = globalCommandCenter.applicationform(inputData,panfile,photofile,aadharfile,voterfile,userDetails);				
			}
		} catch (Exception e) {
			logger_log.error("applicationform :::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/getUserByUserType", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getUserByUserType(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.getUserByUserType(request, userDetails);				
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
	
	
	@RequestMapping(value = "/getUserByUserType1", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getUserByUserType1(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.getUserByUserType1(request, userDetails);				
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
	
	@RequestMapping(value = "/addBalance", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> addBalance(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.addBalance(request, userDetails);
			}
		}catch (Exception e) {
			logger_log.error("addBalance :::::::::::: "+e);			
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	@RequestMapping(value = "/statuschange", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> statuschange(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.statuschange(request, userDetails);
			}
		}catch (Exception e) {
			logger_log.error("addBalance :::::::::::: "+e);			
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	@RequestMapping(value = "/statuschangeforcard", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> statuschangeforcard(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.statuschangeforcard(request, userDetails);
			}
		}catch (Exception e) {
			logger_log.error("addBalance :::::::::::: "+e);			
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/getMyDiscount", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getMyDiscount(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				returnData = globalCommandCenter.getMyDiscount(request);
				
			}
		}catch (Exception e) {
			logger_log.error("getMyDiscount ::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		if(returnData.get("status").equals("0")){
			returnData.put("nextPage", "home");
		}
		return returnData;
	}
	
	
	@RequestMapping(value = "/getMyDefaultDiscount", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getMyDefaultDiscount(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				returnData = globalCommandCenter.getDefaultCommission(request);	
			}
		}catch (Exception e) {
			logger_log.error("getMyDefaultDiscount ::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/setDefaultDiscount", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> setDefaultDiscount(@RequestBody DefaultCommission[]  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");			
				returnData = globalCommandCenter.setDefaultDiscount(request, userDetails);				
			}
		} catch (Exception e) {
			logger_log.error("setDefaultDiscount ::::::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/getIndividualDiscount", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getIndividualDiscount(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");		
				returnData = globalCommandCenter.getIndividualDiscount(request, userDetails);	
			}
		}catch (Exception e) {
			logger_log.error("getIndividualDiscount ::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/setIndividualDiscount", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> setIndividualDiscount(@RequestBody IndividualCommission[]  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");			
				returnData = globalCommandCenter.setIndividualDiscount(request, userDetails);				
			}
		} catch (Exception e) {
			logger_log.error("setIndividualDiscount ::::::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/getDefaultChargeSetting", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getDefaultChargeSetting(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");	
				returnData = globalCommandCenter.getDefaultChargeSetting(request,userDetails);
			}
		} catch (Exception e) {
			logger_log.error("getDefaultChargeSetting ::::::::::: " + e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	
	@RequestMapping(value = "/setDefaultChargeSetting", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> setDefaultChargeSetting(@RequestBody DefaultCharge[]  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");			
				returnData = globalCommandCenter.setDefaultChargeSetting(request, userDetails);				
			}
		} catch (Exception e) {
			logger_log.error("setDefaultDiscount ::::::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/getIndividualCharge", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getIndividualCharge(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");	
				returnData = globalCommandCenter.getIndividualCharge(request,userDetails);	
			}
		}catch (Exception e) {
			logger_log.error("getIndividualCharge ::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/setIndividualCharge", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> setIndividualCharge(@RequestBody IndividualCharge[]  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");		
				System.out.println(request);
				returnData = globalCommandCenter.setIndividualCharge(request, userDetails);				
			}
		} catch (Exception e) {
			logger_log.error("setIndividualDiscount ::::::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	
	@RequestMapping(value = "/getViewUser", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getViewUser(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");	
				returnData = globalCommandCenter.getViewUserDetails(request, userDetails);
			}
		}catch (Exception e) {
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/getViewInactiveUser", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getViewInactiveUser(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");	
				returnData = globalCommandCenter.getViewInactiveUser(request, userDetails);
			}
		}catch (Exception e) {
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/viewBankDetails", method = RequestMethod.POST)
	public Map<String, Object> viewBankDetails(){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.getBankDetailsByWlId(userDetails.getWlId());
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("getUserDetails :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> forgotPassword(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		
		try {
			returnData = globalCommandCenter.forgotPassword(request);			
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("getUserDetails :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/advancedCustomerNo", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> advancedCustomerNo(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.advancedCustomerNo(request, userDetails);
			}	
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("getUserDetails :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/advancedSearchUser", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> advancedSearchUser(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = globalCommandCenter.advancedSearchUser(request, userDetails);
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("advancedSearchUser :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/_GetUpline", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> _GetUpline(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				System.out.println(request);
				returnData = globalCommandCenter._GetUpline(request, userDetails);
			}
		}catch (Exception e) {
			session.invalidate();
			logger_log.error("_GetUpline :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/getMyCharge", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getMyCharge(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				request.put("userId", userDetails.getUserId());
				returnData = globalCommandCenter.getIndividualCharge(request, userDetails);
				
			}
		}catch (Exception e) {
			logger_log.error("getMyCharge ::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		if(returnData.get("status").equals("0")){
			returnData.put("nextPage", "home");
		}
		return returnData;
	}
	
	@RequestMapping(value = "/addEnquery",method = RequestMethod.POST, headers = "content-type=application/json")	
	public Map<String, Object> addEnquery(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			System.out.println(request);
				//User userDetails = (User) session.getAttribute("UserDetails");				
				returnData = globalCommandCenter.addEnquery(request);				
			
		} catch (Exception e) {
			logger_log.error("addEnquery :::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		//returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/getPackagesDetails", method = RequestMethod.POST)
	public Map<String,Object>  getPackagesDetails(){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try{
			User userDetails = (User) session.getAttribute("UserDetails");
			if (userDetails != null) {
				returnData = globalCommandCenter.getPackagesDetails(userDetails);
				
			}else {
				session.invalidate();
				returnData.put("message", "Session Expire! Please Login.");
				returnData.put("nextPage", "home");
				returnData.put("status", "0");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getPackagesDetails :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return  returnData;
	}
	
	@RequestMapping(value = "/viewPackagewiseCommCharge", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String,Object>  viewAssignedPackage(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		User userDetails = (User) session.getAttribute("UserDetails");
		if(session.getAttribute("UserDetails") == null) {
			returnData.put("nextPage", "home");
			returnData.put("message", "Session Expire! Please Login Again.");
			returnData.put("status", "0");
		}else {
			
			returnData = globalCommandCenter.viewPackagewiseCommCharge(request);
		}
		return returnData;
		
	}
	
	@RequestMapping(value="/getpanReport",method = RequestMethod.POST,headers = "content-type=application/json")
	public Map<String, Object> getpanReport(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData= globalCommandCenter.getpanReport(request, userDetails);
				
			}

		} catch (Exception e) {
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}

		return returnData;
	}
	
	@RequestMapping(value="/getpanCouponReport",method = RequestMethod.POST,headers = "content-type=application/json")
	public Map<String, Object> getpanCouponReport(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData= globalCommandCenter.getpanCouponReport(request, userDetails);
				
			}

		} catch (Exception e) {
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}

		return returnData;
	}
	
	@RequestMapping(value = "/DeleteMyPackageDetail", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String,Object>  DeleteMyPackageDetail(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try{
			User userDetails = (User) session.getAttribute("UserDetails");
			if (userDetails != null) {
				returnData = globalCommandCenter.DeleteMyPackageDetail(request,userDetails);
				
			}else {
				session.invalidate();
				returnData.put("message", "Session Expire! Please Login.");
				returnData.put("nextPage", "home");
				returnData.put("status", "0");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			logger_log.error("DeleteMyPackageDetail :::::::::: "+e);
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return  returnData;
	}
	
	@RequestMapping(value = "/fetchaepsbankdt", method = RequestMethod.POST)
	public Map<String, Object> fetchaepsbankdt(){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");		
				returnData = globalCommandCenter.fetchaepsbankdt(userDetails);
			}
		}catch (Exception e) {
			logger_log.error("fetchaepsbankdt :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value="/addgstdt",method = RequestMethod.POST,headers = "content-type=application/json")
	public Map<String, Object> addgstdt(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData= globalCommandCenter.addgstdt(request, userDetails);
				
			}

		} catch (Exception e) {
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}

		return returnData;
	}
		
}
