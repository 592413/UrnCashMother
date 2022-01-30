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
import com.recharge.servicedao.BalancerequestDao;

@Repository("balancerequestDao")
public class BalancerequestImpl implements BalancerequestDao{
	private static final Logger logger_log = Logger.getLogger(BalancerequestDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Balancerequest> getAllBalanceRequest() {
		List<Balancerequest> list = new ArrayList<Balancerequest>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Balancerequest.class).list();
			String message = "Method : getAllBalanceRequest() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllBalanceRequest Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Balancerequest getBalanceRequestByRequestId(Integer requestId) {
		Session session = sessionFactory.openSession();
		Balancerequest balancerequest = new Balancerequest();
		try {
			balancerequest = session.get(Balancerequest.class, requestId);
			String message = "Method : getBalanceRequestByRequestId(requestId) || requestId: "+ balancerequest.getRequestId();
			logger_log.warn(message);

		} catch (Exception e) {
			balancerequest = null;
			String message = "getBalanceRequestByRequestId(requestId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return balancerequest;
	}

	@Override
	public boolean insertBalanceRequest(Balancerequest balancerequest) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(balancerequest);
			String message = "QUERY : Save Balancerequest|| ID: " + balancerequest.getRequestId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			String message = "insertBalanceRequest(Balancerequest) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateBalanceRequest(Balancerequest balancerequest) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(balancerequest);
			String message = "QUERY : updateBalanceRequest || ID: " + balancerequest.getRequestId();
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
	public void deleteBalanceRequest(Integer requestId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Balancerequest> getBalanceRequestByNamedQuery(String query, Map<String, Object> param) {
		List<Balancerequest> list = new ArrayList<Balancerequest>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getBalanceRequestByNamedQuery() ";
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
