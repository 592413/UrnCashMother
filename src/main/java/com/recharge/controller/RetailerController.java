package com.recharge.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Deflater;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.recharge.businessdao.RetailerService;
import com.recharge.model.InsuranceRegister;
import com.recharge.model.NSDLPan;
import com.recharge.model.NSDLPanAttachment;
import com.recharge.model.User;
import com.recharge.servicedao.InsuranceRegisterDao;
import com.recharge.servicedao.NSDLPanAttachmentDao;
import com.recharge.servicedao.NSDLpanDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.UtilityClass;

@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class RetailerController {
	private static final Logger logger_log = Logger.getLogger(RetailerController.class);
	@Autowired RetailerService retailerService;	
	@Autowired HttpSession session;
	@Autowired NSDLpanDao NSDLpanDao;
	@Autowired NSDLPanAttachmentDao NSDLPanAttachmentDao;
	@Autowired InsuranceRegisterDao InsuranceRegisterDao;
	
	@RequestMapping(value = "/webRecharge", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> webRecharge(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				HttpServletRequest httprequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
				String ip = httprequest.getRemoteAddr();
				 request.put("ip", ip);
				request.put("source", "WEB");
				returnData = retailerService.webRecharge(request, userDetails);
			}
		} catch (Exception e) {
			//session.invalidate();
			logger_log.error("getUserDetails :::::::::: "+e);			
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/payUWallet", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> payTMWallet(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.payUWallet(request,userDetails);
			}
		} catch (Exception e) {
			//session.invalidate();
			logger_log.error("payUWallet:::::::::: "+e);			
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		
		return returnData;
	}
	
