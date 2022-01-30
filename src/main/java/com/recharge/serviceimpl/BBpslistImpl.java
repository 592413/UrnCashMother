package com.recharge.serviceimpl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recharge.model.BBPSLIST;
import com.recharge.servicedao.BBPsListDao;
@Repository("BBPsListDao")
public class BBpslistImpl implements BBPsListDao{
	private static final Logger logger_log = Logger.getLogger(BBPsListDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public BBPSLIST getBBPSLISTByApId(int id) {
		Session session = sessionFactory.openSession();
		BBPSLIST api = new BBPSLIST();
		try {
			api = session.get(BBPSLIST.class, id);
			String message = "Method : getBBPSLISTByApId(apiId) || userId: " + api.getId();
			logger_log.warn(message);

		} catch (Exception e) {
			api = null;
			String message = "getBBPSLISTByApId(apiId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return api;
	}

}
