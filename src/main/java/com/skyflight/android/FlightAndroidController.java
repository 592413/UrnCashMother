package com.skyflight.android;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "rest")
public class FlightAndroidController {
	private static final Logger logger_log = Logger.getLogger(FlightAndroidController.class);
	@Autowired FlightBusinessDao flightBusinessDao;
	
	
	@RequestMapping(value = "/flightairport", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> flightairport(@RequestBody String request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = flightBusinessDao.flightairport(request);
		} catch (Exception e) {
			logger_log.error("flightairport :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/flightsearchAndroid", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> flightsearchAndroid(@RequestBody String request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = flightBusinessDao.flightsearchAndroid(request);
		} catch (Exception e) {
			logger_log.error("flightsearchAndroid :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/flightCalenderSearchAndroid", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> flightCalenderSearchAndroid(@RequestBody String request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = flightBusinessDao.flightCalenderSearchAndroid(request);
		} catch (Exception e) {
			logger_log.error("flightCalenderSearchAndroid :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@RequestMapping(value = "/flightTaxAndroid", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> flightTaxAndroid(@RequestBody String request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = flightBusinessDao.flightTaxAndroid(request);
		} catch (Exception e) {
			logger_log.error("flightTaxAndroid :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/flightBookTicketAndroid", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> flightBookTicketAndroid(@RequestBody String request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = flightBusinessDao.flightBookTicketAndroid(request);
		} catch (Exception e) {
			logger_log.error("flightBookTicketAndroid :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	
	@RequestMapping(value = "/flightBookTicketReportAndroid", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> flightBookTicketReportAndroid(@RequestBody String request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = flightBusinessDao.flightBookTicketReportAndroid(request);
		} catch (Exception e) {
			logger_log.error("flightBookTicketReportAndroid :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}

	
	@RequestMapping(value = "/flightTicketCancelAndroid", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> flightTicketCancelAndroid(@RequestBody String request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			returnData = flightBusinessDao.flightTicketCancelAndroid(request);
		} catch (Exception e) {
			logger_log.error("flightTicketCancelAndroid :::::::::: " + e);
			returnData.put("message", "Exception! Please try again");
			returnData.put("status", "0");
		}
		System.out.println(returnData);
		return returnData;
	}

}
