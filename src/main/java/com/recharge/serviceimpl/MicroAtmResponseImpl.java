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
import com.recharge.model.MicroAtmResponse;
import com.recharge.servicedao.MicroAtmLogDao;
import com.recharge.servicedao.MicroAtmResponseDao;
@Repository("microatmresponseDao")
public class MicroAtmResponseImpl implements MicroAtmResponseDao {
	private static final Logger log = Logger.getLogger(MicroAtmResponseDao.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean insertMicroAtmResponse(MicroAtmResponse MicroAtmResponse) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(MicroAtmResponse);
			String message = "QUERY : Save insertMicroAtmResponse|| ID: " + MicroAtmResponse.getId();
			log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertMicroAtmResponse(MicroAtmResponse) Error Details :- " + e;
			log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateMicroAtmResponse(MicroAtmResponse MicroAtmResponse) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(MicroAtmResponse);
			String message = "QUERY : updateAEPSLog(AEPSLog) || ID: " + MicroAtmResponse.getId();
			log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateMicroAtmResponse(MicroAtmResponse)  Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public List<MicroAtmResponse> getMicroAtmResponseByNamedQuery(String query, Map<String, Object> param) {
		List<MicroAtmResponse> list = new ArrayList<MicroAtmResponse>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getMicroAtmResponseByNamedQuery() ";
			log.warn(message);
		} catch (Exception e) {
			String message = "getMicroAtmResponseByNamedQuery Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public MicroAtmResponse getMicroAtmResponseById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

}
