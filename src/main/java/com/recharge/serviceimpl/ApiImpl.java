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
import com.recharge.model.User;
import com.recharge.servicedao.ApiDao;
import com.recharge.servicedao.UserDao;

/**
 * ApiImpl is the class for the CRUD operation in api Table
 * 
 * @author Md Rahim
 *
 */

@Repository("apiDao")
public class ApiImpl implements ApiDao {
	private static final Logger logger_log = Logger.getLogger(ApiDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * for getting all row from the table
	 */
	@Override
	public List<Api> getAllApi() {
		List<Api> list = new ArrayList<Api>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Api.class).list();
			
		} catch (Exception e) {
			String message = "getAllApi Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * get the row from the table based on the ID provided
	 */
	@Override
	public Api getApiByApId(int apiId) {
		Session session = sessionFactory.openSession();
		Api api = new Api();
		try {
			api = session.get(Api.class, apiId);
			

		} catch (Exception e) {
			api = null;
			String message = "getApiByApId(apiId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return api;
	}

	@Override
	public boolean insertApi(Api api) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(api);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertUser(api) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateApi(Api api) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(api);
			
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
	public void deleteApi(int apiId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Api> getApiByNamedQuery(String query, Map<String, Object> param) {
		List<Api> list = new ArrayList<Api>();
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
