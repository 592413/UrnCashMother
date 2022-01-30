package com.redbus.model;

import java.io.Serializable;

public class CancellationResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tin;
	private double refundAmount;
    private double cancellationCharge;
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	public double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}
	public double getCancellationCharge() {
		return cancellationCharge;
	}
	public void setCancellationCharge(double cancellationCharge) {
		this.cancellationCharge = cancellationCharge;
	}
	@Override
	public String toString() {
		return "CancellationResponse [tin=" + tin + ", refundAmount=" + refundAmount + ", cancellationCharge="
				+ cancellationCharge + "]";
	}
    
    
    
}
