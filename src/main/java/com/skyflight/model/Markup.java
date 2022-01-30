package com.skyflight.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="flight_markup")
@NamedQueries({
	@NamedQuery(name="getMarkupByAirlinecodeAndUsername",query="From Markup where airline_code=:airline_code and username=:username"),
	@NamedQuery(name="getMarkupByUsername",query="From Markup where  username=:username")
})
public class Markup {
	@Id
	private int id;
	private String username;
	private String airline_code;
	private double markup_value;
	private String markup_type;
	
	
	public Markup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Markup(String username, String airline_code, double markup_value, String markup_type) {
		super();
		this.username = username;
		this.airline_code = airline_code;
		this.markup_value = markup_value;
		this.markup_type = markup_type;
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
	public String getAirline_code() {
		return airline_code;
	}
	public void setAirline_code(String airline_code) {
		this.airline_code = airline_code;
	}
	public double getMarkup_value() {
		return markup_value;
	}
	public void setMarkup_value(double markup_value) {
		this.markup_value = markup_value;
	}
	public String getMarkup_type() {
		return markup_type;
	}
	public void setMarkup_type(String markup_type) {
		this.markup_type = markup_type;
	}
	

}
