package com.recharge.businessimpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.recharge.businessdao.ApiResponseService;
import com.recharge.businessdao.CommissionService;
import com.recharge.businessdao.CustomQueryServiceLogic;
import com.recharge.businessdao.GlobalCommandCenter;
import com.recharge.businessdao.SendRechargeRequest;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.icicidmtmodel.ImpsSettlement;
import com.recharge.icicidmtmodel.RemitterLimit;
import com.recharge.icicidmtserviceDao.ImpsSettlementDao;
import com.recharge.icicidmtserviceDao.RemitterLimitDao;
import com.recharge.model.AEPSCommission;
import com.recharge.model.AEPSLog;
import com.recharge.model.AEPSUserMap;
import com.recharge.model.Apisetting;
import com.recharge.model.Apitrace;
import com.recharge.model.DMRCommission;
import com.recharge.model.ECommerceLog;
import com.recharge.model.ECommerceUser;
import com.recharge.model.EarningSurcharge;
import com.recharge.model.IRCTCLog;
import com.recharge.model.IRCTCUser;
import com.recharge.model.ImpsTransaction;
import com.recharge.model.Individualcharge;
import com.recharge.model.InstantPayAepsResponse;
import com.recharge.model.InstantPayLog;
import com.recharge.model.Insurance;
import com.recharge.model.MicroAtmLog;
import com.recharge.model.MicroAtmLogNew;
import com.recharge.model.MicroAtmResponse;
import com.recharge.model.MicroAtmResponseNew;
import com.recharge.model.Operator;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.PanApplication;
import com.recharge.model.PaymonkResponse;
import com.recharge.model.PaytmRequest;
import com.recharge.model.Rechargedetails;
import com.recharge.model.ResponseCounter;
import com.recharge.model.SettlementBalance;
import com.recharge.model.SettlementReport;
import com.recharge.model.User;
import com.recharge.model.Utility;
import com.recharge.servicedao.AEPSCommissionDao;
import com.recharge.servicedao.AEPSLogDao;
import com.recharge.servicedao.AEPSUserMapDao;
import com.recharge.servicedao.ApisettingDao;
import com.recharge.servicedao.ApitraceDao;
import com.recharge.servicedao.DMRCommissionDao;
import com.recharge.servicedao.ECommerceLogDao;
import com.recharge.servicedao.ECommerceUserDao;
import com.recharge.servicedao.EarningSurchargeDao;
import com.recharge.servicedao.IRCTCLogDao;
import com.recharge.servicedao.IRCTCUserDao;
import com.recharge.servicedao.ImpsTransactionService;
import com.recharge.servicedao.InstantPayAepsResponseDao;
import com.recharge.servicedao.InstantPayLogDao;
import com.recharge.servicedao.InsuranceDao;
import com.recharge.servicedao.MicroAtmLogDao;
import com.recharge.servicedao.MicroAtmLogNewDao;
import com.recharge.servicedao.MicroAtmResponseDao;
import com.recharge.servicedao.MicroAtmResponseNewDao;
import com.recharge.servicedao.OperatorDao;
import com.recharge.servicedao.PanApplicationDao;
import com.recharge.servicedao.PaytmRequestDao;
import com.recharge.servicedao.RechargedetailsDao;
import com.recharge.servicedao.ResponseCounterDao;
import com.recharge.servicedao.SettlementBalanceDao;
import com.recharge.servicedao.SettlementReportDao;
import com.recharge.servicedao.TransactiondetailsDao;
import com.recharge.servicedao.UserDao;
import com.recharge.servicedao.UtilityDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.HashGenerationAgreepay;
import com.recharge.utill.RoundNumber;
import com.recharge.utill.SHACheckSumExample;
import com.recharge.utill.UtilityClass;
import com.recharge.yesbankmodel.YesBankAEPSResponse;
import com.recharge.yesbankservicedao.YesBankAEPSResponseDao;

@Service("apiResponseService")
public class ApiResponseServiceImpl implements ApiResponseService {	
	private static final Logger logger_log = Logger.getLogger(ApiResponseService.class);
	@Autowired CustomQueryServiceLogic customQueryServiceLogic;
	@Autowired OperatorDao operatorDao;
	@Autowired CommissionService commissionService;
	@Autowired RechargedetailsDao rechargedetailsDao;
	@Autowired TransactiondetailsDao transactiondetailsDao;
	@Autowired UserDao userDao; 
	@Autowired ApisettingDao apisettingDao;
	@Autowired ApitraceDao apitraceDao;
	@Autowired SendRechargeRequest sendRechargeRequest;
	@Autowired EarningSurchargeDao earningSurchargeDao;
	@Autowired ResponseCounterDao responsecounterdao;
	@Autowired UtilityDao utilityDao;
	@Autowired AEPSUserMapDao aepsuserdao;
	@Autowired AEPSCommissionDao aepscommissiondao;
	@Autowired SettlementBalanceDao settlementbalancedao;
	@Autowired SettlementReportDao settlementreportdao;
	@Autowired AEPSLogDao aepslogdao;
	@Autowired YesBankAEPSResponseDao YesBankAEPSResponseDao;
	@Autowired InsuranceDao InsuranceDao;
	@Autowired MicroAtmLogDao microatmlogdao;
	@Autowired MicroAtmResponseDao microatmresponseDao;
	@Autowired PaytmRequestDao paytmrequestdao;
	@Autowired IRCTCUserDao irctcuserdao;
	@Autowired IRCTCLogDao irctclogdao;
	@Autowired InstantPayLogDao instantpaylogdao;
	@Autowired GlobalCommandCenter globalCommandCenter;
	@Autowired PanApplicationDao panapplicationdao;
	@Autowired InstantPayAepsResponseDao instantpayaepsresdao;
	@Autowired ECommerceUserDao ecommercedao;
	@Autowired ECommerceLogDao ecommercelogdao;
	@Autowired MicroAtmLogNewDao microatmlognewdao;
	@Autowired MicroAtmResponseNewDao microatmresponsenew;
	@Autowired ImpsSettlementDao impssettlementDao;
	@Autowired DMRCommissionDao DMRCommissionDao;
	@Autowired ImpsTransactionService ImpsTransactionService;
	@Autowired RemitterLimitDao remitterlimitDao;

	private static String api_key="7rnFly";
	private static String salt="pjVQAWpA";
	
	
	@Override
	public Map<String, Object> paytmfinalres(Map<String, Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		String orderId="";
		String rrn="";
		Map<String, String> parameters = new HashMap<String, String>();
		try{
			String statusCode=request.get("statusCode").toString();
			Map<String, Object> result=(Map<String, Object>)request.get("result");
			 orderId=result.get("orderId").toString();
			 if(statusCode.equalsIgnoreCase("DE_001")) {
				 rrn = result.get("rrn").toString();
				 param= new HashMap<String, Object>();  
					param.put("TranRefNo", orderId);
					List<ImpsSettlement> uplist=impssettlementDao.getImpsSettlementByNamedQuery("getIMPSSettlebyTranNo",param);
					ImpsSettlement ims=uplist.get(0);
					ims.setBankRRN(rrn);
					ims.setStatus("SUCCESS");
					impssettlementDao.updateImpsSettlement(ims);
			 }else{
				 param= new HashMap<String, Object>();  
					param.put("TranRefNo", orderId);
					List<ImpsSettlement> uplist=impssettlementDao.getImpsSettlementByNamedQuery("getIMPSSettlebyTranNo",param);
					if(!uplist.isEmpty()) {
						ImpsSettlement imps=uplist.get(0);	
				    	 if(imps.getStatus().equalsIgnoreCase("PENDING")) {
				    		 imps.setStatus("FAILED");
				    		 impssettlementDao.updateImpsSettlement(imps);
				    		 
				    		 int id=0;
				    		 Map<String, Object> parameter = new HashMap<String, Object>();
				    			parameter.put("api", "SETTLEMENT");
				    			List<DMRCommission> opList =  DMRCommissionDao.getDMRCommissionByNamedQuery("getDMRCommissionByApi", parameter);
				    			if((!opList.isEmpty())) {
				    				for(DMRCommission comm2 : opList){
				    					if(imps.getAmount()>=comm2.getSlab1() && imps.getAmount()<=comm2.getSlab2()){
				    						id = comm2.getId();
				    						break;
				    					}
				    				}
				    			}
				    		 PackageWiseChargeComm pckre = commissionService.getPackagewiseCommisionOnOperator(imps.getUserId(),"SETTLEMENT",id) ;
								double charge=0.0;
								if (pckre.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
									charge = (pckre.getCharge() * imps.getAmount()) / 100;
								} else {
									charge = pckre.getCharge();
								}
				    		 param= new HashMap<String, Object>();
								param.put("username",imps.getUserId());
								List<SettlementBalance> settlelists=settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param);
								SettlementBalance settle1=settlelists.get(0);	
								double settleopbal=settle1.getSettlementbalance();
								double settleclbal=settleopbal+imps.getAmount()+charge;
								settle1.setSettlementbalance(settleclbal);  
							    settlementbalancedao.updateSettlementBalance(settle1);
							    SettlementReport settlere1=new SettlementReport(imps.getUserId(), settleopbal, settleclbal, imps.getAmount()+charge,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"Settle to Bank","FAILED","REVERT"+orderId);
							    
							      settlementreportdao.insertSettlementReport(settlere1);
							      User userDetails=userDao.getUserByUserId(imps.getUserId());
							      if(userDetails.getWlId().startsWith("ASR")){
										param = new HashMap<String, Object>();
										param.put("wlId",userDetails.getWlId());
										List<User> wuser=userDao.getUserByNamedQuery("getWILDbyID", param);
										param = new HashMap<String, Object>();
										param.put("username",wuser.get(0).getUserId());
										List<SettlementBalance> wsettlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
											SettlementBalance SettlementBalance = wsettlelist.get(0);
											double settlementamountprev = SettlementBalance.getSettlementbalance();
											PackageWiseChargeComm pckres = commissionService.getPackagewiseCommisionOnOperator(wuser.get(0).getUserId(),"SETTLEMENT",id) ;
											if (pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
												charge = (pckres.getCharge() * imps.getAmount()) / 100;
											} else {
												charge = pckres.getCharge();
											}
											double settlementamountnew = settlementamountprev+(imps.getAmount()+charge);
											
											
											SettlementBalance.setSettlementbalance(settlementamountnew);
											settlementbalancedao.updateSettlementBalance(SettlementBalance);
											SettlementReport settlew2 = new SettlementReport(wuser.get(0).getUserId(),settlementamountprev,settlementamountnew,imps.getAmount(),GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"Settle to Bank","FAILED","REVERT"+orderId);
										
										settlementreportdao.insertSettlementReport(settlew2);
										
									}
				    	 }
					}
			 }
		}catch(Exception e){
			
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> paytmfinalDMTres(Map<String, Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		String orderId="";String rrn="";
		try{
			String statusCode=request.get("statusCode").toString();
			Map<String, Object> result=(Map<String, Object>)request.get("result");
			 orderId=result.get("orderId").toString();
			 if(statusCode.equalsIgnoreCase("DE_001")) {
				 rrn = result.get("rrn").toString();
				 param= new HashMap<String, Object>();  
					param.put("recieptId", orderId);
					List<ImpsTransaction> list=ImpsTransactionService.getIMPSDetailsByNamedQuery("GetIMPSDetailsByTid", param);
					if(!list.isEmpty()){
						
						ImpsTransaction ims=list.get(0);
						if(ims.getStatus().equals("PENDING")){
						ims.setBanktransactionId(rrn);
						ims.setStatus("SUCCESS");
						ImpsTransactionService.updateImpsTransaction(ims);
						
						int id=0;
						Map<String, Object> parameter = new HashMap<String, Object>();
						parameter.put("api", "DMR");
						List<DMRCommission> opList =  DMRCommissionDao.getDMRCommissionByNamedQuery("getDMRCommissionByApi", parameter);
						for(DMRCommission comm2 : opList){
							if(ims.getAmount()>=comm2.getSlab1() && ims.getAmount()<=comm2.getSlab2()){
								id = comm2.getId();
								break;
							}
						}
						
						double charge=ims.getCharge();
						double comm=ims.getCommission();
						User userDetails=userDao.getUserByUserId(ims.getUsername());
						if(userDetails.getRoleId() == 6) {
							double sdCharge=0.0;double sCharge=0.0;
							double retComm=0.0;double resComm=0.0;
							double dComm=0.0;double sdComm=0.0;
							double rcharge=0.0;
							double retaComm=0.0;
							double distCharge=0.0;double dcharge=0.0;
							double rtCharge=0.0;
							double remain=ims.getAmount();
							String rId=userDetails.getUplineId();
							String accountNumber=ims.getAccount_no();
							Map<String, String> parameters = new HashMap<String, String>();
							parameters.put("userId", rId);	
							String distId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
							
							// Super Distributor Id
							parameters = new HashMap<String, String>();
							parameters.put("userId", distId);	
							String sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
							
							// Reseller Id
							parameters = new HashMap<String, String>();
							parameters.put("userId", sdId);							
							String resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
						
							PackageWiseChargeComm pckret=commissionService.getPackagewiseCommisionOnOperator(rId,"DMR",id);
							if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							retComm =(pckret.getComm()*remain)/100;
							}else{
							retComm =pckret.getComm();	
							}
							
							if(pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								rtCharge = (pckret.getCharge()*remain)/100;
							}else{
								rtCharge = pckret.getCharge();
							}
						//	System.out.println("reseller=="+comm);
							PackageWiseChargeComm	pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"DMR",id); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*remain)/100;
							}else{
							dComm=pckdis.getComm();	
							}
							if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								distCharge = (pckdis.getCharge()*remain)/100;
							}else{
								distCharge = pckdis.getCharge();
							}
						//	System.out.println("dComm=="+dComm);
							PackageWiseChargeComm	pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*remain)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								sdCharge = (pcksd.getCharge()*remain)/100;
							}else{
								sdCharge = pcksd.getCharge();
							}
							
