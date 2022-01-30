package com.recharge.icicidmtservicce;

import java.util.List;
import java.util.Map;

import com.recharge.customModel.P2ATranferReport;
import com.recharge.model.User;

public interface ICICIDmtService {
	public Map<String, Object> p2amoneytransfer(Map<String, String> request, User userDetails);
	public Map<String, Object> p2amoneytransferNew(Map<String, String> request, User userDetails);
	public Map<String, Object> p2aregistration(Map<String, String> request, User userDetails);
	public List<P2ATranferReport> getp2aReport(Map<String, String> request, User userDetails);
	public Map<String, Object> getP2AViewUser(User userDetails);
	public Map<String, Object> changeP2AStatus(Map<String, String> request, User userDetails);
	public Map<String, Object> checkP2AUser(User userDetails);
	public Map<String, Object> updatep2astatus(Map<String, String> request, User userDetails);
	Map<String, Object> checkuserOPEN(Map<String, String> request, User userDetails);
	Map<String, Object> RemitterRegisterOPEN(Map<String, String> request, User userDetails);
	Map<String, Object> otpverifyOPEN(Map<String, String> request, User userDetails);
	Map<String, Object> BeneficiaryRegistrationOPEN(Map<String, String> request, User userDetails);
	Map<String, Object> validateBeneOPEN(Map<String, String> request, User userDetails);
	Map<String, Object> deletebeneOPEN(Map<String, String> request, User userDetails);
	Map<String, Object> MoneytransferOPEN(Map<String, String> request, User userDetails);
	Map<String, Object> p2amoneytransferPaytm(Map<String, String> request, User userDetails);
	public Map<String, Object> getPayoutStatuscheck(Map<String, String> request, User userDetails);
	Map<String, Object> getPayoutStatuscheckdmt(Map<String, String> request, User userDetails);
	public Map<String, Object> BeneficiaryRegistrationRazorpay(Map<String, String> request, User userDetails);
	
	public Map<String, Object> MoneytransferRazorPay(Map<String, String> request, User userDetails);
	
	public Map<String, Object> razorPayStatusWebhook(String request);
	
}
