package com.encorenew.moneyapi;

import java.util.Map;

import com.recharge.model.User;

public interface EncoreMoneyBusinessNew {
	
Map<String, Object> checkuserEncoreNew(Map<String, String> request);
	
	Map<String, Object> remmiterRegisterEncoreNew(Map<String, String> request);
	
	Map<String, Object> addBeneficiaryEncoreNew(Map<String, String> request);
	           
	
	Map<String, Object> deleteBeneficiaryEncoreNew(Map<String, String> request);
	
	Map<String, Object> ValidateBeneficiaryEncoreNew(Map<String,String> request,User user);
	
	Map<String, Object> impsMoneyTransferEncoreNew(Map<String, String> request,User user);

	Map<String, Object> impsMoneyTransferEncoreandroid(Map<String, String> request, User userDetails);

	Map<String, Object> OTPVERIFICATION(Map<String, String> request);

	Map<String, Object> resendOtp(Map<String, String> request);

}
