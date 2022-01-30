package com.skyflight.MealBaggagesCustomModel;

public class Baggages {
	private String TripIndicator;
	private String WayType;
	private String code;
	private String description;
	private int amount;
	
	public String getWayType() {
		return WayType;
	}
	public void setWayType(String wayType) {
		WayType = wayType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getTripIndicator() {
		return TripIndicator;
	}
	public void setTripIndicator(String tripIndicator) {
		TripIndicator = tripIndicator;
	}
	
	
}
