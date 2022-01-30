package com.recharge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "indexx")

@NamedQueries({
	
	@NamedQuery(name ="getIndex", query="From Indexx where domain=:url")
	
})
public class Indexx implements java.io.Serializable {
	
	private String domain;
	private String about_header;
	private String about_description;
	private byte[] about_image;
	private String about_blogtitle;
	private String about_blogdescription;	
	private String contactus_address;
	private String contactus_ph;
	private String contactus_email;
	private String contactus_map;
	public Indexx() {
		super();
	}
	public Indexx(String domain, String about_header, String about_description, byte[] about_image,
			String about_blogtitle, String about_blogdescription, String contactus_address, String contactus_ph,
			String contactus_email, String contactus_map) {
		super();
		this.domain = domain;
		this.about_header = about_header;
		this.about_description = about_description;
		this.about_image = about_image;
		this.about_blogtitle = about_blogtitle;
		this.about_blogdescription = about_blogdescription;
		this.contactus_address = contactus_address;
		this.contactus_ph = contactus_ph;
		this.contactus_email = contactus_email;
		this.contactus_map = contactus_map;
	}
	
	@Id
	@Column(name = "domain", unique = true, nullable = false)
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getAbout_header() {
		return about_header;
	}
	public void setAbout_header(String about_header) {
		this.about_header = about_header;
	}
	public String getAbout_description() {
		return about_description;
	}
	public void setAbout_description(String about_description) {
		this.about_description = about_description;
	}
	public byte[] getAbout_image() {
		return about_image;
	}
	public void setAbout_image(byte[] about_image) {
		this.about_image = about_image;
	}
	public String getAbout_blogtitle() {
		return about_blogtitle;
	}
	public void setAbout_blogtitle(String about_blogtitle) {
		this.about_blogtitle = about_blogtitle;
	}
	public String getAbout_blogdescription() {
		return about_blogdescription;
	}
	public void setAbout_blogdescription(String about_blogdescription) {
		this.about_blogdescription = about_blogdescription;
	}
	public String getContactus_address() {
		return contactus_address;
	}
	public void setContactus_address(String contactus_address) {
		this.contactus_address = contactus_address;
	}
	public String getContactus_ph() {
		return contactus_ph;
	}
	public void setContactus_ph(String contactus_ph) {
		this.contactus_ph = contactus_ph;
	}
	public String getContactus_email() {
		return contactus_email;
	}
	public void setContactus_email(String contactus_email) {
		this.contactus_email = contactus_email;
	}
	public String getContactus_map() {
		return contactus_map;
	}
	public void setContactus_map(String contactus_map) {
		this.contactus_map = contactus_map;
	}
	
	
}
