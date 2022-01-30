package com.recharge.serviceimpl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.recharge.model.Gst;
import com.recharge.servicedao.GstDao;

@Repository("GstDao")
public class GstImpl implements GstDao{
	private static final Logger logger_log = Logger.getLogger(GstDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean insertGst(Gst Gst) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(Gst);
			String message = "QUERY : Save insertGst|| ID: " + Gst.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertGst(Gst) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}
}
