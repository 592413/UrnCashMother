package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.SmsCredential;

public interface SmsCredentialDao {
	public List<SmsCredential> getAllSmsCredential();

	public SmsCredential getSmsCredentialById(Integer id);

	public boolean insertSmsCredential(SmsCredential credential);

	public boolean updateSmsCredential(SmsCredential credential);

	public void deleteSmsCredential(Integer id);

	public List<SmsCredential> getSmsCredentialByNamedQuery(String query, Map<String, Object> param);
}