/*	@RequestMapping(value = "/p2amoneytransfer", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> p2amoneytransfer(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.p2amoneytransfer(request, userDetails);
			}
		}catch (Exception e) {
			logger_log.error("updateNews :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}*/
	
	
	@RequestMapping(value = "/getResponseUrl", method = RequestMethod.POST)
	public Map<String, Object> getResponseUrl(){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.getResponseUrl(userDetails);
			}
		} catch (Exception e) {
			//session.invalidate();
			logger_log.error("getResponseUrl :::::::::: "+e);			
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	@RequestMapping(value = "/cardwalletrequest", method = RequestMethod.POST)
	public Map<String, Object> cardwalletrequest(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.customholder(request,userDetails);
			}
		} catch (Exception e) {
			//session.invalidate();
			logger_log.error("getResponseUrl :::::::::: "+e);			
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	@RequestMapping(value = "/mycardlist", method = RequestMethod.POST)
	public Map<String, Object> walletapplication(){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.mycardlist(userDetails);
				
			}
		} catch (Exception e) {
			//session.invalidate();
			logger_log.error("getResponseUrl :::::::::: "+e);			
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	
	@RequestMapping(value = "/updateResponseUrl", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> updateResponseUrl(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.updateResponseUrl(request,userDetails);
			}
		} catch (Exception e) {
			//session.invalidate();
			logger_log.error("getResponseUrl :::::::::: "+e);			
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
		
	}
	
	@RequestMapping(value = "/settlerequest", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> settlerequest(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.settlerequest(request, userDetails);
			}
		}catch (Exception e) {
			logger_log.error("updateNews :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/addRBLBank", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> addRBLBank(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.addRBLBank(request, userDetails);
			}
		}catch (Exception e) {
			logger_log.error("updateNews :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/getComplainDetails", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getComplainDetails(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.getComplainDetails(request,userDetails);
			}
		} catch (Exception e) {
			logger_log.error("getComplainDetails :::::::::: "+e);			
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		return returnData;
	}
	@RequestMapping(value = "/updateRechargeComplains", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> updateRechargeComplains(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.updateRechargeComplains(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("getComplainDetails :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/balanceRequest", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> balanceRequest(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.balanceRequest(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("balanceRequest :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/addComplains", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> addComplains(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.addComplains(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("addComplains :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	
	@RequestMapping(value = "/eBillPayment", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> eBillPayment(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				HttpServletRequest httprequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
				String ip = httprequest.getRemoteAddr();
				request.put("ip", ip);
				request.put("source", "WEB");
				returnData = retailerService.utilityBillPayment(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("addComplains :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
			
	@RequestMapping(value = "/gasBillPayment", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> gasBillPayment(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				HttpServletRequest httprequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
				String ip = httprequest.getRemoteAddr();
				request.put("ip", ip);
				request.put("source", "WEB");
				returnData = retailerService.utilityBillPayment(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("gasBillPayment :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	@RequestMapping(value = "/insuranceBillPayment", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> insuranceBillPayment(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				HttpServletRequest httprequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
				String ip = httprequest.getRemoteAddr();
				request.put("ip", ip);
				request.put("source", "WEB");
				returnData = retailerService.insurancePremiumPayment(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("insuranceBillPayment :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/agencyrequest", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> agencyrequest(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.agencyrequest(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("agencyrequest :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	/*@RequestMapping(value = "/agencystatusrespnse")
	public Map<String, Object> agencystatusrespnse(@RequestParam Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
				logger_log.warn("agencystatusrespnse:::::::::"+request);
		
				returnData = retailerService.agencystatusrespnse(request);
			
		} catch (Exception e) {
			logger_log.error("agencyrequest :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}*/
	
	@RequestMapping(value = "/couponrequest", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> couponrequest(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.couponrequest(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("agencyrequest :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/nsdlPanadd", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> nsdlPanadd(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.nsdlPanadd(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("nsdlPanadd :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/nsdlEdit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> nsdlEdit(@RequestParam(value = "addnsdlEdit") String userEdit,@RequestParam(value = "CustomerImage") MultipartFile CustomerImage,@RequestParam(value = "CustomerSignature") MultipartFile CustomerSignature,@RequestParam(value = "CustomerFormFront") MultipartFile CustomerFormFront,@RequestParam(value = "CustomerFormBack") MultipartFile CustomerFormBack,@RequestParam(value = "ProofOfIdentity") MultipartFile ProofOfIdentity) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				JSONObject resellerDetails = new JSONObject(userEdit);
				Map<String, String> inputData = new HashMap<String, String>();
				inputData = UtilityClass.toMap(resellerDetails);	
				User userDetails = (User) session.getAttribute("UserDetails");
				/*byte[] bytesImage = CustomerImage.getBytes();
				byte[] bytesSignature = CustomerSignature.getBytes();
				byte[] bytesFormFont = CustomerFormFront.getBytes();
				byte[] bytesFormBack = CustomerFormBack.getBytes();*/
				byte[] bytesProofOfIdentity = ProofOfIdentity.getBytes();
			//	returnData = retailerService.nsdlEdit(inputData,bytesImage,bytesSignature,bytesFormFont,bytesFormBack,bytesProofOfIdentity);
			}
		} catch (Exception e) {
			logger_log.error("nsdlEdit :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/updateimage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateimage(@RequestParam(value = "addnsdlEdit") String userEdit,@RequestParam(value = "CustomerImage") MultipartFile CustomerImage) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag=false;
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {/*
				if(CustomerImage.getBytes().length/1000<=2048){
				JSONObject resellerDetails = new JSONObject(userEdit);
				Map<String, String> inputData = new HashMap<String, String>();
				inputData = UtilityClass.toMap(resellerDetails);	
				User userDetails = (User) session.getAttribute("UserDetails");
				NSDLPan list=NSDLpanDao.getNSDLPanById(Integer.parseInt(inputData.get("id")));
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("invoiceno", list.getInvoiceno());
				List<NSDLPanAttachment> lists=NSDLPanAttachmentDao.getNSDLPanAttachmentByNamedQuery("getNSDLAttachment", parameters);
				if(lists.isEmpty()){
					NSDLPanAttachment np=new NSDLPanAttachment();
					np.setBytesImage(CustomerImage.getBytes());
					np.setImagetype(CustomerImage.getContentType());
					np.setUserId(list.getUserId());
					np.setInvoiceno(list.getInvoiceno());
					np.setName(list.getName());

					flag=NSDLPanAttachmentDao.insertNSDLPanAttachment(np);
				}else{
					NSDLPanAttachment np=lists.get(0);
					np.setBytesImage(CustomerImage.getBytes());
					np.setImagetype(CustomerImage.getContentType());
					flag=NSDLPanAttachmentDao.updateNSDLPanAttachment(np);
				}
				if(flag){
					returnData.put("status", "1");
					returnData.put("message", "Doccument Update Successfully");
				}else{
					returnData.put("status", "0");
					returnData.put("message", "Doccument Failed");
				}
				}else{
					returnData.put("message", "File should not be greater than 2 MB.");
					returnData.put("status", "0");
				}
			*/}
		} catch (Exception e) {
			logger_log.error("updateimage :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/updatesignature", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatesignature(@RequestParam(value = "addnsdlEdit") String userEdit,@RequestParam(value = "CustomerSignature") MultipartFile CustomerSignature) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag=false;
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {/*
				if(CustomerSignature.getBytes().length/1000<=2048){
				JSONObject resellerDetails = new JSONObject(userEdit);
				Map<String, String> inputData = new HashMap<String, String>();
				inputData = UtilityClass.toMap(resellerDetails);	
				User userDetails = (User) session.getAttribute("UserDetails");
				NSDLPan list=NSDLpanDao.getNSDLPanById(Integer.parseInt(inputData.get("id")));
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("invoiceno", list.getInvoiceno());
				List<NSDLPanAttachment> lists=NSDLPanAttachmentDao.getNSDLPanAttachmentByNamedQuery("getNSDLAttachment", parameters);
				if(lists.isEmpty()){
					NSDLPanAttachment np=new NSDLPanAttachment();
					np.setBytesSignature(CustomerSignature.getBytes());
					np.setSignaturetype(CustomerSignature.getContentType());
					np.setUserId(list.getUserId());
					np.setInvoiceno(list.getInvoiceno());
					np.setName(list.getName());

					flag=NSDLPanAttachmentDao.insertNSDLPanAttachment(np);
				}else{
					NSDLPanAttachment np=lists.get(0);
					np.setBytesSignature(CustomerSignature.getBytes());
					np.setSignaturetype(CustomerSignature.getContentType());
					flag=NSDLPanAttachmentDao.updateNSDLPanAttachment(np);
				}
				if(flag){
					returnData.put("status", "1");
					returnData.put("message", "Doccument Update Successfully");
				}else{
					returnData.put("status", "0");
					returnData.put("message", "Doccument Failed");
				}
				}else{
					returnData.put("message", "File should not be greater than 2 MB.");
					returnData.put("status", "0");
				}
			*/}
		} catch (Exception e) {
			logger_log.error("updatesignature :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/updateformfront", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateformfront(@RequestParam(value = "addnsdlEdit") String userEdit,@RequestParam(value = "CustomerFormFront") MultipartFile CustomerFormFront) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag=false;
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {/*
				if(CustomerFormFront.getBytes().length/1000<=2048){
				JSONObject resellerDetails = new JSONObject(userEdit);
				Map<String, String> inputData = new HashMap<String, String>();
				inputData = UtilityClass.toMap(resellerDetails);	
				User userDetails = (User) session.getAttribute("UserDetails");
				NSDLPan list=NSDLpanDao.getNSDLPanById(Integer.parseInt(inputData.get("id")));
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("invoiceno", list.getInvoiceno());
				List<NSDLPanAttachment> lists=NSDLPanAttachmentDao.getNSDLPanAttachmentByNamedQuery("getNSDLAttachment", parameters);
				if(lists.isEmpty()){
					NSDLPanAttachment np=new NSDLPanAttachment();
					np.setBytesFormFont(CustomerFormFront.getBytes());
					np.setFormfonttype(CustomerFormFront.getContentType());
					np.setUserId(list.getUserId());
					np.setInvoiceno(list.getInvoiceno());
					np.setName(list.getName());

					flag=NSDLPanAttachmentDao.insertNSDLPanAttachment(np);
				}else{
					NSDLPanAttachment np=lists.get(0);
					np.setBytesFormFont(CustomerFormFront.getBytes());
					np.setFormfonttype(CustomerFormFront.getContentType());
					flag=NSDLPanAttachmentDao.updateNSDLPanAttachment(np);
				}
				if(flag){
					returnData.put("status", "1");
					returnData.put("message", "Doccument Update Successfully");
				}else{
					returnData.put("status", "0");
					returnData.put("message", "Doccument Failed");
				}
				}else{
					returnData.put("message", "File should not be greater than 2 MB.");
					returnData.put("status", "0");
				}
			*/}
		} catch (Exception e) {
			logger_log.error("updateformfront :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	@RequestMapping(value = "/updateformback", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateformback(@RequestParam(value = "addnsdlEdit") String userEdit,@RequestParam(value = "CustomerFormBack") MultipartFile CustomerFormBack) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag=false;
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {/*
				if(CustomerFormBack.getBytes().length/1000<=2048){
				JSONObject resellerDetails = new JSONObject(userEdit);
				Map<String, String> inputData = new HashMap<String, String>();
				inputData = UtilityClass.toMap(resellerDetails);	
				User userDetails = (User) session.getAttribute("UserDetails");
				NSDLPan list=NSDLpanDao.getNSDLPanById(Integer.parseInt(inputData.get("id")));
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("invoiceno", list.getInvoiceno());
				List<NSDLPanAttachment> lists=NSDLPanAttachmentDao.getNSDLPanAttachmentByNamedQuery("getNSDLAttachment", parameters);
				if(lists.isEmpty()){
					NSDLPanAttachment np=new NSDLPanAttachment();
					np.setBytesFormBack(CustomerFormBack.getBytes());
					np.setFormbacktype(CustomerFormBack.getContentType());
					np.setUserId(list.getUserId());
					np.setInvoiceno(list.getInvoiceno());
					np.setName(list.getName());

					flag=NSDLPanAttachmentDao.insertNSDLPanAttachment(np);
				}else{
					NSDLPanAttachment np=lists.get(0);
					np.setBytesFormBack(CustomerFormBack.getBytes());
					np.setFormbacktype(CustomerFormBack.getContentType());
					flag=NSDLPanAttachmentDao.updateNSDLPanAttachment(np);
				}
				if(flag){
					returnData.put("status", "1");
					returnData.put("message", "Doccument Update Successfully");
				}else{
					returnData.put("status", "0");
					returnData.put("message", "Doccument Failed");
				}
				}else{
					returnData.put("message", "File should not be greater than 2 MB.");
					returnData.put("status", "0");
				}
			*/}
		} catch (Exception e) {
			logger_log.error("updateformback :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	public static byte[] compress(byte[] data) throws IOException {  
		 logger_log.debug("Original: " + data.length / 1024 + " Kb");
		   Deflater deflater = new Deflater();  
		   deflater.setInput(data);  
		   ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);   
		   deflater.finish();  
		   byte[] buffer = new byte[300];   
		   while (!deflater.finished()) {  
		    int count = deflater.deflate(buffer); // returns the generated code... index  
		    outputStream.write(buffer, 0, count);   
		   }  
		   outputStream.close();  
		   byte[] output = outputStream.toByteArray();  
		   logger_log.warn("Original: " + data.length / 1024 + " Kb");  
		   logger_log.warn("Compressed: " + output.length / 1024 + " Kb");  
		   return output;  
		  }
	
	@RequestMapping(value = "/updateidproof", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateidproof(@RequestParam(value = "addnsdlEdit") String userEdit,@RequestParam(value = "ProofOfIdentity") MultipartFile ProofOfIdentity) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag=false;
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				System.out.println(":::::::");
				
				System.out.println(":::::::"+ProofOfIdentity.getOriginalFilename());
				if(ProofOfIdentity.getBytes().length/1000<=2048){
					
					//MultipartFile multipartFile = ProofOfIdentity.getFile();
					
				JSONObject resellerDetails = new JSONObject(userEdit);
				Map<String, String> inputData = new HashMap<String, String>();
				inputData = UtilityClass.toMap(resellerDetails);	
				User userDetails = (User) session.getAttribute("UserDetails");
				NSDLPan list=NSDLpanDao.getNSDLPanById(Integer.parseInt(inputData.get("id")));
				list.setStatus("PENDING");
				
				//logger_log.warn(userEdit);
				String type=ProofOfIdentity.getContentType();
				String ty[]=type.split("/");
				logger_log.warn(type);
				String fileName = inputData.get("invoiceno")+"idproof."+ty[1];
				list.setFilename(fileName);
				NSDLpanDao.updateNSDLPan(list);
				logger_log.warn(fileName);
				 InputStream in =ProofOfIdentity.getInputStream();
				 Path pathToFile = Paths.get(fileName);
				// java.nio.file.Path path = Paths.get("C:/Users/Prateeti/git/safegoal/webapp/assets/doc/" + fileName);
				 java.nio.file.Path path = Paths.get("/usr/local/tomcat7/webapps/doc/" + fileName);
				 String cwd = System.getProperty("user.dir");
				 logger_log.warn("Current working directory : " + cwd);
				 logger_log.warn("pathToFile.toAbsolutePath():"+pathToFile.toAbsolutePath());
				 Files.deleteIfExists(path);
				 logger_log.warn("pathToFile.toAbsolutePath():"+pathToFile.toAbsolutePath());
				 Files.copy(in, path);
				 logger_log.warn("pathToFile.toAbsolutePath():"+pathToFile.toAbsolutePath());
				/*Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("invoiceno", list.getInvoiceno());
				List<NSDLPanAttachment> lists=NSDLPanAttachmentDao.getNSDLPanAttachmentByNamedQuery("getNSDLAttachment", parameters);
				if(lists.isEmpty()){
					NSDLPanAttachment np=new NSDLPanAttachment(list.getUserId(), list.getInvoiceno(), list.getName(), data, ProofOfIdentity.getContentType());
					
					np.setBytesProofOfIdentity(data);
					np.setIdprooftype(ProofOfIdentity.getContentType());
					np.setUserId(list.getUserId());
					np.setInvoiceno(list.getInvoiceno());
					np.setName(list.getName());
					flag=NSDLPanAttachmentDao.insertNSDLPanAttachment(np);
				}else{
					NSDLPanAttachment np=lists.get(0);
					np.setBytesProofOfIdentity(ProofOfIdentity.getBytes());
					np.setIdprooftype(ProofOfIdentity.getContentType());
					flag=NSDLPanAttachmentDao.updateNSDLPanAttachment(np);
				}*/
				/*if(flag){
					returnData.put("status", "1");
					returnData.put("message", "Doccument Update Successfully");
				}else{
					returnData.put("status", "0");
					returnData.put("message", "Doccument Failed");
				}*/
				}else{
					returnData.put("message", "File should not be greater than 2 MB.");
					returnData.put("status", "0");
				}
			}
		} catch (Exception e) {
			logger_log.error("updateidproof :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/editnsdlpandt", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> editnsdlpandt(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.editnsdlpandt(request);
			}
		}catch (Exception e) {
			logger_log.error("editnsdlpandt :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/getNSDLASKDetails", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getNSDLASKDetails(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.getNSDLASKDetails(request);
			}
		}catch (Exception e) {
			logger_log.error("getNSDLASKDetails :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/getapplication", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> getapplication(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.getapplication(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("getapplication :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/billFetch", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> billFetch(@RequestBody Map<String, String> request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.billFetch(request, userDetails);
			}
		} catch (Exception e) {
			//session.invalidate();
			logger_log.error("billFetch :::::::::: "+e);			
			returnData.put("message", "Exception! Please try fresh login.");			
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	@RequestMapping(value = "/eBillPaymentPostPaid", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> eBillPaymentPostPaid(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				HttpServletRequest httprequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
				String ip = httprequest.getRemoteAddr();
				request.put("ip", ip);
				request.put("source", "WEB");
				returnData = retailerService.eBillPaymentPostPaid(request, userDetails);
			}
		} catch (Exception e) {
			logger_log.error("eBillPaymentPostPaid :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}
	
	

	@RequestMapping(value = "/insurancegiblBillPayment", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> insurancegiblBillPayment(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				InsuranceRegister InsuranceRegister=InsuranceRegisterDao.getInsuranceRegisterByUserId(userDetails.getUserId());
				if(InsuranceRegister==null){
					returnData.put("status", "0");
					returnData.put("message", "Register First");
				}else{
					String sessionid=GenerateRandomNumber.generatePassword()+GenerateRandomNumber.generateRequestId();
					System.out.println(request);
					//returnData = retailerService.insurancePremiumPayment(request, userDetails);
					String url="https://www.gibl.in/wallet/?cat="+request.get("category")+"&umc=114585&urc="+userDetails.getUserId()+"&ak="+sessionid+"&fname="+InsuranceRegister.getFname()+"&lname="+InsuranceRegister.getLastname()+"&email="+InsuranceRegister.getEmail()+"&phno="+InsuranceRegister.getMobile();
					System.out.println(url);
					returnData.put("url", url);
					returnData.put("status", "1");
				}
				
			}
		} catch (Exception e) {
			logger_log.error("insurancegiblBillPayment :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		//returnData.put("nextPage", "home");
		return returnData;
	}
	
	
	
	@RequestMapping(value = "/insurancegiblBillPaymentRegi", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> insurancegiblBillPaymentRegi(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				InsuranceRegister InsuranceRegister=InsuranceRegisterDao.getInsuranceRegisterByUserId(userDetails.getUserId());
				if(InsuranceRegister==null){
					InsuranceRegister insreg=new InsuranceRegister(userDetails.getUserId(), request.get("firstname"), request.get("lastName"), request.get("mobile"), request.get("email")) ;
					InsuranceRegisterDao.insertInsuranceRegister(insreg);
					returnData.put("status", "1");
					returnData.put("message", "add Successfully.");
				}else{
					returnData.put("message", "Already Updated.");
					returnData.put("status", "0");
				}
				
			}
		} catch (Exception e) {
			logger_log.error("insurancegiblBillPaymentRegi :::::::::: " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		//returnData.put("nextPage", "home");
		return returnData;
	}
	
	/*
	@RequestMapping(value = "/walletRefillRequest", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> walletRefillRequest(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			} else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = retailerService.walletRefillRequest(request, userDetails);
				
			}
		} catch (Exception e) {
			logger_log.error(" inside walletRefillRequest controller " + e);
			returnData.put("message", "Exception! Please try fresh login.");
			returnData.put("status", "0");
		}
		//returnData.put("nextPage", "home");
		System.out.println("API vendor response from calling controller" + returnData);
		return returnData;
	}
	@RequestMapping(value = "/paymentgatewayredirect", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> walletRefillResponse(@RequestBody Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		User userDetails = (User) session.getAttribute("UserDetails");
//		try {
//			if (session.getAttribute("UserDetails") == null) {
//				returnData.put("nextPage", "home");
//				returnData.put("message", "Session Expire! Please Login Again.");
//				returnData.put("status", "0");
//			} else {
//				User userDetails = (User) session.getAttribute("UserDetails");
//				returnData = retailerService.walletRefillResponse(request, userDetails);
//			}
//		} catch (Exception e) {
//			logger_log.error(" inside walletRefillRequest controller " + e);
//			returnData.put("message", "Exception! Please try fresh login.");
//			returnData.put("status", "0");
//		}
		returnData = retailerService.walletRefillResponse(request);
		returnData.put("nextPage", "home");
		return returnData;
	}
	*/
}
