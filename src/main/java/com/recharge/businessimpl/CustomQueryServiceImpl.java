package com.recharge.businessimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.businessdao.CustomQueryService;
import com.recharge.controller.RestAuthenticationConroller;
import com.recharge.servicedao.CustomQueryDao;


/**
 * custtomQueryService is the service class for the implementing custom query of the hibernate
 * @author Md Rahim
 *
 */
@Service("customQueryService")
public class CustomQueryServiceImpl implements CustomQueryService {
	private static final Logger logger_log = Logger.getLogger(CustomQueryService.class);
	@Autowired CustomQueryDao customQueryDao;
	
	/**
	 * @MethodName getDataByQueryUsingCustomQuery
	 * @Function fetch data from the data base on custom query 
	 * @param Sql String i.e. custom query
	 * @param Map<String, String> param which is the query parameter
	 * @return return type is list of objects
	 * @author Md Rahim
	 */
	@Override
	public List<Object> getDataByQueryUsingCustomQuery(String sql, Map<String, String> param) {
		List<Object> list = new ArrayList<Object>();
		try {		
			list = customQueryDao.getQueryResultFromCustomQuery(sql,param);
		}catch (Exception e) {
			logger_log.error("getDataByQueryUsingCustomQuery :::::::::::::: "+e);
			list = new ArrayList<Object>();
		}
		return list;
	}
	
	

}
