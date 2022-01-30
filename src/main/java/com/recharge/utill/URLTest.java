package com.recharge.utill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class URLTest {
	public static String checkServer(){
		String response = "";
		try {
			String url="https://www.apivendor.com";
		    URL myURL = new URL("https://www.apivendor.com");
		    // also you can put a port 
		   //  URL myURL = new URL("http://localhost:8080");
		   /* URLConnection myURLConnection = myURL.openConnection();
		    myURLConnection.connect();*/
		    
		    HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);
			
			HttpResponse response1 = client.execute(request);
             int responsecode = response1.getStatusLine().getStatusCode();
			System.out.println("Response Code : " 
		                + response1.getStatusLine().getStatusCode());
		    if(responsecode==404){
		    response ="not ok";
		    }else{
		    response ="ok";	
		    }
		} 
		catch (MalformedURLException e) { 
		    // new URL() failed
		    // ...
			response ="not ok";
		} 
		catch (IOException e) {   
		    // openConnection() failed
		    // ...
			response ="not ok";
		}
		
		return response;
	}
	
	public static void main(String[] args) {
		String response = "";
		try {
		    URL myURL = new URL("https://www.apivendor.com");
		    // also you can put a port 
		   //  URL myURL = new URL("http://localhost:8080");
		    URLConnection myURLConnection = myURL.openConnection();
		    myURLConnection.connect();
		    response = "ok";
		    System.out.println("response::::::::::::::::"+response);
		} 
		catch (MalformedURLException e) { 
		    // new URL() failed
		    // ...
			response = "not ok";
			System.out.println("response::::::::::::::::"+response);
		} 
		catch (IOException e) {   
		    // openConnection() failed
		    // ...
		}
	}
	
	

}
