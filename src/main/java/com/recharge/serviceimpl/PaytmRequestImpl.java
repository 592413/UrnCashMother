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

import com.recharge.model.PaymonkResponse;
import com.recharge.model.PaytmRequest;
import com.recharge.servicedao.PaymonkResponseDao;
import com.recharge.servicedao.PaytmRequestDao;
@Repository("paytmrequestdao")
public class PaytmRequestImpl implements PaytmRequestDao {
	private static final Logger logger_log = Logger.getLogger(PaytmRequestDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public boolean insertPaytmRequest(PaytmRequest PaytmRequest) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(PaytmRequest);
			String message = "QUERY : Save insertPaytmRequest|| ID: " + PaytmRequest.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertPaytmRequest(PaytmRequest) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updatePaytmRequest(PaytmRequest PaytmRequest) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(PaytmRequest);
			String message = "QUERY : updatePaytmRequest(PaytmRequest) || ID: " + PaytmRequest.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updatePaytmRequest(PaytmRequest)  Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deletePaytmRequest(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PaytmRequest> getPaytmRequestByNamedQuery(String query, Map<String, Object> param) {
		List<PaytmRequest> list = new ArrayList<PaytmRequest>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getPaytmRequestByNamedQuery() ";
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
