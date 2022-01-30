package com.recharge.instantpay;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;


import com.google.gson.Gson;
import com.recharge.controller.AndroidController;

public class InstantPayWebservice {
	private static final String webServiceURI = "";
	private static final Logger logger_log = Logger.getLogger(InstantPayWebservice.class);
	
	public static String sendRequestToWebservice(Object input,String uri, String methodName) {
		String output = "";
		Client client = null;
		try{
			String url = uri + "/"+methodName;
			String i = (String) input;
			//System.out.println("input::::::::::::::::::"+i);
			 logger_log.warn("sendRequestToWebserviceinput::::::::::::::::::::"+i);
	        client=ClientBuilder.newClient();
			Response response=client.target(url).request(MediaType.APPLICATION_JSON).post(Entity.json(i));
			output =response.readEntity(String.class);
		    logger_log.warn("sendRequestToWebserviceoutput::::::::::::::::::::"+output);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return output;
	}
	
	
	public static Map<String, Object> checkuserInstantPay(String remitter,String token){
		Map<String, Object> returnData = new HashMap<String, Object>();
		String input ="{\"token\": \""+token+"\",\"request\": {\"mobile\": \""+remitter+"\"}}";
		
		String response = sendRequestToWebservice(input,"https://www.instantpay.in/ws/dmi/","remitter_details");
		returnData=InstantWebserviceParser.checkuserInstantPayParser(response);
		returnData.put("MOBILENO",remitter);
		return returnData;
		
	}
	
	
	public static Map<String, Object> instantRemitterRegister(String token,String customermobile,String customername,String Pincode,String surname){
		Map<String, Object> returnData = new HashMap<String, Object>();
		
		
		String input ="{\"token\": \""+token+"\",\"request\": {\"mobile\": \""+customermobile+"\",\"name\": \""+customername+"\",\"surname\": \""+surname+"\",\"pincode\": \""+Pincode+"\"}}";
		//String response = "";
	    String response = sendRequestToWebservice(input,"https://www.instantpay.in/ws/dmi/","remitter");
		returnData=InstantWebserviceParser.instantRemitterRegisterParser(response);
		returnData.put("MOBILENO",customermobile);
		return returnData;
		
	}
	
	public static Map<String, Object> instantPayRemitterRegisterValidate(String token,String REMID,String MOBILENO,String otp){
		Map<String, Object> returnData = new HashMap<String, Object>();
		
		
		String input ="{\"token\": \""+token+"\",\"request\": {\"remitterid\": \""+REMID+"\",\"mobile\": \""+MOBILENO+"\",\"otp\": \""+otp+"\"}}";
		//String response = "";
	    String response = sendRequestToWebservice(input,"https://www.instantpay.in/ws/dmi/","remitter_validate");
		returnData=InstantWebserviceParser.instantRemitterValidateParser(response);
		returnData.put("MOBILENO",MOBILENO);
		return returnData;
		
	}
	
	public static Map<String, Object> instantNewBeneficiary(String token,String remitterid,String bene_name,String beneMobileNumber,String beneIFSCCode,String accountNumber){
		Map<String, Object> returnData = new HashMap<String, Object>();
		String input ="{\"token\": \""+token+"\", \"request\": {\"remitterid\": \""+remitterid+"\",\"name\": \""+bene_name+"\",\"mobile\": \""+beneMobileNumber+"\",\"ifsc\": \""+beneIFSCCode+"\",\"account\": \""+accountNumber+"\"}}";
		//String response = "";
	    String response = sendRequestToWebservice(input,"https://www.instantpay.in/ws/dmi/","beneficiary_register");
		returnData=InstantWebserviceParser.instantNewBeneficiaryParser(response);
		return returnData;
		
	}
	
	
	public static Map<String, Object> instantpayValidateBeneficiary(String token,String accountNumber,String beneIFSCCode,String checkRemitterMobile,String referenceno){
		Map<String, Object> returnData = new HashMap<String, Object>();
		String input ="{\"token\": \""+token+"\", \"request\": {\"remittermobile\": \""+checkRemitterMobile+"\",\"account\": \""+accountNumber+"\",\"ifsc\": \""+beneIFSCCode+"\",\"agentid\": \""+referenceno+"\"}}";
		String response = sendRequestToWebservice(input,"https://www.instantpay.in/ws/imps/","account_validate");
		
		returnData=InstantWebserviceParser.instantpayValidateBeneficiary(response);
		return returnData;
		
	}
	
	public static Map<String, Object> instantpayDeleteBeneficiary(String token,String beneficiaryid,String remitterid){
		Map<String, Object> returnData = new HashMap<String, Object>();
		String input ="{\"token\": \""+token+"\", \"request\": {\"beneficiaryid\": \""+beneficiaryid+"\",\"remitterid\": \""+remitterid+"\"}}";
		String response = sendRequestToWebservice(input,"https://www.instantpay.in/ws/dmi/","beneficiary_remove");
		returnData=InstantWebserviceParser.instantpayRemoveBeneficiaryparser(response);
		return returnData;
		
	}
	
	
	public static Map<String, Object> instantPayDeleteBeneFinal(String token,String beneficiaryid,String remitterid,String otp){
		Map<String, Object> returnData = new HashMap<String, Object>();
	
		String input ="{\"token\": \""+token+"\", \"request\": {\"beneficiaryid\": \""+beneficiaryid+"\",\"remitterid\": \""+remitterid+"\",\"otp\": \""+otp+"\"}}";
		String response = sendRequestToWebservice(input,"https://www.instantpay.in/ws/dmi/","beneficiary_remove_validate");
		returnData=InstantWebserviceParser.instantPayDeleteBeneFinalparser(response);
		return returnData;
		
	}
	
	
	public static Map<String, Object> instantPayNewBeneFinal(String token,String beneficiaryid,String remitterid,String otp){
		Map<String, Object> returnData = new HashMap<String, Object>();
	
		String input ="{\"token\": \""+token+"\", \"request\": {\"beneficiaryid\": \""+beneficiaryid+"\",\"remitterid\": \""+remitterid+"\",\"otp\": \""+otp+"\"}}";
		//String response = "";
		String response = sendRequestToWebservice(input,"https://www.instantpay.in/ws/dmi/","beneficiary_register_validate");
		returnData=InstantWebserviceParser.instantPayNewBeneFinalparser(response);
		return returnData;
		
	}
	
	public static Map<String, Object> instantPaymentTransfer(String token,String checkRemitterMobile,String beneid,String referenceno,String trnsamnt,String transfertype){
		Map<String, Object> returnData = new HashMap<String, Object>();
		
		String input ="{\"token\": \""+token+"\", \"request\": {\"remittermobile\": \""+checkRemitterMobile+"\",\"beneficiaryid\": \""+beneid+"\",\"agentid\": \""+referenceno+"\",\"amount\": \""+trnsamnt+"\",\"mode\": \""+transfertype+"\"}}";
	   // String response = "";
		String response = sendRequestToWebservice(input,"https://www.instantpay.in/ws/dmi/","transfer");
	//	String response="{'statuscode':'TXN','status':'Transaction Successful','data':{'ipay_id':'1190508114157RVCAX','ref_no':'912811484565','opr_id':'912811484565','name':'PARTHA GHOSH','opening_bal':'2000.00','amount':10,'charged_amt':16.7,'locked_amt':0,'ccf_bank':10,'bank_alias':'APBL'}}";
		logger_log.warn("instantPaymentTransferinput:::::::::::::::::::::"+input);
		logger_log.warn("instantPaymentTransfer:::::::::::::::::::::"+response);
		returnData=InstantWebserviceParser.instantpayFundTransfer(response);
		return returnData;
		
	}
	public static void main(String[] args){
		String token = "3cb9f21d136a27196845097c0f03afb6";
		String remitter = "8013724327";
		String input ="{\"token\": \""+token+"\",\"request\": {\"mobile\": \""+remitter+"\"}}";
		System.out.println(sendRequestToWebservice(input,"https://www.instantpay.in/ws/imps/","remitter_details"));
	}

}
