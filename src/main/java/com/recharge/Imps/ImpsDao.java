package com.recharge.Imps;

import java.util.Map;

import com.recharge.model.User;

public interface ImpsDao {


	public Map<String, Object> checkuserDoopme(Map<String, String> request, User userDetails);
	
	public Map<String, Object> doopmeRemitterRegister(Map<String, String> request, User userDetails);

	public Map<String, Object> doopmeNewBeneficiary(Map<String, String> request, User userDetails);

	public Map<String, Object> fetchbank();

	public Map<String, Object> doopmeVerifyBeneficiary(Map<String, String> request, User userDetails);

	public Map<String, Object> viewdoopmebene(User userDetails);

	public Map<String, Object> doopmeMoneytransfer(Map<String, String> request, User userDetails);

	public Map<String, Object> appsdoopmeNewBeneficiary(Map<String, String> request, User userDetails);

	public Map<String, Object> appsdoopmeVerifyBeneficiary(Map<String, String> request, User userDetails);

	public Map<String, Object> viewdoopmebeneANDROID(Map<String, String> request, User userDetails);

	public Map<String, Object> apidoopmeMoneytransfer(Map<String, String> request, User userDetails);

	public Map<String, Object> VerifyDeleteBane(Map<String, String> request, User userDetails);

	public Map<String, Object> doopmeValidateBeneficiary(Map<String, String> request, User userDetails);

	public Map<String, Object> deletedoopmebene(Map<String, String> request, User userDetails);


}
