package com.recharge.instantpay;

import java.util.Map;

import com.recharge.model.User;

public interface InstantPayService {

	public Map<String, Object> checkuserInstantPay(Map<String, String> request, User userDetails);
	
	public Map<String, Object> instantPayRemitterRegister(Map<String, String> request);	
	
	public Map<String, Object> instantPayMoneytransfer(Map<String, String> request, User userDetails);
	
	public Map<String, Object> instantNewBeneficiary(Map<String, String> request);	
	
	public Map<String, Object> instantpayValidateBeneficiary(Map<String, String> request, User userDetails);
	
	public Map<String, Object> deleteinstantbene(Map<String, String> request);
	
	
	public Map<String, Object> instantPayDeleteBeneficiaryFinal(Map<String, String> request);
	
	public Map<String, Object> instantNewBenefinal(Map<String, String> request);
	
	public Map<String, Object> instantPayMoneytransferAndroid(Map<String,String> request, User userDetails);
	
	public Map<String, Object> viewinstantpaybene();
	
	
	
	public Map<String, Object> eBillBBPsPaymentFirst(Map<String, String> request, User userDetails);
	public Map<String, Object> eBillBBPsPaymentFinal(Map<String, String> request, User userDetails);
	public Map<String, Object> eBillBBPsPaymentFirstInsurance(Map<String, String> request, User userDetails);

	public Map<String, Object> instantPayRemitterRegisterValidate(Map<String, String> request);

	public String instantPayAEPS(User userDetails);
}
