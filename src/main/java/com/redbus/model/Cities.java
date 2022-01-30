package com.redbus.model;

import java.io.Serializable;
import java.util.Arrays;

public class Cities implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -805239292313723362L;
	private String id;
	private String cityId;
	private String stateId;
    private String cityName;
    private String latitude;
    private String longitude;
    private String locationType;
    private String name;
    private String state;
    private String aliasNames [];
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String[] getAliasNames() {
		return aliasNames;
	}
	public void setAliasNames(String aliasNames[]) {
		this.aliasNames = aliasNames;
	}
	@Override
	public String toString() {
		return "Cities [id=" + id + ", cityId=" + cityId + ", stateId=" + stateId + ", cityName=" + cityName
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", locationType=" + locationType + ", name="
				+ name + ", state=" + state + ", aliasNames=" + Arrays.toString(aliasNames) + "]";
	}
   
   
	
    
    
}

