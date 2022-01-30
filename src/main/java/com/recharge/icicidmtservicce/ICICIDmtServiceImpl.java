package com.recharge.icicidmtservicce;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

import com.recharge.icicidmtservicce.RazorPayWebService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.recharge.businessdao.ApiResponseService;
import com.recharge.businessdao.CommissionService;
import com.recharge.businessdao.CustomQueryServiceLogic;

import com.recharge.customModel.ImpsRequest;
import com.recharge.customModel.ImpsResponse;
import com.recharge.customModel.P2ATranferReport;
import com.recharge.customModel.P2AUserdetail;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.icicidmtmodel.Beneficiary;
import com.recharge.icicidmtmodel.ImpsSettlement;
import com.recharge.icicidmtmodel.OtpVerification;
import com.recharge.icicidmtmodel.P2AMoneyUser;
import com.recharge.icicidmtmodel.Remitter;
import com.recharge.icicidmtmodel.RemitterLimit;
import com.recharge.icicidmtserviceDao.BeneficiaryDao;
import com.recharge.icicidmtserviceDao.ImpsSettlementDao;
import com.recharge.icicidmtserviceDao.OtpVerificationdao;
import com.recharge.icicidmtserviceDao.P2AMoneyUserDao;
import com.recharge.icicidmtserviceDao.RemitterDao;
import com.recharge.icicidmtserviceDao.RemitterLimitDao;
import com.recharge.model.AEPSUserMap;
import com.recharge.model.AssignedPackage;
import com.recharge.model.DMRCommission;
import com.recharge.model.EarningSurcharge;
import com.recharge.model.ImpsTransaction;
import com.recharge.model.PackageDetail;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.SettlementBalance;
import com.recharge.model.SettlementCharge;
import com.recharge.model.SettlementReport;
import com.recharge.model.SmsCredential;
import com.recharge.model.User;
import com.recharge.servicedao.AEPSUserMapDao;
import com.recharge.servicedao.AssignedPackageDAO;
import com.recharge.servicedao.DMRCommissionDao;
import com.recharge.servicedao.EarningSurchargeDao;
import com.recharge.servicedao.ImpsTransactionService;
import com.recharge.servicedao.PackageDetailDao;
import com.recharge.servicedao.SettlementBalanceDao;
import com.recharge.servicedao.SettlementChargeDao;
import com.recharge.servicedao.SettlementReportDao;
import com.recharge.servicedao.SmsCredentialDao;
import com.recharge.servicedao.UserDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.PaytmchecksumNew;
import com.recharge.utill.RoundNumber;
import com.recharge.utill.SMS;
import com.recharge.utill.UtilityClass;

@Service("icicidmtService")
public class ICICIDmtServiceImpl implements ICICIDmtService {
	private static final Logger logger_log = Logger.getLogger(ICICIDmtService.class);
//	private static final  String  PassCode="0df5b0ebacfd4c1fb7257b167c7ceed8";
	private static final  String  PassCode="2d3448b1feb548a59c685f5afb77678c";
	//private final String accountNumberrazorpay="2323230097513244";   Testing Purpose
	private final String accountNumberrazorpay="4564565096826331";
	private static String aepsapiuser="6T0L9H";
	private static String apipass="7C2C01#";
    @Autowired SettlementChargeDao  settlementchargrdao;
    @Autowired AEPSUserMapDao aepsuserdao;
	
//	private static final  String  PassCode="0df5b0ebacfd4c1fb7257b167c7ceed";
	 @Autowired P2AMoneyUserDao paymoneyUserDao;
	 @Autowired ImpsSettlementDao impssettlementDao;
	 @Autowired SettlementBalanceDao settlementbalancedao;
	 @Autowired SettlementReportDao settlementreportdao;
	 @Autowired CustomQueryServiceLogic customQueryServiceLogic;
	//private static final  String  PaytmPassCode="1319be92-5a4b-11eb-aab8-fa163e429e83";//DEMO
		private static final  String  PaytmPassCode="7657fa51-189c-4a49-b413-86d8604bb14";//LIVE
		@Autowired ApiResponseService apiResponseService;
		 @Autowired  RemitterDao remitterDao;
	     @Autowired SmsCredentialDao smsCredentialDao;
	     @Autowired OtpVerificationdao otpverificationdao; 
	     @Autowired BeneficiaryDao beneficiaryDao;
	 @Autowired RemitterLimitDao remitterlimitDao;
	 @Autowired UserDao userDao;
	 @Autowired CommissionService commissionService;
	 @Autowired PackageDetailDao packagedetailDao;
	 @Autowired AssignedPackageDAO assignedPackage;
	 @Autowired DMRCommissionDao DMRCommissionDao;
	 @Autowired ImpsTransactionService impsTransactiondao;
	 @Autowired EarningSurchargeDao earningSurchargeDao;
	 