							if(rtCharge==0){rcharge=0;}
							else{rcharge = charge - rtCharge;}
							if(distCharge==0){dcharge=0;}
							else{dcharge = rtCharge - distCharge;}
							if(sdCharge==0){sCharge=0;}
							else{sCharge = distCharge - sdCharge;}
							if(rcharge!=0){
								commissionService.updateBalance(rId, "Money Transfer - "+accountNumber, "CHARGE", rcharge, "CREDIT",0);
							
							}
							if(dcharge!=0){
								commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "CHARGE", dcharge, "CREDIT",0);
								
							}
							if(sCharge!=0){
								commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0);
								
							}
							double disComm=0.0,sdisComm=0.0;
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
								commissionService.updateBalance(rId, "Money Transfer - "+accountNumber, "COMMISSION", retaComm, "CREDIT",0);
						
									}
							if(disComm!=0){
								commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "COMMISSION", disComm, "CREDIT",0);
							
									}
							if(sdisComm!=0){
								commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0);
								
									}
							if(!resellerId.equals("admin")){
								if(!userDetails.getWlId().startsWith("ASR")){
								double resCharge=0.0,reCharge=0.0;
								PackageWiseChargeComm pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*remain)/100;
							}else{
							resComm =pckres.getComm();	
							}
							if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								resCharge = (pckres.getCharge()*remain)/100;
								}else{
								resCharge =	pckres.getCharge();
								}
							reCharge = sdCharge - resCharge;
							if(reCharge!=0){
								commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0);
								
							}
							double reComm=0.0;
							if(resComm==0){reComm=0;}
							else{
							reComm = resComm-sdComm;
							}	
						//
						if(reComm!=0){
							commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0);
								}
							}
							}
							
							
						}else if(userDetails.getRoleId() == 5) {
							double sdCharge=0.0;double sCharge=0.0;
							double resComm=0.0;
							double dComm=0.0;double sdComm=0.0;
							double distCharge=0.0;double dcharge=0.0;
							double remain=ims.getAmount();
							
							String accountNumber=ims.getAccount_no();
								String distId=userDetails.getUplineId();
							// Super Distributor Id
								Map<String, String> parameters = new HashMap<String, String>();
							parameters.put("userId", distId);	
							String sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
							
							// Reseller Id
							parameters = new HashMap<String, String>();
							parameters.put("userId", sdId);							
							String resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
						
							
						//	System.out.println("reseller=="+comm);
							PackageWiseChargeComm	pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"DMR",id); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*remain)/100;
							}else{
							dComm=pckdis.getComm();	
							}
							if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								distCharge = (pckdis.getCharge()*remain)/100;
							}else{
								distCharge = pckdis.getCharge();
							}
						//	System.out.println("dComm=="+dComm);
							PackageWiseChargeComm	pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*remain)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								sdCharge = (pcksd.getCharge()*remain)/100;
							}else{
								sdCharge = pcksd.getCharge();
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
							double disComm=0.0,sdisComm=0.0;
							
							if(dComm==0){disComm=0;}
							else{
							disComm=dComm-comm;
							}
							if(sdComm==0){sdisComm=0;}
							else{
							sdisComm = sdComm-dComm;
							}
							
							if(disComm!=0){
								commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "COMMISSION", disComm, "CREDIT",0);
							
									}
							if(sdisComm!=0){
								commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0);
								
									}
							if(!resellerId.equals("admin")){
								if(!userDetails.getWlId().startsWith("ASR")){
								double resCharge=0.0,reCharge=0.0;
								PackageWiseChargeComm pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*remain)/100;
							}else{
							resComm =pckres.getComm();	
							}
							if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								resCharge = (pckres.getCharge()*remain)/100;
								}else{
								resCharge =	pckres.getCharge();
								}
							reCharge = sdCharge - resCharge;
							if(reCharge!=0){
								commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0);
								
							}
							double reComm=0.0;
							if(resComm==0){reComm=0;}
							else{
							reComm = resComm-sdComm;
							}	
						//
						if(reComm!=0){
							commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0);
								}
							}
							}
							
							
						}else if(userDetails.getRoleId() == 4) {
							double sdCharge=0.0;double sCharge=0.0;
							double resComm=0.0;
							double sdComm=0.0;
							double remain=ims.getAmount();
							
							String accountNumber=ims.getAccount_no();
								String sdId=userDetails.getUplineId();
							// Reseller Id
								Map<String, String> parameters = new HashMap<String, String>();
							parameters.put("userId", sdId);							
							String resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
						
						
							PackageWiseChargeComm	pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*remain)/100;
							}else{
							sdComm = pcksd.getComm();	
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
							double sdisComm=0.0;
							
							
							if(sdComm==0){sdisComm=0;}
							else{
							sdisComm = sdComm-comm;
							}
							
							
							if(sdisComm!=0){
								commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0);
								
									}
							if(!resellerId.equals("admin")){
								if(!userDetails.getWlId().startsWith("ASR")){
								double resCharge=0.0,reCharge=0.0;
								PackageWiseChargeComm pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*remain)/100;
							}else{
							resComm =pckres.getComm();	
							}
							if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								resCharge = (pckres.getCharge()*remain)/100;
								}else{
								resCharge =	pckres.getCharge();
								}
							reCharge = sdCharge - resCharge;
							if(reCharge!=0){
								commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0);
								
							}
							double reComm=0.0;
							if(resComm==0){reComm=0;}
							else{
							reComm = resComm-sdComm;
							}	
						//
						if(reComm!=0){
							commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0);
								}
							}
							}
							
							
						}else if(userDetails.getRoleId() == 3) {
							double resComm=0.0;
							double remain=ims.getAmount();
							
							String accountNumber=ims.getAccount_no();
								String resellerId=userDetails.getUplineId();
							// Reseller Id
							
							if(!resellerId.equals("admin")){
								if(!userDetails.getWlId().startsWith("ASR")){
								double resCharge=0.0,reCharge=0.0;
								PackageWiseChargeComm pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*remain)/100;
							}else{
							resComm =pckres.getComm();	
							}
							if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								resCharge = (pckres.getCharge()*remain)/100;
								}else{
								resCharge =	pckres.getCharge();
								}
							reCharge = charge - resCharge;
							if(reCharge!=0){
								commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0);
								
							}
							double reComm=0.0;
							if(resComm==0){reComm=0;}
							else{
							reComm = resComm-comm;
							}	
						//
						if(reComm!=0){
							commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0);
								}
							}
							}
							
							
						}
						
					}
						
					}
			 }else{
				 	param= new HashMap<String, Object>();  
					param.put("recieptId", orderId);
					List<ImpsTransaction> list=ImpsTransactionService.getIMPSDetailsByNamedQuery("GetIMPSDetailsByTid", param);
					if(!list.isEmpty()){
						ImpsTransaction ims=list.get(0);
						if(ims.getStatus().equals("PENDING")){
						ims.setStatus("FAILED");
						ImpsTransactionService.updateImpsTransaction(ims);
						User userDetails=userDao.getUserByUserId(ims.getUsername());
						commissionService.updateBalance(userDetails.getUserId(),
								"Money Transfer - " + ims.getAccount_no(), "Money Transfer Revert", ims.getAmount()+ims.getCharge(), "CREDIT",0);
						commissionService.updateBalance(userDetails.getUserId(),
								"Money Transfer - " + ims.getAccount_no(), "Money Transfer COMMISSION", ims.getCommission(), "DEBIT",0);
						
						if(userDetails.getWlId().startsWith("ASR")){
							int id=0;
							Map<String, Object> parameter = new HashMap<String, Object>();
							parameter.put("api", "DMR");
							List<DMRCommission> opList =  DMRCommissionDao.getDMRCommissionByNamedQuery("getDMRCommissionByApi", parameter);
							for(DMRCommission comm2 : opList){
								if(ims.getAmount()>=comm2.getSlab1() && ims.getAmount()<=comm2.getSlab2()){
									id = comm2.getId();
									break;
								}
							}
							double reseCharge=0.0;
							double resCom=0.0;
							 param = new HashMap<String, Object>();
							param.put("wlId", userDetails.getWlId());
							List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
							User whuser=whlist.get(0);
							PackageWiseChargeComm pckres=commissionService.getPackagewiseCommisionOnOperator(whuser.getUserId(),"DMR",id);
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								reseCharge = (pckres.getCharge()*ims.getAmount())/100;
							
							}else{
								reseCharge =	pckres.getCharge();
							}
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								resCom=(pckres.getComm()*ims.getAmount())/100;
							}else{
								resCom=pckres.getComm();
							}
							
							double whtamnt=(ims.getAmount()+reseCharge);
							 commissionService.updateBalance(whlist.get(0).getUserId(), "Money Transfer - " + ims.getAccount_no(), "Money Transfer Revert", whtamnt, "CREDIT",0);
						}
						
						
						int year = Calendar.getInstance().get(Calendar.YEAR);
						int month = Calendar.getInstance().get(Calendar.MONTH);
						Date d=new Date();
						param = new HashMap<String, Object>();
						param.put("month", d.getMonth());
						param.put("year", year);
						param.put("mobile", ims.getRemmobile());
						List<RemitterLimit> remlist1=remitterlimitDao.getRemitterLimitByNamedQuery("getRemlimitbyMobilemonthyear",param);	
						if(!remlist1.isEmpty()) {
					  RemitterLimit remlim1=remlist1.get(0);	
					  double limit1=remlim1.getCashlimit();
					  double newlimit1=limit1+ims.getAmount();	
					  remlim1.setCashlimit(newlimit1);
					    remitterlimitDao.updateRemitterLimit(remlim1);
					    
						}
					}
					}
			 }
			
		}catch(Exception e){
			
		}
		return returnData;
	}


	
	
	@Override
	public Map<String, Object> yesbankaepsApiResponse(LinkedTreeMap<String, Object> request) {
		logger_log.warn("yesbankaepsApiResponse::::"+request);
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag = false;
		String username = "";
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		double amount = 0.0;
		String processingCode = "";
		boolean update = false;
		double settlementamount = 0.0;
		double settlementamountprev = 0.0;
		double settlementamountnew = 0.0;
		int id = 0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		double AGENT_CHARGE=0.0;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		try {
			LinkedTreeMap tm=new LinkedTreeMap<>();
			SettlementReport settle2=null;
			tm.put("data", request.get("DATA"));
			JSONObject json = new JSONObject(tm);
			System.out.println(json);
			JSONObject data=json.getJSONObject("data");
			JSONArray TXNDETAILS=data.getJSONArray("TXNDETAILS");
			JSONObject dataa=TXNDETAILS.getJSONObject(0);
			//String status=data.get("txnStatus");
			param = new HashMap<String, Object>();
			param.put("referenceno", dataa.get("ORDER_ID"));
			List<AEPSLog> listran=aepslogdao.getAEPSLogByNamedQuery("getAEPSLogByrefno", param);
			if(!listran.isEmpty()){
				AEPSLog AEPSLog=listran.get(0);
				AEPSLog.setStatus((String)dataa.get("txnStatus"));
				AEPSLog.setRemark((String)dataa.get("RESP_MSG"));
				aepslogdao.updateAEPSLog(AEPSLog);
				double txnamount=0.0;
				if(dataa.get("TXN_AMOUNT")  instanceof String){
					String txamnt=(String) dataa.get("TXN_AMOUNT");
					txnamount=Double.parseDouble(txamnt);
				}if(dataa.get("TXN_AMOUNT")  instanceof Double){
					Double txamnt=(Double) dataa.get("TXN_AMOUNT");
					txnamount=txamnt;
				}if(dataa.get("TXN_AMOUNT")  instanceof Integer){
					Integer txamnt=(Integer) dataa.get("TXN_AMOUNT");
					txnamount=(double)txamnt;
				}
				System.out.println("txnamount:::"+txnamount);
				if(dataa.isNull("AGENT_CHARGE")){
					AGENT_CHARGE=0.0;	
					}else{
					AGENT_CHARGE=(double)dataa.get("AGENT_CHARGE");	
					}
				YesBankAEPSResponse yesr=new YesBankAEPSResponse("0", dataa.get("RRN").toString(), dataa.get("AadharNumber").toString(), "0", Double.toString(txnamount), dataa.get("RES_CODE").toString(), "-", "-", "INR", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", dataa.get("txnStatus").toString(), dataa.get("ORDER_ID").toString(), AGENT_CHARGE, txnamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(), AEPSLog.getAgentcode(), AEPSLog.getUserId(),AEPSLog.getProcessingCode(),dataa.get("RESP_MSG").toString());
				YesBankAEPSResponseDao.insertYesBankAEPSResponse(yesr);
			if(dataa.get("txnStatus").equals("SUCCESS")){
					if(AEPSLog.getType().equals("WITHDRAWAL")){
						settlementamount=txnamount;
						param = new HashMap<String, Object>();
						param.put("username",AEPSLog.getUserId());
						List<SettlementBalance> settlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
						if(settlelist.isEmpty()){
							SettlementBalance SettlementBalance = new SettlementBalance(AEPSLog.getUserId(),settlementamount);
							settlementbalancedao.insertSettlementBalance(SettlementBalance);	
							settle2 = new SettlementReport(AEPSLog.getUserId(),0.0,settlementamount, settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
						}else{
							SettlementBalance SettlementBalance = settlelist.get(0);
							settlementamountprev = SettlementBalance.getSettlementbalance();
							settlementamountnew = settlementamountprev+settlementamount;
							SettlementBalance.setSettlementbalance(settlementamountnew);
							settlementbalancedao.updateSettlementBalance(SettlementBalance);
							settle2 = new SettlementReport(AEPSLog.getUserId(),settlementamountprev,settlementamountnew,settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
						}
						settlementreportdao.insertSettlementReport(settle2);
						
						param = new HashMap<String, Object>();
						param.put("api","YesBank");
						
						List<AEPSCommission> aepscommlist = aepscommissiondao.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi",param);
						
						for(AEPSCommission comm2 : aepscommlist){
							if(settlementamount>=comm2.getSlab1() && settlementamount<=comm2.getSlab2()){
								id = comm2.getId();
								break;
							}
						}
						param = new HashMap<String, Object>();
						User user=userDao.getUserByUserId(AEPSLog.getUserId());
						if(user.getRoleId() == 5) {
							pckret = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"YesBank AEPS",id) ;
							//Retailer Id
							//rId=userDetails.getUserId();
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
							if(pckret.getPackage_id()!=null){
							if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							retComm =(pckret.getComm()*settlementamount)/100;
							}else{
							retComm =pckret.getComm();	
							}
							comm = retComm;
							}
							System.out.println("comm:::::::::::::::::::::::::::::"+comm);
						//	System.out.println("reseller=="+comm);
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"YesBank AEPS",id); 
							if(pckdis.getPackage_id()!=null){
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*settlementamount)/100;
							}else{
							dComm=pckdis.getComm();	
							}
							dComm=dComm-comm;
							}
							System.out.println("dComm:::::::::::::::::::::::::::::"+dComm);
						//	System.out.println("dComm=="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"YesBank AEPS",id);
							if(pcksd.getPackage_id()!=null){
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*settlementamount)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							sdComm=sdComm-dComm;
							}
							
						//	System.out.println("sdComm=="+sdComm);
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"YesBank AEPS",id);
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*settlementamount)/100;
							}else{
							resComm =pckres.getComm();	
							}
						//	System.out.println("resComm=="+resComm);
							commissionService.updateBalance(user.getUserId(), "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
							commissionService.updateBalance(distId, "AEPS Commission For Amount - "+settlementamount, "Commission", dComm, "CREDIT",0);
							commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT",0);
							
						}else if(user.getRoleId() == 4) {

							//distId = userDetails.getUserId();	
							sdId = user.getUplineId();	
							
							parameters = new HashMap<String, String>();
							parameters.put("userId", sdId);							
							resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
							
							pckdis=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"YesBank AEPS",id); 
							if(pckdis.getPackage_id()!=null){
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*settlementamount)/100;
							}else{
							dComm=pckdis.getComm();	
							}
							}
							
							comm = dComm;
							
						//	System.out.println("dComm="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"YesBank AEPS",id);
							System.out.println("pcksd:::::::::::::::::"+pcksd);
							if(pcksd.getPackage_id()!=null){
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*settlementamount)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							sdComm=sdComm-dComm;
							}
							
							//System.out.println("sdComm="+sdComm);
							if(!resellerId.equals("admin")) {
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"YesBank AEPS",id);
							if(pckres.getPackage_id()!=null){
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*settlementamount)/100;
							}else{
							resComm =pckres.getComm();	
							}
							resComm=resComm-sdComm;
							}
							commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0);
							}
							//System.out.println("resComm="+resComm);
							
							commissionService.updateBalance(user.getUserId(), "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
							commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT",0);
							
						}else if(user.getRoleId() == 3) {

							resellerId = user.getUplineId();
							pcksd=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"YesBank AEPS",id);
							if(pcksd.getPackage_id()!=null){
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						     sdComm =(pcksd.getComm()*settlementamount)/100;
							}else{
								sdComm = pcksd.getComm();	
							}
							}
							System.out.println("sdComm:::::::::"+sdComm);
							if(!resellerId.equals("admin")) {
								pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"YesBank AEPS",id);
								if(pckres.getPackage_id()!=null){
								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								resComm =(pckres.getComm()*settlementamount)/100;
								}else{
								resComm =pckres.getComm();	
								}
								resComm=resComm-sdComm;
								}
								commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0);
								}
							commissionService.updateBalance(user.getUserId(), "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
							commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT",0); 
						
						}
					}
					returnData.put("RESP_CODE", "300");
					returnData.put("RESPONSE", "SUCCESS");
					returnData.put("RESP_MSG", "Transaction Success");
				}else{
					returnData.put("RESP_CODE", "302");
					returnData.put("RESPONSE", "FAILED");
					returnData.put("RESP_MSG", "Transaction Failed");
				}
				
			}else{
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("yesbankaepsApiResponse::::::::::::::::::::::::" + e);
		}
		return returnData;
	}
	
	
	
	@Override
    public Map<String, Object> doopMeApiResponsenew(Map<String, String> request) {        
           logger_log.warn("doopMeApiResponse::"+request);
           Map<String, Object> returnData = new HashMap<String, Object>();
           double sdComm = 0.0;
           double dComm = 0.0;
           double resComm = 0.0;
           double totalAmount = 0.0;
           
           double comm = 0.0;
           double disComm = 0.0;
           double sdisComm = 0.0;
           double reComm = 0.0;
           double rComm=0.0;
           double resComm1 = 0.0;
           double reComm1=0.0;
           double charge = 0.0;
           double dcharge = 0.0;
           double sCharge = 0.0;
           double reCharge = 0.0;
           double resCharge = 0.0;
           double reCharge1 = 0.0;
           double resCharge1 = 0.0;
           PackageWiseChargeComm pckret=new PackageWiseChargeComm();
           PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
           PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
           PackageWiseChargeComm pckres=new PackageWiseChargeComm();
           PackageWiseChargeComm pckres1=new PackageWiseChargeComm();
           double distCharge = 0.0;
           double sdCharge = 0.0;
           String resellerId = "";
           String resellerId1 = "";
           String sdId = "";
           String distId = "";
           String rId="";
           Map<String, Object> param = new HashMap<String, Object>();
           Map<String, String> parameters = new HashMap<String, String>();
           try {
                           String clientId = request.get("ClientRefNo");
                           String opTransId = request.get("OprID");
                           String reStatus = request.get("Status");
                           String rechargeStatus ="";
                           if(reStatus.equals("1")){
                                   rechargeStatus="SUCCESS";
                           }else if(reStatus.equals("4")){
                                   rechargeStatus="PENDING";
                           }else if(reStatus.equals("6")){
                                   rechargeStatus="PENDING";
                           }else{
           					rechargeStatus="FAILED";
        					if(request.get("OprID").equals("")){
        						opTransId="NA";
        					}if(request.get("OprID").equals("null")){
        						opTransId="NA";
        					}
        					
        				}
                           param.put("tid", clientId);
                           List<Rechargedetails> rechargeDetails = rechargedetailsDao.getRechargeDetailsByNamedQuery("GetRechargeDetailsByTid", param);
                           if(!rechargeDetails.isEmpty()) {
                                   Rechargedetails rechargedetail = rechargeDetails.get(0);
                                   String mobileNo = rechargedetail.getMobile();
                                   Operator operator = operatorDao.getOperatorByOperatorId(rechargedetail.getOperatorId());                                        
                                   User userDetails = userDao.getUserByUserId(rechargedetail.getUserId());
                                   totalAmount = RoundNumber.roundDouble((rechargedetail.getAmount() + rechargedetail.getCharge()) - rechargedetail.getComm());                        
                                   comm = rechargedetail.getComm();
                                   charge = rechargedetail.getCharge();
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
                                                   
                                           //        System.out.println("reseller=="+comm);
                                                   pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
                                                   if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
                                                   dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
                                                   }else{
                                                   dComm=pckdis.getComm();        
                                                   }
                                                   
                                           //        System.out.println("dComm=="+dComm);
                                                   pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
                                                   if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
                                                   sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
                                                   }else{
                                                   sdComm = pcksd.getComm();        
                                                   }
                                           //        System.out.println("sdComm=="+sdComm);
                                                   pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
                                                   if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
                                                   resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
                                                   }else{
                                                   resComm =pckres.getComm();        
                                                   }
                                           
                                   } else if(userDetails.getRoleId() == 4) {
                                           // Distributor Id
                                           distId =rechargedetail.getUserId();
                                           sdId = userDetails.getUplineId();                                                        
                                           parameters = new HashMap<String, String>();
                                           parameters.put("userId", sdId);                                                        
                                           resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
                                           pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
                                           if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
                                           dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
                                           }else{
                                           dComm=pckdis.getComm();        
                                           }
                                           
                                   //        System.out.println("dComm=="+dComm);
                                           pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
                                           if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
                                           sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
                                           }else{
                                           sdComm = pcksd.getComm();        
                                           }
                                           
                                   //        System.out.println("sdComm=="+sdComm);
                                           pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
                                           if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
                                           resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
                                           }else{
                                           resComm =pckres.getComm();        
                                           }
                                           
                                           
                                   } else if(userDetails.getRoleId() == 3) {
                                           // Super Distributor Id
                                           sdId = rechargedetail.getUserId();
                                           resellerId = userDetails.getUplineId();
//                                           System.out.println("dComm=="+dComm);
                                           pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
                                           if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
                                           sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
                                           }else{
                                           sdComm = pcksd.getComm();        
                                           }
                                   //        System.out.println("sdComm=="+sdComm);
                                           pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
                                           if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
                                           resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
                                           }else{
                                           resComm =pckres.getComm();        
                                           }                                                
                                   }
                                   
                                   if(rechargedetail.getValidation().equals("PENDING")) {
                                           if(rechargeStatus.equals("SUCCESS")) {
                                                   
                                                   if(userDetails.getRoleId() == 5) {

                                                           
                                                           distId = userDetails.getUplineId();                
                                                           
                                                           // Super Distributor Id
                                                           parameters = new HashMap<String, String>();
                                                           parameters.put("userId", distId);        
                                                           sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);        
                                                           
                                                           // Reseller Id
                                                           parameters = new HashMap<String, String>();
                                                           parameters.put("userId", sdId);                                                        
                                                           resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);        
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
                                                                   parameters = new HashMap<String, String>();
                                                                   parameters.put("userId", resellerId);                                                        
                                                                   resellerId1 = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
                                                                   if(!resellerId1.equals("admin")) {
                                                                   pckres1=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
                                                                   if(pckres1.getComm_type().equalsIgnoreCase("PERCENTAGE")){
                                                                   resComm1 =(pckres1.getComm()*rechargedetail.getAmount())/100;
                                                                   }else{
                                                                   resComm1 =pckres1.getComm();        
                                                                   }
                                                                   if(resComm1==0){reComm1=0;}
                                                                   else{
                                                                   reComm1 = resComm1-resComm;
                                                                   }
                                                                   commissionService.updateBalance(resellerId1, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", reComm1, "CREDIT",0);
                                                           }
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
                                                                           EarningSurcharge earningSurcharge3 = new EarningSurcharge(2, userDetails.getWlId(), resellerId, reComm, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
                                                                           earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
                                                                           if(!resellerId1.equals("admin")) {
                                                                               EarningSurcharge earningSurcharge4 = new EarningSurcharge(2, userDetails.getWlId(), resellerId1, reComm1, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
                                                                               earningSurchargeDao.insertEarningSurcharge(earningSurcharge4);
                                                                       }
                                                                   }
                                                           /*----------------------------------------------------------------------*/
                                                           }
                                                   }else if(userDetails.getRoleId() == 4) {
                                                           
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
                                                                   parameters = new HashMap<String, String>();
                                                                   parameters.put("userId", resellerId);                                                        
                                                                   resellerId1 = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
                                                                   if(!resellerId1.equals("admin")) {
                                                                   pckres1=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
                                                                   if(pckres1.getComm_type().equalsIgnoreCase("PERCENTAGE")){
                                                                   resComm1 =(pckres1.getComm()*rechargedetail.getAmount())/100;
                                                                   }else{
                                                                   resComm1 =pckres1.getComm();        
                                                                   }
                                                                   if(resComm1==0){reComm1=0;}
                                                                   else{
                                                                   reComm1 = resComm1-resComm;
                                                                   }
                                                                   commissionService.updateBalance(resellerId1, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", reComm1, "CREDIT",0);
                                                           }
                                                           }
                                                   
                                                           if(rechargedetail.getValidation().equals("PENDING")){
                                                                   /*-------------------------------For earning and Surcharge ------------------*/
                                                                   
                                                                   // For Super Distributor
                                                                   EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm, 0.0, "Charge FOR - "+mobileNo,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
                                                                   earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
                                                                   
                                                                   // For RESELLER
                                                                   if(!resellerId.equals("admin")) {
                                                                           EarningSurcharge earningSurcharge3 = new EarningSurcharge(2, userDetails.getWlId(), resellerId, reComm, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
                                                                           earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
                                                                           if(!resellerId1.equals("admin")) {
                                                                               EarningSurcharge earningSurcharge4 = new EarningSurcharge(2, userDetails.getWlId(), resellerId1, reComm1, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
                                                                               earningSurchargeDao.insertEarningSurcharge(earningSurcharge4);
                                                                       }
                                                                   }
                                                           /*----------------------------------------------------------------------*/
                                                           }
                                                   } else if(userDetails.getRoleId() == 3) {
                                                           if(resComm==0){reComm=0;}
                                                           else{
                                                           reComm = resComm-sdComm;
                                                           }
                           
                           
                                                           //System.out.println("COMMISSION for Recharge sdcomm==="+reComm);
                                                           
                                                           if(!resellerId.equals("admin")) {
                                                                   commissionService.updateBalance(resellerId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
                                                                   parameters = new HashMap<String, String>();
                                                                   parameters.put("userId", resellerId);                                                        
                                                                   resellerId1 = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
                                                                   if(!resellerId1.equals("admin")) {
                                                                   pckres1=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
                                                                   if(pckres1.getComm_type().equalsIgnoreCase("PERCENTAGE")){
                                                                   resComm1 =(pckres1.getComm()*rechargedetail.getAmount())/100;
                                                                   }else{
                                                                   resComm1 =pckres1.getComm();        
                                                                   }
                                                                   if(resComm1==0){reComm1=0;}
                                                                   else{
                                                                   reComm1 = resComm1-resComm;
                                                                   }
                                                                   commissionService.updateBalance(resellerId1, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", reComm1, "CREDIT",0);
                                                           }
                                                           }
                                                   
                                                           if(rechargedetail.getValidation().equals("PENDING")){
                                                                   /*-------------------------------For earning and Surcharge ------------------*/
                                                                   // For RESELLER
                                                                   if(!resellerId.equals("admin")) {
                                                                           EarningSurcharge earningSurcharge3 = new EarningSurcharge(2, userDetails.getWlId(), resellerId, reComm, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
                                                                           earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
                                                                           if(!resellerId1.equals("admin")) {
                                                                               EarningSurcharge earningSurcharge4 = new EarningSurcharge(2, userDetails.getWlId(), resellerId1, reComm1, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
                                                                               earningSurchargeDao.insertEarningSurcharge(earningSurcharge4);
                                                                       }
                                                                   }
                                                           /*----------------------------------------------------------------------*/
                                                           }
                                                   }                                                        
                                                   rechargedetail.setStatus("SUCCESS");
                                                   rechargedetail.setValidation("SUCCESS");
                                                   rechargedetail.setOid(opTransId);
                                                   rechargedetailsDao.updateRechargeDetails(rechargedetail);
                                                   if(operator.getServiceType().equals("7") || operator.getServiceType().equals("6")){
                                                           param = new HashMap<>();
                                                           param.put("transactionId", request.get("tid"));
                                                           List<Utility> utiList = utilityDao.getUtilityByNamedQuery("GetUtilityByTransactionId", param );
                                                           if(!utiList.isEmpty()){
                                                                   Utility Utility=utiList.get(0);
                                                                   Utility.setStatus("SUCCESS");
                                                                   utilityDao.updateUtility(Utility);
                                                           }
                                                   }
                                                   
                                           } else if(rechargeStatus.equals("FAILED")) {
                                                   
                                                   //Individualcharge iCharge = commissionService.getIndividualChargeOnOperator(userDetails.getUserId(), operator.getOperatorId());
                                                   rechargedetail.setStatus("FAILED");
                                                   rechargedetail.setValidation("FAILED");
                                                   rechargedetail.setOid(opTransId);
                                                   rechargedetailsDao.updateRechargeDetails(rechargedetail);
                                                   
                                                   if(operator.getServiceType().equals("7") || operator.getServiceType().equals("6")){
                                                           param = new HashMap<>();
                                                           param.put("transactionId", request.get("tid"));
                                                           List<Utility> utiList = utilityDao.getUtilityByNamedQuery("GetUtilityByTransactionId", param );
                                                           if(!utiList.isEmpty()){
                                                                   Utility Utility=utiList.get(0);
                                                                   Utility.setStatus("FAILED");
                                                                   utilityDao.updateUtility(Utility);
                                                           }
                                                   }
                                                   commissionService.updateBalance(userDetails.getUserId(), "REFUND for Recharge - "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
                                                   
                                                   // --------------------- Commission Refund -------------------------------------------------------------
                                                   
                                                   if(userDetails.getRoleId() == 5) {
                                                           
                                                           // Charge REFUND
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
                                                           
                                                           if(distCharge==0){dcharge=0;}
                                                           else{dcharge = charge - distCharge;}
                                                           if(sdCharge==0){sCharge=0;}
                                                           else{sCharge = distCharge - sdCharge;}
                                                           
                                           
                                                           commissionService.updateBalance(distId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", dcharge, "DEBIT",0);                                                                        
                                                           commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
                                                           
                                                           if(!resellerId.equals("admin")) {
                                                                   if(resCharge==0){reCharge=0;}
                                                                   else{reCharge = sdCharge - resCharge;}
                                                                   
                                                                   commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
                                                                   if(!resellerId1.equals("admin")) {
                                                                       if(pckres1.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
                                                                       resCharge1 = (pckres1.getCharge()*rechargedetail.getAmount())/100;
                                                                       }else{
                                                                       resCharge1 = pckres1.getCharge();        
                                                                       }
                                                                       if(resCharge1==0){reCharge1=0;}
                                                                       else{reCharge1 = resCharge - resCharge1;}
                                                                       
                                                                       commissionService.updateBalance(resellerId1, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge1, "DEBIT",0);
                                                               }
                                                           }
                                                   
                                                   
                                                           
                                                   } else if(userDetails.getRoleId() == 4) {        
                                                           
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
                                                           if(sdCharge==0){sCharge=0;}
                                                           else{sCharge = charge - sdCharge;}
                                                                   
                                                           commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
                                                           
                                                           if(!resellerId.equals("admin")) {
                                                                   if(resCharge==0){reCharge=0;}
                                                                   else{reCharge = sdCharge - resCharge;}
                                                                   
                                                                   commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
                                                                   if(!resellerId1.equals("admin")) {
                                                                       if(pckres1.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
                                                                       resCharge1 = (pckres1.getCharge()*rechargedetail.getAmount())/100;
                                                                       }else{
                                                                       resCharge1 = pckres1.getCharge();        
                                                                       }
                                                                       if(resCharge1==0){reCharge1=0;}
                                                                       else{reCharge1 = resCharge - resCharge1;}
                                                                       
                                                                       commissionService.updateBalance(resellerId1, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge1, "DEBIT",0);
                                                               }
                                                           }
                                                   
                                                   } else if(userDetails.getRoleId() == 3) {                                                                
                                                           
                                                           if(!resellerId.equals("admin")) {
                                                                   if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
                                                                           resCharge = (pckres.getCharge()*rechargedetail.getAmount())/100;
                                                                           }else{
                                                                           resCharge = pckres.getCharge();        
                                                                           }
                                                           }
                                                           
                                                           if(!resellerId.equals("admin")) {
                                                                   if(resCharge==0){reCharge=0;}
                                                                   else{reCharge = charge - resCharge;}
                                   
                                                                   commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
                                                                   if(!resellerId1.equals("admin")) {
                                                                       if(pckres1.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
                                                                       resCharge1 = (pckres1.getCharge()*rechargedetail.getAmount())/100;
                                                                       }else{
                                                                       resCharge1 = pckres1.getCharge();        
                                                                       }
                                                                       if(resCharge1==0){reCharge1=0;}
                                                                       else{reCharge1 = resCharge - resCharge1;}
                                                                       
                                                                       commissionService.updateBalance(resellerId1, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge1, "DEBIT",0);
                                                               }
                                                           }
                                                   
                                                   }
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
                                                                   responseUrl = list.get(0).getResponseUrl() + "?tranId="        + rechargedetail.getPtid() + "&opTId=" + opTransId + "&status="        + rechargeStatus;
                                                                   responseUrlResponse = sendRechargeRequest.sendRechagreReq(responseUrl);
                                                           } else {
                                                                   responseUrl = list.get(0).getResponseUrl() + "?tranId="        + rechargedetail.getPtid() + "&opTId=" + opTransId + "&status=" + rechargeStatus;
                                                                   responseUrlResponse = "No Response Url Set";
                                                           }                                                                        
                                                   } catch (Exception e) {
                                                           responseUrlResponse = "EXCEPTION";
                                                           logger_log.error("doopMeApiResponse ::::::::::::::: SENDING RESPONSE ERROR");
                                                           logger_log.error("doopMeApiResponse ::::::::::::::: " + e);
                                                   }
                                                   
                                                   Apitrace apitrace = new Apitrace(userDetails.getUserId(), rechargedetail.getPtid(),        responseUrl, responseUrlResponse, GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime());
                                                   apitraceDao.insertApitrace(apitrace);
                                           }
                                   } else {
                                           returnData.put("status", "0");
                                           returnData.put("message", "Already Status updated.");
                                   }
                           } else {
                                   returnData.put("status", "0");
                                   returnData.put("message", "No Recharge Details Found.");
                           }
                  
           } catch (Exception e) {
                   returnData.put("status", "0");
                   returnData.put("message", "Exception! Please contact to admin.");
                   logger_log.error("doopMeApiResponse ::::::::::::::: " + e);
           }
           return returnData;
   }
	
	
	@Override
	public Map<String, Object> instantpayresponse(Map<String, String> request) {
		logger_log.warn("instantpayresponse:Final:::"+request);
		Map<String, Object> returnData = new HashMap<String, Object>();
		double sdComm = 0.0;
		double dComm = 0.0;
		double resComm = 0.0;
		double totalAmount = 0.0;
		
		double comm = 0.0;
		double disComm = 0.0;
		double sdisComm = 0.0;
		double reComm = 0.0;
		double rComm=0.0;
		double charge = 0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		double resCharge = 0.0;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double distCharge = 0.0;
		double sdCharge = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";
		String rId="";
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				String clientId = request.get("ipay_id");
				String opTransId = request.get("opr_id");
				String rechargeStatus = request.get("status");
				param.put("tid", clientId);
				//<ResponseCounter> listcountr=responsecounterdao.getResponseCounterByNamedQuery("getResponseCounter", param);
				/*if(listcountr.isEmpty()){
					ResponseCounter ResponseCounter= new ResponseCounter(clientId,1);
				boolean flag=responsecounterdao.insertResponseCounter(ResponseCounter);
					if(flag){*/
			
				List<Rechargedetails> rechargeDetails = rechargedetailsDao.getRechargeDetailsByNamedQuery("GetRechargeDetailsByTid", param);
				if(!rechargeDetails.isEmpty()) {
					Rechargedetails rechargedetail = rechargeDetails.get(0);
					String mobileNo = rechargedetail.getMobile();
					Operator operator = operatorDao.getOperatorByOperatorId(rechargedetail.getOperatorId());					
					User userDetails = userDao.getUserByUserId(rechargedetail.getUserId());
					totalAmount = RoundNumber.roundDouble((rechargedetail.getAmount() + rechargedetail.getCharge()) - rechargedetail.getComm());			
					comm = rechargedetail.getComm();
					charge = rechargedetail.getCharge();
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
							
						//	System.out.println("reseller=="+comm);
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
							}else{
							dComm=pckdis.getComm();	
							}
							
						//	System.out.println("dComm=="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
							}else{
							sdComm = pcksd.getComm();	
							}
						//	System.out.println("sdComm=="+sdComm);
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
							}else{
							resComm =pckres.getComm();	
							}
						
					} else if(userDetails.getRoleId() == 4) {
						// Distributor Id
						distId =rechargedetail.getUserId();
						sdId = userDetails.getUplineId();							
						parameters = new HashMap<String, String>();
						parameters.put("userId", sdId);							
						resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
						pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
						if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
						}else{
						dComm=pckdis.getComm();	
						}
						
					//	System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
						}else{
						sdComm = pcksd.getComm();	
						}
						
					//	System.out.println("sdComm=="+sdComm);
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
						}else{
						resComm =pckres.getComm();	
						}
						
						
					} else if(userDetails.getRoleId() == 3) {
						// Super Distributor Id
						sdId = rechargedetail.getUserId();
						resellerId = userDetails.getUplineId();
//						System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
						}else{
						sdComm = pcksd.getComm();	
						}
					//	System.out.println("sdComm=="+sdComm);
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
						}else{
						resComm =pckres.getComm();	
						}						
					}
					
					if(rechargedetail.getValidation().equals("PENDING")) {
						if(rechargeStatus.equalsIgnoreCase("SUCCESS")) {
							rechargedetail.setStatus("SUCCESS");
							rechargedetail.setValidation("SUCCESS");
							rechargedetail.setOid(opTransId);
							rechargedetailsDao.updateRechargeDetails(rechargedetail);
							param=new HashMap<String, Object>();
							param.put("transactionId", clientId);
							List<Utility> ulidt=utilityDao.getUtilityByNamedQuery("GetUtilityByTransactionId", param);
							if(!ulidt.isEmpty()){
								Utility u=ulidt.get(0);
								u.setStatus("SUCCESS");
								utilityDao.updateUtility(u);
							}
							if(userDetails.getRoleId() == 5) {

								
								distId = userDetails.getUplineId();		
								
								// Super Distributor Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", distId);	
								sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
								
								// Reseller Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", sdId);							
								resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
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
							}else if(userDetails.getRoleId() == 4) {
								
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
				
				
								//System.out.println("COMMISSION for Recharge sdcomm==="+reComm);
								
								if(!resellerId.equals("admin")) {
									commissionService.updateBalance(resellerId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
								}
							
								if(rechargedetail.getValidation().equals("PENDING")){
									/*-------------------------------For earning and Surcharge ------------------*/
									// For RESELLER
									if(!resellerId.equals("admin")) {
										EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, reComm, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
										earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
									}
								/*----------------------------------------------------------------------*/
								}
							}							
							
							
						} else if(rechargeStatus.equalsIgnoreCase("FAILED")) {
							
							//Individualcharge iCharge = commissionService.getIndividualChargeOnOperator(userDetails.getUserId(), operator.getOperatorId());
							rechargedetail.setStatus("FAILED");
							rechargedetail.setValidation("FAILED");
							rechargedetail.setOid(opTransId);
							rechargedetailsDao.updateRechargeDetails(rechargedetail);
							param=new HashMap<String, Object>();
									param.put("transactionId", clientId);
									List<Utility> ulidt=utilityDao.getUtilityByNamedQuery("GetUtilityByTransactionId", param);
									if(!ulidt.isEmpty()){
										Utility u=ulidt.get(0);
										u.setStatus("SUCCESS");
										utilityDao.updateUtility(u);
									}
							commissionService.updateBalance(userDetails.getUserId(), "REFUND for Recharge - "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
							
							// --------------------- Commission Refund -------------------------------------------------------------
							
							if(userDetails.getRoleId() == 5) {
								
								// Charge REFUND
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
								
								if(distCharge==0){dcharge=0;}
								else{dcharge = charge - distCharge;}
								if(sdCharge==0){sCharge=0;}
								else{sCharge = distCharge - sdCharge;}
								
						
								commissionService.updateBalance(distId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", dcharge, "DEBIT",0);									
								commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = sdCharge - resCharge;}
									
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							
								
							} else if(userDetails.getRoleId() == 4) {	
								
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
								if(sdCharge==0){sCharge=0;}
								else{sCharge = charge - sdCharge;}
									
								commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = sdCharge - resCharge;}
									
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							} else if(userDetails.getRoleId() == 3) {								
								
								if(!resellerId.equals("admin")) {
									if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
										resCharge = (pckres.getCharge()*rechargedetail.getAmount())/100;
										}else{
										resCharge = pckres.getCharge();	
										}
								}
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = charge - resCharge;}
					
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							}
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
									responseUrl = list.get(0).getResponseUrl() + "?tranId="	+ rechargedetail.getPtid() + "&opTId=" + opTransId + "&status="	+ rechargeStatus;
									responseUrlResponse = sendRechargeRequest.sendRechagreReq(responseUrl);
								} else {
									responseUrl = list.get(0).getResponseUrl() + "?tranId="	+ rechargedetail.getPtid() + "&opTId=" + opTransId + "&status=" + rechargeStatus;
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
						returnData.put("message", "Already Status updated.");
					}
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Recharge Details Found.");
				}
				/*}*/
				/*}else{
					returnData.put("status", "0");
					returnData.put("message", "validation Updated");
				}*/
			} else {
				returnData.put("status", "0");
				returnData.put("message", "Parameter not pass correctly.");
			}
		} catch (Exception e) {
			returnData.put("status", "0");
			returnData.put("message", "Exception! Please contact to admin.");
			logger_log.error("doopMeApiResponse ::::::::::::::: " + e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> cellmoneyresponse(Map<String, String> request) {
		logger_log.warn("cellmoneyresponse:::::::::"+request);
		Map<String, Object> returnData = new HashMap<String, Object>();
		double sdComm = 0.0;
		double dComm = 0.0;
		double resComm = 0.0;
		double totalAmount = 0.0;
		
		double comm = 0.0;
		double disComm = 0.0;
		double sdisComm = 0.0;
		double reComm = 0.0;
		double rComm=0.0;
		double charge = 0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		double resCharge = 0.0;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double distCharge = 0.0;
		double sdCharge = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";
		String rId="";
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			/*if (UtilityClass.checkParameterIsNull(request)) {*/
				String rechargeStatus = request.get("STMSG");
				String clientId = request.get("TNO");
				String status = request.get("ST");
				logger_log.warn("CELLMONEYSTATUS1::::::::::::::::::::::::::::::"+status);
				String opTransId ="0";
				/*if(rechargeStatus.equals("Failure")){
					opTransId ="0";
				}else{*/
				 opTransId = request.get("TID");
				//}
				if(!status.equalsIgnoreCase("4")&&!status.equalsIgnoreCase("6")){
				param.put("tid",clientId);
				List<ResponseCounter> listcountr=responsecounterdao.getResponseCounterByNamedQuery("getResponseCounter", param);
				if(listcountr.isEmpty()){
					ResponseCounter ResponseCounter= new ResponseCounter(clientId,1);
				boolean flag=responsecounterdao.insertResponseCounter(ResponseCounter);
				if(flag){
				List<Rechargedetails> rechargeDetails = rechargedetailsDao.getRechargeDetailsByNamedQuery("GetRechargeDetailsByTid", param);
				if(!rechargeDetails.isEmpty()) {
					Rechargedetails rechargedetail = rechargeDetails.get(0);
					logger_log.warn("rechargedetail:::::::::::::::::::"+rechargedetail);
					logger_log.warn("CELLMONEYSTATUS3:::::::::::::::::::::::::::::"+rechargedetail);
					String mobileNo = rechargedetail.getMobile();
					Operator operator = operatorDao.getOperatorByOperatorId(rechargedetail.getOperatorId());					
					User userDetails = userDao.getUserByUserId(rechargedetail.getUserId());
					totalAmount = RoundNumber.roundDouble((rechargedetail.getAmount() + rechargedetail.getCharge()) - rechargedetail.getComm());			
					comm = rechargedetail.getComm();
					charge = rechargedetail.getCharge();
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
							
						//	System.out.println("reseller=="+comm);
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
							}else{
							dComm=pckdis.getComm();	
							}
							
						//	System.out.println("dComm=="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
							}else{
							sdComm = pcksd.getComm();	
							}
						//	System.out.println("sdComm=="+sdComm);
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
							}else{
							resComm =pckres.getComm();	
							}
						
					} else if(userDetails.getRoleId() == 4) {
						// Distributor Id
						distId =rechargedetail.getUserId();
						sdId = userDetails.getUplineId();							
						parameters = new HashMap<String, String>();
						parameters.put("userId", sdId);							
						resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
						pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
						if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
						}else{
						dComm=pckdis.getComm();	
						}
						
					//	System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
						}else{
						sdComm = pcksd.getComm();	
						}
						
					//	System.out.println("sdComm=="+sdComm);
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
						}else{
						resComm =pckres.getComm();	
						}
						
						
					} else if(userDetails.getRoleId() == 3) {
						// Super Distributor Id
						sdId = rechargedetail.getUserId();
						resellerId = userDetails.getUplineId();
//						System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
						}else{
						sdComm = pcksd.getComm();	
						}
					//	System.out.println("sdComm=="+sdComm);
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
						}else{
						resComm =pckres.getComm();	
						}						
					}
					System.out.println("VALIDATION::::::::::::::::::"+rechargedetail.getValidation());
					if(rechargedetail.getValidation().equals("PENDING")) {
						if(rechargeStatus.equalsIgnoreCase("SUCCESS")) {
							
							if(userDetails.getRoleId() == 5) {

								
								distId = userDetails.getUplineId();		
								
								// Super Distributor Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", distId);	
								sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
								
								// Reseller Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", sdId);							
								resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
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
							}else if(userDetails.getRoleId() == 4) {
								
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
				
				
								//System.out.println("COMMISSION for Recharge sdcomm==="+reComm);
								
								if(!resellerId.equals("admin")) {
									commissionService.updateBalance(resellerId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
								}
							
								if(rechargedetail.getValidation().equals("PENDING")){
									/*-------------------------------For earning and Surcharge ------------------*/
									// For RESELLER
									if(!resellerId.equals("admin")) {
										EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, reComm, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
										earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
									}
								/*----------------------------------------------------------------------*/
								}
							}							
							rechargedetail.setStatus("SUCCESS");
							rechargedetail.setValidation("SUCCESS");
							rechargedetail.setOid(opTransId);
							rechargedetailsDao.updateRechargeDetails(rechargedetail);
							
						} else if(status.equalsIgnoreCase("2") || status.equalsIgnoreCase("3") ||status.equalsIgnoreCase("5")) {
							logger_log.warn("CELLMONEYSTATUS::::::::::::::::::::::::::::::"+status);
							//Individualcharge iCharge = commissionService.getIndividualChargeOnOperator(userDetails.getUserId(), operator.getOperatorId());
							rechargedetail.setStatus("FAILED");
							rechargedetail.setValidation("FAILED");
							rechargedetail.setOid("0");
							rechargedetailsDao.updateRechargeDetails(rechargedetail);
							commissionService.updateBalance(userDetails.getUserId(), "REFUND for Recharge - "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
							
							// --------------------- Commission Refund -------------------------------------------------------------
							
							if(userDetails.getRoleId() == 5) {
								
								// Charge REFUND
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
								
								if(distCharge==0){dcharge=0;}
								else{dcharge = charge - distCharge;}
								if(sdCharge==0){sCharge=0;}
								else{sCharge = distCharge - sdCharge;}
								
						
								commissionService.updateBalance(distId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", dcharge, "DEBIT",0);									
								commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = sdCharge - resCharge;}
									
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							
								
							} else if(userDetails.getRoleId() == 4) {	
								
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
								if(sdCharge==0){sCharge=0;}
								else{sCharge = charge - sdCharge;}
									
								commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = sdCharge - resCharge;}
									
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							} else if(userDetails.getRoleId() == 3) {								
								
								if(!resellerId.equals("admin")) {
									if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
										resCharge = (pckres.getCharge()*rechargedetail.getAmount())/100;
										}else{
										resCharge = pckres.getCharge();	
										}
								}
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = charge - resCharge;}
					
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							}
							returnData.put("status", "0");
							returnData.put("message", "Status : "+rechargeStatus+" Validation Status update Successfully.");
							
							if(rechargedetail.getSource().equals("API")){
								param = new HashMap<>();
								param.put("userId", userDetails.getUserId());
								List<Apisetting> list = apisettingDao.getApisettingByNamedQuery("getApisettingByuserId", param);
								String responseUrl = "";
								String responseUrlResponse = "";
								try {
									if (!list.isEmpty()) {
										responseUrl = list.get(0).getResponseUrl() + "?tranId="	+ rechargedetail.getPtid() + "&opTId=" + opTransId + "&status="	+ rechargeStatus;
										responseUrlResponse = sendRechargeRequest.sendRechagreReq(responseUrl);
									} else {
										responseUrl = list.get(0).getResponseUrl() + "?tranId="	+ rechargedetail.getPtid() + "&opTId=" + opTransId + "&status=" + rechargeStatus;
										responseUrlResponse = "No Response Url Set";
									}									
								} catch (Exception e) {
									responseUrlResponse = "EXCEPTION";
									logger_log.error("cellmoneyresponse ::::::::::::::: SENDING RESPONSE ERROR");
									logger_log.error("cellmoneyresponse ::::::::::::::: " + e);
								}
								
								Apitrace apitrace = new Apitrace(userDetails.getUserId(), rechargedetail.getPtid(),	responseUrl, responseUrlResponse, GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime());
								apitraceDao.insertApitrace(apitrace);
							}
							
							
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Your Request in process.");
						}
					} else {
						returnData.put("status", "0");
						returnData.put("message", "Already Status updated.");
					}
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Recharge Details Found.");
				}
				}
				}else{
					returnData.put("status", "0");
					returnData.put("message", "validation Updated");
				}
			}
			/*} else {
				returnData.put("status", "0");
				returnData.put("message", "Parameter not pass correctly.");
			}*/
		} catch (Exception e) {
			returnData.put("status", "0");
			returnData.put("message", "Exception! Please contact to admin.");
			logger_log.error("cellmoneyresponse ::::::::::::::: " + e);
		}
		return returnData;
	}
	
	
	@Override
	public Map<String, Object> doopMeApiResponse(Map<String, String> request) {		
		Map<String, Object> returnData = new HashMap<String, Object>();
		double sdComm = 0.0;
		double dComm = 0.0;
		double resComm = 0.0;
		double totalAmount = 0.0;
		
		double comm = 0.0;
		double disComm = 0.0;
		double sdisComm = 0.0;
		double reComm = 0.0;
		double rComm=0.0;
		double charge = 0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		double resCharge = 0.0;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double distCharge = 0.0;
		double sdCharge = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";
		String rId="";
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				String clientId = request.get("request_id");
				String opTransId = request.get("optxnid");
				String rechargeStatus = request.get("status");
				param.put("tid", clientId);
				List<Rechargedetails> rechargeDetails = rechargedetailsDao.getRechargeDetailsByNamedQuery("GetRechargeDetailsByTid", param);
				if(!rechargeDetails.isEmpty()) {
					Rechargedetails rechargedetail = rechargeDetails.get(0);
					String mobileNo = rechargedetail.getMobile();
					Operator operator = operatorDao.getOperatorByOperatorId(rechargedetail.getOperatorId());					
					User userDetails = userDao.getUserByUserId(rechargedetail.getUserId());
					totalAmount = RoundNumber.roundDouble((rechargedetail.getAmount() + rechargedetail.getCharge()) - rechargedetail.getComm());			
					comm = rechargedetail.getComm();
					charge = rechargedetail.getCharge();
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
							
						//	System.out.println("reseller=="+comm);
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
							}else{
							dComm=pckdis.getComm();	
							}
							
						//	System.out.println("dComm=="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
							}else{
							sdComm = pcksd.getComm();	
							}
						//	System.out.println("sdComm=="+sdComm);
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
							}else{
							resComm =pckres.getComm();	
							}
						
					} else if(userDetails.getRoleId() == 4) {
						// Distributor Id
						distId =rechargedetail.getUserId();
						sdId = userDetails.getUplineId();							
						parameters = new HashMap<String, String>();
						parameters.put("userId", sdId);							
						resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
						pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
						if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
						}else{
						dComm=pckdis.getComm();	
						}
						
					//	System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
						}else{
						sdComm = pcksd.getComm();	
						}
						
					//	System.out.println("sdComm=="+sdComm);
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
						}else{
						resComm =pckres.getComm();	
						}
						
						
					} else if(userDetails.getRoleId() == 3) {
						// Super Distributor Id
						sdId = rechargedetail.getUserId();
						resellerId = userDetails.getUplineId();
//						System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
						}else{
						sdComm = pcksd.getComm();	
						}
					//	System.out.println("sdComm=="+sdComm);
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
						}else{
						resComm =pckres.getComm();	
						}						
					}
					
					if(rechargedetail.getValidation().equals("PENDING")) {
						if(rechargeStatus.equals("SUCCESS")) {
							
							if(userDetails.getRoleId() == 5) {

								
								distId = userDetails.getUplineId();		
								
								// Super Distributor Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", distId);	
								sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
								
								// Reseller Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", sdId);							
								resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
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
							}else if(userDetails.getRoleId() == 4) {
								
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
				
				
								//System.out.println("COMMISSION for Recharge sdcomm==="+reComm);
								
								if(!resellerId.equals("admin")) {
									commissionService.updateBalance(resellerId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
								}
							
								if(rechargedetail.getValidation().equals("PENDING")){
									/*-------------------------------For earning and Surcharge ------------------*/
									// For RESELLER
									if(!resellerId.equals("admin")) {
										EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, reComm, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
										earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
									}
								/*----------------------------------------------------------------------*/
								}
							}							
							rechargedetail.setStatus("SUCCESS");
							rechargedetail.setValidation("SUCCESS");
							rechargedetail.setOid(opTransId);
							rechargedetailsDao.updateRechargeDetails(rechargedetail);
							
						} else if(rechargeStatus.equals("FAILED")) {
							
							//Individualcharge iCharge = commissionService.getIndividualChargeOnOperator(userDetails.getUserId(), operator.getOperatorId());
							rechargedetail.setStatus("FAILED");
							rechargedetail.setValidation("FAILED");
							rechargedetail.setOid(opTransId);
							rechargedetailsDao.updateRechargeDetails(rechargedetail);
							commissionService.updateBalance(userDetails.getUserId(), "REFUND for Recharge - "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
							
							// --------------------- Commission Refund -------------------------------------------------------------
							
							if(userDetails.getRoleId() == 5) {
								
								// Charge REFUND
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
								
								if(distCharge==0){dcharge=0;}
								else{dcharge = charge - distCharge;}
								if(sdCharge==0){sCharge=0;}
								else{sCharge = distCharge - sdCharge;}
								
						
								commissionService.updateBalance(distId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", dcharge, "DEBIT",0);									
								commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = sdCharge - resCharge;}
									
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							
								
							} else if(userDetails.getRoleId() == 4) {	
								
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
								if(sdCharge==0){sCharge=0;}
								else{sCharge = charge - sdCharge;}
									
								commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = sdCharge - resCharge;}
									
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							} else if(userDetails.getRoleId() == 3) {								
								
								if(!resellerId.equals("admin")) {
									if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
										resCharge = (pckres.getCharge()*rechargedetail.getAmount())/100;
										}else{
										resCharge = pckres.getCharge();	
										}
								}
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = charge - resCharge;}
					
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							}
							returnData.put("status", "0");
							returnData.put("message", "Status : "+rechargeStatus+" Validation Status update Successfully.");
							
							if(rechargedetail.getSource().equals("API")){
								param = new HashMap<>();
								param.put("userId", userDetails.getUserId());
								List<Apisetting> list = apisettingDao.getApisettingByNamedQuery("getApisettingByuserId", param);
								String responseUrl = "";
								String responseUrlResponse = "";
								try {
									if (!list.isEmpty()) {
										responseUrl = list.get(0).getResponseUrl() + "?tranId="	+ rechargedetail.getPtid() + "&opTId=" + opTransId + "&status="	+ rechargeStatus;
										responseUrlResponse = sendRechargeRequest.sendRechagreReq(responseUrl);
									} else {
										responseUrl = list.get(0).getResponseUrl() + "?tranId="	+ rechargedetail.getPtid() + "&opTId=" + opTransId + "&status=" + rechargeStatus;
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
							returnData.put("message", "Your Request in process.");
						}
					} else {
						returnData.put("status", "0");
						returnData.put("message", "Already Status updated.");
					}
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Recharge Details Found.");
				}
			} else {
				returnData.put("status", "0");
				returnData.put("message", "Parameter not pass correctly.");
			}
		} catch (Exception e) {
			returnData.put("status", "0");
			returnData.put("message", "Exception! Please contact to admin.");
			logger_log.error("doopMeApiResponse ::::::::::::::: " + e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> payaepsresponse(PaymonkResponse paymonk) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag = false;
		String username = "";
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		double amount = 0.0;
		String processingCode = "";
		boolean update = false;
		double settlementamount = 0.0;
		double settlementamountprev = 0.0;
		double settlementamountnew = 0.0;
		int id = 0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		try {
			
			
			processingCode = paymonk.getProcessingCode();	
			if(paymonk.getStatusCode().equalsIgnoreCase("00")){
			String agentcode = paymonk.getAgentcode();	
			param.put("agentcode",agentcode);
			param.put("api","RBL");
			List<AEPSUserMap> list = aepsuserdao.getAEPSUserMapByNamedQuery("getUsernamebyagentcode",param);
			if(!list.isEmpty()){
			AEPSUserMap aeps = list.get(0);
			username = aeps.getUsername();
			settlementamount = paymonk.getAmount();
			
			param = new HashMap<String, Object>();
			param.put("api","RBL");
			User userDetails = userDao.getUserByUserId(username);
			List<AEPSCommission> aepscommlist = aepscommissiondao.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi",param);
			for(AEPSCommission comm2 : aepscommlist){
				if(settlementamount>=comm2.getSlab1() && settlementamount<=comm2.getSlab2()){
					id = comm2.getId();
					break;
				}
			}
			System.out.println("id:::::::::::::::::::::::::::::"+id);
			param = new HashMap<String, Object>();
			
			if(userDetails.getRoleId() == 5) {
				pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"RBL AEPS",id) ;
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
				retComm =(pckret.getComm()*settlementamount)/100;
				}else{
				retComm =pckret.getComm();	
				}
				comm = retComm;
				}
				System.out.println("comm:::::::::::::::::::::::::::::"+comm);
			//	System.out.println("reseller=="+comm);
				pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"RBL AEPS",id); 
				if(pckdis.getPackage_id()!=null){
				if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				dComm =(pckdis.getComm()*settlementamount)/100;
				}else{
				dComm=pckdis.getComm();	
				}
				dComm=dComm-comm;
				}
				System.out.println("dComm:::::::::::::::::::::::::::::"+dComm);
			//	System.out.println("dComm=="+dComm);
				pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"RBL AEPS",id);
				if(pcksd.getPackage_id()!=null){
				if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				sdComm =(pcksd.getComm()*settlementamount)/100;
				}else{
				sdComm = pcksd.getComm();	
				}
				sdComm=sdComm-dComm;
				}
				
			//	System.out.println("sdComm=="+sdComm);
				pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"RBL AEPS",id);
				if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				resComm =(pckres.getComm()*settlementamount)/100;
				}else{
				resComm =pckres.getComm();	
				}
			//	System.out.println("resComm=="+resComm);
				commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
				commissionService.updateBalance(distId, "AEPS Commission For Amount - "+settlementamount, "Commission", dComm, "CREDIT",0);
				commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT",0);
				
			}else if(userDetails.getRoleId() == 4) {

				//distId = userDetails.getUserId();	
				sdId = userDetails.getUplineId();	
				
				parameters = new HashMap<String, String>();
				parameters.put("userId", sdId);							
				resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
				
				pckdis=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"RBL AEPS",id); 
				if(pckdis.getPackage_id()!=null){
				if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				dComm =(pckdis.getComm()*settlementamount)/100;
				}else{
				dComm=pckdis.getComm();	
				}
				}
				
				comm = dComm;
				
			//	System.out.println("dComm="+dComm);
				pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"RBL AEPS",id);
				System.out.println("pcksd:::::::::::::::::"+pcksd);
				if(pcksd.getPackage_id()!=null){
				if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				sdComm =(pcksd.getComm()*settlementamount)/100;
				}else{
				sdComm = pcksd.getComm();	
				}
				sdComm=sdComm-dComm;
				}
				
				//System.out.println("sdComm="+sdComm);
				if(!resellerId.equals("admin")) {
				pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"RBL AEPS",id);
				if(pckres.getPackage_id()!=null){
				if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				resComm =(pckres.getComm()*settlementamount)/100;
				}else{
				resComm =pckres.getComm();	
				}
				resComm=resComm-sdComm;
				}
				commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0);
				}
				//System.out.println("resComm="+resComm);
				
				commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
				commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT",0);
				
			}else if(userDetails.getRoleId() == 3) {

				resellerId = userDetails.getUplineId();
				pcksd=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"RBL AEPS",id);
				if(pcksd.getPackage_id()!=null){
				if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
			     sdComm =(pcksd.getComm()*settlementamount)/100;
				}else{
					sdComm = pcksd.getComm();	
				}
				}
				System.out.println("sdComm:::::::::"+sdComm);
				if(!resellerId.equals("admin")) {
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"RBL AEPS",id);
					if(pckres.getPackage_id()!=null){
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*settlementamount)/100;
					}else{
					resComm =pckres.getComm();	
					}
					resComm=resComm-sdComm;
					}
					commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0);
					}
				commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
				commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT",0); 
			
			}
			if(processingCode.equalsIgnoreCase("010000")){
				param.put("username",username);
				List<SettlementBalance> settlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
				if(settlelist.isEmpty()){
					SettlementBalance SettlementBalance = new SettlementBalance(username,settlementamount);
					settlementbalancedao.insertSettlementBalance(SettlementBalance);	
				}else{
					SettlementBalance SettlementBalance = settlelist.get(0);
					settlementamountprev = SettlementBalance.getSettlementbalance();
					settlementamountnew = settlementamountprev+settlementamount;
					SettlementBalance.setSettlementbalance(settlementamountnew);
					settlementbalancedao.updateSettlementBalance(SettlementBalance);
				}
			}else if(processingCode.equalsIgnoreCase("210000")){
			flag = commissionService.updateBalance(username, "ADD by AEPS", "CREDIT FOR AEPS Aadharno"+paymonk.getAadharNumber()+"and Amount"+paymonk.getAmount(),settlementamount,"DEBIT",0);	
			}else if(processingCode.equalsIgnoreCase("310000")){
			update = true;	
			}
			logger_log.warn("aepsresponseupdate::::::::::::::::::::::::" +update);
			}
			
			}
	

		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("aepsresponse::::::::::::::::::::::::" + e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> wellbornapires(Map<String, String> request) {	
		logger_log.warn("wellbornapires final response"+request);
		Map<String, Object> returnData = new HashMap<String, Object>();
		double sdComm = 0.0;
		double dComm = 0.0;
		double resComm = 0.0;
		double totalAmount = 0.0;
		
		double comm = 0.0;
		double disComm = 0.0;
		double sdisComm = 0.0;
		double reComm = 0.0;
		double rComm=0.0;
		double charge = 0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		double resCharge = 0.0;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double distCharge = 0.0;
		double sdCharge = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";
		String rId="";
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				String clientId = request.get("trans_no");
				String opTransId = request.get("success_id");
				String rechargeStatus = request.get("status");
				param.put("tid", clientId);
				List<ResponseCounter> listcountr=responsecounterdao.getResponseCounterByNamedQuery("getResponseCounter", param);
				if(listcountr.isEmpty()){
					ResponseCounter ResponseCounter= new ResponseCounter(clientId,1);
				boolean flag=responsecounterdao.insertResponseCounter(ResponseCounter);
				if(flag){
				List<Rechargedetails> rechargeDetails = rechargedetailsDao.getRechargeDetailsByNamedQuery("GetRechargeDetailsByTid", param);
				if(!rechargeDetails.isEmpty()) {
					Rechargedetails rechargedetail = rechargeDetails.get(0);
					String mobileNo = rechargedetail.getMobile();
					Operator operator = operatorDao.getOperatorByOperatorId(rechargedetail.getOperatorId());					
					User userDetails = userDao.getUserByUserId(rechargedetail.getUserId());
					totalAmount = RoundNumber.roundDouble((rechargedetail.getAmount() + rechargedetail.getCharge()) - rechargedetail.getComm());			
					comm = rechargedetail.getComm();
					charge = rechargedetail.getCharge();
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
							
						//	System.out.println("reseller=="+comm);
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
							}else{
							dComm=pckdis.getComm();	
							}
							
						//	System.out.println("dComm=="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
							}else{
							sdComm = pcksd.getComm();	
							}
						//	System.out.println("sdComm=="+sdComm);
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
							}else{
							resComm =pckres.getComm();	
							}
						
					} else if(userDetails.getRoleId() == 4) {
						// Distributor Id
						distId =rechargedetail.getUserId();
						sdId = userDetails.getUplineId();							
						parameters = new HashMap<String, String>();
						parameters.put("userId", sdId);							
						resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
						pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
						if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
						}else{
						dComm=pckdis.getComm();	
						}
						
					//	System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
						}else{
						sdComm = pcksd.getComm();	
						}
						
					//	System.out.println("sdComm=="+sdComm);
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
						}else{
						resComm =pckres.getComm();	
						}
						
						
					} else if(userDetails.getRoleId() == 3) {
						// Super Distributor Id
						sdId = rechargedetail.getUserId();
						resellerId = userDetails.getUplineId();
//						System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
						}else{
						sdComm = pcksd.getComm();	
						}
					//	System.out.println("sdComm=="+sdComm);
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
						}else{
						resComm =pckres.getComm();	
						}						
					}
					
					if(rechargedetail.getValidation().equals("PENDING")) {
						if(rechargeStatus.equalsIgnoreCase("SUCCESS")) {
							
							if(userDetails.getRoleId() == 5) {

								
								distId = userDetails.getUplineId();		
								
								// Super Distributor Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", distId);	
								sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
								
								// Reseller Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", sdId);							
								resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
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
							}else if(userDetails.getRoleId() == 4) {
								
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
				
				
								//System.out.println("COMMISSION for Recharge sdcomm==="+reComm);
								
								if(!resellerId.equals("admin")) {
									commissionService.updateBalance(resellerId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
								}
							
								if(rechargedetail.getValidation().equals("PENDING")){
									/*-------------------------------For earning and Surcharge ------------------*/
									// For RESELLER
									if(!resellerId.equals("admin")) {
										EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, reComm, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
										earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
									}
								/*----------------------------------------------------------------------*/
								}
							}							
							rechargedetail.setStatus("SUCCESS");
							rechargedetail.setValidation("SUCCESS");
							rechargedetail.setOid(opTransId);
							rechargedetailsDao.updateRechargeDetails(rechargedetail);
							
						} else if(rechargeStatus.equalsIgnoreCase("FAILED")||rechargeStatus.equalsIgnoreCase("FAILURE")) {
							
							//Individualcharge iCharge = commissionService.getIndividualChargeOnOperator(userDetails.getUserId(), operator.getOperatorId());
							rechargedetail.setStatus("FAILED");
							rechargedetail.setValidation("FAILED");
							rechargedetail.setOid(opTransId);
							rechargedetailsDao.updateRechargeDetails(rechargedetail);
							commissionService.updateBalance(userDetails.getUserId(), "REFUND for Recharge - "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
							
							// --------------------- Commission Refund -------------------------------------------------------------
							
							if(userDetails.getRoleId() == 5) {
								
								// Charge REFUND
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
								
								if(distCharge==0){dcharge=0;}
								else{dcharge = charge - distCharge;}
								if(sdCharge==0){sCharge=0;}
								else{sCharge = distCharge - sdCharge;}
								
						
								commissionService.updateBalance(distId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", dcharge, "DEBIT",0);									
								commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = sdCharge - resCharge;}
									
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							
								
							} else if(userDetails.getRoleId() == 4) {	
								
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
								if(sdCharge==0){sCharge=0;}
								else{sCharge = charge - sdCharge;}
									
								commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = sdCharge - resCharge;}
									
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							} else if(userDetails.getRoleId() == 3) {								
								
								if(!resellerId.equals("admin")) {
									if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
										resCharge = (pckres.getCharge()*rechargedetail.getAmount())/100;
										}else{
										resCharge = pckres.getCharge();	
										}
								}
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = charge - resCharge;}
					
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							}
							returnData.put("status", "0");
							returnData.put("message", "Status : "+rechargeStatus+" Validation Status update Successfully.");
							
							if(rechargedetail.getSource().equals("API")){
								param = new HashMap<>();
								param.put("userId", userDetails.getUserId());
								List<Apisetting> list = apisettingDao.getApisettingByNamedQuery("getApisettingByuserId", param);
								String responseUrl = "";
								String responseUrlResponse = "";
								try {
									if (!list.isEmpty()) {
										responseUrl = list.get(0).getResponseUrl() + "?tranId="	+ rechargedetail.getPtid() + "&opTId=" + opTransId + "&status="	+ rechargeStatus;
										responseUrlResponse = sendRechargeRequest.sendRechagreReq(responseUrl);
									} else {
										responseUrl = list.get(0).getResponseUrl() + "?tranId="	+ rechargedetail.getPtid() + "&opTId=" + opTransId + "&status=" + rechargeStatus;
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
							returnData.put("message", "Your Request in process.");
						}
					} else {
						returnData.put("status", "0");
						returnData.put("message", "Already Status updated.");
					}
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Recharge Details Found.");
				}
				}
				}else{
					returnData.put("status", "0");
					returnData.put("message", "validation Updated");
				}
			} else {
				returnData.put("status", "0");
				returnData.put("message", "Parameter not pass correctly.");
			}
		} catch (Exception e) {
			returnData.put("status", "0");
			returnData.put("message", "Exception! Please contact to admin.");
			logger_log.error("doopMeApiResponse ::::::::::::::: " + e);
		}
		logger_log.warn("welborn response returnData:::"+returnData);
		return returnData;
		
	}
	
	@Override
	public Map<String, Object> vastwebindiaresponse(Map<String, String> request) {		
		Map<String, Object> returnData = new HashMap<String, Object>();
		double sdComm = 0.0;
		double dComm = 0.0;
		double resComm = 0.0;
		double totalAmount = 0.0;
		
		double comm = 0.0;
		double disComm = 0.0;
		double sdisComm = 0.0;
		double reComm = 0.0;
		double rComm=0.0;
		double charge = 0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		double resCharge = 0.0;
		double distCharge = 0.0;
		double sdCharge = 0.0;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		
		String resellerId = "";
		String sdId = "";
		String distId = "";
		String rId="";
		String opTransId ="";
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		logger_log.warn("vastwebindiaresponse final response::"+request);
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				String clientId = request.get("rchid");
					 opTransId = request.get("operatorid");
				String rechargeStatus = request.get("Status");
				param.put("tid", clientId);
				List<ResponseCounter> listcountr=responsecounterdao.getResponseCounterByNamedQuery("getResponseCounter",param);
				if(listcountr.isEmpty()){
					ResponseCounter ResponseCounter= new ResponseCounter(clientId,1);
					boolean flag=responsecounterdao.insertResponseCounter(ResponseCounter);
					if(flag){
				param.put("tid", clientId);
				List<Rechargedetails> rechargeDetails = rechargedetailsDao.getRechargeDetailsByNamedQuery("GetRechargeDetailsByTid", param);
				if(!rechargeDetails.isEmpty()) {
					Rechargedetails rechargedetail = rechargeDetails.get(0);
					String mobileNo = rechargedetail.getMobile();
					Operator operator = operatorDao.getOperatorByOperatorId(rechargedetail.getOperatorId());					
					User userDetails = userDao.getUserByUserId(rechargedetail.getUserId());
					totalAmount = RoundNumber.roundDouble((rechargedetail.getAmount() + rechargedetail.getCharge()) - rechargedetail.getComm());			
					comm = rechargedetail.getComm();
					charge = rechargedetail.getCharge();
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
							
						//	System.out.println("reseller=="+comm);
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
							}else{
							dComm=pckdis.getComm();	
							}
							
						//	System.out.println("dComm=="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
							}else{
							sdComm = pcksd.getComm();	
							}
						//	System.out.println("sdComm=="+sdComm);
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
							}else{
							resComm =pckres.getComm();	
							}
						
					} else if(userDetails.getRoleId() == 4) {
						// Distributor Id
						distId =rechargedetail.getUserId();
						sdId = userDetails.getUplineId();							
						parameters = new HashMap<String, String>();
						parameters.put("userId", sdId);							
						resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
						pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
						if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
						}else{
						dComm=pckdis.getComm();	
						}
						
					//	System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
						}else{
						sdComm = pcksd.getComm();	
						}
						
					//	System.out.println("sdComm=="+sdComm);
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
						}else{
						resComm =pckres.getComm();	
						}
						
						
					} else if(userDetails.getRoleId() == 3) {
						// Super Distributor Id
						sdId = rechargedetail.getUserId();
						resellerId = userDetails.getUplineId();
//						System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
						}else{
						sdComm = pcksd.getComm();	
						}
					//	System.out.println("sdComm=="+sdComm);
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
						}else{
						resComm =pckres.getComm();	
						}						
					}
					
					if(rechargedetail.getValidation().equals("PENDING")) {
						if(rechargeStatus.equalsIgnoreCase("SUCCESS")) {
						
							
							if(userDetails.getRoleId() == 5) {

								
								distId = userDetails.getUplineId();		
								
								// Super Distributor Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", distId);	
								sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
								
								// Reseller Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", sdId);							
								resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
								
							//	System.out.println("resComm=="+resComm);
								
							
								disComm=dComm-rComm;
								sdisComm=sdComm-dComm;
								reComm=resComm-sdComm;
								/*	System.out.println("COMMISSION for Recharge sdcomm=="+sdisComm);
								System.out.println("COMMISSION for Recharge sdcomm=="+reComm);*/
								
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
							}else if(userDetails.getRoleId() == 4) {
								
								/*sdisComm = sdComm - comm;
								reComm = resComm - sdComm;*/
								sdisComm=sdComm-dComm;
								reComm=resComm-sdComm;
								/*System.out.println("COMMISSION for Recharge sdcomm="+sdisComm);
								System.out.println("COMMISSION for Recharge sdcomm="+reComm);*/
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
								
								//reComm = resComm - comm;
								reComm=resComm-sdComm;
				
								//System.out.println("COMMISSION for Recharge sdcomm==="+reComm);
								
								if(!resellerId.equals("admin")) {
									commissionService.updateBalance(resellerId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
								}
							
								if(rechargedetail.getValidation().equals("PENDING")){
									/*-------------------------------For earning and Surcharge ------------------*/
									// For RESELLER
									if(!resellerId.equals("admin")) {
										EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, reComm, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
										earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
									}
								/*----------------------------------------------------------------------*/
								}
							}							
							rechargedetail.setStatus("SUCCESS");
							rechargedetail.setValidation("SUCCESS");
							rechargedetail.setOid(opTransId);
							rechargedetailsDao.updateRechargeDetails(rechargedetail);
							
						} else if(rechargeStatus.equalsIgnoreCase("FAILED")||rechargeStatus.equalsIgnoreCase("FAILURE") ) {
							
							rechargedetail.setStatus("FAILED");
							rechargedetail.setValidation("FAILED");
							rechargedetail.setOid(opTransId);
							rechargedetailsDao.updateRechargeDetails(rechargedetail);
							commissionService.updateBalance(userDetails.getUserId(), "REFUND for Recharge - "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
							
							// --------------------- Commission Refund -------------------------------------------------------------
							
							if(userDetails.getRoleId() == 5) {
								
								// Charge REFUND
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
								
								dcharge = charge - distCharge;
								sCharge = distCharge - sdCharge;
								
								commissionService.updateBalance(distId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", dcharge, "DEBIT",0);									
								commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
								
								if(!resellerId.equals("admin")) {
									reCharge = sdCharge - resCharge;
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							
								
							} else if(userDetails.getRoleId() == 4) {	
						
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
								sCharge = charge - sdCharge;		
								commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
								
								if(!resellerId.equals("admin")) {
									reCharge = sdCharge - resCharge;
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							} else if(userDetails.getRoleId() == 3) {								
								
								if(!resellerId.equals("admin")) {
									if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
										resCharge = (pckres.getCharge()*rechargedetail.getAmount())/100;
										}else{
										resCharge = pckres.getCharge();	
										}
								}
								
								if(!resellerId.equals("admin")) {
									reCharge = charge - resCharge;
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							}
							returnData.put("status", "0");
							returnData.put("message", "Status : "+rechargeStatus+" Validation Status update Successfully.");
							
							if(rechargedetail.getSource().equals("API")){
								param = new HashMap<>();
								param.put("userId", userDetails.getUserId());
								List<Apisetting> list = apisettingDao.getApisettingByNamedQuery("getApisettingByuserId", param);
								String responseUrl = "";
								String responseUrlResponse = "";
								try {
									if (!list.isEmpty()) {
										responseUrl = list.get(0).getResponseUrl() + "?tranId="	+ rechargedetail.getPtid() + "&opTId=" + opTransId + "&status="	+ rechargeStatus;
										logger_log.warn("responseUrl:::"+responseUrl);
										responseUrlResponse = sendRechargeRequest.sendRechagreReq(responseUrl);
										logger_log.warn("responseUrlResponse:::"+responseUrlResponse);
									} else {
										responseUrl = list.get(0).getResponseUrl() + "?tranId="	+ rechargedetail.getPtid() + "&opTId=" + opTransId + "&status=" + rechargeStatus;
										responseUrlResponse = "No Response Url Set";
									}									
								} catch (Exception e) {
									responseUrlResponse = "EXCEPTION";
									logger_log.error("vastwebindiaresponse ::::::::::::::: SENDING RESPONSE ERROR");
									logger_log.error("vastwebindiaresponse ::::::::::::::: " + e);
								}
								
								Apitrace apitrace = new Apitrace(userDetails.getUserId(), rechargedetail.getPtid(),	responseUrl, responseUrlResponse, GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime());
								apitraceDao.insertApitrace(apitrace);
							}
							
							
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Your Request in process.");
						}
					} else {
						returnData.put("status", "0");
						returnData.put("message", "Already Status updated.");
					}
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Recharge Details Found.");
				}
					}
				}else{
					returnData.put("status", "0");
					returnData.put("message", "validation Updated");
				}
			} else {
				returnData.put("status", "0");
				returnData.put("message", "Parameter not pass correctly.");
			}
		} catch (Exception e) {
			returnData.put("status", "0");
			returnData.put("message", "Exception! Please contact to admin.");
			logger_log.error("vastwebindiaresponse ::::::::::::::: " + e);
		}
		return returnData;
	}
	
	
	@Override
	public PaymonkResponse aepsstatusCheck(String response) {
		logger_log.warn("aepsstatusCheck:::::::::::::::::::::::::::"+response);
		String payerId ="";
        String payertype="";
		String payeeId="";
		String payeetype="";
		String txnType="";
	    String orderId="";
		double amount=0.0;
		String txnId="";
		String balance="";
		String orderStatus="";
		String paymentStatus="";
		String requestId ="";
		String stan ="";
		String rrn ="";
		String bankAuth ="";
		String processingCode ="";
		String accountBalance ="";
		String bankResponseCode ="";
		String bankResponseMsg="";
		String terminalId="";
		String agentId ="";
		String aadharNumber ="";
		String dateTime ="";
		String statusCode ="";
		String statusMessage ="";
		double commissionAmt = 0.0;
		double gstAmt = 0.0;
		double tdsAmt = 0.0;
		String refno = "";
		//String date2 = GenerateRandomNumber.getCurrentDate();
		//Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//String today = formatter.format(date);
		//Calendar cal = Calendar.getInstance();
		//SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
		//String currentTime = sdf.format(cal.getTime());
		
		DecimalFormat df2 = new DecimalFormat(".##");
		PaymonkResponse paymonk = new PaymonkResponse();
		JSONObject json = new JSONObject(response);
		if(!json.isNull("result")){
			JSONObject resultobj = json.getJSONObject("result");
			String status=resultobj.getString("status");
			paymonk.setStatus(status);
			if(!resultobj.isNull("payload")){
				JSONObject payloadobj = resultobj.getJSONObject("payload");
				paymonk.setPayeeId(payeeId);
				paymonk.setPayeetype(payeetype);
				paymonk.setPayerId(payerId);
				paymonk.setPayertype(payertype);
				paymonk.setTxnType(txnType);
				if(!payloadobj.isNull("processingCode")){
				if(payloadobj.getString("processingCode").equalsIgnoreCase("CASHWITHDRAWAL")){
				paymonk.setProcessingCode("010000");
				}else if(payloadobj.getString("processingCode").equalsIgnoreCase("BALANCEENQUIRY")){
				paymonk.setProcessingCode("310000");		
			    }
				paymonk.setAadharNumber(aadharNumber);
				paymonk.setAccountBalance(accountBalance);
				paymonk.setAgentcode("");
				paymonk.setAgentId(payloadobj.getString("agentId"));
				paymonk.setAmount(payloadobj.getDouble("orderAmount"));
				paymonk.setBalance(accountBalance);
				paymonk.setBankAuth(bankAuth);
				paymonk.setBankResponseCode(bankResponseCode);
				paymonk.setBankResponseMsg(payloadobj.getString("paymentStatus"));
				
				Long updatedDate = payloadobj.getLong("updatedDate");
				Date date3 = new Date(updatedDate);
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss aa");
				String strDate = sd.format(date3);
				System.out.println("strDate::::::::::::::::"+strDate);
				paymonk.setCommissionAmt(payloadobj.getDouble("commissionAmt"));
				paymonk.setDate(strDate.substring(0,10));
				System.out.println("Date::::::::::::::::"+paymonk.getDate());
				paymonk.setTime(strDate.substring(11,strDate.length()));
				System.out.println("Time::::::::::::::::"+paymonk.getTime());
				paymonk.setGstAmt(payloadobj.getDouble("gstAmt"));
				paymonk.setTdsAmt(payloadobj.getDouble("tdsAmt"));
				paymonk.setReferenceNo("");
				paymonk.setRequestId(requestId);
				paymonk.setRrn(payloadobj.getString("rrn"));
				paymonk.setOrderId(payloadobj.getString("ownerOrderId"));
				paymonk.setStan(payloadobj.getString("stan"));
				paymonk.setStatusCode(payloadobj.getString("bankResponseCode"));
				paymonk.setOrderStatus(payloadobj.getString("orderStatus"));
				paymonk.setTerminalId(payloadobj.getString("terminalId"));
				paymonk.setDateTime(strDate);
				paymonk.setStatusMessage("");
				paymonk.setTxnId("");
				if(status.equalsIgnoreCase("success")){
				paymonk.setPaymentStatus("SUCCESS");	
				}else{
				paymonk.setPaymentStatus("FAILURE");		
				}
				}
			}
		}
		
		
		return paymonk;
	}

	@Override
	public Map<String, Object> aepsstatuscheckandroid(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag = false; 
		try{
			AEPSLog aepsLog = new AEPSLog(request.get("refno"),request.get("agentcode"),"PENDING", "EDIT","EDIT", GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime(),"00","0","0",0.0);
			flag =  aepslogdao.insertAEPSLog(aepsLog);
			if(flag){
				returnData.put("status", "1");
				returnData.put("message", "data inserted successfully");	
			}else{
				returnData.put("status", "0");
				returnData.put("message", "data not inserted");	
			}
		}catch (Exception e) {
			logger_log.error("aepsstatuscheckandroid:::::::::::::::::::"+e);
		}
		
		return returnData;
	}
	
	
	
	@Override
	public Map<String, Object> CrowdfinchApiResponse(Map<String, String> request) {		
		logger_log.warn("CrowdfinchApiResponse:::"+request);
		Map<String, Object> returnData = new HashMap<String, Object>();
		double sdComm = 0.0;
		double dComm = 0.0;
		double resComm = 0.0;
		double totalAmount = 0.0;
		
		double comm = 0.0;
		double disComm = 0.0;
		double sdisComm = 0.0;
		double reComm = 0.0;
		double rComm=0.0;
		double charge = 0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		double resCharge = 0.0;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double distCharge = 0.0;
		double sdCharge = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";
		String rId="";
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				String clientId = request.get("Transid");
				String opTransId = request.get("OpratorId");
				String rechargeStatus = request.get("status");
				param.put("tid", clientId);
				List<Rechargedetails> rechargeDetails = rechargedetailsDao.getRechargeDetailsByNamedQuery("GetRechargeDetailsByTid", param);
				if(!rechargeDetails.isEmpty()) {
					logger_log.warn("CrowdfinchApiResponse::clientId:"+clientId);
					Rechargedetails rechargedetail = rechargeDetails.get(0);
					String mobileNo = rechargedetail.getMobile();
					Operator operator = operatorDao.getOperatorByOperatorId(rechargedetail.getOperatorId());					
					User userDetails = userDao.getUserByUserId(rechargedetail.getUserId());
					totalAmount = RoundNumber.roundDouble((rechargedetail.getAmount() + rechargedetail.getCharge()) - rechargedetail.getComm());			
					comm = rechargedetail.getComm();
					charge = rechargedetail.getCharge();
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
							
						//	System.out.println("reseller=="+comm);
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
							}else{
							dComm=pckdis.getComm();	
							}
							
						//	System.out.println("dComm=="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
							}else{
							sdComm = pcksd.getComm();	
							}
						//	System.out.println("sdComm=="+sdComm);
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
							}else{
							resComm =pckres.getComm();	
							}
						
					} else if(userDetails.getRoleId() == 4) {
						// Distributor Id
						distId =rechargedetail.getUserId();
						sdId = userDetails.getUplineId();							
						parameters = new HashMap<String, String>();
						parameters.put("userId", sdId);							
						resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
						pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
						if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
						}else{
						dComm=pckdis.getComm();	
						}
						
					//	System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
						}else{
						sdComm = pcksd.getComm();	
						}
						
					//	System.out.println("sdComm=="+sdComm);
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
						}else{
						resComm =pckres.getComm();	
						}
						
						
					} else if(userDetails.getRoleId() == 3) {
						// Super Distributor Id
						sdId = rechargedetail.getUserId();
						resellerId = userDetails.getUplineId();
//						System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
						}else{
						sdComm = pcksd.getComm();	
						}
					//	System.out.println("sdComm=="+sdComm);
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
						}else{
						resComm =pckres.getComm();	
						}						
					}
					
					if(rechargedetail.getValidation().equals("PENDING")) {
						if(rechargeStatus.equals("SUCCESS")) {
							logger_log.warn("CrowdfinchApiResponse::SUCCESS:"+clientId);
							rechargedetail.setStatus("SUCCESS");
							rechargedetail.setValidation("SUCCESS");
							rechargedetail.setOid(opTransId);
							rechargedetailsDao.updateRechargeDetails(rechargedetail);
							if(userDetails.getRoleId() == 5) {

								
								distId = userDetails.getUplineId();		
								
								// Super Distributor Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", distId);	
								sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
								
								// Reseller Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", sdId);							
								resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
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
							}else if(userDetails.getRoleId() == 4) {
								
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
				
				
								//System.out.println("COMMISSION for Recharge sdcomm==="+reComm);
								
								if(!resellerId.equals("admin")) {
									commissionService.updateBalance(resellerId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
								}
							
								if(rechargedetail.getValidation().equals("PENDING")){
									/*-------------------------------For earning and Surcharge ------------------*/
									// For RESELLER
									if(!resellerId.equals("admin")) {
										EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, reComm, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
										earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
									}
								/*----------------------------------------------------------------------*/
								}
							}							
							
							
						} else if(rechargeStatus.equals("FAILED")) {
							
							//Individualcharge iCharge = commissionService.getIndividualChargeOnOperator(userDetails.getUserId(), operator.getOperatorId());
							rechargedetail.setStatus("FAILED");
							rechargedetail.setValidation("FAILED");
							rechargedetail.setOid(opTransId);
							rechargedetailsDao.updateRechargeDetails(rechargedetail);
							commissionService.updateBalance(userDetails.getUserId(), "REFUND for Recharge - "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
							
							// --------------------- Commission Refund -------------------------------------------------------------
							
							if(userDetails.getRoleId() == 5) {
								
								// Charge REFUND
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
								
								if(distCharge==0){dcharge=0;}
								else{dcharge = charge - distCharge;}
								if(sdCharge==0){sCharge=0;}
								else{sCharge = distCharge - sdCharge;}
								
						
								commissionService.updateBalance(distId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", dcharge, "DEBIT",0);									
								commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = sdCharge - resCharge;}
									
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							
								
							} else if(userDetails.getRoleId() == 4) {	
								
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
								if(sdCharge==0){sCharge=0;}
								else{sCharge = charge - sdCharge;}
									
								commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = sdCharge - resCharge;}
									
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							} else if(userDetails.getRoleId() == 3) {								
								
								if(!resellerId.equals("admin")) {
									if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
										resCharge = (pckres.getCharge()*rechargedetail.getAmount())/100;
										}else{
										resCharge = pckres.getCharge();	
										}
								}
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = charge - resCharge;}
					
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							}
							returnData.put("status", "0");
							returnData.put("message", "Status : "+rechargeStatus+" Validation Status update Successfully.");
							
							if(rechargedetail.getSource().equals("API")){
								param = new HashMap<>();
								param.put("userId", userDetails.getUserId());
								List<Apisetting> list = apisettingDao.getApisettingByNamedQuery("getApisettingByuserId", param);
								String responseUrl = "";
								String responseUrlResponse = "";
								try {
									if (!list.isEmpty()) {
										responseUrl = list.get(0).getResponseUrl() + "?tranId="	+ rechargedetail.getPtid() + "&opTId=" + opTransId + "&status="	+ rechargeStatus;
										responseUrlResponse = sendRechargeRequest.sendRechagreReq(responseUrl);
									} else {
										responseUrl = list.get(0).getResponseUrl() + "?tranId="	+ rechargedetail.getPtid() + "&opTId=" + opTransId + "&status=" + rechargeStatus;
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
							returnData.put("message", "Your Request in process.");
						}
					} else {
						returnData.put("status", "0");
						returnData.put("message", "Already Status updated.");
					}
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Recharge Details Found.");
				}
			} else {
				returnData.put("status", "0");
				returnData.put("message", "Parameter not pass correctly.");
			}
		} catch (Exception e) {
			returnData.put("status", "0");
			returnData.put("message", "Exception! Please contact to admin.");
			logger_log.error("doopMeApiResponse ::::::::::::::: " + e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> EasyPayApiResponse(Map<String, String> request) {		
		logger_log.warn("EasyPayApiResponse:::"+request);
		Map<String, Object> returnData = new HashMap<String, Object>();
		double sdComm = 0.0;
		double dComm = 0.0;
		double resComm = 0.0;
		double totalAmount = 0.0;
		
		double comm = 0.0;
		double disComm = 0.0;
		double sdisComm = 0.0;
		double reComm = 0.0;
		double rComm=0.0;
		double charge = 0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		double resCharge = 0.0;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double distCharge = 0.0;
		double sdCharge = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";
		String rId="";
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				String clientId = request.get("Transid");
				String opTransId = request.get("OpratorId");
				String rechargeStatus = request.get("status");
				param.put("tid", clientId);
				List<Rechargedetails> rechargeDetails = rechargedetailsDao.getRechargeDetailsByNamedQuery("GetRechargeDetailsByTid", param);
				if(!rechargeDetails.isEmpty()) {
					logger_log.warn("CrowdfinchApiResponse::clientId:"+clientId);
					Rechargedetails rechargedetail = rechargeDetails.get(0);
					String mobileNo = rechargedetail.getMobile();
					Operator operator = operatorDao.getOperatorByOperatorId(rechargedetail.getOperatorId());					
					User userDetails = userDao.getUserByUserId(rechargedetail.getUserId());
					totalAmount = RoundNumber.roundDouble((rechargedetail.getAmount() + rechargedetail.getCharge()) - rechargedetail.getComm());			
					comm = rechargedetail.getComm();
					charge = rechargedetail.getCharge();
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
							
						//	System.out.println("reseller=="+comm);
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
							}else{
							dComm=pckdis.getComm();	
							}
							
						//	System.out.println("dComm=="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
							}else{
							sdComm = pcksd.getComm();	
							}
						//	System.out.println("sdComm=="+sdComm);
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
							}else{
							resComm =pckres.getComm();	
							}
						
					} else if(userDetails.getRoleId() == 4) {
						// Distributor Id
						distId =rechargedetail.getUserId();
						sdId = userDetails.getUplineId();							
						parameters = new HashMap<String, String>();
						parameters.put("userId", sdId);							
						resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
						pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
						if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						dComm =(pckdis.getComm()*rechargedetail.getAmount())/100;
						}else{
						dComm=pckdis.getComm();	
						}
						
					//	System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
						}else{
						sdComm = pcksd.getComm();	
						}
						
					//	System.out.println("sdComm=="+sdComm);
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
						}else{
						resComm =pckres.getComm();	
						}
						
						
					} else if(userDetails.getRoleId() == 3) {
						// Super Distributor Id
						sdId = rechargedetail.getUserId();
						resellerId = userDetails.getUplineId();
//						System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*rechargedetail.getAmount())/100;
						}else{
						sdComm = pcksd.getComm();	
						}
					//	System.out.println("sdComm=="+sdComm);
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*rechargedetail.getAmount())/100;
						}else{
						resComm =pckres.getComm();	
						}						
					}
					
					if(rechargedetail.getValidation().equals("PENDING")) {
						if(rechargeStatus.equals("SUCCESS")) {
							logger_log.warn("CrowdfinchApiResponse::SUCCESS:"+clientId);
							rechargedetail.setStatus("SUCCESS");
							rechargedetail.setValidation("SUCCESS");
							rechargedetail.setOid(opTransId);
							rechargedetailsDao.updateRechargeDetails(rechargedetail);
							if(userDetails.getRoleId() == 5) {

								
								distId = userDetails.getUplineId();		
								
								// Super Distributor Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", distId);	
								sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
								
								// Reseller Id
								parameters = new HashMap<String, String>();
								parameters.put("userId", sdId);							
								resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
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
							}else if(userDetails.getRoleId() == 4) {
								
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
				
				
								//System.out.println("COMMISSION for Recharge sdcomm==="+reComm);
								
								if(!resellerId.equals("admin")) {
									commissionService.updateBalance(resellerId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", reComm, "CREDIT",0);
								}
							
								if(rechargedetail.getValidation().equals("PENDING")){
									/*-------------------------------For earning and Surcharge ------------------*/
									// For RESELLER
									if(!resellerId.equals("admin")) {
										EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, reComm, 0.0, "Charge FOR - "+mobileNo, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
										earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
									}
								/*----------------------------------------------------------------------*/
								}
							}							
							
							
						} else if(rechargeStatus.equals("FAILED")) {
							
							//Individualcharge iCharge = commissionService.getIndividualChargeOnOperator(userDetails.getUserId(), operator.getOperatorId());
							rechargedetail.setStatus("FAILED");
							rechargedetail.setValidation("FAILED");
							rechargedetail.setOid(opTransId);
							rechargedetailsDao.updateRechargeDetails(rechargedetail);
							commissionService.updateBalance(userDetails.getUserId(), "REFUND for Recharge - "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
							
							// --------------------- Commission Refund -------------------------------------------------------------
							
							if(userDetails.getRoleId() == 5) {
								
								// Charge REFUND
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
								
								if(distCharge==0){dcharge=0;}
								else{dcharge = charge - distCharge;}
								if(sdCharge==0){sCharge=0;}
								else{sCharge = distCharge - sdCharge;}
								
						
								commissionService.updateBalance(distId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", dcharge, "DEBIT",0);									
								commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = sdCharge - resCharge;}
									
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							
								
							} else if(userDetails.getRoleId() == 4) {	
								
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
								if(sdCharge==0){sCharge=0;}
								else{sCharge = charge - sdCharge;}
									
								commissionService.updateBalance(sdId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", sCharge, "DEBIT",0);
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = sdCharge - resCharge;}
									
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							} else if(userDetails.getRoleId() == 3) {								
								
								if(!resellerId.equals("admin")) {
									if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
										resCharge = (pckres.getCharge()*rechargedetail.getAmount())/100;
										}else{
										resCharge = pckres.getCharge();	
										}
								}
								
								if(!resellerId.equals("admin")) {
									if(resCharge==0){reCharge=0;}
									else{reCharge = charge - resCharge;}
					
									commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - "+mobileNo, "REVERT", reCharge, "DEBIT",0);
								}
							
							}
							returnData.put("status", "0");
							returnData.put("message", "Status : "+rechargeStatus+" Validation Status update Successfully.");
							
							if(rechargedetail.getSource().equals("API")){
								param = new HashMap<>();
								param.put("userId", userDetails.getUserId());
								List<Apisetting> list = apisettingDao.getApisettingByNamedQuery("getApisettingByuserId", param);
								String responseUrl = "";
								String responseUrlResponse = "";
								try {
									if (!list.isEmpty()) {
										responseUrl = list.get(0).getResponseUrl() + "?tranId="	+ rechargedetail.getPtid() + "&opTId=" + opTransId + "&status="	+ rechargeStatus;
										responseUrlResponse = sendRechargeRequest.sendRechagreReq(responseUrl);
									} else {
										responseUrl = list.get(0).getResponseUrl() + "?tranId="	+ rechargedetail.getPtid() + "&opTId=" + opTransId + "&status=" + rechargeStatus;
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
							returnData.put("message", "Your Request in process.");
						}
					} else {
						returnData.put("status", "0");
						returnData.put("message", "Already Status updated.");
					}
				} else {
					returnData.put("status", "0");
					returnData.put("message", "No Recharge Details Found.");
				}
			} else {
				returnData.put("status", "0");
				returnData.put("message", "Parameter not pass correctly.");
			}
		} catch (Exception e) {
			returnData.put("status", "0");
			returnData.put("message", "Exception! Please contact to admin.");
			logger_log.error("doopMeApiResponse ::::::::::::::: " + e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> giblDeductBalanceResponse(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		logger_log.warn("giblDeductBalanceResponse Final:::"+request);
		User uu=userDao.getUserByUserId(request.get("urc"));
		if(uu==null){

			returnData.put("status", "0");
			returnData.put("refno", request.get("refno"));
			returnData.put("message", "Invalid UserId.");
		
		}else{
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("userId", request.get("urc"));	
		double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters );	
		double mount=Double.parseDouble(request.get("pamt"));
		double clbl=op_bal-mount;
		User us=userDao.getUserByUserId(request.get("urc"));
		boolean flag5=false;
		if(us.getWlId().startsWith("ASR")){
			param = new HashMap<String, Object>();
			param.put("wlId", us.getWlId());
			List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
			User whuser=whlist.get(0);
			parameters = new HashMap<String, String>();
			parameters.put("userId", whuser.getUserId());	
			double op_bal1 = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters );	
			double clbl1=op_bal1-mount;
			if(clbl1>=whuser.getLockedAmount()){
				flag5=true;
			}else{
				flag5=false;
			}
		}else{
			flag5=true;
		}
		if(flag5){
		if(clbl>=us.getLockedAmount()){
			
			Insurance ins=new Insurance(us.getUserId(), request.get("refno"), "-", "-", "-", "-", mount, "-", us.getWlId(), "PENDING", request.get("ak"), "0", GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
			boolean flag=InsuranceDao.insertInsurance(ins);
		
			if(flag){
				commissionService.updateBalance(us.getUserId(), "Insurance to "+request.get("refno"), "Insurance", mount, "DEBIT",0.0);
				if(us.getWlId().startsWith("ASR")){
					param = new HashMap<String, Object>();
					param.put("wlId", us.getWlId());
					List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
					User whuser=whlist.get(0);
					commissionService.updateBalance(whuser.getUserId(), us.getUserId() +" Insurance to "+request.get("refno"), "Insurance", mount, "DEBIT",0.0);
				}
				returnData.put("status", "1");
				returnData.put("refno", request.get("refno"));
				returnData.put("message", "SUCCESS");
			}else{
				returnData.put("status", "0");
				returnData.put("refno", request.get("refno"));
				returnData.put("message", "FAILED");
			}
			
			
		}else{
			returnData.put("status", "0");
			returnData.put("refno", request.get("refno"));
			returnData.put("message", "Insufficient Balance");
		}
		}else{
			returnData.put("status", "0");
			returnData.put("refno", request.get("refno"));
			returnData.put("message", "Insufficient Balance.Contact to Admin");
		}
		}
		
		return returnData;
	}
	
	
	@Override
	public Map<String, Object> giblUpdateStatusResponse(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		logger_log.warn("giblUpdateStatusResponse Final:::"+request);
		Map<String, Object> param = new HashMap<String, Object>();
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksubret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
		param.put("userId", request.get("urc"));
		param.put("refno", request.get("refno"));
		param.put("ak", request.get("ak"));
		List<Insurance> list=InsuranceDao.getInsuranceByNamedQuery("GetInsuranceByinsTiduid", param);
		Insurance inslist=list.get(0);
		String status=request.get("pstatus");
		double amount=Double.parseDouble(request.get("pamt"));
		User userDetails=userDao.getUserByUserId(request.get("urc"));
		if(status.equals("1")){
			inslist.setStatus("SUCCESS");
			
			String opcode=request.get("ptype");
			param = new HashMap<String, Object>();
			
			param.put("outCode", opcode);
			List<Operator> opList =  operatorDao.getOperatorByNamedQuery("getOperatorByoutcode", param );	
			if((!opList.isEmpty())) {
				Operator operator = opList.get(0);	
				inslist.setOperatorCode(Integer.toString(operator.getOperatorId()));
				InsuranceDao.updateInsurance(inslist);
				double charge=0.0;
				double comm=0.0;
				double subrComm=0.0;
				double sdComm=0.0;
				double rComm=0.0;
				double rtcharge=0.0;
				double dComm=0.0;
				double distCharge=0.0;
				double sdCharge=0.0;
				String rId="";
				String sdId="";
				String distId="";
				if(userDetails.getRoleId() == 6) {
					//Retailer Id
					rId=userDetails.getUplineId();
					// Distributor Id
					parameters  = new HashMap<String, String>();
					parameters.put("userId", rId);	
					distId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					// Super Distributor Id
					parameters  = new HashMap<String, String>();
					parameters.put("userId", distId);	
					sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
					
						
					pcksubret =commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId()); 
					if(pcksubret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						subrComm =(pcksubret.getComm()*amount)/100;
						}else{
							subrComm=pcksubret.getComm();	
						}
					
					pckret =commissionService.getPackagewiseCommisionOnOperator(rId,"Recharge",operator.getOperatorId()); 
					if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						rComm =(pckret.getComm()*amount)/100;
						}else{
						rComm=pckret.getComm();	
						}
					if(pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
						rtcharge = (pckret.getCharge()*amount)/100;
						}else{
						rtcharge = pckret.getCharge();
						}
						
					//	System.out.println("reseller=="+comm);
						pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
						if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*amount)/100;
							}else{
							dComm=pckdis.getComm();	
							}
							if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								distCharge = (pckdis.getCharge()*amount)/100;
								}else{
								distCharge = pckdis.getCharge();
								}
						
					//	System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*amount)/100;
							}else{
							sdComm = pcksd.getComm();
							}
							if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								sdCharge = (pcksd.getCharge()*amount)/100;
								}else{
								sdCharge = pcksd.getCharge();
								}
				
					
				} else if(userDetails.getRoleId() == 5) {
					//Retailer Id
					rId=userDetails.getUserId();
					// Distributor Id
					distId = userDetails.getUplineId();								
					// Super Distributor Id
					parameters  = new HashMap<String, String>();
					parameters.put("userId", distId);	
					sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
					
					
					pckret =commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId()); 
					if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						rComm =(pckret.getComm()*amount)/100;
						}else{
						rComm=pckret.getComm();	
						}
						
					//	System.out.println("reseller=="+comm);
						pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
						if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						dComm =(pckdis.getComm()*amount)/100;
						}else{
						dComm=pckdis.getComm();	
						}
						if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
							distCharge = (pckdis.getCharge()*amount)/100;
							}else{
							distCharge = pckdis.getCharge();
							}
						
						
					//	System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*amount)/100;
						}else{
						sdComm = pcksd.getComm();
						}
						if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
							sdCharge = (pcksd.getCharge()*amount)/100;
							}else{
							sdCharge = pcksd.getCharge();
							}
						
					
				} else if(userDetails.getRoleId() == 4) {
					// Distributor Id
					distId =userDetails.getUserId();
					sdId = userDetails.getUplineId();							
					
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
						if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
							sdCharge = (pcksd.getCharge()*amount)/100;
							}else{
							sdCharge = pcksd.getCharge();
							}
					
				} else if(userDetails.getRoleId() == 3) {
					// Super Distributor Id
					sdId = userDetails.getUserId();
					
//					System.out.println("dComm=="+dComm);
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					sdComm =(pcksd.getComm()*amount)/100;
					}else{
					sdComm = pcksd.getComm();	
					}
				
				}
				
			boolean	flag = commissionService.updateBalance(userDetails.getUserId(), "Insurance Premium charge "+request.get("refno"), "Insurance Premium Charge", charge, "DEBIT",0.0);
			commissionService.updateBalance(userDetails.getUserId(), "Insurance Premium Commission "+request.get("refno"), "Insurance Premium Commission", comm, "CREDIT",0.0);
			EarningSurcharge earningSurcharge13 = new EarningSurcharge(userDetails.getRoleId(), userDetails.getWlId(), userDetails.getUserId(), comm, 0.0, "Commission FOR - "+request.get("refno"), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
			earningSurchargeDao.insertEarningSurcharge(earningSurcharge13);
			EarningSurcharge earningSurcharge14 = new EarningSurcharge(userDetails.getRoleId(), userDetails.getWlId(), userDetails.getUserId(), charge, 0.0, "Charge FOR - "+request.get("refno"), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
			earningSurchargeDao.insertEarningSurcharge(earningSurcharge14);
			if(userDetails.getWlId().startsWith("ASR")){
				double resCom=0.0;
				double resCharge =0.0;
				param = new HashMap<String, Object>();
				param.put("wlId", userDetails.getWlId());
				List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
				User whuser=whlist.get(0);
				
				pckres=commissionService.getPackagewiseCommisionOnOperator(whuser.getUserId(),"Recharge",operator.getOperatorId());
				if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				 resCharge = (pckres.getCharge()*amount)/100;
				}else{
				resCharge =	pckres.getCharge();
				}
				if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resCom=(pckres.getComm()*amount)/100;
				}else{
					resCom=pckres.getComm();
				}
				commissionService.updateBalance(whuser.getUserId(), "Insurance Premium charge "+request.get("refno"), "Insurance Premium Charge", resCharge, "DEBIT",0.0);
				commissionService.updateBalance(whuser.getUserId(), "Insurance Refund COMMISSION "+request.get("refno"), "Insurance Premium Commission", resCom, "CREDIT",0.0);
				EarningSurcharge earningSurcharge11 = new EarningSurcharge(2, userDetails.getWlId(), whuser.getUserId(), resCom, 0.0, "Commission FOR - "+request.get("refno"), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
				earningSurchargeDao.insertEarningSurcharge(earningSurcharge11);
				EarningSurcharge earningSurcharge12 = new EarningSurcharge(2, userDetails.getWlId(), whuser.getUserId(), resCharge, 0.0, "Charge FOR - "+request.get("refno"), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
				earningSurchargeDao.insertEarningSurcharge(earningSurcharge12);
			
				//commissionService.updateBalance(whlist.get(0).getUserId(), userDetails.getUserId()+" Insurance Premium to "+mobileNo, "Insurance Premium", whtamnt, "DEBIT");
			}
			if(flag){
				double retComm=0.0;
				double disComm=0.0;
				double sdisComm=0.0;
				double sCharge=0.0;
				double retcharge=0.0;
				double dcharge=0.0;
				if(userDetails.getRoleId() == 6) {
					if(rComm==0){retComm=0;}
					else{retComm=rComm-subrComm;}
					if(dComm==0){disComm=0;}
					else{disComm=dComm-rComm;}
					if(sdComm==0){sdisComm=0;}
					else{
					sdisComm = sdComm-dComm;}
					commissionService.updateBalance(rId, "COMMISSION for Insurance - "+request.get("refno"), "COMMISSION", retComm, "CREDIT",0.0);
					commissionService.updateBalance(distId, "COMMISSION for Insurance - "+request.get("refno"), "COMMISSION", disComm, "CREDIT",0.0);
					commissionService.updateBalance(sdId, "COMMISSION for Insurance - "+request.get("refno"), "COMMISSION", sdisComm, "CREDIT",0.0);
					
					if(rtcharge==0){retcharge=0;}
					else{retcharge = charge - rtcharge;}
					if(distCharge==0){dcharge=0;}
					else{dcharge = rtcharge - distCharge;}
					
					if(sdCharge==0){sCharge=0;}
					else{sCharge = distCharge - sdCharge;}
					commissionService.updateBalance(rId, "Charge for Insurance - "+request.get("refno"), "CHARGE", retcharge, "CREDIT",0.0);
					commissionService.updateBalance(distId, "Charge for Insurance - "+request.get("refno"), "CHARGE", dcharge, "CREDIT",0.0);
					commissionService.updateBalance(sdId, "Charge for Insurance - "+request.get("refno"), "CHARGE", sCharge, "CREDIT",0.0);
				
					
						/*-------------------------------For earning and Surcharge ------------------*/
						// for DISTRIBUTOR 
						EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, userDetails.getWlId(), distId, disComm, 0.0, "Charge FOR - "+request.get("refno"), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
						
						// For Super Distributor
						EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm, 0.0, "Charge FOR - "+request.get("refno"),GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
						
						// for RETAILER 
						EarningSurcharge earningSurcharge7 = new EarningSurcharge(5, userDetails.getWlId(), rId, retComm, 0.0, "Charge FOR - "+request.get("refno"), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge7);
						EarningSurcharge earningSurcharge8 = new EarningSurcharge(5, userDetails.getWlId(), rId, retcharge, 0.0, "Charge FOR - "+request.get("refno"), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge8);
						
						
						// for DISTRIBUTOR 
						EarningSurcharge earningSurcharge4 = new EarningSurcharge(4, userDetails.getWlId(), distId, dcharge, 0.0, "Charge FOR - "+request.get("refno"), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge4);
						
						// For Super Distributor
						EarningSurcharge earningSurcharge5 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sCharge, 0.0, "Charge FOR - "+request.get("refno"),GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge5);
					
				
					/*----------------------------------------------------------------------*/
					
				}else if(userDetails.getRoleId() == 5) {

					if(dComm==0){disComm=0;}
					else{disComm=dComm-rComm;}
					if(sdComm==0){sdisComm=0;}
					else{
					sdisComm = sdComm-dComm;}
					commissionService.updateBalance(distId, "COMMISSION for Recharge - "+request.get("refno"), "COMMISSION", disComm, "CREDIT",0.0);
					commissionService.updateBalance(sdId, "COMMISSION for Recharge - "+request.get("refno"), "COMMISSION", sdisComm, "CREDIT",0.0);
					
					
					if(distCharge==0){dcharge=0;}
					else{dcharge = charge - distCharge;}
					
					if(sdCharge==0){sCharge=0;}
					else{sCharge = distCharge - sdCharge;}
					commissionService.updateBalance(distId, "Charge for Recharge - "+request.get("refno"), "CHARGE", dcharge, "CREDIT",0.0);
					commissionService.updateBalance(sdId, "Charge for Recharge - "+request.get("refno"), "CHARGE", sCharge, "CREDIT",0.0);
					
						/*-------------------------------For earning and Surcharge ------------------*/
						// for DISTRIBUTOR 
						EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, userDetails.getWlId(), distId, disComm, 0.0, "Comission FOR - "+request.get("refno"), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
						
						// For Super Distributor
						EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm, 0.0, "Comission FOR - "+request.get("refno"),GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
						
						// for DISTRIBUTOR 
						EarningSurcharge earningSurcharge4 = new EarningSurcharge(4, userDetails.getWlId(), distId, 0.0, dcharge, "Charge FOR - "+request.get("refno"), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge4);
						
						// For Super Distributor
						EarningSurcharge earningSurcharge5 = new EarningSurcharge(3, userDetails.getWlId(), sdId, 0.0, sCharge, "Charge FOR - "+request.get("refno"), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge5);
						
					/*----------------------------------------------------------------------*/
					
				}else if(userDetails.getRoleId() == 4) {
					
					if(sdComm==0){sdisComm=0;}
					else{
					sdisComm = sdComm-dComm;}
				
					if(sdCharge==0){sCharge=0;}
					else{sCharge = charge - sdCharge;}
																			
					commissionService.updateBalance(sdId, "Charge for Recharge - "+request.get("refno"), "CHARGE", sCharge, "CREDIT",0.0);
					
					commissionService.updateBalance(sdId, "COMMISSION for Recharge - "+request.get("refno"), "COMMISSION", sdisComm, "CREDIT",0.0);
					
						/*-------------------------------For earning and Surcharge ------------------*/
						EarningSurcharge earningSurcharge1 = new EarningSurcharge(3, userDetails.getWlId(), sdId, 0.0, sCharge, "Charge FOR - "+request.get("refno"), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
						// For Super Distributor
						EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm, 0.0, "Comission FOR - "+request.get("refno"),GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
						
						
					/*----------------------------------------------------------------------*/
					
				} 
			}
				
			}
			returnData.put("status", "1");
			returnData.put("refno", request.get("refno"));
			returnData.put("message", "SUCCESS");
		}else{
			commissionService.updateBalance(userDetails.getUserId(), "Insurance Refund to "+request.get("refno"), "Insurance REFUND", amount, "CREDIT",0.0);
			inslist.setStatus("FAILED");
			InsuranceDao.updateInsurance(inslist);
			if(userDetails.getWlId().startsWith("ASR")){
				double resCom=0.0;
				param = new HashMap<String, Object>();
				param.put("wlId", userDetails.getWlId());
				List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
				User whuser=whlist.get(0);
				commissionService.updateBalance(whuser.getUserId(), "Insurance Refund to "+request.get("refno"), "Insurance REFUND", amount, "CREDIT",0.0);
				//commissionService.updateBalance(whlist.get(0).getUserId(), userDetails.getUserId()+" Insurance Premium to "+mobileNo, "Insurance Premium", whtamnt, "DEBIT");
			}
			returnData.put("status", "1");
			returnData.put("refno", request.get("refno"));
			returnData.put("message", "SUCCESS");
		}
		
		return returnData;
		
	}



	@Override
	public Map<String, Object> microatmrequest(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean flag=false;
		try {
			MicroAtmLog micro=new MicroAtmLog(request.get("token"), request.get("uCubeUserId"),"PENDING",request.get("transactionType"),request.get("username") ,GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime(),request.get("amount") );	
			flag=microatmlogdao.insertMicroAtmLog(micro);
			if(flag) {
				returnData.put("status","1");	
				returnData.put("message","success");	
			}else {
				returnData.put("status","0");	
				returnData.put("message","Failed");	
			}
		}catch (Exception e) {
			logger_log.warn("microatmrequest::::::::::::::::::::::"+e);
		}
		return returnData;
	}



	@Override
	public Map<String, Object> microatmresponse(Map<String, String> request) {
		logger_log.warn("microatmresponse::::"+request);
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag = false;
		String username = "";
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		double amount = 0.0;
		String processingCode = "";
		boolean update = false;
		double settlementamount = 0.0;
		double settlementamountprev = 0.0;
		double settlementamountnew = 0.0;
		int id = 0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		String status="";
		double AGENT_CHARGE=0.0;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		try {
			SettlementReport settle2=null;
			//String status=data.get("txnStatus");
			param = new HashMap<String, Object>();
			param.put("referenceno", request.get("token"));
			param.put("status", "PENDING");
			List<MicroAtmLog> listran=microatmlogdao.getMicroAtmLogByNamedQuery("getLogByrefnostatus", param);
			if(!listran.isEmpty()){
				String msg= request.get("Msg");
				if(request.get("Msg").equalsIgnoreCase("APPROVED")) {
				status="SUCCESS";	
				}else{
				status="Failed";		
				}
				MicroAtmLog mLog=listran.get(0);
				mLog.setStatus(status);
				microatmlogdao.updateMicroAtmLog(mLog);
				double txnamount=0.0;
				if(mLog.getType().equalsIgnoreCase("WITHDRAWAL")) {
				txnamount=Double.parseDouble(mLog.getAmount());
				}
				String[] timestamp=request.get("date").split("\\s");
				MicroAtmResponse micro=new MicroAtmResponse(request.get("cardno"),timestamp[0],timestamp[1],request.get("invoiceNumber"),request.get("respCode") ,request.get("rrn") , mLog.getReferenceno(), mLog.getUserId(),txnamount,mLog.getType(),status,request.get("msg"));
				microatmresponseDao.insertMicroAtmResponse(micro);
			if(status.equalsIgnoreCase("SUCCESS")){
					if(mLog.getType().equals("WITHDRAWAL")){
						
						settlementamount=Double.parseDouble(mLog.getAmount());
						param = new HashMap<String, Object>();
						param.put("username",mLog.getUserId());
						List<SettlementBalance> settlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
						if(settlelist.isEmpty()){
							SettlementBalance SettlementBalance = new SettlementBalance(mLog.getUserId(),settlementamount);
							settlementbalancedao.insertSettlementBalance(SettlementBalance);	
							settle2 = new SettlementReport(mLog.getUserId(),0.0,settlementamount, settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
						}else{
							Map<String, String>	parameter = new HashMap<String, String>();
							parameter.put("userId", mLog.getUserId());
							 double settleopbal = customQueryServiceLogic
									.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForsettlement,parameter);
							SettlementBalance SettlementBalance = settlelist.get(0);
							settleopbal = SettlementBalance.getSettlementbalance();
							settlementamountnew = settleopbal+settlementamount;
							SettlementBalance.setSettlementbalance(settlementamountnew);
							settlementbalancedao.updateSettlementBalance(SettlementBalance);
							settle2 = new SettlementReport(mLog.getUserId(),settleopbal,settlementamountnew,settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
						}
						settlementreportdao.insertSettlementReport(settle2);
						
						param = new HashMap<String, Object>();
						param.put("api","MicroATM");
						
						List<AEPSCommission> aepscommlist = aepscommissiondao.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi",param);
						
						for(AEPSCommission comm2 : aepscommlist){
							if(settlementamount>=comm2.getSlab1() && settlementamount<=comm2.getSlab2()){
								id = comm2.getId();
								break;
							}
						}
						param = new HashMap<String, Object>();
						User user=userDao.getUserByUserId(mLog.getUserId());
						if(user.getRoleId() == 5) {
							double sudComm=0.0;
							double deComm=0.0;
							pckret = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"MicroATM",id) ;
							//Retailer Id
							//rId=userDetails.getUserId();
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
							if(pckret.getPackage_id()!=null){
							if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							retComm =(pckret.getComm()*settlementamount)/100;
							}else{
							retComm =pckret.getComm();	
							}
							comm = retComm;
							}
							System.out.println("comm:::::::::::::::::::::::::::::"+comm);
						//	System.out.println("reseller=="+comm);
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"MicroATM",id); 
							if(pckdis.getPackage_id()!=null){
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*settlementamount)/100;
							}else{
							dComm=pckdis.getComm();	
							}
							deComm=dComm-comm;
							}
							System.out.println("dComm:::::::::::::::::::::::::::::"+dComm);
						//	System.out.println("dComm=="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"MicroATM",id);
							if(pcksd.getPackage_id()!=null){
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*settlementamount)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							sudComm=sdComm-dComm;
							}
							
							
							if(!resellerId.equals("admin")) {
								pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"MicroATM",id);
								if(pckres.getPackage_id()!=null){
								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								resComm =(pckres.getComm()*settlementamount)/100;
								}else{
								resComm =pckres.getComm();	
								}
								resComm=resComm-sdComm;
								}
								commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0);
								}
							
						//	System.out.println("resComm=="+resComm);
							commissionService.updateBalance(user.getUserId(), "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
							commissionService.updateBalance(distId, "AEPS Commission For Amount - "+settlementamount, "Commission", deComm, "CREDIT",0);
							commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sudComm, "CREDIT",0);
							
						}else if(user.getRoleId() == 4) {
							double sudComm=0.0;

							//distId = userDetails.getUserId();	
							sdId = user.getUplineId();	
							
							parameters = new HashMap<String, String>();
							parameters.put("userId", sdId);							
							resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
							
							pckdis=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"MicroATM",id); 
							if(pckdis.getPackage_id()!=null){
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*settlementamount)/100;
							}else{
							dComm=pckdis.getComm();	
							}
							}
							
							comm = dComm;
							
						//	System.out.println("dComm="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"MicroATM",id);
							System.out.println("pcksd:::::::::::::::::"+pcksd);
							if(pcksd.getPackage_id()!=null){
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*settlementamount)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							sudComm=sdComm-dComm;
							}
							
							//System.out.println("sdComm="+sdComm);
							if(!resellerId.equals("admin")) {
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"MicroATM",id);
							if(pckres.getPackage_id()!=null){
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*settlementamount)/100;
							}else{
							resComm =pckres.getComm();	
							}
							resComm=resComm-sdComm;
							}
							commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0);
							}
							//System.out.println("resComm="+resComm);
							
							commissionService.updateBalance(user.getUserId(), "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
							commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sudComm, "CREDIT",0);
							
						}else if(user.getRoleId() == 3) {

							resellerId = user.getUplineId();
							pcksd=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"MicroATM",id);
							if(pcksd.getPackage_id()!=null){
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						     sdComm =(pcksd.getComm()*settlementamount)/100;
							}else{
								sdComm = pcksd.getComm();	
							}
							comm = sdComm;
							}
							System.out.println("sdComm:::::::::"+sdComm);
							if(!resellerId.equals("admin")) {
								pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"MicroATM",id);
								if(pckres.getPackage_id()!=null){
								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								resComm =(pckres.getComm()*settlementamount)/100;
								}else{
								resComm =pckres.getComm();	
								}
								resComm=resComm-sdComm;
								}
								commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0);
								}
							commissionService.updateBalance(user.getUserId(), "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
						
						}
					}
					returnData.put("RESP_CODE", "300");
					returnData.put("RESPONSE", "SUCCESS");
					returnData.put("RESP_MSG", "Transaction Success");
				}else{
					returnData.put("RESP_CODE", "302");
					returnData.put("RESPONSE", "FAILED");
					returnData.put("RESP_MSG", "Transaction Failed");
				}
				
			}else{
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("yesbankaepsApiResponse::::::::::::::::::::::::" + e);
		}
		return returnData;
	}



	@Override
	public Map<String, Object> paytmresponse(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		List<PaytmRequest> list=new ArrayList<PaytmRequest>();
		try {
			String	ORDERID=request.get("txnid");
			param.put("order_id", ORDERID);
			param.put("status", "PENDING");
			list=paytmrequestdao.getPaytmRequestByNamedQuery("getPaytmRequestbyorderid",param);
			
			if(!list.isEmpty()) {
			PaytmRequest pay=list.get(0);	
			User userDetails=userDao.getUserByUserId(pay.getUserId());
			String amount1=String.valueOf(pay.getAmount())+0;
			String hashSequence =salt+"|"+request.get("status")+"|"+""+"|"+""+"|"+""+"|"+""+"|"+""+"|"+""+"|"+""+"|"+""+"|"+""+"|"+""+"|"+userDetails.getEmail()+"|"+userDetails.getName()+"|"+"Wallet"+"|"+amount1+"|"+pay.getOrder_id()+"|"+api_key;
			String reversehash=SHACheckSumExample.reverseHash(hashSequence);
			logger_log.warn("hashSequence:::::::::::::::::"+hashSequence);
			logger_log.warn("reversehash:::::::::::::::::"+reversehash);
			
			logger_log.warn("reversehash:::::::::::::::::"+request.get("hash"));
			if(request.get("hash").equals(reversehash)) {
			if(request.get("status").equalsIgnoreCase("success")) {
			pay.setTransactionid(request.get("mihpayid"));
			pay.setBank_ref_num(request.get("bank_ref_num"));
			pay.setStatus("SUCCESS");
			paytmrequestdao.updatePaytmRequest(pay);
			double amount=pay.getAmount();
			double properamount=amount;
			commissionService.updateBalance(pay.getUserId(),"PayU wallet transfer "+request.get("transaction_id"), "PayU Wallet Add", properamount, "CREDIT",0.0);
			}else {
			pay.setTransactionid(request.get("mihpayid"));
			pay.setBank_ref_num(request.get("bank_ref_num"));
			pay.setStatus("FAILED");
			paytmrequestdao.updatePaytmRequest(pay);	
		
			}
			}
			}
		}catch (Exception e) {
			logger_log.error("paytmresponse:::::::::::::::::::"+e);
		}
		return returnData;
	}



	@Override
	public Map<String, Object> checkIRCTCBalance(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		boolean flag=false;
		try {
		String irctcid=request.get("RAIL ID");
		double amount=Double.parseDouble(request.get("BOOKING AMOUNT"));
		
		
		param.put("irctcid",irctcid);
		List<IRCTCUser> irctclist=irctcuserdao.getIRCTCUserByNamedQuery("getIRCTCUserbyirctcid",param);
		if(!irctclist.isEmpty()) {
		IRCTCUser irctc=irctclist.get(0);
		parameters = new HashMap<String, String>();
		parameters.put("userId", irctc.getUsername());	
		double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
		double cl_bal = RoundNumber.roundDouble(op_bal - amount);
			if (cl_bal > 0) {
			IRCTCLog irctclog=new IRCTCLog(irctc.getUsername(), amount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(), "PENDING");
			irctclogdao.insertIRCTCLog(irctclog);
			flag = commissionService.updateBalance(irctc.getUsername(), "IRCTC BOOKING", "IRCTC BOOKING", amount, "DEBIT",0);	
			if(flag) {
			returnData.put("status","0");	
			}
			}else {
			returnData.put("status","1");		
			}
		}
		
			
		}catch (Exception e) {
		logger_log.error("checkIRCTCBalance:::::::::::::::::::"+e);
		}
		return returnData;
	}
	@Override
	public Map<String, Object> IRCTCProcess(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		logger_log.warn("IRCTCProcess::::::::::::::::::::"+request);
		try {
		returnData.put("status","0");	
		}catch (Exception e) {
			returnData.put("status","1");
		logger_log.error("IRCTCProcess:::::::::::::::::::"+e);
		}
		return returnData;
	}
	@Override
	public Map<String, Object> arucheckbalance(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		boolean flag=false;
		logger_log.warn("arucheckbalance::::::::::::::::::::"+request);
		try {
		String ecommerceid=request.get("UserId");
		double amount=Double.parseDouble(request.get("Amount"));
		
		
		param.put("ecommerceid",ecommerceid);
		List<ECommerceUser> ecomlist=ecommercedao.getECommerceUserByNamedQuery("getECommerceUserbyecommerceid", param);
		if(!ecomlist.isEmpty()) {
		ECommerceUser ecom=ecomlist.get(0);
		parameters = new HashMap<String, String>();
		parameters.put("userId", ecom.getUsername());	
		double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
		double cl_bal = RoundNumber.roundDouble(op_bal - amount);
			if (cl_bal > 0) {
			ECommerceLog ecomlog=new ECommerceLog(ecom.getUsername(), amount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(), "PENDING");
			ecommercelogdao.insertECommerceLog(ecomlog);
			
			flag = commissionService.updateBalance(ecomlog.getUsername(), "Ecommerce Order", "Ecommerce Order", amount, "DEBIT",0);	
			if(flag) {
			returnData.put("status","1");	
			}
			}else {
			returnData.put("status","0");		
			}
		}
		
			
		}catch (Exception e) {
		logger_log.error("arucheckbalance:::::::::::::::::::"+e);
		}
		return returnData;
	}



	@Override
	public Map<String, Object> IRCTCResponse(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			logger_log.warn("IRCTCResponse::::::::::::::::::::::"+request);	
			returnData.put("status","0");	
		}catch (Exception e) {
			returnData.put("status","1");	
			logger_log.error("IRCTCResponse:::::::::::::::::::"+e);
		}
		return returnData;
	}



	@Override
	public Map<String, Object> instantpayAEPSFinalRes(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		boolean flag=false; 
		String username = "";
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double recom=0.0;
		double retComm = 0.0;
		double disComm=0.0;
		double sdisComm=0.0;
		double amount = 0.0;
		String processingCode = "";
		boolean update = false;
		
		double settlementamount = 0.0;
		double settlementamountprev = 0.0;
		double settlementamountnew = 0.0;
		int id = 0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		SettlementReport settle2=null;
		try{
		String ref_no=request.get("agent_id");
		param.put("referenceno",ref_no);
		List<AEPSLog> aepsloglist=aepslogdao.getAEPSLogByNamedQuery("getAEPSLogByrefno",param);
		if(!aepsloglist.isEmpty()){
		AEPSLog aepslog=aepsloglist.get(0);
		if(aepslog.getStatus().equalsIgnoreCase("PENDING")){
		param = new HashMap<String, Object>();
		param.put("agentcode",aepslog.getAgentcode());
		param.put("api","InstantPay");
		List<AEPSUserMap> aepslist = aepsuserdao.getAEPSUserMapByNamedQuery("getUsernamebyagentcode",param);
		if(!aepslist.isEmpty()){
			AEPSUserMap aeps = aepslist.get(0);
			username = aeps.getUsername();
			settlementamount = aepslog.getAmount();
			processingCode=aepslog.getType();
			param = new HashMap<String, Object>();
			param.put("api","InstantPay");
			User userDetails = userDao.getUserByUserId(username);
			List<AEPSCommission> aepscommlist = aepscommissiondao.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi",param);
			for(AEPSCommission comm2 : aepscommlist){
				if(settlementamount>=comm2.getSlab1() && settlementamount<=comm2.getSlab2()){
					id = comm2.getId();
					break;
				}
			}
			System.out.println("id:::::::::::::::::::::::::::::"+id);
			param = new HashMap<String, Object>();
			InstantPayAepsResponse insaeps=new InstantPayAepsResponse(request.get("ipay_id"),request.get("agent_id"),request.get("opr_id"),request.get("status"),request.get("res_code"),request.get("res_msg"), aepslog.getAmount(),processingCode, username, aepslog.getAgentcode(),aepslog.getDate(),aepslog.getTime());
			flag=instantpayaepsresdao.insertInstantPayAepsResponse(insaeps);
			if(flag){
			
			if(processingCode.equalsIgnoreCase("Withdrawal")){
				if(request.get("status").equalsIgnoreCase("SUCCESS")){
				if(userDetails.getRoleId() == 5) {
					pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"InstantPay",id) ;
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
					retComm =(pckret.getComm()*settlementamount)/100;
					}else{
					retComm =pckret.getComm();	
					}
					comm = retComm;
					}
					System.out.println("comm:::::::::::::::::::::::::::::"+comm);
				//	System.out.println("reseller=="+comm);
					pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"InstantPay",id); 
					if(pckdis.getPackage_id()!=null){
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*settlementamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
					disComm=dComm-comm;
					}
					System.out.println("dComm:::::::::::::::::::::::::::::"+dComm);
				//	System.out.println("dComm=="+dComm);
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"InstantPay",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					sdComm =(pcksd.getComm()*settlementamount)/100;
					}else{
					sdComm = pcksd.getComm();	
					}
					sdisComm=sdComm-dComm;
					}
					
				//	System.out.println("sdComm=="+sdComm);
					if(!resellerId.equals("admin")){
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"InstantPay",id);
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*settlementamount)/100;
					}else{
					resComm =pckres.getComm();	
					}
					recom=resComm-sdComm;
					commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", recom, "CREDIT",0.0);
				}
				//	System.out.println("resComm=="+resComm);
					commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0.0);
					commissionService.updateBalance(distId, "AEPS Commission For Amount - "+settlementamount, "Commission", disComm, "CREDIT",0.0);
					commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdisComm, "CREDIT",0.0);
					
				}else if(userDetails.getRoleId() == 4) {

					//distId = userDetails.getUserId();	
					sdId = userDetails.getUplineId();	
					
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					pckdis=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"InstantPay",id); 
					if(pckdis.getPackage_id()!=null){
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*settlementamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
					}
					
					comm = dComm;
					
				//	System.out.println("dComm="+dComm);
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"InstantPay",id);
					System.out.println("pcksd:::::::::::::::::"+pcksd);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					sdComm =(pcksd.getComm()*settlementamount)/100;
					}else{
					sdComm = pcksd.getComm();	
					}
					sdisComm=sdComm-dComm;
					}
					
					//System.out.println("sdComm="+sdComm);
					if(!resellerId.equals("admin")) {
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"InstantPay",id);
					if(pckres.getPackage_id()!=null){
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*settlementamount)/100;
					}else{
					resComm =pckres.getComm();	
					}
					resComm=resComm-sdComm;
					}
					commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0.0);
					}
					//System.out.println("resComm="+resComm);
					
					commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0.0);
					commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdisComm, "CREDIT",0.0);
					
				}else if(userDetails.getRoleId() == 3) {

					resellerId = userDetails.getUplineId();
					pcksd=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"InstantPay",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				     sdComm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdComm = pcksd.getComm();	
					}
					}
					System.out.println("sdComm:::::::::"+sdComm);
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"InstantPay",id);
						if(pckres.getPackage_id()!=null){
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						resComm=resComm-sdComm;
						}
						commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0.0);
						}
					commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT",0.0);
				//commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT"); 
				
				}
				param.put("username",username);
				List<SettlementBalance> settlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
				if(settlelist.isEmpty()){
					SettlementBalance SettlementBalance = new SettlementBalance(username,settlementamount);
					settlementbalancedao.insertSettlementBalance(SettlementBalance);	
					settle2 = new SettlementReport(username,0.0,settlementamount, settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
					
				}else{
					SettlementBalance SettlementBalance = settlelist.get(0);
					settlementamountprev = SettlementBalance.getSettlementbalance();
					settlementamountnew = settlementamountprev+settlementamount;
					SettlementBalance.setSettlementbalance(settlementamountnew);
					settlementbalancedao.updateSettlementBalance(SettlementBalance);
					settle2 = new SettlementReport(username,settlementamountprev,settlementamountnew,settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
				}
			}
				update = true;	
			}else if(processingCode.equalsIgnoreCase("210000")){
			flag = commissionService.updateBalance(username, "ADD by AEPS", "CREDIT FOR AEPS refno"+aepslog.getReferenceno()+"and Amount"+aepslog.getAmount(),settlementamount,"DEBIT",0.0);	
			}else if(processingCode.equalsIgnoreCase("BalanceEnquiry")){
			update = true;	
			}else if(processingCode.equalsIgnoreCase("1") || processingCode.equalsIgnoreCase("2") || processingCode.equalsIgnoreCase("3")  || processingCode.equalsIgnoreCase("4") || processingCode.equalsIgnoreCase("7") || processingCode.equalsIgnoreCase("6")){
			param = new HashMap<String, Object>();
			param.put("tid", aepslog.getReferenceno());
			List<Rechargedetails> rechargeDetails = rechargedetailsDao.getRechargeDetailsByNamedQuery("GetRechargeDetailsByTid",param);	
			Rechargedetails rechargedetail = rechargeDetails.get(0);
			if(request.get("status").equalsIgnoreCase("SUCCESS")){
			rechargedetail.setStatus("SUCCESS");
			rechargedetail.setValidation("SUCCESS");
			rechargedetailsDao.updateRechargeDetails(rechargedetail);	
			instantpayrechargeres(rechargedetail,userDetails);
			
			}else{
			rechargedetail.setStatus("FAILED");
			rechargedetail.setValidation("FAILED");
			rechargedetailsDao.updateRechargeDetails(rechargedetail);		
			}
			}else if(processingCode.equalsIgnoreCase("PAN")){
			param = new HashMap<String, Object>();
			param.put("referenceno", aepslog.getReferenceno());	
			List<PanApplication> panlist=panapplicationdao.getPanApplicationByNamedQuery("getPanapplicationbyusername",param);
			PanApplication pan=panlist.get(0);
			if(request.get("status").equalsIgnoreCase("SUCCESS")){
			pan.setStatus("SUCCESS");
			panapplicationdao.updatePanApplication(pan);
			}else{
			pan.setStatus("FAILED");
			panapplicationdao.updatePanApplication(pan);
			amount=pan.getAmount();
			double charge=pan.getCharge();
			double totalamount=amount+charge;
			commissionService.updateBalance(username, "PAN Application "+pan.getPan_applicationid(), "PAN Application", totalamount, "CREDIT",0.0);
			}
			}
			if(update){
			aepslog.setStatus("Success");
			aepslogdao.updateAEPSLog(aepslog);
			}
		}
			logger_log.warn("aepsresponseupdate::::::::::::::::::::::::" +update);
			}
		}
		}
			
		}catch (Exception e) {
		logger_log.error("instantpayAEPSFinalRes:::::::::::::::"+e);
		}
		return returnData;
	}



	@Override
	public Map<String, Object> instantpayAEPS(Map<String, Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		String mobileNo="";
		boolean flag=false; 
		double cl_bal=0.0;
		double totalAmount=0.0;
		double charge=0.0;
		double comm=0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		double sdCharge = 0.0;
		double distCharge = 0.0;			
		double resCharge = 0.0;
		
		
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		String customer_params="";
		
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
		/*double totalAmount=0.0;
		double charge=0.0;
		double comm=0.0;*/
		Operator op=new Operator();  
		try{
		String type="";	
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(date);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
		String currentTime = sdf.format(cal.getTime());	
	//	returnData=InstantWebserviceParser.instantpayAEPS(request.toString());	
		String agentcode=request.get("outlet_pan").toString();
		param.put("agentcode",agentcode);
		param.put("api","InstantPay");
		List<AEPSUserMap> list = aepsuserdao.getAEPSUserMapByNamedQuery("getUsernamebyagentcode",param);
		if(!list.isEmpty()){
		AEPSUserMap aeps=list.get(0);	
		List<Map<String, Object>> transact=(List<Map<String, Object>>)request.get("transactions");
		Map<String, Object> trans=transact.get(0);
		User user=globalCommandCenter.getUserByUserId(aeps.getUsername());
		double amount=Double.parseDouble(trans.get("amount").toString());
		String referenceno=GenerateRandomNumber.generatePtid(user.getMobile());
		String sp_key=trans.get("sp_key").toString();
		
		if(sp_key.equalsIgnoreCase("BAP")){	
		type="BALANCEINFO";
		}else if(sp_key.equalsIgnoreCase("WAP")){
		type="WITHDRAWAL";	
		}else if(sp_key.equalsIgnoreCase("PAN")){	
		type="PAN";	
		customer_params=trans.get("customer_params").toString();
	//	customer_params=customparams.get(0).toString();
		}else{
		param = new HashMap<String, Object>();	
		param.put("outCode",sp_key);
		List<Operator> opList =  operatorDao.getOperatorByNamedQuery("getOperatorByoutCode",param);	
		if(!opList.isEmpty()){
		op=opList.get(0);
		type=op.getServiceType();	
		}
		}
		if(type.equalsIgnoreCase("1") || type.equalsIgnoreCase("2") || type.equalsIgnoreCase("3")  || type.equalsIgnoreCase("4") || type.equalsIgnoreCase("7") || type.equalsIgnoreCase("6")){
		pckret = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Recharge",op.getOperatorId()) ;
		if (user.getRoleId() == 5) {
		if (pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
		 charge = (pckret.getCharge() * amount) / 100;
		 } else {
		 charge = pckret.getCharge();
		 }
		 } else if (user.getRoleId() == 4) {
		pckdis = commissionService.getPackagewiseCommisionOnOperator(
						user.getUserId(), "Recharge", op.getOperatorId());
		if (pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
		charge = (pckdis.getCharge() * amount) / 100;
		} else {
		charge = pckdis.getCharge();
		}

		} else if (user.getRoleId() == 3) {
		pcksd = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Recharge", op.getOperatorId());
		if (pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
		charge = (pcksd.getCharge() * amount) / 100;
		} else {
		}

		}else if (user.getRoleId() == 100) {
		pckapiu = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Recharge", op.getOperatorId());
		if (pckapiu.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
		charge = (pckapiu.getCharge() * amount) / 100;
		} else {
		charge = pckapiu.getCharge();
		}
		}	
		
		if(user.getRoleId() == 5) {
			//Retailer Id
			//rId=userDetails.getUserId();
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
			if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
			retComm =(pckret.getComm()*amount)/100;
			}else{
			retComm =pckret.getComm();	
			}
			comm = retComm;
		//	System.out.println("reseller=="+comm);
			pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",op.getOperatorId()); 
			if(pckdis.getComm_type()!=null) {
			if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
			dComm =(pckdis.getComm()*amount)/100;
			}else{
			dComm=pckdis.getComm();	
			}
			}
		//	System.out.println("dComm=="+dComm);
			pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",op.getOperatorId());
			if(pcksd.getComm_type()!=null) {
			if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
			sdComm =(pcksd.getComm()*amount)/100;
			}else{
			sdComm = pcksd.getComm();	
			}
			}
		//	System.out.println("sdComm=="+sdComm);
			pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",op.getOperatorId());
			if(pckres.getComm_type()!=null) {
			if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
			resComm =(pckres.getComm()*amount)/100;
			}else{
			resComm =pckres.getComm();	
			}
			}
		//	System.out.println("resComm=="+resComm);
			
		} else if(user.getRoleId() == 4) {
			//distId = userDetails.getUserId();	
			sdId = user.getUplineId();	
			
			parameters = new HashMap<String, String>();
			parameters.put("userId", sdId);							
			resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
			
			pckdis=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Recharge",op.getOperatorId()); 
			if(pckdis.getComm_type()!=null) {
			if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
			dComm =(pckdis.getComm()*amount)/100;
			}else{
			dComm=pckdis.getComm();	
			}
			comm = dComm;
			}
		//	System.out.println("dComm="+dComm);
			pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",op.getOperatorId());
			if(pcksd.getComm_type()!=null) {
			if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
			sdComm =(pcksd.getComm()*amount)/100;
			}else{
			sdComm = pcksd.getComm();	
			}
			sdComm=sdComm-dComm;
			}
			//System.out.println("sdComm="+sdComm);
			if(!resellerId.equals("admin")) {
			pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",op.getOperatorId());
			if(pckres.getComm_type()!=null) {
			if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
			resComm =(pckres.getComm()*amount)/100;
			}else{
			resComm =pckres.getComm();	
			}
			resComm=resComm-sdComm;
			}
			}
			//System.out.println("resComm="+resComm);
			
		} else if(user.getRoleId() == 3) {
			resellerId = user.getUplineId();
			pcksd=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Recharge",op.getOperatorId());
			if(pcksd.getComm_type()!=null) {
			if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
		     sdComm =(pcksd.getComm()*amount)/100;
			}else{
				sdComm = pcksd.getComm();	
			}
			}
			System.out.println("sdComm:::::::::"+sdComm);
			if(!resellerId.equals("admin")) {
				pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",op.getOperatorId());
				if(pckres.getComm_type()!=null) {
				if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				resComm =(pckres.getComm()*amount)/100;
				}else{
				resComm =pckres.getComm();	
				}
				resComm=resComm-sdComm;
				}
				}

		}
		
		
		totalAmount = RoundNumber.roundDouble((amount + charge) - comm);
		parameters = new HashMap<String, String>();
		double adOpBal = customQueryServiceLogic.getTotalbalanceForAdmin(CustomSqlQuery.getTotalbalanceForAdmin, parameters);
		RoundNumber.roundDouble(adOpBal - totalAmount);
		parameters = new HashMap<String, String>();
		parameters.put("userId", user.getUserId());	
		 double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters );		
		 mobileNo=trans.get("customer_mobile").toString();
		 cl_bal=op_bal-totalAmount;
		 if(cl_bal>0){
		 Rechargedetails rechargedetails = new Rechargedetails(user.getUserId(),mobileNo,op.getOperatorId(),op_bal,amount,charge,comm,cl_bal,referenceno,referenceno,referenceno, "PENDING", "PENDING","WEB" ,op.getApiId(),user.getRoleId(),user.getWlId(),today,currentTime,"");
		 flag = rechargedetailsDao.insertRechargeDetails(rechargedetails);
		 
		
		 if (flag) {
		flag = commissionService.updateBalance(user.getUserId(), "Recharge to "+mobileNo, "Recharge", totalAmount, "DEBIT",0.0);
		if(type.equalsIgnoreCase("6") || type.equalsIgnoreCase("7")){
		Utility utility = new Utility(referenceno, op.getOperatorId(), user.getUserId(), Integer.parseInt(op.getServiceType()),op.getServiceProvider(),"CUSTNAME", mobileNo,"consumerNumber","dueDate", amount, today, currentTime, "PENDING", user.getWlId());
		boolean flag1 = utilityDao.insertUtility(utility);
	    }
		if (flag) {	
		EarningSurcharge earningSurcharge = new EarningSurcharge(user.getRoleId(), user.getWlId(), user.getUserId(), comm, charge, "RECHARGE FOR - "+mobileNo, today, currentTime);
		earningSurchargeDao.insertEarningSurcharge(earningSurcharge );
/*----------------------CHARGE & COMMISSION FOR THE RECHARGE-------------------------------*/
		
		if(user.getRoleId() == 5) {
			if(pckdis.getCharge_type()!=null) {
			if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
				distCharge = (pckdis.getCharge()*amount)/100;
			}else{
				distCharge = pckdis.getCharge();
			}
			}
			if(pcksd.getCharge_type()!=null) {
			if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
				sdCharge = (pcksd.getCharge()*amount)/100;
			}else{
				sdCharge = pcksd.getCharge();
			}
			}
				if(!resellerId.equals("admin")) {
					if(pckres.getCharge_type()!=null) {
					if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
					resCharge = (pckres.getCharge()*amount)/100;
					}else{
					resCharge =	pckres.getCharge();
					}
					}
				}
				if(distCharge==0){dcharge=0;}
				else{dcharge = charge - distCharge;}
				
				if(sdCharge==0){sCharge=0;}
				else{sCharge = distCharge - sdCharge;}
				
				
				commissionService.updateBalance(distId, "Charge for Recharge - "+mobileNo, "CHARGE", dcharge, "CREDIT",0.0);
				commissionService.updateBalance(sdId, "Charge for Recharge - "+mobileNo, "CHARGE", sCharge, "CREDIT",0.0);
				if(!resellerId.equals("admin")) {
					if(resCharge==0){reCharge=0;}
					else{
					reCharge = sdCharge - resCharge;
					}
					commissionService.updateBalance(resellerId, "Charge for Recharge - "+mobileNo, "Recharge Charge", reCharge, "CREDIT",0.0);
				}
				
				/*-------------------------------For earning and Surcharge ------------------*/
				// for DISTRIBUTOR 
				EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, user.getWlId(), distId, 0.0, dcharge, "Charge FOR - "+mobileNo, today, currentTime);
				earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
				
				// For Super Distributor
				EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, user.getWlId(), sdId, 0.0, sCharge, "Charge FOR - "+mobileNo, today, currentTime);
				earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
				
				// For RESELLER
				if(!resellerId.equals("admin")) {
					EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, user.getWlId(), resellerId, 0.0, reCharge, "Charge FOR - "+mobileNo, today, currentTime);
					earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
				}
				
		} else if(user.getRoleId() == 4) {
			
			if(!resellerId.equals("admin")) {
				if(pckres.getCharge_type()!=null) {
				if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
				resCharge = (pckres.getCharge()*amount)/100;
				}else{
				resCharge =	pckres.getCharge();
				}
				}
			}
			if(pcksd.getCharge_type()!=null) {
			if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
				sdCharge = (pcksd.getCharge()*amount)/100;
			}else{
				sdCharge = pcksd.getCharge();
			}
			}
			if(sdCharge==0){sCharge=0;}
			else{sCharge = charge - sdCharge;}
																
			commissionService.updateBalance(sdId, "Charge for Recharge - "+mobileNo, "CHARGE", sCharge, "CREDIT",0.0);
			if(!resellerId.equals("admin")) {
				if(resCharge==0){reCharge=0;}
			else{
			reCharge = sdCharge - resCharge;
			}
				commissionService.updateBalance(resellerId, "Charge for Recharge - "+mobileNo, "Recharge Charge", reCharge, "CREDIT",0.0);
			}														
			/*-------------------------------For earning and Surcharge ------------------*/
																	
			// For Super Distributor
			EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, user.getWlId(), sdId, 0.0, sCharge, "Charge FOR - "+mobileNo, today, currentTime);
			earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
			
			// For RESELLER
			if(!resellerId.equals("admin")) {
				EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, user.getWlId(), resellerId, 0.0, reCharge, "Charge FOR - "+mobileNo, today, currentTime);
				earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
			}
			/*----------------------------------------------------------------------*/
		
			} else if(user.getRoleId() == 3) {

				if (!resellerId.equals("admin")) {
					if(pckres.getCharge_type()!=null) {
					if (pckres.getCharge_type()
							.equalsIgnoreCase("PERCENTAGE"))
						resCharge = (pckres.getCharge() * amount) / 100;
				} else {
					resCharge = pckres.getCharge();
				}
				resCharge = charge - resCharge;
				}
				if (!resellerId.equals("admin")) {
					
					reCharge = sdCharge - charge;
					commissionService.updateBalance(resellerId,
							"Charge for Recharge - " + mobileNo,
							"Recharge Charge", reCharge, "CREDIT",0.0);
				}

				/*-------------------------------For earning and Surcharge ------------------*/

				// For RESELLER
				if (!resellerId.equals("admin")) {
					EarningSurcharge earningSurcharge3 = new EarningSurcharge(
							4, user.getWlId(), resellerId, 0.0,
							reCharge, "Charge FOR - " + mobileNo, today,
							currentTime);
					earningSurchargeDao
							.insertEarningSurcharge(earningSurcharge3);
				}
				/*----------------------------------------------------------------------*/

			}
		}
		}
		InstantPayLog aepslog=new InstantPayLog(referenceno, agentcode,"PENDING",type,"EDIT",today,currentTime,amount);
		flag=instantpaylogdao.insertInstantPayLog(aepslog);
		}else{
		flag=false;	
		}
		}else if(type.equalsIgnoreCase("BALANCEINFO") || type.equalsIgnoreCase("WITHDRAWAL")) {
		InstantPayLog aepslog=new InstantPayLog(referenceno, agentcode,"PENDING",type,"EDIT",today,currentTime,amount);
		flag=instantpaylogdao.insertInstantPayLog(aepslog);
		}else if(type.equalsIgnoreCase("PAN")){
        param = new HashMap<String, Object>();
			
		param.put("serviceProvider", "PAN");
			List<Operator> opList =  operatorDao.getOperatorByNamedQuery("getOperatorByName", param );	
			op=opList.get(0);	
		if (user.getRoleId() == 5) {
		pckret = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Pan",op.getOperatorId()) ;
		charge = pckret.getCharge();
		}else if (user.getRoleId() == 4) {
		pckdis = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Pan", op.getOperatorId());
		charge = pckdis.getCharge();
		}else if (user.getRoleId() == 3) {
	    pcksd = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Pan", op.getOperatorId());
		charge = pcksd.getCharge();
		}
		totalAmount = RoundNumber.roundDouble((amount + charge));
		parameters = new HashMap<String, String>();
		double adOpBal = customQueryServiceLogic.getTotalbalanceForAdmin(CustomSqlQuery.getTotalbalanceForAdmin, parameters);
		RoundNumber.roundDouble(adOpBal - totalAmount);
		parameters = new HashMap<String, String>();
		parameters.put("userId", user.getUserId());	
		 double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters );		
	//	 mobileNo=trans.get("customer_mobile").toString();
		 cl_bal=op_bal-totalAmount;
		 if(cl_bal>0){
		
		String[] customerparams=customer_params.split("\\,");
		String uti_tid=customerparams[0];	
		String pan_applicationid=customerparams[1];
		PanApplication pan=new PanApplication(user.getUserId(), uti_tid, pan_applicationid, op_bal, cl_bal, amount, charge, today, currentTime, referenceno,"PENDING");
		flag=panapplicationdao.insertPanApplication(pan);
		InstantPayLog aepslog=new InstantPayLog(referenceno, agentcode,"PENDING",type,"EDIT",today,currentTime,amount);
		flag=instantpaylogdao.insertInstantPayLog(aepslog);
		if (flag) {
		flag = commissionService.updateBalance(user.getUserId(), "Pan Application "+pan_applicationid, "PAN", totalAmount, "DEBIT",0.0);	 
		}
		}else{
		flag=false;	
		}
		}else{
		flag=false;	
		}
	
		if(flag){
		returnData.put("response_code","TXN");	
		returnData.put("response_msg","Transaction Successfull");	
		List<Map<String, Object>> transactionlist=new ArrayList<>();
		Map<String, Object> transaction=new HashMap<String, Object>();
		transaction.put("agent_id",referenceno);
		transactionlist.add(transaction);
		returnData.put("transactions",transactionlist);
		}else{
		returnData.put("response_code","ERR");	
		returnData.put("response_msg","Transaction Failed");	
		}
		}else{
		returnData.put("response_code","ERR");	
		returnData.put("response_msg","Agent Code not available");	
		}
		}catch (Exception e) {
		logger_log.error("instantpayAEPS:::::::::::::::"+e);
		}
		
		return returnData;
	}



	@Override
	public Map<String, Object> instantpayAEPSBalance(Map<String, Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag=false; 
		double cl_bal=0.0;
		try{
	//	returnData=InstantWebserviceParser.instantpayAEPS(request.toString());	
		String agentcode=request.get("outlet_pan").toString();
		param.put("agentcode",agentcode);
		param.put("api","InstantPay");
		List<AEPSUserMap> list = aepsuserdao.getAEPSUserMapByNamedQuery("getUsernamebyagentcode",param);
		if(!list.isEmpty()){
		AEPSUserMap aeps=list.get(0);	
		
		parameters = new HashMap<String, String>();
		parameters.put("userId",aeps.getUsername());
		cl_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
		returnData.put("response_code","TXN");	
		returnData.put("response_msg","Transaction Successfull");	
		List<Map<String, Object>> transactionlist=new ArrayList<>();
		Map<String, Object> transaction=new HashMap<String, Object>();
		transaction.put("balance",cl_bal);
		transactionlist.add(transaction);
		returnData.put("transactions",transactionlist);
		}else{
		returnData.put("response_code","ERR");	
		returnData.put("response_msg","Agent Code not available");	
		}
		}catch (Exception e) {
		logger_log.error("instantpayAEPS:::::::::::::::"+e);
		}
		
		return returnData;
	}



	@Override
	public Map<String, Object> instantpayrechargeres(Rechargedetails rechargedetail, User userDetails) {	
	//	logger_log.warn("instantpayrechargeres::"+request);
		Map<String, Object> returnData = new HashMap<String, Object>();
		double sdComm = 0.0;
		double dComm = 0.0;
		double resComm = 0.0;
		double totalAmount = 0.0;
		
		double comm = 0.0;
		double disComm = 0.0;
		double sdisComm = 0.0;
		double reComm = 0.0;
		double rComm=0.0;
		double charge = 0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		double resCharge = 0.0;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double distCharge = 0.0;
		double sdCharge = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";
		String rId="";
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try {	
		String mobileNo = rechargedetail.getMobile();
		Operator operator = operatorDao.getOperatorByOperatorId(rechargedetail.getOperatorId());
		totalAmount = RoundNumber.roundDouble((rechargedetail.getAmount() + rechargedetail.getCharge()) - rechargedetail.getComm());			
		comm = rechargedetail.getComm();
		charge = rechargedetail.getCharge();
		parameters = new HashMap<String, String>();
		parameters.put("userId", userDetails.getUserId());	
		/*-------------------------------------COMMISSION------------------------------------------------------*/		
	if(rechargedetail.getValidation().equalsIgnoreCase("Success")){		
			if (userDetails.getRoleId() == 5) {
				// Retailer Id
				rId = rechargedetail.getUserId();
				// Distributor Id
				distId = userDetails.getUplineId();
				// Super Distributor Id
				parameters = new HashMap<String, String>();
				parameters.put("userId", distId);
				sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);

				// Reseller Id
				parameters = new HashMap<String, String>();
				parameters.put("userId", sdId);
				resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId,
						parameters);
				pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(), "Recharge",
						operator.getOperatorId());
				if (pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")) {
					rComm = (pckret.getComm() * rechargedetail.getAmount()) / 100;
				} else {
					rComm = pckret.getComm();
				}

				// System.out.println("reseller=="+comm);
				pckdis = commissionService.getPackagewiseCommisionOnOperator(distId, "Recharge",
						operator.getOperatorId());
				if (pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")) {
					dComm = (pckdis.getComm() * rechargedetail.getAmount()) / 100;
				} else {
					dComm = pckdis.getComm();
				}

				// System.out.println("dComm=="+dComm);
				pcksd = commissionService.getPackagewiseCommisionOnOperator(sdId, "Recharge", operator.getOperatorId());
				if (pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")) {
					sdComm = (pcksd.getComm() * rechargedetail.getAmount()) / 100;
				} else {
					sdComm = pcksd.getComm();
				}
				// System.out.println("sdComm=="+sdComm);
				pckres = commissionService.getPackagewiseCommisionOnOperator(resellerId, "Recharge",
						operator.getOperatorId());
				if (pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")) {
					resComm = (pckres.getComm() * rechargedetail.getAmount()) / 100;
				} else {
					resComm = pckres.getComm();
				}

			} else if (userDetails.getRoleId() == 4) {
				// Distributor Id
				distId = rechargedetail.getUserId();
				sdId = userDetails.getUplineId();
				parameters = new HashMap<String, String>();
				parameters.put("userId", sdId);
				resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId,
						parameters);
				pckdis = commissionService.getPackagewiseCommisionOnOperator(distId, "Recharge",
						operator.getOperatorId());
				if (pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")) {
					dComm = (pckdis.getComm() * rechargedetail.getAmount()) / 100;
				} else {
					dComm = pckdis.getComm();
				}

				// System.out.println("dComm=="+dComm);
				pcksd = commissionService.getPackagewiseCommisionOnOperator(sdId, "Recharge", operator.getOperatorId());
				if (pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")) {
					sdComm = (pcksd.getComm() * rechargedetail.getAmount()) / 100;
				} else {
					sdComm = pcksd.getComm();
				}

				// System.out.println("sdComm=="+sdComm);
				pckres = commissionService.getPackagewiseCommisionOnOperator(resellerId, "Recharge",
						operator.getOperatorId());
				if (pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")) {
					resComm = (pckres.getComm() * rechargedetail.getAmount()) / 100;
				} else {
					resComm = pckres.getComm();
				}

			} else if (userDetails.getRoleId() == 3) {
				// Super Distributor Id
				sdId = rechargedetail.getUserId();
				resellerId = userDetails.getUplineId();
				// System.out.println("dComm=="+dComm);
				pcksd = commissionService.getPackagewiseCommisionOnOperator(sdId, "Recharge", operator.getOperatorId());
				if (pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")) {
					sdComm = (pcksd.getComm() * rechargedetail.getAmount()) / 100;
				} else {
					sdComm = pcksd.getComm();
				}
				// System.out.println("sdComm=="+sdComm);
				pckres = commissionService.getPackagewiseCommisionOnOperator(resellerId, "Recharge",
						operator.getOperatorId());
				if (pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")) {
					resComm = (pckres.getComm() * rechargedetail.getAmount()) / 100;
				} else {
					resComm = pckres.getComm();
				}
			}
					
				
						
							
			if (userDetails.getRoleId() == 5) {

				distId = userDetails.getUplineId();

				// Super Distributor Id
				parameters = new HashMap<String, String>();
				parameters.put("userId", distId);
				sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);

				// Reseller Id
				parameters = new HashMap<String, String>();
				parameters.put("userId", sdId);
				resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId,
						parameters);
				if (dComm == 0) {
					disComm = 0;
				} else {
					disComm = dComm - rComm;
				}
				if (sdComm == 0) {
					sdisComm = 0;
				} else {
					sdisComm = sdComm - dComm;
				}
				if (resComm == 0) {
					reComm = 0;
				} else {
					reComm = resComm - sdComm;
				}

				commissionService.updateBalance(distId, "COMMISSION for Recharge - " + mobileNo, "COMMISSION", disComm,
						"CREDIT",0.0);
				commissionService.updateBalance(sdId, "COMMISSION for Recharge - " + mobileNo, "COMMISSION", sdisComm,
						"CREDIT",0.0);
				if (!resellerId.equals("admin")) {
					commissionService.updateBalance(resellerId, "COMMISSION for Recharge - " + mobileNo, "COMMISSION",
							reComm, "CREDIT",0.0);
				}

				if (rechargedetail.getValidation().equals("PENDING")) {
					/*-------------------------------For earning and Surcharge ------------------*/
					// for DISTRIBUTOR
					EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, userDetails.getWlId(), distId, disComm,
							0.0, "Charge FOR - " + mobileNo, GenerateRandomNumber.getCurrentDate(),
							GenerateRandomNumber.getCurrentTime());
					earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);

					// For Super Distributor
					EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm,
							0.0, "Charge FOR - " + mobileNo, GenerateRandomNumber.getCurrentDate(),
							GenerateRandomNumber.getCurrentTime());
					earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);

					// For RESELLER
					if (!resellerId.equals("admin")) {
						EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId,
								reComm, 0.0, "Charge FOR - " + mobileNo, GenerateRandomNumber.getCurrentDate(),
								GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
					}
					/*----------------------------------------------------------------------*/
				}
			} else if (userDetails.getRoleId() == 4) {

				if (sdComm == 0) {
					sdisComm = 0;
				} else {
					sdisComm = sdComm - dComm;
				}
				if (resComm == 0) {
					reComm = 0;
				} else {
					reComm = resComm - sdComm;
				}

				commissionService.updateBalance(sdId, "COMMISSION for Recharge - " + mobileNo, "COMMISSION", sdisComm,
						"CREDIT",0.0);
				if (!resellerId.equals("admin")) {
					commissionService.updateBalance(resellerId, "COMMISSION for Recharge - " + mobileNo, "COMMISSION",
							reComm, "CREDIT",0.0);
				}

			} else if (userDetails.getRoleId() == 3) {
				if (resComm == 0) {
					reComm = 0;
				} else {
					reComm = resComm - sdComm;
				}

				// System.out.println("COMMISSION for Recharge
				// sdcomm==="+reComm);

				if (!resellerId.equals("admin")) {
					commissionService.updateBalance(resellerId, "COMMISSION for Recharge - " + mobileNo, "COMMISSION",
							reComm, "CREDIT",0.0);
				}
			}							
			if(operator.getServiceType().equals("7") || operator.getServiceType().equals("6")){
				param = new HashMap<>();
				param.put("transactionId",rechargedetail.getPtid());
				
				List<Utility> utiList = utilityDao.getUtilityByNamedQuery("GetUtilityByTransactionId", param );
				if(!utiList.isEmpty()){
					Utility Utility=utiList.get(0);
					Utility.setStatus("SUCCESS");
					utilityDao.updateUtility(Utility);
				}
			}				
			} else {

				commissionService.updateBalance(userDetails.getUserId(), "REFUND for Recharge - " + mobileNo, "REFUND",
						totalAmount, "CREDIT",0.0);
				
				if(operator.getServiceType().equals("7") || operator.getServiceType().equals("6")){
					param = new HashMap<>();
					param.put("transactionId",rechargedetail.getPtid());
					List<Utility> utiList = utilityDao.getUtilityByNamedQuery("GetUtilityByTransactionId", param );
					if(!utiList.isEmpty()){
						Utility Utility=utiList.get(0);
						Utility.setStatus("FAILED");
						utilityDao.updateUtility(Utility);
					}
				}	

				// --------------------- Commission Refund
				// -------------------------------------------------------------

				if (userDetails.getRoleId() == 5) {

					// Charge REFUND
					if (pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						distCharge = (pckdis.getCharge() * rechargedetail.getAmount()) / 100;
					} else {
						distCharge = pckdis.getCharge();
					}
					if (pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						sdCharge = (pcksd.getCharge() * rechargedetail.getAmount()) / 100;
					} else {
						sdCharge = pcksd.getCharge();
					}
					if (!resellerId.equals("admin")) {
						if (pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
							resCharge = (pckres.getCharge() * rechargedetail.getAmount()) / 100;
						} else {
							resCharge = pckres.getCharge();
						}
					}

					if (distCharge == 0) {
						dcharge = 0;
					} else {
						dcharge = charge - distCharge;
					}
					if (sdCharge == 0) {
						sCharge = 0;
					} else {
						sCharge = distCharge - sdCharge;
					}

					commissionService.updateBalance(distId, "REVERT Charge for Recharge - " + mobileNo, "REVERT",
							dcharge, "DEBIT",0.0);
					commissionService.updateBalance(sdId, "REVERT Charge for Recharge - " + mobileNo, "REVERT", sCharge,
							"DEBIT",0.0);

					if (!resellerId.equals("admin")) {
						if (resCharge == 0) {
							reCharge = 0;
						} else {
							reCharge = sdCharge - resCharge;
						}

						commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - " + mobileNo,
								"REVERT", reCharge, "DEBIT",0.0);
					}

				} else if (userDetails.getRoleId() == 4) {

					if (pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						sdCharge = (pcksd.getCharge() * rechargedetail.getAmount()) / 100;
					} else {
						sdCharge = pcksd.getCharge();
					}
					if (!resellerId.equals("admin")) {
						if (pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
							resCharge = (pckres.getCharge() * rechargedetail.getAmount()) / 100;
						} else {
							resCharge = pckres.getCharge();
						}
					}
					if (sdCharge == 0) {
						sCharge = 0;
					} else {
						sCharge = charge - sdCharge;
					}

					commissionService.updateBalance(sdId, "REVERT Charge for Recharge - " + mobileNo, "REVERT", sCharge,
							"DEBIT",0.0);

					if (!resellerId.equals("admin")) {
						if (resCharge == 0) {
							reCharge = 0;
						} else {
							reCharge = sdCharge - resCharge;
						}

						commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - " + mobileNo,
								"REVERT", reCharge, "DEBIT",0.0);
					}

				} else if (userDetails.getRoleId() == 3) {

					if (!resellerId.equals("admin")) {
						if (pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
							resCharge = (pckres.getCharge() * rechargedetail.getAmount()) / 100;
						} else {
							resCharge = pckres.getCharge();
						}
					}

					if (!resellerId.equals("admin")) {
						if (resCharge == 0) {
							reCharge = 0;
						} else {
							reCharge = charge - resCharge;
						}

						commissionService.updateBalance(resellerId, "REVERT Charge for Recharge - " + mobileNo,
								"REVERT", reCharge, "DEBIT",0.0);
					}

				}

			}
						
					
				/*} else {
					returnData.put("status", "0");
					returnData.put("message", "No Recharge Details Found.");
				}*/
		
		} catch (Exception e) {
			returnData.put("status", "0");
			returnData.put("message", "Exception! Please contact to admin.");
			logger_log.error("doopMeApiResponse ::::::::::::::: " + e);
		}
		return returnData;
	}

	 private void writeHeaderLine(XSSFSheet sheet) {
		 
	        Row headerRow = sheet.createRow(0);
	 
	        Cell headerCell = headerRow.createCell(0);
	        headerCell.setCellValue("Course Name");
	 
	        headerCell = headerRow.createCell(1);
	        headerCell.setCellValue("Student Name");
	 
	        headerCell = headerRow.createCell(2);
	        headerCell.setCellValue("Timestamp");
	 
	        headerCell = headerRow.createCell(3);
	        headerCell.setCellValue("Rating");
	 
	        headerCell = headerRow.createCell(4);
	        headerCell.setCellValue("Comment");
	    }
	 
	 private void writeDataLines(List<User> result, XSSFWorkbook workbook,
	            XSSFSheet sheet) throws SQLException {
	        int rowCount = 1;
	 
	        for (int i=0;i<result.size();i++) {
	        	User user=result.get(i);
	            String userId = user.getUserId();
	            int roleId = user.getRoleId();
	            String wlId = user.getWlId();
	            String name = user.getName();
	            String firmName = user.getFirmName();
	            String address = user.getAddress();
	            String city = user.getCity();
	            String pinCode = user.getPinCode();
	            String state = user.getState();
	            String country = user.getCountry();
	            String mobile = user.getMobile();
	            String uplineId = user.getUplineId();
	            String email = user.getEmail();
	            String createdDate = user.getCreatedDate();
	            String createdTime = user.getCreatedTime();
	            double balance = user.getBalance();
	            String status = user.getStatus();
	            String delflag = user.getDelFlag();
	            String password = user.getPassword();
	            String tranpin = user.getTranPin();
	            double lockedamount = user.getLockedAmount();
	            
	 
	            Row row = sheet.createRow(rowCount++);
	 
	            int columnCount = 0;
	            Cell cell = row.createCell(columnCount++);
	            cell.setCellValue(userId);
	 
	            cell = row.createCell(columnCount++);
	            cell.setCellValue(password);
	 
	            cell = row.createCell(columnCount++);
	            cell.setCellValue(roleId);
	 
	            cell = row.createCell(columnCount++);
	            cell.setCellValue(wlId);
	 
	            cell = row.createCell(columnCount++);
	            cell.setCellValue(name);
	 
	            cell = row.createCell(columnCount);
	            cell.setCellValue(firmName);
	            
	            cell = row.createCell(columnCount);
	            cell.setCellValue(address);
	            
	            cell = row.createCell(columnCount);
	            cell.setCellValue(city);
	            
	            cell = row.createCell(columnCount);
	            cell.setCellValue(pinCode);
	            
	            cell = row.createCell(columnCount);
	            cell.setCellValue(state);
	            
	            cell = row.createCell(columnCount);
	            cell.setCellValue(country);
	            
	            cell = row.createCell(columnCount);
	            cell.setCellValue(mobile);
	            
	            cell = row.createCell(columnCount);
	            cell.setCellValue(uplineId);

	            cell = row.createCell(columnCount);
	            cell.setCellValue(email);

	            cell = row.createCell(columnCount);
	            cell.setCellValue(createdDate);

	            cell = row.createCell(columnCount);
	            cell.setCellValue(createdTime);

	            cell = row.createCell(columnCount);
	            cell.setCellValue(balance);

	            cell = row.createCell(columnCount);
	            cell.setCellValue(uplineId);

	            cell = row.createCell(columnCount);
	            cell.setCellValue(uplineId);
	 
	        }
	    }

	@Override
	public Map<String, Object> backup() {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			
			List<User> list=userDao.getAllUser();
			String excelFilePath = "user-export.xlsx";
			XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Reviews");
 
            writeHeaderLine(sheet);
 
            writeDataLines(list, workbook, sheet);
			String restUrl="https://mail.zoho.eu/api/accounts/696194000000002002/messages?authtoken=b039efad13e37485194b6d9175903f46";

			
			String attachments="{{\"storeName\": \"User\",\"attachmentPath\": \"C://Users//Prateeti//Desktop//agentreq.csv\",\"attachmentName\": \"User\"}}";
			
			String json="{"+"\"fromAddress\": \"admin@mobecoin.io\", "+"\"toAddress\": \"prateetisingha21@gmail.com\"," + "\"subject\": \"Email For BACKUP\"," + "\"content\": \"BackUp\"," + "\"attachments\": \""+attachments+"\"}";
			URL url = new URL(restUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
		    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
		    conn.setDoOutput(true);
		    conn.setDoInput(true);
		   OutputStream os=conn.getOutputStream();
		   os.write(json.getBytes("UTF-8"));
		   os.close();
		   Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		    StringBuilder sb = new StringBuilder();
		    for (int c; (c = in.read()) >= 0;)
		        sb.append((char)c);
		    String response1 = sb.toString();
			System.out.println(response1);
		}catch(Exception e){
			logger_log.error("backup:::::::::::::::"+e);
		}
		return returnData;
	}



	



	@Override
	public Map<String, Object> aruresponse(Map<String, String> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			logger_log.warn("aruresponse::::::::::::::::::"+request);	
		}catch (Exception e) {
			logger_log.error("aruresponse:::::::::::::::"+e);
		}
		return returnData;
	}



	@Override
	public Map<String, Object> microatmrequestnew(Map<String, Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag=false;
		String type="";
		try {
			Map<String, Object> mres=(Map<String, Object>)request.get("mMap");
			User user=userDao.getUserByUserId(mres.get("username").toString());
			if(mres.get("TYPE").toString().equalsIgnoreCase("2.0")) {
				type="WITHDRAWAL";	
			}else if(mres.get("TYPE").toString().equalsIgnoreCase("3.0")) {
			type="DEPOSIT";	
			param = new HashMap<String, Object>();
			param.put("username",user.getUserName());
			List<SettlementBalance> settlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
			if(settlelist.isEmpty()) {
				returnData.put("status","0");	
				returnData.put("message","You have no settlement balance");	
				return returnData;
			}
			}else if(mres.get("TYPE").toString().equalsIgnoreCase("4.0")) {
			type="BALANCE ENQUIRY";		
			}
		
			MicroAtmLogNew micro=new MicroAtmLogNew(mres.get("TXN_ID").toString(), mres.get("MERCHANT_USERID").toString(),"PENDING",type,mres.get("username").toString() ,GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime(),mres.get("AMOUNT").toString() );	
			flag=microatmlognewdao.insertMicroAtmLogNew(micro);
			if(flag) {
				returnData.put("status","1");	
				returnData.put("message","success");	
			}else {
				returnData.put("status","0");	
				returnData.put("message","Failed");	
			}
			
		}catch (Exception e) {
			logger_log.warn("microatmrequest::::::::::::::::::::::"+e);
		}
		return returnData;
}



	@Override
	public Map<String, Object> microatmresponsenew(Map<String, Object> request) {
		logger_log.warn("microatmresponsenew::::"+request);
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag = false;
		String username = "";
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		double amount = 0.0;
		String processingCode = "";
		boolean update = false;
		double settlementamount = 0.0;
		double settlementamountprev = 0.0;
		double settlementamountnew = 0.0;
		int id = 0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		String status="";
		double AGENT_CHARGE=0.0;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		try {
			SettlementReport settle2=null;
			//String status=data.get("txnStatus");
			param = new HashMap<String, Object>();
			param.put("referenceno", request.get("transactionid"));
			param.put("status", "PENDING");
			List<MicroAtmLogNew> listran=microatmlognewdao.getMicroAtmLogNewByNamedQuery("getNewLogByrefnostatus", param);
			if(!listran.isEmpty()){
			//	String msg= request.get("Msg");
				if(Boolean.parseBoolean(request.get("status").toString())) {
				status="SUCCESS";	
				}else{
				status="Failed";		
				}
				MicroAtmLogNew mLog=listran.get(0);
				mLog.setStatus(status);
				microatmlognewdao.updateMicroAtmLogNew(mLog);
				double txnamount=0.0;
				if(mLog.getType().equalsIgnoreCase("WITHDRAWAL")) {
				txnamount=Double.parseDouble(mLog.getAmount());
				}
				MicroAtmResponseNew micro=new MicroAtmResponseNew(request.get("cardNumber").toString(), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(), request.get("bankName").toString(), request.get("cardType").toString(), request.get("bankRRN").toString(),request.get("transactionid").toString() , mLog.getUserId(), txnamount, Double.parseDouble(request.get("balanceAmount").toString()), mLog.getType(), status, request.get("transactionRemark").toString());
				microatmresponsenew.insertMicroAtmResponseNew(micro);
			if(status.equalsIgnoreCase("SUCCESS")){
					if(mLog.getType().equals("WITHDRAWAL")){
						
						settlementamount=Double.parseDouble(mLog.getAmount());
						param = new HashMap<String, Object>();
						param.put("username",mLog.getUserId());
						List<SettlementBalance> settlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
						if(settlelist.isEmpty()){
							SettlementBalance SettlementBalance = new SettlementBalance(mLog.getUserId(),settlementamount);
							settlementbalancedao.insertSettlementBalance(SettlementBalance);	
							settle2 = new SettlementReport(mLog.getUserId(),0.0,settlementamount, settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
						}else{
							Map<String, String>	parameter = new HashMap<String, String>();
							parameter.put("userId", mLog.getUserId());
							 double settleopbal = customQueryServiceLogic
									.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForsettlement,parameter);
							SettlementBalance SettlementBalance = settlelist.get(0);
							settleopbal = SettlementBalance.getSettlementbalance();
							settlementamountnew = settleopbal+settlementamount;
							SettlementBalance.setSettlementbalance(settlementamountnew);
							settlementbalancedao.updateSettlementBalance(SettlementBalance);
							settle2 = new SettlementReport(mLog.getUserId(),settleopbal,settlementamountnew,settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
						}
						  settlementreportdao.insertSettlementReport(settle2);
						    User user=userDao.getUserByUserId(mLog.getUserId());
								
								if(user.getWlId().startsWith("ASR")){
								param = new HashMap<String, Object>();
								param.put("wlId",user.getWlId());
								List<User> wuser=userDao.getUserByNamedQuery("getWILDbyID", param);
								param = new HashMap<String, Object>();
								param.put("username",wuser.get(0).getUserId());
								List<SettlementBalance> wsettlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
								SettlementReport settlew2;
								if(wsettlelist.isEmpty()){
									SettlementBalance SettlementBalance = new SettlementBalance(wuser.get(0).getUserId(),settlementamount);
									settlementbalancedao.insertSettlementBalance(SettlementBalance);	
									settlew2 = new SettlementReport(wuser.get(0).getUserId(),0.0,settlementamount, settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
								}else{
									SettlementBalance SettlementBalance = wsettlelist.get(0);
									settlementamountprev = SettlementBalance.getSettlementbalance();
									settlementamountnew = settlementamountprev+settlementamount;
									SettlementBalance.setSettlementbalance(settlementamountnew);
									settlementbalancedao.updateSettlementBalance(SettlementBalance);
									settlew2 = new SettlementReport(wuser.get(0).getUserId(),settlementamountprev,settlementamountnew,settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
								}
								settlementreportdao.insertSettlementReport(settlew2);
							}
						
						param = new HashMap<String, Object>();
						param.put("api","MicroATM");
						
						List<AEPSCommission> aepscommlist = aepscommissiondao.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi",param);
						
						for(AEPSCommission comm2 : aepscommlist){
							if(settlementamount>=comm2.getSlab1() && settlementamount<=comm2.getSlab2()){
								id = comm2.getId();
								break;
							}
						}
						param = new HashMap<String, Object>();
						
						if(user.getRoleId() == 5) {
							double sudComm=0.0;
							double deComm=0.0;
							pckret = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"MicroATM",id) ;
							//Retailer Id
							//rId=userDetails.getUserId();
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
							if(pckret.getPackage_id()!=null){
							if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							retComm =(pckret.getComm()*settlementamount)/100;
							}else{
							retComm =pckret.getComm();	
							}
							comm = retComm;
							}
							System.out.println("comm:::::::::::::::::::::::::::::"+comm);
						//	System.out.println("reseller=="+comm);
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"MicroATM",id); 
							if(pckdis.getPackage_id()!=null){
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*settlementamount)/100;
							}else{
							dComm=pckdis.getComm();	
							}
							deComm=dComm-comm;
							}
							System.out.println("dComm:::::::::::::::::::::::::::::"+dComm);
						//	System.out.println("dComm=="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"MicroATM",id);
							if(pcksd.getPackage_id()!=null){
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*settlementamount)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							sudComm=sdComm-dComm;
							}
							
							
							if(!resellerId.equals("admin")) {
								pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"MicroATM",id);
								if(pckres.getPackage_id()!=null){
								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								resComm =(pckres.getComm()*settlementamount)/100;
								}else{
								resComm =pckres.getComm();	
								}
								resComm=resComm-sdComm;
								}
								commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0);
								}
							
						//	System.out.println("resComm=="+resComm);
							commissionService.updateBalance(user.getUserId(), "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
							commissionService.updateBalance(distId, "AEPS Commission For Amount - "+settlementamount, "Commission", deComm, "CREDIT",0);
							commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sudComm, "CREDIT",0);
							
						}else if(user.getRoleId() == 4) {
							double sudComm=0.0;

							//distId = userDetails.getUserId();	
							sdId = user.getUplineId();	
							
							parameters = new HashMap<String, String>();
							parameters.put("userId", sdId);							
							resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
							
							pckdis=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"MicroATM",id); 
							if(pckdis.getPackage_id()!=null){
							if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							dComm =(pckdis.getComm()*settlementamount)/100;
							}else{
							dComm=pckdis.getComm();	
							}
							}
							
							comm = dComm;
							
						//	System.out.println("dComm="+dComm);
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"MicroATM",id);
							System.out.println("pcksd:::::::::::::::::"+pcksd);
							if(pcksd.getPackage_id()!=null){
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sdComm =(pcksd.getComm()*settlementamount)/100;
							}else{
							sdComm = pcksd.getComm();	
							}
							sudComm=sdComm-dComm;
							}
							
							//System.out.println("sdComm="+sdComm);
							if(!resellerId.equals("admin")) {
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"MicroATM",id);
							if(pckres.getPackage_id()!=null){
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*settlementamount)/100;
							}else{
							resComm =pckres.getComm();	
							}
							resComm=resComm-sdComm;
							}
							commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0);
							}
							//System.out.println("resComm="+resComm);
							
							commissionService.updateBalance(user.getUserId(), "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
							commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sudComm, "CREDIT",0);
							
						}else if(user.getRoleId() == 3) {

							resellerId = user.getUplineId();
							pcksd=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"MicroATM",id);
							if(pcksd.getPackage_id()!=null){
							if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						     sdComm =(pcksd.getComm()*settlementamount)/100;
							}else{
								sdComm = pcksd.getComm();	
							}
							comm = sdComm;
							}
							System.out.println("sdComm:::::::::"+sdComm);
							if(!resellerId.equals("admin")) {
								pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"MicroATM",id);
								if(pckres.getPackage_id()!=null){
								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
								resComm =(pckres.getComm()*settlementamount)/100;
								}else{
								resComm =pckres.getComm();	
								}
								resComm=resComm-sdComm;
								}
								commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0);
								}
							commissionService.updateBalance(user.getUserId(), "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
						
						}
					}else if(mLog.getType().equals("DEPOSIT")){
						settlementamount=Double.parseDouble(mLog.getAmount());
						param = new HashMap<String, Object>();
						param.put("username",mLog.getUserId());
						List<SettlementBalance> settlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
						if(!settlelist.isEmpty()){
							Map<String, String>	parameter = new HashMap<String, String>();
							parameter.put("userId", mLog.getUserId());
							 double settleopbal = customQueryServiceLogic
									.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForsettlement,parameter);
							SettlementBalance SettlementBalance = settlelist.get(0);
							settleopbal = SettlementBalance.getSettlementbalance();
							settlementamountnew = settleopbal-settlementamount;
							SettlementBalance.setSettlementbalance(settlementamountnew);
							settlementbalancedao.updateSettlementBalance(SettlementBalance);
							settle2 = new SettlementReport(mLog.getUserId(),settleopbal,settlementamountnew,settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"DEPOSIT","SUCCESS","SUCCESS");
						}
						  settlementreportdao.insertSettlementReport(settle2);
						    User user=userDao.getUserByUserId(mLog.getUserId());
								
								if(user.getWlId().startsWith("ASR")){
								param = new HashMap<String, Object>();
								param.put("wlId",user.getWlId());
								List<User> wuser=userDao.getUserByNamedQuery("getWILDbyID", param);
								param = new HashMap<String, Object>();
								param.put("username",wuser.get(0).getUserId());
								List<SettlementBalance> wsettlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
								SettlementReport settlew2;
								if(!wsettlelist.isEmpty()){
									SettlementBalance SettlementBalance = wsettlelist.get(0);
									settlementamountprev = SettlementBalance.getSettlementbalance();
									settlementamountnew = settlementamountprev-settlementamount;
									SettlementBalance.setSettlementbalance(settlementamountnew);
									settlementbalancedao.updateSettlementBalance(SettlementBalance);
									settlew2 = new SettlementReport(wuser.get(0).getUserId(),settlementamountprev,settlementamountnew,settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"DEPOSIT","SUCCESS","SUCCESS");
									settlementreportdao.insertSettlementReport(settlew2);
								}
								
							}
							
					}
					returnData.put("RESP_CODE", "300");
					returnData.put("RESPONSE", "SUCCESS");
					returnData.put("RESP_MSG", "Transaction Success");
				}else{
					returnData.put("RESP_CODE", "302");
					returnData.put("RESPONSE", "FAILED");
					returnData.put("RESP_MSG", "Transaction Failed");
				}
				
			}else{
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("yesbankaepsApiResponse::::::::::::::::::::::::" + e);
		}
		return returnData;
	}



	
}
