package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.IRCTCUser;



public interface IRCTCUserDao {
	



	public List<IRCTCUser> getAllIRCTCUser();

	public IRCTCUser getIRCTCUserMapById(String id);

	public boolean insertIRCTCUser(IRCTCUser IRCTCUser);

	public boolean updateIRCTCUser(IRCTCUser IRCTCUser);

	public void deleteIRCTCUser(int id);

	public List<IRCTCUser> getIRCTCUserByNamedQuery(String query, Map<String, Object> param);






}
