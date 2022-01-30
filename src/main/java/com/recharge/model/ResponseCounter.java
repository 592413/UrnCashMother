package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "response_counter")

@NamedQueries({
	@NamedQuery(name= "getResponseCounter",query="from ResponseCounter as r where r.transid=:tid")
})
public class ResponseCounter implements java.io.Serializable {
	private Integer id;
	private String transid;
	private Integer counter;
	public ResponseCounter() {
		super();
	}
	public ResponseCounter(String transid, Integer counter) {
		
		this.transid = transid;
		this.counter = counter;
	}
	public ResponseCounter(Integer id, String transid, Integer counter) {
		
		this.id = id;
		this.transid = transid;
		this.counter = counter;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTransid() {
		return transid;
	}
	public void setTransid(String transid) {
		this.transid = transid;
	}
	public Integer getCounter() {
		return counter;
	}
	public void setCounter(Integer counter) {
		this.counter = counter;
	}
	
	

}
