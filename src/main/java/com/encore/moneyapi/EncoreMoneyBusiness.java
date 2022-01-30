package com.encore.moneyapi;

import java.util.Map;

import com.recharge.model.User;

public interface EncoreMoneyBusiness {
	Map<String, Object> checkuserEncore(Map<String, Object> request);
	
	Map<String, Object> remmiterRegisterEncore(Map<String, Object> request);
	
	Map<String, Object> addBeneficiaryEncore(Map<String, Object> request);
	           
	
	Map<String, Object> deleteBeneficiaryEncore(Map<String, Object> request);
	
	Map<String, Object> ValidateBeneficiaryEncore(Map<String,Object> request,User user);
	
	Map<String, Object> impsMoneyTransferEncore(Map<String, String> request,User user);

	Map<String, Object> impsMoneyTransferEncoreandroid(Map<String, Object> request, User userDetails);
}
