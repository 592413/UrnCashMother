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

import com.skyflight.model.Flightbooking;
import com.skyflight.servicedao.FlightBookingDao;


@Repository("flightDao")
public class FlightBookingDaoImpl implements FlightBookingDao {
	private static final Logger logger_log = Logger.getLogger(FlightBookingDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Flightbooking> getAllFlightbooking() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flightbooking getFlightBookingById(String apiId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertFlightbooking(Flightbooking fb) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try{
			session.save(fb);
			tx.commit();
			flag = true;
			logger_log.warn("insertFlightbooking::::::::::::::"+flag);
		}catch (Exception e) {
			flag = false;
			String message = "insertFlightbooking(fb) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		}finally{
			session.close();
		}
	
		return flag;
	}

	@Override
	public boolean updateFlightbooking(Flightbooking fb) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteFlightbooking(int fbId) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flightbooking> getFlightBookingByNamedQuery(String query, Map<String, Object> param) {
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		List<Flightbooking> list = new ArrayList<Flightbooking>();
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			logger_log.warn("getFlightBookingByNamedQuery::::::::::::::"+list.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getFlightBookingByNamedQuery:::::::::::::::"+e);
			
		} finally {

		}
		return list;
	}

	@Override
	public boolean updateFlightBookingbyAirlinePNR(String airlinepnr,String type) {
		Session session = sessionFactory.openSession();
		String booking_status="";
		boolean flag=false;
		try {
			if(type.equalsIgnoreCase("FULL")){
			booking_status="canceled";
			}else{
			booking_status="Partial canceled";	
			}
			System.out.println("booking_status::::::::::::::::::::::"+booking_status);
			session.beginTransaction();
			Query query = session.createQuery("update Flightbooking set booking_status=:booking_status where PNR=:airlinepnr");
			query.setParameter("booking_status", booking_status);
			query.setParameter("airlinepnr",airlinepnr);
			int i=query.executeUpdate();
			if(i>0){
				System.out.println("Payel::::::::::::::::::::::::::::::");
				flag=true;	
			}else{
				flag=false;
			}
			logger_log.warn("updateFlightBookingbyAirlinePNR::::::::::::::::::"+flag);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("updateFlightBookingbyAirlinePNR::::::::::::::::::"+e.getMessage());
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return flag;
	}
	
	public static void main(String args[]){
		FlightBookingDaoImpl fd= new FlightBookingDaoImpl();
		fd.updateFlightBookingbyAirlinePNR("NYQFVD","PARTIAL");
	}

}
