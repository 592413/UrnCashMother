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

import com.skyflight.model.CancellationDetail;
import com.skyflight.servicedao.CancellationDetailDao;


@Repository("CancellationDetailDao")
public class CancellationDetailImpl implements CancellationDetailDao{
	private static final Logger logger_log = Logger.getLogger(CancellationDetailDao.class);
	@Autowired private SessionFactory sessionFactory;

	

	
	@Override
	public boolean insertCancellationDetail(CancellationDetail airline) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try{
			session.save(airline);
			tx.commit();
			flag = true;
			
		}catch (Exception e) {
			flag = false;
			String message = "insertCancellationDetail(fb) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		}finally{
			session.close();
		}
	
		return flag;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<CancellationDetail> getCancellationDetailByNamedQuery(String query, Map<String, Object> param) {
		List<CancellationDetail> list = new ArrayList<CancellationDetail>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());    
				}
			}
			list = query_q.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getCancellationDetailByNamedQuery:::::::::::::::"+e);
		} finally {
			session.close();
		}
		return list;
	}

	

}
