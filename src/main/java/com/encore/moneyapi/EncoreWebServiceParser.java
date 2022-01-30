package com.encore.moneyapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.recharge.customModel.ImpsResponse;

public class EncoreWebServiceParser {
	private static final Logger logger_log = Logger.getLogger(EncoreWebServiceParser.class);
	
	public static Map<String, Object> checkRemmiterParser(String response) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		List<Map<String, Object>> remitterbeneficiarylist = new ArrayList<Map<String, Object>>();
		try {
			JSONObject json = new JSONObject(response);
			if (json.getString("status").equalsIgnoreCase("00")) {
				returnData.put("message", json.getString("message"));
				returnData.put("status", "1");
				JSONObject remobj = json.getJSONObject("RemitterDetails");
				returnData.put("remitter", remobj.getString("customerId"));
				returnData.put("SENDER_CUSTTYPE", remobj.getString("kycstatus"));
				if(!remobj.isNull("otpverified")) {
				returnData.put("otpverified", remobj.getBoolean("otpverified"));
				}
				returnData.put("Limit", remobj.getString("walletbal"));
				JSONArray BeneficiaryList = json.getJSONArray("BeneficiaryList");
				for (int i = 0; i < BeneficiaryList.length(); i++) {
					JSONObject benejson = BeneficiaryList.getJSONObject(i);
					Map<String, Object> bene = new HashMap<String, Object>();
					bene.put("id", benejson.getString("BeneId"));
					bene.put("mobile", benejson.getString("BeneMobile"));
					bene.put("name", benejson.getString("BeneName"));
					bene.put("account", benejson.getString("BeneAcc"));
					bene.put("bank", benejson.getString("BankName"));
					bene.put("ifsc", benejson.getString("BeneIfsc"));
				//	returnData.put("verified", remobj.getBoolean("verified"));
					remitterbeneficiarylist.add(bene);
				}
				returnData.put("beneficiarylist", remitterbeneficiarylist);
			} else if (json.getString("status").equalsIgnoreCase("2")) {
				returnData.put("status", "2");
				returnData.put("message", json.getString("message"));
			} else {
				returnData.put("status", "0");
				returnData.put("message", json.getString("message"));
			}
		} catch (Exception e) {
			returnData.put("status", "0");
			returnData.put("message", e);
			logger_log.warn("checkRemmiterParser::::::::::::::::" + e);
		}

