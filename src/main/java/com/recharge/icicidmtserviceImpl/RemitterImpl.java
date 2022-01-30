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

import com.recharge.icicidmtmodel.Remitter;
import com.recharge.icicidmtserviceDao.RemitterDao;



@Repository("remitterDao")
public class RemitterImpl implements RemitterDao {
	private static final Logger logger_log = Logger.getLogger(RemitterDao.class);
	@Autowired private SessionFactory sessionFactory;
	@Override
	public boolean insertRemitter(Remitter Remitter) {
		boolean flag=false;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(Remitter);
			
			tx.commit();
			flag=true;
		} catch (Exception e) {
			String message = "insertRemitter(Remitter) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}
	@Override
	public boolean updateRemitter(Remitter Remitter) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(Remitter);
			
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
	public boolean deleteRemitter(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Remitter> getRemitterByNamedQuery(String query, Map<String, Object> param) {
		List<Remitter> list = new ArrayList<Remitter>();
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
