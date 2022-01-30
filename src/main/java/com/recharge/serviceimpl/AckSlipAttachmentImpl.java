package com.recharge.serviceimpl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recharge.model.AckSlipAttchment;
import com.recharge.servicedao.AckSlipAttachmentDao;
@Repository("AckSlipAttachmentDao")
public class AckSlipAttachmentImpl implements AckSlipAttachmentDao{
	private static final Logger logger_log = Logger.getLogger(AckSlipAttachmentDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public AckSlipAttchment getAckSlipAttchmentByApId(String ackno) {
		Session session = sessionFactory.openSession();
		AckSlipAttchment AckSlipAttchment = new AckSlipAttchment();
		try {
			AckSlipAttchment = session.get(AckSlipAttchment.class, ackno);
			String message = "Method : getAckSlipAttchmentByApId(ackno) || userId: " + AckSlipAttchment.getAckno();
			logger_log.warn(message);

		} catch (Exception e) {
			AckSlipAttchment = null;
			String message = "getAckSlipAttchmentByApId(ackno) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return AckSlipAttchment;
	}

	@Override
	public boolean insertAckSlipAttchment(AckSlipAttchment AckSlipAttchment) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(AckSlipAttchment);
			String message = "QUERY : Save insertAckSlipAttchment|| ID: " + AckSlipAttchment.getAckno();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertAckSlipAttchment(api) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

}
