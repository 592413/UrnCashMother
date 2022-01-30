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

import com.recharge.model.SettlementReport;
import com.recharge.servicedao.SettlementReportDao;




@Repository("settlementreportdao")
public class SettlementReportImpl implements SettlementReportDao {
	
	private static final Logger logger_log = Logger.getLogger(SettlementReportDao.class);

	@Autowired private SessionFactory sessionFactory;

	@Override
	public List<SettlementReport> getAllSettlementReport() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SettlementReport getSettlementReportById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertSettlementReport(SettlementReport SettlementReport) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(SettlementReport);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertSettlementReport(SettlementReport) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateSettlementReport(SettlementReport SettlementReport) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(SettlementReport);
			
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
	public void deleteSettlementReport(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SettlementReport> getSettlementReportByNamedQuery(String query, Map<String, Object> param) {
		List<SettlementReport> list = new ArrayList<SettlementReport>();
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
			String message = "getSettlementReportByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
