package com.recharge.model;
// Generated Nov 15, 2017 1:45:55 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Reseller generated by hbm2java
 */

@Entity
@Table(name = "reseller")
@NamedQueries({
	
	@NamedQuery(name ="getReseller", query="From Reseller where domain=:url")
	
})
public class Reseller implements java.io.Serializable {
	@Id
	@Column(name = "wl_id", unique = true, nullable = false)
	private String wlId;
	private String theme;
	private String logo;
	private String supportNumber;
	private String supportEmail;	
	private String address;
	private String poweredBy;
	private String poweredByLink;
	private String pageTitle;
	private byte[] image;
	private String domain;

	public Reseller() {
	}


	public Reseller(String wlId, String theme, String logo, String supportNumber, String supportEmail,
			String address, String poweredBy, String poweredByLink, String pageTitle, byte[] image,String domain) {
		this.wlId = wlId;
		this.theme = theme;
		this.logo = logo;
		this.supportNumber = supportNumber;
		this.supportEmail = supportEmail;		
		this.address = address;
		this.poweredBy = poweredBy;
		this.poweredByLink = poweredByLink;
		this.pageTitle = pageTitle;
		this.image = image;
		this.domain = domain;
	}

	public String getWlId() {
		return this.wlId;
	}

	public void setWlId(String wlId) {
		this.wlId = wlId;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getSupportNumber() {
		return this.supportNumber;
	}

	public void setSupportNumber(String supportNumber) {
		this.supportNumber = supportNumber;
	}

	public String getSupportEmail() {
		return this.supportEmail;
	}

	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}	

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPoweredBy() {
		return this.poweredBy;
	}

	public void setPoweredBy(String poweredBy) {
		this.poweredBy = poweredBy;
	}

	public String getPoweredByLink() {
		return this.poweredByLink;
	}

	public void setPoweredByLink(String poweredByLink) {
		this.poweredByLink = poweredByLink;
	}

	public String getPageTitle() {
		return this.pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}
	

}
