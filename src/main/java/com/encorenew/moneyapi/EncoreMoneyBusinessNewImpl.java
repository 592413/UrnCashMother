package com.encorenew.moneyapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.moneyapi.EncoreMoneyBusiness;
import com.encore.moneyapi.EncoreWebService;
import com.recharge.businessdao.CommissionService;
import com.recharge.businessdao.CustomQueryServiceLogic;
import com.recharge.customModel.ImpsResponse;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.model.AssignedPackage;
import com.recharge.model.DMRCommission;
import com.recharge.model.Gst;
import com.recharge.model.ImpsTransaction;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.User;
import com.recharge.servicedao.AssignedPackageDAO;
import com.recharge.servicedao.DMRCommissionDao;
import com.recharge.servicedao.GstDao;
import com.recharge.servicedao.ImpsTransactionService;
import com.recharge.servicedao.UserDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.RoundNumber;

@Service("encoremoneyservicenew")
public class EncoreMoneyBusinessNewImpl implements EncoreMoneyBusinessNew {
	
	@Autowired CustomQueryServiceLogic customQueryServiceLogic;
	@Autowired DMRCommissionDao DMRCommissionDao;
	@Autowired AssignedPackageDAO assignedPackage;
	@Autowired UserDao userDao;
	@Autowired HttpSession session;
	@Autowired ImpsTransactionService impsTransactiondao;
	@Autowired CommissionService commissionService;
	@Autowired GstDao GstDao;
	private static final Logger logger_log = Logger.getLogger(EncoreMoneyBusiness.class);
	private static final String LoginId="6T0L9H";
	private static final String Password="7C2C01#";
	@Override
	public Map<String, Object> checkuserEncoreNew(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
		String 	input="{\"mobile\":\""+request.get("mobile")+"\",\"Username\":\""+LoginId+"\",\"Password\":\""+Password+"\"}";
		returnData=EncoreWebServiceNew.checkRemmiterNew(input);
		}catch (Exception e) {
		logger_log.error("checkuserEncore:::::::::::::::::::::::::"+e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> remmiterRegisterEncoreNew(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
		String	name=request.get("customerfname")+" "+request.get("customerlname"); 
		String 	input="{\"Username\":\""+LoginId+"\",\"Password\":\""+Password+"\",\"mobile\":\""+request.get("mobile")+"\",\"name\":\""+name+"\"}";
		returnData=EncoreWebServiceNew.remitterRegisterNew(input);
		returnData.put("mobile",request.get("mobile"));
		}catch (Exception e) {
		logger_log.error("checkuserEncore:::::::::::::::::::::::::"+e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> OTPVERIFICATION(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
		String 	input="{\"Username\":\""+LoginId+"\",\"Password\":\""+Password+"\",\"mobile\":\""+request.get("mobile")+"\",\"OTP\":\""+request.get("OTP")+"\"}";
		returnData=EncoreWebServiceNew.OTPVERIFICATION(input);
		}catch (Exception e) {
		logger_log.error("checkuserEncore:::::::::::::::::::::::::"+e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> addBeneficiaryEncoreNew(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
		String 	input="{\"Username\":\""+LoginId+"\",\"Password\":\""+Password+"\",\"mobile\":\""+request.get("mobile")+"\",\"bene_name\":\""+request.get("bene_name")+"\",\"accountNumber\":\""+request.get("accountNumber")+"\",\"IFSC_CODE\":\""+request.get("IFSC_CODE")+"\",\"beneMobileNumber\":\""+request.get("beneMobileNumber")+"\"}";
		returnData=EncoreWebServiceNew.addBeneficiaryEncoreNew(input);
		returnData.put("mobile", request.get("mobile"));
		}catch (Exception e) {
		logger_log.error("checkuserEncore:::::::::::::::::::::::::"+e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> deleteBeneficiaryEncoreNew(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
		String 	input="{\"Username\":\""+LoginId+"\",\"Password\":\""+Password+"\",\"mobile\":\""+request.get("mobile")+"\",\"id\": "+request.get("id")+",\"name\":\""+request.get("name")+"\",\"account\":\""+request.get("account")+"\",\"ifsc\":\""+request.get("ifsc")+"\",\"bene_mobile\":\""+request.get("bene_mobile")+"\"}";
		returnData=EncoreWebServiceNew.deleteBeneficiaryEncoreNew(input);
		returnData.put("mobile", request.get("mobile"));
		}catch (Exception e) {
		logger_log.error("deleteBeneficiaryEncoreNew:::::::::::::::::::::::::"+e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> resendOtp(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
		String 	input="{\"Username\":\""+LoginId+"\",\"Password\":\""+Password+"\",\"mobile\":\""+request.get("mobile")+"\"}";
		returnData=EncoreWebServiceNew.resendOTP(input);
		returnData.put("mobile", request.get("mobile"));
		}catch (Exception e) {
		logger_log.error("deleteBeneficiaryEncoreNew:::::::::::::::::::::::::"+e);
		}
		return returnData;}


	@Override
	public Map<String, Object> impsMoneyTransferEncoreandroid(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		double charge = 0.0;
		boolean flag=false;
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
		
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
		
		String checkRemitterMobile=request.get("mobile");
		try{
		String beneMobileNumber = request.get("bene_mobile");
		String bene_name = request.get("name");
	    String transfertype = "IMPS";
		String accountNumber = request.get("account");
		String beneIFSCCode = request.get("ifsc");
		String beneid = request.get("id");
		String transactionAmount=request.get("amount");
		String transferType=request.get("transferType");
		String referenceno = "";
		double amount=0.0;
		double totalAmount=0.0;
		int id=0;
		ImpsResponse imps = new ImpsResponse();
		
		if(userDetails.getStatus().equals("1")){
			returnData.put("status", "0");
			returnData.put("message", "YOur ID is Inactive");
		}else{
		
		if(userDetails.getWlId().equals("MSR8TDF")){
			returnData.put("status", "0");
			returnData.put("message", "Failed");
		}else{
		parameter = new HashMap<String, Object>();
		parameter.put("user_id", userDetails.getUserId());
		parameter.put("service_type", "DMR");
		List<AssignedPackage> lista = assignedPackage
				.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", parameter);
	/*-------------------------------------------------------------------------------------------------------------------*/
		
		if(!lista.isEmpty()){
			
			parameter = new HashMap<String, Object>();
			parameter.put("api", "DMR");
			List<DMRCommission> opList =  DMRCommissionDao.getDMRCommissionByNamedQuery("getDMRCommissionByApi", parameter);
			if((!opList.isEmpty())) {
					
								
			
	
	//	---------------------------------------------------------------------------------------------------------------
		if (Double.parseDouble(transactionAmount) >= 10) {
			if (Double.parseDouble(transactionAmount) <= 5000) {
			parameters = new HashMap<String, String>();
			parameters.put("userId", userDetails.getUserId());
			double op_bal = customQueryServiceLogic
					.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser,parameters);
			if(Double.parseDouble(transactionAmount)>op_bal){
				returnData.put("message", "Amount is bigger than Your Balance");
				returnData.put("status", "0");
			}else{
			double remain = Double.parseDouble(transactionAmount);
			referenceno = GenerateRandomNumber.referenceNO();
			
		
				
				for(DMRCommission comm2 : opList){
					if(remain>=comm2.getSlab1() && remain<=comm2.getSlab2()){
						id = comm2.getId();
						break;
					}
				}
				/*---amount=10 ,apmahesbank = 6 ,gst=.90p, restamountgst=6-0.90=5.10 , retailer charge=4 ,  restamount=5.10-4=1.10 , retailer comm=1.10*40%=0.44 distributorcomm calcu 1.10*60%=0.66  dcomm=(0.66-0.44)=0.22-(retailer charge-distri charge)-likewise rest upline--------------*/
				double gst=0.0;
				double apmahesbank=remain/100;
				if(apmahesbank<6){
					apmahesbank = 6 ;
				}
				gst=apmahesbank-(apmahesbank/1.18);
				double restamountgst=apmahesbank-gst;
				
				
				if (userDetails.getRoleId() == 5) {
					pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id) ;
					if (pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pckret.getCharge() * remain) / 100;
					} else {
						charge = pckret.getCharge();
					}
					
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

				}	else if (userDetails.getRoleId() == 100) {
					pckapiu = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					if (pckapiu.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pckapiu.getCharge() * remain) / 100;
					} else {
						charge = pckapiu.getCharge();
					}

				}
				double restamount=restamountgst-charge;
		//		-------------------------------------COMMISSION---------------------------------------------------------------------
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
					retComm =(pckret.getComm()*restamount)/100;
					}else{
					retComm =pckret.getComm();	
					}
					comm = retComm;
					pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"DMR",id); 
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*restamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					sdComm =(pcksd.getComm()*restamount)/100;
					}else{
					sdComm = pcksd.getComm();	
					}
					if(!resellerId.equals("admin")){
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*restamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
					}
					
					
				} else if(userDetails.getRoleId() == 4) {
					//distId = userDetails.getUserId();	
					sdId = userDetails.getUplineId();	
					
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					pckdis=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id); 
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*restamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
					comm = dComm;
				
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					sdComm =(pcksd.getComm()*restamount)/100;
					}else{
					sdComm = pcksd.getComm();	
					}
					
					
					if(!resellerId.equals("admin")) {
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*restamount)/100;
					}else{
					resComm =pckres.getComm();	
					}
					
					}
					//System.out.println("resComm="+resComm);
					
				} else if(userDetails.getRoleId() == 3) {
					resellerId = userDetails.getUplineId();
					pcksd=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				     sdComm =(pcksd.getComm()*restamount)/100;
					}else{
						sdComm = pcksd.getComm();	
					}
					comm=sdComm;
					
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*restamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						
						}					
				}else if(userDetails.getRoleId() == 100) {
					pckapiu=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					if(pckapiu.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						apiComm =(pckapiu.getComm()*restamount)/100;
					}else{
						apiComm = pckapiu.getComm();	
					}
					comm = apiComm;
					
					}
				
	
				double lockbalance=userDetails.getLockedAmount();
				//double tds =comm-(comm/1.05);
				double usercom=comm/1.05;
				totalAmount = RoundNumber.roundDouble(remain + apmahesbank) ;
				parameters = new HashMap<String, String>();
				parameters.put("userId", userDetails.getUserId());
				 op_bal = customQueryServiceLogic
						.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser,parameters);
				double cl_bal = RoundNumber.roundDouble(op_bal - totalAmount);
				//System.out.println("cl_bal::::::"+cl_bal);
				if(lockbalance<=cl_bal){
				if (op_bal > totalAmount) {
					//DecimalFormat df2 = new DecimalFormat("#.##");
					amount =remain;
					flag = commissionService.updateBalance(userDetails.getUserId(),
							"Money Transfer - " + accountNumber, "Money Transfer", totalAmount, "DEBIT",0);
					if(usercom!=0){
						
						commissionService.updateBalance(userDetails.getUserId(),
								"Money Transfer - " + accountNumber, "Money Transfer Commission with 5% TDS", usercom, "CREDIT",0);
					}
					String input="{\"Username\":\""+LoginId+"\",\"Password\":\""+Password+"\",\"mobile\":\""+checkRemitterMobile+"\",\"bene_mobile\":\""+beneMobileNumber+"\",\"bene_name\":\""+bene_name+"\",\"bene_account\":\""+accountNumber+"\",\"IFSC_CODE\":\""+beneIFSCCode+"\",\"transferType\":\""+transferType+"\",\"id\":"+beneid+",\"amount\":"+transactionAmount+"}";
					//System.out.println("input::::::::::::::::::::"+input);
					imps  = EncoreWebServiceNew.impsMoneyTransferEncoreNew(input);
					logger_log.warn("returnData::::::::::::::::::::"+returnData);
					String today = GenerateRandomNumber.getCurrentDate();	
					String currentTime = GenerateRandomNumber.getCurrentTime();
				//	String transId = GenerateRandomNumber.generateTransactionNumber();
					String status="";
				
						
					if (imps.getStatus().equalsIgnoreCase("1")) {
						ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, referenceno, "SUCCESS",imps.getBankTransactionId(),usercom,transfertype,checkRemitterMobile,amount,gst,"DMR");
						flag=impsTransactiondao.insertImpsTransaction(imptrans);
						status="1";
					}else if(imps.getStatus().equalsIgnoreCase("703") || imps.getStatus().equalsIgnoreCase("0")){
						ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, referenceno, "FAILED",imps.getBankTransactionId(),usercom,transfertype,checkRemitterMobile,amount,gst,"DMR");
						flag=impsTransactiondao.insertImpsTransaction(imptrans);
						status="0";
					}else{
						ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, referenceno, "PENDING",imps.getBankTransactionId(),usercom,transfertype,checkRemitterMobile,amount,gst,"DMR");
						flag=impsTransactiondao.insertImpsTransaction(imptrans);
						status="2";
					}
			
					logger_log.warn("flag:::::::::::::::::"+flag);
					if(flag){
					if(!status.equalsIgnoreCase("0")){
					String FUNDTRANSNO= imps.getBankTransactionId();
					
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
								if (status.equals("SUCCESS")) {
									Gst gs=new Gst(userDetails.getUserId(), gst, "Money Transfer - " + accountNumber, GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime());
									GstDao.insertGst(gs);
									String reseller = "";
							
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
											if(sdCharge==0){sCharge=0;}
											else{sCharge = distCharge - sdCharge;}
											if(dcharge!=0){
												commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "CHARGE", dcharge, "CREDIT",0);
											}
											if(sCharge!=0){
												commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0);
											}
											
											if(!resellerId.equals("admin")) {
												reCharge = sdCharge - resCharge;
												if(reCharge!=0){
													commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0);
												}
												
											}
											
											if(dComm==0){disComm=0;}
											else{
											disComm=dComm-comm;
											}
											if(sdComm==0){sdisComm=0;}
											else{
											sdisComm = sdComm-dComm;
											}
																			
											commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "COMMISSION with 5% TDS", disComm-(disComm/1.05), "CREDIT",(disComm/1.05));
											commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION with 5% TDS", sdisComm-(sdisComm/1.05), "CREDIT",(sdisComm/1.05));
											if(!resellerId.equals("admin")) {
												if(resComm==0){reComm=0;}
												else{
												reComm = resComm-sdComm;
												}
												commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION with 5% TDS", reComm-(reComm/1.05), "CREDIT",(reComm/1.05));
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
										if(sCharge!=0){
											commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0);
										}													
									
									if(!resellerId.equals("admin")) {
										reCharge = sdCharge - resCharge;
										if(reCharge!=0){
										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0);
										}
									}
									
