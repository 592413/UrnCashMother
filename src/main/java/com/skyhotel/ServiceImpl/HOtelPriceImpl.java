package com.skyhotel.ServiceImpl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skyhotel.ServiceDao.HotelPriceDao;
import com.skyhotel.model.HOtelPrice;


@Repository("HotelPriceDao")
public class HOtelPriceImpl implements HotelPriceDao{
	private static final Logger log = Logger.getLogger(HotelPriceDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean insertHOtelPrice(HOtelPrice HOtelPrice) {
		System.out.println(HOtelPrice);
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(HOtelPrice);
			String message = "QUERY : Save insertHOtelPrice|| ID: ";
			log.warn(message);
			tx.commit();
			flag = true;

		} catch (Exception e) {
			flag = false;
			String message = "insertHOtelPrice(HOtelPrice) Error Details :- " + e;
		     log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}

		return flag;
	}


}
