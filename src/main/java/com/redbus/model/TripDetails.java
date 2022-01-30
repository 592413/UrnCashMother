package com.redbus.model;

import java.io.Serializable;
import java.util.List;

public class TripDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String forcedSeats;
	private long maxSeatsPerTicket;
	private List<Seats> seats;
	private ReschedulingPolicy reschedulingPolicy;
	public String getForcedSeats() {
		return forcedSeats;
	}
	public void setForcedSeats(String forcedSeats) {
		this.forcedSeats = forcedSeats;
	}
	public long getMaxSeatsPerTicket() {
		return maxSeatsPerTicket;
	}
	public void setMaxSeatsPerTicket(long maxSeatsPerTicket) {
		this.maxSeatsPerTicket = maxSeatsPerTicket;
	}
	public List<Seats> getSeats() {
		return seats;
	}
	public void setSeats(List<Seats> seats) {
		this.seats = seats;
	}
	
	
	public ReschedulingPolicy getReschedulingPolicy() {
		return reschedulingPolicy;
	}
	public void setReschedulingPolicy(ReschedulingPolicy reschedulingPolicy) {
		this.reschedulingPolicy = reschedulingPolicy;
	}
	@Override
	public String toString() {
		return "TripDetails [forcedSeats=" + forcedSeats + ", maxSeatsPerTicket=" + maxSeatsPerTicket + ", seats="
				+ seats + ", reschedulingPolicy=" + reschedulingPolicy + "]";
	}
	
	
	                                                                  
	
}
