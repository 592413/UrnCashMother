package com.recharge.icicidmtserviceDao;

import java.util.List;
import java.util.Map;

import com.recharge.icicidmtmodel.Beneficiary;

public interface BeneficiaryDao {



	public List<Beneficiary> getAllBeneficiary();

	public Beneficiary getBeneficiaryById(int id);

	public boolean insertBeneficiary(Beneficiary Beneficiary);

	public boolean updateBeneficiary(Beneficiary Beneficiary);

	public boolean deleteBeneficiary(Beneficiary Beneficiary);

	public List<Beneficiary> getBeneficiaryByNamedQuery(String query, Map<String, Object> param);




	

}
