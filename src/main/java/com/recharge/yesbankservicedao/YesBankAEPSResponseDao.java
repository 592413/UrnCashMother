package com.recharge.yesbankservicedao;

import java.util.List;
import java.util.Map;


import com.recharge.yesbankmodel.YesBankAEPSResponse;


public interface YesBankAEPSResponseDao {

	
	public List<YesBankAEPSResponse> getYesBankAEPSResponsen();

	public YesBankAEPSResponse getYesBankAEPSResponseById(String id);

	public boolean insertYesBankAEPSResponse(YesBankAEPSResponse YesBankAEPSResponse);

	public boolean updateYesBankAEPSResponse(YesBankAEPSResponse YesBankAEPSResponse);
	
	public List<YesBankAEPSResponse> getYesBankAEPSResponseByNamedQuery(String query, Map<String, Object> param);
	

}
