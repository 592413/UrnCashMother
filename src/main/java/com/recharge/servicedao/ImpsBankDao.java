package com.recharge.servicedao;
import java.util.List;
import java.util.Map;

import com.recharge.model.AEPSBankDetail;
import com.recharge.model.Imps_BankDetails;
public interface ImpsBankDao {
	public List<Imps_BankDetails> getAllBank();

	public Imps_BankDetails getBankbyId(String bankid);
	
	public List<Imps_BankDetails> getImpsBankDetailsByNamedQuery(String query, Map<String, Object> param);

}
