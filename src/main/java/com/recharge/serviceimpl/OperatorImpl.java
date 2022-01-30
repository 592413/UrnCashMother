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
import com.recharge.servicedao.OperatorDao;

@Repository("operatorDao")
public class OperatorImpl implements OperatorDao {
	
	private static final Logger logger_log = Logger.getLogger(OperatorDao.class);
	@Autowired private SessionFactory sessionFactory;

	@Override
	public List<Operator> getAllOperator() {
		List<Operator> list = new ArrayList<Operator>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Operator.class).list();
			
		} catch (Exception e) {
			String message = "getAllOperator Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Operator getOperatorByOperatorId(Integer operatorId) {
		Session session = sessionFactory.openSession();
		Operator operator = new Operator();
		try {
			operator = session.get(Operator.class, operatorId);
			

		} catch (Exception e) {
			operator = null;
			String message = "getOperatorByOperatorId(operatorId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return operator;
	}

	@Override
	public Integer insertOperator(Operator operator) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(operator);
			
			tx.commit();
		} catch (Exception e) {
			String message = "insertOperator(Operator) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return operator.getOperatorId();
	}

	@Override
	public boolean updateOperator(Operator operator) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(operator);
			
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
	public void deleteOperator(Integer operatorId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Operator> getOperatorByNamedQuery(String query, Map<String, Object> param) {
		List<Operator> list = new ArrayList<Operator>();
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
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Operator> getAllOpeartor() {
		List<Operator> list = new ArrayList<Operator>();
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("From Operator");
			list =(List<Operator>)query.list();
			logger_log.warn("getAllOpeartor:::::::::::"+list.size());
		}catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getAllOpeartor::::::::::::::"+e);
		}
		return list;
	}

}
