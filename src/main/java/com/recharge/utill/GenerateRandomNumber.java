package com.recharge.utill;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


public class GenerateRandomNumber {
	public static String generatePassword(){
		String alphabet = new String("01234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
		int n = alphabet.length();
		String result = new String();
		Random r = new Random();
		for (int i = 0; i < 100; i++)
			result = result + alphabet.charAt(r.nextInt(n));
		return result.substring(0, 6);
	}
		
	public static synchronized String geOTP(){
		 Random rnd = new Random();
		    int number = rnd.nextInt(999999);

		    // this will convert any number sequence into 6 character.
		    return String.format("%06d", number);
	}
	
	public static long getCurrentTimeinMs() {

		long timeInMillis = 0;
		try {
		timeInMillis = System.currentTimeMillis();
		} catch (Exception e) {
			
		}
		return timeInMillis;
		
	}
	public static String generateTid(String str){
		int c;
	    int num = 0;	    
	    Random t = new Random();
	    for (c = 1; c <= 10; c++) {
	        num = t.nextInt(9999);
	    }
	    String str1 = str + num;
	    return str1;
	}
	
public synchronized static String generateIPtid(String number){		
		
		if (!number.equals("")) {
			int stringLen = number.length();
			int num = 0;
			String lastChar = number.substring(6,stringLen);
			System.out.println("lastChar::::::::::::::::::::"+lastChar);
			Random t = new Random(System.currentTimeMillis());
			num = Math.abs((t.nextInt(999999999)));
			String str = lastChar + Integer.toString(num);
			return str;
		}else {
			return "";
		}
		
	}
	
	public synchronized static String generatePtid(String number){		
		
		if (!number.equals("")) {
			int stringLen = number.length();
			int num = 0;
			String lastChar = number.substring(stringLen - 3, stringLen);
			Random t = new Random(System.currentTimeMillis());
			num = Math.abs((t.nextInt(999999999)));
			String str = lastChar + Integer.toString(num);
			return str;
		}else {
			return "";
		}
		
	}
	
	public static String generateUserName(){
		String alphabet = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		int n = alphabet.length();
		String result = new String();
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			result = result + alphabet.charAt(r.nextInt(n));
		}
		return result.substring(4, 10);
	}
	
	
	public static String getCurrentDate() {
		String today = "";
		try {
			long timeInMillis = System.currentTimeMillis();
			Calendar cal1 = Calendar.getInstance();
			cal1.setTimeInMillis(timeInMillis);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			today = formatter.format(cal1.getTime());
		} catch (Exception e) {
			today = "0000-00-00";
		}
		return today;
	}
	
	
	public static String getCurrentTime() {
		String currentTime = "";
		try {
			long timeInMillis = System.currentTimeMillis();
			Calendar cal1 = Calendar.getInstance();
			cal1.setTimeInMillis(timeInMillis);			
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
			currentTime = sdf.format(cal1.getTime());
		} catch (Exception e) {
			currentTime = "00:00:00 AM";
		}
		return currentTime;
	}
	
	public static synchronized String generateTransactionNumber(){
		int c;
	    int num = 0;
	    Random t = new Random();
	    for (c = 1; c <= 10; c++) {
	        num = t.nextInt(99999);
	    }
	    String str1 = "TRAN" + num;
	    return str1;
	}
	
	public static synchronized String referenceNO(){
		int c;
	    int num = 0;
	    Random t = new Random();
	    for (c = 1; c <= 10; c++) {
	        num = t.nextInt(99999);
	    }
	    String str1 =String.valueOf(num);
	    return str1;
	}
	
	public static String getCurrentDateNew() {
		String today = "";
		try {
			long timeInMillis = System.currentTimeMillis();
			Calendar cal1 = Calendar.getInstance();
			cal1.setTimeInMillis(timeInMillis);
			DateFormat formatter = new SimpleDateFormat("“YYYYMMDDHHmmss");
			today = formatter.format(cal1.getTime());
		} catch (Exception e) {
			today = "0000-00-00";
		}
		return today;
	}
	
	public static String generateWLID(){
		String alphabet = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890123456789ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ");
		int n = alphabet.length();
		String result = new String();
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			result = result + alphabet.charAt(r.nextInt(n));
		}
		
		return "MSR"+ result.substring(4, 8);
	}
	
	public static String generateRequestId(){
		int c;
	    int num = 0;	    
	    Random t = new Random();
	    for (c = 1; c <= 10; c++) {
	        num = t.nextInt(9999);
	    }
	    String str1 = String.valueOf(num);
	    return str1;
	}
	public static synchronized String generateTrannNumber(){
		int c;
		String alphabet = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890123456789ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ");
		int n = alphabet.length();
		String result = new String();
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			result = result + alphabet.charAt(r.nextInt(n));
		}
	    int num = 0;
	    Random t = new Random();
	    for (c = 1; c <= 15; c++) {
	        num = t.nextInt(99999999);
	    }
	    String str1 = result.substring(4, 8) + num;
	    return str1;
	}
	
	public static void main(String[] args) {
		//System.out.println("DATE ::: "+getCurrentDate()+"  TIME :::  "+ getCurrentTime());
		System.out.println("WLID :::::::::: "+generateWLID());
	}
}
