package com.recharge.businessimpl;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.businessdao.AdminService;
import com.recharge.businessdao.CommissionService;
import com.recharge.businessdao.CustomQueryServiceLogic;
import com.recharge.businessdao.GlobalCommandCenter;
import com.recharge.customModel.AEPSUserDetail;
import com.recharge.customModel.DefaultCommission;
import com.recharge.customModel.P2AUserdetail;
import com.recharge.customModel.RechargeReport;
import com.recharge.customModel.ResellerDetails;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.model.AEPSLog;
import com.recharge.model.AEPSUserMap;
import com.recharge.model.Api;
import com.recharge.model.ApiParameters;
import com.recharge.model.AssignedPackage;
import com.recharge.model.Balancetransafer;
import com.recharge.model.Bankdetails;
import com.recharge.model.CouponRequest;
import com.recharge.model.CreateAgent;
import com.recharge.model.DMRCommission;
import com.recharge.model.Defaultcommission;
import com.recharge.model.ECommerceUser;
import com.recharge.model.IRCTCUser;
import com.recharge.model.ImpsTransaction;
import com.recharge.model.Indexx;
import com.recharge.model.NSDLPan;
import com.recharge.model.NSDLPanAttachment;
import com.recharge.model.News;
import com.recharge.model.Operator;
import com.recharge.model.PackageDetail;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.PaymonkResponse;
import com.recharge.model.Reseller;
import com.recharge.model.SMSApiparameters;
import com.recharge.model.SettlementBalance;
import com.recharge.model.SettlementReport;
import com.recharge.model.SmsCredential;
import com.recharge.model.Transactiondetails;
import com.recharge.model.User;
import com.recharge.model.UserBankDetails;
import com.recharge.model.webenquery;
import com.recharge.servicedao.AEPSCommissionDao;
import com.recharge.servicedao.AEPSLogDao;
import com.recharge.servicedao.AEPSUserMapDao;
import com.recharge.servicedao.ApiDao;
import com.recharge.servicedao.ApiParametersDao;
import com.recharge.servicedao.AssignedPackageDAO;
import com.recharge.servicedao.BankdetailsDao;
import com.recharge.servicedao.CouponRequestDao;
import com.recharge.servicedao.CreateAgentDao;
import com.recharge.servicedao.DMRCommissionDao;
import com.recharge.servicedao.ECommerceUserDao;
import com.recharge.servicedao.IRCTCUserDao;
import com.recharge.servicedao.ImpsTransactionService;
import com.recharge.servicedao.IndexDao;
import com.recharge.servicedao.NSDLPanAttachmentDao;
import com.recharge.servicedao.NSDLpanDao;
import com.recharge.servicedao.NewsDao;
import com.recharge.servicedao.OperatorDao;
import com.recharge.servicedao.PackageDetailDao;
import com.recharge.servicedao.PackageWiseChargeCommDao;
import com.recharge.servicedao.PaymonkResponseDao;
import com.recharge.servicedao.ResellerDao;
import com.recharge.servicedao.SMSApiparametersDao;
import com.recharge.servicedao.SettlementBalanceDao;
import com.recharge.servicedao.SettlementReportDao;
import com.recharge.servicedao.WebEnquryDao;
import com.recharge.servicedao.SmsCredentialDao;
import com.recharge.servicedao.UserDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.SMS;
import com.recharge.utill.SMS2;
import com.recharge.utill.UtilityClass;
import com.recharge.servicedao.UserBankDetailsDao;


