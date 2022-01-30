package com.redbus.model;

import java.io.Serializable;
import java.util.List;

public class FareBreakup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String seatName;
	private List<CustomerPriceBreakUp> customerPriceBreakUp;
	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	public List<CustomerPriceBreakUp> getCustomerPriceBreakUp() {
		return customerPriceBreakUp;
	}
	public void setCustomerPriceBreakUp(List<CustomerPriceBreakUp> customerPriceBreakUp) {
		this.customerPriceBreakUp = customerPriceBreakUp;
	}
	@Override
	public String toString() {
		return "FareBreakup [seatName=" + seatName + ", customerPriceBreakUp=" + customerPriceBreakUp + "]";
	}
	
	
	
}
