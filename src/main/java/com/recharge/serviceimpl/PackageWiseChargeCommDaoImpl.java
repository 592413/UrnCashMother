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
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.servicedao.PackageDetailDao;
import com.recharge.servicedao.PackageWiseChargeCommDao;
@Repository("packwisechargecomm")
public class PackageWiseChargeCommDaoImpl implements PackageWiseChargeCommDao {
	private static final Logger logger_log = Logger.getLogger(PackageDetailDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean insertPackageWiseChargeComm(PackageWiseChargeComm pck) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(pck);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertPackageWiseChargeComm pck(PackageWiseChargeComm) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updatePackageWiseChargeComm(PackageWiseChargeComm pck) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deletePackageWiseChargeComm(String packageId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			String sql = "delete from PackageWiseChargeComm where package_id=:packageId";
			Query query = session.createQuery(sql);
			query.setParameter("packageId", packageId);
			int result = query.executeUpdate();
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "deletePackageWiseChargeComm  Error Details :- " + e;
			logger_log.error(message);
		}finally {
			session.close();
		}
		return flag;

	}


	@Override
	public List<PackageWiseChargeComm> getPackageDetailByNamedQuery(String query, Map<String, Object> param) {
		List<PackageWiseChargeComm> list = new ArrayList<PackageWiseChargeComm>();
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
			String message = "Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public boolean updatePackagewiseCommByOperator(Map<String, String> request) {
		boolean flag = false;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {

			Query query = session.createQuery(
					"update PackageWiseChargeComm set charge=:charge,charge_type=:charge_type,comm=:comm,comm_type=:comm_type where package_id=:package_id and operator_id=:operator_id");
			query.setParameter("charge", Double.parseDouble(request.get("charge")));
			query.setParameter("charge_type", request.get("charge_type"));
			query.setParameter("comm", Double.parseDouble(request.get("comm")));
			query.setParameter("comm_type", request.get("comm_type"));
			query.setParameter("package_id", request.get("package_id"));
			query.setParameter("operator_id", Integer.parseInt(request.get("operator_id")));
			int i = query.executeUpdate();
			tx.commit();
			if (i > 0) {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
			String message = "updatePackagewiseCommByOperator pck(PackageWiseChargeComm) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}

		return flag;
	}

}
