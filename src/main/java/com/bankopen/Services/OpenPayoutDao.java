package com.bankopen.Services;

import java.util.List;
import java.util.Map;

import com.bankopen.model.OpenPayout;

public interface OpenPayoutDao {

	public OpenPayout getOpenPayoutByApId(int Id);

	public boolean insertOpenPayout(OpenPayout OpenPayout);

	public boolean updateOpenPayout(OpenPayout OpenPayout);

	public List<OpenPayout> getOpenPayoutByNamedQuery(String query, Map<String, Object> param);

}
