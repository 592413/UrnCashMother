package com.recharge.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.recharge.businessdao.AuthenticationCommandCenter;
import com.recharge.businessdao.GlobalCommandCenter;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.model.User;



@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class RestAuthenticationConroller {
	private static final Logger logger_log = Logger.getLogger(RestAuthenticationConroller.class);
	@Autowired AuthenticationCommandCenter authenticationCommandCenter;
	@Autowired GlobalCommandCenter globalCommandCenter;	
	@Autowired HttpSession session;
	
	
	@RequestMapping(value = "/performLogin", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> performLogin(@RequestHeader (value="User-Agent") String userAgent, @RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		Map<String, String> param = new HashMap<String, String>();
		Map<String, String> param1 = new HashMap<String, String>();	
		
		try{			
			logger_log.warn("username : "+request.get("username")+"      password: "+request.get("password"));							
			param1.put("mobile", request.get("username"));
			param1.put("email", request.get("username"));			
			String userId = authenticationCommandCenter.getUserNamebyMobileEmail(CustomSqlQuery.getUserIdByMobileEmail, param1);
			if(userId!=null){
				param.put("userId", userId);
				param.put("password", request.get("password"));	
				String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
				System.out.println(username);
				if(username != null){					
					//String sessionId = UUID.randomUUID().toString();
					//System.out.println("SESSION ID :::: "+sessionId);
					User userDetails = globalCommandCenter.getUserByUserId(username);
					HttpServletRequest httprequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
					String ip = httprequest.getRemoteAddr();
					String deviceId = userAgent;
					if (userDetails != null) {
						if(userDetails.getStatus().equals("1")){
							returnData.put("status", "0");
							returnData.put("message", "Login failed!");
						} else {
							if(userDetails.getDelFlag().equals("1")){
								returnData.put("status", "0");
								returnData.put("message", "Login Failed");
							} else {
								if(userDetails.getWlId().equals("MSR8TDF")){
									returnData.put("status", "0");
									returnData.put("message", "Login Failed");
								}else{
								//String onstatus="ON";
								boolean flag = globalCommandCenter.insertSigninHistory(ip, userDetails.getUserId(),	userDetails.getMobile(), deviceId);
								if (flag) {
									userDetails.setPassword(null);
									userDetails.setTranPin(null);
									userDetails.setDelFlag(null);
									userDetails.setStatus(null);
									returnData.put("status", "1");
									returnData.put("message", "Login Successfully!");
									returnData.put("nextPage", "home");
									session.setAttribute("UserDetails", userDetails);
								} else {
									returnData.put("status", "0");
									returnData.put("message", "Login failed!");
								}
								}
							}
						}
					}else{
						returnData.put("status", "0");
						returnData.put("message", "Login failed!");
					}
				}else{
					returnData.put("status", "0");
					returnData.put("message", "Login failed! Invalid User or Inactive User");
				}
			}else{
				returnData.put("status", "0");
				returnData.put("message", "Login failed!");
			}			
		}catch(Exception e){
			logger_log.warn("performLogin ::::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		
		return returnData;
	}
	
	@RequestMapping(value = "getMsg", method = RequestMethod.GET)
	public Map<String, Object> getSessionMsg(){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		
		String msg = "";
		if(session.getAttribute("msg") == null) {
			msg = "";
		}else {
			msg = (String)session.getAttribute("msg");
			session.removeAttribute("msg");
		}
		returnData.put("message", msg);
		return returnData;
	}
	
	@RequestMapping(value = "/getReseller", method = RequestMethod.POST, headers = "Content-type=application/json")
	public  Map<String,Object>  getReseller(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try{
			
			String url=request.get("url");
			System.out.println("url:::::::::"+url);
			//String url="asqsas";
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("url", url);
			returnData=authenticationCommandCenter.getReseller(param);
		}catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getReseller :::::::::: "+e);
			//returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please try with proper url.");			
			returnData.put("status", "0");
		}
		return  returnData;
	}
	
}
