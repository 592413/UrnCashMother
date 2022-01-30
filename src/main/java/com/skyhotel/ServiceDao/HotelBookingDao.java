package com.skyhotel.ServiceDao;

import java.util.List;
import java.util.Map;

import com.skyhotel.model.HotelBooking;



public interface HotelBookingDao {


	public HotelBooking getHotelBookingByBookingid(String Bookingid);

	public boolean insertHotelBooking(HotelBooking HotelBooking);

	public boolean updateHotelBooking(HotelBooking HotelBooking);

	public List<HotelBooking> getHotelBookingByNamedQuery(String query, Map<String, Object> param);

	


}
