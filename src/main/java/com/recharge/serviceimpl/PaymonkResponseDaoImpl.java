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

import com.recharge.model.Operator;
import com.recharge.model.PaymonkResponse;
import com.recharge.servicedao.PaymonkResponseDao;



@Repository("paymonkresponsedao")
public class PaymonkResponseDaoImpl implements PaymonkResponseDao {
	
	private static final Logger logger_log = Logger.getLogger(PaymonkResponseDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PaymonkResponse> getAllPaymonkResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymonkResponse getPaymonkResponseById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertPaymonkResponse(PaymonkResponse PaymonkResponse) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(PaymonkResponse);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertPaymonkResponse(PaymonkResponse) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updatePaymonkResponse(PaymonkResponse PaymonkResponse) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deletePaymonkResponse(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PaymonkResponse> getPaymonkResponseByNamedQuery(String query, Map<String, Object> param) {
		List<PaymonkResponse> list = new ArrayList<PaymonkResponse>();
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
