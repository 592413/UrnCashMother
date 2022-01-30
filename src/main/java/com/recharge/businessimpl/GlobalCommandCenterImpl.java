package com.recharge.businessimpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
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
import org.springframework.web.multipart.MultipartFile;

import com.recharge.businessdao.AuthenticationCommandCenter;
import com.recharge.businessdao.CommissionService;
import com.recharge.businessdao.CustomQueryService;
import com.recharge.businessdao.CustomQueryServiceLogic;
import com.recharge.businessdao.GlobalCommandCenter;
import com.recharge.businessdao.PackageService;
import com.recharge.businessdao.SendRechargeRequest;
import com.recharge.customModel.BalanceRequest;
import com.recharge.customModel.ComplainDetails;
import com.recharge.customModel.DefaultCharge;
import com.recharge.customModel.DefaultCommission;
import com.recharge.customModel.ImpsReport;
import com.recharge.customModel.IndividualCharge;
import com.recharge.customModel.IndividualCommission;
import com.recharge.customModel.Notification;
import com.recharge.customModel.PanCouponReport;
import com.recharge.customModel.PanReport;
import com.recharge.customModel.RechargeReport;
import com.recharge.customModel.ViewAssignPackage;
import com.recharge.customModel.ViewUser;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.model.AEPSCommission;
import com.recharge.model.AEPSUserMap;
import com.recharge.model.Api;
import com.recharge.model.Apisetting;
import com.recharge.model.Apitrace;
import com.recharge.model.AssignedPackage;
import com.recharge.model.Balancerequest;
import com.recharge.model.Balancetransafer;
import com.recharge.model.Bankdetails;
import com.recharge.model.CardWalletRequest;
import com.recharge.model.Chargeset;
import com.recharge.model.Complain;
import com.recharge.model.DMRCommission;
import com.recharge.model.Defaultcommission;
import com.recharge.model.EarningSurcharge;
import com.recharge.model.Ezpayapplicationform;
import com.recharge.model.ImpsTransaction;
import com.recharge.model.Impscommission;
import com.recharge.model.Individualcharge;
import com.recharge.model.Individualcommission;
import com.recharge.model.Insurance;
import com.recharge.model.MicroatmUser;
import com.recharge.model.MicroatmUserNew;
import com.recharge.model.News;
import com.recharge.model.Operator;
import com.recharge.model.PackageDetail;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.PaymonkResponse;
import com.recharge.model.Rechargedetails;
import com.recharge.model.Reseller;
import com.recharge.model.SMSApiparameters;
import com.recharge.model.SettlementBalance;
import com.recharge.model.SettlementReport;
import com.recharge.model.Signinhistory;
import com.recharge.model.SmsCredential;
import com.recharge.model.User;
import com.recharge.model.UserBankDetails;
import com.recharge.model.Userattachment;
import com.recharge.model.Utility;
import com.recharge.model.webenquery;
import com.recharge.servicedao.AEPSCommissionDao;
import com.recharge.servicedao.AEPSUserMapDao;
import com.recharge.servicedao.ApiDao;
import com.recharge.servicedao.ApisettingDao;
import com.recharge.servicedao.ApitraceDao;
import com.recharge.servicedao.ApplicationDao;
import com.recharge.servicedao.AssignedPackageDAO;
import com.recharge.servicedao.BalancerequestDao;
import com.recharge.servicedao.BalancetransaferDao;
import com.recharge.servicedao.BankdetailsDao;
import com.recharge.servicedao.CardWalletRequestDao;
import com.recharge.servicedao.ChargesetDao;
import com.recharge.servicedao.ComplainDao;
import com.recharge.servicedao.DMRCommissionDao;
import com.recharge.servicedao.DefaultcommissionDao;
import com.recharge.servicedao.EarningSurchargeDao;
import com.recharge.servicedao.ImpscommissionDao;
import com.recharge.servicedao.IndividualChargeDao;
import com.recharge.servicedao.IndividualcommissionDao;
import com.recharge.servicedao.InsuranceDao;
import com.recharge.servicedao.MicroAtmResponseNewDao;
import com.recharge.servicedao.NewsDao;
import com.recharge.servicedao.OperatorDao;
import com.recharge.servicedao.PackageDetailDao;
import com.recharge.servicedao.PackageWiseChargeCommDao;
import com.recharge.servicedao.PaymonkResponseDao;
import com.recharge.servicedao.RechargedetailsDao;
import com.recharge.servicedao.ResellerDao;
import com.recharge.servicedao.SMSApiparametersDao;
import com.recharge.servicedao.SettlementBalanceDao;
import com.recharge.servicedao.SettlementReportDao;
import com.recharge.servicedao.SigninhistoryDao;
import com.recharge.servicedao.SmsCredentialDao;
import com.recharge.servicedao.UserBankDetailsDao;
import com.recharge.servicedao.UserDao;
import com.recharge.servicedao.UserattachmentDao;
import com.recharge.servicedao.UtilityDao;
import com.recharge.servicedao.WebEnquryDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.RoundNumber;
import com.recharge.utill.SMS;
import com.recharge.utill.SMS2;
import com.recharge.utill.UtilityClass;
import com.skyflight.model.Airline;
import com.skyflight.model.GstFlight;
import com.skyflight.servicedao.AirlineDao;
import com.skyflight.servicedao.GstFlightDao;
import com.recharge.servicedao.MicroatmuserNewDao;

@Service("globalCommandCenter")
public class GlobalCommandCenterImpl implements GlobalCommandCenter{
	private static final Logger logger_log = Logger.getLogger(GlobalCommandCenter.class);
	@Autowired UserDao userDao;
	@Autowired BalancetransaferDao balancetransaferDao;	
	@Autowired CustomQueryServiceLogic customQueryServiceLogic;
	@Autowired OperatorDao operatorDao;
	@Autowired DefaultcommissionDao defaultcommissionDao;
	@Autowired IndividualcommissionDao individualcommissionDao;
	@Autowired ChargesetDao chargesetDao;
	@Autowired IndividualChargeDao individualChargeDao;
	@Autowired NewsDao newsDao;
	@Autowired BankdetailsDao bankdetailsDao;
	@Autowired CustomQueryService customQueryService;
	@Autowired SigninhistoryDao signinhistoryDao;
	@Autowired CommissionService commissionService;
	@Autowired ApiDao apiDao;
	@Autowired ComplainDao complainDao;
	@Autowired SmsCredentialDao smsCredentialDao;
	@Autowired BalancerequestDao balancerequestDao;
	@Autowired AuthenticationCommandCenter authenticationCommandCenter;
	@Autowired UtilityDao utilityDao;
	@Autowired RechargedetailsDao rechargedetailsDao;
	@Autowired InsuranceDao insuranceDao;
	@Autowired ResellerDao resellerDao;
	@Autowired UserattachmentDao userattachmentDao;
	@Autowired ApisettingDao apisettingDao;
	@Autowired ApitraceDao apitraceDao;
	@Autowired SendRechargeRequest sendRechargeRequest;
	@Autowired EarningSurchargeDao earningSurchargeDao;
	@Autowired ImpscommissionDao impscommissionDao;
	@Autowired WebEnquryDao WebEnquryDao;
	@Autowired PackageDetailDao packagedetailDao;
	@Autowired PackageWiseChargeCommDao packwisechargecomm;
	@Autowired PackageService packageservice;
	@Autowired AssignedPackageDAO assignedPackage;
	@Autowired SMSApiparametersDao smsapiparamsdao;
	@Autowired DMRCommissionDao DMRCommissionDao;
	@Autowired AEPSCommissionDao aepscommissiondao;
	@Autowired AEPSUserMapDao aepsuserdao;
	@Autowired PaymonkResponseDao paymonkresponsedao;
	@Autowired SettlementReportDao settlementreportdao;
	@Autowired UserBankDetailsDao userbankdetailsdao;
	@Autowired ApplicationDao applicatiodao;
    @Autowired CardWalletRequestDao CardWalletRequestDao;
    @Autowired com.recharge.servicedao.MicroatmuserDao MicroatmuserDao;
    @Autowired SettlementBalanceDao SettlementBalanceDao;
    @Autowired MicroAtmResponseNewDao microatmresponsenew;
    @Autowired MicroatmuserNewDao MicroatmuserNewDao;
    @Autowired GstFlightDao GstFlightDao;
    @Autowired AirlineDao airlineDao;
    
    
	@Override
	public User getUserByUserId(String userId) {
		return userDao.getUserByUserId(userId);
	}
		
