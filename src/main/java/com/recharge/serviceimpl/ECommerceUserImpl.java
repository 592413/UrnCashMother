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

import com.recharge.model.ECommerceUser;
import com.recharge.model.IRCTCUser;
import com.recharge.servicedao.ECommerceUserDao;
import com.recharge.servicedao.IRCTCUserDao;


@Repository("ecommercedao")
public class ECommerceUserImpl implements ECommerceUserDao {
private static final Logger logger_log = Logger.getLogger(ECommerceUserDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<ECommerceUser> getAllECommerceUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ECommerceUser getECommerceUserMapById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertECommerceUser(ECommerceUser ECommerceUser) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(ECommerceUser);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertECommerceUser(ECommerceUser) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateECommerceUser(ECommerceUser ECommerceUser) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(ECommerceUser);;
			String message = "QUERY : Save updateECommerceUser|| ID: " + ECommerceUser.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			String message = "updateECommerceUser(ECommerceUser) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteECommerceUser(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ECommerceUser> getECommerceUserByNamedQuery(String query, Map<String, Object> param) {
		List<ECommerceUser> list = new ArrayList<ECommerceUser>();
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
			String message = "getECommerceUserByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
