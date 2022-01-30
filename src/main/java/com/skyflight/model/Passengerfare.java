package com.skyflight.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="passengerfare")
@NamedQueries({
	@NamedQuery(name="getPassengerfareByticket",query="From Passengerfare where ticketnumber=:ticketnumber"),
	@NamedQuery(name="getPassengerfareByPNR",query="From Passengerfare where PNR=:PNR")
})
public class Passengerfare implements Serializable {
	@Id
	private int id;
	private String ticketnumber;
	private double basicamount;
	private double totaltax;
	private double servicecharge;
	private double transactionfee;
	private double grossamount;
	private String PNR;
	
	
	
	public Passengerfare() {
		super();
	}
	public Passengerfare(String ticketnumber, double basicamount, double totaltax, double servicecharge,
			double transactionfee, double grossamount, String pNR) {
		super();
		this.ticketnumber = ticketnumber;
		this.basicamount = basicamount;
		this.totaltax = totaltax;
		this.servicecharge = servicecharge;
		this.transactionfee = transactionfee;
		this.grossamount = grossamount;
		PNR = pNR;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTicketnumber() {
		return ticketnumber;
	}
	public void setTicketnumber(String ticketnumber) {
		this.ticketnumber = ticketnumber;
	}
	public double getBasicamount() {
		return basicamount;
	}
	public void setBasicamount(double basicamount) {
		this.basicamount = basicamount;
	}
	public double getTotaltax() {
		return totaltax;
	}
	public void setTotaltax(double totaltax) {
		this.totaltax = totaltax;
	}
	public double getServicecharge() {
		return servicecharge;
	}
	public void setServicecharge(double servicecharge) {
		this.servicecharge = servicecharge;
	}
	public double getTransactionfee() {
		return transactionfee;
	}
	public void setTransactionfee(double transactionfee) {
		this.transactionfee = transactionfee;
	}
	public double getGrossamount() {
		return grossamount;
	}
	public void setGrossamount(double grossamount) {
		this.grossamount = grossamount;
	}
	public String getPNR() {
		return PNR;
	}
	public void setPNR(String pNR) {
		PNR = pNR;
	}
	

}
