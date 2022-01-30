package com.skyflight.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.recharge.model.User;
import com.skyflight.businessdao.FlightBookingImplDao;


@Controller
public class SkyViewController {
	@Autowired
	HttpSession session;
	@Autowired FlightBookingImplDao FlightBookingDao;
	
	private static final Logger logger_log = Logger.getLogger(SkyViewController.class);


	@RequestMapping(value = "/flightsearch", method = RequestMethod.GET)
	public String skyflightsearch() {
		if (session.getAttribute("UserDetails") == null) {
			session.setAttribute("msg", "Session Expire! Please Login Again.");
			return "index";
		} else {
			return "flight/skyflightsearch";
		}
		
	}
	
	@RequestMapping(value = "/flight", method = RequestMethod.GET)
	public String flightmarkup() {
		if (session.getAttribute("UserDetails") == null) {
			session.setAttribute("msg", "Session Expire! Please Login Again.");
			return "index";
		} else {
			return "flight/flightmarkup";
		}
	}
	
	@RequestMapping(value = "/flightTicket", method = RequestMethod.GET)
	public String flightTicket() {
		return "flight/flightTicket";
	}
	
	@RequestMapping(value = "/flightinvoice", method = RequestMethod.GET)
	public String flightinvoice() {
		return "flight/flightinvoice";
	}
	
	/*@RequestMapping(value = "/oneway", method = RequestMethod.GET)
	public String flightlist() {
		return "flight/flightlist";
	}
	
	@RequestMapping(value = "/roundtrip", method = RequestMethod.GET)
	public String roundtrip() {
		return "flight/roundtrip";
	}*/
	
	

}
