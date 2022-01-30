package com.recharge.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.recharge.model.CardWalletRequest;
import com.recharge.servicedao.CardWalletRequestDao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recharge.model.BBPSLIST;
import com.recharge.model.Balancerequest;
import com.recharge.servicedao.BBPsListDao;
import com.recharge.servicedao.BalancetransaferDao;
@Repository("CardWalletRequestDao")
public class CardWalletRequestImpl implements CardWalletRequestDao {
	private static final Logger logger_log = Logger.getLogger(CardWalletRequestDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public List<CardWalletRequest> getAllCardWalletRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CardWalletRequest getCardWalletRequestMapById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertCardholder(CardWalletRequest CardWalletRequest) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(CardWalletRequest);
			String message = "QUERY : Save CardWalletRequest|| ID: " + CardWalletRequest.getCardnumber();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			String message = "insertCardWalletRequest(CardWalletRequest) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateCardWalletRequest(CardWalletRequest CardWalletRequest) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(CardWalletRequest);
			String message = "QUERY : updateBalanceRequest || ID: " + CardWalletRequest.getCardnumber();
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
	public void deleteCardWalletRequest(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CardWalletRequest> getCardWalletRequestByNamedQuery(String query, Map<String, Object> param) {
		List<CardWalletRequest> list = new ArrayList<CardWalletRequest>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getCardWalletRequestByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
