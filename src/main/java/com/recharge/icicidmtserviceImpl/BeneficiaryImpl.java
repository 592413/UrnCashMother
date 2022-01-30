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

import com.recharge.icicidmtmodel.Beneficiary;
import com.recharge.icicidmtserviceDao.BeneficiaryDao;

@Repository("beneficiaryDao")
public class BeneficiaryImpl implements BeneficiaryDao {
	
	private static final Logger logger_log = Logger.getLogger(BeneficiaryDao.class);
	@Autowired private SessionFactory sessionFactory;

	@Override
	public List<Beneficiary> getAllBeneficiary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Beneficiary getBeneficiaryById(int id) {
		Session session = sessionFactory.openSession();
		Beneficiary api = new Beneficiary();
		try {
			api = session.get(Beneficiary.class, id);
			
		} catch (Exception e) {
			api = null;
			String message = "getBeneficiaryById(Id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return api;
	}

	@Override
	public boolean insertBeneficiary(Beneficiary Beneficiary) {
		boolean flag=false;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(Beneficiary);
			
			tx.commit();
			flag=true;
		} catch (Exception e) {
			String message = "insertBeneficiary(Remitter) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateBeneficiary(Beneficiary Beneficiary) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(Beneficiary);
			
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
	public boolean deleteBeneficiary(Beneficiary Beneficiary) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			
			session.delete(Beneficiary);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "deleteAEPSUserMap  Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;

	}

	@Override
	public List<Beneficiary> getBeneficiaryByNamedQuery(String query, Map<String, Object> param) {
		List<Beneficiary> list = new ArrayList<Beneficiary>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					System.out.println(entry.getKey());
					System.out.println(entry.getValue());
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

}
