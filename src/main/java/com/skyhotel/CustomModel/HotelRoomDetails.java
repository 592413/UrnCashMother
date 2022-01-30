package com.skyhotel.CustomModel;

public class HotelRoomDetails {
	private String RoomCode;
	private String RoomTypeCode;
	private String RoomTypeName;
	private String RatePlanCode;
	private HotelRoomPrice HotelRoomPrice;
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
	
	
	

}
