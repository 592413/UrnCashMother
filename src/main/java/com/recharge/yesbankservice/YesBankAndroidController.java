package com.recharge.yesbankservice;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recharge.businessdao.AndroidService;
import com.recharge.controller.AndroidController;
import com.recharge.yesbankservicedao.YesBankAndroidService;

@RestController

@RequestMapping(value = "rest")
public class YesBankAndroidController {
	@Autowired YesBankAndroidService yesbankandroidService;
	private static final Logger logger_log = Logger.getLogger(YesBankAndroidController.class);	
	//YesBank
		@RequestMapping(value = "yesBankTransactionAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> yesBankTransaction(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.yesBankTransactionAndroid(request);
					System.out.println("yesBankTransaction:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			returnData.put("nextPage", "home");
			return returnData;
		}
		
		@RequestMapping(value = "miniStatementAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> miniStatementAndroid(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {
			returnData = yesbankandroidService.miniStatementAndroid(request);
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			
			return returnData;
		}

		
		
		@RequestMapping(value = "yesBanksearchAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> yesBanksearchAndroid(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.yesBanksearchAndroid(request);
					System.out.println("yesBanksearchAndroid:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			returnData.put("nextPage", "home");
			return returnData;
		}
		
		
		@RequestMapping(value = "yesBankRDHashAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> yesBankRDHashAndroid(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.yesBankRDHashAndroid(request);
					System.out.println("yesBanksearchAndroid:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			returnData.put("nextPage", "home");
			return returnData;
		}
		
		
		@RequestMapping(value = "yesBankregisterAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> yesBankregisterAndroid(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.yesBankregisterAndroid(request);
					if(returnData.get("status").toString().equalsIgnoreCase("1")){
						returnData.put("message","Success");
					}
					System.out.println("yesBankregisterAndroid:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			returnData.put("nextPage", "home");
			return returnData;
		}

		
		@RequestMapping(value = "yesBankrddatahashAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> yesBankrddatahashAndroid(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.yesBankrddatahashAndroid(request);
					System.out.println("yesBankTransaction:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			returnData.put("nextPage", "home");
			return returnData;
		}
		
		@RequestMapping(value = "getYesBankAEPSReportAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> getYesBankAEPSReportAndroid(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.getYesBankAEPSReportAndroid(request);
					System.out.println("yesBankTransaction:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			returnData.put("nextPage", "home");
			return returnData;
		}
		
		@RequestMapping(value = "getMicroAEPSReportAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> getMicroAEPSReportAndroid(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.getMicroAEPSReportAndroid(request);
					System.out.println("yesBankTransaction:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			returnData.put("nextPage", "home");
			return returnData;
		}
		
		@RequestMapping(value = "getMicroAEPSReportAndroidnew", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> getMicroAEPSReportAndroidnew(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.getMicroAEPSReportAndroidnew(request);
					System.out.println("yesBankTransaction:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			returnData.put("nextPage", "home");
			return returnData;
		}
		
		@RequestMapping(value = "getFingerAEPSReportAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> getFingerAEPSReportAndroid(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.getFingerAEPSReportAndroid(request);
					System.out.println("yesBankTransaction:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			returnData.put("nextPage", "home");
			return returnData;
		}

		@RequestMapping(value = "checkuserYesBankAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> checkuserYesBankAndroid(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.checkuserYesBankAndroid(request);
					System.out.println("checkuserYesBankAndroid:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
		//	returnData.put("nextPage", "home");
			return returnData;
		}
		@RequestMapping(value = "yesBankdmrregisterAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> yesBankdmrregisterAndroid(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.yesBankdmrregisterAndroid(request);
					System.out.println("yesBankTransaction:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			//returnData.put("nextPage", "home");
			return returnData;
		}
		
		
		@RequestMapping(value = "yesBankremitterverifyAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> yesBankremitterverifyAndroid(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.yesBankremitterverifyAndroid(request);
					System.out.println("yesBankTransaction:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			//returnData.put("nextPage", "home");
			return returnData;
		}

		
		@RequestMapping(value = "yesBankdeletebeneAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> yesBankdeletebeneAndroid(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.yesBankdeletebeneAndroid(request);
					System.out.println("yesBankTransaction:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			returnData.put("nextPage", "home");
			return returnData;
		}   
		
		
		@RequestMapping(value = "VerifyDeleteYesBaneAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> VerifyDeleteYesBaneAndroid(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.VerifyDeleteYesBaneAndroid(request);
					System.out.println("yesBankTransaction:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			returnData.put("nextPage", "home");
			return returnData;
		}

		
		@RequestMapping(value = "addYesBaneAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> addYesBaneAndroid(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.addYesBaneAndroid(request);
					System.out.println("yesBankTransaction:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			returnData.put("nextPage", "home");
			return returnData;
		}
		
		
		@RequestMapping(value = "verifyYesBaneAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> verifyYesBaneAndroid(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.verifyYesBaneAndroid(request);
					System.out.println("yesBankTransaction:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			returnData.put("nextPage", "home");
			return returnData;
		}
		
		
		@RequestMapping(value = "YesmoneytransferAndroid", method = RequestMethod.POST,headers="content-type=application/json")
		public Map<String, Object> YesmoneytransferAndroid(@RequestBody  String request){
			Map<String, Object> returnData = new HashMap<String, Object>();		
			try {			
				
					
					returnData = yesbankandroidService.YesmoneytransferAndroid(request);
					System.out.println("YesmoneytransferAndroid:::::::::::::::::"+returnData);
				
			}catch (Exception e) {
				logger_log.error("searchCustomer::::::::::"+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			returnData.put("nextPage", "home");
			return returnData;
		}
		
		
		
		

		//YesBank
				@RequestMapping(value = "fetchagentcode", method = RequestMethod.POST,headers="content-type=application/json")
				public Map<String, Object> fetchagentcode(@RequestBody  String request){
					Map<String, Object> returnData = new HashMap<String, Object>();		
					try {			
						
							
							returnData = yesbankandroidService.fetchagentcode(request);
						
					}catch (Exception e) {
						logger_log.error("fetchagentcode::::::::::"+e);
						returnData.put("message", "Exception! Please try again");		
						returnData.put("status", "0");
					}
					returnData.put("nextPage", "home");
					return returnData;
				}
				
				
				/*
				 * @RequestMapping(value = "sendaepsreq", method =
				 * RequestMethod.POST,headers="content-type=application/json") public
				 * Map<String, Object> sendaepsreq(@RequestBody String request){ Map<String,
				 * Object> returnData = new HashMap<String, Object>(); try {
				 * 
				 * 
				 * returnData = yesbankandroidService.sendaepsreq(request);
				 * 
				 * }catch (Exception e) { logger_log.error("sendaepsreq::::::::::"+e);
				 * returnData.put("message", "Exception! Please try again");
				 * returnData.put("status", "0"); } // returnData.put("nextPage", "home");
				 * return returnData; }
				 */
		
		
		
		//YesBank
}
