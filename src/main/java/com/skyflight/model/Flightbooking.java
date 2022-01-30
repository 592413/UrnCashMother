package com.skyflight.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="flight_booking")
@NamedQueries({
	@NamedQuery(name="getFltghtBookingByUsernameAndstatus",query="From Flightbooking where username=:username and booking_status  like :booking_status and date between :start_date and :end_date"),
	@NamedQuery(name="getFltghtBookingByUsernameAnddate",query="From Flightbooking where username=:username and date between :start_date and :end_date"),
	@NamedQuery(name="getFltghtBookingBystatus",query="From Flightbooking where booking_status  like :booking_status and date between :start_date and :end_date"),
	@NamedQuery(name="getFltghtBookingBystatusall",query="From Flightbooking where date between :start_date and :end_date"),
	@NamedQuery(name="getFltghtBookingByUsername",query="From Flightbooking where username=:username and date between :start_date and :end_date")
	
})
public class Flightbooking implements Serializable {
	
	@Id
	private int id;
	private String username;
	private String booking_id;
	private String PNR;
	private String traceid;
	private String date;
	private String time;
	private String source;
	private String destination;
	private String travel_date;
	@Column(name="status")
	private String booking_status;
	private boolean isRefundable;
	private String airlinepnr;
	private String crspnr;
	private String flighttype;
	private String airlinecd;
	private String duration;
	private double openbl;
	private double netamount;
	private double closebl;
	@Transient
	private List<Flightdetail> flighdetail;
	@Transient
	private List<PassengerDetail>  passengerlist;
	@Transient
	private String airlinecode;
	
	@Transient
	private String uname;
	@Transient
	private String usermobile;
	
	@Transient
	private String airlinename;

	public Flightbooking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flightbooking(String username, String booking_id, String pNR, String traceid, String date, String time,
			String source, String destination, String travel_date, String booking_status, boolean isRefundable,
			String airlinepnr, String crspnr,String flighttype,String airlinecd,String duration,double openbl,double netamount,double closebl) {
		super();
		this.username = username;
		this.booking_id = booking_id;
		PNR = pNR;
		this.traceid = traceid;
		this.date = date;
		this.time = time;
		this.source = source;
		this.destination = destination;
		this.travel_date = travel_date;
		this.booking_status = booking_status;
		this.isRefundable = isRefundable;
		this.airlinepnr = airlinepnr;
		this.crspnr = crspnr;
		this.flighttype = flighttype;
		this.airlinecd = airlinecd;
		this.duration = duration;
		this.openbl = openbl;
		this.netamount = netamount;
		this.closebl = closebl;
	}
	
	public String getAirlinecode() {
		return airlinecode;
	}

	public void setAirlinecode(String airlinecode) {
		this.airlinecode = airlinecode;
	}
	
	
	public List<Flightdetail> getFlighdetail() {
		return flighdetail;
	}

	public void setFlighdetail(List<Flightdetail> flighdetail) {
		this.flighdetail = flighdetail;
	}

	public List<PassengerDetail> getPassengerlist() {
		return passengerlist;
	}

	public void setPassengerlist(List<PassengerDetail> passengerlist) {
		this.passengerlist = passengerlist;
	}
	
	
	public String getAirlinepnr() {
		return airlinepnr;
	}
	public void setAirlinepnr(String airlinepnr) {
		this.airlinepnr = airlinepnr;
	}
	public String getCrspnr() {
		return crspnr;
	}
	public void setCrspnr(String crspnr) {
		this.crspnr = crspnr;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}
	public String getPNR() {
		return PNR;
	}
	public void setPNR(String pNR) {
		PNR = pNR;
	}
	public String getTraceid() {
		return traceid;
	}
	public void setTraceid(String traceid) {
		this.traceid = traceid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTravel_date() {
		return travel_date;
	}
	public void setTravel_date(String travel_date) {
		this.travel_date = travel_date;
	}
	public String getBooking_status() {
		return booking_status;
	}
	public void setBooking_status(String booking_status) {
		this.booking_status = booking_status;
	}
	public boolean isRefundable() {
		return isRefundable;
	}
	public void setRefundable(boolean isRefundable) {
		this.isRefundable = isRefundable;
	}

	public String getFlighttype() {
		return flighttype;
	}

	public void setFlighttype(String flighttype) {
		this.flighttype = flighttype;
	}

	public String getAirlinecd() {
		return airlinecd;
	}

	public void setAirlinecd(String airlinecd) {
		this.airlinecd = airlinecd;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUsermobile() {
		return usermobile;
	}

	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}

	public double getOpenbl() {
		return openbl;
	}

	public void setOpenbl(double openbl) {
		this.openbl = openbl;
	}

	public double getClosebl() {
		return closebl;
	}

	public void setClosebl(double closebl) {
		this.closebl = closebl;
	}

	public double getNetamount() {
		return netamount;
	}

	public void setNetamount(double netamount) {
		this.netamount = netamount;
	}

	public String getAirlinename() {
		return airlinename;
	}

	public void setAirlinename(String airlinename) {
		this.airlinename = airlinename;
	}
	
	
	

}
