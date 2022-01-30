package com.recharge.servicedao;

import java.util.List;

import com.recharge.model.OpenAccount;

public interface OpenAccountDao {

	boolean insertOpenAccount(OpenAccount OpenAccount);

	boolean deleteOpenAccount(int id);

	List<OpenAccount> getAllOpenAccount();

}
