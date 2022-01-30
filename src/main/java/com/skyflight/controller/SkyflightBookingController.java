package com.skyflight.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.skyflight.businessdao.FlightBookingImplDao;
import com.recharge.model.User;
import com.skyflight.BookingCustomModel.AirBookResponse;

@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class SkyflightBookingController {
	
	private static final Logger log = Logger.getLogger(SkyflightBookingController.class);
	
	@Autowired HttpSession session;
	@Autowired FlightBookingImplDao FlightBookingDao;
	
	@RequestMapping(value="/bookingFlight",method=RequestMethod.POST)
	public Map<String,Object> Flightbookrequest(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData=FlightBookingDao.Flightbookrequest(userDetails,request);
				
			}
			
		}catch(Exception e){
			log.error("bookingFlight:::::::::::::"+e);
			}
		return returnData;
		
	}
	
	
	@RequestMapping(value="/viewBooikngReport",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String,Object> viewBooikngReport(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData=FlightBookingDao.viewBooikngReport(userDetails,request);
			}
			
		}catch(Exception e){
			log.error("viewBooikngReport:::::::::::::"+e);
			}
		return returnData;
	}
	
	
/*	@RequestMapping(value="/ViewTicket",method=RequestMethod.POST,headers="content-type=application/json")
	public ModelAndView ViewTicket(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		ModelAndView mv=new ModelAndView("flightTicket");
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData=FlightBookingDao.ViewTicket(userDetails,request);
				System.out.println(returnData);
				session.setAttribute("flightbooking", returnData.get("request"));
				session.setAttribute("flightdetails", returnData.get("flightdetails"));
				session.setAttribute("listPassengerfare", returnData.get("listPassengerfare"));
				session.setAttribute("passengerlist", returnData.get("passengerlist"));
				session.setAttribute("listFlightFare", returnData.get("listFlightFare"));
				
				mv.addObject("flightbooking", returnData.get("request"));
				mv.addObject("flightdetails", returnData.get("flightdetails"));
				mv.addObject("listPassengerfare", returnData.get("listPassengerfare"));
				mv.addObject("passengerlist", returnData.get("passengerlist"));
			}
			
		}catch(Exception e){
			log.error("ViewTicket:::::::::::::"+e);
			}
		return mv;
	}*/
	
	@RequestMapping(value="/ViewTicket",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> ViewTicket(@RequestBody Map<String,Object>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();
		ModelAndView mv=new ModelAndView("flightTicket");
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData=FlightBookingDao.ViewTicket(userDetails,request);
				System.out.println(returnData);
				/*session.setAttribute("flightbooking", returnData.get("request"));
				session.setAttribute("flightdetails", returnData.get("flightdetails"));
				session.setAttribute("listPassengerfare", returnData.get("listPassengerfare"));
				session.setAttribute("passengerlist", returnData.get("passengerlist"));
				session.setAttribute("listFlightFare", returnData.get("listFlightFare"));
				
				mv.addObject("flightbooking", returnData.get("request"));
				mv.addObject("flightdetails", returnData.get("flightdetails"));
				mv.addObject("listPassengerfare", returnData.get("listPassengerfare"));
				mv.addObject("passengerlist", returnData.get("passengerlist"));*/
			}
			
		}catch(Exception e){
			log.error("ViewTicket:::::::::::::"+e);
			}
		return returnData;
	}
	
	@RequestMapping(value="/cancelTicket",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> cancelTicket(@RequestBody Map<String, Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User user =(User) session.getAttribute("UserDetails");
			    returnData =FlightBookingDao.cancelTicket(request,user);
			
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("cancelTicket:::::::::::::" + returnData);
		}finally{
				
		}
		log.warn("cancelTicket:::::::::::::" + returnData);
		return returnData;
	}

	@RequestMapping(value="/donetransactionfess",method=RequestMethod.POST,headers="content-type=application/json")
	public Map<String, Object> donetransactionfess(@RequestBody Map<String, Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try{
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User user =(User) session.getAttribute("UserDetails");
			    returnData =FlightBookingDao.donetransactionfess(request,user);
			
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("donetransactionfess:::::::::::::" + returnData);
		}finally{
				
		}
		log.warn("donetransactionfess:::::::::::::" + returnData);
		return returnData;
	}
}
