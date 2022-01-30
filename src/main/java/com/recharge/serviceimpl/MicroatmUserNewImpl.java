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

import com.recharge.model.MicroatmUser;
import com.recharge.model.MicroatmUserNew;
import com.recharge.servicedao.MicroatmuserDao;
import com.recharge.servicedao.MicroatmuserNewDao;


@Repository("MicroatmuserNewDao")
public class MicroatmUserNewImpl implements MicroatmuserNewDao {
	private static final Logger logger_log = Logger.getLogger(MicroatmuserDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public boolean insertMicroatmUserNew(MicroatmUserNew MicroatmUserNew) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(MicroatmUserNew);
			String message = "QUERY : Save insertMicroatmUserNew|| ID: " + MicroatmUserNew.getMicropassword();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertMicroatmUserNew(MicroatmUserNew) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateMicroatmUserNew(MicroatmUserNew MicroatmUserNew) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(MicroatmUserNew);
			String message = "QUERY : Save updateMicroatmUser|| ID: " + MicroatmUserNew.getMicropassword();
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
	public List<MicroatmUserNew> getMicroatmUserNewByNamedQuery(String query, Map<String, Object> param) {
		List<MicroatmUserNew> list = new ArrayList<MicroatmUserNew>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getMicroatmUserNewByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getMicroatmUserNewByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public MicroatmUserNew getMicroatmUserNewById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

}
