package com.skyflight.serviceimpl;

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

import com.skyflight.model.PassengerDetail;
import com.skyflight.model.Passengerfare;
import com.skyflight.servicedao.PassengerfareDao;

@Repository("passengerfaredao")
public class PassengerfareDaoImpl implements PassengerfareDao {
	private static final Logger logger_log = Logger.getLogger(PassengerfareDao.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Passengerfare> getAllPassengerfare() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Passengerfare getPassengerfareById(String apiId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertPassengerfare(Passengerfare pf) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try{
			session.save(pf);
			tx.commit();
			flag = true;
			logger_log.warn("insertPassengerfare::::::::::::::"+flag);
		}catch (Exception e) {
			flag = false;
			String message = "insertPassengerfare(pf) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		}finally{
			session.close();
		}
	
		return flag;
	}

	@Override
	public boolean updatePassengerfare(Passengerfare fb) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deletePassengerfare(int fbId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Passengerfare> getPassengerfareByNamedQuery(String query, Map<String, Object> param) {
		List<Passengerfare> list = new ArrayList<>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		System.out.println(param);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getPassengerfareByNamedQuery ";
			logger_log.warn(message);
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getPassengerfareByNamedQuery:::::::::::::" + e);
		}finally{
			session.close();
		}

		return list;
	}

}
