package com.skyhotel.BusinessDao;

import java.util.List;
import java.util.Map;

import com.recharge.model.User;
import com.skyhotel.CustomModel.HotelBookResponse;
import com.skyhotel.CustomModel.HotelDetailsOutput;
import com.skyhotel.CustomModel.RoomBlockOutput;

public interface HotelBusinessDao {

	public Map<String, Object> searchHotal(Map<String, String> request, User userDetails);

	public List<String> getDestinationCities(String term);

	public HotelDetailsOutput searchHotalDetails(Map<String, String> request, User userDetails);

	public RoomBlockOutput getroomblock(Map<String, Object> request);

	public Map<String, Object> getroombook(Map<String, Object> request, User user);

	Map<String, Object> SaveHotelMarkUpData(Map<String, String> request, User user);

	Map<String, Object> viewHotelBooikngReport(User userDetails, Map<String, String> request);


}
