package com.recharge.serviceimpl;



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


import com.recharge.model.ImpsTransaction;
import com.recharge.servicedao.ImpsTransactionService;



@Repository("impsTransactiondao")
public class ImpsTransactionImpl implements ImpsTransactionService {
	private static final Logger log = Logger.getLogger(ImpsTransactionImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean insertImpsTransaction(ImpsTransaction imptrans) {
		Session session = sessionFactory.openSession();
		boolean flag = false;
		try {
			session.beginTransaction();
			session.save(imptrans);
			session.getTransaction().commit();
			flag = true;
       log.warn("insertImpsTransaction::::::::::::::::::::::"+flag);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			log.error("insertImpsTransaction:::::::::::::::::" + e);
			session.getTransaction().rollback();
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("insertImpsTransaction:::::::::::::::::" + e);
			}

		}

		return flag;
	}

	@Override
	public boolean updateImpsTransaction(ImpsTransaction imptrans) {
		Session session = sessionFactory.openSession();
		boolean flag = false;
		try {
			session.beginTransaction();
			session.update(imptrans);
			session.getTransaction().commit();
			flag = true;
			log.warn("updateImpsTransaction:::::::::::::::::::::::::"+flag);

		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			log.error("insertImpsTransaction:::::::::::::::::" + e);
			session.getTransaction().rollback();
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("insertImpsTransaction:::::::::::::::::" + e);
			}

		}

		return flag;
	}

	@Override
	public boolean updateDmrTransactionBankTransactionId(String tranId, String bankTransactionId) {
		Session session = sessionFactory.openSession();
		boolean flag = false;
		int i=0;
		try{
			session.beginTransaction();
			Query query=session.createQuery("update ImpsTransaction set bankTransactionId=:bankTransactionId  where recieptId=:tranId ");
			query.setParameter("bankTransactionId", bankTransactionId);
			query.setParameter("tranId", tranId);
			i=query.executeUpdate();
			session.getTransaction().commit();
			if(i>0){
				flag = true;
			}else{
				flag = false;
			}
			log.warn("updateDmrTransactionBankTransactionId:::::::::::::::::"+flag);
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("updateDmrTransactionBankTransactionId:::::::::::::::::"+e);
		}finally{
			try{
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return flag;
	}

	@Override
	public boolean updateDmrTransactionStatus(String tranId, String status) {
		Session session = sessionFactory.openSession();
		boolean flag = false;
		int i=0;
		try{
			session.beginTransaction();
			Query query=session.createQuery("update ImpsTransaction set status=:status  where recieptId=:tranId ");
			query.setParameter("status", status);
			query.setParameter("tranId", tranId);
			i=query.executeUpdate();
			session.getTransaction().commit();
			if(i>0){
				flag = true;
			}else{
				flag = false;
			}
			log.warn("updateDmrTransactionStatus:::::::::::::::::"+flag);
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("updateDmrTransactionStatus:::::::::::::::::"+e);
		}finally{
			try{
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return flag;
	}
	@Override
	public ImpsTransaction getimpsdetailsById(String banktransactionId){
		Session session = sessionFactory.openSession();
		ImpsTransaction impstransaction=new ImpsTransaction();
		try{
			impstransaction=session.get(ImpsTransaction.class, banktransactionId);
			
		}catch(Exception e){
			impstransaction = null;
			String message = "getimpsdetailsById(banktransactionId) Error Details :- " + e;
			log.error(message);
		}finally {
			session.close();
		}
		return impstransaction;
	}
	
	@Override
	public List<ImpsTransaction> getIMPSDetailsByNamedQuery(String query, Map<String, Object> param) {
		List<ImpsTransaction> list = new ArrayList<ImpsTransaction>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getIMPSDetailsByNamedQuery() ";
			log.warn(message);
		} catch (Exception e) {
			String message = "Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return list;
	}
	

	@Override
	public boolean deleteImpsTransaction(ImpsTransaction imptrans) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			
			session.delete(imptrans);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "deleteAEPSUserMap  Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

}
