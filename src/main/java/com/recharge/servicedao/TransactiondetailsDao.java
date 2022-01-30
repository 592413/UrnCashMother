package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Transactiondetails;

public interface TransactiondetailsDao {
	public List<Transactiondetails> getAllTransactionDetails();

	public Transactiondetails getTransactionDetailsByTransId(Integer transId);

	public boolean insertTransactionDetails(Transactiondetails transactiondetails);

	public boolean updateTransactionDetails(Transactiondetails transactiondetails);

	public void deleteTransactionDetails(Integer transId);

	public List<Transactiondetails> getTransactionDetailsByNamedQuery(String query, Map<String, Object> param);
}
