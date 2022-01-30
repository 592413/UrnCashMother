package com.recharge.instantpay;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import com.recharge.InstantPayModel.BeneficiaryInstantPay;
import com.recharge.model.User;

public class InstantWebserviceParser {
	
	final static Logger log = Logger.getLogger(InstantWebserviceParser.class.getName());
	
	/*private static Document getDocument(String xml) throws ParserConfigurationException, SAXException, IOException{
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		doc = db.parse(is);
		return doc;
	}
	
	private String getCharacterDataFromElement(Element element){
		String data = "";
		Node node = element.getFirstChild();
		if(node instanceof CharacterData){
			CharacterData cd = (CharacterData) node;
			return cd.getData();
			
		}
		
		return data;
	}
	*/
	public static Map<String,Object> checkuserInstantPayParser(String response){
		Map<String,Object> returnData = new HashMap<String, Object>();
		// response = "{'statuscode':'TXN','status':'Transaction Successful','data':{'remitter':{'id':'127874','name':'skypoint','mobile':'8013724327','address':'AMRITSAR II AMRITSAR PUNJAB','pincode':'143105','city':'AMRITSAR II','state':'PUNJAB','kycstatus':'0','consumedlimit':0,'remaininglimit':25000,'kycdocs':'REQUIRED','perm_txn_limit':5000},'beneficiary':[{'id':'194152','name':'Mr  DEBASISH  GHOSH','mobile':'9051297971','account':'20246266451','bank':'STATE BANK OF INDIA','status':'0','last_success_date':'14-03-18 18:35','last_success_name':'Mr  DEBASISH  GHOSH','last_success_imps':'1','ifsc':'SBIN0000001','imps':'1'},{'id':'207013','name':'PAYEL  SARKAR','mobile':'8902416510','account':'0918010199032','bank':null,'status':'1','last_success_date':'13-03-18 11:36','last_success_name':'PAYEL  SARKAR','last_success_imps':'1','ifsc':'UTBI0000001','imps':'1'}],'remitter_limit':[{'code':'40','status':'1','mode':{'imps':'1','neft':'1'},'limit':{'total':'25000.00','consumed':'0.00','remaining':'25000.00'}}]}}";
		 JSONObject response1=new JSONObject(response);
		 String statuscode = response1.getString("statuscode");
		 if(statuscode.equalsIgnoreCase("TXN")){
			 if(response1.getString("status").equals("OTP sent successfully")){
				 returnData.put("message",response1.getString("status"));
				 returnData.put("status","2");
				 JSONObject dataObj = response1.getJSONObject("data");
				 JSONObject remitter = dataObj.getJSONObject("remitter");
				 String remitterid = remitter.getString("id");
				 returnData.put("REMID",remitterid);
				
			 }else{
			 JSONObject dataObj = response1.getJSONObject("data");
			 JSONObject remitter = dataObj.getJSONObject("remitter");
			 String remitterid = remitter.getString("id");
			 int limit = remitter.getInt("remaininglimit");
			 returnData.put("REMID",remitterid);
			 returnData.put("limit",limit);
			 returnData.put("status","1");
			 returnData.put("MOBILENO",remitter.getString("mobile"));
			 returnData.put("customername",remitter.getString("name"));
			 
			 JSONArray beneficiarylist = dataObj.getJSONArray("beneficiary");
			 List<BeneficiaryInstantPay> list = new ArrayList<>();
			 for(int i=0;i<beneficiarylist.length();i++){
				 JSONObject beneficiaryobj = beneficiarylist.getJSONObject(i);
				 BeneficiaryInstantPay benefi  = new BeneficiaryInstantPay();
				 if(!beneficiaryobj.isNull("id")){
					 benefi.setId(beneficiaryobj.getString("id"));	 
				 }
                if(!beneficiaryobj.isNull("account")){
                	benefi.setAccount(beneficiaryobj.getString("account")); 
				 }
                if(!beneficiaryobj.isNull("ifsc")){
                	benefi.setIfsc(beneficiaryobj.getString("ifsc")); 
				 }
                if(!beneficiaryobj.isNull("imps")){
                	benefi.setImps(beneficiaryobj.getString("imps")); 
				 }
                if(!beneficiaryobj.isNull("last_success_date")){
                	benefi.setLast_success_date(beneficiaryobj.getString("last_success_date"));
				 }
                if(!beneficiaryobj.isNull("last_success_name")){
                	benefi.setLast_success_name(beneficiaryobj.getString("last_success_name")); 
				 }
                if(!beneficiaryobj.isNull("mobile")){
                	benefi.setMobile(beneficiaryobj.getString("mobile")); 
				 }
                if(!beneficiaryobj.isNull("name")){
                	benefi.setName(beneficiaryobj.getString("name")); 
				 }
                if(!beneficiaryobj.isNull("status")){
                	benefi.setStatus(beneficiaryobj.getString("status"));
				 }
                if(!beneficiaryobj.isNull("bank")){
                	benefi.setBank(beneficiaryobj.getString("bank")); 
				 }
                if(!beneficiaryobj.isNull("last_success_imps")){
                	benefi.setLast_success_imps(beneficiaryobj.getString("last_success_imps"));
				 }
                list.add(benefi);
                
			 }
			 returnData.put("beneficiarylist",list);
			 }
		 }else{
			 returnData.put("message",response1.getString("status"));
			 returnData.put("status","0");
		 }
		 return returnData;
		 
	}
	
