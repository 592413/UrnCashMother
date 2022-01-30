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

import com.recharge.model.Balancerequest;
import com.recharge.model.Transactiondetails;
import com.recharge.servicedao.TransactiondetailsDao;

@Repository("transactiondetailsDao")
public class TransactiondetailsImpl implements TransactiondetailsDao {
	
	private static final Logger logger_log = Logger.getLogger(TransactiondetailsDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Transactiondetails> getAllTransactionDetails() {
		List<Transactiondetails> list = new ArrayList<Transactiondetails>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Transactiondetails.class).list();
			String message = "Method : getAllTransactionDetails() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllTransactionDetails Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Transactiondetails getTransactionDetailsByTransId(Integer transId) {
		Session session = sessionFactory.openSession();
		Transactiondetails transactiondetails = new Transactiondetails();
		try {
			transactiondetails = session.get(Transactiondetails.class, transId);
			String message = "Method : getTransactionDetailsByTransId(transId) || requestId: "+ transactiondetails.getTransId();
			logger_log.warn(message);

		} catch (Exception e) {
			transactiondetails = null;
			String message = "getTransactionDetailsByTransId(transId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return transactiondetails;
	}

	@Override
	public boolean insertTransactionDetails(Transactiondetails transactiondetails) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(transactiondetails);
			String message = "QUERY : Save Transactiondetails|| ID: " + transactiondetails.getTransId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertTransactionDetails(Transactiondetails) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateTransactionDetails(Transactiondetails transactiondetails) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(transactiondetails);
			String message = "QUERY : updateTransactionDetails || ID: " + transactiondetails.getTransId();
			logger_log.warn(message);
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
	public void deleteTransactionDetails(Integer transId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Transactiondetails> getTransactionDetailsByNamedQuery(String query, Map<String, Object> param) {
		List<Transactiondetails> list = new ArrayList<Transactiondetails>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getTransactionDetailsByNamedQuery() ";
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
