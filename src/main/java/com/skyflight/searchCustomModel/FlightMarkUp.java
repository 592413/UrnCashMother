package com.skyflight.searchCustomModel;

public class FlightMarkUp {
	private int id;
	private String username;
	private String airline_code;
	private double markup_value;
	private String markup_type;
	private String airline_name;
	
	
	public FlightMarkUp(int id, String username, String airline_code, double markup_value, String markup_type,
			String airline_name) {
		super();
		this.id = id;
		this.username = username;
		this.airline_code = airline_code;
		this.markup_value = markup_value;
		this.markup_type = markup_type;
		this.airline_name = airline_name;
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
	public String getAirline_name() {
		return airline_name;
	}
	public void setAirline_name(String airline_name) {
		this.airline_name = airline_name;
	}
	

}
