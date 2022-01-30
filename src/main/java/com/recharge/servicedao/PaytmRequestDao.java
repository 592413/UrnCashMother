package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.PaytmRequest;

public interface PaytmRequestDao {

	public boolean insertPaytmRequest(PaytmRequest PaytmRequest);

	public boolean updatePaytmRequest(PaytmRequest PaytmRequest);

	public void deletePaytmRequest(int id);

	public List<PaytmRequest> getPaytmRequestByNamedQuery(String query, Map<String, Object> param);
}
