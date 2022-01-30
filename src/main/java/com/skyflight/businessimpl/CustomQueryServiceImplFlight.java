package com.skyflight.businessimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.businessdao.CustomQueryService;
import com.skyflight.businessdao.CustomQueryServiceFlight;
import com.skyflight.searchCustomModel.FlightMarkUp;
@Service("customQueryserviceFlight")
public class CustomQueryServiceImplFlight implements CustomQueryServiceFlight {
	private static final Logger logger_log = Logger.getLogger(CustomQueryServiceFlight.class);
	@Autowired CustomQueryService customQueryService;
	
	@Override
	public List<FlightMarkUp> getDomesticFlightMarkupByUsername(String query, Map<String, String> parameters) {
		List<FlightMarkUp> list = new ArrayList<FlightMarkUp>();

		try {
			List<Object> objlist  = customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if (!objlist.isEmpty()) {
				for(Object object:objlist){
				Object[] obj = (Object[])object;
				FlightMarkUp fm = new FlightMarkUp((Integer)obj[0], obj[1].toString(), obj[2].toString(),(Double) obj[3], obj[4].toString(), obj[5].toString());
				list.add(fm);		
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("getDomesticFlightMarkupByUsername::::::::::::::::::::::::"+e);
		}
		return list;
	}
}
