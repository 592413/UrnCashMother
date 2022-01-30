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
import com.recharge.model.Apisetting;
import com.recharge.model.PackageDetail;
import com.recharge.servicedao.ApisettingDao;
import com.recharge.servicedao.PackageDetailDao;
@Repository("packagedetailDao")
public class PackageDetailImpl implements PackageDetailDao {
	private static final Logger logger_log = Logger.getLogger(PackageDetailDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageDetail> getAllPackage() {
		List<PackageDetail> list = new ArrayList<PackageDetail>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(PackageDetail.class).list();
			
		} catch (Exception e) {
			String message = "getAllPackage Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public PackageDetail getPackageByApId(int id) {
		Session session = sessionFactory.openSession();
		PackageDetail pckdetail = new PackageDetail();
		try {
			pckdetail = session.get(PackageDetail.class, id);
			
		} catch (Exception e) {
			pckdetail = null;
			String message = "getPackageByApId(id) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return pckdetail;
	}

	@Override
	public boolean insertPackageDetail(PackageDetail pck) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.save(pck);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			String message = "insertPackageDetail pck(PackageDetail) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean updatePackage(PackageDetail pck) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(pck);
			
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "updatePackage(PackageDetail)  Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return flag;
	}

	@Override
	public boolean deletePackageDetail(String packageId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			String sql = "delete from PackageDetail where package_id=:packageId";
			Query query = session.createQuery(sql);
			query.setParameter("packageId", packageId);
			int result = query.executeUpdate();
			tx.commit();
			flag = true;
		} catch (Exception e) {
			tx.rollback();
			String message = "deletePackageDetail(PackageDetail)  Error Details :- " + e;
			logger_log.error(message);
		}finally {
			session.close();
		}
		return flag;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageDetail> getPackageDetailByNamedQuery(String query, Map<String, Object> param) {
		List<PackageDetail> list = new ArrayList<>();
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
			e.printStackTrace();
			logger_log.error("getPackageDetailByNamedQuery:::::::::::::" + e);
		}finally {
			session.close();
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PackageDetail getLastPackage() {
		PackageDetail pd = null;
		Session session = sessionFactory.openSession();
		List<PackageDetail> list = new ArrayList<>();
		try {
			session.beginTransaction();
			Query query = session.createQuery("From PackageDetail order by id desc");
			list = query.list();
			if (list.size() > 0) {
				pd = list.get(0);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getLastPackage:::::::::::::" + e);
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
				logger_log.error("getLastPackage:::::::::::::" + e);
			}
		}

		return pd;

	}

}