	public static Map<String,Object> instantNewBeneficiaryParser(String response){

		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			//response="{'statuscode':'TXN','status':'Transaction Successful','data':{'remitter':{'id':'127874'},'beneficiary':{'id':'253871'}}}";
			JSONObject response1=new JSONObject(response);
			 String statuscode = response1.getString("statuscode");
			 if(statuscode.equalsIgnoreCase("TXN")){
					JSONObject dataObj = response1.getJSONObject("data");
					if(!dataObj.isNull("remitter")){
						JSONObject remitterobj = dataObj.getJSONObject("remitter");
						if(!remitterobj.isNull("id")){
							returnData.put("remitterid",remitterobj.getString("id"));
						}
						
					}
					
					if(!dataObj.isNull("beneficiary")){
						JSONObject beneficiaryobj = dataObj.getJSONObject("beneficiary");
						if(!beneficiaryobj.isNull("id")){
							returnData.put("id",beneficiaryobj.getInt("id"));
							returnData.put("status",beneficiaryobj.getInt("status"));
						}
						
					}
					returnData.put("message","success");
					//returnData.put("status","1");
			 }

		} catch (Exception e) {
			log.error("instantPayCreateBeneFinalparser::::::::::::::::::" + e);
		}

		return returnData;

	}
	
	public static Map<String,Object> instantPaymentTransferparser(String response){
		Map<String, Object> returnData = new HashMap<String, Object>();
		
		try {
		//response="{'statuscode':'TXN','status':'Transaction Successful','data':{'remitter':{'id':1315870}}}";
			JSONObject response1=new JSONObject(response);
			 String statuscode = response1.getString("statuscode");
			 if(statuscode.equalsIgnoreCase("TXN")){
					JSONObject dataObj = response1.getJSONObject("data");
					if(!dataObj.isNull("remitter")){
						JSONObject remitterobj = dataObj.getJSONObject("remitter");
						if(!remitterobj.isNull("id")){
							returnData.put("remitterid",remitterobj.getInt("id"));
						}
						
					}
					
					
						
						
						if(!dataObj.isNull("ipay_id")){
							returnData.put("ipay_id",dataObj.getString("ipay_id"));
						 }
							
						
						if(!dataObj.isNull("name")){
							returnData.put("name",dataObj.getString("name"));
						 }
							
						
						if(!dataObj.isNull("opening_bal")){
							returnData.put("opening_bal",dataObj.getString("opening_bal"));
						 }
							
						
						if(!dataObj.isNull("amount")){
							returnData.put("amount",dataObj.getString("amount"));
						 }
						
						if(!dataObj.isNull("ref_no")){
							returnData.put("ref_no",dataObj.getInt("ref_no"));
						 }	
								
					//returnData.put("message","success");
					returnData.put("status","1");
			 }

		} catch (Exception e) {
			log.error("instantPayCreateBeneFinalparser::::::::::::::::::" + e);
		}

		
		return returnData;

	}
	
	
	public static Map<String,Object> instantRemitterRegisterParser(String response){
		Map<String, Object> returnData = new HashMap<String, Object>();
		
		try {
		//response="{'statuscode':'TXN','status':'Transaction Successful','data':{'remitter':{'id':1315870}}}";
			JSONObject response1=new JSONObject(response);
			 String statuscode = response1.getString("statuscode");
			 if(statuscode.equalsIgnoreCase("TXN")){
					JSONObject dataObj = response1.getJSONObject("data");
					if(!dataObj.isNull("remitter")){
						JSONObject remitterobj = dataObj.getJSONObject("remitter");
						if(!remitterobj.isNull("id")){
							returnData.put("REMID",remitterobj.getInt("id"));
						}
						
					}
					returnData.put("message","success");
					returnData.put("status","1");
			 }

		} catch (Exception e) {
			log.error("instantPayCreateBeneFinalparser::::::::::::::::::" + e);
		}

		
		return returnData;

	}
	
	
	public static Map<String,Object> instantRemitterValidateParser(String response){
		Map<String, Object> returnData = new HashMap<String, Object>();
		
		try {
		//response="{'statuscode':'TXN','status':'Transaction Successful','data':{'remitter':{'id':1315870}}}";
			JSONObject response1=new JSONObject(response);
			 String statuscode = response1.getString("statuscode");
			 if(statuscode.equalsIgnoreCase("TXN")){
					JSONObject dataObj = response1.getJSONObject("data");
					if(!dataObj.isNull("remitter")){
						JSONObject remitterobj = dataObj.getJSONObject("remitter");
						if(!remitterobj.isNull("id")){
							returnData.put("remitterid",remitterobj.getInt("id"));
						}
						
					}
					returnData.put("message","success");
					returnData.put("status","1");
			 }else{
				 returnData.put("message","Failed");
					returnData.put("status","0");
			 }

		} catch (Exception e) {
			log.error("instantPayCreateBeneFinalparser::::::::::::::::::" + e);
		}

		
		return returnData;

	}
	
	
	public static Map<String,Object> instantpayValidateBeneficiary(String response){
		Map<String,Object> returnData = new HashMap<String, Object>();
		String verification_status = "";
		//response="{'statuscode':'TXN','status':'Transaction Successful','data':{'remarks':'Transaction Successful','bankrefno':831418036692,'ipay_id':'1181110183702HSPST','benename':'BIBHUKALYAN','locked_amt':0,'charged_amt':2.18,'verification_status':'VERIFIED'}}";
		try{
			JSONObject response1=new JSONObject(response);
			 String statuscode = response1.getString("statuscode");
			 if(statuscode.equalsIgnoreCase("TXN")){
				 JSONObject dataObj = response1.getJSONObject("data");	 
				 if(!dataObj.isNull("remarks")){
					 returnData.put("remarks", dataObj.getString("remarks"));
				 }
				// System.out.println(dataObj.getString("bankrefno"));
				 if(!dataObj.isNull("bankrefno")){
					 returnData.put("bankrefno", String.valueOf(dataObj.getInt("bankrefno")));
				 }
				 if(!dataObj.isNull("ipay_id")){
					 returnData.put("ipay_id", dataObj.getString("ipay_id"));
				 }
				 if(!dataObj.isNull("benename")){
					 returnData.put("benename", dataObj.getString("benename"));
				 }
				 if(!dataObj.isNull("locked_amt")){
					 returnData.put("locked_amt", dataObj.getDouble("locked_amt"));
				 }
				 if(!dataObj.isNull("charged_amt")){
					 returnData.put("charged_amt", dataObj.getDouble("charged_amt"));
				 }
				 if(!dataObj.isNull("verification_status")){
					 returnData.put("message", dataObj.getString("verification_status"));
					 verification_status= dataObj.getString("verification_status");
					 if(verification_status.equalsIgnoreCase("VERIFIED")){
						 returnData.put("status","1"); 
					 }
				 }
				
			 }else{
				 returnData.put("status",response1.getString("status")); 
				 returnData.put("status","0"); 
			 }
			
		}catch (Exception e) {
			log.error("instantpayValidateBeneficiary:::::::::::::::"+e);
		}
		
		System.out.println("returnData:::::::::::::::::::::"+returnData);
		 return returnData;
	}
	
	public static Map<String,Object> instantpayFundTransfer(String response){
		Map<String,Object> returnData = new HashMap<String, Object>();
	//response="{'statuscode':'TXN','status':'Transaction Successful','data':{'ipay_id':'1181004115145YZCNK','ref_no':'827711868287','opr_id':'827711868287','name':'PARTHA GHOSH','opening_bal':'746.18','amount':'10.00','charged_amt':14.5,'locked_amt':0,'bank_alias':'PPBL'}}";
		try{
		JSONObject response1=new JSONObject(response);
		 String statuscode = response1.getString("statuscode");
		 if(statuscode.equalsIgnoreCase("TXN")){
			JSONObject dataObj = response1.getJSONObject("data");
			/*if(!dataObj.isNull("ipay_id")){
				 returnData.put("ipay_id", dataObj.getString("ipay_id"));
			 }
			if(!dataObj.isNull("opening_bal")){
				 returnData.put("opening_bal", dataObj.getString("opening_bal"));
			 }
			if(!dataObj.isNull("amount")){
				 returnData.put("amount", dataObj.getString("amount"));
			 }
			if(!dataObj.isNull("charged_amt")){
				 returnData.put("charged_amt", dataObj.getDouble("charged_amt"));
			 }*/
			
			if(!dataObj.isNull("ref_no")){
				 returnData.put("ref_no", dataObj.getString("ref_no"));
			 }
			if(!dataObj.isNull("opr_id")){
				 returnData.put("opr_id", dataObj.getString("opr_id"));
			 }	
			returnData.put("statuscode","TXN");
			returnData.put("status","1" );
		 }else if(statuscode.equalsIgnoreCase("ERR")){
			 //JSONObject dataObj = response1.getJSONObject("data");
			 returnData.put("message",response1.getString("status") ); 
			 returnData.put("statuscode","ERR" );
			 returnData.put("status","0" ); 
		 }else{
			 returnData.put("statuscode","ERR" );
			 returnData.put("status","0" ); 
		 }
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
			 
		 return returnData;
		}
	
	public static Map<String,Object> instantpayRemoveBeneficiaryparser(String response){
		Map<String,Object> 	returnData = new HashMap<String, Object>();
		String message = "";
		try{
			JSONObject response1=new JSONObject(response);
			 String statuscode = response1.getString("statuscode");
			 if(statuscode.equalsIgnoreCase("TXN")){
				
				if(!response1.isNull("data")){
					
					returnData.put("message", "Success");
					message = "Success";
					if(message.equalsIgnoreCase("Success")){
						returnData.put("status","1");	
					}else{
						returnData.put("status","0");	
					}
				}else{
					returnData.put("status","0");	
				}
			 }
			
		}catch (Exception e) {
			log.error("instantpayRemoveBeneficiaryparser::::::::::::::::::"+e);
		}
		
		return returnData;
	}
	
	
	
	public static Map<String,Object> instantPayDeleteBeneFinalparser(String response){
		Map<String,Object> 	returnData = new HashMap<String, Object>();
		String message = "";
		try{
			JSONObject response1=new JSONObject(response);
			 String statuscode = response1.getString("statuscode");
			 if(statuscode.equalsIgnoreCase("TXN")){
				
				if(!response1.isNull("data")){
					returnData.put("message", response1.getString("data"));
					message = response1.getString("data");
					if(message.equalsIgnoreCase("Success")){
						returnData.put("status","1");	
					}else{
						returnData.put("status","0");	
					}
				}else{
					returnData.put("status","0");	
				}
			 }
			
		}catch (Exception e) {
			log.error("instantPayDeleteBeneFinalparser::::::::::::::::::"+e);
		}
		
		return returnData;
	}
	
	
	public static Map<String,Object> instantPayNewBeneFinalparser(String response){
		Map<String,Object> 	returnData = new HashMap<String, Object>();
		String message = "";
		//response="{'statuscode':'TXN','status':'Transaction Successful','data':'Success'}";
		try{
			JSONObject response1=new JSONObject(response);
			 String statuscode = response1.getString("statuscode");
			 if(statuscode.equalsIgnoreCase("TXN")){
				if(!response1.isNull("data")){
					returnData.put("message", response1.getString("data"));
					message = response1.getString("data");
					if(message.equalsIgnoreCase("Success")){
						returnData.put("status","1");	
					}else{
						returnData.put("status","0");	
					}
				}else{
					returnData.put("status","0");	
				}
			 }
			
		}catch (Exception e) {
			log.error("instantPayDeleteBeneFinalparser::::::::::::::::::"+e);
		}
		
		return returnData;
	}
	
	
	public static Map<String, Object> instantPayCreateBeneFinalparser(String response) {
		Map<String,Object> 	returnData = new HashMap<String, Object>();
		String message = "";
		try{
			JSONObject response1=new JSONObject(response);
			 String statuscode = response1.getString("statuscode");
			 if(statuscode.equalsIgnoreCase("TXN")){
				
				if(!response1.isNull("data")){
					returnData.put("message", response1.getString("data"));
					message = response1.getString("data");
					if(message.equalsIgnoreCase("Success")){
						returnData.put("status","1");	
					}else{
						returnData.put("status","0");	
					}
				}else{
					returnData.put("status","0");	
				}
			 }
			
		}catch (Exception e) {
			log.error("instantPayDeleteBeneFinalparser::::::::::::::::::"+e);
		}
		
		return returnData;
	}
	
	
	public static Map<String, Object> bbpsBillPaymentFirstParser(String response) {
		Map<String,Object> 	returnData = new HashMap<String, Object>();
		String message = "";
		//response="{'ipay_errorcode':'TXN','ipay_errordesc':'Successful','particulars':{'dueamount':10,'duedate':'2018-09-26','customername':'AVIJIT HALDER','billnumber':'151359707','billdate':'2018-09-26','billperiod':''}}";
		//{"dueamount":"190.76","duedate":"07-01-2019","customername":"MANGALA KHANRA","billnumber":"430006871075","billdate":"28-10-2018","billperiod":"NOV2018DEC2018JAN2019","billdetails":[],"customerparamsdetails":[{"Name":"Consumer ID","Value":"133225986"}],"additionaldetails":[],"reference_id":6050}
		try{
			JSONObject response1=new JSONObject(response);
			
					if(!response1.isNull("dueamount")){
						 returnData.put("BILLAMT", response1.getDouble("dueamount"));
					 }
					if(!response1.isNull("duedate")){
						 returnData.put("BILLDUEDATE", response1.getString("duedate"));
					 }
					if(!response1.isNull("customername")){
						 returnData.put("CUSTNAME", response1.getString("customername"));
					 }
					if(!response1.isNull("billnumber")){
						 returnData.put("BILLNO", response1.getString("billnumber"));
					 }
					if(!response1.isNull("billdate")){
						 returnData.put("BILLDATE", response1.getString("billdate"));
					 }
					if(!response1.isNull("billperiod")){
						 returnData.put("billperiod", response1.getString("billperiod"));
					 }
					if(!response1.isNull("reference_id")){
						 returnData.put("reference_id", response1.getInt("reference_id"));
					 }
					returnData.put("status","1");	
				
			 
			
		}catch (Exception e) {
			log.error("instantPayDeleteBeneFinalparser::::::::::::::::::"+e);
		}
		
		return returnData;
	}
	
	public static Map<String, Object> bbpsBillPaymentFinalParser(String response) {
		Map<String,Object> 	returnData = new HashMap<String, Object>();
		String message = "";
	//response="{'ipay_id':'1181004194047GFGUY','agent_id':'2','opr_id':'648039990','account_no':'8013734327','sp_key':'ATP','trans_amt':10,'charged_amt':9.91,'opening_bal':'731.68','datetime':'2018-10-04 19:40:50','status':'SUCCESS','res_code':'TXN','res_msg':'Transaction Successful'}	";
		try{
			JSONObject response1=new JSONObject(response);
			 String statuscode = response1.getString("res_code");
			// String ipay_errordesc = response1.getString("ipay_errordesc");
			 returnData.put("res_msg",response1.getString("res_msg"));
			 if(statuscode.equalsIgnoreCase("TXN")){
					if(!response1.isNull("ipay_id")){
						 returnData.put("ipay_id", response1.getString("ipay_id"));
					 }
					if(!response1.isNull("agent_id")){
						 returnData.put("agent_id", response1.getString("agent_id"));
					 }
					if(!response1.isNull("opr_id")){
						 returnData.put("opr_id", response1.getString("opr_id"));
					 }
					/*if(!response1.isNull("agent_id")){
						 returnData.put("agent_id", response1.getString("agent_id"));
					 }*/
					if(!response1.isNull("account_no")){
						 returnData.put("account_no", response1.getString("account_no"));
					 }
					
					
					
					
					returnData.put("status","1");	
					
				
			 }else{
					returnData.put("status","0");	
				}
			
		}catch (Exception e) {
			log.error("bbpsBillPaymentFinalParser::::::::::::::::::"+e);
		}
		
		return returnData;
	}
	public static void main(String[] args){
		String response = "";	
		InstantWebserviceParser inv = new InstantWebserviceParser();
		System.out.println(instantpayValidateBeneficiary(response));
		//inv.checkuserInstantPayParser(response);
	}

}
