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

import com.recharge.model.Complain;
import com.recharge.servicedao.ComplainDao;

@Repository("complainDao")
public class ComplainImpl implements ComplainDao {
	private static final Logger logger_log = Logger.getLogger(ComplainDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Complain> getAllComplain() {
		List<Complain> list = new ArrayList<Complain>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Complain.class).list();
			String message = "Method : getAllComplain() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllComplain Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Complain getComplainById(Integer id) {
		Session session = sessionFactory.openSession();
		Complain complain = new Complain();
		try {
			complain = session.get(Complain.class, id);
			String message = "Method : getComplainById(id) || requestId: " + complain.getId();
			logger_log.warn(message);
		} catch (Exception e) {
			complain = null;
			String message = "getComplainById(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return complain;
	}

	@Override
	public boolean insertComplain(Complain complain) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(complain);
			String message = "QUERY : Save Complain|| ID: " + complain.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertComplain(complain) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateComplain(Complain complain) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(complain);
			String message = "QUERY : updateComplain || ID: " + complain.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateComplain Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteComplain(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Complain> getComplainByNamedQuery(String query, Map<String, Object> param) {
		List<Complain> list = new ArrayList<Complain>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getComplainByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getComplainByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
