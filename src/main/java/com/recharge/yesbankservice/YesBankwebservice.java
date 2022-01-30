package com.recharge.yesbankservice;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;



import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;


import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.recharge.model.AEPSLog;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class YesBankwebservice {
	private static final Logger logger_log = Logger.getLogger(YesBankBusiness.class);
	/* demo */
//	 private static final String DMRURL="https://paisanikal.co.in";
	/* Live */
	private static final String DMRURL = "https://paisanikalapi.easypay.co.in";
	/* demo */
	
	 /* private static final String username ="triotech";
	  private static final String password ="tRioTeCH";*/
	

	/* Live */
	private static final String username = "ENcoDIGi";
	private static final String password = "ENcoD#3761@EP";

	public static String sendRequestToYesBankDMR(String token, Object input, String methodName) {
		
		String url = DMRURL + "/" + methodName;
		Client restClient = Client.create();
		
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type(MediaType.APPLICATION_JSON_TYPE).accept("application/json")
				.header("Authorization", "Bearer " + token).post(ClientResponse.class, input);
		if (resp.getStatus() != 200) {
			logger_log.warn("Unable to connect to the server");
		}
		String output = resp.getEntity(String.class);
		logger_log.warn("sendRequestToYesBankDMR::::::::::::::::" + output);
		return output;
	}

	public static String sendRequestToYesBankDMR2(String token, Object input, String methodName) {
		
		String url = DMRURL + "/" + methodName;
		Client restClient = Client.create();
		MultivaluedMap formData = new MultivaluedMapImpl();
		formData.add("jsonData", input);
		String i = (String) input;
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).accept("application/json")
				.header("Authorization", "Bearer " + token).post(ClientResponse.class, formData);

		System.out.println();
		if (resp.getStatus() != 200) {
			logger_log.warn("Unable to connect to the server");
		}
		String output = resp.getEntity(String.class);
		logger_log.warn("sendRequestToYesBankDMR2::::::::::::" + output);
		return output;
	}

	public static String sendRequestToyessBank(String token, String input, String url) {
		logger_log.warn("sendRequestToyessBankinput::::::::::::::::::::::" + input);
		logger_log.warn("sendRequestToyessBankurl::::::::::::::::::::::" + url);
		// String response ="";
		Client restClient = Client.create();
		MultivaluedMap formData = new MultivaluedMapImpl();
		formData.add("jsonData", input);

		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).accept("application/json")
				.header("Authorization", "Bearer " + token).post(ClientResponse.class, formData);

		System.out.println();
		if (resp.getStatus() != 200) {
			logger_log.warn("Unable to connect to the server");
		}
		String output = resp.getEntity(String.class);
		logger_log.warn("sendRequestToyessBank:::::::::::::::::"+output);
		return output;
	}

	
	public static String sendRequestToyessBankministatement(String token, String input, String checksum, String url) {
		logger_log.warn("sendRequestToyessBankinput::::::::::::::::::::::" + input);
		logger_log.warn("sendRequestToyessBankurl::::::::::::::::::::::" + url);
		// String response ="";
		Client restClient = Client.create();
		MultivaluedMap formData = new MultivaluedMapImpl();
		formData.add("jsonData", input);

		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).accept("application/json")
				.header("Authorization", "Bearer " + token).header("CHECKSUM", checksum)
				.post(ClientResponse.class, formData);

		System.out.println();
		if (resp.getStatus() != 200) {
			logger_log.warn("Unable to connect to the server");
		}
		String output = resp.getEntity(String.class);
		logger_log.warn("sendRequestToyessBank:::::::::::::::::"+output);
		return output;
	}

	public static String sendRequestToyessBankTransaction(String token, String input, String checksum, String mode) {
		logger_log.warn("sendRequestToyessBankTransactionINPUT:::::::::::: " + input);
		String response = "";
		// token ="d532a21a-ff98-48f2-8097-afd013e7cb67";
		// input = "{\"HEADER\": {\"ST\": \"BALANCEINFO\",\"AID\":
		// \"RS00789\",\"OP\": \"AEPS\"},\"DATA\": {\"CUSTOMER_MOBILE\":
		// \"8013724327\"}}";
		String url = "";
		if (mode.equalsIgnoreCase("BALANCEINFO")) {
			/* demo */
		// url ="https://uat2yesmoney.easypay.co.in/epMoney/txn/rest/aeps/balanceinfo";
			/* Live */
			url = "https://paisanikalapi.easypay.co.in/epMoney/txn/rest/aeps/balanceinfo";
		} else if (mode.equalsIgnoreCase("WITHDRAWAL")) {
			/* demo */
			// url ="https://uat2yesmoney.easypay.co.in/epMoney/txnNonCash/rest/aeps/accountwithdrawal";
			/* Live */
			url = "https://paisanikalapi.easypay.co.in/epMoney/txnNonCash/rest/aeps/accountwithdrawal";
		}
		Client restClient = Client.create();
		MultivaluedMap formData = new MultivaluedMapImpl();
		formData.add("jsonData", input);

		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).accept("application/json")
				.header("Authorization", "Bearer " + token).header("CHECKSUM", checksum)
				.post(ClientResponse.class, formData);

		System.out.println();
		if (resp.getStatus() != 200) {
			logger_log.warn("Unable to connect to the server");
		}
		response = resp.getEntity(String.class);
		logger_log.warn("sendRequestToyessBankTransactionresponse:::::::::::: " + response);
		return response;
	}

	public static Map<String, Object> searchCustomer(String token, String input) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String res = "";
		try {
			/* demo */
			//String url ="https://uat2yesmoney.easypay.co.in/epMoney/rest/aeps/searchcustomer";
			/* Live */
			String url = "https://paisanikalapi.easypay.co.in/epMoney/rest/aeps/searchcustomer";
			res = sendRequestToyessBank(token, input, url);
			logger_log.warn("searchCustomerres:::::::::::::::::::" + res);
			returnData = YesbankWebserviceParser.searchCustomerparser(res);
		} catch (Exception e) {
			logger_log.error("searchCustomer::::::::::::::::::::" + e);
		}

		return returnData;

	}
	
	public static Map<String, Object> miniSettlement(String token, String input,String checksum) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String res = "";
		try {
			/* demo */
			//String url ="https://uat2yesmoney.easypay.co.in/epMoney/rest/aeps/searchcustomer";
			/* Live */
			String url = "https://paisanikalapi.easypay.co.in/epMoney/txn/rest/aeps/miniStatement";
			res = sendRequestToyessBankministatement(token, input,checksum, url);
			logger_log.warn("miniSettlement:::::::::::::::::::" + res);
			returnData = YesbankWebserviceParser.miniStatementParser(res);
		} catch (Exception e) {
			logger_log.error("searchCustomer::::::::::::::::::::" + e);
		}

		return returnData;

	}

	public static Map<String, Object> checkuserYesBank(String token, String input) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String res = "";
		try {
			String method = "epMoney/retvalcustomer/v1.0";
			res = sendRequestToYesBankDMR(token, input, method);
			logger_log.warn("checkuserYesBankres:::::::::::::::::::" + res);
			returnData = YesbankWebserviceParser.checkuserYesBankparser(res);
		} catch (Exception e) {
			logger_log.error("checkuserYesBank::::::::::::::::::::" + e);
		}

		return returnData;

	}

	public static Map<String, Object> yesBankRemitterRegister(String token, String input) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String res = "";
		try {
			String method = "epMoney/add-customer/v1.0";
			res = sendRequestToYesBankDMR(token, input, method);
			// res="{'RESP_CODE':200,'RESPONSE':'EPMONEY_ADD_CUSTOMER_BENEFICIARY_SUCCESS','RESP_MSG':'Customer
			// and Beneficiary added successfully, OTP sent to registered
			// xxxxxx3211 for beneficiary
			// verification','BENE_ID':411,'RESPONSE_CODE':852}";
			logger_log.warn("yesBankRemitterRegisterres:::::::::::::::::::" + res);
			returnData = YesbankWebserviceParser.generateOTPparser(res);
		} catch (Exception e) {
			logger_log.error("yesBankRemitterRegister::::::::::::::::::::" + e);
		}

		return returnData;

	}

	public static Map<String, Object> verifyremitter(String token, String input) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String res = "";
		try {
			String method = "epMoney/generateotp/v1.0";
		res = sendRequestToYesBankDMR(token, input,method);
			// res="{'RESP_CODE':200,'RESPONSE':'EPMONEY_ADD_CUSTOMER_BENEFICIARY_SUCCESS','RESP_MSG':'Customer
			// and Beneficiary added successfully, OTP sent to registered
			// xxxxxx3211 for beneficiary
			// verification','BENE_ID':411,'RESPONSE_CODE':852}";
			//res="{'RESP_CODE':200,'RESPONSE':'EPMONEY_GENERATE_OTPCUSTVERIFICATION_SUCCESS','RESP_MSG':'OTP sent to registered xxxxxx6101 for customer verification','RESPONSE_CODE':3730353}";
			logger_log.warn("verifyremitterres:::::::::::::::::::" + res);
			returnData = YesbankWebserviceParser.generateOTPparser(res);
		} catch (Exception e) {
			logger_log.error("verifyremitter::::::::::::::::::::" + e);
		}

		return returnData;

	}

	public static Map<String, Object> yesBankNewBeneficiary(String token, String input) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String res = "";
		try {
			String method = "epMoney/add-beneficiary/v1.0";
			res = sendRequestToYesBankDMR(token, input, method);
			// res="{'RESP_CODE':200,'RESPONSE':'EPMONEY_ADD_BENEFICIARY_SUCCESS','RESP_MSG':'Add
			// beneficiary successfully, OTP sent to registered xxxxxx4327 for
			// beneficiary verification','BENE_ID':404,'RESPONSE_CODE':834}";
			logger_log.warn("yesBankNewBeneficiaryres:::::::::::::::::::" + res);
			returnData = YesbankWebserviceParser.addbeneficiaryParser(res);
		} catch (Exception e) {
			logger_log.error("yesBankNewBeneficiary::::::::::::::::::::" + e);
		}

		return returnData;

	}

	public static Map<String, Object> yesBankValidateBeneficiary(String token, String input) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String res = "";
		try {
			String method = "epMoney/generateotp/v1.0";
			logger_log.warn("token:::::::::::::" + token);
			res = sendRequestToYesBankDMR(token, input, method);
			// res="{'RESP_CODE':200,'RESPONSE':'EPMONEY_GENERATE_OTPCUSTREFUND_SUCCESS','RESP_MSG':'OTP
			// sent to registered xxxxxx4327 for customer
			// refund','RESPONSE_CODE':1160}";

			logger_log.warn("yesBankValidateBeneficiary:::::::::::::::::::" + res);
			returnData = YesbankWebserviceParser.generateOTPparser(res);
		} catch (Exception e) {
			logger_log.error("yesBankValidateBeneficiary::::::::::::::::::::" + e);
		}

		return returnData;

	}

	public static Map<String, Object> refundOTP(String token, String input) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String res = "";
		try {
			String method = "epMoney/cp/refund-transaction/v1.0";
			res = sendRequestToYesBankDMR2(token, input, method);
			// res="{'RESP_CODE':200,'RESP_MSG':'Transaction Refund has been
			// successfully
			// processed.','RESPONSE':'EPMONEY_UPDATE_TRANSACTIONSTATUS_SUCCESS','DATA':{'SENDER_AVAILBAL':24990}}";

			logger_log.warn("refundOTP:::::::::::::::::::" + res);
			returnData = YesbankWebserviceParser.refundOTPParser(res);
		} catch (Exception e) {
			logger_log.error("refundOTP::::::::::::::::::::" + e);
		}

		return returnData;

	}

	public static Map<String, Object> yesBankVerifyBeneficiary(String token, String input) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String res = "";
		try {
			String method = "epMoney/verifyotp/v1.0";
			res = sendRequestToYesBankDMR(token, input, method);
			// res="{'RESP_CODE':200,'RESPONSE':'EPMONEY_VERIFY_OTP_SUCCESS','RESP_MSG':'OTP
			// verification successfully.'}";
			logger_log.warn("yesBankVerifyBeneficiary:::::::::::::::::::" + res);
			returnData = YesbankWebserviceParser.verifyOTPparser(res);
		} catch (Exception e) {
			logger_log.error("yesBankNewBeneficiary::::::::::::::::::::" + e);
		}

		return returnData;

	}

	public static Map<String, Object> VerifyDeleteYesBane(String token, String input) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String res = "";
		try {
			String method = "epMoney/delete-beneficiary/v1.0";
			// System.out.println("token:::::::::::::"+token);
			res = sendRequestToYesBankDMR(token, input, method);
			// res="{'RESP_CODE':520,'RESPONSE':'EPMONEY_BENEFICIARY_REFUND_PENDING','RESP_MSG':'Beneficiary
			// can not delete as transactions in pending / failed status'}";
			
			returnData = YesbankWebserviceParser.verifyOTPparser(res);
			logger_log.warn("VerifyDeleteBane:::::::::::::::::::::" + returnData);
		} catch (Exception e) {
			logger_log.error("VerifyDeleteBane::::::::::::::::::::" + e);
		}

		return returnData;

	}

	public static Map<String, Object> yesBankMoneytransfer(String token, String input) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String res = "";
		try {
			//String url ="https://uat2yesmoney.easypay.co.in/epMoney/cp/dmt/moneytransfer/v1.0";
			String url = DMRURL+"/epMoney/cp/dmt/moneytransfer/v1.0";
			// System.out.println("token:::::::::::::"+token);
			res = sendRequestToyessBank(token, input, url);
		//	res="{'RESP_CODE':300,'RESPONSE':'SUCCESS','RESP_MSG':'Transaction successful.','DATA':{'TRANSACTION_DATE':'Thu November.14.2019 05:51:06 PM','CUSTOMER_REFERENCE_NO':'I79718882209','SENDER_AVAILBAL':24900.0,'TRANSACTION_DETAILS':[{'RESP_CODE':300,'RESPONSE':'SUCCESS','RESP_MSG':'Transaction successful.','VERSION':'2.0','UNIQUE_RESPONSENO':'39f2290206d911ea97cc0a0047330000','ATTEPMTNO':'1','TRANSFER_TYPE':'IMPS','LOW_BALANCE_ALERT':false,'STATUS_CODE':'COMPLETED','SUB_STATUS_CODE':'0','EP_REFERENCE_NO':'EP161409','BANK_REFERENCE_NO':'931817029763','REQUEST_REFERENCE_NO':'I15737340633241369250','RESPONSE_REFERENCE_NO':17413440,'TRANSFER_AMOUNT':100.0,'INIT_PENDING':false,'TRANSACTIONN_FEE':10.0,'PAID_AMOUNT':100.0,'TXN_BENENAME':'PARTHA GHOSH'}],'BENEFICIARY_DETAILS':{'BENE_NAME':'PARTHA GHOSH','BANK_ACCOUNTNO':'037101005373','BANKIFSC_CODE':'ICIC0000001','BENE_MMID':'','BENE_MOBILENO':'9051297971','BENE_BANKNAME':'ICICI BANK LIMITED'},'CUSTOMER_DETAILS':{'CUST_NAME':'Prateeti singha ','CUSTOMER_MOBILE':'9051297971','PANCARD_FLAG':false,'PANCARD_NO':''}}}";
			// res="{'TRANSACTIONN_FEE':12.0,'RESP_CODE':302,'RESPONSE':'PENDING','RESP_MSG':'Transaction
			// Pending.','DATA':{'TRANSACTION_DATE':'Wed January.09.2019
			// 11:37:55
			// AM','CUSTOMER_REFERENCE_NO':'40349','SENDER_AVAILBAL':130.0,'TRANSACTION_DETAILS':[{'RESP_CODE':302,'RESPONSE':'PENDING','RESP_MSG':'Transport
			// error: 401 Error:
			// Unauthorized','INIT_PENDING':true,'VERSION':'NA','UNIQUE_RESPONSENO':'NA','ATTEPMTNO':'NA','TRANSFER_TYPE':'NA','LOW_BALANCE_ALERT':'NA','STATUS_CODE':'NA','SUB_STATUS_CODE':'NA','BANK_REFERENCE_NO':'NA','EP_REFERENCE_NO':'EP803855','REQUEST_REFERENCE_NO':'I154701407348811230','RESPONSE_REFERENCE_NO':960,'TRANSFER_AMOUNT':5000,'PAID_AMOUNT':5000.0,'TXN_BENENAME':'NA'},{'RESP_CODE':302,'RESPONSE':'PENDING','RESP_MSG':'Transport
			// error: 401 Error:
			// Unauthorized','INIT_PENDING':true,'VERSION':'NA','UNIQUE_RESPONSENO':'NA','ATTEPMTNO':'NA','TRANSFER_TYPE':'NA','LOW_BALANCE_ALERT':'NA','STATUS_CODE':'NA','SUB_STATUS_CODE':'NA','BANK_REFERENCE_NO':'NA','EP_REFERENCE_NO':'EP451392','REQUEST_REFERENCE_NO':'I154701407348811231','RESPONSE_REFERENCE_NO':961,'TRANSFER_AMOUNT':800,'PAID_AMOUNT':800.0,'TXN_BENENAME':'NA'}],'BENEFICIARY_DETAILS':{'BENE_NAME':'PRATEETI
			// SINGHA','BANK_ACCOUNTNO':'520331002177522','BANKIFSC_CODE':'CORP0008954','BENE_MMID':'','BENE_MOBILENO':'8013724327','BENE_BANKNAME':'CORPORATION
			// BANK CREDIT CARD'},'CUSTOMER_DETAILS':{'CUST_NAME':'PAYEL SARKAR
			// ','CUSTOMER_MOBILE':'8013724327','PANCARD_FLAG':false,'PANCARD_NO':''}}}";
			logger_log.warn("yesBankMoneytransferres:::::::::::::::::::" + res);
			returnData = YesbankWebserviceParser.yesBankMoneytransfer(res);
			// System.out.println("yesBankMoneytransfer:::::::::::::::::::::"+returnData);
		} catch (Exception e) {
			logger_log.error("yesBankNewBeneficiary::::::::::::::::::::" + e);
		}

		return returnData;

	}

	public static Map<String, Object> Customerregister(String token, String input) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String res = "";
		try {
			/* demo */
		//	String url ="https://uat2yesmoney.easypay.co.in/epMoney/rest/aeps/addcustomerwithoutbeni";
			/* Live */
		   String url = "https://paisanikalapi.easypay.co.in/epMoney/rest/aeps/addcustomerwithoutbeni";
			res = sendRequestToyessBank(token, input, url);
			// res="{'RESP_CODE': 200,'RESPONSE':
			// 'EPMONEY_CUST_ADD_SUCCESS','RESP_MSG': 'Customer registration
			// success.','CUST_OTP_REQUIRED': 'TRUE'}";
			logger_log.warn("Customerregisterres:::::::::::::::::::" + res);
			returnData = YesbankWebserviceParser.customerRegisterparser(res);
		} catch (Exception e) {
			logger_log.error("searchCustomer::::::::::::::::::::" + e);
		}

		return returnData;

	}

	public static Map<String, Object> getDevicePID(String token, String input) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String res = "";
		try {
			/* demo */
			// String url ="https://uat2yesmoney.easypay.co.in/epMoney/rest/aeps/getrddatahash";
			/* Live */
		    String url = "https://paisanikalapi.easypay.co.in/epMoney/rest/aeps/getrddatahash";
			res = sendRequestToyessBank(token, input, url);
			// res="{'RESP_CODE': 200,'RESPONSE':
			// 'EPMONEY_CUST_ADD_SUCCESS','RESP_MSG': 'Customer registration
			// success.','CUST_OTP_REQUIRED': 'TRUE'}";
			logger_log.warn("getDevicePID:::::::::::::::::::" + res);
			returnData = YesbankWebserviceParser.getDevicePIDparser(res);
		} catch (Exception e) {
			logger_log.error("getDevicePID::::::::::::::::::::" + e);
		}

		return returnData;

	}

	public static Map<String, Object> checkImpsStatus(String token, String input) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String res = "";
		try {

			String method = "epMoney/transaction-status/v1.0";
			res = sendRequestToYesBankDMR(token, input, method);
			// res="{'RESP_CODE':'200','RESPONSE':'QUERY_SUCCESS','RESP_MSG':'Transaction
			// status fetch
			// successfully.','DATA':{'TRANSACTION_DATE':'2018-01-16
			// 11:51:56.0','TRANSFER_AMOUNT':5000,'BANK_REFERENCE_NO':'N016180009194788','REQUEST_REFERENCE_NO':'N15160837163610','CUSTOMER_MOBILE':'xxxxxx6123','TRANSACTION_STATUS':'SUCCESS','TRANSACTION_STATUSMESSAGE':'Funds
			// sent to beneficiary bank, final status will beknown after 2 hours
			// (NEFT/RTGS)'}}";
			// System.out.println("checkImpsStatus:::::::::::::::::::"+res);
			logger_log.warn("searchCustomer::::::::::::::::::::" + res);
			returnData = YesbankWebserviceParser.checkImpsStatusparser(res);
		} catch (Exception e) {
			logger_log.error("searchCustomer::::::::::::::::::::" + e);
		}

		return returnData;

	}

	public static String sendRequestToclientcredential() {
		String response = "";
		String token = "";
		try {

			String url = DMRURL+"/epMoney/oauth/token";
			System.out.println("url:: " + url);
			String authString = username + ":" + password;
			String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
			System.out.println("Basic " + authStringEnc);
			Client restClient = Client.create();
			WebResource webResource = restClient.resource(url).queryParam("grant_type", "client_credentials");
			ClientResponse resp = webResource.accept("application/json")
					.header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class);
			System.out.println(resp);
			if (resp.getStatus() != 200) {
				logger_log.warn("Unable to connect to the server");
			}

			response = resp.getEntity(String.class);
			logger_log.warn("sendRequestToclientcredential:::::::::::::" + response);
			token = YesbankWebserviceParser.getClientCredential(response);
		} catch (Exception e) {
			logger_log.error("sendRequestToclientcredential:::::::::::::::::" + e);
		}

		return token;

	}

	public static void main(String... args) {
		sendRequestToyessBank("", "", "");
	}

	
	
	public static String aepsstatuscheck(String token, AEPSLog aEPSLog, String cpcode, String agentcode) {
		String OP="";
		String ST="";
		//String url="https://paisanikalapi.easypay.co.in/epMoney/";
		String url = DMRURL+"/epMoney/transaction-status/aeps/v1.0/";
		if(aEPSLog.getProcessingCode().equals("010")){
			OP="AEPS";
		}else if(aEPSLog.getProcessingCode().equals("011")){
			OP="AEPS";
		}else if(aEPSLog.getProcessingCode().equals("002")){
			OP="FINOAEPS";
		}else if(aEPSLog.getProcessingCode().equals("001")){
			OP="FINOAEPS";
		}else if(aEPSLog.getProcessingCode().equals("003")){
			OP="MICROATM";
		}else if(aEPSLog.getProcessingCode().equals("004")){
			OP="MICROATM";
		}
		Client restClient = Client.create();
		String input = "{\"OP\":\""+OP+"\",\"ST\":\""+aEPSLog.getType()+"\",\"AID\":\""+agentcode+"\",\"CUSTOMER_MOBILE\":\""+aEPSLog.getCustomermob()+"\",\"REQUEST_REFERENCE_NO\":\""+aEPSLog.getReferenceno()+"\",\"CPCODE\":\""+cpcode+"\"}";
		logger_log.warn("aepsstatuscheck::input:"+input);
	/*	MultivaluedMap formData = new MultivaluedMapImpl();
		formData.add("jsonData", input);*/

		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type(MediaType.APPLICATION_JSON_TYPE).accept("application/json")
				.header("Authorization", "Bearer " + token).post(ClientResponse.class, input);
		/*ClientResponse resp = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).accept("application/json")
				.header("Authorization", "Bearer " + token).post(ClientResponse.class, input);*/
		System.out.println(resp);
		if (resp.getStatus() != 200) {
			logger_log.warn("Unable to connect to the server");
		}
		String output = resp.getEntity(String.class);
		logger_log.warn("aepsstatuscheck output::"+output);
		
		
		return output;
	}
	
	public static String sendRequestToclientcredentialNew() {
		String response = "";
		String token = "";
		try {

			String url = DMRURL+"/epMoney/oauth/token";
			String authString = username + ":" + password;
			String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
			System.out.println("Basic " + authStringEnc);
			Client restClient = Client.create();
			WebResource webResource = restClient.resource(url).queryParam("grant_type", "client_credentials");
			ClientResponse resp = webResource.accept("application/json")
					.header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class);
			System.out.println();
			if (resp.getStatus() != 200) {
				logger_log.warn("Unable to connect to the server");
			}

			response = resp.getEntity(String.class);
			logger_log.warn("sendRequestToclientcredential:::::::::::::" + response);
			token = YesbankWebserviceParser.getClientCredential(response);
		} catch (Exception e) {
			logger_log.error("sendRequestToclientcredential:::::::::::::::::" + e);
		}

		return token;

	}
}
