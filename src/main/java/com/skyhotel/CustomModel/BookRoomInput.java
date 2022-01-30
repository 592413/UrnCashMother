package com.skyhotel.CustomModel;

import java.util.List;

import com.skyflight.model.Authentication;

public class BookRoomInput {
	private Authentication Authentication;
	private String TrackId;
	private String AvailabilityType;
	private String ResultIndex;
	private String Searchid;
	private String Providerid;
	private String HotelId;
	private String HotelName;
	private String GuestNationality;
	private String NoOfRooms;
	private String ReferenceNo;
	private List<HotelRoomDetails> HotelRoomDetails;
	private List<HotelPassenger> HotelPassenger;
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
	public String getAvailabilityType() {
		return AvailabilityType;
	}
	public void setAvailabilityType(String availabilityType) {
		AvailabilityType = availabilityType;
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
	public String getHotelName() {
		return HotelName;
	}
	public void setHotelName(String hotelName) {
		HotelName = hotelName;
	}
	public String getGuestNationality() {
		return GuestNationality;
	}
	public void setGuestNationality(String guestNationality) {
		GuestNationality = guestNationality;
	}
	public String getNoOfRooms() {
		return NoOfRooms;
	}
	public void setNoOfRooms(String noOfRooms) {
		NoOfRooms = noOfRooms;
	}
	public String getReferenceNo() {
		return ReferenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		ReferenceNo = referenceNo;
	}
	public List<HotelRoomDetails> getHotelRoomDetails() {
		return HotelRoomDetails;
	}
	public void setHotelRoomDetails(List<HotelRoomDetails> hotelRoomDetails) {
		HotelRoomDetails = hotelRoomDetails;
	}
	public List<HotelPassenger> getHotelPassenger() {
		return HotelPassenger;
	}
	public void setHotelPassenger(List<HotelPassenger> hotelPassenger) {
		HotelPassenger = hotelPassenger;
	}
	
	
	
	
}
