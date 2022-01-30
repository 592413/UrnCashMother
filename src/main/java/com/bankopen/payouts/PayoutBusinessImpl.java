package com.bankopen.payouts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bankopen.Services.OpenPayoutDao;

import com.bankopen.model.OpenPayout;
import com.bankopen.model.PaymentCustom;

import com.encorenew.moneyapi.EncoreWebServiceNew;
import com.recharge.businessdao.CommissionService;
import com.recharge.businessdao.CustomQueryServiceLogic;
import com.recharge.customModel.ImpsRequest;
import com.recharge.customModel.ImpsResponse;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.icicidmtmodel.ImpsSettlement;
import com.recharge.icicidmtmodel.P2AMoneyUser;
import com.recharge.icicidmtservicce.ICICIWebservice;
import com.recharge.icicidmtserviceDao.ImpsSettlementDao;
import com.recharge.icicidmtserviceDao.P2AMoneyUserDao;
import com.recharge.model.AEPSUserMap;
import com.recharge.model.AssignedPackage;
import com.recharge.model.DMRCommission;
import com.recharge.model.EarningSurcharge;
import com.recharge.model.ImpsTransaction;
import com.recharge.model.OpenAccount;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.SettlementBalance;
import com.recharge.model.SettlementCharge;
import com.recharge.model.SettlementReport;
import com.recharge.model.SmsCredential;
import com.recharge.model.User;
import com.recharge.model.UserBankDetails;
import com.recharge.servicedao.AEPSUserMapDao;
import com.recharge.servicedao.AssignedPackageDAO;
import com.recharge.servicedao.DMRCommissionDao;
import com.recharge.servicedao.EarningSurchargeDao;
import com.recharge.servicedao.ImpsTransactionService;
import com.recharge.servicedao.OpenAccountDao;
import com.recharge.servicedao.SettlementBalanceDao;
import com.recharge.servicedao.SettlementChargeDao;
import com.recharge.servicedao.SettlementReportDao;
import com.recharge.servicedao.SmsCredentialDao;
import com.recharge.servicedao.UserBankDetailsDao;
import com.recharge.servicedao.UserDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.RoundNumber;
import com.recharge.utill.SMS;

@Service("PayoutBusinessDao")
public class PayoutBusinessImpl implements PayoutBusinessDao{
	private static final Logger logger_log = Logger.getLogger(PayoutBusinessDao.class);
	
	@Autowired UserBankDetailsDao UserBankDetailsDao;
	@Autowired OpenPayoutDao OpenPayoutDao;
	@Autowired SmsCredentialDao smsCredentialDao;
	@Autowired SettlementChargeDao settlementchargrdao;
	@Autowired SettlementBalanceDao settlementbalancedao;
	@Autowired SettlementReportDao settlementreportdao;
	@Autowired CustomQueryServiceLogic customQueryServiceLogic;
	@Autowired AssignedPackageDAO assignedPackage;
	@Autowired DMRCommissionDao DMRCommissionDao;
	@Autowired CommissionService commissionService;
	@Autowired UserDao userDao;
	@Autowired OpenAccountDao OpenAccountDao;
	@Autowired P2AMoneyUserDao P2AMoneyUserDao;
	@Autowired SmsCredentialDao SmsCredentialDao;
	@Autowired EarningSurchargeDao earningSurchargeDao;
	@Autowired ImpsTransactionService impsTransactiondao;
	@Autowired AEPSUserMapDao aepsuserdao;
	@Autowired ImpsSettlementDao impssettlementDao;

