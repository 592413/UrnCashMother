package com.recharge.businessdao;

import java.util.Map;

import com.google.gson.internal.LinkedTreeMap;
import com.recharge.model.PaymonkResponse;
import com.recharge.model.Rechargedetails;
import com.recharge.model.User;

public interface ApiResponseService {
	public Map<String, Object> wellbornapires(Map<String, String> request);

	public Map<String, Object> doopMeApiResponse(Map<String, String> request);

	public Map<String, Object> vastwebindiaresponse(Map<String, String> request);

	public Map<String, Object> payaepsresponse(PaymonkResponse  paymonk);
	
	 public Map<String, Object> aepsstatuscheckandroid(Map<String, String>  request);
		
	 public PaymonkResponse aepsstatusCheck(String response);
	 
	 public Map<String, Object> instantpayrechargeres(Rechargedetails rechargedetail,User userDetails);

	public Map<String, Object> CrowdfinchApiResponse(Map<String, String> request);

	public Map<String, Object> EasyPayApiResponse(Map<String, String> request);

	public Map<String, Object> cellmoneyresponse(Map<String, String> request);

	public Map<String, Object> instantpayresponse(Map<String, String> request);

	public Map<String, Object> yesbankaepsApiResponse(LinkedTreeMap<String, Object> request);

	public Map<String, Object> doopMeApiResponsenew(Map<String, String> request);

	public Map<String, Object> giblDeductBalanceResponse(Map<String, String> request);

	public Map<String, Object> giblUpdateStatusResponse(Map<String, String> request);

	public Map<String, Object> microatmrequest(Map<String, String> request);

	public Map<String, Object> microatmresponse(Map<String, String> request);

	public Map<String, Object> paytmresponse(Map<String, String> request);

	public Map<String, Object> checkIRCTCBalance(Map<String, String> request);

	public Map<String, Object> IRCTCResponse(Map<String, String> request);

	public Map<String, Object> instantpayAEPSFinalRes(Map<String, String> request);

	public Map<String, Object> instantpayAEPS(Map<String, Object> request);

	public Map<String, Object> instantpayAEPSBalance(Map<String, Object> request);

	public Map<String, Object> backup();

	public Map<String, Object> arucheckbalance(Map<String, String> request);

	public Map<String, Object> aruresponse(Map<String, String> request);

	public Map<String, Object> IRCTCProcess(Map<String, String> request);

	public Map<String, Object> microatmrequestnew(Map<String, Object> request);

	public Map<String, Object> microatmresponsenew(Map<String, Object> request);

	Map<String, Object> paytmfinalres(Map<String, Object> request);

	Map<String, Object> paytmfinalDMTres(Map<String, Object> request);
	
	
}
