package com.recharge.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="assigned_package")
@NamedQueries({
	
	@NamedQuery(name ="getAssignedPackageByUserAndservice", query="From AssignedPackage where service_type=:service_type and user_id=:user_id"),
	@NamedQuery(name ="getAssignedPackageByUser", query="From AssignedPackage where  user_id=:user_id")
})
public class AssignedPackage implements Serializable {
    @Id
	private int id;
	private String package_id;
	private String service_type;
	private String user_id;
	
	
	
	public AssignedPackage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AssignedPackage(String package_id, String service_type, String user_id) {
		super();
		this.package_id = package_id;
		this.service_type = service_type;
		this.user_id = user_id;
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
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
