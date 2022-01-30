package com.recharge.icicidmtmodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="remitterlimit")
@NamedQueries({
@NamedQuery(name="getRemlimitbyMobile",query="From RemitterLimit where mobile=:mobile"),
@NamedQuery(name="getRemlimitbyMobilemonthyear",query="From RemitterLimit where mobile=:mobile and month=:month and year=:year"),
      })
public class RemitterLimit {
	@Id
	private int id;
	private String mobile;
	private double cashlimit;
    private int month;
    private int year;
    
    
	
	public RemitterLimit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RemitterLimit(String mobile, double cashlimit, int month, int year) {
		super();
		this.mobile = mobile;
		this.cashlimit = cashlimit;
		this.month = month;
		this.year = year;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getCashlimit() {
		return cashlimit;
	}
	public void setCashlimit(double cashlimit) {
		this.cashlimit = cashlimit;
	}
	
    
}
