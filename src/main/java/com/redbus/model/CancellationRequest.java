package com.redbus.model;

import java.io.Serializable;
import java.util.Arrays;

public class CancellationRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tin;
	private String seatsToCancel[];
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	public String[] getSeatsToCancel() {
		return seatsToCancel;
	}
	public void setSeatsToCancel(String[] seatsToCancel) {
		this.seatsToCancel = seatsToCancel;
	}
	@Override
	public String toString() {
		return "CancellationRequest [tin=" + tin + ", seatsToCancel=" + Arrays.toString(seatsToCancel) + "]";
	}
	
	
  
}
