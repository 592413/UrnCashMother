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

import com.recharge.model.Signinhistory;
import com.recharge.model.Transactiondetails;
import com.recharge.servicedao.SigninhistoryDao;
import com.recharge.servicedao.TransactiondetailsDao;

@Repository("signinhistoryDao")
public class SigninhistoryImpl implements SigninhistoryDao{
	private static final Logger logger_log = Logger.getLogger(TransactiondetailsDao.class);

	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public List<Signinhistory> getAllSigninhistory() {
		List<Signinhistory> list = new ArrayList<Signinhistory>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Signinhistory.class).list();
			
		} catch (Exception e) {
			String message = "getAllSigninhistory Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Signinhistory getSigninhistoryById(Integer id) {
		Session session = sessionFactory.openSession();
		Signinhistory signinhistory = new Signinhistory();
		try {
			signinhistory = session.get(Signinhistory.class, id);
			

		} catch (Exception e) {
			signinhistory = null;
			String message = " getSigninhistoryById(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return signinhistory;
	}

	@Override
	public boolean insertSigninhistory(Signinhistory signinhistory) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(signinhistory);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertSigninhistory(signinhistory) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateSigninhistory(Signinhistory signinhistory) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(signinhistory);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateSigninhistory(signinhistory) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteSigninhistory(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Signinhistory> getSigninhistoryByNamedQuery(String query, Map<String, Object> param) {
		List<Signinhistory> list = new ArrayList<Signinhistory>();
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
			String message = "getSigninhistoryByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
