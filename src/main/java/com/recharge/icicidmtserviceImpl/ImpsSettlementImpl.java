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

import com.recharge.icicidmtmodel.ImpsSettlement;
import com.recharge.icicidmtserviceDao.ImpsSettlementDao;




@Repository("impssettlementDao")
public class ImpsSettlementImpl implements ImpsSettlementDao {

	
	private static final Logger logger_log = Logger.getLogger(ImpsSettlementDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<ImpsSettlement> getAllImpsSettlement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImpsSettlement getImpsSettlementById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertImpsSettlement(ImpsSettlement ImpsSettlement) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(ImpsSettlement);
			String message = "QUERY : Save insertImpsSettlement|| ID: " + ImpsSettlement.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertImpsSettlement(ImpsSettlement) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateImpsSettlement(ImpsSettlement ImpsSettlement) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(ImpsSettlement);
			String message = "QUERY : updateImpsSettlement(ImpsSettlement) || ID: " + ImpsSettlement.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateImpsSettlement(ImpsSettlement)  Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean deleteImpsSettlement(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ImpsSettlement> getImpsSettlementByNamedQuery(String query, Map<String, Object> param) {
		List<ImpsSettlement> list = new ArrayList<ImpsSettlement>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getImpsSettlementByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getImpsSettlementByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
