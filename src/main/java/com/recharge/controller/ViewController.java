package com.recharge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.recharge.businessdao.ApiParserService;
import com.recharge.businessdao.ApiResponseService;
import com.recharge.businessdao.GlobalCommandCenter;
import com.recharge.instantpay.InstantPayService;
import com.recharge.model.AEPSLog;
import com.recharge.model.AEPSUserMap;
import com.recharge.model.PaymonkResponse;
import com.recharge.model.User;
import com.recharge.servicedao.AEPSLogDao;
import com.recharge.servicedao.AEPSUserMapDao;
import com.recharge.servicedao.PaymonkResponseDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.URLTest;



@Controller
public class ViewController {	
	@Autowired
	HttpSession session;
	@Autowired
	GlobalCommandCenter globalCommandCenter;
	@Autowired AEPSUserMapDao aepsuserdao;
	@Autowired ApiParserService apiParserService;
	@Autowired PaymonkResponseDao paymonkresponsedao;
	@Autowired ApiResponseService apiResponseService;
	@Autowired AEPSLogDao aepslogdao;
	@Autowired  InstantPayService instantpay;
	
	private static final Logger logger_log = Logger.getLogger(ViewController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root() {
		return "home/index";
	}
	@RequestMapping(value = "/index1", method = RequestMethod.GET)
	public String index1() {
		return "index";
	}
	
	
	@RequestMapping("/BBPS")
	public ModelAndView instantAEPS(){
		ModelAndView mv = new ModelAndView("AEPSNEW");
		String encodedString;
		try{
		if (session.getAttribute("UserDetails")!= null) {
			User userDetails = (User) session.getAttribute("UserDetails");
			encodedString=instantpay.instantPayAEPS(userDetails);
			if(!encodedString.equalsIgnoreCase("NA")){
			session.setAttribute("encodedString",encodedString);	
			}else{
			mv = new ModelAndView("retailer");	
			}
		} 
		
		}catch (Exception e) {
			logger_log.error("AEPSNEW ::::::::::"+e);
		}
		return mv;
		
	}
	@RequestMapping(value = "/DMROPEN", method = RequestMethod.GET)
	public String DMROPEN() {
		if (session.getAttribute("UserDetails") == null) {
			session.setAttribute("msg", "Session Expire! Please Login Again.");
			return "index";
		} else {
		return "DMROPEN";
		}
	}
	
	@RequestMapping(value = "/aepspage", method = RequestMethod.GET)
	public String aepspage(Model model) {
		if (session.getAttribute("UserDetails") == null) {
			return "index";
		} else {
			User user = (User) session.getAttribute("UserDetails");
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			if(list.size()==0){
				model.addAttribute("msg","You are not YesBank registered");	
				return "retailer";
			}else{
				return "aeps2";
			}
		}
		
	}
	
	/*===================================================*/
	@RequestMapping(value = "/PayU", method = RequestMethod.GET)
	public String PayU() {
		return "PayU";
	}
	
	
	@RequestMapping(value = "/aepsnew", method = RequestMethod.GET)
	public String aepsfing() {
		return "aepsfing";
	}
	
	@RequestMapping(value = "/aepseasy", method = RequestMethod.GET)
	public String aepseasy() {
		return "aepseasy";
	}
	
	
	@RequestMapping(value = "/aepsaadharpay", method = RequestMethod.GET)
	public String aepsaadharpay() {
		return "aepsaadharpay";
	}
	
	
	@RequestMapping(value = "/payesponse", method = RequestMethod.POST)
	public String paytmresponse(@RequestParam Map<String,String> request,Model model) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		logger_log.warn("paytmresponse:::::::::::::::::::::::"+request);
		if(request.get("status").equalsIgnoreCase("success")) {
			returnData = apiResponseService.paytmresponse(request);
			model.addAttribute("PayTmreceipt",request);
			/*
			 * if(request.get("RESPCODE").equalsIgnoreCase("01")) { String
			 * ORDERID=request.get("ORDERID"); }
			 */
			return "PaytmReceipt";
		}
		return "retailer";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "home/index";
	}
	
