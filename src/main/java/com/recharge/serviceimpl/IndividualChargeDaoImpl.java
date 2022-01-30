/**
 * 
 */
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

import com.recharge.model.Individualcharge;
import com.recharge.model.Individualcommission;
import com.recharge.servicedao.IndividualChargeDao;
import com.recharge.servicedao.IndividualcommissionDao;

/**
 * @author Md Rahim
 *
 */

@Repository("individualChargeDao")
public class IndividualChargeDaoImpl implements IndividualChargeDao{
	
	private static final Logger logger_log = Logger.getLogger(IndividualcommissionDao.class);

	@Autowired private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.recharge.servicedao.IndividualChargeDao#getAllIndividualcharge()
	 */
	@Override
	public List<Individualcharge> getAllIndividualcharge() {
		List<Individualcharge> list = new ArrayList<Individualcharge>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Individualcharge.class).list();
			String message = "Method : getAllIndividualcharge() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllIndividualcharge Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.recharge.servicedao.IndividualChargeDao#getIndividualchargeById(java.lang.Integer)
	 */
	@Override
	public Individualcharge getIndividualchargeById(Integer Id) {
		Session session = sessionFactory.openSession();
		Individualcharge individualcharge = new Individualcharge();
		try {
			individualcharge = session.get(Individualcharge.class, Id);
			String message = "Method : getIndividualchargeById(Id) || requestId: "+ individualcharge.getId();
			logger_log.warn(message);

		} catch (Exception e) {
			individualcharge = null;
			String message = "getIndividualchargeById(Id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return individualcharge;
	}

	/* (non-Javadoc)
	 * @see com.recharge.servicedao.IndividualChargeDao#insertIndividualcharge(com.recharge.model.Individualcharge)
	 */
	@Override
	public boolean insertIndividualcharge(Individualcharge individualcharge) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(individualcharge);
			String message = "QUERY : Save Individual charge|| ID: " + individualcharge.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertIndividualcharge(individualcharge) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.recharge.servicedao.IndividualChargeDao#updateIndividualcharge(com.recharge.model.Individualcharge)
	 */
	@Override
	public boolean updateIndividualcharge(Individualcharge individualcharge) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(individualcharge);
			String message = "QUERY : updateIndividualcharge || ID: " + individualcharge.getId();
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

	/* (non-Javadoc)
	 * @see com.recharge.servicedao.IndividualChargeDao#deleteIndividualcharge(java.lang.Integer)
	 */
	@Override
	public void deleteIndividualcharge(Integer Id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.recharge.servicedao.IndividualChargeDao#getIndividualchargeByNamedQuery(java.lang.String, java.util.Map)
	 */
	@Override
	public List<Individualcharge> getIndividualchargeByNamedQuery(String query, Map<String, Object> param) {
		List<Individualcharge> list = new ArrayList<Individualcharge>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getIndividualchargeByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getIndividualchargeByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
