package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Role;

public interface RoleDao {
	public List<Role> getAllRole();

	public Role getRoleByroleId(Integer roleId);

	public Integer insertRoleId(Role role);

	public boolean updateRoleId(Role role);

	public void deleteRoleId(Integer roleId);

	public List<Role> getRoleByNamedQuery(String query, Map<String, Object> param);
}
