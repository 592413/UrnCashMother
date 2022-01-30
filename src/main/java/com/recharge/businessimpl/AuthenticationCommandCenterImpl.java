package com.recharge.businessimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.businessdao.AuthenticationCommandCenter;
import com.recharge.businessdao.CustomQueryService;
import com.recharge.model.Indexx;
import com.recharge.model.Reseller;
import com.recharge.servicedao.IndexDao;
import com.recharge.servicedao.ResellerDao;


/**
 * AuthenticationCommandCenterImpl.java is class for authentication only 
 * @author Md Rahim
 *
 */
@Service("authenticationCommandCenter")
public class AuthenticationCommandCenterImpl implements AuthenticationCommandCenter {
	private static final Logger logger = Logger.getLogger(AuthenticationCommandCenter.class);
	@Autowired private SessionFactory sessionFactory;
	@Autowired CustomQueryService customQueryService;
	@Autowired ResellerDao resellerdao;
	@Autowired IndexDao indexdao;
	/**
	 * @MethodName getUserNamebyMobileEmail
	 * 
	 * @function get the user details based on it mobile and email address from the database
	 *  
	 * @param String which is sql query and Map<String, String> which is mobile and email address of the user in map format
	 * 
	 * @return String return the username of the user
	 * 
	 * @author Md Rahim
	 * 
	 */
	@Override
	public String getUserNamebyMobileEmail(String sql, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<String> userName = new ArrayList<String>();
		List<Object> list = new ArrayList<Object>();
		try {			
			list =(List<Object>) customQueryService.getDataByQueryUsingCustomQuery(sql, parameters);
			if(!list.isEmpty()){
				return list.get(0).toString();
			}

		} catch (Exception e) {
			logger.error(" Error From  AuthenticationImpl.checkUsernamePassword :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;		
	}
	
	/**
	 * @MethodName loginUser
	 * 
	 * @function check the user have correct username and password 
	 *  
	 * @param String which is sql query and Map<String, String> which is username and password of the user in map format
	 * 
	 * @return String return the username of the user
	 * 
	 * @author Md Rahim
	 * 
	 */
	@Override
	public String loginUser(String sql, Map<String, String> param) {
		Session session = sessionFactory.openSession();
		List<String> userName = new ArrayList<String>();
		List<Object> list = new ArrayList<Object>();
		try {
			list =(List<Object>) customQueryService.getDataByQueryUsingCustomQuery(sql, param);
			if(!list.isEmpty()){
				for(Object obj : list){
					//Object[] str = (Object[]) obj;
					userName.add(obj.toString());					
				}
				return userName.get(0);
			}

		} catch (Exception e) {
			logger.error(" Error From  AuthenticationImpl.checkUsernamePassword :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
		
	}
	
	@Override
	public Map<String, Object> getReseller(Map<String, Object> parameters) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		// TODO Auto-generated method stu
		Session session = sessionFactory.openSession();
		try {
		List<Reseller> relist=resellerdao.getResellerByNamedQuery("getReseller", parameters);
		
		List<Indexx> indexlist=indexdao.getIndexxByNamedQuery("getIndex", parameters);
		if(!relist.isEmpty()) {
			returnData.put("reselletList", relist);
			returnData.put("indexList", indexlist);
			returnData.put("status", "1");
			if(!relist.get(0).getWlId().equals("ADMIN")){
				returnData.put("nextPage", "login");
			}
		}else{
			returnData.put("nextPage", "login");
		}
		return returnData;
		} catch (Exception e) {
			logger.error(" Error From  AuthenticationImpl.getReseller :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

}
