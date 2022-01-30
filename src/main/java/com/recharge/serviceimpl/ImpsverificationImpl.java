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
import com.recharge.model.Impsverification;
import com.recharge.servicedao.ApiDao;
import com.recharge.servicedao.ImpsverificationDao;

@Repository("impsverificationDao")
public class ImpsverificationImpl implements ImpsverificationDao {
	private static final Logger logger_log = Logger.getLogger(ImpsverificationDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Impsverification> getAllImpsverification() {
		List<Impsverification> list = new ArrayList<Impsverification>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Impsverification.class).list();
			String message = "Method : getAllImpsverification() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllImpsverification Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Impsverification getImpsverificationById(String Id) {
		Session session = sessionFactory.openSession();
		Impsverification impsverification = new Impsverification();
		try {
			impsverification = session.get(Impsverification.class, Id);
			String message = "Method : getImpsverificationById(Id) || userId: " + impsverification.getId();
			logger_log.warn(message);

		} catch (Exception e) {
			impsverification = null;
			String message = "getImpsverificationById(Id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return impsverification;
	}

	@Override
	public boolean insertImpsverification(Impsverification impsverification) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(impsverification);
			String message = "QUERY : Save Impsverification|| ID: " + impsverification.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertImpsverification(Impsverification) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateImpsverification(Impsverification impsverification) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(impsverification);
			String message = "QUERY : updateImpsverification || ID: " + impsverification.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateImpsverification Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteImpsverification(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Impsverification> getImpsverificationByNamedQuery(String query, Map<String, Object> param) {
		List<Impsverification> list = new ArrayList<Impsverification>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getImpsverificationByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getImpsverificationByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
