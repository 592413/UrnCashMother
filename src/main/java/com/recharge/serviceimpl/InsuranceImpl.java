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

import com.recharge.model.Api;
import com.recharge.model.Insurance;
import com.recharge.servicedao.ApiDao;
import com.recharge.servicedao.InsuranceDao;

@Repository("insuranceDao")
public class InsuranceImpl implements InsuranceDao {
	private static final Logger logger_log = Logger.getLogger(InsuranceDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Insurance> getAllInsurance() {
		List<Insurance> list = new ArrayList<Insurance>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Insurance.class).list();
			String message = "Method : getAllInsurance() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllInsurance Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Insurance getInsuranceById(String id) {
		Session session = sessionFactory.openSession();
		Insurance insurance = new Insurance();
		try {
			insurance = session.get(Insurance.class, id);
			String message = "Method : getInsuranceById(id) || userId: " + insurance.getId();
			logger_log.warn(message);

		} catch (Exception e) {
			insurance = null;
			String message = "getInsuranceById(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return insurance;
	}

	@Override
	public boolean insertInsurance(Insurance insurance) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(insurance);
			String message = "QUERY : Save Insurance|| ID: " + insurance.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertInsurance(insurance) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateInsurance(Insurance insurance) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(insurance);
			String message = "QUERY : Update Api || ID: " + insurance.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateInsurance Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteInsurance(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Insurance> getInsuranceByNamedQuery(String query, Map<String, Object> param) {
		List<Insurance> list = new ArrayList<Insurance>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getInsuranceByNamedQuery() ";
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
