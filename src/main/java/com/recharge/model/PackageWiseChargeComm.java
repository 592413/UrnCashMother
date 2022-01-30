package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="pck_charge_com")
@NamedQueries({
	@NamedQuery(name="getPackageWiseChargeCommbypackage",query="From PackageWiseChargeComm where package_id=:package_id")
})
public class PackageWiseChargeComm {
	
	@Id
	private int id;
	private String  package_id;
	private int operator_id;
	private double charge;
	private String charge_type;
	private double  comm;
	private String comm_type;
	@Transient
	private String serviceProvider;
	
	@Transient
	private double slab1;
	@Transient
	private double slab2;
	
	
	public double getSlab1() {
		return slab1;
	}


	public void setSlab1(double slab1) {
		this.slab1 = slab1;
	}


	public double getSlab2() {
		return slab2;
	}


	public void setSlab2(double slab2) {
		this.slab2 = slab2;
	}


	public PackageWiseChargeComm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public PackageWiseChargeComm(String package_id, int operator_id, double charge, String charge_type, double comm,
			String comm_type, String serviceProvider) {
		super();
		this.package_id = package_id;
		this.operator_id = operator_id;
		this.charge = charge;
		this.charge_type = charge_type;
		this.comm = comm;
		this.comm_type = comm_type;
		this.serviceProvider = serviceProvider;
	}
	public String getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPackage_id() {
		return package_id;
	}
	public void setPackage_id(String package_id) {
		this.package_id = package_id;
	}
	public int getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(int operator_id) {
		this.operator_id = operator_id;
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	public String getCharge_type() {
		return charge_type;
	}
	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}
	public double getComm() {
		return comm;
	}
	public void setComm(double comm) {
		this.comm = comm;
	}
	public String getComm_type() {
		return comm_type;
	}
	public void setComm_type(String comm_type) {
		this.comm_type = comm_type;
	}
	
	

}
