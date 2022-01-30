package com.skyhotel.CustomModel;

import com.skyflight.model.Authentication;

public class HotelDetailsInput {
	private Authentication Authentication;
	private String TrackId;
	private String ResultIndex;
	private String Searchid;
	private String Providerid;
	private String HotelId;
	public Authentication getAuthentication() {
		return Authentication;
	}
	public void setAuthentication(Authentication authentication) {
		Authentication = authentication;
	}
	public String getTrackId() {
		return TrackId;
	}
	public void setTrackId(String trackId) {
		TrackId = trackId;
	}
	public String getResultIndex() {
		return ResultIndex;
	}
	public void setResultIndex(String resultIndex) {
		ResultIndex = resultIndex;
	}
	public String getSearchid() {
		return Searchid;
	}
	public void setSearchid(String searchid) {
		Searchid = searchid;
	}
	public String getProviderid() {
		return Providerid;
	}
	public void setProviderid(String providerid) {
		Providerid = providerid;
	}
	public String getHotelId() {
		return HotelId;
	}
	public void setHotelId(String hotelId) {
		HotelId = hotelId;
	}
	
	

}
