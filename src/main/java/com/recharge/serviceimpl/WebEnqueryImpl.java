package com.recharge.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import com.recharge.model.User;
import com.recharge.model.webenquery;
import com.recharge.servicedao.UserDao;
import com.recharge.servicedao.WebEnquryDao;
@Repository("WebEnquryDao")
public class WebEnqueryImpl implements WebEnquryDao{
	private static final Logger logger_log = Logger.getLogger(WebEnquryDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean insertquery(webenquery webenquery) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(webenquery);
			String message = "QUERY : Save User|| ID: " + webenquery.getUserName();
			logger_log.warn(message);
			tx.commit();			
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertUser(webenquery) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public List<webenquery> getUserByNamedQuery(String query, Map<String, Object> param) {
		List<webenquery> list = new ArrayList<webenquery>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			
		if (!param.isEmpty()) {
			for (Map.Entry<String, Object> entry : param.entrySet()) {
				query_q.setParameter(entry.getKey(), entry.getValue());
			}
		}
		list = query_q.list();
			String message = "Method : getUserByNamedQuery() ";
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
