package com.recharge.serviceimpl;


import com.recharge.servicedao.ImpsBankDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recharge.model.AEPSCommission;
import com.recharge.model.Imps_BankDetails;

@Repository("impsBankDao")
public class ImpsBankImpl implements ImpsBankDao{
	private static final Logger logger_log = Logger.getLogger(ImpsBankDao.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Imps_BankDetails> getAllBank() {
		List<Imps_BankDetails> list = new ArrayList<Imps_BankDetails>();
		Session session = sessionFactory.openSession();
		try {
			Criteria cr =session.createCriteria(Imps_BankDetails.class);
			cr.addOrder(Order.asc("bank_Name"));
			list = cr.list();
			String message = "Method : getAllBank() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllBank Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public Imps_BankDetails getBankbyId(String bankid){
		Session session = sessionFactory.openSession();
		Imps_BankDetails impsbank=new Imps_BankDetails();
		try{
			impsbank=session.get(Imps_BankDetails.class, bankid);
		}catch(Exception e){
			impsbank = null;
			String message = "getBankbyId(bankid) Error Details :- " + e;
			logger_log.error(message);
		}
		return impsbank;
	}

	@Override
	public List<Imps_BankDetails> getImpsBankDetailsByNamedQuery(String query, Map<String, Object> param) {
		List<Imps_BankDetails> list = new ArrayList<Imps_BankDetails>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getImpsBankDetailsByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getImpsBankDetailsByNamedQuery Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
