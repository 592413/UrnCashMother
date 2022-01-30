package com.skyhotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="hotelbooking")
/*
 * @NamedQueries({
 * 
 * @NamedQuery(name = "getCitybyCityId", query =
 * "from City  where CityId=:CityId")
 * 
 * })
 */

 @NamedQueries(@NamedQuery(name="getHotelBookingbyBookingId",query="From HotelBooking where booking_id=:booking_id"))
public class HotelBooking {
	@Id
	private int id;
	private String booking_id;
	private String invoiceno;
	private String status;
	private int rooms;
	private String room_type;
	private String check_in;
	private String check_out;
	private String booking_date;
	private String booking_time;
	private String username;
	private String hotelname;
	private String traceid;
	private int totalguests;
	private String confirmationno;
	private String bookingrefno;
	private String sources;
	
	
	
	
	public HotelBooking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HotelBooking(String booking_id,String invoiceno, String status, int rooms, String room_type, String check_in,
			String check_out, String booking_date, String booking_time,String username, String hotelname, String traceid,
			int totalguests, String confirmationno, String bookingrefno,String sources) {
		super();
		this.booking_id = booking_id;
		this.invoiceno = invoiceno;
		this.status = status;
		this.rooms = rooms;
		this.room_type = room_type;
		this.check_in = check_in;
		this.check_out = check_out;
		this.booking_date = booking_date;
		this.booking_time = booking_time;
		this.username = username;
		this.hotelname = hotelname;
		this.traceid = traceid;
		this.totalguests = totalguests;
		this.confirmationno = confirmationno;
		this.bookingrefno = bookingrefno;
		this.sources = sources;
	}
	public int getId() {
		return id;
	}
	
	
	public String getBooking_time() {
		return booking_time;
	}
	public void setBooking_time(String booking_time) {
		this.booking_time = booking_time;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getRooms() {
		return rooms;
	}
	public void setRooms(int rooms) {
		this.rooms = rooms;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public String getCheck_in() {
		return check_in;
	}
	public void setCheck_in(String check_in) {
		this.check_in = check_in;
	}
	public String getCheck_out() {
		return check_out;
	}
	public void setCheck_out(String check_out) {
		this.check_out = check_out;
	}
	public String getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(String booking_date) {
		this.booking_date = booking_date;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	public String getTraceid() {
		return traceid;
	}
	public void setTraceid(String traceid) {
		this.traceid = traceid;
	}
	
	public int getTotalguests() {
		return totalguests;
	}
	public void setTotalguests(int totalguests) {
		this.totalguests = totalguests;
	}
	public String getConfirmationno() {
		return confirmationno;
	}
	public void setConfirmationno(String confirmationno) {
		this.confirmationno = confirmationno;
	}
	public String getBookingrefno() {
		return bookingrefno;
	}
	public void setBookingrefno(String bookingrefno) {
		this.bookingrefno = bookingrefno;
	}
	public String getInvoiceno() {
		return invoiceno;
	}
	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}
	public String getSources() {
		return sources;
	}
	public void setSources(String sources) {
		this.sources = sources;
	}
    
	
	
}
