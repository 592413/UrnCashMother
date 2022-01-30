package com.skyflight.BookingCustomModel;

import java.util.List;

import com.skyflight.model.Authentication;

public class BookingRequest {
	private Authentication Authentication;
	private String TrackId;
	private String TripType;
	private boolean isDomestic;
	private List<PassengerDetails> PassengerDetails;
	private List<BookingSegmentDetail> BookingSegmentDetail;
	private List<Fare> Fare;
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
	public String getTripType() {
		return TripType;
	}
	public void setTripType(String tripType) {
		TripType = tripType;
	}
	public List<PassengerDetails> getPassengerDetails() {
		return PassengerDetails;
	}
	public void setPassengerDetails(List<PassengerDetails> passengerDetails) {
		PassengerDetails = passengerDetails;
	}
	public List<BookingSegmentDetail> getBookingSegmentDetail() {
		return BookingSegmentDetail;
	}
	public void setBookingSegmentDetail(List<BookingSegmentDetail> bookingSegmentDetail) {
		BookingSegmentDetail = bookingSegmentDetail;
	}
	public List<Fare> getFare() {
		return Fare;
	}
	public void setFare(List<Fare> fare) {
		Fare = fare;
	}
	public boolean isDomestic() {
		return isDomestic;
	}
	public void setDomestic(boolean isDomestic) {
		this.isDomestic = isDomestic;
	}
	
	
	
}
