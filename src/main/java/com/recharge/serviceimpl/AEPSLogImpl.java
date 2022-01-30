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

import com.recharge.model.AEPSCommission;
import com.recharge.model.AEPSLog;
import com.recharge.model.Api;
import com.recharge.servicedao.AEPSBankDetailDao;
import com.recharge.servicedao.AEPSLogDao;



@Repository("aepslogdao")
public class AEPSLogImpl implements AEPSLogDao {
	
	private static final Logger log = Logger.getLogger(AEPSLogDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public AEPSLog getAEPSLogById(int Id) {
		Session session = sessionFactory.openSession();
		AEPSLog AEPSLog = new AEPSLog();
		try {
			AEPSLog = session.get(AEPSLog.class, Id);
			

		} catch (Exception e) {
			AEPSLog = null;
			String message = "getAEPSLogById(Id) Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return AEPSLog;
	}
	
	
	@Override
	public boolean insertAEPSLog(AEPSLog AEPSLog) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(AEPSLog);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertAEPSCommission(AEPSCommission) Error Details :- " + e;
			log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateAEPSLog(AEPSLog AEPSLog) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(AEPSLog);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateAEPSLog(AEPSLog)  Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public List<AEPSLog> getAEPSLogByNamedQuery(String query, Map<String, Object> param) {
		List<AEPSLog> list = new ArrayList<AEPSLog>();
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
			String message = "getAEPSLogByNamedQuery Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
