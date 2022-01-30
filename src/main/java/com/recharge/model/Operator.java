package com.recharge.model;
// Generated Aug 31, 2017 9:56:23 PM by Hibernate Tools 4.3.1

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Operator generated by hbm2java
 */

@Entity
@Table(name = "operator")

@NamedQueries({ @NamedQuery(name = "getOperatorByInCode", query = "from Operator As o where o.inCode= :inCode"),
	@NamedQuery(name = "getOperatorByName", query = "from Operator As o where o.serviceProvider= :serviceProvider"),
	@NamedQuery(name = "getOperatorByoutCode", query = "from Operator As o where o.outCode= :outCode"),
	@NamedQuery(name = "getOperatorByServiceType", query = "from Operator As o where o.serviceType= :serviceType")})
public class Operator implements java.io.Serializable {

	private Integer operatorId;
	private Integer apiId;
	private String serviceProvider;
	private String operatorCode;
	private String serviceType;
	private String inCode;
	private String outCode;
	private String status;

	public Operator() {}

	public Operator(String serviceProvider, Integer apiId, String operatorCode, String serviceType, String inCode,
			String outCode, String status) {
		this.serviceProvider = serviceProvider;
		this.apiId = apiId;
		this.operatorCode = operatorCode;
		this.serviceType = serviceType;
		this.inCode = inCode;
		this.outCode = outCode;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getApiId() {
		return this.apiId;
	}

	public void setApiId(Integer apiId) {
		this.apiId = apiId;
	}

	public String getServiceProvider() {
		return this.serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String getOperatorCode() {
		return this.operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getInCode() {
		return this.inCode;
	}

	public void setInCode(String inCode) {
		this.inCode = inCode;
	}

	public String getOutCode() {
		return this.outCode;
	}

	public void setOutCode(String outCode) {
		this.outCode = outCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
