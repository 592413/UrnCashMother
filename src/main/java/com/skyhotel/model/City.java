package com.skyhotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="city")
@Entity
public class City {
	
	
	@Id
	private int id;
	private String CityName;
	private String CityId;
	private String country;
	private String countryId;
	
	public City() {
		super();
	}
	
	
	
	public City(String cityName, String cityId, String country, String countryId) {
		super();
		CityName = cityName;
		CityId = cityId;
		this.country = country;
		this.countryId = countryId;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}


	public String getCityId() {
		return CityId;
	}



	public void setCityId(String cityId) {
		CityId = cityId;
	}



	public String getCountryId() {
		return countryId;
	}



	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}



	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	
	

}
