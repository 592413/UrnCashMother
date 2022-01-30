package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.User;
import com.recharge.model.UserBankDetails;

public interface UserBankDetailsDao {

	public List<UserBankDetails> getAllUserBankDetails();

	public UserBankDetails getUserByUserBankDetails(int id);

	public boolean insertUserBankDetails(UserBankDetails UserBankDetails);

	public boolean updateUserBankDetails(UserBankDetails UserBankDetails);

	public void deleteUserBankDetails(int id);

	public List<UserBankDetails> getUserBankDetailsByNamedQuery(String query, Map<String, Object> param);

	public boolean updateUserBankDetails(User user);


}
