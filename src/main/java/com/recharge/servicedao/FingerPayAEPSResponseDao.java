package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.FingerPayAEPSResponse;





public interface FingerPayAEPSResponseDao {


	public List<FingerPayAEPSResponse> getAllFingerPayAEPSResponse();

	public FingerPayAEPSResponse getFingerPayAEPSResponseById(String id);

	public boolean insertFingerPayAEPSResponse(FingerPayAEPSResponse FingerPayAEPSResponse);

	public boolean updateFingerPayAEPSResponse(FingerPayAEPSResponse FingerPayAEPSResponse);

	public void deleteFingerPayAEPSResponse(int id);

	public List<FingerPayAEPSResponse> getFingerPayAEPSResponseByNamedQuery(String query, Map<String, Object> param);



}
