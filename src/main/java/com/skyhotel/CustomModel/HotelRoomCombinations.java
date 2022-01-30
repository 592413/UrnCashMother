package com.skyhotel.CustomModel;

import java.util.List;

public class HotelRoomCombinations {

	private String InfoSource;
	private boolean IsPolicyPerStay;
	private List<HotelRoomCombination> HotelRoomCombination;
	public String getInfoSource() {
		return InfoSource;
	}
	public void setInfoSource(String infoSource) {
		InfoSource = infoSource;
	}
	public boolean isIsPolicyPerStay() {
		return IsPolicyPerStay;
	}
	public void setIsPolicyPerStay(boolean isPolicyPerStay) {
		IsPolicyPerStay = isPolicyPerStay;
	}
	public List<HotelRoomCombination> getHotelRoomCombination() {
		return HotelRoomCombination;
	}
	public void setHotelRoomCombination(List<HotelRoomCombination> hotelRoomCombination) {
		HotelRoomCombination = hotelRoomCombination;
	}
	
	
	
}
