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

import com.recharge.model.Chargeset;
import com.recharge.servicedao.ChargesetDao;

@Repository("chargesetDao")
public class ChargesetImpl implements ChargesetDao {
	
	private static final Logger logger_log = Logger.getLogger(ChargesetDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Chargeset> getAllBalanceTransfer() {
		List<Chargeset> list = new ArrayList<Chargeset>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Chargeset.class).list();
			String message = "Method : getAllBalanceTransfer() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllBalanceTransfer Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Chargeset getChargeSetByChargeId(Integer chargeId) {
		Session session = sessionFactory.openSession();
		Chargeset chargeset = new Chargeset();
		try {
			chargeset = session.get(Chargeset.class, chargeId);
			String message = "Method : getChargeSetByChargeId(chargeId) || requestId: "+ chargeset.getChargeId();
			logger_log.warn(message);

		} catch (Exception e) {
			chargeset = null;
			String message = "getChargeSetByChargeId(chargeId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return chargeset;
	}

	@Override
	public boolean insertChargeSet(Chargeset chargeset) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(chargeset);
			String message = "QUERY : Save ChargeSet|| ID: " + chargeset.getChargeId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertChargeSet(chargeset) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateChargeSet(Chargeset chargeset) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(chargeset);
			String message = "QUERY : updateBalanceRequest || ID: " + chargeset.getChargeId();
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
	public void deleteChargeSet(Integer chargeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Chargeset> getChargeSetByNamedQuery(String query, Map<String, Object> param) {
		List<Chargeset> list = new ArrayList<Chargeset>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getChargeSetByNamedQuery() ";
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
