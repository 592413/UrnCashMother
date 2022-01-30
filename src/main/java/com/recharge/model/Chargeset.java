package com.recharge.model;
// Generated Aug 31, 2017 9:56:23 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Chargeset generated by hbm2java
 */

@Entity
@Table(name = "chargeset")
@NamedQueries({ 
	@NamedQuery(name = "GetChargesByWlIdAndOperatorId", query = "from Chargeset As c where c.wlId = :wlId and c.operatorId =:operatorId"),
})
public class Chargeset implements java.io.Serializable {

	private Integer chargeId;
	private String wlId;
	private String resId;
	private Integer operatorId;
	private Double sdist;
	private Double dist;
	private Double ret;
	private String flag;

	public Chargeset() {
	}

	public Chargeset(String wlId, String resId, Integer operatorId, Double sdist, Double dist, Double ret, String flag) {
		this.wlId = wlId;
		this.resId = resId;
		this.operatorId = operatorId;
		this.sdist = sdist;
		this.dist = dist;
		this.ret = ret;
		this.flag = flag;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getChargeId() {
		return this.chargeId;
	}

	public void setChargeId(Integer chargeId) {
		this.chargeId = chargeId;
	}
	
	@Column(name = "wl_id")
	public String getWlId() {
		return this.wlId;
	}

	public void setWlId(String wlId) {
		this.wlId = wlId;
	}

	public String getResId() {
		return this.resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public Integer getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Double getSdist() {
		return this.sdist;
	}

	public void setSdist(Double sdist) {
		this.sdist = sdist;
	}

	public Double getDist() {
		return this.dist;
	}

	public void setDist(Double dist) {
		this.dist = dist;
	}

	public Double getRet() {
		return this.ret;
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

}