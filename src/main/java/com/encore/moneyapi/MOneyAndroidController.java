package com.encore.moneyapi;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recharge.yesbankservice.YesBankAndroidController;

@RestController

@RequestMapping(value = "rest")
public class MOneyAndroidController {
	private static final Logger logger_log = Logger.getLogger(MOneyAndroidController.class);
	
	@Autowired MoneyAndroidBussinnessDao PaytmAndroidBussinnessDao;
	
	@RequestMapping(value = "checkuserPaytmAndroid", method = RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> checkuserYesBankAndroid(@RequestBody  String request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			
				
				returnData = PaytmAndroidBussinnessDao.checkuserPaytmAndroid(request);
				System.out.println("checkuserPaytmAndroid:::::::::::::::::"+returnData);
			
		}catch (Exception e) {
			logger_log.error("checkuserPaytmAndroid::::::::::"+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	@RequestMapping(value = "PaytmRemitterRegisterAndroid", method = RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> PaytmRemitterRegisterAndroid(@RequestBody  String request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			
				
				returnData = PaytmAndroidBussinnessDao.PaytmRemitterRegisterAndroid(request);
				
			
		}catch (Exception e) {
			logger_log.error("PaytmRemitterRegisterAndroid::::::::::"+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		//returnData.put("nextPage", "home");
		return returnData;
	}
	
	


	
	
	@RequestMapping(value = "PaytmNewBeneficiaryAndroid", method = RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> PaytmNewBeneficiaryAndroid(@RequestBody  String request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			
				
				returnData = PaytmAndroidBussinnessDao.PaytmNewBeneficiaryAndroid(request);
			
		}catch (Exception e) {
			logger_log.error("PaytmNewBeneficiaryAndroid::::::::::"+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "deletePaytmbeneAndroid", method = RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> deletePaytmbeneAndroid(@RequestBody  String request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			
				
				returnData = PaytmAndroidBussinnessDao.deletePaytmbeneAndroid(request);
			
		}catch (Exception e) {
			logger_log.error("deletePaytmbeneAndroid::::::::::"+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}   
	
	
	
	
	@RequestMapping(value = "PaytmValidateBeneficiaryAndroid", method = RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> PaytmValidateBeneficiaryAndroid(@RequestBody  String request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			
				
				returnData = PaytmAndroidBussinnessDao.PaytmValidateBeneficiaryAndroid(request);
			
		}catch (Exception e) {
			logger_log.error("PaytmValidateBeneficiaryAndroid::::::::::"+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
	//	returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "PaytmMoneytransferAndroid", method = RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> PaytmMoneytransferAndroid(@RequestBody  String request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			
				
				returnData = PaytmAndroidBussinnessDao.PaytmMoneytransferAndroid(request);
			
		}catch (Exception e) {
			logger_log.error("PaytmMoneytransferAndroid::::::::::"+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		//returnData.put("nextPage", "home");
		return returnData;
	}
	
	
}
