package com.skyflight.serviceimpl;

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

import com.recharge.model.User;
import com.skyflight.model.GstFlight;
import com.skyflight.servicedao.GstFlightDao;

@Repository("GstFlightDao")
public class GstFLightImpl implements GstFlightDao{
	private static final Logger logger_log = Logger.getLogger(GstFlightDao.class);
	@Autowired private SessionFactory sessionFactory;
	
	
	@Override
	public GstFlight getGstFlightByUserId(String userId) {
		Session session = sessionFactory.openSession();
		GstFlight GstFlight = null;
		try {
			GstFlight = session.get(GstFlight.class, userId);
			
		} catch (Exception e) {
			GstFlight = null;
			String message = "getGstFlightByUserId(userId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return GstFlight;
	}
	
	@Override
	public boolean insertGstFlight(GstFlight GstFlight) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(GstFlight);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertGstFlight(GstFlight) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}
	
	
	@Override
	public boolean updateGstFlight(GstFlight GstFlight) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(GstFlight);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateGstFlight(GstFlight)  Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}
	
	@Override
	public List<GstFlight> getGstFlightLogByNamedQuery(String query, Map<String, Object> param) {
		List<GstFlight> list = new ArrayList<GstFlight>();
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
			String message = "getGstFlightByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}
}
