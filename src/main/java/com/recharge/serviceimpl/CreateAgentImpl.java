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
import com.recharge.model.CreateAgent;
import com.recharge.servicedao.CreateAgentDao;

@Repository("createAgentDao")
public class CreateAgentImpl implements CreateAgentDao{
	private static final Logger logger_log = Logger.getLogger(CreateAgentDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean insertagent(CreateAgent creatagent) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(creatagent);
			String message = "QUERY : Save User|| ID: " ;
			logger_log.warn(message);
			tx.commit();			
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertagent(creatagent) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}
	
	@Override
	public CreateAgent getagentById(Integer id) {
		Session session = sessionFactory.openSession();
		CreateAgent CreateAgent = new CreateAgent();
		try {
			CreateAgent = session.get(CreateAgent.class, id);
			String message = "Method : getAllotRequestById(id) || requestId: ";
			logger_log.warn(message);

		} catch (Exception e) {
			CreateAgent = null;
			String message = "getAllotRequestById(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return CreateAgent;
	}
	
	@Override
	public boolean updateagent(CreateAgent creatagent) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(creatagent);
			String message = "QUERY : Update updateagent || ID: ";
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
	public List<CreateAgent> getagentDetailsByNamedQuery(String query, Map<String, Object> param) {
		List<CreateAgent> list = new ArrayList<CreateAgent>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					System.out.println(entry.getKey());
					System.out.println(entry.getValue());
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getagentDetailsByNamedQuery() ";
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
