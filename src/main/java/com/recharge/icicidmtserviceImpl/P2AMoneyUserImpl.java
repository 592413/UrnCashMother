package com.recharge.icicidmtserviceImpl;

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

import com.recharge.icicidmtmodel.P2AMoneyUser;
import com.recharge.icicidmtserviceDao.P2AMoneyUserDao;



@Repository("P2AMoneyUserDao")
public class P2AMoneyUserImpl implements P2AMoneyUserDao {

	private static final Logger logger_log = Logger.getLogger(P2AMoneyUserDao.class);
	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public List<P2AMoneyUser> getAllP2AMoneyUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public P2AMoneyUser getP2AMoneyUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertP2AMoneyUser(P2AMoneyUser pmu) {
		boolean flag=false;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(pmu);
			String message = "QUERY : Save Operator|| ID: " + pmu.getId();
			logger_log.warn(message);
			tx.commit();
			flag=true;
		} catch (Exception e) {
			String message = "insertOperator(Operator) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateP2AMoneyUser(P2AMoneyUser pmu) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(pmu);
			String message = "QUERY : updateOperator || ID: " + pmu.getId();
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
	public boolean deleteP2AMoneyUser(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<P2AMoneyUser> getP2AMoneyUserByNamedQuery(String query, Map<String, Object> param) {
		List<P2AMoneyUser> list = new ArrayList<P2AMoneyUser>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getP2AMoneyUserByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getP2AMoneyUserByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public boolean deleteP2AMoneyUser(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
