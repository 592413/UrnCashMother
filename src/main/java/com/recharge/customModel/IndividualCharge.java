/**
 * 
 */
package com.recharge.customModel;

import java.io.Serializable;

/**
 * @author Md Rahim
 *
 */
public class IndividualCharge implements Serializable{
	private Integer id;
	private String userId;
	private Double rupees;
	private Double percentage;
	private String flag;
	private String wlId;
	private String serviceProvider;
	private Integer operatorId;

	/**
	 * 
	 */
	public IndividualCharge() {

	}

	/**
	 * @param id
	 * @param userId
	 * @param rupees
	 * @param percentage
	 * @param flag
	 * @param wlId
	 * @param serviceProvider
	 */
	public IndividualCharge(Integer id, String userId, Double rupees, Double percentage, String flag, String wlId,
			String serviceProvider, Integer operatorId) {
		this.id = id;
		this.userId = userId;
		this.rupees = rupees;
		this.percentage = percentage;
		this.flag = flag;
		this.wlId = wlId;
		this.serviceProvider = serviceProvider;
		this.operatorId = operatorId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getRupees() {
		return rupees;
	}

	public void setRupees(Double rupees) {
		this.rupees = rupees;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getWlId() {
		return wlId;
	}

	public void setWlId(String wlId) {
		this.wlId = wlId;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

}
