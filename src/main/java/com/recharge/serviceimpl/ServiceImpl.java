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

import com.recharge.model.Balancerequest;
import com.recharge.model.Service;
import com.recharge.servicedao.ServiceDao;

public class ServiceImpl implements ServiceDao {
	
	private static final Logger logger_log = Logger.getLogger(ServiceDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Service> getAllService() {
		List<Service> list = new ArrayList<Service>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Service.class).list();
			String message = "Method : getAllService() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllService Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Service getServiceByServiceType(Integer id) {
		Session session = sessionFactory.openSession();
		Service service = new Service();
		try {
			service = session.get(Service.class, id);
			String message = "Method : getServiceByServiceType(id) || requestId: "+ service.getServiceType();
			logger_log.warn(message);

		} catch (Exception e) {
			service = null;
			String message = "getServiceByServiceType(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return service;
	}

	@Override
	public Integer insertService(Service service) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(service);
			String message = "QUERY : Save Service|| ID: " + service.getServiceType();
			logger_log.warn(message);
			tx.commit();
		} catch (Exception e) {
			String message = "insertService(Service) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return service.getServiceType();
	}

	@Override
	public boolean updateService(Service service) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(service);
			String message = "QUERY : updateBalanceRequest || ID: " + service.getServiceType();
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
	public void deleteService(Integer serviceType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Service> getServiceByNamedQuery(String query, Map<String, Object> param) {
		List<Service> list = new ArrayList<Service>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getServiceByNamedQuery() ";
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