									if(sdComm==0){sdisComm=0;}
									else{
									sdisComm=sdComm-dComm;
									}
									
									
									commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION with 5% TDS", sdisComm-(sdisComm/1.05), "CREDIT",(sdisComm/1.05));
									if(!resellerId.equals("admin")) {
										if(resComm==0){reComm=0;}
										else{
										reComm = resComm-sdComm;
										}
										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION with 5% TDS", reComm-(reComm/1.05), "CREDIT",(reComm/1.05));
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
											if(reCharge!=0){
												commissionService.updateBalance(resellerId,
														"Money Transfer - " + accountNumber,
														"CHARGE", reCharge, "CREDIT",0);
											}
											
											
										
											if(resComm==0){reComm=0;}
											else{
											reComm=resComm-sdComm;
											}
											commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION with 5% TDS", reComm-(reComm/1.05), "CREDIT",(reComm/1.05));
										}
										
										
									}
									//impsTransactiondao.updateDmrTransactionBankTransactionId(transId,trans.getRequestRetrievalNumber());
									returnData.put("message", "Transaction Successful :"+FUNDTRANSNO);
									returnData.put("status", "1");

								}
								else if (status.equals("FAILED")) {
									commissionService.updateBalance(userDetails.getUserId(),
											"Money Transfer - " + accountNumber, "Money Transfer", totalAmount, "CREDIT",0);
									commissionService.updateBalance(userDetails.getUserId(),
											"Money Transfer - " + accountNumber, "Money Transfer COMMISSION with 5% TDS", usercom, "DEBIT",0);
									returnData.put("message", returnData.get("message"));
									returnData.put("status", "0");
								}
								//impsTransactiondao.updateDmrTransactionStatus(transId, status);
							
						}else{
							returnData.put("message", "Balance deduct Failed");
							returnData.put("status", "0");
						}
					
					
						
					
					}else{
						returnData.put("message",imps.getMessage());
						returnData.put("status", "0");	
					}
					}else{
						returnData.put("message","db update Failed.Please Contact to Developer soon.");
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
			//}
			}
			} else {
				returnData.put("message", "Amount should be maximum 5000");
				returnData.put("status", "0");
			}
		} else {
			returnData.put("message", "Amount should be minimum 10");
			returnData.put("status", "0");
		}
		}else{
			returnData.put("message", "Operator Is Not Available");
			returnData.put("status", "0");
		}
		}else {
			returnData.put("status", "0");
			returnData.put("message", "Please Assign Package");
			
		}
		
		}
		}
		}catch (Exception e) {
		logger_log.error("instantPayMoneytransfer::::::::::::::"+e);
		returnData.put("status", "0");
		returnData.put("message", e);
		}
		return returnData;
		}

	@Override
	public Map<String, Object> ValidateBeneficiaryEncoreNew(Map<String, String> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		boolean flag=false;
		try{
			if(user.getStatus().equals("1")){
				returnData.put("status", "0");
				returnData.put("message", "YOur ID is Inactive");
			}else{
			parameters = new HashMap<String, String>();
			parameters.put("userId", user.getUserId());
			double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser,parameters);
			double cl_bal = RoundNumber.roundDouble(op_bal - 1.0);
		if(cl_bal>0) {
		String input="{\"Username\":\""+LoginId+"\",\"Password\":\""+Password+"\",\"mobile\":\""+request.get("mobile")+"\",\"bene_mobile\":\""+request.get("bene_mobile")+"\",\"bene_name\":\""+request.get("name")+"\",\"bene_account\":\""+request.get("account")+"\",\"IFSC_CODE\":\""+request.get("ifsc")+"\"}";
		returnData =EncoreWebServiceNew.ValidateBeneficiaryEncoreNew(input);
		returnData.put("mobile",request.get("mobile"));
		if(returnData.get("status").toString().equalsIgnoreCase("1")){
		System.out.println("op_bal::::::"+op_bal);
		flag = commissionService.updateBalance(user.getUserId(),
				"Verify Beneficiary Account - " +request.get("account"), "Verify Beneficiary Account", 1.0, "DEBIT",0);
		}
		}else {
		returnData.put("status", "0");	
		returnData.put("message", "Insufficient Balance");	
		}
			}
		}catch (Exception e) {
		logger_log.error("ValidateBeneficiaryEncore:::::::::::::::::::::::::"+e);
		}
			return returnData;
		}

	@Override
	public Map<String, Object> impsMoneyTransferEncoreNew(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		double charge = 0.0;
		boolean flag=false;
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
		System.out.println("request::::::::::::"+request.get("transfertype"));
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
		
		String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
		try{
			if(userDetails.getStatus().equals("1")){
				returnData.put("status", "0");
				returnData.put("message", "YOur ID is Inactive");
			}else{
			if(userDetails.getWlId().equals("MSR8TDF")){
				returnData.put("status", "0");
				returnData.put("message", "Failed");
			}else{
			
		String beneMobileNumber = request.get("bene_mobile");
		String bene_name = request.get("name");
		String transfertype = "IMPS";
		String accountNumber = request.get("account");
		String beneIFSCCode = request.get("ifsc");
		String beneid = request.get("id");
		String transactionAmount=request.get("amount");
		String referenceno = "";
		double amount=0.0;
		double totalAmount=0.0;
		int id=0;
		ImpsResponse imps = new ImpsResponse();
		
		parameter = new HashMap<String, Object>();
		parameter.put("user_id", userDetails.getUserId());
		parameter.put("service_type", "DMR");
		List<AssignedPackage> lista = assignedPackage
				.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", parameter);
	/*-------------------------------------------------------------------------------------------------------------------*/
		
		if(!lista.isEmpty()){
			
			parameter = new HashMap<String, Object>();
			parameter.put("api", "DMR");
			List<DMRCommission> opList =  DMRCommissionDao.getDMRCommissionByNamedQuery("getDMRCommissionByApi", parameter);
			if((!opList.isEmpty())) {
					
								
			
	
	//	---------------------------------------------------------------------------------------------------------------
		if (Double.parseDouble(transactionAmount) >= 10) {
			if (Double.parseDouble(transactionAmount) <= 5000) {
			parameters = new HashMap<String, String>();
			parameters.put("userId", userDetails.getUserId());
			double op_bal = customQueryServiceLogic
					.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser,parameters);
			if(Double.parseDouble(transactionAmount)>op_bal){
				returnData.put("message", "Amount is bigger than Your Balance");
				returnData.put("status", "0");
			}else{
			double remain = Double.parseDouble(transactionAmount);
			referenceno = GenerateRandomNumber.referenceNO();
		
				
				for(DMRCommission comm2 : opList){
					if(remain>=comm2.getSlab1() && remain<=comm2.getSlab2()){
						id = comm2.getId();
						break;
					}
				}
				/*---amount=10 ,apmahesbank = 6 ,gst=.90p, restamountgst=6-0.90=5.10 , retailer charge=4 ,  restamount=5.10-4=1.10 , retailer comm=1.10*40%=0.44 distributorcomm calcu 1.10*60%=0.66  dcomm=(0.66-0.44)=0.22-(retailer charge-distri charge)-likewise rest upline--------------*/
				double gst=0.0;
				double apmahesbank=remain/100;
				if(apmahesbank<6){
					apmahesbank = 6 ;
				}
				gst=apmahesbank-(apmahesbank/1.18);
				double restamountgst=apmahesbank-gst;
				
				if (userDetails.getRoleId() == 5) {
					pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id) ;
					if (pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pckret.getCharge() * remain) / 100;
					} else {
						charge = pckret.getCharge();
					}
					
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

				}	else if (userDetails.getRoleId() == 100) {
					pckapiu = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					if (pckapiu.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pckapiu.getCharge() * remain) / 100;
					} else {
						charge = pckapiu.getCharge();
					}

				}
				
				double restamount=restamountgst-charge;
		//		-------------------------------------COMMISSION---------------------------------------------------------------------
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
					retComm =(pckret.getComm()*restamount)/100;
					}else{
					retComm =pckret.getComm();	
					}
					comm = retComm;
					pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"DMR",id); 
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*restamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					sdComm =(pcksd.getComm()*restamount)/100;
					}else{
					sdComm = pcksd.getComm();	
					}
					if(!resellerId.equals("admin")){
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*restamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
					}
					
					
				} else if(userDetails.getRoleId() == 4) {
					//distId = userDetails.getUserId();	
					sdId = userDetails.getUplineId();	
					
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					pckdis=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id); 
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*restamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
					comm = dComm;
				
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					sdComm =(pcksd.getComm()*restamount)/100;
					}else{
					sdComm = pcksd.getComm();	
					}
					
					
					if(!resellerId.equals("admin")) {
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*restamount)/100;
					}else{
					resComm =pckres.getComm();	
					}
					
					}
					//System.out.println("resComm="+resComm);
					
				} else if(userDetails.getRoleId() == 3) {
					resellerId = userDetails.getUplineId();
					pcksd=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				     sdComm =(pcksd.getComm()*restamount)/100;
					}else{
						sdComm = pcksd.getComm();	
					}
					comm=sdComm;
					
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*restamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						
						}					
				}else if(userDetails.getRoleId() == 100) {
					pckapiu=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					if(pckapiu.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						apiComm =(pckapiu.getComm()*restamount)/100;
					}else{
						apiComm = pckapiu.getComm();	
					}
					comm = apiComm;
					
					}
				
	
				double lockbalance=userDetails.getLockedAmount();
				//double tds =comm-(comm/1.05);
				double usercom=comm/1.05;
				totalAmount = RoundNumber.roundDouble(remain + apmahesbank) ;
				parameters = new HashMap<String, String>();
				parameters.put("userId", userDetails.getUserId());
				 op_bal = customQueryServiceLogic
						.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser,parameters);
				double cl_bal = RoundNumber.roundDouble(op_bal - totalAmount);
				//System.out.println("cl_bal::::::"+cl_bal);
				if(lockbalance<=cl_bal){
				if (op_bal > totalAmount) {
					amount =remain;
					flag = commissionService.updateBalance(userDetails.getUserId(),
							"Money Transfer - " + accountNumber, "Money Transfer", totalAmount, "DEBIT",0);
					if(usercom!=0){
						
						commissionService.updateBalance(userDetails.getUserId(),
								"Money Transfer - " + accountNumber, "Money Transfer Commission with 5% TDS", usercom, "CREDIT",0);
					}
					String input="{\"Username\":\""+LoginId+"\",\"Password\":\""+Password+"\",\"mobile\":\""+checkRemitterMobile+"\",\"bene_mobile\":\""+beneMobileNumber+"\",\"bene_name\":\""+bene_name+"\",\"bene_account\":\""+accountNumber+"\",\"IFSC_CODE\":\""+beneIFSCCode+"\",\"id\":"+beneid+",\"amount\":"+transactionAmount+"}";
					//System.out.println("input::::::::::::::::::::"+input);
					imps  = EncoreWebServiceNew.impsMoneyTransferEncoreNew(input);
					logger_log.warn("returnData::::::::::::::::::::"+returnData);
					String today = GenerateRandomNumber.getCurrentDate();	
					String currentTime = GenerateRandomNumber.getCurrentTime();
				//	String transId = GenerateRandomNumber.generateTransactionNumber();
					String status="";
				
						
					if (imps.getStatus().equalsIgnoreCase("1")) {
						ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, referenceno, "SUCCESS",imps.getBankTransactionId(),usercom,transfertype,checkRemitterMobile,amount,gst,"DMR");
						flag=impsTransactiondao.insertImpsTransaction(imptrans);
						status="1";
					}else if(imps.getStatus().equalsIgnoreCase("703") || imps.getStatus().equalsIgnoreCase("0")){
						ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, referenceno, "FAILED",imps.getBankTransactionId(),usercom,transfertype,checkRemitterMobile,amount,gst,"DMR");
						flag=impsTransactiondao.insertImpsTransaction(imptrans);
						status="0";
					}else{
						ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, referenceno, "PENDING",imps.getBankTransactionId(),usercom,transfertype,checkRemitterMobile,amount,gst,"DMR");
						flag=impsTransactiondao.insertImpsTransaction(imptrans);
						status="2";
					}
			
					logger_log.warn("flag:::::::::::::::::"+flag);
					if(flag){
					if(!status.equalsIgnoreCase("0")){
					String FUNDTRANSNO= imps.getBankTransactionId();
					
					
						
						
						
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
								if (status.equals("SUCCESS")) {
									Gst gs=new Gst(userDetails.getUserId(), gst, "Money Transfer - " + accountNumber, GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime());
									GstDao.insertGst(gs);
									String reseller = "";
							
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
											if(sdCharge==0){sCharge=0;}
											else{sCharge = distCharge - sdCharge;}
											
											commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "CHARGE", dcharge, "CREDIT",0);
											commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0);
											if(!resellerId.equals("admin")) {
												reCharge = sdCharge - resCharge;
												commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0);
											}
											
											if(dComm==0){disComm=0;}
											else{
											disComm=dComm-comm;
											}
											if(sdComm==0){sdisComm=0;}
											else{
											sdisComm = sdComm-dComm;
											}
																			
											commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "COMMISSION with 5% TDS", disComm-(disComm/1.05), "CREDIT",(disComm/1.05));
											commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION with 5% TDS", sdisComm-(sdisComm/1.05), "CREDIT",(sdisComm/1.05));
											if(!resellerId.equals("admin")) {
												if(resComm==0){reComm=0;}
												else{
												reComm = resComm-sdComm;
												}
												commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION with 5% TDS", reComm-(reComm/1.05), "CREDIT",(reComm/1.05));
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
																							
									commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0);
									if(!resellerId.equals("admin")) {
										reCharge = sdCharge - resCharge;
										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0);
									}
									
									if(sdComm==0){sdisComm=0;}
									else{
									sdisComm=sdComm-dComm;
									}
									
									
									commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION with 5% TDS", sdisComm-(sdisComm/1.05), "CREDIT",(sdisComm/1.05));
									if(!resellerId.equals("admin")) {
										if(resComm==0){reComm=0;}
										else{
										reComm = resComm-sdComm;
										}
										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION with 5% TDS", reComm-(reComm/1.05), "CREDIT",(reComm/1.05));
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
											
										
											if(resComm==0){reComm=0;}
											else{
											reComm=resComm-sdComm;
											}
											commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION with 5% TDS", reComm-(reComm/1.05), "CREDIT",(reComm/1.05));
										}
										
										
									}
									//impsTransactiondao.updateDmrTransactionBankTransactionId(transId,trans.getRequestRetrievalNumber());
									returnData.put("message", "Transaction Successful :"+FUNDTRANSNO);
									returnData.put("status", "1");

								}
								else if (status.equals("FAILED")) {
									
									commissionService.updateBalance(userDetails.getUserId(),
											"Money Transfer - " + accountNumber, "Money Transfer", totalAmount, "CREDIT",0);
									commissionService.updateBalance(userDetails.getUserId(),
											"Money Transfer - " + accountNumber, "Money Transfer COMMISSION with 5% TDS", usercom, "DEBIT",0);
									returnData.put("message", returnData.get("message"));
									returnData.put("status", "0");
								}
								//impsTransactiondao.updateDmrTransactionStatus(transId, status);
							
						}else{
							returnData.put("message", "Balance deduct Failed");
							returnData.put("status", "0");
						}
					
					
						
					
					}else{
						returnData.put("message",imps.getMessage());
						returnData.put("status", "0");	
					}
					}else{
						returnData.put("message","db update Failed.Please Contact to Developer soon.");
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
			//}
			}
			} else {
				returnData.put("message", "Amount should be maximum 5000");
				returnData.put("status", "0");
			}
		} else {
			returnData.put("message", "Amount should be minimum 10");
			returnData.put("status", "0");
		}
		}else{
			returnData.put("message", "Operator Is Not Available");
			returnData.put("status", "0");
		}
		}else {
			returnData.put("status", "0");
			returnData.put("message", "Please Assign Package");
			
		}
			}
			}
		
		}catch (Exception e) {
		logger_log.error("instantPayMoneytransfer::::::::::::::"+e);
		returnData.put("status", "0");
		returnData.put("message", e);
		}
		return returnData;
		}

	
	

}
