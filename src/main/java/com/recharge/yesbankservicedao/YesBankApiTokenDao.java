package com.recharge.yesbankservicedao;

import java.util.List;
import java.util.Map;

import com.recharge.yesbankmodel.YesBankApiToken;



public interface YesBankApiTokenDao {
	
	public List<YesBankApiToken> getYesBankApiToken();

	public YesBankApiToken getYesBankApiTokenById(String id);

	public boolean insertYesBankApiToken(YesBankApiToken YesBankApiToken);

	public boolean updateYesBankApiToken(YesBankApiToken YesBankApiToken);
	
}
