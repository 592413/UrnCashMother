/**
 * 
 */
package com.recharge.customModel;

/**
 * @author Md Rahim
 *
 */
public class DefaultCommission implements java.io.Serializable {
	private Integer commissionId;
	private String userId;
	private Integer operatorId;
	private double superDistributor;
	private double distributor;
	private double retailer;
	private String serviceProvider;

	/**
	 * @param commissionId
	 * @param userId
	 * @param operatorId
	 * @param superDistributor
	 * @param distributor
	 * @param retailer
	 * @param serviceProvider
	 */
	public DefaultCommission(Integer commissionId, String userId, Integer operatorId, double superDistributor,
			double distributor, double retailer, String serviceProvider) {
		this.commissionId = commissionId;
		this.userId = userId;
		this.operatorId = operatorId;
		this.superDistributor = superDistributor;
		this.distributor = distributor;
		this.retailer = retailer;
		this.serviceProvider = serviceProvider;
	}

	public DefaultCommission() {

	}

	public Integer getCommissionId() {
		return commissionId;
	}

	public void setCommissionId(Integer commissionId) {
		this.commissionId = commissionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public double getSuperDistributor() {
		return superDistributor;
	}

	public void setSuperDistributor(double superDistributor) {
		this.superDistributor = superDistributor;
	}

	public double getDistributor() {
		return distributor;
	}

	public void setDistributor(double distributor) {
		this.distributor = distributor;
	}

	public double getRetailer() {
		return retailer;
	}

	public void setRetailer(double retailer) {
		this.retailer = retailer;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

}
