package com.recharge.utill;

import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.paytm.pg.merchant.PaytmChecksum;

public class PaytmchecksumNew {

	private static final Logger log = Logger.getLogger(PaytmchecksumNew.class);
	
//LIVE
	private static final String MID="Encore99211820111833";
	private static final String MERCHANT_KEY="V9MLCj6ZZnJhh_Go";
//TEST
	/*private static final String MID = "Encore33060974552301";
	private static final String MERCHANT_KEY = "IZAPU!#R5ANMvzB8";*/

	public static String generateChecksum(String input) {
		String checksum = "";
		String paytmChecksum = "";
		/*System.out.println("orderid::::::::::::::::::" + input);
		System.out.println("MID::::::::::::::::::" + MID);
		System.out.println("MERCHANT_KEY::::::::::::::::::" + MERCHANT_KEY);*/
		
		try {

			boolean verifySignature = false;
			
			paytmChecksum = PaytmChecksum.generateSignature(input,MERCHANT_KEY);
			verifySignature = PaytmChecksum.verifySignature(input, MERCHANT_KEY, paytmChecksum);
			/*System.out.println("generateSignature Returns: " + paytmChecksum);
			System.out.println("verifySignature Returns: " + verifySignature);*/
		} catch (Exception e) {
			log.error("generateChecksum::::::::::::::::::::::" + e);
		}
		// System.out.println("checksum::::::::::::::::::::::"+checksum);
		return paytmChecksum;
	}

}
