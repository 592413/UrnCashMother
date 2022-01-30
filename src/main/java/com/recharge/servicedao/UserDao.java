package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.User;

public interface UserDao {
	public List<User> getAllUser();

	public User getUserByUserId(String userId);

	public boolean insertUser(User user);

	public boolean updateUser(User user);

	public void deleteUser(int id);

	public List<User> getUserByNamedQuery(String query, Map<String, Object> param);

	public boolean updateUserStatus(User user);
}
