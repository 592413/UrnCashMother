package com.skyhotel.Controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skyhotel.BusinessDao.Hotelandroidbustnessdao;


@RestController
@RequestMapping(value = "rest")
public class HotelAndroidController {
	private static final Logger logger_log = Logger.getLogger(HotelAndroidController.class);
	@Autowired Hotelandroidbustnessdao hotelandroidbustnessdao;
	
	
	@RequestMapping(value = "/hotelcity", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> hotelcity(@RequestBody String request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = hotelandroidbustnessdao.hotelcity(request);
		} catch (Exception e) {
			logger_log.error("hotelcity :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/searchHotalapps", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> searchHotalapps(@RequestBody String request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = hotelandroidbustnessdao.searchHotalapps(request);
		} catch (Exception e) {
			logger_log.error("searchHotalapps :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/HOtelDetails", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> HOtelDetails(@RequestBody String request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = hotelandroidbustnessdao.HOtelDetails(request);
		} catch (Exception e) {
			logger_log.error("HOtelDetails :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	
	
	@RequestMapping(value = "/HOtelBlockRoom", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> HOtelBlockRoom(@RequestBody String request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = hotelandroidbustnessdao.HOtelBlockRoom(request);
		} catch (Exception e) {
			logger_log.error("HOtelBlockRoom :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/HOtelBookRoom", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> HOtelBookRoom(@RequestBody String request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = hotelandroidbustnessdao.HOtelBookRoom(request);
		} catch (Exception e) {
			logger_log.error("HOtelBookRoom :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
}
