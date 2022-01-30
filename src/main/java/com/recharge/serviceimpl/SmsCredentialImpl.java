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

import com.recharge.model.SmsCredential;
import com.recharge.servicedao.SmsCredentialDao;

@Repository("smsCredentialDao")
public class SmsCredentialImpl implements SmsCredentialDao {
	private static final Logger logger_log = Logger.getLogger(SmsCredentialDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<SmsCredential> getAllSmsCredential() {
		List<SmsCredential> list = new ArrayList<SmsCredential>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(SmsCredential.class).list();
			
		} catch (Exception e) {
			String message = "getAllSmsCredential Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public SmsCredential getSmsCredentialById(Integer id) {
		Session session = sessionFactory.openSession();
		SmsCredential credentials = new SmsCredential();
		try {
			credentials = session.get(SmsCredential.class, id);
			

		} catch (Exception e) {
			credentials = null;
			String message = " getSmsCredentialById(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return credentials;
	}

	@Override
	public boolean insertSmsCredential(SmsCredential credential) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(credential);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertSmsCredential(signinhistory) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateSmsCredential(SmsCredential credential) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(credential);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateSmsCredential(credential) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteSmsCredential(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SmsCredential> getSmsCredentialByNamedQuery(String query, Map<String, Object> param) {
		List<SmsCredential> list = new ArrayList<SmsCredential>();
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
			String message = "getSmsCredentialByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
