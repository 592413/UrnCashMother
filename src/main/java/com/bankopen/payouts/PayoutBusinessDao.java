package com.bankopen.payouts;

import java.util.List;
import java.util.Map;

import com.bankopen.model.OpenPayout;
import com.recharge.model.User;

public interface PayoutBusinessDao {

	public Map<String, Object> initiatePayout(Map<String, String> request, User userDetails);

	public Map<String, Object> payoutOtp( User userDetails);

	List<OpenPayout> getPayoutReport(Map<String, String> request, User userDetails);

	Map<String, Object> getPayoutStatus(Map<String, String> request, User userDetails);

	//Map<String, Object> initiatedepositePayout(Map<String, String> request, User userDetails);

	
	public Map<String, Object> MoneytransferOPEN(Map<String, String> request, User userDetails);
}
