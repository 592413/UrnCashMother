package com.recharge.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recharge.servicedao.CustomQueryDao;

@Repository("customQueryDao")
public class CustomQueryDaoImpl implements CustomQueryDao {
	private static final Logger logger = Logger.getLogger(CustomQueryDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Object> getQueryResultFromCustomQuery(String sql, Map<String, String> parameters) {		
		Session session = sessionFactory.openSession();
		List<Object> objectList = new ArrayList<Object>();
		//logger.warn("EXECUTION OF METHOD :- CustomQueryDaoImpl.getQueryResultFromCustomQuery(String sql,Map<String,String>parameters) \n");
		try {
			System.out.println("SQL:::"+sql);
			SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
			//logger.warn("QUERY:" + query.toString() + " || QUERY CRITERIA: " + parameters.toString());
			if (!parameters.isEmpty()) {
				for (Map.Entry<String, String> entry : parameters.entrySet()) {
					//logger.warn("QUERY CRITERIA: " + entry.getKey() + entry.getValue());
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
			logger.warn("QUERY:" + query.toString() + " || QUERY CRITERIA: " + parameters.toString());
			objectList = query.list();
		} catch (Exception e) {
			logger.error(" Error From  CustomQueryDaoImpl.getQueryResultFromCustomQuery", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return objectList;

	}

}
