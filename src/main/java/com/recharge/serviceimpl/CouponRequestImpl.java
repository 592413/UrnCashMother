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

import com.recharge.model.CouponRequest;
import com.recharge.servicedao.CouponRequestDao;

@Repository("CouponRequestDao")
public class CouponRequestImpl implements CouponRequestDao{
private static final Logger logger_log = Logger.getLogger(CouponRequestDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public boolean insertCouponRequest(CouponRequest couponrequest) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(couponrequest);
			String message = "QUERY : Save couponrequest|| ID: " ;
			logger_log.warn(message);
			tx.commit();			
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insert couponrequest(couponrequest) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateCouponRequest(CouponRequest couponrequest) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(couponrequest);
			String message = "QUERY : Update updateCouponRequest || ID: ";
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
	public List<CouponRequest> getCouponRequestByNamedQuery(String query, Map<String, Object> param) {
		List<CouponRequest> list = new ArrayList<CouponRequest>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					System.out.println(entry.getKey());
					System.out.println(entry.getValue());
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getCouponRequestByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public CouponRequest getcouponById(Integer id) {
		Session session = sessionFactory.openSession();
		CouponRequest CouponRequest = new CouponRequest();
		try {
			CouponRequest = session.get(CouponRequest.class, id);
			String message = "Method : getcouponById(id) || requestId: ";
			logger_log.warn(message);

		} catch (Exception e) {
			CouponRequest = null;
			String message = "getcouponById(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return CouponRequest;
	}

}
