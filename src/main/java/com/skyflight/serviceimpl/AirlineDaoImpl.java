package com.skyflight.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.skyflight.model.Airline;
import com.skyflight.servicedao.AirlineDao;


@Repository("airlineDao")
public class AirlineDaoImpl implements AirlineDao {
	private static final Logger logger_log = Logger.getLogger(AirlineDao.class);
	@Autowired private SessionFactory sessionFactory;

	

	
	@Override
	public Integer insertAirline(Airline airline) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateAirline(Airline airline) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteAirline(Integer airlineId) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Airline> getAirlineByNamedQuery(String query, Map<String, Object> param) {
		List<Airline> list = new ArrayList<>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());    
				}
			}
			list = query_q.list();
			String message = "Method : getAirlineByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getAirlineByNamedQuery:::::::::::::::"+e);
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Airline> getAllAirline() {
		List<Airline> list = new ArrayList<Airline>();
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			Query query = session.createQuery("From Airline");
			list = (List<Airline>) query.list();
			logger_log.warn("getAllAirline:::::::::::" + list.size());

		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getAllOpeartor::::::::::::::" + e);
		} finally {
			session.close();
		}

		// TODO Auto-generated method stub
		return list;
	}
	
	
	@Override
	public Airline getAirlineByAirlineCode(String airline_code) {
		Airline airline = new Airline();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("From Airline where airline_code=:airline_code");
			query.setParameter("airline_code", airline_code);
			airline = (Airline) query.list().get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getAirlineByAirlineCode::::::::::::::" + e);
		} finally {
            session.close();
		}
		return airline;
	}


}
