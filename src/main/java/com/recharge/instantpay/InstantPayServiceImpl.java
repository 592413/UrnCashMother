package com.recharge.instantpay;

import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.recharge.Imps.ImpsDao;
import com.recharge.Imps.MoneyApiDao;
import com.recharge.businessdao.ApiResponseService;
import com.recharge.businessdao.CommissionService;
import com.recharge.businessdao.CustomQueryServiceLogic;
import com.recharge.businessdao.SendRechargeRequest;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.instantpayutil.AEPSUtil;
import com.recharge.model.AEPSUserMap;
import com.recharge.model.AssignedPackage;
import com.recharge.model.Chargeset;
import com.recharge.model.DMRCommission;
import com.recharge.model.EarningSurcharge;
import com.recharge.model.ImpsTransaction;
import com.recharge.model.Operator;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.Rechargedetails;
import com.recharge.model.SmsCredential;
import com.recharge.model.User;
import com.recharge.model.Utility;
import com.recharge.servicedao.AEPSUserMapDao;
import com.recharge.servicedao.AssignedPackageDAO;
import com.recharge.servicedao.DMRCommissionDao;
import com.recharge.servicedao.EarningSurchargeDao;
import com.recharge.servicedao.ImpsTransactionService;
import com.recharge.servicedao.InsuranceDao;
import com.recharge.servicedao.OperatorDao;
import com.recharge.servicedao.RechargedetailsDao;
import com.recharge.servicedao.SmsCredentialDao;
import com.recharge.servicedao.UserDao;
import com.recharge.servicedao.UtilityDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.RoundNumber;
import com.recharge.utill.SMS;
import com.recharge.utill.UtilityClass;

@Service("instantpay")
public class InstantPayServiceImpl implements InstantPayService {
	private static final Logger logger_log = Logger.getLogger(InstantPayService.class);
	private static String token="f3b328e59b7f12cee986cabf0ed02049";
	
	private static final String keyFull= "c1bf87d466b8b2cc1bf69e996e2cc2ef4935413a7281ff952ba1826379b95b8b";
	private static final String APP_ID= "302";
	private static final String key = "c1bf87d466b8b2cc";
	@Autowired HttpSession session;
	@Autowired CommissionService commissionService;
	@Autowired ImpsTransactionService impsTransactiondao;
	@Autowired OperatorDao operatorDao;
	@Autowired CustomQueryServiceLogic customQueryServiceLogic;
	@Autowired DMRCommissionDao DMRCommissionDao;
	@Autowired AssignedPackageDAO assignedPackage;
	@Autowired UserDao userDao;
	@Autowired SendRechargeRequest sendRechargeRequest;
	@Autowired RechargedetailsDao rechargedetailsDao;
	@Autowired UtilityDao utilityDao;
	@Autowired SmsCredentialDao smsCredentialDao;
	@Autowired InsuranceDao insuranceDao;
	@Autowired ApiResponseService ApiResponseService;
	@Autowired EarningSurchargeDao earningSurchargeDao;
	@Autowired AEPSUserMapDao aepsuserdao;
	
	@Override
	public Map<String, Object> checkuserInstantPay(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String remitter = request.get("mobile");
         returnData = InstantPayWebservice.checkuserInstantPay(remitter,token);
		session.setAttribute("checkRemitterMobile",request.get("mobile"));
		return returnData;
	}
	
	
	@Override
	public Map<String, Object> instantPayRemitterRegisterValidate(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String REMID = request.get("REMID");
		String MOBILENO = request.get("MOBILENO");
		String otp = request.get("otp");

		returnData = InstantPayWebservice.instantPayRemitterRegisterValidate(token,REMID,MOBILENO,otp);
		
		
		return returnData;
		}
	
	@Override
	public Map<String, Object> viewinstantpaybene() {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
		 returnData = InstantPayWebservice.checkuserInstantPay(checkRemitterMobile,token);
		return returnData;
	}

	@Override
	public Map<String, Object> instantNewBeneficiary(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String beneMobileNumber = request.get("beneMobileNumber");
		String bene_name = request.get("bene_name");
		//String bene_type = "IMPS";
		String accountNumber = request.get("accountNumber");
		//String acc_type = request.get("acc_type");
		String beneIFSCCode = request.get("IFSC_CODE");
		String remitterid = request.get("remitterid");
		//String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
		returnData = InstantPayWebservice.instantNewBeneficiary(token,remitterid,bene_name,beneMobileNumber,beneIFSCCode,accountNumber);
		return returnData;
	}
	@Override
	public Map<String, Object> instantpayValidateBeneficiary(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		
		String accountNumber = request.get("account");
		String beneIFSCCode = request.get("ifsc");
		String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
		String referenceno = GenerateRandomNumber.referenceNO();
		if(userDetails.getBalance()<4){
			returnData.put("status", "0");
			returnData.put("message", "Donot have sufficient Balance");
			
		}else{
			commissionService.updateBalance(userDetails.getUserId(), "IMPS Account Validate", "Charge", 4, "DEBT",0);
		returnData = InstantPayWebservice.instantpayValidateBeneficiary(token,accountNumber,beneIFSCCode,checkRemitterMobile,referenceno);
		}
		return returnData;
		}
	@Override
	public Map<String, Object> deleteinstantbene(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String remitterid = request.get("remitterid");
		String id = request.get("id");
		returnData = InstantPayWebservice.instantpayDeleteBeneficiary(token,id,remitterid);
		return returnData;
	}
	@Override
	public Map<String, Object> instantPayDeleteBeneficiaryFinal(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String remitterid = request.get("remitterid");
		String id = request.get("id");
		String otp = request.get("otp");
		returnData = InstantPayWebservice.instantPayDeleteBeneFinal(token,id,remitterid,otp);
		return returnData;
	}

	@Override
	public Map<String, Object> instantNewBenefinal(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String remitterid = request.get("remitterid");
		String id = request.get("id");
		String otp = request.get("otp");
		returnData = InstantPayWebservice.instantPayNewBeneFinal(token,id,remitterid,otp);
		return returnData;
	}

	@Override
	public Map<String, Object> instantPayRemitterRegister(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String customermobile = request.get("customermobile");
		String customername = request.get("customername");
		String surname = request.get("customersurnme");
		String Pincode = "700052";

		returnData = InstantPayWebservice.instantRemitterRegister(token,customermobile,customername,Pincode,surname);
		
		
		return returnData;
		}

