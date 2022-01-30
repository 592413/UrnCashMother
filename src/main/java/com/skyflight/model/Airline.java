package com.skyflight.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="airline")
@NamedQueries({
	@NamedQuery(name="getAirlinebyservice",query="From Airline where service_type=:service_type"),
	@NamedQuery(name="getAirlinebyairline_code",query="From Airline where airline_code=:airline_code")
})
public class Airline implements Serializable {
	
	@Id
	private int id;
	private String service_type;
	private String airline_name;
	private String airline_code;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	public String getAirline_name() {
		return airline_name;
	}
	public void setAirline_name(String airline_name) {
		this.airline_name = airline_name;
	}
	public String getAirline_code() {
		return airline_code;
	}
	public void setAirline_code(String airline_code) {
		this.airline_code = airline_code;
	}
	
	

}
