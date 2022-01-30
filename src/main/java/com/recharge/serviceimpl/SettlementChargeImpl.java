package com.recharge.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recharge.model.SettlementCharge;
import com.recharge.model.Userservice;
import com.recharge.servicedao.SettlementChargeDao;
import com.recharge.servicedao.UserserviceDao;


@Repository("settlementchargrdao")
public class SettlementChargeImpl implements SettlementChargeDao {
    
	private static final Logger logger_log = Logger.getLogger(SettlementChargeDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<SettlementCharge> getAllSettlementCharge() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SettlementCharge getSettlementChargeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertSettlementCharge(SettlementCharge SettlementCharge) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSettlementCharge(SettlementCharge SettlementCharge) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteSettlementCharge(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SettlementCharge> getSettlementChargeByNamedQuery(String query, Map<String, Object> param) {
		List<SettlementCharge> list = new ArrayList<SettlementCharge>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getSettlementChargeByNamedQuery() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

}
