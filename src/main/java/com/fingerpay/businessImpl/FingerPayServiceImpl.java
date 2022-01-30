package com.fingerpay.businessImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fingerpay.businessDao.FingerPayService;
import com.fingerpay.util.FingerCaptureParser;
import com.fingerpay.webservice.FingerPayWebServiceParser;
import com.fingerpay.webservice.FingerPayWebservice;
import com.recharge.businessdao.CommissionService;
import com.recharge.businessdao.CustomQueryServiceLogic;
import com.recharge.customModel.EncoreAEPSRequest;
import com.recharge.customModel.EncoreAEPSResponse;
import com.recharge.customModel.EncoreAadharPayResponse;
import com.recharge.customModel.MinistatementResponse;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.model.AEPSCommission;
import com.recharge.model.AEPSUserMap;
import com.recharge.model.AssignedPackage;
import com.recharge.model.EarningSurcharge;
import com.recharge.model.FingerPayAEPSResponse;
import com.recharge.model.Operator;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.SettlementBalance;
import com.recharge.model.SettlementReport;
import com.recharge.model.TdsReport;
import com.recharge.model.User;
import com.recharge.servicedao.AEPSCommissionDao;
import com.recharge.servicedao.AEPSUserMapDao;
import com.recharge.servicedao.EarningSurchargeDao;
import com.recharge.servicedao.FingerPayAEPSResponseDao;
import com.recharge.servicedao.SettlementBalanceDao;
import com.recharge.servicedao.SettlementChargeDao;
import com.recharge.servicedao.SettlementReportDao;
import com.recharge.servicedao.TdsReportDao;
import com.recharge.servicedao.UserDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.yesbankmodel.YesBankAEPSResponse;
@Service("fingerpayservice")
public class FingerPayServiceImpl implements FingerPayService {
	private static final Logger logger_log = Logger.getLogger(FingerPayService.class);
	@Autowired AEPSUserMapDao aepsuserdao;
	@Autowired FingerPayAEPSResponseDao fingerpayaepsresponseDao;
	@Autowired SettlementBalanceDao settlementbalancedao;
	@Autowired SettlementReportDao settlementreportdao;
	@Autowired SettlementChargeDao  settlementchargrdao;
	@Autowired UserDao userDao;
	@Autowired AEPSCommissionDao aepscommissiondao;
	@Autowired CommissionService commissionService;
	@Autowired CustomQueryServiceLogic customQueryServiceLogic;
	@Autowired TdsReportDao TdsReportDao;
	@Autowired EarningSurchargeDao earningSurchargeDao;
	
	private static String aepsurl="https://www.apivendor.com";
	//private static String aepsurl="http://localhost:8081/apiVendor";
    private static String aepsapiuser="6T0L9H";
    private static String apipass="7C2C01#";

