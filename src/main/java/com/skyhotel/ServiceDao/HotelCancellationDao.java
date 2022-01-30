package com.skyhotel.ServiceDao;

import java.util.List;
import java.util.Map;

import com.skyhotel.model.HotelCancellation;




public interface HotelCancellationDao {

	public HotelCancellation getHotelCancellationByBookingid(String Bookingid);

	public boolean insertHotelCancellation(HotelCancellation HotelCancellation);

	public boolean updateHotelCancellation(HotelCancellation HotelCancellation);

	public List<HotelCancellation> getHotelCancellationByNamedQuery(String query, Map<String, Object> param);



}
