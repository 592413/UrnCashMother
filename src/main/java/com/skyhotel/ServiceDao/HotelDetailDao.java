package com.skyhotel.ServiceDao;

import java.util.List;
import java.util.Map;

import com.skyhotel.model.HotelDetail;


public interface HotelDetailDao {
	
	public boolean insertHotelDetail(HotelDetail HotelDetail);

	public boolean updateHotelDetail(HotelDetail HotelDetail);

	public List<HotelDetail> getHotelDetailByNamedQuery(String query, Map<String, Object> param);


}
