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

import com.recharge.model.Ezpayapplicationform;

import com.recharge.servicedao.ApplicationDao;

@Repository("ApplicationDao")
public class EzpayapplicationformImpl implements ApplicationDao {
	private static final Logger logger_log = Logger.getLogger(ApplicationDao.class);

	private static final Object Ezpayapplicationform = null;
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public List<Ezpayapplicationform> getAllEzpayapplicationform() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ezpayapplicationform getEzpayapplicationformMapById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertEzpayapplicationform(Ezpayapplicationform Ezpayapplicationform) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(Ezpayapplicationform);
			String message = "QUERY : Save insertEzpayapplicationform|| ID: " + Ezpayapplicationform.getApplicationid();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertEzpayapplicationform(Ezpayapplicationform) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateEzpayapplicationform(Ezpayapplicationform Ezpayapplicationform) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(Ezpayapplicationform);
			String message = "QUERY : Save updateEzpayapplicationform|| ID: " + Ezpayapplicationform.getApplicationid();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "updateEzpayapplicationform(Ezpayapplicationform) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}
	@Override
	public void deleteEzpayapplicationform(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Ezpayapplicationform> getEzpayapplicationformByNamedQuery(String query, Map<String, Object> param) {
		List<Ezpayapplicationform> list = new ArrayList<Ezpayapplicationform>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getEzpayapplicationformByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getEzpayapplicationformByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}
	
	

}
