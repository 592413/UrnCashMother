package com.skyflight.businessimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.recharge.model.User;
import com.skyflight.businessdao.CustomQueryServiceFlight;
import com.skyflight.businessdao.FlightMarkupDao;
import com.skyflight.customquery.CustomSqlQueryFlight;
import com.skyflight.model.Airline;
import com.skyflight.model.Markup;
import com.skyflight.searchCustomModel.FlightMarkUp;
import com.skyflight.servicedao.AirlineDao;
import com.skyflight.servicedao.MarkupDao;

@Service("FlightMarkupDao")
public class FlightMarkUpImpl implements FlightMarkupDao{
	private static final Logger log = Logger.getLogger(FlightMarkupDao.class);
	@Autowired AirlineDao airlineDao;
	@Autowired MarkupDao markupdao;
	@Autowired CustomQueryServiceFlight customQueryserviceFlight;
	
	@Override
	public Map<String, Object> saveMarkUpData(Map<String, String> request, User user) {
		Map<String, Object> returndata = new HashMap<String, Object>();
		Map<String, Object> param = null;
		boolean flag = false;
		try {
			param = new HashMap<String, Object>();
			param.put("service_type", request.get("service_type"));
			List<Airline> airlines = airlineDao.getAirlineByNamedQuery("getAirlinebyservice", param);
			//System.out.println(airlines.size());
			for (Airline air : airlines) {
				param = new HashMap<String, Object>();
				param.put("airline_code", air.getAirline_code());
				param.put("username", user.getUserId());
				//System.out.println("airline_code:::::::::::::::"+air.getAirline_code());
				List<Markup> list = markupdao.getMarkupByNamedQuery("getMarkupByAirlinecodeAndUsername",param);
			   // System.out.println("MarkupLIST::::::::::"+list.size());
				if (list.size() > 0) {
					   Markup markup1 = list.get(0);
					   markup1.setMarkup_type(request.get("markup_type"));
					   markup1.setMarkup_value(Double.parseDouble(request.get("markup_value")));
					   flag=markupdao.updateMarkup(list.get(0));
				
				} else {
					System.out.println("Airline_code::::::::::::::"+air.getAirline_code());
					Markup markup = new Markup(user.getUserId(), air.getAirline_code(),
					Double.parseDouble(request.get("markup_value")), request.get("markup_type"));
					flag=markupdao.insertMarkup(markup);
					
				}
			}
			if(flag){
				returndata.put("message","success");
			}
		} catch (Exception e) {
			log.error("saveMarkUpData::::::::::::" + e);
		}
		return returndata;
	}
	
	
	@Override
	public Map<String, Object> showAllDomesticMarkup(User user) {
		Map<String, Object> returndata = new HashMap<String, Object>();
		Map<String, String> param = null;
		String service_type = "Domestic Flight";
		try{
			param = new HashMap<String, String>();
			//System.out.println("username::::::::::::"+user.getUserId());
			param.put("username", user.getUserId());
			param.put("service_type",service_type);
			List<FlightMarkUp> list = customQueryserviceFlight.getDomesticFlightMarkupByUsername(CustomSqlQueryFlight.getAllDomesticMarkup,param);
			if(!list.isEmpty()){
			returndata.put("DomesticMarkup",list);
			}else{
			returndata.put("message","No data available");	
			}
			log.warn("showAllDomesticMarkup::::::::::::"+list.size());
			
		}catch (Exception e) {
			log.error("showAllDomesticMarkup::::::::::::"+e);
		}
		
		return returndata;
	}
	
	@Override
	public Map<String, Object> savesingleData(Map<String, String> request, User user) {
		Map<String, Object> returndata = new HashMap<String, Object>();
		Map<String, Object> param = null;
		boolean flag = false;
		try {
			System.out.println(request);
		Markup mk=markupdao.getMarkupByid(Integer.parseInt(request.get("id")));
		mk.setMarkup_value(Double.parseDouble(request.get("markup_value")));
		mk.setMarkup_type(request.get("markup_type"));
		flag =markupdao.updateMarkup(mk);
		if(flag){
			returndata.put("message","success");
			returndata.put("status","1");
		}else{
			returndata.put("message","Failed");
			returndata.put("status","0");
		}
		} catch (Exception e) {
			log.error("saveMarkUpData::::::::::::" + e);
			returndata.put("status","0");
		}
		return returndata;
	}
}
