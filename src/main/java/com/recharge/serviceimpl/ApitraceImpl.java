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

import com.recharge.model.Api;
import com.recharge.model.Apisetting;
import com.recharge.model.Apitrace;
import com.recharge.servicedao.ApitraceDao;

@Repository("apitraceDao")
public class ApitraceImpl implements ApitraceDao{
	private static final Logger logger_log = Logger.getLogger(ApitraceDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Apitrace> getAllApitrace() {
		List<Apitrace> list = new ArrayList<Apitrace>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Apitrace.class).list();
			String message = "Method : getAllApitrace() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllApitrace Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Apitrace getApitraceById(String id) {
		Session session = sessionFactory.openSession();
		Apitrace apitrace = new Apitrace();
		try {
			apitrace = session.get(Apitrace.class, id);
			String message = "Method : getApitraceById(id) || userId: " + apitrace.getId();
			logger_log.warn(message);
		} catch (Exception e) {
			apitrace = null;
			String message = "getApitraceById(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return apitrace;
	}

	@Override
	public boolean insertApitrace(Apitrace apitrace) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(apitrace);
			String message = "QUERY : Save insertApitrace|| ID: " + apitrace.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertApitrace(apitrace) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateApitrace(Apitrace apitrace) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(apitrace);
			String message = "QUERY : updateApitrace(apitrace) || ID: " + apitrace.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateApitrace(apitrace)  Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteApitrace(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Apitrace> getApitraceByNamedQuery(String query, Map<String, Object> param) {
		List<Apitrace> list = new ArrayList<Apitrace>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getApitraceByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getApitraceByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
