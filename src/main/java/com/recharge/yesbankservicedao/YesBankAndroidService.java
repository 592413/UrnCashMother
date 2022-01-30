package com.recharge.yesbankservicedao;

import java.util.Map;

public interface YesBankAndroidService {

	//YesBank
	public Map<String, Object> yesBankTransactionAndroid(String requestJson);
	
	public Map<String, Object> yesBanksearchAndroid(String requestJson);
	
	public Map<String, Object> yesBankRDHashAndroid(String requestJson);
	
	public Map<String, Object> yesBankregisterAndroid(String requestJson);
	
	public Map<String, Object> yesBankrddatahashAndroid(String requestJson);
	
	public Map<String, Object> getYesBankAEPSReportAndroid(String requestJson);
	
	public Map<String, Object> checkuserYesBankAndroid(String requestJson);
	
	public Map<String, Object> yesBankdmrregisterAndroid(String requestJson);
	
	public Map<String, Object> yesBankremitterverifyAndroid(String requestJson);
	
	public Map<String, Object> yesBankdeletebeneAndroid(String requestJson);
	
	public Map<String, Object> VerifyDeleteYesBaneAndroid(String requestJson);
	
	public Map<String, Object> addYesBaneAndroid(String requestJson);
	
	public Map<String, Object> verifyYesBaneAndroid(String requestJson);
	
	public Map<String, Object> YesmoneytransferAndroid(String requestJson);

	public Map<String, Object> fetchagentcode(String requestJson);

	public Map<String, Object> sendaepsreq(String requestJson);

	public Map<String, Object> miniStatementAndroid(String request);

	public Map<String, Object> getMicroAEPSReportAndroid(String request);

	public Map<String, Object> getFingerAEPSReportAndroid(String request);

	public Map<String, Object> getMicroAEPSReportAndroidnew(String request);

	

}
