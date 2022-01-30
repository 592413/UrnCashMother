package com.skyhotel.ServiceImpl;

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

import com.skyhotel.ServiceDao.HotelLeadPassengerDao;
import com.skyhotel.model.HotelLeadPassenger;



@Repository("hotelleadpassDao")
public class HotelLeadPassengerImpl implements HotelLeadPassengerDao {
	
private static final Logger logger_log = Logger.getLogger(HotelLeadPassengerDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean insertHotelLeadPassenger(HotelLeadPassenger HotelLeadPassenger) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(HotelLeadPassenger);
			String message = "QUERY : Save insertHotelLeadPassenger|| ID: " + HotelLeadPassenger.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertHotelLeadPassenger(HotelLeadPassenger) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateHotelLeadPassenger(HotelLeadPassenger HotelLeadPassenger) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(HotelLeadPassenger);
			String message = "QUERY : updateHotelLeadPassenger(HotelLeadPassenger) || ID: " + HotelLeadPassenger.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateHotelLeadPassenger(HotelLeadPassenger)  Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public List<HotelLeadPassenger> getHotelLeadPassengerByNamedQuery(String query, Map<String, Object> param) {
		List<HotelLeadPassenger> list = new ArrayList<HotelLeadPassenger>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getHotelLeadPassengerByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getHotelLeadPassengerByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
