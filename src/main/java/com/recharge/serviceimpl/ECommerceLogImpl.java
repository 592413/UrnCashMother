package com.recharge.serviceimpl;

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

import com.recharge.model.ECommerceLog;

import com.recharge.servicedao.ECommerceLogDao;




@Repository("ecommercelogdao")
public class ECommerceLogImpl implements ECommerceLogDao {
private static final Logger logger_log = Logger.getLogger(ECommerceLog.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public boolean insertECommerceLog(ECommerceLog ECommerceLog) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(ECommerceLog);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertECommerceLog(ECommerceLog) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateECommerceLog(ECommerceLog ECommerceLog) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(ECommerceLog);;
			String message = "QUERY : Save updateECommerceLog|| ID: " + ECommerceLog.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			String message = "updateECommerceLog(ECommerceLog) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	
	@Override
	public List<ECommerceLog> getECommerceLogByNamedQuery(String query, Map<String, Object> param) {
		List<ECommerceLog> list = new ArrayList<ECommerceLog>();
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
			String message = "getECommerceLogByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public ECommerceLog getECommerceLogById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

}
