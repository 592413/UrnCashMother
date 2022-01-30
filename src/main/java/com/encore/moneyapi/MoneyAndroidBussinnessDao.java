package com.encore.moneyapi;

import java.util.Map;

public interface MoneyAndroidBussinnessDao {

	public Map<String, Object> checkuserPaytmAndroid(String requestJson);

	public Map<String, Object> PaytmRemitterRegisterAndroid(String requestJson);

	public Map<String, Object> generatePaytmotp(String requestJson);

	public Map<String, Object> PaytmremitterverifyAndroid(String requestJson);

	public Map<String, Object> PaytmNewBeneficiaryAndroid(String requestJson);

	public Map<String, Object> PaytmValidateBeneficiaryAndroid(String requestJson);

	public Map<String, Object> PaytmVerifyBeneficiaryAndroid(String requestJson);

	public Map<String, Object> deletePaytmbeneAndroid(String requestJson);

	public Map<String, Object> VerifyDeletePaytmBaneAndroid(String requestJson);

	public Map<String, Object> PaytmMoneytransferAndroid(String requestJson);

	public Map<String, Object> checkImpsStatusPandroid(String requestJson);

}
