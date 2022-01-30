package com.recharge.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name="package")
@NamedQueries({
	@NamedQuery(name="getPackageBynameAnduserid",query="from PackageDetail where package_name=:name and owner=:user_id"),
	@NamedQuery(name="getPackageuserid",query="from PackageDetail where  owner=:user_id"),
	@NamedQuery(name="getPackageByuseridAndservice",query="from PackageDetail where service_type=:service_type and owner=:user_id"),
	@NamedQuery(name="getPackageBydservice",query="from PackageDetail where service_type=:service_type")
})
public class PackageDetail implements Serializable {
	@Id
	private int id;
	private String package_id;
	private String package_name;
	private String service_type;
	private String owner;
	
	
	
	public PackageDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PackageDetail(String package_id, String package_name, String service_type, String owner) {
		super();
		this.package_id = package_id;
		this.package_name = package_name;
		this.service_type = service_type;
		this.owner = owner;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
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
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	

}
