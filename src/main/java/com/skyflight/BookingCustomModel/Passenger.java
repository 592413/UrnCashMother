package com.skyflight.BookingCustomModel;

import java.util.List;

public class Passenger {
	private String TicketId;
	private String TicketNumber;
	private String PaxType;
	private String Title;
	private String FirstName;
	private String LastName;
	private String gender;
	private List<BookedSegments> BookedSegments;
	
	
	
	public String getTicketId() {
		return TicketId;
	}
	public void setTicketId(String ticketId) {
		TicketId = ticketId;
	}
	public String getTicketNumber() {
		return TicketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		TicketNumber = ticketNumber;
	}
	public String getPaxType() {
		return PaxType;
	}
	public void setPaxType(String paxType) {
		PaxType = paxType;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<BookedSegments> getBookedSegments() {
		return BookedSegments;
	}
	public void setBookedSegments(List<BookedSegments> bookedSegments) {
		BookedSegments = bookedSegments;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	

}
