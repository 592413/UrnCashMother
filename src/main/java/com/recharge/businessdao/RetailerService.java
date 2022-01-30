package com.recharge.businessdao;

import java.text.ParseException;
import java.util.Map;

import com.recharge.model.User;

public interface RetailerService {
	
	public Map<String, Object> webRecharge(Map<String, String> request, User userDetails);
	public Map<String, Object> checkApiUser(Map<String, String> request);
	public Map<String, Object> settlerequest(Map<String, String> request, User userDetails);
	public Map<String, Object> addRBLBank(Map<String, String> request, User userDetails);
	public Map<String, Object> getResponseUrl(User userDetails);
	public Map<String, Object> customholder(Map<String, String> request,User userDetails);
	public Map<String, Object> mycardlist(User userDetails);
	public Map<String, Object> updateResponseUrl(Map<String, String> request, User userDetails);
	public Map<String, Object> getComplainDetails(Map<String, String> request, User userDetails);
	public Map<String, Object> updateRechargeComplains(Map<String, String> request, User userDetails);
	public Map<String, Object> balanceRequest(Map<String, String> request, User userDetails);
	public Map<String, Object> addComplains(Map<String, String> request, User userDetails);
	public Map<String, Object> utilityBillPayment(Map<String, String> request, User userDetails);
	public Map<String, Object> insurancePremiumPayment(Map<String, String> request, User userDetails);
	public Map<String, Object> agencyrequest(Map<String, String> request, User userDetails);
	public Map<String, Object> couponrequest(Map<String, String> request, User userDetails);
	public Map<String, Object> nsdlPanadd(Map<String, String> request, User userDetails) throws ParseException;
	public Map<String, Object> nsdlEdit(Map<String, String> request, byte[] bytesImage, byte[] bytesSignature,
			byte[] bytesFormFont, byte[] bytesFormBack, byte[] bytesProofOfIdentity);
	public Map<String, Object> getNSDLASKDetails(Map<String, String> request);
	public Map<String, Object> getapplication(Map<String, String> request, User userDetails);
	public Map<String, Object> editnsdlpandt(Map<String, String> request);
	public Map<String, Object> billFetch(Map<String, String> request, User userDetails);
	public Map<String, Object> eBillPaymentPostPaid(Map<String, String> request, User userDetails);
	public Map<String, Object> payUWallet(Map<String, String> request, User userDetails);
	

}
