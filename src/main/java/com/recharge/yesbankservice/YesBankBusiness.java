package com.recharge.yesbankservice;

import java.util.Map;

import com.recharge.model.User;

public interface YesBankBusiness {
	public Map<String, Object> getyesbankdetails();
	
	public Map<String, Object> Customerregister(Map<String, Object> request,User user);
	
	public Map<String, Object> searchCustomer(Map<String, Object> request,User user);
	
	
	public Map<String, Object> deleteyesBankbeneAndroid(Map<String, Object> request,User user);
	
	public Map<String, Object> remitterverifyAndroid(Map<String, Object> request,User user);
	
	public Map<String, Object> checkuserYesBank(Map<String, Object> request,User user);
	
	public Map<String, Object> yesBankRemitterRegister(Map<String, Object> request,User user);
	
	public Map<String, Object> yesBankNewBeneficiary(Map<String, Object> request,User user);
	
	public Map<String, Object> deleteyesBankbene(Map<String, Object> request,User user);
	
	public Map<String, Object> VerifyDeleteYesBane(Map<String, Object> request,User user);
	
	public Map<String, Object> VerifyDeleteYesBaneAndroid(Map<String, Object> request,User user);
	
	public Map<String, Object> addYesBaneAndroid(Map<String, Object> request,User user);
	
	public Map<String, Object> verifyYesBaneAndroid(Map<String, Object> request,User user);
	
	public Map<String, Object> yesBankMoneytransfer(Map<String, Object> request,User user);
	
	public Map<String, Object> checkImpsStatus(Map<String, Object> request,User user);
	
	public Map<String, Object> REFUNDImpsStatus(Map<String, Object> request,User user);
	
	public Map<String, Object> refundOTP(Map<String, Object> request,User user);
	
	public Map<String, Object> YesmoneytransferAndroid(Map<String, Object> request,User user);
	
	public Map<String, Object> yesBankValidateBeneficiary(Map<String, Object> request,User user);
	
	public Map<String, Object> yesBankVerifyBeneficiary(Map<String, Object> request,User user);
	
	public Map<String, Object> remitterverify(Map<String, Object> request,User user);
	
	public Map<String, Object> verifyremitter(Map<String, Object> request,User user);
	
	public Map<String, Object> viewYesBankbene();
	
	public Map<String, Object> getYesBankAEPSReport(Map<String, String> request,User user);
	
	public Map<String, Object> getMicroATMReport(Map<String, String> request,User user);
	
	public Map<String, Object> getFingerATMReport(Map<String, String> request,User user);
	
	public Map<String, Object> yesBankTransaction(Map<String, Object> request,User user);
	
	public Map<String, Object> getDeviceByDeviceType(Map<String, Object> request,User user);
	
	public Map<String, Object> getRDHashAndroid(Map<String, Object> request,User user);
	
	public Map<String, Object> yesBankTransAndroid(Map<String, String> request,User user);
	
	public Map<String, Object> yesBankpidAndroid(Map<String, String> request,User user);

	public String aepstransaction(Map<String, Object> request, User user);

	public Map<String, Object> aepsstatuscheck(Map<String, Object> request);

	public Map<String, Object> miniStatementAndroid(Map<String, String> inputData, User userDetails);

	public Map<String, Object> getMicroATMReportNew(Map<String, String> inputData, User userDetails);
}
