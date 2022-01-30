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

import com.recharge.model.User;
import com.recharge.servicedao.UserDao;

@Repository("userDao")
public class UserImpl implements UserDao {
	private static final Logger logger_log = Logger.getLogger(UserDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> getAllUser() {
		List<User> list = new ArrayList<User>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(User.class).list();
			
		} catch (Exception e) {
			String message = "getAllUser Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public User getUserByUserId(String userId) {
		Session session = sessionFactory.openSession();
		User user = null;
		try {
			user = session.get(User.class, userId);
			
		} catch (Exception e) {
			user = null;
			String message = "getUserByUserId(userId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public boolean insertUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(user);
			
			tx.commit();			
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertUser(user) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(user);
			
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
	public void deleteUser(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getUserByNamedQuery(String query, Map<String, Object> param) {
		List<User> list = new ArrayList<User>();
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
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public boolean updateUserStatus(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(user);
			
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

	public static void main(String[] args) {
		UserDao obj = new UserImpl();
		List<User>  list = obj.getAllUser();
		System.out.println(list.size());
	}
}
