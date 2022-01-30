package com.recharge.yesbankserviceimpl;

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

import com.recharge.model.AEPSUserMap;
import com.recharge.yesbankmodel.YesbankCustomer;

import com.recharge.yesbankservicedao.YesbankCustomerDao;


@Repository("yesbankcustomerdao")
public class YesbankCustomerDaoImpl implements YesbankCustomerDao {
	
	private static final Logger logger_log = Logger.getLogger(YesbankCustomerDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<YesbankCustomer> getYesbankCustomer() {
		List<YesbankCustomer> list = new ArrayList<YesbankCustomer>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(YesbankCustomer.class).list();
			String message = "Method : getYesbankCustomer() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllApi Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public YesbankCustomer getYesbankCustomerById(String id) {
		Session session = sessionFactory.openSession();
		YesbankCustomer cust = new YesbankCustomer();
		try {
			cust = session.get(YesbankCustomer.class,id);
			String message = "Method : getApiByApId(apiId) || userId: " + cust.getId();
			logger_log.warn(message);

		} catch (Exception e) {
			cust = null;
			String message = "getApiByApId(apiId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return cust;
	}

	@Override
	public boolean insertYesbankCustomer(YesbankCustomer YesbankCustomer) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(YesbankCustomer);
			String message = "QUERY : Save YesbankCustomer|| ID: " + YesbankCustomer.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertYesbankCustomer(api) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateYesbankCustomer(YesbankCustomer YesbankCustomer) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(YesbankCustomer);
			String message = "QUERY : Update YesbankCustomer || ID: " + YesbankCustomer.getId();
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
	public List<YesbankCustomer> getYesbankCustomerByNamedQuery(String query, Map<String, Object> param) {
		List<YesbankCustomer> list = new ArrayList<YesbankCustomer>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getYesbankCustomerByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getYesbankCustomerByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
