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

import com.recharge.model.Individualcommission;
import com.recharge.servicedao.IndividualcommissionDao;

@Repository("individualcommissionDao")
public class IndividualcommissionImpl implements IndividualcommissionDao {
	
	private static final Logger logger_log = Logger.getLogger(IndividualcommissionDao.class);

	@Autowired private SessionFactory sessionFactory;

	@Override
	public List<Individualcommission> getAllIndividualCommission() {
		List<Individualcommission> list = new ArrayList<Individualcommission>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Individualcommission.class).list();
			String message = "Method : getAllIndividualCommission() ";
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
	public Individualcommission getIndividualCommissionByChargeId(Integer commissionId) {
		Session session = sessionFactory.openSession();
		Individualcommission individualcommission = new Individualcommission();
		try {
			individualcommission = session.get(Individualcommission.class, commissionId);
			String message = "Method : getBalanceRequestByRequestId(requestId) || requestId: "+ individualcommission.getCommissionId();
			logger_log.warn(message);

		} catch (Exception e) {
			individualcommission = null;
			String message = "getBalanceRequestByRequestId(requestId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return individualcommission;
	}

	@Override
	public boolean insertIndividualCommission(Individualcommission individualcommission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(individualcommission);
			String message = "QUERY : Save Individual Commission|| ID: " + individualcommission.getCommissionId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertIndividualCommission(Individualcommission) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateIndividualCommission(Individualcommission individualcommission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(individualcommission);
			String message = "QUERY : updateBalanceRequest || ID: " + individualcommission.getCommissionId();
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
	public void deleteIndividualCommission(Integer commissionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Individualcommission> getIndividualCommissionByNamedQuery(String query, Map<String, Object> param) {
		List<Individualcommission> list = new ArrayList<Individualcommission>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getBalanceRequestByNamedQuery() ";
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
