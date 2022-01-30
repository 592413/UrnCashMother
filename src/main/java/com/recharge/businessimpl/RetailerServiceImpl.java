package com.recharge.businessimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.ApiClient.VendorPayApi;
import com.recharge.businessdao.ApiParserService;
import com.recharge.businessdao.ApiResponseService;
import com.recharge.businessdao.CommissionService;
import com.recharge.businessdao.CustomQueryServiceLogic;
import com.recharge.businessdao.RetailerService;
import com.recharge.businessdao.SendRechargeRequest;
import com.recharge.customModel.BalanceTransfer;
import com.recharge.customModel.BringwayModel;
import com.recharge.customModel.DoopMeModel;
import com.recharge.customModel.InstantPayModel;
import com.recharge.customModel.SettlementApiRequest;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.easypayBbps.EasyPayBbpsDao;
import com.recharge.icicidmtservicce.RazorPayWebService;
import com.recharge.instantpay.InstantPayService;
import com.recharge.model.AEPSUserMap;
import com.recharge.model.AckSlipAttchment;
import com.recharge.model.Api;
import com.recharge.model.ApiParameters;
import com.recharge.model.Apisetting;
import com.recharge.model.AssignedPackage;
import com.recharge.model.Balancerequest;
import com.recharge.model.Balancetransafer;
import com.recharge.model.Bankdetails;
import com.recharge.model.CardWalletRequest;

import com.recharge.model.Chargeset;
import com.recharge.model.Complain;
import com.recharge.model.CouponRequest;
import com.recharge.model.CreateAgent;
import com.recharge.model.EarningSurcharge;
import com.recharge.model.Ezpayapplicationform;
import com.recharge.model.Individualcharge;
import com.recharge.model.Insurance;
import com.recharge.model.NSDLPan;
import com.recharge.model.NSDLPanAttachment;
import com.recharge.model.Operator;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.PaytmRequest;
import com.recharge.model.Rechargedetails;
import com.recharge.model.SMSApiparameters;
import com.recharge.model.SettlementBalance;
import com.recharge.model.SettlementCharge;
import com.recharge.model.SettlementReport;
import com.recharge.model.SmsCredential;
import com.recharge.model.Transactiondetails;
import com.recharge.model.User;
import com.recharge.model.UserBankDetails;
import com.recharge.model.Utility;
import com.recharge.servicedao.AEPSUserMapDao;
import com.recharge.servicedao.AckSlipAttachmentDao;
import com.recharge.servicedao.ApiDao;
import com.recharge.servicedao.ApiParametersDao;
import com.recharge.servicedao.ApisettingDao;
import com.recharge.servicedao.ApplicationDao;
import com.recharge.servicedao.AssignedPackageDAO;
import com.recharge.servicedao.BalancerequestDao;
import com.recharge.servicedao.BalancetransaferDao;
import com.recharge.servicedao.BankdetailsDao;
import com.recharge.servicedao.ComplainDao;
import com.recharge.servicedao.CouponRequestDao;
import com.recharge.servicedao.CreateAgentDao;
import com.recharge.servicedao.EarningSurchargeDao;
import com.recharge.servicedao.InsuranceDao;
import com.recharge.servicedao.NSDLPanAttachmentDao;
import com.recharge.servicedao.NSDLpanDao;
import com.recharge.servicedao.OperatorDao;
import com.recharge.servicedao.PaytmRequestDao;
import com.recharge.servicedao.CardWalletRequestDao;

import com.recharge.servicedao.RechargedetailsDao;
import com.recharge.servicedao.SMSApiparametersDao;
import com.recharge.servicedao.SettlementBalanceDao;
import com.recharge.servicedao.SettlementChargeDao;
import com.recharge.servicedao.SettlementReportDao;
import com.recharge.servicedao.SmsCredentialDao;
import com.recharge.servicedao.TransactiondetailsDao;
import com.recharge.servicedao.UserBankDetailsDao;
import com.recharge.servicedao.UserDao;
import com.recharge.servicedao.UtilityDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.HashGenerationAgreepay;
import com.recharge.utill.RBLService;
import com.recharge.utill.RoundNumber;
import com.recharge.utill.SMS;
import com.recharge.utill.SMS2;
import com.recharge.utill.URLTest;
import com.recharge.utill.UtilityClass;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Service("retailerService")
public class RetailerServiceImpl implements RetailerService {
	private static final Logger logger_log = Logger.getLogger(RetailerService.class);
	@Autowired CustomQueryServiceLogic customQueryServiceLogic;
	@Autowired OperatorDao operatorDao;
	@Autowired CommissionService commissionService;
	@Autowired RechargedetailsDao rechargedetailsDao;
	@Autowired TransactiondetailsDao transactiondetailsDao;
	@Autowired UserDao userDao;
	@Autowired SendRechargeRequest sendRechargeRequest;
	@Autowired ApiParserService apiParserService;
	@Autowired ApisettingDao apisettingDao;
	@Autowired ComplainDao complainDao;
	@Autowired BankdetailsDao bankdetailsDao;
	@Autowired BalancerequestDao balancerequestDao;
	@Autowired BalancetransaferDao balancetransferDao;
	@Autowired UtilityDao utilityDao;
	@Autowired SmsCredentialDao smsCredentialDao;
	@Autowired InsuranceDao insuranceDao;
	@Autowired EarningSurchargeDao earningSurchargeDao;
	@Autowired CreateAgentDao createagent;
	@Autowired CouponRequestDao cponrequest;
	@Autowired ApiDao apiDao;
	@Autowired ApiParametersDao  apiParametersdao;
	@Autowired ApiResponseService ApiResponseService;
	@Autowired SMSApiparametersDao smsapiparamsdao;
	@Autowired NSDLpanDao NSDLpanDao;
	@Autowired AssignedPackageDAO assignedPackage;
	@Autowired NSDLPanAttachmentDao NSDLPanAttachmentDao;
	@Autowired AckSlipAttachmentDao AckSlipAttachmentDao;
	@Autowired SettlementBalanceDao settlementbalancedao;
	@Autowired SettlementReportDao settlementreportdao;
	@Autowired AEPSUserMapDao aepsuserdao;
	@Autowired UserBankDetailsDao userbankdetailsdao;
    @Autowired SettlementChargeDao  settlementchargrdao;
    @Autowired EasyPayBbpsDao EasyPayBbpsDao;
    @Autowired InstantPayService InstantPayService;
    @Autowired CardWalletRequestDao CardWalletRequestDao;
    @Autowired ApplicationDao applicatiodao;
    @Autowired HttpSession session;
    @Autowired PaytmRequestDao paytmrequestdao;
	private static String token="ffce176dcf5a958d88413b0c7f86cc0d";
	
	
	
	private static String UMobileNo="xxxx";
	private static String Password="5XWA38";
	private static String aepsapiuser="xxxx";
	private static String aepsurl="https://www.apivendor.com";
	private static String api_key="7rnFly";
	private static String salt="pjVQAWpA";
	
	private static String tokennumberForApiVendor="120520210551450";
	
