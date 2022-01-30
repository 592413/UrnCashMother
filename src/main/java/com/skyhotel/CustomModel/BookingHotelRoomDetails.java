package com.skyhotel.CustomModel;

import java.util.List;

public class BookingHotelRoomDetails {
	
	private String AdultCount;
	private String ChildCount;
	private List<HotelPassengers> HotelPassengers;
	private String RoomCode;
	private String RoomTypeCode;
	private String RoomTypeName;
	private String RatePlanCode;
	private HotelRoomPrice HotelRoomPrice;
	private String CancellationPolicy;
	public String getAdultCount() {
		return AdultCount;
	}
	public void setAdultCount(String adultCount) {
		AdultCount = adultCount;
	}
	public String getChildCount() {
		return ChildCount;
	}
	public void setChildCount(String childCount) {
		ChildCount = childCount;
	}
	public List<HotelPassengers> getHotelPassengers() {
		return HotelPassengers;
	}
	public void setHotelPassengers(List<HotelPassengers> hotelPassengers) {
		HotelPassengers = hotelPassengers;
	}
	public String getRoomCode() {
		return RoomCode;
	}
	public void setRoomCode(String roomCode) {
		RoomCode = roomCode;
	}
	public String getRoomTypeCode() {
		return RoomTypeCode;
	}
	public void setRoomTypeCode(String roomTypeCode) {
		RoomTypeCode = roomTypeCode;
	}
	public String getRoomTypeName() {
		return RoomTypeName;
	}
	public void setRoomTypeName(String roomTypeName) {
		RoomTypeName = roomTypeName;
	}
	public String getRatePlanCode() {
		return RatePlanCode;
	}
	public void setRatePlanCode(String ratePlanCode) {
		RatePlanCode = ratePlanCode;
	}
	public HotelRoomPrice getHotelRoomPrice() {
		return HotelRoomPrice;
	}
	public void setHotelRoomPrice(HotelRoomPrice hotelRoomPrice) {
		HotelRoomPrice = hotelRoomPrice;
	}
	public String getCancellationPolicy() {
		return CancellationPolicy;
	}
	public void setCancellationPolicy(String cancellationPolicy) {
		CancellationPolicy = cancellationPolicy;
	}
	
	

}
