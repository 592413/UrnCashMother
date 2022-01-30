package com.recharge.businessimpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.recharge.businessdao.SendRechargeRequest;

@Service("sendRechargeRequest")
public class SendRechargeRequestImpl implements SendRechargeRequest {

	@Override
	public String sendRechagreReq(String Rechargeurl) {
		String response = "";
		BufferedReader reader = null;
		try {
			URL url = new URL(Rechargeurl);
			reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			for (String line; (line = reader.readLine()) != null;) {
				response = line;
			}
			response = response.replaceAll("<br>", "");
			response = response.trim();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException ignore) {
				}
		}
		return response;
	}
	
	public static void main(String[] args) {
		SendRechargeRequest sd = new SendRechargeRequestImpl();
		String response = sd.sendRechagreReq("http://doopme.in/recharge_api/recharge?member_id=9836475767&api_password=bring123&api_pin=90364&number=9903712171&opcode=11&amount=10&request_id=1000010010&field1=&field2=");
		System.out.println("RESPONSE :: "+response);
	}

}
