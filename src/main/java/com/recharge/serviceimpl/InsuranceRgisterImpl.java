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
import com.recharge.model.InsuranceRegister;
import com.recharge.model.User;
import com.recharge.servicedao.InsuranceRegisterDao;
@Repository("InsuranceRegisterDao")
public class InsuranceRgisterImpl implements InsuranceRegisterDao{

	
	private static final Logger log = Logger.getLogger(InsuranceRegisterDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean insertInsuranceRegister(InsuranceRegister InsuranceRegister) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(InsuranceRegister);
			String message = "QUERY : Save insertInsuranceRegister|| ID: " + InsuranceRegister.getUserId();
			log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertInsuranceRegister(InsuranceRegister) Error Details :- " + e;
			log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public List<InsuranceRegister> getInsuranceRegisterByNamedQuery(String query, Map<String, Object> param) {
		List<InsuranceRegister> list = new ArrayList<InsuranceRegister>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getInsuranceRegisterByNamedQuery() ";
			log.warn(message);
		} catch (Exception e) {
			String message = "Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return list;
	}
	
	
	@Override
	public InsuranceRegister getInsuranceRegisterByUserId(String userId) {
		Session session = sessionFactory.openSession();
		InsuranceRegister user = null;
		try {
			user = session.get(InsuranceRegister.class, userId);
			/*String message = "Method : getUserByUserId(userId) || userId: "+ user.getUserId();
			logger_log.warn(message);*/
		} catch (Exception e) {
			user = null;
			String message = "getInsuranceRegisterByUserId(userId) Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return user;
	}
	
}
