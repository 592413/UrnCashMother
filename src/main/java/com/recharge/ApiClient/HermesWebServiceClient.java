//package com.recharge.ApiClient;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.StringReader;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.xml.namespace.QName;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.soap.MessageFactory;
//import javax.xml.soap.MimeHeaders;
//import javax.xml.soap.SOAPBody;
//import javax.xml.soap.SOAPConnection;
//import javax.xml.soap.SOAPConnectionFactory;
//import javax.xml.soap.SOAPElement;
//import javax.xml.soap.SOAPEnvelope;
//import javax.xml.soap.SOAPHeader;
//import javax.xml.soap.SOAPHeaderElement;
//import javax.xml.soap.SOAPMessage;
//import javax.xml.soap.SOAPPart;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.w3c.dom.CharacterData;
//import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;
//
//import com.recharge.customModel.HermsOperator;
//import org.apache.log4j.Logger;
//
//public class HermesWebServiceClient {
//	static Logger log = Logger.getLogger(HermesWebServiceClient.class.getName());
//	
//	//**************Live Id*************//
//	private static final String url = "http://api.hermes-it.in/mobile/hermesmobile.svc/SoapService";
//	private static final String loginid = "Getonline";
//	private static final String password = "apiGetonline";
//	
//	
//	////***************Demo id******************//
//
//	/*private static final String url = "http://115.248.39.80/HermesMobAPI/hermesmobile.svc/SOAPService";
//	private static final String loginid = "skypoint";
//	private static final String password = "skypoint";*/
//	
//	
//	private static Document getDocument(String xml) throws ParserConfigurationException, SAXException, IOException
//	{
//		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//		InputSource is = new InputSource(new StringReader(xml));
//		Document doc = dBuilder.parse(is);
//		return doc;
//	}
//	
//	public static String extendUsertrackid(String id) {
//	    //System.out.println("SKYPOINT000000000000000"+id);
//	    return "SURAKSHA00000000000000000000"+id;
//	  }
//	
//    public static String reduceUsertrackid(String id) {
//	    
//	    return id.substring(23);
//	  }
//	
//	public static String getCharacterDataFromElement(Element e) {
//	    Node child = e.getFirstChild();
//	    if (child instanceof CharacterData) {
//	      CharacterData cd = (CharacterData) child;
//	      return cd.getData();
//	    }
//	    return "";
//	  }
//	
//	//------------------------------------------Getting Available operators for recharge--------------------------------------------//
//		
//	public static List<HermsOperator> getAvailableoperators(String type)  {
//		 log.warn("recharge type============================================================================= "+type);
//		List<HermsOperator> operatorlist=new ArrayList<HermsOperator>();
//		try{
//       SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//       SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//       SOAPMessage soapResponse = null;
//       if(type.equalsIgnoreCase("Prepaid")){
//       	soapResponse = soapConnection.call(getAvailableoperatorsRes(), url);
//       }
//       else{
//       	soapResponse = soapConnection.call(getAvailableoperatorsResBillPay(), url);
//       }
//       
//       ByteArrayOutputStream baout = new ByteArrayOutputStream();
//       soapResponse.writeTo(baout);
//       String strMsg = new String(baout.toByteArray());
//             
//       strMsg = strMsg.replaceAll("&lt;", "<");
//       strMsg = strMsg.replaceAll("&gt;", ">");        
//       
//       soapConnection.close();
//       
//    // soapResponse.writeTo(System.out);
//       
//       
//       strMsg = strMsg.replaceAll("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?>","");
//       strMsg = strMsg.replaceAll("<?xml version=\"1.0\" encoding=\"utf-8\"?>","");
//       strMsg = strMsg.replaceAll("\\<\\?xml(.+?)\\?\\>", "");
//       //System.out.println("Response SOAP Message:"+strMsg);
//       log.debug("Response SOAP Message:"+strMsg);
//     //*****persing recharge detail***********//
//       Document doc = getDocument(strMsg);		
//		doc.getDocumentElement().normalize();
//             
//       NodeList nodes = doc.getElementsByTagName("Operators");
//       for (int i = 0; i < nodes.getLength(); i++) {
//       	
//       HermsOperator hmop = new HermsOperator();	
//       Element element = (Element) nodes.item(i);
//       
//       NodeList name = element.getElementsByTagName("OperatorCode");
//       Element line = (Element) name.item(0);
//       hmop.setCode(getCharacterDataFromElement(line).trim());
//       
//       name = element.getElementsByTagName("OperatorDescritpion");
//       line = (Element) name.item(0);
//       hmop.setDesc(getCharacterDataFromElement(line).trim());
//
//       name = element.getElementsByTagName("RechargeType");
//       line = (Element) name.item(0);
//       hmop.setItemType(getCharacterDataFromElement(line).trim());
//       
//       name = element.getElementsByTagName("Commission");
//       line = (Element) name.item(0);
//       hmop.setCommission(getCharacterDataFromElement(line).trim());
//       
//       
//       operatorlist.add(hmop);
//       
//       }
//       
//       
//       
//       
//		}catch(Exception e){
//			log.debug("Response SOAP Message:");
//			log.error(e);
//			//e.printStackTrace();
//		}
//       //*************************************//
//       return operatorlist;
//       
//   }
//
//    private static SOAPMessage getAvailableoperatorsRes() {
//		SOAPMessage soapMessage = null;
//		try{
//        MessageFactory messageFactory = MessageFactory.newInstance();
//        soapMessage = messageFactory.createMessage();
//        SOAPPart soapPart = soapMessage.getSOAPPart();
//        
//        SOAPEnvelope envelope = soapPart.getEnvelope();
//        envelope.setPrefix("s");
//        envelope.removeNamespaceDeclaration("SOAP-ENV");
//        
//        
//        //Remove Header 
//        
//        SOAPHeader header = envelope.getHeader();
//        header.detachNode();
//        
//        // SOAP Body
//        SOAPBody soapBody = envelope.getBody();
//        soapBody.setPrefix("s");
//        SOAPElement soapBodyActnElem = soapBody.addChildElement("GetRechargeOperators","","http://hermes-it.in/API"); 
//       
//        SOAPElement soapBodypobjSecurityElem = soapBodyActnElem.addChildElement("GetRechargeOperatorsRequest");
//        soapBodypobjSecurityElem.addNamespaceDeclaration("i", "http://www.w3.org/2001/XMLSchemainstance");
//        SOAPElement soapBodyWebProviderIdElem = soapBodypobjSecurityElem.addChildElement("Authentication");
//        
//        SOAPElement soapBodyLoginIdElem = soapBodyWebProviderIdElem.addChildElement("LoginId");
//        soapBodyLoginIdElem.addTextNode(loginid);
//        SOAPElement soapBodyPasswordElem = soapBodyWebProviderIdElem.addChildElement("Password");
//        soapBodyPasswordElem.addTextNode(password);
//        
//       
//        
//        MimeHeaders headers = soapMessage.getMimeHeaders();
//        
//        headers.addHeader("SOAPAction","http://hermes-it.in/API/HermesMobile/GetRechargeOperators");
//        
//        soapMessage.saveChanges();
//
//        //System.out.print("Request SOAP Message:");
//        //soapMessage.writeTo(System.out);
//        ByteArrayOutputStream baout = new ByteArrayOutputStream();
//        soapMessage.writeTo(baout);
//        String strMsg = new String(baout.toByteArray());
//        log.debug("Request SOAP Message:"+strMsg);
//        System.out.println();
//        
//        
//
//		}catch(Exception e)
//		{
//			System.out.println(e);
//		}
//	
//		return soapMessage;
//	}
//    
//    private static SOAPMessage getAvailableoperatorsResBillPay() throws Exception {
//		SOAPMessage soapMessage = null;
//		try{
//       MessageFactory messageFactory = MessageFactory.newInstance();
//       soapMessage = messageFactory.createMessage();
//       SOAPPart soapPart = soapMessage.getSOAPPart();
//       
//       SOAPEnvelope envelope = soapPart.getEnvelope();
//       envelope.setPrefix("s");
//       envelope.removeNamespaceDeclaration("SOAP-ENV");
//       
//       
//       //Remove Header 
//       
//       SOAPHeader header = envelope.getHeader();
//       header.detachNode();
//       
//       // SOAP Body
//       SOAPBody soapBody = envelope.getBody();
//       soapBody.setPrefix("s");
//       SOAPElement soapBodyActnElem = soapBody.addChildElement("GetBillPaymentOperators","","http://hermes-it.in/API"); 
//      
//       SOAPElement soapBodypobjSecurityElem = soapBodyActnElem.addChildElement("GetBillPaymentOperatorsRequest");
//       soapBodypobjSecurityElem.addNamespaceDeclaration("i", "http://www.w3.org/2001/XMLSchema-instance");
//       SOAPElement soapBodyWebProviderIdElem = soapBodypobjSecurityElem.addChildElement("Authentication");
//       
//       SOAPElement soapBodyLoginIdElem = soapBodyWebProviderIdElem.addChildElement("LoginId");
//       soapBodyLoginIdElem.addTextNode(loginid);
//       SOAPElement soapBodyPasswordElem = soapBodyWebProviderIdElem.addChildElement("Password");
//       soapBodyPasswordElem.addTextNode(password);
//       
//      
//       
//       MimeHeaders headers = soapMessage.getMimeHeaders();
//       
//       headers.addHeader("SOAPAction","http://hermes-it.in/API/HermesMobile/GetBillPaymentOperators");
//       
//       soapMessage.saveChanges();
//
//       //System.out.print("Request SOAP Message:");
//       //soapMessage.writeTo(System.out);
//       ByteArrayOutputStream baout = new ByteArrayOutputStream();
//       soapMessage.writeTo(baout);
//       String strMsg = new String(baout.toByteArray());
//       log.debug("Request SOAP Message:"+strMsg);
//     
//      
//       
//       
//
//		}catch(Exception e)
//		{
//			System.out.println(e);
//		}
//	
//		return soapMessage;
//	}
//  //------------------------------------------Book the selected recharge option--------------------------------------------//
//    
//    public static String rechargeRequest(String type,String usertrackid, String opcode, String opname, String monile,String amount,String postpaidcirlcode,String accno,String postpaidstdcode) {//changes
//    	
//    	String returnMsg ="";
//    	
//    	try{
//        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//        
//        SOAPMessage soapResponse = null;  
//        String usertrackid2="";
//		if(type.equalsIgnoreCase("Prepaid")) {			
//			usertrackid2=HermesWebServiceClient.extendUsertrackid(usertrackid);
//			soapResponse = soapConnection.call(rechargeResponse(usertrackid2,opcode,monile,amount), url);
//		}else if(type.equalsIgnoreCase("postpaid")){
//			usertrackid2=HermesWebServiceClient.extendUsertrackid(usertrackid);
//			
//			soapResponse = soapConnection.call(rechargeResponseBillPay(usertrackid2,opcode,monile,amount,postpaidcirlcode,postpaidstdcode,accno), url);//changes
//		}
//        ByteArrayOutputStream baout = new ByteArrayOutputStream();
//        soapResponse.writeTo(baout);
//        String strMsg = new String(baout.toByteArray());
//              
//        strMsg = strMsg.replaceAll("&lt;", "<");
//        strMsg = strMsg.replaceAll("&gt;", ">");  
//        strMsg = strMsg.replaceAll("\\<\\?xml(.+?)\\?\\>", "");
//        
//        soapConnection.close();
//        
//     // soapResponse.writeTo(System.out);
//      
//        log.debug("Response SOAP Message:"+strMsg);
//        
//        //*****persing recharge detail***********// 
//        Document doc = getDocument(strMsg);		
//		doc.getDocumentElement().normalize();
//		if(type.equalsIgnoreCase("Prepaid")) {     
//    
//        NodeList name = doc.getElementsByTagName("ResponseStatus");
//        Element line = (Element) name.item(0);
//        String status = (getCharacterDataFromElement(line).trim().equals("1")?"SUCCESS":"PENDING");
//        if(status.equals("SUCCESS")){
//        	name = doc.getElementsByTagName("ReferenceNumber");
//            line = (Element) name.item(0);
//            String RefNo = getCharacterDataFromElement(line).trim();
//            
//           
//            
//            
//            returnMsg = ""+status+"|"+RefNo+"|"+RefNo;
//            
//        }
//        else{
//        	returnMsg = checkStatus(usertrackid);
//        }
//		}else{			
//			
//		 NodeList name = doc.getElementsByTagName("ResponseStatus");
//		
//	        Element line = (Element) name.item(0);
//	        String status = (getCharacterDataFromElement(line).trim().equals("1")?"SUCCESS":"PENDING");
//	      
//	        if(status.equals("SUCCESS")){
//	        	name = doc.getElementsByTagName("ReferenceNumber");
//	            line = (Element) name.item(0);
//	            String RefNo = getCharacterDataFromElement(line).trim();
//	            
//	          
//	            
//	            returnMsg = ""+status+"|"+RefNo+"|"+RefNo;
//	            
//	        }
//	        else{
//	        	returnMsg = checkStatus(usertrackid);
//	        }
//		}
//       
//        
//        //System.out.println("returnMsg: " + returnMsg);
//       
//        
//        //*************************************//
//    	}catch(Exception e){
//    		log.debug("Response SOAP Message:");    		
//			log.error(e);
//			returnMsg = "PENDING|NIL|NIL";
//    	}
//        
//        return returnMsg;
//        
//    }
//    
//    
//    private static SOAPMessage rechargeResponse(String usertrackid,String opcode,String mobno,String amount) {
//		SOAPMessage soapMessage = null;
//		try{
//        MessageFactory messageFactory = MessageFactory.newInstance();
//        soapMessage = messageFactory.createMessage();
//        SOAPPart soapPart = soapMessage.getSOAPPart();
//        
//        SOAPEnvelope envelope = soapPart.getEnvelope();
//        envelope.setPrefix("s");
//        envelope.removeNamespaceDeclaration("SOAP-ENV");
//        
//        
//        //Remove Header 
//        
//        SOAPHeader header = envelope.getHeader();
//        header.detachNode();
//        
//        // SOAP Body
//        SOAPBody soapBody = envelope.getBody();
//        soapBody.setPrefix("s");
//        SOAPElement soapBodyActnElem = soapBody.addChildElement("GetRechargeDone","","http://hermes-it.in/API"); 
//       
//        SOAPElement soapBodypobjSecurityElem = soapBodyActnElem.addChildElement("GetRechargeDoneRequest");
//        soapBodypobjSecurityElem.addNamespaceDeclaration("i", "http://www.w3.org/2001/XMLSchemainstance");
//        SOAPElement soapBodyWebProviderIdElem = soapBodypobjSecurityElem.addChildElement("Authentication");
//        
//        SOAPElement soapBodyLoginIdElem = soapBodyWebProviderIdElem.addChildElement("LoginId");
//        soapBodyLoginIdElem.addTextNode(loginid);
//        SOAPElement soapBodyPasswordElem = soapBodyWebProviderIdElem.addChildElement("Password");
//        soapBodyPasswordElem.addTextNode(password);
//        
//        SOAPElement userTrackElem=soapBodypobjSecurityElem.addChildElement("UserTrackId");
//        userTrackElem.addTextNode(usertrackid);
//        
//        SOAPElement RechInputElem=soapBodypobjSecurityElem.addChildElement("RechargeInput");
//        
//        SOAPElement OPElem=RechInputElem.addChildElement("OperatorCode");
//        OPElem.addTextNode(opcode);
//        
//        SOAPElement MOBElem=RechInputElem.addChildElement("MobileNumber");
//        MOBElem.addTextNode(mobno);
//        
//        SOAPElement AMNTElem=RechInputElem.addChildElement("Amount");
//        AMNTElem.addTextNode(amount);
//        
//        /*RechInputElem.addTextNode("<OperatorCode>HACL</OperatorCode>");
//        RechInputElem.addTextNode("<MobileNumber>8013372123</MobileNumber>");
//        RechInputElem.addTextNode("<Amount>10</Amount>");*/
//        
//        
//        MimeHeaders headers = soapMessage.getMimeHeaders();
//        
//        headers.addHeader("SOAPAction","http://hermes-it.in/API/HermesMobile/GetRechargeDone");
//        
//        soapMessage.saveChanges();
//
//        //System.out.print("Request SOAP Message:");
//        /*soapMessage.writeTo(System.out);*/
//        ByteArrayOutputStream baout = new ByteArrayOutputStream();
//        soapMessage.writeTo(baout);
//        String strMsg = new String(baout.toByteArray());
//        log.debug("Request SOAP Message:"+strMsg);
//       /*System.out.println("Request SOAP Message:"+strMsg);*/
//        
//        
//
//		}catch(Exception e)
//		{
//			 log.debug("Request SOAP Message:"+e);
//		}
//	
//		return soapMessage;
//	}
//    
//private static SOAPMessage rechargeResponseBillPay(String usertrackid2,String opcode,String mobno,String amount,String postpaidcirlcode,String accno,String postpaidstdcode) throws Exception { //changes alll
//		
//    	System.out.println("Enteredd*************************************************************************");
//    	SOAPMessage soapMessage = null;
//		try{
//        MessageFactory messageFactory = MessageFactory.newInstance();
//        soapMessage = messageFactory.createMessage();
//        SOAPPart soapPart = soapMessage.getSOAPPart();
//        
//        SOAPEnvelope envelope = soapPart.getEnvelope();
//        envelope.setPrefix("s");
//        envelope.removeNamespaceDeclaration("SOAP-ENV");
//        
//        
//        //Remove Header 
//        
//        SOAPHeader header = envelope.getHeader();
//        header.detachNode();
//        
//        // SOAP Body
//        SOAPBody soapBody = envelope.getBody();
//        soapBody.setPrefix("s");
//        SOAPElement soapBodyActnElem = soapBody.addChildElement("GetBillPaymentDone","","http://hermes-it.in/API"); 
//       
//        SOAPElement soapBodypobjSecurityElem = soapBodyActnElem.addChildElement("GetBillPaymentDoneRequest");
//        soapBodypobjSecurityElem.addNamespaceDeclaration("i", "http://schemas.xmlsoap.org/soap/envelope/");
//        SOAPElement soapBodyWebProviderIdElem = soapBodypobjSecurityElem.addChildElement("Authentication");
//        
//        SOAPElement soapBodyLoginIdElem = soapBodyWebProviderIdElem.addChildElement("LoginId");
//        soapBodyLoginIdElem.addTextNode(loginid);
//        SOAPElement soapBodyPasswordElem = soapBodyWebProviderIdElem.addChildElement("Password");
//        soapBodyPasswordElem.addTextNode(password);
//        
//        SOAPElement userTrackElem=soapBodypobjSecurityElem.addChildElement("UserTrackId");
//        userTrackElem.addTextNode(usertrackid2);
//        
//        SOAPElement RechInputElem=soapBodypobjSecurityElem.addChildElement("BillPaymentInput");
//        
//        SOAPElement OPElem=RechInputElem.addChildElement("OperatorCode");
//        OPElem.addTextNode(opcode);
//        
//        SOAPElement MOBElem=RechInputElem.addChildElement("MobileNumber");
//        MOBElem.addTextNode(mobno);
//        
//        SOAPElement AMNTElem=RechInputElem.addChildElement("Amount");
//        AMNTElem.addTextNode(amount);
//        
//        SOAPElement Otherdetail=RechInputElem.addChildElement("OtherDetails");
//        if(postpaidcirlcode.equalsIgnoreCase("") || postpaidcirlcode==null){
//        	Otherdetail.addTextNode("");
//        }else{
//        	 Otherdetail.addTextNode(postpaidcirlcode+"$"+accno+"$"+postpaidstdcode+"$");
//        }
//       
//        
//        /*RechInputElem.addTextNode("<OperatorCode>HACL</OperatorCode>");
//        RechInputElem.addTextNode("<MobileNumber>8013372123</MobileNumber>");
//        RechInputElem.addTextNode("<Amount>10</Amount>");*/
//        
//        
//        MimeHeaders headers = soapMessage.getMimeHeaders();
//        
//        headers.addHeader("SOAPAction","http://hermes-it.in/API/HermesMobile/GetBillPaymentDone");
//        
//        soapMessage.saveChanges();
//
//        //System.out.print("Request SOAP Message:");
//      /*  soapMessage.writeTo(System.out);*/
//        ByteArrayOutputStream baout = new ByteArrayOutputStream();
//        soapMessage.writeTo(baout);
//        String strMsg = new String(baout.toByteArray());
//       log.debug("Request SOAP Message:"+strMsg);
//    /*  System.out.println("Request SOAP Message:"+strMsg);*/
//        
//		}catch(Exception e)
//		{
//			System.out.println(e);
//		}
//	
//		return soapMessage;
//	}
//  //------------------------------------------Book previous recharge status--------------------------------------------//
//    public static String checkStatus(String usertrackid) {
//    	String returnMsg="";
//    	try{
//        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//        
//        String usertrackid2=HermesWebServiceClient.extendUsertrackid(usertrackid);
//        
//        
//
//        SOAPMessage soapResponse = soapConnection.call(checkStatusRes(usertrackid2), url);
//        
//        ByteArrayOutputStream baout = new ByteArrayOutputStream();
//        soapResponse.writeTo(baout);
//        String strMsg = new String(baout.toByteArray());
//              
//        strMsg = strMsg.replaceAll("&lt;", "<");
//        strMsg = strMsg.replaceAll("&gt;", ">");        
//        strMsg = strMsg.replaceAll("\\<\\?xml(.+?)\\?\\>", "");
//        soapConnection.close();
//        
//     // soapResponse.writeTo(System.out);
//        //System.out.print("Response SOAP Message for status check:"+strMsg); 
//        
//        log.debug("Response SOAP Message:"+strMsg);
//        
//        //*****persing status code detail***********//
//        Document doc = getDocument(strMsg);		
//		doc.getDocumentElement().normalize();
//              
//     /*   NodeList nodes = doc.getElementsByTagName("CheckTransRes");
//        Element element = (Element) nodes.item(0);*/
//        
//        NodeList name = doc.getElementsByTagName("TransactionStatus");
//        Element line = (Element) name.item(0);
//        String statuscode = getCharacterDataFromElement(line);
//        String status = (statuscode.trim().equals("1")?"SUCCESS":((statuscode.trim().equals("0")||statuscode.trim().equals("2"))?"PENDING":"FAILED"));
//        if(status.equals("SUCCESS")){
//        	returnMsg = reprintRequest(usertrackid);
//            
//        }
//        else{
//        	returnMsg = status;
//        	
//        }
//        
//        //System.out.println("returnMsg: " + returnMsg);
//    	}catch(Exception e){
//
//    		log.debug("Response SOAP Message:");
//			log.error(e);
//    	}
//       
//        
//        //*************************************//
//        
//        
//        return returnMsg;
//        
//    }
//    
//    
//    private static SOAPMessage checkStatusRes(String usertrackid) throws Exception {
//		SOAPMessage soapMessage = null;
//		try{
//        MessageFactory messageFactory = MessageFactory.newInstance();
//        soapMessage = messageFactory.createMessage();
//        SOAPPart soapPart = soapMessage.getSOAPPart();
//        
//        SOAPEnvelope envelope = soapPart.getEnvelope();
//        envelope.setPrefix("s");
//        envelope.removeNamespaceDeclaration("SOAP-ENV");
//        
//        
//        //Remove Header 
//        
//        SOAPHeader header = envelope.getHeader();
//        header.detachNode();
//        
//        // SOAP Body
//        SOAPBody soapBody = envelope.getBody();
//        soapBody.setPrefix("s");
//        SOAPElement soapBodyActnElem = soapBody.addChildElement("GetTransactionStatus","","http://hermes-it.in/API"); 
//       
//        SOAPElement soapBodypobjSecurityElem = soapBodyActnElem.addChildElement("GetTransactionStatusRequest");
//        soapBodypobjSecurityElem.addNamespaceDeclaration("i", "http://www.w3.org/2001/XMLSchemainstance");
//        SOAPElement soapBodyWebProviderIdElem = soapBodypobjSecurityElem.addChildElement("Authentication");
//        
//        SOAPElement soapBodyLoginIdElem = soapBodyWebProviderIdElem.addChildElement("LoginId");
//        soapBodyLoginIdElem.addTextNode(loginid);
//        SOAPElement soapBodyPasswordElem = soapBodyWebProviderIdElem.addChildElement("Password");
//        soapBodyPasswordElem.addTextNode(password);
//        
//        SOAPElement userTrackElem=soapBodypobjSecurityElem.addChildElement("UserTrackId");
//        
//        userTrackElem.addTextNode(usertrackid);
//        
//        
//        MimeHeaders headers = soapMessage.getMimeHeaders();
//        
//        headers.addHeader("SOAPAction","http://hermes-it.in/API/HermesMobile/GetTransactionStatus");
//        
//        soapMessage.saveChanges();
//
//        //System.out.print("Request SOAP Message:");
//       // soapMessage.writeTo(System.out);
//        ByteArrayOutputStream baout = new ByteArrayOutputStream();
//        soapMessage.writeTo(baout);
//        String strMsg = new String(baout.toByteArray());
//        log.debug("Request SOAP Message:"+strMsg);
//       
//        
//        
//
//		}catch(Exception e)
//		{
//			System.out.println(e);
//		}
//	
//		return soapMessage;
//	} 
//  //------------------------------------------reprint of your Booked details--------------------------------------------//
//
//    public static String reprintRequest(String usertrackid) {
//    	String returnMsg = "";
//    	try{
//        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//        
//        
//        String usertrackid2=HermesWebServiceClient.extendUsertrackid(usertrackid);
//        SOAPMessage soapResponse = soapConnection.call(reprintResponse(usertrackid2), url);
//        
//        ByteArrayOutputStream baout = new ByteArrayOutputStream();
//        soapResponse.writeTo(baout);
//        String strMsg = new String(baout.toByteArray());
//              
//        strMsg = strMsg.replaceAll("&lt;", "<");
//        strMsg = strMsg.replaceAll("&gt;", ">");        
//        strMsg = strMsg.replaceAll("\\<\\?xml(.+?)\\?\\>", "");
//        soapConnection.close();
//        
//     // soapResponse.writeTo(System.out);
//       // System.out.print("Response SOAP Message for reprint:"+strMsg); 
//        
//
//        log.debug("Response SOAP Message:"+strMsg);
//        
//      //*****persing reprint recharge detail***********//
//        Document doc = getDocument(strMsg);		
//		doc.getDocumentElement().normalize();
//              
//        /*NodeList nodes = doc.getElementsByTagName("ReprintBookingResponse");
//        Element element = (Element) nodes.item(0);*/
//        
//        NodeList name = doc.getElementsByTagName("ResponseStatus");
//        Element line = (Element) name.item(0);
//        String status = (getCharacterDataFromElement(line).trim().equals("1")?"SUCCESS":"PENDING");
//        if(status.equals("SUCCESS")){
//        	name = doc.getElementsByTagName("ReferenceNumber");
//            line = (Element) name.item(0);
//            String RefNo = getCharacterDataFromElement(line).trim();
//            
//            /*name = doc.getElementsByTagName("TransNo");
//            line = (Element) name.item(0);
//            String TransNo = getCharacterDataFromElement(line).trim();*/
//            
//            
//            returnMsg = ""+status+"|"+RefNo+"|"+RefNo;
//            
//        }
//        
//        
//        //System.out.println("returnMsg: " + returnMsg);
//    	}catch(Exception e){
//    		log.debug("Response SOAP Message:");
//			log.error(e);
//    	}
//       
//        
//        //*************************************//
//        
//        return returnMsg;
//        
//    }
//    
//    
//    private static SOAPMessage reprintResponse(String usertrackid) {
//		SOAPMessage soapMessage = null;
//		try{
//        MessageFactory messageFactory = MessageFactory.newInstance();
//        soapMessage = messageFactory.createMessage();
//        SOAPPart soapPart = soapMessage.getSOAPPart();
//        
//        SOAPEnvelope envelope = soapPart.getEnvelope();
//        envelope.setPrefix("s");
//        envelope.removeNamespaceDeclaration("SOAP-ENV");
//        
//        
//        //Remove Header 
//        
//        SOAPHeader header = envelope.getHeader();
//        header.detachNode();
//        
//        // SOAP Body
//        SOAPBody soapBody = envelope.getBody();
//        soapBody.setPrefix("s");
//        SOAPElement soapBodyActnElem = soapBody.addChildElement("GetReprint","","http://hermes-it.in/API"); 
//       
//        SOAPElement soapBodypobjSecurityElem = soapBodyActnElem.addChildElement("GetReprintRequest");
//        soapBodypobjSecurityElem.addNamespaceDeclaration("i", "http://www.w3.org/2001/XMLSchemainstance");
//        SOAPElement soapBodyWebProviderIdElem = soapBodypobjSecurityElem.addChildElement("Authentication");
//        
//        SOAPElement soapBodyLoginIdElem = soapBodyWebProviderIdElem.addChildElement("LoginId");
//        soapBodyLoginIdElem.addTextNode(loginid);
//        SOAPElement soapBodyPasswordElem = soapBodyWebProviderIdElem.addChildElement("Password");
//        soapBodyPasswordElem.addTextNode(password);
//        
//        SOAPElement userTrackElem=soapBodypobjSecurityElem.addChildElement("UserTrackId");
//        
//        userTrackElem.addTextNode(usertrackid);
//        
//        
//        MimeHeaders headers = soapMessage.getMimeHeaders();
//        
//        headers.addHeader("SOAPAction","http://hermes-it.in/API/HermesMobile/GetReprint");
//        
//        soapMessage.saveChanges();
//
//        //System.out.print("Request SOAP Message:");
//       // soapMessage.writeTo(System.out);
//        ByteArrayOutputStream baout = new ByteArrayOutputStream();
//        soapMessage.writeTo(baout);
//        String strMsg = new String(baout.toByteArray());
//        log.debug("Request SOAP Message:"+strMsg);
//        System.out.println();
//        
//        
//
//		}catch(Exception e)
//		{
//			System.out.println(e);
//		}
//	
//		return soapMessage;
//	}
//  //------------------------------------------credit balance Enquery--------------------------------------------//  
//    public static String checkBalance() {
//    	String strMsg = "";
//    	try{
//        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//
//        SOAPMessage soapResponse = soapConnection.call(checkBalanceRes(), url);
//        
//        ByteArrayOutputStream baout = new ByteArrayOutputStream();
//        soapResponse.writeTo(baout);
//       
//        strMsg = new String(baout.toByteArray());
//        
//           
//        strMsg = strMsg.replaceAll("&lt;", "<");
//        strMsg = strMsg.replaceAll("&gt;", ">");        
//        strMsg = strMsg.replaceAll("\\<\\?xml(.+?)\\?\\>", "");
//        soapConnection.close();
//        
//     // soapResponse.writeTo(System.out);
//        //System.out.print("Response SOAP Message:"+strMsg); 
//
//        log.debug("Response SOAP Message:"+strMsg);
//    	}catch(Exception e){
//    		log.debug("Response SOAP Message:");
//			log.error(e);
//    		
//    	}
//        
//        return strMsg;
//        
//    }
//
//    private static SOAPMessage checkBalanceRes() throws Exception {
//        MessageFactory messageFactory = MessageFactory.newInstance();
//        SOAPMessage soapMessage = messageFactory.createMessage();
//        SOAPPart soapPart = soapMessage.getSOAPPart();
//        SOAPEnvelope envelope = soapPart.getEnvelope();
//        envelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
//        envelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
//        
//        //SOAP Header
//        SOAPHeader soapHeader = envelope.getHeader();        
//        QName headerName = new QName("http://tempuri.org/WsHermes/Service1","clsSecurity", "ns1");        
//        SOAPHeaderElement soapHeaderclsSecurityElem = soapHeader.addHeaderElement(headerName);
//       soapHeaderclsSecurityElem.setMustUnderstand(false);
//        SOAPElement soapHeaderLoginIdElem = soapHeaderclsSecurityElem.addChildElement("loginid", "ns1");
//        soapHeaderLoginIdElem.addTextNode(loginid);
//        SOAPElement soapHeaderPasswordElem = soapHeaderclsSecurityElem.addChildElement("password", "ns1");
//        soapHeaderPasswordElem.addTextNode(password);
//        SOAPElement soapHeaderIsAgentElem = soapHeaderclsSecurityElem.addChildElement("IsAgent", "ns1");
//        soapHeaderIsAgentElem.addTextNode("false");
//        
//
//        // SOAP Body
//        SOAPBody soapBody = envelope.getBody(); 
//        SOAPElement soapBodyActnElem = soapBody.addChildElement("CheckQuota","","http://tempuri.org/HERMESAPI/HermesMobile/"); 
//        SOAPElement soapBodypobjSecurityElem = soapBodyActnElem.addChildElement("pobjSecurity");
//        
//        SOAPElement soapBodyWebProviderIdElem = soapBodypobjSecurityElem.addChildElement("WebProviderId");
//        soapBodyWebProviderIdElem.addTextNode("0");
//        SOAPElement soapBodyLoginIdElem = soapBodypobjSecurityElem.addChildElement("loginid");
//        soapBodyLoginIdElem.addTextNode(loginid);
//        SOAPElement soapBodyPasswordElem = soapBodypobjSecurityElem.addChildElement("password");
//        soapBodyPasswordElem.addTextNode(password);
//        SOAPElement soapBodyIsAgentElem = soapBodypobjSecurityElem.addChildElement("IsAgent");
//        soapBodyIsAgentElem.addTextNode("false");       
//        soapBodyActnElem.addChildElement("PstrFinalOutPut");
//        soapBodyActnElem.addChildElement("pstrError");
//       
//        
//        MimeHeaders headers = soapMessage.getMimeHeaders();
//        headers.addHeader("SOAPAction", "http://tempuri.org/HERMESAPI/HermesMobile/"  + "CheckQuota");
//
//        soapMessage.saveChanges();
//
//        //System.out.print("Request SOAP Message:");
//        //soapMessage.writeTo(System.out);
//        //System.out.println();
//        
//        ByteArrayOutputStream baout = new ByteArrayOutputStream();
//        soapMessage.writeTo(baout);
//        String strMsg = new String(baout.toByteArray());
//        log.debug("Request SOAP Message:"+strMsg);
//
//        return soapMessage;
//    }
//    
//    
//    
//    
//   
//    
//    
//    
//    
//    
//   //---------------------------------------------Testing code------------------------------------------------------// 
//    public static void main(String args[]) throws Exception {
//    	//checkBalance();
//    	//checkStatus("INTRV03000000S04122013133909");
//    	//reprintRequest("INTRV03000000S04122013133909");
//    	//rechargeRequest("KDTEST70005", "HACL", "AIRCEL", "", "10");
//  getAvailableoperators("prepaid");
// /* rechargeRequest("postpaid","p964223456123", "47", "RELIANCE POSTPAID", "9007252268","300","","","");*/
//    	//getAvailableoperators("Prepaid");
//        //BasicConfigurator.configure();
//       //System.out.println(HermesWebServiceClient.reduceUsertrackid(HermesWebServiceClient.extendUsertrackid("")));
//    }
//
//}
