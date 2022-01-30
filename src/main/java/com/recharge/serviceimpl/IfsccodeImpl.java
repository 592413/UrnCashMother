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
import com.recharge.model.Ifsccode;
import com.recharge.servicedao.ApiDao;
import com.recharge.servicedao.IfsccodeDao;

@Repository("ifsccodeDao")
public class IfsccodeImpl implements IfsccodeDao {
	private static final Logger logger_log = Logger.getLogger(IfsccodeDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Ifsccode> getAllIfsccode() {
		List<Ifsccode> list = new ArrayList<Ifsccode>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Ifsccode.class).list();
			String message = "Method : getAllIfsccode() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllIfsccode Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Ifsccode getIfsccodeById(String id) {
		Session session = sessionFactory.openSession();
		Ifsccode ifsccode = new Ifsccode();
		try {
			ifsccode = session.get(Ifsccode.class, id);
			String message = "Method : getIfsccodeById || id: " + ifsccode.getId();
			logger_log.warn(message);

		} catch (Exception e) {
			ifsccode = null;
			String message = "getIfsccodeById(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return ifsccode;
	}

	@Override
	public boolean insertIfsccode(Ifsccode ifsccode) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(ifsccode);
			String message = "QUERY : Save ifsccode|| ID: " + ifsccode.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertIfsccode(ifsccode) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateIfsccode(Ifsccode ifsccode) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(ifsccode);
			String message = "QUERY : Update Ifsccode || ID: " + ifsccode.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "Update Ifsccode Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteIfsccode(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Ifsccode> getIfsccodeByNamedQuery(String query, Map<String, Object> param) {
		List<Ifsccode> list = new ArrayList<Ifsccode>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getIfsccodeByNamedQuery() ";
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
