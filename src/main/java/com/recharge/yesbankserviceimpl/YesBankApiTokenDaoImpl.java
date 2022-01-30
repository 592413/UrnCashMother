package com.recharge.yesbankserviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.recharge.model.Api;
import com.recharge.servicedao.AEPSBankDetailDao;
import com.recharge.yesbankmodel.YesBankApiToken;
import com.recharge.yesbankservicedao.YesBankApiTokenDao;
@Repository("yesbanktokendao")
public class YesBankApiTokenDaoImpl implements YesBankApiTokenDao {
	
	private static final Logger logger_log = Logger.getLogger(YesBankApiTokenDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<YesBankApiToken> getYesBankApiToken() {
		List<YesBankApiToken> list = new ArrayList<YesBankApiToken>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(YesBankApiToken.class).list();
			String message = "Method : getYesBankApiToken() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllApi Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public YesBankApiToken getYesBankApiTokenById(String id) {
		Session session = sessionFactory.openSession();
		YesBankApiToken api = new YesBankApiToken();
		try {
			api = session.get(YesBankApiToken.class,id);
			String message = "Method : getApiByApId(apiId) || userId: " + api.getId();
			logger_log.warn(message);

		} catch (Exception e) {
			api = null;
			String message = "getApiByApId(apiId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return api;
	}

	@Override
	public boolean insertYesBankApiToken(YesBankApiToken YesBankApiToken) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(YesBankApiToken);
			String message = "QUERY : Save YesBankApiToken|| ID: " + YesBankApiToken.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertYesBankApiToken(api) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateYesBankApiToken(YesBankApiToken YesBankApiToken) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(YesBankApiToken);
			
			tx.commit();
			flag = true;
			String message = "QUERY : Update updateYesBankApiToken || ID: " + YesBankApiToken.getId();
			logger_log.warn(message);
		} catch (Exception e) {
			tx.rollback();
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}


}
