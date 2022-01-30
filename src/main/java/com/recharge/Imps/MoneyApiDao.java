package com.recharge.Imps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.recharge.model.User;
import com.skypoint.moneyapi.*;

public class MoneyApiDao {
	
	
	private static Logger log = Logger.getLogger(MoneyApiDao.class.getName());
	
	
	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	
	
	
	public static Map<String, Object> checkuserDoopme(String remitter,String member_id,String api_password,String api_pin){
		Map<String, Object> returnData = new HashMap<String, Object>();
		String restUrl="http://www.doopme.in/money_api/checkuser";
		try{
		 HttpClient httpclient = new DefaultHttpClient();
		  HttpPost httppost = new HttpPost(restUrl);
		  ArrayList<NameValuePair> postParameters= new ArrayList<NameValuePair>();
		   postParameters.add(new BasicNameValuePair("member_id", member_id));
		   postParameters.add(new BasicNameValuePair("api_password", api_password));
		   postParameters.add(new BasicNameValuePair("api_pin", api_pin));
		   postParameters.add(new BasicNameValuePair("mobile_no", remitter));
		   httppost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
		    ResponseHandler<String> responseHandler=new BasicResponseHandler();
	        String responseBody = httpclient.execute(httppost, responseHandler);
	        JSONObject response1=new JSONObject(responseBody);
	        log.warn("Check User RESPONSE:::::::::::::"+responseBody);
	       if(response1.get("STATUS").equals("FAILED")){
	    	   returnData.put("status", "0");
	    	   returnData.put("message", "This MObile NO is Not Registered with our banking partner system, register now");
	       }else if(response1.get("STATUS").equals("SUCCESS")){
	    	   returnData.put("MOBILENO", response1.get("MOBILENO"));
	    	   returnData.put("REMID", response1.get("REMID"));
	    	   returnData.put("status", "1");
	    	   returnData.put("message", "Customer mobile is registered with our banking partner system");
	       }
		}catch(Exception e){
			log.error("checkuserDoopme ::::::::::: "+e);
		}
		return returnData;
	}
	
	public static Map<String, Object> doopmeRemitterRegister(String customermobile,String member_id,String api_password,String api_pin,String customername,String Pincode){
		Map<String, Object> returnData = new HashMap<String, Object>();
		String restUrl="http://www.doopme.in/money_api/createuser";
		try{
		 HttpClient httpclient = new DefaultHttpClient();
		  HttpPost httppost = new HttpPost(restUrl);
		  ArrayList<NameValuePair> postParameters= new ArrayList<NameValuePair>();
		   postParameters.add(new BasicNameValuePair("member_id", member_id));
		   postParameters.add(new BasicNameValuePair("api_password", api_password));
		   postParameters.add(new BasicNameValuePair("api_pin", api_pin));
		   postParameters.add(new BasicNameValuePair("mobile_no", customermobile));
		   postParameters.add(new BasicNameValuePair("customer_name", customername));
		   postParameters.add(new BasicNameValuePair("Pincode", Pincode));
		   httppost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
		    ResponseHandler<String> responseHandler=new BasicResponseHandler();
	        String responseBody = httpclient.execute(httppost, responseHandler);
	        JSONObject response1=new JSONObject(responseBody);
	        log.warn("Register User RESPONSE:::::::::::::"+responseBody);
	       if(response1.get("STATUS").equals("FAILED")){
	    	   returnData.put("status", "0");
	    	   returnData.put("message", "Sorry Please try after some times");
	       }else if(response1.get("STATUS").equals("SUCCESS")){
	    	   returnData.put("MOBILENO", response1.get("MOBILENO"));
	    	   returnData.put("REMID", response1.get("REMID"));
	    	   returnData.put("status", "1");
	    	   returnData.put("message", "Customer successfully register");
	       }
		}catch(Exception e){
			log.error("doopmeRemitterRegister ::::::::::: "+e);
		}
		return returnData;
	}
	
