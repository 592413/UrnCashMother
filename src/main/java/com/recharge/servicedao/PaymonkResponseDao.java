package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.PaymonkResponse;





public interface PaymonkResponseDao {

	public List<PaymonkResponse> getAllPaymonkResponse();

	public PaymonkResponse getPaymonkResponseById(String id);

	public boolean insertPaymonkResponse(PaymonkResponse PaymonkResponse);

	public boolean updatePaymonkResponse(PaymonkResponse PaymonkResponse);

	public void deletePaymonkResponse(int id);

	public List<PaymonkResponse> getPaymonkResponseByNamedQuery(String query, Map<String, Object> param);

}
