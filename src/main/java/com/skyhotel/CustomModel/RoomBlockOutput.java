package com.skyhotel.CustomModel;

import java.util.List;

public class RoomBlockOutput {
	private int ResponseStatus;
	private String AvailabilityType;
	private String TrackId;
	private String HotelRules;
	private boolean IsPriceChanged;
	private boolean IsCancellationPolicyChanged;
	private boolean IsHotelPolicyChanged;
	private boolean IsPackageFare;
	private boolean IsPackageDetailsMandatory;
	private List<HotelRoomsDetail> HotelRoomsDetail;
	
	
	
	
	public String getHotelRules() {
		return HotelRules;
	}
	public void setHotelRules(String hotelRules) {
		HotelRules = hotelRules;
	}
	public int getResponseStatus() {
		return ResponseStatus;
	}
	public void setResponseStatus(int responseStatus) {
		ResponseStatus = responseStatus;
	}
	public String getAvailabilityType() {
		return AvailabilityType;
	}
	public void setAvailabilityType(String availabilityType) {
		AvailabilityType = availabilityType;
	}
	public String getTrackId() {
		return TrackId;
	}
	public void setTrackId(String trackId) {
		TrackId = trackId;
	}
	public boolean isIsPriceChanged() {
		return IsPriceChanged;
	}
	public void setIsPriceChanged(boolean isPriceChanged) {
		IsPriceChanged = isPriceChanged;
	}
	public boolean isIsCancellationPolicyChanged() {
		return IsCancellationPolicyChanged;
	}
	public void setIsCancellationPolicyChanged(boolean isCancellationPolicyChanged) {
		IsCancellationPolicyChanged = isCancellationPolicyChanged;
	}
	public boolean isIsHotelPolicyChanged() {
		return IsHotelPolicyChanged;
	}
	public void setIsHotelPolicyChanged(boolean isHotelPolicyChanged) {
		IsHotelPolicyChanged = isHotelPolicyChanged;
	}
	public boolean isIsPackageFare() {
		return IsPackageFare;
	}
	public void setIsPackageFare(boolean isPackageFare) {
		IsPackageFare = isPackageFare;
	}
	public boolean isIsPackageDetailsMandatory() {
		return IsPackageDetailsMandatory;
	}
	public void setIsPackageDetailsMandatory(boolean isPackageDetailsMandatory) {
		IsPackageDetailsMandatory = isPackageDetailsMandatory;
	}
	public List<HotelRoomsDetail> getHotelRoomsDetail() {
		return HotelRoomsDetail;
	}
	public void setHotelRoomsDetail(List<HotelRoomsDetail> hotelRoomsDetail) {
		HotelRoomsDetail = hotelRoomsDetail;
	}
	

	
}
