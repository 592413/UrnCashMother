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

import com.recharge.model.Userservice;
import com.recharge.servicedao.UserserviceDao;

public class UserserviceImpl implements UserserviceDao {
	
	private static final Logger logger_log = Logger.getLogger(UserserviceDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Userservice> getAllUserService() {
		List<Userservice> list = new ArrayList<Userservice>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Userservice.class).list();
			String message = "Method : getAllUserService() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllUserService Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Userservice getUserServiceByUserId(String id) {
		Session session = sessionFactory.openSession();
		Userservice userservice = new Userservice();
		try {
			userservice = session.get(Userservice.class, id);
			String message = "Method : getUserServiceByUserId(id) || requestId: "+ userservice.getId();
			logger_log.warn(message);

		} catch (Exception e) {
			userservice = null;
			String message = "getUserServiceByUserId(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return userservice;
	}

	@Override
	public Integer insertUserService(Userservice userservice) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(userservice);
			String message = "QUERY : Save Userservice|| ID: " + userservice.getId();
			logger_log.warn(message);
			tx.commit();
		} catch (Exception e) {
			String message = "insertUserService(Userservice) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return userservice.getId();
	}

	@Override
	public boolean updateUserService(Userservice userservice) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(userservice);
			String message = "QUERY : updateUserService || ID: " + userservice.getId();
			logger_log.warn(message);
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
	public void deleteUserService(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Userservice> getUserServiceByNamedQuery(String query, Map<String, Object> param) {
		List<Userservice> list = new ArrayList<Userservice>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getUserServiceByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
