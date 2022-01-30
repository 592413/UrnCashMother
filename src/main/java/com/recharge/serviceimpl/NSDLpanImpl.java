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
import com.recharge.model.NSDLPan;
import com.recharge.servicedao.NSDLpanDao;
@Repository("NSDLpanDao")
public class NSDLpanImpl implements NSDLpanDao{
	private static final Logger logger_log = Logger.getLogger(NSDLpanDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public NSDLPan getNSDLPanById(int id) {
		Session session = sessionFactory.openSession();
		NSDLPan NSDLPan = new NSDLPan();
		try {
			NSDLPan = session.get(NSDLPan.class, id);
			String message = "Method : getNSDLPanById(id) || userId: " + NSDLPan.getId();
			logger_log.warn(message);

		} catch (Exception e) {
			NSDLPan = null;
			String message = "getNSDLPanById(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return NSDLPan;
	}

	@Override
	public boolean insertNSDLPan(NSDLPan NSDLPan) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(NSDLPan);
			String message = "QUERY : Save NSDLPan|| ID: " + NSDLPan.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertNSDLPan(id) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateNSDLPan(NSDLPan NSDLPan) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(NSDLPan);
			String message = "QUERY : Update NSDLPan || ID: " + NSDLPan.getId();
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
	public List<NSDLPan> getNSDLPanByNamedQuery(String query, Map<String, Object> param) {
		List<NSDLPan> list = new ArrayList<NSDLPan>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getNSDLPanByNamedQuery() ";
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
