package com.skyflight.serviceimpl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skyflight.model.BaggageDetails;
import com.skyflight.servicedao.BaggageDao;

@Repository("BaggageDao")
public class BaggageImpl implements BaggageDao{
	private static final Logger logger_log = Logger.getLogger(BaggageDao.class);
	@Autowired private SessionFactory sessionFactory;
	
	
	@Override
	public boolean insertBaggageDetails(BaggageDetails fb) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try{
			session.save(fb);
			tx.commit();
			flag = true;
			logger_log.warn("insertBaggageDetails::::::::::::::"+flag);
		}catch (Exception e) {
			flag = false;
			String message = "insertBaggageDetails(fb) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		}finally{
			session.close();
		}
	
		return flag;
	}
}
