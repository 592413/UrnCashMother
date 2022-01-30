package com.fingerpay.webservice;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;


public class FingerPayWebservice {
//	private static final String webServiceURI = "http://localhost:8080/Encohead/";
	private static final String webServiceURI = "https://api.encodigi.net.in";
	private static final Logger log = Logger.getLogger(FingerPayWebservice.class);
	public static String sendRequestToWebservice(Object input, String methodName) {
		String output = "";
		Client client = null;
		try{
			String url = webServiceURI + "/"+methodName;
			String i = new Gson().toJson(input);
			log.warn("input::::::::::::::::::"+methodName+":::"+i);
	        client=ClientBuilder.newClient();
	      //  System.out.println("url::::::::::::::::::"+url);
			Response response=client.target(url).request(MediaType.APPLICATION_JSON).post(Entity.json(i));
			//System.out.println("response::"+response);
			output =response.readEntity(String.class);
			log.warn("output::"+methodName+":::"+output);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return output;
	}


}
