package com.recharge.icicidmtservicce;

import java.util.Map;

import com.recharge.model.User;

public interface RazorpayDMTService {
	
	public Map<String, Object> checkuserRazorpayNew(Map<String, String> request);
	
	public Map<String, Object> remmiterRegisterRazorpayNew(Map<String, String> request); 
	
	public Map<String, Object> otpVerifyRazorpay(Map<String, String> request);
	
	public Map<String, Object> addBeneficiaryRazorPay(Map<String, String> request);
	
	public Map<String, Object> moneyTransferRazorpay(Map<String, String> request, User userDetails);
	
	public Map<String, Object> validatemoneyTransferRazorpay(Map<String, String> request, User userDetails);
	
	public Map<String, Object> deleteBeneficiaryrazorpay(Map<String, String> request);

}
