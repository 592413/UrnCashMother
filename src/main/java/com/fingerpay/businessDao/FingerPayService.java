package com.fingerpay.businessDao;

import java.util.Map;

import com.recharge.model.User;

public interface FingerPayService {
	public Map<String, Object> encorepayaeps(Map<String, String> request,User user);
	
	
	public Map<String, Object> encorepayaadhar(Map<String, String> request,User user);
	
	public Map<String, Object> encorepayaepseb(Map<String, String> request,User user);

	public Map<String, Object> encorepayaadharweb(Map<String, String> request, User user);


	public Map<String, Object> getICICIBankAEPSReport(Map<String, String> request, User userDetails);


	public Map<String, Object> ministatement(Map<String, String> request, User user);


	public Map<String, Object> ministatementandroid(Map<String, String> inputData, User userDetails);
}
