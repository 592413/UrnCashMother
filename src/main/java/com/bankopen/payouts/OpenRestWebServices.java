package com.bankopen.payouts;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/*import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;*/

import org.apache.log4j.Logger;

import com.bankopen.model.PaymentCustom;
import com.google.gson.Gson;
import com.recharge.yesbankservice.YesbankWebserviceParser;
/*import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;*/
import com.sun.jersey.api.client.WebResource;



public class OpenRestWebServices {
	private static final Logger logger_log = Logger.getLogger(OpenRestWebServices.class);
	
	/* demo */
	// private static final String URL="https://sandbox.bankopen.co/v1";
	
/*	 private static final String apikey = "fe345c40-3144-11eb-8bef-d7804d82041a";
		private static final String secret = "3db3287fdc98c4672b1bffbb4b0121ba747765a0";*/
	/* Live */
	private static final String URL = "https://v2-api.bankopen.co/v1";

	 
	 
	private static final String apikey = "e88d5cf0-017a-11eb-b84f-832cd0073a86";
	private static final String secret = "cb046d83529324c5b10afffca9759cd15d7eed39";
		
		public static String sendRequestToOpen(Object input, String methodName) {
			String output="";
			Client client = null;
			try{/*
			String url = URL + "/" + methodName;
			 logger_log.warn("url::"+url);
			Client restClient = Client.create();
			Date date= new Date();
			 long time = date.getTime();
			 logger_log.warn("time::"+time);
			 String authen=apikey+":"+secret;
			 logger_log.warn("authen::"+authen);
			WebResource webResource = restClient.resource(url);
			ClientResponse resp = webResource.type(MediaType.APPLICATION_JSON_TYPE).accept("application/json")
					.header("Authorization","Bearer "+authen).header("X-O-Timestamp",time).post(ClientResponse.class, input);
			//System.out.println(webResource. );
			System.out.println(resp.toString());
			if (resp.getStatus() != 200) {
				logger_log.warn("Unable to connect to the server");
			}
			 output = resp.getEntity(String.class);
			logger_log.warn("sendRequestToOpen::::::::::::::::" + output);
			*/
				String url = URL + "/" + methodName;
				Date date= new Date();
				 long time = date.getTime();
				 logger_log.warn("time::"+time);
				 String authen=apikey+":"+secret;
				 logger_log.warn("authen::"+authen);
				String i = new Gson().toJson(input);
				 logger_log.warn("input::::::::::::::::::"+i);
		        client=ClientBuilder.newClient();
				Response response=client.target(url).request(MediaType.APPLICATION_JSON).header("Authorization", "Bearer "+authen).header("X-O-Timestamp",time).post(Entity.json(i));
				output =response.readEntity(String.class);
				logger_log.warn(output);
			
			
			
			}catch(Exception e){
				logger_log.warn("sendRequestToOpen::::::::::::::::" + e);
			}
			return output;
		}
		
		public static String initiatepayout(PaymentCustom input) {
			String response="";
			try{
				String methodName = "payouts";
				 response=sendRequestToOpen(input,methodName);
				 logger_log.warn(response);
			}catch(Exception e){
				
			}
			return response;
		}
		
		/*public static String initiatepayout(String input) {
			String response="";
			try{
				String methodName = "payouts";
				logger_log.warn(input);
				 response=sendRequestToOpen(input,methodName);
				 logger_log.warn(response);
			}catch(Exception e){
				
			}
			return response;
		}*/
		
		
		public static Map<String, Object> payoutOtp() {
			Map<String, Object> returnData = new HashMap<String, Object>();
			String res = "";
			try {/*
				String methodName = "payouts/otp";
				Date date= new Date();
				 long time = date.getTime();
				 System.out.println("time::"+time);
			//	res = sendRequestToOpen( input, method);
				String url = URL + "/" + methodName;
				System.out.println("url::"+url);
				Client restClient = Client.create();
				String authen=apikey+":"+secret;
				System.out.println(authen);
				WebResource webResource = restClient.resource(url);
				ClientResponse resp = webResource.type(MediaType.APPLICATION_JSON_TYPE).accept("application/json")
						.header("Authorization", "Bearer " + authen).header("X-O-Timestamp", time).post(ClientResponse.class);
				if (resp.getStatus() != 200) {
					logger_log.warn("Unable to connect to the server");
				}
				String output = resp.getEntity(String.class);
				logger_log.warn("sendRequestToOpen::::::::::::::::" + output);
				
				logger_log.warn("payoutOtp:::::::::::::::::::" + res);
				//returnData = YesbankWebserviceParser.checkuserYesBankparser(res);
			*/} catch (Exception e) {
				logger_log.error("payoutOtp::::::::::::::::::::" + e);
			}

			return returnData;

		}

		public static String PayoutStatus(String string) {
			String response="";
			try{/*
				String url = URL + "/payouts/" + string;
				Client restClient = Client.create();
				Date date= new Date();
				 long time = date.getTime();
				 System.out.println("time::"+time);
				 String authen=apikey+":"+secret;
				 WebResource webResource = restClient.resource(url);
					ClientResponse resp = webResource.type(MediaType.APPLICATION_JSON_TYPE).accept("application/json")
							.header("Authorization", "Bearer " + authen).header("X-O-Timestamp", time).get(ClientResponse.class);
					if (resp.getStatus() != 200) {
						logger_log.warn("Unable to connect to the server");
					}
					 response = resp.getEntity(String.class);
					logger_log.warn("sendRequestToOpen::::::::::::::::" + response);
				
			*/}catch(Exception e){
				logger_log.error("PayoutStatus::::::::::::::::::::" + e);
			}
			return response;
		}

}
