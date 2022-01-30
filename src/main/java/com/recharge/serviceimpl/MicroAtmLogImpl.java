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

import com.recharge.model.MicroAtmLog;
import com.recharge.servicedao.MicroAtmLogDao;
@Repository("microatmlogdao")
public class MicroAtmLogImpl implements MicroAtmLogDao {
	private static final Logger log = Logger.getLogger(MicroAtmLogDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean insertMicroAtmLog(MicroAtmLog MicroAtmLog) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(MicroAtmLog);
			String message = "QUERY : Save insertMicroAtmLog|| ID: " + MicroAtmLog.getId();
			log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertMicroAtmLog(MicroAtmLog) Error Details :- " + e;
			log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateMicroAtmLog(MicroAtmLog MicroAtmLog) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(MicroAtmLog);
			String message = "QUERY : updateAEPSLog(AEPSLog) || ID: " + MicroAtmLog.getId();
			log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateMicroAtmLog(MicroAtmLog)  Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public List<MicroAtmLog> getMicroAtmLogByNamedQuery(String query, Map<String, Object> param) {
		List<MicroAtmLog> list = new ArrayList<MicroAtmLog>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getMicroAtmLogByNamedQuery() ";
			log.warn(message);
		} catch (Exception e) {
			String message = "getMicroAtmLogByNamedQuery Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public MicroAtmLog getMicroAtmLogById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

}
