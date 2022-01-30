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

import com.recharge.model.Ezpayapplicationform;
import com.recharge.model.MicroatmUser;
import com.recharge.servicedao.MicroatmuserDao;
@Repository("MicroatmuserDao")
public class MicroatmImpl implements MicroatmuserDao{
	private static final Logger logger_log = Logger.getLogger(MicroatmuserDao.class);
	private static final Object MicroatmUser = null;
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public boolean insertMicroatmUser(MicroatmUser MicroatmUser) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(MicroatmUser);
			String message = "QUERY : Save insertMicroatmUser|| ID: " + MicroatmUser.getMicropassword();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertMicroatmUser(MicroatmUser) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateMicroatmUser(MicroatmUser MicroatmUser) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(MicroatmUser);
			String message = "QUERY : Save updateMicroatmUser|| ID: " + MicroatmUser.getMicropassword();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "updateMicroatmUser(MicroatmUser) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public List<MicroatmUser> getMicroatmUserByNamedQuery(String query, Map<String, Object> param) {
		List<MicroatmUser> list = new ArrayList<MicroatmUser>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getMicroatmUserByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getMicroatmUserByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public MicroatmUser getMicroatmUserById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

}
