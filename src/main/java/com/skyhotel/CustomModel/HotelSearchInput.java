package com.skyhotel.CustomModel;

import java.util.List;

import com.skyflight.model.Authentication;

public class HotelSearchInput {
	private Authentication Authentication;
	private String CheckIn;
	private String Nights;
	private String CountryCode;
	private String CityId;
	private List<SearchRoomGuests> RoomGuests;
	public Authentication getAuthentication() {
		return Authentication;
	}
	public void setAuthentication(Authentication authentication) {
		Authentication = authentication;
	}
	public String getCheckIn() {
		return CheckIn;
	}
	public void setCheckIn(String checkIn) {
		CheckIn = checkIn;
	}
	public String getNights() {
		return Nights;
	}
	public void setNights(String nights) {
		Nights = nights;
	}
	public String getCountryCode() {
		return CountryCode;
	}
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}
	public String getCityId() {
		return CityId;
	}
	public void setCityId(String cityId) {
		CityId = cityId;
	}
	public List<SearchRoomGuests> getRoomGuests() {
		return RoomGuests;
	}
	public void setRoomGuests(List<SearchRoomGuests> roomGuests) {
		RoomGuests = roomGuests;
	}

	
	

}
