package com.skyflight.serviceimpl;

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

import com.recharge.model.AEPSLog;
import com.recharge.model.InstantPayLog;
import com.recharge.servicedao.AEPSLogDao;
import com.recharge.servicedao.InstantPayLogDao;


@Repository("instantpaylogdao")
public class InstantPayLogImpl implements InstantPayLogDao {
	
	private static final Logger log = Logger.getLogger(InstantPayLogDao.class);

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public boolean insertInstantPayLog(InstantPayLog InstantPayLog) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(InstantPayLog);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertInstantPayLog(InstantPayLog) Error Details :- " + e;
			log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateInstantPayLog(InstantPayLog InstantPayLog) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(InstantPayLog);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateInstantPayLog(InstantPayLog)  Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public List<InstantPayLog> getInstantPayLogByNamedQuery(String query, Map<String, Object> param) {
		List<InstantPayLog> list = new ArrayList<InstantPayLog>();
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
			String message = "getInstantPayLogByNamedQuery Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public InstantPayLog getInstantPayLogById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

}
