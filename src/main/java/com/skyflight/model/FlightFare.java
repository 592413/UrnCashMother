package com.skyflight.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name="flight_fare")
@Entity
@NamedQueries({

	@NamedQuery(name="getFlightFarebytraceId",query="From FlightFare where traceId=:traceId")
	
	
})
public class FlightFare implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String traceId;
	private double totalamount;
	private double usermarkup;
	private double adminmarkup;
	private double commission;
	private double fees;
	private double discount;
	
	public FlightFare() {
		super();
	}
	
	
	
	public FlightFare(String traceId, double totalamount, double usermarkup, double adminmarkup, double commission,
			double fees, double discount) {
		super();
		this.traceId = traceId;
		this.totalamount = totalamount;
		this.usermarkup = usermarkup;
		this.adminmarkup = adminmarkup;
		this.commission = commission;
		this.fees = fees;
		this.discount = discount;
	}

	


	public FlightFare(int id, String traceId, double totalamount, double usermarkup, double adminmarkup,
			double commission, double fees, double discount) {
		super();
		this.id = id;
		this.traceId = traceId;
		this.totalamount = totalamount;
		this.usermarkup = usermarkup;
		this.adminmarkup = adminmarkup;
		this.commission = commission;
		this.fees = fees;
		this.discount = discount;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTraceId() {
		return traceId;
	}
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	public double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}
	public double getUsermarkup() {
		return usermarkup;
	}
	public void setUsermarkup(double usermarkup) {
		this.usermarkup = usermarkup;
	}
	public double getAdminmarkup() {
		return adminmarkup;
	}
	public void setAdminmarkup(double adminmarkup) {
		this.adminmarkup = adminmarkup;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}



	public double getFees() {
		return fees;
	}



	public void setFees(double fees) {
		this.fees = fees;
	}



	public double getDiscount() {
		return discount;
	}



	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	
	
	

}
