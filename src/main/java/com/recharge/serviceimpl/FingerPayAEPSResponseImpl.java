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

import com.recharge.model.FingerPayAEPSResponse;
import com.recharge.servicedao.FingerPayAEPSResponseDao;


@Repository("fingerpayaepsresponseDao")
public class FingerPayAEPSResponseImpl implements FingerPayAEPSResponseDao {
	
	private static final Logger logger_log = Logger.getLogger(FingerPayAEPSResponseDao.class);

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public List<FingerPayAEPSResponse> getAllFingerPayAEPSResponse() {
		List<FingerPayAEPSResponse> list = new ArrayList<FingerPayAEPSResponse>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(FingerPayAEPSResponse.class).list();
			String message = "Method : getAllApitrace() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllApitrace Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public FingerPayAEPSResponse getFingerPayAEPSResponseById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertFingerPayAEPSResponse(FingerPayAEPSResponse FingerPayAEPSResponse) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(FingerPayAEPSResponse);
			String message = "QUERY : Save insertFingerPayAEPSResponse|| ID: " + FingerPayAEPSResponse.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertFingerPayAEPSResponse(FingerPayAEPSResponse) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateFingerPayAEPSResponse(FingerPayAEPSResponse FingerPayAEPSResponse) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(FingerPayAEPSResponse);
			String message = "QUERY : updateFingerPayAEPSResponse(FingerPayAEPSResponse) || ID: " + FingerPayAEPSResponse.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateFingerPayAEPSResponse(FingerPayAEPSResponse)  Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteFingerPayAEPSResponse(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FingerPayAEPSResponse> getFingerPayAEPSResponseByNamedQuery(String query, Map<String, Object> param) {
		List<FingerPayAEPSResponse> list = new ArrayList<FingerPayAEPSResponse>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getFingerPayAEPSResponseByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getFingerPayAEPSResponseByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
