package com.skyflight.searchCustomModel;

import com.skyflight.model.Authentication;

public class CalenderfareInputRequest {
	private Authentication Authentication;
	private CalendarFare CalendarFare;
	public Authentication getAuthentication() {
		return Authentication;
	}
	public void setAuthentication(Authentication authentication) {
		Authentication = authentication;
	}
	public CalendarFare getCalendarFare() {
		return CalendarFare;
	}
	public void setCalendarFare(CalendarFare calendarFare) {
		CalendarFare = calendarFare;
	}
	
	

}