	@Override
	public Map<String, Object> instantPayMoneytransfer(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		double charge = 0.0;
		boolean flag;
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		double apiComm = 0.0;
		double sdCharge = 0.0;
		double distCharge = 0.0;			
		double resCharge = 0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		double disComm = 0.0;
		double sdisComm = 0.0;
		double reComm = 0.0;
		double slab1 = 0.0;
		double slab2 = 0.0;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
		
		String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
		try{
		String beneMobileNumber = request.get("mobile");
		String bene_name = request.get("name");
		String transfertype = request.get("transfertype");
		String accountNumber = request.get("account");
		String acc_type = request.get("accountType");
		String beneIFSCCode = request.get("ifsc");
		String beneid = request.get("id");
		String transactionAmount=request.get("amount");
		String referenceno = "";
		double amount=0.0;
		double totalAmount=0.0;
		double transamount=Double.parseDouble(transactionAmount);
		int id=0;
		parameter = new HashMap<String, Object>();
		parameter.put("user_id", userDetails.getUserId());
		parameter.put("service_type", "DMR");
		List<AssignedPackage> lista = assignedPackage
				.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", parameter);
	/*-------------------------------------------------------------------------------------------------------------------*/
		String tId = "";
		
		if(!lista.isEmpty()){
			
			parameter = new HashMap<String, Object>();
			parameter.put("api", "DMR");
			List<DMRCommission> opList =  DMRCommissionDao.getDMRCommissionByNamedQuery("getDMRCommissionByApi", parameter);
			if((!opList.isEmpty())) {
				double ckbl=0;
				if(transamount>5000){
					ckbl=5000;
				}else{
					ckbl=transamount;
				}
				for(DMRCommission comm2 : opList){
					if(ckbl>=comm2.getSlab1() && ckbl<=comm2.getSlab2()){
						id = comm2.getId();
						break;
					}
				}	
				
								
			
		
		/*---------------------------------------------------------------------------------------------------------------*/
		if (Double.parseDouble(transactionAmount) >= 10) {
			
			double temp = Double.parseDouble(transactionAmount);
			double remain = 0.0;
			while (temp > 0) {
				referenceno = GenerateRandomNumber.referenceNO();
				if (temp > 5000) {
					temp = temp - 5000;
					remain = 5000;
				} else {
					remain = temp;
					temp = 0.0;
				}
				
				for(DMRCommission comm2 : opList){
					if(remain>=comm2.getSlab1() && remain<=comm2.getSlab2()){
						id = comm2.getId();
						break;
					}
				}
				
				if (userDetails.getRoleId() == 5) {
					pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id) ;
					if (pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pckret.getCharge() * remain) / 100;
					} else {
						charge = pckret.getCharge();
					}
					
					System.out.println("charge="+charge);
				} else if (userDetails.getRoleId() == 4) {
					pckdis = commissionService.getPackagewiseCommisionOnOperator(
							userDetails.getUserId(),"DMR",id);
					if (pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pckdis.getCharge() * remain) / 100;
					} else {
						charge = pckdis.getCharge();
					}

				} else if (userDetails.getRoleId() == 3) {
					pcksd = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					
					if (pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pcksd.getCharge() * remain) / 100;
					} else {
						charge = pcksd.getCharge();
					}
					logger_log.warn("charge:::::"+charge);

				}	else if (userDetails.getRoleId() == 100) {
					pckapiu = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					if (pckapiu.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pckapiu.getCharge() * remain) / 100;
					} else {
						charge = pckapiu.getCharge();
					}

				}
				
				/*-------------------------------------COMMISSION---------------------------------------------------------------------*/
				if(userDetails.getRoleId() == 5) {
					//Retailer Id
					//rId=userDetails.getUserId();
					// Distributor Id
					distId = userDetails.getUplineId();		
					System.out.println("distId=="+distId);
					// Super Distributor Id
					parameters = new HashMap<String, String>();
					parameters.put("userId", distId);	
					sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
					System.out.println("sdId=="+sdId);
					// Reseller Id
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
					System.out.println("resellerId=="+resellerId);
					System.out.println("resellerId=="+pckret.getComm_type());
					System.out.println("resellerId=="+pckret.getComm());
					if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					retComm =(pckret.getComm()*transamount)/100;
					}else{
					retComm =pckret.getComm();	
					}
					comm = retComm;
					System.out.println("reseller=="+comm);
					pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"DMR",id); 
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*transamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
					System.out.println("dComm=="+dComm);
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					sdComm =(pcksd.getComm()*transamount)/100;
					}else{
					sdComm = pcksd.getComm();	
					}
					System.out.println("sdComm=="+sdComm);
					if(!resellerId.equals("admin")){
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*transamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						System.out.println("resComm=="+resComm);
					}
					
					
				} else if(userDetails.getRoleId() == 4) {
					//distId = userDetails.getUserId();	
					sdId = userDetails.getUplineId();	
					
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					pckdis=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id); 
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*transamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
					comm = dComm;
				//	System.out.println("dComm="+dComm);
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					sdComm =(pcksd.getComm()*transamount)/100;
					}else{
					sdComm = pcksd.getComm();	
					}
					sdComm=sdComm-dComm;
					//System.out.println("sdComm="+sdComm);
					if(!resellerId.equals("admin")) {
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*transamount)/100;
					}else{
					resComm =pckres.getComm();	
					}
					resComm=resComm-sdComm;
					}
					//System.out.println("resComm="+resComm);
					
				} else if(userDetails.getRoleId() == 3) {
					resellerId = userDetails.getUplineId();
					pcksd=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				     sdComm =(pcksd.getComm()*transamount)/100;
					}else{
						sdComm = pcksd.getComm();	
					}
					comm=sdComm;
					logger_log.warn("sdComm:::::::::"+sdComm);
					
					/*sdComm = commissionService.getCommisionOnOperator(userDetails.getUplineId(), userDetails.getWlId(), 3, operator.getOperatorId());
					
					resComm = commissionService.getCommisionOnOperator(resellerId, userDetails.getWlId(), 2, operator.getOperatorId());			*/			
				}else if(userDetails.getRoleId() == 100) {
					pckapiu=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					if(pckapiu.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						apiComm =(pckapiu.getComm()*transamount)/100;
					}else{
						apiComm = pckapiu.getComm();	
					}
					comm = apiComm;
					System.out.println("apiComm:::::::::"+apiComm);
					
					}
				
	
				double lockbalance=userDetails.getLockedAmount();
				totalAmount = RoundNumber.roundDouble((remain + charge) - comm) ;
				parameters = new HashMap<String, String>();
				parameters.put("userId", userDetails.getUserId());
				double op_bal = customQueryServiceLogic
						.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser,parameters);
				System.out.println("op_bal::::::"+op_bal);
				double cl_bal = RoundNumber.roundDouble(op_bal - totalAmount);
				//System.out.println("cl_bal::::::"+cl_bal);
				if(lockbalance<=cl_bal){
				if (userDetails.getBalance() > totalAmount) {
					String trnsamnt=Double.toString(remain);
					amount = remain;
					returnData = InstantPayWebservice.instantPaymentTransfer(token,checkRemitterMobile,beneid,referenceno,trnsamnt,transfertype);
					if(returnData.get("statuscode").toString().equalsIgnoreCase("TXN")){
					String FUNDTRANSNO= returnData.get("ref_no").toString();
					//System.out.println("FUNDTRANSNO::::::::::::" + FUNDTRANSNO);
					String status=(String) returnData.get("status");
					
					//System.out.println("charge::::::::::::" + charge);
				//	totalAmount = charge + amount;
					
					//System.out.println("totalAmount::::::::::::::::::::" + totalAmount);
					parameters = new HashMap<String, String>();
					double adOpBal = customQueryServiceLogic
							.getTotalbalanceForAdmin(CustomSqlQuery.getTotalbalanceForAdmin, parameters);
					RoundNumber.roundDouble(adOpBal - totalAmount);
					
					if (cl_bal > 0) {
						String today = GenerateRandomNumber.getCurrentDate();	
						String currentTime = GenerateRandomNumber.getCurrentTime();
						String transId = GenerateRandomNumber.generateTransactionNumber();
						flag = commissionService.updateBalance(userDetails.getUserId(),
								"Money Transfer - " + accountNumber, "Money Transfer", totalAmount, "DEBIT",0);
						//System.out.println("flag::::::"+flag);
						if (flag) {/*
							if (status.equals("1")) {
							ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, transId, "SUCCESS", FUNDTRANSNO,comm,"WEB");
							flag=impsTransactiondao.insertImpsTransaction(imptrans);
							}else if(status.equals("0")){
								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, transId, "FAILED", FUNDTRANSNO,comm,"WEB");
								flag=impsTransactiondao.insertImpsTransaction(imptrans);
							}else{
								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, transId, "PENDING", FUNDTRANSNO,comm,"WEB");
								flag=impsTransactiondao.insertImpsTransaction(imptrans);
							}
							logger_log.warn("flag:::::::::::::::::"+flag);
							if (flag) {
									
									if (status.equals("1")) {
										status="SUCCESS";
									}
									else if(status.equals("0")){
										status="FAILED";
									}else{
										status="PENDING";
									}
								//}
								System.out.println("status::::::::::::"+status);
								if (status.equals("SUCCESS")) {
									
									double amount1 = amount;
									String dist = "";
									String s_dist = "";
									String reseller = "";
									double s_dis = 0.0;
									double dis = 0.0;
									double reseller_com = 0.0;
									double sd_com = 0.0;
									double d_com = 0.0;
									double ret_com = 0.0;
									if (userDetails.getRoleId() == 5) {
										if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
											distCharge = (pckdis.getCharge()*remain)/100;
										}else{
											distCharge = pckdis.getCharge();
										}
										if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
											sdCharge = (pcksd.getCharge()*remain)/100;
										}else{
											sdCharge = pcksd.getCharge();
										}
											if(!resellerId.equals("admin")) {
												if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
												resCharge = (pckres.getCharge()*remain)/100;
												}else{
												resCharge =	pckres.getCharge();
												}
												
											}
											if(distCharge==0){dcharge=0;}
											else{dcharge = charge - distCharge;}
											System.out.println("dcharge:::"+dcharge);
											if(sdCharge==0){sCharge=0;}
											else{sCharge = distCharge - sdCharge;}
											System.out.println("sCharge:::"+sCharge);
											
											commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "CHARGE", dcharge, "CREDIT",0);
											commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0);
											if(!resellerId.equals("admin")) {
												reCharge = sdCharge - resCharge;
												commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0);
											}
											
											if(dComm==0){disComm=0;}
											else{
											disComm=dComm-retComm;
											}
											if(sdComm==0){sdisComm=0;}
											else{
											sdisComm = sdComm-dComm;
											}
											if(resComm==0){reComm=0;}
											else{
											reComm = resComm-sdComm;
											}								
											commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "COMMISSION", disComm, "CREDIT",0);
											commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0);
											if(!resellerId.equals("admin")) {
												commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0);
											}
											
									} else if (userDetails.getRoleId() == 4) {
										if(!resellerId.equals("admin")) {
										if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
										resCharge = (pckres.getCharge()*remain)/100;
										}else{
										resCharge =	pckres.getCharge();
										}
										
									}
									if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
										sdCharge = (pcksd.getCharge()*remain)/100;
									}else{
										sdCharge = pcksd.getCharge();
									}
									if(sdCharge==0){sCharge=0;}
									else{sCharge = charge - sdCharge;}
									System.out.println("sCharge:::"+sCharge);
																							
									commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0);
									if(!resellerId.equals("admin")) {
										reCharge = sdCharge - resCharge;
										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0);
									}
									
									if(sdComm==0){sdisComm=0;}
									else{
									sdisComm=sdComm-dComm;
									}
									if(resComm==0){reComm=0;}
									else{
									reComm = resComm-sdComm;
									}
									
									commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0);
									if(!resellerId.equals("admin")) {
										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0);
									}

									} else if (userDetails.getRoleId() == 3) {
										reseller = userDetails.getUplineId();
										if (!resellerId.equals("admin")) {
											if (pckres.getCharge_type()
													.equalsIgnoreCase("PERCENTAGE")){
												resCharge = (pckres.getCharge() * remain) / 100;
										} else {
											resCharge = pckres.getCharge();
										}

											reCharge = sdCharge - charge;
											commissionService.updateBalance(resellerId,
													"Money Transfer - " + accountNumber,
													"CHARGE", reCharge, "CREDIT",0);
											
											if (pckres.getComm_type()
													.equalsIgnoreCase("PERCENTAGE")){
												resComm = (pckres.getComm() * remain) / 100;
										} else {
											resComm = pckres.getComm();
										}
											if(resComm==0){reComm=0;}
											else{
											reComm=resComm-sdComm;
											}
											commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0);
										}
										
										
									}
									//impsTransactiondao.updateDmrTransactionBankTransactionId(transId,trans.getRequestRetrievalNumber());
									returnData.put("message", "Transaction Successful :"+FUNDTRANSNO);
									returnData.put("status", "1");

								}
								if (status.equals("FAILED")) {
									commissionService.updateBalance(userDetails.getUserId(),
											"Money Transfer - " + accountNumber, "Money Transfer", totalAmount, "CREDIT",0);
									returnData.put("message", "Transaction Failed ");
									returnData.put("status", "0");
								}
								//impsTransactiondao.updateDmrTransactionStatus(transId, status);
							
						}
					*/}	
						
					}
					}else{
						returnData.put("message",returnData.get("status"));
						returnData.put("status", "0");	
					}

				} else {

					returnData.put("message", "Donot Have Sufficient Balance");
					returnData.put("status", "0");

				}
				} else {

					returnData.put("message", "Donot Have Sufficient Balance");
					returnData.put("status", "0");

				}
			}
		} else {
			returnData.put("message", "Amount should be minimum 10");
			returnData.put("status", "0");
		}
		}else{
			returnData.put("message", "Please Assign Package");
			returnData.put("status", "0");
		}
		}else {
			returnData.put("status", "0");
			returnData.put("message", "Operator Is Not Available");
		}
		System.out.println();
		}catch (Exception e) {
		logger_log.error("instantPayMoneytransfer::::::::::::::"+e);
		}
		return returnData;
		}

	@Override
	public Map<String, Object> instantPayMoneytransferAndroid(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		double charge = 0.0;
		boolean flag;
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		double apiComm = 0.0;
		double sdCharge = 0.0;
		double distCharge = 0.0;			
		double resCharge = 0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		double disComm = 0.0;
		double sdisComm = 0.0;
		double reComm = 0.0;
		double slab1 = 0.0;
		double slab2 = 0.0;
		
		
		int id=0;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
		
		String checkRemitterMobile=request.get("remittermobile");
		/*String member_id=(String) session.getAttribute("member_id");
		String api_password=(String) session.getAttribute("api_password");
		String api_pin=(String) session.getAttribute("api_pin");*/
		String beneMobileNumber = request.get("mobile");
		String bene_name = request.get("name");
		String transfertype = request.get("transfertype");
		String accountNumber = request.get("account");
		/*String acc_type = request.get("accountType");
		String beneIFSCCode = request.get("ifsc");*/
		String beneid = request.get("id");
		String transactionAmount=request.get("amount");
		String referenceno = "";
		double amount=0.0;
		double totalAmount=0.0;
		double transamount=Double.parseDouble(transactionAmount);
		parameter = new HashMap<String, Object>();
		parameter.put("user_id", userDetails.getUserId());
		parameter.put("service_type", "DMR");
		List<AssignedPackage> lista = assignedPackage
				.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", parameter);
		if(!lista.isEmpty()){
			parameter = new HashMap<String, Object>();
			parameter.put("api", "DMR");
			List<DMRCommission> opList =  DMRCommissionDao.getDMRCommissionByNamedQuery("getDMRCommissionByApi", parameter);
		// ------------------------------  User's Charge calculation for the Recharge -------------------------------------//
			if((!opList.isEmpty())) {
				double ckbl=0;
				if(transamount>5000){
					ckbl=5000;
				}else{
					ckbl=transamount;
				}
				for(DMRCommission comm2 : opList){
					if(ckbl>=comm2.getSlab1() && ckbl<=comm2.getSlab2()){
						id = comm2.getId();
						break;
					}
				}	
				
							
					
	/*-------------------------------------------------------------------------------------------------------------------*/
		String tId = "";
		/*-------------------------------------COMMISSION---------------------------------------------------------------------*/
		
		/*---------------------------------------------------------------------------------------------------------------*/
		if (Double.parseDouble(transactionAmount) >= 10) {
			
			double temp = Double.parseDouble(transactionAmount);
			double remain = 0.0;
			while (temp > 0) {
				referenceno = GenerateRandomNumber.referenceNO();
				if (temp > 5000) {
					temp = temp - 5000;
					remain = 5000;
				} else {
					remain = temp;
					temp = 0.0;
				}
				for(DMRCommission comm2 : opList){
					if(remain>=comm2.getSlab1() && remain<=comm2.getSlab2()){
						id = comm2.getId();
						break;
					}
				}	
				
				if (userDetails.getRoleId() == 5) {
					pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id) ;
					if (pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pckret.getCharge() * remain) / 100;
					} else {
						charge = pckret.getCharge();
					}
					
					System.out.println("charge="+charge);
				} else if (userDetails.getRoleId() == 4) {
					pckdis = commissionService.getPackagewiseCommisionOnOperator(
							userDetails.getUserId(),"DMR",id);
					if (pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pckdis.getCharge() * remain) / 100;
					} else {
						charge = pckdis.getCharge();
					}

				} else if (userDetails.getRoleId() == 3) {
					pcksd = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					
					if (pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pcksd.getCharge() * remain) / 100;
					} else {
						charge = pcksd.getCharge();
					}
					logger_log.warn("charge:::::"+charge);

				}	else if (userDetails.getRoleId() == 100) {
					pckapiu = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					if (pckapiu.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pckapiu.getCharge() * remain) / 100;
					} else {
						charge = pckapiu.getCharge();
					}

				}	
				

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
						retComm =(pckret.getComm()*transamount)/100;
						}else{
						retComm =pckret.getComm();	
						}
						comm = retComm;
					//	System.out.println("reseller=="+comm);
						pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"DMR",id); 
						if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						dComm =(pckdis.getComm()*transamount)/100;
						}else{
						dComm=pckdis.getComm();	
						}
					//	System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*transamount)/100;
						}else{
						sdComm = pcksd.getComm();	
						}
					//	System.out.println("sdComm=="+sdComm);
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*transamount)/100;
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
						
						pckdis=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id); 
						if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						dComm =(pckdis.getComm()*transamount)/100;
						}else{
						dComm=pckdis.getComm();	
						}
						comm = dComm;
					//	System.out.println("dComm="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*transamount)/100;
						}else{
						sdComm = pcksd.getComm();	
						}
						sdComm=sdComm-dComm;
						//System.out.println("sdComm="+sdComm);
						if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*transamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						resComm=resComm-sdComm;
						}
						//System.out.println("resComm="+resComm);
						
					} else if(userDetails.getRoleId() == 3) {
						resellerId = userDetails.getUplineId();
						pcksd=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					     sdComm =(pcksd.getComm()*transamount)/100;
						}else{
							sdComm = pcksd.getComm();	
						}
						comm=sdComm;
						logger_log.warn("sdComm:::::::::"+sdComm);
						
						/*sdComm = commissionService.getCommisionOnOperator(userDetails.getUplineId(), userDetails.getWlId(), 3, operator.getOperatorId());
						
						resComm = commissionService.getCommisionOnOperator(resellerId, userDetails.getWlId(), 2, operator.getOperatorId());			*/			
					}else if(userDetails.getRoleId() == 100) {
						pckapiu=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
						if(pckapiu.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							apiComm =(pckapiu.getComm()*transamount)/100;
						}else{
							apiComm = pckapiu.getComm();	
						}
						comm = apiComm;
						System.out.println("apiComm:::::::::"+apiComm);
						
						}
				
				
				double lockbalance=userDetails.getLockedAmount();
				totalAmount = RoundNumber.roundDouble(remain + charge) ;
				parameters = new HashMap<String, String>();
				parameters.put("userId", userDetails.getUserId());
				double op_bal = customQueryServiceLogic
						.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
				System.out.println("op_bal::::::"+op_bal);
				double cl_bal = RoundNumber.roundDouble(op_bal - totalAmount);
				System.out.println("cl_bal::::::"+cl_bal);
				if(lockbalance<=cl_bal){
				if (userDetails.getBalance() > totalAmount) {
					String trnsamnt=Double.toString(remain);
					amount = remain;
					returnData = InstantPayWebservice.instantPaymentTransfer(token,checkRemitterMobile,beneid,referenceno,trnsamnt,transfertype);
					logger_log.warn("instantPayMoneytransferAndroidreturnData::::::::::::::::"+returnData);
					if(returnData.get("statuscode").toString().equalsIgnoreCase("TXN")){
					String FUNDTRANSNO= returnData.get("ref_no").toString();
					System.out.println("FUNDTRANSNO::::::::::::" + FUNDTRANSNO);
					String status=(String) returnData.get("status");
					
					System.out.println("charge::::::::::::" + charge);
				//	totalAmount = charge + amount;
					
					//System.out.println("totalAmount::::::::::::::::::::" + totalAmount);
					parameters = new HashMap<String, String>();
					double adOpBal = customQueryServiceLogic
							.getTotalbalanceForAdmin(CustomSqlQuery.getTotalbalanceForAdmin, parameters);
					RoundNumber.roundDouble(adOpBal - totalAmount);
					
					if (cl_bal > 0) {
						String today = GenerateRandomNumber.getCurrentDate();	
						String currentTime = GenerateRandomNumber.getCurrentTime();
						String transId = GenerateRandomNumber.generateTransactionNumber();
						flag = commissionService.updateBalance(userDetails.getUserId(),
								"Money Transfer - " + accountNumber, "Money Transfer", totalAmount, "DEBIT",0);
						System.out.println("flag::::::"+flag);
						if (flag) {/*
							if (status.equals("1")) {
							ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, remain, charge, cl_bal, today, currentTime, transId, "SUCCESS", FUNDTRANSNO,comm,"APPS");
							flag=impsTransactiondao.insertImpsTransaction(imptrans);
							}else if(status.equals("0")){
								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, remain, charge, cl_bal, today, currentTime, transId, "FAILED", FUNDTRANSNO,comm,"APPS");
								flag=impsTransactiondao.insertImpsTransaction(imptrans);
							}else{
								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, remain, charge, cl_bal, today, currentTime, transId, "PENDING", FUNDTRANSNO,comm,"APPS");
								flag=impsTransactiondao.insertImpsTransaction(imptrans);
							}
							logger_log.warn("flag:::::::::::::::::"+flag);
							if (flag) {
									
									if (status.equals("1")) {
										status="SUCCESS";
									}
									else if(status.equals("0")){
										status="FAILED";
									}else{
										status="PENDING";
									}
								//}
								System.out.println("status::::::::::::"+status);
								if (status.equals("SUCCESS")) {
									double amount1 = amount;
									
									
									String dist = "";
									String s_dist = "";
									String reseller = "";
									double s_dis = 0.0;
									double dis = 0.0;
									double reseller_com = 0.0;
									double sd_com = 0.0;
									double d_com = 0.0;
									double ret_com = 0.0;
									if (userDetails.getRoleId() == 5) {
										if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
											distCharge = (pckdis.getCharge()*remain)/100;
										}else{
											distCharge = pckdis.getCharge();
										}
										if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
											sdCharge = (pcksd.getCharge()*remain)/100;
										}else{
											sdCharge = pcksd.getCharge();
										}
											if(!resellerId.equals("admin")) {
												if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
												resCharge = (pckres.getCharge()*remain)/100;
												}else{
												resCharge =	pckres.getCharge();
												}
												
											}
											if(distCharge==0){dcharge=0;}
											else{dcharge = charge - distCharge;}
											System.out.println("dcharge:::"+dcharge);
											if(sdCharge==0){sCharge=0;}
											else{sCharge = distCharge - sdCharge;}
											System.out.println("sCharge:::"+sCharge);
											
											commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "CHARGE", dcharge, "CREDIT",0);
											commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0);
											if(!resellerId.equals("admin")) {
												reCharge = sdCharge - resCharge;
												commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0);
											}
											
											if(dComm==0){disComm=0;}
											else{
											disComm=dComm-retComm;
											}
											if(sdComm==0){sdisComm=0;}
											else{
											sdisComm = sdComm-dComm;
											}
											if(resComm==0){reComm=0;}
											else{
											reComm = resComm-sdComm;
											}								
											commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "COMMISSION", disComm, "CREDIT",0);
											commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0);
											if(!resellerId.equals("admin")) {
												commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0);
											}
											
									} else if (userDetails.getRoleId() == 4) {
										if(!resellerId.equals("admin")) {
										if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
										resCharge = (pckres.getCharge()*remain)/100;
										}else{
										resCharge =	pckres.getCharge();
										}
										
									}
									if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
										sdCharge = (pcksd.getCharge()*remain)/100;
									}else{
										sdCharge = pcksd.getCharge();
									}
									if(sdCharge==0){sCharge=0;}
									else{sCharge = charge - sdCharge;}
									System.out.println("sCharge:::"+sCharge);
																							
									commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0);
									if(!resellerId.equals("admin")) {
										reCharge = sdCharge - resCharge;
										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0);
									}
									
									if(sdComm==0){sdisComm=0;}
									else{
									sdisComm=sdComm-dComm;
									}
									if(resComm==0){reComm=0;}
									else{
									reComm = resComm-sdComm;
									}
									
									commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0);
									if(!resellerId.equals("admin")) {
										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0);
									}

									} else if (userDetails.getRoleId() == 3) {
										reseller = userDetails.getUplineId();
										if (!resellerId.equals("admin")) {
											if (pckres.getCharge_type()
													.equalsIgnoreCase("PERCENTAGE")){
												resCharge = (pckres.getCharge() * remain) / 100;
										} else {
											resCharge = pckres.getCharge();
										}

											reCharge = sdCharge - charge;
											commissionService.updateBalance(resellerId,
													"Money Transfer - " + accountNumber,
													"CHARGE", reCharge, "CREDIT",0);
											
											if (pckres.getComm_type()
													.equalsIgnoreCase("PERCENTAGE")){
												resComm = (pckres.getComm() * remain) / 100;
										} else {
											resComm = pckres.getComm();
										}
											if(resComm==0){reComm=0;}
											else{
											reComm=resComm-sdComm;
											}
											commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0);
										}
										
										
									}									//impsTransactiondao.updateDmrTransactionBankTransactionId(transId,trans.getRequestRetrievalNumber());
									returnData.put("message", "Transaction Successful :"+FUNDTRANSNO);
									returnData.put("status", "1");

								}
								if (status.equals("FAILED")) {
									commissionService.updateBalance(userDetails.getUserId(),
											"Money Transfer - " + accountNumber, "Money Transfer", totalAmount, "CREDIT",0);
									returnData.put("message", "Transaction Failed ");
									returnData.put("status", "0");
								}
								//impsTransactiondao.updateDmrTransactionStatus(transId, status);
							
						}
					*/}	
						
					}
				}else{
					returnData.put("message",returnData.get("status"));
					returnData.put("status", "0");	
				}

				} else {

					returnData.put("message", "Donot Have Sufficient Balance");
					returnData.put("status", "0");

				}
				} else {

					returnData.put("message", "Donot Have Sufficient Balance");
					returnData.put("status", "0");

				}
			}
		} else {
			returnData.put("message", "Amount should be minimum 10");
			returnData.put("status", "0");
		}
			}else{
				returnData.put("message", "Please Assign Package");
				returnData.put("status", "0");
			}

		}else {
			returnData.put("status", "0");
			returnData.put("message", "Operator Is Not Available");
		}
		
		
		return returnData;
		}
	
	
	@Override
	public Map<String, Object> eBillBBPsPaymentFirstInsurance(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		String rechargerl ="";
		String response = "";
		try {			
			Operator operator = new Operator();
		
			if (UtilityClass.checkParameterIsNull(request)) {				
				
				if (request.get("operator") == null || request.get("policyNumber") == null
						|| request.get("custNumber") == null
						||request.get("operator").equals("") || request.get("policyNumber").equals("")
						 || request.get("custNumber").equals("")
						) {
					
					returnData.put("status", "0");
					returnData.put("message", "Invalid Request!");

				} else{	
				
					String consumerMobile =request.get("custNumber");
					String mobileNo = request.get("policyNumber");
					User user = userDao.getUserByUserId(userDetails.getUserId());
					String activestatus=user.getStatus();
					if(activestatus.equals("1")){
						returnData.put("status", "0");
						returnData.put("message", "Inactive User.");
					}else{
					parameters = new HashMap<String, String>();
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
							
							String tId = "";
							
												
								tId = GenerateRandomNumber.generatePtid(request.get("policyNumber"));
								if (!tId.equals("")) {				
									 rechargerl ="https://www.instantpay.in/ws/api/transaction?format=json&token="+token+"a&agentid="+tId+"&amount=10&spkey="+operator.getOutCode()+"&outletid=23321&paymentchannel=AGT&paymentmode=CASH&optional9=22.7893,88.2731|712223&account="+mobileNo+"&customermobile="+consumerMobile+"&mode=VALIDATE&optional8=TEST";
									 logger_log.warn("instantpay rechargerl :: "+rechargerl);	
									 response = sendRechargeRequest.sendRechagreReq(rechargerl);
								     logger_log.warn("instantpay RESPONSE :: "+response);	
								     returnData=InstantWebserviceParser.bbpsBillPaymentFirstParser(response);	
								     returnData.put("servicepropvidername",operator.getServiceProvider());
								     if(returnData.get("status").toString().equalsIgnoreCase("0")){
								    	 returnData.put("consumerMobile",consumerMobile); 
								    	 returnData.put("consumerNumber",mobileNo); 
								     }else{
								    	 returnData.put("consumerMobile",consumerMobile); 
								    	 returnData.put("tId",tId); 
								    	 returnData.put("operator",operator.getInCode()); 
								     }
									
								}else {
									returnData.put("status", "0");
									returnData.put("message", "Bill Submit Failed Failed. Invalid Mobile Numbers!");
								}
							
							}else {
								returnData.put("status", "0");
								returnData.put("message", "Recharge Failed. Operator is not available");
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Bill Submit Failed. Please try after Some time");
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
	public Map<String, Object> eBillBBPsPaymentFirst(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> returnData1 = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		String rechargerl ="";
		
		try {			
			Operator operator = new Operator();
		
			if (UtilityClass.checkParameterIsNull(request)) {				
				
				if (request.get("operator") == null || request.get("consumerNumber") == null
						|| request.get("consumerMobile") == null
						||request.get("operator").equals("") || request.get("consumerNumber").equals("")
						 || request.get("consumerMobile").equals("")
						) {
					
					returnData.put("status", "0");
					returnData.put("message", "Invalid Request!");

				} else{	
				
					String consumerMobile =request.get("consumerMobile");
					String mobileNo = request.get("consumerNumber");
					User user = userDao.getUserByUserId(userDetails.getUserId());
					String activestatus=user.getStatus();
					if(activestatus.equals("1")){
						returnData.put("status", "0");
						returnData.put("message", "Inactive User.");
					}else{
					parameters = new HashMap<String, String>();
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
							
							String tId = "";
								tId = GenerateRandomNumber.generatePtid(request.get("consumerNumber"));
								if (!tId.equals("")) {
									Client client = null;
									 rechargerl ="https://www.instantpay.in/ws/bbps/bill_fetch";
									 MultivaluedMap<String, Object> headers= new MultivaluedHashMap<>();
										headers.add("Accept", "application/json");
										headers.add("Content-Type", "application/json");
									 logger_log.warn("instantpay rechargerl :: "+rechargerl);
									 String bodyjson="{\"token\": \""+token+"\",\"request\": {\"sp_key\": \""+operator.getOutCode()+"\",\"agentid\": \""+tId+"\",\"customer_mobile\": \""+consumerMobile+"\",\"customer_params\": [\""+mobileNo+"\",\"{{customer_param2}}\"], \"init_channel\": \"AGT\",\"endpoint_ip\": \"123.258.255.12\",\"mac\": \"AD-fg-12-78-GH\",\"payment_mode\": \"Cash\",\"payment_info\": \"bill\",\"amount\": \"10\",\"reference_id\": \"\", \"latlong\": \"24.1568,78.5263\", \"outletid\": \"23321\"}}";
									 logger_log.warn(bodyjson);
									 client=ClientBuilder.newClient();
										//String i = new Gson().toJson(bodyjson);
										Response response1=client.target(rechargerl).request(MediaType.APPLICATION_JSON).headers(headers).post(Entity.entity(bodyjson, MediaType.APPLICATION_JSON));
								//	 response = sendRechargeRequest.sendRechagreReq(rechargerl);
									// response = "{'ipay_errorcode':'IRA','ipay_errordesc':'Invalid Refill Amount','particulars':{'dueamount':253.21,'duedate':'2018-11-26','customername':'NA','billnumber':'40024477713','billdate':'2018-11-08','billperiod':'NA'}}";
								     logger_log.warn("instantpay RESPONSE :: "+response1);	
								   String output =response1.readEntity(String.class);
								   logger_log.warn("instantpay output :: "+output);	
								   JSONObject response=new JSONObject(output);
								   
								    
								     returnData.put("servicepropvidername",operator.getServiceProvider());
								     if(!response.get("status").toString().equalsIgnoreCase("Transaction Successful")){
								    	
								    	 	returnData.put("consumerNumber", request.get("consumerNumber"));
											returnData.put("consumerMobile", request.get("consumerMobile"));
											returnData.put("status", "0");
											returnData.put("message", response.get("status"));
								     }else{
								    	 JSONObject data= response.getJSONObject("data");
								    	 returnData1=InstantWebserviceParser.bbpsBillPaymentFirstParser(data.toString());
								    	 returnData.put("consumerMobile",consumerMobile); 
								    	 returnData.put("consumerNumber",mobileNo);
								    	 returnData.put("tId",tId); 
								    	 returnData.put("operator",operator.getInCode()); 
								    	 
								    	 
								    	 
											returnData.put("status", "1");
											returnData.put("Dueamount", returnData1.get("BILLAMT"));
											returnData.put("BILLNO", returnData1.get("BILLNO"));
											returnData.put("CUSTNAME", returnData1.get("CUSTNAME"));
											returnData.put("BILLDATE", returnData1.get("BILLDATE"));
											returnData.put("PARTIALPAYALLOW", returnData1.get("PARTIALPAYALLOW"));
											returnData.put("consumerNumber", request.get("consumerNumber"));
											returnData.put("consumerMobile", request.get("consumerMobile"));
											returnData.put("operator", request.get("operator"));
											returnData.put("reference_id", returnData1.get("reference_id"));
											returnData.put("message", "Customer Number "+returnData1.get("CUSTNAME")+"Due Amount="+returnData1.get("BILLAMT")+",Your Due Date="+returnData1.get("BILLDUEDATE"));

								     }
									
								}else {
									returnData.put("status", "0");
									returnData.put("message", "Bill Submit Failed Failed. Invalid Mobile Numbers!");
								}
							
							}else {
								returnData.put("status", "0");
								returnData.put("message", "Recharge Failed. Operator is not available");
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Bill Submit Failed. Please try after Some time");
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
	public Map<String, Object> eBillBBPsPaymentFinal(Map<String, String> request, User userDetails) {
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
		
			Chargeset dCharge = null;
			String rechargerl = "";
			if (UtilityClass.checkParameterIsNull(request)) {				
				
				if (request.get("operator") == null || request.get("consumerNumber") == null
						|| request.get("amount") == null || request.get("consumerMobile") == null
						|| request.get("consumerName") == null || request.get("dueDate") == null						
						||request.get("operator").equals("") || request.get("consumerNumber").equals("")
						|| request.get("amount").equals("") || request.get("consumerMobile").equals("")
						|| request.get("consumerName").equals("")|| request.get("dueDate").equals("")) {
					
					returnData.put("status", "0");
					returnData.put("message", "Invalid Request!");

				} else{
					
					String consumerMobile = request.get("consumerMobile");
					String mobileNo = request.get("consumerNumber");
					String tId = request.get("tId");
					double amount = Double.parseDouble(request.get("amount"));
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
											flag = commissionService.updateBalance(userDetails.getUserId(), "UTILITY to "+mobileNo, "UTILITY", totalAmount, "DEBIT",0);
											if (flag) {			
													Utility utility = new Utility(tId, operator.getOperatorId(), userDetails.getUserId(), Integer.parseInt(operator.getServiceType()), operator.getServiceProvider(), request.get("consumerName"), request.get("consumerMobile"), request.get("consumerNumber"), request.get("billdate"), amount, today, currentTime, "PENDING", userDetails.getWlId());
													boolean flag1 = utilityDao.insertUtility(utility);
													if(flag1){
														Client client = null;
														 rechargerl ="https://www.instantpay.in/ws/bbps/bill_pay";
														 logger_log.warn("instantpay rechargeurl :: "+rechargerl);
														 MultivaluedMap<String, Object> headers= new MultivaluedHashMap<>();
															headers.add("Accept", "application/json");
															headers.add("Content-Type", "application/json");
															 String bodyjson="{\"token\": \""+token+"\",\"request\": {\"sp_key\": \""+operator.getOutCode()+"\",\"agentid\": \""+tId+"\",\"customer_mobile\": \""+consumerMobile+"\",\"customer_params\": [\""+mobileNo+"\",\"{{customer_param2}}\"], \"init_channel\": \"AGT\",\"endpoint_ip\": \"123.258.255.12\",\"mac\": \"AD-fg-12-78-GH\",\"payment_mode\": \"Cash\",\"payment_info\": \"bill\",\"amount\": \""+request.get("amount")+"\",\"reference_id\": \""+request.get("reference_id")+"\", \"latlong\": \"24.1568,78.5263\", \"outletid\": \"23321\"}}";
															 client=ClientBuilder.newClient();
															 Response response1=client.target(rechargerl).request(MediaType.APPLICATION_JSON).headers(headers).post(Entity.entity(bodyjson, MediaType.APPLICATION_JSON));
															 
														// response = "{'ipay_id':'1181110140749MYFJA','agent_id':'451850100825','opr_id':'EU01UDGMRKQ9','account_no':'11000049451','sp_key':'AAE','trans_amt':253.21,'charged_amt':252.5,'opening_bal':'3778.39','datetime':'2018-11-10 14:07:55','status':'SUCCESS','res_code':'TXN','res_msg':'Transaction Successful'}";
													     logger_log.warn("instantpay RESPONSE :: "+response1);
													     String output =response1.readEntity(String.class);
														   logger_log.warn("instantpay output :: "+output);	
														   JSONObject response=new JSONObject(output);
														   logger_log.warn("instantpay BBPS response :: "+response);	
													  //   returnData=InstantWebserviceParser.bbpsBillPaymentFinalParser(response.toString());	
													

														   if (response.get("status").toString().equalsIgnoreCase("Transaction Successful") ) {
																 JSONObject data= response.getJSONObject("data");
																status = "SUCCESS";
																/*String ipay_id = returnData.get("ipay_id").toString();
																rechargedetails.setTid(tId);
																rechargedetailsDao.updateRechargeDetails(rechargedetails);*/
																/*utility.setTransactionId(tId);
																utilityDao.updateUtility(utility);*/
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
												} else {
													status = "FAILED";
												}
													
													logger_log.warn("status:::::::::::::::::::"+status);
		                                         
												if (status.equals("SUCCESS")) {
													
													logger_log.warn("status1:::::::::::::::::::"+status);
													
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
													//rechargedetails.setStatus("SUCCESS");
													//rechargedetails.setValidation("PENDING");
													//rechargedetailsDao.updateRechargeDetails(rechargedetails);	
													//utility.setStatus("SUCCESS");
													//utilityDao.updateUtility(utility);
													returnData.put("status", "1");
													returnData.put("message", "Bill Submit SUCCESS. Transaction Id: "+tId);
													if(source.equals("APPS")){
														returnData.put("closingBalance", cl_bal);
													}
													if(source.equals("API")){
														returnData.put("TransactionId", tId);
													}
												} else if (status.equals("FAILED")) {
													
													logger_log.warn("status2:::::::::::::::::::"+status);
													rechargedetails.setStatus("FAILED");
													rechargedetails.setValidation("FAILED");
													rechargedetailsDao.updateRechargeDetails(rechargedetails);	
													utility.setStatus("FAILED");
													utilityDao.updateUtility(utility);
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
	public String instantPayAEPS(User user) {
		String encodedString="";
		Map<String, Object> param = new HashMap<String, Object>();
		try{
			 // Order of Keys is important. Do not change it.
			param.put("username",user.getUserName());
			param.put("api","InstantPay");
			List<AEPSUserMap> list = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			if(!list.isEmpty()){
			AEPSUserMap aeps=list.get(0);	
	        Map<String, String> map = new TreeMap<String, String>();
	        map.put("ALLOWED_SERVICES", "");
	        map.put("APP_ID",APP_ID);
	        map.put("BLOCKED_SERVICES", "");
	        map.put("PAN",aeps.getAgentcode());
	        
	        //AOIPM9606C
	        
	        String checkSumString  = "";
	        
	        for (Map.Entry<String, String> entry : map.entrySet()) {
	            if(checkSumString.isEmpty() == true){
	                checkSumString += entry.getKey()+ ":" + entry.getValue();
	            }else{
	                checkSumString += "|" + entry.getKey() + ":" + entry.getValue();
	            }
	        }
	        String plainText = checkSumString;

	        String IV = "1234567812345678";
	        
	        // Encrypting the parameters
	        byte[] cipherText = AEPSUtil.encrypt(plainText.getBytes(),key, IV.getBytes());	
	        // Generating HMAC
	        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
	        SecretKeySpec secret_key = new SecretKeySpec(keyFull.getBytes("UTF-8"), "HmacSHA256");
	        sha256_HMAC.init(secret_key);

	        // Combine IV, HMAC and Ciphertext
	        byte[] combined = AEPSUtil.combine(IV, sha256_HMAC, cipherText);
	     // Put CHECKSUMHASH in map
	        map.put("CHECKSUMHASH",Base64.getEncoder().encodeToString(combined));
	        
	        // Convert Map to JSON
	        String jsonMap = AEPSUtil.buildJson(map);

	        // URL Encode the JSON
	        encodedString = URLEncoder.encode(jsonMap,"UTF-8" ); 
	        System.out.println("encodedString::::::::::::::::::"+encodedString);
	        session.setAttribute("encodeURL",encodedString);
		}else{
			encodedString="NA";	
		}
		}catch (Exception e) {
		logger_log.warn("instantPayAEPS:::::::::::::::::::::"+e);
		}
		return encodedString;
	}
	

	
	
}
