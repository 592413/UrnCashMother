package com.skyhotel.CustomModel;

import java.util.List;

public class SearchResult {
	private int ResponseStatus;
	private String TrackId;
	private String CheckIn;
	private String CheckOut;
	private String nightno;
	private String CityId;
	private String RoomsNo;
	private String guestno;
	private List<RoomGuests> RoomGuests;
	private List<HotelResults> HotelResults;
	private int ErrorCode;
	private String ErrorMessage;
	
	
	public int getResponseStatus() {
		return ResponseStatus;
	}
	public void setResponseStatus(int responseStatus) {
		ResponseStatus = responseStatus;
	}
	public String getTrackId() {
		return TrackId;
	}
	public void setTrackId(String trackId) {
		TrackId = trackId;
	}
	public String getCheckIn() {
		return CheckIn;
	}
	public void setCheckIn(String checkIn) {
		CheckIn = checkIn;
	}
	public String getCheckOut() {
		return CheckOut;
	}
	public void setCheckOut(String checkOut) {
		CheckOut = checkOut;
	}
	public String getCityId() {
		return CityId;
	}
	public void setCityId(String cityId) {
		CityId = cityId;
	}
	public String getRoomsNo() {
		return RoomsNo;
	}
	public void setRoomsNo(String roomsNo) {
		RoomsNo = roomsNo;
	}
	public List<RoomGuests> getRoomGuests() {
		return RoomGuests;
	}
	public void setRoomGuests(List<RoomGuests> roomGuests) {
		RoomGuests = roomGuests;
	}
	public List<HotelResults> getHotelResults() {
		return HotelResults;
	}
	public void setHotelResults(List<HotelResults> hotelResults) {
		HotelResults = hotelResults;
	}
	public String getNightno() {
		return nightno;
	}
	public void setNightno(String nightno) {
		this.nightno = nightno;
	}
	public String getGuestno() {
		return guestno;
	}
	public void setGuestno(String guestno) {
		this.guestno = guestno;
	}
	public int getErrorCode() {
		return ErrorCode;
	}
	public void setErrorCode(int errorCode) {
		ErrorCode = errorCode;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	
	
	

}
