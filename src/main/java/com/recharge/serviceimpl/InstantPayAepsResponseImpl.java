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

import com.recharge.model.InstantPayAepsResponse;
import com.recharge.servicedao.InstantPayAepsResponseDao;


@Repository("instantpayaepsresdao")
public class InstantPayAepsResponseImpl implements InstantPayAepsResponseDao {
	private static final Logger logger_log = Logger.getLogger(InstantPayAepsResponseDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;


	


	@Override
	public boolean insertInstantPayAepsResponse(InstantPayAepsResponse instantaeps) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(instantaeps);
			String message = "QUERY : Save insertAEPSUserMap|| ID: " + instantaeps.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertInstantPayAepsResponse(instantaeps) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateInstantPayAepsResponse(InstantPayAepsResponse instantaeps) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public List<InstantPayAepsResponse> getInstantPayAepsResponseByNamedQuery(String query, Map<String, Object> param) {
		List<InstantPayAepsResponse> list = new ArrayList<InstantPayAepsResponse>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getInstantPayAepsResponseByNamedQuery()";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getInstantPayAepsResponseByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public InstantPayAepsResponse getInstantPayAepsResponseById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

}
