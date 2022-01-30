package com.skyhotel.webserver;

import java.text.SimpleDateFormat;
import java.util.Date;


public class UtilityHotel {
	public static String DifferenceDate(String str,String str1){
		Date d1;
		String differen = "";
		 int diff =0;
		try {
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date dateBefore = myFormat.parse(str);
			Date dateAfter = myFormat.parse(str1);
			 long difference = dateAfter.getTime() - dateBefore.getTime();
			 System.out.println("diffInMillies::"+difference);
		     diff =  (int)(difference / (1000*60*60*24));
		    System.out.println("diff::"+diff);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.toString(diff);
	}
	
	public static int dateDifference(String dateStart,String dateStop){
		System.out.println(dateStart+"---"+dateStop);
		float daysBetween =0;
		try{
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateBefore = myFormat.parse(dateStart);
		Date dateAfter = myFormat.parse(dateStop);
		 long difference = dateAfter.getTime() - dateBefore.getTime();
		  daysBetween = (difference / (1000*60*60*24));
		 
		}catch(Exception e){
			e.printStackTrace();
		}
		return (int)daysBetween;
		 
		
	}
}
