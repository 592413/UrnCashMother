package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="settlementcharge")
@NamedQueries({
	
	@NamedQuery(name="getSettlementChargebyapi",query="From SettlementCharge where api=:api")
	
})
public class SettlementCharge {
	@Id
	private int id;
	private double slab1;
	private double slab2;
	private double charge;
	private String api;
	
	
	public SettlementCharge() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SettlementCharge(double slab1, double slab2, double charge, String api) {
		super();
		this.slab1 = slab1;
		this.slab2 = slab2;
		this.charge = charge;
		this.api = api;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getSlab1() {
		return slab1;
	}
	public void setSlab1(double slab1) {
		this.slab1 = slab1;
	}
	public double getSlab2() {
		return slab2;
	}
	public void setSlab2(double slab2) {
		this.slab2 = slab2;
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	
	
	

}
