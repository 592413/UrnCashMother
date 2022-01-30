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

import com.recharge.model.Api;
import com.recharge.model.Impscommission;
import com.recharge.servicedao.ApiDao;
import com.recharge.servicedao.ImpscommissionDao;

@Repository("impscommissionDao")
public class ImpscommissionImpl implements ImpscommissionDao {
	private static final Logger logger_log = Logger.getLogger(ImpscommissionDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Impscommission> getAllImpscommission() {
		List<Impscommission> list = new ArrayList<Impscommission>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Impscommission.class).list();
			String message = "Method : getAllImpscommission() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllImpscommission Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Impscommission getImpscommissionById(Integer Id) {
		Session session = sessionFactory.openSession();
		Impscommission impscommission = new Impscommission();
		try {
			impscommission = session.get(Impscommission.class, Id);
			String message = "Method : getImpscommissionById(Id) || userId: " + impscommission.getId();
			logger_log.warn(message);

		} catch (Exception e) {
			impscommission = null;
			String message = "getImpscommissionById(Id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return impscommission;
	}

	@Override
	public boolean insertImpscommission(Impscommission impscommission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(impscommission);
			String message = "QUERY : Save Impscommission|| ID: " + impscommission.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertImpscommission(impscommission) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateImpscommission(Impscommission impscommission) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(impscommission);
			String message = "QUERY : Update Impscommission || ID: " + impscommission.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateImpscommission Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteImpscommission(int id) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Impscommission> getImpscommissionByNamedQuery(String query, Map<String, Object> param) {
		Impscommission ip = null;
		List<Impscommission> list = new ArrayList<Impscommission>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			if (list.size() == 0) {
				double slab1 = 10.0;
				double slab2 = 1000.0;
				for (int i = 0; i < 5; i++) {
					ip = new Impscommission();
					ip.setCharge(0.0);
					ip.setSlab1(slab1);
					ip.setSlab2(slab2);
					ip.setId(0);
					ip.setUserId((String) param.get("userId"));
					if (slab1 == 10.0) {
						slab1 = slab1 + 991;
					} else {
						slab1 = slab1 + 1000;
					}
					slab2 = slab2 + 1000;
					list.add(ip);
				}
			}
			
			String message = "Method : getImpscommissionByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getImpscommissionByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
