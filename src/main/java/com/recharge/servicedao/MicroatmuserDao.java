package com.recharge.servicedao;

import java.util.List;

import java.util.Map;

import com.recharge.model.MicroatmUser;

public interface MicroatmuserDao {
	public boolean insertMicroatmUser(MicroatmUser MicroatmUser);

	public boolean updateMicroatmUser(MicroatmUser MicroatmUser);

	public List<MicroatmUser> getMicroatmUserByNamedQuery(String query, Map<String, Object> param);

	public MicroatmUser getMicroatmUserById(int Id);

}
