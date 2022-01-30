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

import com.recharge.model.Bankdetails;
import com.recharge.servicedao.BankdetailsDao;
import com.recharge.utill.GenerateRandomNumber;

@Repository("bankdetailsDao")
public class BankdetailsImpl implements BankdetailsDao {
	private static final Logger logger_log = Logger.getLogger(BankdetailsDao.class);

	@Autowired private SessionFactory sessionFactory;
	@Override
	public List<Bankdetails> getAllBankdetails() {
		List<Bankdetails> list = new ArrayList<>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Bankdetails.class).list();
			String message = "Method : getAllBankdetails() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllBankdetails Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Bankdetails getBankdetailsById(Integer id) {
		Session session = sessionFactory.openSession();
		Bankdetails bankdetails = new Bankdetails();
		try {
			bankdetails = session.get(Bankdetails.class, id);
			String message = "Method : getBankdetailsById(id) || requestId: "+ bankdetails.getId();
			logger_log.warn(message);

		} catch (Exception e) {
			bankdetails = null;
			String message = "getBankdetailsById(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return bankdetails;
	}

	@Override
	public boolean insertBankdetails(Bankdetails bankdetails) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(bankdetails);
			String message = "QUERY : Save Bankdetails|| ID: " + bankdetails.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertBankdetails(bankdetails) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateBankdetails(Bankdetails bankdetails) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(bankdetails);
			String message = "QUERY : updateBankdetails(bankdetails) || ID: " + bankdetails.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = " updateBankdetails(bankdetails) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean deleteBankdetails(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.delete(session.get(Bankdetails.class, id));
			String message = "TIME:-" + GenerateRandomNumber.getCurrentDate() + " "
					+ GenerateRandomNumber.getCurrentTime() + "|| QUERY : Delete deleteBankdetails || ID: " + id;
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "Error In time of deleteBankdetails, || Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public List<Bankdetails> getBankdetailsByNamedQuery(String query, Map<String, Object> param) {
		List<Bankdetails> list = new ArrayList<>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getBankdetailsByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getBankdetailsByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
