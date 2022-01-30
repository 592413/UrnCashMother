package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.ImpsTransaction;

public interface ImpsTransactionService {
	public boolean insertImpsTransaction(ImpsTransaction imptrans);
	
	public boolean deleteImpsTransaction(ImpsTransaction imptrans);
	public boolean updateImpsTransaction(ImpsTransaction imptrans);
	public boolean updateDmrTransactionBankTransactionId(String tranId, String bankTransactionId);
	public boolean updateDmrTransactionStatus(String tranId,String status);
	public ImpsTransaction getimpsdetailsById(String banktransactionId);
	public List<ImpsTransaction> getIMPSDetailsByNamedQuery(String query, Map<String, Object> param);
	

}
