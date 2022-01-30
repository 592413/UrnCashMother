package com.recharge.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recharge.model.Api;
import com.recharge.model.User;
import com.recharge.model.UserBankDetails;
import com.recharge.model.Userservice;
import com.recharge.servicedao.UserBankDetailsDao;
import com.recharge.servicedao.UserserviceDao;


@Repository("userbankdetailsdao")
public class UserBankDetailsImpl implements UserBankDetailsDao {
	private static final Logger logger_log = Logger.getLogger(UserBankDetailsDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<UserBankDetails> getAllUserBankDetails() {
		List<UserBankDetails> list = new ArrayList<UserBankDetails>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(UserBankDetails.class).addOrder(Order.desc("id")).list();
			String message = "Method : getAllUserBankDetails() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllUserBankDetails Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public UserBankDetails getUserByUserBankDetails(int id) {
		Session session = sessionFactory.openSession();
		UserBankDetails api = new UserBankDetails();
		try {
			api = session.get(UserBankDetails.class, id);
			

		} catch (Exception e) {
			api = null;
			String message = "getUserByUserBankDetails(apiId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return api;
	}

	@Override
	public boolean insertUserBankDetails(UserBankDetails UserBankDetails) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(UserBankDetails);
			String message = "QUERY : Save UserBankDetails|| ID: " + UserBankDetails.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			String message = "insertUserBankDetails(UserBankDetails) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateUserBankDetails(UserBankDetails UserBankDetails) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(UserBankDetails);
			String message = "QUERY : updateUserBankDetails || ID: " + UserBankDetails.getId();
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
	public void deleteUserBankDetails(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserBankDetails> getUserBankDetailsByNamedQuery(String query, Map<String, Object> param) {
		List<UserBankDetails> list = new ArrayList<UserBankDetails>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getUserBankDetailsByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public boolean updateUserBankDetails(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
