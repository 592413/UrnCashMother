package com.recharge.instantpayutil;

import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AEPSUtil {
	
	 public static byte[] encrypt (byte[] plaintext,String key,byte[] IV ) throws Exception {
	        //Get Cipher Instance
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        
	        //Create SecretKeySpec
	        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");

	        //Create IvParameterSpec
	        IvParameterSpec ivSpec = new IvParameterSpec(IV);
	        
	        //Initialize Cipher for ENCRYPT_MODE
	        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
	        
	        //Perform Encryption
	        byte[] cipherText = cipher.doFinal(plaintext);
	        return cipherText;
	    }
	
	 public static String buildJson(Map<String, String> map) {
	        String jsonMap = "{";
	        for (Map.Entry<String, String> entry : map.entrySet()) {
	            jsonMap += ",";
	            jsonMap += "\""+entry.getKey()+"\":\""+entry.getValue()+"\"";
	        }
	        jsonMap += "}";
	        jsonMap = jsonMap.substring(0, 1) + jsonMap.substring(2);

	        return jsonMap;
	    }
	 
	 public static byte[] combine(String IV, Mac sha256_HMAC, byte[] cipherText) {
	        byte[] c1 = IV.getBytes();
	        byte[] c2 = sha256_HMAC.doFinal(cipherText);
	        byte[] c3 = cipherText;
	        byte[] combined = new byte[c1.length + c2.length + c3.length];

	        int comLength = 0;
	        for (int i = 0; i < c1.length; ++i)
	        {
	            combined[comLength] = c1[i];
	            comLength++;
	        }

	        for (int i = 0; i < c2.length; ++i)
	        {
	            combined[comLength] = c2[i];
	            comLength++;
	        }

	        for (int i = 0; i < c3.length; ++i)
	        {
	            combined[comLength] = c3[i];
	            comLength++;
	        }

	        return combined;
	    }


}
