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

import com.skyhotel.ServiceDao.HotelBookingDao;
import com.skyhotel.model.HotelBooking;

@Repository("hotelbookingDao")
public class HotelBookingImpl implements HotelBookingDao {
private static final Logger logger_log = Logger.getLogger(HotelBookingDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public HotelBooking getHotelBookingByBookingid(String Bookingid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertHotelBooking(HotelBooking HotelBooking) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(HotelBooking);
			String message = "QUERY : Save insertHotelBooking|| ID: " + HotelBooking.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertHotelBooking(HotelBooking) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateHotelBooking(HotelBooking HotelBooking) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(HotelBooking);
			String message = "QUERY : updateHotelBooking(HotelBooking) || ID: " + HotelBooking.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateHotelBooking(HotelBooking)  Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public List<HotelBooking> getHotelBookingByNamedQuery(String query, Map<String, Object> param) {
		List<HotelBooking> list = new ArrayList<HotelBooking>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getHotelBookingByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getHotelBookingByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}



}
