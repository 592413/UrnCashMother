package com.recharge.utill;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;


import com.google.gson.Gson;

public class RBLService {
	private static final Logger log = Logger.getLogger(RBLService.class);
	
	public static String sendRequestToWebservice(String url,Object input, String methodName) {
		String output = "";
		Client client = null;
		String url2 = "" ; 
		try{
			System.out.println(url2);
			url2 = url + "/"+methodName;
			String i = new Gson().toJson(input);
			System.out.println("input::::::::::::::::::"+i);
	        client=ClientBuilder.newClient();
			Response response=client.target(url2).request(MediaType.APPLICATION_JSON).post(Entity.json(input));
			output =response.readEntity(String.class);
		    System.out.println(output);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("sendRequestToWebservice::::::::::::::::"+e);
			return null;
		}
		return output;
	}
	
	public static String sendRequestToWebserviceGet(String agentcode,String refno) {
		String output = "";
		Client client = null;
		String url2 = "" ; 
		String mid="5d69cafaa8334373";
		String secret = "3553e5ce34114d6c9014f4c8772b4ca5"; 
		byte[] result = null;
		// agentcode = "f5884c930c";
		try{
			SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(),"HmacSHA256");
			Long ts=System.currentTimeMillis();
			String payload = "5d69cafaa8334373|"+agentcode+"|"+ts;
		    Mac mac = null;
		    mac = Mac.getInstance("HmacSHA256");
			mac.init(keySpec);
			result = mac.doFinal(payload.getBytes());
		    String cs=Base64.getEncoder().encodeToString(result);
			System.out.println(url2);
			url2 = "https://shreedhan.paymonk.com/megatron/partner/aepsCheckStatus/"+refno;
			//String i = new Gson().toJson(input);
			//System.out.println("input::::::::::::::::::"+i);
	        client=ClientBuilder.newClient();
			Response response=client.target(url2).request(MediaType.APPLICATION_JSON).header("ac",agentcode)
					.header("ts",ts.toString())
					.header("cs",cs)
					.header("mid",mid).get();
			output =response.readEntity(String.class);
		    System.out.println(output);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("sendRequestToWebservice::::::::::::::::"+e);
			return null;
		}
		return output;
	}
	
	public static void main(String args[]){
		String input=" {\"ST\": \"REMDOMESTIC\",\"AID\": \"RS00789\",\"OP\": \"DMTNUR\",\"CUSTOMER_MOBILE\": \"8013724327\"}";
		sendRequestToWebservice("https://uat2yesmoney.easypay.co.in/epMoney",input,"retvalcustomer/v1.0");
	}

}
