package com.recharge.icicidmtservicce;

import java.util.Base64;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class RazorPayWebService {
	
	
	private static final Logger log = Logger.getLogger(RazorPayWebService.class);
	
	private static final String url = "https://api.razorpay.com/v1/";
	
	//For Test Account
//	 private static final  String keyId= "rzp_test_ZgNIrVb6A6wdYd";
//	 private static final  String keySecret = "6DgfUrjwdrNUOKmJ99GM5GzH";
	 
	
	//For Live Account
	 private static final  String keyId= "rzp_live_jhDYMkNv77ZLft";
	 private static final  String keySecret = "37whUaiVrRwdK7WIZYF4lWQy";
	 
     private static final String authString = keyId + ":" + keySecret;
     
     
	
	
	public static String sendRequestToWebservice(String method,String input) {
		String output = "";
		Client client = null;
		String finalUrl = url+method ;
		String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
		
		try{
			String i = new Gson().toJson(input);
	
	        client=ClientBuilder.newClient();
			Response response=client.target(finalUrl)
					.request(MediaType.APPLICATION_JSON)
					.header("Authorization", "Basic " + authStringEnc)
					.post(Entity.json(input));
			output =response.readEntity(String.class);
			log.warn("outputpaytm::::::::::::::::::"+output);
		 //   System.out.println(response.getHeaders());
		}catch (Exception e) {
			e.printStackTrace();
			log.error("sendRequestToWebservice::::::::::::::::"+e);
			return null;
		}
		return output;
	}

}
