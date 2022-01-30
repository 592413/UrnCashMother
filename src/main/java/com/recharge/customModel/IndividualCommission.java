/**
 * 
 */
package com.recharge.customModel;

import java.io.Serializable;

/**
 * @author Md Rahim
 *
 */
public class IndividualCommission implements Serializable {
	private Integer commissionId;
	private String userName;
	private Integer operatorId;
	private String serviceProvider;
	private double commission;

	
	
	public IndividualCommission() {
		
	}

	/**
	 * @param commissionId
	 * @param userName
	 * @param operatorId
	 * @param serviceProvider
	 * @param commission
	 */
	public IndividualCommission(Integer commissionId, String userName, Integer operatorId, String serviceProvider, double commission) {		
		this.commissionId = commissionId;
		this.userName = userName;
		this.operatorId = operatorId;
		this.serviceProvider = serviceProvider;
		this.commission = commission;
	}

	public Integer getCommissionId() {
		return commissionId;
	}

	public void setCommissionId(Integer commissionId) {
		this.commissionId = commissionId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

}