	@Override
	public Map<String, Object> editProfile(Map<String, String> request, User userDetail) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if(UtilityClass.checkStringIsNull(request.get("userId"))) {				
				if (userDetail.getUserId().equals(request.get("userId"))) {
					User userDetails = getUserByUserId(request.get("userId"));
					userDetails.setName(request.get("name"));
					userDetails.setFirmName(request.get("firmName"));
					userDetails.setEmail(request.get("email"));
					userDetails.setAddress(request.get("address"));
					userDetails.setPinCode(request.get("pinCode"));
					userDetails.setMobile(request.get("mobile"));
					userDetails.setState(request.get("state"));
					userDetails.setCity(request.get("city"));
					boolean flag = userDao.updateUser(userDetails);
					if (flag) {
						returnData.put("message", "Profile Edited Successfully!");
						returnData.put("status", "1");
					} else {
						returnData.put("message", "Failed to Update User profile");
						returnData.put("status", "0");
					}
				} else {
					returnData.put("message", "No Authority");
					returnData.put("status", "0");
				}
			}else {				
				returnData.put("message", "No Such User found");
				returnData.put("status", "0");
			}			
		}catch (Exception e) {
			logger_log.error("editProfile :::::::::::::: "+e);			
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	
	@Override
	public Map<String, Object> getImpsReport(Map<String, String> request, User userDetails) {
		Map<String, String> param = new HashMap<String, String>();
		Map<String, Object> param2 = null;
		Map<String, Object> returndata = new HashMap<String, Object>();
		List<ImpsReport> list = new ArrayList<>();
		List<ImpsReport> list2 = null;
		String msg = "";
		String msg1 = "";
		List<User> userList = new ArrayList<>();
		try {
			if (userDetails.getRoleId() == 1) {
				param = new HashMap<String, String>();
				param.put("start_date", request.get("start_date"));
				param.put("end_date", request.get("end_date"));
				list = customQueryServiceLogic.getImpsReport(CustomSqlQuery.getIMPSReportForAdmin, param);
			} else if (userDetails.getRoleId() == 2) {
				param2 = new HashMap<String, Object>();
				param2.put("wlId", userDetails.getWlId());
				userList = userDao.getUserByNamedQuery("GetAllUserByReseller", param2);
				for (User user : userList) {
					param = new HashMap<String, String>();
					param.put("start_date", request.get("start_date"));
					param.put("end_date", request.get("end_date"));
					param.put("username", user.getUserName());
					list2 = customQueryServiceLogic.getImpsReport(CustomSqlQuery.getIMPSReportForUser, param);
					list.addAll(list2);
				}
			} else if (userDetails.getRoleId() == 3) {
				param = new HashMap<String, String>();
				param.put("start_date", request.get("start_date"));
				param.put("end_date", request.get("end_date"));
				param.put("username", userDetails.getUserName());
				list = customQueryServiceLogic.getImpsReport(CustomSqlQuery.getIMPSReportForUser, param);
				param2 = new HashMap<String, Object>();
				param2.put("userId", userDetails.getUserName());
				userList = userDao.getUserByNamedQuery("getMyUserForSD", param2);
				for (User user : userList) {
					System.out.println(user.getUserName());
					param = new HashMap<String, String>();
					param.put("start_date", request.get("start_date"));
					param.put("end_date", request.get("end_date"));
					param.put("username", user.getUserName());
					list2 = customQueryServiceLogic.getImpsReport(CustomSqlQuery.getIMPSReportForUser, param);
					list.addAll(list2);
				}
			} else if (userDetails.getRoleId() == 4) {
				param = new HashMap<String, String>();
				param.put("start_date", request.get("start_date"));
				param.put("end_date", request.get("end_date"));
				param.put("username", userDetails.getUserName());
				list = customQueryServiceLogic.getImpsReport(CustomSqlQuery.getIMPSReportForUser, param);
				param2 = new HashMap<String, Object>();
				param2.put("uplineId", userDetails.getUserName());
				userList = userDao.getUserByNamedQuery("GetUserByUplineId", param2);
				for (User user : userList) {
					param = new HashMap<String, String>();
					param.put("start_date", request.get("start_date"));
					param.put("end_date", request.get("end_date"));
					param.put("username", user.getUserName());
					list2 = customQueryServiceLogic.getImpsReport(CustomSqlQuery.getIMPSReportForUser, param);
					list.addAll(list2);
				}

			} else if (userDetails.getRoleId() == 5) {
				param = new HashMap<String, String>();
				param.put("start_date", request.get("start_date"));
				param.put("end_date", request.get("end_date"));
				param.put("username", userDetails.getUserName());
				list = customQueryServiceLogic.getImpsReport(CustomSqlQuery.getIMPSReportForUser, param);
			}
			if(request.get("type")!=null){
				if (list.size() > 0) {
					returndata.put("status", "1");
				returndata.put("list", list);	
				}else{
					returndata.put("status", "0");
				returndata.put("message", "No report available");	
				}
			}else{
				if (list.size() > 0) {
					returndata.put("list", list);
					returndata.put("status", "1");
					returndata.put("message", "Report Fetch Successfully");
				}
					else{
						List<ImpsReport> list3 = new ArrayList<>();
						returndata.put("status", "0");
					returndata.put("list",list3);
					returndata.put("message", "No report available");
				}
				}

		} catch (Exception e) {
			logger_log.error("getImpsReport:::::::::::::::::::::" + e);
			returndata.put("message", e.getMessage());
		}
		return returndata;
	}
	
	@Override
	public Map<String, Object> getRBLAEPSReport(Map<String, String> request, User userDetail) {
		Map<String, Object> returndata = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try{
			if(!userDetail.getUserName().equalsIgnoreCase("admin")){
				System.out.println("HI:::::::::::::::::::::"+userDetail.getUserName());
				param.put("username", userDetail.getUserName());
				param.put("api","YesBank" );
				List<AEPSUserMap> aepsuserlist = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
				if(!aepsuserlist.isEmpty()){
					AEPSUserMap aeps = aepsuserlist.get(0);
					param = new HashMap<String, Object>();
					param.put("agentcode",aeps.getAgentcode());
					param.put("start_date",request.get("startDate"));
					param.put("end_date",request.get("endDate"));
					List<PaymonkResponse> rblreport = paymonkresponsedao.getPaymonkResponseByNamedQuery("getpaymonkresponsebyagentId",param);
					if(!rblreport.isEmpty()){
						returndata.put("status","1");
						returndata.put("rblReport",rblreport);	
					}else{
						returndata.put("status", "0");
						returndata.put("message", "No data available");		
					}
					
				}else{
					returndata.put("status", "0");
					returndata.put("message", "You are not YesBank REGISTERED USER");	
				}
			}else{
				/*param.put("start_date",request.get("startDate"));
				param.put("end_date",request.get("endDate"));*/
				parameters.put("start_date",request.get("startDate"));
				parameters.put("end_date",request.get("endDate"));
				List<PaymonkResponse> rblreport = customQueryServiceLogic.getPaymonkRespons(CustomSqlQuery.getAEPSReport,parameters);
				for(PaymonkResponse pay:rblreport){
					User newUser = 	getUserByUserId(pay.getUsername());
					pay.setName(newUser.getName());
					pay.setMobile(newUser.getMobile());
				}
				//List<PaymonkResponse> rblreport = paymonkresponsedao.getPaymonkResponseByNamedQuery("getpaymonkresponsebyadmin",param);
				if(!rblreport.isEmpty()){
					returndata.put("status", "1");
					returndata.put("rblReport",rblreport);	
				}else{
					returndata.put("status", "0");
					returndata.put("message", "No data available");		
				}	
			}
		}catch (Exception e) {
			logger_log.error("getRBLAEPSReport:::::::::::::::::::::" + e);
			returndata.put("message", e.getMessage());
		}
		return returndata;
	}
	
	@Override
	public Map<String, Object> getRBLSETTLEMENTReport(Map<String, String> request, User userDetail) {
		Map<String, Object> returndata = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();	
		Map<String, String> parameters = new HashMap<String, String>();	
		List<Object> list2 = null;
		try{
			List<SettlementReport> list = new ArrayList<>();
			if(!userDetail.getUserName().equalsIgnoreCase("admin")){
			param.put("username",userDetail.getUserName());
			param.put("start_date",request.get("startDate"));
			param.put("end_date",request.get("endDate"));
			list = settlementreportdao.getSettlementReportByNamedQuery("getSettlementreportbyusername",param);
			}else{
				
				double  totalBalance=customQueryServiceLogic.getTotalSettleWalletbalance(CustomSqlQuery.getTotalSettleWalletbalance,parameters);
				returndata.put("totalBalance",totalBalance);
				param.put("start_date",request.get("startDate"));
				param.put("end_date",request.get("endDate"));
				if(request.get("type").equals("ALL")){
				    list = settlementreportdao.getSettlementReportByNamedQuery("getSettlementreportbyadminwthOttype",param);
				}else{
					param.put("type",request.get("type"));
				    list = settlementreportdao.getSettlementReportByNamedQuery("getSettlementreportbyadmin",param);	
				}
				
			for(SettlementReport settle:list){
				User newUser = getUserByUserId(settle.getUsername());
				settle.setName(newUser.getName());
				settle.setMobile(newUser.getMobile());
				param = new HashMap<String, Object>();	
				param.put("username",settle.getUsername());
				returndata.put("type",request.get("type"));
				if(request.get("type").equalsIgnoreCase("Settle to Bank")){
					List<UserBankDetails> listubank=userbankdetailsdao.getUserBankDetailsByNamedQuery("getBankdetailsbyusername",param);
					if(listubank.isEmpty()){
						settle.setAccount("-");
						settle.setBank("-");
						settle.setBranch("-");
						settle.setIfsc("-");
					}else{
						UserBankDetails userbank =listubank.get(0);
						settle.setAccount(userbank.getAccount());
						settle.setBank(userbank.getBank());
						settle.setBranch(userbank.getBranch());
						settle.setIfsc(userbank.getIfsc());
					}
				
				}
			}
			}
			if(!list.isEmpty()){
				returndata.put("status","1");
				returndata.put("settlementreport",list);	
			}else{
				returndata.put("status","0");
				returndata.put("message","No data available");
			}
			
		}catch (Exception e) {
			logger_log.error("getRBLSETTLEMENTReport:::::::::::::::::::::" + e);
			returndata.put("message", e.getMessage());
		}
		return returndata;
	}

	@Override
	public Map<String, Object> changePass(Map<String, String> request, User userDetail) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if(UtilityClass.checkParameterIsNull(request)) {
				if (userDetail.getUserId().equals(request.get("userId"))) {
					if(!userDetail.getUserId().equals("Y0CDQ0")){
					if (request.get("newPassword").equals(request.get("cnfrmPassword"))) {
						User userDetails = getUserByUserId(request.get("userId"));
						if (userDetails != null) {
							if (userDetails.getPassword().equals(request.get("oldPassword"))) {
								userDetails.setPassword(request.get("newPassword"));
								boolean flag = userDao.updateUser(userDetails);
								if (flag) {
									returnData.put("message", "Password Change Successfully!");
									returnData.put("status", "1");
								} else {
									returnData.put("message", "Failed to Change Password!");
									returnData.put("status", "0");
								}
							} else {
								returnData.put("message", "Wrong Old password!");
								returnData.put("status", "0");
							}
						} else {
							returnData.put("message", "Invalid Details!");
							returnData.put("status", "0");
						}
					} else {
						returnData.put("message", "Confirm password and New Password must be same!");
						returnData.put("status", "0");
					}
				}else{
					returnData.put("message", "Cannot change debo id password!");
					returnData.put("status", "0");
				}
				} else {
					returnData.put("message", "Not A valid User!");
					returnData.put("status", "0");
				}
			} else {
				returnData.put("message", "Please Fill all the details!");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("changePass :::::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}		
		return returnData;
	}
	
	@Override
	public Map<String, Object> getNamebyUserId(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			if(UtilityClass.checkParameterIsNull(request)) {
				param.put("userId", request.get("userId"));
				param.put("wlId",userDetails.getWlId());
				List<User> userList = userDao.getUserByNamedQuery("GetUserByUserIdAndWlId", param);
				if(!userList.isEmpty()) {
					User user = userList.get(0);
					returnData.put("name", user.getName());
					returnData.put("status", "1");
				}else {
					returnData.put("name", "No User found!");
					returnData.put("status", "1");
				}
			} else {
				returnData.put("message", "Please Fill all the details!");
				returnData.put("status", "2");
			}
		} catch (Exception e) {
			logger_log.error("changePass :::::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("name", "No User Found");
			returnData.put("status", "2");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> addEnquery(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag = false;
		String msg = "";
		try{
		if (UtilityClass.checkParameterIsNull(request)) {
			webenquery webenquery=new webenquery(request.get("UserName"),request.get("Address"),request.get("mail_id"),request.get("mobile"),request.get("remark"), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
			 flag = WebEnquryDao.insertquery(webenquery);
			 if(flag){
				 msg = "Registration submit successfully..Wait 4-5 days to get your user credential!!! ";
					returnData.put("status", "1");
			 }else{
				 msg = "Information Submit Failed..!";
					returnData.put("status", "0");
			 }
			
		}else {
			flag = false;
			msg = "Please Fill all the details!";
			returnData.put("status", "0");
		}
		}catch (Exception e) {
			logger_log.error("addEnquery :::::::::::::: "+e);
			msg = "Technical Error! Please try After Sometime.";				
			returnData.put("status", "0");
		}
		returnData.put("message", msg);
		return returnData;
		}
	

	@Override
	public Map<String, Object> addUser(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		String msg = "";
		String uplineType = "";
		Integer userType = 0;
		String uplineId = "";
		String tranPin = "N/A";
		double balance = 0.0;
		boolean flag = false;
		double lockedAmount = 0;
		double op_bal = 0.0;
		double clBal = 0.0;
		try {
			String userId = GenerateRandomNumber.generateUserName();
			String password = GenerateRandomNumber.generatePassword();
			if (UtilityClass.checkParameterIsNull(request)) {	
					
				HashMap<String, String> param1 = new HashMap<String, String>();
				param1.put("mobile", request.get("mobile"));
				param1.put("email", request.get("mobile"));
				String userId1 = authenticationCommandCenter.getUserNamebyMobileEmail(CustomSqlQuery.getUserIdByMobileEmail, param1);
				if (userId1 == null) {
					param1 = new HashMap<String, String>();
					param1.put("mobile", request.get("email"));
					param1.put("email", request.get("email"));
					userId1 = authenticationCommandCenter.getUserNamebyMobileEmail(CustomSqlQuery.getUserIdByMobileEmail, param1);
					if (userId1 == null) {
						if (userDetails.getRoleId() == 1 || userDetails.getRoleId() == 2) {
							if (request.get("userType").equals("5")) {
								uplineType = "4";
							} else if (request.get("userType").equals("4")) {
								uplineType = "3";
							} else {
								if (userDetails.getRoleId() == 2) {
									uplineType = "2";
								} else {
									uplineType = "1";
								}
								if(request.get("userType").equals("100")){
									tranPin = GenerateRandomNumber.generatePassword();
								}
							}
							param = new HashMap<String, Object>();
							param.put("userId", request.get("uplineId"));
							param.put("wlId", userDetails.getWlId());
							param.put("roleId", Integer.parseInt(uplineType));
							List<User> userList = userDao.getUserByNamedQuery("GetUserByRoleIdUserIdAndWlId", param);
							if (!userList.isEmpty()) {
								userType = Integer.parseInt(request.get("userType"));
								uplineId = request.get("uplineId");
								flag = true;
							} else {
								msg = "Choose Downline Correctly!";
								returnData.put("status", "0");
								flag = false;
							}
						} else if (userDetails.getRoleId() == 3) {
							//User upline = userDao.getUserByUserId(userDetails.getUserId());
								userType = 4;
								uplineId = userDetails.getUserId();
								//flag = commissionService.updateBalance(upline.getUserId(), "BALANCE Transfer to"+userId, "JOINING", 300, "DEBIT",0);
								flag = true;
								
							
							
						} else if (userDetails.getRoleId() == 4) {
							flag = true;
							userType = 5;
							uplineId = userDetails.getUserId();
						} else {
							flag = false;
							msg = "You dont have authority to add user!";
							returnData.put("status", "0");
						}	
					}else {
						flag = false;
						msg = "Email Already exist!";
						returnData.put("status", "0");
					}
				
				} else {
					flag = false;
					msg = "Mobile Number Already exist!";
					returnData.put("status", "0");
				}
				
			} else {
				flag = false;
				msg = "Please Fill all the details!";
				returnData.put("status", "0");
			}			
			if(flag) {				
				String wl_id = userDetails.getWlId();				
				User user = new User(userId, password,userType, wl_id, userId, request.get("name"),request.get("firmName"), request.get("address"), request.get("city"),request.get("pinCode"),request.get("state"), "India",request.get("mobile"),uplineId, request.get("email"), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(), balance, "1", "0", tranPin,lockedAmount);
				boolean flag1 = userDao.insertUser(user);
				if(flag1) {
	
					/*-------------------SEND SMS TO USER ------------------------*/
					param = new HashMap<String, Object>();
					param.put("wlId", userDetails.getWlId());
					SmsCredential credential = smsCredentialDao.getSmsCredentialByNamedQuery("GetSmsCredentialByWlId", param).get(0);
					String sms = "Welcome to "+credential.getCompany()+",your account has been created with Mobile number:"+request.get("mobile")+" & Password:"+password+" Log on to :"+credential.getDomain()+"";
					//SMS.sendSMS(user.getMobile(), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());
					SMS.sendSMS2(user.getMobile(), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());
					/*-----------------------------------------------------------------*/
					msg = "User Added SuccessFully!";
					returnData.put("userId", userId);
					returnData.put("status", "1");
				}else {
					msg = "Unable to Add User";
					returnData.put("status", "0");
				}
			} else {				
				returnData.put("status", "0");
			}
		}catch (Exception e) {
			logger_log.error("addUser :::::::::::::: "+e);
			msg = "Technical Error! Please try After Sometime.";				
			returnData.put("status", "0");
		}		
		returnData.put("message", msg);			
		return returnData;
	}

	@Override
	public Map<String, Object> getUserByUserType(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String msg = "";		
		List<User> userList = null;
		String select = "";
		Map<String, Object> param = null;
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				String modelName = request.get("modelName") + ".userId";
				if (userDetails.getRoleId() == 1) {					
					param = new HashMap<String, Object>();
					param.put("roleId",Integer.parseInt(request.get("userType")));
					if(Integer.parseInt(request.get("userType")) == 2) {
						userList = userDao.getUserByNamedQuery("GetUserByRoleId", param);
					}else {
						param.put("wlId", userDetails.getWlId());
						userList = userDao.getUserByNamedQuery("GetUserByRoleIdAndWlId", param);
					}
				} else if (userDetails.getRoleId() == 2) {					
					param = new HashMap<String, Object>();					
					param.put("roleId",Integer.parseInt(request.get("userType")));
					param.put("wlId", userDetails.getWlId());
					userList = userDao.getUserByNamedQuery("GetUserByRoleIdAndWlId", param);
					
				} else if (userDetails.getRoleId() == 3) {					
					param = new HashMap<String, Object>();	
					if(Integer.parseInt(request.get("userType")) == 5) {
						userList = new ArrayList<User>();
						param.put("wlId", userDetails.getWlId());
						param.put("uplineId", userDetails.getUserId());
						List<User> disUser = userDao.getUserByNamedQuery("GetUserByUplineIdAndWlId", param);
						for(User dis : disUser) {
							param.put("roleId",Integer.parseInt(request.get("userType")));
							param.put("uplineId", dis.getUserId());
							List<User> retUser = userDao.getUserByNamedQuery("GetUserByRoleIdUplineIdAndWlId", param);
							if(!retUser.isEmpty()) {
								userList.addAll(retUser);
							}
						}
					}else {
						param.put("roleId",Integer.parseInt(request.get("userType")));
						param.put("wlId", userDetails.getWlId());
						param.put("uplineId", userDetails.getUserId());
						userList = userDao.getUserByNamedQuery("GetUserByRoleIdUplineIdAndWlId", param);
					}
					
				} else if (userDetails.getRoleId() == 4) {
					param = new HashMap<String, Object>();	
					param.put("roleId",Integer.parseInt(request.get("userType")));
					param.put("wlId", userDetails.getWlId());
					param.put("uplineId", userDetails.getUserId());
					userList = userDao.getUserByNamedQuery("GetUserByRoleIdUplineIdAndWlId", param);
				} else {
					returnData.put("status", "0");
					msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
							+ "<label for='email_address_2'>Select User </label>"
						+ "</div>"
						+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
						+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
						+ "<label for='email_address_2' class='danger'>Technical Error! Try After Sometime. </label>"
						+ "</div>"
						+ "</div>";
				}
				if(!userList.isEmpty()) {
					returnData.put("status", "1");					
					select +="<select ng-model='"+modelName+"' class='form-control'><option value='ALL' >ALL</option>";
					for(User user : userList) {
						select += "<option value='"+user.getUserId()+"'>"+user.getName()+" "+"("+user.getMobile()+")</option>";
					}
					select += "</select>";
					msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
							+ "<label for='email_address_2'>Select User </label>"
						+ "</div>"
						
						+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"+select+ "</div>";
				}else {
					returnData.put("status", "0");
					msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
							+ "<label for='email_address_2'>Select User </label>"
						+ "</div>"
						+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
						+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
						+ "<label for='email_address_2' class='text-danger'>No User Available. </label>"
						+ "</div>"
						+ "</div>";
				}
			}else {
				returnData.put("status", "0");
				msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
						+ "<label for='email_address_2'>Select User </label>"
					+ "</div>"
					+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
					+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
					+ "<label for='email_address_2' class='text-danger'>Technical Error! Try After Sometime. </label>"
					+ "</div>"
					+ "</div>";
			}
			
		} catch (Exception e) {
			returnData.put("status", "0");
			logger_log.error("getUserByUserType :::::::::::::: " + e);
			msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
					+ "<label for='email_address_2'>Select User </label>"
				+ "</div>"
				+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
				+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
				+ "<label for='email_address_2' class='text-danger'>Technical Error! Try After Sometime. </label>"
				+ "</div>"
				+ "</div>";
			returnData.put("status", "0");

		}
		returnData.put("message", msg);
		return returnData;
	}
	
	
	@Override
	public Map<String, Object> getUserByUserType1(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String msg = "";		
		List<User> userList = null;
		String select = "";
		Map<String, Object> param = null;
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				String modelName = request.get("modelName") + ".userId";
				if (userDetails.getRoleId() == 1) {					
					param = new HashMap<String, Object>();
					param.put("roleId",Integer.parseInt(request.get("userType")));
					if(Integer.parseInt(request.get("userType")) == 2) {
						userList = userDao.getUserByNamedQuery("GetUserByRoleId", param);
					}else {
						param.put("wlId", userDetails.getWlId());
						userList = userDao.getUserByNamedQuery("GetUserByRoleIdAndWlId", param);
					}
				} else if (userDetails.getRoleId() == 2) {					
					param = new HashMap<String, Object>();					
					param.put("roleId",Integer.parseInt(request.get("userType")));
					param.put("wlId", userDetails.getWlId());
					userList = userDao.getUserByNamedQuery("GetUserByRoleIdAndWlId", param);
					
				} else if (userDetails.getRoleId() == 3) {					
					param = new HashMap<String, Object>();	
					if(Integer.parseInt(request.get("userType")) == 5) {
						userList = new ArrayList<User>();
						param.put("wlId", userDetails.getWlId());
						param.put("uplineId", userDetails.getUserId());
						List<User> disUser = userDao.getUserByNamedQuery("GetUserByUplineIdAndWlId", param);
						for(User dis : disUser) {
							param.put("roleId",Integer.parseInt(request.get("userType")));
							param.put("uplineId", dis.getUserId());
							List<User> retUser = userDao.getUserByNamedQuery("GetUserByRoleIdUplineIdAndWlId", param);
							if(!retUser.isEmpty()) {
								userList.addAll(retUser);
							}
						}
					}else {
						param.put("roleId",Integer.parseInt(request.get("userType")));
						param.put("wlId", userDetails.getWlId());
						param.put("uplineId", userDetails.getUserId());
						userList = userDao.getUserByNamedQuery("GetUserByRoleIdUplineIdAndWlId", param);
					}
					
				} else if (userDetails.getRoleId() == 4) {
					param = new HashMap<String, Object>();	
					param.put("roleId",Integer.parseInt(request.get("userType")));
					param.put("wlId", userDetails.getWlId());
					param.put("uplineId", userDetails.getUserId());
					userList = userDao.getUserByNamedQuery("GetUserByRoleIdUplineIdAndWlId", param);
				} else {
					returnData.put("status", "0");
					msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
							+ "<label for='email_address_2'>Select User </label>"
						+ "</div>"
						+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
						+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
						+ "<label for='email_address_2' class='danger'>Technical Error! Try After Sometime. </label>"
						+ "</div>"
						+ "</div>";
				}
				if(!userList.isEmpty()) {
					returnData.put("status", "1");					
					select +="<select ng-model='"+modelName+"' class='form-control'><option value='0'>All</option>";
					for(User user : userList) {
						select += "<option value='"+user.getUserId()+"'>"+user.getName()+" "+"("+user.getMobile()+")</option>";
					}
					select += "</select>";
					msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
							+ "<label for='email_address_2'>Select User </label>"
						+ "</div>"
						
						+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8 form-line1 focused'>"+select+ "</div>";
				}else {
					returnData.put("status", "0");
					msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
							+ "<label for='email_address_2'>Select User </label>"
						+ "</div>"
						+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
						+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
						+ "<label for='email_address_2' class='text-danger'>No User Available. </label>"
						+ "</div>"
						+ "</div>";
				}
			}else {
				returnData.put("status", "0");
				msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
						+ "<label for='email_address_2'>Select User </label>"
					+ "</div>"
					+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
					+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
					+ "<label for='email_address_2' class='text-danger'>Technical Error! Try After Sometime. </label>"
					+ "</div>"
					+ "</div>";
			}
			
		} catch (Exception e) {
			returnData.put("status", "0");
			logger_log.error("getUserByUserType :::::::::::::: " + e);
			msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
					+ "<label for='email_address_2'>Select User </label>"
				+ "</div>"
				+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
				+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
				+ "<label for='email_address_2' class='text-danger'>Technical Error! Try After Sometime. </label>"
				+ "</div>"
				+ "</div>";
			returnData.put("status", "0");

		}
		returnData.put("message", msg);
		return returnData;
	}
	
	@Override
	public Map<String, Object> addBalance(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();		
		String msg = "";
		String status = "0";		
		double toOpBal = 0.0;
		double toClBal = 0.0;
		double fromOpBal = 0.0;
		double fromClBal = 0.0;
		try {System.out.println(request);
			if(request.containsKey("remarks")){
				if(!request.get("remarks").equals("")){
			Date date = Calendar.getInstance().getTime();
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String today = formatter.format(date);
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
			String currentTime = sdf.format(cal.getTime());
			if (UtilityClass.checkParameterIsNull(request)) {
				User toUser = userDao.getUserByUserId(request.get("userId"));				
				if(toUser != null) {
					if(Double.parseDouble(request.get("amount")) < 1) {
						status = "0";
						msg = "Invalid Amount!";
					}else {	
						parameters = new HashMap<String, String>();						
						parameters.put("fromId", userDetails.getUserId());
						parameters.put("toId", toUser.getUserId());
						parameters.put("transferAmount", request.get("amount"));
						parameters.put("date", GenerateRandomNumber.getCurrentDate());
						long timeDiff = customQueryServiceLogic.getAddBalanceLock(CustomSqlQuery.getAddBalanceLock, parameters);
						if (timeDiff > 10) {
							if (userDetails.getRoleId() == 1) {
								parameters = new HashMap<String, String>();
								parameters.put("userId", toUser.getUserId());
								toOpBal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
								toClBal = toOpBal + Double.parseDouble(request.get("amount"));
								boolean flag1 = commissionService.updateBalance(toUser.getUserId(),	"BALANCE CREDITED BY SUPER ADMIN", request.get("remarks"),Double.parseDouble(request.get("amount")), "CREDIT",0);
								if (flag1) {
									Balancetransafer balancetransafer = new Balancetransafer("admin", 0.0, Double.parseDouble(request.get("amount")), 0.0, toUser.getUserId(), toOpBal, Double.parseDouble(request.get("amount")), toClBal, request.get("remarks"), "BALANCE CREDITED BY SUPER ADMIN", today, currentTime, toUser.getWlId());
									balancetransaferDao.insertBalanceTransfer(balancetransafer);									
									status = "1";
									msg = "Balance Added Successfully";
								} else {
									status = "0";
									msg = "Unable to Add Balance!";
								}
							} else if (userDetails.getRoleId() == 2) {
								if(userDetails.getStatus().equals("1")){
									status = "0";
									msg = "YOur ID is Inactive";
								
								}else{
								User fromUser = userDao.getUserByUserId(userDetails.getUserId());
								parameters = new HashMap<String, String>();	
								parameters.put("userId", fromUser.getUserId());
								fromOpBal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
								fromClBal = fromOpBal - Double.parseDouble(request.get("amount"));
								if (fromClBal > fromUser.getLockedAmount()) {
									boolean flag1 = commissionService.updateBalance(fromUser.getUserId(),"BALANCE Transfer to " + toUser.getMobile(), request.get("remarks"),Double.parseDouble(request.get("amount")), "DEBIT",0);
									if (flag1) {
										parameters = new HashMap<String, String>();
										parameters.put("userId", toUser.getUserId());
										toOpBal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
										toClBal = toOpBal + Double.parseDouble(request.get("amount"));
										boolean flag2 = commissionService.updateBalance(toUser.getUserId(),	"BALANCE CREDITED BY ADMIN", request.get("remarks"),Double.parseDouble(request.get("amount")), "CREDIT",0);
										if (flag2) {
											Balancetransafer balancetransafer = new Balancetransafer(fromUser.getUserId(), fromOpBal,Double.parseDouble(request.get("amount")), fromClBal, toUser.getUserId(), toOpBal, Double.parseDouble(request.get("amount")), toClBal,	request.get("remarks"), "BALANCE CREDITED BY ADMIN", today, currentTime, toUser.getWlId());
											balancetransaferDao.insertBalanceTransfer(balancetransafer);
											status = "1";
											msg = "Balance Added Successfully";
										} else {
											status = "0";
											msg = "Unable to Add Balance!";
										}
									} else {
										status = "0";
										msg = "Unable to Add Balance!";
									}
								} else {
									status = "0";
									msg = "Insufficient Balance!";
								}
								}
							} else if (userDetails.getRoleId() == 3) {
								if(userDetails.getStatus().equals("1")){
									status = "0";
									msg = "YOur ID is Inactive";
								
								}else{
								User fromUser = userDao.getUserByUserId(userDetails.getUserId());
								parameters =  new HashMap<String, String>();
								parameters.put("userId", fromUser.getUserId());
								fromOpBal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
								fromClBal = fromOpBal - Double.parseDouble(request.get("amount"));
								if (fromClBal > fromUser.getLockedAmount()) {
									boolean flag1 = commissionService.updateBalance(fromUser.getUserId(),"BALANCE Transfer to " + toUser.getMobile(), request.get("remarks"),Double.parseDouble(request.get("amount")), "DEBIT",0);
									if (flag1) {
										parameters = new HashMap<String, String>();
										parameters.put("userId", toUser.getUserId());
										toOpBal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
										toClBal = toOpBal + Double.parseDouble(request.get("amount"));
										boolean flag2 = commissionService.updateBalance(toUser.getUserId(), "BALANCE CREDITED BY SUPER DISTRIBUTOR", request.get("remarks"), Double.parseDouble(request.get("amount")), "CREDIT",0);
										if (flag2) {
											Balancetransafer balancetransafer = new Balancetransafer(fromUser.getUserId(), fromOpBal,Double.parseDouble(request.get("amount")), fromClBal,toUser.getUserId(), toOpBal,Double.parseDouble(request.get("amount")), toClBal, request.get("remarks"), "BALANCE CREDITED BY SUPER DISTRIBUTOR", today, currentTime, toUser.getWlId());
											balancetransaferDao.insertBalanceTransfer(balancetransafer);
											status = "1";
											msg = "Balance Added Successfully";
										} else {
											status = "0";
											msg = "Unable to Add Balance!";
										}
									} else {
										status = "0";
										msg = "Unable to Add Balance!";
									}
								} else {
									status = "0";
									msg = "Insufficient Balance!";
								}
								}
							} else if (userDetails.getRoleId() == 4) {
								if(userDetails.getStatus().equals("1")){
									status = "0";
									msg = "YOur ID is Inactive";
								
								}else{
								User fromUser = userDao.getUserByUserId(userDetails.getUserId());
								parameters = new HashMap<String, String>();
								parameters.put("userId", fromUser.getUserId());
								fromOpBal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
								fromClBal = fromOpBal - Double.parseDouble(request.get("amount"));
								if (fromClBal > fromUser.getLockedAmount()) {
									boolean flag1 = commissionService.updateBalance(fromUser.getUserId(), "BALANCE Transfer to " + toUser.getMobile(), request.get("remarks"), Double.parseDouble(request.get("amount")), "DEBIT",0);
									if (flag1) {
										parameters = new HashMap<String, String>();
										parameters.put("userId", toUser.getUserId());
										toOpBal = customQueryServiceLogic.getClosingBalanceForUser( CustomSqlQuery.getClosingBalanceForUser, parameters);
										toClBal = toOpBal + Double.parseDouble(request.get("amount"));
										boolean flag2 = commissionService.updateBalance(toUser.getUserId(), "BALANCE CREDITED BY DISTRIBUTOR", request.get("remarks"),Double.parseDouble(request.get("amount")), "CREDIT",0);
										if (flag2) {
											Balancetransafer balancetransafer = new Balancetransafer(fromUser.getUserId(), fromOpBal,Double.parseDouble(request.get("amount")), fromClBal,toUser.getUserId(), toOpBal,Double.parseDouble(request.get("amount")), toClBal,request.get("remarks"), "BALANCE CREDITED BY ADMIN", today,currentTime, toUser.getWlId());
											balancetransaferDao.insertBalanceTransfer(balancetransafer);
											status = "1";
											msg = "Balance Added Successfully";
										} else {
											status = "0";
											msg = "Unable to Add Balance!";
										}
									} else {
										status = "0";
										msg = "Unable to Add Balance!";
									}
								} else {
									status = "0";
									msg = "Insufficient Balance!";
								}
								}
							} else {
								status = "0";
								msg = "Forbidden To Transfer Balance!";
							}
						} else {
							status = "0";
							msg = "Please try after 10 minutes!";
						}
						
						if(status.equals("1")){
							/*-------------------SEND SMS TO USER ------------------------*/
							HashMap<String, Object> param = new HashMap<String, Object>();
							param.put("wlId", toUser.getWlId());
							SmsCredential credential = smsCredentialDao.getSmsCredentialByNamedQuery("GetSmsCredentialByWlId", param).get(0);
							String sms = "Dear Partner, Your Account "+toUser.getMobile()+" has been credited with INR "+Double.parseDouble(request.get("amount"))+" on "+GenerateRandomNumber.getCurrentDate()+" Your current Balance is Rs. "+toClBal+",Thank You";
							SMS.sendSMS2(toUser.getMobile(), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());
								//new SMS().sendSMS(toUser.getMobile(), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword(),params);
							/*-----------------------------------------------------------------*/
							
						}
						
					}
				}else {
					status = "0";
					msg = "Invalid User Details!";
				}			
			}else {				
				msg = "Please Fill all the details!";
				returnData.put("status", "0");
			}
				}else{				
					msg = "Please Fill Remark!";
					returnData.put("status", "0");
				}
		}else{				
			msg = "Please Fill Remark!";
			returnData.put("status", "0");
		}
			returnData.put("message", msg);
			returnData.put("status", status);
		}catch (Exception e) {
			logger_log.error("addBalance :::::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");			
			returnData.put("status", "0");
		}
		return returnData;
	}

	
	
	@Override
	public Map<String, Object> getMyDiscount(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			
			parameters.put("userId", request.get("userId"));
			List<IndividualCommission> list = customQueryServiceLogic.getIndividualCommission(CustomSqlQuery.getIndividualDiscount, parameters);
			if (list == null || list.isEmpty()) {
				User user = userDao.getUserByUserId(request.get("userId"));
				Integer roleId = user.getRoleId();
				parameters.put("userId", user.getUserId());
				List<DefaultCommission> dcList = customQueryServiceLogic.getMyDefaultCommission(CustomSqlQuery.getMyDefaultDiscount, parameters);
				if (dcList == null || dcList.isEmpty()) {
					list = new ArrayList<IndividualCommission>();
					List<Operator> operatorlIst = operatorDao.getAllOperator();
					for (Operator op : operatorlIst) {
						IndividualCommission dc = new IndividualCommission();
						dc.setOperatorId(op.getOperatorId());
						dc.setUserName(request.get("username"));
						dc.setCommissionId(0);
						dc.setServiceProvider(op.getServiceProvider());
						list.add(dc);
					}
				} else {
					list = new ArrayList<IndividualCommission>();
					for (DefaultCommission defaultCommission : dcList) {
						IndividualCommission dc = new IndividualCommission();
						dc.setOperatorId(defaultCommission.getOperatorId());
						dc.setUserName(defaultCommission.getUserId());
						if (roleId == 3) {
							dc.setCommission(defaultCommission.getSuperDistributor());
						} else if (roleId == 4) {
							dc.setCommission(defaultCommission.getDistributor());
						} else {
							dc.setCommission(defaultCommission.getRetailer());
						}
						dc.setServiceProvider(defaultCommission.getServiceProvider());
						list.add(dc);
					}
				}
			}
			returnData.put("commissionList", list);
			returnData.put("status", "1");
		}catch (Exception e) {
			logger_log.error("getDefaultCommission ::::::::::: "+e);			
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	/* (non-Javadoc)
	 * @see com.recharge.businessdao.GlobalCommandCenter#getDefaultCommission(java.util.Map)
	 */
	@Override
	public Map<String, Object> getDefaultCommission(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			// For default Discount 
			parameters.put("userId", request.get("username"));
			List<DefaultCommission> list = customQueryServiceLogic.getMyDefaultCommission(CustomSqlQuery.getMyDefaultDiscount, parameters);
			if (list == null || list.isEmpty()) {
				list = new ArrayList<DefaultCommission>();
				List<Operator> operatorlIst = operatorDao.getAllOperator();
				for (Operator op : operatorlIst) {
					DefaultCommission dc = new DefaultCommission();
					dc.setOperatorId(op.getOperatorId());
					dc.setUserId(request.get("username"));
					dc.setCommissionId(0);
					dc.setServiceProvider(op.getServiceProvider());
					list.add(dc);
				}
			}
			
			// My Discount 			
			parameters.put("userId", request.get("username"));
			List<IndividualCommission> list1 = customQueryServiceLogic.getIndividualCommission(CustomSqlQuery.getIndividualDiscount, parameters);
			if(list1 == null ||list1.isEmpty()) {
				list1 = new ArrayList<IndividualCommission>();
				List<Operator> operatorlIst = operatorDao.getAllOperator();
				for(Operator op : operatorlIst) {
					IndividualCommission ic = new IndividualCommission();
					ic.setOperatorId(op.getOperatorId());				
					ic.setUserName(request.get("username"));
					ic.setCommissionId(0);				
					ic.setServiceProvider(op.getServiceProvider());			
					list1.add(ic);
				}
			}
			
			returnData.put("commissionList", list);
			returnData.put("myComission", list1);
			returnData.put("status", "1");
		}catch (Exception e) {
			logger_log.error("getDefaultCommission ::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		return returnData;
	}

	/* (non-Javadoc)
	 * @see com.recharge.businessdao.GlobalCommandCenter#setDefaultDiscount(java.util.Map, com.recharge.model.User)
	 */
	@Override
	public Map<String, Object> setDefaultDiscount(DefaultCommission[] request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag = false;		
		try {
			for(DefaultCommission dc : request) {
				Defaultcommission defaultcommission = null;
				if(dc.getCommissionId() == 0) {
					defaultcommission = new Defaultcommission(dc.getUserId(), dc.getOperatorId(), dc.getSuperDistributor(), dc.getDistributor(), dc.getRetailer());
					flag = defaultcommissionDao.insertDefaultCommission(defaultcommission);
				}else {
					defaultcommission = new Defaultcommission(dc.getUserId(), dc.getOperatorId(), dc.getSuperDistributor(), dc.getDistributor(), dc.getRetailer());
					defaultcommission.setCommissionId(dc.getCommissionId());
					flag = defaultcommissionDao.updateDefaultCommission(defaultcommission);
				}
			}			
		} catch (Exception e) {
			logger_log.error("setDefaultDiscount ::::::::::: "+e);			
			returnData.put("message", "Exception! Please try again!");		
			returnData.put("status", "0");
			return returnData;
		}
		if(flag) {
			returnData.put("message", "Update Successfully!");		
			returnData.put("status", "1");			
		} else {
			returnData.put("message", "Failed to Update!");		
			returnData.put("status", "1");	
		}
		return returnData;
	}

	/* (non-Javadoc)
	 * @see com.recharge.businessdao.GlobalCommandCenter#getIndividualDiscount(java.util.Map)
	 */
	@Override
	public Map<String, Object> getIndividualDiscount(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			if(request.get("username") == null || request.get("username").equals("")){
				returnData.put("message", "Please choose username properly!");		
				returnData.put("status", "0");
			} else {
				
				parameters.put("userId", request.get("username"));
				List<IndividualCommission> list = customQueryServiceLogic.getIndividualCommission(CustomSqlQuery.getIndividualDiscount, parameters);
				if(list == null ||list.isEmpty()) {
					list = new ArrayList<IndividualCommission>();
					List<Operator> operatorlIst = operatorDao.getAllOperator();
					for(Operator op : operatorlIst) {
						IndividualCommission ic = new IndividualCommission();
						ic.setOperatorId(op.getOperatorId());				
						ic.setUserName(request.get("username"));
						ic.setCommissionId(0);				
						ic.setServiceProvider(op.getServiceProvider());			
						list.add(ic);
					}
				}
				
				// My Discount 			
				parameters = new HashMap<String, String>();
				parameters.put("userId", userDetails.getUserId());
				List<IndividualCommission> list1 = customQueryServiceLogic.getIndividualCommission(CustomSqlQuery.getIndividualDiscount, parameters);
				if(list1 == null ||list1.isEmpty()) {
					list1 = new ArrayList<IndividualCommission>();
					List<Operator> operatorlIst = operatorDao.getAllOperator();
					for(Operator op : operatorlIst) {
						IndividualCommission ic = new IndividualCommission();
						ic.setOperatorId(op.getOperatorId());				
						ic.setUserName(request.get("username"));
						ic.setCommissionId(0);				
						ic.setServiceProvider(op.getServiceProvider());			
						list1.add(ic);
					}
				}
				returnData.put("commissionList", list);
				returnData.put("myComission", list1);
				returnData.put("status", "1");
			}
		
		}catch (Exception e) {
			logger_log.error("getIndividualDiscount ::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		return returnData;
	}

	/* (non-Javadoc)
	 * @see com.recharge.businessdao.GlobalCommandCenter#setIndividualDiscount(com.recharge.customModel.IndividualCommission[], com.recharge.model.User)
	 */
	@Override
	public Map<String, Object> setIndividualDiscount(IndividualCommission[] request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();;
		boolean flag = false;		
		try {
			for(IndividualCommission ic : request) {
				Individualcommission individualcommission = null;
				if(ic.getCommissionId() == 0) {
					individualcommission = new Individualcommission(ic.getUserName(), ic.getOperatorId(), ic.getCommission());
					flag = individualcommissionDao.insertIndividualCommission(individualcommission);
				}else {
					individualcommission = new Individualcommission(ic.getUserName(), ic.getOperatorId(), ic.getCommission());
					individualcommission.setCommissionId(ic.getCommissionId());
					flag = individualcommissionDao.updateIndividualCommission(individualcommission);
				}
			}			
		} catch (Exception e) {
			logger_log.error("setIndividualDiscount ::::::::::: "+e);			
			returnData.put("message", "Exception! Please try again!");		
			returnData.put("status", "0");
			return returnData;
		}
		if(flag) {
			returnData.put("message", "Update Successfully!");		
			returnData.put("status", "1");			
		} else {
			returnData.put("message", "Failed to Update!");		
			returnData.put("status", "1");	
		}
		return returnData;
	}

	/* (non-Javadoc)
	 * @see com.recharge.businessdao.GlobalCommandCenter#getAllOperator()
	 */
	@Override
	public List<Operator> getAllOperator() {
		try {
			List<Operator> operatorlist = operatorDao.getAllOperator();
			return operatorlist;
		}catch (Exception e) {
			logger_log.error("getAllOperator ::::::::::: "+e);	
		}
		return null;
	}
	
	@Override
	public News getNews(String wlId) {
		News news = new News();
		List<News> list = new ArrayList<>();
		try{
			Map<String, Object> param = new HashMap<>();
			param.put("wlId", wlId);
			list = newsDao.getNewsByNamedQuery("GetNewsByWlId", param );
			if(list.isEmpty()){
				news = new News();
				news.setNewsid(0);
				news.setNews("Welcome User!");
				news.setWlId(wlId);		
			} else {
				news = list.get(0);
			}
		} catch (Exception e) {
			news = new News();
			news.setNewsid(0);
			news.setNews("Welcome User!");
			news.setWlId(wlId);			
			logger_log.error("getNews ::::::::::: "+e);	
		}
		return news;
	}

	/* (non-Javadoc)
	 * @see com.recharge.businessdao.GlobalCommandCenter#getDefaultChargeSetting(java.util.Map)
	 */
	@Override
	public Map<String, Object> getDefaultChargeSetting(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			if (userDetails.getRoleId() == 1 || userDetails.getRoleId() == 2) {
				parameters.put("userId", request.get("username"));
				List<DefaultCharge> list = customQueryServiceLogic.getMyDefaultCharge(CustomSqlQuery.getDefaultCharge, parameters);
				if (list == null || list.isEmpty()) {
					list = new ArrayList<DefaultCharge>();
					List<Operator> operatorlIst = operatorDao.getAllOperator();
					for (Operator op : operatorlIst) {
						DefaultCharge dc = new DefaultCharge(0,userDetails.getWlId(),userDetails.getUserId(),op.getOperatorId(),0.0,0.0,0.0,"0",op.getServiceProvider());
						list.add(dc);
					}
				}
				returnData.put("chargeList", list);
				returnData.put("status", "1");
			} else {
				returnData.put("message", "No Authority to Set!");
				returnData.put("status", "0");
			}
		}catch (Exception e) {
			logger_log.error("getDefaultChargeSetting ::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		return returnData;
	}

	/* (non-Javadoc)
	 * @see com.recharge.businessdao.GlobalCommandCenter#setDefaultChargeSetting(com.recharge.customModel.DefaultCharge[], com.recharge.model.User)
	 */
	@Override
	public Map<String, Object> setDefaultChargeSetting(DefaultCharge[] request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag = false;		
		try {
			for(DefaultCharge dc : request) {
				Chargeset defaultCharge = null;
				if(dc.getChargeId() == 0) {
					defaultCharge = new Chargeset(userDetails.getWlId(),userDetails.getUserId(),dc.getOperatorId(),dc.getSdist(),dc.getDist(),dc.getRet(),dc.getFlag());
					flag = chargesetDao.insertChargeSet(defaultCharge);
				}else {
					defaultCharge =  new Chargeset(userDetails.getWlId(),userDetails.getUserId(),dc.getOperatorId(),dc.getSdist(),dc.getDist(),dc.getRet(),dc.getFlag());
					defaultCharge.setChargeId(dc.getChargeId());
					flag = chargesetDao.updateChargeSet(defaultCharge);
				}
			}			
		} catch (Exception e) {
			logger_log.error("setDefaultChargeSetting ::::::::::: "+e);			
			returnData.put("message", "Exception! Please try again!");		
			returnData.put("status", "0");
			return returnData;
		}
		if(flag) {
			returnData.put("message", "Update Successfully!");		
			returnData.put("status", "1");			
		} else {
			returnData.put("message", "Failed to Update!");		
			returnData.put("status", "1");	
		}
		return returnData;
	}

	/* (non-Javadoc)
	 * @see com.recharge.businessdao.GlobalCommandCenter#getIndividualCharge(java.util.Map)
	 */
	@Override
	public Map<String, Object> getIndividualCharge(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			if(request.get("userId") == null || request.get("userId").equals("")){
				returnData.put("message", "Please choose username properly!");		
				returnData.put("status", "0");
			} else {
				parameters.put("userId", request.get("userId"));
				List<IndividualCharge> list = customQueryServiceLogic.getIndividualCharge(CustomSqlQuery.getIndividualCharge, parameters);
				if(list == null ||list.isEmpty()) {
					list = new ArrayList<IndividualCharge>();
					List<Operator> operatorlIst = operatorDao.getAllOperator();
					for(Operator op : operatorlIst) {
						IndividualCharge ic = new IndividualCharge(0,request.get("userId"),0.0,0.0,"0",userDetails.getWlId(),op.getServiceProvider(), op.getOperatorId());						
						list.add(ic);
					}
				}
				returnData.put("chargeList", list);
				returnData.put("status", "1");
			}
		}catch (Exception e) {
			logger_log.error("getIndividualCharge ::::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		return returnData;
	}

	/* (non-Javadoc)
	 * @see com.recharge.businessdao.GlobalCommandCenter#setIndividualCharge(com.recharge.customModel.IndividualCharge[], com.recharge.model.User)
	 */
	@Override
	public Map<String, Object> setIndividualCharge(IndividualCharge[] request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();		
		boolean flag = false;		
		try {
			for(IndividualCharge ic : request) {
				Individualcharge individualcharge = null;
				if(ic.getId() == 0) {
					individualcharge = new Individualcharge(ic.getUserId(),ic.getRupees(),ic.getPercentage(),ic.getFlag(),ic.getWlId(),ic.getOperatorId());
					flag = individualChargeDao.insertIndividualcharge(individualcharge);
				}else {
					individualcharge = new Individualcharge(ic.getUserId(),ic.getRupees(),ic.getPercentage(),ic.getFlag(),ic.getWlId(),ic.getOperatorId());
					individualcharge.setId(ic.getId());
					flag = individualChargeDao.updateIndividualcharge(individualcharge);
				}
			}			
		} catch (Exception e) {
			logger_log.error("setIndividualCharge ::::::::::: "+e);			
			returnData.put("message", "Exception! Please try again!");		
			returnData.put("status", "0");
			return returnData;
		}
		if(flag) {
			returnData.put("message", "Update Successfully!");		
			returnData.put("status", "1");			
		} else {
			returnData.put("message", "Failed to Update!");		
			returnData.put("status", "1");	
		}
		
	
		return returnData;
	}
	
	@Override
	public Map<String, Object> getViewUserDetails(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();		
		Map<String, String> param = null;
		
		List<ViewUser> userList = new ArrayList<>();
		List<ViewUser> userList1 = new ArrayList<>();
		
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if(userDetails.getRoleId() == 1){
					if(request.get("userType").equals("0")){
						param = new HashMap<String, String>();
						userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.GetAllUserByAdmin, param);
						//userList = userDao.getUserByNamedQuery("GetAllUserByAdmin", param );
					} else if (request.get("userType").equals("2")){
						param = new HashMap<String, String>();	
						userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.getResellerByAdmin, param);
						//userList = userDao.getUserByNamedQuery("getResellerByAdmin", param );
					}else {
						param = new HashMap<String, String>();			
						param.put("roleId",request.get("userType"));
						param.put("wlId", userDetails.getWlId());
						userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.GetUserByRoleIdAndWlId, param);
						//userList = userDao.getUserByNamedQuery("GetUserByRoleIdAndWlId", param );
					}
				} else if(userDetails.getRoleId() == 2){
					if(request.get("userType").equals("0")){
						param = new HashMap<String, String>();		
						param.put("wlId", userDetails.getWlId());
						userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.GetAllUserByReseller, param);
						//userList = userDao.getUserByNamedQuery("GetAllUserByReseller", param );
					} else {
						param = new HashMap<String, String>();			
						param.put("roleId", request.get("userType"));
						param.put("wlId", userDetails.getWlId());
						userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.GetUserByRoleIdAndWlId, param);
						//userList = userDao.getUserByNamedQuery("GetUserByRoleIdAndWlId", param );
					}
				} else if(userDetails.getRoleId() == 5){
					returnData.put("message", "No User Found!");		
					returnData.put("status", "0");						
				} else {
					param = new HashMap<String, String>();						
					if(userDetails.getUserId().equals(request.get("userId"))){
						if(userDetails.getRoleId() == 3){
							if(request.get("userType").equals("0")){
								List<ViewUser> userList2 = new ArrayList<>();
								param.put("userId", request.get("userId"));	
								userList2 = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.getMyRetailerForSD, param);
								
								List<ViewUser> userList3 = new ArrayList<>();
								Map<String, String> param1 =  new HashMap<String, String>();
								System.out.println(request.get("userId"));
								param1.put("uplineId", request.get("userId"));
								userList3 = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.GetUserByUplineId, param1);
								userList.addAll(userList3);
								userList.addAll(userList2);
							/*	param.put("userId", request.get("userId"));		
								userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.getMyUserForSD, param);*/
								//userList = userDao.getUserByNamedQuery("getMyUserForSD", param );
							} else if(request.get("userType").equals("5")){
								param.put("userId", request.get("userId"));	
								userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.getMyRetailerForSD, param);
								//userList = userDao.getUserByNamedQuery("getMyRetailerForSD", param );
							} else {
								param.put("uplineId", request.get("userId"));
								userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.GetUserByUplineId, param);
								//userList = userDao.getUserByNamedQuery("GetUserByUplineId", param);
							}
						} else {
							param.put("uplineId", request.get("userId"));
							userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.GetUserByUplineId, param);
							//userList = userDao.getUserByNamedQuery("GetUserByUplineId", param);
						}
					}
				}
				if (!userList.isEmpty()) {
					if (userDetails.getRoleId() != 1) {
						for (ViewUser u : userList) {
						//	u.setPassword(null);
							u.setTranPin(null);
							userList1.add(u);
						}
					}
					returnData.put("status", "1");
				} else {
					returnData.put("status", "0");
				}
				if (userDetails.getRoleId() != 1) {
					returnData.put("report", userList1);
				} else {
					returnData.put("report", userList);
				}
				
			} else {
				returnData.put("message", "Invalid Details");		
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("getViewUserDetails ::::::::::: "+e);			
			returnData.put("message", "Exception! Please try again!");		
			returnData.put("status", "0");			
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> getViewInactiveUser(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();		
		Map<String, String> param = null;
		
		List<ViewUser> userList = new ArrayList<>();
		List<ViewUser> userList1 = new ArrayList<>();
		
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if(userDetails.getRoleId() == 1){
					if(request.get("userType").equals("0")){
						param = new HashMap<String, String>();
						userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.GetAllInactiveUserByAdmin, param);
						//userList = userDao.getUserByNamedQuery("GetAllUserByAdmin", param );
					} else if (request.get("userType").equals("2")){
						param = new HashMap<String, String>();	
						userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.getInActiveResellerByAdmin, param);
						//userList = userDao.getUserByNamedQuery("getResellerByAdmin", param );
					}else {
						param = new HashMap<String, String>();			
						param.put("roleId",request.get("userType"));
						//param.put("wlId", userDetails.getWlId());
						userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.GetInactiveUserByRoleId, param);
						//userList = userDao.getUserByNamedQuery("GetUserByRoleIdAndWlId", param );
					}
				} else if(userDetails.getRoleId() == 2){
					if(request.get("userType").equals("0")){
						param = new HashMap<String, String>();		
						param.put("wlId", userDetails.getWlId());
						userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.GetAllUserByReseller, param);
						//userList = userDao.getUserByNamedQuery("GetAllUserByReseller", param );
					} else {
						param = new HashMap<String, String>();			
						param.put("roleId", request.get("userType"));
						param.put("wlId", userDetails.getWlId());
						userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.GetUserByRoleIdAndWlId, param);
						//userList = userDao.getUserByNamedQuery("GetUserByRoleIdAndWlId", param );
					}
				} 
				if (!userList.isEmpty()) {
					if (userDetails.getRoleId() != 1) {
						for (ViewUser u : userList) {
							u.setPassword(null);
							u.setTranPin(null);
							userList1.add(u);
						}
					}
					returnData.put("status", "1");
				} else {
					returnData.put("status", "0");
				}
				if (userDetails.getRoleId() != 1) {
					returnData.put("report", userList1);
				} else {
					returnData.put("report", userList);
				}
				
			} else {
				returnData.put("message", "Invalid Details");		
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("getViewUserDetails ::::::::::: "+e);			
			returnData.put("message", "Exception! Please try again!");		
			returnData.put("status", "0");			
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getBankDetailsByWlId(String wlId) {	
		Map<String, Object> returnData = new HashMap<String, Object>();	
		List<Bankdetails> list = new ArrayList<>();
		try{
			Map<String, Object> param = new HashMap<>();
			param.put("wlId", wlId);
			list = bankdetailsDao.getBankdetailsByNamedQuery("GetBankDetailsByWlId", param);
			returnData.put("bankDetails", list);
			returnData.put("status", "1");
		} catch (Exception e) {
			logger_log.error("getBankDetailsByWlId ::::::::::: "+e);			
			returnData.put("message", "Exception! Please try again!");		
			returnData.put("status", "0");			
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getTotalUserBalanceRechargeStatus(User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, String> param = null;
		List<Object> list = new ArrayList<>();
		double purchase=0.0;
		double earning=0.0;
		try{
			if(userDetails.getRoleId() == 1){
				param = new HashMap<String, String>();
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getTotalUserTotalBalance, param);
				if(!list.isEmpty()){
					for(Object object : list){	
						Object[] obj = (Object[]) object;
						returnData.put("totalUser", obj[0].toString());
						returnData.put("totalBalance", obj[1].toString());
					}
				}
				param = new HashMap<String, String>();
				param.put("date", GenerateRandomNumber.getCurrentDate());
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.totalSuccessPendingFailed, param);
				if(!list.isEmpty()){
					for(Object object : list){	
						Object[] obj = (Object[]) object;
						returnData.put("SUCCESS", obj[0].toString());
						returnData.put("PENDING", obj[1].toString());
						returnData.put("FAILED", obj[2].toString());
					}
				}
				
				param = new HashMap<String, String>();
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getTotalSettleWalletbalance, param);
				
				if(!list.isEmpty()){
					for(Object object : list){	
						Double totalPayout = (Double) object;
						returnData.put("totalPayout", totalPayout);
					}
				}
				
				param = new HashMap<String, String>();
				param.put("date", GenerateRandomNumber.getCurrentDate());
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getAepsDetailsForAdmin, param);
				
				if(!list.isEmpty()){
					for(Object object : list){	
						Double totalAeps = (Double) object;
						returnData.put("totalAeps", totalAeps);
					}
				}
				
				param = new HashMap<String, String>();
				param.put("date", GenerateRandomNumber.getCurrentDate());
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getMicroatmDetailsForAdmin, param);
				
				if(!list.isEmpty()){
					for(Object object : list){	
						Double totalMicro = (Double) object;
						returnData.put("totalMicro", totalMicro);
					}
				}
				
				param = new HashMap<String, String>();
				param.put("date", GenerateRandomNumber.getCurrentDate());
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getDmrDetailsForAdmin, param);
				
				if(!list.isEmpty()){
					for(Object object : list){	
						Double totalDmr = (Double) object;
						returnData.put("totalDmr", totalDmr);
					}
				}
				
				param = new HashMap<String, String>();
				param.put("date", GenerateRandomNumber.getCurrentDate());
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getFlightBookingForAdmin, param);
				
				if(!list.isEmpty()){
					for(Object object : list){	
						Double totalFlight = (Double) object;
						returnData.put("totalFlight", totalFlight);
					}
				}
				
				param = new HashMap<String, String>();
				param.put("date", GenerateRandomNumber.getCurrentDate());
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getRechargeDetailsForAdmin, param);
				
				if(!list.isEmpty()){
					for(Object object : list){	
						Double totalRecharge = (Double) object;
						returnData.put("totalRecharge", totalRecharge);
					}
				}
				
				param = new HashMap<String, String>();
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getTotalWhiteLevelUser, param);
				if(!list.isEmpty()){
					for(Object object : list){	
						Object[] obj = (Object[]) object;
						returnData.put("totalWlUser", obj[0].toString());
						returnData.put("totalWlBalance", obj[1].toString());
					}
				}
				
										
			} else if(userDetails.getRoleId() == 2) {
				param = new HashMap<String, String>();
				param.put("wlId", userDetails.getWlId());
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getTotalUserTotalBalanceByReseller, param);
				if(!list.isEmpty()){
					for(Object object : list){	
						Object[] obj = (Object[]) object;
						returnData.put("totalUser", obj[0].toString());
						returnData.put("totalBalance", obj[1].toString());
					}
				}
				param = new HashMap<String, String>();
				param.put("wlId", userDetails.getWlId());
				param.put("date", GenerateRandomNumber.getCurrentDate());
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.totalSuccessPendingFailedByReseller, param);
				if(!list.isEmpty()){
					for(Object object : list){	
						Object[] obj = (Object[]) object;
						returnData.put("SUCCESS", obj[0].toString());
						returnData.put("PENDING", obj[1].toString());
						returnData.put("FAILED", obj[2].toString());
					}
				}
				param.put("userId", userDetails.getUserId());
				param.put("date", GenerateRandomNumber.getCurrentDate());
				purchase = customQueryServiceLogic.getTotalPurchaseAmount(CustomSqlQuery.totalPurchase, param);
				
				//System.out.println("purchase=/////;.;.;"+ purchase);
				returnData.put("Purchase_Amount", purchase);
				//Today Earning Amount Count
				earning = customQueryServiceLogic.getTotalPurchaseAmount(CustomSqlQuery.totalEarning, param);
						
								returnData.put("Earning_Amount", earning);
				param = new HashMap<String, String>();
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getTotalSettleWalletbalance, param);
				if(!list.isEmpty())
				{	for(Object object : list){	
					Double totalPayout = (Double) object;
					returnData.put("totalPayout", totalPayout);
					}
				}
				
				param = new HashMap<String, String>();
				param.put("date", GenerateRandomNumber.getCurrentDate());
				param.put("userId", userDetails.getUserId());
				
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getAepsDetailsForOthers, param);
				
				if(!list.isEmpty()){
					for(Object object : list){	
						Double totalAeps = (Double) object;
						returnData.put("totalAeps", totalAeps);
					}
				}
				
				param = new HashMap<String, String>();
				param.put("date", GenerateRandomNumber.getCurrentDate());
				param.put("userId", userDetails.getUserId());
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getMicroatmDetailsForOthers, param);
				
				if(!list.isEmpty()){
					for(Object object : list){	
						Double totalMicro = (Double) object;
						returnData.put("totalMicro", totalMicro);
					}
				}
				
				param = new HashMap<String, String>();
				param.put("date", GenerateRandomNumber.getCurrentDate());
				param.put("userId", userDetails.getUserId());
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getDmrDetailsForOthers, param);
				
				if(!list.isEmpty()){
					for(Object object : list){	
						Double totalDmr = (Double) object;
						returnData.put("totalDmr", totalDmr);
					}
				}
				
				param = new HashMap<String, String>();
				param.put("date", GenerateRandomNumber.getCurrentDate());
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getFlightBookingForAdmin, param);
				
				if(!list.isEmpty()){
					for(Object object : list){	
						Double totalFlight = (Double) object;
						returnData.put("totalFlight", totalFlight);
					}
				}
				
				param = new HashMap<String, String>();
				param.put("date", GenerateRandomNumber.getCurrentDate());
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getRechargeDetailsForAdmin, param);
				
			} else if(userDetails.getRoleId() == 3 || userDetails.getRoleId() == 4){
				param = new HashMap<String, String>();
				param.put("uplineId", userDetails.getUserId());
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getTotalUserTotalBalanceByUser, param);
				if(!list.isEmpty()){
					for(Object object : list){	
						Object[] obj = (Object[]) object;
						returnData.put("totalUser", obj[0].toString());
						returnData.put("totalBalance", obj[1].toString());
					}
				}
				param = new HashMap<String, String>();
				param.put("userId", userDetails.getUserId());
				param.put("date", GenerateRandomNumber.getCurrentDate());
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.totalSuccessPendingFailedByUser, param);
				if(!list.isEmpty()){
					for(Object object : list){	
						Object[] obj = (Object[]) object;
						returnData.put("SUCCESS", obj[0].toString());
						returnData.put("PENDING", obj[1].toString());
						returnData.put("FAILED", obj[2].toString());
					}
				}
				param.put("userId", userDetails.getUserId());
				param.put("date", GenerateRandomNumber.getCurrentDate());
				purchase = customQueryServiceLogic.getTotalPurchaseAmount(CustomSqlQuery.totalPurchase, param);
				
			//	System.out.println("purchase=/////;.;.;"+ purchase);
				returnData.put("Purchase_Amount", purchase);
				//Today Earning Amount Count
				earning = customQueryServiceLogic.getTotalPurchaseAmount(CustomSqlQuery.totalEarning, param);
						
								returnData.put("Earning_Amount", earning);
								
								Map<String, Object>	pam = new HashMap<String, Object>();
								pam.put("username", userDetails.getUserId());
								List<SettlementBalance> ewallet=SettlementBalanceDao.getSettlementBalanceByNamedQuery("getsettlementbyUsername", pam);
								if(ewallet .isEmpty()){
									returnData.put("aepsb", 0);
								}else{
									returnData.put("aepsb", ewallet.get(0).getSettlementbalance());
								}	
								param = new HashMap<String, String>();
								list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getTotalSettleWalletbalance, param);
								if(!list.isEmpty())
								{	for(Object object : list){	
									Double totalPayout = (Double) object;
									returnData.put("totalPayout", totalPayout);
									}
								}
								
								param = new HashMap<String, String>();
								param.put("date", GenerateRandomNumber.getCurrentDate());
								param.put("userId", userDetails.getUserId());
								
								list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getAepsDetailsForOthers, param);
								
								if(!list.isEmpty()){
									for(Object object : list){	
										Double totalAeps = (Double) object;
										returnData.put("totalAeps", totalAeps);
									}
								}
								
								param = new HashMap<String, String>();
								param.put("date", GenerateRandomNumber.getCurrentDate());
								param.put("userId", userDetails.getUserId());
								list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getMicroatmDetailsForOthers, param);
								
								if(!list.isEmpty()){
									for(Object object : list){	
										Double totalMicro = (Double) object;
										returnData.put("totalMicro", totalMicro);
									}
								}
								
								param = new HashMap<String, String>();
								param.put("date", GenerateRandomNumber.getCurrentDate());
								param.put("userId", userDetails.getUserId());
								list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getDmrDetailsForOthers, param);
								if(!list.isEmpty()){
									for(Object object : list){	
										Double totalDmr = (Double) object;
										returnData.put("totalDmr", totalDmr);
									}
								}
			} else {
				Map<String, Object>	pam = new HashMap<String, Object>();
				pam.put("username", userDetails.getUserId());
				List<SettlementBalance> ewallet=SettlementBalanceDao.getSettlementBalanceByNamedQuery("getsettlementbyUsername", pam);
				if(ewallet .isEmpty()){
					returnData.put("aepsb", 0);
				}else{
					returnData.put("aepsb", ewallet.get(0).getSettlementbalance());
				}	
				param = new HashMap<String, String>();
				param.put("userId", userDetails.getUserId());
				param.put("date", GenerateRandomNumber.getCurrentDate());
				list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.totalSuccessPendingFailedByUser, param);
				if(!list.isEmpty()){
					for(Object object : list){	
						Object[] obj = (Object[]) object;
						returnData.put("SUCCESS", obj[0].toString());
						returnData.put("PENDING", obj[1].toString());
						returnData.put("FAILED", obj[2].toString());
					}
				}
				
				param.put("userId", userDetails.getUserId());
				param.put("date", GenerateRandomNumber.getCurrentDate());
				purchase = customQueryServiceLogic.getTotalPurchaseAmount(CustomSqlQuery.totalPurchase, param);
				//System.out.println("purchase=/////;.;.;"+ purchase);
				returnData.put("Purchase_Amount", purchase);
				
				//Today Earning Amount Count
				earning = customQueryServiceLogic.getTotalPurchaseAmount(CustomSqlQuery.totalEarning, param);
						
								returnData.put("Earning_Amount", earning);
								param = new HashMap<String, String>();
								list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getTotalSettleWalletbalance, param);
								if(!list.isEmpty())
								{	for(Object object : list){	
									Double totalPayout = (Double) object;
									returnData.put("totalPayout", totalPayout);
									}
								}
								
								param = new HashMap<String, String>();
								param.put("date", GenerateRandomNumber.getCurrentDate());
								param.put("userId", userDetails.getUserId());
								
								list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getAepsDetailsForOthers, param);
								
								if(!list.isEmpty()){
									for(Object object : list){	
										Double totalAeps = (Double) object;
										returnData.put("totalAeps", totalAeps);
									}
								}
								
								
								
								param = new HashMap<String, String>();
								param.put("date", GenerateRandomNumber.getCurrentDate());
								param.put("userId", userDetails.getUserId());
								list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getMicroatmDetailsForOthers, param);
								
								if(!list.isEmpty()){
									for(Object object : list){	
										Double totalMicro = (Double) object;
										returnData.put("totalMicro", totalMicro);
									}
								}
								
								param = new HashMap<String, String>();
								param.put("date", GenerateRandomNumber.getCurrentDate());
								param.put("userId", userDetails.getUserId());
								list = customQueryService.getDataByQueryUsingCustomQuery(CustomSqlQuery.getDmrDetailsForOthers, param);
								
								if(!list.isEmpty()){
									for(Object object : list){	
										Double totalDmr = (Double) object;
										returnData.put("totalDmr", totalDmr);
									}
								}
			}
		}  catch (Exception e) {
			logger_log.error("getTotalUserBalanceRechargeStatus ::::::::::: "+e);	
			returnData.put("totalUser", "0");
			returnData.put("totalBalance", "0");
			returnData.put("SUCCESS", "0");
			returnData.put("PENDING", "0");
			returnData.put("FAILED", "0");
			returnData.put("Purchase_Amount", "0");
			returnData.put("Earning_Amount", "0");
		}
		return returnData;
	}

	@Override
	public List<RechargeReport> getDashBoardRechargeReport(User userDetails) {
		Map<String, String> param = new HashMap<String, String>();
		List<RechargeReport> list = new ArrayList<>();
		try{
			if(userDetails.getRoleId() == 1){
				param = new HashMap<String, String>();				
				list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getTodayRechargeReport, param);
			} else if(userDetails.getRoleId() == 2) {
				param = new HashMap<String, String>();
				param.put("wlId", userDetails.getWlId());
				list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getTodayRechargeReportByReseller, param);
			} else if(userDetails.getRoleId() == 3 || userDetails.getRoleId() == 4){
				param = new HashMap<String, String>();
				param.put("userId", userDetails.getUserId());
				list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getTodayRechargeReportbyUser, param);
			} else {
				param = new HashMap<String, String>();
				param.put("userId", userDetails.getUserId());
				list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getTodayRechargeReportbyUser, param);			
			}
			
		} catch (Exception e) {
			logger_log.error("getTotalUserBalanceRechargeStatus ::::::::::: "+e);				
		}
		return list;
	}

	

	@Override
	public boolean insertSigninHistory(String ip, String userId, String mobile, String deviceId) {
		Signinhistory signinhistory = new Signinhistory(userId, mobile, ip, deviceId, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
		return signinhistoryDao.insertSigninhistory(signinhistory );
	}

	@Override
	public List<Api> getAllApi() {
		try {
			List<Api> apiList = apiDao.getAllApi();
			return apiList;
		}catch (Exception e) {
			logger_log.error("getAllOperator ::::::::::: "+e);	
		}
		return null;
	}
	
	@Override
	public Notification getNotification(User userDetails){
		Map<String, String> parameters = new HashMap<>();
		if(userDetails.getRoleId() == 1 || userDetails.getRoleId() == 2){
			parameters = new HashMap<>();
			parameters.put("wlId", userDetails.getWlId());
			parameters.put("request_user", userDetails.getUserId());
			return customQueryServiceLogic.getNotification(CustomSqlQuery.notificationForAdmin, parameters );
		} else {
			parameters = new HashMap<>();
			parameters.put("request_user", userDetails.getUserId());
			return customQueryServiceLogic.getNotification(CustomSqlQuery.notificationForUpline, parameters );
		}
	}

	@Override
	public Map<String, Object> viewComplain(Map<String, String> request, User userDetails, String type) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = null;	
		List<ComplainDetails> list = new ArrayList<>();
		try {
			if (userDetails.getRoleId() == 1) {
				if(type.equals("ALL")){
					parameters = new HashMap<>();
					parameters.put("startDate", request.get("startDate"));
					parameters.put("endDate", request.get("endDate"));
					list = customQueryServiceLogic.viewComplain(CustomSqlQuery.viewComplainByAdmin, parameters);
				} else if(type.equals("LATEST")){
					parameters = new HashMap<>();					
					list = customQueryServiceLogic.viewComplain(CustomSqlQuery.viewlatestComplainByAdmin, parameters);
				} else if(type.equals("PENDING")){
					parameters = new HashMap<>();					
					list = customQueryServiceLogic.viewComplain(CustomSqlQuery.viewpendingComplainByAdmin, parameters);
				} 				
			} else if(userDetails.getRoleId() == 2){
				if(type.equals("ALL")){
					parameters = new HashMap<>();
					parameters.put("wlId", userDetails.getWlId());
					parameters.put("startDate", request.get("startDate"));
					parameters.put("endDate", request.get("endDate"));
					list = customQueryServiceLogic.viewComplain(CustomSqlQuery.viewComplainByReseller, parameters);
				} else if(type.equals("LATEST")){
					parameters = new HashMap<>();
					parameters.put("wlId", userDetails.getWlId());
					list = customQueryServiceLogic.viewComplain(CustomSqlQuery.viewlatestComplainByReseller, parameters);
				} else if(type.equals("PENDING")){
					parameters = new HashMap<>();	
					parameters.put("wlId", userDetails.getWlId());
					list = customQueryServiceLogic.viewComplain(CustomSqlQuery.viewpendingComplainByReseller, parameters);
				} 	
				
			} else {
				if(type.equals("ALL")){
					parameters = new HashMap<>();
					parameters.put("userId", userDetails.getUserId());
					parameters.put("startDate", request.get("startDate"));
					parameters.put("endDate", request.get("endDate"));
					list = customQueryServiceLogic.viewComplain(CustomSqlQuery.viewComplainByUser, parameters);
				} else if(type.equals("LATEST")){
					parameters = new HashMap<>();	
					parameters.put("userId", userDetails.getUserId());
					list = customQueryServiceLogic.viewComplain(CustomSqlQuery.viewlatestComplainByUser, parameters);
				} else if(type.equals("PENDING")){
					parameters = new HashMap<>();	
					parameters.put("userId", userDetails.getUserId());
					list = customQueryServiceLogic.viewComplain(CustomSqlQuery.viewpendingComplainByUser, parameters);
				} 					
			}
			
			if(list.isEmpty()){
				returnData.put("complain", list);	
				returnData.put("message", "No Record Found!");	
				returnData.put("status", "0");
			} else {
				returnData.put("complain", list);		
				returnData.put("status", "1");
			}
		} catch (Exception e) {
			logger_log.error("viewComplain ::::::::::: "+e);
			returnData.put("message", "Exception! Please try again!");		
			returnData.put("status", "0");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> updateComplainDetails(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				Complain complain = complainDao.getComplainById(Integer.parseInt(request.get("id")));
				if (complain != null) {
					complain.setAdminMessage(request.get("adminMessage"));
					complain.setStatus(request.get("status"));
					boolean flag = complainDao.updateComplain(complain);
					if (flag) {
						returnData.put("message", "Complain Updated Successsfully!");
						returnData.put("status", "1");
					} else {
						returnData.put("message", "Failed to Update Complain!");
						returnData.put("status", "0");
					}
				} else {
					returnData.put("message", "Invalid Complain Details!");
					returnData.put("status", "0");
				}
			} else {
				returnData.put("message", "Invalid Complain Details!");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("updateComplainDetails ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again!");
			returnData.put("status", "0");
		}
		return returnData;

	}

	@Override
	public Map<String, Object> forgotPassword(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		if(UtilityClass.checkParameterIsNull(request)) {
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("mobile", request.get("mobile"));
			List<User> userList = userDao.getUserByNamedQuery("getUserByMobile", param);
			if(userList.isEmpty()){
				returnData.put("message", "Invalid Mobile number!");
				returnData.put("status", "1");
			} else {
				User user = userList.get(0);
				param = new HashMap<String, Object>();
				param.put("wlId", user.getWlId());
				SmsCredential credential = smsCredentialDao.getSmsCredentialByNamedQuery("GetSmsCredentialByWlId", param).get(0);
				// List<SMSApiparameters> params =  smsapiparamsdao.getAllSMSApiparameters();
				String sms = "Welcome again to "+credential.getCompany()+",Your Mobile number is :"+user.getMobile()+" & Password:"+user.getPassword()+" Log on to :"+credential.getDomain()+"";
				SMS.sendSMS2(user.getMobile(), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());

				//new SMS().sendSMS(user.getMobile(), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword(),params);
				returnData.put("message", "Password send Successfully!");
				returnData.put("status", "1");				
			}
		} else {
			returnData.put("message", "Invalid Details!");
			returnData.put("status", "0");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> advancedCustomerNo(Map<String, String> request, User userDetails) {
		List<RechargeReport> list = new ArrayList<RechargeReport>();
		Map<String, Object> returnData = new HashMap<String, Object>();
		HashMap<String, String> param = null;
		try{
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1) {
					param = new HashMap<String, String>();
					param.put("searchKeyword", request.get("customerNo"));
					list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.advancedSearchOnCustomerNoByAdmin,param);
				} else if(userDetails.getRoleId() == 2){
					param = new HashMap<String, String>();
					param.put("searchKeyword", request.get("customerNo"));
					param.put("wlId", userDetails.getWlId());
					list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.advancedSearchOnCustomerNoByUser,param);
				} else {
					param = new HashMap<String, String>();
					param.put("searchKeyword", request.get("customerNo"));
					param.put("userId", userDetails.getUserId());
					list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.advancedSearchOnCustomerNoByUser,param);
				}
				if(!list.isEmpty()) {
					returnData.put("report", list);
					returnData.put("status", "1");					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Records Found!");
				}
			} else {
				returnData.put("message", "Invalid Search Keyword!");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("advancedCustomerNo ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again!");
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> advancedSearchUser(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, String> param = null;
		List<ViewUser> userList = new ArrayList<>();
		List<ViewUser> userList1 = new ArrayList<>();
		try {
			if(userDetails.getRoleId() == 1){
				param = new HashMap<String, String>();
				param.put("mobile", request.get("mobile"));
				userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.advancedSearchUserByAdmin, param);	
				
				//userList = userDao.getUserByNamedQuery("advancedSearchUserByAdmin", param );
				
			} else if(userDetails.getRoleId() == 2){
				
				param = new HashMap<String, String>();		
				param.put("mobile", request.get("mobile"));
				param.put("uplineId", userDetails.getUserId());
				userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.advancedSearchUserByUsers, param);	
				
				//userList = userDao.getUserByNamedQuery("advancedSearchUserByUser", param );	
				
			} else if(userDetails.getRoleId() == 3 || userDetails.getRoleId() == 4){
				
				param = new HashMap<String, String>();		
				param.put("mobile", request.get("mobile"));
				param.put("uplineId", userDetails.getUserId());
				userList = customQueryServiceLogic.getViewUserDetails(CustomSqlQuery.advancedSearchUserByUsers, param);	
				
				//userList = userDao.getUserByNamedQuery("advancedSearchUserByUser", param );
				
			} else {
				
				returnData.put("message", "Forbidden User!");	
			}
			if (!userList.isEmpty()) {
				if (userDetails.getRoleId() != 1) {
					for (ViewUser u : userList) {
						u.setPassword(null);
						u.setTranPin(null);
						userList1.add(u);
					}
				}
				returnData.put("status", "1");
			} else {
				returnData.put("status", "0");
			}
			if (userDetails.getRoleId() != 1) {
				returnData.put("report", userList1);
			} else {
				returnData.put("report", userList);
			}
		} catch (Exception e) {
			logger_log.error("advancedSearchUser ::::::::::: "+e);			
			returnData.put("message", "Exception! Please try again!");		
			returnData.put("status", "0");			
		}
		return returnData;
	}
	
	
	@Override
	public Map<String, Object> editProfileForUser(Map<String, String> request, User userDetail) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (UtilityClass.checkStringIsNull(request.get("userId"))) {
				if (request.get("password") == null || request.get("password").equals("")
						|| request.get("cPassword") == null || request.get("cPassword").equals("")
						|| !request.get("cPassword").equals(request.get("password"))) {
					
				} else {
					if (userDetail.getRoleId() == 1 || userDetail.getRoleId() == 2) {
						User userDetails = getUserByUserId(request.get("userId"));
						userDetails.setName(request.get("name"));
						userDetails.setFirmName(request.get("firmName"));
						userDetails.setEmail(request.get("email"));
						userDetails.setPinCode(request.get("pinCode"));
						userDetails.setMobile(request.get("mobile"));
						userDetails.setState(request.get("state"));
						userDetails.setCity(request.get("city"));
						userDetails.setPassword(request.get("password"));
						boolean flag = userDao.updateUser(userDetails);
						if (flag) {
							returnData.put("message", "Profile Edited Successfully!");
							returnData.put("status", "1");
						} else {
							returnData.put("message", "Failed to Update User profile");
							returnData.put("status", "0");
						}
					} else {
						returnData.put("message", "No Authority");
						returnData.put("status", "0");
					}
				}
			}else {				
				returnData.put("message", "No Such User found");
				returnData.put("status", "0");
			}			
		}catch (Exception e) {
			logger_log.error("editProfile :::::::::::::: "+e);			
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}		
		return returnData;
	}

	@Override
	public Map<String, Object> viewBalanceRequest(Map<String, String> request, User userDetails, String type) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = null;	
		List<BalanceRequest> list = new ArrayList<>();
		try {

			if(type.equals("ALL")){
				parameters = new HashMap<>();
				parameters.put("request_user", userDetails.getUserId());
				parameters.put("startDate", request.get("startDate"));
				parameters.put("endDate", request.get("endDate"));
				list = customQueryServiceLogic.viewBalanceRequest(CustomSqlQuery.viewBalanceRequest, parameters);
			} else if(type.equals("LATEST")){
				
			} else if(type.equals("PENDING")){
				parameters = new HashMap<>();
				parameters.put("request_user", userDetails.getUserId());
				list = customQueryServiceLogic.viewBalanceRequest(CustomSqlQuery.viewPendingBalanceRequest, parameters);
			} 				
		
			
			if(list.isEmpty()){
				returnData.put("BalanceRequest", list);	
				returnData.put("message", "No Record Found!");	
				returnData.put("status", "0");
			} else {
				returnData.put("BalanceRequest", list);		
				returnData.put("status", "1");
			}
		} catch (Exception e) {
			logger_log.error("viewBalanceRequest ::::::::::: "+e);
			returnData.put("message", "Exception! Please try again!");		
			returnData.put("status", "0");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> updateBalanceRequest(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if(request.get("requestId") == null || request.get("requestId").equals("")){
					returnData.put("message", "Invalid Balance Request Details!");
					returnData.put("status", "0");
				} else {
					Map<String, Object> param = new HashMap<>();
					param.put("userId", userDetails.getUserId());
					param.put("id",Integer.parseInt(request.get("requestId")));
					List<Balancerequest> bRlist = balancerequestDao.getBalanceRequestByNamedQuery("GetBalancerequestByRequestIdAndRequestUser", param );
					if(bRlist.isEmpty()){
						returnData.put("message", "Invalid Balance Request Details!");
						returnData.put("status", "0");
					} else {
						Balancerequest br = bRlist.get(0);
						if (br.getStatus().equals("PENDING")) {
							if (request.get("status").equals("SUCCESS")) {									
								User requestUser = userDao.getUserByUserId(br.getRequestUser());
								Map<String,String> parameters = new HashMap<String, String>();
								parameters.put("userId", br.getUserName());
								double toOpBal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
								double toClBal = toOpBal + Double.parseDouble(request.get("amount"));
								if(requestUser.getRoleId() == 1){
									Balancetransafer balancetransafer = new Balancetransafer("admin", 0.0, Double.parseDouble(request.get("amount")), 0.0, br.getUserName(), toOpBal, Double.parseDouble(request.get("amount")), toClBal, br.getBankTransactionId(), "BALANCE CREDITED BY SUPER ADMIN", GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(), br.getWlId());
									boolean flag = balancetransaferDao.insertBalanceTransfer(balancetransafer);
									if(flag){
										boolean flag1 = commissionService.updateBalance(br.getUserName(), "Balanace Request for - "+br.getBankTransactionId(), "ADD BALANCE", br.getAmount(), "CREDIT",0);
										if(flag1){
											br.setStatus("SUCCESS");
											balancerequestDao.updateBalanceRequest(br);
											returnData.put("message", "balance Request update Successfully.");
											returnData.put("status", "1");
										} else {
											returnData.put("message", "Failed to Update Balance Request.");
											returnData.put("status", "0");
										}
									} else {
										returnData.put("message", "Failed to Update Balance Request.");
										returnData.put("status", "0");
									}
								} else {
									parameters = new HashMap<String, String>();
									parameters.put("userId", requestUser.getUserId());
									double fromOpBal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
									double fromClBal = fromOpBal - Double.parseDouble(request.get("amount"));
									if(fromClBal>0) {
									String narration = "";
									if(requestUser.getRoleId() == 2){
										narration = "BALANCE CREDITED BY ADMIN";
									} else if(requestUser.getRoleId() == 3) {
										narration = "BALANCE CREDITED BY SUPER DISTRIBUTOR";
									} else {
										narration = "BALANCE CREDITED BY DISTRIBUTOR";
									}
									Balancetransafer balancetransafer = new Balancetransafer(requestUser.getUserId(), fromOpBal,Double.parseDouble(request.get("amount")), fromClBal, br.getUserName(), toOpBal, Double.parseDouble(request.get("amount")), toClBal,br.getBankTransactionId(), narration, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(), br.getWlId());
									boolean flag = balancetransaferDao.insertBalanceTransfer(balancetransafer);
									if(flag){
										boolean flag1 = commissionService.updateBalance(requestUser.getUserId(), "Balanace transafer for  - "+br.getBankTransactionId(), "BALANCE REQUEST", br.getAmount(), "DEBIT",0);
										if(flag1){
											boolean flag2 = commissionService.updateBalance(br.getUserName(), "Balanace Request for - "+br.getBankTransactionId(), "ADD BALANCE", br.getAmount(), "CREDIT",0);
											if(flag2){
												br.setStatus("SUCCESS");
												balancerequestDao.updateBalanceRequest(br);												
												returnData.put("message", "balance Request update Successfully.");
												returnData.put("status", "1");
											} else {
												returnData.put("message", "Failed to Update Balance Request.");
												returnData.put("status", "0");
											}
										} else {
											returnData.put("message", "Failed to Update Balance Request.");
											returnData.put("status", "0");
										}
									} else {
										returnData.put("message", "Failed to Update Balance Request.");
										returnData.put("status", "0");
									}
									
								}else {
									returnData.put("message", "Insufficient Fund");
									returnData.put("status", "0");
								}
								}
							} else {
								br.setStatus("FAILED");
								boolean flag = balancerequestDao.updateBalanceRequest(br);
								if(flag){
									returnData.put("message", "balance Request update Successfully.");
									returnData.put("status", "1");
								} else {
									returnData.put("message", "Failed to Update Balance Request.");
									returnData.put("status", "0");
								}
							}
						}else {
							returnData.put("message", "Already! "+br.getStatus()+" Request.");
							returnData.put("status", "0");
						}
					}
				}
			} else {
				returnData.put("message", "Invalid Balance Request Details!");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("updateBalanceRequest ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again!");
			returnData.put("status", "0");
		}
		return returnData;

	}

	@Override
	public Map<String, Object> getOperatorByServiceType(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if(request.get("serviceType") == null || request.get("serviceType").equals("")){
					returnData.put("message", "Invalid Service Type!");
					returnData.put("status", "0");
				} else {
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("serviceType", request.get("serviceType"));
					List<Operator> opList = operatorDao.getOperatorByNamedQuery("getOperatorByServiceType", param );
					if(opList == null || opList.isEmpty()){
						returnData.put("message", "Invalid Service Type!");
						returnData.put("status", "0");
					} else {
						returnData.put("Operator", opList);
						returnData.put("status", "1");
					}
				}
			} else {
				
			}
		} catch (Exception e) {
			logger_log.error("getOperatorByServiceType ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again!");
			returnData.put("status", "0");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> _GetUpline(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String msg = "";		
		List<User> userList = null;
		String select = "";
		Map<String, Object> param = null;
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				String modelName = request.get("modelName") + ".uplineId";
				if (userDetails.getRoleId() == 1) {					
					param = new HashMap<String, Object>();
					param.put("roleId",(Integer.parseInt(request.get("userType")) - 1));
					if(Integer.parseInt(request.get("userType")) == 2) {
						userList = userDao.getUserByNamedQuery("GetUserByRoleId", param);
					}else {
						param.put("wlId", userDetails.getWlId());
						userList = userDao.getUserByNamedQuery("GetUserByRoleIdAndWlId", param);
					}
				} else if (userDetails.getRoleId() == 2) {					
					param = new HashMap<String, Object>();					
					param.put("roleId",(Integer.parseInt(request.get("userType")) - 1));
					param.put("wlId", userDetails.getWlId());
					userList = userDao.getUserByNamedQuery("GetUserByRoleIdAndWlId", param);
					
				} else if (userDetails.getRoleId() == 3) {					
					param = new HashMap<String, Object>();	
					if(Integer.parseInt(request.get("userType")) == 5) {
						param.put("roleId",Integer.parseInt(request.get("userType")));
						param.put("wlId", userDetails.getWlId());
						param.put("uplineId", userDetails.getUserId());
						userList = userDao.getUserByNamedQuery("GetUserByRoleIdUplineIdAndWlId", param);
					}else {
						userList = new ArrayList<>();
						userList.add(userDetails);
					}
					
				} else if (userDetails.getRoleId() == 4) {
					userList = new ArrayList<>();
					userList.add(userDetails);
				} else {
					returnData.put("status", "0");
					msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
							+ "<label for='email_address_2'>Select User </label>"
						+ "</div>"
						+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
						+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
						+ "<label for='email_address_2' class='danger'>Forbidden User. </label>"
						+ "</div>"
						+ "</div>";
				}
				if(!userList.isEmpty()) {
					returnData.put("status", "1");					
					select +="<select ng-model='"+modelName+"' class='form-control'><option value='' >Select User</option>";
					for(User user : userList) {
						select += "<option value='"+user.getUserId()+"'>"+user.getName()+" "+"("+user.getMobile()+")</option>";
					}
					select += "</select>";
					msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
							+ "<label for='email_address_2'>Select User </label>"
						+ "</div>"
						
						+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"+select+ "</div>";
				}else {
					returnData.put("status", "0");
					msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
							+ "<label for='email_address_2'>Select User </label>"
						+ "</div>"
						+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
						+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
						+ "<label for='email_address_2' class='text-danger'>No User Available. </label>"
						+ "</div>"
						+ "</div>";
				}
			}else {
				returnData.put("status", "0");
				msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
						+ "<label for='email_address_2'>Select User </label>"
					+ "</div>"
					+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
					+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
					+ "<label for='email_address_2' class='text-danger'>Technical Error! Try After Sometime. </label>"
					+ "</div>"
					+ "</div>";
			}
			
		} catch (Exception e) {
			returnData.put("status", "0");
			logger_log.error("getUserByUserType :::::::::::::: " + e);
			msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
					+ "<label for='email_address_2'>Select User </label>"
				+ "</div>"
				+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
				+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
				+ "<label for='email_address_2' class='text-danger'>Technical Error! Try After Sometime. </label>"
				+ "</div>"
				+ "</div>";
			returnData.put("status", "0");

		}
		returnData.put("message", msg);
		return returnData;
	}
	
	/*--------------------------------Update insurance Report---------------------------*/
	@Override
	public Map<String, Object> updateInsuranceReport(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String>parameters = new HashMap<String, String>();
		String status = "";
		Operator operator = new Operator();			
		double dComm = 0.0;
		double retComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		
		double comm = 0.0;
		double disComm = 0.0;
		double sdisComm = 0.0;
		double reComm = 0.0;
		double charge = 0.0;
		
		String resellerId = "";
		String sdId = "";
		String distId = "";		
		String mobileNo = "";
		double sdCharge = 0.0;
		double distCharge = 0.0;			
		double resCharge = 0.0;
		double amount = 0.0;
		User user = null;
		String pending = "";
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if(request.get("id") == null || request.get("id").equals("")){
					returnData.put("message", "Invalid Insurance Request Details!");
					returnData.put("status", "0");
				} else {
					Map<String, Object> param = new HashMap<>();
					param.put("userId", request.get("userId"));
					param.put("id",Integer.parseInt(request.get("id")));
					List<Insurance> insList = insuranceDao.getInsuranceByNamedQuery("GetInsuranceByIdAndUser", param );
					if(insList.isEmpty()){
						returnData.put("message", "Invalid Insurance Request Details!");
						returnData.put("status", "0");
					} else {
						Insurance ins = insList.get(0);
						mobileNo = ins.getPolicyNumber();
						amount = ins.getAmount();
						pending = ins.getStatus();
						user = userDao.getUserByUserId(ins.getUserId());

						if (request.get("status").equals("SUCCESS")) {
							ins.setStatus("SUCCESS");
							boolean flag = insuranceDao.updateInsurance(ins);
							if(flag){
								param = new HashMap<>();
								param.put("tid", ins.getInsTid());
								List<Rechargedetails> rechargeList = rechargedetailsDao.getRechargeDetailsByNamedQuery("GetRechargeDetailsByTid", param);
								if(rechargeList.isEmpty()){
									ins.setStatus("SUCCESS");
									insuranceDao.updateInsurance(ins);
									status = "FAILED";
									returnData.put("message", "Invalid Insurance Request Details!");
									returnData.put("status", "0");
								} else {
									Rechargedetails rd = rechargeList.get(0);
									rd.setValidation("SUCCESS");
									rechargedetailsDao.updateRechargeDetails(rd);
									status = "SUCCESS";
									returnData.put("message", "Insurance Request update Successfully.");
									returnData.put("status", "1");
								}
								
							} else {
								status = "FAILED";
								returnData.put("message", "Failed to Update Insurance Request.");
								returnData.put("status", "1");
							}
						} else {
							status = "FAILED";
							ins.setStatus("FAILED");
							boolean flag = insuranceDao.updateInsurance(ins);
							if(flag){
								returnData.put("message", "Insurance Request update Successfully.");
								returnData.put("status", "1");
								param = new HashMap<>();
								param.put("tid", ins.getInsTid());
								List<Rechargedetails> rechargeList = rechargedetailsDao.getRechargeDetailsByNamedQuery("GetRechargeDetailsByTid", param);
								Rechargedetails rd = rechargeList.get(0);
								charge = rd.getCharge();
								comm = rd.getComm();
								rd.setValidation("FAILED");
								rechargedetailsDao.updateRechargeDetails(rd);
								commissionService.updateBalance(ins.getUserId(), "Insurance REFUND FOR -"+ins.getPolicyNumber(), "REFUND", (rd.getAmount()+rd.getCharge()-rd.getComm()), "CREDIT",0);
							} else {
								returnData.put("message", "Failed to Update Insurance Request.");
								returnData.put("status", "1");
							}
						}
					
					}
				}
			} else {
				returnData.put("message", "Invalid Insurance Request Details!");
				returnData.put("status", "0");
			}
			
			if(status.equals("SUCCESS")){											
				// ------------------------------  User's Charge calculation for the Recharge -------------------------------------//
				Individualcharge iCharge = commissionService.getIndividualChargeOnOperator(userDetails.getUserId(), operator.getOperatorId());
				if(user.getRoleId() == 5) {
					// Distributor Id
					distId = user.getUplineId();		
					
					// Super Distributor Id
					parameters = new HashMap<String, String>();
					parameters.put("userId", distId);	
					sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
					
					// Reseller Id
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					retComm = commissionService.getCommisionOnOperator(user.getUserId(), user.getWlId(), 5, operator.getOperatorId());
					dComm = commissionService.getCommisionOnOperator(distId, user.getWlId(), 4, operator.getOperatorId());
					sdComm = commissionService.getCommisionOnOperator(sdId, user.getWlId(), 3, operator.getOperatorId());
					resComm = commissionService.getCommisionOnOperator(resellerId, user.getWlId(), 2, operator.getOperatorId());
					//Charge
					if(iCharge == null) {						
						distCharge = commissionService.calculateChargeOnOperator(distId,  operator.getOperatorId(), userDetails.getWlId(), 4, amount);
						sdCharge = commissionService.calculateChargeOnOperator(sdId,  operator.getOperatorId(), userDetails.getWlId(), 3, amount);
						if(!resellerId.equals("admin")) {
							resCharge = commissionService.calculateChargeOnOperator(resellerId,  operator.getOperatorId(), userDetails.getWlId(), 2, amount);
						}
						
						dcharge = charge - distCharge;
						sCharge = distCharge - sdCharge;
						
						commissionService.updateBalance(distId, "Charge for Insurance Premium - "+mobileNo, "CHARGE", dcharge, "CREDIT",0);
						commissionService.updateBalance(sdId, "Charge for Insurance Premium - "+mobileNo, "CHARGE", sCharge, "CREDIT",0);
						if(!resellerId.equals("admin")) {
							reCharge = sdCharge - resCharge;
							commissionService.updateBalance(resellerId, "Charge for Insurance Premium - "+mobileNo, "INSURANCE Charge", reCharge, "CREDIT",0);
						}
					}
					//----------------Commission payment -----------------------------------------------------//
					dComm = RoundNumber.roundDouble((dComm * amount)/100);
					sdComm = RoundNumber.roundDouble((sdComm * amount)/100);
					resComm = RoundNumber.roundDouble((resComm * amount)/100);	
					
					disComm = dComm - comm;
					sdisComm = sdComm - dComm;
					reComm = resComm - sdisComm;
					
					commissionService.updateBalance(distId, "COMMISSION for Utility - "+mobileNo, "COMMISSION", disComm, "CREDIT",0);
					commissionService.updateBalance(sdId, "COMMISSION for Utility - "+mobileNo, "COMMISSION", sdisComm, "CREDIT",0);
					if(!resellerId.equals("admin")) {
						commissionService.updateBalance(resellerId, "COMMISSION for Utility - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
					}
					
					if(pending.equals("PENDING")){
						/*-------------------------------For earning and Surcharge ------------------*/
						// for DISTRIBUTOR 
						EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, userDetails.getWlId(), distId, disComm, dcharge, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
						
						// For Super Distributor
						EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm, sCharge, "Charge FOR - "+mobileNo,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
						
						// For RESELLER
						if(!resellerId.equals("admin")) {
							EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, reComm, reCharge, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
							earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
						}
						/*----------------------------------------------------------------------*/
					}
					
				} else if(user.getRoleId() == 4) {
					sdId = user.getUplineId();	
					
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					dComm = commissionService.getCommisionOnOperator(user.getUserId(), user.getWlId(), 4, operator.getOperatorId());
					sdComm = commissionService.getCommisionOnOperator(sdId, user.getWlId(), 3, operator.getOperatorId());
					resComm = commissionService.getCommisionOnOperator(resellerId, user.getWlId(), 2, operator.getOperatorId());
					//--------------Charge-------------
					if(iCharge == null) {					
						sdCharge = commissionService.calculateChargeOnOperator(sdId,  operator.getOperatorId(), userDetails.getWlId(), 3, amount);
						if(!resellerId.equals("admin")) {
							resCharge = commissionService.calculateChargeOnOperator(resellerId,  operator.getOperatorId(), userDetails.getWlId(), 2, amount);
						}					
						sCharge = charge - sdCharge;
						
						commissionService.updateBalance(sdId, "Charge for Insurance Premium - "+mobileNo, "CHARGE", sCharge, "CREDIT",0);
						if(!resellerId.equals("admin")) {
							reCharge = sdCharge - resCharge;
							commissionService.updateBalance(resellerId, "Charge for Insurance Premium - "+mobileNo, "INSURANCE Charge", reCharge, "CREDIT",0);
						}
					}
					//-----------------commission---------
					
					sdComm = RoundNumber.roundDouble((sdComm * amount)/100);
					resComm = RoundNumber.roundDouble((resComm * amount)/100);	
					
					
					
					sdisComm = sdComm - comm;
					reComm = resComm - sdisComm;					
					
					commissionService.updateBalance(sdId, "COMMISSION for Insurance Premium  - "+mobileNo, "COMMISSION", sdisComm, "CREDIT",0);
					if(!resellerId.equals("admin")) {
						commissionService.updateBalance(resellerId, "COMMISSION for Insurance Premium  - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
					}
					
					if(pending.equals("PENDING")){
						/*-------------------------------For earning and Surcharge ------------------*/
						
						// For Super Distributor
						EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdComm, sCharge, "Charge FOR - "+mobileNo,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
						
						// For RESELLER
						if(!resellerId.equals("admin")) {
							EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, resComm, reCharge, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
							earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
						}
						/*----------------------------------------------------------------------*/
					}
					
				} else if(user.getRoleId() == 3) {
					resellerId = userDetails.getUplineId();
					commissionService.getCommisionOnOperator(user.getUplineId(), user.getWlId(), 4, operator.getOperatorId());
					resComm = commissionService.getCommisionOnOperator(resellerId, user.getWlId(), 2, operator.getOperatorId());
					//--------------------Charge ----------------------------------
					if(iCharge == null) {					
						
						if(!resellerId.equals("admin")) {
							resCharge = commissionService.calculateChargeOnOperator(resellerId,  operator.getOperatorId(), userDetails.getWlId(), 2, amount);
						}					
						commissionService.updateBalance(sdId, "Charge for Insurance Premium - "+mobileNo, "CHARGE", sCharge, "CREDIT",0);
						if(!resellerId.equals("admin")) {
							reCharge = charge - resCharge;
							commissionService.updateBalance(resellerId, "Charge for Insurance Premium - "+mobileNo, "INSURANCE Charge", reCharge, "CREDIT",0);
						}
					}
					//-------------------------Commission-----------------------------
					resComm = RoundNumber.roundDouble((resComm * amount)/100);	
					
					reComm = resComm - comm;	
					if(!resellerId.equals("admin")) {
						commissionService.updateBalance(resellerId, "COMMISSION for Insurance Premium  - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
					}
					
					if(pending.equals("PENDING")){
						/*-------------------------------For earning and Surcharge ------------------*/
						
						// For RESELLER
						if(!resellerId.equals("admin")) {
							EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, resComm, reCharge, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
							earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
						}
						/*----------------------------------------------------------------------*/
					}
				}				
			}
		} catch (Exception e) {
			logger_log.error("updateInsuranceReport ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again!");
			returnData.put("status", "0");
		}
		return returnData;

	}
	
	/*----------------------UPDATE UTILITY REPORT ----------------------------*/
	@Override
	public Map<String, Object> updateUtilityReport(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String>parameters = new HashMap<String, String>();
		String status = "";
		Operator operator = new Operator();			
		double dComm = 0.0;
		
		double retComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		
		double comm = 0.0;
		double disComm = 0.0;
		double sdisComm = 0.0;
		double reComm = 0.0;
		
		String resellerId = "";
		String sdId = "";
		String distId = "";		
		String mobileNo = "";
		double sdCharge = 0.0;
		double distCharge = 0.0;			
		double resCharge = 0.0;
		double amount = 0.0;
		double charge = 0.0;
		User user = null;
		String pending = "";
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if(request.get("utilityId") == null || request.get("utilityId").equals("")){
					returnData.put("message", "Invalid Utility Request Details!");
					returnData.put("status", "0");
				} else {
					Map<String, Object> param = new HashMap<>();
					param.put("userId", request.get("userName"));
					param.put("id",Integer.parseInt(request.get("utilityId")));
					List<Utility> utiList = utilityDao.getUtilityByNamedQuery("GetUtilityByIdAndUser", param );
					if(utiList.isEmpty()){
						returnData.put("message", "Invalid Utility Request Details!");
						returnData.put("status", "0");
					} else {
						Utility utility = utiList.get(0);
						mobileNo = utility.getAccountNo();
						amount = utility.getAmount();
						pending = utility.getStatus();
						user = userDao.getUserByUserId(utility.getUserName());

						if (request.get("status").equals("SUCCESS")) {
							utility.setStatus("SUCCESS");
							boolean flag = utilityDao.updateUtility(utility);
							if(flag){
								param = new HashMap<>();
								param.put("tid", utility.getTransactionId());
								List<Rechargedetails> rechargeList = rechargedetailsDao.getRechargeDetailsByNamedQuery("GetRechargeDetailsByTid", param);
								if(rechargeList.isEmpty()){
									utility.setStatus("PENDING");
									utilityDao.updateUtility(utility);
									status = "FAILED";
									returnData.put("message", "Invalid Utility Request Details!");
									returnData.put("status", "0");
								} else {
									Rechargedetails rd = rechargeList.get(0);
									charge = rd.getCharge();
									comm = rd.getComm();
									rd.setValidation("SUCCESS");
									rechargedetailsDao.updateRechargeDetails(rd);
									status = "SUCCESS";
									returnData.put("message", "Utility Request update Successfully.");
									returnData.put("status", "1");
								}
								
							} else {
								status = "FAILED";
								returnData.put("message", "Failed to Update Balance Request.");
								returnData.put("status", "1");
							}
						} else {
							status = "FAILED";
							utility.setStatus("FAILED");
							boolean flag = utilityDao.updateUtility(utility);
							if(flag){
								returnData.put("message", "Utility Request update Successfully.");
								returnData.put("status", "1");
								param = new HashMap<>();
								param.put("tid", utility.getTransactionId());
								List<Rechargedetails> rechargeList = rechargedetailsDao.getRechargeDetailsByNamedQuery("GetRechargeDetailsByTid", param);
								Rechargedetails rd = rechargeList.get(0);
								rd.setValidation("FAILED");
								rechargedetailsDao.updateRechargeDetails(rd);
								commissionService.updateBalance(utility.getUserName(), "UTILITY REFUND FOR -"+utility.getAccountNo(), "REFUND", (rd.getAmount()+rd.getCharge()-rd.getComm()), "CREDIT",0);
							} else {
								returnData.put("message", "Failed to Update Balance Request.");
								returnData.put("status", "1");
							}
						}
						
					}
				}
			} else {
				returnData.put("message", "Invalid Utility Request Details!");
				returnData.put("status", "0");
			}
			
			if(status.equals("SUCCESS")){											
				// ------------------------------  User's Charge calculation for the Recharge -------------------------------------//
				Individualcharge iCharge = commissionService.getIndividualChargeOnOperator(userDetails.getUserId(), operator.getOperatorId());
				if(user.getRoleId() == 5) {
					// Distributor Id
					distId = user.getUplineId();		
					
					// Super Distributor Id
					parameters = new HashMap<String, String>();
					parameters.put("userId", distId);	
					sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
					
					// Reseller Id
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					retComm = commissionService.getCommisionOnOperator(user.getUserId(), user.getWlId(), 5, operator.getOperatorId());
					dComm = commissionService.getCommisionOnOperator(distId, user.getWlId(), 4, operator.getOperatorId());
					sdComm = commissionService.getCommisionOnOperator(sdId, user.getWlId(), 3, operator.getOperatorId());
					resComm = commissionService.getCommisionOnOperator(resellerId, user.getWlId(), 2, operator.getOperatorId());
					//Charge
					if(iCharge == null) {						
						distCharge = commissionService.calculateChargeOnOperator(distId,  operator.getOperatorId(), userDetails.getWlId(), 4, amount);
						sdCharge = commissionService.calculateChargeOnOperator(sdId,  operator.getOperatorId(), userDetails.getWlId(), 3, amount);
						if(!resellerId.equals("admin")) {
							resCharge = commissionService.calculateChargeOnOperator(resellerId,  operator.getOperatorId(), userDetails.getWlId(), 2, amount);
						}
						
						dcharge = charge - distCharge;
						sCharge = distCharge - sdCharge;
						
						commissionService.updateBalance(distId, "Charge for Utility - "+mobileNo, "CHARGE", dcharge, "CREDIT",0);
						commissionService.updateBalance(sdId, "Charge for Utility - "+mobileNo, "CHARGE", sCharge, "CREDIT",0);
						if(!resellerId.equals("admin")) {
							reCharge = sdCharge - resCharge;
							commissionService.updateBalance(resellerId, "Charge for Utility - "+mobileNo, "Utility Charge", reCharge, "CREDIT",0);
						}
					}
					//----------------Commission payment -----------------------------------------------------//
					dComm = RoundNumber.roundDouble((dComm * amount)/100);
					sdComm = RoundNumber.roundDouble((sdComm * amount)/100);
					resComm = RoundNumber.roundDouble((resComm * amount)/100);	
					
					disComm = dComm - comm;
					sdisComm = sdComm - dComm;
					reComm = resComm - sdisComm;
					
					commissionService.updateBalance(distId, "COMMISSION for Utility - "+mobileNo, "COMMISSION", disComm, "CREDIT",0);
					commissionService.updateBalance(sdId, "COMMISSION for Utility - "+mobileNo, "COMMISSION", sdisComm, "CREDIT",0);
					if(!resellerId.equals("admin")) {
						commissionService.updateBalance(resellerId, "COMMISSION for Utility - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
					}
					
					if(pending.equals("PENDING")){
						/*-------------------------------For earning and Surcharge ------------------*/
						// for DISTRIBUTOR 
						EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, userDetails.getWlId(), distId, disComm, dcharge, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
						
						// For Super Distributor
						EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm, sCharge, "Charge FOR - "+mobileNo,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
						
						// For RESELLER
						if(!resellerId.equals("admin")) {
							EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, reComm, reCharge, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
							earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
						}
						/*----------------------------------------------------------------------*/
					}
					
				} else if(user.getRoleId() == 4) {
					sdId = user.getUplineId();	
					
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					dComm = commissionService.getCommisionOnOperator(user.getUserId(), user.getWlId(), 4, operator.getOperatorId());
					sdComm = commissionService.getCommisionOnOperator(sdId, user.getWlId(), 3, operator.getOperatorId());
					resComm = commissionService.getCommisionOnOperator(resellerId, user.getWlId(), 2, operator.getOperatorId());
					//--------------Charge-------------
					if(iCharge == null) {					
						sdCharge = commissionService.calculateChargeOnOperator(sdId,  operator.getOperatorId(), userDetails.getWlId(), 3, amount);
						if(!resellerId.equals("admin")) {
							resCharge = commissionService.calculateChargeOnOperator(resellerId,  operator.getOperatorId(), userDetails.getWlId(), 2, amount);
						}					
						sCharge = charge - sdCharge;
						
						commissionService.updateBalance(sdId, "Charge for Utility - "+mobileNo, "CHARGE", sCharge, "CREDIT",0);
						if(!resellerId.equals("admin")) {
							reCharge = sdCharge - resCharge;
							commissionService.updateBalance(resellerId, "Charge for Utility - "+mobileNo, "INSURANCE Charge", reCharge, "CREDIT",0);
						}
					}
					//-----------------commission---------
					
					sdComm = RoundNumber.roundDouble((sdComm * amount)/100);
					resComm = RoundNumber.roundDouble((resComm * amount)/100);	
					
					
					
					sdisComm = sdComm - comm;
					reComm = resComm - sdisComm;					
					
					commissionService.updateBalance(sdId, "COMMISSION for Utility - "+mobileNo, "COMMISSION", sdisComm, "CREDIT",0);
					if(!resellerId.equals("admin")) {
						commissionService.updateBalance(resellerId, "COMMISSION for Utility - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
					}
					
					if(pending.equals("PENDING")){
						/*-------------------------------For earning and Surcharge ------------------*/
						
						// For Super Distributor
						EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdComm, sCharge, "Charge FOR - "+mobileNo,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
						
						// For RESELLER
						if(!resellerId.equals("admin")) {
							EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, resComm, reCharge, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
							earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
						}
						/*----------------------------------------------------------------------*/
					}
					
				} else if(user.getRoleId() == 3) {
					resellerId = userDetails.getUplineId();
					commissionService.getCommisionOnOperator(user.getUplineId(), user.getWlId(), 4, operator.getOperatorId());
					resComm = commissionService.getCommisionOnOperator(resellerId, user.getWlId(), 2, operator.getOperatorId());
					//--------------------Charge ----------------------------------
					if(iCharge == null) {					
						
						if(!resellerId.equals("admin")) {
							resCharge = commissionService.calculateChargeOnOperator(resellerId,  operator.getOperatorId(), userDetails.getWlId(), 2, amount);
						}					
						commissionService.updateBalance(sdId, "Charge for Utility - "+mobileNo, "CHARGE", sCharge, "CREDIT",0);
						if(!resellerId.equals("admin")) {
							reCharge = charge - resCharge;
							commissionService.updateBalance(resellerId, "Charge for Utility - "+mobileNo, "Utility Charge", reCharge, "CREDIT",0);
						}
					}
					//-------------------------Commission-----------------------------
					resComm = RoundNumber.roundDouble((resComm * amount)/100);	
					
					reComm = resComm - comm;	
					if(!resellerId.equals("admin")) {
						commissionService.updateBalance(resellerId, "COMMISSION for Utility - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
					}
					
					if(pending.equals("PENDING")){
						/*-------------------------------For earning and Surcharge ------------------*/
						
						// For RESELLER
						if(!resellerId.equals("admin")) {
							EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, resComm, reCharge, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
							earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
						}
						/*----------------------------------------------------------------------*/
					}
				}				
			}
		} catch (Exception e) {
			logger_log.error("updateUtilityReport ::::::::::: " + e);
			returnData.put("message", "Exception! Please try again!");
			returnData.put("status", "0");
		}
		return returnData;

	}
	
	@Override
	public Map<String, Object> updateRemark(Map<String, String> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		return returnData;
	}
	

	@Override
	public Map<String, Object> updateRechargeReport(Map<String, String> request, User user) {		
		Map<String, Object> returnData = new HashMap<String, Object>();
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
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double distCharge=0.0;
		double sdCharge=0.0;
		double totalAmount = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";
		String rId="";
		String oidremark="";
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			if(user.getRoleId() == 1){
				if (UtilityClass.checkParameterIsNull(request)) {
					String rechargeStatus = request.get("status");
					
					param = new HashMap<String, Object>();
					param.put("tid", request.get("tid"));
					oidremark=request.get("oid");
					List<Rechargedetails> rechargeDetails = rechargedetailsDao.getRechargeDetailsByNamedQuery("GetRechargeDetailsByTid", param);
					if(!rechargeDetails.isEmpty()) {
						Rechargedetails rechargedetail = rechargeDetails.get(0);
						String mobileNo = rechargedetail.getMobile();
						charge = rechargedetail.getCharge();
						comm = rechargedetail.getComm();
						Operator operator = operatorDao.getOperatorByOperatorId(rechargedetail.getOperatorId());					
						User userDetails = userDao.getUserByUserId(rechargedetail.getUserId());
						totalAmount = RoundNumber.roundDouble((rechargedetail.getAmount() + rechargedetail.getCharge()) - rechargedetail.getComm());			
						
						parameters = new HashMap<String, String>();
						parameters.put("userId", userDetails.getUserId());	
						
						/*-------------------------------------COMMISSION------------------------------------------------------*/
						if(userDetails.getRoleId() == 5) {
							//Retailer Id
							rId=rechargedetail.getUserId();
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
							pckret =commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId()); 
							if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								rComm =(pckret.getComm()*rechargedetail.getAmount())/100;
								}else{
								rComm=pckret.getComm();	
								}
							
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
							}else{
							dComm=pckdis.getComm();	
							}
							
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							if(!resellerId.equalsIgnoreCase("admin")){
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
							}else{
							resComm =pckres.getComm();	
							}
							}
							if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
                            	distCharge=(pckdis.getCharge()*rechargedetail.getAmount())/100;
                            }else{
                            	distCharge=pckdis.getCharge();
                            }
						     if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
						    	 sdCharge =(pcksd.getCharge()*rechargedetail.getAmount())/100;
						     }else{
						    	 sdCharge=pcksd.getCharge();
						     }
							if(!resellerId.equals("admin")) {
								if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								resCharge = (pckres.getCharge()*rechargedetail.getAmount())/100;
								}else{
								resCharge = pckres.getCharge();	
								}
							}
							
						} else if(userDetails.getRoleId() == 4) {
							// Distributor Id
							distId =rechargedetail.getUserId();
							// Super Distributor Id
							sdId = userDetails.getUplineId();	
							// Reseller Id
							parameters = new HashMap<String, String>();
							parameters.put("userId", sdId);							
							resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
							
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
							}else{
							dComm=pckdis.getComm();	
							}
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							if(!resellerId.equalsIgnoreCase("admin")){
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
							}else{
							resComm =pckres.getComm();	
							}
							}
							if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
                            	distCharge=(pckdis.getCharge()*rechargedetail.getAmount())/100;
                            }else{
                            	distCharge=pckdis.getCharge();
                            }
						     if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
						    	 sdCharge =(pcksd.getCharge()*rechargedetail.getAmount())/100;
						     }else{
						    	 sdCharge=pcksd.getCharge();
						     }
							if(!resellerId.equals("admin")) {
								if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								resCharge = (pckres.getCharge()*rechargedetail.getAmount())/100;
								}else{
								resCharge = pckres.getCharge();	
								}
							}
						} else if(userDetails.getRoleId() == 3) {
							// Super Distributor Id
							sdId = rechargedetail.getUserId();
							// Reseller Id
							resellerId = userDetails.getUplineId();	
							
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
						    	 sdCharge =(pcksd.getCharge()*rechargedetail.getAmount())/100;
						     }else{
						    	 sdCharge=pcksd.getCharge();
						     }
						//	System.out.println("sdComm=="+sdComm);
							if(!resellerId.equalsIgnoreCase("admin")){
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
							}else{
							resComm =pckres.getComm();	
							}
							if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								resCharge = (pckres.getCharge()*rechargedetail.getAmount())/100;
								}else{
								resCharge = pckres.getCharge();	
								}
						}
							
							
							
						}
						

						if(rechargeStatus.equals("SUCCESS")) {
							
							if(rechargedetail.getStatus().equals("FAILED")){
								if(operator.getServiceType().equals("7") || operator.getServiceType().equals("6")){
									commissionService.updateBalance(userDetails.getUserId(), "UTILITY to "+mobileNo, "UTILITY", totalAmount, "DEBIT",0);
								} else if(operator.getServiceType().equals("5")){
									commissionService.updateBalance(userDetails.getUserId(), "Insurance Premium to "+mobileNo, "Insurance Premium", totalAmount, "DEBIT",0);
								} else {
									commissionService.updateBalance(userDetails.getUserId(), "Recharge to "+mobileNo, "Recharge", totalAmount, "DEBIT",0);
								}
							}
							
							if(rechargedetail.getValidation().equals("PENDING")){	
								
								if(userDetails.getRoleId() == 5) {	
									if(dComm==0){disComm=0;}
									else{disComm=dComm-rComm;}
									if(sdComm==0){sdisComm=0;}
									else{
									sdisComm = sdComm-dComm;}
									if(resComm==0){reComm=0;}
									else{
									reComm = resComm-sdComm;
									}
																	
									commissionService.updateBalance(distId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", disComm, "CREDIT",0);
									commissionService.updateBalance(sdId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", sdisComm, "CREDIT",0);
									if(!resellerId.equals("admin")) {
										commissionService.updateBalance(resellerId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
									}
									
									if(rechargedetail.getValidation().equals("PENDING")){
										/*-------------------------------For earning and Surcharge ------------------*/
										// for DISTRIBUTOR 
										EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, userDetails.getWlId(), distId, disComm, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
										earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
										
										// For Super Distributor
										EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm, 0.0, "Charge FOR - "+mobileNo,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
										earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
										
										// For RESELLER
										if(!resellerId.equals("admin")) {
											EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, reComm, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
											earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
										}
										/*----------------------------------------------------------------------*/
									}
									
									
								} else if(userDetails.getRoleId() == 4) {								
									if(sdComm==0){sdisComm=0;}
									else{
									sdisComm = sdComm-dComm;}
									if(resComm==0){reComm=0;}
									else{
									reComm = resComm-sdComm;
									}	
									
									
									commissionService.updateBalance(sdId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", sdisComm, "CREDIT",0);
									if(!resellerId.equals("admin")) {
										commissionService.updateBalance(resellerId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
									}
									
									if(rechargedetail.getValidation().equals("PENDING")){
										/*-------------------------------For earning and Surcharge ------------------*/
										// For Super Distributor
										EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm, 0.0, "Charge FOR - "+mobileNo,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
										earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
										
										// For RESELLER
										if(!resellerId.equals("admin")) {
											EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, reComm, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
											earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
										}
										/*----------------------------------------------------------------------*/
									}
									
									
								} else if(userDetails.getRoleId() == 3) {																			
									if(resComm==0){reComm=0;}
									else{
									reComm = resComm-sdComm;
									}
									
								//	System.out.println("COMMISSION for Recharge sdcomm=="+sdisComm);
								//	System.out.println("COMMISSION for Recharge recomm=="+reComm);
									commissionService.updateBalance(sdId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", sdisComm, "CREDIT",0);
									if(!resellerId.equals("admin")) {
										commissionService.updateBalance(resellerId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
									}
									
									if(rechargedetail.getValidation().equals("PENDING")){
										/*-------------------------------For earning and Surcharge ------------------*/
										
										// For Super Distributor
										EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm, 0.0, "Charge FOR - "+mobileNo,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
										earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
										
										// For RESELLER
										if(!resellerId.equals("admin")) {
											EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, reComm, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
											earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
										}
										/*----------------------------------------------------------------------*/
									}
									
									
								}		
							}
							rechargedetail.setStatus("SUCCESS");
							rechargedetail.setValidation("SUCCESS");
							rechargedetail.setOid(oidremark);
							rechargedetailsDao.updateRechargeDetails(rechargedetail);
							returnData.put("status", "1");
							returnData.put("message", "Status : "+rechargeStatus+" Validation Status update Successfully.");
							
						} else if(rechargeStatus.equals("FAILED")) {
							
							if(rechargedetail.getValidation().equals("PENDING")){
								if(operator.getServiceType().equals("7") || operator.getServiceType().equals("6")){
									commissionService.updateBalance(userDetails.getUserId(), "REFUND UTILITY to "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
								} else if(operator.getServiceType().equals("5")){
									commissionService.updateBalance(userDetails.getUserId(), "REFUND Insurance Premium to "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
								} else {
									commissionService.updateBalance(userDetails.getUserId(), "REFUND Recharge to "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
								}
							}
							
							if(rechargedetail.getValidation().equals("SUCCESS")){
								
								if(operator.getServiceType().equals("7") || operator.getServiceType().equals("6")){
									commissionService.updateBalance(userDetails.getUserId(), "REFUND UTILITY to "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
								} else if(operator.getServiceType().equals("5")){
									commissionService.updateBalance(userDetails.getUserId(), "REFUND Insurance Premium to "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
								} else {
									commissionService.updateBalance(userDetails.getUserId(), "REFUND Recharge to "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
								}
								
							//	Individualcharge iCharge = commissionService.getIndividualChargeOnOperator(userDetails.getUserId(), operator.getOperatorId());
						/*		rechargedetail.setStatus("FAILED");
								rechargedetail.setValidation("FAILED");							
								rechargedetailsDao.updateRechargeDetails(rechargedetail);*/
								/*returnData.put("status", "0");
								returnData.put("message", "Status : "+rechargeStatus+" Validation Status update Successfully.");*/
								//commissionService.updateBalance(userDetails.getUserId(), "REFUND for Recharge - "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
								
								// --------------------- CHARGE BACK Refund -------------------------------------------------------------
								
								if(userDetails.getRoleId() == 5) {
									//Retailer Id
									double dcomm1=0.0;
									double sdComm1=0.0;
									// Charge REFUND
									if(dComm==0){dcomm1=0;}
									else{dcomm1=dComm-rComm;}
									if(sdComm==0){sdComm1=0;}
									else{sdComm1= sdComm-dComm;}
										double dcharge1=charge-distCharge;
										
										double sdCharge1=distCharge-sdCharge;
										dcharge = dcomm1+dcharge1;
										System.out.println(dcomm1);
										System.out.println(dcharge1);
										System.out.println(dcharge);
										sCharge = sdComm1+sdCharge1;
										System.out.println(sdComm-dComm);
										System.out.println(distCharge-sdCharge);
										
										commissionService.updateBalance(distId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", dcharge, "DEBIT",0);									
										commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
										
										if(!resellerId.equals("admin")) {
											reCharge = resComm -( dcharge+sCharge+rComm);
											commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
										}
									
									
									
								} else if(userDetails.getRoleId() == 4) {
									// Distributor Id
									distId =rechargedetail.getUserId();
									sdId = userDetails.getUplineId();							
									parameters = new HashMap<String, String>();
									parameters.put("userId", sdId);							
									resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
									
									
																		
									 sdCharge = commissionService.calculateChargeOnOperator(sdId,  operator.getOperatorId(), userDetails.getWlId(), 3, rechargedetail.getAmount());
										if(!resellerId.equals("admin")) {
											resCharge = commissionService.calculateChargeOnOperator(resellerId,  operator.getOperatorId(), userDetails.getWlId(), 2, rechargedetail.getAmount());
										}								
										
										sCharge = sdComm;	
										//System.out.println("sCharge=="+sCharge);
										
										commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
										
										if(!resellerId.equals("admin")) {
											reCharge = resComm -( dComm+sdComm);
											//System.out.println("reCharge=="+reCharge);
											commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
										}
									
								
									
								} else if(userDetails.getRoleId() == 3) {	
									
									// Super Distributor Id
									sdId = rechargedetail.getUserId();
									resellerId = userDetails.getUplineId();
									
									
										if(!resellerId.equals("admin")) {
											resCharge = commissionService.calculateChargeOnOperator(resellerId,  operator.getOperatorId(), userDetails.getWlId(), 2, rechargedetail.getAmount());
										}	
										if(!resellerId.equals("admin")) {
											reCharge = resComm -sdComm;
											//System.out.println("reCharge=="+reCharge);
											commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
										}
									
									
								}
								returnData.put("status", "1");
								returnData.put("message", "Status : "+rechargeStatus+" Validation Status update Successfully.");
								
								if(operator.getServiceType().equals("7") || operator.getServiceType().equals("6")){								
									param = new HashMap<>();
									param.put("transactionId", rechargedetail.getTid());
									List<Utility> uList = utilityDao.getUtilityByNamedQuery("GetUtilityByTransactionId", param);
									if(!uList.isEmpty()){
										Utility u = uList.get(0);
										u.setStatus(rechargeStatus);
										utilityDao.updateUtility(u);
									}
								} else if(operator.getServiceType().equals("5")) {								
									param = new HashMap<>();
									param.put("insTid", rechargedetail.getTid());
									List<Insurance> insList = insuranceDao.getInsuranceByNamedQuery("GetInsuranceByinsTid", param);
									if(!insList.isEmpty()){
										Insurance insurance = insList.get(0);
										insurance.setStatus(rechargeStatus);
										insuranceDao.updateInsurance(insurance);
									}
								}
								
								
							}
							rechargedetail.setStatus("FAILED");
							rechargedetail.setValidation("FAILED");	
							rechargedetail.setOid(oidremark);
							rechargedetailsDao.updateRechargeDetails(rechargedetail);
							returnData.put("status", "0");
							returnData.put("message", "Status : "+rechargeStatus+" Validation Status update Successfully.");
							
							
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Your Request in process.");
						}
						
						if(rechargedetail.getSource().equals("API")){
							param = new HashMap<>();
							param.put("userId", userDetails.getUserId());
							List<Apisetting> list = apisettingDao.getApisettingByNamedQuery("getApisettingByuserId", param);
							String responseUrl = "";
							String responseUrlResponse = "";
							try {
								if (!list.isEmpty()) {
									responseUrl = list.get(0).getResponseUrl() + "?tranId="	+ rechargedetail.getPtid() + "&opTId=" + rechargedetail.getOperatorId() + "&status="	+ rechargeStatus;
									responseUrlResponse = sendRechargeRequest.sendRechagreReq(responseUrl);
								} else {
									responseUrl = list.get(0).getResponseUrl() + "?tranId="	+ rechargedetail.getPtid() + "&opTId=" + rechargedetail.getOperatorId() + "&status=" + rechargeStatus;
									responseUrlResponse = "No Response Url Set";
								}									
							} catch (Exception e) {
								responseUrlResponse = "EXCEPTION";
								logger_log.error("doopMeApiResponse ::::::::::::::: SENDING RESPONSE ERROR");
								logger_log.error("doopMeApiResponse ::::::::::::::: " + e);
							}
							
							Apitrace apitrace = new Apitrace(userDetails.getUserId(), rechargedetail.getPtid(),	responseUrl, responseUrlResponse, GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime());
							apitraceDao.insertApitrace(apitrace);
						}
					
					} else {
						returnData.put("status", "0");
						returnData.put("message", "No Recharge Details Found.");
					}
				} else {
					returnData.put("status", "0");
					returnData.put("message", "Parameter not pass correctly.");
				}
			}else {
				returnData.put("status", "0");
				returnData.put("message", "Forbidden User.");
			}
		} catch (Exception e) {
			returnData.put("status", "0");
			returnData.put("message", "Exception! Please contact to admin.");
			logger_log.error("doopMeApiResponse ::::::::::::::: " + e);
		}
		return returnData;
	}

	@Override
	public Reseller getResellerByWlId(String wlId) {		
		return resellerDao.getResellerById(wlId);
	}

	@Override
	public void insertUserAttachment(String userId, MultipartFile panCard, MultipartFile adharCard,
			MultipartFile photo) {

		try{
			byte[] pan = panCard.getBytes();
			byte[] adhar = adharCard.getBytes();
			byte[] pic = photo.getBytes();
			User userDetails = userDao.getUserByUserId(userId);
			Userattachment userattachment = new Userattachment(userDetails.getUserId(), userDetails.getWlId(),pan, adhar, pic);
			userattachmentDao.insertUserattachment(userattachment );
		} catch (Exception e) {			
			logger_log.error("insertUserAttachment ::::::::::::::: " + e);
		}
		
	}
	
	@Override
	public Map<String, Object> getIMPScharge(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", request.get("userId"));
			User user=userDao.getUserByUserId(request.get("userId"));
			List<Impscommission> list = impscommissionDao.getImpscommissionByNamedQuery("getImpsCommissionByUsername",
					param);
			for(Impscommission imp:list){
				System.out.println("Id::::::::::::::::"+imp.getId());
				imp.setWlId(user.getWlId());
				imp.setRoleId(user.getRoleId().toString());
			}
			System.out.println("getIMPScharge:::::::::::" + list.size());
			returnData.put("impscommission", list);
		} catch (Exception e) {
			logger_log.error("getIMPScharge :::::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> setIMPScharge(Impscommission[] request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag = false;
		try {
			for (Impscommission imps : request) {
				if (imps.getId() == 0) {
					flag = impscommissionDao.insertImpscommission(imps);
				} else {
					flag = impscommissionDao.updateImpscommission(imps);
				}
			}
			if (flag) {
				returnData.put("message", "Update Successfully!");
				returnData.put("status", "1");
			} else {
				returnData.put("message", "Failed to Update!");
				returnData.put("status", "1");
			}

		} catch (Exception e) {
			logger_log.error("setIMPScharge :::::::::::::: " + e);
			returnData.put("message", "Exception! Please try again!");
			returnData.put("status", "0");
			return returnData;

		}
		return returnData;
	}
	
	
	@Override
	public Map<String, Object> packageCreate(Map<String, String> request, User userDetail) {
		Map<String, Object> returndata = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		String pck_name = "";
		String service_type = "";
		String package_id = "";
		String query = "getPackageBynameAnduserid";
		boolean flag = false;
		try {
			pck_name = request.get("name");
			service_type = request.get("type");
			params.put("name", pck_name);
			params.put("user_id", userDetail.getUserId());
			PackageDetail pck = null;
			package_id = packageservice.generatePackageId();
			List<PackageDetail> list = packagedetailDao.getPackageDetailByNamedQuery(query, params);
			if (list.size() == 0) {
				package_id = packageservice.generatePackageId();
				pck = new PackageDetail(package_id, pck_name, service_type, userDetail.getUserId());
				flag = packagedetailDao.insertPackageDetail(pck);
				if (flag) {
					returndata.put("message", "Package added successfully");
					if (service_type.equalsIgnoreCase("Recharge")) {
						List<Operator> operatorlist = operatorDao.getAllOpeartor();
						for (Operator op : operatorlist) {
							PackageWiseChargeComm pckw = new PackageWiseChargeComm(package_id, op.getOperatorId(), 0.0,
									"PERCENTAGE", 0.0, "PERCENTAGE", "");
							packwisechargecomm.insertPackageWiseChargeComm(pckw);
						}
					} else if (service_type.equalsIgnoreCase("Pan")) {
						params = new HashMap<String, Object>();
						params.put("serviceType", "8");
						List<Operator> operatorlist = operatorDao.getOperatorByNamedQuery("getOperatorByServiceType",
								params);
						for (Operator op : operatorlist) {
							PackageWiseChargeComm pckw = new PackageWiseChargeComm(package_id, op.getOperatorId(), 0.0,
									"RUPEE", 0.0, "RUPEE", "");
							packwisechargecomm.insertPackageWiseChargeComm(pckw);
						}

					} else if (service_type.equalsIgnoreCase("RBL AEPS")) {
						params = new HashMap<String, Object>();
						params.put("api", "RBL");
						List<AEPSCommission> aepscommlist = aepscommissiondao
								.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi", params);
						for (AEPSCommission aeps : aepscommlist) {
							PackageWiseChargeComm pckw = new PackageWiseChargeComm(package_id, aeps.getId(), 0.0,
									"RUPEE", 0.0, "RUPEE", "");
							packwisechargecomm.insertPackageWiseChargeComm(pckw);
						}

					}else if (service_type.equalsIgnoreCase("YesBank AEPS")) {
						params = new HashMap<String, Object>();
						params.put("api", "YesBank");
						List<AEPSCommission> aepscommlist = aepscommissiondao
								.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi", params);
						for (AEPSCommission aeps : aepscommlist) {
							PackageWiseChargeComm pckw = new PackageWiseChargeComm(package_id, aeps.getId(), 0.0,
									"RUPEE", 0.0, "RUPEE", "");
							packwisechargecomm.insertPackageWiseChargeComm(pckw);
						}

					}
					
					
					else if(service_type.equalsIgnoreCase("DMR")){
						params = new HashMap<String, Object>();
						params.put("api","DMR");
						List<DMRCommission> aepscommlist = DMRCommissionDao.getDMRCommissionByNamedQuery("getDMRCommissionByApi", params);
						for(DMRCommission aeps :aepscommlist){
							PackageWiseChargeComm pckw = new PackageWiseChargeComm(package_id, aeps.getId(), 0.0,
									"RUPEE", 0.0, "RUPEE", "");
							packwisechargecomm.insertPackageWiseChargeComm(pckw);
						}
						
					}else if(service_type.equalsIgnoreCase("MicroATM")){
						params = new HashMap<String, Object>();
						params.put("api", "MicroATM");
						List<AEPSCommission> aepscommlist = aepscommissiondao
								.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi", params);
						for (AEPSCommission aeps : aepscommlist) {
							PackageWiseChargeComm pckw = new PackageWiseChargeComm(package_id, aeps.getId(), 0.0,
									"RUPEE", 0.0, "RUPEE", "");
							packwisechargecomm.insertPackageWiseChargeComm(pckw);
						}

					}else if(service_type.equalsIgnoreCase("Encore AEPS")){
						params = new HashMap<String, Object>();
						params.put("api", "Encore AEPS");
						List<AEPSCommission> aepscommlist = aepscommissiondao
								.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi", params);
						for (AEPSCommission aeps : aepscommlist) {
							PackageWiseChargeComm pckw = new PackageWiseChargeComm(package_id, aeps.getId(), 0.0,
									"RUPEE", 0.0, "RUPEE", "");
							packwisechargecomm.insertPackageWiseChargeComm(pckw);
						}

					}else if(service_type.equalsIgnoreCase("AadharPay")){
						params = new HashMap<String, Object>();
						params.put("api", "AadharPay");
						List<AEPSCommission> aepscommlist = aepscommissiondao
								.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi", params);
						for (AEPSCommission aeps : aepscommlist) {
							PackageWiseChargeComm pckw = new PackageWiseChargeComm(package_id, aeps.getId(), 0.0,
									"RUPEE", 0.0, "RUPEE", "");
							packwisechargecomm.insertPackageWiseChargeComm(pckw);
						}

					}else if(service_type.equalsIgnoreCase("Flight")){
						params = new HashMap<String, Object>();
						
						List<Airline> aepscommlist = airlineDao.getAllAirline();
						for (Airline aeps : aepscommlist) {
							PackageWiseChargeComm pckw = new PackageWiseChargeComm(package_id, aeps.getId(), 0.0,
									"RUPEE", 0.0, "RUPEE", "");
							packwisechargecomm.insertPackageWiseChargeComm(pckw);
						}

					}
					returndata.put("message", "Package added successfully");
				} else {
					returndata.put("message", "Package not added");
				}
			} else {
				returndata.put("message", "Package already exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.warn("packageCreate::::::::::::" + e);
		}
		return returndata;
	}
	
	@Override
	public Map<String, Object> updatemyPackage(Map<String, String> request,User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		boolean flag = false;
		try {
			if(userDetails.getRoleId()==1){
				flag = packwisechargecomm.updatePackagewiseCommByOperator(request);
				if (flag) {
					returnData.put("message", "updated successfully");
					returnData.put("status", "1");
				} else {
					returnData.put("message", "Not updated");
					returnData.put("status", "0");
				}
			}else{
				boolean flags=false;
					
				param=new HashMap<String, String>();
				param.put("userId", userDetails.getUserId());
				param.put("packageId", request.get("package_id"));
				param.put("operatorId", request.get("operator_id"));
				String sql="select * from pck_charge_com where operator_id=:operatorId and package_id=(select package_id from assigned_package where user_id=:userId and service_type=(select distinct service_type from package where package_id=:packageId))";
				List<Object> list = customQueryService.getDataByQueryUsingCustomQuery(sql, param);
				System.out.println(list.size());
				Object[] obj = (Object[]) list.get(0);
			
				if(Double.parseDouble(request.get("charge"))>=(Double)obj[3]){
					System.out.println((Double)obj[3]);
					if(Double.parseDouble(request.get("comm"))<=(Double)obj[5]){
						System.out.println((Double)obj[5]);
					flags=true;
					}
				}
			
				if(flags){
					flag = packwisechargecomm.updatePackagewiseCommByOperator(request);
					if (flag) {
						returnData.put("message", "updated successfully");
						returnData.put("status", "1");
					} else {
						returnData.put("message", "Not updated");
						returnData.put("status", "0");
					}
				}else{
					returnData.put("message", "Please assign correct charge and commission");
					returnData.put("status", "0");
				}
			}

		} catch (Exception e) {
			logger_log.error("updatemyPackage :::::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "2");
		}

		return returnData;
	}
	
	@Override
	public Map<String, Object> assignPackage(Map<String, String> request, String username,User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag = false;
		List<AssignedPackage> list=new ArrayList<AssignedPackage>();
		try {
			String[] servicedetails = request.get("package").split(",");
			/*param.put("service_type", servicedetails[0]);
			param.put("user_id", username);*/
			int UserID=Integer.parseInt(request.get("userType"));
			int loginUserID=userDetails.getRoleId();
			if(request.get("userId").equals("ALL")) {
				Map<String,Object> userTyPe=new HashMap<String, Object>();
				List<User> list1=new ArrayList<User>();
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
				}
				else if(loginUserID==2){
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
				else if(loginUserID==3){
					if(UserID==4){
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
				else{
					System.out.println("uplineid:::::::::::::"+userDetails.getUserId());
					userTyPe.put("userTyPe", Integer.parseInt(request.get("userType")));
					userTyPe.put("uplineid", userDetails.getUserId());
					list1=userDao.getUserByNamedQuery("getUserByUserType",userTyPe);
				}
				
				String AllId="";
				if(!list1.isEmpty()) {
					for(User user : list1) {
						 AllId =user.getUserId();
						 param.put("service_type", servicedetails[0]);
							param.put("user_id", AllId);
							list = assignedPackage.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", param);
							if (list.isEmpty()) {
								AssignedPackage asp1 = new AssignedPackage(servicedetails[1], servicedetails[0], AllId);
								flag = assignedPackage.insertAssignedPackage(asp1);
							}
					}
					if (flag) {
						returnData.put("message", "Package is Assigned Successfully");
					}

				 else {
					returnData.put("message", "Same service type Package can not be added two times");
				 	}
				}
				
			}else{
				param.put("service_type", servicedetails[0]);
				param.put("user_id", username);
			 list = assignedPackage.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", param);
			
			if (list.isEmpty()) {
				AssignedPackage asp = new AssignedPackage(servicedetails[1], servicedetails[0], username);
				flag = assignedPackage.insertAssignedPackage(asp);
			}
				if (flag) {
					returnData.put("message", "Package is Assigned Successfully");
				}

			 else {
				returnData.put("message", "Same service type Package can not be added two times");

			}
			}

		
		}catch (Exception e) {
			e.printStackTrace();
			logger_log.error("assignPackage :::::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "2");
		}

		return returnData;
	}
	
	@Override
	public Map<String, Object> viewAssignPackage(User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		try {
			param.put("userId", userDetails.getUserId());
			List<ViewAssignPackage> list = customQueryServiceLogic.getAssignedPackage(CustomSqlQuery.getAssignedPackage,param);
			if (list.isEmpty()) {
				returnData.put("status", "0");
				returnData.put("message", "No data available");
			} else {
				returnData.put("report", list);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("viewAssignPackage :::::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "2");
		}

		return returnData;
	}
	
	@Override
	public Map<String, Object> assignEditPackage(Map<String, String> request, String username) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag = false;
		List<AssignedPackage> list=new ArrayList<AssignedPackage>();
		try {
			String[] servicedetails = request.get("package").split(",");
			/*param.put("service_type", servicedetails[0]);
			param.put("user_id", username);*/
			
			if(request.get("user_id").equals("ALL")) {
				returnData.put("message", "Please Select One User");
			}else{
				param.put("service_type", servicedetails[0]);
				param.put("user_id", username);
			    list = assignedPackage.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", param);
			// System.out.println("packageOldId::::::::::::::::"+list);
			if (!list.isEmpty()) {
				
					AssignedPackage asspkage=list.get(0);
					String packageOldId=asspkage.getPackage_id();
					int rowId=asspkage.getId();
					if(!packageOldId.equals(servicedetails[1])){
					asspkage.setPackage_id(servicedetails[1]);
					flag=assignedPackage.updateAssignedPackage(asspkage);
			
				if (flag) {
					returnData.put("message", "Package is Assigned Successfully");
					}

				else {
					returnData.put("message", "Package is Not Assigned Successfully!!");
					}
			} 
					else{
						returnData.put("message", "Same Package is Already Assigned..");
					}		
			}
			}
		
		}catch (Exception e) {
			e.printStackTrace();
			logger_log.error("assignEditPackage :::::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "2");
		}
		return returnData;
		}
	
	@Override
	public Map<String, Object> assignedPackage(User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			param.put("user_id", userDetails.getUserId());
			List<AssignedPackage> list = assignedPackage.getAssignedPackageByNamedQuery("getAssignedPackageByUser",param);
			if (list.isEmpty()) {
				returnData.put("status", "0");
				returnData.put("message", "No data available");
			} else {
				returnData.put("report", list);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("assignPackage :::::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "2");
		}

		return returnData;
	}
	
	@Override
	public Map<String, Object> DeleteMyPackageDetail(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				String pack_id = request.get("package_id");
				boolean flag = packagedetailDao.deletePackageDetail(pack_id);
				boolean flag1 = packwisechargecomm.deletePackageWiseChargeComm(pack_id);
				boolean flag2 = assignedPackage.deleteAssignPackageById(pack_id);
				if (flag) {
					if (flag1) {
						if (flag2) {
							returnData.put("message", "Package Deleted Successfully.");
							returnData.put("status", "1");
						}
					}
				} else {
					returnData.put("message", "Failed to Delete Package.");
					returnData.put("status", "0");
				}
			} else {
				returnData.put("message", "Failed to Delete.");
				returnData.put("status", "0");
			}

		} catch (Exception e) {
			logger_log.error("DeleteMyPackageDetail :::::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> getPackagesDetails(User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {

			param.put("user_id", userDetails.getUserId());
			List<PackageDetail> packagelist = packagedetailDao.getPackageDetailByNamedQuery("getPackageuserid", param);
			if (!packagelist.isEmpty()) {
				returnData.put("packagelist", packagelist);
				returnData.put("status", "1");
			} else {
				returnData.put("name", "No User found!");
				returnData.put("status", "1");
			}

		} catch (Exception e) {
			logger_log.error("getPackagesDetails :::::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "2");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> viewPackagewiseCommCharge(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		String sql = "";
		try {
			param.put("package_id", request.get("package_id"));
			String service_type = request.get("service_type");
			if (service_type.equalsIgnoreCase("Recharge")) {
				sql = CustomSqlQuery.getPackagewisecharecom;
			} else if (service_type.equalsIgnoreCase("Flight")) {
				sql = CustomSqlQuery.getPackagewisecharecomflight;
			} else if (service_type.equalsIgnoreCase("Bus")) {
				sql = CustomSqlQuery.getPackagewisecharecombus;
			} else if (service_type.equalsIgnoreCase("Hotel")) {
				sql = CustomSqlQuery.getPackagewisecharecomhotel;
			} else if (service_type.equalsIgnoreCase("Pan")) {
				sql = CustomSqlQuery.getPackagewisecharecom;
			} else if (service_type.equalsIgnoreCase("RBL AEPS")) {
				sql = CustomSqlQuery.getPackagewisecharecomaeps;
			}else if (service_type.equalsIgnoreCase("YesBank AEPS") || service_type.equalsIgnoreCase("MicroATM") || service_type.equalsIgnoreCase("Encore AEPS")  || service_type.equalsIgnoreCase("AadharPay")) {
				sql = CustomSqlQuery.getPackagewisecharecomaeps;
			}
			else if(service_type.equalsIgnoreCase("DMR")){
				sql = CustomSqlQuery.getPackagewisecharecomdmr;	
			}
			param.put("package_id", request.get("package_id"));
			List<PackageWiseChargeComm> list = customQueryServiceLogic.getPackagewisecharecom(sql, param, service_type);
			returnData.put("packdetails", list);
			System.out.println("PackageWiseChargeComm:::::::" + list.size());
		} catch (Exception e) {
			logger_log.error("getPackagesDetails :::::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "2");
		}

		return returnData;
	}
	
	@Override
	public Map<String, Object> getpanReport(Map<String, String> request, User userDetails) {
		Map<String, Object> returndata = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		Map<String, Object> param2 = new HashMap<String, Object>();
		List<PanReport> list = new ArrayList<>();
		List<User> userList = new ArrayList<>();
		try{
			if (userDetails.getRoleId() == 1) {
				param = new HashMap<String, String>();
				param.put("start_date", request.get("startDate"));
				param.put("end_date", request.get("endDate"));
				list=customQueryServiceLogic.getpanReport(CustomSqlQuery.getpanReportForAdmin, param);
				
			}else if (userDetails.getRoleId() == 2) {
				
				param = new HashMap<String, String>();
				param.put("start_date", request.get("startDate"));
				param.put("end_date", request.get("endDate"));
				param.put("username", userDetails.getUserName());
				list = customQueryServiceLogic.getpanReport(CustomSqlQuery.getpanReportForReseller, param);
				
		}else if (userDetails.getRoleId() == 3) {
			param = new HashMap<String, String>();
			param.put("start_date", request.get("startDate"));
			param.put("end_date", request.get("endDate"));
			param.put("username", userDetails.getUserName());
			list = customQueryServiceLogic.getpanReport(CustomSqlQuery.getpanReportForSD, param);
		
	}else if (userDetails.getRoleId() == 4) {
		param = new HashMap<String, String>();
		param.put("start_date", request.get("startDate"));
		param.put("end_date", request.get("endDate"));
		param.put("username", userDetails.getUserName());
		list = customQueryServiceLogic.getpanReport(CustomSqlQuery.getpanReportForSD, param);
	
}else{
				param = new HashMap<String, String>();
				param.put("start_date", request.get("startDate"));
				param.put("end_date", request.get("endDate"));
				param.put("username", userDetails.getUserName());
				list = customQueryServiceLogic.getpanReport(CustomSqlQuery.getpanReportForUser, param);
			}
			
			if(list==null){
				returndata.put("status", "0");
				returndata.put("message", "No Record Found");
			}else{
				returndata.put("status", "1");
				returndata.put("report", list);
			}
			
		}catch(Exception e){
			logger_log.error("getpanReport:::::::::::::::::::::" + e);
			returndata.put("message", e.getMessage());
		}
		return returndata;
	}
	
	@Override
	public Map<String, Object> getpanCouponReport(Map<String, String> request, User userDetails) {
		Map<String, Object> returndata = new HashMap<String, Object>();	
		Map<String, String> param = new HashMap<String, String>();
		List<PanCouponReport> list = new ArrayList<>();
		Map<String, Object> param2 = new HashMap<String, Object>();
		List<User> userList = new ArrayList<>();
		try{
			if (userDetails.getRoleId() == 1) {
				param = new HashMap<String, String>();
				param.put("start_date", request.get("startDate"));
				param.put("end_date", request.get("endDate"));
				list=customQueryServiceLogic.getpanCouponReport(CustomSqlQuery.getpanCouponReportForAdmin, param);
				
			}else if (userDetails.getRoleId() == 2) {
				param = new HashMap<String, String>();
				param.put("start_date", request.get("startDate"));
				param.put("end_date", request.get("endDate"));
				param.put("username", userDetails.getUserId());
				list = customQueryServiceLogic.getpanCouponReport(CustomSqlQuery.getpanCouponReportForReseller, param);
				
			}else if (userDetails.getRoleId() == 3) {

				param = new HashMap<String, String>();
				param.put("start_date", request.get("startDate"));
				param.put("end_date", request.get("endDate"));
				param.put("username", userDetails.getUserId());
				list = customQueryServiceLogic.getpanCouponReport(CustomSqlQuery.getpanCouponReportForSD, param);
				
			}else if (userDetails.getRoleId() == 4) {

				param = new HashMap<String, String>();
				param.put("start_date", request.get("startDate"));
				param.put("end_date", request.get("endDate"));
				param.put("username", userDetails.getUserId());
				list = customQueryServiceLogic.getpanCouponReport(CustomSqlQuery.getpanCouponReportForSD, param);
			}else{
				param = new HashMap<String, String>();
				param.put("start_date", request.get("startDate"));
				param.put("end_date", request.get("endDate"));
				param.put("username", userDetails.getUserName());
				list = customQueryServiceLogic.getpanCouponReport(CustomSqlQuery.getpanCouponReportForUser, param);
			}
			
			if(list==null){
				returndata.put("status", "0");
				returndata.put("message", "No Record Found");
			}else{
				returndata.put("status", "1");
				returndata.put("report", list);
			}
			
		}catch(Exception e){
			logger_log.error("getpanCouponReport:::::::::::::::::::::" + e);
			returndata.put("message", e.getMessage());
		}
		return returndata;
	}
	
	@Override
	public Map<String, Object> fetchaepsbankdt(User userDetail) {
		Map<String, Object> returndata = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();
		try{
		param.put("username", userDetail.getUserId());
		List<UserBankDetails> list=userbankdetailsdao.getUserBankDetailsByNamedQuery("getBankdetailsbyusername", param);
			if(list.isEmpty()){
				returndata.put("message", "No Details Found");
				returndata.put("status", "0");
			}else{
				returndata.put("list", list);
				returndata.put("status", "1");
			}
			
	}catch(Exception e){
		logger_log.error("fetchaepsbankdt ::::::::::: " + e);
		returndata.put("message", "Exception! Please try again");
		returndata.put("status", "0");
	}
		return returndata;	

	}

	@Override
	public Map<String, Object> applicationform(Map<String, String> request,MultipartFile panfile,MultipartFile photofile,MultipartFile aadharfile,MultipartFile voterfile , User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();

		
		String msg="";
		boolean flag = false;
		String yearofmarriage="";
		String  middlename="";
		String applicationid=GenerateRandomNumber.generatePtid(request.get("applicantmobileno"));
		System.out.println("request::::::::::::::::::::::"+request);
		try {
		//	String path = "C:"+ File.separator + "18-12-12";
		String path = "/usr" + File.separator + "local" + File.separator + "tomcat7" + File.separator + "webapps"+ File.separator + "imagesencore";
			String agentPanBytImgType = panfile.getContentType();
			String panImg[] = agentPanBytImgType.split("/");
			if(!panImg[1].equalsIgnoreCase("jpeg")){
				returnData.put("status", "0");
				returnData.put("message", "PLEASE, PAN WILL BE JPEG TYPE");
				return returnData;
				}
			String panimage = applicationid + "PAN" + "." + panImg[1];
			BufferedOutputStream stream1 = new BufferedOutputStream(
					new FileOutputStream(new File(path + File.separator + panimage)));
			stream1.write(panfile.getBytes());
			stream1.flush();
			stream1.close();

			logger_log.warn("agentPanBytImgType:::::::::::::::" + agentPanBytImgType);

			String agentPhotobyImgType = photofile.getContentType();
			String photoImg[] = agentPhotobyImgType.split("/");
			if(!photoImg[1].equalsIgnoreCase("jpeg")){
			returnData.put("status", "0");
			returnData.put("message", "PLEASE, PHOTO WILL BE JPEG TYPE");
			return returnData;
			}
			String photoimage = applicationid + "PHOTO" + "." + photoImg[1];
			logger_log.warn("agentPhotobyImgType:::::::::::::::" + agentPhotobyImgType);
			BufferedOutputStream stream2 = new BufferedOutputStream(
					new FileOutputStream(new File(path + File.separator + photoimage)));
			stream2.write(panfile.getBytes());
			stream2.flush();
			stream2.close();

			String agentAadharBytImgType = aadharfile.getContentType();
			System.out.println("agentAadharBytImgType:::::::::::::::::::" + agentAadharBytImgType);
			String aadharImg[] = agentAadharBytImgType.split("/");
			if(!aadharImg[1].equalsIgnoreCase("jpeg")){
				returnData.put("status", "0");
				returnData.put("message", "PLEASE, AADHAR WILL BE JPEG TYPE");
				return returnData;
				}
			String aadharimage = applicationid + "AADHAR" + "." + aadharImg[1];
			logger_log.warn("agentAadharBytImgType:::::::::::::::" + agentAadharBytImgType);
			
			
			BufferedOutputStream stream3 = new BufferedOutputStream(
					new FileOutputStream(new File(path + File.separator + aadharimage)));
			stream3.write(aadharfile.getBytes());
			stream3.flush();
			stream3.close();
			
			String agentVoterBytImgType = voterfile.getContentType();
			System.out.println("agentAadharBytImgType:::::::::::::::::::" + agentAadharBytImgType);
			String voterImg[] = agentVoterBytImgType.split("/");
			if(!voterImg[1].equalsIgnoreCase("jpeg")){
				returnData.put("status", "0");
				returnData.put("message", "PLEASE, Voter WILL BE JPEG TYPE");
				return returnData;
				}
			String voterimage = applicationid + "VOTER" + "." + aadharImg[1];
			logger_log.warn("agentVoterBytImgType:::::::::::::::" + agentVoterBytImgType);
			BufferedOutputStream stream4 = new BufferedOutputStream(
					new FileOutputStream(new File(path + File.separator + voterimage)));
			stream4.write(aadharfile.getBytes());
			stream4.flush();
			stream4.close();
			if(request.get("middlename")!=null) {
			middlename=request.get("middlename");
			}else {
			middlename="";	
			}
            if(request.get("yearofmarriage")!=null) {
            yearofmarriage=request.get("yearofmarriage");	
            }else {
            yearofmarriage="";	
            }
			Ezpayapplicationform applicant = new Ezpayapplicationform(request.get("firstname"),
					middlename, request.get("lastname"), request.get("address1"),
					request.get("address2"), request.get("city"), request.get("state"), request.get("pin"),
					request.get("mothersname"), request.get("mothersdateofbirth").substring(0,10),yearofmarriage,
					request.get("yearofpassingssc"), applicationid, request.get("applicantmobileno"),
					request.get("applicantemail"), userDetails.getUserName(), request.get("referencenumber"),
					request.get("cardreferencenumber"), request.get("cardnumber"), request.get("cardvalidtill"),
					request.get("officialname"), "PENDING", "", GenerateRandomNumber.getCurrentDate(),
					GenerateRandomNumber.getCurrentTime());
			flag = applicatiodao.insertEzpayapplicationform(applicant);
			if (flag) {
				msg = "Your application is successfully submitted";
				String sms="You have successfully Applied SBI Prepaid Card.Your Card Application No is:"+applicationid+" Thank you to Visit us.";
				param = new HashMap<String, Object>();
				param.put("wlId", userDetails.getWlId());
				SmsCredential credential = smsCredentialDao.getSmsCredentialByNamedQuery("GetSmsCredentialByWlId", param).get(0);
				SMS.sendSMS2(request.get("applicantmobileno"), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());
				returnData.put("status", "0");
				returnData.put("message", msg);

			} else {
				msg = "information submit failed";
				returnData.put("status", "0");
				returnData.put("message", msg);
			}

		}catch (Exception e) {
			logger_log.error(" Application:::::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
			return returnData;
	}

	@Override
	public Map<String, Object> statuschange(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		String status = "";
		String mobileNo = "";
        boolean flag=false;
		String pending = "";
		String msg="";
		try {
			param.put("applicationid", request.get("applicationid"));
			List<Ezpayapplicationform> statuschange=applicatiodao.getEzpayapplicationformByNamedQuery("getappformstatusupdatebyapplicationid",param);
			if(!statuschange.isEmpty()){
				Ezpayapplicationform applicationfrm=statuschange.get(0);
				applicationfrm.setStatus(request.get("status"));
				applicationfrm.setRemarks(request.get("remarks"));
				
			flag=applicatiodao.updateEzpayapplicationform(applicationfrm);
			if(flag){
				returnData.put("status","1");
				returnData.put("message", "Success");
				String sms="You have successfully Registered SBI Prepaid Card.Your Card No is:"+request.get("cardnumber")+" Thank you to sign up with Us";

				param = new HashMap<String, Object>();
				param.put("wlId", userDetails.getWlId());
				SmsCredential credential = smsCredentialDao.getSmsCredentialByNamedQuery("GetSmsCredentialByWlId", param).get(0);
				SMS.sendSMS2(request.get("applicantmobileno"), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());
			}
			
			}else{
				returnData.put("status","0");
				returnData.put("message", "FAILED");
				
			}
			System.out.println("request::::::::::::::"+request);
			
			
		}catch (Exception e) {
			logger_log.error(" StatusChange:::::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> statuschangeforcard(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag=false;
		try{
		System.out.println("request::::::::::::::"+request);
		
		flag = commissionService.updateBalance(request.get("userId"), "Add money to card", "commission for Card Approval",5,"CREDIT",0);
		if(flag) {
		CardWalletRequest  card=new CardWalletRequest(request.get("cardnumber"), request.get("cardholdernme"), request.get("userId"),Double.parseDouble(request.get("amount")) ,Double.parseDouble(request.get("charge")), request.get("status"), request.get("remarks"), request.get("date"), request.get("time"),request.get("applicationid"));
		card.setId(Integer.parseInt(request.get("id")));
		flag=CardWalletRequestDao.updateCardWalletRequest(card);
		if(flag){
			returnData.put("status","1");
			returnData.put("message", "Success");
		}else{
			returnData.put("status","0");
			returnData.put("message", "FAILED");
			
		}
		}
		
		}catch (Exception e) {
			logger_log.error("statuschangeforcard:::::::::::::: " + e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> checkBalance(Map<String, String> request, User userDetail) {
		Map<String, Object> returndata = new HashMap<String, Object>();	
		Map<String, String> param = new HashMap<String, String>();
		try{
		param.put("userId", userDetail.getUserId());	
		double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, param );	
		//double op_bal1 = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingPanBalanceForUser, param );	
		//double op_bal1 =0.0;
		returndata.put("wallet", op_bal);
		returndata.put("panwallet", 0);
		returndata.put("status", "1");
	}catch(Exception e){
		logger_log.error("checkBalance ::::::::::: " + e);
		returndata.put("message", "Exception! Please try again");
		returndata.put("status", "0");
	}
		return returndata;	

	}

	@Override
	public Map<String, Object> getUserByUserTypelatest(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String msg = "";		
		List<User> userList = null;
		String select = "";
		Map<String, Object> param = null;
		try {System.out.println(request);
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1) {					
					param = new HashMap<String, Object>();
					param.put("roleId",Integer.parseInt(request.get("userType")));
					/*if(Integer.parseInt(request.get("userType")) == 2) {
						userList = userDao.getUserByNamedQuery("GetUserByRoleId", param);
					}*/if(Integer.parseInt(request.get("userType")) == 10){
						param = new HashMap<String, Object>();
						userList = userDao.getUserByNamedQuery("GetAllUserByAdmin",param);
					}else {
						//param.put("wlId", userDetails.getWlId());
					//	userList = userDao.getUserByNamedQuery("GetUserByRoleIdAndWlId", param);
						userList = userDao.getUserByNamedQuery("GetUserByRoleId",param);
					}
				} else if (userDetails.getRoleId() == 2) {					
					param = new HashMap<String, Object>();					
					param.put("roleId",Integer.parseInt(request.get("userType")));
					param.put("wlId", userDetails.getWlId());
					userList = userDao.getUserByNamedQuery("GetUserByRoleIdAndWlId", param);
					
				} else if (userDetails.getRoleId() == 3) {					
					param = new HashMap<String, Object>();	
					if(Integer.parseInt(request.get("userType")) == 5) {
						userList = new ArrayList<User>();
						param.put("wlId", userDetails.getWlId());
						param.put("uplineId", userDetails.getUserId());
						List<User> disUser = userDao.getUserByNamedQuery("GetUserByUplineIdAndWlId", param);
						for(User dis : disUser) {
							param.put("roleId",Integer.parseInt(request.get("userType")));
							param.put("uplineId", dis.getUserId());
							List<User> retUser = userDao.getUserByNamedQuery("GetUserByRoleIdUplineIdAndWlId", param);
							if(!retUser.isEmpty()) {
								userList.addAll(retUser);
							}
						}
					}else {
						param.put("roleId",Integer.parseInt(request.get("userType")));
						param.put("wlId", userDetails.getWlId());
						param.put("uplineId", userDetails.getUserId());
						userList = userDao.getUserByNamedQuery("GetUserByRoleIdUplineIdAndWlId", param);
					}
					
				} else if (userDetails.getRoleId() == 4||userDetails.getRoleId() == 5) {
					param = new HashMap<String, Object>();	
					param.put("roleId",Integer.parseInt(request.get("userType")));
					param.put("wlId", userDetails.getWlId());
					param.put("uplineId", userDetails.getUserId());
					userList = userDao.getUserByNamedQuery("GetUserByRoleIdUplineIdAndWlId", param);
				} else {
					returnData.put("status","0");
					
				}
				
				if(!userList.isEmpty()) {
					returnData.put("status", "1");		
					returnData.put("userList", userList);	
				}else {
					returnData.put("status", "0");
					
				}
			}else {
				returnData.put("status", "0");
				msg = "Technical Error! Try After Sometime.";
			}
			
		} catch (Exception e) {
			returnData.put("status", "0");
			logger_log.error("getUserByUserType :::::::::::::: " + e);
			msg = "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-4 form-control-label'>"
					+ "<label for='email_address_2'>Select User </label>"
				+ "</div>"
				+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
				+ "<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8'>"
				+ "<label for='email_address_2' class='text-danger'>Technical Error! Try After Sometime. </label>"
				+ "</div>"
				+ "</div>";
			returnData.put("status", "0");

		}
		returnData.put("message", msg);
		return returnData;
	}

	@Override
	public Map<String, Object> microatmUser(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		String status = "";
		String mobileNo = "";
        boolean flag=false;
		String pending = "";
		String msg="";
		String date=GenerateRandomNumber.getCurrentDate();
		String time=GenerateRandomNumber.getCurrentTime();
		try {
			MicroatmUser micro=new MicroatmUser( request.get("microuser"),request.get("micropassword"),request.get("company"),request.get("mid"),request.get("tid"),request.get("userId"),"PENDING","",date,time);
			flag=MicroatmuserDao.insertMicroatmUser(micro);
			if (flag) {
				msg = "Your details is successfully submitted";
				
				returnData.put("status", "1");
				returnData.put("message", msg);

			} else {
				msg = "information submit failed";
				returnData.put("status", "0");
				returnData.put("message", msg);
			}	
		}catch (Exception e) {
			logger_log.error(" StatusChange:::::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
		
	}

	@Override
	public Map<String, Object> addmicroatmusernew(Map<String, String> request, User userDetails) {
	    Map<String, Object> returnData = new HashMap<String, Object>();
	    Map<String, Object> param = new HashMap<String, Object>();
			

			
			String msg="";
			boolean flag = false;
			try {
				param.put("username",request.get("userId"));
				List<MicroatmUserNew> list=MicroatmuserNewDao.getMicroatmUserNewByNamedQuery("getmicroatmnewUserbyUserid", param);
				if(list.isEmpty()) {
				MicroatmUserNew micro=new MicroatmUserNew(request.get("microuser"),request.get("micropassword"),"36",request.get("userId"));
				flag=MicroatmuserNewDao.insertMicroatmUserNew(micro);
				}else {
			//	MicroatmUser micro=list.get(0);
			//	micro.setMicropassword(micropassword);
			//	micro.set
				}
				if (flag) {
					msg = "Your application is successfully submitted";
					returnData.put("status", "1");
					returnData.put("message", msg);

				} else {
					msg = "information submit failed";
					returnData.put("status", "0");
					returnData.put("message", msg);
				}
			}catch (Exception e) {
				logger_log.error(" Application:::::::::::::: " + e);
				returnData.put("message", "Exception! Please try again");
				returnData.put("status", "0");
			}
				return returnData;
		}

	@Override
	public Map<String, Object> addgstdt(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
	    Map<String, Object> param = new HashMap<String, Object>();
	    boolean flag=false;
	    try{
	    	GstFlight GstFlight=GstFlightDao.getGstFlightByUserId(userDetails.getUserId());
	    	if(GstFlight==null){
	    		GstFlight gst=new GstFlight(userDetails.getUserId(), request.get("companyname"), request.get("companyaddress"), request.get("gstno"), request.get("panno"));
	    		flag=GstFlightDao.insertGstFlight(gst);
	    	}else{
	    		GstFlight.setCompanyNAme(request.get("companyname"));
	    		GstFlight.setCompanyAddress(request.get("companyaddress"));
	    		GstFlight.setGst(request.get("gstno"));
	    		GstFlight.setPan(request.get("panno"));
	    		flag=GstFlightDao.updateGstFlight(GstFlight);
	    	}
	    	if(flag){
	    		returnData.put("message", "Success");
				returnData.put("status", "1");
	    	}else{
	    		returnData.put("message", "Failed");
				returnData.put("status", "0");
	    	}
	    	
	    }catch (Exception e) {
			logger_log.error(" addgstdt:::::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
	}
	
}
