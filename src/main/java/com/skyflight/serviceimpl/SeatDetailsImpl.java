package com.skyflight.serviceimpl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skyflight.model.SeatDetails;
import com.skyflight.servicedao.SeatDetailsDao;
@Repository("SeatDetailsDao")
public class SeatDetailsImpl implements SeatDetailsDao{
	private static final Logger logger_log = Logger.getLogger(SeatDetailsDao.class);
	@Autowired private SessionFactory sessionFactory;
	//SeatDetails


	@Override
	public boolean insertSeatDetails(SeatDetails fb) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try{
			session.save(fb);
			tx.commit();
			flag = true;
			logger_log.warn("insertSeatDetails::::::::::::::"+flag);
		}catch (Exception e) {
			flag = false;
			String message = "insertSeatDetails(fb) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		}finally{
			session.close();
		}
	
		return flag;
	}
}
