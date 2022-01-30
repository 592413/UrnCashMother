package com.redbus.model;

import java.io.Serializable;

public class CustomerPriceBreakUp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String componentName;
	private double value;
    private String cancellationHandling;
    private String type;
    private double refundableValue;
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getCancellationHandling() {
		return cancellationHandling;
	}
	public void setCancellationHandling(String cancellationHandling) {
		this.cancellationHandling = cancellationHandling;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getRefundableValue() {
		return refundableValue;
	}
	public void setRefundableValue(double refundableValue) {
		this.refundableValue = refundableValue;
	}
	@Override
	public String toString() {
		return "CustomerPriceBreakUp [componentName=" + componentName + ", value=" + value + ", cancellationHandling="
				+ cancellationHandling + ", type=" + type + ", refundableValue=" + refundableValue + "]";
	}
    
    
    
}
