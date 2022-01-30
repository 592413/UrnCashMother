package com.recharge.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recharge.model.AEPSBankDetail;
import com.recharge.servicedao.AEPSBankDetailDao;


@Repository("aepsBankdetail")
public class AEPSBankDetailImpl implements AEPSBankDetailDao {
	
	private static final Logger log = Logger.getLogger(AEPSBankDetailDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<AEPSBankDetail> getAllAEPSBankDetail() {
		List<AEPSBankDetail> list =  new ArrayList<>();
		Session session = sessionFactory.openSession();
		try{
			list = session.createCriteria(AEPSBankDetail.class).list();
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("getAllAEPSBankDetail::::::::::::::::::"+e);
		}finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<AEPSBankDetail> getAEPSBankDetailByNamedQuery(String query, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

}
