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

import com.recharge.model.AEPSCommission;
import com.recharge.servicedao.AEPSCommissionDao;

@Repository("aepscommissiondao")
public class AEPSCommissionImpl implements AEPSCommissionDao {

	private static final Logger logger_log = Logger.getLogger(AEPSCommissionDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<AEPSCommission> getAllAEPSCommission() {
		List<AEPSCommission> list = new ArrayList<AEPSCommission>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(AEPSCommission.class).list();
			
		} catch (Exception e) {
			String message = "getAllApitrace Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public AEPSCommission getAEPSCommissionById(String id) {
		Session session = sessionFactory.openSession();
		AEPSCommission AEPSComm = new AEPSCommission();
		try {
			AEPSComm = session.get(AEPSCommission.class, id);
			
		} catch (Exception e) {
			AEPSComm = null;
			String message = "getAEPSCommissionById(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return AEPSComm;
	}

	@Override
	public boolean insertAEPSCommission(AEPSCommission AEPSCommission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(AEPSCommission);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertAEPSCommission(AEPSCommission) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateAEPSCommission(AEPSCommission AEPSCommission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(AEPSCommission);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateAEPSCommission(AEPSCommission)  Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteAEPSCommission(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AEPSCommission> getAEPSCommissionByNamedQuery(String query, Map<String, Object> param) {
		List<AEPSCommission> list = new ArrayList<AEPSCommission>();
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
			String message = "getAEPSCommissionByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}



}
