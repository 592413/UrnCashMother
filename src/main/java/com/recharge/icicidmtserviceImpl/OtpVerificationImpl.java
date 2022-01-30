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

import com.recharge.icicidmtmodel.OtpVerification;
import com.recharge.icicidmtmodel.Remitter;
import com.recharge.icicidmtserviceDao.OtpVerificationdao;

@Repository("otpverificationdao")
public class OtpVerificationImpl implements OtpVerificationdao {
	private static final Logger logger_log = Logger.getLogger(OtpVerificationdao.class);
	@Autowired private SessionFactory sessionFactory;

	@Override
	public boolean insertOtpVerification(OtpVerification OtpVerification) {
		boolean flag=false;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(OtpVerification);
			String message = "QUERY : insertOtpVerification|| ID: " + OtpVerification.getId();
			logger_log.warn(message);
			tx.commit();
			flag=true;
		} catch (Exception e) {
			String message = "insertOtpVerification(OtpVerification) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateOtpVerification(OtpVerification OtpVerification) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOtpVerification(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<OtpVerification> getOtpVerificationByNamedQuery(String query, Map<String, Object> param) {
		List<OtpVerification> list = new ArrayList<OtpVerification>();
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
			String message = "Method : getOtpVerificationByNamedQuery() ";
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