@Service("adminService")
public class AdminServiceImpl implements AdminService {
	private static final Logger logger_log = Logger.getLogger(AdminService.class);
	@Autowired NewsDao newsDao;
	@Autowired BankdetailsDao bankdetailsDao;
	@Autowired SmsCredentialDao smsCredentialDao;
	@Autowired UserDao userDao;
	@Autowired CustomQueryServiceLogic customQueryServiceLogic;
	@Autowired CommissionService commissionService;
	@Autowired OperatorDao operatorDao;
	@Autowired ResellerDao resellerDao;
	@Autowired WebEnquryDao WebEnquryDao;
	@Autowired PackageDetailDao packagedetailDao;
	@Autowired PackageWiseChargeCommDao packwisechargecomm;
	@Autowired IndexDao indexdao;
	@Autowired ImpsTransactionService impstransactionservice;
	@Autowired CreateAgentDao creatagentdao;
	@Autowired CouponRequestDao coupondao;
	@Autowired ApiDao apiDao;
	@Autowired ApiParametersDao apiParametersdao;
	@Autowired SMSApiparametersDao smsapiparamsdao;
	@Autowired NSDLPanAttachmentDao NSDLPanAttachmentDao;
	@Autowired NSDLpanDao NSDLpanDao;
	@Autowired AEPSUserMapDao aepsuserdao;
	@Autowired AEPSCommissionDao aepscommissiondao;
	@Autowired SettlementBalanceDao settlementbalancedao;
	@Autowired PaymonkResponseDao paymonkresponsedao;
	@Autowired SettlementReportDao settlementreportdao;
	@Autowired AssignedPackageDAO AssignedPackageDAO;
	@Autowired DMRCommissionDao DMRCommissionDao;
	@Autowired AEPSLogDao aepslogdao;
	@Autowired IRCTCUserDao irctcuserdao;
	@Autowired ECommerceUserDao ecommercedao;
	@Autowired UserBankDetailsDao UserBankDetailsDao;
	
	
	@Override
	public Map<String, Object> updateNews(Map<String,String>  request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		News news = new News();
		boolean flag = false;
		try{
			if(UtilityClass.checkParameterIsNull(request)) {
				if(Integer.parseInt(request.get("newsid")) > 0){
					news = new News();
					news.setNews(request.get("news"));
					news.setNewsid(Integer.parseInt(request.get("newsid")));
					news.setWlId(request.get("wlId"));
					flag = newsDao.updateNews(news);
				} else {
					news = new News(request.get("news"),request.get("wlId"));
					flag = newsDao.insertNews(news);
				}
				if(flag){
					returnData.put("message", "News Update Successfully!");
					returnData.put("status", "1");
				} else {
					returnData.put("message", "Failed To update news!");
					returnData.put("status", "0");
				}
			} else {
				returnData.put("message", "Insert News Properly!");
				returnData.put("status", "0");
			}
		} catch (Exception e) {				
			logger_log.error("updateNews ::::::::::: "+e);	
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> aepsSettleToBank(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();	
		boolean flag = false;
		double settlementamountnew = 0.0;
		double settleopbal = 0.0;
		try{
			SettlementReport settle = new SettlementReport(request.get("username"), Double.parseDouble(request.get("settleopbal").toString()),Double.parseDouble(request.get("settleclbal").toString()) ,Double.parseDouble(request.get("amount").toString()) ,request.get("date").toString() ,request.get("time").toString(),request.get("type").toString() ,request.get("newstatus").toString() ,request.get("remarks").toString() );
			settle.setId(Integer.parseInt(request.get("id").toString()));
			flag =settlementreportdao.updateSettlementReport(settle);
			if(request.get("type").toString().equalsIgnoreCase("Settle to Bank")){
			if(request.get("newstatus").toString().equalsIgnoreCase("FAILED")){	
				commissionService.updateBalance(request.get("username"), "BALANCE CREDIT FOR FAILED SETTLEMENT BY ADMIN", "FAILED SETTLEMENT", Double.parseDouble(request.get("amount").toString()),"CREDIT",0);
					}
			returnData.put("status","1");
			returnData.put("message","success");
			returnData.put("nextPage","home");
			}
		}catch (Exception e) {
			logger_log.error("aepsSettleToBank::::::::::::::::::::"+e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> getSettlementBalance(User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		double settlementbalance = 0.0;
		double transactionamount = 0.0;
		double eligiblewallet =0.0;
		double eligiblebank = 0.0;
		
		
		try{
			param = new HashMap<String, Object>();
			param.put("username",userDetails.getUserName());
			List<SettlementBalance> list=settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param);
			if(list.isEmpty()){
				settlementbalance = 0.0;	
			}else{
				SettlementBalance settle = list.get(0);
				settlementbalance = settle.getSettlementbalance();
				eligiblewallet=settlementbalance;
				eligiblebank =settlementbalance;
				
			}
			returnData.put("eligiblebank",eligiblebank);
			returnData.put("transactionamount",transactionamount);
			returnData.put("settlementbalance",settlementbalance);
			returnData.put("eligiblewallet",eligiblewallet);
			returnData.put("status","1");	
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getSettlementBalance::::::::::::::::::::::::"+e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> aepsUseradd(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
	Map<String, Object> param = new HashMap<String, Object>();	
		boolean flag = false;
		try{
			
			//userId
			String username = request.get("userId").toString();
			String api = request.get("apiname").toString();
			String agentode = request.get("agentcode").toString();
			param.put("username",username);
			param.put("api",api);
			List<AEPSUserMap> list = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			if(list.isEmpty()){
			AEPSUserMap aeps = new AEPSUserMap(username,api,agentode);
			flag = aepsuserdao.insertAEPSUserMap(aeps);
			if(flag){
				returnData.put("message","inserted Successfully");	
				returnData.put("status","1");
				returnData.put("nextPage","home");
			}else{
				returnData.put("message","failed");	
				returnData.put("status","0");
				returnData.put("nextPage","home");
			}
			}else{
				
				AEPSUserMap aeps =list.get(0);
				aeps.setAgentcode(request.get("agentcode"));
				
				flag = aepsuserdao.updateAEPSUserMap(aeps);
				if(flag){
				returnData.put("message","user in this api is updated");	
				returnData.put("status","0");
				returnData.put("nextPage","home");
				}else{
					
				}
			}
			
		}catch (Exception e) {
			logger_log.error("aepsUseradd::::::::::::::::::::"+e);
		}
		return returnData;
	}
	

	@Override
	public Map<String, Object> addRailUser(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
	Map<String, Object> param = new HashMap<String, Object>();	
		boolean flag = false;
		try{
			
			//userId
			String username = request.get("userId").toString();
			String agentode = request.get("agentcode").toString();
			param.put("username",username);
			List<IRCTCUser> list = irctcuserdao.getIRCTCUserByNamedQuery("getIRCTCUserbyagentcode", param);
			if(list.isEmpty()){
			IRCTCUser  irctc= new IRCTCUser(username,agentode);
			flag = irctcuserdao.insertIRCTCUser(irctc);
			if(flag){
				returnData.put("message","inserted Successfully");	
				returnData.put("status","1");
				returnData.put("nextPage","home");
			}else{
				returnData.put("message","failed");	
				returnData.put("status","0");
				returnData.put("nextPage","home");
			}
			}else{
				IRCTCUser irctc =list.get(0);
				irctc.setIrctcid(agentode);
				flag = irctcuserdao.insertIRCTCUser(irctc);
				if(flag){
				returnData.put("message","user in this api is updated");	
				returnData.put("status","0");
				returnData.put("nextPage","home");
				}else{
					
				}
			}
			
		}catch (Exception e) {
			logger_log.error("aepsUseradd::::::::::::::::::::"+e);
		}
		return returnData;
	}
	@Override
	public Map<String, Object> addECommerceUser(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
	Map<String, Object> param = new HashMap<String, Object>();	
		boolean flag = false;
		try{
			System.out.println("request::::::::::::::::::"+request);
			//userId
			String username = request.get("userId").toString();
			String agentode = request.get("agentcode").toString();
			param.put("username",username);
			List<ECommerceUser> list = ecommercedao.getECommerceUserByNamedQuery("getECommerceUser", param);
			if(list.isEmpty()){
				ECommerceUser  irctc= new ECommerceUser(username,agentode);
			flag = ecommercedao.insertECommerceUser(irctc);
			if(flag){
				returnData.put("message","inserted Successfully");	
				returnData.put("status","1");
				returnData.put("nextPage","home");
			}else{
				returnData.put("message","failed");	
				returnData.put("status","0");
				returnData.put("nextPage","home");
			}
			}else{
				ECommerceUser irctc =list.get(0);
				irctc.setEcommerceid(agentode);
				flag = ecommercedao.updateECommerceUser(irctc);
				if(flag){
				returnData.put("message","user in this api is updated");	
				returnData.put("status","0");
				returnData.put("nextPage","home");
				}else{
					
				}
			}
			
		}catch (Exception e) {
			logger_log.error("addECommerceUser::::::::::::::::::::"+e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getAEPSViewUser(User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		List<AEPSUserDetail> aepslist = new ArrayList<>();
		try{
		     if(user.getRoleId()==1){
			 aepslist = customQueryServiceLogic.getAEPSUserDetailwithbalance(CustomSqlQuery.getAEPSUserdetail,parameters);
		     }else if(user.getRoleId()==2){
		     parameters.put("wl_id",user.getWlId());	 
		     aepslist = customQueryServiceLogic.getAEPSUserDetailwithbalance(CustomSqlQuery.getAEPSUserdetailByWlid,parameters);	 
		     }
		     
			if(!aepslist.isEmpty()){
				returnData.put("aepslist",aepslist);
				returnData.put("status","1");
			}else{
				returnData.put("message","No data available");
				returnData.put("status","0");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getAEPSViewUser::::::::::::::::::"+e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> addOperator(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			boolean flag = false;
			String service = "";
			int operatorid = 0;

			String service_type = request.get("service");
			String operator_name = request.get("operatorname");
			int api =Integer.parseInt(request.get("apiname"));
			if (service_type.equalsIgnoreCase("Mobile Recharge")) {
				service = "1";
			}else if(service_type.equalsIgnoreCase("DTH Recharge")){
				service = "2";
			}else if(service_type.equalsIgnoreCase("DATACARD Recharge")){
				service = "3";
			}else if(service_type.equalsIgnoreCase("POSTPAID")){
				service = "4";
			}else if(service_type.equalsIgnoreCase("INSURANCE")){
				service = "5";
			}else if(service_type.equalsIgnoreCase("GAS")){
				service = "6";
			}else if(service_type.equalsIgnoreCase("ELECTRIC")){
				service = "7";
			}else if(service_type.equalsIgnoreCase("PAN")){
				service = "8";
			}
			List<Operator> list = operatorDao.getAllOpeartor();
			Operator op1 = list.get(list.size() - 1);
			String operatorCode = String.valueOf(op1.getOperatorId()+ 1);
			Operator op = new Operator(operator_name, api, operatorCode, service, operatorCode, "", "unavailable");
			operatorid = operatorDao.insertOperator(op);
			if (operatorid > 0) {
				String serv="";
				if(service.equals("1")||service.equals("2")||service.equals("3")||service.equals("4")||service.equals("5")||service.equals("6")||service.equals("7")){
					serv="Recharge";
					
				}else if(service.equals("8")){
					serv="Pan";
				}
				param.put("service_type", serv);
				List<PackageDetail> pcklist = packagedetailDao.getPackageDetailByNamedQuery("getPackageBydservice", param);
				for (PackageDetail pck : pcklist) {
					PackageWiseChargeComm pckw = new PackageWiseChargeComm(pck.getPackage_id(), operatorid, 0.0,
							"PERCENTAGE", 0.0, "PERCENTAGE", "");
					packwisechargecomm.insertPackageWiseChargeComm(pckw);
				}
				returnData.put("message", "Operator Added successfully");
				returnData.put("status", "1");
			}else{
				returnData.put("message", "Operator not Added successfully");
				returnData.put("status", "0");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("addOperator ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> insertBankDetails(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag = false;
		String msg = "";
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if (request.get("accName") != null || request.get("accNo") != null || request.get("ifscCode") != null
						|| request.get("branchName") != null || request.get("bankName") != null) {
					
					Bankdetails bankdetails = new Bankdetails(request.get("accName"), request.get("accNo"),request.get("ifscCode"), request.get("branchName"), request.get("bankName"),userDetails.getWlId(), GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime());
					if(request.get("id") == null){
						flag = bankdetailsDao.insertBankdetails(bankdetails);
						msg = "Bank Details insert successfully!";
					} else {
						bankdetails.setId(Integer.parseInt(request.get("id")));
						flag = bankdetailsDao.updateBankdetails(bankdetails);
						msg = "Bank Details Update successfully!";
					}
				} else {
					flag = false;
				}
				if(flag){
					returnData.put("message", msg);
					returnData.put("status", "1");
				} else {
					returnData.put("message", "Failed to insert bank details!");
					returnData.put("status", "0");
				}
			} else {
				returnData.put("message", "Insert Bank Details Properly!");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("insertBankDetails/Update ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;

	}

	@Override
	public Map<String, Object> signInByAdmin(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if(userDetails.getRoleId() == 1){
					returnData.put("message", "Sign In Successfully.");
					returnData.put("status", "1");
				} else {
					returnData.put("message", "Unauthorised Sign in.");
					returnData.put("status", "0");
				}
			} else {
				returnData.put("message", "Failed to Sign in.");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("signInByAdmin ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> sendPassword(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1) {
					User user = userDao.getUserByUserId(request.get("userId"));
					if(user != null && user.getDelFlag().equals("0")){
						Map<String, Object> param = new HashMap<>();
						param.put("wlId", user.getWlId());
						SmsCredential credential = smsCredentialDao.getSmsCredentialByNamedQuery("GetSmsCredentialByWlId", param).get(0);
						String sms = "Welcome to "+credential.getCompany()+",Your Mobile number is :"+user.getMobile()+" & Password:"+user.getPassword()+" Log on to :"+credential.getDomain()+"";
						SMS.sendSMS2(user.getMobile(), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());
						returnData.put("message", "Password Send Successfully.");
						returnData.put("status", "1");
					} else {
						returnData.put("message", "Failed to Send Password.");
						returnData.put("status", "0");
					}
					
				} else {
					returnData.put("message", "Forbidden to send password");
					returnData.put("status", "0");
				}
			} else {
				returnData.put("message", "Failed to Send Password.");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("signInByAdmin ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;}

	@Override
	public Map<String, Object> deleteUser(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1) {
					User user = userDao.getUserByUserId(request.get("userId"));
					if(user != null && user.getDelFlag().equals("0")){
						user.setDelFlag("1");
						boolean flag = userDao.updateUser(user);
						if(flag){
							returnData.put("message", "User Deleted Successfully.");
							returnData.put("status", "1");						
						} else {
							returnData.put("message", "Failed to Delete User.");
							returnData.put("status", "0");					
						}
					} else {
						returnData.put("message", "Failed to Delete User.");
						returnData.put("status", "0");
					}					
				} else {
					returnData.put("message", "Forbidden to Delete User");
					returnData.put("status", "0");
				}
			} else {
				returnData.put("message", "Failed to Delete.");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("signInByAdmin ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;}

	@Override
	public Map<String, Object> deleteBankDetails(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag = false;
		String msg = "";
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if (request.get("accName") != null || request.get("accNo") != null || request.get("ifscCode") != null
						|| request.get("branchName") != null || request.get("bankName") != null) {
					
					Bankdetails bankdetails = new Bankdetails(request.get("accName"), request.get("accNo"),request.get("ifscCode"), request.get("branchName"), request.get("bankName"),userDetails.getWlId(), GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime());
					if(request.get("id") == null){						
						msg = "Failed to Delete Bank Details!";
					} else {
						bankdetails.setId(Integer.parseInt(request.get("id")));
						flag = bankdetailsDao.deleteBankdetails(bankdetails.getId());
						msg = "Bank Details Update successfully!";
					}
				} else {
					flag = false;
				}
				if(flag){
					returnData.put("message", msg);
					returnData.put("status", "1");
				} else {
					returnData.put("message", "Failed to Delete bank details!");
					returnData.put("status", "0");
				}
			} else {
				returnData.put("message", "Failed to Delete bank details!");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("insertBankDetails ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;

	}

	@Override
	public Map<String, Object> revertUserBalance(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if(userDetails.getRoleId() == 1 || userDetails.getRoleId() == 2){
					if(request.get("userId") == null || request.get("amount") == null || request.get("remark") == null){
						returnData.put("message", "Failed to Revert User Balance!");
						returnData.put("status", "0");
					} else {						
						boolean flag = false;
						boolean flag1 = false;
						if(Double.parseDouble(request.get("amount")) < 1) {
							returnData.put("message", "Invalid Amount!");
							returnData.put("status", "0");
						} else {
							User user = userDao.getUserByUserId(request.get("userId"));
							System.out.println("User ID::::::::::::::"+request.get("userId"));
							if (user != null) {
								double clBal =user.getBalance() - Double.parseDouble(request.get("amount"));
								if (clBal > user.getLockedAmount()) {
									if (userDetails.getRoleId() == 1) {
										flag = commissionService.updateBalance(request.get("userId"), "REVERT BALANCE BY ADMIN", request.get("remark"), Double.parseDouble(request.get("amount")), "DEBIT",0);
									}
									else {
										flag1 = commissionService.updateBalance(userDetails.getUserId(),"REVERT BACK FROM " + request.get("userId"), request.get("remark"), Double.parseDouble(request.get("amount")), "CREDIT",0);
										if (flag1) {
											flag = commissionService.updateBalance(request.get("userId"), "REVERT BALANCE BY ADMIN", request.get("remark") ,Double.parseDouble(request.get("amount")), "DEBIT",0);
										}
									}
									if (flag) {
										returnData.put("message", "Balance Reverted Successfully!");
										returnData.put("status", "1");
									} else {
										returnData.put("message", "Failed to Revert Balance!");
										returnData.put("status", "0");
									}
								} else {
									returnData.put("message", "Insufficient Balance to Revert!");
									returnData.put("status", "0");
								}
							} else {
								returnData.put("message", "Not a Valid User!");
								returnData.put("status", "0");
							}
						}
					}
				} else {
					returnData.put("message", "Forbidden to Revert User Balance!");
					returnData.put("status", "0");
				}
			} else {
				returnData.put("message", "Failed to Revert User Balance!");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("revertUserBalance ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> apiSwitching(Operator[] request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag = false;
		try {
			for (Operator operator : request) {
				flag = operatorDao.updateOperator(operator);
			}
			if (flag) {
				returnData.put("message", "Api Switching Sucessfully!");
				returnData.put("status", "1");
			} else {
				returnData.put("message", "Failed to Switch.");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("apiSwitching  ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again!");
			returnData.put("status", "0");
			return returnData;
		}
		if (flag) {
			returnData.put("message", "Update Successfully!");
			returnData.put("status", "1");
		} else {
			returnData.put("message", "Failed to Update!");
			returnData.put("status", "1");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> userMapping(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if(Integer.parseInt(request.get("mapUserType")) <= Integer.parseInt(request.get("mapToUserType"))){				
					returnData.put("message", "Invalid Order Selection!");
					returnData.put("status", "0");
				} else {
					if (userDetails.getRoleId() == 1 || userDetails.getRoleId() == 2) {
						User mapUser = userDao.getUserByUserId(request.get("mapUserId"));
						if (mapUser != null) {
							User toUser = userDao.getUserByUserId(request.get("mapToUserId"));
							if (toUser != null) {
								mapUser.setUplineId(toUser.getUserId());
								boolean flag = userDao.updateUser(mapUser);
								if (flag) {
									returnData.put("message", "User Mapped Successfully!");
									returnData.put("status", "1");
								} else {
									returnData.put("message", "User Mappeing Failed!");
									returnData.put("status", "1");
								}
							} else {
								returnData.put("message", "Invalid User Selection!");
								returnData.put("status", "0");
							}
						} else {
							returnData.put("message", "Invalid User Selection!");
							returnData.put("status", "0");
						}
					} else {				
						returnData.put("message", "Forbidden User!");
						returnData.put("status", "0");
					}
				}
			} else {				
				returnData.put("message", "Select option properly");
				returnData.put("status", "0");
			}
		}catch (Exception e) {
			logger_log.error("userMapping ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again properly");
			returnData.put("status", "0");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> setUserLockedAmount(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1 || userDetails.getRoleId() == 2) {
					if (request.get("amount") != null) {
						if (Integer.parseInt(request.get("amount")) > 0) {
							if(request.get("userId").equals("ALL")) {
								Map<String,Object> userTyPe=new HashMap<String, Object>();
								List<User> list1=new ArrayList<User>();
								int loginUserID=userDetails.getRoleId();
								int UserID=Integer.parseInt(request.get("userType"));
								if(loginUserID==1){
									if(UserID==2){
										System.out.println("uplineid:::::::::::::"+userDetails.getUserId());
										userTyPe.put("userTyPe", Integer.parseInt(request.get("userType")));
										userTyPe.put("uplineid", userDetails.getUserId());
										list1=userDao.getUserByNamedQuery("getUserByUserType",userTyPe);
									}else{
									System.out.println("wiId:::::::::::::"+userDetails.getWlId());
									userTyPe.put("userTyPe", Integer.parseInt(request.get("userType")));
									userTyPe.put("wiId", userDetails.getWlId());
									list1=userDao.getUserByNamedQuery("getUserByAdminType",userTyPe);
									}
								}else if(loginUserID==2){
									if(UserID==3){
										System.out.println("uplineid:::::::::::::"+userDetails.getUserId());
										userTyPe.put("userTyPe", Integer.parseInt(request.get("userType")));
										userTyPe.put("uplineid", userDetails.getUserId());
										list1=userDao.getUserByNamedQuery("getUserByUserType",userTyPe);
									}else{
									System.out.println("wiId:::::::::::::"+userDetails.getWlId());
									userTyPe.put("userTyPe", Integer.parseInt(request.get("userType")));
									userTyPe.put("wiId", userDetails.getWlId());
									list1=userDao.getUserByNamedQuery("getUserByAdminType",userTyPe);
									}
								}
								String AllId="";
								if(!list1.isEmpty()) {
									for(User user : list1) {
										AllId =user.getUserId();
										User user1 = userDao.getUserByUserId(AllId);
										if (user1 != null) {
											user1.setLockedAmount(Double.parseDouble(request.get("amount")));
											boolean flag = userDao.updateUser(user1);
											if (flag) {
												returnData.put("message", "Locked amount set Successfully!");
												returnData.put("status", "1");
											} else {
												returnData.put("message", "Failed to set Locked amount!");
												returnData.put("status", "0");
											}
										} else {
											returnData.put("message", "Invalid User!");
											returnData.put("status", "0");
										}
									}
								}
							}else{
							User user = userDao.getUserByUserId(request.get("userId"));
							if (user != null) {
								user.setLockedAmount(Double.parseDouble(request.get("amount")));
								boolean flag = userDao.updateUser(user);
								if (flag) {
									returnData.put("message", "Locked amount set Successfully!");
									returnData.put("status", "1");
								} else {
									returnData.put("message", "Failed to set Locked amount!");
									returnData.put("status", "0");
								}
							} else {
								returnData.put("message", "Invalid User!");
								returnData.put("status", "0");
							}
							}
						} else {
							returnData.put("message", "Amount cannot be negative!");
							returnData.put("status", "0");
						}
					} else {
						returnData.put("message", "Enter Amount!");
						returnData.put("status", "0");
					}
				} else {
					returnData.put("message", "Forbidden User!");
					returnData.put("status", "0");
				}
			} else {
				returnData.put("message", "Invalid Details!");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("userMapping ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again properly");
			returnData.put("status", "0");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getUserDetailsForEdit(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1 || userDetails.getRoleId() == 2) {
					if(request.get("userId") != null){
						User user = userDao.getUserByUserId(request.get("userId"));
						if(user != null){
							returnData.put("userProfile", user);
							returnData.put("status", "1");
						} else {
							returnData.put("message", "Invalid User!");
							returnData.put("status", "0");
						}
					} else {
						returnData.put("message", "Invalid User!");
						returnData.put("status", "0");
					}
				} else {
					returnData.put("message", "Forbidden User!");
					returnData.put("status", "0");
				}
			} else {
				returnData.put("message", "Invalid Details!");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("userMapping ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again properly");
			returnData.put("status", "0");
		}
		return returnData;
	}
	


	@Override
	public Map<String, Object> addReseller(Map<String, String> request, User userDetails, byte[] bytes) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1 || userDetails.getRoleId() == 2) {
					
					if (request.get("name") == null || request.get("firmName") == null || request.get("address") == null
							|| request.get("city") == null || request.get("pinCode") == null
							|| request.get("state") == null || request.get("email") == null
							|| request.get("poweredBy") == null || request.get("poweredByLink") == null
							|| request.get("pageTitle") == null
							|| request.get("name").equals("") || request.get("firmName").equals("") || request.get("address").equals("")
							|| request.get("city").equals("") || request.get("pinCode").equals("")
							|| request.get("state").equals("") || request.get("email").equals("")
							|| request.get("poweredBy").equals("") || request.get("poweredByLink").equals("")
							|| request.get("pageTitle").equals("") || bytes == null || request.get("domain").equals("")) {
						returnData.put("message", "Invalid Details!");
						returnData.put("status", "0");
					} else {
						String userId = GenerateRandomNumber.generateUserName();
						String password = GenerateRandomNumber.generatePassword();
						String wl_id = GenerateRandomNumber.generateWLID();				
						User user = new User(userId, password,2, wl_id, userId, request.get("name"),request.get("firmName"), request.get("address"), request.get("city"),request.get("pinCode"),request.get("state"), "India",request.get("mobile"),userDetails.getUserId(), request.get("email"), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(), 0.0, "0", "0", "N/A",0.0);
						boolean flag = userDao.insertUser(user);
						if(flag){
							Reseller reseller = new Reseller(wl_id, "theme-blue", "LOGO", request.get("mobile"),  request.get("email"), request.get("address"),request.get("poweredBy"),request.get("poweredByLink"),request.get("pageTitle"), bytes,request.get("domain"));
							boolean flag1 = resellerDao.insertReseller(reseller);
							if(flag1){
								HashMap<String, Object> param = new HashMap<String, Object>();
								param.put("wlId", userDetails.getWlId());
								SmsCredential credential = smsCredentialDao.getSmsCredentialByNamedQuery("GetSmsCredentialByWlId", param).get(0);
								SmsCredential resellerCredentials = new SmsCredential(credential.getSmsUsername(), credential.getSmsPassword(), credential.getSenderId(), wl_id, request.get("poweredBy"), request.get("poweredByLink"));
								smsCredentialDao.insertSmsCredential(resellerCredentials);							
								returnData.put("message", "Reseller Added Successfully!");
								returnData.put("status", "1");
								/*-------------------SEND SMS TO USER ------------------------*/								
								String sms = "Welcome to "+credential.getCompany()+",your account has been created with Mobile number:"+request.get("mobile")+" & Password:"+password+" Log on to :"+credential.getDomain()+"";
								SMS.sendSMS2(user.getMobile(), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());
	/*-----------------------------------------------------------------*/
							} else {
								returnData.put("message", "Failed To Add Reseller!");
								returnData.put("status", "0");
							}
						} else {
							returnData.put("message", "Failed To Add Reseller!");
							returnData.put("status", "0");
						}
					}
				} else {
					returnData.put("message", "Forbidden User!");
					returnData.put("status", "0");
				}
			} else {
				returnData.put("message", "Invalid Details!");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("addReseller ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again properly");
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> addIndex(Map<String, String> request, byte[] bytes) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Indexx index=new Indexx(request.get("domain"),request.get("auTitle"),request.get("auDescription"),bytes,request.get("auBlogtitle"),request.get("auBlogDetail"),request.get("address"),request.get("phno"),request.get("email"),request.get("map"));
		boolean flag =indexdao.insertIndex(index);
		if(flag){
			returnData.put("message", "Reseller Index Added Successfully!");
			returnData.put("status", "1");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> setResellerTheme(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1 || userDetails.getRoleId() == 2) {
					if (request.get("theme") == null || request.get("userType") == null 
							|| request.get("theme").equals("") || request.get("userType").equals("")) {
						returnData.put("message", "Invalid Details!");
						returnData.put("status", "0");
					} else {
						if(request.get("userType").equals("10")){
							Reseller reseller = resellerDao.getResellerById(userDetails.getWlId());
							if(reseller != null){
								reseller.setTheme(request.get("theme"));
								boolean flag = resellerDao.updateReseller(reseller);
								if(flag){
									returnData.put("message", "Theme update Successfully!");
									returnData.put("status", "1");
								} else {
									returnData.put("message", "Failed to update theme!");
									returnData.put("status", "0");
								}
							} else {
								returnData.put("message", "Failed to update theme!");
								returnData.put("status", "0");
							}
						} else if(request.get("userType").equals("2")){
							 User user = userDao.getUserByUserId(request.get("userId"));
							 if(user != null){
								if (user.getRoleId() == 2) {
									Reseller reseller = resellerDao.getResellerById(user.getWlId());
									if (reseller != null) {
										reseller.setTheme(request.get("theme"));
										boolean flag = resellerDao.updateReseller(reseller);
										if (flag) {
											returnData.put("message", "Theme update Successfully!");
											returnData.put("status", "1");
										} else {
											returnData.put("message", "Failed to update theme!");
											returnData.put("status", "0");
										}
									} else {
										returnData.put("message", "Failed to update theme!");
										returnData.put("status", "0");
									}
								} else {
									 returnData.put("message", "Invalid User Selected!");
									 returnData.put("status", "0");
								 }
							 } else {
								 returnData.put("message", "Invalid User Selected!");
								 returnData.put("status", "0");
							 }
						} else {
							returnData.put("message", "Invalid Details!");
							returnData.put("status", "0");
						}
					}
				} else {
					returnData.put("message", "Forbidden User!");
					returnData.put("status", "0");
				}
			} else {
				returnData.put("message", "Invalid Details!");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("setResellerTheme ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again properly");
			returnData.put("status", "0");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getResellerDetails(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1 || userDetails.getRoleId() == 2) {
					if (request.get("userType") == null	|| request.get("userType").equals("") || request.get("userId") == null || request.get("userId").equals("")) {
						returnData.put("message", "Invalid Details!");
						returnData.put("status", "0");
					} else {
						if (request.get("userType").equals("2")) {
							User user = userDao.getUserByUserId(request.get("userId"));
							if (user != null) {
								if (user.getRoleId() == 2) {
									Map<String, String> parameters = new HashMap<>();
									parameters.put("wlId", user.getWlId());
									List<ResellerDetails> rList = customQueryServiceLogic.getResellerDetails(CustomSqlQuery.getResellerDetails, parameters );
									if(!rList.isEmpty()){
										returnData.put("details", rList.get(0));
										returnData.put("status", "1");
									
									} else {
										returnData.put("message", "Invalid User Selected!");
										returnData.put("status", "0");
									}
								} else {
									returnData.put("message", "Invalid User Selected!");
									returnData.put("status", "0");
								}
							} else {
								returnData.put("message", "Invalid User Selected!");
								returnData.put("status", "0");
							}
						} else {
							returnData.put("message", "Invalid Details!");
							returnData.put("status", "0");
						}
					}
				} else {
					returnData.put("message", "Forbidden User!");
					returnData.put("status", "0");
				}
			} else {
				returnData.put("message", "Invalid Details!");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("setResellerTheme ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again properly");
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	
	@Override
	public Map<String, Object> setResellerLogo(Map<String, String> request, User userDetails, byte[] bytes) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1 || userDetails.getRoleId() == 2) {
					if (bytes == null || request.get("userType") == null 
							|| request.get("userType").equals("")) {
						returnData.put("message", "Invalid Details!");
						returnData.put("status", "0");
					} else {
						if(request.get("userType").equals("10")){
							Reseller reseller = resellerDao.getResellerById(userDetails.getWlId());
							if(reseller != null){
								reseller.setImage(bytes);
								boolean flag = resellerDao.updateReseller(reseller);
								if(flag){
									returnData.put("message", "Theme update Successfully!");
									returnData.put("status", "1");
								} else {
									returnData.put("message", "Failed to update theme!");
									returnData.put("status", "0");
								}
							} else {
								returnData.put("message", "Failed to update theme!");
								returnData.put("status", "0");
							}
						} else if(request.get("userType").equals("2")){
							 User user = userDao.getUserByUserId(request.get("userId"));
							 if(user != null){
								if (user.getRoleId() == 2) {
									Reseller reseller = resellerDao.getResellerById(user.getWlId());
									if (reseller != null) {
										reseller.setImage(bytes);
										boolean flag = resellerDao.updateReseller(reseller);
										if (flag) {
											returnData.put("message", "Theme update Successfully!");
											returnData.put("status", "1");
										} else {
											returnData.put("message", "Failed to update theme!");
											returnData.put("status", "0");
										}
									} else {
										returnData.put("message", "Failed to update theme!");
										returnData.put("status", "0");
									}
								} else {
									 returnData.put("message", "Invalid User Selected!");
									 returnData.put("status", "0");
								 }
							 } else {
								 returnData.put("message", "Invalid User Selected!");
								 returnData.put("status", "0");
							 }
						} else {
							returnData.put("message", "Invalid Details!");
							returnData.put("status", "0");
						}
					}
				} else {
					returnData.put("message", "Forbidden User!");
					returnData.put("status", "0");
				}
			} else {
				returnData.put("message", "Invalid Details!");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("setResellerTheme ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again properly");
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@Override
	public List<webenquery> viewWebEnquery(Map<String, String> request,User userDetails) {
		List<webenquery> list = new ArrayList<webenquery>();
		Map<String, Object> param = null;
		try{
			param = new HashMap<String, Object>();
			param.put("startDate", request.get("startDate"));
			param.put("endDate", request.get("endDate"));
			list = WebEnquryDao.getUserByNamedQuery("GetwebenqueryReport",param);
				
			
			}catch(Exception e){
			logger_log.error("viewWebEnquery :::::::::::: "+e);
			list = new ArrayList<webenquery>();
			}
		return list;
	}
	
	@Override
	public Map<String, Object> fetchDomain(){
		Map<String, Object> returnData = new HashMap<String, Object>();
		List<Reseller> resellerList = resellerDao.getAllReseller();
		if(!resellerList.isEmpty()){
			/*for(Reseller res : resellerList){*/
				returnData.put("domainlist", resellerList);
				returnData.put("status","1");
			/*}*/
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> updateImpsReport(Map<String, String> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
	//	Operator operator = new Operator();
		
		double sdComm = 0.0;
		double dComm = 0.0;
		double resComm = 0.0;
		double charge = 0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		double resCharge = 0.0;
		double comm = 0.0;
		double disComm = 0.0;
		double sdisComm = 0.0;
		double reComm = 0.0;
		double rComm=0.0;
		double distCharge=0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";
		String rId="";
		double sdCharge=0.0;
		double commission=0.0;
		double totalAmount = 0.0;
		try {
			if(user.getRoleId() == 1){
				if (UtilityClass.checkParameterIsNull(request)) {
					
					String ImpsStatus = request.get("status");
					param = new HashMap<String, Object>();
					param.put("recieptId", request.get("recieptId"));
					List<ImpsTransaction> ReportDetails =impstransactionservice.getIMPSDetailsByNamedQuery("GetIMPSDetailsByTid",param);
					if(!ReportDetails.isEmpty()){
						ImpsTransaction impstransaction=ReportDetails.get(0);
						double transactionAmount = impstransaction.getAmount();
						User userDetails = userDao.getUserByUserId(impstransaction.getUsername());
						 charge=impstransaction.getCharge();
						 commission=impstransaction.getCommission();
						
						totalAmount = (transactionAmount-charge)+commission;
						int id=0;
						parameters = new HashMap<String, String>();
						parameter.put("api", "DMR");
						List<DMRCommission> opList =  DMRCommissionDao.getDMRCommissionByNamedQuery("getDMRCommissionByApi", parameter);
						if((!opList.isEmpty())) {
							for(DMRCommission comm2 : opList){
								if(transactionAmount>=comm2.getSlab1() && transactionAmount<=comm2.getSlab2()){
									id = comm2.getId();
									break;
								}
							}
						}
						/*-------------------------------------COMMISSION------------------------------------------------------*/
						if(userDetails.getRoleId() == 5) {
							//Retailer Id
							rId=impstransaction.getUsername();
							// Distributor Id
							distId = userDetails.getUplineId();								
							// Super Distributor Id
							parameters  = new HashMap<String, String>();
							parameters.put("userId", distId);	
							sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
							
							// Reseller Id
							parameters = new HashMap<String, String>();
							parameters.put("userId", sdId);							
							resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
							pckret =commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id); 
							if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								rComm =(pckret.getComm()*totalAmount)/100;
								}else{
								rComm=pckret.getComm();	
								}
							
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"DMR",id); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*totalAmount)/100;
							}else{
							dComm=pckdis.getComm();	
							}
							
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*totalAmount)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							if(!resellerId.equalsIgnoreCase("admin")){
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*totalAmount)/100;
							}else{
							resComm =pckres.getComm();	
							}
							}
							if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
                            	distCharge=(pckdis.getCharge()*totalAmount)/100;
                            }else{
                            	distCharge=pckdis.getCharge();
                            }
						     if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
						    	 sdCharge =(pcksd.getCharge()*totalAmount)/100;
						     }else{
						    	 sdCharge=pcksd.getCharge();
						     }
							if(!resellerId.equals("admin")) {
								if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								resCharge = (pckres.getCharge()*totalAmount)/100;
								}else{
								resCharge = pckres.getCharge();	
								}
							}
							
						} else if(userDetails.getRoleId() == 4) {
							// Distributor Id
							distId =userDetails.getUserId();
							// Super Distributor Id
							sdId = userDetails.getUplineId();	
							// Reseller Id
							parameters = new HashMap<String, String>();
							parameters.put("userId", sdId);							
							resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
							
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"DMR",id); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*totalAmount)/100;
							}else{
							dComm=pckdis.getComm();	
							}
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*totalAmount)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							if(!resellerId.equalsIgnoreCase("admin")){
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*totalAmount)/100;
							}else{
							resComm =pckres.getComm();	
							}
							}
							if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
                            	distCharge=(pckdis.getCharge()*totalAmount)/100;
                            }else{
                            	distCharge=pckdis.getCharge();
                            }
						     if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
						    	 sdCharge =(pcksd.getCharge()*totalAmount)/100;
						     }else{
						    	 sdCharge=pcksd.getCharge();
						     }
							if(!resellerId.equals("admin")) {
								if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								resCharge = (pckres.getCharge()*totalAmount)/100;
								}else{
								resCharge = pckres.getCharge();	
								}
							}
						} else if(userDetails.getRoleId() == 3) {
							// Super Distributor Id
							sdId = userDetails.getUserId();
							// Reseller Id
							resellerId = userDetails.getUplineId();	
							
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*totalAmount)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
						    	 sdCharge =(pcksd.getCharge()*totalAmount)/100;
						     }else{
						    	 sdCharge=pcksd.getCharge();
						     }
						//	System.out.println("sdComm=="+sdComm);
							if(!resellerId.equalsIgnoreCase("admin")){
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*totalAmount)/100;
							}else{
							resComm =pckres.getComm();	
							}
							if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								resCharge = (pckres.getCharge()*totalAmount)/100;
								}else{
								resCharge = pckres.getCharge();	
								}
						}
						}
						
						
						
					
						String tablestatus=impstransaction.getStatus();
						if(ImpsStatus.equals("SUCCESS") && tablestatus.equals("PENDING")){
						//String banktransactionId=request.get("banktransactionId");
						impstransactionservice.updateDmrTransactionStatus(impstransaction.getRecieptId(),ImpsStatus);
						impstransactionservice.updateDmrTransactionBankTransactionId(impstransaction.getRecieptId(),request.get("banktransactionId"));
						returnData.put("status", "1");
						returnData.put("message", "Status : Validation Status update Successfully.");
						}
						else if(ImpsStatus.equals("FAILED")){
							commissionService.updateBalance(userDetails.getUserId(),"Money Revert - " + impstransaction.getRecieptId(),"Money Transfer", impstransaction.getAmount(), "CREDIT",0);
							
							if(userDetails.getRoleId() == 5) {
								//Retailer Id
								// Charge REFUND
								double dcomm1=0.0;
								double dcharge1=0.0;
								double sdComm1=0.0;
								double sdCharge1=0.0;
									if(dComm==0){dcomm1=0;}
									else{
									 dcomm1=dComm-rComm;
									}
									if(distCharge==0){dcharge1=0;}
									else{
									 dcharge1=charge-distCharge;
									}
									if(sdComm==0){sdComm1=0;}
									else{
									 sdComm1= sdComm-dComm;
									}
									if(sdCharge==0){sdCharge1=0;}
									else{
									 sdCharge1=distCharge-sdCharge;
									}
									//dcharge = dcomm1+dcharge1;
									System.out.println(dcomm1);
									System.out.println(dcharge1);
									System.out.println(dcharge);
									//sCharge = sdComm1+sdCharge1;
									System.out.println(sdComm-dComm);
									System.out.println(distCharge-sdCharge);
									
									commissionService.updateBalance(distId, "REVERT Charge for Money Transfer - "+impstransaction.getAccount_no(), "REVERT", dcharge1, "DEBIT",0);									
									commissionService.updateBalance(sdId, "REVERT Charge for Money Transfer - "+impstransaction.getAccount_no(), "REVERT", sdCharge1, "DEBIT",0);
									commissionService.updateBalance(distId, "REVERT COMMISSION for Money Transfer - "+impstransaction.getAccount_no(), "REVERT", dcomm1, "DEBIT",0);									
									commissionService.updateBalance(sdId, "REVERT COMMISSION for Money Transfer - "+impstransaction.getAccount_no(), "REVERT", sdComm1, "DEBIT",0);
									
									if(!resellerId.equals("admin")) {
										if(resComm==0){reComm=0;}
										else{
										reComm=resComm-sdComm;
										}
										commissionService.updateBalance(resellerId, "REVERT COMMISSION for Money Transfer - "+impstransaction.getAccount_no(), "REVERT", reComm, "DEBIT",0);
										if(resCharge==0){reCharge=0;}
										else{reCharge=resCharge-sdCharge;}
										//reCharge = resComm -( dcharge+sCharge+rComm);
										commissionService.updateBalance(resellerId, "REVERT Charge for Money Transfer - "+impstransaction.getAccount_no(), "REVERT", reCharge, "DEBIT",0);
									}
								
								
								
							} else if(userDetails.getRoleId() == 4) {
								// Distributor Id
								distId =userDetails.getUserId();
								sdId = userDetails.getUplineId();	
								
								double sdComm1=0.0;
								double sdCharge1=0.0;
									if(sdComm==0){sdComm1=0;}
									else{
									 sdComm1= sdComm-dComm;
									}
									if(sdCharge==0){sdCharge1=0;}
									else{
									 sdCharge1=distCharge-sdCharge;
									}
									
																	
									commissionService.updateBalance(sdId, "REVERT Charge for Money Transfer - "+impstransaction.getAccount_no(), "REVERT", sdCharge1, "DEBIT",0);							
									commissionService.updateBalance(sdId, "REVERT COMMISSION for Money Transfer - "+impstransaction.getAccount_no(), "REVERT", sdComm1, "DEBIT",0);
									
									if(!resellerId.equals("admin")) {
										if(resComm==0){reComm=0;}
										else{
										reComm=resComm-sdComm;
										}
										commissionService.updateBalance(resellerId, "REVERT COMMISSION for Money Transfer - "+impstransaction.getAccount_no(), "REVERT", reComm, "DEBIT",0);
										if(resCharge==0){reCharge=0;}
										else{reCharge=resCharge-sdCharge;}
										//reCharge = resComm -(sCharge+rComm);
										commissionService.updateBalance(resellerId, "REVERT Charge for Money Transfer - "+impstransaction.getAccount_no(), "REVERT", reCharge, "DEBIT",0);
									}
									
								
							} else if(userDetails.getRoleId() == 3) {	
								
								// Super Distributor Id
								sdId = userDetails.getUserId();
								resellerId = userDetails.getUplineId();
									
									if(!resellerId.equals("admin")) {
										if(resComm==0){reComm=0;}
										else{
										reComm=resComm-sdComm;
										}
										commissionService.updateBalance(resellerId, "REVERT COMMISSION for Money Transfer - "+impstransaction.getAccount_no(), "REVERT", reComm, "DEBIT",0);
										if(resCharge==0){reCharge=0;}
										else{reCharge=resCharge-sdCharge;}
										//reCharge = resComm -sdComm;
										//System.out.println("reCharge=="+reCharge);
										commissionService.updateBalance(resellerId, "REVERT Charge for Money Transfer - "+impstransaction.getAccount_no(), "REVERT", reCharge, "DEBIT",0);
									}
								
								
							}
							
							
			
							impstransactionservice.updateDmrTransactionStatus(impstransaction.getRecieptId(),ImpsStatus);
							impstransactionservice.updateDmrTransactionBankTransactionId(impstransaction.getRecieptId(),request.get("banktransactionId"));
							returnData.put("status", "0");
							returnData.put("message", "Status : Validation Status update Successfully.");
					}
						else if(ImpsStatus.equals("SUCCESS") && tablestatus.equals("FAILED")){
							commissionService.updateBalance(userDetails.getUserId(),"Money Transfer - " + impstransaction.getRecieptId(),"Money Transfer", totalAmount, "DEBIT",0);
						
							if (userDetails.getRoleId() == 5) {
								if(distCharge==0){dcharge=0;}
								else{dcharge = charge - distCharge;}
								System.out.println("dcharge:::"+dcharge);
								if(sdCharge==0){sCharge=0;}
								else{sCharge = distCharge - sdCharge;}
								System.out.println("sCharge:::"+sCharge);
								
								commissionService.updateBalance(distId, "Money Transfer - "+impstransaction.getAccount_no(), "CHARGE", dcharge, "CREDIT",0);
								commissionService.updateBalance(sdId, "Money Transfer - "+impstransaction.getAccount_no(), "CHARGE", sCharge, "CREDIT",0);
								if(!resellerId.equals("admin")) {
									reCharge = sdCharge - resCharge;
									commissionService.updateBalance(resellerId, "Money Transfer - "+impstransaction.getAccount_no(), "CHARGE", reCharge, "CREDIT",0);
								}
								
								if(dComm==0){disComm=0;}
								else{
								disComm=dComm-rComm;
								}
								if(sdComm==0){sdisComm=0;}
								else{
								sdisComm = sdComm-dComm;
								}
								if(resComm==0){reComm=0;}
								else{
								reComm = resComm-sdComm;
								}								
								commissionService.updateBalance(distId, "Money Transfer - "+impstransaction.getAccount_no(), "COMMISSION", disComm, "CREDIT",0);
								commissionService.updateBalance(sdId, "Money Transfer - "+impstransaction.getAccount_no(), "COMMISSION", sdisComm, "CREDIT",0);
								if(!resellerId.equals("admin")) {
									commissionService.updateBalance(resellerId, "Money Transfer - "+impstransaction.getAccount_no(), "COMMISSION", reComm, "CREDIT",0);
								}
							}
								else if (userDetails.getRoleId() == 4) {
									if(sdCharge==0){sCharge=0;}
								else{sCharge = charge - sdCharge;}
								System.out.println("sCharge:::"+sCharge);
																						
								commissionService.updateBalance(sdId, "Money Transfer - "+impstransaction.getAccount_no(), "CHARGE", sCharge, "CREDIT",0);
								if(!resellerId.equals("admin")) {
									reCharge = sdCharge - resCharge;
									commissionService.updateBalance(resellerId, "Money Transfer - "+impstransaction.getAccount_no(), "CHARGE", reCharge, "CREDIT",0);
								}
								
								if(sdComm==0){sdisComm=0;}
								else{
								sdisComm=sdComm-dComm;
								}
								if(resComm==0){reComm=0;}
								else{
								reComm = resComm-sdComm;
								}
								
								commissionService.updateBalance(sdId, "Money Transfer - "+impstransaction.getAccount_no(), "COMMISSION", sdisComm, "CREDIT",0);
								if(!resellerId.equals("admin")) {
									commissionService.updateBalance(resellerId, "Money Transfer - "+impstransaction.getAccount_no(), "COMMISSION", reComm, "CREDIT",0);
								}}
								else if (userDetails.getRoleId() == 3) {resCharge = charge - resCharge;

								if (!resellerId.equals("admin")) {
									reCharge = sdCharge - charge;
									commissionService.updateBalance(resellerId,
											"Money Transfer - " + impstransaction.getAccount_no(),
											"CHARGE", reCharge, "CREDIT",0);
								}
								
								if(resComm==0){reComm=0;}
								else{
								reComm=resComm-sdComm;
								}
							
								commissionService.updateBalance(sdId, "Money Transfer - "+impstransaction.getAccount_no(), "COMMISSION", sdisComm, "CREDIT",0);
								if(!resellerId.equals("admin")) {
									commissionService.updateBalance(resellerId, "Money Transfer - "+impstransaction.getAccount_no(), "COMMISSION", reComm, "CREDIT",0);
								}}
								impstransactionservice.updateDmrTransactionStatus(impstransaction.getRecieptId(),ImpsStatus);
								impstransactionservice.updateDmrTransactionBankTransactionId(impstransaction.getRecieptId(),request.get("banktransactionId"));
								returnData.put("status", "1");
								returnData.put("message", "Status : Validation Status update Successfully.");
						
					}
					}else {
						returnData.put("status", "0");
						returnData.put("message", "No Report Details Found.");
					}
					
				}else {
					returnData.put("status", "0");
					returnData.put("message", "Parameter not pass correctly.");
				}
			}else {
				returnData.put("status", "0");
				returnData.put("message", "Forbidden User.");
			}
			
		}catch(Exception e){
				returnData.put("status", "0");
				returnData.put("message", "Exception! Please contact to admin.");
				logger_log.error("updateImpsReport ::::::::::::::: " + e);
			}
		return returnData;
		}
	
	@Override
	public Map<String, Object> agentstatusupdate(Map<String, String> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		System.out.println(request);
		String id=request.get("id");
		CreateAgent CreateAgent=creatagentdao.getagentById(Integer.parseInt(id));
		
		CreateAgent.setStatus(request.get("status"));
		boolean flag=creatagentdao.updateagent(CreateAgent);
		if(flag){
			returnData.put("message", "updated Successfully");
			returnData.put("status", "1");
		}else{
			returnData.put("message", "update Failed");
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> couponstatusupdate(Map<String, String> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		System.out.println(request);
		String id=request.get("id");
		CouponRequest coupon=coupondao.getcouponById(Integer.parseInt(id));
		coupon.setStatus(request.get("status"));
		//coupon.set
		boolean flag=coupondao.updateCouponRequest(coupon);
		if(flag){
			returnData.put("message", "updated Successfully");
			returnData.put("status", "1");
		}else{
			returnData.put("message", "update Failed");
			returnData.put("status", "0");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> addApi(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag = false;
		 Api api = null;
		try {
			param.put("apiName",request.get("apiname"));
			param.put("url",request.get("apiurl"));
			List<Api> list = apiDao.getApiByNamedQuery("getApibynameandurl",param);
			if(list.isEmpty()){
            api = new Api(request.get("apiname"), request.get("apiurl"));    
           flag = apiDao.insertApi(api);
           if(flag){
        	   returnData.put("status","1");  
        	   returnData.put("message","Inserted successfully");  
           }else{
        	   returnData.put("status","0");
        	   returnData.put("message","failed to insert");  
           }
			}else{
				api = list.get(0);
				api.setApiName(request.get("apiname"));
				api.setUrl(request.get("apiurl"));
				flag = apiDao.updateApi(api);
				  if(flag){
		        	   returnData.put("status","1");  
		        	   returnData.put("message","Inserted successfully");  
		           }else{
		        	   returnData.put("status","0");
		        	   returnData.put("message","failed to insert");  
		           }
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("addApi::::::::::::::::::::::::" + e);
		}
		return returnData;

	}

	@Override
	public Map<String, Object> addApiParameters(Map<String,Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag = false;
		ApiParameters apiparam = null;
	    System.out.println("addApiParameters::::::::::::::::::"+request);
		try {
			String[]  apiId= request.get("apiId").toString().split("\\."); 
			param.put("apiId",Integer.parseInt(apiId[0] ));
			param.put("service_type",request.get("service"));
			List<ApiParameters> list = apiParametersdao.getApiParametersByNamedQuery("getApiParametersbyapiidtype",param);
			if(list.isEmpty()){
				apiparam = new ApiParameters(request.get("apiparameternames").toString(),request.get("apiparametervalues").toString() ,Integer.parseInt(apiId[0] ),request.get("service").toString() , request.get("number").toString(),request.get("opcode").toString(),request.get("amount").toString(),request.get("request_id").toString());    
                flag = apiParametersdao.insertApiParameters(apiparam);
                if(flag){
        	   returnData.put("status","1");  
        	   returnData.put("message","Inserted successfully");  
                }else{
        	   returnData.put("status","0");
        	   returnData.put("message","failed to insert");  
              }
			}else{
				apiparam = list.get(0);
				apiparam.setApiparameternames(request.get("apiparameternames").toString());
				apiparam.setApiparametervalues(request.get("apiparametervalues").toString());
				apiparam.setNumber(request.get("number").toString());
				apiparam.setOpcode(request.get("opcode").toString());
				apiparam.setAmount(request.get("amount").toString());
				apiparam.setRequest_id(request.get("request_id").toString());
				//apiparam.setDynamicparams(request.get("dynamicapiparameters").toString());
				flag = apiParametersdao.updateApiParameters(apiparam);
				  if(flag){
		        	   returnData.put("status","1");  
		        	   returnData.put("message","updated successfully");  
		           }else{
		        	   returnData.put("status","0");
		        	   returnData.put("message","failed to updated");  
		           }
			}
		
		}catch (Exception e) {
			e.printStackTrace();
			logger_log.error("addApi::::::::::::::::::::::::" + e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> addsmsApi(Map<String, String> request,User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag = false;
		SMSApiparameters sms = null;
		try {
			
			List<SMSApiparameters> list = smsapiparamsdao.getAllSMSApiparameters();
			
			if(list.isEmpty()){
				sms =  new SMSApiparameters(request.get("baseurl").toString(),request.get("username").toString() ,request.get("password").toString() ,request.get("destination").toString() ,request.get("source").toString() ,request.get("message").toString() ,request.get("staticparameters").toString() ,request.get("staticparametervalues").toString());
                flag = smsapiparamsdao.insertSMSApiparameters(sms);
           if(flag){
        	   returnData.put("status","1");  
        	   returnData.put("message","Inserted successfully");  
           }else{
        	   returnData.put("status","0");
        	   returnData.put("message","failed to insert");  
           }
			}else{
				sms = list.get(0);
				SMSApiparameters	sms2 =  new SMSApiparameters(request.get("baseurl").toString(),request.get("username").toString() ,request.get("password").toString() ,request.get("destination").toString() ,request.get("source").toString() ,request.get("message").toString() ,request.get("staticparameters").toString() ,request.get("staticparametervalues").toString());
				sms.setId(sms.getId());
				flag = smsapiparamsdao.updateSMSApiparameters(sms2);
				  if(flag){
		        	   returnData.put("status","1");  
		        	   returnData.put("message","Inserted successfully");  
		           }else{
		        	   returnData.put("status","0");
		        	   returnData.put("message","failed to insert");  
		           }
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("addApi::::::::::::::::::::::::" + e);
		}
		return returnData;

	}
	
	@Override
	public Map<String, Object> getNSDLAttachmentDetails(Map<String, String> request) {
        Map<String, Object> returnData = new HashMap<String, Object>();
        Map<String, String> param = null;
        Map<String, Object> parameters = null;
        List<NSDLPanAttachment> userList = new ArrayList<>();
        try{
                System.out.println(request);
                String filepath="http://doc.safegoal.co.in/";
                String filename=request.get("invoiceno")+"idproof.pdf";
                String fileurl=filepath+filename;
               // FileInputStream fileInputStream = new FileInputStream(filepath + filename); 
                
                /*int i;   
                while ((i=fileInputStream.read()) != -1) {  
                 // out.write(i);   
                }*/   
               // fileInputStream.close();   
                returnData.put("status","1");
                returnData.put("filepath",filepath);
                returnData.put("filename",filename);
                returnData.put("fileurl",fileurl);
                
            /*    List<NSDLPanAttachment> documentlist = new ArrayList<>();
                List<NSDLPanAttachment> documentlistrequired = new ArrayList<>();
                parameters = new HashMap<String, Object>();
                        parameters.put("invoiceno", request.get("invoiceno"));
                        documentlist = NSDLPanAttachmentDao.getNSDLPanAttachmentByNamedQuery("getNSDLAttachment", parameters);
                        if(documentlist.size()>0){
                                returnData.put("documentlist",documentlist);
                                NSDLPanAttachment nsatt=documentlist.get(0);
        
                if(documentlist.get(0).getIdprooftype()!=null){
                        String Idprooftype[]=documentlist.get(0).getIdprooftype().split("/");
                        returnData.put("Idproofname",documentlist.get(0).getInvoiceno()+"Idproof."+Idprooftype[1]);
                }else{
                        returnData.put("Idproofname","0");
                }
                returnData.put("status","1");
        }else{
                returnData.put("documentlist","0");
                returnData.put("status","0");
                returnData.put("message","No Attachement Found");
        }*/
                
                
        }catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
        }
        return returnData;
        }
	
	@Override
	public Map<String, Object> changeStatus(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		//String updateStatus="";
		try{
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1 || userDetails.getRoleId() == 2) {
					String status=request.get("status");
					System.out.println("updatestatus===="+status);
					User user = userDao.getUserByUserId(request.get("userId"));
				//	List<User> userDetail = UserDao.getUserByUserId(userDetails.getUserId());
					if(status.equals("0")){
						//updateStatus="1";
						user.setStatus("1");
						boolean flag =userDao.updateUserStatus(user);
					}
					else{
						user.setStatus("0");
						boolean flag =userDao.updateUserStatus(user);
					}
				
				}else {
					returnData.put("message", "Forbidden User!");
					returnData.put("status", "0");
				}
				
			}else {
				returnData.put("message", "Invalid Details!");
				returnData.put("status", "0");
			}
			
		}catch(Exception e){
			logger_log.error("changeStatus :::::::"+ e);
			returnData.put("message", "Exception! Please try again properly");
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> searchackno(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		//String updateStatus="";
		try{
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1 || userDetails.getRoleId() == 2) {
					String ackno=request.get("ackno");
					System.out.println("updatestatus===="+ackno);
					param.put("ackno", ackno);
					List<NSDLPan> list=customQueryServiceLogic.getNSDLPanDetails(CustomSqlQuery.getNSDLPanReportackno, param);
				if(list.isEmpty()){
					returnData.put("message", "No Details Found!");
					returnData.put("status", "0");
				}else{
					returnData.put("report", list);
					returnData.put("status", "1");
				}
				}else {
					returnData.put("message", "Forbidden User!");
					returnData.put("status", "0");
				}
				
			}else {
				returnData.put("message", "Invalid Details!");
				returnData.put("status", "0");
			}
			
		}catch(Exception e){
			logger_log.error("changeStatus :::::::"+ e);
			returnData.put("message", "Exception! Please try again properly");
			returnData.put("status", "0");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> fetchnonpkguser(User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		List<User> list1=new ArrayList<User>();
		List<User> list2=new ArrayList<User>();
		try{
			List<User> list=new ArrayList<User>();
		if(userDetails.getRoleId()==1) {
		list=userDao.getUserByNamedQuery("GetAllUser", param);	
		}else {
		param.put("wlId", userDetails.getWlId());	
		list=userDao.getUserByNamedQuery("GetAllUserByReseller", param);		
		}
		
				if(list!=null){
					for(int i=0;i<list.size();i++){
						User u=list.get(i);
						//System.out.println(u.getUserId()+"==i="+i+"==list"+list.size());
						param.put("user_id", u.getUserId());
						List<AssignedPackage> alist=AssignedPackageDAO.getAssignedPackageByNamedQuery("getAssignedPackageByUser", param);
						//System.out.println(u.getUserId()+"==i="+i+"==list"+list.size());
						if(alist.isEmpty()){
							list1.add(u);
							//System.out.println(list1.size());
						}
						list2.addAll(list1);
					}
					returnData.put("listu", list1);
					returnData.put("status", "1");
				}
			
		}catch(Exception e){
			logger_log.error("fetchnonpkguser :::::::"+ e);
			returnData.put("message", "Exception! Please try again properly");
			returnData.put("status", "0");
		}
		return returnData;
	}

	
	@Override
	public Map<String, Object> getaepslogreport(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		List<AEPSLog> list ;
		try {
			if(userDetails.getRoleId()==1){
				param.put("start_date", request.get("startDate"));
				param.put("end_date", request.get("endDate"));
				param.put("status", "PENDING");
				//param.put("apiremarks", "EDIT");
				 list = aepslogdao.getAEPSLogByNamedQuery("getAEPSLogBystatus",param);
			}else{
			param.put("start_date", request.get("startDate"));
			param.put("end_date", request.get("endDate"));
			param.put("status", "PENDING");
			param.put("userId", userDetails.getUserId());
			 list = aepslogdao.getAEPSLogByNamedQuery("getAEPSLogBystatususer",param);
			}
		     if(list.size()>0){
		    	 returnData.put("aepslogreport",list); 
		    	 returnData.put("status","1"); 
		     }else{
		    	 returnData.put("message","No data available"); 
		    	 returnData.put("status","0");  
		    	 returnData.put("nextPage","home");  
		     }
		} catch (Exception e) {
			logger_log.error("getaepslogreport::::::::::::::::::::" + e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getP2AViewUser() {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try{
			
			List<P2AUserdetail> aepslist = customQueryServiceLogic.getP2AUserdetail(CustomSqlQuery.getP2AUser,parameters);
			if(!aepslist.isEmpty()){
				returnData.put("p2auserlist",aepslist);
				returnData.put("status","1");
			}else{
				returnData.put("message","No data available");
				returnData.put("status","0");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getAEPSViewUser::::::::::::::::::"+e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> updatebankt(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		UserBankDetails ub=UserBankDetailsDao.getUserByUserBankDetails(Integer.parseInt(request.get("id")));
		ub.setStatus(request.get("status"));
		boolean flag=UserBankDetailsDao.updateUserBankDetails(ub);
		if(flag){
			returnData.put("status","1");
			returnData.put("message","Done");
		}else{
			returnData.put("message","Failed");
			returnData.put("status","0");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> updateResellerdetails(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag=false;
		System.out.println(request);
		Reseller re=resellerDao.getResellerById(request.get("wlId"));
		System.out.println(re);
		re.setPoweredBy(request.get("poweredBy"));
		re.setPoweredByLink(request.get("poweredByLink"));
		re.setPageTitle(request.get("pageTitle"));
		flag=resellerDao.updateReseller(re);
		
		User resu=userDao.getUserByUserId(request.get("userId"));
		resu.setCity(request.get("city"));
		resu.setPinCode(request.get("pinCode"));
		resu.setFirmName(request.get("firmName"));
		resu.setAddress(request.get("address"));
		resu.setState(request.get("state"));
		resu.setMobile(request.get("mobile"));
		resu.setEmail(request.get("email"));
		resu.setName(request.get("name"));
		userDao.updateUser(resu);
		if(flag){
			returnData.put("status", "1");
			returnData.put("message", "Update successfully");
		}else{
			returnData.put("status", "0");
			returnData.put("message", "Update Failed");
		}
		return returnData;
	}

	

	
}
