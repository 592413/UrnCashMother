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
import com.recharge.model.Userattachment;
import com.recharge.servicedao.ApiDao;
import com.recharge.servicedao.UserattachmentDao;

@Repository("userattachmentDao")
public class UserattachmentImpl implements UserattachmentDao {
	private static final Logger logger_log = Logger.getLogger(UserattachmentDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Userattachment> getAllUserattachment() {
		List<Userattachment> list = new ArrayList<Userattachment>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Userattachment.class).list();
			String message = "Method : getAllUserattachment() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllUserattachment Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Userattachment getUserattachmentById(String Id) {
		Session session = sessionFactory.openSession();
		Userattachment userattachment = new Userattachment();
		try {
			userattachment = session.get(Userattachment.class, Id);
			String message = "Method : getUserattachmentById(Id) || userId: " + userattachment.getId();
			logger_log.warn(message);

		} catch (Exception e) {
			userattachment = null;
			String message = "getUserattachmentById(Id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return userattachment;
	}

	@Override
	public boolean insertUserattachment(Userattachment userattachment) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(userattachment);
			String message = "QUERY : Save insertUserattachment|| ID: " + userattachment.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertUserattachment(userattachment) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateUserattachment(Userattachment userattachment) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(userattachment);
			String message = "QUERY : updateUserattachment || ID: " + userattachment.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateUserattachment Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteUserattachment(int Id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Userattachment> getUserattachmentByNamedQuery(String query, Map<String, Object> param) {
		List<Userattachment> list = new ArrayList<Userattachment>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getUserattachmentByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getUserattachmentByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
