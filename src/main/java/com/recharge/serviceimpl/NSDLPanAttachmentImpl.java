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

import com.recharge.model.Api;
import com.recharge.model.NSDLPanAttachment;
import com.recharge.servicedao.NSDLPanAttachmentDao;
@Repository("NSDLPanAttachmentDao")
public class NSDLPanAttachmentImpl implements NSDLPanAttachmentDao{
	private static final Logger logger_log = Logger.getLogger(NSDLPanAttachmentDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean insertNSDLPanAttachment(NSDLPanAttachment NSDLPanAttachment) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(NSDLPanAttachment);
			String message = "QUERY : Save NSDLPanAttachment|| ID: " + NSDLPanAttachment.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertNSDLPanAttachment(NSDLPanAttachment) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateNSDLPanAttachment(NSDLPanAttachment NSDLPanAttachment) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(NSDLPanAttachment);
			String message = "QUERY : Update NSDLPanAttachment || ID: " + NSDLPanAttachment.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}



	@Override
	public List<NSDLPanAttachment> getNSDLPanAttachmentByNamedQuery(String query, Map<String, Object> param) {
		List<NSDLPanAttachment> list = new ArrayList<NSDLPanAttachment>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getNSDLPanAttachmentByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public NSDLPanAttachment getNSDLPanAttachmentByApId(int id) {
		Session session = sessionFactory.openSession();
		NSDLPanAttachment NSDLPanAttachment = new NSDLPanAttachment();
		try {
			NSDLPanAttachment = session.get(NSDLPanAttachment.class, id);
			String message = "Method : getNSDLPanAttachmentByApId(id) || userId: " + NSDLPanAttachment.getId();
			logger_log.warn(message);

		} catch (Exception e) {
			NSDLPanAttachment = null;
			String message = "getNSDLPanAttachmentByApId(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return NSDLPanAttachment;
	}

}
