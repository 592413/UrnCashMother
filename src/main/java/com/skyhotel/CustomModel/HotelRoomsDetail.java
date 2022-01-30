package com.skyhotel.CustomModel;

import java.util.List;

public class HotelRoomsDetail {
	private boolean RequireAllPax;
	private String RoomCode;
	private String RoomName;
	private String RatePlanCode;
	private String RoomTypeCode;
	private String RoomTypeName;
	private String InfoSource;
	private String SequenceNo;
	private String LastCancellationDate;
	private List<CancellationDetail> CancellationDetail;
	private List<String> Amenities;
	private List<String> Inclusion;
	private String CancellationPolicies;
	private String RatePlan;
	private HotelRoomPrice HotelRoomPrice;
	
	private double RoomPrice;
	private double Tax;
	private double total;
	private double OtherCharges;
	private double customRoomPrice;
	private double customtotal;
	public boolean isRequireAllPax() {
		return RequireAllPax;
	}
	public void setRequireAllPax(boolean requireAllPax) {
		RequireAllPax = requireAllPax;
	}
	public String getRoomCode() {
		return RoomCode;
	}
	public void setRoomCode(String roomCode) {
		RoomCode = roomCode;
	}
	public String getRoomName() {
		return RoomName;
	}
	public void setRoomName(String roomName) {
		RoomName = roomName;
	}
	public String getRatePlanCode() {
		return RatePlanCode;
	}
	public void setRatePlanCode(String ratePlanCode) {
		RatePlanCode = ratePlanCode;
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
	public String getInfoSource() {
		return InfoSource;
	}
	public void setInfoSource(String infoSource) {
		InfoSource = infoSource;
	}
	public String getSequenceNo() {
		return SequenceNo;
	}
	public void setSequenceNo(String sequenceNo) {
		SequenceNo = sequenceNo;
	}
	public String getLastCancellationDate() {
		return LastCancellationDate;
	}
	public void setLastCancellationDate(String lastCancellationDate) {
		LastCancellationDate = lastCancellationDate;
	}
	public List<CancellationDetail> getCancellationDetail() {
		return CancellationDetail;
	}
	public void setCancellationDetail(List<CancellationDetail> cancellationDetail) {
		CancellationDetail = cancellationDetail;
	}
	public List<String> getAmenities() {
		return Amenities;
	}
	public void setAmenities(List<String> amenities) {
		Amenities = amenities;
	}
	public List<String> getInclusion() {
		return Inclusion;
	}
	public void setInclusion(List<String> inclusion) {
		Inclusion = inclusion;
	}
	public String getCancellationPolicies() {
		return CancellationPolicies;
	}
	public void setCancellationPolicies(String cancellationPolicies) {
		CancellationPolicies = cancellationPolicies;
	}
	public String getRatePlan() {
		return RatePlan;
	}
	public void setRatePlan(String ratePlan) {
		RatePlan = ratePlan;
	}
	public HotelRoomPrice getHotelRoomPrice() {
		return HotelRoomPrice;
	}
	public void setHotelRoomPrice(HotelRoomPrice hotelRoomPrice) {
		HotelRoomPrice = hotelRoomPrice;
	}
	public double getRoomPrice() {
		return RoomPrice;
	}
	public void setRoomPrice(double roomPrice) {
		RoomPrice = roomPrice;
	}
	public double getTax() {
		return Tax;
	}
	public void setTax(double tax) {
		Tax = tax;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getOtherCharges() {
		return OtherCharges;
	}
	public void setOtherCharges(double otherCharges) {
		OtherCharges = otherCharges;
	}
	public double getCustomRoomPrice() {
		return customRoomPrice;
	}
	public void setCustomRoomPrice(double customRoomPrice) {
		this.customRoomPrice = customRoomPrice;
	}
	public double getCustomtotal() {
		return customtotal;
	}
	public void setCustomtotal(double customtotal) {
		this.customtotal = customtotal;
	}
	

	

}
