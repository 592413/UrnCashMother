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

import com.recharge.model.Rechargedetails;
import com.recharge.servicedao.RechargedetailsDao;

@Repository("rechargedetailsDao")
public class RechargedetailsImpl implements RechargedetailsDao{
	
	private static final Logger logger_log = Logger.getLogger(RechargedetailsDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Rechargedetails> getAllRechargeDetails() {
		List<Rechargedetails> list = new ArrayList<Rechargedetails>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Rechargedetails.class).list();
		
		} catch (Exception e) {
			String message = "getAllRechargeDetails Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Rechargedetails getRechargeDetailsById(Integer Id) {
		Session session = sessionFactory.openSession();
		Rechargedetails rechargedetails = new Rechargedetails();
		try {
			rechargedetails = session.get(Rechargedetails.class, Id);
			

		} catch (Exception e) {
			rechargedetails = null;
			String message = "getRechargeDetailsByRechargeId(rechargeId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return rechargedetails;
	}

	@Override
	public boolean insertRechargeDetails(Rechargedetails rechargedetails) {
		Session session = sessionFactory.openSession();
		boolean flag = false;
		Transaction tx = session.beginTransaction();
		try {
			session.save(rechargedetails);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertRechargeDetails(rechargedetails) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateRechargeDetails(Rechargedetails rechargedetails) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(rechargedetails);
			
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
	public void deleteRechargeDetails(Integer rechargeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Rechargedetails> getRechargeDetailsByNamedQuery(String query, Map<String, Object> param) {
		List<Rechargedetails> list = new ArrayList<Rechargedetails>();
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
