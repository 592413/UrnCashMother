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

import com.recharge.model.AssignedPackage;
import com.recharge.servicedao.AssignedPackageDAO;
import com.recharge.servicedao.OperatorDao;

@Repository("assignedPackage")
public class AssignedPackageDAOImpl implements AssignedPackageDAO {
	private static final Logger log = Logger.getLogger(AssignedPackageDAO.class);
	@Autowired private SessionFactory sessionFactory;

	@Override
	public List<AssignedPackage> getAllAssignedPackage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssignedPackage getAssignedPackageById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateAssignedPackage(AssignedPackage asp) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(asp);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "Error Details :- " + e;
			log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<AssignedPackage> getAssignedPackageByNamedQuery(String query, Map<String, Object> param) {
		List<AssignedPackage> list = new ArrayList<>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try{
			if(!param.isEmpty()){
				for(Map.Entry<String, Object> entry : param.entrySet()){
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
		}catch (Exception e) {
			e.printStackTrace();
			log.error("getAssignedPackageByNamedQuery::::::::::::"+e);
		}finally{
			session.close();	
		}
		
		return list;
	}

	@Override
	public boolean insertAssignedPackage(AssignedPackage asp) {
		boolean status = false;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(asp);
			
			tx.commit();
			status = true;
		} catch (Exception e) {
			String message = "insertAssignedPackage(AssignedPackage) Error Details :- " + e;
			log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return status;
	}
	
	@Override
	public boolean deleteAssignPackageById(String packageId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try{
			String sql = "delete from AssignedPackage where package_id=:packageId";
			Query query = session.createQuery(sql);
			 query.setParameter("packageId", packageId);

			int result = query.executeUpdate();
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "deleteAssignPackageById  Error Details :- " + e;
			log.error(message);
			}finally {
				session.close();
			}
		return flag;

	}

}
