package com.recharge.businessimpl;

import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.recharge.businessdao.ApiParserService;
import com.recharge.customModel.BringwayModel;
import com.recharge.customModel.DoopMeModel;
import com.recharge.customModel.InstantPayModel;
import com.recharge.model.PaymonkResponse;
import com.recharge.utill.GenerateRandomNumber;


@Service("apiParserService")
public class ApiParserServiceImpl implements ApiParserService {
	
	private static final Logger log = Logger.getLogger(ApiParserService.class);
	public static void main(String args[]){}
	@Override
	public BringwayModel BringwayParsing(String xmlRecords){
		BringwayModel bm=new BringwayModel();
		try{
			
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xmlRecords));
		Document doc = db.parse(is);
		NodeList  STATUS= doc.getElementsByTagName("STATUS");
		Element element1 = (Element) STATUS.item(0);
		bm.setMESSAGE(getCharacterDataFromElement(element1));
		NodeList  MESSAGE= doc.getElementsByTagName("MESSAGE");
		Element element2 = (Element) MESSAGE.item(0);
		bm.setMESSAGE(getCharacterDataFromElement(element2));
		NodeList  TID= doc.getElementsByTagName("TID");
		Element element3 = (Element) TID.item(0);
		bm.setTID(getCharacterDataFromElement(element3));
		NodeList  MOBILE= doc.getElementsByTagName("MOBILE");
		Element element4 = (Element) MOBILE.item(0);
		bm.setMOBILE(getCharacterDataFromElement(element4));
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return bm;
		
	}
	
	
	@Override
	public InstantPayModel instantpayParserFirst(String xml) {
		InstantPayModel insp = new InstantPayModel();
		try {
			
			Document doc = getDocument(xml);
			doc.getDocumentElement().normalize();
			
			NodeList node = doc.getElementsByTagName("ipay_errorcode");
			Element ipay_errorcode = (Element)node.item(0);
			insp.setIpay_errorcode(getCharacterDataFromElement(ipay_errorcode).trim());
			System.out.println("ipay_errorcode::::::::::::::::::"+getCharacterDataFromElement(ipay_errorcode).trim());
			
			NodeList node2 = doc.getElementsByTagName("ipay_errordesc");
			Element ipay_errordesc = (Element)node2.item(0);
			insp.setIpay_errordesc(getCharacterDataFromElement(ipay_errordesc).trim());
			
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return insp;
	}
	
	@Override
	public InstantPayModel instantpayParserSecond(String xml) {
		InstantPayModel insp = new InstantPayModel();
		try {
			
			Document doc = getDocument(xml);
			doc.getDocumentElement().normalize();
			
			NodeList node = doc.getElementsByTagName("ipay_id");
			Element ipay_id = (Element)node.item(0);
			insp.setIpay_id(getCharacterDataFromElement(ipay_id).trim());
			System.out.println("ipay_errorcode::::::::::::::::::"+getCharacterDataFromElement(ipay_id).trim());
			
			NodeList node2 = doc.getElementsByTagName("agent_id");
			Element agent_id = (Element)node2.item(0);
			insp.setAgent_id(getCharacterDataFromElement(agent_id).trim());
			
			
			NodeList node4 = doc.getElementsByTagName("res_code");
			Element res_code = (Element)node4.item(0);
			insp.setIpay_errordesc(getCharacterDataFromElement(res_code).trim());
			
			NodeList node3 = doc.getElementsByTagName("status");
			Element status = (Element)node3.item(0);
			insp.setStatus(getCharacterDataFromElement(status).trim());
			
			
			
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return insp;
	}

	
	private static Document getDocument(String xml) throws ParserConfigurationException, SAXException, IOException{
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		doc = db.parse(is);
		return doc;
	}
	
	@Override
	public PaymonkResponse paymonkParser(Map<String, Object> request) {
		
		log.warn("paymonkParser:::::::::::::::::::::::"+request);
		String payerId ="";
        String payertype="";
		String payeeId="";
		String payeetype="";
		String txnType="";
	    String orderId="";
		double amount=0.0;
		String txnId="";
		String balance="";
		String orderStatus="";
		String paymentStatus="";
		String requestId ="";
		String stan ="";
		String rrn ="";
		String bankAuth ="";
		String processingCode ="";
		String accountBalance ="";
		String bankResponseCode ="";
		String bankResponseMsg="";
		String terminalId="";
		String agentId ="";
		String aadharNumber ="";
		String dateTime ="";
		String statusCode ="";
		String statusMessage ="";
		double commissionAmt = 0.0;
		double gstAmt = 0.0;
		double tdsAmt = 0.0;
		String date = GenerateRandomNumber.getCurrentDate();
		String time = GenerateRandomNumber.getCurrentTime();
		
		
		
		
		
		
		try {
			
			String data =(String) request.get("data");
		   System.out.println(data);
			
			//String	res = data.substring(1, data.length()-1);  
			/*System.out.println("res::::::::::::::::"+res);*/
			JSONObject jsonObj = new JSONObject(data);
			//System.out.println(jsonObj);
			/*String	resmetadata = res.substring(res.indexOf("metadata=")+9, res.indexOf("aeps=")-2); 
			Map<String,Object> map2 = new HashMap<>();    
			System.out.println("res2::::::::::::::::::::::::::"+res.substring(res.indexOf("metadata")+9, res.indexOf("aeps")-2));
			*/
			System.out.println(jsonObj.getString("metadata"));
			System.out.println(jsonObj.getJSONObject("aeps"));
			JSONObject aepsObj =(JSONObject) jsonObj.getJSONObject("aeps");
			System.out.println("aepsObj::::::::::::::::::::::::"+aepsObj);
			JSONObject metaObj = new JSONObject(jsonObj.getString("metadata"));
			String referenceNo = metaObj.getString("refno");
			String agentcode = metaObj.getString("agentcode");
			System.out.println(metaObj.getString("refno"));
			System.out.println(metaObj.getString("agentcode"));
			
			//System.out.println("aepsObj::::::::::::::::::::::::"+aepsObj);
		/*	String resmetadata2 = resmetadata.substring(1, resmetadata.length()-1);    
			System.out.println("resmetadata2:::::::::::::::::::::"+resmetadata2);
			System.out.println("resmetadata2:::::::::::::::::::::::::"+resmetadata2); 
			String[] keyValuePairs2 = resmetadata2.split(","); 
			System.out.println("keyValuePairs:::::::::::::::::::::::::"+keyValuePairs2.length);
			
			System.out.println(map2);
			System.out.println();           
			Map<String,Object> map = new HashMap<>();        
			System.out.println("res:::::::::::::::::::"+res);
			System.out.println("res:::::::::::::::::::"+res.substring(res.indexOf("aeps=")+5, res.length()-1));
			
		    String	res3 = res.substring(res.indexOf("aeps=")+5,res.length()-1);  
		    System.out.println("res3:::::::::::::::::::::::::"+resmetadata2); 
			String res4 = res3.substring(1, res3.length()-1);          
			System.out.println("res4:::::::::::::::::::::::::"+res4); 
			String[] keyValuePairs = res4.split(","); 
			System.out.println("keyValuePairs:::::::::::::::::::::::::"+keyValuePairs.length);
			for(int i=0;i<keyValuePairs.length-2;i++)                       
			{   System.out.println(keyValuePairs[i]);
			    String[] entry = keyValuePairs[i].split("="); 
			    
			    //	System.out.println(entry[1].trim());
			    map.put(entry[0].trim(), entry[1].trim());   
			    }else{
			    map.put(entry[0].trim(),"");  	
			    }
			    
			}*/
			
			
				if (!aepsObj.isNull("payerId")) {
					System.out.println("HTT::::::::::::::::::::::::::::::::::");
					payerId = aepsObj.getString("payerId");
				}
				if (!aepsObj.isNull("payertype")) {
					payertype = aepsObj.getString("payertype");
				}
				if (!aepsObj.isNull("payeeId")) {
					payeeId = aepsObj.getString("payeeId");
				}
				if (!aepsObj.isNull("payeetype")) {
					payeetype = aepsObj.getString("payeetype");
				}
				if (!aepsObj.isNull("txnType")) {
					txnType = aepsObj.getString("txnType");
				}
				if (aepsObj.isNull("orderId")) {
					orderId = aepsObj.getString("orderId");
				}
				if (!aepsObj.isNull("amount")) {
					amount = aepsObj.getDouble("amount");
				}
				if (!aepsObj.isNull("txnId")) {
					txnId = aepsObj.getString("txnId");
				}
				if (!aepsObj.isNull("balance")) {
					balance = aepsObj.getString("balance");
				}
				if (!aepsObj.isNull("orderStatus")) {
					orderStatus = aepsObj.getString("orderStatus");
				}
				if (!aepsObj.isNull("paymentStatus")) {
					paymentStatus = aepsObj.getString("paymentStatus");
				}
				if (!aepsObj.isNull("requestId")) {
					requestId = aepsObj.getString("requestId");
				}
				if (!aepsObj.isNull("stan")) {
					stan = aepsObj.getString("stan");
				}
				if (!aepsObj.isNull("rrn")) {
					rrn =aepsObj.getString("rrn");
				}
				if (!aepsObj.isNull("bankAuth")) {
					bankAuth = aepsObj.getString("bankAuth");
				}
				if (!aepsObj.isNull("processingCode")) {
					processingCode = aepsObj.getString("processingCode");
				}
				if (!aepsObj.isNull("accountBalance")) {
					accountBalance = aepsObj.getString("accountBalance");
				}
				
				
				if (!aepsObj.isNull("bankResponseCode")) {
					bankResponseCode = aepsObj.getString("bankResponseCode");
				}
				
				if (!aepsObj.isNull("bankResponseMsg")) {
					bankResponseMsg =aepsObj.getString("bankResponseMsg");
				}
				
				if (!aepsObj.isNull("terminalId")) {
					terminalId = aepsObj.getString("terminalId");
				}
				
				if (!aepsObj.isNull("agentId")) {
					agentId = aepsObj.getString("agentId");
				}
				if (!aepsObj.isNull("aadharNumber")) {
					aadharNumber = aepsObj.getString("aadharNumber");
				}
				if (!aepsObj.isNull("dateTime")) {
					dateTime = String.valueOf(aepsObj.getInt("dateTime"));
				}
				if (!aepsObj.isNull("statusCode")) {
					statusCode = aepsObj.getString("statusCode");
				}
				if (!aepsObj.isNull("statusMessage")) {
					statusMessage = aepsObj.getString("statusMessage");
				}
				
				if (!aepsObj.isNull("commissionAmt")) {
					commissionAmt = aepsObj.getDouble("commissionAmt");
				}
				
				if (!aepsObj.isNull("gstAmt")) {
					gstAmt =aepsObj.getDouble("gstAmt");
				}
				
				if (aepsObj.get("tdsAmt")!=null) {
					tdsAmt = aepsObj.getDouble("tdsAmt");
				}
				PaymonkResponse obj = new PaymonkResponse(payerId, payertype, payeeId, payeetype, txnType, orderId, amount, txnId, balance, orderStatus, paymentStatus, requestId, stan, rrn, bankAuth, processingCode, accountBalance, bankResponseCode, bankResponseMsg, terminalId, agentId, aadharNumber, dateTime, statusCode, statusMessage, commissionAmt, gstAmt, tdsAmt, date, time,referenceNo,agentcode);
				System.out.println(obj);
				return obj;
			
		} catch (Exception e) {
			PaymonkResponse obj = new PaymonkResponse();
			log.error("error::::::::::::::::::::"+e);
			return obj;
			
		}
	}
	
	@Override
	public DoopMeModel doopMeParser(String response) {
		String eRROR = "";
		String sTATUS = "";
		String oPTXNID = "";
		String mEMBERREQID = "";
		String aMOUNT = "";
		String nUMBER = "";
		String mESSAGE = "";
		String fIELD1 = "";
		String fIELD2 = "";
		String ip = "";
		try {
			if(response == null || response.equals("")) {
				return null;
			} else {
				JSONObject jsonObj = new JSONObject(response);
				if (!jsonObj.isNull("ERROR")) {
					eRROR = jsonObj.getString("ERROR");
				}
				if (!jsonObj.isNull("STATUS")) {
					sTATUS = jsonObj.getString("STATUS");
				}
				if (!jsonObj.isNull("MESSAGE")) {
					mESSAGE = jsonObj.getString("MESSAGE");
				}
				if (!jsonObj.isNull("OPTXNID")) {
					oPTXNID = jsonObj.getString("OPTXNID");
				}
				if (!jsonObj.isNull("MEMBERREQID")) {
					mEMBERREQID = jsonObj.getString("MEMBERREQID");
				}
				if (!jsonObj.isNull("AMOUNT")) {
					aMOUNT = jsonObj.getString("AMOUNT");
				}
				if (!jsonObj.isNull("NUMBER")) {
					nUMBER = jsonObj.getString("NUMBER");
				}
				if (!jsonObj.isNull("FIELD1")) {
					fIELD1 = jsonObj.getString("FIELD1");
				}
				if (!jsonObj.isNull("FIELD2")) {
					fIELD2 = jsonObj.getString("FIELD2");
				}
				if (!jsonObj.isNull("ip")) {
					ip = jsonObj.getString("ip");
				}
				DoopMeModel obj = new DoopMeModel(eRROR, sTATUS, oPTXNID, mEMBERREQID, aMOUNT, nUMBER, mESSAGE, fIELD1,
						fIELD2, ip);
				return obj;
			}
		} catch (Exception e) {
			DoopMeModel obj = new DoopMeModel();
			return obj;
		}
	}
		private static String getCharacterDataFromElement(Element e) {
			// TODO Auto-generated method stub
			 Node child = e.getFirstChild();
			    if (child instanceof CharacterData) {
			      CharacterData cd = (CharacterData) child;
			      return cd.getData();
			    }
			
			return "";
		}
		@Override
		public PaymonkResponse paymonkParserupdated(Map<String, Object> res) {
			String payerId ="";
	        String payertype="";
			String payeeId="";
			String payeetype="";
			String txnType="";
		    String orderId="";
			double amount=0.0;
			String txnId="";
			String balance="";
			String orderStatus="";
			String paymentStatus="";
			String requestId ="";
			String stan ="";
			String rrn ="";
			String bankAuth ="";
			String processingCode ="";
			String accountBalance ="";
			String bankResponseCode ="";
			String bankResponseMsg="";
			String terminalId="";
			String agentId ="";
			String aadharNumber ="";
			String dateTime ="";
			String statusCode ="";
			String statusMessage ="";
			double commissionAmt = 0.0;
			double gstAmt = 0.0;
			double tdsAmt = 0.0;
			String date2 = GenerateRandomNumber.getCurrentDate();
			Date date = Calendar.getInstance().getTime();
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String today = formatter.format(date);
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
			String currentTime = sdf.format(cal.getTime());
			Map<String,Object> request = (Map<String,Object>)res.get("result");
			Map<String,Object> payload = (Map<String,Object>)request.get("payload");
			String metadata = payload.get("metadata").toString();
			//Map<String,Object> metadata2 = (Map<String,Object>)metadata.get("metadata");
			System.out.println("metadata::::::::::::::::::::"+metadata);
			//System.out.println("metadata::::::::::::::::::::"+metadata2);
			System.out.println("payload::::::::::::::::::::"+payload);
			Map<String,Object> response = (Map<String,Object>)payload.get("aeps");
			System.out.println("aeps::::::::::::::::::::"+response);
			JSONObject metaObj = new JSONObject(metadata);
			try {
				if(response == null || response.equals("")) {
					return null;
				} else {
					
					if (response.get("payerId")!=null) {
						payerId = response.get("payerId").toString();
					}
					if (response.get("payertype")!=null) {
						payertype = response.get("payertype").toString();
					}
					if (response.get("payeeId")!=null) {
						payeeId = response.get("payeeId").toString();
					}
					if (response.get("payeetype")!=null) {
						payeetype = response.get("payeetype").toString();
					}
					if (response.get("txnType")!=null) {
						txnType = response.get("txnType").toString();
					}
					if (response.get("orderId")!=null) {
						orderId = response.get("orderId").toString();
					}
					if (response.get("amount")!=null) {
						amount = Double.parseDouble(response.get("amount").toString());
					}
					if (response.get("txnId")!=null) {
						txnId = response.get("txnId").toString();
					}
					if (response.get("balance")!=null) {
						balance = response.get("balance").toString();
					}
					if (response.get("orderStatus")!=null) {
						orderStatus = response.get("orderStatus").toString();
					}
					if (response.get("paymentStatus")!=null) {
						paymentStatus = response.get("paymentStatus").toString();
					}
					if (response.get("requestId")!=null) {
						requestId = response.get("requestId").toString();
					}
					if (response.get("stan")!=null) {
						stan = response.get("stan").toString();
					}
					if (response.get("rrn")!=null) {
						rrn = response.get("rrn").toString();
					}
					if (response.get("bankAuth")!=null) {
						bankAuth = response.get("bankAuth").toString();
					}
					if (response.get("processingCode")!=null) {
						processingCode = response.get("processingCode").toString();
					}
					if (response.get("accountBalance")!=null) {
						accountBalance = response.get("accountBalance").toString();
					}
					
					
					if (response.get("bankResponseCode")!=null) {
						bankResponseCode = response.get("bankResponseCode").toString();
					}
					
					if (response.get("bankResponseMsg")!=null) {
						bankResponseMsg =response.get("bankResponseMsg").toString();
					}
					
					if (response.get("terminalId")!=null) {
						terminalId = response.get("terminalId").toString();
					}
					
					if (response.get("agentId")!=null) {
						agentId = response.get("agentId").toString();
					}
					if (response.get("aadharNumber")!=null) {
						aadharNumber = response.get("aadharNumber").toString();
					}
					if (response.get("dateTime")!=null) {
						dateTime = response.get("dateTime").toString();
					}
					if (response.get("statusCode")!=null) {
						statusCode = response.get("statusCode").toString();
					}
					if (response.get("statusMessage")!=null) {
						statusMessage = response.get("statusMessage").toString();
					}
					
					if (response.get("commissionAmt")!=null) {
						commissionAmt = Double.parseDouble(response.get("commissionAmt").toString());
					}
					
					if (response.get("gstAmt")!=null) {
						gstAmt = Double.parseDouble(response.get("gstAmt").toString());
					}
					
					if (response.get("tdsAmt")!=null) {
						tdsAmt = Double.parseDouble(response.get("tdsAmt").toString());
					}
					PaymonkResponse obj = new PaymonkResponse(payerId, payertype, payeeId, payeetype, txnType, orderId, amount, txnId, balance, orderStatus, paymentStatus, requestId, stan, rrn, bankAuth, processingCode, accountBalance, bankResponseCode, bankResponseMsg, terminalId, agentId, aadharNumber, dateTime, statusCode, statusMessage, commissionAmt, gstAmt, tdsAmt, date2,currentTime,metaObj.getString("refno").toString(),response.get("agentcode").toString());
					
					System.out.println(obj);
					return obj;
				}
			} catch (Exception e) {
				PaymonkResponse obj = new PaymonkResponse();
				return obj;
			}
		}
}
