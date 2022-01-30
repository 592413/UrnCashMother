package com.recharge.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.internal.LinkedTreeMap;
import com.recharge.businessdao.ApiResponseService;
import com.recharge.businessdao.RetailerService;
import com.recharge.model.User;

@RestController
public class ApiResponseController {
	
	@Autowired ApiResponseService apiResponseService;
	@Autowired RetailerService retailerService;
	private static final Logger logger_log = Logger.getLogger(ApiResponseController.class);
	
	@RequestMapping(value = "/paytmfinalres", method = RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> paytmfinalres(@RequestBody Map<String,Object> request) {
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> returnData = new HashMap<String, Object>();
		//{status=SUCCESS, statusCode=DE_001, statusMessage=Successful disbursal to Bank Account is done, result={mid=Mybusi44785915550152, orderId=0069658429509, paytmOrderId=202101151713336295936858, amount=100.00, commissionAmount=0.00, tax=0.00, rrn=null, beneficiaryName=null, isCachedData=null, cachedTime=null, reversalReason=null}}

		logger_log.warn("paytmfinalres:::::"+request);
		try {
			returnData = apiResponseService.paytmfinalres(request);
			
		} catch (Exception e) {
			logger_log.error("paytmfinalres::::::::::::::::::::::::::" + e);
		}
		return returnData;
	}
	
	@RequestMapping(value = "/paytmfinalDMTres", method = RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> paytmfinalDMTres(@RequestBody Map<String,Object> request) {
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> returnData = new HashMap<String, Object>();
		//{status=SUCCESS, statusCode=DE_001, statusMessage=Successful disbursal to Bank Account is done, result={mid=Mybusi44785915550152, orderId=13842, paytmOrderId=202101151659296748889744, amount=100.00, commissionAmount=2.00, tax=0.36, rrn=0223476727, beneficiaryName=null, isCachedData=null, cachedTime=null, reversalReason=null}}

		logger_log.warn("paytmfinalDMTres:::::"+request);
		try {
			returnData = apiResponseService.paytmfinalDMTres(request);
			
		} catch (Exception e) {
			logger_log.error("paytmfinalDMTres::::::::::::::::::::::::::" + e);
		}
		return returnData;
	}
	
	//IRCTC
	@RequestMapping(value = "/checkIRCTCBalance", method = RequestMethod.POST)
	public Map<String, Object> checkIRCTCBalance(@RequestParam Map<String,String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData = apiResponseService.checkIRCTCBalance(request);
		return returnData;
		
	}
	
	@RequestMapping(value = "/IRCTCProcess", method = RequestMethod.POST)
	public Map<String, Object> IRCTCProcess(@RequestParam Map<String,String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData = apiResponseService.IRCTCProcess(request);
		return returnData;
		
	}
	
	@RequestMapping(value = "/IRCTCResponse", method = RequestMethod.POST)
	public Map<String, Object> IRCTCResponse(@RequestParam Map<String,String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData = apiResponseService.IRCTCResponse(request);
		return returnData;
		
	}
	
	@RequestMapping(value = "/microatmrequestnew", method = RequestMethod.POST)
	public Map<String, Object> microatmrequestnew(@RequestBody Map<String,Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		logger_log.warn("microatmrequest:::::::::::::::::::::"+request);
		returnData = apiResponseService.microatmrequestnew(request);
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/microatmresponsenew", method = RequestMethod.POST)
	public Map<String, Object> microatmresponsenew(@RequestBody Map<String,Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		logger_log.warn("microatmresponsenew:::::::::::::::::::::"+request);
		returnData = apiResponseService.microatmresponsenew(request);
		System.out.println(returnData);
		return returnData;
	}
	
	//IRCTC
		@RequestMapping(value = "/arucheckbalance", method = RequestMethod.POST)
		public Map<String, Object> arucheckbalance(@RequestBody Map<String,String> request) {
			Map<String, Object> returnData = new HashMap<String, Object>();
			returnData = apiResponseService.arucheckbalance(request);
			return returnData;
			
		}
		
		@RequestMapping(value = "/aruresponse", method = RequestMethod.POST)
		public Map<String, Object> aruresponse(@RequestBody Map<String,String> request) {
			Map<String, Object> returnData = new HashMap<String, Object>();
			returnData = apiResponseService.aruresponse(request);
			return returnData;
			
		}
	
	@RequestMapping(value = "/instantpayAEPSFinalRes", method = RequestMethod.GET)
	public void instantpayAEPSFinalRes(@RequestParam Map<String,String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		logger_log.warn("instantpayAEPSFinalRes:::::::::::::::::::::::::"+request);
		returnData = apiResponseService.instantpayAEPSFinalRes(request);
	//	System.out.println(returnData);
		
	}
	
	@RequestMapping(value = "/backup", method = RequestMethod.GET)
	public void backup() {
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData = apiResponseService.backup();
	//	System.out.println(returnData);
		
	}
	
	
	@RequestMapping(value ="/instantpayAEPS",method = RequestMethod.POST,headers ="content-type=application/json")
	public Map<String, Object> instantpayAEPS(@RequestBody Map<String,Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag = false;
		try{
			logger_log.warn("instantpayAEPSrequest:::::::::::::::::::::::::"+request);
			returnData=apiResponseService.instantpayAEPS(request);
			logger_log.warn("instantpayAEPSres:::::::::::::::::::::::::"+returnData);
		}catch (Exception e) {
			logger_log.error("instantpayAEPS:::::::::::::::::::::::::"+e);
		}
		return returnData;
	}
	
	@RequestMapping(value ="/instantpayAEPSDisplay",method = RequestMethod.POST,headers ="content-type=application/json")
	public Map<String, Object> instantpayAEPSDisplay(@RequestBody Map<String,Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
	//	boolean flag = false;
		try{
			logger_log.warn("instantpayAEPSDisplay:::::::::::::::::::::::::"+request);
			returnData.put("response_code","TXN");
			Map<String, Object> display_params= new HashMap<String, Object>();
			display_params.put("message","success");
			returnData.put("display_params",display_params);
			returnData.put("response_msg","Transaction Successfull");
			logger_log.warn("instantpayAEPSDisplay:::::::::::::::::::::::::"+returnData);
		}catch (Exception e) {
			logger_log.error("instantpayAEPSDisplay:::::::::::::::::::::::::"+e);
		}
		return returnData;
	}
	
	@RequestMapping(value ="/instantpayAEPSBalance",method = RequestMethod.POST,headers ="content-type=application/json")
	public Map<String, Object> instantpayAEPSBalance(@RequestBody Map<String,Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag = false;
		try{
			logger_log.warn("instantpayAEPSBalancerequest:::::::::::::::::::::::::"+request);
			returnData=apiResponseService.instantpayAEPSBalance(request);
			logger_log.warn("instantpayAEPSBalance:::::::::::::::::::::::::"+returnData);
		}catch (Exception e) {
			logger_log.error("instantpayAEPS:::::::::::::::::::::::::"+e);
		}
		return returnData;
	}
	
	
	
	
	@RequestMapping(value = "/doopMeApiResponsenew", method = RequestMethod.GET)
	public void doopMeApiResponsenew(@RequestParam Map<String,String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData = apiResponseService.doopMeApiResponsenew(request);
		System.out.println(returnData);
		
	}
	
	@RequestMapping(value = "/giblDeductBalanceResponse", method = RequestMethod.POST)
	public Map<String, Object> giblDeductBalanceResponse(@RequestBody Map<String,String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData = apiResponseService.giblDeductBalanceResponse(request);
		System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/giblUpdateStatusResponse", method = RequestMethod.POST)
	public Map<String, Object> giblUpdateStatusResponse(@RequestBody Map<String,String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData = apiResponseService.giblUpdateStatusResponse(request);
		System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/microatmrequest", method = RequestMethod.POST)
	public Map<String, Object> microatmrequest(@RequestBody Map<String,String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		logger_log.warn("microatmrequest:::::::::::::::::::::"+request);
		returnData = apiResponseService.microatmrequest(request);
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/microatmresponse", method = RequestMethod.POST)
	public Map<String, Object> microatmresponse(@RequestBody Map<String,String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		logger_log.warn("microatmresponse:::::::::::::::::::::"+request);
		returnData = apiResponseService.microatmresponse(request);
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/yesaepsReturn", method = RequestMethod.POST)
	public Map<String, Object> yesaepsReturn(@RequestBody LinkedTreeMap<String, Object> request) {
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> returnData = new HashMap<String, Object>();
		
		logger_log.warn("yesaepsReturn:::::"+request);
		//String op="{HEADER={AID=ENCODG1001, OP=AEPS, TXN_AMOUNT=101, ST=WITHDRAWAL},DATA={CN=9051297971, ORDER_ID=AW157284387493235,TXNDETAILS=[{ORDER_ID=AW157284387493235, txnStatus=SUCCESS, TXN_AMOUNT=101.0, APM_CHARGE=0.0, AGENT_CHARGE=0.0,CUSTOMER_CHARGE=0.0, RESP_MSG=Transaction Successful (300), BANK_NAME=Corporation Bank, AadharNumber=XXXXXXXXXXXX, RES_CODE=00,RRN=930810222907}]}}";
		try {
			returnData = apiResponseService.yesbankaepsApiResponse(request);
			
		} catch (Exception e) {
			logger_log.error("skyReturn::::::::::::::::::::::::::" + e);
		}
		return returnData;
	}
	
	@RequestMapping(value = "/modempending", method = RequestMethod.GET)
	public Map<String, Object> modempending() {
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("status", "0");
		returnData.put("message", "TESTING");
		return returnData;
	}
	
	@RequestMapping(value = "/cellmoneyresponse", method = RequestMethod.POST)
	public void cellmoneyresponse(@RequestParam Map<String,String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData = apiResponseService.cellmoneyresponse(request);
		System.out.println(returnData);
		
	}
	
	@RequestMapping(value = "/instantpayresponse", method = RequestMethod.GET)
	public void instantpayresponse(@RequestParam Map<String,String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData = apiResponseService.instantpayresponse(request);
		System.out.println(returnData);
		
	}
	
	
	@RequestMapping(value = "/doopmeApiResponse", method = RequestMethod.GET)
	public void doopmeApiResponse(@RequestParam Map<String,String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData = apiResponseService.doopMeApiResponse(request);
		System.out.println(returnData);
		
	}
	
	@RequestMapping(value = "/yesbankaepsApiResponse", method = RequestMethod.POST)
	public void yesbankaepsApiResponse(@RequestParam Map<String,String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
	//	returnData = apiResponseService.yesbankaepsApiResponse(request);
		
	}
	
	@RequestMapping(value = "/crowdfinchresponse", method = RequestMethod.GET)
	public void CrowdfinchApiResponse(@RequestParam Map<String,String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData = apiResponseService.CrowdfinchApiResponse(request);
		System.out.println(returnData);
		
	}
	
	@RequestMapping(value = "/digitalIndiaApi")
	public Map<String, Object> digitalIndiaApi(@RequestParam Map<String,String> request){		;
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData = retailerService.checkApiUser(request);
		if(returnData.get("status").equals("1")){
			Map<String,String> inputData = new HashMap<>();
			User userDetails = (User) returnData.get("userDetails");
			inputData.put("source", "API");
			inputData.put("operator", request.get("opCode"));
			inputData.put("mobile", request.get("mobile"));
			inputData.put("amount", request.get("amount"));
			returnData = retailerService.webRecharge(inputData, userDetails);
			if(returnData.get("status").equals("1")){
				returnData.put("status", "SUCCESS");
			} else {
				returnData.put("status", "FAILED");
			}
		} else {			
			returnData.put("status", "FAILED");
		}
		return returnData;
	}
	
	
	
}
