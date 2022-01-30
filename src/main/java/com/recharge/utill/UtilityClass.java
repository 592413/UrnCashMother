package com.recharge.utill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UtilityClass {
	public static boolean checkParameterIsNull(Map<String, String> param) {
		boolean flag = true;
		for(Map.Entry<String, String> entry : param.entrySet()){
            if (entry.getValue() == null || entry.getValue().equals("")) {
            	flag = false;        
            	break;
            }           
        }		
		return flag; 
	} 
	
	public static boolean checkStringIsNull(String obj) {
		if(obj == null || obj.equals("")) {
			return false;
		}else {
			return true;
		}
	}
	
	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
	
	public static boolean isValidMobileNumber(String mobile) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\d{10}");
		java.util.regex.Matcher m = p.matcher(mobile);
		return m.matches();
	}
	
	public static Map<String, String> toMap(JSONObject object) throws JSONException {
		Map<String, String> map = new HashMap<String, String>();
		Iterator<String> keysItr = object.keys();
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			String value =String.valueOf(object.get(key));			
			map.put(key, value);
		}
		return map;
	}
	
	
	public static Map<String, Object> toMapObj(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator<String> keysItr = object.keys();
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			String value =String.valueOf(object.get(key));			
			map.put(key, value);
		}
		return map;
	}

	
	public static long timeDifference(String dateStart,String dateStop){
		System.out.println(dateStart+"---"+dateStop);
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");

	    Date d1 = null;
	    Date d2 = null;
	    try {
	        d1 = format.parse(dateStart);
	        d2 = format.parse(dateStop);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // Get msec from each, and subtract.
	    long diff = d2.getTime() - d1.getTime();
	    System.out.println(diff+"--diff");	    
		return diff / 60000;
	}
	
	public static void main(String[] args) {
		//System.out.println(isValidEmailAddress("skypointgmail..com"));
		System.out.println(timeDifference("08:10:28 PM", GenerateRandomNumber.getCurrentTime()));
	}
}
