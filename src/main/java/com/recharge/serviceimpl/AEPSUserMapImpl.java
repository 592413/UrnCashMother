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

import com.recharge.model.AEPSUserMap;
import com.recharge.servicedao.AEPSUserMapDao;


@Repository("aepsuserdao")
public class AEPSUserMapImpl implements AEPSUserMapDao {
	private static final Logger logger_log = Logger.getLogger(AEPSUserMapDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<AEPSUserMap> getAllAEPSUserMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AEPSUserMap getAEPSUserMapById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertAEPSUserMap(AEPSUserMap AEPSUserMap) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(AEPSUserMap);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertAEPSUserMap(AEPSUserMap) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateAEPSUserMap(AEPSUserMap AEPSUserMap) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(AEPSUserMap);;
			String message = "QUERY : Save Balancerequest|| ID: " + AEPSUserMap.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			String message = "updateAEPSUserMap(AEPSUser) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteAEPSUserMap(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AEPSUserMap> getAEPSUserMapByNamedQuery(String query, Map<String, Object> param) {
		List<AEPSUserMap> list = new ArrayList<AEPSUserMap>();
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
			String message = "getAEPSUserMapByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
