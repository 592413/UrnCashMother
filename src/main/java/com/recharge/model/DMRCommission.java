package com.recharge.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Table(name="dmrcommission")
@Entity
@NamedQueries({

	@NamedQuery(name="getDMRCommissionByApi",query="From DMRCommission where api=:api")
})
public class DMRCommission implements Serializable{
	
	 @Id
		private int id;
		private String api;
		private double slab1;
		private double slab2;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getApi() {
			return api;
		}
		public void setApi(String api) {
			this.api = api;
		}
		
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
		

}
