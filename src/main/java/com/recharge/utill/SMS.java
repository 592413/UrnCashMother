/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.recharge.utill;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.recharge.model.SMSApiparameters;
import com.recharge.servicedao.SMSApiparametersDao;

public class SMS {
	

	public  void sendSMS(String phn, String msg, String sender_id, String sms_user_id, String sms_password,List<SMSApiparameters> params) {
		try {
            //List<SMSApiparameters> params =  smsapiparamsdao.getAllSMSApiparameters();
            SMSApiparameters sms = params.get(0);
			String baseurl = sms.getBaseurl()+"?";
			String[] staticparameters = sms.getStaticparameters().split("\\,");
			String[] staticparmetervalue = sms.getStaticparametervalues().split("\\,");
			System.out.println(baseurl);
			URL url = new URL(baseurl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
			String content = sms.getUsername()+"=" + URLEncoder.encode(sms_user_id) + "&"+sms.getPassword()+"="+ URLEncoder.encode(sms_password) + "&type=0" + "&dlr=1" + "&"+sms.getDestination()+"=" + phn + "&"+sms.getSource()+"="+ sender_id + "&"+sms.getMessage()+"=" + URLEncoder.encode(msg);
			for(int i=0;i<staticparameters.length;i++){
				content=content+"&"+staticparameters[i]+"="+staticparmetervalue[i];
			}
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

			String baseurl = "http://rdemo.mediaalertbox.in/sendsms/sendsms.php?";
			System.out.println(baseurl);
			URL url = new URL(baseurl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
			String content = "username=" + URLEncoder.encode(sms_user_id) + "&password="+ URLEncoder.encode(sms_password) + "&type=TEXT" + "&mobile=" + phn + "&sender="+ sender_id + "&message=" + URLEncoder.encode(msg);
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

			String baseurl = "http://136.243.176.144/domestic/sendsms/bulksms.php?";
			System.out.println(baseurl);
			URL url = new URL(baseurl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
			String content = "username=" + URLEncoder.encode(sms_user_id) + "&password="+ URLEncoder.encode(sms_password) + "&type=TEXT" + "&mobile=" + phn + "&sender="+ sender_id + "&message=" + URLEncoder.encode(msg);
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
			//sendSMS("9088531085","Welcome to One Point Dream,One stop Travel Portal.Your User Id is 121212 and Password is 121212.visit Us-www.onepointdream.com","WALLET", "ysk-digital", "partha1");
		} catch (Exception ex) {
			/*Logger.getLogger(SMS.class.getName()).log(Level.SEVERE, null, ex);*/
		}

	}
}
