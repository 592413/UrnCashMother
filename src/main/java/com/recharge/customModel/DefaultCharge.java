/**
 * 
 */
package com.recharge.customModel;

/**
 * @author Md Rahim
 *
 */
public class DefaultCharge implements java.io.Serializable {

	private Integer chargeId;
	private String wlId;
	private String resId;
	private Integer operatorId;
	private Double sdist;
	private Double dist;
	private Double ret;
	private String flag;
	private String serviceProvider;

	public DefaultCharge() {
	}

	public DefaultCharge(Integer chargeId, String wlId, String resId, Integer operatorId, Double sdist, Double dist, Double ret,
			String flag, String serviceProvider) {
		this.chargeId = chargeId;
		this.wlId = wlId;
		this.resId = resId;
		this.operatorId = operatorId;
		this.sdist = sdist;
		this.dist = dist;
		this.ret = ret;
		this.flag = flag;
		this.serviceProvider = serviceProvider;
	}

	public Integer getChargeId() {
		return chargeId;
	}

	public void setChargeId(Integer chargeId) {
		this.chargeId = chargeId;
	}

	public String getWlId() {
		return wlId;
	}

	public void setWlId(String wlId) {
		this.wlId = wlId;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Double getSdist() {
		return sdist;
	}

	public void setSdist(Double sdist) {
		this.sdist = sdist;
	}

	public Double getDist() {
		return dist;
	}

	public void setDist(Double dist) {
		this.dist = dist;
	}

	public Double getRet() {
		return ret;
	}

	public void setRet(Double ret) {
		this.ret = ret;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

}
