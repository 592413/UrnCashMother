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
import com.recharge.model.Role;
import com.recharge.servicedao.RoleDao;

public class RoleImpl implements RoleDao {
	
	private static final Logger logger_log = Logger.getLogger(RoleDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Role> getAllRole() {
		List<Role> list = new ArrayList<Role>();
		Session session = sessionFactory.openSession();
		try {
			list = session.createCriteria(Role.class).list();
			String message = "Method : getAllRole() ";
			logger_log.warn(message);
		} catch (Exception e) {
			String message = "getAllRole Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public Role getRoleByroleId(Integer roleId) {
		Session session = sessionFactory.openSession();
		Role role = new Role();
		try {
			role = session.get(Role.class, roleId);
			String message = "Method : getRoleByroleId(roleId) || requestId: "+ role.getRoleId();
			logger_log.warn(message);

		} catch (Exception e) {
			role = null;
			String message = "getRoleByroleId(roleId) Error Details :- " + e;
			logger_log.error(message);
		} finally {
			session.close();
		}
		return role;
	}

	@Override
	public Integer insertRoleId(Role role) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(role);
			String message = "QUERY : Save Role|| ID: " + role.getRoleId();
			logger_log.warn(message);
			tx.commit();
		} catch (Exception e) {
			String message = "insertRoleId(role) Error Details :- " + e;
			logger_log.error(message);
			tx.rollback();
		} finally {
			session.close();
		}
		return role.getRoleId();
	}

	@Override
	public boolean updateRoleId(Role role) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean flag = false;
		try {
			session.update(role);
			String message = "QUERY : updateRoleId || ID: " + role.getRoleId();
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
	public void deleteRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Role> getRoleByNamedQuery(String query, Map<String, Object> param) {
		List<Role> list = new ArrayList<Role>();
		Session session = sessionFactory.openSession();
		Query query_q = session.getNamedQuery(query);
		try {
			if (!param.isEmpty()) {
				for (Map.Entry<String, Object> entry : param.entrySet()) {
					query_q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			list = query_q.list();
			String message = "Method : getBalanceRequestByNamedQuery() ";
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
