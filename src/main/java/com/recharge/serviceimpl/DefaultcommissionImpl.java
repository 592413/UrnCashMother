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

import com.recharge.model.Defaultcommission;
import com.recharge.servicedao.DefaultcommissionDao;


@Repository("defaultcommissionDao")
public class DefaultcommissionImpl implements DefaultcommissionDao {
	
	private static final Logger logger_log = Logger.getLogger(DefaultcommissionDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Defaultcommission> getAllBalanceTransfer() {
		List<Defaultcommission> list = new ArrayList<Defaultcommission>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Defaultcommission.class).list();
			String message = "Method : getAllBalanceRequest() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllBalanceRequest Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Defaultcommission getDefaultCommissionByChargeId(Integer commissionId) {
		Session session = sessionFactory.openSession();
		Defaultcommission defaultcommission = new Defaultcommission();
		try {
			defaultcommission = session.get(Defaultcommission.class, commissionId);
			String message = "Method : getDefaultCommissionByChargeId(commissionId) || requestId: "+ defaultcommission.getCommissionId();
			logger_log.warn(message);

		} catch (Exception e) {
			defaultcommission = null;
			String message = "getDefaultCommissionByChargeId(commissionId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return defaultcommission;
	}

	@Override
	public boolean insertDefaultCommission(Defaultcommission defaultcommission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(defaultcommission);
			String message = "QUERY : Save Default Commission|| ID: " + defaultcommission.getCommissionId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertDefaultCommission(Defaultcommission) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateDefaultCommission(Defaultcommission defaultcommission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(defaultcommission);
			String message = "QUERY : updateDefaultCommission || ID: " + defaultcommission.getCommissionId();
			logger_log.warn(message);
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
	public void deleteDefaultCommission(Integer commissionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Defaultcommission> getDefaultCommissionByNamedQuery(String query, Map<String, Object> param) {
		List<Defaultcommission> list = new ArrayList<Defaultcommission>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getDefaultCommissionByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
