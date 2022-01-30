package com.skyflight.serviceimpl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skyflight.model.MealDetails;
import com.skyflight.servicedao.MealDetailsDao;

@Repository("MealDetailsDao")
public class MealDetailsImpl implements MealDetailsDao{
	private static final Logger logger_log = Logger.getLogger(MealDetailsDao.class);
	@Autowired private SessionFactory sessionFactory;
	
	
	@Override
	public boolean insertMealDetails(MealDetails fb) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try{
			session.save(fb);
			tx.commit();
			flag = true;
			logger_log.warn("insertMealDetails::::::::::::::"+flag);
		}catch (Exception e) {
			flag = false;
			String message = "insertMealDetails(fb) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		}finally{
			session.close();
		}
	
		return flag;
	}
}