	@Override
	public synchronized Map<String, Object> initiatePayout(Map<String,String>  request, User userDetails) {
		Map<String, Object> returnData= new HashMap<String, Object>();	
	Map<String, Object> param= new HashMap<String, Object>();	
	double settleopbal=0.0;
	double settleclbal=0.0;
	double settlementamount = 0.0;
	double settlementcharge = 0.0;
	String TranRef="";
	try{
		if(userDetails.getStatus().equals("1")){
			returnData.put("status", "0");
			returnData.put("message", "YOur ID is Inactive");
		}else{
		param = new HashMap<String, Object>();
		param.put("username",userDetails.getUserName());
		param.put("api","Encore");
		List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
		if(!list2.isEmpty()) {
	String userId=userDetails.getUserId();	
	String normaldate = GenerateRandomNumber.getCurrentDate();
	
	
	
	settlementamount=Double.parseDouble(request.get("amount"));
	
	
	param= new HashMap<String, Object>();		
	param.put("userId", userDetails.getUserId());
	List<P2AMoneyUser> list=P2AMoneyUserDao.getP2AMoneyUserByNamedQuery("getP2AUserbyuserId",param);
	SettlementReport settlew2=null;
	if(!list.isEmpty()){
		AEPSUserMap aeps=list2.get(0);	 	
		P2AMoneyUser pmu=list.get(0);
		String TranRefNo=GenerateRandomNumber.generateIPtid(pmu.getRemMobile());
		
		
		if(pmu.isActive()){
		param= new HashMap<String, Object>();
		param.put("username",userDetails.getUserId());
		List<SettlementBalance> settlelist=settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param);
		if(!settlelist.isEmpty()){
			
		SettlementBalance settle=settlelist.get(0);	
		settleopbal=settle.getSettlementbalance();
		param = new HashMap<String, Object>();
		param.put("api","ICICIP2A");
		List<SettlementCharge> settlecharge = settlementchargrdao.getSettlementChargeByNamedQuery("getSettlementChargebyapi",param);
		for(SettlementCharge settle2:settlecharge){
			if(settle2.getSlab1()<=settlementamount && settle2.getSlab2()>=settlementamount){
				settlementcharge = settle2.getCharge();	
			}
			
		}
		System.out.println(settlementcharge);
		settleclbal=settleopbal-(Double.parseDouble(request.get("amount")));
		Map<String, String>	parameters = new HashMap<String, String>();
		parameters.put("userId", userDetails.getUserId());
		 settleopbal = customQueryServiceLogic
				.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForsettlement,parameters);
		 settleclbal=settleopbal-(Double.parseDouble(request.get("amount")));
		if(settleclbal>0){
			List<OpenAccount> adminbanks=OpenAccountDao.getAllOpenAccount();
			String adminbank=adminbanks.get(0).getAccountno();
			 String refno=GenerateRandomNumber.generateTrannNumber();
			 OpenPayout OpenPayout=new OpenPayout(userDetails.getUserId(), pmu.getBeneAccNo(), pmu.getBeneIFSC(), pmu.getRemName(), userDetails.getEmail(), pmu.getRemMobile(), adminbank, request.get("trantype"), request.get("amount"), refno, request.get("remarks"), "PENDING", GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(), request.get("source"), userDetails.getWlId(),"-");
			 boolean flag=OpenPayoutDao.insertOpenPayout(OpenPayout);
			 if(flag){
				 double sendamount=Double.parseDouble(request.get("amount"))-settlementcharge;
				 	parameters = new HashMap<String, String>();
					parameters.put("userId", userDetails.getUserId());
					double settlementamountopen = customQueryServiceLogic
							.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForsettlement,parameters);
					settleclbal=settlementamountopen-settlementamount;
					
					 
					 ImpsSettlement impssettle=new ImpsSettlement(userId,"-",TranRef,Double.parseDouble(request.get("amount"))-settlementcharge, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"PENDING");
					   impssettlementDao.insertImpsSettlement(impssettle);
				    	 
				      settle.setSettlementbalance(settleclbal);  
				      settlementbalancedao.updateSettlementBalance(settle);
				      SettlementReport settlere=new SettlementReport(userDetails.getUserId(), settleopbal, settleclbal, Double.parseDouble(request.get("amount"))-settlementcharge,normaldate, GenerateRandomNumber.getCurrentTime(),"Settle to Bank","SUCCESS","SUCCESS");
				    
				      settlementreportdao.insertSettlementReport(settlere);
				      int acamount=Integer.parseInt(request.get("amount"))-(int)settlementcharge;
				      PaymentCustom pc=new PaymentCustom(pmu.getBeneAccNo(),pmu.getBeneIFSC(),pmu.getRemName(),userDetails.getEmail(),pmu.getRemMobile(),adminbank,request.get("trantype"),Integer.toString(acamount),refno,"P2A");
						 logger_log.warn(pc.toString());
						 param = new HashMap<String, Object>();	
							param.put("refid", refno);
							List<OpenPayout> paylist=OpenPayoutDao.getOpenPayoutByNamedQuery("getOpenPayoutbyref", param);
							if(paylist.isEmpty()){
								returnData.put("message","Failed");
								returnData.put("status","0");
							}else{
								OpenPayout op=paylist.get(0);
								String reponse= OpenRestWebServices.initiatepayout(pc);
								JSONObject obj=new JSONObject(reponse);
								String rrn="";
								if(obj.getInt("status")==200){
									
									JSONObject data=obj.getJSONObject("data");
									 if(data.isNull("bank_transaction_ref_id")) {
										 rrn=data.getString("open_transaction_ref_id");
										    }else {
										    	rrn=data.getString("bank_transaction_ref_id");	
										    }
									op.setStatus("SUCCESS");
									op.setRnn(rrn);
									OpenPayoutDao.updateOpenPayout(op);
									param = new HashMap<String, Object>();
									param.put("TranRefNo", TranRef);
									param.put("userId", userDetails.getUserId());
									List<ImpsSettlement> uplist=impssettlementDao.getImpsSettlementByNamedQuery("getImpsSettlementTranRefNo", param);
									ImpsSettlement impss=uplist.get(0);
									impss.setBankRRN(rrn);impss.setStatus("SUCCESS");
									impssettlementDao.updateImpsSettlement(impss);
									
									returnData.put("rnn", rrn);
									returnData.put("servicecharge", settlementcharge);
									returnData.put("closingBalance", settleclbal);
									returnData.put("message","Transaction Successful");
									returnData.put("status","1");
								
									

									 returnData.put("message","SUCCESSFULL");
										returnData.put("status","1");
								}else if(obj.getInt("status")==422){
									
									param = new HashMap<String, Object>();	
									param.put("username", userDetails.getUserName());
									SettlementBalance SettlementBalance1  = 	settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param).get(0);
									settlementamountopen=SettlementBalance1.getSettlementbalance();
									double settlementamountnew = SettlementBalance1.getSettlementbalance()+settlementamount;
									 SettlementBalance1.setSettlementbalance(settlementamountnew);
									 flag=settlementbalancedao.updateSettlementBalance(SettlementBalance1);
									SettlementReport settle23 = new SettlementReport(userDetails.getUserName(), settlementamountopen,settlementamountnew, settlementamount-settlementcharge,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"Settle to Bank","FAILED","REVERT");
									flag=settlementreportdao.insertSettlementReport(settle23);
									op.setStatus("FAILED");
									op.setRnn(rrn);
									OpenPayoutDao.updateOpenPayout(op);
									
									param = new HashMap<String, Object>();
									param.put("TranRefNo", TranRef);
									param.put("userId", userDetails.getUserId());
									List<ImpsSettlement> uplist=impssettlementDao.getImpsSettlementByNamedQuery("getImpsSettlementTranRefNo", param);
									ImpsSettlement impss=uplist.get(0);
									impss.setBankRRN(rrn);impss.setStatus("FAILED");
									impssettlementDao.updateImpsSettlement(impss);
								
									
									 if(userDetails.getWlId().startsWith("ASR")){
											param = new HashMap<String, Object>();
											param.put("wlId",userDetails.getWlId());
											List<User> wuser=userDao.getUserByNamedQuery("getWILDbyID", param);
											param = new HashMap<String, Object>();
											param.put("username",wuser.get(0).getUserId());
											List<SettlementBalance> wsettlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
												SettlementBalance SettlementBalance2 = wsettlelist.get(0);
												double settlementamountprev1=0.0;
												settlementamountprev1 = SettlementBalance2.getSettlementbalance();
												/*pckres = commissionService.getPackagewiseCommisionOnOperator(wuser.get(0).getUserId(),"SETTLEMENT",id) ;
												double charge=0.0;
												if (pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
													charge = (pckres.getCharge() * settlementamount) / 100;
												} else {
													charge = pckres.getCharge();
												}*/
												double settlementamountnew1 = settlementamountprev1+Double.parseDouble(request.get("amount"));
												
												SettlementBalance2.setSettlementbalance(settlementamountnew1);
												settlementbalancedao.updateSettlementBalance(SettlementBalance2);
												SettlementReport settlew3 = new SettlementReport(wuser.get(0).getUserId(),settlementamountprev1,settlementamountnew1,Double.parseDouble(request.get("amount"))-settlementcharge,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"Settle to Bank","FAILED","REVERT");
											settlementreportdao.insertSettlementReport(settlew3);
											
										}
									returnData.put("message",obj.getString("message"));
									returnData.put("status","0");
								}else if(obj.getInt("status")==401){
									param = new HashMap<String, Object>();	
									param.put("username", userDetails.getUserName());
									SettlementBalance SettlementBalance1  = 	settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param).get(0);
									settlementamountopen=SettlementBalance1.getSettlementbalance();
									double settlementamountnew = SettlementBalance1.getSettlementbalance()+settlementamount;
									 SettlementBalance1.setSettlementbalance(settlementamountnew);
									 flag=settlementbalancedao.updateSettlementBalance(SettlementBalance1);
									SettlementReport settle23 = new SettlementReport(userDetails.getUserName(), settlementamountopen,settlementamountnew, settlementamount-settlementcharge,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"Settle to Bank","FAILED","REVERT");
									//settle23.setApi("OPEN Settlement");
									flag=settlementreportdao.insertSettlementReport(settle23);
									op.setStatus("FAILED");
									OpenPayoutDao.updateOpenPayout(op);
									param = new HashMap<String, Object>();
									param.put("TranRefNo", TranRef);
									param.put("userId", userDetails.getUserId());
									List<ImpsSettlement> uplist=impssettlementDao.getImpsSettlementByNamedQuery("getImpsSettlementTranRefNo", param);
									ImpsSettlement impss=uplist.get(0);
									impss.setBankRRN(rrn);impss.setStatus("FAILED");
									impssettlementDao.updateImpsSettlement(impss);
									
									 if(userDetails.getWlId().startsWith("ASR")){
											param = new HashMap<String, Object>();
											param.put("wlId",userDetails.getWlId());
											List<User> wuser=userDao.getUserByNamedQuery("getWILDbyID", param);
											param = new HashMap<String, Object>();
											param.put("username",wuser.get(0).getUserId());
											List<SettlementBalance> wsettlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
												SettlementBalance SettlementBalance2 = wsettlelist.get(0);
												double settlementamountprev1=0.0;
												settlementamountprev1 = SettlementBalance2.getSettlementbalance();
												/*pckres = commissionService.getPackagewiseCommisionOnOperator(wuser.get(0).getUserId(),"SETTLEMENT",id) ;
												double charge=0.0;
												if (pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
													charge = (pckres.getCharge() * settlementamount) / 100;
												} else {
													charge = pckres.getCharge();
												}*/
												double settlementamountnew1 = settlementamountprev1+Double.parseDouble(request.get("amount"));
												
												SettlementBalance2.setSettlementbalance(settlementamountnew1);
												settlementbalancedao.updateSettlementBalance(SettlementBalance2);
												SettlementReport settlew3 = new SettlementReport(wuser.get(0).getUserId(),settlementamountprev1,settlementamountnew1,Double.parseDouble(request.get("amount"))-settlementcharge,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"Settle to Bank","FAILED","REVERT");
											//	settlew2.setApi("OPEN Settlement");
											settlementreportdao.insertSettlementReport(settlew3);
											
										}
									returnData.put("message",obj.getString("message"));
									returnData.put("status","0");
								
								}else if(obj.getInt("status")==502){
									param = new HashMap<String, Object>();	
									param.put("username", userDetails.getUserName());
									SettlementBalance SettlementBalance1  = 	settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param).get(0);
									settlementamountopen=SettlementBalance1.getSettlementbalance();
									double settlementamountnew = SettlementBalance1.getSettlementbalance()+settlementamount;
									 SettlementBalance1.setSettlementbalance(settlementamountnew);
									 flag=settlementbalancedao.updateSettlementBalance(SettlementBalance1);
									SettlementReport settle23 = new SettlementReport(userDetails.getUserName(), settlementamountopen,settlementamountnew, settlementamount-settlementcharge,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"Settle to Bank","FAILED","REVERT");
									//settle23.setApi("OPEN Settlement");
									flag=settlementreportdao.insertSettlementReport(settle23);
									op.setStatus("FAILED");
									OpenPayoutDao.updateOpenPayout(op);
									
									param = new HashMap<String, Object>();
									param.put("TranRefNo", TranRef);
									param.put("userId", userDetails.getUserId());
									List<ImpsSettlement> uplist=impssettlementDao.getImpsSettlementByNamedQuery("getImpsSettlementTranRefNo", param);
									ImpsSettlement impss=uplist.get(0);
									impss.setBankRRN(rrn);impss.setStatus("FAILED");
									impssettlementDao.updateImpsSettlement(impss);									 if(userDetails.getWlId().startsWith("ASR")){
											param = new HashMap<String, Object>();
											param.put("wlId",userDetails.getWlId());
											List<User> wuser=userDao.getUserByNamedQuery("getWILDbyID", param);
											param = new HashMap<String, Object>();
											param.put("username",wuser.get(0).getUserId());
											List<SettlementBalance> wsettlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
												SettlementBalance SettlementBalance2 = wsettlelist.get(0);
												double settlementamountprev1=0.0;
												settlementamountprev1 = SettlementBalance2.getSettlementbalance();
												/*pckres = commissionService.getPackagewiseCommisionOnOperator(wuser.get(0).getUserId(),"SETTLEMENT",id) ;
												double charge=0.0;
												if (pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
													charge = (pckres.getCharge() * settlementamount) / 100;
												} else {
													charge = pckres.getCharge();
												}*/
												double settlementamountnew1 = settlementamountprev1+Double.parseDouble(request.get("amount"));
												
												SettlementBalance2.setSettlementbalance(settlementamountnew1);
												settlementbalancedao.updateSettlementBalance(SettlementBalance2);
												SettlementReport settlew3 = new SettlementReport(wuser.get(0).getUserId(),settlementamountprev1,settlementamountnew1,Double.parseDouble(request.get("amount"))-settlementcharge,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"Settle to Bank","FAILED","REVERT");
											//	settlew2.setApi("OPEN Settlement");
											settlementreportdao.insertSettlementReport(settlew3);
											
										}
									returnData.put("message",obj.getString("message"));
									returnData.put("status","0");
								}else{
									returnData.put("message","Failed");
									returnData.put("status","0");
								}
								
							}
			 }
		
		
		}else{
		returnData.put("message","Inefficient Settlement Balance");	
		returnData.put("status","0");	
		}
	}else{
	returnData.put("message","Inefficient Settlement Balance");	
	returnData.put("status","0");
	}
	}else{
	returnData.put("message","You are not active");	
	returnData.put("status","0");
	}      
	}
	}else {
	returnData.put("status","0");
	returnData.put("message","You are not active agent");	
	}
		}
	}catch (Exception e) {
		logger_log.error("p2amoneytransfer::::::::::::::: "+e);
	}
	return returnData;
}
	

	@Override
	public List<OpenPayout> getPayoutReport(Map<String,String>  request, User userDetails) {
		List<OpenPayout> list=new ArrayList<>();
		try{
			Map<String, String> param = new HashMap<String, String>();
			param.put("startdate", request.get("startDate"));
			param.put("enddate", request.get("endDate"));
			if(userDetails.getRoleId()==1){
				list=customQueryServiceLogic.getOpenPayoutreport(CustomSqlQuery.getPayoutAdmin, param);
			}else if(userDetails.getRoleId()==2){
				param.put("wild", userDetails.getWlId());
				list=customQueryServiceLogic.getOpenPayoutreport(CustomSqlQuery.getPayoutwl, param);
			}else{
				param.put("userId", userDetails.getUserId());
				list=customQueryServiceLogic.getOpenPayoutreport(CustomSqlQuery.getPayoutuser, param);
			}
		}catch(Exception e){
			logger_log.error("getPayoutReport ::::::::::: "+e);	
			
		}
		return list;
	}
	
	@Override
	public Map<String, Object> getPayoutStatus(Map<String,String>  request, User userDetail) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();	
		try{
			System.out.println(request);
			param = new HashMap<String, Object>();	
			param.put("refid", request.get("refid"));
			List<OpenPayout> paylist=OpenPayoutDao.getOpenPayoutByNamedQuery("getOpenPayoutbyref", param);
			System.out.println(paylist.size());
			if(!paylist.isEmpty()){
				OpenPayout OpenPayout=paylist.get(0);
				OpenPayout.setRemark("MANUAL");
			//	OpenPayout.setManudate(GenerateRandomNumber.getCurrentDate());
				String prevstatus=OpenPayout.getStatus();
				System.out.println(prevstatus);
				if(request.get("status").equals("SUCCESS")){
					OpenPayout.setStatus("SUCCESS");
					OpenPayoutDao.updateOpenPayout(OpenPayout);
					returnData.put("status", "1");
					returnData.put("message", "SUCCESS");
					if(prevstatus.equals("FAILED")){
					double settlementamount=Double.parseDouble(OpenPayout.getAmount());
					double settlementcharge=0.0;
					param = new HashMap<String, Object>();	
					param.put("username", request.get("userId"));
					SettlementBalance SettlementBalance  = 	settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param).get(0);
					
					if(OpenPayout.getTrantype().equals("4")){
						if(settlementamount<=25000){
							settlementcharge=5;
						}else{
							settlementcharge=10;
						}
						
					}if(request.get("trantype").equals("2")){
						settlementcharge=5;
					}
					/*param = new HashMap<String, Object>();
					param.put("api","ICICI");
					List<SettlementCharge> settlecharge = settlementchargrdao.getSettlementChargeByNamedQuery("getSettlementChargebyapi",param);
					for(SettlementCharge settle2:settlecharge){
						if(settle2.getSlab1()<=settlementamount && settle2.getSlab2()>=settlementamount){
							settlementcharge = settle2.getCharge();	
						}
						
					}*/
				
					double openbl=SettlementBalance.getSettlementbalance();
					double settlementamountnew = openbl-settlementamount;
					SettlementBalance.setSettlementbalance(settlementamountnew);
					boolean flag=settlementbalancedao.updateSettlementBalance(SettlementBalance);
					SettlementReport settle2 = new SettlementReport(request.get("userId"), openbl,settlementamountnew, settlementamount-settlementcharge,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"Settle to Bank","SUCCESS","Manual SUCCESS");
					flag=settlementreportdao.insertSettlementReport(settle2);
					User uu=userDao.getUserByUserId(request.get("userId"));
					 if(uu.getWlId().startsWith("ASR")){
							param = new HashMap<String, Object>();
							param.put("wlId",uu.getWlId());
							List<User> wuser=userDao.getUserByNamedQuery("getWILDbyID", param);
							param = new HashMap<String, Object>();
							param.put("username",wuser.get(0).getUserId());
							List<SettlementBalance> wsettlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
								SettlementBalance SettlementBalance1 = wsettlelist.get(0);
								double settlementamountprev1=0.0;
								settlementamountprev1 = SettlementBalance1.getSettlementbalance();
								
								double settlementamountnew1 = settlementamountprev1-Double.parseDouble(request.get("amount"));
								
								SettlementBalance1.setSettlementbalance(settlementamountnew1);
								settlementbalancedao.updateSettlementBalance(SettlementBalance1);
								SettlementReport settlew2 = new SettlementReport(wuser.get(0).getUserId(),settlementamountprev1,settlementamountnew1,settlementamount-settlementcharge,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"Settle to Bank","SUCCESS","Manual SUCCESS");
							settlementreportdao.insertSettlementReport(settlew2);
							
						}
					
				}
					
					
				}else{
					OpenPayout.setStatus("FAILED");
					OpenPayoutDao.updateOpenPayout(OpenPayout);
					returnData.put("message", "SUCCESS");
					returnData.put("status", "0");
					
					double settlementamount=Double.parseDouble(OpenPayout.getAmount());
					double settlementcharge=0.0;
					param = new HashMap<String, Object>();	
					param.put("username", request.get("userId"));
					SettlementBalance SettlementBalance  = 	settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param).get(0);
					param = new HashMap<String, Object>();
					param.put("api","Skypoint");
					
					List<SettlementCharge> settlecharge = settlementchargrdao.getSettlementChargeByNamedQuery("getSettlementChargebyapi",param);
					for(SettlementCharge settle:settlecharge){
						if(settle.getSlab1()<=settlementamount && settle.getSlab2()>=settlementamount){
							settlementcharge = settle.getCharge();	
						}
					}
					double openbl=SettlementBalance.getSettlementbalance();
					double settlementamountnew = openbl+settlementamount;
					SettlementBalance.setSettlementbalance(settlementamountnew);
					boolean flag=settlementbalancedao.updateSettlementBalance(SettlementBalance);
					SettlementReport settle2 = new SettlementReport(request.get("userId"), openbl,settlementamountnew, settlementamount-settlementcharge,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"Settle to Bank","FAILED","Manual REVERT");
					flag=settlementreportdao.insertSettlementReport(settle2);
			
					User uu=userDao.getUserByUserId(request.get("userId"));
					 if(uu.getWlId().startsWith("ASR")){
							param = new HashMap<String, Object>();
							param.put("wlId",uu.getWlId());
							List<User> wuser=userDao.getUserByNamedQuery("getWILDbyID", param);
							param = new HashMap<String, Object>();
							param.put("username",wuser.get(0).getUserId());
							List<SettlementBalance> wsettlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
								SettlementBalance SettlementBalance1 = wsettlelist.get(0);
								double settlementamountprev1=0.0;
								settlementamountprev1 = SettlementBalance1.getSettlementbalance();
								
								double settlementamountnew1 = settlementamountprev1+Double.parseDouble(request.get("amount"));
								
								SettlementBalance1.setSettlementbalance(settlementamountnew1);
								settlementbalancedao.updateSettlementBalance(SettlementBalance1);
								SettlementReport settlew2 = new SettlementReport(wuser.get(0).getUserId(),settlementamountprev1,settlementamountnew1,settlementamount-settlementcharge,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"Settle to Bank","FAILED","Manual REVERT");
							settlementreportdao.insertSettlementReport(settlew2);
							
						}
					
				}
			}else{
				returnData.put("message", "Invalid Reference Id");
				returnData.put("status", "0");
			}
			
		}catch(Exception e){
			logger_log.error("getPayoutStatus ::::::::::: "+e);	
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
	}
	
	
	@Override
	public Map<String, Object> payoutOtp( User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		try{
			OpenRestWebServices.payoutOtp();
			
		}catch(Exception e){
			logger_log.error("payoutOtp ::::::::::: "+e);	
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
	}



	@Override
	public Map<String, Object> MoneytransferOPEN(Map<String, String> request, User userDetails) {
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
		PackageWiseChargeComm pcksubret=new PackageWiseChargeComm();
		System.out.println("request::::::::::::"+request);
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
		String bankrefno = "";
		try{
		String beneMobileNumber = request.get("mobile");
		String checkRemitterMobile = request.get("remmobile");
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
			if (Double.parseDouble(transactionAmount) <= 25000) {
				
				double temp = Double.parseDouble(transactionAmount);
				double remain = 0.0;
			
				while (temp > 0) {
					if (temp > 5000) {
						temp = temp - 5000;
						remain = 5000;
					} else {
						remain = temp;
						temp = 0.0;
						
					}
					
					parameters = new HashMap<String, String>();
					parameters.put("userId", userDetails.getUserId());
					double op_bal = customQueryServiceLogic
							.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser,parameters);
					if(Double.parseDouble(transactionAmount)>op_bal){
						returnData.put("message", "Amount is bigger than Your Balance");
						returnData.put("status", "0");
					}else{
					//	double remain=Double.parseDouble(transactionAmount);

						
						for(DMRCommission comm2 : opList){
							if(remain>=comm2.getSlab1() && remain<=comm2.getSlab2()){
								id = comm2.getId();
								break;
							}
						}
						
						if (userDetails.getRoleId() == 6) {
							pcksubret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id) ;
							if (pcksubret.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
								charge = (pcksubret.getCharge() * remain) / 100;
							} else {
								charge = pcksubret.getCharge();
							}
							
							System.out.println("charge="+charge);
						}
						else if (userDetails.getRoleId() == 5) {
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
						
				//		-------------------------------------COMMISSION---------------------------------------------------------------------
						String rId="";
						if(userDetails.getRoleId() == 6) {
							//Retailer Id
							rId=userDetails.getUplineId();
							// Distributor Id
							
							parameters = new HashMap<String, String>();
							parameters.put("userId", rId);	
							distId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
							
							// Super Distributor Id
							parameters = new HashMap<String, String>();
							parameters.put("userId", distId);	
							sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
							
							// Reseller Id
							parameters = new HashMap<String, String>();
							parameters.put("userId", sdId);							
							resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
							
							pcksubret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id) ;
							if (pcksubret.getComm_type().equalsIgnoreCase("PERCENTAGE")) {
								comm = (pcksubret.getComm() * remain) / 100;
							} else {
								comm = pcksubret.getComm();
							}
							
							pckret=commissionService.getPackagewiseCommisionOnOperator(rId,"DMR",id);
							if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							retComm =(pckret.getComm()*remain)/100;
							}else{
							retComm =pckret.getComm();	
							}
							
						//	System.out.println("reseller=="+comm);
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"DMR",id); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*remain)/100;
							}else{
							dComm=pckdis.getComm();	
							}
						//	System.out.println("dComm=="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*remain)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
						//	System.out.println("sdComm=="+sdComm);
							if(!resellerId.equals("admin")){
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*remain)/100;
							}else{
							resComm =pckres.getComm();	
							}
							}
						//	System.out.println("resComm=="+resComm);
							
						}
						else if(userDetails.getRoleId() == 5) {
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
							retComm =(pckret.getComm()*remain)/100;
							}else{
							retComm =pckret.getComm();	
							}
							comm = retComm;
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"DMR",id); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*remain)/100;
							}else{
							dComm=pckdis.getComm();	
							}
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*remain)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							if(!resellerId.equals("admin")){
								pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								resComm =(pckres.getComm()*remain)/100;
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
							dComm =(pckdis.getComm()*remain)/100;
							}else{
							dComm=pckdis.getComm();	
							}
							comm = dComm;
						
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*remain)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							
							
							if(!resellerId.equals("admin")) {
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*remain)/100;
							}else{
							resComm =pckres.getComm();	
							}
							
							}
							//System.out.println("resComm="+resComm);
							
						} else if(userDetails.getRoleId() == 3) {
							resellerId = userDetails.getUplineId();
							pcksd=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						     sdComm =(pcksd.getComm()*remain)/100;
							}else{
								sdComm = pcksd.getComm();	
							}
							comm=sdComm;
							
							if(!resellerId.equals("admin")) {
								pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								resComm =(pckres.getComm()*remain)/100;
								}else{
								resComm =pckres.getComm();	
								}
								
								}					
						}else if(userDetails.getRoleId() == 100) {
							pckapiu=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
							if(pckapiu.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								apiComm =(pckapiu.getComm()*remain)/100;
							}else{
								apiComm = pckapiu.getComm();	
							}
							comm = apiComm;
							
							}
						
						double gst=0.0;
						gst=(charge*18)/100;
						double lockbalance=userDetails.getLockedAmount();
						//double tds =comm-(comm/1.05);
						//double usercom=comm/1.05;
						totalAmount = RoundNumber.roundDouble(remain + charge+gst) ;
						parameters = new HashMap<String, String>();
						parameters.put("userId", userDetails.getUserId());
						 op_bal = customQueryServiceLogic
								.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser,parameters);
						double cl_bal = RoundNumber.roundDouble(op_bal - totalAmount);
						//System.out.println("cl_bal::::::"+cl_bal);
						if(lockbalance<=cl_bal){
						if (op_bal > totalAmount) {
							boolean flag5=false;
							if(userDetails.getWlId().startsWith("ASR")){
								Map<String, Object> param = new HashMap<String, Object>();
								param.put("wlId", userDetails.getWlId());
								List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
								parameters = new HashMap<String, String>();
								parameters.put("userId", whlist.get(0).getUserId());	
								double op_bal1 = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters );
								double cl_bal1=op_bal1-(remain+gst);
								if(whlist.get(0).getLockedAmount()<=cl_bal1){
									flag5=true;
								}else{
									flag5=false;
								}
							}else{
								flag5=true;
							}
							if(!flag5)
							{
								returnData.put("status", "0");
								returnData.put("message", "Technical Error Please Contact to Admin!");
							}else{
								String TranRefNo = GenerateRandomNumber.generateIPtid(checkRemitterMobile);
							amount =remain;
							

							flag = commissionService.updateBalance(userDetails.getUserId(),
									"Money Transfer - " + accountNumber, "Money Transfer", totalAmount, "DEBIT",0);
							
							if(comm!=0){
								//usercom=usercom-charge;
								commissionService.updateBalance(userDetails.getUserId(),
										"Money Transfer - " + accountNumber, "Money Transfer Commission", comm, "CREDIT",0);
							}
							
							if(userDetails.getWlId().startsWith("ASR")){
								double reseCharge=0.0;
								double resCom=0.0;
								Map<String, Object> param = new HashMap<String, Object>();
								param.put("wlId", userDetails.getWlId());
								List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
								User whuser=whlist.get(0);
								pckres=commissionService.getPackagewiseCommisionOnOperator(whuser.getUserId(),"DMR",id);
								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
									reseCharge = (pckres.getCharge()*remain)/100;
								
								}else{
									reseCharge =	pckres.getCharge();
								}
								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
									resCom=(pckres.getComm()*remain)/100;
								}else{
									resCom=pckres.getComm();
								}
								
								double whtamnt=(remain+reseCharge+gst)-resCom;
								flag = commissionService.updateBalance(whlist.get(0).getUserId(), "Money Transfer - " + accountNumber, "Money Transfer", whtamnt, "DEBIT",0);
								EarningSurcharge earningSurcharge41 = new EarningSurcharge(2, userDetails.getWlId(), whlist.get(0).getUserId(), resCom,reseCharge, "Money Transfer - " + accountNumber, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge41);
							}
							
							
							
							List<OpenAccount> adminbanks=OpenAccountDao.getAllOpenAccount();
							String adminbank=adminbanks.get(0).getAccountno();
							try{
							PaymentCustom pc = new PaymentCustom(accountNumber, beneIFSCCode, bene_name,
									"samadhan@gmail.com", checkRemitterMobile, adminbank, request.get("sendType"),
									Double.toString(remain), TranRefNo, "DMR");
							String reponse = OpenRestWebServices.initiatepayout(pc);
							JSONObject obj = new JSONObject(reponse);
							//System.out.println("input::::::::::::::::::::"+input);
							logger_log.warn("returnData::::::::::::::::::::"+reponse);
							String today = GenerateRandomNumber.getCurrentDate();	
							String currentTime = GenerateRandomNumber.getCurrentTime();
						//	String transId = GenerateRandomNumber.generateTransactionNumber();
							String status="";
							if (obj.getInt("status") == 200) {

								JSONObject dataObj = obj.getJSONObject("data");
								if (dataObj.isNull("bank_transaction_ref_id")) {
									bankrefno = dataObj.getString("open_transaction_ref_id");
									status = "PENDING";
								} else {
									bankrefno = dataObj.getString("bank_transaction_ref_id");
									status = "SUCCESS";
								}
								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, TranRefNo, status,bankrefno,comm,transfertype,checkRemitterMobile,amount,gst,"DMR");
								flag=impsTransactiondao.insertImpsTransaction(imptrans);
								status="1";
								EarningSurcharge earningSurcharge41 = new EarningSurcharge(userDetails.getRoleId(), userDetails.getWlId(), userDetails.getUserId(), comm,charge, "Money Transfer - " + accountNumber, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge41);

								/*Gst gs=new Gst(userDetails.getUserId(), gst, "Money Transfer - " + accountNumber, GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime());
								GstDao.insertGst(gs);*/
								String reseller = "";
						
								if(userDetails.getRoleId() == 6) {
									double rcharge=0.0;
									double retaComm=0.0;
									double rtCharge=0.0;
									if(pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
										rtCharge = (pckret.getCharge()*remain)/100;
									}else{
										rtCharge = pckret.getCharge();
									}
									
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
										if(rtCharge==0){rcharge=0;}
										else{rcharge = charge - rtCharge;}
										if(distCharge==0){dcharge=0;}
										else{dcharge = rtCharge - distCharge;}
										if(sdCharge==0){sCharge=0;}
										else{sCharge = distCharge - sdCharge;}
										if(rcharge!=0){
											EarningSurcharge earningSurcharge11 = new EarningSurcharge(5, userDetails.getWlId(), rId, 0.0, rcharge, "DMT Charge FOR - "+accountNumber, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
											earningSurchargeDao.insertEarningSurcharge(earningSurcharge11);
										}
										if(dcharge!=0){
											EarningSurcharge earningSurcharge11 = new EarningSurcharge(4, userDetails.getWlId(), distId, 0.0, dcharge, "DMT Charge FOR - "+accountNumber, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
											earningSurchargeDao.insertEarningSurcharge(earningSurcharge11);
										}
										if(sCharge!=0){
											EarningSurcharge earningSurcharge11 = new EarningSurcharge(3, userDetails.getWlId(), sdId, 0.0, sCharge, "DMT Charge FOR - "+accountNumber, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
											earningSurchargeDao.insertEarningSurcharge(earningSurcharge11);
										}
										
										/*commissionService.updateBalance(rId, "Money Transfer - "+accountNumber, "CHARGE", rcharge, "CREDIT");
										commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "CHARGE", dcharge, "CREDIT");
										commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT");*/
										if(!resellerId.equals("admin")) {
											if(!userDetails.getWlId().startsWith("ASR")){
											reCharge = sdCharge - resCharge;
											if(reCharge!=0){
												EarningSurcharge earningSurcharge11 = new EarningSurcharge(2, userDetails.getWlId(), resellerId, 0.0, reCharge, "DMT Charge FOR - "+accountNumber, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
												earningSurchargeDao.insertEarningSurcharge(earningSurcharge11);
											}
											//commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT");
										}
										}
										if(retComm==0){retaComm=0;}
										else{
										retaComm=retComm-comm;
										}
										if(dComm==0){disComm=0;}
										else{
										disComm=dComm-retComm;
										}
										if(sdComm==0){sdisComm=0;}
										else{
										sdisComm = sdComm-dComm;
										}
										if(retaComm!=0){
											EarningSurcharge earningSurcharge1 = new EarningSurcharge(5, userDetails.getWlId(), rId, retaComm, 0.0, "DMT Commission FOR - "+accountNumber, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
											earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
												}
										if(disComm!=0){
											EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, userDetails.getWlId(), distId, disComm, 0.0, "DMT Commission FOR - "+accountNumber, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
											earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
												}
										if(sdisComm!=0){
											EarningSurcharge earningSurcharge1 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm, 0.0, "DMT Commission FOR - "+accountNumber, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
											earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
												}
										/*commissionService.updateBalance(rId, "Money Transfer - "+accountNumber, "COMMISSION", retaComm, "CREDIT");
										commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "COMMISSION", disComm, "CREDIT");
										commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT");*/
										if(!resellerId.equals("admin")) {
											if(!userDetails.getWlId().startsWith("ASR")){
												if(resComm==0){reComm=0;}
												else{
												reComm = resComm-sdComm;
												}	
											//commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT");
											if(reComm!=0){
												EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, userDetails.getWlId(), resellerId, reComm, 0.0, "DMT Commission FOR - "+accountNumber, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
												earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
													}
										}
										}
										
								}else if (userDetails.getRoleId() == 5) {
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
								returnData.put("message", "Transaction Successful :"+bankrefno);
								returnData.put("status", "1");

								/*-------------------SEND SMS TO USER ------------------------*/
								Map<String, Object> param = new HashMap<String, Object>();
								param.put("wlId", userDetails.getWlId());
								SmsCredential credential = smsCredentialDao.getSmsCredentialByNamedQuery("GetSmsCredentialByWlId", param).get(0);
								String sms = "Dear  "+userDetails.getName()+",your DMR Transaction is SUCCESSFULL to:"+beneMobileNumber+" & Transaction Id:"+bankrefno+" Log on to :"+credential.getDomain()+"";
								SMS.sendSMS2(checkRemitterMobile, sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());
								/*-----------------------------------------------------------------*/
								
							}else{
								if (!obj.isNull("data")) {
									JSONObject dataObj = obj.getJSONObject("data");
									if (dataObj.isNull("bank_transaction_ref_id")) {
										bankrefno = dataObj.getString("open_transaction_ref_id");
									} else {
										bankrefno = dataObj.getString("bank_transaction_ref_id");
									}
								} else {
									bankrefno = "NA";
								}
								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, TranRefNo, "FAILED",bankrefno,comm,transfertype,checkRemitterMobile,amount,gst,"DMR");
								flag=impsTransactiondao.insertImpsTransaction(imptrans);
								status="0";
								

								flag = commissionService.updateBalance(userDetails.getUserId(),
										"Money Transfer Revert- " + accountNumber, "Money Transfer", totalAmount, "CREDIT",0);
								if(comm!=0){
									//usercom=usercom-charge;
									commissionService.updateBalance(userDetails.getUserId(),
											"Money Transfer Revert- " + accountNumber, "Money Transfer Commission", comm, "DEBIT",0);
								}
								
								if(userDetails.getWlId().startsWith("ASR")){
									double reseCharge=0.0;
									double resCom=0.0;
									Map<String, Object> param = new HashMap<String, Object>();
									param.put("wlId", userDetails.getWlId());
									List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
									User whuser=whlist.get(0);
									pckres=commissionService.getPackagewiseCommisionOnOperator(whuser.getUserId(),"DMR",id);
									if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
										reseCharge = (pckres.getCharge()*remain)/100;
									
									}else{
										reseCharge =	pckres.getCharge();
									}
									if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
										resCom=(pckres.getComm()*remain)/100;
									}else{
										resCom=pckres.getComm();
									}
									
									double whtamnt=(remain+reseCharge+gst)-resCom;
									flag = commissionService.updateBalance(whlist.get(0).getUserId(), "Money Transfer Revert- " + accountNumber, "Money Transfer", whtamnt, "CREDIT",0);
								}
								returnData.put("message", "Transaction Failed");
								returnData.put("status", "0");
								/*-------------------SEND SMS TO USER ------------------------*/
								Map<String, Object> param = new HashMap<String, Object>();
								param.put("wlId", userDetails.getWlId());
								SmsCredential credential = smsCredentialDao.getSmsCredentialByNamedQuery("GetSmsCredentialByWlId", param).get(0);
								String sms = "Dear  "+userDetails.getName()+",your DMR Transaction is FAILED to:"+beneMobileNumber+" Log on to :"+credential.getDomain()+"";
								SMS.sendSMS2(checkRemitterMobile, sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());
								/*-----------------------------------------------------------------*/
						
							}
							}catch(Exception e){
								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(), TranRefNo, "PENDING",bankrefno,comm,transfertype,checkRemitterMobile,amount,gst,"DMR");
								flag=impsTransactiondao.insertImpsTransaction(imptrans);
								returnData.put("message", "Transaction Pending");
								returnData.put("status", "1");
								/*-------------------SEND SMS TO USER ------------------------*/
								Map<String, Object> param = new HashMap<String, Object>();
								param.put("wlId", userDetails.getWlId());
								SmsCredential credential = smsCredentialDao.getSmsCredentialByNamedQuery("GetSmsCredentialByWlId", param).get(0);
								String sms = "Dear  "+userDetails.getName()+",your DMR Transaction is PENDING to:"+beneMobileNumber+" Log on to :"+credential.getDomain()+"";
								SMS.sendSMS2(checkRemitterMobile, sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword());
								/*-----------------------------------------------------------------*/
						
							}
								
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
					
				}
				

			} else {
				returnData.put("message", "Amount should be maximum 25000");
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
		
		
		}catch (Exception e) {
		logger_log.error("instantPayMoneytransfer::::::::::::::"+e);
		returnData.put("status", "0");
		returnData.put("message", e);
		}
		return returnData;
		}
}
