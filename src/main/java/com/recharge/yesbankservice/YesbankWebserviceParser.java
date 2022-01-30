package com.recharge.yesbankservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.recharge.utill.GenerateRandomNumber;
import com.recharge.yesbankmodel.BeneficiaryYesBank;
import com.recharge.yesbankmodel.TransactionDetail;
import com.recharge.yesbankmodel.YesBankAEPSResponse;

public class YesbankWebserviceParser {
	private static final Logger logger_log = Logger.getLogger(YesbankWebserviceParser.class);
	
	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
	    Map<String, Object> map = new HashMap<String, Object>();

	    Iterator<String> keysItr = object.keys();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next();
	        Object value = object.get(key);

	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        map.put(key, value);
	    }
	    return map;
	}

	public static List<Object> toList(JSONArray array) throws JSONException {
	    List<Object> list = new ArrayList<Object>();
	    for(int i = 0; i < array.length(); i++) {
	        Object value = array.get(i);
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        list.add(value);
	    }
	    return list;
	}
	
	public static Map<String, Object> miniStatementParser(String res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String RESPONSE = "";
		try {
			JSONObject jobj = new JSONObject(res);
			if (jobj.isNull("error")) {
			int RESP_CODE= Integer.parseInt(jobj.getString("RESP_CODE"));
			if(RESP_CODE==300) {
			returnData.put("RESP_CODE",RESP_CODE);	
			returnData.put("RESPONSE",jobj.getString("RESPONSE"));
			returnData.put("RESP_MSG",jobj.getString("RESP_MSG"));	
			JSONObject datajobj = jobj.getJSONObject("DATA");
			returnData.put("DATA",datajobj);
			}else {
			returnData.put("RESP_CODE",RESP_CODE);	
			returnData.put("RESPONSE",jobj.getString("RESPONSE"));
			returnData.put("RESP_MSG",jobj.getString("RESP_MSG"));
			}
			}else {
			returnData.put("status", "0");
			returnData.put("error", jobj.getString("error"));
			returnData.put("error_description", jobj.getString("error_description"));
			}
			/*
			 * RESPONSE = jobj.getString("RESPONSE"); if
			 * (RESPONSE.equalsIgnoreCase("EPMONEY_CUST_NOT_EXIST")) {
			 * returnData.put("status", "0"); returnData.put("message",
			 * "customer doesn't exist"); }else if (RESPONSE.equalsIgnoreCase("FAILURE")) {
			 * returnData.put("status", "0"); returnData.put("message",
			 * "You are not authorized to use this service"); } else {
			 * returnData.put("status", "1"); }
			 */
			

		} catch (Exception e) {
			logger_log.error("miniStatementParser::::::::::::::::::::::" + e);
		}

		return returnData;
	}
	
	public static Map<String, Object> searchCustomerparser(String res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String RESPONSE = "";
		try {
			JSONObject jobj = new JSONObject(res);
			if (jobj.isNull("error")) {
				RESPONSE = jobj.getString("RESPONSE");
				if (RESPONSE.equalsIgnoreCase("EPMONEY_CUST_NOT_EXIST")) {
					returnData.put("status", "0");
					returnData.put("message", "customer doesn't exist");
				}else if (RESPONSE.equalsIgnoreCase("FAILURE")) {
					returnData.put("status", "0");
					returnData.put("message", "You are not authorized to use this service");
				}
				else {
					returnData.put("status", "1");
				}
			} else {
				returnData.put("status", "0");
				returnData.put("error", jobj.getString("error"));
				returnData.put("error_description", jobj.getString("error_description"));
			}

		} catch (Exception e) {
			logger_log.error("searchCustomerparser::::::::::::::::::::::" + e);
		}

		return returnData;
	}
	
	public static Map<String, Object> searchCustomerparserSKY(String res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String RESPONSE = "";
		int RESP_CODE=0;
		try {
			JSONObject jobj = new JSONObject(res);
			if (jobj.isNull("error")) {
				RESP_CODE=jobj.getInt("RESP_CODE");
				RESPONSE = jobj.getString("RESPONSE");
				if (RESPONSE.equalsIgnoreCase("SKYMONEY_CUST_NOT_EXIST")) {
					returnData.put("status", "0");
					returnData.put("message", "customer does not exist");
				}else if (RESPONSE.equalsIgnoreCase("FAILURE")) {
					returnData.put("status",RESP_CODE);
					returnData.put("message",jobj.getString("RESP_MSG"));
				}
				else {
					returnData.put("status", "1");
					returnData.put("message", "customer exists");
				}
			} else {
				returnData.put("status", "0");
				returnData.put("error", jobj.getString("error"));
				returnData.put("error_description", jobj.getString("error_description"));
			}

		} catch (Exception e) {
			logger_log.error("searchCustomerparser::::::::::::::::::::::" + e);
		}

		return returnData;
	}
	
	public static Map<String, Object> checkuserYesBankparser(String res) {
		String RESPONSE = "";
		int RESP_CODE = 0;
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			JSONObject jobj = new JSONObject(res);
			if (jobj.isNull("error")) {
				RESP_CODE = jobj.getInt("RESP_CODE");
				if (RESP_CODE == 200 || RESP_CODE == 700) {
					JSONObject datajobj = jobj.getJSONObject("DATA");
					returnData.put("status", "1");
					returnData.put("RESP_CODE", RESP_CODE);
					returnData.put("message",jobj.getString("RESP_MSG"));
					if (RESP_CODE == 200) {
						returnData.put("limit", datajobj.getDouble("SENDER_AVAILBAL"));
					}
					// returnData.put("MOBILENO",datajobj.getString("mobile"));
					returnData.put("customername", datajobj.getString("SEDNER_FNAME"));
					returnData.put("SENDER_CUSTTYPE", datajobj.getString("SENDER_CUSTTYPE"));
					JSONArray benearr = (JSONArray) datajobj.getJSONArray("BENEFICIARY_DATA");
					List<BeneficiaryYesBank> list = new ArrayList<>();
					for (int i = 0; i < benearr.length(); i++) {
						JSONObject beneobj = (JSONObject) benearr.get(i);
						BeneficiaryYesBank bene = new BeneficiaryYesBank();
						bene.setId(beneobj.getInt("BENE_ID"));
						bene.setAccount(beneobj.getString("BANK_ACCOUNTNO"));
						bene.setIfsc(beneobj.getString("BANKIFSC_CODE"));
						bene.setMobile(beneobj.getString("BENE_MOBILENO"));
						bene.setName(beneobj.getString("BENE_NAME"));
						bene.setBank(beneobj.getString("BENE_BANKNAME"));
						bene.setOtpverified(beneobj.getBoolean("BENE_OTP_VERIFIED"));
						list.add(bene);
					}
					returnData.put("beneficiarylist", list);
				} else {
					returnData.put("RESP_CODE",RESP_CODE);
					returnData.put("status", "0");
					returnData.put("message", jobj.getString("RESP_MSG"));
				}
			} else {
				if(jobj.has("RESP_CODE")){
					returnData.put("RESP_CODE",jobj.getInt("RESP_CODE"));
				}
				
				returnData.put("status", "0");
				returnData.put("message", jobj.getString("error_description"));
				returnData.put("error", jobj.getString("error"));
				returnData.put("error_description", jobj.getString("error_description"));

			}

		} catch (Exception e) {
			logger_log.error("checkuserYesBankparser::::::::::::::::::::::" + e);
		}

		return returnData;
	}

	public static Map<String, Object> beneficiaryParser(String res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String RESPONSE = "";
		int RESP_CODE = 0;
		try {
			JSONObject jobj = new JSONObject(res);
			if (jobj.isNull("error")) {
				RESP_CODE = jobj.getInt("RESP_CODE");
				if (RESP_CODE == 200) {
					RESPONSE = jobj.getString("RESPONSE");
					if (RESPONSE.equalsIgnoreCase("EPMONEY_LIST_BENEFICIARY_SUCCESS")) {
						JSONObject datajobj = jobj.getJSONObject("DATA");
						JSONArray benearr = (JSONArray) datajobj.getJSONArray("BENEFICIARY_DATA");
						List<BeneficiaryYesBank> list = new ArrayList<>();
						for (int i = 0; i < benearr.length(); i++) {
							JSONObject beneobj = (JSONObject) benearr.get(i);
							BeneficiaryYesBank bene = new BeneficiaryYesBank();
							bene.setId(beneobj.getInt("BENE_ID"));
							bene.setAccount(beneobj.getString("BANK_ACCOUNTNO"));
							bene.setIfsc(beneobj.getString("BANKIFSC_CODE"));
							bene.setMobile(beneobj.getString("BENE_MOBILENO"));
							bene.setName(beneobj.getString("BENE_NAME"));
							list.add(bene);
						}
						returnData.put("beneficiarylist", list);
					}
				}
			} else {
				returnData.put("RESP_CODE",jobj.getInt("RESP_CODE"));
				returnData.put("message", jobj.getString("error_description"));
				returnData.put("status", "0");
				returnData.put("error", jobj.getString("error"));
				returnData.put("error_description", jobj.getString("error_description"));
			}
		} catch (Exception e) {
			logger_log.error("beneficiaryParser::::::::::::::::::::::" + e);
		}

		return returnData;
	}
	
	public static Map<String, Object> addbeneficiaryParser(String res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String RESPONSE = "";
		int RESP_CODE = 0;
		try {
			JSONObject jobj = new JSONObject(res);
			if (jobj.isNull("error")) {
				RESP_CODE = jobj.getInt("RESP_CODE");
				RESPONSE = jobj.getString("RESPONSE");
				if (RESP_CODE == 200) {
					returnData.put("status", "1");
					returnData.put("message", jobj.getString("RESP_MSG"));
					returnData.put("id", jobj.getInt("BENE_ID"));
					returnData.put("REQUESTNO", jobj.getInt("RESPONSE_CODE"));
				} else {
					returnData.put("RESP_CODE",jobj.getInt("RESP_CODE"));
					returnData.put("status", "0");
					returnData.put("message", jobj.getString("RESP_MSG"));
				}

			}
		} catch (Exception e) {
			logger_log.error("addbeneficiaryParser::::::::::::::::::::::" + e);
		}

		return returnData;
	}
	
	public static Map<String, Object> yesBankMoneytransfer(String res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String RESPONSE = "";
		int RESP_CODE = 0;
		try {
			JSONObject jobj = new JSONObject(res);
			if (jobj.isNull("error")) {
				RESP_CODE = jobj.getInt("RESP_CODE");
				RESPONSE = jobj.getString("RESPONSE");
				if (RESP_CODE == 300) {
					returnData.put("status", "1");
					returnData.put("message", jobj.getString("RESP_MSG"));
					//returnData.put("BENE_ID", jobj.getInt("BENE_ID"));
					JSONObject datajobj = jobj.getJSONObject("DATA");
					String CUSTOMER_REFERENCE_NO = datajobj.getString("CUSTOMER_REFERENCE_NO");
					returnData.put("CUSTOMER_REFERENCE_NO", CUSTOMER_REFERENCE_NO);
					JSONArray tranarr = datajobj.getJSONArray("TRANSACTION_DETAILS");
					List<TransactionDetail> transdetail = new ArrayList<TransactionDetail>();
					for (int i = 0; i < tranarr.length(); i++) {
						TransactionDetail trans = new TransactionDetail();
						JSONObject tranobj = tranarr.getJSONObject(0);
						String REQUEST_REFERENCE_NO = tranobj.getString("REQUEST_REFERENCE_NO");
						String EP_REFERENCE_NO = tranobj.getString("EP_REFERENCE_NO");
						double TRANSFER_AMOUNT = tranobj.getDouble("TRANSFER_AMOUNT");
						double PAID_AMOUNT = tranobj.getDouble("PAID_AMOUNT");
						trans.setPAID_AMOUNT(PAID_AMOUNT);
						trans.setREQUEST_REFERENCE_NO(REQUEST_REFERENCE_NO);
						trans.setRESPONSE_REFERENCE_NO(EP_REFERENCE_NO);
						trans.setTRANSFER_AMOUNT(TRANSFER_AMOUNT);
						transdetail.add(trans);

					}
					returnData.put("transdetail", transdetail);
				}
				else if (RESP_CODE == 302) {
					returnData.put("status", "3");
					JSONObject datajobj = jobj.getJSONObject("DATA");
					String CUSTOMER_REFERENCE_NO = datajobj.getString("CUSTOMER_REFERENCE_NO");
					returnData.put("CUSTOMER_REFERENCE_NO", CUSTOMER_REFERENCE_NO);
					JSONArray tranarr = datajobj.getJSONArray("TRANSACTION_DETAILS");
					List<TransactionDetail> transdetail = new ArrayList<TransactionDetail>();
					for (int i = 0; i < tranarr.length(); i++) {
						TransactionDetail trans = new TransactionDetail();
						JSONObject tranobj = tranarr.getJSONObject(i);
						String REQUEST_REFERENCE_NO = tranobj.getString("REQUEST_REFERENCE_NO");
						String EP_REFERENCE_NO = tranobj.getString("EP_REFERENCE_NO");
						double TRANSFER_AMOUNT = tranobj.getDouble("TRANSFER_AMOUNT");
						double PAID_AMOUNT = tranobj.getDouble("PAID_AMOUNT");
						trans.setPAID_AMOUNT(PAID_AMOUNT);
						trans.setREQUEST_REFERENCE_NO(REQUEST_REFERENCE_NO);
						trans.setRESPONSE_REFERENCE_NO(EP_REFERENCE_NO);
						trans.setTRANSFER_AMOUNT(TRANSFER_AMOUNT);
						transdetail.add(trans);

					}
					returnData.put("transdetail", transdetail);

				}

				else {
					returnData.put("RESP_CODE",jobj.getInt("RESP_CODE"));
					returnData.put("status", "0");
					returnData.put("message",jobj.getString("RESPONSE"));
				}

			}
		} catch (Exception e) {
			logger_log.error("addbeneficiaryParser::::::::::::::::::::::" + e);
		}

		return returnData;
	}

	public static Map<String, Object> customerRegisterparser(String res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String RESPONSE = "";
		int RESP_CODE = 0;
		// System.out.println("rescust:::::::::::::::::::::::::::::::::"+res);
		try {
			JSONObject jobj = new JSONObject(res);
			if (jobj.isNull("error")) {
				RESP_CODE = jobj.getInt("RESP_CODE");
				RESPONSE = jobj.getString("RESPONSE");
				System.out.println("RESP_CODE::::::::::::::::::" + RESP_CODE);
				if (RESP_CODE == 200) {
					returnData.put("status", "1");
				} else {
					returnData.put("status", "0");
					if (RESP_CODE == 500) {
						returnData.put("message", "customer doesn't exist");
					} else if (RESP_CODE == 420) {
						returnData.put("message", jobj.getString("RESP_MSG"));
					} else {
						returnData.put("message", jobj.getString("RESP_MSG"));
					}
				}
			} else {
				returnData.put("status", "0");
				//returnData.put("RESP_CODE",jobj.getInt("RESP_CODE"));
				returnData.put("message", jobj.getString("error_description"));
				returnData.put("error", jobj.getString("error"));
				returnData.put("error_description", jobj.getString("error_description"));
			}

		} catch (Exception e) {
			logger_log.error("searchCustomerparser::::::::::::::::::::::" + e);
		}

		return returnData;
	}
	
	
	public static Map<String, Object> generateOTPparser(String res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String RESPONSE = "";
		int RESP_CODE = 0;
		// System.out.println("generateOTPparser:::::::::::::::::::::::::::::::::"+res);
		try {
			JSONObject jobj = new JSONObject(res);
			if (jobj.isNull("error")) {
				RESP_CODE = jobj.getInt("RESP_CODE");
				RESPONSE = jobj.getString("RESPONSE");
				System.out.println("RESP_CODE::::::::::::::::::" + RESP_CODE);
				if (RESP_CODE == 200) {
					returnData.put("status", "1");
					returnData.put("REQUESTNO", jobj.getInt("RESPONSE_CODE"));
					returnData.put("message", jobj.getString("RESP_MSG"));
					if(jobj.has("BENE_ID")){
					returnData.put("id", jobj.getInt("BENE_ID"));
					}
				} else {
					returnData.put("message", jobj.getString("RESP_MSG"));
					returnData.put("status", "0");

				}
			} else {
				returnData.put("status", "0");
				returnData.put("message", jobj.getString("error_description"));
				returnData.put("error", jobj.getString("error"));
				returnData.put("error_description", jobj.getString("error_description"));
			}

		} catch (Exception e) {
			logger_log.error("generateOTPparser::::::::::::::::::::::" + e);
		}

		return returnData;
	}
	
	public static Map<String, Object> verifyOTPparser(String res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String RESPONSE = "";
		int RESP_CODE = 0;
		// System.out.println("verifyOTPparser:::::::::::::::::::::::::::::::::"+res);
		try {
			JSONObject jobj = new JSONObject(res);
			if (jobj.isNull("error")) {
				RESP_CODE = jobj.getInt("RESP_CODE");
				RESPONSE = jobj.getString("RESPONSE");
				System.out.println("RESP_CODE::::::::::::::::::" + RESP_CODE);
				if (RESP_CODE == 200) {
					returnData.put("status", "1");
					returnData.put("message", jobj.getString("RESP_MSG"));
				} else {
					returnData.put("status", "0");
					returnData.put("message", jobj.getString("RESP_MSG"));

				}
			} else {
				returnData.put("status", "0");
				returnData.put("message", jobj.getString("error_description"));
				returnData.put("error", jobj.getString("error"));
				returnData.put("error_description", jobj.getString("error_description"));
			}

		} catch (Exception e) {
			logger_log.error("verifyOTPparser::::::::::::::::::::::" + e);
		}

		return returnData;
	}
	
	
	public static Map<String, Object> refundOTPParser(String res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String RESPONSE = "";
		int RESP_CODE = 0;
		//System.out.println("refundOTPParser:::::::::::::::::::::::::::::::::" + res);
		try {
			JSONObject jobj = new JSONObject(res);
			if (jobj.isNull("error")) {
				RESP_CODE = jobj.getInt("RESP_CODE");
				RESPONSE = jobj.getString("RESPONSE");
				//System.out.println("RESP_CODE::::::::::::::::::" + RESP_CODE);
				if (RESP_CODE == 200) {
					returnData.put("status", "1");
					returnData.put("message", jobj.getString("RESP_MSG"));
				} else {
					returnData.put("status", "0");
					returnData.put("message", jobj.getString("RESP_MSG"));

				}
			} else {
				returnData.put("error", jobj.getString("error"));
				returnData.put("error_description", jobj.getString("error_description"));
			}

		} catch (Exception e) {
			logger_log.error("refundOTPParser::::::::::::::::::::::" + e);
		}

		return returnData;
	}
	

	
	public static Map<String, Object> checkImpsStatusparser(String res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String RESPONSE = "";
		String RESP_MSG = "";
		int RESP_CODE = 0;
		// System.out.println("rescust:::::::::::::::::::::::::::::::::"+res);
		try {
			JSONObject jobj = new JSONObject(res);
			if (jobj.isNull("error")) {
				RESP_CODE = jobj.getInt("RESP_CODE");
				RESPONSE = jobj.getString("RESPONSE");
				RESP_MSG = jobj.getString("RESP_MSG");
				System.out.println("RESP_CODE::::::::::::::::::" + RESP_CODE);
				if (RESP_CODE == 200) {
					JSONObject dataobj = jobj.getJSONObject("DATA");
					String status = dataobj.getString("TRANSACTION_STATUS");
					returnData.put("status", status);
					returnData.put("REQUEST_REFERENCE_NO", dataobj.getString("REQUEST_REFERENCE_NO"));
					if (status.equalsIgnoreCase("SUCCESS")) {
						returnData.put("BANK_REFERENCE_NO", dataobj.getString("BANK_REFERENCE_NO"));
					}

				} else {
					returnData.put("status", "0");
					returnData.put("status", "FAILED");
					/* if(RESP_CODE ==500){ */

					/* }else if(RESP_CODE ==420){ */

					/* } */
				}
			} else {
				returnData.put("error", jobj.getString("error"));
				returnData.put("error_description", jobj.getString("error_description"));
				returnData.put("status", "0");
			}

		} catch (Exception e) {
			logger_log.error("checkImpsStatusparser::::::::::::::::::::::" + e);
		}

		return returnData;
	}
	
	
	public static Map<String, Object> getDevicePIDparser(String res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String RESPONSE = "";
		String RESP_MSG = "";
		int RESP_CODE = 0;
		// System.out.println("rescust:::::::::::::::::::::::::::::::::"+res);
		try {
			JSONObject jobj = new JSONObject(res);
			if (jobj.isNull("error")) {
				RESP_CODE = jobj.getInt("RESP_CODE");
				RESPONSE = jobj.getString("RESPONSE");
				RESP_MSG = jobj.getString("RESP_MSG");
				System.out.println("RESP_CODE::::::::::::::::::" + RESP_CODE);
				if (RESP_CODE == 200) {
					if (RESP_MSG.equalsIgnoreCase("RD data hash fetch successfully.")) {
						JSONObject datjobj = (JSONObject) jobj.getJSONObject("DATA");
						String pidOpt = datjobj.getString("pidOpt");
						String reqUrl = datjobj.getString("reqUrl");
						returnData.put("status", "1");
						returnData.put("reqUrl", reqUrl);
						returnData.put("pidOpt", pidOpt);
					}

				} else {
					returnData.put("status", "0");
					/* if(RESP_CODE ==500){ */
					returnData.put("message", "customer doesn't exist");
					/* }else if(RESP_CODE ==420){ */

					/* } */
				}
			} else {
				returnData.put("status", "0");
				returnData.put("message", jobj.getString("error_description"));
				returnData.put("error", jobj.getString("error"));
				returnData.put("error_description", jobj.getString("error_description"));
			}

		} catch (Exception e) {
			logger_log.error("getDevicePIDparser::::::::::::::::::::::" + e);
		}

		return returnData;
	}
	
	public static Map<String, Object> yesBankTransaction(String response){
		Map<String, Object> returnData = new HashMap<String, Object>();
		String RESPONSE="";
		int RESP_CODE =0;
		try{
			JSONObject jobj= new JSONObject(response);
			if(jobj.isNull("error")){
				RESP_CODE =jobj.getInt("RESP_CODE");
				RESPONSE=jobj.getString("RESPONSE");
				if(RESPONSE.equalsIgnoreCase("SUCCESS")){
					
				}
			}
			
		}catch (Exception e) {
			logger_log.error("yesBankTransaction::::::::::::::::::::::"+e);
		}
		
		
		return returnData;
	}

	public static String getClientCredential(String response){
		String token ="";
		
		try{
			JSONObject jobj = new JSONObject(response);
			if(jobj.isNull("error")){
				if(!jobj.isNull("access_token")){
					token =jobj.getString("access_token");	
				}
			}
			
		}catch (Exception e) {
			logger_log.error("getClientCredential::::::::::::::::::::"+e);
		}
		
		return token;
		
	}

	public static YesBankAEPSResponse yesBankaepsResponseParser(String response) {
		YesBankAEPSResponse aepsres = null;
		String STAN = "";
		String RRN = "";
		String Aadhar = "";
		String IIN = "";
		String Status = "";
		String TxnAmount = "";
		String ResponseCode = "";
		String AccountType = "";
		String BalanceType = "";
		String CurrancyCode = "";
		String BalanceIndicator = "";
		String BalanceAmount = "";
		String AccountTypeLedger = "";
		String BalanceTypeLedger = "";
		String CurrancyCodeLedger = "";
		String BalanceIndicatorLedger = "";
		String BalanceAmountLedger = "";
		String AccountTypeActual = "";
		String BalanceTypeActual = "";
		String CurrancyCodeActual = "";
		String BalanceIndicatorActual = "";
		String BalanceAmountActual = "";
		String RetailerTxnId = "";
		String date = GenerateRandomNumber.getCurrentDate();
		String time = GenerateRandomNumber.getCurrentTime();
		double  txnCharge = 0.0;
		double paidAmount = 0.0;
		/*String STAN = "";
		String STAN = "";*/
		try {
			JSONObject jobj = new JSONObject(response);
			int RESP_CODE=jobj.getInt("RESP_CODE");
			String RESPONSE=jobj.getString("RESP_MSG");
			
				JSONObject Data = jobj.getJSONObject("DATA");
				if(!Data.isNull("STAN")){
					STAN = Data.getString("STAN");	
				}
				if(!Data.isNull("RRN")){
					RRN = Data.getString("RRN");	
				}
				if(!Data.isNull("Aadhar")){
					Aadhar = Data.getString("Aadhar");	
					System.out.println("Aadhar::::::::::::::::::::"+Aadhar);
				}
				if(!Data.isNull("IIN")){
					IIN = Data.getString("IIN");		
				}
				if(!Data.isNull("TxnAmount")){
					TxnAmount =String.valueOf(Data.getDouble("TxnAmount"));		
				}
				if(!Data.isNull("AccountType")){
					AccountType = Data.getString("AccountType");		
				}
				if(!Data.isNull("BalanceType")){
					BalanceType = Data.getString("BalanceType");	
				}
				if(!Data.isNull("CurrancyCode")){
					CurrancyCode = Data.getString("CurrancyCode");	
				}
				if(!Data.isNull("BalanceIndicator")){
					BalanceIndicator = Data.getString("BalanceIndicator");		
				}
				if(!Data.isNull("BalanceAmount")){
					BalanceAmount = Data.getString("BalanceAmount");		
				}
				if(!Data.isNull("AccountTypeLedger")){
					AccountTypeLedger = Data.getString("AccountTypeLedger");		
				}
				if(!Data.isNull("BalanceTypeLedger")){
					BalanceTypeLedger = Data.getString("BalanceTypeLedger");	
				}
				if(!Data.isNull("CurrancyCodeLedger")){
					CurrancyCodeLedger = Data.getString("CurrancyCodeLedger");	
				}
				if(!Data.isNull("BalanceIndicatorLedger")){
					BalanceIndicatorLedger =Data.getString("BalanceIndicatorLedger");	
				}
				if(!Data.isNull("BalanceAmountLedger")){
					Object balaceamount = Data.get("BalanceAmountLedger");
					if(balaceamount instanceof Double){
					BalanceAmountLedger =  String.valueOf(Data.getDouble("BalanceAmountLedger"));	
					}else{
					BalanceAmountLedger = Data.getString("BalanceAmountLedger");	
					}
				}
				if(!Data.isNull("AccountTypeActual")){
					AccountTypeActual =Data.getString("AccountTypeActual");		
				}
				if(!Data.isNull("BalanceTypeActual")){
					Object balaceatypeactual = Data.get("BalanceTypeActual");
					if(balaceatypeactual instanceof Double){
					BalanceTypeActual =  String.valueOf(Data.getDouble("BalanceTypeActual"));	
					}else{
					BalanceTypeActual = Data.getString("BalanceTypeActual");	
					}
				}
				if(!Data.isNull("CurrancyCodeActual")){
					CurrancyCodeActual = Data.getString("CurrancyCodeActual");		
				}
				if(!Data.isNull("BalanceIndicatorActual")){
					BalanceIndicatorActual = Data.getString("BalanceIndicatorActual");	
				}
				if(!Data.isNull("BalanceAmountActual")){
					Object balanceamountactual = Data.get("BalanceAmountActual");
					if(balanceamountactual instanceof Double){
					BalanceAmountActual =  String.valueOf(Data.getDouble("BalanceAmountActual"));	
					}else{
					BalanceAmountActual = Data.getString("BalanceAmountActual");	
					}	
				}
				if(!Data.isNull("RetailerTxnId")){
					RetailerTxnId = Data.getString("RetailerTxnId");		
				}
				if(!Data.isNull("txnCharge")){
					txnCharge = Data.getDouble("txnCharge");		
				}
				if(!Data.isNull("paidAmount")){
					paidAmount = Data.getDouble("paidAmount");		
				}
				if(!Data.isNull("Status")){
					Status = String.valueOf(Data.getInt("Status"));		
				}
				if(!Data.isNull("ResponseCode")){
					ResponseCode = Data.getString("ResponseCode");		
				}
				
				/*
				 * 
				 * 
				 * if(!Data.isNull("")){
					STAN = Data.getString("STAN");		
				}
				if(!Data.isNull("")){
					STAN = Data.getString("STAN");		
				}
				 * if(!Data.isNull("")){
					STAN = Data.getString("STAN");		
				}
				if(!Data.isNull("")){
					STAN = Data.getString("STAN");		
				}
				if(!Data.isNull("")){
					STAN =Data.getString("STAN");		
				}
				if(!Data.isNull("")){
					STAN =Data.getString("STAN");		
				}
				if(!Data.isNull("")){
					STAN =Data.getString("STAN");		
				}*/
				
				
			
			aepsres = new YesBankAEPSResponse(STAN, RRN, Aadhar,IIN, TxnAmount,ResponseCode,AccountType,BalanceType,CurrancyCode,BalanceIndicator,BalanceAmount,AccountTypeLedger,BalanceTypeLedger,CurrancyCodeLedger, BalanceIndicatorLedger,BalanceAmountLedger,AccountTypeActual,BalanceTypeActual,CurrancyCodeActual,BalanceIndicatorActual,BalanceAmountActual,Status,RetailerTxnId, txnCharge, paidAmount, date, time, "", "");
			aepsres.setRESP_CODE(String.valueOf(RESP_CODE));
			aepsres.setRESPONSE(RESPONSE);

		} catch (Exception e) {
			logger_log.error("yesBankaepsResponseParser::::::::::::::::::::" + e);

		}

		return aepsres;
	}
	
	
	public static void main(String...args){
		String res="{'error':'invalid_token','error_description':'Invalid access token: d532a21a-ff98-48f2-8097-afd013e7cb67'}";
		searchCustomerparser(res);
	}
	
	

}
