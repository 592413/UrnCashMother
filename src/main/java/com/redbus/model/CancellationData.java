package com.redbus.model;

import java.io.Serializable;
import java.util.Map;

public class CancellationData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cancellable;
    private String partiallyCancellable;
    private CommonData fares;
    private CommonData cancellationCharges;
    private String freeCancellationTime;
    private String tatkalTime;
    private double serviceCharge;
	public String getCancellable() {
		return cancellable;
	}
	public void setCancellable(String cancellable) {
		this.cancellable = cancellable;
	}
	public String getPartiallyCancellable() {
		return partiallyCancellable;
	}
	public void setPartiallyCancellable(String partiallyCancellable) {
		this.partiallyCancellable = partiallyCancellable;
	}
	public CommonData getFares() {
		return fares;
	}
	public void setFares(CommonData fares) {
		this.fares = fares;
	}
	public CommonData getCancellationCharges() {
		return cancellationCharges;
	}
	public void setCancellationCharges(CommonData cancellationCharges) {
		this.cancellationCharges = cancellationCharges;
	}
	public String getFreeCancellationTime() {
		return freeCancellationTime;
	}
	public void setFreeCancellationTime(String freeCancellationTime) {
		this.freeCancellationTime = freeCancellationTime;
	}
	public String getTatkalTime() {
		return tatkalTime;
	}
	public void setTatkalTime(String tatkalTime) {
		this.tatkalTime = tatkalTime;
	}
	public double getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	@Override
	public String toString() {
		return "CancellationData [cancellable=" + cancellable + ", partiallyCancellable=" + partiallyCancellable
				+ ", fares=" + fares + ", cancellationCharges=" + cancellationCharges + ", freeCancellationTime="
				+ freeCancellationTime + ", tatkalTime=" + tatkalTime + ", serviceCharge=" + serviceCharge + "]";
	}
    
    
}
