package com.recharge.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.recharge.model.OpenAccount;
import com.recharge.servicedao.OpenAccountDao;

@Repository("OpenAccountDao")
public class OpenAccountImpl implements OpenAccountDao{
	private static final Logger logger_log = Logger.getLogger(OpenAccountDao.class);
	
	@Autowired private SessionFactory sessionFactory;
	
	
	@Override
	public List<OpenAccount> getAllOpenAccount() {
		List<OpenAccount> list = new ArrayList<OpenAccount>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(OpenAccount.class).list();
			
		} catch (Exception e) {
			String message = "getAllOpenAccount Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public boolean insertOpenAccount(OpenAccount OpenAccount) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(OpenAccount);
			
			tx.commit();			
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertOpenAccount(OpenAccount) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}
	@Override
	public boolean deleteOpenAccount(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			String sql = "delete from OpenAccount where id=:id";
			Query query = session.createQuery(sql);
			 query.setParameter("id", id);

			int result = query.executeUpdate();
			//session.delete(id);
			
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

}
