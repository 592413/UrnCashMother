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

import com.recharge.model.MicroAtmResponse;
import com.recharge.model.MicroAtmResponseNew;
import com.recharge.servicedao.MicroAtmResponseDao;
import com.recharge.servicedao.MicroAtmResponseNewDao;
@Repository("microatmresponsenew")
public class MicroAtmResponseNewImpl implements MicroAtmResponseNewDao {

	
	private static final Logger log = Logger.getLogger(MicroAtmResponseDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public boolean insertMicroAtmResponseNew(MicroAtmResponseNew MicroAtmResponseNew) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(MicroAtmResponseNew);
			String message = "QUERY : Save insertMicroAtmResponse|| ID: " + MicroAtmResponseNew.getId();
			log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertMicroAtmResponseNew(MicroAtmResponseNew) Error Details :- " + e;
			log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateMicroAtmResponseNew(MicroAtmResponseNew MicroAtmResponseNew) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(MicroAtmResponseNew);
			String message = "QUERY : updateMicroAtmResponseNew(MicroAtmResponseNew) || ID: " + MicroAtmResponseNew.getId();
			log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateMicroAtmResponseNew(MicroAtmResponseNew)  Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public List<MicroAtmResponseNew> getMicroAtmResponseNewByNamedQuery(String query, Map<String, Object> param) {
		List<MicroAtmResponseNew> list = new ArrayList<MicroAtmResponseNew>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getMicroAtmResponseNewByNamedQuery() ";
			log.warn(message);
		} catch (Exception e) {
			String message = "getMicroAtmResponseNewByNamedQuery Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public MicroAtmResponseNew getMicroAtmResponseNewById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

}
