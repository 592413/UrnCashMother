package com.skyhotel.ServiceImpl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skyhotel.ServiceDao.HOtelRoomsDao;
import com.skyhotel.model.City;
import com.skyhotel.model.HOtelRooms;
@Repository("HOtelRoomsDao")
public class HotelRoomsImpl implements HOtelRoomsDao{
	private static final Logger log = Logger.getLogger(HOtelRoomsDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean insertHOtelRooms(HOtelRooms HOtelRooms) {
		System.out.println(HOtelRooms);
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(HOtelRooms);
			String message = "QUERY : Save insertHOtelRooms|| ID: " + HOtelRooms.getId();
			log.warn(message);
			tx.commit();
			flag = true;

		} catch (Exception e) {
			flag = false;
			String message = "insertHOtelRooms(HOtelRooms) Error Details :- " + e;
		     log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}

		return flag;
	}

}
