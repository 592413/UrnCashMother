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

import com.skyhotel.ServiceDao.HotelDetailDao;
import com.skyhotel.model.HotelDetail;


@Repository("hoteldetailDao")
public class HotelDetailImpl implements HotelDetailDao {
	
private static final Logger logger_log = Logger.getLogger(HotelDetailDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;




	@Override
	public boolean insertHotelDetail(HotelDetail HotelDetail) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(HotelDetail);
			String message = "QUERY : Save insertHotelDetail|| ID: " + HotelDetail.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertHotelDetail(HotelDetail) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateHotelDetail(HotelDetail HotelDetail) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(HotelDetail);
			String message = "QUERY : updateHotelBooking(HotelBooking) || ID: " + HotelDetail.getId();
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
	public List<HotelDetail> getHotelDetailByNamedQuery(String query, Map<String, Object> param) {
		List<HotelDetail> list = new ArrayList<HotelDetail>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getHotelDetailByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getHotelDetailByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
