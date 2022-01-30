package com.recharge.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recharge.model.IRCTCLog;
import com.recharge.model.IRCTCUser;
import com.recharge.servicedao.IRCTCLogDao;
import com.recharge.servicedao.IRCTCUserDao;

@Repository("irctclogdao")
public class IRCTCLogImpl implements IRCTCLogDao {
private static final Logger logger_log = Logger.getLogger(IRCTCUserDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean insertIRCTCLog(IRCTCLog IRCTCLog) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(IRCTCLog);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertIRCTCLog(IRCTCLog) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateIRCTCLog(IRCTCLog IRCTCLog) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(IRCTCLog);;
			String message = "QUERY : updateIRCTCLog|| ID: " + IRCTCLog.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			String message = "updateIRCTCLog(IRCTCUser) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public List<IRCTCLog> getIRCTCLogByNamedQuery(String query, Map<String, Object> param) {
		List<IRCTCLog> list = new ArrayList<IRCTCLog>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			
		} catch (Exception e) {
			String message = "getIRCTCLogByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public IRCTCLog getIRCTCLogById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