	public static Map<String, Object> doopmeNewBeneficiary(String beneMobileNumber,String checkRemitterMobile,String member_id,String api_password,String api_pin,String bene_name,String bene_type,String accountNumber,String acc_type,String beneIFSCCode,String remitterid){
		Map<String, Object> returnData = new HashMap<String, Object>();
		String restUrl="http://www.doopme.in/money_api/addbeneficiary";
		try{
		 HttpClient httpclient = new DefaultHttpClient();
		  HttpPost httppost = new HttpPost(restUrl);
		  ArrayList<NameValuePair> postParameters= new ArrayList<NameValuePair>();
		   postParameters.add(new BasicNameValuePair("member_id", member_id));
		   postParameters.add(new BasicNameValuePair("api_password", api_password));
		   postParameters.add(new BasicNameValuePair("api_pin", api_pin));
		   postParameters.add(new BasicNameValuePair("mobile_no", checkRemitterMobile));
		   postParameters.add(new BasicNameValuePair("rem_id", remitterid));
		   postParameters.add(new BasicNameValuePair("benfname", bene_name));
		   postParameters.add(new BasicNameValuePair("benftype", bene_type));
		   postParameters.add(new BasicNameValuePair("account_no", accountNumber));
		   postParameters.add(new BasicNameValuePair("account_type", acc_type));
		   postParameters.add(new BasicNameValuePair("IFSC", beneIFSCCode));
		   postParameters.add(new BasicNameValuePair("benf_mobile_no", beneMobileNumber));
		  
		   httppost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
		    ResponseHandler<String> responseHandler=new BasicResponseHandler();
	        String responseBody = httpclient.execute(httppost, responseHandler);
	        JSONObject response1=new JSONObject(responseBody);
	        log.warn("Add Beneficiary RESPONSE:::::::::::::"+responseBody);
	       if(response1.get("STATUS").equals("FAILED")){
	    	   returnData.put("status", "0");
	    	   returnData.put("message",  response1.get("MESSAGE"));
	       }else if(response1.get("STATUS").equals("SUCCESS")){
	    	   Map<String, Object> param = new HashMap<String, Object>();
	    	   param.put("REQUESTNO", response1.get("REQUESTNO"));
	    	   param.put("MOBILENO", response1.get("MOBILENO"));
	    	   param.put("REMID", response1.get("REMID"));
	    	   returnData.put("response", param);
	    	   returnData.put("status", "1");
	    	   returnData.put("message", "OTP generate Successfully..");
	       }
		}catch(Exception e){
			log.error("doopmeNewBeneficiary ::::::::::: "+e);
		}
		return returnData;
	}
	
	public static Map<String, Object> doopmeVerifyBeneficiary(String MOBILENO,String checkRemitterMobile,String member_id,String api_password,String api_pin,String REQUESTNO,String OTP,String remitterid){
		Map<String, Object> returnData = new HashMap<String, Object>();
		String restUrl="http://www.doopme.in/money_api/verifyotp";
		try{
		 HttpClient httpclient = new DefaultHttpClient();
		  HttpPost httppost = new HttpPost(restUrl);
		  ArrayList<NameValuePair> postParameters= new ArrayList<NameValuePair>();
		   postParameters.add(new BasicNameValuePair("member_id", member_id));
		   postParameters.add(new BasicNameValuePair("api_password", api_password));
		   postParameters.add(new BasicNameValuePair("api_pin", api_pin));
		   postParameters.add(new BasicNameValuePair("mobile_no", MOBILENO));
		   postParameters.add(new BasicNameValuePair("otp", OTP));
		   postParameters.add(new BasicNameValuePair("rem_id", remitterid));
		   postParameters.add(new BasicNameValuePair("request_no", REQUESTNO));
		 
		  
		   httppost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
		    ResponseHandler<String> responseHandler=new BasicResponseHandler();
	        String responseBody = httpclient.execute(httppost, responseHandler);
	        JSONObject response1=new JSONObject(responseBody);
	        log.warn("Verify Beneficiary RESPONSE:::::::::::::"+responseBody);
	       if(response1.get("OTPVERIFY").equals("0")){
	    	   returnData.put("status", "0");
	    	   returnData.put("message", "Otp Verification Failed");
	       }else if(response1.get("OTPVERIFY").equals("1")){
	    	   returnData.put("status", "1");
	    	   returnData.put("message", "OTP Verification Successfull..");
	       }
		}catch(Exception e){
			log.error("doopmeVerifyBeneficiary ::::::::::: "+e);
		}
		return returnData;
	}
	