		return returnData;

	}


	

	public static Map<String, Object> deleteBeneficiaryEncoreParser(String response) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			JSONObject jobj = new JSONObject(response);
			if (jobj.getString("status").equalsIgnoreCase("00")) {
				returnData.put("status", "1");
				returnData.put("message", jobj.getString("message"));
			} else {
				returnData.put("message", jobj.getString("message"));
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.warn("deleteBeneficiaryEncoreParser::::::::::::::::" + e);
		}
		return returnData;
	}

	public static List<ImpsResponse> impsMoneyTransferEncoreParser(String response) {
		List<ImpsResponse> list = new ArrayList<ImpsResponse>();
		try {
			String bene_mobile="";
			String bene_id="";
			String bene_account="";
			String bene_ifsc="";
			String remitter_Name="";
			String mobile="";
			String clientId="";
			String bankTransactionId="";
			String date="";
			String time="";
			String status="";
			JSONObject jobj = new JSONObject(response);
			JSONArray imparr = jobj.getJSONArray("ImpsResponse");
			for (int i = 0; i < imparr.length(); i++) {
				JSONObject impobj = imparr.getJSONObject(i);
				if(!impobj.isNull("bene_mobile")) {
					bene_mobile=impobj.getString("bene_mobile");	
				}
				if(!impobj.isNull("bene_id")) {
					bene_id=impobj.getString("bene_id");	
				}
				
				if(!impobj.isNull("bene_account")) {
					bene_account=impobj.getString("bene_account");	
				}
				
				if(!impobj.isNull("bene_ifsc")) {
					bene_ifsc=impobj.getString("bene_ifsc");	
				}
				
				if(!impobj.isNull("remitter_Name")) {
					remitter_Name=impobj.getString("remitter_Name");	
				}
				
				if(!impobj.isNull("mobile")) {
					mobile=impobj.getString("mobile");		
				}
				
				if(!impobj.isNull("clientId")) {
					clientId=impobj.getString("clientId");		
				}

				
				if(!impobj.isNull("bankTransactionId")) {
					bankTransactionId=impobj.getString("bankTransactionId");		
				}
				
				if(!impobj.isNull("date")) {
					date=impobj.getString("date");	
				}
				
				if(!impobj.isNull("time")) {
					time=impobj.getString("time");		
				}
				if(impobj.getString("status").equalsIgnoreCase("00")) {
				status="1";	
				}else if(impobj.getString("status").equalsIgnoreCase("703") || impobj.getString("status").equalsIgnoreCase("0") ) {
				status="0";		
				}else {
				status="2";			
				}
				ImpsResponse imps = new ImpsResponse(bene_mobile, bene_id,
						impobj.getDouble("gst"), impobj.getDouble("charge"), impobj.getDouble("amount"),
						bene_account, bene_ifsc,
						remitter_Name, mobile, clientId,
						bankTransactionId, date, time,
						status,impobj.getString("message"));
				list.add(imps);
				
				/*
				 * if(jobj.getString("status").equalsIgnoreCase("00")){
				 * returnData.put("message",jobj.getString("message"));
				 * returnData.put("bene_name",jobj.getString("bene_name"));
				 * returnData.put("bene_id",jobj.getString("bene_id"));
				 * returnData.put("bene_account",jobj.getString("bene_account"));
				 * returnData.put("bene_mobile",jobj.getString("bene_mobile"));
				 * returnData.put("DateTime",jobj.getString("DateTime"));
				 * returnData.put("bene_name",jobj.getString("bene_name"));
				 * returnData.put("clientId",jobj.getString("clientId"));
				 * returnData.put("amount",jobj.getString("amount"));
				 * returnData.put("charge",jobj.getString("charge"));
				 * returnData.put("mobile",jobj.getString("mobile"));
				 * returnData.put("bankTransactionId",jobj.getString("bankTransactionId"));
				 * returnData.put("serviceTax",jobj.getString("serviceTax"));
				 * returnData.put("serviceTax",jobj.getString("serviceTax"));
				 * returnData.put("status","1"); }else
				 * if(jobj.getString("status").equalsIgnoreCase("703")){
				 * returnData.put("status","703");
				 * returnData.put("message",jobj.getString("message"));
				 * returnData.put("bankTransactionId","NA"); }else{
				 * returnData.put("status","0");
				 * returnData.put("message",jobj.getString("message"));
				 * returnData.put("bankTransactionId","NA"); }
				 */
			}
		} catch (Exception e) {
			logger_log.warn("impsMoneyTransferEncoreParser::::::::::::::::" + e);
		}

		return list;
	}

	public static Map<String, Object> ValidateBeneficiaryParser(String response) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			JSONObject jobj = new JSONObject(response);
			if (jobj.getString("status").equalsIgnoreCase("00")) {
				returnData.put("message", jobj.getString("message"));
				returnData.put("status", "1");
				returnData.put("charge", jobj.getString("charge"));
				returnData.put("serviceTax", jobj.getString("message"));
				returnData.put("mobile", jobj.getString("mobile"));
				returnData.put("bene_name", jobj.getString("bene_name"));
				returnData.put("txnId", jobj.getString("txnId"));
			} else {
				returnData.put("status", "0");
				returnData.put("message", jobj.getString("message"));
			}
		} catch (Exception e) {
			logger_log.warn("ValidateBeneficiaryParser::::::::::::::::" + e);
		}

		return returnData;
	}

	public static Map<String, Object> addBeneficiaryParser(String response) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			JSONObject jobj = new JSONObject(response);
			if (jobj.getString("status").equalsIgnoreCase("00")) {
				returnData.put("message", jobj.getString("message"));
				returnData.put("bene_name", jobj.getString("bene_name"));
				returnData.put("bene_id", jobj.getString("bene_id"));
				returnData.put("status", "1");
			} else {
				returnData.put("message", jobj.getString("message"));
				returnData.put("status", "0");
			}

		} catch (Exception e) {
			logger_log.warn("addBeneficiaryParser:::::::::::::::::::" + e);
		}

		return returnData;
	}

	public static Map<String, Object> remitterRegisterParser(String response) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			JSONObject json = new JSONObject(response);
			if (json.getString("status").equalsIgnoreCase("00")) {
				returnData.put("message", json.getString("message"));
				returnData.put("status", "1");
			} else {
				returnData.put("status", "0");
				returnData.put("message", json.getString("message"));
			}
		} catch (Exception e) {
			logger_log.warn("remitterRegisterParser::::::::::::::::" + e);
		}

		return returnData;
	}

}
