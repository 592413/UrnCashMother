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

import com.skyhotel.ServiceDao.HotelCancellationDao;
import com.skyhotel.model.HotelCancellation;



@Repository("hotelcancellationDao")
public class HotelCancellationImpl implements HotelCancellationDao {
private static final Logger logger_log = Logger.getLogger(HotelCancellationDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public HotelCancellation getHotelCancellationByBookingid(String Bookingid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertHotelCancellation(HotelCancellation HotelCancellation) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(HotelCancellation);
			String message = "QUERY : Save insertHotelCancellation|| ID: " + HotelCancellation.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertHotelCancellation(HotelCancellation) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateHotelCancellation(HotelCancellation HotelCancellation) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(HotelCancellation);
			String message = "QUERY : updateHotelBooking(HotelBooking) || ID: " + HotelCancellation.getId();
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
	public List<HotelCancellation> getHotelCancellationByNamedQuery(String query, Map<String, Object> param) {
		List<HotelCancellation> list = new ArrayList<HotelCancellation>();
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