	public static Map<String, Object> viewdoopmebene(String checkRemitterMobile,String member_id,String api_password,String api_pin){
		Map<String, Object> returnData = new HashMap<String, Object>();
		String restUrl="http://www.doopme.in/money_api/getbeneficiary";
		try{
		 HttpClient httpclient = new DefaultHttpClient();
		  HttpPost httppost = new HttpPost(restUrl);
		  ArrayList<NameValuePair> postParameters= new ArrayList<NameValuePair>();
		   postParameters.add(new BasicNameValuePair("member_id", member_id));
		   postParameters.add(new BasicNameValuePair("api_password", api_password));
		   postParameters.add(new BasicNameValuePair("api_pin", api_pin));
		   postParameters.add(new BasicNameValuePair("mobile_no", checkRemitterMobile));
		  
		 
		  
		   httppost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
		    ResponseHandler<String> responseHandler=new BasicResponseHandler();
	        String responseBody = httpclient.execute(httppost, responseHandler);
	        JSONObject response1=new JSONObject(responseBody);
	        log.warn("View Beneficiary RESPONSE:::::::::::::"+responseBody);
	       if(response1.get("ACCOUNTEXISTS").equals("0")){
	    	   returnData.put("status", "0");
	    	   returnData.put("message", "Please Add Beneficiary");
	       }else if(response1.get("ACCOUNTEXISTS").equals("1")){
	    	   returnData.put("status", "1");
	    	   JSONArray beneficiaryresponse=(JSONArray) response1.get("BENEFICIARYLIST");
	    	   returnData.put("beneficiaryresponse", beneficiaryresponse);
	       }
		}catch(Exception e){
			log.error("viewdoopmebene ::::::::::: "+e);
		}
		return returnData;
	}
	
