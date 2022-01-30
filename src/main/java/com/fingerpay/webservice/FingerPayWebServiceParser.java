package com.fingerpay.webservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.recharge.customModel.EncoreAEPSResponse;
import com.recharge.customModel.EncoreAadharPayResponse;
import com.recharge.customModel.Ministatement;
import com.recharge.customModel.MinistatementResponse;



public class FingerPayWebServiceParser {
	private static final Logger log = Logger.getLogger(FingerPayWebServiceParser.class);
	
	public static  EncoreAEPSResponse getFinPayAepsParser(String response) {
		EncoreAEPSResponse encore=new EncoreAEPSResponse();
		try {
			JSONObject jobj=new JSONObject(response);
			JSONObject encoreobj=jobj.getJSONObject("EncoreAEPSResponse");
			int statuscode=encoreobj.getInt("statusCode");
			encore.setStatusCode(statuscode);
			encore.setMessage(encoreobj.getString("message"));
			encore.setStatus(encoreobj.getBoolean("status"));
			/* if(statuscode==10000) { */
		
			encore.setBankRRN(encoreobj.getString("bankRRN"));
			encore.setDate(encoreobj.getString("date"));
			
			encore.setEncoreTransactionId(encoreobj.getString("encoreTransactionId"));
			encore.setMerchantTxnId(encoreobj.getString("merchantTxnId"));
			encore.setTerminalId(encoreobj.getString("terminalId"));
			encore.setTransactionStatus(encoreobj.getString("transactionStatus"));
			encore.setBalanceAmount(encoreobj.getString("balanceAmount"));
			encore.setTransactionAmount(encoreobj.getString("transactionAmount"));
			String transactionType=encoreobj.getString("transactionType");
			encore.setTransactionType(transactionType);
			/*
			 * if(transactionType.equalsIgnoreCase("CW")) {
			 * encore.setTransactionType("WITHDRAWAL"); }else {
			 * encore.setTransactionType("BALANCEINFO"); }
			 */
			encore.setTime(encoreobj.getString("time"));
		/*	}*/
			System.out.println("encore:::::::::::::::::::"+encore);
		}catch (Exception e) {
			log.error("getFinPayAepsParser:::::::::::::::::::"+e);
		}
		
		return encore;
	}
	public static  MinistatementResponse getMinistatementParser(String response) {
		MinistatementResponse minires=new MinistatementResponse();
		try {
		JSONObject obj=new JSONObject(response);
		JSONObject jobj=obj.getJSONObject("MinistatementResponse");
		minires.setStatus(jobj.getBoolean("status"));
		minires.setStatusCode(jobj.getInt("statusCode"));
		minires.setMessage(jobj.getString("message"));
		
		
		minires.setBalanceAmount(jobj.getDouble("balanceAmount"));
		minires.setBankRRN(jobj.getString("bankRRN"));
		minires.setTransactionTime(jobj.getString("transactionTime"));
		if(!jobj.isNull("merchantTxnId")) {
		minires.setMerchantTxnId(jobj.getString("merchantTxnId"));
		}
		minires.setTerminalId(jobj.getString("terminalId"));
		minires.setTransactionId(jobj.getString("transactionId"));
		minires.setTransactionStatus(jobj.getString("transactionStatus"));
		List<Ministatement> ministatement=new ArrayList<Ministatement>();
		if(!jobj.isNull("ministatement")) {
		JSONArray ministatearr=jobj.getJSONArray("ministatement");
		for(int i=0;i<ministatearr.length();i++) {
		JSONObject miniobj=ministatearr.getJSONObject(i);
		Ministatement mini=new Ministatement(miniobj.getString("date"),miniobj.getString("txnType") ,miniobj.getString("amount") ,miniobj.getString("narration"));
		ministatement.add(mini);
		}
		}
		minires.setMinistatement(ministatement);
		
		}catch (Exception e) {
			log.error("getFinPayAepsParser:::::::::::::::::::"+e);
		}
		
		return minires;
	}
	
	public static  EncoreAadharPayResponse getFinPayAepsParser2(String response) {
		EncoreAadharPayResponse encore=new EncoreAadharPayResponse();
		try {
			JSONObject jobj=new JSONObject(response);
			JSONObject encoreobj=jobj.getJSONObject("EncoreAadharPayResponse");
			int statuscode=encoreobj.getInt("statusCode");
			encore.setStatusCode(statuscode);
			encore.setMessage(encoreobj.getString("message"));
			encore.setStatus(encoreobj.getBoolean("status"));
			/* if(statuscode==10000) { */
		
			encore.setBankRRN(encoreobj.getString("bankRRN"));
			encore.setDate(encoreobj.getString("date"));
			
			encore.setEncoreTransactionId(encoreobj.getString("encoreTransactionId"));
			encore.setMerchantTxnId(encoreobj.getString("merchantTxnId"));
			encore.setTerminalId(encoreobj.getString("terminalId"));
			encore.setTransactionStatus(encoreobj.getString("transactionStatus"));
			encore.setBalanceAmount(encoreobj.getString("balanceAmount"));
			encore.setTransactionAmount(encoreobj.getString("transactionAmount"));
			String transactionType=encoreobj.getString("transactionType");
			encore.setTransactionType(transactionType);
			/*
			 * if(transactionType.equalsIgnoreCase("CW")) {
			 * encore.setTransactionType("WITHDRAWAL"); }else {
			 * encore.setTransactionType("BALANCEINFO"); }
			 */
			encore.setTime(encoreobj.getString("time"));
		/*	}*/
			System.out.println("encore:::::::::::::::::::"+encore);
		}catch (Exception e) {
			log.error("getFinPayAepsParser:::::::::::::::::::"+e);
		}
		
		return encore;
	}
	
	public static void main(String args[]) {
	String response="{\"EncoreAEPSResponse\":{\"status\":true,\"statusCode\":10000,\"message\":\"Request Completed\",\"terminalId\":\"FA466151\",\"date\":\"2020-05-27\",\"time\":\"15:30:57\",\"transactionStatus\":\"successful\",\"bankRRN\":\"014815261926\",\"transactionType\":\"BALANCEINFO\",\"merchantTxnId\":\"47684434085624\",\"transactionAmount\":\"0.0\",\"balanceAmount\":\"328.19\",\"encoreTransactionId\":\"BEBE0750290270520153102430I\"}}";
	System.out.println(getFinPayAepsParser(response));
	}

}
