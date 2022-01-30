package com.recharge.yesbankserviceimpl;

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

import com.recharge.yesbankmodel.YesBankAEPSResponse;
import com.recharge.yesbankservicedao.YesBankAEPSResponseDao;
import com.recharge.yesbankservicedao.YesBankApiTokenDao;


@Repository("yesbankresponseDao")
public class YesBankAEPSResponseDaoImpl implements YesBankAEPSResponseDao {
	private static final Logger logger_log = Logger.getLogger(YesBankAEPSResponseDao.class);

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public List<YesBankAEPSResponse> getYesBankAEPSResponsen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public YesBankAEPSResponse getYesBankAEPSResponseById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertYesBankAEPSResponse(YesBankAEPSResponse YesBankAEPSResponse) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(YesBankAEPSResponse);
			String message = "QUERY : Save YesBankAEPSResponse|| ID: " + YesBankAEPSResponse.getId();
			logger_log.warn(message);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertYesBankAEPSResponse(YesBankAEPSResponse) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updateYesBankAEPSResponse(YesBankAEPSResponse YesBankAEPSResponse) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<YesBankAEPSResponse> getYesBankAEPSResponseByNamedQuery(String query, Map<String, Object> param) {
		List<YesBankAEPSResponse> list = new ArrayList<YesBankAEPSResponse>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		if(!param.isEmpty()){
			for(Map.Entry<String,Object> entry:param.entrySet()){
				query_q.setParameter(entry.getKey(), entry.getValue());
				
			}
			list=query_q.list();
		}
		return list;
	}

}
