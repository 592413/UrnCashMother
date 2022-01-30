package com.skyhotel.ServiceDao;

import java.util.List;
import java.util.Map;

import com.skyhotel.model.HotelFailedBooking;


public interface HotelFailedBookingDao {
	
	public boolean insertHotelFailedBooking(HotelFailedBooking HotelFailedBooking);

	public boolean updateHotelFailedBooking(HotelFailedBooking HotelFailedBooking);

	public List<HotelFailedBooking> getHotelFailedBookingByNamedQuery(String query, Map<String, Object> param);


}
