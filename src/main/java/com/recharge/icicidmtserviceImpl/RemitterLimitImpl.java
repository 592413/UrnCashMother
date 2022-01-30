package com.recharge.icicidmtserviceImpl;

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

import com.recharge.icicidmtmodel.RemitterLimit;
import com.recharge.icicidmtserviceDao.RemitterLimitDao;



@Repository("remitterlimitDao")
public class RemitterLimitImpl implements RemitterLimitDao {
	private static final Logger logger_log = Logger.getLogger(RemitterLimitDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<RemitterLimit> getRemitterLimit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RemitterLimit getRemitterLimitById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertRemitterLimit(RemitterLimit RemitterLimit) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			
			session.save(RemitterLimit);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertRemitterLimit(RemitterLimit) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateRemitterLimit(RemitterLimit RemitterLimit) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(RemitterLimit);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateRemitterLimit(RemitterLimit)  Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteRemitterLimit(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RemitterLimit> getRemitterLimitByNamedQuery(String query, Map<String, Object> param) {
		List<RemitterLimit> list = new ArrayList<RemitterLimit>();
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
			String message = "getRemitterLimitByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
