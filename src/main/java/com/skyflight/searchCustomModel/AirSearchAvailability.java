package com.skyflight.searchCustomModel;

import java.util.List;


public class AirSearchAvailability {
	private int ResponseStatus;
	private String TrackId;
	private String Origin;
	private String Destination;
	private boolean isDomestic;
	private List<SerachResults> SerachResults;
	private String Errormessage;
	private String Errorcode;
	private double minvalue;
	private double maxvalue;
	
	public int getResponseStatus() {
		return ResponseStatus;
	}
	public void setResponseStatus(int responseStatus) {
		ResponseStatus = responseStatus;
	}
	public String getTrackId() {
		return TrackId;
	}
	public void setTrackId(String trackId) {
		TrackId = trackId;
	}
	public String getOrigin() {
		return Origin;
	}
	public void setOrigin(String origin) {
		Origin = origin;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public List<SerachResults> getSerachResults() {
		return SerachResults;
	}
	public void setSerachResults(List<SerachResults> serachResults) {
		SerachResults = serachResults;
	}
	public String getErrormessage() {
		return Errormessage;
	}
	public void setErrormessage(String errormessage) {
		Errormessage = errormessage;
	}
	public String getErrorcode() {
		return Errorcode;
	}
	public void setErrorcode(String errorcode) {
		Errorcode = errorcode;
	}
	
	
	
	public boolean isDomestic() {
		return isDomestic;
	}
	public void setDomestic(boolean isDomestic) {
		this.isDomestic = isDomestic;
	}
	
	
	
	
	public double getMinvalue() {
		return minvalue;
	}
	public void setMinvalue(double minvalue) {
		this.minvalue = minvalue;
	}
	public double getMaxvalue() {
		return maxvalue;
	}
	public void setMaxvalue(double maxvalue) {
		this.maxvalue = maxvalue;
	}
	@Override
	public String toString() {
		return "AirSearchAvailability [ResponseStatus=" + ResponseStatus + ", TrackId=" + TrackId + ", Origin=" + Origin
				+ ", Destination=" + Destination + ", SerachResults=" + SerachResults + ", Errormessage=" + Errormessage
				+ ", Errorcode=" + Errorcode + "]";
	}
	
	

}
