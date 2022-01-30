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

import com.recharge.model.Api;
import com.recharge.model.Balancerequest;
import com.recharge.model.Balancetransafer;
import com.recharge.servicedao.BalancerequestDao;
import com.recharge.servicedao.BalancetransaferDao;

@Repository("balancetransaferDao")
public class BalancetransaferImpl implements BalancetransaferDao {
	private static final Logger logger_log = Logger.getLogger(BalancetransaferDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Balancetransafer> getAllBalanceTransfer() {
		List<Balancetransafer> list = new ArrayList<Balancetransafer>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Balancetransafer.class).list();
			String message = "Method : getAllBalanceTransfer() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllBalanceTransfer Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Balancetransafer getBalanceTransferByRequestId(Integer transferId) {
		Session session = sessionFactory.openSession();
		Balancetransafer balancetransfer = new Balancetransafer();
		try {
			balancetransfer = session.get(Balancetransafer.class, transferId);
			String message = "Method : getBalanceTransferByRequestId(transferId) || requestId: "+ balancetransfer.getTransferId();
			logger_log.warn(message);

		} catch (Exception e) {
			balancetransfer = null;
			String message = "getBalanceTransferByRequestId(transferId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return balancetransfer;
	}

	@Override
	public boolean insertBalanceTransfer(Balancetransafer balancetransafer) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(balancetransafer);
			String message = "QUERY : Save Balancerequest|| ID: " + balancetransafer.getTransferId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			String message = "insertBalanceTransfer(Balancetransafer) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateBalanceTransfer(Balancetransafer balancetransafer) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(balancetransafer);
			String message = "QUERY : updateBalanceTransfer || ID: " + balancetransafer.getTransferId();
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
	public void deleteBalanceTransfer(Integer transferId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Balancetransafer> getBalanceRequestByNamedQuery(String query, Map<String, Object> param) {
		List<Balancetransafer> list = new ArrayList<Balancetransafer>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getBalanceRequestByNamedQuery() ";
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
