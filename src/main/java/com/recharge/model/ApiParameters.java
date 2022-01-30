package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="apiparameters")
@NamedQueries({
	@NamedQuery(name="getApiParametersbyapiidtype",query="From ApiParameters where apiId=:apiId and service_type=:service_type")
})
public class ApiParameters {
	@Id
	private int id;
	private String apiparameternames;
	private String apiparametervalues;
	private int apiId;
	private String service_type;
	private String number;
	private String opcode;
	private String amount;
	private String request_id;
	
	
	public ApiParameters() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public ApiParameters(String apiparameternames, String apiparametervalues, int apiId, String service_type,
			String number, String opcode, String amount, String request_id) {
		super();
		this.apiparameternames = apiparameternames;
		this.apiparametervalues = apiparametervalues;
		this.apiId = apiId;
		this.service_type = service_type;
		this.number = number;
		this.opcode = opcode;
		this.amount = amount;
		this.request_id = request_id;
	}

	public String getApiparameternames() {
		return apiparameternames;
	}
	public void setApiparameternames(String apiparameternames) {
		this.apiparameternames = apiparameternames;
	}
	public String getApiparametervalues() {
		return apiparametervalues;
	}
	public void setApiparametervalues(String apiparametervalues) {
		this.apiparametervalues = apiparametervalues;
	}
	public int getApiId() {
		return apiId;
	}
	public void setApiId(int apiId) {
		this.apiId = apiId;
	}
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOpcode() {
		return opcode;
	}

	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	
   
}
