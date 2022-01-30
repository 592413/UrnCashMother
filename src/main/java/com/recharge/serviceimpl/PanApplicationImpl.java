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


import com.recharge.model.PanApplication;

import com.recharge.servicedao.PanApplicationDao;

@Repository("panapplicationdao")
public class PanApplicationImpl implements PanApplicationDao {

	private static final Logger logger_log = Logger.getLogger(PanApplicationDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<PanApplication> getAllPanApplication() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PanApplication getPanApplicationById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertPanApplication(PanApplication PanApplication) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(PanApplication);
			String message = "QUERY : Save insertPanApplication|| ID: " + PanApplication.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertPanApplication(PanApplication) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updatePanApplication(PanApplication PanApplication) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(PanApplication);
			String message = "QUERY : updatePanApplication(PanApplication) || ID: " + PanApplication.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updatePanApplication(PanApplication)  Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deletePanApplication(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PanApplication> getPanApplicationByNamedQuery(String query, Map<String, Object> param) {
		List<PanApplication> list = new ArrayList<PanApplication>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getPanApplicationByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getPanApplicationByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
