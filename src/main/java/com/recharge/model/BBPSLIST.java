package com.recharge.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "bbpslist")
public class BBPSLIST implements Serializable{
	private int id;
	private String name;
	private String partialpay;
	private String onlinepay;
	private String validator1;
	private String validator2;
	private String validator3;
	public BBPSLIST() {
		super();
	}
	public BBPSLIST(int id, String name, String partialpay, String onlinepay, String validator1, String validator2,
			String validator3) {
		super();
		this.id = id;
		this.name = name;
		this.partialpay = partialpay;
		this.onlinepay = onlinepay;
		this.validator1 = validator1;
		this.validator2 = validator2;
		this.validator3 = validator3;
	}
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPartialpay() {
		return partialpay;
	}
	public void setPartialpay(String partialpay) {
		this.partialpay = partialpay;
	}
	public String getOnlinepay() {
		return onlinepay;
	}
	public void setOnlinepay(String onlinepay) {
		this.onlinepay = onlinepay;
	}
	public String getValidator1() {
		return validator1;
	}
	public void setValidator1(String validator1) {
		this.validator1 = validator1;
	}
	public String getValidator2() {
		return validator2;
	}
	public void setValidator2(String validator2) {
		this.validator2 = validator2;
	}
	public String getValidator3() {
		return validator3;
	}
	public void setValidator3(String validator3) {
		this.validator3 = validator3;
	}
	
	

}
