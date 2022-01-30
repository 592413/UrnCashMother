package com.redbus.model;

import java.io.Serializable;

public class ReschedulingPolicy implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long windowTime;
    private double reschedulingCharge;
    
    public long getWindowTime() {
        return this.windowTime;
    }
    
    public void setWindowTime(final long windowTime) {
        this.windowTime = windowTime;
    }
    
    public double getReschedulingCharge() {
        return this.reschedulingCharge;
    }
    
    public void setReschedulingCharge(final double reschedulingCharge) {
        this.reschedulingCharge = reschedulingCharge;
    }
    
    @Override
    public String toString() {
        return "ReschedulingPolicy{windowTime=" + this.windowTime + ", reschedulingCharge=" + this.reschedulingCharge + '}';
    }
}