	public static Map<String, Object> doopmeMoneytransfer(String checkRemitterMobile,String member_id,String api_password,String api_pin,String beneMobileNumber,String bene_name,String transfertype,String accountNumber,String acc_type,String beneIFSCCode,String beneid,String amount,String referenceno){
		Map<String, Object> returnData = new HashMap<String, Object>();
		String restUrl="http://www.doopme.in/money_api/moneytransfer";
		try{
		 HttpClient httpclient = new DefaultHttpClient();
		  HttpPost httppost = new HttpPost(restUrl);
		  ArrayList<NameValuePair> postParameters= new ArrayList<NameValuePair>();
		   postParameters.add(new BasicNameValuePair("member_id", member_id));
		   postParameters.add(new BasicNameValuePair("api_password", api_password));
		   postParameters.add(new BasicNameValuePair("api_pin", api_pin));
		   postParameters.add(new BasicNameValuePair("mobile_no", checkRemitterMobile));
		   postParameters.add(new BasicNameValuePair("benfcode", beneid));
		   postParameters.add(new BasicNameValuePair("benfname", bene_name));
		   postParameters.add(new BasicNameValuePair("benfmobile", beneMobileNumber));
		   postParameters.add(new BasicNameValuePair("account_no", accountNumber));
		   postParameters.add(new BasicNameValuePair("account_type", acc_type));
		   postParameters.add(new BasicNameValuePair("ifsc", beneIFSCCode));
		   postParameters.add(new BasicNameValuePair("amount", amount));
		   postParameters.add(new BasicNameValuePair("transfer_type", transfertype));
		   postParameters.add(new BasicNameValuePair("member_request_id", referenceno));
		 
		  
		   httppost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
		    ResponseHandler<String> responseHandler=new BasicResponseHandler();
	        String responseBody = httpclient.execute(httppost, responseHandler);
	        JSONObject response1=new JSONObject(responseBody);
	        log.warn("Money Transfer RESPONSE:::::::::::::"+responseBody);
	       String FUNDTRANSNO="";
	        if(!response1.isNull("FUNDTRANSNO")){
	        	 Object checkfund=response1.get("FUNDTRANSNO");
	        	if(checkfund instanceof String){
		    		    FUNDTRANSNO=(String) response1.get("FUNDTRANSNO");
		    	   }else{
		    		   Long bankid=(Long) response1.get("FUNDTRANSNO");
		    		    FUNDTRANSNO=String.valueOf(bankid);
		    	   }
	        	returnData.put("FUNDTRANSNO", FUNDTRANSNO);
	        }else{
	        	returnData.put("FUNDTRANSNO", "NA");
	        }
	        
	        
	       if(response1.get("STATUS").equals("FAILED")){
	    	   returnData.put("status", "0");
	    	   returnData.put("message", "Money Transfer Failed");
	       }else if(response1.get("STATUS").equals("SUCCESS")){
	    	  
	    	   returnData.put("status", "1");
	    	   returnData.put("message", "Money Transfer Success");
	       }else{
	    	   returnData.put("status", "2");
	    	   returnData.put("message", response1.get("STATUS"));
	       }
		}catch(Exception e){
			log.error("doopmeMoneytransfer ::::::::::: "+e);
		}
		return returnData;
	}
	
	public static Map<String, Object> VerifyDeleteBane(String checkRemitterMobile,String member_id,String api_password,String api_pin,String BenfId,String otp){
		Map<String, Object> returnData = new HashMap<String, Object>();
		String restUrl="http://www.doopme.in/money_api/delVerifyOTP";
		try{
			HttpClient httpclient = new DefaultHttpClient();
			  HttpPost httppost = new HttpPost(restUrl);
			  ArrayList<NameValuePair> postParameters= new ArrayList<NameValuePair>();
			   postParameters.add(new BasicNameValuePair("member_id", member_id));
			   postParameters.add(new BasicNameValuePair("api_password", api_password));
			   postParameters.add(new BasicNameValuePair("api_pin", api_pin));
			   postParameters.add(new BasicNameValuePair("rem_id", checkRemitterMobile));
			   postParameters.add(new BasicNameValuePair("benf_id", BenfId));
			   postParameters.add(new BasicNameValuePair("otp", otp));
			   httppost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
			    ResponseHandler<String> responseHandler=new BasicResponseHandler();
		        String responseBody = httpclient.execute(httppost, responseHandler);
		        JSONObject response1=new JSONObject(responseBody);
		        log.warn("VerifyDeleteBane RESPONSE:::::::::::::"+responseBody);
		        if(response1.get("STATUS").equals("SUCCESS")){
		        	returnData.put("status", "1");
		        	returnData.put("message", response1.get("MESSAGE"));
		        }else{
		        	returnData.put("status", "0");
		        	returnData.put("message", response1.get("MESSAGE"));
		        }
			
		}catch(Exception e){
			log.error("VerifyDeleteBane ::::::::::: "+e);
		}
		return returnData;
		
	}
	
