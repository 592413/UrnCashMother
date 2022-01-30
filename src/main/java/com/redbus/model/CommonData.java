package com.redbus.model;

import java.util.List;

public class CommonData {
	private List<Cities> cities;
	private List<AvailableTrips> availableTrips;
	private List<Entry> entry;

	public List<Cities> getCities() {
		return cities;
	}

	public void setCities(List<Cities> cities) {
		this.cities = cities;
	}

	public List<AvailableTrips> getAvailableTrips() {
		return availableTrips;
	}

	public void setAvailableTrips(List<AvailableTrips> availableTrips) {
		this.availableTrips = availableTrips;
	}
	
	public List<Entry> getEntry() {
		return entry;
	}

	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}

	@Override
	public String toString() {
		return "CommonData [cities=" + cities + ", availableTrips=" + availableTrips + ", entry=" + entry + "]";
	}
	
	
}
