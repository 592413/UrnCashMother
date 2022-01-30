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

import com.recharge.model.Api;
import com.skyflight.model.FlightFare;
import com.skyflight.model.Flightbooking;
import com.skyflight.servicedao.FlightFareDao;
@Repository("FlightFareDao")
public class FlightFareImpl implements FlightFareDao{
	private static final Logger logger_log = Logger.getLogger(FlightFareDao.class);
	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public FlightFare getFlightFareByApId(int Id) {
		Session session = sessionFactory.openSession();
		FlightFare api = new FlightFare();
		try {
			api = session.get(FlightFare.class, Id);
			

		} catch (Exception e) {
			api = null;
			String message = "getFlightFareByApId(apiId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return api;
	}
	
	@Override
	public boolean insertFlightFare(FlightFare fb) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try{
			session.save(fb);
			tx.commit();
			flag = true;
			logger_log.warn("insertFlightFare::::::::::::::"+flag);
		}catch (Exception e) {
			flag = false;
			String message = "insertFlightFare(fb) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		}finally{
			session.close();
		}
	
		return flag;
	}
	
	@Override
	public boolean updateFlightFare(FlightFare fb) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try{
			session.update(fb);
			tx.commit();
			flag = true;
			
		}catch (Exception e) {
			flag = false;
			String message = "insertFlightFare(fb) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		}finally{
			session.close();
		}
	
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FlightFare> getFlightFareByNamedQuery(String query, Map<String, Object> param) {
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		List<FlightFare> list = new ArrayList<FlightFare>();
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			logger_log.warn("getFlightFareByNamedQuery::::::::::::::"+list.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getFlightFareByNamedQuery:::::::::::::::"+e);
			
		} finally {

		}
		return list;
	}
}
