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

import com.recharge.model.SettlementBalance;
import com.recharge.model.Signinhistory;
import com.recharge.servicedao.SettlementBalanceDao;
import com.recharge.servicedao.TransactiondetailsDao;



@Repository("settlementbalancedao")
public class SettlementBalanceImpl implements SettlementBalanceDao {
	
	private static final Logger logger_log = Logger.getLogger(SettlementBalanceDao.class);

	@Autowired private SessionFactory sessionFactory;

	@Override
	public List<SettlementBalance> getAllSettlementBalance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SettlementBalance getSettlementBalanceById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertSettlementBalance(SettlementBalance SettlementBalance) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(SettlementBalance);
			tx.commit();
			
			flag = true;
			logger_log.error("insertSettlementBalance::::::::::::::::::::::"+SettlementBalance.getSettlementbalance());
		} catch (Exception e) {
			flag = false;
			String message = "insertSettlementBalance(SettlementBalance) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateSettlementBalance(SettlementBalance SettlementBalance) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(SettlementBalance);
			tx.commit();
			flag = true;
			logger_log.error("updateSettlementBalance::::::::::::::::::::::"+SettlementBalance.getSettlementbalance());
		} catch (Exception e) {
			tx.rollback();
			String message = "updateSettlementBalance(SettlementBalance) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public void deleteSettlementBalance(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SettlementBalance> getSettlementBalanceByNamedQuery(String query, Map<String, Object> param) {
		List<SettlementBalance> list = new ArrayList<SettlementBalance>();
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
			String message = "getSettlementBalanceByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
