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

import com.recharge.model.Apisetting;
import com.recharge.servicedao.ApisettingDao;

@Repository("apisettingDao")
public class ApisettingImpl implements ApisettingDao {
	private static final Logger logger_log = Logger.getLogger(ApisettingDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Apisetting> getAllApisetting() {
		List<Apisetting> list = new ArrayList<Apisetting>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Apisetting.class).list();
			String message = "Method : getAllApisetting() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllApisetting Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Apisetting getApisettingById(String id) {
		Session session = sessionFactory.openSession();
		Apisetting apisetting = new Apisetting();
		try {
			apisetting = session.get(Apisetting.class, id);
			String message = "Method : getApisettingById(id) || userId: " + apisetting.getId();
			logger_log.warn(message);
		} catch (Exception e) {
			apisetting = null;
			String message = "getApisettingById(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return apisetting;
	}

	@Override
	public boolean insertApisetting(Apisetting apisetting) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(apisetting);
			String message = "QUERY : Save Apisetting|| ID: " + apisetting.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertApisetting(apisetting) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateApisetting(Apisetting apisetting) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(apisetting);
			String message = "QUERY : updateApisetting(apisetting) || ID: " + apisetting.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateApisetting(apisetting)  Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteApisetting(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Apisetting> getApisettingByNamedQuery(String query, Map<String, Object> param) {
		List<Apisetting> list = new ArrayList<Apisetting>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getApisettingByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getApisettingByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
