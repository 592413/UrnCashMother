package com.recharge.utill;

import java.security.MessageDigest;

public class SHACheckSumExample {
	public static String  reverseHash(String hash) throws Exception
	{
	MessageDigest md = MessageDigest.getInstance("SHA-512");
	
	 md.update(hash.getBytes("UTF-8"));
	byte[] mdbytes = md.digest();
	//convert the byte to hex format method
	StringBuffer sb = new StringBuffer();
	for (int i = 0; i < mdbytes.length; i++)
	{
	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100,
	16).substring(1));
	}
	System.out.println("Hex format : " + sb.toString());
	/*
	 * //convert the byte to hex format method 2 StringBuffer hexString = new
	 * StringBuffer(); for (int i=0;i<mdbytes.length;i++) {
	 * hexString.append(Integer.toHexString(0xFF & mdbytes[i])); }
	 * 
	 * System.out.println("Hex format : " + hexString.toString());
	 */
	return sb.toString();
}
	
	public static void main(String[] args) throws Exception {
	System.out.println(reverseHash("pjVQAWpA|success|||||||||||rbanerjee.csp@gmail.com|ROHAN BANERJEE|Wallet|10.00|962636363444|7rnFly"));	
	}
}
