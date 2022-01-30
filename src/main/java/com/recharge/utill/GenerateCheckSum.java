package com.recharge.utill;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.util.Base64;
import java.util.Map;

public class GenerateCheckSum {
	/* demo */
//	private static final String secret="aefc05467d";
	/* Live */
	private static final String secret="ca230dac50";
	public static String generateChecksumyesbank(Map<String, Object> request,String AID) throws DecoderException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException{
		String checksum="";
	System.out.println(request);
		String ORDER_ID=request.get("ORDER_ID").toString();
		String ST=request.get("mode").toString();
		String TXN_AMOUNT="";
		//String OP="AEPS";
		if(ST.equalsIgnoreCase("BALANCEINFO")){
		TXN_AMOUNT="0";
		}else if(ST.equalsIgnoreCase("WITHDRAWAL")){
		TXN_AMOUNT=request.get("amount").toString();
		
		}
		//String data =""+ORDER_ID+"|"+AID+"|"+TXN_AMOUNT+"|"+OP+"|"+ST+"|"+AadharNumber+"";
		String data =""+request.get("cpCode").toString()+"|"+request.get("timestap").toString()+"|"+TXN_AMOUNT+"|"+ORDER_ID+"";
		System.out.println("data:::::::::::::::::::::"+data);
		byte[] decodedKey = Hex.decodeHex(secret.toCharArray());
		SecretKeySpec keySpec = new SecretKeySpec(decodedKey, "HmacSHA512");
		Mac mac = Mac.getInstance("HmacSHA512");
		mac.init(keySpec);
		byte[] dataBytes = data.getBytes("UTF-8");
		byte[] signatureBytes = mac.doFinal(dataBytes);
		checksum = new String(Base64.getEncoder().encodeToString(signatureBytes));
        System.out.println("checksum::::::::::::::::::"+checksum);  
		
		return checksum;
	}
	
	public static String generateChecksumMinistate(Map<String, String> request,String AID) throws DecoderException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException{
		String checksum="";
	
		String ORDER_ID=request.get("ORDER_ID");
		String ST="MINISTATEMENT";
		String TXN_AMOUNT="";
		String OP="ICICIAEPS";
		TXN_AMOUNT="0";
		
		String AadharNumber=request.get("aadhar");
		String data =""+ORDER_ID+"|"+AID+"|"+TXN_AMOUNT+"|"+OP+"|"+ST+"|"+AadharNumber+"";
		System.out.println("data:::::::::::::::::::::"+data);
		System.out.println("secret:::::::::::::::::::::"+secret);
		byte[] decodedKey = Hex.decodeHex(secret.toCharArray());
		SecretKeySpec keySpec = new SecretKeySpec(decodedKey, "HmacSHA512");
		Mac mac = Mac.getInstance("HmacSHA512");
		mac.init(keySpec);
		byte[] dataBytes = data.getBytes("UTF-8");
		byte[] signatureBytes = mac.doFinal(dataBytes);
		checksum = new String(Base64.getEncoder().encodeToString(signatureBytes));
        System.out.println("checksum::::::::::::::::::"+checksum);  
		
		return checksum;
	}
	
	
	public static String generateChecksumyesbankAnd(Map<String, String> request,String AID) throws DecoderException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException{
		String checksum="";
	
		String ORDER_ID=request.get("ORDER_ID");
		String ST=request.get("mode");
		String TXN_AMOUNT="";
		String OP="AEPS";
		if(ST.equalsIgnoreCase("BALANCEINFO")){
		TXN_AMOUNT="0";
		}else if(ST.equalsIgnoreCase("WITHDRAWAL")){
		TXN_AMOUNT=request.get("amount");
		}
		String AadharNumber=request.get("aadhar");
		String data =""+ORDER_ID+"|"+AID+"|"+TXN_AMOUNT+"|"+OP+"|"+ST+"|"+AadharNumber+"";
		System.out.println("data:::::::::::::::::::::"+data);
		byte[] decodedKey = Hex.decodeHex(secret.toCharArray());
		SecretKeySpec keySpec = new SecretKeySpec(decodedKey, "HmacSHA512");
		Mac mac = Mac.getInstance("HmacSHA512");
		mac.init(keySpec);
		byte[] dataBytes = data.getBytes("UTF-8");
		byte[] signatureBytes = mac.doFinal(dataBytes);
		checksum = new String(Base64.getEncoder().encodeToString(signatureBytes));
        System.out.println("checksum::::::::::::::::::"+checksum);  
		
		return checksum;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, DecoderException, InvalidKeyException {
	String data ="AB12345687123|RS00789|0|AEPS|BALANCEINFO|624499181514";
	String secret = "abcd987534";
	byte[] decodedKey = Hex.decodeHex(secret.toCharArray());
	SecretKeySpec keySpec = new SecretKeySpec(decodedKey, "HmacSHA512");
	Mac mac = Mac.getInstance("HmacSHA512");
	mac.init(keySpec);
	byte[] dataBytes = data.getBytes("UTF-8");
	byte[] signatureBytes = mac.doFinal(dataBytes);
	String signature = new String(Base64.getEncoder().encodeToString(signatureBytes));
		System.out.println(signature);


	}

}
