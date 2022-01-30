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

import com.recharge.model.PackageDetail;
import com.skyflight.model.Flightdetail;
import com.skyflight.servicedao.FlightdetailDao;

@Repository("flightdetaildao")
public class FlightdetailDaoImpl implements FlightdetailDao {
private static final Logger logger_log = Logger.getLogger(FlightdetailDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Flightdetail> getAllFlightdetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flightdetail getFlightdetailById(String apiId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertFlightdetail(Flightdetail fb) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try{
			session.save(fb);
			tx.commit();
			flag = true;
			logger_log.warn("insertFlightdetail::::::::::::::"+flag);
		}catch (Exception e) {
			flag = false;
			String message = "insertFlightdetail(fb) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		}finally{
			session.close();
		}
	
		return flag;
	}

	@Override
	public boolean updateFlightdetail(Flightdetail fb) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteFlightdetail(int fbId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Flightdetail> getFlightdetailByNamedQuery(String query, Map<String, Object> param) {
		List<Flightdetail> list = new ArrayList<>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getFlightdetailByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getFlightdetailByNamedQuery:::::::::::::" + e);
		}finally{
			session.close();
		}

		return list;
	}

}
