package com.recharge.serviceimpl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recharge.model.TdsReport;
import com.recharge.servicedao.TdsReportDao;


@Repository("TdsReportDao")
public class TdsReportImpl implements TdsReportDao {
	private static final Logger logger_log = Logger.getLogger(TdsReportDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public boolean insertTdsReport(TdsReport TdsReport) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(TdsReport);
		
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertTdsReport(TdsReport) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}
}
