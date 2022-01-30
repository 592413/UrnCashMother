package com.bankopen.Services;

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
import com.bankopen.model.OpenPayout;





@Repository("OpenPayoutDao")
public class OpenPayoutImpl implements OpenPayoutDao{
	private static final Logger logger_log = Logger.getLogger(OpenPayoutDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public OpenPayout getOpenPayoutByApId(int Id) {
		Session session = sessionFactory.openSession();
		OpenPayout OpenPayout = new OpenPayout();
		try {
			OpenPayout = session.get(OpenPayout.class, Id);
			

		} catch (Exception e) {
			OpenPayout = null;
			String message = "getOpenPayoutByApId(Id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return OpenPayout;
	}

	@Override
	public boolean insertOpenPayout(OpenPayout OpenPayout) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(OpenPayout);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertOpenPayout(OpenPayout) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateOpenPayout(OpenPayout OpenPayout) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(OpenPayout);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}
	
	
	@Override
	public List<OpenPayout> getOpenPayoutByNamedQuery(String query, Map<String, Object> param) {
		List<OpenPayout> list = new ArrayList<OpenPayout>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			
		} catch (Exception e) {
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
