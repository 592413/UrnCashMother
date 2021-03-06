package com.recharge.model;
// Generated Nov 21, 2017 11:24:00 AM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Userattachment generated by hbm2java
 */

@Entity
@Table(name = "userattachment")
public class Userattachment implements java.io.Serializable {

	private Integer id;
	private String userId;
	private String wlId;
	private byte[] pancard;
	private byte[] adhar;
	private byte[] photo;

	public Userattachment() {
	}

	public Userattachment(String userId, String wlId, byte[] pancard, byte[] adhar, byte[] photo) {
		this.userId = userId;
		this.wlId = wlId;
		this.pancard = pancard;
		this.adhar = adhar;
		this.photo = photo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "wl_id")
	public String getWlId() {
		return this.wlId;
	}

	public void setWlId(String wlId) {
		this.wlId = wlId;
	}

	public byte[] getPancard() {
		return this.pancard;
	}

	public void setPancard(byte[] pancard) {
		this.pancard = pancard;
	}

	public byte[] getAdhar() {
		return this.adhar;
	}

	public void setAdhar(byte[] adhar) {
		this.adhar = adhar;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

}
