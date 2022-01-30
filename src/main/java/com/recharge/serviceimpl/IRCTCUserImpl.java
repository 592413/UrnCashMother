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

import com.recharge.model.AEPSUserMap;
import com.recharge.model.IRCTCUser;
import com.recharge.servicedao.AEPSUserMapDao;
import com.recharge.servicedao.IRCTCUserDao;

@Repository("irctcuserdao")
public class IRCTCUserImpl implements IRCTCUserDao {
	private static final Logger logger_log = Logger.getLogger(IRCTCUserDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<IRCTCUser> getAllIRCTCUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRCTCUser getIRCTCUserMapById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertIRCTCUser(IRCTCUser IRCTCUser) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(IRCTCUser);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertAEPSUserMap(AEPSUserMap) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateIRCTCUser(IRCTCUser IRCTCUser) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(IRCTCUser);;
			String message = "QUERY : Save IRCTCUser|| ID: " + IRCTCUser.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			String message = "updateIRCTCUser(IRCTCUser) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteIRCTCUser(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<IRCTCUser> getIRCTCUserByNamedQuery(String query, Map<String, Object> param) {
		List<IRCTCUser> list = new ArrayList<IRCTCUser>();
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
			String message = "getIRCTCUserByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
