package com.skyhotel.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skyflight.model.Airport_code;
import com.skyhotel.ServiceDao.CityDao;
import com.skyhotel.model.City;

@Repository("citydao")
public class CityDaoImpl implements CityDao {
	private static final Logger log = Logger.getLogger(CityDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public List<City> getAllCity() {
		List<City> list = new ArrayList<City>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(City.class).addOrder(Order.asc("country")).list();
			String message = "Method : getAllCity() ";
			log.warn(message);
		} catch (Exception e) {
			String message = "getAllCity Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public boolean insertCity(City city) {
		System.out.println(city);
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(city);
			String message = "QUERY : Save insertCity|| ID: " + city.getId();
			log.warn(message);
			tx.commit();
			flag = true;

		} catch (Exception e) {
			flag = false;
			String message = "insertCity city(City) Error Details :- " + e;
		     log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}

		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> getCity(String key) {
		Session session = sessionFactory.openSession();
		List<City> list = new ArrayList<>();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from City as c where c.CityName like concat('%',:key,'%') or c.country like concat('%',:key,'%')");
			query.setString("key",key);
			list=query.list();
			session.getTransaction().commit();
			log.warn("getCity:::::::::::::::::::::"+list.size());
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("getCity:::::::::::::::::::::"+e);
		}finally{
			session.close();
		}
		return list;
	}

}
