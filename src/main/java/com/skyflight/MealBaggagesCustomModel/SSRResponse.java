package com.skyflight.MealBaggagesCustomModel;

import java.util.List;

public class SSRResponse {
	private int ResponseStatus;
	private String TrackId;
	private List<SSRSegments> SSRSegments;
	private String Errormessage;
	private String Errorcode;
	
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
	public List<SSRSegments> getSSRSegments() {
		return SSRSegments;
	}
	public void setSSRSegments(List<SSRSegments> sSRSegments) {
		SSRSegments = sSRSegments;
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
	
	

	
}
