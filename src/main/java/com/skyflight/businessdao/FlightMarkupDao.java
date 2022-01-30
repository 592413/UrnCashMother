package com.skyflight.businessdao;

import java.util.Map;

import com.recharge.model.User;

public interface FlightMarkupDao {

	public Map<String, Object> saveMarkUpData(Map<String, String> request, User user);

	Map<String, Object> showAllDomesticMarkup(User user);

	Map<String, Object> savesingleData(Map<String, String> request, User user);

}