	@RequestMapping(value = "/home2", method = RequestMethod.GET)
	public String home2() {
		return "index";
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about() {
		return "about";
	}
	
	@RequestMapping(value = "/SBIPay", method = RequestMethod.GET)
	public String eZPay() {
		return "eZPay";
	}
	
	@RequestMapping(value = "/PackageManagement", method = RequestMethod.GET)
	public String PackageManagement() {
		return "PackageManagement";
	}
	
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String report() {
		return "report";
	}
	
	
	@RequestMapping(value = "services", method = RequestMethod.GET)
	public String services() {
		return "services";
	}
	
	@RequestMapping(value = "/aadhaar-services", method = RequestMethod.GET)
	public String aadhaarservices() {
		return "home/aadhaar-services";
	}
	
	@RequestMapping(value = "/AepsForm", method = RequestMethod.GET)
	public String AepsForm() {
		return "AepsForm";
	}
	
	
	
	@RequestMapping(value = "/IMPS2", method = RequestMethod.GET)
	public String IMPS2() {
		return "IMPS2";
	}
	
	@RequestMapping(value = "/IMPSNEW", method = RequestMethod.GET)
	public String IMPSNEW() {
		return "IMPSNEW";
	}  
	@RequestMapping(value = "/DMRNEW", method = RequestMethod.GET)
	public String DMRNEW() {
		return "DMRNEW";
	} 
	
	
	@RequestMapping(value = "/ministatement", method = RequestMethod.GET)
	public String ministatment() {
		return "ministatement";
	}
	
	@RequestMapping(value = "/bussearch", method = RequestMethod.GET)
	public String bussearch() {
		return "bus/bussearch";
	}
	
	@RequestMapping(value = "/aeps", method = RequestMethod.GET)
	public String aeps() {
		return "home/aeps";
	}
	
	@RequestMapping(value = "/balance-enquiry", method = RequestMethod.GET)
	public String balanceenquiry() {
		return "home/balance-enquiry";
	}
	
	@RequestMapping(value = "/bus-booking", method = RequestMethod.GET)
	public String busbooking() {
		return "home/bus-booking";
	}
	
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact() {
		return "contact";
	}
	

	
	@RequestMapping(value = "/GET", method = RequestMethod.GET)
	public String GET() {
		return "home/GET";
	}

	
	@RequestMapping(value = "/privacy", method = RequestMethod.GET)
	public String privacy() {
		return "home/privacy";
	}
	
	
	
	
	@RequestMapping(value = "/refund", method = RequestMethod.GET)
	public String refund() {
		return "home/refund";
	}
	
	@RequestMapping(value = "/terms", method = RequestMethod.GET)
	public String terms() {
		return "home/terms";
	}
	
	
	/*===================================================*/
	

	@RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
	public String adminPanel() {
		return "admin";
	}

	@RequestMapping(value = "/retailerPanel", method = RequestMethod.GET)
	public String retailerPanel() {
		return "retailer";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/forgetpassword", method = RequestMethod.GET)
	public String forgetpassword() {
		return "forgetpassword";
	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home() {

		if (session.getAttribute("UserDetails") == null) {
			session.setAttribute("msg", "Session Expire! Please Login Again.");
			return "index";
		} else {
			try {
				User userDetails = (User) session.getAttribute("UserDetails");
				User user = globalCommandCenter.getUserByUserId(userDetails.getUserId());
				if (user != null) {
					userDetails.setPassword(null);
					userDetails.setTranPin(null);
					userDetails.setDelFlag(null);
					userDetails.setStatus(null);
					session.setAttribute("UserDetails", user);
					if (userDetails.getRoleId() == 1 || userDetails.getRoleId() == 2||userDetails.getRoleId() == 3 || userDetails.getRoleId() == 4) {
						return "admin";
					} else if (
							 userDetails.getRoleId() == 5) {
						return "retailer";
					} else if (userDetails.getRoleId() == 100){
						return "apiUser";
					}else {
						session.invalidate();
						session.setAttribute("msg", "Session Expire! Please Login Again.");
						return "index";
					}
				} else {
					session.invalidate();
					session.setAttribute("msg", "Session Expire! Please Login Again.");
					return "index";
				}
			} catch (Exception e) {
				session.invalidate();
				session.setAttribute("msg", "Technical Error! Please Contact Admin.");
				return "index";

			}
		}

	}
	
	@RequestMapping(value = "/Aeps", method = RequestMethod.GET)
	public String payMonkAeps(Model model) {
		if (session.getAttribute("UserDetails") == null) {
			return "index";
		} else {
			String response=URLTest.checkServer();
			if(response.equalsIgnoreCase("ok")){
			User user = (User) session.getAttribute("UserDetails");
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("username",user.getUserName());
			param.put("api","RBL");
			List<AEPSUserMap> list = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			if(list.size()==0){
				model.addAttribute("msg","You are not RBL registered");	
				return "retailer";
			}else{
				 String mid="5d69cafaa8334373";
				 AEPSUserMap aeps = list.get(0);
				 Long ts=System.currentTimeMillis();
				 String payload = "5d69cafaa8334373|"+aeps.getAgentcode()+"|"+ts;
				 String secret = "3553e5ce34114d6c9014f4c8772b4ca5"; 
				 String agentcode = aeps.getAgentcode();
				 String referenceno = ts.toString();
				 session.setAttribute("timestamp",ts.toString());
				 session.setAttribute("payload",payload);
				 session.setAttribute("agentcode",aeps.getAgentcode());
				 AEPSLog aepsLog = new AEPSLog(referenceno,agentcode,"PENDING", "EDIT","EDIT", GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime(),"00","0","0",0.0);
				 aepslogdao.insertAEPSLog(aepsLog);
				 return "RBL";	
			}
			}else{
				model.addAttribute("msg","server is down please try after some time");	
				return "retailer";
			}
			
			
		}
		
	}
	
	/*@RequestMapping(value = "/yesaepsReturn", method = RequestMethod.POST)
	public String yesaepsReturn(@RequestParam Map<String, Object> request) {
		Map<String, Object> param = new HashMap<String, Object>();
		String username = "";
		boolean flag = false;
		logger_log.warn("yesaepsReturn:::::"+request);
		try {
			username=request.get("userID").toString();
			User userDetails = globalCommandCenter.getUserByUserId(username);
			session.setAttribute("UserDetails",userDetails);
			//logger_log.warn("skyReturn::::::::::::::::::::::::::"+request);
		} catch (Exception e) {
			logger_log.error("skyReturn::::::::::::::::::::::::::" + e);
		}
		return "retailer";
	}*/

	
	@RequestMapping(value = "/aepsReturn", method = RequestMethod.POST)
	public String skyReturn(@RequestParam Map<String, Object> request,Model model) {
		Map<String, Object> param = new HashMap<String, Object>();
		String username = "";
		boolean flag = false;
		logger_log.warn(""+request);
		try {
			
			PaymonkResponse paymonk = apiParserService.paymonkParser(request);
			param.put("referenceNo",paymonk.getReferenceNo());
			List<PaymonkResponse> pay = paymonkresponsedao.getPaymonkResponseByNamedQuery("getpaymonkresponsebyrefId",param);
			if(pay.isEmpty()){
			flag = paymonkresponsedao.insertPaymonkResponse(paymonk);
			if(flag){
			String agentcode = paymonk.getAgentcode();	
			param = new HashMap<String, Object>();
			param.put("agentcode",agentcode);
			param.put("api","RBL");
			List<AEPSUserMap> list = aepsuserdao.getAEPSUserMapByNamedQuery("getUsernamebyagentcode",param);
			if(!list.isEmpty()){
				AEPSUserMap aeps = list.get(0);
				username = aeps.getUsername();
				User userDetails = globalCommandCenter.getUserByUserId(username);
				userDetails.setPassword(null);
				userDetails.setTranPin(null);
				userDetails.setDelFlag(null);
				userDetails.setStatus(null);
				apiResponseService.payaepsresponse(paymonk);
			    session.setAttribute("UserDetails",userDetails);
			    model.addAttribute("RBLTransaction",paymonk);
			}
			}else{
				
			}
			return "RBLReport";
			}else{
				param = new HashMap<String, Object>();
				param.put("agentcode",paymonk.getAgentcode());
				param.put("api","RBL");
				List<AEPSUserMap> list = aepsuserdao.getAEPSUserMapByNamedQuery("getUsernamebyagentcode",param);
				AEPSUserMap aeps = list.get(0);
				username = aeps.getUsername();
				User userDetails = globalCommandCenter.getUserByUserId(username);
				session.setAttribute("UserDetails",userDetails);
				 model.addAttribute("msg","Same reference number transaction again");	
			return "retailer";
			}
			//logger_log.warn("skyReturn::::::::::::::::::::::::::"+request);
		} catch (Exception e) {
			logger_log.error("skyReturn::::::::::::::::::::::::::" + e);
		}
		return "retailer";
	}

	@RequestMapping(value = "/transReport", method = RequestMethod.GET)
	public String transReport() {
		return "TransactionReport";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {

		session.invalidate();
		return "login";
	}
}