	public static Map<String, Object> deletedoopmebene(String checkRemitterMobile,String member_id,String api_password,String api_pin,String BenfId){
		Map<String, Object> returnData = new HashMap<String, Object>();
		String restUrl="http://www.doopme.in/money_api/deleteBeneficiary";
		try{
			HttpClient httpclient = new DefaultHttpClient();
			  HttpPost httppost = new HttpPost(restUrl);
			  ArrayList<NameValuePair> postParameters= new ArrayList<NameValuePair>();
			   postParameters.add(new BasicNameValuePair("member_id", member_id));
			   postParameters.add(new BasicNameValuePair("api_password", api_password));
			   postParameters.add(new BasicNameValuePair("api_pin", api_pin));
			   postParameters.add(new BasicNameValuePair("rem_id", checkRemitterMobile));
			   postParameters.add(new BasicNameValuePair("benf_id", BenfId));
			
			   httppost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
			    ResponseHandler<String> responseHandler=new BasicResponseHandler();
		        String responseBody = httpclient.execute(httppost, responseHandler);
		        JSONObject response1=new JSONObject(responseBody);
		        log.warn("deletedoopmebene RESPONSE:::::::::::::"+responseBody);
		        if(response1.get("STATUS").equals("SUCCESS")){
		        	returnData.put("status", "1");
		        	returnData.put("message", response1.get("MESSAGE"));
		        }else{
		        	returnData.put("status", "0");
		        	returnData.put("message", response1.get("MESSAGE"));
		        }
			
		}catch(Exception e){
			log.error("deletedoopmebene ::::::::::: "+e);
		}
		return returnData;
		
	}
	
	public static Map<String, Object> doopmeValidateBeneficiary(String checkRemitterMobile,String member_id,String api_password,String api_pin,String accountNumber,String beneIFSCCode,String referenceno){
		Map<String, Object> returnData = new HashMap<String, Object>();
		String restUrl="http://www.doopme.in/money_api/validate_beneficiary";
		try{
		 HttpClient httpclient = new DefaultHttpClient();
		  HttpPost httppost = new HttpPost(restUrl);
		  ArrayList<NameValuePair> postParameters= new ArrayList<NameValuePair>();
		   postParameters.add(new BasicNameValuePair("member_id", member_id));
		   postParameters.add(new BasicNameValuePair("api_password", api_password));
		   postParameters.add(new BasicNameValuePair("api_pin", api_pin));
		   postParameters.add(new BasicNameValuePair("mobile_no", checkRemitterMobile));
		   postParameters.add(new BasicNameValuePair("account_no", accountNumber));
		   postParameters.add(new BasicNameValuePair("ifsc", beneIFSCCode));
		   postParameters.add(new BasicNameValuePair("member_request_id", referenceno));
		  
		   httppost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
		    ResponseHandler<String> responseHandler=new BasicResponseHandler();
	        String responseBody = httpclient.execute(httppost, responseHandler);
	        JSONObject response1=new JSONObject(responseBody);
	        log.warn("doopmeValidateBeneficiary RESPONSE:::::::::::::"+responseBody);
	       if(response1.get("STATUS").equals("FAILED")){
	    	   returnData.put("status", "0");
	    	   returnData.put("message",  response1.get("MESSAGE"));
	       }else if(response1.get("STATUS").equals("SUCCESS")){
	    	   Map<String, Object> param = new HashMap<String, Object>();
	    	   returnData.put("status", "1");
	    	   returnData.put("message",  response1.get("MESSAGE"));
	       }
		}catch(Exception e){
			log.error("doopmeValidateBeneficiary ::::::::::: "+e);
		}
		return returnData;
	}
	
	
	public static void main(String[] args) {
		//System.out.println(GetConsumerDetailsByConsumerID(36821257).getDateOfBirth());
		//System.out.println(GetBeneficiaryList(36821257).get(0).getBeneficiaryAccountAlias());
		//System.out.println(GetBeneficiaryDetails(36821257,"ABCCCCC-SBIN-9999").getAccountNumber());
		//System.out.println(GetIFSCCodeDetails("SBIN0000180").getBank());
		
		//System.out.println(submitPaymentDetails("36821257", "6701260", "10.0", "TESTING").isPostingStatus());
		//System.out.println(getAllIFSCCodeList().size());
		
	//	System.out.println(checkTransactionStatus("TRAN2748").toString());
	}

}
