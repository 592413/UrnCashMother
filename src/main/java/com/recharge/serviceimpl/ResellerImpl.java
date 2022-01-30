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

import com.recharge.model.Reseller;
import com.recharge.servicedao.ResellerDao;

@Repository("resellerDao")
public class ResellerImpl implements ResellerDao {
	private static final Logger logger_log = Logger.getLogger(ResellerDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Reseller> getAllReseller() {
		List<Reseller> list = new ArrayList<Reseller>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Reseller.class).list();
			
		} catch (Exception e) {
			String message = "getAllReseller Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Reseller getResellerById(String id) {
		Session session = sessionFactory.openSession();
		Reseller reseller = new Reseller();
		try {
			reseller = session.get(Reseller.class, id);
			

		} catch (Exception e) {
			reseller = null;
			String message = "getResellerById(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return reseller;
	}

	@Override
	public boolean insertReseller(Reseller reseller) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(reseller);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertReseller(reseller) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateReseller(Reseller reseller) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(reseller);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "Update Reseller Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteReseller(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Reseller> getResellerByNamedQuery(String query, Map<String, Object> param) {
		List<Reseller> list = new ArrayList<Reseller>();
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
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
