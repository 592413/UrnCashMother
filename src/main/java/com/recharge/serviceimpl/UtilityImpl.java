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

import com.recharge.model.Balancerequest;
import com.recharge.model.User;
import com.recharge.model.Utility;
import com.recharge.servicedao.UtilityDao;

@Repository("utilityDao")
public class UtilityImpl implements UtilityDao {
	
	private static final Logger logger_log = Logger.getLogger(UtilityDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Utility> getAllUtility() {
		List<Utility> list = new ArrayList<Utility>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Utility.class).list();
			
		} catch (Exception e) {
			String message = "getAllUtility Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public User getUtilityByUtilityId(Integer utilityId) {
		Session session = sessionFactory.openSession();
		User user = new User();
		try {
			user = session.get(User.class, utilityId);
			

		} catch (Exception e) {
			user = null;
			String message = "getUtilityByUtilityId(utilityId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public boolean insertUtility(Utility utility) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(utility);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			String message = "insertUtility(Utility) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateUtility(Utility utility) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(utility);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteUtility(Integer utilityId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Utility> getUtilityByNamedQuery(String query, Map<String, Object> param) {
		List<Utility> list = new ArrayList<Utility>();
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
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
