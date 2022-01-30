package com.recharge.businessdao;

import java.util.Map;

public interface AndroidService {
	public Map<String, Object> androidLogin(String requestJson, String ip);

	public Map<String, Object> tokenizer(String deviceId);

	public Map<String, Object> editProfile(String requestJson);

	public Map<String, Object> changePassword(String requestJson);

	public Map<String, Object> myDiscount(String request);

	public Map<String, Object> balanceRequest(String request);

	public Map<String, Object> rechargeReport(String request);

	public Map<String, Object> transactionReport(String request);

	public Map<String, Object> purchaseReport(String request);
	
	public Map<String, Object> getSettlementBalanceAndroid(String requestJson);
	
	public Map<String, Object> settlementReqAndroid(Map<String, Object> requestJson);
	
	public Map<String, Object> forgetPassword(String request);

	public Map<String, Object> sendRechargeRequst(String request, String ip);

	public Map<String, Object> addComplain(String request);

	public Map<String, Object> viewComplain(String request);

	public Map<String, Object> getOperator(String request);

	public Map<String, Object> addUser(String request);

	public Map<String, Object> viewUser(String request);

	public Map<String, Object> addBalance(String request);

	public Map<String, Object> viewBalanceTransfer(String request);

	public Map<String, Object> utilityBillPayment(String request, String ip);

	public Map<String, Object> utilityReport(String request);

	public Map<String, Object> doopmecheckRemitter(String requestJson);

	public Map<String, Object> doopmeRgisterRemitter(String requestJson);

	public Map<String, Object> doopmeNewBeneficiary(String requestJson);

	public Map<String, Object> doopmeVerifyBeneficiary(String requestJson);

	public Map<String, Object> doopmeViewBeneficiary(String requestJson);

	public Map<String, Object> doopmeMoneyTransfer(String requestJson);

	public Map<String, Object> agencyrequest(String requestJson);

	public Map<String, Object> Couponrequest(String requestJson);
	
	//aeps
	
    public Map<String, Object> aepsAndroid(Map<String, Object> requestJson);
	
	public Map<String, Object> aepsReportAndroid(Map<String, Object> requestJson);
	
	public Map<String, Object> getAEPSBankDetail(String requestJson);

	public Map<String, Object> addRblBankAndroid(Map<String, Object> requestJson);

	public Map<String, Object> getSettlementReportAndroid(Map<String, Object> requestJson);

	public Map<String, Object> fetchbank(String requestJson);

	public Map<String, Object> advancedSearchUser(String requestJson);

	public Map<String, Object> advancedCustomerNo(String requestJson);

	public Map<String, Object> viewRblBankAndroid(Map<String, Object> request);

	public Map<String, Object> appgetImpsReport(String requestJson);

	public Map<String, Object> checkBalance(Map<String, Object> requestJson);

	public Map<String, Object> p2aregisterAndroid(String request);

	public Map<String, Object> p2amoneytransferAndroid(String request);

	public Map<String, Object> getp2aReportAndroid(String request);

	public Map<String, Object> checkP2AUser(String requestJson);

	public Map<String, Object> encoreaepstransactionAndroid(String request);

	public Map<String, Object> encoreaadhartransactionAndroid(String request);

	public Map<String, Object> checkuserEncoreNewAndroid(String request);

	public Map<String, Object> remmiterRegisterAndroid(String request);

	public Map<String, Object> OtpverifyAndroid(String request);

	public Map<String, Object> addBeneficiaryAndroid(String request);

	public Map<String, Object> deleteBeneficiaryAndroid(String request);

	public Map<String, Object> ValidateBeneficiaryAndroid(String request);

	public Map<String, Object> MoneyTransferAndroid(String request);

	public Map<String, Object> ResendOtpAndroid(String request);

	public Map<String, Object> minitransactionNewAndroid(String request);
	
	
}
