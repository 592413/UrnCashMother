package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.IRCTCLog;



public interface IRCTCLogDao {

	public boolean insertIRCTCLog(IRCTCLog IRCTCLog);

	public boolean updateIRCTCLog(IRCTCLog IRCTCLog);

	public List<IRCTCLog> getIRCTCLogByNamedQuery(String query, Map<String, Object> param);

	public IRCTCLog getIRCTCLogById(int Id);





}
