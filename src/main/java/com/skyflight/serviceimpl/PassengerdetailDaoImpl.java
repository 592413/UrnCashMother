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
import com.skyflight.model.PassengerDetail;
import com.skyflight.servicedao.PassengerdetailDao;

@Repository("passengerdetaildao")
public class PassengerdetailDaoImpl implements PassengerdetailDao {
private static final Logger logger_log = Logger.getLogger(PassengerdetailDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<PassengerDetail> getAllPassengerDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PassengerDetail getPassengerDetailById(String passengerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertPassengerDetail(PassengerDetail passenger) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try{
			session.save(passenger);
			tx.commit();
			flag = true;
			logger_log.warn("insertPassengerDetail::::::::::::::"+flag);
		}catch (Exception e) {
			flag = false;
			String message = "insertPassengerDetail(passenger) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		}finally{
			session.close();
		}
	
		return flag;
	}

	@Override
	public boolean updatePassengerDetail(PassengerDetail passenger) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deletePassengerDetail(int passengerId) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PassengerDetail> getPassengerDetailByNamedQuery(String query, Map<String, Object> param) {
		List<PassengerDetail> list = new ArrayList<>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		System.out.println(param);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getPassengerDetailByNamedQuery ";
			logger_log.warn(message);
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getPassengerDetailByNamedQuery:::::::::::::" + e);
		}finally{
			session.close();
		}

		return list;
	}

	@Override
	public boolean updatePassengerDetailbyairlinepnrAndTicketNumber(String PNR, String ticketnumber) {
		boolean flag = false;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String cancellation_status = "cancelled";
		try {
         Query query = session.createQuery("update PassengerDetail set cancellation_status=:cancellation_status where PNR=:PNR and ticketnumber=:ticketnumber");
         query.setParameter("cancellation_status", cancellation_status);
         query.setParameter("PNR", PNR);
         query.setParameter("ticketnumber", ticketnumber);
         int i = query.executeUpdate();
         tx.commit();
         if(i>0){
        	 flag = true;	 
         }else{
        	 flag = false;
         }
         logger_log.error("updatePassengerDetailbyairlinepnrAndTicketNumber::::::::::::::::::::"+flag);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("updatePassengerDetailbyairlinepnrAndTicketNumber:::::::::::::" + e);
		} finally {

		}
		return flag;
	}

}
