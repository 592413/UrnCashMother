package com.recharge.icicidmtservicce;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;


public class ICICIWebservice {
	//private static final String MID="Encore33060974552301";//demo
		private static final String MID="Encore99211820111833";//live
		private static final  String url="https://billpayment.paytm.com/billpay";
	private static final Logger log = Logger.getLogger(ICICIWebservice.class);
	
	public static String sendRequestToWebservice(String url,String input,String checksum) {
		String output = "";
		Client client = null;
		String url2 = "" ; 
		try{
			System.out.println(url2);
			String i = new Gson().toJson(input);
			log.warn("timestamp::::::::::::::::::"+System.currentTimeMillis());
			log.warn("url::::::::::::::::::"+url);
			log.warn("checksum1::::::::::::::::::"+checksum);
			log.warn("inputpaytm::::::::::::::::::"+input);
	        client=ClientBuilder.newClient();
			Response response=client.target(url).request(MediaType.APPLICATION_JSON).header("x-mid",MID).header("x-checksum",checksum).post(Entity.json(input));
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
	
	
	public static String sendRequestToICICIWebservice(String url,Object input) {
		String output = "";
		Client client = null;
		String url2 = "" ; 
		try{
			String i = new Gson().toJson(input);
			log.warn("input::::::::::::::::::"+i);
			log.warn("url2::::::::::::::::::"+url);
	        client=ClientBuilder.newClient();
			Response response=client.target(url).request(MediaType.APPLICATION_JSON).post(Entity.json(i));
			output =response.readEntity(String.class);
			log.warn("output:::::::::::::::::::::"+output);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("sendRequestToSKYWebservice::::::::::::::::"+e);
			return null;
		}
		return output;
	}



}
