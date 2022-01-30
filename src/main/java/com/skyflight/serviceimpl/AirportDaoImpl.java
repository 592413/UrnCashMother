package com.skyflight.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recharge.model.Api;
import com.skyflight.model.Airport_code;
import com.skyflight.servicedao.AirportDao;



@Repository("airportdao")
public class AirportDaoImpl implements AirportDao {
	private static final Logger log = Logger.getLogger("AirportDaoImpl.class");
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@Override
	public List<Airport_code> getAllAirport_code() {
		List<Airport_code> list = new ArrayList<Airport_code>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Airport_code.class).addOrder(Order.asc("country")).list();
			String message = "Method : getAllAirport_code() ";
			log.warn(message);
		} catch (Exception e) {
			String message = "getAllAirport_code Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Airport_code> getCities(String key) {
		Session session = null;
		List<Airport_code> list = new ArrayList<Airport_code>();

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"from Airport_code ac  where ac.code  like concat('%',:key,'%') or ac.city like concat('%',:key,'%')");
			query.setString("key", key);
			list = query.list();
			session.getTransaction().commit();
			/*System.out.println(list.size());*/
			log.warn("getCities::::::::::" + list.size());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getCities::::::::::" + e);
		} finally {
			try {
				session.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				log.error("getCities::::::::::" + ex);
			}
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Airport_code> getAirportcodeByNamedQuery(String query, Map<String, Object> param) {
		List<Airport_code> list = new ArrayList<>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try{
			if(!param.isEmpty()){
				for(Map.Entry<String, Object> entry :param.entrySet()){
					query_q.setParameter(entry.getKey(),entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getPassengerDetailByNamedQuery ";
			log.warn(message);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("getPAirport_codeByNamedQuery:::::::::::::::::::::"+e);
		}finally{
			try {
				session.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				log.error("getCities::::::::::" + ex);
			}
		}
		return list;
	}

}
