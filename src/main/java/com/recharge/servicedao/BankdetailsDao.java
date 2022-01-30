package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Bankdetails;

public interface BankdetailsDao {
	public List<Bankdetails> getAllBankdetails();

	public Bankdetails getBankdetailsById(Integer id);

	public boolean insertBankdetails(Bankdetails bankdetails);

	public boolean updateBankdetails(Bankdetails bankdetails);

	public boolean deleteBankdetails(Integer id);

	public List<Bankdetails> getBankdetailsByNamedQuery(String query, Map<String, Object> param);
}
