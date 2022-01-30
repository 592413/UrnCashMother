package com.recharge.utill;

import java.io.IOException;
import java.io.StringReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.recharge.yesbankservice.YesbankWebserviceParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class RDServiceStartek {
   
	private static final Logger logger_log = Logger.getLogger(YesbankWebserviceParser.class);
	
	public static String getPidStartek(String pidOpt, String reqUrl)
			throws NoSuchAlgorithmException, KeyManagementException {
		String response = "";
        try{
		TrustManager[] certs = new TrustManager[] { new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

		} };

		SSLContext ctx = null;
		ctx = SSLContext.getInstance("SSL");
		ctx.init(null, certs, new SecureRandom());

		HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
		DefaultClientConfig config = new DefaultClientConfig();

		config.getProperties().put(URLConnectionClientHandler.PROPERTY_HTTP_URL_CONNECTION_SET_METHOD_WORKAROUND, true);
		config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
				new HTTPSProperties(new HostnameVerifier() {
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				}, ctx));
		// System.out.println("Payel:::::::::::::::::");
		Client c = Client.create(config);
		// System.out.println("Payel:::::::::::::::::");

	//	System.out.println("Payel:::::::::::::::::"+reqUrl);
		reqUrl="http://127.0.0.1:11102/rd/capture";
		//reqUrl="http://localhost:11100/capture";
	   
	   // reqUrl="http://localhost:8003/mfs100";
	//	reqUrl="http://localhost:11100/rd/capture";
		System.out.println("Payel:::::::::::::::::"+reqUrl);
		WebResource r = c.resource(reqUrl);
		// WebResource r = c.resource("http://localhost:11100/rd/capture");
		// System.out.println("Payel:::::::::::::::::");
		response = r.method("CAPTURE",String.class,pidOpt);
		logger_log.warn("getPidStartekresponse::::::::::::::::::::::"+response);
		// System.out.println("Payel:::::::::::::::::");
		System.out.println(response);
        }catch (Exception e) {
			logger_log.error("getPidStartek::::::::::::::::::::::"+e);
		}

		return response;
	}
	public static String getPidStartek2(String pidOpt, String reqUrl){
		String response = "";
		for(int i=11100;i<11120;i++){
		reqUrl=":"+i;
		DefaultClientConfig config = new DefaultClientConfig();
		config.getProperties().put(URLConnectionClientHandler.PROPERTY_HTTP_URL_CONNECTION_SET_METHOD_WORKAROUND, true);
		Client c = Client.create(config);
		WebResource r = c.resource(reqUrl);
		 response = r.method("RDSERVICE",String.class,pidOpt);
		 System.out.println("response:::::::::::::::::::::::"+response);
		}
		System.out.println("response:::::::::::::::::::::::"+response);
		return response;
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
	
	
	private static Document getDocument(String xml) throws ParserConfigurationException, SAXException, IOException{
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		doc = db.parse(is);
		return doc;
	}
	
	
	public static Map<String,Object> getPIDParser(String xml){
		Map<String, Object> returnData = new HashMap<String, Object>();
		System.out.println("getPIDParser:::::::::::::::::"+xml);
		try{
			Document  doc = getDocument(xml);
			doc.getDocumentElement().normalize();
			NodeList node = doc.getElementsByTagName("PidData");
			System.out.println("Payel::::::::::::::::::::::");
			Element element =(Element)node.item(0);
			NodeList Resp = element.getElementsByTagName("Resp");
			Element Respelement =(Element)Resp.item(0);
			System.out.println("Payel::::::::::::::::::::::");
			returnData.put("errCode",Respelement.getAttributes().getNamedItem("errCode").getNodeValue());
			returnData.put("errInfo", Respelement.getAttributes().getNamedItem("errCode").getNodeValue());
			
		}catch (Exception e) {
			logger_log.warn("getPIDParser:::::::::::::::::::::::::"+e);	
			returnData.put("errCode","1");
			returnData.put("errInfo","Technical Error");
			return returnData;
		}
		
		return returnData;
	}
	
	
	public static String getPIDParser2(String xml){
		String returnData = "";
		System.out.println("getPIDParser:::::::::::::::::"+xml);
		try{
			Document  doc = getDocument(xml);
			doc.getDocumentElement().normalize();
			NodeList node = doc.getElementsByTagName("PidData");
			System.out.println("Payel::::::::::::::::::::::");
			Element element =(Element)node.item(0);
			NodeList Resp = element.getElementsByTagName("Resp");
			Element Respelement =(Element)Resp.item(0);
			System.out.println("Payel::::::::::::::::::::::");
			
			
		}catch (Exception e) {
			logger_log.warn("getPIDParser:::::::::::::::::::::::::"+e);	
			/*returnData.put("errCode","1");
			returnData.put("errInfo","Technical Error");*/
			return returnData;
		}
		
		return returnData;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
