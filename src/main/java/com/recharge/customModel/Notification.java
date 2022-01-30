package com.recharge.customModel;

public class Notification implements java.io.Serializable {
	private Integer complain;
	private Integer balRequest;
	private Integer utility;
	private Integer insurance;

	public Notification() {
	}

	public Notification(Integer complain, Integer balRequest, Integer utility,Integer insurance) {
		this.complain = complain;
		this.balRequest = balRequest;
		this.utility = utility;
		this.setInsurance(insurance);
	}

	public Integer getComplain() {
		return complain;
	}

	public void setComplain(Integer complain) {
		this.complain = complain;
	}

	public Integer getBalRequest() {
		return balRequest;
	}

	public void setBalRequest(Integer balRequest) {
		this.balRequest = balRequest;
	}

	public Integer getUtility() {
		return utility;
	}

	public void setUtility(Integer utility) {
		this.utility = utility;
	}

	public Integer getInsurance() {
		return insurance;
	}

	public void setInsurance(Integer insurance) {
		this.insurance = insurance;
	}

}
