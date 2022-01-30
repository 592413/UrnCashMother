package com.recharge.utill;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SMS2 {
	
	public static void sendSMS(String phn, String msg, String sender_id, String sms_user_id, String sms_password) {
		try {

			String baseurl = "http://173.45.76.227/send.aspx?";
			System.out.println(baseurl);
			URL url = new URL(baseurl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
			String content = "username=" + URLEncoder.encode(sms_user_id) + "&pass="+ URLEncoder.encode(sms_password) + "&route=trans1&senderid="+sender_id+"&numbers="+phn+"&message="+ URLEncoder.encode(msg);
			System.out.println(content);
			writer.writeBytes(content);
			writer.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			if (reader.ready()) {
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
			}
			reader.close();
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void sendSMS2(String phn, String msg, String sender_id, String sms_user_id, String sms_password) {
		try {

			String baseurl = "http://173.45.76.227/send.aspx?";
			System.out.println(baseurl);
			URL url = new URL(baseurl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
			String content = "username=" + URLEncoder.encode(sms_user_id) + "&pass="+ URLEncoder.encode(sms_password) + "&route=trans1&senderid="+sender_id+"&numbers="+phn+"&message="+ URLEncoder.encode(msg);
			System.out.println(content);
			writer.writeBytes(content);
			writer.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			if (reader.ready()) {
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
			}
			reader.close();
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendSMSCust(String phn, String msg, String sender_id, String sms_user_id, String sms_password) {
		try {

			String baseurl = "http://173.45.76.227/send.aspx?";
			System.out.println(baseurl);
			URL url = new URL(baseurl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
			String content = "username=" + URLEncoder.encode(sms_user_id) + "&pass="+ URLEncoder.encode(sms_password) + "&route=trans1&senderid="+sender_id+"&numbers="+phn+"&message="+ URLEncoder.encode(msg);
			System.out.println(content);
			writer.writeBytes(content);
			writer.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			if (reader.ready()) {
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
			}
			reader.close();
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {		
		try {
			sendSMS("9088531085","Welcome to One Point Dream,One stop Travel Portal.Your User Id is 121212 and Password is 121212.visit Us-www.onepointdream.com","WALLET", "ysk-digital", "partha1");
		} catch (Exception ex) {
			Logger.getLogger(SMS2.class.getName()).log(Level.SEVERE, null, ex);
		}

	}


}
