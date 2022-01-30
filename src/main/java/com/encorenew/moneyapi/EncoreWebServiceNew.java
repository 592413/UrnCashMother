package com.encorenew.moneyapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.encore.moneyapi.EncoreWebService;
import com.encore.moneyapi.EncoreWebServiceParser;
import com.google.gson.Gson;
import com.recharge.customModel.ImpsResponse;
import com.recharge.icicidmtmodel.DmtRemitter;
import com.recharge.icicidmtmodel.RemitterDetail;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class EncoreWebServiceNew {
	

	private static final Logger logger_log = Logger.getLogger(EncoreWebService.class);
	
  //private static final String DMRURL="http://localhost:8085/Encohead";
    private static final String DMRURL="https://api.encodigi.net.in";
	
	 public static String sendRequestToYesBankDMR(Object input, String methodName) {
			
			String url = DMRURL + "/" + methodName;
			Client restClient = Client.create();
			logger_log.warn("input::::::::::::"+input);
			logger_log.warn("url::::::::::::"+url);
			WebResource webResource = restClient.resource(url);
			ClientResponse resp = webResource.type(MediaType.APPLICATION_JSON_TYPE).accept("application/json")
					.post(ClientResponse.class, input);
			/*if (resp.getStatus() != 200) {
				logger_log.warn("Unable to connect to the server");
			}*/
			String output = resp.getEntity(String.class);
			logger_log.warn("sendRequestToYesBankDMR::::::::::::::::"+output);
			return output;
		}
	 
	 
	 public static Map<String, Object> checkRemmiterNew(String input){
		 Map<String, Object> returnData = new HashMap<String, Object>();
		 String response="";
		 try{
		 String method="checkuserICICI";
	    response=sendRequestToYesBankDMR(input,method);	
	//	response="{'RemitterDetails':{'totalMonthlyLimit':'75000.00','createdDate':'13/12/2019 12:02:23','walletbal':'49990.00','customerId':'8013724327','kycstatus':'0','name':'Payel Sarkar'},'BeneficiaryList':[{'BankName':'UNITED BANK OF INDIA','BeneAcc':'0918010199032','BeneId':'140','BeneName':'PAYEL SARKAR','BeneMobile':'8013724327','BeneType':'2','BeneIfsc':'UTBI0KNBC77'}],'message':'SUCCESS','status':'00'}";
		 returnData=EncoreWebServiceParserNew.checkRemmiterParser(response);
		 }catch (Exception e) {
		 logger_log.warn("checkRemmiter::::::::::::::::"+e);
		}
		 return returnData;
	 }
	 
	 
	 public static Map<String, Object> checkRemmiterNewForWeb(String input){
		 Map<String, Object> returnData = new HashMap<String, Object>();
		 String response="";
		 try{
		 String method="checkuserICICI";
	    response=sendRequestToYesBankDMR(input,method);	
	//	response="{'RemitterDetails':{'totalMonthlyLimit':'75000.00','createdDate':'13/12/2019 12:02:23','walletbal':'49990.00','customerId':'8013724327','kycstatus':'0','name':'Payel Sarkar'},'BeneficiaryList':[{'BankName':'UNITED BANK OF INDIA','BeneAcc':'0918010199032','BeneId':'140','BeneName':'PAYEL SARKAR','BeneMobile':'8013724327','BeneType':'2','BeneIfsc':'UTBI0KNBC77'}],'message':'SUCCESS','status':'00'}";
	    DmtRemitter remt= new Gson().fromJson(response, DmtRemitter.class);
	    JSONObject jobj=new JSONObject(response);
	    if(remt.getStatus().equalsIgnoreCase("1")){
	    returnData.put("status", "1");
		returnData.put("RemitterDetail", remt.getRemitterDetail());
		}else if(remt.getStatus().equalsIgnoreCase("0")){
			
			returnData.put("status", "0");
			returnData.put("message", jobj.getString("message"));
			returnData.put("mobile", jobj.getString("mobile"));
		}else {
			returnData.put("status", "2");
			returnData.put("mobile", jobj.getString("mobile"));
			returnData.put("message", jobj.getString("message"));
		}
		 }catch (Exception e) {
		 logger_log.warn("checkRemmiter::::::::::::::::"+e);
		}
		 return returnData;
	 }

	 
	 public static Map<String, Object> remitterRegisterNew(String input){
		 Map<String, Object> returnData = new HashMap<String, Object>();
		 String response="";
		 try{
		 String method="RemitterRegister";
		 response=sendRequestToYesBankDMR(input,method);	
		// response="{'message':'OTP sent successfully for Remitter Registration','status':'2'}";
		 returnData=EncoreWebServiceParserNew.remitterRegisterParser(response);
		 }catch (Exception e) {
		 logger_log.warn("checkRemmiter::::::::::::::::" +e);
		}
		 return returnData;
	 }
	 
	 public static Map<String, Object> deleteBeneficiaryEncoreNew(String input){
		 Map<String, Object> returnData = new HashMap<String, Object>();
		 String response="";
		 try{
		 String method="deletebene";
		 response=sendRequestToYesBankDMR(input,method);	
		// response="{'message':'OTP sent successfully for Remitter Registration','status':'2'}";
		 returnData=EncoreWebServiceParserNew.deleteBeneficiaryEncoreParser(response);
		 }catch (Exception e) {
		 logger_log.warn("deleteBeneficiaryEncore::::::::::::::::" +e);
		}
		 return returnData;
	 }
	 
	 public static Map<String, Object> addBeneficiaryEncoreNew(String input){
		 Map<String, Object> returnData = new HashMap<String, Object>();
		 String response="";
		 try{
		// String method="BeneficiaryRegistration";
			 String method="BeneficiaryRegistrationEncore";
		 response=sendRequestToYesBankDMR(input,method);	
		// response="{'message':'OTP sent successfully for Remitter Registration','status':'2'}";
		 returnData=EncoreWebServiceParserNew.addBeneficiaryParser(response);
		 }catch (Exception e) {
		 logger_log.warn("addBeneficiaryEncore::::::::::::::::" +e);
		}
		 return returnData;
	 }
	 
	 public static Map<String, Object> OTPVERIFICATION(String input){
		 Map<String, Object> returnData = new HashMap<String, Object>();
		 String response="";
		 try{
		 String method="otpverify";
		 response=sendRequestToYesBankDMR(input,method);	
		// response="{'message':'OTP sent successfully for Remitter Registration','status':'2'}";
		 returnData=EncoreWebServiceParserNew.OTPVERIFYParser(response);
		 }catch (Exception e) {
		 logger_log.warn("addBeneficiaryEncore::::::::::::::::" +e);
		}
		 return returnData;
	 }
	 
	 public static Map<String, Object> ValidateBeneficiaryEncoreNew(String input){
		 Map<String, Object> returnData = new HashMap<String, Object>();
		 String response="";
		 try{
		 String method="validateEncoreBeneficiary";
		response=sendRequestToYesBankDMR(input,method);	
		// response="{'charge':'0','serviceTax':'0.0','mobile':'8013724327','bene_name':'MOBILEWARE PVT LTD','status':'00','txnId':'J33020010151225033'}";
		 logger_log.warn("ValidateBeneficiaryEncoreinput:::::::::::::::::::::::::"+input);
		 logger_log.warn("ValidateBeneficiaryEncoreres:::::::::::::::::::::::::"+response);
		 returnData=EncoreWebServiceParserNew.ValidateBeneficiaryParser(response);
		 }catch (Exception e) {
		 logger_log.warn("ValidateBeneficiaryEncore::::::::::::::::" +e);
		}
		 return returnData;
	 }
	 
	
	 
	 public static synchronized ImpsResponse impsMoneyTransferEncoreNew(String input){
		 Map<String, Object> returnData = new HashMap<String, Object>();
		ImpsResponse imps=new ImpsResponse();
		 String response="";
		 try{
		// String method="Moneytransfer";
			 
			 String method="EncoreMoneyTransfer";
		 response=sendRequestToYesBankDMR(input,method);	
		//response="{'bankTransactionId':'934717003176','amount':'10.00','charge':'0','clientId':'T35819347170130327','serviceTax':'0.0','bene_account':'0918010199032','mobile':'8013724327','message':'Transaction done successfully','bene_ifsc':'UTBI0KNBC77','DateTime':'13/12/2019 17:01:30','bene_name':'PAYEL SARKAR','bene_mobile':'8013724327','bene_id':'140','status':'00'}";
		//response="{'amount':'100.00','bene_mobile':'8013724327','mobile':'8013724327','bene_id':'4153284','message':'Failure NPCI Connection','bene_name':'Partha Ghosh','status':'E1641'}";
		 imps=EncoreWebServiceParserNew.impsMoneyTransferEncoreParser(response);
		 }catch (Exception e) {
		 logger_log.warn("impsMoneyTransferEncore::::::::::::::::" +e);
		}
		 return imps;
	 }
	 
	 
	 public static synchronized ImpsResponse impsCheckBeneficiaryEncoreNew(String input){
		 Map<String, Object> returnData = new HashMap<String, Object>();
		ImpsResponse imps=new ImpsResponse();
		 String response="";
		 try{
		// String method="Moneytransfer";
			 
			 String method="validateEncoreBeneficiary";
		 response=sendRequestToYesBankDMR(input,method);	
		//response="{'bankTransactionId':'934717003176','amount':'10.00','charge':'0','clientId':'T35819347170130327','serviceTax':'0.0','bene_account':'0918010199032','mobile':'8013724327','message':'Transaction done successfully','bene_ifsc':'UTBI0KNBC77','DateTime':'13/12/2019 17:01:30','bene_name':'PAYEL SARKAR','bene_mobile':'8013724327','bene_id':'140','status':'00'}";
		//response="{'amount':'100.00','bene_mobile':'8013724327','mobile':'8013724327','bene_id':'4153284','message':'Failure NPCI Connection','bene_name':'Partha Ghosh','status':'E1641'}";
		 imps=EncoreWebServiceParserNew.impsCheckBenficiaryEncoreParser(response);
		 }catch (Exception e) {
		 logger_log.warn("impsMoneyTransferEncore::::::::::::::::" +e);
		}
		 return imps;
	 }


	public static Map<String, Object> resendOTP(String input) {
		 Map<String, Object> returnData = new HashMap<String, Object>();
		 String response="";
		 try{
		 String method="resendOTP";
		response=sendRequestToYesBankDMR(input,method);	
		// response="{'charge':'0','serviceTax':'0.0','mobile':'8013724327','bene_name':'MOBILEWARE PVT LTD','status':'00','txnId':'J33020010151225033'}";
		 logger_log.warn("resendOTP:::::::::::::::::::::::::"+input);
		 logger_log.warn("resendOTPres:::::::::::::::::::::::::"+response);
		 returnData=EncoreWebServiceParserNew.OTPVERIFYParser(response);
		 }catch (Exception e) {
		 logger_log.warn("ValidateBeneficiaryEncore::::::::::::::::" +e);
		}
		 return returnData;
	 }


}
