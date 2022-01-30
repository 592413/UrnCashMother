package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Userattachment;

public interface UserattachmentDao {
	public List<Userattachment> getAllUserattachment();

	public Userattachment getUserattachmentById(String Id);

	public boolean insertUserattachment(Userattachment userattachment);

	public boolean updateUserattachment(Userattachment userattachment);

	public void deleteUserattachment(int Id);

	public List<Userattachment> getUserattachmentByNamedQuery(String query, Map<String, Object> param);
}
