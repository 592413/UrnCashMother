package com.skyhotel.CustomModel;

import java.util.List;

public class HotelDetailsOutput {
	private int ResponseStatus;
	private String TrackId;
	private String Searchid;
	private String Providerid;
	private String HotelId;
	private String HotelName;
	private String StarRating;
	private String HotelDescription;
	private String HotelPhone;
	private String HotelAddress;
	private String Latitude;
	private String Longitude;
	private String CountryName;
	private List<String> HotelAttractions;
	private List<String> HotelImages;
	private List<String> HotelFacilities;
	private List<HotelRoomsDetail> HotelRoomsDetail;
	private HotelRoomCombinations HotelRoomCombinations;
	private double adminmarkup;
	private double usermarkup;
	private double commission;
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
	public String getStarRating() {
		return StarRating;
	}
	public void setStarRating(String starRating) {
		StarRating = starRating;
	}
	public String getHotelDescription() {
		return HotelDescription;
	}
	public void setHotelDescription(String hotelDescription) {
		HotelDescription = hotelDescription;
	}
	public String getHotelPhone() {
		return HotelPhone;
	}
	public void setHotelPhone(String hotelPhone) {
		HotelPhone = hotelPhone;
	}
	public String getHotelAddress() {
		return HotelAddress;
	}
	public void setHotelAddress(String hotelAddress) {
		HotelAddress = hotelAddress;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getCountryName() {
		return CountryName;
	}
	public void setCountryName(String countryName) {
		CountryName = countryName;
	}
	public List<String> getHotelAttractions() {
		return HotelAttractions;
	}
	public void setHotelAttractions(List<String> hotelAttractions) {
		HotelAttractions = hotelAttractions;
	}
	public List<String> getHotelImages() {
		return HotelImages;
	}
	public void setHotelImages(List<String> hotelImages) {
		HotelImages = hotelImages;
	}
	public List<String> getHotelFacilities() {
		return HotelFacilities;
	}
	public void setHotelFacilities(List<String> hotelFacilities) {
		HotelFacilities = hotelFacilities;
	}
	public List<HotelRoomsDetail> getHotelRoomsDetail() {
		return HotelRoomsDetail;
	}
	public void setHotelRoomsDetail(List<HotelRoomsDetail> hotelRoomsDetail) {
		HotelRoomsDetail = hotelRoomsDetail;
	}
	public HotelRoomCombinations getHotelRoomCombinations() {
		return HotelRoomCombinations;
	}
	public void setHotelRoomCombinations(HotelRoomCombinations hotelRoomCombinations) {
		HotelRoomCombinations = hotelRoomCombinations;
	}
	public double getAdminmarkup() {
		return adminmarkup;
	}
	public void setAdminmarkup(double adminmarkup) {
		this.adminmarkup = adminmarkup;
	}
	public double getUsermarkup() {
		return usermarkup;
	}
	public void setUsermarkup(double usermarkup) {
		this.usermarkup = usermarkup;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	
	
	
	

}