	@Override
	public Map<String, Object> encorepayaeps(Map<String, String> request,User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		boolean flag=false;
		SettlementReport settle2=null;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double settlementamount = 0.0;
		double settlementamountprev = 0.0;
		double settlementamountnew = 0.0;
		String username = user.getUserName();
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		double amount =0.0;
		int id=0;
		try {
			if(user.getStatus().equals("1")){
				returnData.put("status", "0");
				returnData.put("message", "YOur ID is Inactive");
			}else{
			param = new HashMap<String, Object>();
			param.put("username",user.getUserName());
			param.put("api","Encore");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			if(!list2.isEmpty()) {
			AEPSUserMap aeps=list2.get(0);	
			EncoreAEPSRequest aepsreq=new EncoreAEPSRequest(request.get("date"), request.get("time"), request.get("BiometricData"), request.get("aadhar"), request.get("iin"), request.get("order_id"), request.get("latitude"), request.get("longitude"), request.get("mobile"), request.get("amount"), request.get("type"), request.get("deviceIMEI"), aepsapiuser, apipass, aeps.getAgentcode());
		   // String 	input="{\"date\":\""+request.get("date")+"\",\"time\":\""+request.get("time")+"\",\"BiometricData\":\""+request.get("BiometricData")+"\",\"aadhar\":\""+request.get("aadhar")+"\",\"iin\":\""+request.get("iin")+"\",\"order_id\":\""+request.get("order_id")+"\",\"latitude\":\""+request.get("latitude")+"\",\"longitude\":\""+request.get("longitude")+"\",\"mobile\":\""+request.get("mobile")+"\",\"amount\":\""+request.get("amount")+"\",\"type\":\""+request.get("type")+"\",\"deviceIMEI\":\""+request.get("deviceIMEI")+"\",\"Username\":\""+aepsapiuser+"\",\"Password\":\""+apipass+"\",\"AID\":\""+aeps.getAgentcode()+"\"}";
		   String response=FingerPayWebservice.sendRequestToWebservice(aepsreq,"encorepayaeps");
		 //  String response="{\"EncoreAEPSResponse\":{\"status\":true,\"statusCode\":10000,\"message\":\"Request Completed\",\"terminalId\":\"FA466151\",\"date\":\"2020-05-28\",\"time\":\"14:20:31\",\"transactionStatus\":\"successful\",\"bankRRN\":\"014914025001\",\"transactionType\":\"BALANCEINFO\",\"merchantTxnId\":\"44798575491714\",\"transactionAmount\":\"0.0\",\"balanceAmount\":\"497.98\",\"encoreTransactionId\":\"BEBE0750290280520142032738I\"}} ";
		    EncoreAEPSResponse encore=FingerPayWebServiceParser.getFinPayAepsParser(response);
		    JSONObject jobj=new JSONObject(response);
		    JSONObject encoreobj=jobj.getJSONObject("EncoreAEPSResponse");
		    if(encoreobj.getInt("statusCode")==10000) {
		    FingerPayAEPSResponse fing=new FingerPayAEPSResponse(encore.getTerminalId(),encore.getDate(),encore.getTime(),encore.getBankRRN() ,encore.getTransactionType(),encore.getEncoreTransactionId(),encore.getMerchantTxnId(),Double.parseDouble(encore.getTransactionAmount()),Double.parseDouble(encore.getBalanceAmount()),encore.getTransactionStatus(), user.getUserName());	
		    flag=fingerpayaepsresponseDao.insertFingerPayAEPSResponse(fing);
		    if(flag){
		    if(fing.getTransactionType().equalsIgnoreCase("WITHDRAWAL")){
				amount=fing.getTransactionAmount();	
				settlementamount=amount;
				
				param = new HashMap<String, Object>();
				param.put("username",user.getUserName());
				List<SettlementBalance> settlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
				if(settlelist.isEmpty()){
					SettlementBalance SettlementBalance = new SettlementBalance(username,settlementamount);
					settlementbalancedao.insertSettlementBalance(SettlementBalance);	
					settle2 = new SettlementReport(user.getUserName(),0.0,settlementamount, settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
				}else{
					Map<String, String>	parameter = new HashMap<String, String>();
					parameter.put("userId", user.getUserId());
					double settleopbal = customQueryServiceLogic
							.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForsettlement,parameter);
					SettlementBalance SettlementBalance = settlelist.get(0);
					settlementamountprev = SettlementBalance.getSettlementbalance();
					settlementamountnew = settleopbal+settlementamount;
					SettlementBalance.setSettlementbalance(settlementamountnew);
					settlementbalancedao.updateSettlementBalance(SettlementBalance);
					settle2 = new SettlementReport(user.getUserName(),settleopbal,settlementamountnew,settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
				}
				    settlementreportdao.insertSettlementReport(settle2);
				
				
				param = new HashMap<String, Object>();
				param.put("api","Encore AEPS");
				
				List<AEPSCommission> aepscommlist = aepscommissiondao.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi",param);
				for(AEPSCommission comm2 : aepscommlist){
					if(settlementamount>=comm2.getSlab1() && settlementamount<=comm2.getSlab2()){
						id = comm2.getId();
						break;
					}
				}
				param = new HashMap<String, Object>();
				
				if(user.getRoleId() == 5) {
					pckret = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Encore AEPS",id) ;
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
				//	System.out.println("reseller=="+comm);
					double decomm=0;
					pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Encore AEPS",id); 
					if(pckdis.getPackage_id()!=null){
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						decomm =(pckdis.getComm()*settlementamount)/100;
					}else{
						decomm=pckdis.getComm();	
					}
					dComm=decomm-comm;
					}
				//	System.out.println("dComm=="+dComm);
					double sdecomm=0;
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Encore AEPS",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdecomm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdecomm = pcksd.getComm();	
					}
					sdComm=sdecomm-decomm;
					}
					
				
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Encore AEPS",id);
						if(pckres.getPackage_id()!=null){
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						resComm=resComm-sdecomm;
						}
						commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm/1.05, "CREDIT",0);
						TdsReport td1=new TdsReport(resellerId, resComm, resComm-(resComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						TdsReportDao.insertTdsReport(td1);
						}
					
				//	System.out.println("resComm=="+resComm);
					commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm/1.05, "CREDIT",0);
					TdsReport td=new TdsReport(username, comm, comm-(comm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td);
					commissionService.updateBalance(distId, "AEPS Commission For Amount - "+settlementamount, "Commission", dComm/1.05, "CREDIT",0);
					TdsReport td1=new TdsReport(distId, dComm, dComm-(dComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td1);
					commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm/1.05, "CREDIT",0);
					TdsReport td2=new TdsReport(sdId, sdComm, sdComm-(sdComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td2);
					
				}else if(user.getRoleId() == 4) {

					//distId = userDetails.getUserId();	
					sdId = user.getUplineId();	
					
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					pckdis=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Encore AEPS",id); 
					if(pckdis.getPackage_id()!=null){
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*settlementamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
					}
					
					comm = dComm;
					
					double sdecomm=0;
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Encore AEPS",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdecomm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdecomm = pcksd.getComm();	
					}
					sdComm=sdecomm-dComm;
					}
					
					//System.out.println("sdComm="+sdComm);
					if(!resellerId.equals("admin")) {
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Encore AEPS",id);
					if(pckres.getPackage_id()!=null){
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*settlementamount)/100;
					}else{
					resComm =pckres.getComm();	
					}
					resComm=resComm-sdecomm;
					}
					commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm/1.05, "CREDIT",0);
					TdsReport td1=new TdsReport(resellerId, resComm, resComm-(resComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td1);
					}
					//System.out.println("resComm="+resComm);
					
					commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm/1.05, "CREDIT",0);
					TdsReport td=new TdsReport(username, comm, comm-(comm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td);
					commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm/1.05, "CREDIT",0);
					TdsReport td1=new TdsReport(sdId, sdComm, sdComm-(sdComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td1);
					
				}else if(user.getRoleId() == 3) {

					resellerId = user.getUplineId();
					pcksd=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Encore AEPS",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				     sdComm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdComm = pcksd.getComm();	
					}
					comm=sdComm;
					}
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Encore AEPS",id);
						if(pckres.getPackage_id()!=null){
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						resComm=resComm-sdComm;
						}
						commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm/1.05, "CREDIT",0);
						TdsReport td1=new TdsReport(resellerId, resComm, resComm-(resComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						TdsReportDao.insertTdsReport(td1);
						}
					commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm/1.05, "CREDIT",0);
					TdsReport td=new TdsReport(username, comm, comm-(comm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td);
				
				}
			
					
				
				}
				/*
				 * returnData.put("status","1"); returnData.put("message","SUCCESS");
				 */
		    }
		   
		    
		    }
		    returnData.put("EncoreAEPSResponse",encore);
		    
			}else {
				returnData.put("status","0");
			    returnData.put("message","You are not active agent");	
			}
			}
		}catch (Exception e) {
			logger_log.error("encorepayaeps::::::::::::::::::::::::::"+e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> encorepayaepseb(Map<String, String> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		boolean flag=false;
		SettlementReport settle2=null;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double settlementamount = 0.0;
		double settlementamountprev = 0.0;
		double settlementamountnew = 0.0;
		String username = user.getUserName();
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		double amount =0.0;
		String amount2="";
		int id=0;
	//	Location
		try {
			if(user.getStatus().equals("1")){
				returnData.put("status", "0");
				returnData.put("message", "Your ID is Inactive");
			}else{
			DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			Date date3 = Calendar.getInstance().getTime();
			String today3 = formatter2.format(date3);
			param = new HashMap<String, Object>();
			param.put("username",user.getUserName());
			param.put("api","Encore");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			if(!list2.isEmpty()) {
			AEPSUserMap aeps=list2.get(0);	
			String deviceIMEI=FingerCaptureParser.captureResponse(request.get("pidData"));
			String order_id=GenerateRandomNumber.generateIPtid("mobile");
			String date=GenerateRandomNumber.getCurrentDate();
			String time=GenerateRandomNumber.getCurrentTime();
			if(request.get("mode").equalsIgnoreCase("Balace Enquiry")) {
			amount2="";	
			}else {
			amount2=request.get("amount");		
			}
			EncoreAEPSRequest aepsreq=new EncoreAEPSRequest(date, time,request.get("pidData"), request.get("aadhar"), request.get("iin"),order_id, "25.6742129", "87.4733856", request.get("mobile"),amount2, request.get("mode"),deviceIMEI, aepsapiuser, apipass, aeps.getAgentcode());
		   // String 	input="{\"date\":\""+request.get("date")+"\",\"time\":\""+request.get("time")+"\",\"BiometricData\":\""+request.get("BiometricData")+"\",\"aadhar\":\""+request.get("aadhar")+"\",\"iin\":\""+request.get("iin")+"\",\"order_id\":\""+request.get("order_id")+"\",\"latitude\":\""+request.get("latitude")+"\",\"longitude\":\""+request.get("longitude")+"\",\"mobile\":\""+request.get("mobile")+"\",\"amount\":\""+request.get("amount")+"\",\"type\":\""+request.get("type")+"\",\"deviceIMEI\":\""+request.get("deviceIMEI")+"\",\"Username\":\""+aepsapiuser+"\",\"Password\":\""+apipass+"\",\"AID\":\""+aeps.getAgentcode()+"\"}";
		    String response=FingerPayWebservice.sendRequestToWebservice(aepsreq,"encorepayaeps");
			//String response="{'EncoreAEPSResponse':{'status':true,'statusCode':10000,'message':'Request Completed','terminalId':'FA581835','date':'2020-08-04','time':'09:57:37','transactionStatus':'successful','bankRRN':'021709811748','transactionType':'WITHDRAWAL','merchantTxnId':'42477262513501','transactionAmount':'5000.0','balanceAmount':'252605.47','responseCode':'00','mobile':'9182001794','aadhar':'553727753222','iin':'607126','encoreTransactionId':'CWBC1046049040820095737971I'}}";

		    
		    
		//	String response="{\"EncoreAEPSResponse\":{\"status\":true,\"statusCode\":10000,\"message\":\"Request Completed\",\"terminalId\":\"FA466151\",\"date\":\"2020-05-28\",\"time\":\"14:20:31\",\"transactionStatus\":\"successful\",\"bankRRN\":\"014914025001\",\"transactionType\":\"BALANCEINFO\",\"merchantTxnId\":\"44798575491714\",\"transactionAmount\":\"0.0\",\"balanceAmount\":\"497.98\",\"encoreTransactionId\":\"BEBE0750290280520142032738I\"}} ";
		//  String response="{\"EncoreAEPSResponse\":{\"status\":false,\"statusCode\":10004,\"message\":\"Customer  Aadhaar number is not linked with Selected Bank. Please check with the bank or select another Aadhaar linked bank\",\"terminalId\":\"FA466151\",\"date\":\"2020-07-13\",\"time\":\"01:05:59 PM\",\"transactionStatus\":\"failed\",\"bankRRN\":\"019513481472\",\"transactionType\":\"BALANCEINFO\",\"merchantTxnId\":\"NA\",\"transactionAmount\":\"0.0\",\"balanceAmount\":\"0.0\",\"encoreTransactionId\":\"BEBT0750290130720130600276I\"}}";
		    EncoreAEPSResponse encore=FingerPayWebServiceParser.getFinPayAepsParser(response);
		    JSONObject jobj=new JSONObject(response);
		    JSONObject encoreobj=jobj.getJSONObject("EncoreAEPSResponse");
		    FingerPayAEPSResponse fing=new FingerPayAEPSResponse(encore.getTerminalId(),date,time,encore.getBankRRN() ,encore.getTransactionType(),encore.getEncoreTransactionId(),encore.getMerchantTxnId(),Double.parseDouble(encore.getTransactionAmount()),Double.parseDouble(encore.getBalanceAmount()),encore.getTransactionStatus(), user.getUserName());	
		    flag=fingerpayaepsresponseDao.insertFingerPayAEPSResponse(fing);
		    if(encoreobj.getInt("statusCode")==10000) {
		    
		    if(flag){
		    if(fing.getTransactionType().equalsIgnoreCase("WITHDRAWAL")){
				amount=fing.getTransactionAmount();	
				settlementamount=amount;
				param = new HashMap<String, Object>();
				param.put("api","Encore AEPS");
				
				List<AEPSCommission> aepscommlist = aepscommissiondao.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi",param);
				for(AEPSCommission comm2 : aepscommlist){
					if(settlementamount>=comm2.getSlab1() && settlementamount<=comm2.getSlab2()){
						id = comm2.getId();
						break;
					}
				}
				param = new HashMap<String, Object>();
				
				param.put("username",user.getUserName());
				List<SettlementBalance> settlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
				if(settlelist.isEmpty()){
					SettlementBalance SettlementBalance = new SettlementBalance(username,settlementamount);
					settlementbalancedao.insertSettlementBalance(SettlementBalance);	
					settle2 = new SettlementReport(user.getUserName(),0.0,settlementamount, settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
				}else{
					Map<String, String>	parameter = new HashMap<String, String>();
					parameter.put("userId", user.getUserId());
					double settleopbal = customQueryServiceLogic
							.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForsettlement,parameter);
					SettlementBalance SettlementBalance = settlelist.get(0);
					settlementamountprev = SettlementBalance.getSettlementbalance();
					settlementamountnew = settleopbal+settlementamount;
					SettlementBalance.setSettlementbalance(settlementamountnew);
					settlementbalancedao.updateSettlementBalance(SettlementBalance);
					settle2 = new SettlementReport(user.getUserName(),settleopbal,settlementamountnew,settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
				}
				    settlementreportdao.insertSettlementReport(settle2);
				
				    param = new HashMap<String, Object>();
				
				if(user.getRoleId() == 5) {
					pckret = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Encore AEPS",id) ;
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
				//	System.out.println("reseller=="+comm);
					double decomm=0;
					pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Encore AEPS",id); 
					if(pckdis.getPackage_id()!=null){
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						decomm =(pckdis.getComm()*settlementamount)/100;
					}else{
						decomm=pckdis.getComm();	
					}
					dComm=decomm-comm;
					}
				//	System.out.println("dComm=="+dComm);
					double sdecomm=0;
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Encore AEPS",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdecomm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdecomm = pcksd.getComm();	
					}
					sdComm=sdecomm-decomm;
					}
					
				
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Encore AEPS",id);
						if(pckres.getPackage_id()!=null){
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						resComm=resComm-sdecomm;
						}
						commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm/1.05, "CREDIT",0);
						TdsReport td1=new TdsReport(resellerId, resComm, resComm-(resComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						TdsReportDao.insertTdsReport(td1);
						}
					
				//	System.out.println("resComm=="+resComm);
					commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm/1.05, "CREDIT",0);
					TdsReport td=new TdsReport(username, comm, comm-(comm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td);
					commissionService.updateBalance(distId, "AEPS Commission For Amount - "+settlementamount, "Commission", dComm/1.05, "CREDIT",0);
					TdsReport td1=new TdsReport(distId, dComm, dComm-(dComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td1);
					commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm/1.05, "CREDIT",0);
					TdsReport td2=new TdsReport(sdId, sdComm, sdComm-(sdComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td2);
					
				}else if(user.getRoleId() == 4) {

					//distId = userDetails.getUserId();	
					sdId = user.getUplineId();	
					
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					pckdis=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Encore AEPS",id); 
					if(pckdis.getPackage_id()!=null){
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*settlementamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
					}
					
					comm = dComm;
					
					double sdecomm=0;
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Encore AEPS",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdecomm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdecomm = pcksd.getComm();	
					}
					sdComm=sdecomm-dComm;
					}
					
					//System.out.println("sdComm="+sdComm);
					if(!resellerId.equals("admin")) {
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Encore AEPS",id);
					if(pckres.getPackage_id()!=null){
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*settlementamount)/100;
					}else{
					resComm =pckres.getComm();	
					}
					resComm=resComm-sdecomm;
					}
					commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm/1.05, "CREDIT",0);
					TdsReport td1=new TdsReport(resellerId, resComm, resComm-(resComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td1);
					}
					//System.out.println("resComm="+resComm);
					
					commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm/1.05, "CREDIT",0);
					TdsReport td=new TdsReport(username, comm, comm-(comm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td);
					commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm/1.05, "CREDIT",0);
					TdsReport td1=new TdsReport(sdId, sdComm, sdComm-(sdComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td1);
					
				}else if(user.getRoleId() == 3) {

					resellerId = user.getUplineId();
					pcksd=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Encore AEPS",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				     sdComm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdComm = pcksd.getComm();	
					}
					comm=sdComm;
					}
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Encore AEPS",id);
						if(pckres.getPackage_id()!=null){
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						resComm=resComm-sdComm;
						}
						commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm/1.05, "CREDIT",0);
						TdsReport td1=new TdsReport(resellerId, resComm, resComm-(resComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						TdsReportDao.insertTdsReport(td1);
						}
					commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm/1.05, "CREDIT",0);
					TdsReport td=new TdsReport(username, comm, comm-(comm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td);
				
				}
			
				
				
				}
				 returnData.put("status","1"); 
				 returnData.put("message","SUCCESS");
				 returnData.put("EncoreAEPSResponse",encore);
		    }
		   
		    
		    }else {
		    	 returnData.put("status","0"); 
		    	 returnData.put("message",encore.getMessage());
		    	 returnData.put("nextPage","aepsnew"); 
		//    FingerPayAEPSResponse fing=new FingerPayAEPSResponse(encore.getTerminalId(),date,time,encore.getBankRRN() ,encore.getTransactionType(),encore.getEncoreTransactionId(),encore.getMerchantTxnId(),Double.parseDouble(encore.getTransactionAmount()),Double.parseDouble(encore.getBalanceAmount()),encore.getTransactionStatus(), user.getUserName());	
		    }
		   
		    
			}else {
				returnData.put("status","0");
			    returnData.put("message","You are not active agent");	
			}
			}
		}catch (Exception e) {
			logger_log.error("encorepayaeps::::::::::::::::::::::::::"+e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> encorepayaadharweb(Map<String, String> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		boolean flag=false;
		SettlementReport settle2=null;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double settlementamount = 0.0;
		double settlementamountprev = 0.0;
		double settlementamountnew = 0.0;
		String username = user.getUserName();
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		double amount =0.0;
		String amount2="";
		int id=0;
	//	Location
		try {
			DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			Date date3 = Calendar.getInstance().getTime();
			String today3 = formatter2.format(date3);
			param = new HashMap<String, Object>();
			param.put("username",user.getUserName());
			param.put("api","Encore");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			if(!list2.isEmpty()) {
			AEPSUserMap aeps=list2.get(0);	
			String deviceIMEI=FingerCaptureParser.captureResponse(request.get("pidData"));
			String order_id=GenerateRandomNumber.generateIPtid("mobile");
			String date=GenerateRandomNumber.getCurrentDate();
			String time=GenerateRandomNumber.getCurrentTime();
			
			amount2=request.get("amount");		
			
			EncoreAEPSRequest aepsreq=new EncoreAEPSRequest(date, time,request.get("pidData"), request.get("aadhar"), request.get("iin"),order_id, "25.6742129", "87.4733856", request.get("mobile"),amount2,deviceIMEI, aepsapiuser, apipass, aeps.getAgentcode());
		   // String 	input="{\"date\":\""+request.get("date")+"\",\"time\":\""+request.get("time")+"\",\"BiometricData\":\""+request.get("BiometricData")+"\",\"aadhar\":\""+request.get("aadhar")+"\",\"iin\":\""+request.get("iin")+"\",\"order_id\":\""+request.get("order_id")+"\",\"latitude\":\""+request.get("latitude")+"\",\"longitude\":\""+request.get("longitude")+"\",\"mobile\":\""+request.get("mobile")+"\",\"amount\":\""+request.get("amount")+"\",\"type\":\""+request.get("type")+"\",\"deviceIMEI\":\""+request.get("deviceIMEI")+"\",\"Username\":\""+aepsapiuser+"\",\"Password\":\""+apipass+"\",\"AID\":\""+aeps.getAgentcode()+"\"}";
		    String response=FingerPayWebservice.sendRequestToWebservice(aepsreq,"aadharpay");
		//	String response="{\"EncoreAEPSResponse\":{\"status\":true,\"statusCode\":10000,\"message\":\"Request Completed\",\"terminalId\":\"FA466151\",\"date\":\"2020-05-28\",\"time\":\"14:20:31\",\"transactionStatus\":\"successful\",\"bankRRN\":\"014914025001\",\"transactionType\":\"BALANCEINFO\",\"merchantTxnId\":\"44798575491714\",\"transactionAmount\":\"0.0\",\"balanceAmount\":\"497.98\",\"encoreTransactionId\":\"BEBE0750290280520142032738I\"}} ";
		//  String response="{\"EncoreAEPSResponse\":{\"status\":false,\"statusCode\":10004,\"message\":\"Customer  Aadhaar number is not linked with Selected Bank. Please check with the bank or select another Aadhaar linked bank\",\"terminalId\":\"FA466151\",\"date\":\"2020-07-13\",\"time\":\"01:05:59 PM\",\"transactionStatus\":\"failed\",\"bankRRN\":\"019513481472\",\"transactionType\":\"BALANCEINFO\",\"merchantTxnId\":\"NA\",\"transactionAmount\":\"0.0\",\"balanceAmount\":\"0.0\",\"encoreTransactionId\":\"BEBT0750290130720130600276I\"}}";
		    EncoreAadharPayResponse encore=FingerPayWebServiceParser.getFinPayAepsParser2(response);
		    JSONObject jobj=new JSONObject(response);
		    JSONObject encoreobj=jobj.getJSONObject("EncoreAadharPayResponse");
		    FingerPayAEPSResponse fing=new FingerPayAEPSResponse(encore.getTerminalId(),date,time,encore.getBankRRN() ,encore.getTransactionType(),encore.getEncoreTransactionId(),encore.getMerchantTxnId(),Double.parseDouble(encore.getTransactionAmount()),Double.parseDouble(encore.getBalanceAmount()),encore.getTransactionStatus(), user.getUserName());	
		    flag=fingerpayaepsresponseDao.insertFingerPayAEPSResponse(fing);
		    if(encoreobj.getInt("statusCode")==10000) {
		    
		    if(flag){
				/* if(fing.getTransactionType().equalsIgnoreCase("WITHDRAWAL")){ */
				amount=fing.getTransactionAmount();	
				settlementamount=amount;
				param = new HashMap<String, Object>();
				param.put("api","AadharPay");
				
				List<AEPSCommission> aepscommlist = aepscommissiondao.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi",param);
				for(AEPSCommission comm2 : aepscommlist){
					if(settlementamount>=comm2.getSlab1() && settlementamount<=comm2.getSlab2()){
						id = comm2.getId();
						break;
					}
				}
				param = new HashMap<String, Object>();
				
				param.put("username",user.getUserName());
				List<SettlementBalance> settlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
				if(settlelist.isEmpty()){
					SettlementBalance SettlementBalance = new SettlementBalance(username,settlementamount);
					settlementbalancedao.insertSettlementBalance(SettlementBalance);	
					settle2 = new SettlementReport(user.getUserName(),0.0,settlementamount, settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
				}else{
					Map<String, String>	parameter = new HashMap<String, String>();
					parameter.put("userId", user.getUserId());
					double settleopbal = customQueryServiceLogic
							.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForsettlement,parameter);
					SettlementBalance SettlementBalance = settlelist.get(0);
					settlementamountprev = SettlementBalance.getSettlementbalance();
					settlementamountnew = settleopbal+settlementamount;
					SettlementBalance.setSettlementbalance(settlementamountnew);
					settlementbalancedao.updateSettlementBalance(SettlementBalance);
					settle2 = new SettlementReport(user.getUserName(),settleopbal,settlementamountnew,settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
				}
				    settlementreportdao.insertSettlementReport(settle2);
				    param = new HashMap<String, Object>();
				if(user.getRoleId() == 5) {
					pckret = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"AadharPay",id) ;
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
				//	System.out.println("reseller=="+comm);
					double decomm=0;
					pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"AadharPay",id); 
					if(pckdis.getPackage_id()!=null){
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						decomm =(pckdis.getComm()*settlementamount)/100;
					}else{
						decomm=pckdis.getComm();	
					}
					dComm=decomm-comm;
					}
				//	System.out.println("dComm=="+dComm);
					double sdecomm=0;
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"AadharPay",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdecomm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdecomm = pcksd.getComm();	
					}
					sdComm=sdecomm-decomm;
					}
					
				
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"AadharPay",id);
						if(pckres.getPackage_id()!=null){
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						resComm=resComm-sdecomm;
						}
						commissionService.updateBalance(resellerId, "AadharPay Commission For Amount - "+settlementamount, "Commission", resComm/1.05, "CREDIT",0);
						TdsReport td1=new TdsReport(resellerId, resComm, resComm-(resComm/1.05), "AadharPay Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						TdsReportDao.insertTdsReport(td1);
						}
					
				//	System.out.println("resComm=="+resComm);
					commissionService.updateBalance(username, "AadharPay Commission For Amount - "+settlementamount, "Commission", comm/1.05, "CREDIT",0);
					TdsReport td=new TdsReport(username, comm, comm-(comm/1.05), "AadharPay Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td);
					commissionService.updateBalance(distId, "AadharPay Commission For Amount - "+settlementamount, "Commission", dComm/1.05, "CREDIT",0);
					TdsReport td1=new TdsReport(distId, dComm, dComm-(dComm/1.05), "AadharPay Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td1);
					commissionService.updateBalance(sdId, "AadharPay Commission For Amount - "+settlementamount, "Commission", sdComm/1.05, "CREDIT",0);
					TdsReport td2=new TdsReport(sdId, sdComm, sdComm-(sdComm/1.05), "AadharPay Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td2);
					
				}else if(user.getRoleId() == 4) {

					//distId = userDetails.getUserId();	
					sdId = user.getUplineId();	
					
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					pckdis=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"AadharPay",id); 
					if(pckdis.getPackage_id()!=null){
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*settlementamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
					}
					
					comm = dComm;
					
					double sdecomm=0;
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"AadharPay",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdecomm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdecomm = pcksd.getComm();	
					}
					sdComm=sdecomm-dComm;
					}
					
					//System.out.println("sdComm="+sdComm);
					if(!resellerId.equals("admin")) {
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"AadharPay",id);
					if(pckres.getPackage_id()!=null){
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*settlementamount)/100;
					}else{
					resComm =pckres.getComm();	
					}
					resComm=resComm-sdecomm;
					}
					commissionService.updateBalance(resellerId, "AadharPay Commission For Amount - "+settlementamount, "Commission", resComm/1.05, "CREDIT",0);
					TdsReport td1=new TdsReport(resellerId, resComm, resComm-(resComm/1.05), "AadharPay Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td1);
					}
					//System.out.println("resComm="+resComm);
					
					commissionService.updateBalance(username, "AadharPay Commission For Amount - "+settlementamount, "Commission", comm/1.05, "CREDIT",0);
					TdsReport td=new TdsReport(username, comm, comm-(comm/1.05), "AadharPay Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td);
					commissionService.updateBalance(sdId, "AadharPay Commission For Amount - "+settlementamount, "Commission", sdComm/1.05, "CREDIT",0);
					TdsReport td1=new TdsReport(sdId, sdComm, sdComm-(sdComm/1.05), "AadharPay Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td1);
					
				}else if(user.getRoleId() == 3) {

					resellerId = user.getUplineId();
					pcksd=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"AadharPay",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				     sdComm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdComm = pcksd.getComm();	
					}
					comm=sdComm;
					}
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"AadharPay",id);
						if(pckres.getPackage_id()!=null){
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						resComm=resComm-sdComm;
						}
						commissionService.updateBalance(resellerId, "AadharPay Commission For Amount - "+settlementamount, "Commission", resComm/1.05, "CREDIT",0);
						TdsReport td1=new TdsReport(resellerId, resComm, resComm-(resComm/1.05), "AadharPay Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						TdsReportDao.insertTdsReport(td1);
						}
					commissionService.updateBalance(username, "AadharPay Commission For Amount - "+settlementamount, "Commission", comm/1.05, "CREDIT",0);
					TdsReport td=new TdsReport(username, comm, comm-(comm/1.05), "AadharPay Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td);
				
				}
			
					
				
			/*	}*/
				 returnData.put("status","1"); 
				 returnData.put("message","SUCCESS");
				 returnData.put("EncoreAEPSResponse",encore);
		    }
		   
		    
		    }else {
		    	 returnData.put("status","0"); 
		    	 returnData.put("message",encore.getMessage());
		    	 returnData.put("nextPage","aepsfing"); 
		//    FingerPayAEPSResponse fing=new FingerPayAEPSResponse(encore.getTerminalId(),date,time,encore.getBankRRN() ,encore.getTransactionType(),encore.getEncoreTransactionId(),encore.getMerchantTxnId(),Double.parseDouble(encore.getTransactionAmount()),Double.parseDouble(encore.getBalanceAmount()),encore.getTransactionStatus(), user.getUserName());	
		    }
		   
		    
			}else {
				returnData.put("status","0");
			    returnData.put("message","You are not active agent");	
			}
		}catch (Exception e) {
			logger_log.error("encorepayaeps::::::::::::::::::::::::::"+e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> encorepayaadhar(Map<String, String> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		boolean flag=false;
		SettlementReport settle2=null;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double settlementamount = 0.0;
		double settlementamountprev = 0.0;
		double settlementamountnew = 0.0;
		String username = user.getUserName();
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		double amount =0.0;
		int id=0;
		try {
			if(user.getStatus().equals("1")){
				returnData.put("status", "0");
				returnData.put("message", "YOur ID is Inactive");
			}else{
			param = new HashMap<String, Object>();
			param.put("username",user.getUserName());
			param.put("api","Encore");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			if(!list2.isEmpty()) {
			AEPSUserMap aeps=list2.get(0);	
			EncoreAEPSRequest aepsreq=new EncoreAEPSRequest(request.get("date"), request.get("time"), request.get("BiometricData"), request.get("aadhar"), request.get("iin"), request.get("order_id"), request.get("latitude"), request.get("longitude"), request.get("mobile"), request.get("amount"), request.get("deviceIMEI"), aepsapiuser, apipass, aeps.getAgentcode());
		   // String 	input="{\"date\":\""+request.get("date")+"\",\"time\":\""+request.get("time")+"\",\"BiometricData\":\""+request.get("BiometricData")+"\",\"aadhar\":\""+request.get("aadhar")+"\",\"iin\":\""+request.get("iin")+"\",\"order_id\":\""+request.get("order_id")+"\",\"latitude\":\""+request.get("latitude")+"\",\"longitude\":\""+request.get("longitude")+"\",\"mobile\":\""+request.get("mobile")+"\",\"amount\":\""+request.get("amount")+"\",\"type\":\""+request.get("type")+"\",\"deviceIMEI\":\""+request.get("deviceIMEI")+"\",\"Username\":\""+aepsapiuser+"\",\"Password\":\""+apipass+"\",\"AID\":\""+aeps.getAgentcode()+"\"}";
		   String response=FingerPayWebservice.sendRequestToWebservice(aepsreq,"aadharpay");
		 //  String response="{\"EncoreAEPSResponse\":{\"status\":true,\"statusCode\":10000,\"message\":\"Request Completed\",\"terminalId\":\"FA466151\",\"date\":\"2020-05-28\",\"time\":\"14:20:31\",\"transactionStatus\":\"successful\",\"bankRRN\":\"014914025001\",\"transactionType\":\"BALANCEINFO\",\"merchantTxnId\":\"44798575491714\",\"transactionAmount\":\"0.0\",\"balanceAmount\":\"497.98\",\"encoreTransactionId\":\"BEBE0750290280520142032738I\"}} ";
		   EncoreAadharPayResponse encore=FingerPayWebServiceParser.getFinPayAepsParser2(response);
		    JSONObject jobj=new JSONObject(response);
		    JSONObject encoreobj=jobj.getJSONObject("EncoreAadharPayResponse");
		    if(encoreobj.getInt("statusCode")==10000) {
		    FingerPayAEPSResponse fing=new FingerPayAEPSResponse(encore.getTerminalId(),encore.getDate(),encore.getTime(),encore.getBankRRN() ,encore.getTransactionType(),encore.getEncoreTransactionId(),encore.getMerchantTxnId(),Double.parseDouble(encore.getTransactionAmount()),Double.parseDouble(encore.getBalanceAmount()),encore.getTransactionStatus(), user.getUserName());	
		    flag=fingerpayaepsresponseDao.insertFingerPayAEPSResponse(fing);
		    if(flag){
		  
				amount=fing.getTransactionAmount();	
				settlementamount=amount;
				param = new HashMap<String, Object>();
				param.put("api","AadharPay");
				
				List<AEPSCommission> aepscommlist = aepscommissiondao.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi",param);
				for(AEPSCommission comm2 : aepscommlist){
					if(settlementamount>=comm2.getSlab1() && settlementamount<=comm2.getSlab2()){
						id = comm2.getId();
						break;
					}
				}
				
				param = new HashMap<String, Object>();
				param.put("username",user.getUserName());
				List<SettlementBalance> settlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
				if(settlelist.isEmpty()){
					SettlementBalance SettlementBalance = new SettlementBalance(username,settlementamount);
					settlementbalancedao.insertSettlementBalance(SettlementBalance);	
					settle2 = new SettlementReport(user.getUserName(),0.0,settlementamount, settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
				}else{
					Map<String, String>	parameter = new HashMap<String, String>();
					parameter.put("userId", user.getUserId());
					double settleopbal = customQueryServiceLogic
							.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForsettlement,parameter);
					SettlementBalance SettlementBalance = settlelist.get(0);
					settlementamountprev = SettlementBalance.getSettlementbalance();
					settlementamountnew = settleopbal+settlementamount;
					SettlementBalance.setSettlementbalance(settlementamountnew);
					settlementbalancedao.updateSettlementBalance(SettlementBalance);
					settle2 = new SettlementReport(user.getUserName(),settleopbal,settlementamountnew,settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
				}
				    settlementreportdao.insertSettlementReport(settle2);
				param = new HashMap<String, Object>();
				
				
				if(user.getRoleId() == 5) {
					pckret = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"AadharPay",id) ;
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
				//	System.out.println("reseller=="+comm);
					double decomm=0;
					pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"AadharPay",id); 
					if(pckdis.getPackage_id()!=null){
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						decomm =(pckdis.getComm()*settlementamount)/100;
					}else{
						decomm=pckdis.getComm();	
					}
					dComm=decomm-comm;
					}
				//	System.out.println("dComm=="+dComm);
					double sdecomm=0;
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"AadharPay",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdecomm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdecomm = pcksd.getComm();	
					}
					sdComm=sdecomm-decomm;
					}
					
				
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"AadharPay",id);
						if(pckres.getPackage_id()!=null){
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						resComm=resComm-sdecomm;
						}
						commissionService.updateBalance(resellerId, "AadharPay Commission For Amount - "+settlementamount, "Commission", resComm/1.05, "CREDIT",0);
						TdsReport td1=new TdsReport(resellerId, resComm, resComm-(resComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						TdsReportDao.insertTdsReport(td1);
						}
					
				//	System.out.println("resComm=="+resComm);
					commissionService.updateBalance(username, "AadharPay Commission For Amount - "+settlementamount, "Commission", comm/1.05, "CREDIT",0);
					TdsReport td=new TdsReport(username, comm, comm-(comm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td);
					commissionService.updateBalance(distId, "AadharPay Commission For Amount - "+settlementamount, "Commission", dComm/1.05, "CREDIT",0);
					TdsReport td1=new TdsReport(distId, dComm, dComm-(dComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td1);
					commissionService.updateBalance(sdId, "AadharPay Commission For Amount - "+settlementamount, "Commission", sdComm/1.05, "CREDIT",0);
					TdsReport td2=new TdsReport(sdId, sdComm, sdComm-(sdComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td2);
					
				}else if(user.getRoleId() == 4) {

					//distId = userDetails.getUserId();	
					sdId = user.getUplineId();	
					
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					pckdis=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"AadharPay",id); 
					if(pckdis.getPackage_id()!=null){
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*settlementamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
					}
					
					comm = dComm;
					
					double sdecomm=0;
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"AadharPay",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdecomm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdecomm = pcksd.getComm();	
					}
					sdComm=sdecomm-dComm;
					}
					
					//System.out.println("sdComm="+sdComm);
					if(!resellerId.equals("admin")) {
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"AadharPay",id);
					if(pckres.getPackage_id()!=null){
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*settlementamount)/100;
					}else{
					resComm =pckres.getComm();	
					}
					resComm=resComm-sdecomm;
					}
					commissionService.updateBalance(resellerId, "AadharPay Commission For Amount - "+settlementamount, "Commission", resComm/1.05, "CREDIT",0);
					TdsReport td1=new TdsReport(resellerId, resComm, resComm-(resComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td1);
					}
					//System.out.println("resComm="+resComm);
					
					commissionService.updateBalance(username, "AadharPay Commission For Amount - "+settlementamount, "Commission", comm/1.05, "CREDIT",0);
					TdsReport td=new TdsReport(username, comm, comm-(comm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td);
					commissionService.updateBalance(sdId, "AadharPay Commission For Amount - "+settlementamount, "Commission", sdComm/1.05, "CREDIT",0);
					TdsReport td1=new TdsReport(sdId, sdComm, sdComm-(sdComm/1.05), "AadharPay Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td1);
					
				}else if(user.getRoleId() == 3) {

					resellerId = user.getUplineId();
					pcksd=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"AadharPay",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				     sdComm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdComm = pcksd.getComm();	
					}
					comm=sdComm;
					}
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"AadharPay",id);
						if(pckres.getPackage_id()!=null){
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						resComm=resComm-sdComm;
						}
						commissionService.updateBalance(resellerId, "AadharPay Commission For Amount - "+settlementamount, "Commission", resComm/1.05, "CREDIT",0);
						TdsReport td1=new TdsReport(resellerId, resComm, resComm-(resComm/1.05), "AEPS Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						TdsReportDao.insertTdsReport(td1);
						}
					commissionService.updateBalance(username, "AadharPay Commission For Amount - "+settlementamount, "Commission", comm/1.05, "CREDIT",0);
					TdsReport td=new TdsReport(username, comm, comm-(comm/1.05), "AadharPay Commission For Amount - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
					TdsReportDao.insertTdsReport(td);
				
				}
				
				
				
		    }
		   
		    
		    }
		    returnData.put("EncoreAEPSResponse",encore);
		    
			}else {
				returnData.put("status","0");
			    returnData.put("message","You are not active agent");	
			}
			}
		}catch (Exception e) {
			logger_log.error("encorepayaeps::::::::::::::::::::::::::"+e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getICICIBankAEPSReport(Map<String, String> request, User userDetail) {
		Map<String, Object> returndata = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try{
			if(userDetail.getRoleId()>2){
				//System.out.println("HI:::::::::::::::::::::"+userDetail.getUserName());
				/*
				 * param.put("username", userDetail.getUserName()); param.put("api","YesBank" );
				 * List<AEPSUserMap> aepsuserlist =
				 * aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
				 * if(!aepsuserlist.isEmpty()){
				 */
				
					param = new HashMap<String, Object>();
					param.put("username",userDetail.getUserName());
					param.put("start_date",request.get("startDate"));
					param.put("end_date",request.get("endDate"));
					List<FingerPayAEPSResponse> rblreport =fingerpayaepsresponseDao.getFingerPayAEPSResponseByNamedQuery("getFingerPayReportByusername",param);
					if(!rblreport.isEmpty()){
						returndata.put("status","1");
						returndata.put("fingeraepsReport",rblreport);	
					}else{
						returndata.put("status", "0");
						returndata.put("message", "No data available");		
					}
					
				/*}else{
					returndata.put("status", "0");
					returndata.put("message", "You are not YesBank REGISTERED USER");	
				}*/
			}else if(userDetail.getRoleId()==1){
				parameters.put("start_date",request.get("startDate"));
				parameters.put("end_date",request.get("endDate"));
				List<FingerPayAEPSResponse> rblreport = customQueryServiceLogic.getFingerAepsReport(CustomSqlQuery.getFingerAepsReportAll,parameters);
				
				//List<PaymonkResponse> rblreport = paymonkresponsedao.getPaymonkResponseByNamedQuery("getpaymonkresponsebyadmin",param);
				if(!rblreport.isEmpty()){
					returndata.put("status", "1");
					returndata.put("fingeraepsReport",rblreport);	
				}else{
					returndata.put("status", "0");
					returndata.put("message", "No data available");		
				}	
			}else if(userDetail.getRoleId()==2){
				parameters.put("wl_id",userDetail.getWlId());
				parameters.put("start_date",request.get("startDate"));
				parameters.put("end_date",request.get("endDate"));
				List<FingerPayAEPSResponse> rblreport = customQueryServiceLogic.getFingerAepsReport(CustomSqlQuery.getFingerAepsReportAllwl,parameters);
				if(!rblreport.isEmpty()){
					returndata.put("status", "1");
					returndata.put("fingeraepsReport",rblreport);	
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
	public Map<String, Object> ministatement(Map<String, String> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
	
		PackageWiseChargeComm pcksubret=new PackageWiseChargeComm();
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double settlementamount = 0.0;
		
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		double amount =0.0;
		String amount2="";
		int id=0;
	//	Location
		try {
			DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			Date date3 = Calendar.getInstance().getTime();
			String today3 = formatter2.format(date3);
			param = new HashMap<String, Object>();
			param.put("username",user.getUserName());
			param.put("api","Softmint");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			if(!list2.isEmpty()) {
           /* param = new HashMap<String, Object>();
			param.put("serviceProvider","Ministatement");
			List<Operator> opList =  operatorDao.getOperatorByNamedQuery("getOperatorByName", param );	
			if(!opList.isEmpty()) {
			Operator op=opList.get(0);	
			id=op.getOperatorId();
			param = new HashMap<String, Object>(); param.put("user_id",
			user.getUserId()); param.put("service_type", "Ministatement"); 
			List<AssignedPackage> list = assignedPackage.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", param);*/
		//	if(!list.isEmpty()) {	 
			AEPSUserMap aeps=list2.get(0);	
			String deviceIMEI=FingerCaptureParser.captureResponse(request.get("pidData"));
			String order_id=GenerateRandomNumber.generatePtid("mobile");
			String date=GenerateRandomNumber.getCurrentDate();
			String time=GenerateRandomNumber.getCurrentTime();
			
			String bankdetails[]= request.get("iin").split(",");
			String aadhar="********"+request.get("aadhar").substring(request.get("aadhar").length()-4, request.get("aadhar").length());
			EncoreAEPSRequest aepsreq=new EncoreAEPSRequest(date, time,request.get("pidData"), request.get("aadhar"), bankdetails[0],order_id, "25.6742129", "87.4733856", request.get("mobile"),deviceIMEI, aepsapiuser, apipass, aeps.getAgentcode());
		   // String 	input="{\"date\":\""+request.get("date")+"\",\"time\":\""+request.get("time")+"\",\"BiometricData\":\""+request.get("BiometricData")+"\",\"aadhar\":\""+request.get("aadhar")+"\",\"iin\":\""+request.get("iin")+"\",\"order_id\":\""+request.get("order_id")+"\",\"latitude\":\""+request.get("latitude")+"\",\"longitude\":\""+request.get("longitude")+"\",\"mobile\":\""+request.get("mobile")+"\",\"amount\":\""+request.get("amount")+"\",\"type\":\""+request.get("type")+"\",\"deviceIMEI\":\""+request.get("deviceIMEI")+"\",\"Username\":\""+aepsapiuser+"\",\"Password\":\""+apipass+"\",\"AID\":\""+aeps.getAgentcode()+"\"}";
	   String response=FingerPayWebservice.sendRequestToWebservice(aepsreq,"ministatementApi");
	//	String response="{\"MinistatementResponse\":{\"status\":true,\"statusCode\":10000,\"message\":\"SUCCESS\",\"terminalId\":\"FA634270\",\"transactionStatus\":\"SUCCESS\",\"balanceAmount\":1000,\"bankRRN\":\"023411217081\",\"merchantTxnId\":\"ile26647058\",\"transactionId\":\"MSBC1091684210820113654754I\",\"transactionTime\":\"21/08/2020 11:36:54\",\"ministatement\":[{\"date\":\"31/12/2019\",\"txnType\":\"Cr\",\"amount\":\"1.00\",\"narration\":\" INF/INFT/021841\"},{\"date\":\"31/12/2019\",\"txnType\":\"Cr\",\"amount\":\"1.00\",\"narration\":\" INF/INFT/021841\"}]}}";
		//String response="{\"EncoreAEPSResponse\":{\"status\":true,\"statusCode\":10000,\"message\":\"Request Completed\",\"terminalId\":\"FA516102\",\"date\":\"2020-07-27\",\"time\":\"08:20:06\",\"transactionStatus\":\"successful\",\"bankRRN\":\"020908820440\",\"transactionType\":\"WITHDRAWAL\",\"merchantTxnId\":\"19047515367872\",\"transactionAmount\":\"2500.0\",\"balanceAmount\":\"487.58\",\"responseCode\":\"00\",\"mobile\":\"7667173950\",\"aadhar\":\"949542903455\",\"iin\":\"508505\",\"encoreTransactionId\":\"CWBT1029275270720082007739I\"}}";
		//	String response="{\"EncoreAEPSResponse\":{\"status\":true,\"statusCode\":10000,\"message\":\"Request Completed\",\"terminalId\":\"FA466151\",\"date\":\"2020-05-28\",\"time\":\"14:20:31\",\"transactionStatus\":\"successful\",\"bankRRN\":\"014914025001\",\"transactionType\":\"BALANCEINFO\",\"merchantTxnId\":\"44798575491714\",\"transactionAmount\":\"0.0\",\"balanceAmount\":\"497.98\",\"encoreTransactionId\":\"BEBE0750290280520142032738I\"}} ";
		//  String response="{\"EncoreAEPSResponse\":{\"status\":false,\"statusCode\":10004,\"message\":\"Customer  Aadhaar number is not linked with Selected Bank. Please check with the bank or select another Aadhaar linked bank\",\"terminalId\":\"FA466151\",\"date\":\"2020-07-13\",\"time\":\"01:05:59 PM\",\"transactionStatus\":\"failed\",\"bankRRN\":\"019513481472\",\"transactionType\":\"BALANCEINFO\",\"merchantTxnId\":\"NA\",\"transactionAmount\":\"0.0\",\"balanceAmount\":\"0.0\",\"encoreTransactionId\":\"BEBT0750290130720130600276I\"}}";
		 MinistatementResponse encore=FingerPayWebServiceParser.getMinistatementParser(response);
		 encore.setAadhar(aadhar);
		 encore.setBank(bankdetails[1]);
		   
		   
		    
		    if(encore.getStatusCode()==10000) {
				
				
				
				
				param = new HashMap<String, Object>();
				if(user.getRoleId() == 6) {
					double subrComm=0.0;
					double rComm=0.0;
					double disComm=0.0;
					double sudComm=0.0;
					//Retailer Id
					String rId=user.getUplineId();
					// Distributor Id
					parameters  = new HashMap<String, String>();
					parameters.put("userId", rId);	
					distId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					// Super Distributor Id
					parameters  = new HashMap<String, String>();
					parameters.put("userId", distId);	
					sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
					
					// Reseller Id
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					pcksubret =commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Ministatement",id); 
					if(pcksubret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						subrComm =(pcksubret.getComm()*settlementamount)/100;
						}else{
							subrComm=pcksubret.getComm();	
						}
					comm = subrComm;
					if(comm!=0){
							commissionService.updateBalance( user.getUserId(), "COMMISSION for Ministatement - "+settlementamount, "COMMISSION", comm, "CREDIT",0);
							EarningSurcharge earningSurcharge1 = new EarningSurcharge(user.getRoleId(), user.getWlId(),  user.getUserId(), comm, 0.0, "AEPS Commission - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
							earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
						}
					
					pckret =commissionService.getPackagewiseCommisionOnOperator(rId,"Ministatement",id); 
					if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						retComm =(pckret.getComm()*settlementamount)/100;
						}else{
							retComm=pckret.getComm();	
						}
					rComm=retComm-comm;
					if(rComm!=0){
						//	commissionService.updateBalance( userDetails.getUplineId(), "COMMISSION for Recharge - "+operator.getServiceProvider(), "COMMISSION", retComm, "CREDIT");
							EarningSurcharge earningSurcharge1 = new EarningSurcharge(5, user.getWlId(), rId, rComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
							earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
						}
						pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Ministatement",id); 
						if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							disComm =(pckdis.getComm()*settlementamount)/100;
						}else{
							disComm=pckdis.getComm();	
						}
						dComm=disComm-retComm;
						if(dComm!=0){
							//	commissionService.updateBalance( userDetails.getUplineId(), "COMMISSION for Recharge - "+operator.getServiceProvider(), "COMMISSION", retComm, "CREDIT");
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, user.getWlId(),  distId, dComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Ministatement",id);
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sudComm =(pcksd.getComm()*settlementamount)/100;
						}else{
							sudComm = pcksd.getComm();	
						}
						sdComm =sudComm-disComm;
						if(sdComm!=0){
							//	commissionService.updateBalance( userDetails.getUplineId(), "COMMISSION for Recharge - "+operator.getServiceProvider(), "COMMISSION", retComm, "CREDIT");
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(3, user.getWlId(),  sdId, sdComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
					if(!resellerId.equalsIgnoreCase("admin")){
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Ministatement",id);
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						if(user.getWlId().startsWith("ASR")){
							if(resComm!=0){
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, user.getWlId(),  resellerId, resComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						}else{
							resComm=resComm-sudComm;
							if(resComm!=0){
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, user.getWlId(),  resellerId, resComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						}
						//commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT");
				}
					
				} else if(user.getRoleId() == 5) {
					pckret = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Ministatement",id) ;
					
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
					if(comm!=0){
						commissionService.updateBalance( user.getUserId(), "COMMISSION for AEPS - "+settlementamount, "COMMISSION", comm, "CREDIT",0);
						EarningSurcharge earningSurcharge1 = new EarningSurcharge(user.getRoleId(), user.getWlId(),  user.getUserId(), comm, 0.0, "Ministatement Commission - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
					}
					double decomm=0;
					pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Ministatement",id); 
					if(pckdis.getPackage_id()!=null){
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						decomm =(pckdis.getComm()*settlementamount)/100;
					}else{
						decomm=pckdis.getComm();	
					}
					dComm=decomm-comm;
					}
					if(dComm!=0){
						EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, user.getWlId(),  distId, dComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
					}
					double sdecomm=0;
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Ministatement",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdecomm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdecomm = pcksd.getComm();	
					}
					sdComm=sdecomm-decomm;
					}
					if(sdComm!=0){
							EarningSurcharge earningSurcharge1 = new EarningSurcharge(3, user.getWlId(),  sdId, sdComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
							earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
						}
				
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Ministatement",id);
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						if(user.getWlId().startsWith("ASR")){
							if(resComm!=0){
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, user.getWlId(),  resellerId, resComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						}else{
							resComm=resComm-sdecomm;
							if(resComm!=0){
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, user.getWlId(),  resellerId, resComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						}
						//commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT");
				}
					
					
				}else if(user.getRoleId() == 4) {

					//distId = userDetails.getUserId();	
					sdId = user.getUplineId();	
					
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					pckdis=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Ministatement",id); 
					if(pckdis.getPackage_id()!=null){
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*settlementamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
					}
					
					comm = dComm;
					if(comm!=0){
						commissionService.updateBalance( user.getUserId(), "COMMISSION for Ministatement - "+settlementamount, "COMMISSION", comm, "CREDIT",0);
						EarningSurcharge earningSurcharge1 = new EarningSurcharge(user.getRoleId(), user.getWlId(),  user.getUserId(), comm, 0.0, "Ministatement Commission - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
					}
					
					double sdecomm=0;
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Ministatement",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdecomm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdecomm = pcksd.getComm();	
					}
					sdComm=sdecomm-dComm;
					}
					if(sdComm!=0){
						EarningSurcharge earningSurcharge1 = new EarningSurcharge(3, user.getWlId(),  sdId, sdComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
					}
					//System.out.println("sdComm="+sdComm);
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Ministatement",id);
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						if(user.getWlId().startsWith("ASR")){
							if(resComm!=0){
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, user.getWlId(),  resellerId, resComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						}else{
							resComm=resComm-sdecomm;
							if(resComm!=0){
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, user.getWlId(),  resellerId, resComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						}
						//commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT");
				}
				
				}else if(user.getRoleId() == 3) {

					resellerId = user.getUplineId();
					pcksd=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Ministatement",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				     sdComm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdComm = pcksd.getComm();	
					}
					comm=sdComm;
					}
					if(comm!=0){
						commissionService.updateBalance( user.getUserId(), "COMMISSION for Ministatement - "+settlementamount, "COMMISSION", comm, "CREDIT",0);
						EarningSurcharge earningSurcharge1 = new EarningSurcharge(user.getRoleId(), user.getWlId(),  user.getUserId(), comm, 0.0, "Ministatement Commission - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
					}
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Ministatement",id);
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						if(user.getWlId().startsWith("ASR")){
							if(resComm!=0){
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, user.getWlId(),  resellerId, resComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						}else{
							resComm=resComm-comm;
							if(resComm!=0){
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, user.getWlId(),  resellerId, resComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						}
						//commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT");
				}
				
				}
			
				
				
			
				 returnData.put("status","1"); 
				 returnData.put("message","SUCCESS");
				 returnData.put("MinistatementResponse",encore);
				 returnData.put("AgentId",aeps.getAgentcode());
		  
		   
		    
		    }else {
		    	 returnData.put("status","0"); 
		    	 returnData.put("message",encore.getMessage());
		    	 returnData.put("nextPage","ministatement"); 
		//    FingerPayAEPSResponse fing=new FingerPayAEPSResponse(encore.getTerminalId(),date,time,encore.getBankRRN() ,encore.getTransactionType(),encore.getEncoreTransactionId(),encore.getMerchantTxnId(),Double.parseDouble(encore.getTransactionAmount()),Double.parseDouble(encore.getBalanceAmount()),encore.getTransactionStatus(), user.getUserName());	
		    }
			/*}else {

				returnData.put("status", "0");
				returnData.put("message", "Package is not assigned");
			    returnData.put("nextPage","ministatement"); 
				
			}*/
			/*
			 * }else { returnData.put("status", "0"); returnData.put("message",
			 * "Operator is not available"); returnData.put("nextPage","ministatement"); }
			 */
				
		    
			}else {
				returnData.put("status","0");
			    returnData.put("message","You are not active agent");	
			}
		}catch (Exception e) {
			logger_log.error("encorepayaeps::::::::::::::::::::::::::"+e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> ministatementandroid(Map<String, String> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
	
		PackageWiseChargeComm pcksubret=new PackageWiseChargeComm();
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		double settlementamount = 0.0;
		
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";
		int id=0;
	//	Location
		try {
			
			param = new HashMap<String, Object>();
			param.put("username",user.getUserName());
			param.put("api","Softmint");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			if(!list2.isEmpty()) {
           /* param = new HashMap<String, Object>();
			param.put("serviceProvider","Ministatement");
			List<Operator> opList =  operatorDao.getOperatorByNamedQuery("getOperatorByName", param );	
			if(!opList.isEmpty()) {
			Operator op=opList.get(0);	
			id=op.getOperatorId();
			param = new HashMap<String, Object>(); param.put("user_id",
			user.getUserId()); param.put("service_type", "Ministatement"); 
			List<AssignedPackage> list = assignedPackage.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", param);
			if(!list.isEmpty()) {	 */
			AEPSUserMap aeps=list2.get(0);	
			String deviceIMEI=request.get("deviceIMEI");
			String order_id=GenerateRandomNumber.generatePtid("mobile");
			String date=GenerateRandomNumber.getCurrentDate();
			String time=GenerateRandomNumber.getCurrentTime();
			
			String bankdetails[]= request.get("iin").split(",");
			String aadhar="********"+request.get("aadhar").substring(request.get("aadhar").length()-4, request.get("aadhar").length());
			EncoreAEPSRequest aepsreq=new EncoreAEPSRequest(date, time,request.get("pidData"), request.get("aadhar"), bankdetails[0],order_id, "25.6742129", "87.4733856", request.get("mobile"),deviceIMEI, aepsapiuser, apipass, aeps.getAgentcode());
		   // String 	input="{\"date\":\""+request.get("date")+"\",\"time\":\""+request.get("time")+"\",\"BiometricData\":\""+request.get("BiometricData")+"\",\"aadhar\":\""+request.get("aadhar")+"\",\"iin\":\""+request.get("iin")+"\",\"order_id\":\""+request.get("order_id")+"\",\"latitude\":\""+request.get("latitude")+"\",\"longitude\":\""+request.get("longitude")+"\",\"mobile\":\""+request.get("mobile")+"\",\"amount\":\""+request.get("amount")+"\",\"type\":\""+request.get("type")+"\",\"deviceIMEI\":\""+request.get("deviceIMEI")+"\",\"Username\":\""+aepsapiuser+"\",\"Password\":\""+apipass+"\",\"AID\":\""+aeps.getAgentcode()+"\"}";
		 String response=FingerPayWebservice.sendRequestToWebservice(aepsreq,"ministatementApi");
	//	String response="{\"EncoreAadharPayResponse\":{\"status\":true,\"statusCode\":10000,\"message\":\"Request Completed\",\"terminalId\":\"FA000002\",\"date\":\"2020-08-26\",\"time\":\"10:40:28 AM\",\"transactionStatus\":\"successful\",\"bankRRN\":\"023910075030\",\"transactionType\":\"WITHDRAWAL\",\"merchantTxnId\":\"ile974099879\",\"transactionAmount\":\"1000.0\",\"balanceAmount\":\"53870.99\",\"responseCode\":\"00\",\"mobile\":\"7003528783\",\"aadhar\":\"869457399348\",\"iin\":\"607095\",\"encoreTransactionId\":\"MBS1081764260820104029116I\"}}";
		//String response="{\"EncoreAEPSResponse\":{\"status\":true,\"statusCode\":10000,\"message\":\"Request Completed\",\"terminalId\":\"FA516102\",\"date\":\"2020-07-27\",\"time\":\"08:20:06\",\"transactionStatus\":\"successful\",\"bankRRN\":\"020908820440\",\"transactionType\":\"WITHDRAWAL\",\"merchantTxnId\":\"19047515367872\",\"transactionAmount\":\"2500.0\",\"balanceAmount\":\"487.58\",\"responseCode\":\"00\",\"mobile\":\"7667173950\",\"aadhar\":\"949542903455\",\"iin\":\"508505\",\"encoreTransactionId\":\"CWBT1029275270720082007739I\"}}";
		//	String response="{\"EncoreAEPSResponse\":{\"status\":true,\"statusCode\":10000,\"message\":\"Request Completed\",\"terminalId\":\"FA466151\",\"date\":\"2020-05-28\",\"time\":\"14:20:31\",\"transactionStatus\":\"successful\",\"bankRRN\":\"014914025001\",\"transactionType\":\"BALANCEINFO\",\"merchantTxnId\":\"44798575491714\",\"transactionAmount\":\"0.0\",\"balanceAmount\":\"497.98\",\"encoreTransactionId\":\"BEBE0750290280520142032738I\"}} ";
		//  String response="{\"EncoreAEPSResponse\":{\"status\":false,\"statusCode\":10004,\"message\":\"Customer  Aadhaar number is not linked with Selected Bank. Please check with the bank or select another Aadhaar linked bank\",\"terminalId\":\"FA466151\",\"date\":\"2020-07-13\",\"time\":\"01:05:59 PM\",\"transactionStatus\":\"failed\",\"bankRRN\":\"019513481472\",\"transactionType\":\"BALANCEINFO\",\"merchantTxnId\":\"NA\",\"transactionAmount\":\"0.0\",\"balanceAmount\":\"0.0\",\"encoreTransactionId\":\"BEBT0750290130720130600276I\"}}";
		 MinistatementResponse encore=FingerPayWebServiceParser.getMinistatementParser(response);
		 encore.setAadhar(aadhar);
		 encore.setBank(bankdetails[1]);
		   
		   
		    
		    if(encore.getStatusCode()==10000) {
				
				
				
				
				param = new HashMap<String, Object>();
				if(user.getRoleId() == 6) {
					double subrComm=0.0;
					double rComm=0.0;
					double disComm=0.0;
					double sudComm=0.0;
					//Retailer Id
					String rId=user.getUplineId();
					// Distributor Id
					parameters  = new HashMap<String, String>();
					parameters.put("userId", rId);	
					distId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					// Super Distributor Id
					parameters  = new HashMap<String, String>();
					parameters.put("userId", distId);	
					sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
					
					// Reseller Id
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					pcksubret =commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Ministatement",id); 
					if(pcksubret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						subrComm =(pcksubret.getComm()*settlementamount)/100;
						}else{
							subrComm=pcksubret.getComm();	
						}
					comm = subrComm;
					if(comm!=0){
							commissionService.updateBalance( user.getUserId(), "COMMISSION for Ministatement - "+settlementamount, "COMMISSION", comm, "CREDIT",0);
							EarningSurcharge earningSurcharge1 = new EarningSurcharge(user.getRoleId(), user.getWlId(),  user.getUserId(), comm, 0.0, "AEPS Commission - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
							earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
						}
					
					pckret =commissionService.getPackagewiseCommisionOnOperator(rId,"Ministatement",id); 
					if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						retComm =(pckret.getComm()*settlementamount)/100;
						}else{
							retComm=pckret.getComm();	
						}
					rComm=retComm-comm;
					if(rComm!=0){
						//	commissionService.updateBalance( userDetails.getUplineId(), "COMMISSION for Recharge - "+operator.getServiceProvider(), "COMMISSION", retComm, "CREDIT");
							EarningSurcharge earningSurcharge1 = new EarningSurcharge(5, user.getWlId(), rId, rComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
							earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
						}
						pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Ministatement",id); 
						if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							disComm =(pckdis.getComm()*settlementamount)/100;
						}else{
							disComm=pckdis.getComm();	
						}
						dComm=disComm-retComm;
						if(dComm!=0){
							//	commissionService.updateBalance( userDetails.getUplineId(), "COMMISSION for Recharge - "+operator.getServiceProvider(), "COMMISSION", retComm, "CREDIT");
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, user.getWlId(),  distId, dComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Ministatement",id);
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							sudComm =(pcksd.getComm()*settlementamount)/100;
						}else{
							sudComm = pcksd.getComm();	
						}
						sdComm =sudComm-disComm;
						if(sdComm!=0){
							//	commissionService.updateBalance( userDetails.getUplineId(), "COMMISSION for Recharge - "+operator.getServiceProvider(), "COMMISSION", retComm, "CREDIT");
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(3, user.getWlId(),  sdId, sdComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
					if(!resellerId.equalsIgnoreCase("admin")){
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Ministatement",id);
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						if(user.getWlId().startsWith("ASR")){
							if(resComm!=0){
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, user.getWlId(),  resellerId, resComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						}else{
							resComm=resComm-sudComm;
							if(resComm!=0){
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, user.getWlId(),  resellerId, resComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						}
						//commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT");
				}
					
				} else if(user.getRoleId() == 5) {
					pckret = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Ministatement",id) ;
					
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
					if(comm!=0){
						commissionService.updateBalance( user.getUserId(), "COMMISSION for AEPS - "+settlementamount, "COMMISSION", comm, "CREDIT",0);
						EarningSurcharge earningSurcharge1 = new EarningSurcharge(user.getRoleId(), user.getWlId(),  user.getUserId(), comm, 0.0, "Ministatement Commission - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
					}
					double decomm=0;
					pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Ministatement",id); 
					if(pckdis.getPackage_id()!=null){
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						decomm =(pckdis.getComm()*settlementamount)/100;
					}else{
						decomm=pckdis.getComm();	
					}
					dComm=decomm-comm;
					}
					if(dComm!=0){
						EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, user.getWlId(),  distId, dComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
					}
					double sdecomm=0;
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Ministatement",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdecomm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdecomm = pcksd.getComm();	
					}
					sdComm=sdecomm-decomm;
					}
					if(sdComm!=0){
							EarningSurcharge earningSurcharge1 = new EarningSurcharge(3, user.getWlId(),  sdId, sdComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
							earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
						}
				
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Ministatement",id);
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						if(user.getWlId().startsWith("ASR")){
							if(resComm!=0){
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, user.getWlId(),  resellerId, resComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						}else{
							resComm=resComm-sdecomm;
							if(resComm!=0){
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, user.getWlId(),  resellerId, resComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						}
						//commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT");
				}
					
					
				}else if(user.getRoleId() == 4) {

					//distId = userDetails.getUserId();	
					sdId = user.getUplineId();	
					
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					pckdis=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Ministatement",id); 
					if(pckdis.getPackage_id()!=null){
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*settlementamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
					}
					
					comm = dComm;
					if(comm!=0){
						commissionService.updateBalance( user.getUserId(), "COMMISSION for Ministatement - "+settlementamount, "COMMISSION", comm, "CREDIT",0);
						EarningSurcharge earningSurcharge1 = new EarningSurcharge(user.getRoleId(), user.getWlId(),  user.getUserId(), comm, 0.0, "Ministatement Commission - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
					}
					
					double sdecomm=0;
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Ministatement",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdecomm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdecomm = pcksd.getComm();	
					}
					sdComm=sdecomm-dComm;
					}
					if(sdComm!=0){
						EarningSurcharge earningSurcharge1 = new EarningSurcharge(3, user.getWlId(),  sdId, sdComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
					}
					//System.out.println("sdComm="+sdComm);
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Ministatement",id);
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						if(user.getWlId().startsWith("ASR")){
							if(resComm!=0){
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, user.getWlId(),  resellerId, resComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						}else{
							resComm=resComm-sdecomm;
							if(resComm!=0){
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, user.getWlId(),  resellerId, resComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						}
						//commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT");
				}
				
				}else if(user.getRoleId() == 3) {

					resellerId = user.getUplineId();
					pcksd=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Ministatement",id);
					if(pcksd.getPackage_id()!=null){
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				     sdComm =(pcksd.getComm()*settlementamount)/100;
					}else{
						sdComm = pcksd.getComm();	
					}
					comm=sdComm;
					}
					if(comm!=0){
						commissionService.updateBalance( user.getUserId(), "COMMISSION for Ministatement - "+settlementamount, "COMMISSION", comm, "CREDIT",0);
						EarningSurcharge earningSurcharge1 = new EarningSurcharge(user.getRoleId(), user.getWlId(),  user.getUserId(), comm, 0.0, "Ministatement Commission - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
					}
					if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Ministatement",id);
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						if(user.getWlId().startsWith("ASR")){
							if(resComm!=0){
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, user.getWlId(),  resellerId, resComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						}else{
							resComm=resComm-comm;
							if(resComm!=0){
								EarningSurcharge earningSurcharge1 = new EarningSurcharge(2, user.getWlId(),  resellerId, resComm, 0.0, "Ministatement Commission FOR - "+settlementamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							}
						}
						//commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT");
				}
				
				}
			
				
				
			
			//	 returnData.put("status","1"); 
			//	 returnData.put("message","SUCCESS");
				 returnData.put("MinistatementResponse",encore);
				// returnData.put("AgentId",aeps.getAgentcode());
		  
		   
		    
		    }else {
		    	returnData.put("MinistatementResponse",encore);
		    	// returnData.put("status","0"); 
		    	// returnData.put("message",encore.getMessage());
		    	// returnData.put("nextPage","ministatement"); 
		//    FingerPayAEPSResponse fing=new FingerPayAEPSResponse(encore.getTerminalId(),date,time,encore.getBankRRN() ,encore.getTransactionType(),encore.getEncoreTransactionId(),encore.getMerchantTxnId(),Double.parseDouble(encore.getTransactionAmount()),Double.parseDouble(encore.getBalanceAmount()),encore.getTransactionStatus(), user.getUserName());	
		    }
			/*
			 * }else {
			 * 
			 * returnData.put("status", "0"); returnData.put("message",
			 * "Package is not assigned"); returnData.put("nextPage","ministatement");
			 * 
			 * } }else { returnData.put("status", "0"); returnData.put("message",
			 * "Operator is not available"); returnData.put("nextPage","ministatement"); }
			 */
				
		    
			}else {
				returnData.put("status","0");
			    returnData.put("message","You are not active agent");	
			}
		}catch (Exception e) {
			logger_log.error("encorepayaeps::::::::::::::::::::::::::"+e);
		}
		return returnData;
	}

}
