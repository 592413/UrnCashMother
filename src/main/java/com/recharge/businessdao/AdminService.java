package com.recharge.businessdao;

import java.util.List;
import java.util.Map;


import com.recharge.model.Operator;
import com.recharge.model.User;
import com.recharge.model.webenquery;

public interface AdminService {
	public Map<String, Object> updateNews(Map<String, String> request, User userDetails);
	
	
	
	
	public Map<String, Object> addApiParameters(Map<String, Object> request);
	
	public Map<String, Object> addApi(Map<String, String> request);
	
	public Map<String, Object> getSettlementBalance(User userDetails);
	
	public Map<String, Object> addsmsApi(Map<String, String> request,User userDetails);

	public Map<String, Object> insertBankDetails(Map<String, String> request, User userDetails);

	public Map<String, Object> signInByAdmin(Map<String, String> request, User userDetails);

	public Map<String, Object> sendPassword(Map<String, String> request, User userDetails);

	public Map<String, Object> deleteUser(Map<String, String> request, User userDetails);

	public Map<String, Object> deleteBankDetails(Map<String, String> request, User userDetails);

	public Map<String, Object> revertUserBalance(Map<String, String> request, User userDetails);

	public Map<String, Object> apiSwitching(Operator[] request, User userDetails);

	public Map<String, Object> userMapping(Map<String, String> request, User userDetails);

	public Map<String, Object> setUserLockedAmount(Map<String, String> request, User userDetails);

	public Map<String, Object> getUserDetailsForEdit(Map<String, String> request, User userDetails);

	public Map<String, Object> addReseller(Map<String, String> request, User userDetails, byte[] bytes);

	public Map<String, Object> setResellerTheme(Map<String, String> request, User userDetails);

	public Map<String, Object> getResellerDetails(Map<String, String> request, User userDetails);

	public Map<String, Object> setResellerLogo(Map<String, String> request, User userDetails, byte[] bytes);

	public List<webenquery> viewWebEnquery(Map<String, String> request, User userDetails);

	public Map<String, Object> addOperator(Map<String, String> request);

	public Map<String, Object> fetchDomain();

	public Map<String, Object> addIndex(Map<String, String> request, byte[] bytes);

	public Map<String, Object> updateImpsReport(Map<String, String> request, User user);

	public Map<String, Object> agentstatusupdate(Map<String, String> request, User user);

	public Map<String, Object> couponstatusupdate(Map<String, String> request, User user);


	public Map<String, Object> getNSDLAttachmentDetails(Map<String, String> request);
	
	public Map<String, Object> aepsSettleToBank(Map<String, String> request);
	
	public Map<String, Object> aepsUseradd(Map<String, String> request);
	
	public Map<String, Object> getAEPSViewUser(User user);


	public Map<String, Object> changeStatus(Map<String, String> request, User userDetails);


	public Map<String, Object> searchackno(Map<String, String> request, User userDetails);


	public Map<String, Object> fetchnonpkguser(User userDetails);

	public Map<String, Object> getaepslogreport(Map<String, String> request, User userDetails);
	public Map<String, Object> getP2AViewUser();




	public Map<String, Object> addRailUser(Map<String, String> request);




	public Map<String, Object> addECommerceUser(Map<String, String> request);




	public Map<String, Object> updatebankt(Map<String, String> request, User userDetails);




	public Map<String, Object> updateResellerdetails(Map<String, String> request, User userDetails);

	/*public Map<String, Object> getApiRefillTransaction(Map<String, String> request, User userDetails); */
}
