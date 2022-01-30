package com.skyflight.serviceimpl;

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
import com.skyflight.model.Markup;
import com.skyflight.servicedao.MarkupDao;
@Repository("markupdao")
public class MarkupDaoImpl implements MarkupDao {
	
	private static final Logger logger_log = Logger.getLogger(MarkupDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Markup getMarkupByid(int id) {
		Markup list = new Markup();
		Session session = sessionFactory.openSession();
		try {
			list = session.get(Markup.class, id);
		} catch (Exception e) {
			String message = "getAllApi Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public boolean insertMarkup(Markup markup) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(markup);
			String message = "QUERY : Save insertMarkup|| ID: " + markup.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;

		} catch (Exception e) {
			flag = false;
			String message = "insertMarkup markup(Markup) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}

		return flag;
	}

	@Override
	public boolean updateMarkup(Markup markup) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(markup);
			String message = "QUERY : updatemarkup(Markup) || ID: " + markup.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updatePackage(Markup)  Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteMarkup(Integer id) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Markup> getMarkupByNamedQuery(String query, Map<String, Object> param) {
		List<Markup> list = new ArrayList<>();
		Session session = sessionFactory.openSession();

		try {
			Query query_q = session.getNamedQuery(query);
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
				list = query_q.list();
			}
			String message = "Method : getMarkupByNamedQuery() ";
			logger_log.warn(message);

		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getMarkupByNamedQuery:::::::::::::::::::" + e);

		} finally {

		}
		return list;
	}

	@Override
	public List<Markup> getAllAirline() {
		// TODO Auto-generated method stub
		return null;
	}

}
