package com.fingerpay.webservice;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.recharge.customModel.EncoreAEPSResponse;

public class FingerPayWebserviceSILParser {
	
private static final Logger log = Logger.getLogger(FingerPayWebserviceSILParser.class);
	
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

}
