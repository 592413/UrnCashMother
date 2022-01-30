package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="settlementwallet")
@NamedQueries({
	@NamedQuery(name="getsettlementbyUsername",query="From SettlementBalance where username=:username")
})
public class SettlementBalance {
	@Id
	private int id;
	private String username;
	private double settlementbalance;
	
	public SettlementBalance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SettlementBalance(String username, double settlementbalance) {
		super();
		this.username = username;
		this.settlementbalance = settlementbalance;
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
	public double getSettlementbalance() {
		return settlementbalance;
	}
	public void setSettlementbalance(double settlementbalance) {
		this.settlementbalance = settlementbalance;
	}
	
	
	

}
