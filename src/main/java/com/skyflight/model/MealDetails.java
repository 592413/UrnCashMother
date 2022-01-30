package com.skyflight.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table
@Entity(name="mealdetail")
public class MealDetails implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String PNR;
	private String ticketnumber;
	private String description;
	private String origin;
	private String destination;
	private double amount;
	public MealDetails() {
		super();
	}
	public MealDetails(String pNR, String ticketnumber, String description, String origin, String destination,
			double amount) {
		super();
		PNR = pNR;
		this.ticketnumber = ticketnumber;
		this.description = description;
		this.origin = origin;
		this.destination = destination;
		this.amount = amount;
	}
	public MealDetails(int id, String pNR, String ticketnumber, String description, String origin, String destination,
			double amount) {
		super();
		this.id = id;
		PNR = pNR;
		this.ticketnumber = ticketnumber;
		this.description = description;
		this.origin = origin;
		this.destination = destination;
		this.amount = amount;
	}
	
	

}
