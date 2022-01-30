package com.redbus.model;

import java.io.Serializable;

public class InventoryItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String seatName;
    private double fare;
    private String ladiesSeat;
    private Passenger passenger;
    private PassengerGSTDetails passengerGSTDetails;
	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public String getLadiesSeat() {
		return ladiesSeat;
	}
	public void setLadiesSeat(String ladiesSeat) {
		this.ladiesSeat = ladiesSeat;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public PassengerGSTDetails getPassengerGSTDetails() {
		return passengerGSTDetails;
	}
	public void setPassengerGSTDetails(PassengerGSTDetails passengerGSTDetails) {
		this.passengerGSTDetails = passengerGSTDetails;
	}
	@Override
	public String toString() {
		return "InventoryItem [seatName=" + seatName + ", fare=" + fare + ", ladiesSeat=" + ladiesSeat + ", passenger="
				+ passenger + ", passengerGSTDetails=" + passengerGSTDetails + "]";
	}
    
    
    
}
