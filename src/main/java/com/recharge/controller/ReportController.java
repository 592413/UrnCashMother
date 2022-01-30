package com.recharge.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.recharge.businessdao.GlobalCommandCenter;
import com.recharge.businessdao.ReportService;
import com.recharge.customModel.APPReport;
import com.recharge.customModel.BalanceTransfer;
import com.recharge.customModel.EarningSurchargeReport;
import com.recharge.customModel.P2ATranferReport;
import com.recharge.customModel.RechargeReport;
import com.recharge.customModel.TransactionReport;
import com.recharge.customModel.UserWalletReport;
import com.recharge.model.Ezpayapplicationform;
import com.recharge.model.MicroAtmResponse;
import com.recharge.model.NSDLPan;
import com.recharge.model.User;
import com.recharge.utill.UtilityClass;
import com.recharge.yesbankservice.YesBankBusiness;

@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class ReportController {
	private static final Logger logger_log = Logger.getLogger(ReportController.class);
	@Autowired GlobalCommandCenter globalCommandCenter;
	@Autowired ReportService reportService;	
	@Autowired HttpSession session;
	@Autowired YesBankBusiness yesbankservice;
	
	@RequestMapping(value = "/getTransactionReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getTransactionReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				List<TransactionReport> list = reportService.getTransactionReport(request, userDetails);		
				if(!list.isEmpty()) {
					returnData.put("report", list);
					returnData.put("status", "1");					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Records Found!");
				}
			}
		} catch (Exception e) {
			logger_log.error("getTransactionReport  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	


	@RequestMapping(value = "/getRechargeReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getRechargeReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {				
				User userDetails = (User) session.getAttribute("UserDetails");
				List<RechargeReport> list  = reportService.getRechargeReport(request, userDetails);
				if(!list.isEmpty()) {
					returnData.put("report", list);
					returnData.put("status", "1");					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Records Found!");
				}
			}
		} catch (Exception e) {
			logger_log.error("getRechargeReport  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	@RequestMapping(value = "/getMicroATMReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getMicroATMReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData =  yesbankservice.getMicroATMReport(request, userDetails);
				
			}
		} catch (Exception e) {
			logger_log.error("getMicroATMReport:::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	@RequestMapping(value = "/getAPPReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getAPPReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {				
				User userDetails = (User) session.getAttribute("UserDetails");
				List<APPReport> list  = reportService.getAPPReport(request, userDetails);
				if(!list.isEmpty()) {
					returnData.put("report", list);
					returnData.put("status", "1");					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Records Found!");
				}
			}
		} catch (Exception e) {
			logger_log.error("getRechargeReport  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	@RequestMapping(value = "/getWALLETReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getWALLETReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {				
				User userDetails = (User) session.getAttribute("UserDetails");
				List<UserWalletReport> list  = reportService.getWALLETReport(request, userDetails);
				if(!list.isEmpty()) {
					returnData.put("report", list);
					returnData.put("status", "1");					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Records Found!");
				}
			}
		} catch (Exception e) {
			logger_log.error("getWALLETReport  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	@RequestMapping(value = "/getTransactionReportDetails", method = RequestMethod.POST)
	public Map<String, Object> getTransactionReportDetails(){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {				
				User userDetails = (User) session.getAttribute("UserDetails");
				List<RechargeReport> list  = reportService.getLatestTransactionReport(userDetails);
				//System.out.println("list="+list);
				if(!list.isEmpty()) {
					returnData.put("rechargeReport", list);
					returnData.put("status", "1");					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Records Found!");
				}
			}
		} catch (Exception e) {
			logger_log.error("getRechargeReport  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	
	@RequestMapping(value = "/viewBalRevReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> viewBalRevReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				List<TransactionReport> list = reportService.viewBalRevReport(request,userDetails);
				if(!list.isEmpty()) {
					returnData.put("report", list);
					returnData.put("status", "1");					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Records Found!");
				}
			}
		} catch (Exception e) {
			logger_log.error("viewBalRevReport  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	@RequestMapping(value = "/viewPurchaseReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> viewPurchaseReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				List<BalanceTransfer> list = reportService.viewPurchaseReport(request,userDetails);
				if(!list.isEmpty()) {
					returnData.put("PurchaseReport", list);
					returnData.put("status", "1");					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Records Found!");
				}
			}
		} catch (Exception e) {
			logger_log.error("viewPurchaseReport  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	@RequestMapping(value = "/viewBalanceTransferReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> viewBalanceTransferReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				List<BalanceTransfer> list = reportService.viewBalanceTransferReport(request,userDetails);
				if(!list.isEmpty()) {
					returnData.put("BalanceTransfer", list);
					returnData.put("status", "1");					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Records Found!");
				}
			}
		} catch (Exception e) {
			logger_log.error("viewBalanceTransferReport  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	@RequestMapping(value = "/viewPendingUtilityRequest", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> viewPendingUtilityRequest(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = reportService.viewUtilityReport(request,userDetails,"PENDING");				
			}
		} catch (Exception e) {
			logger_log.error("viewPendingUtilityRequest  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	
	@RequestMapping(value = "/viewUtilityReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> viewUtilityReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = reportService.viewUtilityReport(request,userDetails,"ALL");				
			}
		} catch (Exception e) {
			logger_log.error("viewUtilityReport  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	@RequestMapping(value = "/viewPendingInsuranceRequest", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> viewPendingInsuranceRequest(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = reportService.viewInsuranceReport(request,userDetails,"PENDING");				
			}
		} catch (Exception e) {
			logger_log.error("viewPendingUtilityRequest  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	@RequestMapping(value = "/viewInsuranceReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> viewInsuranceReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = reportService.viewInsuranceReport(request,userDetails,"ALL");				
			}
		} catch (Exception e) {
			logger_log.error("viewInsuranceReport  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	@RequestMapping(value = "/getRefundReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getRefundReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				List<TransactionReport> list = reportService.getRefundReport(request, userDetails);		
				if(!list.isEmpty()) {
					returnData.put("report", list);
					returnData.put("status", "1");					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Records Found!");
				}
			}
		} catch (Exception e) {
			logger_log.error("getTransactionReport  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	
	
	@RequestMapping(value = "/getEarningReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getEarningReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				List<EarningSurchargeReport> list = reportService.getEarningReport(request, userDetails);		
				if(!list.isEmpty()) {
					returnData.put("report", list);
					returnData.put("status", "1");					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Records Found!");
				}
			}
		} catch (Exception e) {
			logger_log.error("getEarningReport  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	@RequestMapping(value = "/getnsdlReport", method = RequestMethod.POST, headers = "Content-type=application/json")
	public Map<String, Object> getnsdlReport(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				List<NSDLPan> list = reportService.getnsdlReport(request, userDetails);		
				if(!list.isEmpty()) {
					returnData.put("report", list);
					returnData.put("status", "1");					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Records Found!");
				}
			}
		} catch (Exception e) {
			logger_log.error("getnsdlReport  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	@RequestMapping(value = "/fetchnsdlpan", method = RequestMethod.POST)
	public Map<String, Object> fetchnsdlpan(){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				List<NSDLPan> list = reportService.fetchnsdlpan(userDetails);		
				if(!list.isEmpty()) {
					returnData.put("report", list);
					returnData.put("status", "1");					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Records Found!");
				}
			}
		} catch (Exception e) {
			logger_log.error("fetchnsdlpan  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	@RequestMapping(value = "/updateNSDLReport", method = RequestMethod.POST)
	public Map<String, Object> updateNSDLReport(@RequestParam(value = "NSDLReport") String NSDLReport,@RequestParam(value = "ackslip") MultipartFile ackslip){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {
			if(session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				System.out.println(ackslip.getBytes().length/1000);
				if(ackslip.getBytes().length/1000<=2058){
				
				User userDetails = (User) session.getAttribute("UserDetails");
				JSONObject NSDLReports = new JSONObject(NSDLReport);
				Map<String, String> inputData = new HashMap<String, String>();
				inputData = UtilityClass.toMap(NSDLReports);
				String type=ackslip.getContentType();
				String ty[]=type.split("/");	
				String fileName = inputData.get("invoiceno")+"ack."+ty[1];
				InputStream in =ackslip.getInputStream();
				 Path pathToFile = Paths.get(fileName);
				 java.nio.file.Path path = Paths.get("/usr/local/tomcat7/webapps/doc/" + fileName);
				 Files.deleteIfExists(path);
				 Files.copy(in, path);
				returnData = reportService.updateNSDLReport(inputData,userDetails,ackslip.getBytes(),fileName);		
			}else{
				returnData.put("message", "File should not be greater than 2 MB.");
				returnData.put("status", "0");
			}
			}
		} catch (Exception e) {
			logger_log.error("updateNSDLReport  :::::::::: "+e);
			returnData = new HashMap<String, Object>();	
			returnData.put("nextPage", "home");
			returnData.put("message", "Exception! Please Login Again or Contact Admin");			
			returnData.put("status", "0");
		} 
		return returnData;	
	}
	
	@RequestMapping(value = "/updateNSDLReportFailed", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> updateNSDLReportFailed(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = reportService.updateNSDLReportFailed(request,userDetails);
			}
		}catch (Exception e) {
			logger_log.error("updateNSDLReportFailed :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/updateNSDLHoldReport", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> updateNSDLHoldReport(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = reportService.updateNSDLHoldReport(request,userDetails);
			}
		}catch (Exception e) {
			logger_log.error("updateNSDLHoldReport :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/viewuserbankdt", method = RequestMethod.POST)
	public Map<String, Object> viewuserbankdt(){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = reportService.viewuserbankdt(userDetails);
			}
		}catch (Exception e) {
			logger_log.error("viewuserbankdt :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
}
