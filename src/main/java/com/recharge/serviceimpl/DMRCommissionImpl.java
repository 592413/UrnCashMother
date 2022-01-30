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
import com.recharge.model.DMRCommission;
import com.recharge.servicedao.DMRCommissionDao;
@Repository("DMRCommissionDao")
public class DMRCommissionImpl implements DMRCommissionDao{
	
	private static final Logger logger_log = Logger.getLogger(DMRCommissionDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<DMRCommission> getAllDMRCommission() {
		List<DMRCommission> list = new ArrayList<DMRCommission>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(DMRCommission.class).list();
			String message = "Method : getAllDMRCommission() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllDMRCommission Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public DMRCommission getDMRCommissionById(String id) {
		Session session = sessionFactory.openSession();
		DMRCommission AEPSComm = new DMRCommission();
		try {
			AEPSComm = session.get(DMRCommission.class, id);
			String message = "Method : getDMRCommissionById(id) || userId: " + AEPSComm.getId();
			logger_log.warn(message);
		} catch (Exception e) {
			AEPSComm = null;
			String message = "getDMRCommissionById(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return AEPSComm;
	}

	@Override
	public boolean insertDMRCommission(DMRCommission AEPSCommission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(AEPSCommission);
			String message = "QUERY : Save insertDMRCommission|| ID: " + AEPSCommission.getId();
			logger_log.warn(message);
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
	public boolean updateDMRCommission(DMRCommission AEPSCommission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(AEPSCommission);
			String message = "QUERY : updateDMRCommission(DMRCommission) || ID: " + AEPSCommission.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateDMRCommission(DMRCommission)  Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	

	@Override
	public List<DMRCommission> getDMRCommissionByNamedQuery(String query, Map<String, Object> param) {
		List<DMRCommission> list = new ArrayList<DMRCommission>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getDMRCommissionByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getDMRCommissionByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}



}
