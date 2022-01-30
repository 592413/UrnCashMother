package com.recharge.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.recharge.businessdao.AndroidService;
import com.recharge.model.User;
import com.recharge.yesbankservice.YesBankBusiness;

@RestController

@RequestMapping(value = "rest")
public class AndroidController {
	private static final Logger logger_log = Logger.getLogger(AndroidController.class);
	@Autowired AndroidService androidService;
	@Autowired YesBankBusiness yesbankservice;
	
	@RequestMapping(value = "tokenizer", method = RequestMethod.GET)
	public Map<String, Object> tokenizer(@RequestParam Map<String,String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try{
			if(request.get("tokenId") == null){
				returnData.put("status", "0");
				returnData.put("message", "Invalid Token!");
			} else {
				returnData = androidService.tokenizer(request.get("tokenId"));
			}
		} catch(Exception e){
			logger_log.warn("tokenizer ::::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/getSettlementBalanceAndroid", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getSettlementBalanceAndroid(@RequestBody String request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = androidService.getSettlementBalanceAndroid(request);
		} catch (Exception e) {
			logger_log.error("remitterRegistration :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	@RequestMapping(value = "/settlementReqAndroid", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> settlementReqAndroid(@RequestBody  Map<String,Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = androidService.settlementReqAndroid(request);
		} catch (Exception e) {
			logger_log.error("remitterRegistration :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/addRblBankAndroid", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> addRblBankAndroid(@RequestBody  Map<String,Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = androidService.addRblBankAndroid(request);
		} catch (Exception e) {
			logger_log.error("remitterRegistration :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/viewRblBankAndroid", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> viewRblBankAndroid(@RequestBody  Map<String,Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = androidService.viewRblBankAndroid(request);
		} catch (Exception e) {
			logger_log.error("viewRblBankAndroid :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/getSettlementReportAndroid", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getSettlementReportAndroid(@RequestBody  Map<String,Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = androidService.getSettlementReportAndroid(request);
		} catch (Exception e) {
			logger_log.error("remitterRegistration :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	//aeps
	
	@RequestMapping(value = "/aepsAndroid", method = RequestMethod.POST, headers = "content-type=application/json")
	public  Map<String,Object> aepsAndroid(@RequestBody Map<String, Object> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
				returnData = androidService.aepsAndroid(request);
			} catch (Exception e) {
				logger_log.error("impsReport :::::::::: "+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			System.out.println(returnData);
			return returnData;
		}
	
	
	
	
	@RequestMapping(value = "/aepsReportAndroid", method = RequestMethod.POST, headers = "content-type=application/json")
	public  Map<String,Object> aepsReportAndroid(@RequestBody Map<String, Object> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
				returnData = androidService.aepsReportAndroid(request);
			} catch (Exception e) {
				logger_log.error("impsReport :::::::::: "+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			System.out.println(returnData);
			return returnData;
		}
	
	
	@RequestMapping(value = "getBankDetailAEPSNew", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getAEPSBankDetail(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			System.out.println(request);
			returnData = androidService.getAEPSBankDetail(request);
		}catch (Exception e) {
			logger_log.error("getAEPSBankDetail::::::::::"+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}	
		return returnData;
	}
	
	//aeps
	
	@RequestMapping(value = "/ImpsReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> appgetImpsReport(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.appgetImpsReport(request);
		}catch (Exception e) {
			logger_log.error("appgetImpsReport :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
/*	@RequestMapping(value = "/impsReport", method = RequestMethod.POST, headers = "content-type=application/json")
	public  Map<String,Object> impsReport(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
				returnData = androidService.impsReport(request);
			} catch (Exception e) {
				logger_log.error("impsReport :::::::::: "+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			System.out.println(returnData);
			return returnData;
		}*/
/*	
	@RequestMapping(value = "/fetchbank", method = RequestMethod.POST, headers = "content-type=application/json")
	public  Map<String,Object>   fetchbank(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
				returnData = androidService.fetchbank(request);
			} catch (Exception e) {
				logger_log.error("fetchbank :::::::::: "+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			System.out.println(returnData);
			return returnData;
		}*/
	
	
	@RequestMapping(value = "userLogin", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> userLogin(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try{
			HttpServletRequest httprequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			String ip = httprequest.getRemoteAddr();
			returnData = androidService.androidLogin(request, ip);
		} catch(Exception e){
			logger_log.warn("userLogin ::::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}	
		return returnData;
	}
	
	@RequestMapping(value = "editMyProfile", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> editProfile(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			System.out.println(request);
			returnData = androidService.editProfile(request);
		}catch (Exception e) {
			logger_log.error("editmyProfile :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}	
		return returnData;
	}
	
	@RequestMapping(value = "changePassword", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> changePassword(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.changePassword(request);
		}catch (Exception e) {
			logger_log.error("changePassword :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	
	@RequestMapping(value = "myDiscount", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> myDiscount(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.myDiscount(request);
		}catch (Exception e) {
			logger_log.error("myDiscount :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "balanceRequest", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> balanceRequest(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.balanceRequest(request);
		}catch (Exception e) {
			logger_log.error("balanceRequest :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "rechargeReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> rechargeReport(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.rechargeReport(request);
		}catch (Exception e) {
			logger_log.error("rechargeReport :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	@RequestMapping(value = "transactionReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> transactionReport(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.transactionReport(request);
		}catch (Exception e) {
			logger_log.error("rechargeReport :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "purchaseReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> purchaseReport(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.purchaseReport(request);
		}catch (Exception e) {
			logger_log.error("purchaseReport :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	

	@RequestMapping(value = "forgetPassword", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> forgetPassword(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.forgetPassword(request);
		}catch (Exception e) {
			logger_log.error("forgetPassword :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "sendRechargeRequst", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> sendRechargeRequst(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {			
			HttpServletRequest httprequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			String ip = httprequest.getRemoteAddr();
			returnData = androidService.sendRechargeRequst(request, ip);
		}catch (Exception e) {
			logger_log.error("sendRechargeRequst :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "addComplain", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> addComplain(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {			
			returnData = androidService.addComplain(request);
		}catch (Exception e) {
			logger_log.error("sendRechargeRequst :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	@RequestMapping(value = "viewComplain", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> viewComplain(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {			
			returnData = androidService.viewComplain(request);
		}catch (Exception e) {
			logger_log.error("sendRechargeRequst :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "getOperator", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getOperator(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {			
			returnData = androidService.getOperator(request);
		}catch (Exception e) {
			logger_log.error("getOperator :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "addUser", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> addUser(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {			
			returnData = androidService.addUser(request);
		}catch (Exception e) {
			logger_log.error("getOperator :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "viewUser", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> viewUser(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {			
			returnData = androidService.viewUser(request);
		}catch (Exception e) {
			logger_log.error("viewUser :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "addBalance", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> addBalance(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {			
			returnData = androidService.addBalance(request);
		}catch (Exception e) {
			logger_log.error("viewUser :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "viewBalanceTransfer", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> viewBalanceTransfer(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {			
			returnData = androidService.viewBalanceTransfer(request);
		}catch (Exception e) {
			logger_log.error("viewUser :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "utilityBillPayment", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> utilityBillPayment(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {			
			HttpServletRequest httprequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			String ip = httprequest.getRemoteAddr();
			returnData = androidService.utilityBillPayment(request, ip);
		}catch (Exception e) {
			logger_log.error("utilityBillPayment :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "utilityReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> utilityReport(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.utilityReport(request);
		}catch (Exception e) {
			logger_log.error("rechargeReport :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "doopmecheckRemitter", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> doopmecheckRemitter(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.doopmecheckRemitter(request);
		}catch (Exception e) {
			logger_log.error("doopmecheckRemitter :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "doopmeRgisterRemitter", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> doopmeRgisterRemitter(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.doopmeRgisterRemitter(request);
		}catch (Exception e) {
			logger_log.error("doopmeRgisterRemitter :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "doopmeNewBeneficiary", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> doopmeNewBeneficiary(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.doopmeNewBeneficiary(request);
		}catch (Exception e) {
			logger_log.error("doopmeNewBeneficiary :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	@RequestMapping(value = "doopmeVerifyBeneficiary", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> doopmeVerifyBeneficiary(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.doopmeVerifyBeneficiary(request);
		}catch (Exception e) {
			logger_log.error("doopmeNewBeneficiary :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	@RequestMapping(value = "doopmeViewBeneficiary", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> doopmeViewBeneficiary(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.doopmeViewBeneficiary(request);
		}catch (Exception e) {
			logger_log.error("doopmeViewBeneficiary :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "/fetchbank", method = RequestMethod.POST, headers = "content-type=application/json")
	public  Map<String,Object>   fetchbank(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
				returnData = androidService.fetchbank(request);
			} catch (Exception e) {
				logger_log.error("fetchbank :::::::::: "+e);
				returnData.put("message", "Exception! Please try again");		
				returnData.put("status", "0");
			}
			System.out.println(returnData);
			return returnData;
		}
	
	@RequestMapping(value = "doopmeMoneyTransfer", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> doopmeMoneyTransfer(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.doopmeMoneyTransfer(request);
		}catch (Exception e) {
			logger_log.error("doopmeMoneyTransfer :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "agencyrequest", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> agencyrequest(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.agencyrequest(request);
		}catch (Exception e) {
			logger_log.error("agencyrequest :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "Couponrequest", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> Couponrequest(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.Couponrequest(request);
		}catch (Exception e) {
			logger_log.error("Couponrequest :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	@RequestMapping(value = "advancedSearchUser", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> advancedSearchUser(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.advancedSearchUser(request);
		}catch (Exception e) {
			logger_log.error("advancedSearchUser :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "advancedCustomerNo", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> advancedCustomerNo(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.advancedCustomerNo(request);
		}catch (Exception e) {
			logger_log.error("advancedCustomerNo :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "checkBalance", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> checkBalance(@RequestBody  Map<String,Object> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try{
			
			returnData = androidService.checkBalance(request);
		} catch(Exception e){
			logger_log.warn("checkBalance ::::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}	
		return returnData;
	}
	
	//New ICICI


	@RequestMapping(value = "p2aregisterAndroid", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> p2aregisterAndroid(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.p2aregisterAndroid(request);
		}catch (Exception e) {
			logger_log.error("advancedCustomerNo :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}




	@RequestMapping(value = "p2amoneytransferAndroid", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> p2amoneytransferAndroid(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.p2amoneytransferAndroid(request);
		}catch (Exception e) {
			logger_log.error("advancedCustomerNo :::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}





	@RequestMapping(value = "getp2aReportAndroid", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getp2aReportAndroid(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.getp2aReportAndroid(request);
		}catch (Exception e) {
			logger_log.error("ICICIPaymentAndroid:::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}

	@RequestMapping(value = "checkP2AUser", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> checkP2AUser(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.checkP2AUser(request);
		}catch (Exception e) {
			logger_log.error("ICICIPaymentAndroid:::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	
	//fongerpayaeps
	
	@RequestMapping(value = "encoreaepstransactionAndroid", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> encoreaepstransactionAndroid(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.encoreaepstransactionAndroid(request);
		}catch (Exception e) {
			logger_log.error("encoreaepstransactionAndroid:::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "encoreaadhartransactionAndroid", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> encoreaadhartransactionAndroid(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.encoreaadhartransactionAndroid(request);
		}catch (Exception e) {
			logger_log.error("encoreaadhartransactionAndroid:::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
    // NEW DMR BASED ON ICICI P2A
	
	@RequestMapping(value = "checkuserEncoreNewAndroid", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> checkuserEncoreNewAndroid(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.checkuserEncoreNewAndroid(request);
		}catch (Exception e) {
			logger_log.error("encoreaadhartransactionAndroid:::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "remmiterRegisterAndroid", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> remmiterRegisterAndroid(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.remmiterRegisterAndroid(request);
		}catch (Exception e) {
			logger_log.error("encoreaadhartransactionAndroid:::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	
	@RequestMapping(value = "OtpverifyAndroid", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> OtpverifyAndroid(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.OtpverifyAndroid(request);
		}catch (Exception e) {
			logger_log.error("encoreaadhartransactionAndroid:::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	
	@RequestMapping(value = "addBeneficiaryAndroid", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> addBeneficiaryAndroid(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.addBeneficiaryAndroid(request);
		}catch (Exception e) {
			logger_log.error("encoreaadhartransactionAndroid:::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	
	
	@RequestMapping(value = "deleteBeneficiaryAndroid", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> deleteBeneficiaryAndroid(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.deleteBeneficiaryAndroid(request);
		}catch (Exception e) {
			logger_log.error("encoreaadhartransactionAndroid:::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	
	
	
	@RequestMapping(value = "ValidateBeneficiaryAndroid", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> ValidateBeneficiaryAndroid(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.ValidateBeneficiaryAndroid(request);
		}catch (Exception e) {
			logger_log.error("encoreaadhartransactionAndroid:::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	
	@RequestMapping(value = "ResendOtpAndroid", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> ResendOtpAndroid(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.ResendOtpAndroid(request);
		}catch (Exception e) {
			logger_log.error("encoreaadhartransactionAndroid:::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	
	@RequestMapping(value = "MoneyTransferAndroid", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> MoneyTransferAndroid(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.MoneyTransferAndroid(request);
		}catch (Exception e) {
			logger_log.error("encoreaadhartransactionAndroid:::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@RequestMapping(value = "minitransactionNewAndroid", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> minitransactionNewAndroid(@RequestBody String request){
		Map<String, Object> returnData = new HashMap<String, Object>();			
		try {
			returnData = androidService.minitransactionNewAndroid(request);
		}catch (Exception e) {
			logger_log.error("encoreaadhartransactionAndroid:::::::::: "+e);
			returnData = new HashMap<String, Object>();				
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		}		
		return returnData;
	}
	

	
}
