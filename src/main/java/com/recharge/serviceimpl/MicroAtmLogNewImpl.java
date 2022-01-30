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

import com.recharge.model.MicroAtmLog;
import com.recharge.model.MicroAtmLogNew;
import com.recharge.servicedao.MicroAtmLogDao;
import com.recharge.servicedao.MicroAtmLogNewDao;
@Repository("microatmlognewdao")
public class MicroAtmLogNewImpl implements MicroAtmLogNewDao {
private static final Logger log = Logger.getLogger(MicroAtmLogNewDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean insertMicroAtmLogNew(MicroAtmLogNew MicroAtmLogNew) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(MicroAtmLogNew);
			String message = "QUERY : Save insertMicroAtmLogNew|| ID: " + MicroAtmLogNew.getId();
			log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertMicroAtmLogNew(MicroAtmLogNew) Error Details :- " + e;
			log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateMicroAtmLogNew(MicroAtmLogNew MicroAtmLogNew) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(MicroAtmLogNew);
			String message = "QUERY : updateMicroAtmLogNew(MicroAtmLogNew) || ID: " + MicroAtmLogNew.getId();
			log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updateMicroAtmLog(MicroAtmLogNew)  Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public List<MicroAtmLogNew> getMicroAtmLogNewByNamedQuery(String query, Map<String, Object> param) {
		List<MicroAtmLogNew> list = new ArrayList<MicroAtmLogNew>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getMicroAtmLogNewByNamedQuery() ";
			log.warn(message);
		} catch (Exception e) {
			String message = "getMicroAtmLogNewByNamedQuery Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public MicroAtmLogNew getMicroAtmLogNewById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

}
