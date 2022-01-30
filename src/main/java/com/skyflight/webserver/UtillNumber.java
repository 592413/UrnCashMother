package com.skyflight.webserver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UtillNumber {
	public static String DifferenceDuration(String str,String str1){
		Date d1;
		String differen = "";
		try {
			d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
		
		Date d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str1);
		long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
	     differen = diffHours + "h "+diffMinutes+"m";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return differen;
	}
}
