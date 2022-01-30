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

import com.recharge.model.EarningSurcharge;
import com.recharge.servicedao.EarningSurchargeDao;

@Repository("earningSurchargeDao")
public class EarningSurchargeImpl implements EarningSurchargeDao {
	private static final Logger logger_log = Logger.getLogger(EarningSurchargeDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<EarningSurcharge> getAllEarningSurcharge() {
		List<EarningSurcharge> list = new ArrayList<EarningSurcharge>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(EarningSurcharge.class).list();
			String message = "Method : getAllEarningSurcharge() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllEarningSurcharge Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public EarningSurcharge getEarningSurchargeById(String Id) {
		Session session = sessionFactory.openSession();
		EarningSurcharge earningSurcharge = new EarningSurcharge();
		try {
			earningSurcharge = session.get(EarningSurcharge.class, Id);
			String message = "Method : getEarningSurchargeById(Id) || Id: " + earningSurcharge.getId();
			logger_log.warn(message);

		} catch (Exception e) {
			earningSurcharge = null;
			String message = "getEarningSurchargeById(Id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return earningSurcharge;
	}

	@Override
	public boolean insertEarningSurcharge(EarningSurcharge earningSurcharge) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(earningSurcharge);
			String message = "QUERY : Save insertEarningSurcharge|| ID: " + earningSurcharge.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertEarningSurcharge(earningSurcharge) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateEarningSurcharge(EarningSurcharge earningSurcharge) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(earningSurcharge);
			String message = "QUERY : updateEarningSurcharge || ID: " + earningSurcharge.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateEarningSurcharge Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteEarningSurcharge(int Id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EarningSurcharge> getEarningSurchargeByNamedQuery(String query, Map<String, Object> param) {
		List<EarningSurcharge> list = new ArrayList<EarningSurcharge>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getEarningSurchargeByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getEarningSurchargeByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