	/* (non-Javadoc)
	 * @see com.recharge.businessdao.RetailerService#webRecharge(java.util.Map, com.recharge.model.User)
	 */
	@Override
	public Map<String, Object> webRecharge(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {			
			Operator operator = new Operator();
			PackageWiseChargeComm pckret=new PackageWiseChargeComm();
			PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
			PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
			PackageWiseChargeComm pckres=new PackageWiseChargeComm();
			PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
			String status = "";			
			double comm = 0.0;
			double dComm = 0.0;
			double sdComm = 0.0;
			double resComm = 0.0;
			double retComm = 0.0;
			String operatorid="";
			double sdCharge = 0.0;
			double distCharge = 0.0;			
			double resCharge = 0.0;
			
			
			double dcharge = 0.0;
			double sCharge = 0.0;
			double reCharge = 0.0;
			
			double charge = 0.0;
			double totalAmount = 0.0;
			String resellerId = "";
			String sdId = "";
			String distId = "";	
			String rId="";
			String response = "";
			Chargeset dCharge = null;
			String service_type = "";
			int amnt = Integer.parseInt(request.get("amount"));
			ApiParameters apiparams = null;
			
			String Rechargeurl = "";
			if (UtilityClass.checkParameterIsNull(request)) {
				if(userDetails.getStatus().equals("1")){
					returnData.put("status", "0");
					returnData.put("message", "YOur ID is Inactive");
				}else{
				String mobileNo = request.get("mobile");
				double amount = Double.parseDouble(request.get("amount"));
				String source = request.get("source");	
				
				parameters = new HashMap<String, String>();
				
				parameters.put("mobile", mobileNo);
				parameters.put("amount", String.valueOf(amount));
				parameters.put("userId", userDetails.getUserId());
				parameters.put("date", GenerateRandomNumber.getCurrentDate());
				long timeDiff = customQueryServiceLogic.getRechargeLock(CustomSqlQuery.getRechargeLock, parameters);
				/*if(balance<amount)
				{
					returnData.put("status", "0");
					returnData.put("message", "Amount cannot greater than Balance!");
				}else{*/
				
				if(timeDiff > 10){
					userDetails = userDao.getUserByUserId(userDetails.getUserId());
					param = new HashMap<String, Object>();
					
					param.put("inCode", request.get("operator"));
					List<Operator> opList =  operatorDao.getOperatorByNamedQuery("getOperatorByInCode", param );				
					param = new HashMap<String, Object>();	
					
					if((!opList.isEmpty())) {
						operator = opList.get(0);	
						if(operator.getStatus().equals("available"))
						{
						   int apiid = operator.getApiId();
						   param.put("apiId",apiid);
							Api lapi = apiDao.getApiByNamedQuery("getApibyapiId", param).get(0);
							
						// ------------------------------  User's Charge calculation for the Recharge -------------------------------------//
							
							
								if (userDetails.getRoleId() == 5) {
									pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId()) ;
									if(pckret.getPackage_id()!=null){
									if (pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
										charge = (pckret.getCharge() * amount) / 100;
									} else {
										charge = pckret.getCharge();
									}
									}
								} else if (userDetails.getRoleId() == 4) {
									pckdis = commissionService.getPackagewiseCommisionOnOperator(
											userDetails.getUserId(), "Recharge", operator.getOperatorId());
									if(pckdis.getPackage_id()!=null){
									if (pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
										charge = (pckdis.getCharge() * amount) / 100;
									} else {
										charge = pckdis.getCharge();
									}
									}

								} else if (userDetails.getRoleId() == 3) {
									pcksd = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),
											"Recharge", operator.getOperatorId());
									if(pcksd.getPackage_id()!=null){
									if (pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
										charge = (pcksd.getCharge() * amount) / 100;
									} else {
										charge = pcksd.getCharge();
									}
									} 
								}else if (userDetails.getRoleId() == 100) {
									pckapiu = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),
											"Recharge", operator.getOperatorId());
									if(pckapiu.getPackage_id()!=null){
									if (pckapiu.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
										charge = (pckapiu.getCharge() * amount) / 100;
										comm = (pckapiu.getComm() * amount) / 100;
									} else {
										charge = pckapiu.getCharge();
										comm = pckapiu.getComm();
									}
									}
								}
						/*-------------------------------------------------------------------------------------------------------------------*/
						String tId = "";
						/*-------------------------------------COMMISSION---------------------------------------------------------------------*/
						if(userDetails.getRoleId() == 5) {
							//Retailer Id
							//rId=userDetails.getUserId();
							// Distributor Id
							distId = userDetails.getUplineId();		
							
							// Super Distributor Id
							parameters = new HashMap<String, String>();
							parameters.put("userId", distId);	
							sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
							
							// Reseller Id
							parameters = new HashMap<String, String>();
							parameters.put("userId", sdId);							
							resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
							if(pckret.getPackage_id()!=null){
							if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							retComm =(pckret.getComm()*amount)/100;
							}else{
							retComm =pckret.getComm();	
							}
							comm = retComm;
							}
						//	System.out.println("reseller=="+comm);
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
							if(pckdis.getPackage_id()!=null){
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*amount)/100;
							}else{
							dComm=pckdis.getComm();	
							}
							}
						//	System.out.println("dComm=="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
							if(pcksd.getPackage_id()!=null){
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*amount)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							}
						//	System.out.println("sdComm=="+sdComm);
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
							if(pckres.getPackage_id()!=null){
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*amount)/100;
							}else{
							resComm =pckres.getComm();	
							}
							}
						//	System.out.println("resComm=="+resComm);
							
						} else if(userDetails.getRoleId() == 4) {
							//distId = userDetails.getUserId();	
							sdId = userDetails.getUplineId();	
							
							parameters = new HashMap<String, String>();
							parameters.put("userId", sdId);							
							resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
							
							pckdis=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId()); 
							if(pckdis.getPackage_id()!=null){
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*amount)/100;
							}else{
							dComm=pckdis.getComm();	
							}
							comm = dComm;
							}
						//	System.out.println("dComm="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
							if(pcksd.getPackage_id()!=null){
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*amount)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							sdComm=sdComm-dComm;
							}
							//System.out.println("sdComm="+sdComm);
							if(!resellerId.equals("admin")) {
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
							if(pckres.getPackage_id()!=null){
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*amount)/100;
							}else{
							resComm =pckres.getComm();	
							}
							resComm=resComm-sdComm;
							}
							}
							//System.out.println("resComm="+resComm);
							
						} else if(userDetails.getRoleId() == 3) {
							resellerId = userDetails.getUplineId();
							pcksd=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId());
							if(pcksd.getPackage_id()!=null){
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						     sdComm =(pcksd.getComm()*amount)/100;
							}else{
								sdComm = pcksd.getComm();	
							}
							}
							
							System.out.println("sdComm:::::::::"+sdComm);
							if(!resellerId.equals("admin")) {
								pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
								if(pckres.getPackage_id()!=null){
								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								resComm =(pckres.getComm()*amount)/100;
								}else{
								resComm =pckres.getComm();	
								}
								resComm=resComm-sdComm;
								}
								}
				
						}
						/*---------------------------------------------------------------------------------------------------------------*/
						if (amount > 0) {						
							if(source.equals("API")){tId=request.get("request_id");}
							else{
							tId = GenerateRandomNumber.generatePtid(request.get("mobile"));
							}
							if (!tId.equals("")) {
								String today = GenerateRandomNumber.getCurrentDate();	
								String currentTime = GenerateRandomNumber.getCurrentTime();
							//	comm = RoundNumber.roundDouble((comm * amount)/100);
								totalAmount = RoundNumber.roundDouble((amount + charge) - comm);
								parameters = new HashMap<String, String>();
								double adOpBal = customQueryServiceLogic.getTotalbalanceForAdmin(CustomSqlQuery.getTotalbalanceForAdmin, parameters);
								RoundNumber.roundDouble(adOpBal - totalAmount);
								parameters = new HashMap<String, String>();
								parameters.put("userId", userDetails.getUserId());	
								double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters );	
								
								System.out.println("op_bal"+op_bal);
								double cl_bal = RoundNumber.roundDouble(op_bal - totalAmount);
								System.out.println("totalAmount"+totalAmount);
								System.out.println("op_bal"+cl_bal);
								if (cl_bal > userDetails.getLockedAmount()) {
									Rechargedetails rechargedetails = new Rechargedetails(userDetails.getUserId(), mobileNo, operator.getOperatorId(), op_bal, amount, charge,comm, cl_bal, tId, tId, tId, "PENDING", "PENDING",source , operator.getApiId(), userDetails.getRoleId(), userDetails.getWlId(), today, currentTime,request.get("ip"));
									boolean flag = rechargedetailsDao.insertRechargeDetails(rechargedetails);								
									if (flag) {
										
										flag = commissionService.updateBalance(userDetails.getUserId(), "Recharge to "+mobileNo, "Recharge", totalAmount, "DEBIT",0);
										if (flag) {	
											EarningSurcharge earningSurcharge = new EarningSurcharge(userDetails.getRoleId(), userDetails.getWlId(), userDetails.getUserId(), comm, charge, "RECHARGE FOR - "+mobileNo, today, currentTime);
											earningSurchargeDao.insertEarningSurcharge(earningSurcharge );
											if(apiid==1){
												 amnt = Integer.parseInt(request.get("amount"));
												String message=operator.getOutCode()+"$"+mobileNo+"$"+amnt+"$1730$0$"+tId;
												 Rechargeurl="https://cellmoney.org/Admin/RechargeAPI.aspx?UserID="+lapi.getUsername()+"&Password="+lapi.getPassword()+"&MobileNo="+lapi.getUsername()+"&Message="+message;
												logger_log.warn("cellmoney Rechargeurl :: "+Rechargeurl);
												response = sendRechargeRequest.sendRechagreReq(Rechargeurl);
												logger_log.warn("cellmoney RESPONSE :: "+response);
												if(response.contains("0=Successfully Inserted")){
													status = "SUCCESS";
												}else{
													status = "FAILED";	
												}
												
											}else if(apiid==2){
												status = "SUCCESS";
											}else if(apiid==3){
												 amnt = Integer.parseInt(request.get("amount"));
												 Rechargeurl="https://www.instantpay.in/ws/api/transaction?format=xml&token="+lapi.getUsername()+"&agentid="+tId+"&amount="+amnt+"&spkey="+operator.getOutCode()+"&account="+mobileNo+"&mode=VALIDATE";
												logger_log.warn("instantpay Rechargeurl :: "+Rechargeurl);
												response = sendRechargeRequest.sendRechagreReq(Rechargeurl);
												logger_log.warn("instantpay RESPONSE :: "+response);
												InstantPayModel insp = apiParserService.instantpayParserFirst(response);
												if(insp.getIpay_errorcode().equalsIgnoreCase("TXN")){
													Rechargeurl="https://www.instantpay.in/ws/api/transaction?format=xml&token="+lapi.getUsername()+"&spkey="+operator.getOutCode()+"&agentid="+tId+"&amount="+amnt+"&account="+mobileNo+"";	
													logger_log.warn("instantpay Rechargeurl :: "+Rechargeurl);
													response = sendRechargeRequest.sendRechagreReq(Rechargeurl);
													logger_log.warn("instantpay RESPONSE :: "+response);
													insp = apiParserService.instantpayParserSecond(response);
													if(insp.getIpay_errordesc()!=null){	
														rechargedetails.setTid(insp.getIpay_id());
														rechargedetails.setOid(insp.getIpay_id());
														status = "SUCCESS";	
														
													}else{
														status = "FAILED";	
													}
												}else{
													status = "FAILED";	
												}
												
											}else if(apiid==4){
												 String stv = "";
                                                 if (operator.getServiceProvider().contains("SPACIAL")) {
                                                     stv = "1";
                                                 } else if (operator.getServiceProvider()
                                                     .contains("SPECIAL")) {
                                                     stv = "1";
                                                 } else {
                                                     stv = "0";
                                                 }
                                                 if(operator.getServiceType().equals("1")||operator.getServiceType().equals("2")){
                                                 	Rechargeurl = "https://www.doopme.com/RechargeAPI/RechargeAPI.aspx?MobileNo=" + lapi.getUsername() + "&APIKey=" + lapi.getPassword() + "&REQTYPE=RECH&RESPTYPE=JSON&REFNO=" + tId + "&SERCODE=" + operator.getOutCode() + "&CUSTNO=" + mobileNo + "&AMT=" + amnt + "&STV=" + stv;
                                                 }else{
                                                 	Rechargeurl = "https://www.doopme.com/RechargeAPI/RechargeAPI.aspx?MobileNo=" + lapi.getUsername() + "&APIKey=" + lapi.getPassword() + "&REQTYPE=RECH&RESPTYPE=JSON&REFNO=" + tId + "&SERCODE=" + operator.getOutCode() + "&CUSTNO=" + request.get("accountno") + "&REFMOBILENO="+mobileNo+"&AMT=" + amnt + "&STV=" + stv;
                                                 }
                                                  
                                                 logger_log.warn("doopme Rechargeurl :: " + Rechargeurl);
                                                response = sendRechargeRequest.sendRechagreReq(Rechargeurl);
                                                     logger_log.warn("doopme RESPONSE :: " + response);
                                                     JSONObject obj = new JSONObject(response);
                                                     if (obj.get("STATUSMSG").equals("Success")) {
                                                         status = "SUCCESS";
                                                        // rechargeStatus="SUCCESS";
                                                         if (!obj.get("OPRID").equals("")) {
                                                             String opd = (String) obj.get("OPRID");
                                                             Map < String, String > pm = new HashMap < > ();
                                                             pm.put("Status", "1");
                                                             pm.put("OprID", opd);
                                                             pm.put("ClientRefNo", tId);
                                                             ApiResponseService.doopMeApiResponsenew(pm);
                                                             operatorid = opd;
                                                         }
                                                     } else {
                                                         status = "FAILED";
                                                         response = "FAILED";
                                                       //  rechargeStatus="FAILED";
                                                     }
											}
												
												
												if(response.contains("FAILED")||response.contains("FAILURE")){
													status="FAILED";	
												}else{
													status="SUCCESS";
												}
	
											if (status.equals("SUCCESS")) {
												
												/*----------------------CHARGE & COMMISSION FOR THE RECHARGE-------------------------------*/
											
												if(userDetails.getRoleId() == 5) {
													if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
														distCharge = (pckdis.getCharge()*amount)/100;
													}else{
														distCharge = pckdis.getCharge();
													}
													if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
														sdCharge = (pcksd.getCharge()*amount)/100;
													}else{
														sdCharge = pcksd.getCharge();
													}
														if(!resellerId.equals("admin")) {
															if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
															resCharge = (pckres.getCharge()*amount)/100;
															}else{
															resCharge =	pckres.getCharge();
															}
															
														}
														if(distCharge==0){dcharge=0;}
														else{dcharge = charge - distCharge;}
														
														if(sdCharge==0){sCharge=0;}
														else{sCharge = distCharge - sdCharge;}
														
														
														commissionService.updateBalance(distId, "Charge for Recharge - "+mobileNo, "CHARGE", dcharge, "CREDIT",0);
														commissionService.updateBalance(sdId, "Charge for Recharge - "+mobileNo, "CHARGE", sCharge, "CREDIT",0);
														if(!resellerId.equals("admin")) {
															if(resCharge==0){reCharge=0;}
															else{
															reCharge = sdCharge - resCharge;
															}
															commissionService.updateBalance(resellerId, "Charge for Recharge - "+mobileNo, "Recharge Charge", reCharge, "CREDIT",0);
														}
														
														/*-------------------------------For earning and Surcharge ------------------*/
														// for DISTRIBUTOR 
														EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, userDetails.getWlId(), distId, 0.0, dcharge, "Charge FOR - "+mobileNo, today, currentTime);
														earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
														
														// For Super Distributor
														EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, 0.0, sCharge, "Charge FOR - "+mobileNo, today, currentTime);
														earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
														
														// For RESELLER
														if(!resellerId.equals("admin")) {
															EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, 0.0, reCharge, "Charge FOR - "+mobileNo, today, currentTime);
															earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
														}
														/*----------------------------------------------------------------------*/
														
																					
													/*//commissionService.updateBalance(distId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", dComm, "CREDIT",0);
													//commissionService.updateBalance(sdId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", sdComm, "CREDIT",0);
													if(!resellerId.equals("admin")) {
														//commissionService.updateBalance(resellerId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", resComm, "CREDIT",0);
													}*/
												} else if(userDetails.getRoleId() == 4) {
													
													if(!resellerId.equals("admin")) {
														if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
														resCharge = (pckres.getCharge()*amount)/100;
														}else{
														resCharge =	pckres.getCharge();
														}
														
													}
													if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
														sdCharge = (pcksd.getCharge()*amount)/100;
													}else{
														sdCharge = pcksd.getCharge();
													}
													if(sdCharge==0){sCharge=0;}
													else{sCharge = charge - sdCharge;}
																										
													commissionService.updateBalance(sdId, "Charge for Recharge - "+mobileNo, "CHARGE", sCharge, "CREDIT",0);
													if(!resellerId.equals("admin")) {
														if(resCharge==0){reCharge=0;}
													else{
													reCharge = sdCharge - resCharge;
													}
														commissionService.updateBalance(resellerId, "Charge for Recharge - "+mobileNo, "Recharge Charge", reCharge, "CREDIT",0);
													}														
													/*-------------------------------For earning and Surcharge ------------------*/
																											
													// For Super Distributor
													EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, 0.0, sCharge, "Charge FOR - "+mobileNo, today, currentTime);
													earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
													
													// For RESELLER
													if(!resellerId.equals("admin")) {
														EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, 0.0, reCharge, "Charge FOR - "+mobileNo, today, currentTime);
														earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
													}
													/*----------------------------------------------------------------------*/
												
													} else if(userDetails.getRoleId() == 3) {

														if (!resellerId.equals("admin")) {
															if (pckres.getCharge_type()
																	.equalsIgnoreCase("PERCENTAGE"))
																resCharge = (pckres.getCharge() * amount) / 100;
														} else {
															resCharge = pckres.getCharge();
														}
														resCharge = charge - resCharge;

														if (!resellerId.equals("admin")) {
															
															reCharge = sdCharge - charge;
															commissionService.updateBalance(resellerId,
																	"Charge for Recharge - " + mobileNo,
																	"Recharge Charge", reCharge, "CREDIT",0);
														}

														/*-------------------------------For earning and Surcharge ------------------*/

														// For RESELLER
														if (!resellerId.equals("admin")) {
															EarningSurcharge earningSurcharge3 = new EarningSurcharge(
																	4, userDetails.getWlId(), resellerId, 0.0,
																	reCharge, "Charge FOR - " + mobileNo, today,
																	currentTime);
															earningSurchargeDao
																	.insertEarningSurcharge(earningSurcharge3);
														}
														/*----------------------------------------------------------------------*/

													}
												rechargedetails.setStatus("SUCCESS");
											//	rechargedetails.setValidation("PENDING");
												rechargedetailsDao.updateRechargeDetails(rechargedetails);											
												returnData.put("status", "1");
												returnData.put("message", "Recharge Submit SUCCESSFUL. Transaction Id: "+tId);
												if(source.equals("APPS")){
													returnData.put("closingBalance", cl_bal);
												}
												if(source.equals("API")){
													returnData.put("TransactionId", tId);
												}
											} else if (status.equals("FAILED")) {
												rechargedetails.setStatus("FAILED");
												rechargedetails.setValidation("FAILED");
												rechargedetailsDao.updateRechargeDetails(rechargedetails);										
												commissionService.updateBalance(userDetails.getUserId(), "REFUND for Recharge - "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
												
												returnData.put("status", "0");
												returnData.put("message", "Recharge FAILED. Transaction Id: "+tId);
												if(source.equals("API")){
													returnData.put("TransactionId", tId);
												}
												if(source.equals("APPS")){
													returnData.put("closingBalance", cl_bal);
												}
											}
	
										} else {
											returnData.put("status", "0");
											if(source.equals("APPS")){
												returnData.put("closingBalance", cl_bal);
											}
											returnData.put("message", "Recharge Failed. Technical Error!");
										}
									} else {
										returnData.put("status", "0");
										if(source.equals("APPS")){
											returnData.put("closingBalance", cl_bal);
										}
										returnData.put("message", "Recharge Failed. Technical Error!");
									}
								} else {
									returnData.put("status", "0");
									if(source.equals("APPS")){
										returnData.put("closingBalance", cl_bal);
									}
									returnData.put("message", "Recharge Failed. Insufficient Balance!");
								}
							}else {
								returnData.put("status", "0");
								returnData.put("message", "Recharge Failed. Invalid Mobile Numbers!");
							}
						}else {
							returnData.put("status", "0");
							returnData.put("message", "Recharge Failed. Invalid Amount!");
						}
					}else {
						returnData.put("status", "0");
						returnData.put("message", "Recharge Failed. Operator is not available");
					}
					}else {
						returnData.put("status", "0");
						returnData.put("message", "Recharge Failed. Please try after Some time");
					}
					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "Recharge Failed. Same amount same number after 10 minutes.");
				}
				}
				//}
			} else {
				returnData.put("status", "0");
				returnData.put("message", "Please try after 10 minutes");
			}
			
		} catch (Exception e) {
			logger_log.error("webRecharge ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	
	
	@Override
	public Map<String,Object> billFetch(Map<String, String> request, User userDetails){
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		Operator operator = new Operator();
		param.put("inCode", request.get("operator"));
		List<Operator> opList =  operatorDao.getOperatorByNamedQuery("getOperatorByInCode", param );
		if((!opList.isEmpty())) {
			operator = opList.get(0);	
		}
		Api lapi=apiDao.getApiByApId(operator.getApiId());
	/*	param = new HashMap<String, Object>();	
		param.put("opcode", opList.get(0).getOperatorCode());
		param.put("apiid", opList.get(0).getApiId());
		List<OperatorOutcode> opcdlist=OperatorOutcodeDao.getOperatorOutcodeByNamedQuery("getOperatorOutcodeByid", param);
		if(!opcdlist.isEmpty()){*/
			//String ocd=opcdlist.get(0).getOutCode();
					if(operator.getApiId()==7){
			            String fetchurl =  "https://www.doopme.com/RechargeAPI/RechargeAPI.aspx?MobileNo="+lapi.getUsername()+"&APIKey="+lapi.getPassword()+"&REQTYPE=BILLINFO&SERCODE="+operator.getOutCode()+"&CUSTNO="+request.get("consumerNumber")+"&REFMOBILENO="+request.get("consumerMobile")+"&AMT=0&STV=0&PCODE=380054&LAT=22.6343188&LONG=88.4346478&RESPTYPE=JSON";
						logger_log.warn("doopme Utility BIll Check :: "+fetchurl);
						String response = sendRechargeRequest.sendRechagreReq(fetchurl);
						logger_log.warn("doopme Utility BIll Check RESPONSE :: "+response);
						//response = "{"STATUSCODE":"0","STATUSMSG":"Success","REFNO":"123","TRNID":11111,"TRNSTATUS":0,"TRNSTATUSDESC":"Request Accepted","OPRID":""}";
						
					
				
				JSONObject obj=new JSONObject(response);
				if(obj.get("STATUSMSG").equals("Success")){
					if(!operator.getServiceType().equals("4")){
						returnData.put("DueDate", obj.get("BILLDUEDATE"));
					}
					returnData.put("status", "1");
					returnData.put("Dueamount", obj.get("BILLAMT"));
					returnData.put("BILLNO", obj.get("BILLNO"));
					returnData.put("CUSTNAME", obj.get("CUSTNAME"));
					returnData.put("BILLDATE", obj.get("BILLDATE"));
					returnData.put("PARTIALPAYALLOW", obj.get("PARTIALPAYALLOW"));
					returnData.put("consumerNumber", request.get("consumerNumber"));
					returnData.put("consumerMobile", request.get("consumerMobile"));
					returnData.put("operator", request.get("operator"));
					returnData.put("reference_id", "123");
					returnData.put("message", "Customer Number "+obj.get("CUSTNAME")+"Due Amount="+obj.get("BILLAMT")+",Your Due Date="+obj.get("BILLDUEDATE"));

				}else{
					returnData.put("consumerNumber", request.get("consumerNumber"));
					returnData.put("consumerMobile", request.get("consumerMobile"));
					returnData.put("status", "0");
					returnData.put("message", obj.get("STATUSMSG"));
				}
			       }else if(operator.getApiId()==2){
		    	   returnData.put("status", "2");
		    	   returnData.put("consumerNumber", request.get("consumerNumber"));
		   		returnData.put("consumerMobile", request.get("consumerMobile"));
		   		returnData.put("operator", request.get("operator"));
		       }else if(operator.getApiId()==3){
		    	   returnData=  InstantPayService.eBillBBPsPaymentFirst(request, userDetails);
		       }									
	//}
				
		return returnData;	
	}
	
	@Override
	public Map<String, Object> checkApiUser(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if (request.get("userId") == null || request.get("password") == null || request.get("tranPin") == null
						|| request.get("mobile") == null || request.get("amount") == null
						|| request.get("opCode") == null || request.get("userId").equals("")
						|| request.get("password").equals("") || request.get("tranPin").equals("")
						|| request.get("mobile").equals("")|| request.get("amount").equals("")
						|| request.get("opCode").equals("")) {
					
					returnData.put("status", "0");
					returnData.put("message", "Invalid Input");	
				} else {
					String userId = request.get("userId");
					User user = userDao.getUserByUserId(userId);
					if (user.getUserId().equals(userId) && user.getPassword().equals(request.get("password"))
							&& user.getTranPin().equals(request.get("tranPin")) && user.getRoleId() == 100) {
						returnData.put("status", "1");
						returnData.put("userDetails", user);
						returnData.put("message", "OK");
					} else {
						returnData.put("status", "0");
						returnData.put("message", "Invalid Credentials");
					}
				}
			} else {
				returnData.put("status", "0");
				returnData.put("message", "Recharge Failed. Please try after Some time");			
			}
		} catch (Exception e) {
			logger_log.error("checkApiUser ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	
	@Override
	public Map<String, Object> settlerequest(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();	
		List<UserBankDetails> banklist= new ArrayList<>();
		SettlementReport settle2 = null;
		double settlementamount = 0.0;
		double settleopbal = 0.0;
		double settlementamountnew = 0.0;
		double settlementcharge = 0.0;
	//	String response = URLTest.checkServer();
		/*
		 * param.put("username",userDetails.getUserName()); param.put("api","YesBank");
		 * List<AEPSUserMap> aepslist =
		 * aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
		 * AEPSUserMap aeps = aepslist.get(0);
		 */
		boolean flag = false;
		String type="";
		SettlementApiRequest settleapi = null;
		try{
			/*if(response.equalsIgnoreCase("ok")){*/
				settlementamount = Double.parseDouble(request.get("amount").toString());
				param = new HashMap<String, Object>();
				param.put("api","YesBank");
			/*	if(request.get("type").toString().equalsIgnoreCase("Settle to Bank")){*/
				List<SettlementCharge> settlecharge = settlementchargrdao.getSettlementChargeByNamedQuery("getSettlementChargebyapi",param);
				for(SettlementCharge settle:settlecharge){
					if(settle.getSlab1()<=settlementamount && settle.getSlab2()>=settlementamount){
						settlementcharge = settle.getCharge();	
					}
					
				}
				/*}*/
				
				Map<String, String>	parameters = new HashMap<String, String>();
				parameters.put("userId", userDetails.getUserId());
				 settleopbal = customQueryServiceLogic
						.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForsettlement,parameters);
				double settleclbal=settleopbal-((Double.parseDouble(request.get("amount"))));
					if(settleclbal>0){
			if(request.get("type").toString().equalsIgnoreCase("Settle to Wallet")){
				flag = commissionService.updateBalance(userDetails.getUserName(), "SETTLEMENT AMOUNT TO WALLET", "SETTLEMENT", settlementamount, "CREDIT",0);
				type="Settle to Wallet";
				logger_log.warn("Settle to Wallet::::::::::::::::::::::::::::::::::::"+flag);
				flag = true;
			}else if(request.get("type").toString().equalsIgnoreCase("Settle to Bank")){
				type="Settle to Bank";
				param = new HashMap<String, Object>();	
				param.put("username", userDetails.getUserName());
				banklist=userbankdetailsdao.getUserBankDetailsByNamedQuery("getBankdetailsbyusername",param);
				if(banklist.isEmpty()){
					returnData.put("status","1");
					returnData.put("message","First update your bank details for settlement");
					return returnData;
				}else{
					UserBankDetails UserBankDetails=banklist.get(0);
					if(UserBankDetails.getStatus().equals("INACTIVE")){
						returnData.put("status","1");
						returnData.put("message","Your Bank Status INACTIVE.");
						return returnData;
					}else{
						flag = true;
					}
				}
				flag = true;
			}
			
			if(flag){
				settlementamountnew = settleopbal-(settlementamount+settlementcharge);
				param = new HashMap<String, Object>();	
				param.put("username", userDetails.getUserName());
				SettlementBalance SettlementBalance  = 	settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param).get(0);
				SettlementBalance.setSettlementbalance(settlementamountnew);
				flag=settlementbalancedao.updateSettlementBalance(SettlementBalance);
				if(flag){
					if(request.get("type").toString().equalsIgnoreCase("Settle to Bank")){
					settle2 = new SettlementReport(userDetails.getUserName(), settleopbal,settlementamountnew, settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"Settle to Bank","PENDING","PENDING");
					}else{
						settle2 = new SettlementReport(userDetails.getUserName(), settleopbal,settlementamountnew, settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"Settle to Wallet","SUCCESS","SUCCESS");	
					}
					flag=settlementreportdao.insertSettlementReport(settle2);
					if(flag){
						/*if(request.get("type").toString().equalsIgnoreCase("Settle to Bank")){
						UserBankDetails userbank= banklist.get(0);
						settleapi =  new SettlementApiRequest(aepsapiuser, aeps.getAgentcode(), settlementamount,type,"","","","","","" ,"",settlementcharge);
						}else{
							settleapi =  new SettlementApiRequest(aepsapiuser, aeps.getAgentcode(), settlementamount,type,"","","","","","" ,"",settlementcharge);	
						}
						RBLService.sendRequestToWebservice(aepsurl,settleapi,"settlementreq");*/
						returnData.put("status","1");
						returnData.put("message","success");
					}else{
						returnData.put("message","failed");
						returnData.put("status","0");
					}
				}
				}
					}else{
						returnData.put("message","Insufficient Settlement Balance");	
						returnData.put("status","0");	
						}
	/*	}*/
		}catch (Exception e) {
			logger_log.error("settlerequest::::::::::::::::::::::::::"+e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getResponseUrl(User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		List<Apisetting> list = new ArrayList<Apisetting>();
		Map<String, Object> param = new HashMap<>();
		try {
			if(userDetails.getRoleId() != 100){
				returnData.put("status", "0");
				returnData.put("message", "Not Api User!");
			} else {				
				param.put("userId", userDetails.getUserId());
				list = apisettingDao.getApisettingByNamedQuery("getApisettingByuserId", param);
				if(list.isEmpty()){
					returnData.put("status", "0");
					returnData.put("message", "No Url Setting Found");
				} else {
					returnData.put("status", "1");									
					returnData.put("ApiSetting", list.get(0));
				}
			}
		}catch (Exception e) {
			logger_log.error("getResponseUrl ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> updateResponseUrl(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		List<Apisetting> list = new ArrayList<Apisetting>();
		Map<String, Object> param = new HashMap<>();
		boolean flag = false;
		try {
			if(userDetails.getRoleId() != 100){
				returnData.put("status", "0");
				returnData.put("message", "Not Api User!");
			} else {	
				if (UtilityClass.checkParameterIsNull(request)) {
					if(request.get("responseUrl") == null || request.get("responseUrl").equals("")) {
						returnData.put("status", "0");
						returnData.put("message", "Enter Response Url Properly!");
					} else {
						param.put("userId", userDetails.getUserId());
						list = apisettingDao.getApisettingByNamedQuery("getApisettingByuserId", param);
						if (list.isEmpty()) {
							Apisetting apisetting = new Apisetting(userDetails.getUserId(), request.get("responseUrl"), "0", GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
							flag = apisettingDao.insertApisetting(apisetting);
						} else {
							Apisetting apisetting = list.get(0);
							apisetting.setResponseUrl(request.get("responseUrl"));
							flag = apisettingDao.updateApisetting(apisetting );
						}
						if(flag){
							returnData.put("status", "1");
							returnData.put("message", "Response url Update Successfully!");
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Failed to update Response url!");
						}
					}
				} else {
					returnData.put("status", "0");
					returnData.put("message", "Invalid Details!");
				}
			}
		}catch (Exception e) {
			logger_log.error("getResponseUrl ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getComplainDetails(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Complain complain = null;
		if (UtilityClass.checkParameterIsNull(request)) {
			try {
				Map<String, Object> param = new HashMap<>();
				param.put("subject", request.get("subject"));
				List<Complain> complainList = complainDao.getComplainByNamedQuery("getComplainBySubject", param);
				if (complainList.isEmpty()) {
					complain = new Complain(request.get("subject"), userDetails.getUserId(),"", "PENDING", "", userDetails.getWlId(),"Recharge Complain", null, null);
					complain.setId(0);
				} else {
					complain = complainList.get(0);
				}
				returnData.put("status", "1");
				returnData.put("complain", complain);
			} catch (Exception e) {
				logger_log.error("getComplainDetails ::::::::::::::: "+e);
				returnData.put("status", "0");
				returnData.put("message", "Exception!");
			}
		} else {
			returnData.put("status", "0");
			returnData.put("message", "Invalid Details!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> updateRechargeComplains(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Complain complain = null;
		boolean flag = false;

		try {
			if (request.get("id") == null || request.get("subject") == null || request.get("complainType") == null
					|| request.get("complain") == null || request.get("id").equals("")
					|| request.get("subject").equals("") || request.get("complainType").equals("") || request.get("complain").equals("")) {

				returnData.put("status", "0");
				returnData.put("message", "Invalid Details!");

			} else {
				if (request.get("id").equals("0")) {
					complain = new Complain(request.get("subject"),userDetails.getUserId(), request.get("complain"), "PENDING", "",
							userDetails.getWlId(), request.get("complainType"), GenerateRandomNumber.getCurrentDate(),
							GenerateRandomNumber.getCurrentTime());
					flag = complainDao.insertComplain(complain);
					if (flag) {
						returnData.put("status", "1");
						returnData.put("message", "Complain sent Successfully!");
					} else {
						returnData.put("status", "0");
						returnData.put("message", "Failed to Sent Complain!");
					}
				} else {
					complain = complainDao.getComplainById(Integer.parseInt(request.get("id")));
					if (complain == null) {
						returnData.put("status", "0");
						returnData.put("message", "Invalid Details!");
					} else {
						complain.setComplain(request.get("complain"));
						flag = complainDao.updateComplain(complain);
						if (flag) {
							returnData.put("status", "1");
							returnData.put("message", "Complain Update Successfully!");
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Failed to Update Complain!");
						}
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("updateRechargeComplains ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}

		return returnData;
	}

	@Override
	public Map<String, Object> balanceRequest(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (UtilityClass.checkParameterIsNull(request)) {
				if (Double.parseDouble(request.get("amount")) > 0) {
					if (request.get("type") == null || request.get("type").equals("")) {
						returnData.put("status", "0");
						returnData.put("message", "Invalid Request Type!");
					} else {
						if (request.get("type").equals("1")) {
							// balance request to admin
							if (request.get("id") == null || request.get("accNo") == null
									|| request.get("paymentMode") == null || request.get("transactionId") == null
									|| request.get("amount") == null || request.get("remarks") == null
									|| request.get("id").equals("") || request.get("accNo").equals("")
									|| request.get("paymentMode").equals("") || request.get("transactionId").equals("")
									|| request.get("amount").equals("") || request.get("remarks").equals("")) {

								returnData.put("status", "0");
								returnData.put("message", "Invalid Details!");

							} else {
								Bankdetails bank = bankdetailsDao.getBankdetailsById(Integer.parseInt(request.get("id")));
								if (bank != null) {
									Map<String, Object> param = new HashMap<>();
									param.put("wlId", userDetails.getWlId());
									User user = userDao.getUserByNamedQuery("getResellerDetails", param).get(0);
									param = new HashMap<>();
									param.put("transactionId",request.get("transactionId"));
									List<Balancerequest> blncreq = balancerequestDao.getBalanceRequestByNamedQuery("getBalancerequestbyTransactionId",param);
									if(blncreq.isEmpty()){
									Balancerequest balancerequest = new Balancerequest(userDetails.getWlId(),
											userDetails.getUserId(), user.getUserId(),bank.getAccName(), bank.getBankName(),
											bank.getBranchName(), bank.getIfscCode(), bank.getAccNo(),
											request.get("paymentMode"), request.get("transactionId"),
											request.get("remarks"), request.get("paymentDate"),
											userDetails.getBalance(), Double.parseDouble(request.get("amount")),
											GenerateRandomNumber.getCurrentDate(),
											GenerateRandomNumber.getCurrentTime(), "PENDING");
									boolean flag = balancerequestDao.insertBalanceRequest(balancerequest);
									if (flag) {
										returnData.put("status", "1");
										returnData.put("message", "Balance Request sent Successfully to admin!");
									} else {
										returnData.put("status", "0");
										returnData.put("message", "Failed to send balance request!");
									}
									
								}else{

									returnData.put("status", "0");
									returnData.put("message", "same transaction number again");
								
								}

								} else {
									returnData.put("status", "0");
									returnData.put("message", "Invalid Bank Details Details!");
								}
							}
						} else {
							// balance Request to upline

							if (request.get("accNo") == null
									|| request.get("accName") == null || request.get("bankName") == null
									|| request.get("branchName") == null || request.get("ifscCode") == null
									|| request.get("paymentMode") == null || request.get("transactionId") == null
									|| request.get("amount") == null || request.get("remarks") == null									
									|| request.get("accNo").equals("") || request.get("accName").equals("") || request.get("bankName").equals("") || request.get("branchName").equals("") || request.get("ifscCode").equals("")
									|| request.get("paymentMode").equals("") || request.get("transactionId").equals("")
									|| request.get("amount").equals("") || request.get("remarks").equals("")) {

								returnData.put("status", "0");
								returnData.put("message", "Invalid Details!");

							} else {								
								Balancerequest balancerequest = new Balancerequest(userDetails.getWlId(),
										userDetails.getUserId(), userDetails.getUplineId(),request.get("accName"), request.get("bankName"),
										request.get("branchName"), request.get("ifscCode"), request.get("accNo"),
										request.get("paymentMode"), request.get("transactionId"),
										request.get("remarks"), request.get("paymentDate"),
										userDetails.getBalance(), Double.parseDouble(request.get("amount")),
										GenerateRandomNumber.getCurrentDate(),
										GenerateRandomNumber.getCurrentTime(), "PENDING");
								boolean flag = balancerequestDao.insertBalanceRequest(balancerequest);
								if (flag) {
									returnData.put("status", "1");
									returnData.put("message", "Balance Request sent Successfully to Upline!");
								} else {
									returnData.put("status", "0");
									returnData.put("message", "Failed to send balance request!");
								}
							}
						}
					}
				} else {
					returnData.put("status", "0");
					returnData.put("message", "Invalid Amount!");
				}
			} else {
				returnData.put("status", "0");
				returnData.put("message", "Invalid Details!");
			}
		} catch (Exception e) {
			logger_log.error("updateRechargeComplains ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> addComplains(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Complain complain = null;
		boolean flag = false;
		try {
			if (request.get("subject") == null || request.get("complainType") == null || request.get("complain") == null
				|| request.get("subject").equals("") || request.get("complainType").equals("") || request.get("complain").equals("0")) {

				returnData.put("status", "0");
				returnData.put("message", "Invalid Details!");

			} else {
				if (userDetails.getRoleId() != 1) {
					complain = new Complain(request.get("subject"), userDetails.getUserId(), request.get("complain"),
							"PENDING", "", userDetails.getWlId(), request.get("complainType"),
							GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					flag = complainDao.insertComplain(complain);
					if (flag) {
						returnData.put("status", "1");
						returnData.put("message", "Complain sent Successfully!");
					} else {
						returnData.put("status", "0");
						returnData.put("message", "Failed to Sent Complain!");
					}
				} else {
					returnData.put("status", "0");
					returnData.put("message", "Forbidden User!");
				}
			}
		} catch (Exception e) {
			logger_log.error("addComplains ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}

		return returnData;
	}
	
	@Override
	public Map<String, Object> addRBLBank(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag = false;
		try{
			UserBankDetails userbank = new UserBankDetails(request.get("mobile"),request.get("email"),request.get("name"),request.get("branch"),request.get("account"),request.get("ifsc"),request.get("bank"),userDetails.getUserName(),"ACTIVE");
			param.put("username", userDetails.getUserName());
			List<UserBankDetails> list=userbankdetailsdao.getUserBankDetailsByNamedQuery("getBankdetailsbyusername",param);
			if(!list.isEmpty()){
				UserBankDetails user =  list.get(0);
				user.setAccount(request.get("account"));
				user.setBranch(request.get("branch"));
				user.setEmail(request.get("email"));
				user.setIfsc(request.get("ifsc"));
				user.setMobile(request.get("mobile"));
				user.setName(request.get("name"));
				user.setBank(request.get("bank"));
				user.setStatus("ACTIVE");
				flag=userbankdetailsdao.updateUserBankDetails(user);
				if(flag){
					returnData.put("status","1");
					returnData.put("message","data updated successfully");
					if(!request.containsKey("source")){
					returnData.put("nextPage","home");
					}
				}else{
					returnData.put("status","0");
					returnData.put("message","data not updated");
					if(!request.containsKey("source")){
					returnData.put("nextPage","home");
					}
					}
			}else{
				flag = userbankdetailsdao.insertUserBankDetails(userbank);
				if(flag){
					returnData.put("status","1");
					returnData.put("message","data inserted successfully");
					if(!request.containsKey("source")){
					returnData.put("nextPage","home");
					}
				}else{
					returnData.put("status","0");
					returnData.put("message","data not inserted");
					if(!request.containsKey("source")){
					returnData.put("nextPage","home");
					}
				}
			}
		}catch (Exception e) {
			logger_log.error("addRBLBank::::::::::::::::::::::::::"+e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> eBillPaymentPostPaid(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {			
			Operator operator = new Operator();
			PackageWiseChargeComm pckret=new PackageWiseChargeComm();
			PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
			PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
			PackageWiseChargeComm pckres=new PackageWiseChargeComm();
			PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
			String status = "";			
			double comm = 0.0;
			double dComm = 0.0;
			double sdComm = 0.0;
			double resComm = 0.0;
			double retComm = 0.0;
			double sdCharge = 0.0;
			double distCharge = 0.0;			
			double resCharge = 0.0;
			String rechargeStatus="";
			String operatorid="";
			double dcharge = 0.0;
			double sCharge = 0.0;
			double reCharge = 0.0;
			
			double charge = 0.0;
			double totalAmount = 0.0;
			String resellerId = "";
			String sdId = "";
			String distId = "";	
			String rId="";
			String response = "";
			Chargeset dCharge = null;
			if (UtilityClass.checkParameterIsNull(request)) {
				logger_log.warn("eBillPaymentPostPaid:::"+request);
				
				System.out.println("Balance::"+userDetails.getBalance());
				String mobileNo = request.get("consumerMobile");
				double balance=userDetails.getBalance();
				double amount = Double.parseDouble(request.get("Dueamount"));
				String source = request.get("source");	
				
				parameters = new HashMap<String, String>();
				
				parameters.put("mobile", mobileNo);
				parameters.put("amount", String.valueOf(amount));
				parameters.put("userId", userDetails.getUserId());
				parameters.put("date", GenerateRandomNumber.getCurrentDate());
				long timeDiff = customQueryServiceLogic.getRechargeLock(CustomSqlQuery.getRechargeLock, parameters);
				
				double lockbalance=userDetails.getLockedAmount();
				double cbal=balance-amount;
				if(lockbalance<=cbal){
				if(balance<amount)
				{
					returnData.put("status", "0");
					returnData.put("message", "Amount cannot greater than Balance!");
				}else{
				
				if(timeDiff > 10){
					userDetails = userDao.getUserByUserId(userDetails.getUserId());
					param = new HashMap<String, Object>();
					
					param.put("inCode", request.get("operator"));
					List<Operator> opList =  operatorDao.getOperatorByNamedQuery("getOperatorByInCode", param );				
					param = new HashMap<String, Object>();	
					
					if((!opList.isEmpty())) {
						operator = opList.get(0);	
						String operatorStatus=operator.getStatus();
						System.out.println("operatorStatus==="+operatorStatus);
						if(operatorStatus.equals("available"))
						{
						// ------------------------------  User's Charge calculation for the Recharge -------------------------------------//
							
							
								if (userDetails.getRoleId() == 5) {
									pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId()) ;
									if (pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
										charge = (pckret.getCharge() * amount) / 100;
									} else {
										charge = pckret.getCharge();
									}
								} else if (userDetails.getRoleId() == 4) {
									pckdis = commissionService.getPackagewiseCommisionOnOperator(
											userDetails.getUserId(), "Recharge", operator.getOperatorId());
									if (pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
										charge = (pckdis.getCharge() * amount) / 100;
									} else {
										charge = pckdis.getCharge();
									}

								} else if (userDetails.getRoleId() == 3) {
									pcksd = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),
											"Recharge", operator.getOperatorId());
									if (pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
										charge = (pcksd.getCharge() * amount) / 100;
									} else {
										charge = pcksd.getCharge();
									}

								}else if (userDetails.getRoleId() == 100) {
									pckapiu = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),
											"Recharge", operator.getOperatorId());
									if (pckapiu.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
										charge = (pckapiu.getCharge() * amount) / 100;
										comm=(pckapiu.getComm() * amount) / 100;
									} else {
										charge = pckapiu.getCharge();
										comm=pckapiu.getComm();
									}

								}
						/*-------------------------------------------------------------------------------------------------------------------*/
						String tId = "";
						/*-------------------------------------COMMISSION---------------------------------------------------------------------*/
						if(userDetails.getRoleId() == 5) {
							//Retailer Id
							//rId=userDetails.getUserId();
							// Distributor Id
							distId = userDetails.getUplineId();		
							
							// Super Distributor Id
							parameters = new HashMap<String, String>();
							parameters.put("userId", distId);	
							sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
							
							// Reseller Id
							parameters = new HashMap<String, String>();
							parameters.put("userId", sdId);							
							resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
							if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							retComm =(pckret.getComm()*amount)/100;
							}else{
							retComm =pckret.getComm();	
							}
							comm = retComm;
						//	System.out.println("reseller=="+comm);
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*amount)/100;
							}else{
							dComm=pckdis.getComm();	
							}
						//	System.out.println("dComm=="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*amount)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
						//	System.out.println("sdComm=="+sdComm);
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*amount)/100;
							}else{
							resComm =pckres.getComm();	
							}
						//	System.out.println("resComm=="+resComm);
							
						} else if(userDetails.getRoleId() == 4) {
							//distId = userDetails.getUserId();	
							sdId = userDetails.getUplineId();	
							
							parameters = new HashMap<String, String>();
							parameters.put("userId", sdId);							
							resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
							
							pckdis=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId()); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*amount)/100;
							}else{
							dComm=pckdis.getComm();	
							}
							comm = dComm;
						//	System.out.println("dComm="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*amount)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							sdComm=sdComm-dComm;
							//System.out.println("sdComm="+sdComm);
							if(!resellerId.equals("admin")) {
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*amount)/100;
							}else{
							resComm =pckres.getComm();	
							}
							resComm=resComm-sdComm;
							}
							//System.out.println("resComm="+resComm);
							
						} else if(userDetails.getRoleId() == 3) {
							resellerId = userDetails.getUplineId();
							pcksd=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId());
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						     sdComm =(pcksd.getComm()*amount)/100;
							}else{
								sdComm = pcksd.getComm();	
							}
							comm=sdComm;
							System.out.println("sdComm:::::::::"+sdComm);
							if(!resellerId.equals("admin")) {
								pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								resComm =(pckres.getComm()*amount)/100;
								}else{
								resComm =pckres.getComm();	
								}
								resComm=resComm-sdComm;
								}
				
						}
						/*---------------------------------------------------------------------------------------------------------------*/
						if (amount > 0) {						
							if(source.equals("API")){
								tId=request.get("request_id");
								}
							else{
							tId = GenerateRandomNumber.generatePtid(request.get("consumerMobile"));
							}
							if (!tId.equals("")) {
								String today = GenerateRandomNumber.getCurrentDate();	
								String currentTime = GenerateRandomNumber.getCurrentTime();
							//	comm = RoundNumber.roundDouble((comm * amount)/100);
								totalAmount = RoundNumber.roundDouble((amount + charge) - comm);
								parameters = new HashMap<String, String>();
								double adOpBal = customQueryServiceLogic.getTotalbalanceForAdmin(CustomSqlQuery.getTotalbalanceForAdmin, parameters);
								RoundNumber.roundDouble(adOpBal - totalAmount);
								parameters = new HashMap<String, String>();
								parameters.put("userId", userDetails.getUserId());	
								double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters );	
								
								System.out.println("op_bal"+op_bal);
								double cl_bal = RoundNumber.roundDouble(op_bal - totalAmount);
								System.out.println("totalAmount"+totalAmount);
								System.out.println("op_bal"+cl_bal);
								if (cl_bal > userDetails.getLockedAmount()) {
									Rechargedetails rechargedetails = new Rechargedetails(userDetails.getUserId(), mobileNo, operator.getOperatorId(), op_bal, amount, charge,comm, cl_bal, tId, tId, tId, "PENDING", "PENDING",source , operator.getApiId(), userDetails.getRoleId(), userDetails.getWlId(), today, currentTime,request.get("ip"));
									boolean flag = rechargedetailsDao.insertRechargeDetails(rechargedetails);								
									if (flag) {
									
										flag = commissionService.updateBalance(userDetails.getUserId(), "Recharge to "+mobileNo, "Recharge", totalAmount, "DEBIT",0);
										if (flag) {	
											EarningSurcharge earningSurcharge = new EarningSurcharge(userDetails.getRoleId(), userDetails.getWlId(), userDetails.getUserId(), comm, charge, "RECHARGE FOR - "+mobileNo, today, currentTime);
											earningSurchargeDao.insertEarningSurcharge(earningSurcharge );
											Api lapi=apiDao.getApiByApId(operator.getApiId());
										//	String ocd=opcdlist.get(0).getOutCode();
											 if (operator.getApiId() == 3) {
													Client client = null;
													String Rechargeurl ="https://www.instantpay.in/ws/bbps/bill_pay";
													 logger_log.warn("instantpay rechargeurl :: "+Rechargeurl);
													 MultivaluedMap<String, Object> headers= new MultivaluedHashMap<>();
														headers.add("Accept", "application/json");
														headers.add("Content-Type", "application/json");
														 String bodyjson="{\"token\": \""+token+"\",\"request\": {\"sp_key\": \""+operator.getOutCode()+"\",\"agentid\": \""+tId+"\",\"customer_mobile\": \""+request.get("consumerMobile")+"\",\"customer_params\": [\""+request.get("consumerNumber")+"\",\"{{customer_param2}}\"], \"init_channel\": \"AGT\",\"endpoint_ip\": \"123.258.255.12\",\"mac\": \"AD-fg-12-78-GH\",\"payment_mode\": \"Cash\",\"payment_info\": \"bill\",\"amount\": \""+amount+"\",\"reference_id\": \"\", \"latlong\": \"24.1568,78.5263\", \"outletid\": \"23321\"}}";
														 client=ClientBuilder.newClient();
														 logger_log.warn("instantpay bodyjson :: "+bodyjson);
														 Response response1=client.target(Rechargeurl).request(MediaType.APPLICATION_JSON).headers(headers).post(Entity.entity(bodyjson, MediaType.APPLICATION_JSON));
														 
													// response = "{'ipay_id':'1181110140749MYFJA','agent_id':'451850100825','opr_id':'EU01UDGMRKQ9','account_no':'11000049451','sp_key':'AAE','trans_amt':253.21,'charged_amt':252.5,'opening_bal':'3778.39','datetime':'2018-11-10 14:07:55','status':'SUCCESS','res_code':'TXN','res_msg':'Transaction Successful'}";
												     logger_log.warn("instantpay RESPONSE :: "+response1);
												     String output =response1.readEntity(String.class);
													   logger_log.warn("instantpay output :: "+output);	
													   JSONObject respons=new JSONObject(output);
													   logger_log.warn("instantpay BBPS response :: "+respons);	
												  //   returnData=InstantWebserviceParser.bbpsBillPaymentFinalParser(response.toString());
													   
													   if (respons.get("status").toString().equalsIgnoreCase("Transaction Successful") ) {
															 JSONObject data= respons.getJSONObject("data");
															status = "SUCCESS";
															
															if(!data.get("opr_id").equals("")){
																String opdid=data.get("opr_id").toString();
																Map<String,String> params=new HashMap<String,String>();
																params.put("status", status);
																params.put("opr_id", opdid);
																params.put("ipay_id", tId);
																ApiResponseService.instantpayresponse(params);
															}
															
														}else {
															status = "FAILED";
												}		
												
												} else if (operator.getApiId() == 6) {
												status = "SUCCESS";
												}else if (operator.getApiId() == 7) {
													status = "SUCCESS";
													}
											Map<String, Object> param1 = new HashMap<String, Object>();
											param1.put("ptid", tId);
											List<Rechargedetails> rechargeDetails = rechargedetailsDao.getRechargeDetailsByNamedQuery("GetRechargeDetailsByPTid", param1);
											if(!rechargeDetails.isEmpty()) {
												Rechargedetails rechargedetail = rechargeDetails.get(0);
												logger_log.warn("rechargedetail:::"+rechargedetail);
												if(rechargedetail.getValidation().equals("PENDING")) {
											if (status.equals("SUCCESS")) {
												
												rechargedetails.setStatus("SUCCESS");
												//rechargedetails.setValidation("PENDING");
												rechargedetailsDao.updateRechargeDetails(rechargedetails);											
												returnData.put("status", "1");
												returnData.put("message", "Recharge Submit SUCCESSFUL. Transaction Id: "+tId);
												if(source.equals("APPS")){
													returnData.put("closingBalance", cl_bal);
												}
												if(source.equals("API")){
													returnData.put("operatorid", operatorid);
													returnData.put("rechargeStatus", rechargeStatus);
													returnData.put("TransactionId", tId);
												}
												/*----------------------CHARGE & COMMISSION FOR THE RECHARGE-------------------------------*/
												
												if(userDetails.getRoleId() == 5) {
													if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
														distCharge = (pckdis.getCharge()*amount)/100;
													}else{
														distCharge = pckdis.getCharge();
													}
													if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
														sdCharge = (pcksd.getCharge()*amount)/100;
													}else{
														sdCharge = pcksd.getCharge();
													}
														if(!resellerId.equals("admin")) {
															if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
															resCharge = (pckres.getCharge()*amount)/100;
															}else{
															resCharge =	pckres.getCharge();
															}
															
														}
														if(distCharge==0){dcharge=0;}
														else{dcharge = charge - distCharge;}
														
														if(sdCharge==0){sCharge=0;}
														else{sCharge = distCharge - sdCharge;}
														
														
														commissionService.updateBalance(distId, "Charge for Recharge - "+mobileNo, "CHARGE", dcharge, "CREDIT",0);
														commissionService.updateBalance(sdId, "Charge for Recharge - "+mobileNo, "CHARGE", sCharge, "CREDIT",0);
														if(!resellerId.equals("admin")) {
															if(resCharge==0){reCharge=0;}
															else{
															reCharge = sdCharge - resCharge;
															}
															commissionService.updateBalance(resellerId, "Charge for Recharge - "+mobileNo, "Recharge Charge", reCharge, "CREDIT",0);
														}
														
														/*-------------------------------For earning and Surcharge ------------------*/
														// for DISTRIBUTOR 
														EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, userDetails.getWlId(), distId, 0.0, dcharge, "Charge FOR - "+mobileNo, today, currentTime);
														earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
														
														// For Super Distributor
														EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, 0.0, sCharge, "Charge FOR - "+mobileNo, today, currentTime);
														earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
														
														// For RESELLER
														if(!resellerId.equals("admin")) {
															EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, 0.0, reCharge, "Charge FOR - "+mobileNo, today, currentTime);
															earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
														}
														/*----------------------------------------------------------------------*/
														
																					
													/*//commissionService.updateBalance(distId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", dComm, "CREDIT",0);
													//commissionService.updateBalance(sdId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", sdComm, "CREDIT",0);
													if(!resellerId.equals("admin")) {
														//commissionService.updateBalance(resellerId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", resComm, "CREDIT",0);
													}*/
												} else if(userDetails.getRoleId() == 4) {
													
													if(!resellerId.equals("admin")) {
														if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
														resCharge = (pckres.getCharge()*amount)/100;
														}else{
														resCharge =	pckres.getCharge();
														}
														
													}
													if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
														sdCharge = (pcksd.getCharge()*amount)/100;
													}else{
														sdCharge = pcksd.getCharge();
													}
													if(sdCharge==0){sCharge=0;}
													else{sCharge = charge - sdCharge;}
																										
													commissionService.updateBalance(sdId, "Charge for Recharge - "+mobileNo, "CHARGE", sCharge, "CREDIT",0);
													if(!resellerId.equals("admin")) {
														if(resCharge==0){reCharge=0;}
													else{
													reCharge = sdCharge - resCharge;
													}
														commissionService.updateBalance(resellerId, "Charge for Recharge - "+mobileNo, "Recharge Charge", reCharge, "CREDIT",0);
													}														
													/*-------------------------------For earning and Surcharge ------------------*/
																											
													// For Super Distributor
													EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, 0.0, sCharge, "Charge FOR - "+mobileNo, today, currentTime);
													earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
													
													// For RESELLER
													if(!resellerId.equals("admin")) {
														EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, 0.0, reCharge, "Charge FOR - "+mobileNo, today, currentTime);
														earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
													}
													/*----------------------------------------------------------------------*/
												
													} else if(userDetails.getRoleId() == 3) {

														if (!resellerId.equals("admin")) {
															if (pckres.getCharge_type()
																	.equalsIgnoreCase("PERCENTAGE"))
																resCharge = (pckres.getCharge() * amount) / 100;
														} else {
															resCharge = pckres.getCharge();
														}
														resCharge = charge - resCharge;

														if (!resellerId.equals("admin")) {
															
															reCharge = sdCharge - charge;
															commissionService.updateBalance(resellerId,
																	"Charge for Recharge - " + mobileNo,
																	"Recharge Charge", reCharge, "CREDIT",0);
														}

														/*-------------------------------For earning and Surcharge ------------------*/

														// For RESELLER
														if (!resellerId.equals("admin")) {
															EarningSurcharge earningSurcharge3 = new EarningSurcharge(
																	4, userDetails.getWlId(), resellerId, 0.0,
																	reCharge, "Charge FOR - " + mobileNo, today,
																	currentTime);
															earningSurchargeDao
																	.insertEarningSurcharge(earningSurcharge3);
														}
														/*----------------------------------------------------------------------*/

													}
												
											} else if (status.equals("FAILED")) {
												rechargedetails.setStatus("FAILED");
												rechargedetails.setValidation("FAILED");
												rechargedetailsDao.updateRechargeDetails(rechargedetails);										
												commissionService.updateBalance(userDetails.getUserId(), "REFUND for Recharge - "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
												
												returnData.put("status", "0");
												returnData.put("message", "Recharge FAILED. Transaction Id: "+tId);
												if(source.equals("API")){
													returnData.put("operatorid", "NA");
													returnData.put("rechargeStatus", rechargeStatus);
													returnData.put("TransactionId", tId);
												}
												if(source.equals("APPS")){
													returnData.put("closingBalance", cl_bal);
												}
											}
												}else{
													returnData.put("status", "1");
													returnData.put("message", "Recharge "+rechargedetail.getValidation()+"Transaction Id: "+tId);
													logger_log.warn("Validation is not Pending ::::::::: ");
													
														if(source.equals("API")){
															returnData.put("operatorid", operatorid);
															returnData.put("rechargeStatus", status);
															returnData.put("TransactionId", tId);
														}
														if(source.equals("APPS")){
															returnData.put("closingBalance", cl_bal);
														}
												}
											}
	
										} else {
											returnData.put("status", "0");
											if(source.equals("APPS")){
												returnData.put("closingBalance", cl_bal);
											}
											returnData.put("message", "Recharge Failed. Technical Error!");
										}
									} else {
										returnData.put("status", "0");
										if(source.equals("APPS")){
											returnData.put("closingBalance", cl_bal);
										}
										returnData.put("message", "Recharge Failed. Technical Error!");
									}
								} else {
									returnData.put("status", "0");
									if(source.equals("APPS")){
										returnData.put("closingBalance", cl_bal);
									}
									returnData.put("message", "Recharge Failed. Insufficient Balance!");
								}
							}else {
								returnData.put("status", "0");
								returnData.put("message", "Recharge Failed. Invalid Mobile Numbers!");
							}
						}else {
							returnData.put("status", "0");
							returnData.put("message", "Recharge Failed. Invalid Amount!");
						}
					}else {
						returnData.put("status", "0");
						returnData.put("message", "Recharge Failed. Operator is not available");
					}
					}else {
						returnData.put("status", "0");
						returnData.put("message", "Recharge Failed. Please try after Some time");
					}
					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "Recharge Failed. Same amount same number after 10 minutes.");
				}
				
				}
				}else{
					returnData.put("status", "0");
					returnData.put("message", "Insufficient Balance!");
				}
			
			} else {
				returnData.put("status", "0");
				returnData.put("message", "Please try after 10 minutes");
			}
			
		} catch (Exception e) {
			logger_log.error("webRecharge ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	
	@Override
	public Map<String, Object> utilityBillPayment(Map<String, String> request, User userDetails){
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {			
			Operator operator = new Operator();
			PackageWiseChargeComm pckret=new PackageWiseChargeComm();
			PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
			PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
			PackageWiseChargeComm pckres=new PackageWiseChargeComm();
			PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
			String status = "";			
			double comm = 0.0;
			double dComm = 0.0;
			double sdComm = 0.0;
			double apiComm=0.0;
			double resComm = 0.0;
			double retComm = 0.0;
			double sdCharge = 0.0;
			double distCharge = 0.0;			
			double resCharge = 0.0;
			
			double dcharge = 0.0;
			double sCharge = 0.0;
			double reCharge = 0.0;
			
			double charge = 0.0;
			double totalAmount = 0.0;
			String resellerId = "";
			String sdId = "";
			String distId = "";		
			String response = "";
			Chargeset dCharge = null;
			if (UtilityClass.checkParameterIsNull(request)) {				
				logger_log.warn("utility request:::::"+request);
				if (request.get("operator") == null || request.get("consumerNumber") == null
						|| request.get("Dueamount") == null || request.get("consumerMobile") == null
						|| request.get("CUSTNAME") == null						
						||request.get("operator").equals("") || request.get("consumerNumber").equals("")
						|| request.get("Dueamount").equals("") || request.get("consumerMobile").equals("")
						|| request.get("CUSTNAME").equals("")) {
					
					returnData.put("status", "0");
					returnData.put("message", "Invalid Request!");

				} else{	
				/*	System.out.println("INCODE :: "+request.get("operator"));
					System.out.println("mobile :: "+request.get("consumerNumber"));
					System.out.println("amount :: "+request.get("amount"));*/
					
					String mobileNo = request.get("consumerNumber");
					double amount = Double.parseDouble(request.get("Dueamount"));
					String source = request.get("source");	
					double balance=userDetails.getBalance();
					User user = userDao.getUserByUserId(userDetails.getUserId());
					String activestatus=user.getStatus();
					if(activestatus.equals("1")){
						returnData.put("status", "0");
						returnData.put("message", "Inactive User.");
					}else{
					parameters = new HashMap<String, String>();
					
					parameters.put("mobile", mobileNo);
					parameters.put("amount", String.valueOf(amount));
					parameters.put("userId", userDetails.getUserId());
					parameters.put("date", GenerateRandomNumber.getCurrentDate());
					long timeDiff = customQueryServiceLogic.getRechargeLock(CustomSqlQuery.getRechargeLock, parameters);
					///=======
					parameters = new HashMap<String, String>();
					parameters.put("userId", userDetails.getUserId());
					double op_bal = customQueryServiceLogic
							.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
				//	double lockbalance=user.getLockedAmount();
					double cbal=op_bal-amount;
					if(0<=cbal){
						if(op_bal<amount)
						{
							returnData.put("status", "0");
							returnData.put("message", "Amount cannot greater than Balance!");
						}else{
					if(timeDiff > 10){
						userDetails = userDao.getUserByUserId(userDetails.getUserId());
						param = new HashMap<String, Object>();
						
						param.put("inCode", request.get("operator"));
						List<Operator> opList =  operatorDao.getOperatorByNamedQuery("getOperatorByInCode", param );				
						param = new HashMap<String, Object>();	
						
						if(!opList.isEmpty()) {
							operator = opList.get(0);	
							String operatorStatus=operator.getStatus();
							System.out.println("operatorStatus==="+operatorStatus);
							if(operatorStatus.equals("available"))
							{
							// ------------------------------  User's Charge calculation for the Recharge -------------------------------------//
								
								if (userDetails.getRoleId() == 5) {
									pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId()) ;	
									if (pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
										charge = (pckret.getCharge() * amount) / 100;
									} else {
										charge = pckret.getCharge();
									}
								} else if (userDetails.getRoleId() == 4) {
									pckdis = commissionService.getPackagewiseCommisionOnOperator(
											userDetails.getUserId(), "Recharge", operator.getOperatorId());
									if (pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
										charge = (pckdis.getCharge() * amount) / 100;
									} else {
										charge = pckdis.getCharge();
									}

								} else if (userDetails.getRoleId() == 3) {
									pcksd = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),
											"Recharge", operator.getOperatorId());
									if (pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
										charge = (pcksd.getCharge() * amount) / 100;
									} else {
										charge = pcksd.getCharge();
									}

								}else if (userDetails.getRoleId() == 100) {
									pckapiu = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),
											"Recharge", operator.getOperatorId());
									if (pckapiu.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
										charge = (pckapiu.getCharge() * amount) / 100;
									} else {
										charge = pckapiu.getCharge();
									}

								}			
							/*-------------------------------------------------------------------------------------------------------------------*/
							String tId = "";
							/*-------------------------------------COMMISSION---------------------------------------------------------------------*/
							if(userDetails.getRoleId() == 5) {
								//Retailer Id
								//rId=userDetails.getUserId();
								// Distributor Id
								distId = userDetails.getUplineId();		
								
								// Super Distributor Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", distId);	
								sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
								
								// Reseller Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", sdId);							
								resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
								if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								retComm =(pckret.getComm()*amount)/100;
								}else{
								retComm =pckret.getComm();	
								}
								comm = retComm;
								System.out.println("reseller=="+comm);
								pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
								if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								dComm =(pckdis.getComm()*amount)/100;
								}else{
								dComm=pckdis.getComm();	
								}
								System.out.println("dComm=="+dComm);
								pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
								if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								sdComm =(pcksd.getComm()*amount)/100;
								}else{
								sdComm = pcksd.getComm();	
								}
								System.out.println("sdComm=="+sdComm);
								pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								resComm =(pckres.getComm()*amount)/100;
								}else{
								resComm =pckres.getComm();	
								}
								System.out.println("resComm=="+resComm);
								
							} else if(userDetails.getRoleId() == 4) {
								//distId = userDetails.getUserId();	
								sdId = userDetails.getUplineId();	
								
								parameters = new HashMap<String, String>();
								parameters.put("userId", sdId);							
								resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
								
								pckdis=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId()); 
								if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								dComm =(pckdis.getComm()*amount)/100;
								}else{
								dComm=pckdis.getComm();	
								}
								comm = dComm;
								System.out.println("dComm="+dComm);
								pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
								if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								sdComm =(pcksd.getComm()*amount)/100;
								}else{
								sdComm = pcksd.getComm();	
								}
								sdComm=sdComm-dComm;
								System.out.println("sdComm="+sdComm);
								if(!resellerId.equals("admin")) {
								pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								resComm =(pckres.getComm()*amount)/100;
								}else{
								resComm =pckres.getComm();	
								}
								resComm=resComm-sdComm;
								}
								System.out.println("resComm="+resComm);
								
							} else if(userDetails.getRoleId() == 3) {
								resellerId = userDetails.getUplineId();
								pcksd=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId());
								if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							     sdComm =(pcksd.getComm()*amount)/100;
								}else{
									sdComm = pcksd.getComm();	
								}
								comm = sdComm;
								System.out.println("sdComm:::::::::"+sdComm);
							}else if(userDetails.getRoleId() == 100) {
								pckapiu=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId());
								if(pckapiu.getComm_type().equalsIgnoreCase("PERCENTAGE")){
									apiComm =(pckapiu.getComm()*amount)/100;
								}else{
									apiComm = pckapiu.getComm();	
								}
								comm = apiComm;
								System.out.println("apiComm:::::::::"+apiComm);
								
								}
							/*---------------------------------------------------------------------------------------------------------------*/
							if (amount > 0) {						
								tId = GenerateRandomNumber.generatePtid(request.get("consumerNumber"));
								if (!tId.equals("")) {
									String today = GenerateRandomNumber.getCurrentDate();	
									String currentTime = GenerateRandomNumber.getCurrentTime();
									totalAmount = RoundNumber.roundDouble((amount + charge) - comm);
									parameters = new HashMap<String, String>();
									double adOpBal = customQueryServiceLogic.getTotalbalanceForAdmin(CustomSqlQuery.getTotalbalanceForAdmin, parameters);
									RoundNumber.roundDouble(adOpBal - totalAmount);
									/*parameters = new HashMap<String, String>();
									parameters.put("userId", userDetails.getUserId());	
									double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters );							*/
									double cl_bal = RoundNumber.roundDouble(op_bal - totalAmount);							
									if (cl_bal > 0) {
										Rechargedetails rechargedetails = new Rechargedetails(userDetails.getUserId(), mobileNo, operator.getOperatorId(), op_bal, amount, charge,comm, cl_bal, tId, tId, tId, "SUCCESS", "PENDING",source , operator.getApiId(), userDetails.getRoleId(), userDetails.getWlId(), today, currentTime,request.get("ip"));
										boolean flag = rechargedetailsDao.insertRechargeDetails(rechargedetails);								
										if (flag) {
											/*Transactiondetails trsansaction = new Transactiondetails(userDetails.getUserId(), op_bal, 0.0, totalAmount, cl_bal, "Recharge to "+mobileNo, "Recharge", today, currentTime, adOpBal, adClBal, userDetails.getWlId());
											flag = transactiondetailsDao.insertTransactionDetails(trsansaction);
											userDetails.setBalance(cl_bal);
											flag = userDao.updateUser(userDetails);*/
											flag = commissionService.updateBalance(userDetails.getUserId(), "UTILITY to "+mobileNo, "UTILITY", totalAmount, "DEBIT",0);
											if (flag) {			
													Utility utility = new Utility(tId, operator.getOperatorId(), userDetails.getUserId(), Integer.parseInt(operator.getServiceType()), operator.getServiceProvider(), request.get("CUSTNAME"), request.get("consumerMobile"), request.get("consumerNumber"), request.get("BILLDATE"), amount, today, currentTime, "PENDING", userDetails.getWlId());
													boolean flag1 = utilityDao.insertUtility(utility);
													if(flag1){
														Api lapi=apiDao.getApiByApId(operator.getApiId());
														//	String ocd=opcdlist.get(0).getOutCode();
															if (operator.getApiId() == 1) {
																returnData=EasyPayBbpsDao.bbpsBillPAy(request, userDetails);
																
																}else if (operator.getApiId() == 2) {
														status = "PENDING";
													} else if (operator.getApiId() == 4) {												
														//int amnt = Integer.parseInt(request.get("Dueamount"));
														 String Rechargeurl = "https://www.doopme.com/RechargeAPI/RechargeAPI.aspx?MobileNo="+lapi.getUsername()+"&APIKey="+lapi.getPassword()+"&REQTYPE=BILLPAY&REFNO="+tId+"&SERCODE="+operator.getOutCode()+"&CUSTNO="+request.get("consumerNumber")+"&REFMOBILENO="+request.get("consumerMobile")+"&AMT="+request.get("Dueamount")+"&STV=0&PCODE=380054&LAT=22.6343188&LONG=88.4346478&RESPTYPE=JSON";
															logger_log.warn("doopme Utility Rechargeurl :: "+Rechargeurl);
														response = sendRechargeRequest.sendRechagreReq(Rechargeurl);
														logger_log.warn("doopme Utility RESPONSE :: "+response);
														//response = "{"STATUSCODE":"0","STATUSMSG":"Success","REFNO":"123","TRNID":11111,"TRNSTATUS":0,"TRNSTATUSDESC":"Request Accepted","OPRID":""}";
														//DoopMeModel doope = apiParserService.doopMeParser(response);
														JSONObject obj=new JSONObject(response);
														if(obj.get("STATUSMSG").equals("Success")){
															status = "SUCCESS";
															if(!obj.get("OPRID").equals("")){
																String opd=(String) obj.get("OPRID");
																Map<String,String> pm=new HashMap<>();
																pm.put("Status", "1");
																pm.put("OprID", opd);
																pm.put("ClientRefNo", tId);
																ApiResponseService.doopMeApiResponse(pm);
															}
														}else{
															status = "FAILED";
														}
													} else if (operator.getApiId() == 5) {
														status = "SUCCESS";
													} else if (operator.getApiId() == 3) {
														Client client = null;
														String Rechargeurl ="https://www.instantpay.in/ws/bbps/bill_pay";
														 logger_log.warn("instantpay rechargeurl :: "+Rechargeurl);
														 MultivaluedMap<String, Object> headers= new MultivaluedHashMap<>();
															headers.add("Accept", "application/json");
															headers.add("Content-Type", "application/json");
															 String bodyjson="{\"token\": \""+token+"\",\"request\": {\"sp_key\": \""+operator.getOutCode()+"\",\"agentid\": \""+tId+"\",\"customer_mobile\": \""+request.get("consumerMobile")+"\",\"customer_params\": [\""+request.get("consumerNumber")+"\",\"{{customer_param2}}\"], \"init_channel\": \"AGT\",\"endpoint_ip\": \"123.258.255.12\",\"mac\": \"AD-fg-12-78-GH\",\"payment_mode\": \"Cash\",\"payment_info\": \"bill\",\"amount\": \""+amount+"\",\"reference_id\": \""+request.get("reference_id")+"\", \"latlong\": \"24.1568,78.5263\", \"outletid\": \"23321\"}}";
															 client=ClientBuilder.newClient();
															 Response response1=client.target(Rechargeurl).request(MediaType.APPLICATION_JSON).headers(headers).post(Entity.entity(bodyjson, MediaType.APPLICATION_JSON));
															 
														// response = "{'ipay_id':'1181110140749MYFJA','agent_id':'451850100825','opr_id':'EU01UDGMRKQ9','account_no':'11000049451','sp_key':'AAE','trans_amt':253.21,'charged_amt':252.5,'opening_bal':'3778.39','datetime':'2018-11-10 14:07:55','status':'SUCCESS','res_code':'TXN','res_msg':'Transaction Successful'}";
													     logger_log.warn("instantpay RESPONSE :: "+response1);
													     String output =response1.readEntity(String.class);
														   logger_log.warn("instantpay output :: "+output);	
														   JSONObject respons=new JSONObject(output);
														   logger_log.warn("instantpay BBPS response :: "+respons);	
													  //   returnData=InstantWebserviceParser.bbpsBillPaymentFinalParser(response.toString());
														   
														   if (respons.get("statuscode").toString().equalsIgnoreCase("TXN") ) {
																 JSONObject data= respons.getJSONObject("data");
																status = "SUCCESS";
																
																if(!data.get("opr_id").equals("")){
																	String opdid=data.get("opr_id").toString();
																	Map<String,String> params=new HashMap<String,String>();
																	params.put("status", status);
																	params.put("opr_id", opdid);
																	params.put("ipay_id", tId);
																	ApiResponseService.instantpayresponse(params);
																}
																
															}else  if (respons.get("statuscode").toString().equalsIgnoreCase("ERR") ) {
																status = "FAILED";
															}else  if (respons.get("statuscode").toString().equalsIgnoreCase("DTX") ) {
																status = "FAILED";
															}else {
														status = "SUCCESS";
													}
													
													}
												} else {
													status = "FAILED";
												}
		
													Map<String, Object> param1 = new HashMap<String, Object>();
													param1.put("tid", tId);
													List<Rechargedetails> rechargeDetails = rechargedetailsDao.getRechargeDetailsByNamedQuery("GetRechargeDetailsByTid", param1);
													if(!rechargeDetails.isEmpty()) {
														Rechargedetails rechargedetail = rechargeDetails.get(0);
														logger_log.warn("database validation::::::::: "+rechargedetail.getValidation());
													if(rechargedetail.getValidation().equals("PENDING")) {
												if (status.equals("SUCCESS")) {
													rechargedetail.setStatus("SUCCESS");
													rechargedetailsDao.updateRechargeDetails(rechargedetail);
													/*----------------------CHARGE & COMMISSION FOR THE Utility-------------------------------*/
													
													if(userDetails.getRoleId() == 5) {
														if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
															distCharge = (pckdis.getCharge()*amount)/100;
														}else{
															distCharge = pckdis.getCharge();
														}
														if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
															sdCharge = (pcksd.getCharge()*amount)/100;
														}else{
															sdCharge = pcksd.getCharge();
														}
															if(!resellerId.equals("admin")) {
																if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
																resCharge = (pckres.getCharge()*amount)/100;
																}else{
																resCharge =	pckres.getCharge();
																}
																
															}
															//dcharge =distCharge;
															//	sCharge =sdCharge;
															dcharge = charge - distCharge;
															sCharge = distCharge - sdCharge;
															
															commissionService.updateBalance(distId, "Charge for UTILITY - "+mobileNo, "CHARGE", dcharge, "CREDIT",0);
															commissionService.updateBalance(sdId, "Charge for UTILITY - "+mobileNo, "CHARGE", sCharge, "CREDIT",0);
															if(!resellerId.equals("admin")) {
																reCharge = sdCharge - resCharge;
																commissionService.updateBalance(resellerId, "Charge for UTILITY - "+mobileNo, "Recharge UTILITY", reCharge, "CREDIT",0);
															}
															
															/*-------------------------------For earning and Surcharge ------------------*/
															// for DISTRIBUTOR 
															EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, userDetails.getWlId(), distId, 0.0, dcharge, "Charge FOR - "+mobileNo, today, currentTime);
															earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
															
															// For Super Distributor
															EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, 0.0, sCharge, "Charge FOR - "+mobileNo, today, currentTime);
															earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
															
															// For RESELLER
															if(!resellerId.equals("admin")) {
																EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, 0.0, reCharge, "Charge FOR - "+mobileNo, today, currentTime);
																earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
															}
															/*----------------------------------------------------------------------*/
													
													} else if(userDetails.getRoleId() == 4) {
														
														if(!resellerId.equals("admin")) {
															if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
															resCharge = (pckres.getCharge()*amount)/100;
															}else{
															resCharge =	pckres.getCharge();
															}
															
														}
														if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
															sdCharge = (pcksd.getCharge()*amount)/100;
														}else{
															sdCharge = pcksd.getCharge();
														}
														sCharge = charge - sdCharge;	
														System.out.println("sCharge=="+sCharge);
														commissionService.updateBalance(sdId, "Charge for UTILITY - "+mobileNo, "CHARGE", sCharge, "CREDIT",0);
														if(!resellerId.equals("admin")) {
															reCharge = sdCharge - resCharge;
															System.out.println("reCharge=="+reCharge);
															commissionService.updateBalance(resellerId, "Charge for UTILITY - "+mobileNo, "UTILITY Charge", reCharge, "CREDIT",0);
														}														
														/*-------------------------------For earning and Surcharge ------------------*/
																												
														// For Super Distributor
														EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, 0.0, sCharge, "Charge FOR - "+mobileNo, today, currentTime);
														earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
														
														// For RESELLER
														if(!resellerId.equals("admin")) {
															EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, 0.0, reCharge, "Charge FOR - "+mobileNo, today, currentTime);
															earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
														}
														/*----------------------------------------------------------------------*/
													
														} else if(userDetails.getRoleId() == 3) {

															if (!resellerId.equals("admin")) {
																if (pckres.getCharge_type()
																		.equalsIgnoreCase("PERCENTAGE"))
																	resCharge = (pckres.getCharge() * amount) / 100;
															} else {
																resCharge = pckres.getCharge();
															}
															resCharge = charge - resCharge;

															if (!resellerId.equals("admin")) {
																reCharge = sdCharge - charge;
																commissionService.updateBalance(resellerId,
																		"Charge for UTILITY - " + mobileNo,
																		"UTILITY Charge", reCharge, "CREDIT",0);
															}

															/*-------------------------------For earning and Surcharge ------------------*/

															// For RESELLER
															if (!resellerId.equals("admin")) {
																EarningSurcharge earningSurcharge3 = new EarningSurcharge(
																		4, userDetails.getWlId(), resellerId, 0.0,
																		reCharge, "Charge FOR - " + mobileNo, today,
																		currentTime);
																earningSurchargeDao
																		.insertEarningSurcharge(earningSurcharge3);
															}
															/*----------------------------------------------------------------------*/

														}
																								
													returnData.put("status", "1");
													returnData.put("message", "Bill Submit SUCCESS. Transaction Id: "+tId);
													if(source.equals("APPS")){
														returnData.put("closingBalance", cl_bal);
													}
													if(source.equals("API")){
														returnData.put("TransactionId", tId);
													}
												} else if (status.equals("FAILED")) {
													rechargedetail.setStatus("FAILED");
													rechargedetail.setValidation("FAILED");
													rechargedetailsDao.updateRechargeDetails(rechargedetail);										
													commissionService.updateBalance(userDetails.getUserId(), "REFUND for UTILITY - "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
													
													returnData.put("status", "0");
													returnData.put("message", "Bill Submit Failed. Transaction Id: "+tId);
													if(source.equals("API")){
														returnData.put("TransactionId", tId);
													}
													if(source.equals("APPS")){
														returnData.put("closingBalance", cl_bal);
													}
												}
												
												if(status.equals("SUCCESS") || status.equals("PENDING")){
													returnData.put("status", "1");
													returnData.put("message", "Bill Submit SUCCESS. Transaction Id: "+tId);
													if(source.equals("APPS")){
														returnData.put("closingBalance", cl_bal);
													}
													if(source.equals("API")){
														returnData.put("TransactionId", tId);
													}
													String utilityType = "";
													//-------------------SEND SMS TO USER ------------------------
													param = new HashMap<String, Object>();
													param.put("wlId", userDetails.getWlId());
													SmsCredential credential = smsCredentialDao.getSmsCredentialByNamedQuery("GetSmsCredentialByWlId", param).get(0);
													
													String sms = "Dear Customer, Bill Payment successful for "+operator.getServiceProvider()+" of Rs. "+amount+", your order is being process & "+operator.getServiceProvider()+" will be confirm you shortly, Thank You.";
													SMS.sendSMS2(request.get("consumerMobile"), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());
													//-----------------------------------------------------------------
												}
													}else{
														returnData.put("status", "0");
														returnData.put("message", "Recharge "+rechargedetail.getValidation()+"Transaction Id: "+tId);
														logger_log.warn("Validation is not Pending ::::::::: ");
													}
													}
		
											} else {
												returnData.put("status", "0");
												if(source.equals("APPS")){
													returnData.put("closingBalance", cl_bal);
												}
												returnData.put("message", "Bill Submit Failed. Technical Error!");
											}
										} else {
											returnData.put("status", "0");
											if(source.equals("APPS")){
												returnData.put("closingBalance", cl_bal);
											}
											returnData.put("message", "Bill Submit Failed. Technical Error!");
										}
									} else {
										returnData.put("status", "0");
										if(source.equals("APPS")){
											returnData.put("closingBalance", cl_bal);
										}
										returnData.put("message", "Bill Submit Failed. Insufficient Balance!");
									}
								}else {
									returnData.put("status", "0");
									returnData.put("message", "Bill Submit Failed Failed. Invalid Mobile Numbers!");
								}
							}else {
								returnData.put("status", "0");
								returnData.put("message", "Bill Submit Failed. Invalid Amount!");
							}
							}else {
								returnData.put("status", "0");
								returnData.put("message", "Recharge Failed. Operator is not available");
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Bill Submit Failed. Please try after Some time");
						}
					} else {
						returnData.put("status", "0");
						returnData.put("message", "Bill amount Failed. Same amount same number after 10 minutes.");
					}
						}
					}else{
						returnData.put("status", "0");
						returnData.put("message", "Insufficient Balance!");
					}
				}
				}
			} else {
				returnData.put("status", "0");
				returnData.put("message", "Please try after 10 minutes");
			}
			
		} catch (Exception e) {
			logger_log.error("eBillPayment ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	
	@Override
	public Map<String, Object> insurancePremiumPayment(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {			
			Operator operator = new Operator();
			PackageWiseChargeComm pckret=new PackageWiseChargeComm();
			PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
			PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
			PackageWiseChargeComm pckres=new PackageWiseChargeComm();
			PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
			String status = "";			
			double comm = 0.0;
			double dComm = 0.0;
			double retComm = 0.0;
			double sdComm = 0.0;
			double resComm = 0.0;
			double apiComm=0.0;
			double sdCharge = 0.0;
			double distCharge = 0.0;			
			double resCharge = 0.0;
			
			double dcharge = 0.0;
			double sCharge = 0.0;
			double reCharge = 0.0;
			
			double charge = 0.0;
			double totalAmount = 0.0;
			String resellerId = "";
			String sdId = "";
			String distId = "";		
			String response = "";
			Chargeset dCharge = null;
			if (UtilityClass.checkParameterIsNull(request)) {				
				
				if (request.get("policyHolderName") == null || request.get("policyNumber") == null
						|| request.get("amount") == null || request.get("custNumber") == null
						|| request.get("dob") == null || request.get("operator") == null	
						
						||request.get("policyHolderName").equals("") || request.get("policyNumber").equals("")
						|| request.get("amount").equals("") || request.get("custNumber").equals("")
						|| request.get("dob").equals("")|| request.get("operator").equals("")) {
					
					returnData.put("status", "0");
					returnData.put("message", "Invalid Request!");

				} else{	
					System.out.println("INCODE :: "+request.get("operator"));
					System.out.println("mobile :: "+request.get("policyNumber"));
					System.out.println("amount :: "+request.get("amount"));
					double balance=userDetails.getBalance();
					String mobileNo = request.get("policyNumber");
					double amount = Double.parseDouble(request.get("amount"));
					String source = request.get("source");	
					User user = userDao.getUserByUserId(userDetails.getUserId());
					String activestatus=user.getStatus();
					if(activestatus.equals("1")){
						returnData.put("status", "0");
						returnData.put("message", "Inactive User.");
					}else{
					parameters = new HashMap<String, String>();					
					parameters.put("mobile", mobileNo);
					parameters.put("amount", String.valueOf(amount));
					parameters.put("userId", userDetails.getUserId());
					parameters.put("date", GenerateRandomNumber.getCurrentDate());
					long timeDiff = customQueryServiceLogic.getRechargeLock(CustomSqlQuery.getRechargeLock, parameters);
					///=======
					double lockbalance=user.getLockedAmount();
					double cbal=balance-amount;
					if(lockbalance<=cbal){
						if(balance<amount)
						{
							returnData.put("status", "0");
							returnData.put("message", "Amount cannot greater than Balance!");
						}else{
					if(timeDiff > 10){
						userDetails = userDao.getUserByUserId(userDetails.getUserId());
						param = new HashMap<String, Object>();
						
						param.put("inCode", request.get("operator"));
						List<Operator> opList =  operatorDao.getOperatorByNamedQuery("getOperatorByInCode", param );				
						param = new HashMap<String, Object>();	
						
						if(!opList.isEmpty()) {
							operator = opList.get(0);
							String operatorStatus=operator.getStatus();
							System.out.println("operatorStatus==="+operatorStatus);
							if(operatorStatus.equals("available"))
							{
							// ------------------------------  User's Charge calculation for the Recharge -------------------------------------//
								pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId()) ;	
								if (userDetails.getRoleId() == 5) {
									if (pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
										charge = (pckret.getCharge() * amount) / 100;
									} else {
										charge = pckret.getCharge();
									}
								} else if (userDetails.getRoleId() == 4) {
									pckdis = commissionService.getPackagewiseCommisionOnOperator(
											userDetails.getUserId(), "Recharge", operator.getOperatorId());
									if (pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
										charge = (pckdis.getCharge() * amount) / 100;
									} else {
										charge = pckdis.getCharge();
									}

								} else if (userDetails.getRoleId() == 3) {
									pcksd = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),
											"Recharge", operator.getOperatorId());
									if (pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
										charge = (pcksd.getCharge() * amount) / 100;
									} else {
										charge = pcksd.getCharge();
									}

								}else if (userDetails.getRoleId() == 100) {
									pckapiu = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),
											"Recharge", operator.getOperatorId());
									if (pckapiu.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
										charge = (pckapiu.getCharge() * amount) / 100;
									} else {
										charge = pckapiu.getCharge();
									}

								}				
							/*-------------------------------------------------------------------------------------------------------------------*/
							String tId = "";
							/*-------------------------------------COMMISSION---------------------------------------------------------------------*/
							if(userDetails.getRoleId() == 5) {
								//Retailer Id
								//rId=userDetails.getUserId();
								// Distributor Id
								distId = userDetails.getUplineId();		
								
								// Super Distributor Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", distId);	
								sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
								
								// Reseller Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", sdId);							
								resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
								if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								retComm =(pckret.getComm()*amount)/100;
								}else{
								retComm =pckret.getComm();	
								}
								comm = retComm;
								System.out.println("reseller=="+comm);
								pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
								if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								dComm =(pckdis.getComm()*amount)/100;
								}else{
								dComm=pckdis.getComm();	
								}
								System.out.println("dComm=="+dComm);
								pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
								if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								sdComm =(pcksd.getComm()*amount)/100;
								}else{
								sdComm = pcksd.getComm();	
								}
								System.out.println("sdComm=="+sdComm);
								pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								resComm =(pckres.getComm()*amount)/100;
								}else{
								resComm =pckres.getComm();	
								}
								System.out.println("resComm=="+resComm);
								
							} else if(userDetails.getRoleId() == 4) {
								//distId = userDetails.getUserId();	
								sdId = userDetails.getUplineId();	
								
								parameters = new HashMap<String, String>();
								parameters.put("userId", sdId);							
								resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
								
								pckdis=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId()); 
								if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								dComm =(pckdis.getComm()*amount)/100;
								}else{
								dComm=pckdis.getComm();	
								}
								comm = dComm;
								System.out.println("dComm="+dComm);
								pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
								if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								sdComm =(pcksd.getComm()*amount)/100;
								}else{
								sdComm = pcksd.getComm();	
								}
								sdComm=sdComm-dComm;
								System.out.println("sdComm="+sdComm);
								if(!resellerId.equals("admin")) {
								pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								resComm =(pckres.getComm()*amount)/100;
								}else{
								resComm =pckres.getComm();	
								}
								resComm=resComm-sdComm;
								}
								System.out.println("resComm="+resComm);
								
							} else if(userDetails.getRoleId() == 3) {
								resellerId = userDetails.getUplineId();
								pcksd=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId());
								if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							     sdComm =(pcksd.getComm()*amount)/100;
								}else{
									sdComm = pcksd.getComm();	
								}
								
								System.out.println("sdComm:::::::::"+sdComm);
							}
							else if(userDetails.getRoleId() == 100) {
								pckapiu=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId());
								if(pckapiu.getComm_type().equalsIgnoreCase("PERCENTAGE")){
									apiComm =(pckapiu.getComm()*amount)/100;
								}else{
									apiComm = pckapiu.getComm();	
								}
								comm = apiComm;
								System.out.println("apiComm:::::::::"+apiComm);
								
								}
							/*---------------------------------------------------------------------------------------------------------------*/
							if (amount > 0) {						
								tId = GenerateRandomNumber.generatePtid(request.get("policyNumber"));
								if (!tId.equals("")) {
									String today = GenerateRandomNumber.getCurrentDate();	
									String currentTime = GenerateRandomNumber.getCurrentTime();
									totalAmount = RoundNumber.roundDouble((amount + charge) - comm);
									parameters = new HashMap<String, String>();
									double adOpBal = customQueryServiceLogic.getTotalbalanceForAdmin(CustomSqlQuery.getTotalbalanceForAdmin, parameters);
									RoundNumber.roundDouble(adOpBal - totalAmount);
									parameters = new HashMap<String, String>();
									parameters.put("userId", userDetails.getUserId());	
									double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters );							
									double cl_bal = RoundNumber.roundDouble(op_bal - totalAmount);							
									if (cl_bal > 0) {
										Rechargedetails rechargedetails = new Rechargedetails(userDetails.getUserId(), mobileNo, operator.getOperatorId(), op_bal, amount, charge,comm, cl_bal, tId, tId, tId, "SUCCESS", "PENDING",source , operator.getApiId(), userDetails.getRoleId(), userDetails.getWlId(), today, currentTime,request.get("ip"));
										boolean flag = rechargedetailsDao.insertRechargeDetails(rechargedetails);								
										if (flag) {
											/*Transactiondetails trsansaction = new Transactiondetails(userDetails.getUserId(), op_bal, 0.0, totalAmount, cl_bal, "Recharge to "+mobileNo, "Recharge", today, currentTime, adOpBal, adClBal, userDetails.getWlId());
											flag = transactiondetailsDao.insertTransactionDetails(trsansaction);
											userDetails.setBalance(cl_bal);
											flag = userDao.updateUser(userDetails);*/
											flag = commissionService.updateBalance(userDetails.getUserId(), "Insurance Premium to "+mobileNo, "Insurance Premium", totalAmount, "DEBIT",0);
											if (flag) {
													Insurance insurance = new Insurance(userDetails.getUserId(), tId, String.valueOf(operator.getOperatorId()), request.get("policyHolderName"), request.get("policyNumber"), request.get("dob"), amount, request.get("custNumber"), userDetails.getWlId(), "PENDING",  tId, "0", GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
													boolean flag1 = insuranceDao.insertInsurance(insurance);
													
													if(flag1){
													if (operator.getApiId() == 1) {
														status = "SUCCESS";
													} else if (operator.getApiId() == 2) {
														status = "PENDING";
													} else if (operator.getApiId() == 3) {
														status = "SUCCESS";
													} else if (operator.getApiId() == 4) {
														status = "SUCCESS";
													} else if (operator.getApiId() == 5) {
														status = "SUCCESS";
													} else if (operator.getApiId() == 6) {
														status = "SUCCESS";
													}
												} else {
													status = "FAILED";
												}
		
												if (status.equals("SUCCESS")) {
													
													/*----------------------CHARGE & COMMISSION FOR THE Utility-------------------------------*/
													
													if(userDetails.getRoleId() == 5) {
														if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
															distCharge = (pckdis.getCharge()*amount)/100;
														}else{
															distCharge = pckdis.getCharge();
														}
														if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
															sdCharge = (pcksd.getCharge()*amount)/100;
														}else{
															sdCharge = pcksd.getCharge();
														}
															if(!resellerId.equals("admin")) {
																if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
																resCharge = (pckres.getCharge()*amount)/100;
																}else{
																resCharge =	pckres.getCharge();
																}
																
															}
															//dcharge =distCharge;
															//	sCharge =sdCharge;
															dcharge = charge - distCharge;
															sCharge = distCharge - sdCharge;
															
															commissionService.updateBalance(distId, "Charge for Insurance Premium  - "+mobileNo, "CHARGE", dcharge, "CREDIT",0);
															commissionService.updateBalance(sdId, "Charge for Insurance Premium  - "+mobileNo, "CHARGE", sCharge, "CREDIT",0);
															if(!resellerId.equals("admin")) {
																reCharge = sdCharge - resCharge;
																commissionService.updateBalance(resellerId, "Charge for Insurance Premium  - "+mobileNo, "INSURANCE Charge", reCharge, "CREDIT",0);
															}
															
															/*-------------------------------For earning and Surcharge ------------------*/
															// for DISTRIBUTOR 
															EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, userDetails.getWlId(), distId, 0.0, dcharge, "Charge FOR - "+mobileNo, today, currentTime);
															earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
															
															// For Super Distributor
															EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, 0.0, sCharge, "Charge FOR - "+mobileNo, today, currentTime);
															earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
															
															// For RESELLER
															if(!resellerId.equals("admin")) {
																EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, 0.0, reCharge, "Charge FOR - "+mobileNo, today, currentTime);
																earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
															}
															/*----------------------------------------------------------------------*/
													
													} else if(userDetails.getRoleId() == 4) {
														
														if(!resellerId.equals("admin")) {
															if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
															resCharge = (pckres.getCharge()*amount)/100;
															}else{
															resCharge =	pckres.getCharge();
															}
															
														}
														if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
															sdCharge = (pcksd.getCharge()*amount)/100;
														}else{
															sdCharge = pcksd.getCharge();
														}
														sCharge = charge - sdCharge;	
														System.out.println("sCharge=="+sCharge);
														commissionService.updateBalance(sdId, "Charge for Insurance Premium - "+mobileNo, "CHARGE", sCharge, "CREDIT",0);
														if(!resellerId.equals("admin")) {
															reCharge = sdCharge - resCharge;
															System.out.println("reCharge=="+reCharge);
															commissionService.updateBalance(resellerId, "Charge for Insurance Premium - "+mobileNo, "INSURANCE Charge", reCharge, "CREDIT",0);
														}														
														/*-------------------------------For earning and Surcharge ------------------*/
																												
														// For Super Distributor
														EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, 0.0, sCharge, "Charge FOR - "+mobileNo, today, currentTime);
														earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
														
														// For RESELLER
														if(!resellerId.equals("admin")) {
															EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, 0.0, reCharge, "Charge FOR - "+mobileNo, today, currentTime);
															earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
														}
														/*----------------------------------------------------------------------*/
													
														} else if(userDetails.getRoleId() == 3) {

															if (!resellerId.equals("admin")) {
																if (pckres.getCharge_type()
																		.equalsIgnoreCase("PERCENTAGE"))
																	resCharge = (pckres.getCharge() * amount) / 100;
															} else {
																resCharge = pckres.getCharge();
															}
															resCharge = charge - resCharge;

															if (!resellerId.equals("admin")) {
																reCharge = sdCharge - charge;
																commissionService.updateBalance(resellerId,
																		"Charge for UTILITY - " + mobileNo,
																		"UTILITY Charge", reCharge, "CREDIT",0);
															}

															/*-------------------------------For earning and Surcharge ------------------*/

															// For RESELLER
															if (!resellerId.equals("admin")) {
																EarningSurcharge earningSurcharge3 = new EarningSurcharge(
																		4, userDetails.getWlId(), resellerId, 0.0,
																		reCharge, "Charge FOR - " + mobileNo, today,
																		currentTime);
																earningSurchargeDao
																		.insertEarningSurcharge(earningSurcharge3);
															}
															/*----------------------------------------------------------------------*/

														}
													rechargedetails.setStatus("SUCCESS");
													rechargedetails.setValidation("PENDING");
													rechargedetailsDao.updateRechargeDetails(rechargedetails);											
													returnData.put("status", "1");
													returnData.put("message", "Insurance Premium Submit SUCCESS. Transaction Id: "+tId);
													if(source.equals("APPS")){
														returnData.put("closingBalance", cl_bal);
													}
													if(source.equals("API")){
														returnData.put("TransactionId", tId);
													}
												} else if (status.equals("FAILED")) {
													rechargedetails.setStatus("FAILED");
													rechargedetails.setValidation("FAILED");
													rechargedetailsDao.updateRechargeDetails(rechargedetails);										
													commissionService.updateBalance(userDetails.getUserId(), "REFUND for Insurance Premium - "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
													
													returnData.put("status", "0");
													returnData.put("message", "Insurance Premium Submit Failed. Transaction Id: "+tId);
													if(source.equals("API")){
														returnData.put("TransactionId", tId);
													}
													if(source.equals("APPS")){
														returnData.put("closingBalance", cl_bal);
													}
												}
												
												if(status.equals("SUCCESS") || status.equals("PENDING")){
													returnData.put("status", "1");
													returnData.put("message", "Insurance Premium Submit SUCCESS. Transaction Id: "+tId);
													if(source.equals("APPS")){
														returnData.put("closingBalance", cl_bal);
													}
													if(source.equals("API")){
														returnData.put("TransactionId", tId);
													}
													String utilityType = "";
													//-------------------SEND SMS TO USER ------------------------
													param = new HashMap<String, Object>();
													param.put("wlId", userDetails.getWlId());
													SmsCredential credential = smsCredentialDao.getSmsCredentialByNamedQuery("GetSmsCredentialByWlId", param).get(0);
												//	List<SMSApiparameters> params =  smsapiparamsdao.getAllSMSApiparameters();
													String sms = "Dear Customer, Insurance Premium Payment successful for "+operator.getServiceProvider()+" of Rs. "+amount+", your order is being process & "+operator.getServiceProvider()+" will be confirm you shortly, Thank You.";
													SMS.sendSMS2(request.get("custNumber"), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());

													//new SMS().sendSMS(request.get("custNumber"), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword(),params);
													//-----------------------------------------------------------------
												}
		
											} else {
												returnData.put("status", "0");
												if(source.equals("APPS")){
													returnData.put("closingBalance", cl_bal);
												}
												returnData.put("message", "Insurance Premium Submit Failed. Technical Error!");
											}
										} else {
											returnData.put("status", "0");
											if(source.equals("APPS")){
												returnData.put("closingBalance", cl_bal);
											}
											returnData.put("message", "Insurance Premium Submit Failed. Technical Error!");
										}
									} else {
										returnData.put("status", "0");
										if(source.equals("APPS")){
											returnData.put("closingBalance", cl_bal);
										}
										returnData.put("message", "Insurance Premium Submit Failed. Insufficient Balance!");
									}
								}else {
									returnData.put("status", "0");
									returnData.put("message", "Insurance Premium Submit Failed. Invalid Mobile Numbers!");
								}
							}else {
								returnData.put("status", "0");
								returnData.put("message", "Insurance Premium Submit Failed. Invalid Amount!");
							}
							}else {
								returnData.put("status", "0");
								returnData.put("message", "Recharge Failed. Operator is not available");
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Insurance Premium Submit Failed. Please try after Some time");
						}
					} else {
						returnData.put("status", "0");
						returnData.put("message", "Insurance Premium Failed. Same amount same number after 10 minutes.");
					}
						}
					}else{
						returnData.put("status", "0");
						returnData.put("message", "Insufficient Balance!");
					}
					}
				}
			} else {
				returnData.put("status", "0");
				returnData.put("message", "Failed! Please try after 10 minutes");
			}
			
		} catch (Exception e) {
			logger_log.error("Insurance Premium ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> agencyrequest(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		Operator operator = new Operator();
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double charge=0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		String rId="";
		double discharge=0.0;
		double spdischarge=0.0;
		double rescharge=0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		try{
			
			
			param = new HashMap<String, Object>();
			
			param.put("serviceProvider", "AGENT FEES");
			List<Operator> opList =  operatorDao.getOperatorByNamedQuery("getOperatorByName", param );
			if((!opList.isEmpty())) {
				operator=opList.get(0);	
			
				if (userDetails.getRoleId() == 5) {
					pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Pan",operator.getOperatorId()) ;
					charge = pckret.getCharge();
				}else if (userDetails.getRoleId() == 4) {
					pckdis = commissionService.getPackagewiseCommisionOnOperator(
							userDetails.getUserId(), "Pan", operator.getOperatorId());
					
						charge = pckdis.getCharge();
				}else if (userDetails.getRoleId() == 3) {
					pcksd = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),
							"Pan", operator.getOperatorId());
					
						charge = pcksd.getCharge();
				}
				//balance check
				if(userDetails.getBalance()>charge){
				
			
			String today = GenerateRandomNumber.getCurrentDate();	
			String currentTime = GenerateRandomNumber.getCurrentTime();
			String restUrl="https://www.apivendor.com/creatAgent";
			//String restUrl="http://localhost:8080/apiVendor/creatAgent";
			HttpClient httpclient = new DefaultHttpClient();
			  HttpPost httppost = new HttpPost(restUrl);
			  ArrayList<NameValuePair> postParameters= new ArrayList<NameValuePair>();
			  postParameters.add(new BasicNameValuePair("psaname", request.get("psaname")));
			   postParameters.add(new BasicNameValuePair("location", request.get("location")));
			   postParameters.add(new BasicNameValuePair("pincode", request.get("pincode")));
			   postParameters.add(new BasicNameValuePair("state", request.get("state")));
			   postParameters.add(new BasicNameValuePair("phone1", request.get("phone1")));
			   postParameters.add(new BasicNameValuePair("phone2", request.get("phone2")));
			   postParameters.add(new BasicNameValuePair("emailid", request.get("emailid"))); 
			   postParameters.add(new BasicNameValuePair("pan", request.get("pan")));
			   postParameters.add(new BasicNameValuePair("dob", request.get("dob")));
			   postParameters.add(new BasicNameValuePair("aadhaar", request.get("aadhaar")));
			   postParameters.add(new BasicNameValuePair("UMobileNo", UMobileNo));
			   postParameters.add(new BasicNameValuePair("Password", Password));
			   postParameters.add(new BasicNameValuePair("district", request.get("district")));
			   httppost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
			    ResponseHandler<String> responseHandler=new BasicResponseHandler();
		        String responseBody = httpclient.execute(httppost, responseHandler);
		        JSONObject response1=new JSONObject(responseBody);
		        System.out.println("requestagentr RESPONSE:::::::::::::"+responseBody);
		        if(response1.get("status").equals("0")){
		        	 returnData.put("message", response1.get("message"));
		        	 returnData.put("status", "0");
		        	 
		        }else{
		        	 returnData.put("message", response1.get("message"));
		        	 returnData.put("status", "1");
		        	 returnData.put("requestid", response1.get("requestid"));
		        	 String panuserid=(String) response1.get("userId");
		        	 String requestid=(String) response1.get("requestid");
		        	 CreateAgent agent=new CreateAgent(UMobileNo,Password,request.get("psaname"),request.get("location"),request.get("pincode"),request.get("phone1"),request.get("phone2"),request.get("emailid"),request.get("pan"),request.get("dob"),request.get("aadhaar"),today,currentTime,panuserid,panuserid,requestid,request.get("state"),"SUCCESS",userDetails.getUserId(),request.get("district"));
		        	boolean flag =createagent.insertagent(agent);
		        	if(flag){
		        		param = new HashMap<String, Object>();
		    			param.put("wlId", userDetails.getWlId());
		    			SmsCredential credential = smsCredentialDao.getSmsCredentialByNamedQuery("GetSmsCredentialByWlId", param).get(0);
		    			String sms = "Congratulation, Your UTIITSL PSA Account Has been Created.Your User Name is :"+panuserid+" & Password:"+panuserid+"";
		    			SMS.sendSMS2(userDetails.getMobile(), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());
		    			//new SMS().sendSMS(userDetails.getMobile(), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword(),params);
		    			
		        		// ------------------------------  User's Charge calculation for the Recharge -------------------------------------//
		        		System.out.println("operator.getOperatorId()::::"+operator.getOperatorId());
						
							if (userDetails.getRoleId() == 5) {
								pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Pan",operator.getOperatorId()) ;
								System.out.println("pckret::::::"+pckret);
								charge = pckret.getCharge();
								System.out.println("charge====="+charge);
								distId = userDetails.getUplineId();
								pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Pan",operator.getOperatorId()); 
								discharge=pckdis.getCharge();
								// Super Distributor Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", distId);	
								sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
								pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Pan",operator.getOperatorId());
								spdischarge=pcksd.getCharge();
								// Reseller Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", sdId);							
								resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
								if(!resellerId.equals("admin")) {
									pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Pan",operator.getOperatorId());
									rescharge=pckres.getCharge();
								}
								
								if(discharge==0){dcharge=0;}
								else{dcharge = charge - discharge;}
								
								if(spdischarge==0){sCharge=0;}
								else{sCharge = discharge - spdischarge;}
								
								if(rescharge==0){reCharge=0;}
								else{
								reCharge = spdischarge - rescharge;
								}
								commissionService.updateBalance(userDetails.getUserId(), "Charge for Pan Agent Request", "CHARGE", charge, "DEBIT",0);
								commissionService.updateBalance(distId, "Charge for Pan Agent Request", "CHARGE", dcharge, "CREDIT",0);
								commissionService.updateBalance(sdId, "Charge for Pan Agent Request", "CHARGE", sCharge, "CREDIT",0);
								
								if(!resellerId.equals("admin")) {
									commissionService.updateBalance(resellerId, "Charge for Pan Agent Request", "Pan Charge", reCharge, "CREDIT",0);
								}
								
								
								
							} else if (userDetails.getRoleId() == 4) {
								pckdis = commissionService.getPackagewiseCommisionOnOperator(
										userDetails.getUserId(), "Pan", operator.getOperatorId());
								
									charge = pckdis.getCharge();
									
									sdId = userDetails.getUplineId();
									parameters = new HashMap<String, String>();
									parameters.put("userId", sdId);							
									resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
									pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Pan",operator.getOperatorId());
									spdischarge=pcksd.getCharge();
									
									if(!resellerId.equals("admin")) {
										pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Pan",operator.getOperatorId());
										rescharge=pckres.getCharge();
									}
									if(spdischarge==0){sCharge=0;}
									else{sCharge = charge - spdischarge;}
									if(rescharge==0){reCharge=0;}
									else{
									reCharge = spdischarge - rescharge;
									}
									commissionService.updateBalance(userDetails.getUserId(), "Charge for Pan Agent Request", "CHARGE", charge, "DEBIT",0);
									commissionService.updateBalance(sdId, "Charge for Pan Agent Request", "CHARGE", sCharge, "CREDIT",0);
									if(!resellerId.equals("admin")) {
										commissionService.updateBalance(resellerId, "Charge for Pan Agent Request", "Pan Charge", reCharge, "CREDIT",0);
									}

							} else if (userDetails.getRoleId() == 3) {
								pcksd = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),
										"Pan", operator.getOperatorId());
								
									charge = pcksd.getCharge();
									
									resellerId = userDetails.getUplineId();
									commissionService.updateBalance(userDetails.getUserId(), "Charge for Pan Agent Request", "CHARGE", charge, "DEBIT",0);
									if(!resellerId.equals("admin")) {
										pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Pan",operator.getOperatorId());
										rescharge=pckres.getCharge();
										reCharge = charge - rescharge;
										commissionService.updateBalance(resellerId, "Charge for Pan Agent Request", "Pan Charge", reCharge, "CREDIT",0);
									}
									
							}
					/*-------------------------------------------------------------------------------------------------------------------*/
							
		        	}
		        	
		        }
				}else{
					returnData.put("status", "0");
					returnData.put("message", "DoNot Have Sufficient Balance ");
				}
			}else {
				returnData.put("status", "0");
				returnData.put("message", "Operator is not available");
			}
		       
		}catch(Exception e){
			logger_log.error(e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> couponrequest(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		Operator operator = new Operator();
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double charge=0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		String rId="";
		double discharge=0.0;
		double spdischarge=0.0;
		double rescharge=0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		try{
			param = new HashMap<String, Object>();
			
			param.put("serviceProvider", "COUPON FEES");
			List<Operator> opList =  operatorDao.getOperatorByNamedQuery("getOperatorByName", param );
			if((!opList.isEmpty())) {
				operator=opList.get(0);	
			String couponcount=	request.get("totalcoupan");
			double totalcoupon=Double.parseDouble(couponcount);
			
			String today = GenerateRandomNumber.getCurrentDate();	
			String currentTime = GenerateRandomNumber.getCurrentTime();
			String restUrl="https://www.apivendor.com/couponrequest";
			HttpClient httpclient = new DefaultHttpClient();
			  HttpPost httppost = new HttpPost(restUrl);
			  ArrayList<NameValuePair> postParameters= new ArrayList<NameValuePair>();
			  postParameters.add(new BasicNameValuePair("totalcoupan", request.get("totalcoupan")));
			   postParameters.add(new BasicNameValuePair("agentPassword", request.get("userid")));
			   postParameters.add(new BasicNameValuePair("userid", request.get("userid")));
			   postParameters.add(new BasicNameValuePair("UMobileNo", UMobileNo));
			   postParameters.add(new BasicNameValuePair("Password", Password));
			   httppost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
			    ResponseHandler<String> responseHandler=new BasicResponseHandler();
		        String responseBody = httpclient.execute(httppost, responseHandler);
		        JSONObject response1=new JSONObject(responseBody);
		        System.out.println("couponrequest RESPONSE:::::::::::::"+responseBody);
		        if(response1.get("status").equals("0")){
		        	 returnData.put("message", response1.get("message"));
		        	 returnData.put("status", "0");
		        	 
		        }else{
		        	 returnData.put("message", response1.get("message"));
		        	 returnData.put("status", "1");
		        	 returnData.put("requestid", response1.get("requestid"));
		        	 String requestid=(String) response1.get("requestid");
		        	CouponRequest cprequest=new CouponRequest(UMobileNo, Password, request.get("userid"), request.get("userid"), request.get("totalcoupan"), requestid, "SUCCESS", today, currentTime,userDetails.getUserId());
		        	boolean flag =cponrequest.insertCouponRequest(cprequest);
		        	if(flag){
		        		// ------------------------------  User's Charge calculation for the Recharge -------------------------------------//
						
						pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Pan",operator.getOperatorId()) ;
							if (userDetails.getRoleId() == 5) {
								charge = pckret.getCharge();
								charge=charge*totalcoupon;
								System.out.println("coupon charge::::"+charge);
								distId = userDetails.getUplineId();
								pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Pan",operator.getOperatorId()); 
								discharge=pckdis.getCharge();
								discharge=discharge*totalcoupon;
								System.out.println("coupon charge:discharge:::"+discharge);
								// Super Distributor Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", distId);	
								sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
								pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Pan",operator.getOperatorId());
								spdischarge=pcksd.getCharge();
								spdischarge=spdischarge*totalcoupon;
								System.out.println("coupon charge:spdischarge:::"+spdischarge);
								// Reseller Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", sdId);							
								resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
								if(!resellerId.equals("admin")) {
									pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Pan",operator.getOperatorId());
									rescharge=pckres.getCharge();
									rescharge=rescharge*totalcoupon;
									System.out.println("coupon charge:rescharge:::"+rescharge);
								}
								
								if(discharge==0){dcharge=0;}
								else{dcharge = charge - discharge;}
								
								if(spdischarge==0){sCharge=0;}
								else{sCharge = discharge - spdischarge;}
								
								if(rescharge==0){reCharge=0;}
								else{
								reCharge = spdischarge - rescharge;
								}
								commissionService.updateBalance(userDetails.getUserId(), "Charge for Pan Coupon Request", "CHARGE", charge, "DEBIT",0);
								commissionService.updateBalance(distId, "Charge for Pan Coupon Request", "CHARGE", dcharge, "CREDIT",0);
								commissionService.updateBalance(sdId, "Charge for Pan Coupon Request", "CHARGE", sCharge, "CREDIT",0);
								
								if(!resellerId.equals("admin")) {
									commissionService.updateBalance(resellerId, "Charge for Pan Coupon Request", "Pan Charge", reCharge, "CREDIT",0);
								}
								
								
								
							} else if (userDetails.getRoleId() == 4) {
								pckdis = commissionService.getPackagewiseCommisionOnOperator(
										userDetails.getUserId(), "Pan", operator.getOperatorId());
								
									charge = pckdis.getCharge();
									charge=charge*totalcoupon;
									sdId = userDetails.getUplineId();
									parameters = new HashMap<String, String>();
									parameters.put("userId", sdId);							
									resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
									pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Pan",operator.getOperatorId());
									spdischarge=pcksd.getCharge();
									spdischarge=spdischarge*totalcoupon;
									if(!resellerId.equals("admin")) {
										pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Pan",operator.getOperatorId());
										rescharge=pckres.getCharge();
										rescharge=rescharge*totalcoupon;
									}
									if(spdischarge==0){sCharge=0;}
									else{sCharge = charge - spdischarge;}
									if(rescharge==0){reCharge=0;}
									else{
									reCharge = spdischarge - rescharge;
									}
									commissionService.updateBalance(userDetails.getUserId(), "Charge for Pan Coupon Request", "CHARGE", charge, "DEBIT",0);
									commissionService.updateBalance(sdId, "Charge for Pan Coupon Request", "CHARGE", sCharge, "CREDIT",0);
									if(!resellerId.equals("admin")) {
										commissionService.updateBalance(resellerId, "Charge for Pan Coupon Request", "Pan Charge", reCharge, "CREDIT",0);
									}

							} else if (userDetails.getRoleId() == 3) {
								pcksd = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),
										"Pan", operator.getOperatorId());
								
									charge = pcksd.getCharge();
									charge=charge*totalcoupon;
									resellerId = userDetails.getUplineId();
									commissionService.updateBalance(userDetails.getUserId(), "Charge for Pan Coupon Request", "CHARGE", charge, "DEBIT",0);
									if(!resellerId.equals("admin")) {
										pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Pan",operator.getOperatorId());
										rescharge=pckres.getCharge();
										rescharge=rescharge*totalcoupon;
										
										if(rescharge==0){reCharge=0;}
										else{
										reCharge = charge - rescharge;
										}
										commissionService.updateBalance(resellerId, "Charge for Pan Coupon Request", "Pan Charge", reCharge, "CREDIT",0);
									}
									
							}
					/*-------------------------------------------------------------------------------------------------------------------*/
							
		        	}
		        	
		        }
			}else {
				returnData.put("status", "0");
				returnData.put("message", "Operator is not available");
			}
		       
		}catch(Exception e){
			logger_log.error(e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> nsdlPanadd(Map<String, String> request, User userDetails) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		Operator operator = new Operator();
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double charge=0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		String rId="";
		double discharge=0.0;
		double spdischarge=0.0;
		double rescharge=0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		System.out.println(request);
		if(request.get("type").equals("0")){
			returnData.put("status", "0");
			returnData.put("message", "Select Application Type");
		}else{
			if(request.get("idproof").equals("0")){
				returnData.put("status", "0");
				returnData.put("message", "Select ID Proof Type");
			}else{
				if(request.get("sex").equals("0")){
					returnData.put("status", "0");
					returnData.put("message", "Select Sex Type");
				}else{
					String oldpanno="-";
					if(request.get("type").equals("CORRECTION")){
						oldpanno=request.get("oldpan");
					}
					param = new HashMap<String, Object>();
					
					param.put("serviceProvider", "NSDL");
					List<Operator> opList =  operatorDao.getOperatorByNamedQuery("getOperatorByName", param );
					if((!opList.isEmpty())) {
						operator=opList.get(0);	
						param = new HashMap<String, Object>();
					param.put("user_id", userDetails.getUserId());
					param.put("service_type", "Pan");
					List<AssignedPackage> list = assignedPackage
							.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", param);
					if (list.size() > 0) {
						if (userDetails.getRoleId() == 5) {
							pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Pan",operator.getOperatorId()) ;
							charge = pckret.getCharge();
						}else if (userDetails.getRoleId() == 4) {
							pckdis = commissionService.getPackagewiseCommisionOnOperator(
									userDetails.getUserId(), "Pan", operator.getOperatorId());
							
								charge = pckdis.getCharge();
						}else if (userDetails.getRoleId() == 3) {
							pcksd = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),
									"Pan", operator.getOperatorId());
							
								charge = pcksd.getCharge();
						}
						double CLBL=userDetails.getBalance()-charge;
						if(CLBL>=userDetails.getLockedAmount()){
							System.out.println(":::::::::::::::::");
							String custonmf="";
							if(request.get("custonamef").isEmpty()){
								custonmf="0";
							}else{
								custonmf=request.get("custonamef");
							}
							System.out.println("custonmf::"+custonmf);
							String custonmm="";
							if(request.get("custonamem").equals("")){
								custonmm="0";
							}else{
								custonmm=request.get("custonamem");
							}
							System.out.println(":::::::::::::::::");
							String custonml="";
							if(request.get("custonamel").isEmpty()){
								custonml="0";
							}else{
								custonml=request.get("custonamel");
							}
							System.out.println(":::::::::::::::::");
							String fatherf="";
							if(request.get("fathernamef").isEmpty()){
								fatherf="0";
							}else{
								fatherf=request.get("fathernamef");
							}
							System.out.println(":::::::::::::::::");
							String fatherm="";
							if(request.get("fathernamem").isEmpty()){
								fatherm="0";
							}else{
								fatherm=request.get("fathernamem");
							}
							System.out.println(":::::::::::::::::");
							String fatherl="";
							if(request.get("fathernamel").isEmpty()){
								fatherl="0";
							}else{
								fatherl=request.get("fathernamel");
							}
							System.out.println(":::::::::::::::::");
							
							parameters = new HashMap<String, String>();
							parameters.put("userId", userDetails.getUserId());	
							double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters );	
							double clbl=op_bal-charge;
							
					String date=GenerateRandomNumber.getCurrentDate();
					String time=GenerateRandomNumber.getCurrentTime();
					String Invoiceno=GenerateRandomNumber.referenceNO();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
					SimpleDateFormat formatter1 = new SimpleDateFormat("dd/mm/yyyy");
					Calendar c = Calendar.getInstance();
					c.setTime(formatter.parse(request.get("dob")));
					String dob= formatter1.format(c.getTime());
					NSDLPan nepan=new NSDLPan(userDetails.getUserId(),custonmf, request.get("mobile"), request.get("email"), dob, request.get("sex"), fatherf, request.get("add1"), "0", request.get("pincode"), request.get("idproof"), request.get("idno"), "PENDING", date, time, "-", "-", Invoiceno, "-",request.get("type"),custonmm,custonml,request.get("state"),fatherm,fatherl,oldpanno,userDetails.getName(),op_bal,clbl,request.get("namecard"),request.get("district"),request.get("addressproof"),request.get("dobproof"),"-","-","","");
					boolean flag=NSDLpanDao.insertNSDLPan(nepan);
					param=new HashMap<String, Object>();
					param.put("date", date);
					param.put("time", time);
					param.put("mobile", request.get("mobile"));
					param.put("email", request.get("email"));
					List<NSDLPan> nlist=NSDLpanDao.getNSDLPanByNamedQuery("getNSDLPanPendingReport", param);
					if(flag){
	// ------------------------------  User's Charge calculation for the Recharge -------------------------------------//
						
						pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Pan",operator.getOperatorId()) ;
							if (userDetails.getRoleId() == 5) {
								charge = pckret.getCharge();
								System.out.println("coupon charge::::"+charge);
								distId = userDetails.getUplineId();
								pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Pan",operator.getOperatorId()); 
								discharge=pckdis.getCharge();
								System.out.println("coupon charge:discharge:::"+discharge);
								// Super Distributor Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", distId);	
								sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
								pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Pan",operator.getOperatorId());
								spdischarge=pcksd.getCharge();
								System.out.println("coupon charge:spdischarge:::"+spdischarge);
								// Reseller Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", sdId);							
								resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
								if(!resellerId.equals("admin")) {
									pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Pan",operator.getOperatorId());
									rescharge=pckres.getCharge();
									System.out.println("coupon charge:rescharge:::"+rescharge);
								}
								
								if(discharge==0){dcharge=0;}
								else{dcharge = charge - discharge;}
								
								if(spdischarge==0){sCharge=0;}
								else{sCharge = discharge - spdischarge;}
								
								if(rescharge==0){reCharge=0;}
								else{
								reCharge = spdischarge - rescharge;
								}
								commissionService.updateBalance(userDetails.getUserId(), "Charge for NSDL Pan Charge", "CHARGE", charge, "DEBIT",0);
								commissionService.updateBalance(distId, "Charge for NSDL Pan Charge", "CHARGE", dcharge, "CREDIT",0);
								commissionService.updateBalance(sdId, "Charge for NSDL Pan Charge", "CHARGE", sCharge, "CREDIT",0);
								
								if(!resellerId.equals("admin")) {
									commissionService.updateBalance(resellerId, "Charge for NSDL Pan Charge", "Pan Charge", reCharge, "CREDIT",0);
								}
								
								
								
							} else if (userDetails.getRoleId() == 4) {
								pckdis = commissionService.getPackagewiseCommisionOnOperator(
										userDetails.getUserId(), "Pan", operator.getOperatorId());
								
									charge = pckdis.getCharge();
									sdId = userDetails.getUplineId();
									parameters = new HashMap<String, String>();
									parameters.put("userId", sdId);							
									resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
									pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Pan",operator.getOperatorId());
									spdischarge=pcksd.getCharge();
									if(!resellerId.equals("admin")) {
										pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Pan",operator.getOperatorId());
										rescharge=pckres.getCharge();
									}
									if(spdischarge==0){sCharge=0;}
									else{sCharge = charge - spdischarge;}
									if(rescharge==0){reCharge=0;}
									else{
									reCharge = spdischarge - rescharge;
									}
									commissionService.updateBalance(userDetails.getUserId(), "Charge for NSDL Pan Charge", "CHARGE", charge, "DEBIT",0);
									commissionService.updateBalance(sdId, "Charge for NSDL Pan Charge", "CHARGE", sCharge, "CREDIT",0);
									if(!resellerId.equals("admin")) {
										commissionService.updateBalance(resellerId, "Charge for NSDL Pan Charge", "Pan Charge", reCharge, "CREDIT",0);
									}

							} else if (userDetails.getRoleId() == 3) {
								pcksd = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),
										"Pan", operator.getOperatorId());
								
									charge = pcksd.getCharge();
									resellerId = userDetails.getUplineId();
									commissionService.updateBalance(userDetails.getUserId(), "Charge for NSDL Pan Charge", "CHARGE", charge, "DEBIT",0);
									if(!resellerId.equals("admin")) {
										pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Pan",operator.getOperatorId());
										rescharge=pckres.getCharge();
										
										if(rescharge==0){reCharge=0;}
										else{
										reCharge = charge - rescharge;
										}
										commissionService.updateBalance(resellerId, "Charge for NSDL Pan Charge", "Pan Charge", reCharge, "CREDIT",0);
									}
									
							}
					/*-------------------------------------------------------------------------------------------------------------------*/
						returnData.put("nlist", nlist);	
						returnData.put("status", "1");
						returnData.put("message", "Update Successfully");
					}else{
						returnData.put("status", "0");
						returnData.put("message", "Update Failed");
					}
					}else{
						returnData.put("status", "0");
						returnData.put("message", "Donot have Sufficient Balance");
					}
					}else{
						returnData.put("status", "0");
						returnData.put("message", "Please Set Pan Package");
					}
				}else{
					returnData.put("status", "0");
					returnData.put("message", "NSDL operator Not Availabel");
				}
				}
			}
		}
		
		
		return returnData;	
	}
	
	@Override
	public Map<String, Object> nsdlEdit(Map<String, String> request,byte[] bytesImage,byte[] bytesSignature,byte[] bytesFormFont,byte[] bytesFormBack,byte[] bytesProofOfIdentity) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		
		/*NSDLPan list=NSDLpanDao.getNSDLPanById(Integer.parseInt(request.get("id")));
		NSDLPanAttachment nsdl=new NSDLPanAttachment(list.getUserId(), list.getInvoiceno(), list.getName(), bytesImage, bytesSignature, bytesFormFont, bytesFormBack, bytesProofOfIdentity);
		boolean flag=NSDLPanAttachmentDao.insertNSDLPanAttachment(nsdl);
		if(flag){
			returnData.put("status", "1");
			returnData.put("message", "Update Successfully");
		}else{
			returnData.put("status", "0");
			returnData.put("message", "Update Failed");
		}*/
		return returnData;
	}
	
	@Override
	public Map<String, Object> getNSDLASKDetails(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		System.out.println(request);
		AckSlipAttchment list=AckSlipAttachmentDao.getAckSlipAttchmentByApId(request.get("ackno"));
	
		String acktype[]=list.getAcktype().split("/");
		returnData.put("list", list);
		returnData.put("acknm", list.getAckno()+"."+acktype[1]);
		
		
		return returnData;	
	}
	
	@Override
	public Map<String, Object> getapplication(Map<String, String> request, User userDetails){
		Map<String, Object> returnData = new HashMap<String, Object>();
		String msg="";
		if(request.get("type").equals("CORRECTION")){
			msg="<div class='form-group form-float'>"
					+ "<label class='form-label'>Old Pan NO</label>"
					+ "<div class='form-line'>"
					+ "<input type='text' class='form-control' ng-model='nsdl.oldpan' ng-change='nsdl.oldpan=nsdl.oldpan.toUpperCase();'> "
					+ "</div>"
					+ "</div>";
		}
		returnData.put("message", msg);
		return returnData;
	
	}
	
	
	@Override
	public Map<String, Object> editnsdlpandt(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		System.out.println(request);
		NSDLPan list=NSDLpanDao.getNSDLPanById(Integer.parseInt(request.get("id")));
		list.setAdd1(request.get("add1"));
		list.setDistrict(request.get("district"));
		list.setName(request.get("name"));
		list.setMiddlename(request.get("middlename"));
		list.setLastname(request.get("lastname"));
		list.setEmail(request.get("email"));
		list.setDob(request.get("dob"));
		list.setSex(request.get("sex"));
		list.setFathername(request.get("fathername"));
		list.setFathernamem(request.get("fathernamem"));
		list.setFathernamel(request.get("fathernamel"));
		list.setPin(request.get("pin"));
		list.setState(request.get("state"));
		list.setNamecard(request.get("namecard"));
		list.setProoftype(request.get("prooftype"));
		list.setIdno(request.get("idno"));
		list.setAddressproof(request.get("addressproof"));
		list.setDobproof(request.get("dobproof"));
		list.setStatus("PENDING");
		boolean flag=NSDLpanDao.updateNSDLPan(list);
		if(flag){
			returnData.put("status", "1");
			returnData.put("message", "Update Successful");
		}else{
			returnData.put("status", "0");
			returnData.put("message", "Update Failed");
		}
		return returnData;	
	}



	@Override
	public Map<String, Object> customholder(Map<String, String> request,User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		String msg="";
		boolean flag = false;
		System.out.println("request::::::::::::::::::::::"+request);
		
		try {
			String [] wallet=request.get("carddetail").split(",");
			
			
			System.out.println(wallet[0]);
			System.out.println(wallet[1]);
			parameters = new HashMap<String, String>();
			double adOpBal = customQueryServiceLogic.getTotalbalanceForAdmin(CustomSqlQuery.getTotalbalanceForAdmin, parameters);
			
			//double charge=Double.parseDouble(request.get("amount"))-10;
		//	double amount=Double.parseDouble(request.get("amount"))-charge;
			RoundNumber.roundDouble(adOpBal - Double.parseDouble(request.get("amount")));
			parameters = new HashMap<String, String>();
			parameters.put("userId", userDetails.getUserId());	
			double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters );	
			
			System.out.println("op_bal"+op_bal);
			double cl_bal = RoundNumber.roundDouble(op_bal - Double.parseDouble(request.get("amount")));
			if (cl_bal > 0) {
			flag = commissionService.updateBalance(userDetails.getUserId(), "Add money to card"+wallet[1], "Money to Card", Double.parseDouble(request.get("amount")), "DEBIT",0);
			CardWalletRequest cardapplicant=new CardWalletRequest(wallet[1],wallet[0],userDetails.getUserName(),Double.parseDouble(request.get("amount")),10,"PENDING","",GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime(),wallet[2]);
			flag =CardWalletRequestDao.insertCardholder(cardapplicant);
			if (flag) {
				returnData.put("status","1");
				returnData.put("message", "Success");
				
				
			}else {
				msg = "information submit failed";
				returnData.put("status", "0");
				returnData.put("message", msg);
			}
			}else{
			returnData.put("status","1");
			returnData.put("message", "Insufficient Balance");	
			}
			
		}catch (Exception e) {
			logger_log.error(" Application:::::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
			return returnData;
	}



	@Override
	public Map<String, Object> mycardlist(User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		String status = "";
		boolean flag=false;
		String pending = "";
		String msg="";
		try {
			param.put("status","SUCCESS");
			param.put("username",userDetails.getUserName());
			List<Ezpayapplicationform> mycardlist=applicatiodao.getEzpayapplicationformByNamedQuery("getcardholdernamebystatus",param);
			if(!mycardlist.isEmpty()){
				returnData.put("list",mycardlist);
				returnData.put("status","1");
			}else{
				returnData.put("status","0");
				returnData.put("message", "NO DATA AVailABLE");
				
			}
			
		}catch (Exception e) {
			logger_log.error(" Mycardlist:::::::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		
		return returnData;
	}



	@Override
	public Map<String, Object> payUWallet(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		boolean flag=false;
		try {
		String ORDER_ID=GenerateRandomNumber.generatePtid(userDetails.getMobile());
		String amount=request.get("amount");
		
	
		String productinfo="Wallet";
		String hashSequence = api_key+"|"+ORDER_ID+"|"+amount+"|"+productinfo+"|"+userDetails.getName()+"|"+userDetails.getEmail()+"|"+""+"|"+""+"|"+""+"|"+""+"|"+""+"|"+""+"|"+""+"|"+""+"|"+""+"|"+""+"|"+salt;
		
		
		System.out.println("checksum:::::::::::::::::"+hashSequence);
		String checksum=HashGenerationAgreepay.getHashCodeFromString(hashSequence);
		logger_log.warn("checksum:::::::::::::::::"+checksum);
		session.setAttribute("hash",checksum);
		session.setAttribute("ORDER_ID",ORDER_ID);
		session.setAttribute("amount",amount);
		PaytmRequest pay=new PaytmRequest(ORDER_ID, Double.parseDouble(amount),"PENDING" , userDetails.getUserId(), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),ORDER_ID,"");
		flag=paytmrequestdao.insertPaytmRequest(pay);
		if(flag) {
		session.setAttribute("hash",checksum);
		returnData.put("nextPage","PayU");
		}else {
			session.setAttribute("hash",checksum);
		returnData.put("nextPage","home");	
		}
		}catch (Exception e) {
			logger_log.error("payUWallet::::::::::::::: "+e);
		}
		
		return returnData;
	}
/*
	// Wallet Refill start
	@Override
	public Map<String, Object> walletRefillRequest(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag = false;
		double toOpBal = 0.0;
		double toClBal = 0.0;
		double fromOpBal = 0.0;
		double fromClBal = 0.0;
		int tokenno = 0;
		String msg = "";
		// String status = "0";
		try {

			Map<String, String> parameters = new HashMap<String, String>();
			Map<String, String> param = new HashMap<String, String>();
			String transaction_id = GenerateRandomNumber.generateIPtid(userDetails.getMobile());
			String amount = request.get("amount");
			Double walletRefillAmount = Double.parseDouble(amount);

			String Date=GenerateRandomNumber.getCurrentDate();
			String Time=GenerateRandomNumber.getCurrentTime();
			if (UtilityClass.checkParameterIsNull(request)) {

				if (walletRefillAmount > 0.0) {

					String today = GenerateRandomNumber.getCurrentDate();
					String currentTime = GenerateRandomNumber.getCurrentTime();
					parameters = new HashMap<String, String>();
					parameters.put("fromId", "admin");
					parameters.put("toId", userDetails.getUserId());
					parameters.put("transferAmount", request.get("amount"));
					parameters.put("date", GenerateRandomNumber.getCurrentDate());
					param = new HashMap<String, String>();
					param.put("userId", userDetails.getUserId());
					toOpBal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser,param);
					toClBal = toOpBal + walletRefillAmount;
					TreeMap<String, Object> walletRefillRequest = new TreeMap<String, Object>();
					walletRefillRequest.put("cName", "Encore Digitech Pvt Ltd");
					walletRefillRequest.put("cEmail", "tusharkanti.dutta@edpl.info");
					walletRefillRequest.put("cMobile", "8240593169");
					walletRefillRequest.put("cBillAdd", "3 canal Street , loknath apartment , 1st floor , kolkata - 700014");
					walletRefillRequest.put("cAmount", walletRefillAmount);
					walletRefillRequest.put("tokenNumber", tokennumberForApiVendor);
					walletRefillRequest.put("callback", "http://localhost:8080/EncoreDigi2021/paymentgatewayredirect");
					walletRefillRequest.put("transaction_id", transaction_id);
					
					MultivaluedMap formData = new MultivaluedMapImpl();
					formData.add("cName", "Encore Digitech Pvt Ltd");
					formData.add("cEmail", "tusharkanti.dutta@edpl.info");
					formData.add("cMobile", "8240593169");
					formData.add("cBillAdd", "3 canal Street , loknath apartment , 1st floor , kolkata - 700014");
					formData.add("cAmount", amount);
					formData.add("tokenNumber", tokennumberForApiVendor);
					formData.add("callback", "http://localhost:8080/EncoreDigi2021/paymentgatewayredirect");
					formData.add("cName", transaction_id);
					
//					JSONObject walletRefillObj = new JSONObject(walletRefillRequest);
//					String walletRefilljson = walletRefillObj.toString();
					//API Call
					String walletRefillResponse = VendorPayApi.sendRequestToPayVendorWebservice(formData);
					logger_log.warn("Pay Vendor RESPONSE==>" + walletRefillResponse);
					
					returnData.put("respnse", walletRefillResponse);
					JSONObject walletRefillObj1 = new JSONObject(walletRefillResponse);
					logger_log.warn("Api Vendor  RESPONSE:::::::::::::" + walletRefillObj1);
					if(walletRefillObj1!=null){
			        	JSONObject obj = new JSONObject(walletRefillObj1);
			        	if(obj.getString("status").equalsIgnoreCase("1")){
			        		status="Success";
			        		returnData.put("msg", "This is a success response");
			        		Balancetransafer balancetransafer = new Balancetransafer("admin", 0.0, walletRefillAmount, 0.0,
									userDetails.getUserId(), toOpBal, walletRefillAmount, toClBal, "Wallet Refill",
									"Pending", today, currentTime, userDetails.getWlId());
							balancetransferDao.insertBalanceTransfer(balancetransafer);
							
							ApiTransaction apiTransaction =new ApiTransaction(transaction_id, walletRefillAmount,userDetails.getUserId(), "Pending", "", "admin",Date,Time);
							apiTransactionDao.insertApiTransaction(apiTransaction);
			        	}
			        		else{
			        			status="Failed";
			        			returnData.put("msg", "This is a failure response");
			        		}
			        	}
					
				} else {
					returnData.put("status", "0");
					returnData.put("message", "Invalid Amount!");
				}
			} else {

				returnData.put("status", "0");
				returnData.put("message", "Invalid Details!");
			}
		} catch (Exception e) {
			logger_log.error("payUWallet::::::::::::::: " + e.getMessage());
		}
		return returnData;
	}

	// Wallet Refill end
	public Map<String, Object> walletRefillResponse(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			param.put("transactionId", request.get("transaction_id"));
			List<ApiTransaction> list=apiTransactionDao.getTransactionIdbyNamedQuery("getTransactionId", param);
			
			if(list.get(0).getTransactionId().equalsIgnoreCase(request.get("transaction_id").toString()) ){
				
				String status = request.get("status").toString();
				
				if(status.equals("1")){
					String payment_id = request.get("payment_id").toString();
					ApiTransaction apiTransaction =new ApiTransaction();
					apiTransaction.setStatus("SUCCESS");
					apiTransaction.setPaymentId(payment_id);
					apiTransactionDao.updateApiTransaction(apiTransaction);
					
				}
			}
		
		 String payment_id=""; String transaction_id = "";
		  logger_log.warn("walletRefillResponse:::::::::::::::::::"+request);
		  String status=request.get("Status").toString(); Map<String, Object>
		  result = new HashMap<String, Object>();
		  payment_id=result.get("payment_id").toString();
		  transaction_id=result.get("transaction_id").toString(); try {
		  if(status.equalsIgnoreCase("1")) { returnData.put("paymentId",
		  payment_id); returnData.put("transactionId", transaction_id);
		  returnData.put("status", "1"); returnData.put("message",
		  "Payment has done successfully!"); } {
		  
		  returnData.put("status", "0"); returnData.put("message",
		  "Payment failed!"); } }catch (Exception e) {
		  logger_log.error("payUWallet::::::::::::::: " + e); } return
		  returnData;
		 

		System.out.println("call back url successful" + request);
		}
		catch (Exception e) {
			logger_log.error("setCallBackurl::::::::::::::::::::::::" + e);
		}
		return returnData;
	}	
	*/
}
	
	
	