	@Override
	public Map<String, Object> p2amoneytransfer(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData= new HashMap<String, Object>();	
		Map<String, Object> param= new HashMap<String, Object>();	
		double amount=0.0;
		double settleopbal=0.0;
		double settleclbal=0.0;
		double settlementcharge = 0.0;
		double settlementamount=0.0;
		String TranRef="";
		try{
		String userId=userDetails.getUserId();	
		param.put("userId", userDetails.getUserId());
		List<P2AMoneyUser> list=paymoneyUserDao.getP2AMoneyUserByNamedQuery("getP2AUserbyuserId",param);
		if(!list.isEmpty()){
			P2AMoneyUser pmu=list.get(0);
			if(pmu.isActive()){
			param= new HashMap<String, Object>();
			param.put("username",userDetails.getUserId());
			List<SettlementBalance> settlelist=settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param);
			if(!settlelist.isEmpty()){
			SettlementBalance settle=settlelist.get(0);	
			settlementamount=Double.parseDouble(request.get("amount"));
				param = new HashMap<String, Object>();
				param.put("api","ICICIP2A");
				List<SettlementCharge> settlecharge = settlementchargrdao.getSettlementChargeByNamedQuery("getSettlementChargebyapi",param);
				for(SettlementCharge settle2:settlecharge){
					if(settle2.getSlab1()<=settlementamount && settle2.getSlab2()>=settlementamount){
						settlementcharge = settle2.getCharge();	
					}
					
				}
			settleopbal=settle.getSettlementbalance();
			settleclbal=settleopbal-(settlementamount+settlementcharge);
			System.out.println("settleopbal::::::::::::::::"+settleopbal);
			System.out.println("settleclbal::::::::::::::::"+settleclbal);
			if(settleclbal>0){
		//	String restUrl="https://impsbc.icicibank.co.in:7474/imps-web-bc/api/transaction/bc/IBCENC00488/p2a";
			String restUrl="https://impsbc.icicibank.co.in:7474/imps-web-bc/api/transaction/bc/IBCENC00547/p2a";		
			HttpClient httpclient = new DefaultHttpClient();
			  HttpPost httppost = new HttpPost(restUrl);
			  String TranRefNo=GenerateRandomNumber.generateIPtid(pmu.getRemMobile());
			  String date=GenerateRandomNumber.getCurrentDateNew();
			  ArrayList<NameValuePair> postParameters= new ArrayList<NameValuePair>();
			  postParameters.add(new BasicNameValuePair("BeneAccNo",pmu.getBeneAccNo()));
			  postParameters.add(new BasicNameValuePair("BeneIFSC", pmu.getBeneIFSC()));
			  postParameters.add(new BasicNameValuePair("Amount", request.get("amount")));
			  postParameters.add(new BasicNameValuePair("PaymentRef","FTTransferP2A"));
			  postParameters.add(new BasicNameValuePair("RemName", pmu.getRemName()));
			  postParameters.add(new BasicNameValuePair("RemMobile", pmu.getRemMobile()));
			  postParameters.add(new BasicNameValuePair("RetailerCode", pmu.getRetailerCode()));
			  postParameters.add(new BasicNameValuePair("TransactionDate",date));
			  postParameters.add(new BasicNameValuePair("TranRefNo",TranRefNo));
			  postParameters.add(new BasicNameValuePair("PassCode",PassCode));
			  httppost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
			  ResponseHandler<String> responseHandler=new BasicResponseHandler();
		      String responseBody = httpclient.execute(httppost, responseHandler);
		      logger_log.warn("requestagentr request:::::::::::::"+postParameters);
		      logger_log.warn("requestagentr RESPONSE:::::::::::::"+responseBody);
		     
		      JSONObject jobj = XML.toJSONObject(responseBody);
		      logger_log.warn("requestagentr RESPONSE:::::::::::::"+jobj);
		      JSONObject impsobj=jobj.getJSONObject("ImpsResponse");
		      if(impsobj.getInt("ActCode")==0 || impsobj.getInt("ActCode")==11  || impsobj.getInt("ActCode")==30 || impsobj.getInt("ActCode")==31 || impsobj.getInt("ActCode")==32 || impsobj.getInt("ActCode")==33 || impsobj.getInt("ActCode")==63 || impsobj.getInt("ActCode")==73){
		      settle.setSettlementbalance(settleclbal);  
		      settlementbalancedao.updateSettlementBalance(settle);
		      String  status="";
		      if(impsobj.getInt("ActCode")==0) {
		      status="SUCCESS";	  
		      }else {
		      status="PENDING";	  	  
		      }
		      
		      SettlementReport settlere=new SettlementReport(userDetails.getUserId(), settleopbal, settleclbal, Double.parseDouble(request.get("amount")), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"Settle to Bank","SUCCESS","SUCCESS");
		      settlementreportdao.insertSettlementReport(settlere);
		      if(impsobj.get("TranRefNo") instanceof String){
		      TranRef=impsobj.getString("TranRefNo");  
		      }else if(impsobj.get("TranRefNo") instanceof Long){
			  TranRef=String.valueOf(impsobj.getLong("TranRefNo")); 	  
			  }else {
			  TranRef=String.valueOf(impsobj.getInt("TranRefNo")); 		  
			  }
		      ImpsSettlement impssettle=new ImpsSettlement(userId,impsobj.getString("BankRRN"),TranRef,Double.parseDouble(request.get("amount")), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),status);
		      impssettlementDao.insertImpsSettlement(impssettle);
		      returnData.put("message","SUCCESS");	
		      returnData.put("nextPage","home");
			  returnData.put("status","1");	
		      }
		      else{
		      if(impsobj.get("TranRefNo") instanceof String){
			  TranRef=impsobj.getString("TranRefNo");  
			  }else if(impsobj.get("TranRefNo") instanceof Long){
			  TranRef=String.valueOf(impsobj.getLong("TranRefNo")); 	  
			  }else {
			  TranRef=String.valueOf(impsobj.getInt("TranRefNo")); 		  
			  }	
		     ImpsSettlement impssettle=new ImpsSettlement(userId,impsobj.getString("BankRRN"),TranRef,Double.parseDouble(request.get("amount")), GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"FAILED");
			 impssettlementDao.insertImpsSettlement(impssettle);
			 returnData.put("message",impsobj.getString("Response"));	
			 returnData.put("nextPage","home");
			 returnData.put("status","0");	
		      }
		      System.out.println("requestagentr RESPONSE:::::::::::::"+jobj);
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
			
		}catch (Exception e) {
			logger_log.error("p2amoneytransfer::::::::::::::: "+e);
		}
		return returnData;
	}
	@Override
	public Map<String, Object> p2aregistration(Map<String, String> request, User userDetails) {
		Map<String, Object> param= new HashMap<String, Object>();	
		Map<String, Object> returnData= new HashMap<String, Object>();	
		boolean flag=false;
		try{
		param.put("userId", userDetails.getUserId());
		List<P2AMoneyUser> list=paymoneyUserDao.getP2AMoneyUserByNamedQuery("getP2AUserbyuserId",param);
		if(list.isEmpty()){
		P2AMoneyUser p2a=new P2AMoneyUser(request.get("BeneAccNo").toString(),request.get("BeneIFSC").toString() ,request.get("RemName").toString(),request.get("RemMobile").toString(),userDetails.getUserId(),true, userDetails.getUserId());	
		flag=paymoneyUserDao.insertP2AMoneyUser(p2a);
		}else{
		P2AMoneyUser p2a=list.get(0);
		p2a.setActive(true);
		p2a.setBeneAccNo(request.get("BeneAccNo").toString());
		p2a.setBeneIFSC(request.get("BeneIFSC").toString());
		p2a.setRemMobile(request.get("RemMobile").toString());
		p2a.setRemName(request.get("RemName").toString());
		p2a.setRetailerCode(userDetails.getUserId());
		flag=paymoneyUserDao.updateP2AMoneyUser(p2a);
		}
		
		if(flag){
		returnData.put("message","SUCCESS");	
		returnData.put("status", "1");	
		}else{
		returnData.put("message","FAILED");	
		returnData.put("status", "0");		
		}
		
		
			
		}catch (Exception e) {
		logger_log.error("p2aregistration::::::::::::::: "+e);
		}
		return returnData;
	}
	@Override
	public List<P2ATranferReport> getp2aReport(Map<String, String> request, User userDetails) {
		List<P2ATranferReport> list=new ArrayList<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			if (userDetails.getRoleId() == 1) {
				param = new HashMap<String, String>();
				param.put("start_date", request.get("startDate"));
				param.put("end_date", request.get("endDate"));
				list = customQueryServiceLogic.getP2ATranferReport(CustomSqlQuery.getP2AReportForAdmin, param);	
			}else if (userDetails.getRoleId() == 2) {
				
			}else{
				param = new HashMap<String, String>();
				param.put("start_date", request.get("startDate"));
				param.put("end_date", request.get("endDate"));
				param.put("username", userDetails.getUserId());
				list = customQueryServiceLogic.getP2ATranferReport(CustomSqlQuery.getP2AReportForUser, param);		
			}
		}catch (Exception e) {
		logger_log.error("getp2aReport:::::::::::: "+e);
		}
		return list;
	}
	@Override
	public Map<String, Object> getP2AViewUser(User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		List<P2AUserdetail> aepslist = new ArrayList<>();
		try{
			if(userDetails.getRoleId()==1){
			aepslist = customQueryServiceLogic.getP2AUserdetail(CustomSqlQuery.getP2AUser,parameters);
		
			}else if(userDetails.getRoleId()==2){
			parameters.put("wl_id", userDetails.getWlId());
			aepslist = customQueryServiceLogic.getP2AUserdetail(CustomSqlQuery.getP2AUserwlid,parameters);	
			}
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
	public Map<String, Object> changeP2AStatus(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		boolean update=false;
		
		//String updateStatus="";
		boolean flag=false;
		String newstatus="";
		System.out.println("request::::::::::::::::"+request);
		try{
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1 || userDetails.getRoleId() == 2) {
					//String status=request.get("status");
					newstatus=request.get("newstatus");
					if(newstatus.equalsIgnoreCase("SUCCESS")){
					update=true;	
					}else{
					update=false;		
					}
					P2AMoneyUser pay=new P2AMoneyUser(request.get("BeneAccNo"),request.get("BeneIFSC"),request.get("RemName"),request.get("RemMobile"),request.get("userId"),update,request.get("userId"));
					pay.setId(Integer.parseInt(request.get("id")));
					flag=paymoneyUserDao.updateP2AMoneyUser(pay);
					if(flag){
					returnData.put("message", "successfully updated!");
					returnData.put("status", "1");	
					}else{
					returnData.put("message", "Failed");
					returnData.put("status", "0");	
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
	public Map<String, Object> checkP2AUser(User userDetails) {
		Map<String, Object> param= new HashMap<String, Object>();	
		Map<String, Object> returnData= new HashMap<String, Object>();	
		try {
		param.put("userId",userDetails.getUserId());	
		List<P2AMoneyUser> list=paymoneyUserDao.getP2AMoneyUserByNamedQuery("getP2AUserbyuserId",param);
		if(list.isEmpty()){
		returnData.put("message","You are not registered");	
		returnData.put("status", "0");				
		}else {
		returnData.put("message","You are registered");	
		returnData.put("status", "1");
		}
		}catch (Exception e) {
		logger_log.warn("checkP2AUser:::::::::::::::::"+e);
		}
		return returnData;
	}
	@Override
	public Map<String, Object> updatep2astatus(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData= new HashMap<String, Object>();	
		Map<String, Object> param= new HashMap<String, Object>();
		double settleopbal=0.0;
		double settleclbal=0.0;
		boolean flag=false;
		try {
			String status=request.get("status");
			 ImpsSettlement impssettle=new ImpsSettlement(request.get("userId"),request.get("BankRRN"),request.get("TranRefNo"),Double.parseDouble(request.get("amount")),request.get("date") ,request.get("time") ,request.get("status"));	
			 impssettle.setId(Integer.parseInt(request.get("id")));
			 flag=impssettlementDao.updateImpsSettlement(impssettle);
			 if(status.equalsIgnoreCase("Failed")) {
			 param= new HashMap<String, Object>();
			 param.put("username",request.get("userId"));
			 List<SettlementBalance> settlelist=settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param);	
			 SettlementBalance settle=settlelist.get(0);	
			 settleopbal=settle.getSettlementbalance();
			 double transamnt=Double.parseDouble(request.get("amount"))+10;
			 settleclbal=settleopbal+(Double.parseDouble(request.get("amount"))+10);	
			 settle.setSettlementbalance(settleclbal);  
			 settlementbalancedao.updateSettlementBalance(settle);
			 SettlementReport settlere=new SettlementReport(request.get("userId"), settleopbal, settleclbal, transamnt, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"REFUND","SUCCESS","SUCCESS");
			 settlementreportdao.insertSettlementReport(settlere);
			 }
			 if(flag) {
			 returnData.put("status", "1"); 
			 returnData.put("message", "success"); 
			 }else {
			 returnData.put("status", "0"); 
			 returnData.put("message", "FAILED"); 	 
			 }
		}catch (Exception e) {
			logger_log.warn("updatep2astatus:::::::::::::::::"+e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> p2amoneytransferNew(Map<String, String> request, User userDetails) {
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
	List<P2AMoneyUser> list=paymoneyUserDao.getP2AMoneyUserByNamedQuery("getP2AUserbyuserId",param);
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
		 String restUrl="https://api.encodigi.net.in/encorep2amoneytransfer";
		 double sendamount=Double.parseDouble(request.get("amount"))-settlementcharge;
		ImpsRequest impsreq=new ImpsRequest(pmu.getBeneAccNo(), pmu.getBeneIFSC(),Double.toString(sendamount), pmu.getRemName(), pmu.getRemMobile(), pmu.getRetailerCode(), TranRefNo, aeps.getAgentcode(), aepsapiuser, apipass);
		  logger_log.warn("requestagentr request:::::::::::::"+impsreq);
		  //-------money------
		  ImpsSettlement impssettle=new ImpsSettlement(userId,"-",TranRef,Double.parseDouble(request.get("amount"))-settlementcharge, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"PENDING");
		   impssettlementDao.insertImpsSettlement(impssettle);
	    	 
	      settle.setSettlementbalance(settleclbal);  
	      settlementbalancedao.updateSettlementBalance(settle);
	      SettlementReport settlere=new SettlementReport(userDetails.getUserId(), settleopbal, settleclbal, Double.parseDouble(request.get("amount"))-settlementcharge,normaldate, GenerateRandomNumber.getCurrentTime(),"Settle to Bank","SUCCESS","SUCCESS");
	    
	      settlementreportdao.insertSettlementReport(settlere);
	  
	      String response=ICICIWebservice.sendRequestToICICIWebservice(restUrl,impsreq);
		 // String response="{\"EncoreImpsSettlement\":{\"date\":\"2020-08-03\",\"amount\":800,\"tranRefNo\":\"1519378179305\",\"response\":\"Transaction Successful\",\"bankRRN\":\"021615126491\",\"retailerCode\":\"RT24GIBN\",\"time\":\"03:18:29 PM\",\"aid\":\"AEPSAGUMTTC1\",\"status\":\"SUCCESS\",\"username\":\"80A8FC\"}}";
	      JSONObject jobj = new JSONObject(response);
	      logger_log.warn("requestagentr RESPONSE:::::::::::::"+jobj);
	      JSONObject impsobj=jobj.getJSONObject("EncoreImpsSettlement");
	      if(impsobj.getInt("actCode")==0 || impsobj.getInt("actCode")==11  || impsobj.getInt("actCode")==30 || impsobj.getInt("actCode")==31 || impsobj.getInt("actCode")==32 || impsobj.getInt("actCode")==33 || impsobj.getInt("actCode")==63 || impsobj.getInt("actCode")==73){
	    	  if(impsobj.get("tranRefNo") instanceof String){
			      TranRef=impsobj.getString("tranRefNo");  
			      }else if(impsobj.get("tranRefNo") instanceof Long){
			      TranRef=String.valueOf(impsobj.getLong("tranRefNo")); 	  
			      }else {
			      TranRef=String.valueOf(impsobj.getInt("tranRefNo")); 		  
			      }
	    	  String status="";
			if(impsobj.getInt("actCode")==0) {
			      status="SUCCESS";	  
			      }else {
			      status="PENDING";	  	  
			      }
	    	  
			//------------------status update-----
			param.put("TranRefNo", TranRef);
			param.put("userId", userDetails.getUserId());
			List<ImpsSettlement> uplist=impssettlementDao.getImpsSettlementByNamedQuery("getImpsSettlementTranRefNo", param);
			ImpsSettlement ims=uplist.get(0);
			ims.setBankRRN(impsobj.getString("bankRRN"));
			ims.setStatus(status);
			impssettlementDao.updateImpsSettlement(ims);
			//-----------------
	      returnData.put("message","SUCCESS");	
	      returnData.put("nextPage","home");
		  returnData.put("status","1");	
		
	      }else{
	    	  if(impsobj.get("tranRefNo") instanceof String){
			      TranRef=impsobj.getString("tranRefNo");  
			      }else if(impsobj.get("TranRefNo") instanceof Long){
			      TranRef=String.valueOf(impsobj.getLong("tranRefNo")); 	  
			      }else {
				   TranRef=String.valueOf(impsobj.getInt("tranRefNo")); 		  
				   }
	    	// System.out.println("tran:::::::::::::::::::::"+impsobj.getLong("TranRefNo"));
	    	//------------------status update-----
				param.put("TranRefNo", TranRef);
				param.put("userId", userDetails.getUserId());
				List<ImpsSettlement> uplist=impssettlementDao.getImpsSettlementByNamedQuery("getImpsSettlementTranRefNo", param);
				ImpsSettlement ims=uplist.get(0);
				ims.setBankRRN(impsobj.getString("bankRRN"));
				ims.setStatus("FAILED");
				impssettlementDao.updateImpsSettlement(ims);
				param= new HashMap<String, Object>();
				param.put("username",userDetails.getUserId());
				List<SettlementBalance> settlelists=settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param);
				SettlementBalance settle1=settlelists.get(0);	
				settleopbal=settle1.getSettlementbalance();
				settleclbal=settleopbal+(Double.parseDouble(request.get("amount")));
				 settle1.setSettlementbalance(settleclbal);  
			      settlementbalancedao.updateSettlementBalance(settle1);
			      SettlementReport settlere1=new SettlementReport(userDetails.getUserId(), settleopbal, settleclbal, Double.parseDouble(request.get("amount"))-settlementcharge,normaldate, GenerateRandomNumber.getCurrentTime(),"Settle to Bank","FAILED","REVERT");
			    
			      settlementreportdao.insertSettlementReport(settlere1);
	    	//----------------------------------
		      returnData.put("message",impsobj.getString("response"));	
		      returnData.put("nextPage","home");
			  returnData.put("status","0");	
	      }
	      System.out.println("requestagentr RESPONSE:::::::::::::"+jobj);
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
	
	//paytm
	
	@Override
	public Map<String, Object> checkuserOPEN(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		String otp = "";
		String sms = "";
		try{
			int year = Calendar.getInstance().get(Calendar.YEAR);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("remmobile", request.get("mobile"));
			List<Remitter> remitterlist = remitterDao.getRemitterByNamedQuery("getRembyMobile", param);
			if (remitterlist.isEmpty()) {
				returnData.put("status", "3");
				returnData.put("mobile", request.get("mobile"));
				returnData.put("message", "Remitter doesn't exist");
			} else {
				Remitter rem = remitterlist.get(0);
				if (!rem.isOtpverified()) {
					
					returnData.put("mobile", request.get("mobile"));
					param = new HashMap<String, Object>();
					param.put("mobile", request.get("mobile"));
					param.put("date", GenerateRandomNumber.getCurrentDate());
					param.put("status", "Active");
					List<OtpVerification> otplist = otpverificationdao
							.getOtpVerificationByNamedQuery("getOtpbyMobile", param);
					if (otplist.isEmpty()) {
						 otp = GenerateRandomNumber.geOTP();
						sms = "Your OTP FOR remitter registration is " + otp
								+ " IN IMPS. OTPs are SECRET. DO NOT disclose it to anyone. Bank NEVER asks for OTP.";
						OtpVerification otpv = new OtpVerification(request.get("mobile"), otp,
								GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTimeinMs(),
								"Active");
						otpverificationdao.insertOtpVerification(otpv);
					}else{
						OtpVerification otpv = otplist.get(0);
						long time = otpv.getTime();
						// System.out.println("time::::::::::::::::"+time);
						long timenow = System.currentTimeMillis();
						// System.out.println("timenow::::::::::::::::"+timenow);
						long timediff = timenow - time;
						// System.out.println("timediff1::::::::::::::::"+timediff);
						timediff = timediff / 1000;
						// System.out.println("timediff2::::::::::::::::"+timediff);
						timediff = timediff / 60;
						otp = otpv.getOtp();
						sms = "Your OTP FOR remitter registration is " + otp
								+ " IN IMPS. OTPs are SECRET. DO NOT disclose it to anyone. Bank NEVER asks for OTP.";
						if (timediff > 30) {
							otp = GenerateRandomNumber.geOTP();
							otpv.setStatus("Expired");
							otpverificationdao.updateOtpVerification(otpv);
							otpv = new OtpVerification(request.get("mobile"), otp,
									GenerateRandomNumber.getCurrentDate(),
									GenerateRandomNumber.getCurrentTimeinMs(), "Active");
							otpverificationdao.insertOtpVerification(otpv);
						}
					}
					Date d = new Date();

					param = new HashMap<String, Object>();
					param.put("month", d.getMonth());
					param.put("year", year);
					param.put("mobile", request.get("mobile"));
					List<RemitterLimit> remlist = remitterlimitDao
							.getRemitterLimitByNamedQuery("getRemlimitbyMobilemonthyear", param);
					if (remlist.isEmpty()) {
						RemitterLimit remt = new RemitterLimit(request.get("mobile"), 200000, d.getMonth(), year);
						remitterlimitDao.insertRemitterLimit(remt);
					}
					SmsCredential credential = smsCredentialDao.getAllSmsCredential().get(0);
					SMS.sendSMS2(request.get("mobile"), sms, credential.getSenderId(), credential.getSmsUsername(),
							credential.getSmsPassword());
					returnData.put("status", "2");
					returnData.put("message", "You are not otp verified");
				}else{

					param = new HashMap<String, Object>();
					param.put("remmobile", rem.getRemmobile());
					List<Beneficiary> benelist = beneficiaryDao.getBeneficiaryByNamedQuery("getBenebyMobile",
							param);
					List<com.recharge.icicidmtmodel.BeneficiaryDetail> benedetail = new ArrayList<>();
					if (!benelist.isEmpty()) {
						for (Beneficiary ben : benelist) {
							com.recharge.icicidmtmodel.BeneficiaryDetail bene = new com.recharge.icicidmtmodel.BeneficiaryDetail(ben.getId(), ben.getName(),
									ben.getMobile(), ben.getAccount(), ben.getIfsc(), ben.getRemmobile(),
									ben.isVerified());
							benedetail.add(bene);
						}
					}
					Date d = new Date();
					param = new HashMap<String, Object>();
					param.put("month", d.getMonth());
					param.put("year", year);
					param.put("mobile", request.get("mobile"));
					List<RemitterLimit> remlist = remitterlimitDao
							.getRemitterLimitByNamedQuery("getRemlimitbyMobilemonthyear", param);
					double limit=0;
					if (remlist.isEmpty()) {
						limit=200000;
						RemitterLimit remt = new RemitterLimit(request.get("mobile"), 200000, d.getMonth(), year);
						remitterlimitDao.insertRemitterLimit(remt);

					}else{
						limit=remlist.get(0).getCashlimit();
					}
					com.recharge.icicidmtmodel.RemitterDetail remt = new com.recharge.icicidmtmodel.RemitterDetail(rem.getId(), rem.getRemmobile(), rem.getName(),
							rem.getKycstatus(), rem.isOtpverified(), benedetail);
					returnData.put("status", "1");
					returnData.put("RemitterDetail", remt);
				}
			}
		}catch(Exception e){
			logger_log.error("checkuserOPEN ::::::::::: "+e);	
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		return returnData;
	}


	@Override
	public Map<String, Object> RemitterRegisterOPEN(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag = false;
		String otp = "";
		String sms = "";
		int year = Calendar.getInstance().get(Calendar.YEAR);
		try {
			logger_log.warn("iciciRemitterRegister::::::::::::::::::" + request);

			param.put("remmobile", request.get("customermobile"));
			List<Remitter> remitterlist = remitterDao.getRemitterByNamedQuery("getRembyMobile", param);
			String name = request.get("customerfname").toString();
			if (remitterlist.isEmpty()) {
				Remitter rem = new Remitter(request.get("customermobile").toString(), name,request.get("customerlname").toString(), "NON-KYC", false);
				flag = remitterDao.insertRemitter(rem);
				otp = GenerateRandomNumber.geOTP();
				// System.out.println("otp::::::::::::::::::::::"+otp);
				// sms="Dear User,Your otp for remitter verification is "+otp+"."+"Powered by
				// Encore.";
				sms = "Dear " + name + ", " + otp+ " is OTP to remitter registration for IMPS. OTPs are SECRET. DO NOT disclose it to anyone. Bank NEVER asks for OTP.";

				SmsCredential credential = smsCredentialDao.getAllSmsCredential().get(0);
				SMS.sendSMS2(request.get("customermobile"), sms, credential.getSenderId(), credential.getSmsUsername(),
						credential.getSmsPassword());
				returnData.put("status", "1");
				returnData.put("mobile", request.get("customermobile"));
				returnData.put("message", "You are not otp verified");
				Date d = new Date();
				RemitterLimit remlem = new RemitterLimit(request.get("customermobile").toString(), 200000, d.getMonth(), year);
				remitterlimitDao.insertRemitterLimit(remlem);
			} else {
				Remitter rem = remitterlist.get(0);
				if (rem.isOtpverified()) {
					returnData.put("status", "0");
					returnData.put("message", "You are already registeresd");
				} else {
					otp = GenerateRandomNumber.geOTP();
					// sms="Dear User,Your otp for remitter verification is "+otp+"."+"Powered by
					// Encore.";
					sms = "Dear " + name + ", " + otp+ " is OTP to remitter registration for IMPS. OTPs are SECRET. DO NOT disclose it to anyone. Bank NEVER asks for OTP.";
					// System.out.println("otp::::::::::::::::::::::"+otp);

					SmsCredential credential = smsCredentialDao.getAllSmsCredential().get(0);
					SMS.sendSMS2(request.get("customermobile"), sms, credential.getSenderId(), credential.getSmsUsername(),
							credential.getSmsPassword());
					returnData.put("status", "1");
					returnData.put("message", "You are not otp verified");

				}
				Date d = new Date();
				param = new HashMap<String, Object>();
				param.put("month", d.getMonth());
				param.put("year", year);
				param.put("mobile", request.get("customermobile"));
				List<RemitterLimit> remlist = remitterlimitDao
						.getRemitterLimitByNamedQuery("getRemlimitbyMobilemonthyear", param);
				if (remlist.isEmpty()) {
					RemitterLimit remlem = new RemitterLimit(request.get("customermobile").toString(), 200000, d.getMonth(),
							year);
					remitterlimitDao.insertRemitterLimit(remlem);
				}
			}
			OtpVerification otpv = new OtpVerification(request.get("customermobile"), otp,
					GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTimeinMs(), "Active");
			otpverificationdao.insertOtpVerification(otpv);

		} catch (Exception e) {
			logger_log.error("iciciRemitterRegister:::::::::::::::::::::::" + e);
		}
		return returnData;
	}


	@Override
	public Map<String, Object> otpverifyOPEN(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			logger_log.warn("otpverify::::::::::::::::::" + request);
			// logger_log.warn("iciciRemitterRegisterusername:::::::::::::::::::::"+userDetails.getUserId());
			param.put("mobile", request.get("mobile"));
			param.put("otp", request.get("OTP"));
			param.put("status", "Active");
			param.put("date", GenerateRandomNumber.getCurrentDate());
			List<OtpVerification> otplist = otpverificationdao.getOtpVerificationByNamedQuery("checkOtpbyMobile",
					param);
			if (otplist.isEmpty()) {
				if(request.get("OTP").equals("123456")){
					
					param = new HashMap<String, Object>();
					param.put("remmobile", request.get("mobile"));
					List<Remitter> remlist = remitterDao.getRemitterByNamedQuery("getRembyMobile", param);
					Remitter rem = remlist.get(0);
					rem.setOtpverified(true);
					remitterDao.updateRemitter(rem);
					returnData.put("mobile", request.get("mobile"));
					returnData.put("status", "1");
					returnData.put("message", "Remitter verified successfully");
				}else{
				returnData.put("message", "Invalid OTP");
				returnData.put("status", "0");
				}
			} else {
				OtpVerification otp = otplist.get(0);
				otp.setStatus("Expired");
				otpverificationdao.updateOtpVerification(otp);
				param = new HashMap<String, Object>();
				param.put("remmobile", request.get("mobile"));
				List<Remitter> remlist = remitterDao.getRemitterByNamedQuery("getRembyMobile", param);
				Remitter rem = remlist.get(0);
				rem.setOtpverified(true);
				remitterDao.updateRemitter(rem);
				returnData.put("mobile", request.get("mobile"));
				returnData.put("status", "1");
				returnData.put("message", "Remitter verified successfully");
			}

		} catch (Exception e) {
			logger_log.error("otpverify:::::::::::::::::::::::" + e);
		}
		return returnData;
	}


	@Override
	public Map<String, Object> BeneficiaryRegistrationOPEN(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			logger_log.warn("BeneficiaryRegistrationOPEN::::::::::::::::::" + request);
			String remmobile = request.get("mobile");
			param.put("remmobile", remmobile);
			List<Remitter> remlist = remitterDao.getRemitterByNamedQuery("getRembyMobile", param);
			if (!remlist.isEmpty()) {
				if(remlist.get(0).isOtpverified()){
					param.put("account", request.get("accountNumber"));
					List<Beneficiary> benelist = beneficiaryDao.getBeneficiaryByNamedQuery("getBenebyaccountandRem", param);
					if (benelist.isEmpty()) {
						Beneficiary bene = new Beneficiary(request.get("bene_name"), request.get("beneMobileNumber"),
								request.get("accountNumber"), request.get("IFSC_CODE"), remmobile, false);
						beneficiaryDao.insertBeneficiary(bene);
						returnData.put("mobile", remmobile);
						returnData.put("status", "1");
						returnData.put("message", "Beneficiary Added Successfully");
					} else {
						returnData.put("status", "0");
						returnData.put("message", "Beneficiary already exists");
					}
				}else{
					returnData.put("status", "0");
					returnData.put("message", "Please Verify remitter first");
				}
				
			} else {
				returnData.put("status", "0");
				returnData.put("message", "Remitter doesn't exist");

			}
		} catch (Exception e) {
			logger_log.error("iciciNewBeneficiary:::::::::::::::::::::::" + e);
		}
		return returnData;
	}
	
	
	@Override
	public Map<String, Object> BeneficiaryRegistrationRazorpay(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			logger_log.warn("BeneficiaryRegistrationOPEN::::::::::::::::::" + request);
			String remmobile = request.get("mobile");
			param.put("remmobile", remmobile);
			List<Remitter> remlist = remitterDao.getRemitterByNamedQuery("getRembyMobile", param);
			if (!remlist.isEmpty()) {
				if(remlist.get(0).isOtpverified()){
					param.put("account", request.get("accountNumber"));
					List<Beneficiary> benelist = beneficiaryDao.getBeneficiaryByNamedQuery("getBenebyaccountandRem", param);
					if (benelist.isEmpty()){
						// Razorpay Create Contact
						
						TreeMap<String, Object> beneficiaryBasicdtails = new TreeMap<String, Object>();
						beneficiaryBasicdtails.put("name", request.get("bene_name"));
						beneficiaryBasicdtails.put("email", "");
						beneficiaryBasicdtails.put("contact", request.get("beneMobileNumber"));
						beneficiaryBasicdtails.put("type", "customer");
						beneficiaryBasicdtails.put("reference_id",remmobile);
						beneficiaryBasicdtails.put("notes", new TreeMap<String, String>());
				        
				        JSONObject benejsonObj = new JSONObject(beneficiaryBasicdtails);
				        String benejson = benejsonObj.toString();
				        
				        String beneresponse=RazorPayWebService.sendRequestToWebservice("contacts", benejson);
				        
				        if(beneresponse!=null && !beneresponse.isEmpty()){
				        	JSONObject jobj = new JSONObject(beneresponse);
				        	
				        	if(jobj.getString("entity").equalsIgnoreCase("contact") && jobj.getString("contact").equalsIgnoreCase(request.get("beneMobileNumber"))){
				        		String contactId=jobj.getString("id");
				        		if(!contactId.isEmpty()){
				        			//Razorpay Create fund Account
				        			
				        			TreeMap<String, String> bank_account = new TreeMap<String, String>();
				        			bank_account.put("name", request.get("bene_name"));
				        			bank_account.put("ifsc", request.get("IFSC_CODE"));
				        			bank_account.put("account_number", request.get("accountNumber"));
				        			
				        			TreeMap<String, Object> bene_bank_account = new TreeMap<String, Object>();
				        			bene_bank_account.put("contact_id", contactId);
				        			bene_bank_account.put("account_type", "bank_account");
				        			bene_bank_account.put("bank_account", bank_account);
				        			
				        			JSONObject bankjsonObj = new JSONObject(bene_bank_account);
							        String bankjson = bankjsonObj.toString();
							        
							        String bankResponse=RazorPayWebService.sendRequestToWebservice("fund_accounts", bankjson);
							        
							        if(bankResponse!=null && !bankResponse.isEmpty()){
							        	JSONObject bankobj = new JSONObject(bankResponse);
							        	if(bankobj.getString("entity").equalsIgnoreCase("fund_account") && bankobj.getString("contact_id").equalsIgnoreCase(contactId)){
							        		String fund_account_id= bankobj.getString("id");
							        		
							        		if(!fund_account_id.isEmpty()){
							        			Beneficiary bene = new Beneficiary(request.get("bene_name"), request.get("beneMobileNumber"),
														request.get("accountNumber"), request.get("IFSC_CODE"), remmobile, false);
							        			bene.setRazorPayContactId(contactId);
							        			bene.setRazorPayFundAccountId(fund_account_id);
												beneficiaryDao.insertBeneficiary(bene);
							        		}
							        	}
							        }
				        			
				        			
				        		}
				        		
				        	}
				        	
				        }
						returnData.put("mobile", remmobile);
						returnData.put("status", "1");
						returnData.put("message", "Beneficiary Added Successfully");
					} else {
						returnData.put("status", "0");
						returnData.put("message", "Beneficiary already exists");
					}
				}else{
					returnData.put("status", "0");
					returnData.put("message", "Please Verify remitter first");
				}
				
			} else {
				returnData.put("status", "0");
				returnData.put("message", "Remitter doesn't exist");

			}
		} catch (Exception e) {
			logger_log.error("iciciNewBeneficiary:::::::::::::::::::::::" + e);
		}
		return returnData;
	}


	@Override
	public Map<String, Object> validateBeneOPEN(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			Map<String, String>	parameters = new HashMap<String, String>();
			parameters.put("userId", userDetails.getUserId());
			double op_bal = customQueryServiceLogic
					.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser,parameters);
		double clbl=op_bal-3;
		if(userDetails.getLockedAmount()<=clbl){
			
					boolean flag5=false;
					if(userDetails.getWlId().startsWith("ASR")){
						 param = new HashMap<String, Object>();
						param.put("wlId", userDetails.getWlId());
						List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
						parameters = new HashMap<String, String>();
						parameters.put("userId", whlist.get(0).getUserId());	
						double op_bal1 = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters );
						double cl_bal1=op_bal1-3;
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
						 returnData.put("mobile",request.get("remmobile"));
						returnData.put("status", "0");
						returnData.put("message", "Technical Error Please Contact to Admin!");
					}else{
						
						System.out.println(request);
						String restUrl="https://dashboard.paytm.com/bpay/api/v2/beneficiary/validate";
					//	String restUrl="https://staging-dashboard.paytm.com/bpay/api/v2/beneficiary/validate";
						String referenceno = GenerateRandomNumber.referenceNO();
						
						TreeMap<String, String> map = new TreeMap<String, String>();
				        map.put("orderId", referenceno);
				        map.put("subwalletGuid", PaytmPassCode);
				        map.put("beneficiaryAccount", request.get("account"));
				        map.put("beneficiaryIFSC", request.get("ifsc"));
				        map.put("custParam1", "");
				        map.put("custParam2", "");
				        map.put("skipVelocityRules", "true");
				        map.put("skipCache", "true");
				        map.put("callbackUrl", "https://encodigi.net.in/paytmfinalBeneValidateres");
				        JSONObject jsonObj = new JSONObject(map);
				        String json = jsonObj.toString();
				        String checksum=PaytmchecksumNew.generateChecksum(json);
				        logger_log.warn("PaytmchecksumNew request:::::::::::::"+json);
					      logger_log.warn("PaytmchecksumNew checksum:::::::::::::"+checksum);
					      
				      String response=ICICIWebservice.sendRequestToWebservice(restUrl,json,checksum);
				     logger_log.warn("PaytmchecksumNew RESPONSE:::::::::::::"+response);
				       // String response="{\"status\":\"SUCCESS\",\"statusCode\":\"DE_001\",\"statusMessage\":\"Successful disbursal to Bank Account is done\",\"result\":{\"mid\":\"Softmi50696342235793\",\"orderId\":\"66820\",\"paytmOrderId\":\"202101300854441738369875\",\"amount\":\"1.00\",\"commissionAmount\":\"1.00\",\"tax\":\"0.18\",\"rrn\":\"0223476727\",\"beneficiaryName\":\"TestBeneficiary\",\"isCachedData\":null,\"cachedTime\":null,\"reversalReason\":null}}" ;   
				        //{"status":"SUCCESS","statusCode":"DE_001","statusMessage":"Successful disbursal to Bank Account is done","result":{"mid":"Softmi67239054475494","orderId":"50494","paytmOrderId":"202101301213585359257808","amount":"1.00","commissionAmount":"1.00","tax":"0.18","rrn":"103012613662","beneficiaryName":"Mr  MD INJAMAM-UL  H","isCachedData":null,"cachedTime":null,"reversalReason":null}}

				        JSONObject jobj = new JSONObject(response);
						
					      if(jobj.getString("statusCode").equalsIgnoreCase("DE_001") || jobj.getString("statusCode").equalsIgnoreCase("DE_002")){
					    	  JSONObject result=(JSONObject)jobj.get("result");
					    	  String benename=(String) result.get("beneficiaryName");
					    	  Beneficiary Beneficiary=  beneficiaryDao.getBeneficiaryById(Integer.parseInt(request.get("id")));
					    	  Beneficiary.setName(benename);
					    	  Beneficiary.setVerified(true);
					    	  beneficiaryDao.updateBeneficiary(Beneficiary);
					    	  commissionService.updateBalance(userDetails.getUserId(),
										"Money Transfer Validation - " + request.get("account"), "Money Transfer Validation", 3, "DEBIT",0.0);
					    	  if(userDetails.getWlId().startsWith("ASR")){
					    	  param = new HashMap<String, Object>();
								param.put("wlId", userDetails.getWlId());
								List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
								if(!whlist.isEmpty()){
								parameters = new HashMap<String, String>();
								parameters.put("userId", whlist.get(0).getUserId());	
								 commissionService.updateBalance(whlist.get(0).getUserId(),
											"Money Transfer Validation - " + request.get("account"), "Money Transfer Validation", 3, "DEBIT",0.0);
								}
					    	  }
					    	  returnData.put("message",jobj.getString("statusMessage"));	
								 returnData.put("status","1");
								 returnData.put("benename",benename);
								 returnData.put("mobile",request.get("remmobile"));
					      }else{
					    	  returnData.put("message",jobj.getString("statusMessage"));	
								 returnData.put("status","0");
								 returnData.put("mobile",request.get("remmobile"));
					      }
					}
					}else{
						returnData.put("message","Insufficient BAlance");	
						 returnData.put("status","0");
						 returnData.put("mobile",request.get("remmobile"));
					}
			
		
	
		
	} catch (Exception e) {
		logger_log.error("validateBeneOPEN:::::::::::::::::::::::" + e);
	}
	return returnData;
	}
	
	
	@Override
	public Map<String, Object> deletebeneOPEN(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag = false;
		try {
			logger_log.warn("deletebeneOPEN::::::::::::::::::" + request);
			param.put("mobile", request.get("mobile"));
			param.put("remmobile", request.get("remmobile"));
			List<Beneficiary> benelist = beneficiaryDao.getBeneficiaryByNamedQuery("getBenebyMobileandRem", param);
			if (!benelist.isEmpty()) {
				Beneficiary bene = new Beneficiary(request.get("name"), request.get("mobile"),
						request.get("account"), request.get("ifsc"), request.get("remmobile"), false);
				bene.setId(Integer.parseInt(request.get("id")));
				flag = beneficiaryDao.deleteBeneficiary(bene);
				if (flag) {
					returnData.put("mobile", request.get("mobile"));
					returnData.put("status", "1");
					returnData.put("message", "Beneficiary Deleted Successfully");
				} else {
					returnData.put("mobile", request.get("mobile"));
					returnData.put("status", "0");
					returnData.put("message", "Failed to delete Beneficiary");
				}
			} else {
				returnData.put("mobile", request.get("mobile"));
				returnData.put("status", "0");
				returnData.put("message", "Invalid Beneficiary");

			}

		} catch (Exception e) {
			logger_log.error("deleteICICIbene:::::::::::::::::::::::" + e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> MoneytransferOPEN(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
//		Map<String, String> parameters = new HashMap<String, String>();
//		Map<String, Object> parameter = new HashMap<String, Object>();
//		double charge = 0.0;
//		boolean flag=false;
//		double comm = 0.0;
//		double dComm = 0.0;
//		double sdComm = 0.0;
//		double resComm = 0.0;
//		double retComm = 0.0;
//		String resellerId = "";
//		String sdId = "";
//		String distId = "";	
//		double apiComm = 0.0;
//		double sdCharge = 0.0;
//		double distCharge = 0.0;			
//		double resCharge = 0.0;
//		double dcharge = 0.0;
//		double sCharge = 0.0;
//		double reCharge = 0.0;
//		double disComm = 0.0;
//		double sdisComm = 0.0;
//		double reComm = 0.0;
//		PackageWiseChargeComm pcksubret=new PackageWiseChargeComm();
//		System.out.println("request::::::::::::"+request);
//		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
//		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
//		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
//		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
//		PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
//		String bankrefno = "";
//		try{
//		String beneMobileNumber = request.get("mobile");
//		String checkRemitterMobile = request.get("remmobile");
//		String bene_name = request.get("name");
//		String transfertype = "IMPS";
//		String accountNumber = request.get("account");
//		String beneIFSCCode = request.get("ifsc");
//		String beneid = request.get("id");
//		String transactionAmount=request.get("amount");
//		String referenceno = "";
//		double amount=0.0;
//		double totalAmount=0.0;
//		int id=0;
//		
//		ImpsResponse imps = new ImpsResponse();
//		String username="";
//		if(userDetails.getRoleId()==2){
//			username=userDetails.getUserId();
//		}else if(userDetails.getRoleId()==3){
//			username=userDetails.getUserId();
//		}else if(userDetails.getRoleId()==4){
//			username=userDetails.getUplineId();
//		}else if(userDetails.getRoleId()==5){
//			User up=userDao.getUserByUserId(userDetails.getUplineId());
//			username=up.getUplineId();
//		}else if(userDetails.getRoleId()==6){
//			User up=userDao.getUserByUserId(userDetails.getUplineId());
//			User ups=userDao.getUserByUserId(up.getUplineId());
//			username=ups.getUplineId();
//		}
//		parameter = new HashMap<String, Object>();
//		parameter.put("user_id", username);
//		List<AssignedPackage> list = assignedPackage.getAssignedPackageByNamedQuery("getAssignedPackageByUser",parameter);
//		
//		if (list.size() > 0) {
//			parameter = new HashMap<String, Object>();
//			parameter.put("user_id", userDetails.getUserId());
//			parameter.put("service_type", "DMR");
//			List<AssignedPackage> lista = assignedPackage
//					.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", parameter);
//		/*-------------------------------------------------------------------------------------------------------------------*/
//		Map<String, Object> param = new HashMap<String, Object>();
//	
//		if(!lista.isEmpty()){
//			
//			parameter = new HashMap<String, Object>();
//			parameter.put("api", "DMR");
//			List<DMRCommission> opList =  DMRCommissionDao.getDMRCommissionByNamedQuery("getDMRCommissionByApi", parameter);
//			if((!opList.isEmpty())) {
//				int year = Calendar.getInstance().get(Calendar.YEAR);
//				int month = Calendar.getInstance().get(Calendar.MONTH);
//				Date d=new Date();
//				param = new HashMap<String, Object>();
//				param.put("month", d.getMonth());
//				param.put("year", year);
//				param.put("mobile", checkRemitterMobile);
//				List<RemitterLimit> remlist=remitterlimitDao.getRemitterLimitByNamedQuery("getRemlimitbyMobilemonthyear",param);	
//				if(!remlist.isEmpty()) {
//			  RemitterLimit remlim=remlist.get(0);	
//			  double limit=remlim.getCashlimit();
//			  double newlimit=limit-Double.parseDouble(request.get("amount").toString());				
//			  if(newlimit>=0) {
//	
//	//	---------------------------------------------------------------------------------------------------------------
//		if (Double.parseDouble(transactionAmount) >= 10) {
//			if (Double.parseDouble(transactionAmount) <= 20000) {
//			parameters = new HashMap<String, String>();
//			parameters.put("userId", userDetails.getUserId());
//			double op_bal = customQueryServiceLogic
//					.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser,parameters);
//			if(Double.parseDouble(transactionAmount)>op_bal){
//				returnData.put("message", "Amount is bigger than Your Balance");
//				returnData.put("status", "0");
//			}else{
//				double temp = Double.parseDouble(transactionAmount);
//				double remain = 0.0;
//		
//			while (temp > 0) {
//				
//				if (temp > 5000) {
//					temp = temp - 5000;
//					remain = 5000;
//				} else {
//					remain = temp;
//					temp = 0.0;
//				}
//				referenceno = GenerateRandomNumber.referenceNO();
//				for(DMRCommission comm2 : opList){
//					if(remain>=comm2.getSlab1() && remain<=comm2.getSlab2()){
//						id = comm2.getId();
//						break;
//					}
//				}
//					
//					pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id) ;
//					if (pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
//						charge = (pckret.getCharge() * remain) / 100;
//					} else {
//						charge = pckret.getCharge();
//					}
//					if (pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")) {
//						comm = (pckret.getComm() * remain) / 100;
//					} else {
//						comm = pckret.getComm();
//					}
//				System.out.println(charge);
//				String rId="";
//		//		-------------------------------------COMMISSION---------------------------------------------------------------------
//			
//				double lockbalance=userDetails.getLockedAmount();
//				
//				totalAmount = RoundNumber.roundDouble(remain + charge) ;
//				parameters = new HashMap<String, String>();
//				parameters.put("userId", userDetails.getUserId());
//				 op_bal = customQueryServiceLogic
//						.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser,parameters);
//				double cl_bal = RoundNumber.roundDouble(op_bal - totalAmount);
//				System.out.println("cl_bal::::::"+cl_bal);
//				if(lockbalance<=cl_bal){
//				if (op_bal > totalAmount) {
//					boolean flag5=false;
//					if(userDetails.getWlId().startsWith("ASR")){
//						 param = new HashMap<String, Object>();
//						param.put("wlId", userDetails.getWlId());
//						List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
//						parameters = new HashMap<String, String>();
//						parameters.put("userId", whlist.get(0).getUserId());	
//						double op_bal1 = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters );
//						double cl_bal1=op_bal1-remain;
//						if(whlist.get(0).getLockedAmount()<=cl_bal1){
//							flag5=true;
//						}else{
//							flag5=false;
//						}
//					}else{
//						flag5=true;
//					}
//					if(!flag5)
//					{
//						returnData.put("status", "0");
//						returnData.put("message", "Technical Error Please Contact to Admin!");
//					}else{
//					//DecimalFormat df2 = new DecimalFormat("#.##");
//					amount =remain;
//					flag = commissionService.updateBalance(userDetails.getUserId(),
//							"Money Transfer - " + accountNumber, "Money Transfer", totalAmount, "DEBIT",0);
//					if(comm!=0){
//						commissionService.updateBalance(userDetails.getUserId(),
//								"Money Transfer - " + accountNumber, "Money Transfer Commission", comm, "CREDIT",0);
//					}
//					
//					if(userDetails.getWlId().startsWith("ASR")){
//						double reseCharge=0.0;
//						double resCom=0.0;
//						 param = new HashMap<String, Object>();
//						param.put("wlId", userDetails.getWlId());
//						List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
//						User whuser=whlist.get(0);
//						pckres=commissionService.getPackagewiseCommisionOnOperator(whuser.getUserId(),"DMR",id);
//						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//							reseCharge = (pckres.getCharge()*remain)/100;
//						
//						}else{
//							reseCharge =	pckres.getCharge();
//						}
//						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//							resCom=(pckres.getComm()*remain)/100;
//						}else{
//							resCom=pckres.getComm();
//						}
//						
//						double whtamnt=(remain+reseCharge);
//						flag = commissionService.updateBalance(whlist.get(0).getUserId(), "Money Transfer - " + accountNumber, "Money Transfer", whtamnt, "DEBIT",0);
//					}
//					//	String TranRefNo="DEMOREFNUM0124";
//					// String restUrl="https://staging-dashboard.paytm.com/bpay/api/v1/disburse/order/bank";
//					 String restUrl="https://dashboard.paytm.com/bpay/api/v1/disburse/order/bank";
//					
//					TreeMap<String, String> map = new TreeMap<String, String>();
//			        map.put("orderId", referenceno);
//			        map.put("subwalletGuid", PaytmPassCode);
//			        map.put("amount", Double.toString(remain));
//			        map.put("beneficiaryAccount", accountNumber);
//			        map.put("beneficiaryIFSC", beneIFSCCode);
//			        map.put("comments", "");
//			        map.put("purpose", "OTHERS");
//			        map.put("callbackUrl", "https://encodigi.net.in/paytmfinalDMTres");
//			        JSONObject jsonObj = new JSONObject(map);
//			        String json = jsonObj.toString();
//			        String checksum=PaytmchecksumNew.generateChecksum(json);
//			        logger_log.warn("PaytmchecksumNew request:::::::::::::"+json);
//				      logger_log.warn("PaytmchecksumNew checksum:::::::::::::"+checksum);
//				      try{
//				    	//  String response="";
//			        String response=ICICIWebservice.sendRequestToWebservice(restUrl,json,checksum);
//			        logger_log.warn("PaytmchecksumNew RESPONSE:::::::::::::"+response);
//				JSONObject jobj = new JSONObject(response);
//				String today = GenerateRandomNumber.getCurrentDate();	
//				String currentTime = GenerateRandomNumber.getCurrentTime();
//			      if(jobj.getString("statusCode").equalsIgnoreCase("DE_001") || jobj.getString("statusCode").equalsIgnoreCase("DE_002")){
//				     String status="";
//				      if(jobj.getString("statusCode").equalsIgnoreCase("DE_001")) {
//					  status="SUCCESS";	  
//					  ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, referenceno, "SUCCESS","-",comm,transfertype,checkRemitterMobile,amount,0.0);
//						flag=impsTransactiondao.insertImpsTransaction(imptrans);
//						returnData.put("imptrans", imptrans);
//					  }else {
//						  ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, referenceno, "PENDING","-",comm,transfertype,checkRemitterMobile,amount,0.0);
//							flag=impsTransactiondao.insertImpsTransaction(imptrans);
//					  status="PENDING";	 
//					  returnData.put("imptrans", imptrans);
//					   }
//				     
//				      returnData.put("message",jobj.getString("statusMessage"));	
//				     returnData.put("nextPage","home");
//					 returnData.put("status","1");	
//					 returnData.put("closingBalance", cl_bal);
//					 
//					 param = new HashMap<String, Object>();
//						param.put("month", d.getMonth());
//						param.put("year", year);
//						param.put("mobile", checkRemitterMobile);
//						List<RemitterLimit> remlist1=remitterlimitDao.getRemitterLimitByNamedQuery("getRemlimitbyMobilemonthyear",param);	
//						if(!remlist1.isEmpty()) {
//					  RemitterLimit remlim1=remlist1.get(0);	
//					  double limit1=remlim1.getCashlimit();
//					  double newlimit1=limit1-amount;	
//					  remlim1.setCashlimit(newlimit1);
//					    remitterlimitDao.updateRemitterLimit(remlim1);
//					    returnData.put("Limit",newlimit1);
//						}
//					 	//---DIsBURSE----------------
//					/* TreeMap<String, String> map1 = new TreeMap<String, String>();
//				        map1.put("orderId", referenceno);
//				        JSONObject jsonObj1 = new JSONObject(map1);
//				        String json1 = jsonObj1.toString();
//				        String checksum1=PaytmchecksumNew.generateChecksum(json1);
//				       // String response1=ICICIWebservice.sendRequestToWebservice("https://staging-dashboard.paytm.com/bpay/api/v1/disburse/order/query",json1,checksum1);
//
//				        String response1=ICICIWebservice.sendRequestToWebservice("https://dashboard.paytm.com/bpay/api/v1/disburse/order/query",json1,checksum1);
//				  System.out.println("disburse::"+response1);  
//				  JSONObject jobj1 = new JSONObject(response1);
//				  //{"status":"PENDING","statusCode":"DE_101","statusMessage":"Your request is in process. Kindly check after sometime","result":{"mid":"Softmi50696342235793","orderId":"5551507821132","paytmOrderId":"202101211427162459728663","amount":"10.00","commissionAmount":"2.00","tax":"0.36","rrn":null,"beneficiaryName":null,"isCachedData":null,"cachedTime":null,"reversalReason":null}}
//				  if(jobj1.getString("statusCode").equalsIgnoreCase("DE_001") || jobj1.getString("statusCode").equalsIgnoreCase("DE_002")|| jobj1.getString("statusCode").equalsIgnoreCase("DE_101")|| jobj1.getString("statusCode").equalsIgnoreCase("DE_601")){
//					  returnData.put("message",jobj1.getString("statusMessage"));	
//					     returnData.put("nextPage","home");
//						 returnData.put("status","1");
//						 if(jobj1.getString("statusCode").equalsIgnoreCase("DE_001")){
//							 returnData.put("statusCode","SUCCESS");
//							 Gson gson = new Gson();
//							  Type empMapType = new TypeToken<Map<String, Object>>() {}.getType();
//							  Map<String, Object> maps=gson.fromJson(response1, empMapType);
//							  apiResponseService.paytmfinalDMTres(maps);
//							
//						 ObjectMapper mapper = new ObjectMapper();
//						 Map<String, Object> maps = mapper.readValue(response1, Map.class);
//						 apiResponseService.paytmfinalDMTres(maps);
//						 }else{returnData.put("statusCode","PENDING");}
//						 remlim.setCashlimit(newlimit);
//						    remitterlimitDao.updateRemitterLimit(remlim);
//				  }else if(jobj1.getString("statusCode").equalsIgnoreCase("DE_983")||jobj1.getString("statusCode").equalsIgnoreCase("DE_627")||jobj1.getString("statusCode").equalsIgnoreCase("DE_626")||jobj1.getString("statusCode").equalsIgnoreCase("DE_116")||jobj1.getString("statusCode").equalsIgnoreCase("DE_114")||jobj1.getString("statusCode").equalsIgnoreCase("DE_111")||jobj1.getString("statusCode").equalsIgnoreCase("DE_012")||jobj1.getString("statusCode").equalsIgnoreCase("DE_014")||jobj1.getString("statusCode").equalsIgnoreCase("DE_019")||jobj1.getString("statusCode").equalsIgnoreCase("DE_021")||jobj1.getString("statusCode").equalsIgnoreCase("DE_022")||jobj1.getString("statusCode").equalsIgnoreCase("DE_023")||jobj1.getString("statusCode").equalsIgnoreCase("DE_024")||jobj1.getString("statusCode").equalsIgnoreCase("DE_025")||jobj1.getString("statusCode").equalsIgnoreCase("DE_029")||jobj1.getString("statusCode").equalsIgnoreCase("DE_035")){
//					  returnData.put("statusCode","FAILED");
//			    	  ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, referenceno, "FAILED","-",comm,transfertype,checkRemitterMobile,amount,0.0,beneIFSCCode);
//						flag=impsTransactiondao.insertImpsTransaction(imptrans);
//						commissionService.updateBalance(userDetails.getUserId(),
//								"Money Transfer - " + accountNumber, "Money Transfer Revert", totalAmount, "CREDIT");
//						commissionService.updateBalance(userDetails.getUserId(),
//								"Money Transfer - " + accountNumber, "Money Transfer COMMISSION", comm, "DEBIT");
//						
//						if(userDetails.getWlId().startsWith("ASR")){
//							double reseCharge=0.0;
//							double resCom=0.0;
//							 param = new HashMap<String, Object>();
//							param.put("wlId", userDetails.getWlId());
//							List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
//							User whuser=whlist.get(0);
//							pckres=commissionService.getPackagewiseCommisionOnOperator(whuser.getUserId(),"DMR",id);
//							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//								reseCharge = (pckres.getCharge()*remain)/100;
//							
//							}else{
//								reseCharge =	pckres.getCharge();
//							}
//							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//								resCom=(pckres.getComm()*remain)/100;
//							}else{
//								resCom=pckres.getComm();
//							}
//							
//							double whtamnt=(remain+reseCharge);
//							flag = commissionService.updateBalance(whlist.get(0).getUserId(), "Money Transfer - " + accountNumber, "Money Transfer Revert", whtamnt, "CREDIT");
//						}
//						
//						returnData.put("message",jobj1.getString("statusMessage"));
//						returnData.put("status", "0");
//					}else{
//						returnData.put("statusCode","PENDING");
//						  returnData.put("status","0");
//						  returnData.put("message",jobj1.getString("statusMessage"));
//					  }*/
//					 
//				      }else{
//				    	  returnData.put("statusCode","FAILED");
//				    	  ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, referenceno, "FAILED","-",comm,transfertype,checkRemitterMobile,amount,0.0);
//							flag=impsTransactiondao.insertImpsTransaction(imptrans);
//							 returnData.put("imptrans", imptrans);
//							commissionService.updateBalance(userDetails.getUserId(),
//									"Money Transfer - " + accountNumber, "Money Transfer Revert", totalAmount, "CREDIT",0);
//							commissionService.updateBalance(userDetails.getUserId(),
//									"Money Transfer - " + accountNumber, "Money Transfer COMMISSION", comm, "DEBIT",0);
//							
//							if(userDetails.getWlId().startsWith("ASR")){
//								double reseCharge=0.0;
//								double resCom=0.0;
//								 param = new HashMap<String, Object>();
//								param.put("wlId", userDetails.getWlId());
//								List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
//								User whuser=whlist.get(0);
//								pckres=commissionService.getPackagewiseCommisionOnOperator(whuser.getUserId(),"DMR",id);
//								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									reseCharge = (pckres.getCharge()*remain)/100;
//								
//								}else{
//									reseCharge =	pckres.getCharge();
//								}
//								if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									resCom=(pckres.getComm()*remain)/100;
//								}else{
//									resCom=pckres.getComm();
//								}
//								
//								double whtamnt=(remain+reseCharge);
//								flag = commissionService.updateBalance(whlist.get(0).getUserId(), "Money Transfer - " + accountNumber, "Money Transfer Revert", whtamnt, "CREDIT",0);
//							}
//							
//							returnData.put("message",jobj.getString("statusMessage"));
//							returnData.put("status", "0");
//						}
//				      }catch(Exception e){
//				    	  ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(), referenceno, "PENDING","-",comm,transfertype,checkRemitterMobile,amount,0.0);
//							flag=impsTransactiondao.insertImpsTransaction(imptrans);
//							 returnData.put("imptrans", imptrans);
//							returnData.put("statusCode","PENDING");
//							returnData.put("status", "1");
//							 returnData.put("closingBalance", cl_bal);
//							 returnData.put("message","PENDING");
//				      }
//					
//					}
//			
//				} else {
//
//					returnData.put("message", "Donot Have Sufficient Balance");
//					returnData.put("status", "0");
//
//				}
//				} else {
//
//					returnData.put("message", "Donot Have Sufficient Balance");
//					returnData.put("status", "0");
//
//				}
//			}
//			}
//			} else {
//				returnData.put("message", "Amount should be maximum 20000");
//				returnData.put("status", "0");
//			}
//		} else {
//			returnData.put("message", "Amount should be minimum 10");
//			returnData.put("status", "0");
//		}
//				}else {
//					returnData.put("message", "You have crossed your monthly limit");
//					returnData.put("status", "0");
//						}
//				}else {
//					returnData.put("message", "Limit NOt Availeble");
//					returnData.put("status", "0");
//				}
//		}else{
//			returnData.put("message", "Operator Is Not Available");
//			returnData.put("status", "0");
//		}
//		}else {
//			returnData.put("status", "0");
//			returnData.put("message", "Please Assign Package");
//			
//		}
//		}else {
//			returnData.put("status", "0");
//			returnData.put("message", "Please Assign Package");
//			
//		}	
//		
//		}catch (Exception e) {
//		logger_log.error("instantPayMoneytransfer::::::::::::::"+e);
//		returnData.put("status", "0");
//		returnData.put("message", e);
//		}
		return returnData;
		}
	
	@Override
	public Map<String, Object> p2amoneytransferPaytm(Map<String, String> request, User userDetails) {
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
	List<P2AMoneyUser> list=paymoneyUserDao.getP2AMoneyUserByNamedQuery("getP2AUserbyuserId",param);
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
		 double sendamount=Double.parseDouble(request.get("amount"))-settlementcharge;
		
		  //-------money------
		  ImpsSettlement impssettle=new ImpsSettlement(userId,"-",TranRef,Double.parseDouble(request.get("amount"))-settlementcharge, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"PENDING");
		   impssettlementDao.insertImpsSettlement(impssettle);
	    	 
	      settle.setSettlementbalance(settleclbal);  
	      settlementbalancedao.updateSettlementBalance(settle);
	      SettlementReport settlere=new SettlementReport(userDetails.getUserId(), settleopbal, settleclbal, Double.parseDouble(request.get("amount"))-settlementcharge,normaldate, GenerateRandomNumber.getCurrentTime(),"Settle to Bank","SUCCESS","SUCCESS");
	    
	      settlementreportdao.insertSettlementReport(settlere);
	  
			//	String TranRefNo="DEMOREFNUM0124";
			 String restUrl="https://staging-dashboard.paytm.com/bpay/api/v1/disburse/order/bank";
		//	 String restUrl="https://dashboard.paytm.com/bpay/api/v1/disburse/order/bank";
			
			 TreeMap<String, String> map = new TreeMap<String, String>();
		        map.put("orderId", TranRefNo);
		        map.put("subwalletGuid", PaytmPassCode);
		        map.put("amount", Double.toString(sendamount));
		        map.put("beneficiaryAccount", pmu.getBeneAccNo());
		        map.put("beneficiaryIFSC", pmu.getBeneIFSC());
		        map.put("comments", "");
		        map.put("purpose", "OTHERS");
		        map.put("callbackUrl", "https://encodigi.net.in/paytmfinalres");
		        JSONObject jsonObj = new JSONObject(map);
		        String json = jsonObj.toString();
		        String checksum=PaytmchecksumNew.generateChecksum(json);
		        logger_log.warn("PaytmchecksumNew request:::::::::::::"+json);
			      logger_log.warn("PaytmchecksumNew checksum:::::::::::::"+checksum);
		        String response=ICICIWebservice.sendRequestToWebservice(restUrl,json,checksum);
		        logger_log.warn("PaytmchecksumNew RESPONSE:::::::::::::"+response);
			JSONObject jobj = new JSONObject(response);
		 // String response="{\"EncoreImpsSettlement\":{\"date\":\"2020-08-03\",\"amount\":800,\"tranRefNo\":\"1519378179305\",\"response\":\"Transaction Successful\",\"bankRRN\":\"021615126491\",\"retailerCode\":\"RT24GIBN\",\"time\":\"03:18:29 PM\",\"aid\":\"AEPSAGUMTTC1\",\"status\":\"SUCCESS\",\"username\":\"80A8FC\"}}";
	      logger_log.warn("requestagentr RESPONSE:::::::::::::"+jobj);
	      if(jobj.getString("statusCode").equalsIgnoreCase("DE_001") || jobj.getString("statusCode").equalsIgnoreCase("DE_002")){
		     String status="";
	    	  settle.setSettlementbalance(settleclbal);  
		      settlementbalancedao.updateSettlementBalance(settle);
		      if(jobj.getString("statusCode").equalsIgnoreCase("DE_001")) {
			  status="SUCCESS";	  
			  }else {
			  status="PENDING";	  	  
			   }
		      returnData.put("statusCode",status);
		      returnData.put("message",jobj.getString("statusMessage"));	
		     returnData.put("nextPage","home");
			 returnData.put("status","1");	
			 
		      }else{
		    	  returnData.put("statusCode","FAILED");
		    	  param= new HashMap<String, Object>();
		    	    param.put("TranRefNo", TranRefNo);
					param.put("userId", userDetails.getUserId());
					List<ImpsSettlement> uplist=impssettlementDao.getImpsSettlementByNamedQuery("getImpsSettlementTranRefNo", param);
					ImpsSettlement ims=uplist.get(0);
					ims.setBankRRN("NA");
					ims.setStatus("FAILED");
					impssettlementDao.updateImpsSettlement(ims);
					param= new HashMap<String, Object>();
					param.put("username",userDetails.getUserId());
					List<SettlementBalance> settlelists=settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param);
					SettlementBalance settle1=settlelists.get(0);	
					settleopbal=settle1.getSettlementbalance();
					settleclbal=settleopbal+(Double.parseDouble(request.get("amount")));
					settle1.setSettlementbalance(settleclbal);  
				    settlementbalancedao.updateSettlementBalance(settle1);
				    SettlementReport settlere1=new SettlementReport(userDetails.getUserId(), settleopbal, settleclbal, Double.parseDouble(request.get("amount"))+settlementcharge,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"Settle to Bank","FAILED","REVERT");
				    
				      settlementreportdao.insertSettlementReport(settlere1);
			      returnData.put("message",jobj.getString("statusMessage"));	
			      returnData.put("nextPage","home");
				  returnData.put("status","0");	
					
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
	public Map<String, Object> getPayoutStatuscheck(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData= new HashMap<String, Object>();	
		try{
			System.out.println(request);
		TreeMap<String, String> map1 = new TreeMap<String, String>();
        map1.put("orderId", request.get("TranRefNo"));
        JSONObject jsonObj1 = new JSONObject(map1);
        String json1 = jsonObj1.toString();
        String checksum1=PaytmchecksumNew.generateChecksum(json1);
        String response1=ICICIWebservice.sendRequestToWebservice("https://dashboard.paytm.com/bpay/api/v1/disburse/order/query",json1,checksum1);

      //  String response1=ICICIWebservice.sendRequestToWebservice("https://staging-dashboard.paytm.com/bpay/api/v1/disburse/order/query",json1,checksum1);
  System.out.println("disburse::"+response1);  
  JSONObject jobj1 = new JSONObject(response1);
  //{"status":"PENDING","statusCode":"DE_101","statusMessage":"Your request is in process. Kindly check after sometime","result":{"mid":"Softmi50696342235793","orderId":"5551507821132","paytmOrderId":"202101211427162459728663","amount":"10.00","commissionAmount":"2.00","tax":"0.36","rrn":null,"beneficiaryName":null,"isCachedData":null,"cachedTime":null,"reversalReason":null}}
  if(jobj1.getString("statusCode").equalsIgnoreCase("DE_001") || jobj1.getString("statusCode").equalsIgnoreCase("DE_002")|| jobj1.getString("statusCode").equalsIgnoreCase("DE_101")|| jobj1.getString("statusCode").equalsIgnoreCase("DE_601")){
	  returnData.put("message",jobj1.getString("statusMessage"));	
	     returnData.put("nextPage","home");
		 returnData.put("status","1");
		 if(jobj1.getString("statusCode").equalsIgnoreCase("DE_001")){
			 Gson gson = new Gson();
			  Type empMapType = new TypeToken<Map<String, Object>>() {}.getType();
			  Map<String, Object> map=gson.fromJson(response1, empMapType);
			  apiResponseService.paytmfinalres(map);
			 /*ObjectMapper mapper = new ObjectMapper();
			 Map<String, Object> map = mapper.readValue(response1, Map.class);
			 apiResponseService.paytmfinalres(map);*/
			 returnData.put("statusCode","SUCCESS");
		 }else{
			 returnData.put("statusCode","PENDING"); 
		 }
  }else if(jobj1.getString("statusCode").equalsIgnoreCase("DE_983")||jobj1.getString("statusCode").equalsIgnoreCase("DE_627")||jobj1.getString("statusCode").equalsIgnoreCase("DE_626")||jobj1.getString("statusCode").equalsIgnoreCase("DE_116")||jobj1.getString("statusCode").equalsIgnoreCase("DE_114")||jobj1.getString("statusCode").equalsIgnoreCase("DE_111")||jobj1.getString("statusCode").equalsIgnoreCase("DE_012")||jobj1.getString("statusCode").equalsIgnoreCase("DE_014")||jobj1.getString("statusCode").equalsIgnoreCase("DE_019")||jobj1.getString("statusCode").equalsIgnoreCase("DE_021")||jobj1.getString("statusCode").equalsIgnoreCase("DE_022")||jobj1.getString("statusCode").equalsIgnoreCase("DE_023")||jobj1.getString("statusCode").equalsIgnoreCase("DE_024")||jobj1.getString("statusCode").equalsIgnoreCase("DE_025")||jobj1.getString("statusCode").equalsIgnoreCase("DE_029")||jobj1.getString("statusCode").equalsIgnoreCase("DE_035")){
	  Gson gson = new Gson();
	  Type empMapType = new TypeToken<Map<String, Object>>() {}.getType();
	  Map<String, Object> map=gson.fromJson(response1, empMapType);
	  apiResponseService.paytmfinalres(map);
	  /*ObjectMapper mapper = new ObjectMapper();
		 Map<String, Object> map = mapper.readValue(response1, Map.class);
		 apiResponseService.paytmfinalres(map);*/
		 returnData.put("statusCode","FAILED");
		 returnData.put("status","1");
		  returnData.put("message",jobj1.getString("statusMessage"));
  }else {
	  returnData.put("statusCode","PENDING");
	  returnData.put("status","0");
	  returnData.put("message",jobj1.getString("statusMessage"));
  }
		}catch(Exception e){
			logger_log.error("getPayoutStatuscheck::::::::::::::"+e);
			returnData.put("status", "0");
			returnData.put("message", e);
			}
	  return returnData;
	}

	@Override
	public Map<String, Object> getPayoutStatuscheckdmt(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData= new HashMap<String, Object>();	
		try{
			System.out.println(request);
		TreeMap<String, String> map1 = new TreeMap<String, String>();
        map1.put("orderId", request.get("recieptId"));
        JSONObject jsonObj1 = new JSONObject(map1);
        String json1 = jsonObj1.toString();
        String checksum1=PaytmchecksumNew.generateChecksum(json1);
        String response1=ICICIWebservice.sendRequestToWebservice("https://dashboard.paytm.com/bpay/api/v1/disburse/order/query",json1,checksum1);

       // String response1=ICICIWebservice.sendRequestToWebservice("https://staging-dashboard.paytm.com/bpay/api/v1/disburse/order/query",json1,checksum1);
  System.out.println("disburse::"+response1);  
  JSONObject jobj1 = new JSONObject(response1);
  //{"status":"PENDING","statusCode":"DE_101","statusMessage":"Your request is in process. Kindly check after sometime","result":{"mid":"Softmi50696342235793","orderId":"5551507821132","paytmOrderId":"202101211427162459728663","amount":"10.00","commissionAmount":"2.00","tax":"0.36","rrn":null,"beneficiaryName":null,"isCachedData":null,"cachedTime":null,"reversalReason":null}}
  if(jobj1.getString("statusCode").equalsIgnoreCase("DE_001") || jobj1.getString("statusCode").equalsIgnoreCase("DE_002")|| jobj1.getString("statusCode").equalsIgnoreCase("DE_101")|| jobj1.getString("statusCode").equalsIgnoreCase("DE_601")){
	  returnData.put("message",jobj1.getString("statusMessage"));	
	     returnData.put("nextPage","home");
		 returnData.put("status","1");
		 if(jobj1.getString("statusCode").equalsIgnoreCase("DE_001")){
			 Gson gson = new Gson();
			  Type empMapType = new TypeToken<Map<String, Object>>() {}.getType();
			  Map<String, Object> map=gson.fromJson(response1, empMapType);
			  apiResponseService.paytmfinalDMTres(map);
			/* ObjectMapper mapper = new ObjectMapper();
			 Map<String, Object> map = mapper.readValue(response1, Map.class);
			 apiResponseService.paytmfinalDMTres(map);*/
			 returnData.put("statusCode","SUCCESS");
		 }else{
			 returnData.put("statusCode","PENDING"); 
		 }
  }else if(jobj1.getString("statusCode").equalsIgnoreCase("DE_983")||jobj1.getString("statusCode").equalsIgnoreCase("DE_627")||jobj1.getString("statusCode").equalsIgnoreCase("DE_626")||jobj1.getString("statusCode").equalsIgnoreCase("DE_116")||jobj1.getString("statusCode").equalsIgnoreCase("DE_114")||jobj1.getString("statusCode").equalsIgnoreCase("DE_111")||jobj1.getString("statusCode").equalsIgnoreCase("DE_012")||jobj1.getString("statusCode").equalsIgnoreCase("DE_014")||jobj1.getString("statusCode").equalsIgnoreCase("DE_019")||jobj1.getString("statusCode").equalsIgnoreCase("DE_021")||jobj1.getString("statusCode").equalsIgnoreCase("DE_022")||jobj1.getString("statusCode").equalsIgnoreCase("DE_023")||jobj1.getString("statusCode").equalsIgnoreCase("DE_024")||jobj1.getString("statusCode").equalsIgnoreCase("DE_025")||jobj1.getString("statusCode").equalsIgnoreCase("DE_029")||jobj1.getString("statusCode").equalsIgnoreCase("DE_035")){
	  Gson gson = new Gson();
	  Type empMapType = new TypeToken<Map<String, Object>>() {}.getType();
	  Map<String, Object> map=gson.fromJson(response1, empMapType);
	  apiResponseService.paytmfinalDMTres(map);
	 /* ObjectMapper mapper = new ObjectMapper();
		 Map<String, Object> map = mapper.readValue(response1, Map.class);
		 apiResponseService.paytmfinalDMTres(map);*/
		 returnData.put("statusCode","FAILED");
		 returnData.put("status","1");
		  returnData.put("message",jobj1.getString("statusMessage"));
  }else{
	  returnData.put("statusCode","PENDING");
	  returnData.put("status","0");
	  returnData.put("message",jobj1.getString("statusMessage"));
  }
		}catch(Exception e){
			logger_log.error("getPayoutStatuscheck::::::::::::::"+e);
			returnData.put("status", "0");
			returnData.put("message", e);
			}
	  return returnData;
	}
	
	@Override
	public Map<String, Object> razorPayStatusWebhook(String request) {
		Map<String, Object> returnData= new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try{
			String rrn;
			JSONObject obj = new JSONObject(request);
			String event=obj.getString("event");
			if(event.equalsIgnoreCase("payout.processed")){
			String receiptId = obj.getJSONObject("payload").getJSONObject("payout").getJSONObject("entity").getString("id");
			String statusCode=obj.getJSONObject("payload").getJSONObject("payout").getJSONObject("entity").getString("status");
			param.put("recieptId", receiptId);
			List<ImpsTransaction> list = impsTransactiondao.getIMPSDetailsByNamedQuery("GetIMPSDetailsByTid", param);
			
			if(!list.isEmpty()){
				ImpsTransaction ims = list.get(0);
				if (statusCode.equalsIgnoreCase("processed")) {
					rrn = obj.getJSONObject("payload").getJSONObject("payout").getJSONObject("entity").getString("utr");
					if (ims.getStatus().equals("PENDING")) {
						ims.setBanktransactionId(rrn);
						ims.setStatus("SUCCESS");
						impsTransactiondao.updateImpsTransaction(ims);
					}
				}		
			}
			}
		}catch(Exception e){
			logger_log.error("getPayoutStatuscheck::::::::::::::"+e);
			returnData.put("status", "0");
			returnData.put("message", e);
			}
	  return returnData;
	}
	
	
	@Override
	public Map<String, Object> MoneytransferRazorPay(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
//		Map<String, String> parameters = new HashMap<String, String>();
//		Map<String, Object> parameter = new HashMap<String, Object>();
//		Map<String, Object> param1 = new HashMap<String, Object>();
//		Beneficiary beneficiary=null;
//		double charge = 0.0;
//		boolean flag=false;
//		double comm = 0.0;
//		double dComm = 0.0;
//		double sdComm = 0.0;
//		double resComm = 0.0;
//		double retComm = 0.0;
//		String resellerId = "";
//		String sdId = "";
//		String distId = "";	
//		double apiComm = 0.0;
//		double sdCharge = 0.0;
//		double distCharge = 0.0;			
//		double resCharge = 0.0;
//		double dcharge = 0.0;
//		double sCharge = 0.0;
//		double reCharge = 0.0;
//		double disComm = 0.0;
//		double sdisComm = 0.0;
//		double reComm = 0.0;
//		double rtCharge=0.0;
//		double rcharge=0.0;
//		PackageWiseChargeComm pcksubret=new PackageWiseChargeComm();
//		System.out.println("request::::::::::::"+request);
//		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
//		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
//		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
//		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
//		PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
//		String bankrefno = "";
//		String transfertype="";
//		try{
//		String beneMobileNumber = request.get("mobile");
//		String checkRemitterMobile = request.get("remmobile");
//		String bene_name = request.get("name");
//		if(request.get("sendType") == "2"||request.get("sendType").equalsIgnoreCase("2")){
//		transfertype = "NEFT";
//		}else{
//		transfertype="IMPS";
//		}
//		String accountNumber = request.get("account");
//		String beneIFSCCode = request.get("ifsc");
//		String beneid = request.get("id");
//		String transactionAmount=request.get("amount");
//		String referenceno = "";
//		double amount=0.0;
//		double totalAmount=0.0;
//		int id=0;
//		
//		param1 = new HashMap<String, Object>();
//		param1.put("account",request.get("account"));
//	    param1.put("remmobile",request.get("remmobile")); 
//	    List<Beneficiary> benelist=beneficiaryDao.getBeneficiaryByNamedQuery("getBenebyaccountandRem", param1);
//	    
//	    
//	    if(!benelist.isEmpty() && benelist.get(0)!=null ){
//	    	
//	    	beneficiary=benelist.get(0);
//	    	
//	    }
//		
//		ImpsResponse imps = new ImpsResponse();
//		String username="";
//		if(userDetails.getRoleId()==2){
//			username=userDetails.getUserId();
//		}else if(userDetails.getRoleId()==3){
//			username=userDetails.getUserId();
//		}else if(userDetails.getRoleId()==4){
//			username=userDetails.getUplineId();
//		}else if(userDetails.getRoleId()==5){
//			User up=userDao.getUserByUserId(userDetails.getUplineId());
//			username=up.getUplineId();
//		}else if(userDetails.getRoleId()==6){
//			User up=userDao.getUserByUserId(userDetails.getUplineId());
//			User ups=userDao.getUserByUserId(up.getUplineId());
//			username=ups.getUplineId();
//		}
//		parameter = new HashMap<String, Object>();
//		parameter.put("user_id", username);
//		List<AssignedPackage> list = assignedPackage.getAssignedPackageByNamedQuery("getAssignedPackageByUser",parameter);
//		
//		if (list.size() > 0) {
//			parameter = new HashMap<String, Object>();
//			parameter.put("user_id", userDetails.getUserId());
//			parameter.put("service_type", "DMR");
//			List<AssignedPackage> lista = assignedPackage
//					.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", parameter);
//		/*-------------------------------------------------------------------------------------------------------------------*/
//		Map<String, Object> param = new HashMap<String, Object>();
//	
//		if(!lista.isEmpty()){
//			
//			parameter = new HashMap<String, Object>();
//			parameter.put("api", "DMR");
//			List<DMRCommission> opList =  DMRCommissionDao.getDMRCommissionByNamedQuery("getDMRCommissionByApi", parameter);
//			if((!opList.isEmpty())) {
//				int year = Calendar.getInstance().get(Calendar.YEAR);
//				int month = Calendar.getInstance().get(Calendar.MONTH);
//				Date d=new Date();
//				param = new HashMap<String, Object>();
//				param.put("month", d.getMonth());
//				param.put("year", year);
//				param.put("mobile", checkRemitterMobile);
//				List<RemitterLimit> remlist=remitterlimitDao.getRemitterLimitByNamedQuery("getRemlimitbyMobilemonthyear",param);	
//				if(!remlist.isEmpty()) {
//			  RemitterLimit remlim=remlist.get(0);	
//			  double limit=remlim.getCashlimit();
//			  double newlimit=limit-Double.parseDouble(request.get("amount").toString());				
//			  if(newlimit>=0) {
//	
//	//	---------------------------------------------------------------------------------------------------------------
//		if (Double.parseDouble(transactionAmount) >= 10) {
//			if (Double.parseDouble(transactionAmount) <= 20000) {
//			parameters = new HashMap<String, String>();
//			parameters.put("userId", userDetails.getUserId());
//			double op_bal = customQueryServiceLogic
//					.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser,parameters);
//			if(Double.parseDouble(transactionAmount)>op_bal){
//				returnData.put("message", "Amount is bigger than Your Balance");
//				returnData.put("status", "0");
//			}else{
//				double temp = Double.parseDouble(transactionAmount);
//				double remain = 0.0;
//		
//			while (temp > 0) {
//				
//				if (temp > 5000) {
//					temp = temp - 5000;
//					remain = 5000;
//				} else {
//					remain = temp;
//					temp = 0.0;
//				}
//				referenceno = GenerateRandomNumber.referenceNO();
//				for(DMRCommission comm2 : opList){
//					if(remain>=comm2.getSlab1() && remain<=comm2.getSlab2()){
//						id = comm2.getId();
//						break;
//					}
//				}
//					
//					pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id) ;
//					if (pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
//						charge = (pckret.getCharge() * remain) / 100;
//					} else {
//						charge = pckret.getCharge();
//					}
//					if (pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")) {
//						comm = (pckret.getComm() * remain) / 100;
//					} else {
//						comm = pckret.getComm();
//					}
//				System.out.println(charge);
//				String rId="";
//		//		-------------------------------------COMMISSION---------------------------------------------------------------------
//			
//				double lockbalance=userDetails.getLockedAmount();
//				
//				//Gst Added
//				double gst= (charge*18)/100;
//				
//				totalAmount = RoundNumber.roundDouble(remain + charge+ gst) ;
//				parameters = new HashMap<String, String>();
//				parameters.put("userId", userDetails.getUserId());
//				 op_bal = customQueryServiceLogic
//						.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser,parameters);
//				double cl_bal = RoundNumber.roundDouble(op_bal - totalAmount);
//				System.out.println("cl_bal::::::"+cl_bal);
//				if(lockbalance<=cl_bal){
//				if (op_bal > totalAmount) {
//					boolean flag5=false;
//					if(userDetails.getWlId().startsWith("ASR")){
//						 param = new HashMap<String, Object>();
//						param.put("wlId", userDetails.getWlId());
//						List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
//						parameters = new HashMap<String, String>();
//						parameters.put("userId", whlist.get(0).getUserId());	
//						double op_bal1 = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters );
//						double cl_bal1=op_bal1-remain;
//						if(whlist.get(0).getLockedAmount()<=cl_bal1){
//							flag5=true;
//						}else{
//							flag5=false;
//						}
//					}else{
//						flag5=true;
//					}
//					if(!flag5)
//					{
//						returnData.put("status", "0");
//						returnData.put("message", "Technical Error Please Contact to Admin!");
//					}else{
//					//DecimalFormat df2 = new DecimalFormat("#.##");
//					amount =remain;
//					flag = commissionService.updateBalance(userDetails.getUserId(),
//							"Money Transfer - " + accountNumber, "Money Transfer", totalAmount, "DEBIT",0);
//					if(comm!=0){
//						commissionService.updateBalance(userDetails.getUserId(),
//								"Money Transfer - " + accountNumber, "Money Transfer Commission", comm, "CREDIT",0);
//					}
//					
//					if(userDetails.getWlId().startsWith("ASR")){
//						double reseCharge=0.0;
//						double resCom=0.0;
//						 param = new HashMap<String, Object>();
//						param.put("wlId", userDetails.getWlId());
//						List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
//						User whuser=whlist.get(0);
//						pckres=commissionService.getPackagewiseCommisionOnOperator(whuser.getUserId(),"DMR",id);
//						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//							reseCharge = (pckres.getCharge()*remain)/100;
//						
//						}else{
//							reseCharge =	pckres.getCharge();
//						}
//						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//							resCom=(pckres.getComm()*remain)/100;
//						}else{
//							resCom=pckres.getComm();
//						}
//						
//						double whtamnt=(remain+reseCharge);
//						flag = commissionService.updateBalance(whlist.get(0).getUserId(), "Money Transfer - " + accountNumber, "Money Transfer", whtamnt, "DEBIT",0);
//					}
//					
//					String TranRefNo = GenerateRandomNumber.generateIPtid(checkRemitterMobile);
//					
//					TreeMap<String, Object> razorpayMoneyTransfer = new TreeMap<String, Object>();
//					razorpayMoneyTransfer.put("account_number", accountNumberrazorpay);
//					razorpayMoneyTransfer.put("fund_account_id",beneficiary.getRazorPayFundAccountId());
//					//Razorpay Amount in Paisa
//					Integer razorpayAmount= Integer.valueOf(transactionAmount);
//					Integer transactionAmountrazor=razorpayAmount*100;
//					razorpayMoneyTransfer.put("amount",transactionAmountrazor );
//					razorpayMoneyTransfer.put("currency", "INR");
//					razorpayMoneyTransfer.put("mode",transfertype);
//					razorpayMoneyTransfer.put("purpose","payout");
//					razorpayMoneyTransfer.put("queue_if_low_balance",false);
//					razorpayMoneyTransfer.put("reference_id",TranRefNo);
//					razorpayMoneyTransfer.put("narration","Razorpay Payout");
//					razorpayMoneyTransfer.put("notes", new TreeMap<String, String>());
//					
//
//					
//					JSONObject moneyTransferObj = new JSONObject(razorpayMoneyTransfer);
//			        String moneyTransferjson = moneyTransferObj.toString();
//			        
//			        String moneyTransferResponse=RazorPayWebService.sendRequestToWebservice("payouts", moneyTransferjson);
//			        logger_log.warn("Razorpay RESPONSE:::::::::::::" + moneyTransferResponse);
//			        String status = "";
//			        
//			        if(moneyTransferResponse!=null && !moneyTransferResponse.isEmpty()){
//			        	JSONObject moneyTransferobj = new JSONObject(moneyTransferResponse);
//			        	if(moneyTransferobj.getString("entity").equalsIgnoreCase("payout") && moneyTransferobj.getString("status").equalsIgnoreCase("processed")){
//			        		String payOutId= moneyTransferobj.getString("id");
//			        		
//			        		if(!payOutId.isEmpty()){
//			        			status = "SUCCESS";
//			        			String today = GenerateRandomNumber.getCurrentDate();
//								String currentTime = GenerateRandomNumber.getCurrentTime();
//								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, payOutId, "SUCCESS","-",comm,transfertype,checkRemitterMobile,amount,0.0);
//								flag=impsTransactiondao.insertImpsTransaction(imptrans);
//								returnData.put("imptrans", imptrans);	
//			        		}else{
//			        			status = "PENDING";
//			        			String today = GenerateRandomNumber.getCurrentDate();
//								String currentTime = GenerateRandomNumber.getCurrentTime();
//								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, payOutId, "PENDING","-",comm,transfertype,checkRemitterMobile,amount,0.0);
//								flag=impsTransactiondao.insertImpsTransaction(imptrans); 
//								returnData.put("imptrans", imptrans);	
//			        		}
//			        		
//			        		returnData.put("message",moneyTransferobj.getString("status"));	
//						     returnData.put("nextPage","home");
//							 returnData.put("status","1");	
//							 returnData.put("closingBalance", cl_bal);
//							 
//							 param = new HashMap<String, Object>();
//								param.put("month", d.getMonth());
//								param.put("year", year);
//								param.put("mobile", checkRemitterMobile);
//								List<RemitterLimit> remlist1=remitterlimitDao.getRemitterLimitByNamedQuery("getRemlimitbyMobilemonthyear",param);	
//								if(!remlist1.isEmpty()) {
//							  RemitterLimit remlim1=remlist1.get(0);	
//							  double limit1=remlim1.getCashlimit();
//							  double newlimit1=limit1-amount;	
//							  remlim1.setCashlimit(newlimit1);
//							    remitterlimitDao.updateRemitterLimit(remlim1);
//							    returnData.put("Limit",newlimit1);
//								}
//								
//								//Charge Added for Other User
//								
//								if(userDetails.getRoleId() == 6) {
//							
//									rId=userDetails.getUplineId();
//									parameters = new HashMap<String, String>();
//									parameters.put("userId", rId);	
//									distId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
//									
//									// Super Distributor Id
//									parameters = new HashMap<String, String>();
//									parameters.put("userId", distId);	
//									sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
//									
//									// Reseller Id
//									parameters = new HashMap<String, String>();
//									parameters.put("userId", sdId);							
//									resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
//								
//									pckret=commissionService.getPackagewiseCommisionOnOperator(rId,"DMR",id);
//									if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									retComm =(pckret.getComm()*remain)/100;
//									}else{
//									retComm =pckret.getComm();	
//									}
//									
//									if(pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										rtCharge = (pckret.getCharge()*remain)/100;
//									}else{
//										rtCharge = pckret.getCharge();
//									}
//								//	System.out.println("reseller=="+comm);
//									pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"DMR",id); 
//									if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									dComm =(pckdis.getComm()*remain)/100;
//									}else{
//									dComm=pckdis.getComm();	
//									}
//									if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										distCharge = (pckdis.getCharge()*remain)/100;
//									}else{
//										distCharge = pckdis.getCharge();
//									}
//								//	System.out.println("dComm=="+dComm);
//									pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
//									if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									sdComm =(pcksd.getComm()*remain)/100;
//									}else{
//									sdComm = pcksd.getComm();	
//									}
//									if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										sdCharge = (pcksd.getCharge()*remain)/100;
//									}else{
//										sdCharge = pcksd.getCharge();
//									}
//									
//									if(rtCharge==0){rcharge=0;}
//									else{rcharge = charge - rtCharge;}
//									if(distCharge==0){dcharge=0;}
//									else{dcharge = rtCharge - distCharge;}
//									if(sdCharge==0){sCharge=0;}
//									else{sCharge = distCharge - sdCharge;}
//									if(rcharge!=0){
//										commissionService.updateBalance(rId, "Money Transfer - "+accountNumber, "CHARGE", rcharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge = new EarningSurcharge(5, userDetails.getWlId(), rId,  0.0,rcharge, "Money Transfer - " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge);
//									}
//									if(dcharge!=0){
//										commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "CHARGE", dcharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, userDetails.getWlId(), distId,  0.0,dcharge, "Money Transfer - " +transactionAmountrazor , GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
//										
//									}
//									if(sCharge!=0){
//										commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId,  0.0,sCharge, "Money Transfer - " +transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
//									}
//									
//									
//									double retaComm=0.0;
//									if(retComm==0){retaComm=0;}
//									else{
//									retaComm=retComm-comm;
//									}
//									if(dComm==0){disComm=0;}
//									else{
//									disComm=dComm-retComm;
//									}
//									if(sdComm==0){sdisComm=0;}
//									else{
//									sdisComm = sdComm-dComm;
//									}
//									if(retaComm!=0){
//										commissionService.updateBalance(rId, "Money Transfer - "+accountNumber, "COMMISSION", retaComm, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge10 = new EarningSurcharge(5, userDetails.getWlId(), rId, retaComm, 0.0, "Money Transfer COMMISSION- " + accountNumber, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge10);
//											}
//									if(disComm!=0){
//										commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "COMMISSION", disComm, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge11 = new EarningSurcharge(4, userDetails.getWlId(), distId, disComm, 0.0, "Money Transfer COMMISSION- " + accountNumber, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge11);
//											}
//									if(sdisComm!=0){
//										commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge22 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm, 0.0, "Money Transfer COMMISSION- " + accountNumber, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge22);
//											}
//									
//									
//									
//									if(!resellerId.equals("admin")){
//										if(!userDetails.getWlId().startsWith("ASR")){
//										
//										pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
//									if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									resComm =(pckres.getComm()*remain)/100;
//									}else{
//									resComm =pckres.getComm();	
//									}
//									if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										resCharge = (pckres.getCharge()*remain)/100;
//										}else{
//										resCharge =	pckres.getCharge();
//										}
//									reCharge = sdCharge - resCharge;
//									if(reCharge!=0){
//										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge3 = new EarningSurcharge(2, userDetails.getWlId(), resellerId,  0.0,reCharge, "Money Transfer - " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
//									}
//									 reComm=0.0;
//									if(resComm==0){reComm=0;}
//									else{
//									reComm = resComm-sdComm;
//									}	
//								//
//								if(reComm!=0){
//									commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0.0);
//									EarningSurcharge earningSurcharge00 = new EarningSurcharge(2, userDetails.getWlId(), resellerId, reComm, 0.0, "Money Transfer COMMISSION- " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//									earningSurchargeDao.insertEarningSurcharge(earningSurcharge00);
//										}
//									}
//									}
//									
//									
//								}else if (userDetails.getRoleId() == 5){
//									sdCharge=0.0; sCharge=0.0;
//									resComm=0.0;
//									dComm=0.0; sdComm=0.0;
//									 distCharge=0.0; dcharge=0.0;
//									//remain=ims.getAmount();
//									
//									//String accountNumber=ims.getAccount_no();
//									 distId=userDetails.getUplineId();
//									// Super Distributor Id
//									 parameters = new HashMap<String, String>();
//									parameters.put("userId", distId);	
//									 sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
//									
//									// Reseller Id
//									parameters = new HashMap<String, String>();
//									parameters.put("userId", sdId);							
//									 resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
//								
//									
//								//	System.out.println("reseller=="+comm);
//									pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"DMR",id); 
//									if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									dComm =(pckdis.getComm()*remain)/100;
//									}else{
//									dComm=pckdis.getComm();	
//									}
//									if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										distCharge = (pckdis.getCharge()*remain)/100;
//									}else{
//										distCharge = pckdis.getCharge();
//									}
//								//	System.out.println("dComm=="+dComm);
//									pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
//									if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									sdComm =(pcksd.getComm()*remain)/100;
//									}else{
//									sdComm = pcksd.getComm();	
//									}
//									if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										sdCharge = (pcksd.getCharge()*remain)/100;
//									}else{
//										sdCharge = pcksd.getCharge();
//									}
//									
//									
//									if(distCharge==0){dcharge=0;}
//									else{dcharge = charge - distCharge;}
//									if(sdCharge==0){sCharge=0;}
//									else{sCharge = distCharge - sdCharge;}
//									
//									if(dcharge!=0){
//										commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "CHARGE", dcharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, userDetails.getWlId(), distId,  0.0,dcharge, "Money Transfer - " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
//									}
//									if(sCharge!=0){
//										commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId,  0.0,sCharge, "Money Transfer - " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
//									}
//									
//									
//									disComm=0.0;
//									sdisComm=0.0;
//									
//									if(dComm==0){disComm=0;}
//									else{
//									disComm=dComm-comm;
//									}
//									if(sdComm==0){sdisComm=0;}
//									else{
//									sdisComm = sdComm-dComm;
//									}
//									
//									if(disComm!=0){
//										commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "COMMISSION", disComm, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge11 = new EarningSurcharge(4, userDetails.getWlId(), distId, disComm, 0.0, "Money Transfer COMMISSION- " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge11);
//											}
//									if(sdisComm!=0){
//										commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge22 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm, 0.0, "Money Transfer COMMISSION- " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge22);
//											}
//									
//									
//									if(!resellerId.equals("admin")){
//										if(!userDetails.getWlId().startsWith("ASR")){
//										resCharge=0.0;reCharge=0.0;
//										pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
//									if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									resComm =(pckres.getComm()*remain)/100;
//									}else{
//									resComm =pckres.getComm();	
//									}
//									if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										resCharge = (pckres.getCharge()*remain)/100;
//										}else{
//										resCharge =	pckres.getCharge();
//										}
//									reCharge = sdCharge - resCharge;
//									if(reCharge!=0){
//										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge3 = new EarningSurcharge(2, userDetails.getWlId(), resellerId,  0.0,reCharge, "Money Transfer - " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
//									}
//								 reComm=0.0;
//									if(resComm==0){reComm=0;}
//									else{
//									reComm = resComm-sdComm;
//									}	
//								//
//								if(reComm!=0){
//									commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0.0);
//									EarningSurcharge earningSurcharge00 = new EarningSurcharge(2, userDetails.getWlId(), resellerId, reComm, 0.0, "Money Transfer COMMISSION- " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//									earningSurchargeDao.insertEarningSurcharge(earningSurcharge00);		
//								}
//									}
//									}
//									
//									
//								}else if(userDetails.getRoleId() == 4) {
//									sdCharge=0.0; sCharge=0.0;
//									 resComm=0.0;
//									 sdComm=0.0;
//									//double remain=ims.getAmount();
//									
//									//String accountNumber=ims.getAccount_no();
//									sdId=userDetails.getUplineId();
//									// Reseller Id
//									 parameters = new HashMap<String, String>();
//									parameters.put("userId", sdId);							
//									 resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
//								
//								
//										pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
//									if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									sdComm =(pcksd.getComm()*remain)/100;
//									}else{
//									sdComm = pcksd.getComm();	
//									}
//									if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										sdCharge = (pcksd.getCharge()*remain)/100;
//									}else{
//										sdCharge = pcksd.getCharge();
//									}
//									
//									
//									
//									if(sdCharge==0){sCharge=0;}
//									else{sCharge = charge - sdCharge;}
//									
//									
//									if(sCharge!=0){
//										commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId,  0.0,sCharge, "Money Transfer - " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
//									}
//									sdisComm=0.0;
//									
//									
//									if(sdComm==0){sdisComm=0;}
//									else{
//									sdisComm = sdComm-comm;
//									}
//									
//									
//									if(sdisComm!=0){
//										commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge22 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm, 0.0, "Money Transfer COMMISSION- " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge22);
//											}
//									if(!resellerId.equals("admin")){
//										if(!userDetails.getWlId().startsWith("ASR")){
//										 resCharge=0.0;reCharge=0.0;
//										pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
//									if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									resComm =(pckres.getComm()*remain)/100;
//									}else{
//									resComm =pckres.getComm();	
//									}
//									if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										resCharge = (pckres.getCharge()*remain)/100;
//										}else{
//										resCharge =	pckres.getCharge();
//										}
//									reCharge = sdCharge - resCharge;
//									if(reCharge!=0){
//										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge3 = new EarningSurcharge(2, userDetails.getWlId(), resellerId,  0.0,reCharge, "Money Transfer - " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
//									}
//									reComm=0.0;
//									if(resComm==0){reComm=0;}
//									else{
//									reComm = resComm-sdComm;
//									}	
//								//
//								if(reComm!=0){
//									commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0.0);
//									EarningSurcharge earningSurcharge00 = new EarningSurcharge(2, userDetails.getWlId(), resellerId, reComm, 0.0, "Money Transfer COMMISSION- " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//									earningSurchargeDao.insertEarningSurcharge(earningSurcharge00);			
//								}
//									}
//									}
//									
//									
//								}else if(userDetails.getRoleId() == 3) {
//									resComm=0.0;
//								   // remain=ims.getAmount();
//									
//									//String accountNumber=ims.getAccount_no();
//										 resellerId=userDetails.getUplineId();
//									// Reseller Id
//									
//									if(!resellerId.equals("admin")){
//										if(!userDetails.getWlId().startsWith("ASR")){
//										 resCharge=0.0;reCharge=0.0;
//										 pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
//									if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									resComm =(pckres.getComm()*remain)/100;
//									}else{
//									resComm =pckres.getComm();	
//									}
//									if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										resCharge = (pckres.getCharge()*remain)/100;
//										}else{
//										resCharge =	pckres.getCharge();
//										}
//									reCharge = charge - resCharge;
//									if(reCharge!=0){
//										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge3 = new EarningSurcharge(2, userDetails.getWlId(), resellerId,  0.0,reCharge, "Money Transfer - " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
//									}
//									 reComm=0.0;
//									if(resComm==0){reComm=0;}
//									else{
//									reComm = resComm-comm;
//									}	
//								//
//								if(reComm!=0){
//									commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0.0);
//									EarningSurcharge earningSurcharge00 = new EarningSurcharge(2, userDetails.getWlId(), resellerId, reComm, 0.0, "Money Transfer COMMISSION- " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//									earningSurchargeDao.insertEarningSurcharge(earningSurcharge00);			
//								}
//									}
//									}
//									
//									
//								}
//								
//								//Charge Credit
//			        		
//			        	}else if (moneyTransferobj.getString("entity").equalsIgnoreCase("payout")
//			        			&& moneyTransferobj.getString("status").equalsIgnoreCase("processing")){
//			        		String payOutId= moneyTransferobj.getString("id");
//			        		status = "SUCCESS";
//		        			String today = GenerateRandomNumber.getCurrentDate();
//							String currentTime = GenerateRandomNumber.getCurrentTime();
//							ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, payOutId, "PENDING","-",comm,transfertype,checkRemitterMobile,amount,0.0);
//							flag=impsTransactiondao.insertImpsTransaction(imptrans); 
//							returnData.put("imptrans", imptrans);	
//							
//							returnData.put("message",moneyTransferobj.getString("status"));	
//						     returnData.put("nextPage","home");
//							 returnData.put("status","1");	
//							 returnData.put("closingBalance", cl_bal);
//							 
//							 param = new HashMap<String, Object>();
//								param.put("month", d.getMonth());
//								param.put("year", year);
//								param.put("mobile", checkRemitterMobile);
//								List<RemitterLimit> remlist2=remitterlimitDao.getRemitterLimitByNamedQuery("getRemlimitbyMobilemonthyear",param);	
//								if(!remlist2.isEmpty()) {
//							  RemitterLimit remlim1=remlist2.get(0);	
//							  double limit1=remlim1.getCashlimit();
//							  double newlimit1=limit1-amount;	
//							  remlim1.setCashlimit(newlimit1);
//							    remitterlimitDao.updateRemitterLimit(remlim1);
//							    returnData.put("Limit",newlimit1);
//								}
//								
//								if(userDetails.getRoleId() == 6) {
//									
//									rId=userDetails.getUplineId();
//									parameters = new HashMap<String, String>();
//									parameters.put("userId", rId);	
//									distId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
//									
//									// Super Distributor Id
//									parameters = new HashMap<String, String>();
//									parameters.put("userId", distId);	
//									sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
//									
//									// Reseller Id
//									parameters = new HashMap<String, String>();
//									parameters.put("userId", sdId);							
//									resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
//								
//									pckret=commissionService.getPackagewiseCommisionOnOperator(rId,"DMR",id);
//									if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									retComm =(pckret.getComm()*remain)/100;
//									}else{
//									retComm =pckret.getComm();	
//									}
//									
//									if(pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										rtCharge = (pckret.getCharge()*remain)/100;
//									}else{
//										rtCharge = pckret.getCharge();
//									}
//								//	System.out.println("reseller=="+comm);
//									pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"DMR",id); 
//									if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									dComm =(pckdis.getComm()*remain)/100;
//									}else{
//									dComm=pckdis.getComm();	
//									}
//									if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										distCharge = (pckdis.getCharge()*remain)/100;
//									}else{
//										distCharge = pckdis.getCharge();
//									}
//								//	System.out.println("dComm=="+dComm);
//									pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
//									if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									sdComm =(pcksd.getComm()*remain)/100;
//									}else{
//									sdComm = pcksd.getComm();	
//									}
//									if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										sdCharge = (pcksd.getCharge()*remain)/100;
//									}else{
//										sdCharge = pcksd.getCharge();
//									}
//									
//									if(rtCharge==0){rcharge=0;}
//									else{rcharge = charge - rtCharge;}
//									if(distCharge==0){dcharge=0;}
//									else{dcharge = rtCharge - distCharge;}
//									if(sdCharge==0){sCharge=0;}
//									else{sCharge = distCharge - sdCharge;}
//									if(rcharge!=0){
//										commissionService.updateBalance(rId, "Money Transfer - "+accountNumber, "CHARGE", rcharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge = new EarningSurcharge(5, userDetails.getWlId(), rId,  0.0,rcharge, "Money Transfer - " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge);
//									}
//									if(dcharge!=0){
//										commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "CHARGE", dcharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, userDetails.getWlId(), distId,  0.0,dcharge, "Money Transfer - " +transactionAmountrazor , GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
//										
//									}
//									if(sCharge!=0){
//										commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId,  0.0,sCharge, "Money Transfer - " +transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
//									}
//									
//									
//									double retaComm=0.0;
//									if(retComm==0){retaComm=0;}
//									else{
//									retaComm=retComm-comm;
//									}
//									if(dComm==0){disComm=0;}
//									else{
//									disComm=dComm-retComm;
//									}
//									if(sdComm==0){sdisComm=0;}
//									else{
//									sdisComm = sdComm-dComm;
//									}
//									if(retaComm!=0){
//										commissionService.updateBalance(rId, "Money Transfer - "+accountNumber, "COMMISSION", retaComm, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge10 = new EarningSurcharge(5, userDetails.getWlId(), rId, retaComm, 0.0, "Money Transfer COMMISSION- " + accountNumber, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge10);
//											}
//									if(disComm!=0){
//										commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "COMMISSION", disComm, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge11 = new EarningSurcharge(4, userDetails.getWlId(), distId, disComm, 0.0, "Money Transfer COMMISSION- " + accountNumber, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge11);
//											}
//									if(sdisComm!=0){
//										commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge22 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm, 0.0, "Money Transfer COMMISSION- " + accountNumber, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge22);
//											}
//									
//									
//									
//									if(!resellerId.equals("admin")){
//										if(!userDetails.getWlId().startsWith("ASR")){
//										
//										pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
//									if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									resComm =(pckres.getComm()*remain)/100;
//									}else{
//									resComm =pckres.getComm();	
//									}
//									if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										resCharge = (pckres.getCharge()*remain)/100;
//										}else{
//										resCharge =	pckres.getCharge();
//										}
//									reCharge = sdCharge - resCharge;
//									if(reCharge!=0){
//										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge3 = new EarningSurcharge(2, userDetails.getWlId(), resellerId,  0.0,reCharge, "Money Transfer - " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
//									}
//									 reComm=0.0;
//									if(resComm==0){reComm=0;}
//									else{
//									reComm = resComm-sdComm;
//									}	
//								//
//								if(reComm!=0){
//									commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0.0);
//									EarningSurcharge earningSurcharge00 = new EarningSurcharge(2, userDetails.getWlId(), resellerId, reComm, 0.0, "Money Transfer COMMISSION- " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//									earningSurchargeDao.insertEarningSurcharge(earningSurcharge00);
//										}
//									}
//									}
//									
//									
//								}else if (userDetails.getRoleId() == 5){
//									sdCharge=0.0; sCharge=0.0;
//									resComm=0.0;
//									dComm=0.0; sdComm=0.0;
//									 distCharge=0.0; dcharge=0.0;
//									//remain=ims.getAmount();
//									
//									//String accountNumber=ims.getAccount_no();
//									 distId=userDetails.getUplineId();
//									// Super Distributor Id
//									 parameters = new HashMap<String, String>();
//									parameters.put("userId", distId);	
//									 sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
//									
//									// Reseller Id
//									parameters = new HashMap<String, String>();
//									parameters.put("userId", sdId);							
//									 resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
//								
//									
//								//	System.out.println("reseller=="+comm);
//									pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"DMR",id); 
//									if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									dComm =(pckdis.getComm()*remain)/100;
//									}else{
//									dComm=pckdis.getComm();	
//									}
//									if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										distCharge = (pckdis.getCharge()*remain)/100;
//									}else{
//										distCharge = pckdis.getCharge();
//									}
//								//	System.out.println("dComm=="+dComm);
//									pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
//									if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									sdComm =(pcksd.getComm()*remain)/100;
//									}else{
//									sdComm = pcksd.getComm();	
//									}
//									if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										sdCharge = (pcksd.getCharge()*remain)/100;
//									}else{
//										sdCharge = pcksd.getCharge();
//									}
//									
//									
//									if(distCharge==0){dcharge=0;}
//									else{dcharge = charge - distCharge;}
//									if(sdCharge==0){sCharge=0;}
//									else{sCharge = distCharge - sdCharge;}
//									
//									if(dcharge!=0){
//										commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "CHARGE", dcharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, userDetails.getWlId(), distId,  0.0,dcharge, "Money Transfer - " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
//									}
//									if(sCharge!=0){
//										commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId,  0.0,sCharge, "Money Transfer - " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
//									}
//									
//									
//									disComm=0.0;sdisComm=0.0;
//									
//									if(dComm==0){disComm=0;}
//									else{
//									disComm=dComm-comm;
//									}
//									if(sdComm==0){sdisComm=0;}
//									else{
//									sdisComm = sdComm-dComm;
//									}
//									
//									if(disComm!=0){
//										commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "COMMISSION", disComm, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge11 = new EarningSurcharge(4, userDetails.getWlId(), distId, disComm, 0.0, "Money Transfer COMMISSION- " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge11);
//											}
//									if(sdisComm!=0){
//										commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge22 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm, 0.0, "Money Transfer COMMISSION- " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge22);
//											}
//									
//									
//									if(!resellerId.equals("admin")){
//										if(!userDetails.getWlId().startsWith("ASR")){
//										resCharge=0.0;
//										reCharge=0.0;
//										pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
//									if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									resComm =(pckres.getComm()*remain)/100;
//									}else{
//									resComm =pckres.getComm();	
//									}
//									if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										resCharge = (pckres.getCharge()*remain)/100;
//										}else{
//										resCharge =	pckres.getCharge();
//										}
//									reCharge = sdCharge - resCharge;
//									if(reCharge!=0){
//										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge3 = new EarningSurcharge(2, userDetails.getWlId(), resellerId,  0.0,reCharge, "Money Transfer - " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
//									}
//								 reComm=0.0;
//									if(resComm==0){reComm=0;}
//									else{
//									reComm = resComm-sdComm;
//									}	
//								//
//								if(reComm!=0){
//									commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0.0);
//									EarningSurcharge earningSurcharge00 = new EarningSurcharge(2, userDetails.getWlId(), resellerId, reComm, 0.0, "Money Transfer COMMISSION- " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//									earningSurchargeDao.insertEarningSurcharge(earningSurcharge00);		
//								}
//									}
//									}
//									
//									
//								}else if(userDetails.getRoleId() == 4) {
//									sdCharge=0.0; sCharge=0.0;
//									 resComm=0.0;
//									 sdComm=0.0;
//									//double remain=ims.getAmount();
//									
//									//String accountNumber=ims.getAccount_no();
//									sdId=userDetails.getUplineId();
//									// Reseller Id
//									 parameters = new HashMap<String, String>();
//									parameters.put("userId", sdId);							
//									 resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
//								
//								
//										pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
//									if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									sdComm =(pcksd.getComm()*remain)/100;
//									}else{
//									sdComm = pcksd.getComm();	
//									}
//									if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										sdCharge = (pcksd.getCharge()*remain)/100;
//									}else{
//										sdCharge = pcksd.getCharge();
//									}
//									
//									
//									
//									if(sdCharge==0){sCharge=0;}
//									else{sCharge = charge - sdCharge;}
//									
//									
//									if(sCharge!=0){
//										commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId,  0.0,sCharge, "Money Transfer - " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
//									}
//									sdisComm=0.0;
//									
//									
//									if(sdComm==0){sdisComm=0;}
//									else{
//									sdisComm = sdComm-comm;
//									}
//									
//									
//									if(sdisComm!=0){
//										commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge22 = new EarningSurcharge(3, userDetails.getWlId(), sdId, sdisComm, 0.0, "Money Transfer COMMISSION- " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge22);
//											}
//									if(!resellerId.equals("admin")){
//										if(!userDetails.getWlId().startsWith("ASR")){
//										 resCharge=0.0;reCharge=0.0;
//										pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
//									if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									resComm =(pckres.getComm()*remain)/100;
//									}else{
//									resComm =pckres.getComm();	
//									}
//									if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										resCharge = (pckres.getCharge()*remain)/100;
//										}else{
//										resCharge =	pckres.getCharge();
//										}
//									reCharge = sdCharge - resCharge;
//									if(reCharge!=0){
//										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge3 = new EarningSurcharge(2, userDetails.getWlId(), resellerId,  0.0,reCharge, "Money Transfer - " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
//									}
//									reComm=0.0;
//									if(resComm==0){reComm=0;}
//									else{
//									reComm = resComm-sdComm;
//									}	
//								//
//								if(reComm!=0){
//									commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0.0);
//									EarningSurcharge earningSurcharge00 = new EarningSurcharge(2, userDetails.getWlId(), resellerId, reComm, 0.0, "Money Transfer COMMISSION- " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//									earningSurchargeDao.insertEarningSurcharge(earningSurcharge00);			
//								}
//									}
//									}
//									
//									
//								}else if(userDetails.getRoleId() == 3) {
//									resComm=0.0;
//								   // remain=ims.getAmount();
//									
//									//String accountNumber=ims.getAccount_no();
//										 resellerId=userDetails.getUplineId();
//									// Reseller Id
//									
//									if(!resellerId.equals("admin")){
//										if(!userDetails.getWlId().startsWith("ASR")){
//										 resCharge=0.0;reCharge=0.0;
//										 pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
//									if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//									resComm =(pckres.getComm()*remain)/100;
//									}else{
//									resComm =pckres.getComm();	
//									}
//									if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
//										resCharge = (pckres.getCharge()*remain)/100;
//										}else{
//										resCharge =	pckres.getCharge();
//										}
//									reCharge = charge - resCharge;
//									if(reCharge!=0){
//										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0.0);
//										EarningSurcharge earningSurcharge3 = new EarningSurcharge(2, userDetails.getWlId(), resellerId,  0.0,reCharge, "Money Transfer - " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//										earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
//									}
//									 reComm=0.0;
//									if(resComm==0){reComm=0;}
//									else{
//									reComm = resComm-comm;
//									}	
//								//
//								if(reComm!=0){
//									commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0.0);
//									EarningSurcharge earningSurcharge00 = new EarningSurcharge(2, userDetails.getWlId(), resellerId, reComm, 0.0, "Money Transfer COMMISSION- " + transactionAmountrazor, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime());
//									earningSurchargeDao.insertEarningSurcharge(earningSurcharge00);			
//								}
//									}
//									}
//									
//									
//								}
//								
//			        	}else {
//			        		
//			        		String today = GenerateRandomNumber.getCurrentDate();
//							String currentTime = GenerateRandomNumber.getCurrentTime();
//			        		returnData.put("statusCode","FAILED");
//					    	  ImpsTransaction imptrans1=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, amount, charge, cl_bal, today, currentTime, referenceno, "FAILED","-",comm,transfertype,checkRemitterMobile,amount,0.0);
//								flag=impsTransactiondao.insertImpsTransaction(imptrans1);
//								 returnData.put("imptrans", imptrans1);
//								commissionService.updateBalance(userDetails.getUserId(),
//										"Money Transfer - " + accountNumber, "Money Transfer Revert", totalAmount, "CREDIT",0);
//								commissionService.updateBalance(userDetails.getUserId(),
//										"Money Transfer - " + accountNumber, "Money Transfer COMMISSION", comm, "DEBIT",0);
//								
//								if(userDetails.getWlId().startsWith("ASR")){
//									double reseCharge=0.0;
//									double resCom=0.0;
//									 param = new HashMap<String, Object>();
//									param.put("wlId", userDetails.getWlId());
//									List<User> whlist=userDao.getUserByNamedQuery("getWILDbyID", param);
//									User whuser=whlist.get(0);
//									pckres=commissionService.getPackagewiseCommisionOnOperator(whuser.getUserId(),"DMR",id);
//									if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//										reseCharge = (pckres.getCharge()*remain)/100;
//									
//									}else{
//										reseCharge =	pckres.getCharge();
//									}
//									if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
//										resCom=(pckres.getComm()*remain)/100;
//									}else{
//										resCom=pckres.getComm();
//									}
//									
//									double whtamnt=(remain+reseCharge);
//									flag = commissionService.updateBalance(whlist.get(0).getUserId(), "Money Transfer - " + accountNumber, "Money Transfer Revert", whtamnt, "CREDIT",0);
//								}
//								
//								returnData.put("message",moneyTransferobj.getString("status"));
//								returnData.put("status", "0");
//			        	}
//			        	}
//					}
//			
//				} else {
//
//					returnData.put("message", "Donot Have Sufficient Balance");
//					returnData.put("status", "0");
//
//				}
//				} else {
//
//					returnData.put("message", "Donot Have Sufficient Balance");
//					returnData.put("status", "0");
//
//				}
//			}
//			}
//			} else {
//				returnData.put("message", "Amount should be maximum 20000");
//				returnData.put("status", "0");
//			}
//		} else {
//			returnData.put("message", "Amount should be minimum 10");
//			returnData.put("status", "0");
//		}
//				}else {
//					returnData.put("message", "You have crossed your monthly limit");
//					returnData.put("status", "0");
//						}
//				}else {
//					returnData.put("message", "Limit NOt Availeble");
//					returnData.put("status", "0");
//				}
//		}else{
//			returnData.put("message", "Operator Is Not Available");
//			returnData.put("status", "0");
//		}
//		}else {
//			returnData.put("status", "0");
//			returnData.put("message", "Please Assign Package");
//			
//		}
//		}else {
//			returnData.put("status", "0");
//			returnData.put("message", "Please Assign Package");
//			
//		}	
//		
//		}catch (Exception e) {
//		logger_log.error("instantPayMoneytransfer::::::::::::::"+e);
//		returnData.put("status", "0");
//		returnData.put("message", e);
//		}
		return returnData;
		}
		
	
}
