package com.skyhotel.CustomModel;

import java.util.List;

public class HotelBookResponse {
	
	private int ResponseStatus;
	private String TrackId;
	private String ConfirmationNo;
	private String BookingRefNo;
	private String BookingId;
	private String CheckIn;
	private String CheckOut;
	private String City;
	private String InvoiceNumber;
	private String InvoiceCreatedOn;
	private String BookingDate;
	private String HotelName;
	private String StarRating;
	private String HotelPolicyDetail;
	private String Address1;
	private String Address2;
	private String Latitude;
	private String Longitude;
	private String LastCancellationDate; 
	private int ErrorCode;
	private String ErrorMessage;
	private List<BookingHotelRoomDetails> BookingHotelRoomDetails;
	
	
	public HotelBookResponse() {
		super();
	}
	public HotelBookResponse(int responseStatus, int errorCode, String errorMessage) {
		super();
		ResponseStatus = responseStatus;
		ErrorCode = errorCode;
		ErrorMessage = errorMessage;
	}

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
	public String getConfirmationNo() {
		return ConfirmationNo;
	}
	public void setConfirmationNo(String confirmationNo) {
		ConfirmationNo = confirmationNo;
	}
	public String getBookingRefNo() {
		return BookingRefNo;
	}
	public void setBookingRefNo(String bookingRefNo) {
		BookingRefNo = bookingRefNo;
	}
	public String getBookingId() {
		return BookingId;
	}
	public void setBookingId(String bookingId) {
		BookingId = bookingId;
	}
	public String getCheckIn() {
		return CheckIn;
	}
	public void setCheckIn(String checkIn) {
		CheckIn = checkIn;
	}
	public String getCheckOut() {
		return CheckOut;
	}
	public void setCheckOut(String checkOut) {
		CheckOut = checkOut;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getInvoiceNumber() {
		return InvoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		InvoiceNumber = invoiceNumber;
	}
	public String getInvoiceCreatedOn() {
		return InvoiceCreatedOn;
	}
	public void setInvoiceCreatedOn(String invoiceCreatedOn) {
		InvoiceCreatedOn = invoiceCreatedOn;
	}
	public String getBookingDate() {
		return BookingDate;
	}
	public void setBookingDate(String bookingDate) {
		BookingDate = bookingDate;
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
	public String getHotelPolicyDetail() {
		return HotelPolicyDetail;
	}
	public void setHotelPolicyDetail(String hotelPolicyDetail) {
		HotelPolicyDetail = hotelPolicyDetail;
	}
	public String getAddress1() {
		return Address1;
	}
	public void setAddress1(String address1) {
		Address1 = address1;
	}
	public String getAddress2() {
		return Address2;
	}
	public void setAddress2(String address2) {
		Address2 = address2;
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
	public String getLastCancellationDate() {
		return LastCancellationDate;
	}
	public void setLastCancellationDate(String lastCancellationDate) {
		LastCancellationDate = lastCancellationDate;
	}
	public List<BookingHotelRoomDetails> getBookingHotelRoomDetails() {
		return BookingHotelRoomDetails;
	}
	public void setBookingHotelRoomDetails(List<BookingHotelRoomDetails> bookingHotelRoomDetails) {
		BookingHotelRoomDetails = bookingHotelRoomDetails;
	}
	public int getErrorCode() {
		return ErrorCode;
	}
	public void setErrorCode(int errorCode) {
		ErrorCode = errorCode;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	
	

}
