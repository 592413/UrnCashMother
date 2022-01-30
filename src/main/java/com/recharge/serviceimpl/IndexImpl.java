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
import com.recharge.model.Indexx;
import com.recharge.servicedao.IndexDao;
import com.recharge.servicedao.ResellerDao;

@Repository("indexDao")
public class IndexImpl implements IndexDao{
	private static final Logger logger_log = Logger.getLogger(IndexDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public boolean insertIndex(Indexx index) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			System.out.println("indexx:::::::::::"+index);
			session.save(index);
			String message = "QUERY : Save Indexx|| ID: " ;
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insert indexx(indexx) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}
	
	@Override
	public List<Indexx> getIndexxByNamedQuery(String query, Map<String, Object> param) {
		List<Indexx> list = new ArrayList<Indexx>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getIndexxByNamedQuery() ";
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
