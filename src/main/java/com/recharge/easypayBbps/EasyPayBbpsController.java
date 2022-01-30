package com.recharge.easypayBbps;

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
import com.recharge.model.User;
@RestController
@SessionAttributes(value = "userDetails", types = {User.class})
public class EasyPayBbpsController {
	private static final Logger logger_log = Logger.getLogger(EasyPayBbpsController.class);
	@Autowired HttpSession session;
	@Autowired EasyPayBbpsDao EasyPayBbpsDao;
	
	@RequestMapping(value = "/eBillFetch", method = RequestMethod.POST, headers = "content-type=application/json")
	public Map<String, Object> eBillFetch(@RequestBody Map<String,String>  request){
		Map<String, Object> returnData = new HashMap<String, Object>();		
		try {			
			if (session.getAttribute("UserDetails") == null) {
				returnData.put("nextPage", "home");
				returnData.put("message", "Session Expire! Please Login Again.");
				returnData.put("status", "0");
			}else {
				User userDetails = (User) session.getAttribute("UserDetails");
				returnData = EasyPayBbpsDao.eBillFetch(request, userDetails);
			}
		}catch (Exception e) {
			logger_log.error("eBillFetch :::::::::: "+e);
			returnData.put("message", "Exception! Please try again");		
			returnData.put("status", "0");
		}
		returnData.put("nextPage", "home");
		return returnData;
	}

}
