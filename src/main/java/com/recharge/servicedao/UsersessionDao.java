package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.*;

public interface UsersessionDao {
	public List<Usersession> getAllUserSession();

	public User getUserSessionBySessionid(Integer sessionid);

	public Integer insertUserSession(Usersession usersession);

	public boolean updateUserSession(Usersession usersession);

	public void deleteUserSession(Integer sessionid);

	public List<User> getUserSessionByNamedQuery(String query, Map<String, Object> param);
}
