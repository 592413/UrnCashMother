package com.encore.moneyapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.recharge.customModel.ImpsResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class EncoreWebService {
	private static final Logger logger_log = Logger.getLogger(EncoreWebService.class);
	
	// private static final String DMRURL="http://localhost:8080/Encohead";
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
	 
	 
	 public static Map<String, Object> checkRemmiter(String input){
		 Map<String, Object> returnData = new HashMap<String, Object>();
		 String response="";
		 try{
		 String method="searchRemitter";
	    response=sendRequestToYesBankDMR(input,method);	
	    System.out.println("response::::::::::::::::"+response);
	//	response="{'RemitterDetails':{'totalMonthlyLimit':'75000.00','createdDate':'13/12/2019 12:02:23','walletbal':'49990.00','customerId':'8013724327','kycstatus':'0','name':'Payel Sarkar'},'BeneficiaryList':[{'BankName':'UNITED BANK OF INDIA','BeneAcc':'0918010199032','BeneId':'140','BeneName':'PAYEL SARKAR','BeneMobile':'8013724327','BeneType':'2','BeneIfsc':'UTBI0KNBC77'}],'message':'SUCCESS','status':'00'}";
		 returnData=EncoreWebServiceParser.checkRemmiterParser(response);
		 }catch (Exception e) {
		 logger_log.warn("checkRemmiter::::::::::::::::"+e);
		}
		 return returnData;
	 }

	 
	 public static Map<String, Object> remitterRegister(String input){
		 Map<String, Object> returnData = new HashMap<String, Object>();
		 String response="";
		 try{
		 String method="remitterRegister";
		 response=sendRequestToYesBankDMR(input,method);	
		// response="{'message':'OTP sent successfully for Remitter Registration','status':'2'}";
		 returnData=EncoreWebServiceParser.remitterRegisterParser(response);
		 }catch (Exception e) {
		 logger_log.warn("checkRemmiter::::::::::::::::" +e);
		}
		 return returnData;
	 }
	 
	 public static Map<String, Object> deleteBeneficiaryEncore(String input){
		 Map<String, Object> returnData = new HashMap<String, Object>();
		 String response="";
		 try{
		 String method="deleteBeneficiaryEnco";
		 response=sendRequestToYesBankDMR(input,method);	
		// response="{'message':'OTP sent successfully for Remitter Registration','status':'2'}";
		 returnData=EncoreWebServiceParser.deleteBeneficiaryEncoreParser(response);
		 }catch (Exception e) {
		 logger_log.warn("deleteBeneficiaryEncore::::::::::::::::" +e);
		}
		 return returnData;
	 }
	 
	 public static Map<String, Object> addBeneficiaryEncore(String input){
		 Map<String, Object> returnData = new HashMap<String, Object>();
		 String response="";
		 try{
		 String method="addBeneficiary";
		 response=sendRequestToYesBankDMR(input,method);	
		// response="{'message':'OTP sent successfully for Remitter Registration','status':'2'}";
		 returnData=EncoreWebServiceParser.addBeneficiaryParser(response);
		 }catch (Exception e) {
		 logger_log.warn("addBeneficiaryEncore::::::::::::::::" +e);
		}
		 return returnData;
	 }
	 
	 public static Map<String, Object> ValidateBeneficiaryEncore(String input){
		 Map<String, Object> returnData = new HashMap<String, Object>();
		 String response="";
		 try{
		 String method="validateBeneficiary";
		response=sendRequestToYesBankDMR(input,method);	
		// response="{'charge':'0','serviceTax':'0.0','mobile':'8013724327','bene_name':'MOBILEWARE PVT LTD','status':'00','txnId':'J33020010151225033'}";
		 logger_log.warn("ValidateBeneficiaryEncoreinput:::::::::::::::::::::::::"+input);
		 logger_log.warn("ValidateBeneficiaryEncoreres:::::::::::::::::::::::::"+response);
		 returnData=EncoreWebServiceParser.ValidateBeneficiaryParser(response);
		 }catch (Exception e) {
		 logger_log.warn("ValidateBeneficiaryEncore::::::::::::::::" +e);
		}
		 return returnData;
	 }
	 
	
	 
	 public static synchronized List<ImpsResponse> impsMoneyTransferEncore(String input){
		 Map<String, Object> returnData = new HashMap<String, Object>();
		 List<ImpsResponse> list=new ArrayList<ImpsResponse>();
		 String response="";
		 try{
		 String method="ImpsMoneyTransfer";
		 response=sendRequestToYesBankDMR(input,method);	
		//response="{'bankTransactionId':'934717003176','amount':'10.00','charge':'0','clientId':'T35819347170130327','serviceTax':'0.0','bene_account':'0918010199032','mobile':'8013724327','message':'Transaction done successfully','bene_ifsc':'UTBI0KNBC77','DateTime':'13/12/2019 17:01:30','bene_name':'PAYEL SARKAR','bene_mobile':'8013724327','bene_id':'140','status':'00'}";
		//response="{'amount':'100.00','bene_mobile':'8013724327','mobile':'8013724327','bene_id':'4153284','message':'Failure NPCI Connection','bene_name':'Partha Ghosh','status':'E1641'}";
		 list=EncoreWebServiceParser.impsMoneyTransferEncoreParser(response);
		 }catch (Exception e) {
		 logger_log.warn("impsMoneyTransferEncore::::::::::::::::" +e);
		}
		 return list;
	 }
}



