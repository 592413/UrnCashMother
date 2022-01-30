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
import com.recharge.model.ResponseCounter;
import com.recharge.servicedao.ResponseCounterDao;
@Repository("responseCounterDao")
public class ResponseCounterImpl implements ResponseCounterDao{
	private static final Logger logger_log = Logger.getLogger(ResponseCounterDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public boolean insertResponseCounter(ResponseCounter ResponseCounter) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(ResponseCounter);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertResponseCounterResponseCounter) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}
	
	@Override
	public boolean updateResponseCounter(ResponseCounter ResponseCounter) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(ResponseCounter);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateResponseCounter Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}
	
	@Override
	public List<ResponseCounter> getResponseCounterByNamedQuery(String query, Map<String, Object> param) {
		List<ResponseCounter> list = new ArrayList<ResponseCounter>();
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
			String message = "getResponseCounterByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
