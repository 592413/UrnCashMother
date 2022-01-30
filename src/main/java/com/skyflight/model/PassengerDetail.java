package com.skyflight.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="passenger")
@NamedQueries({
	@NamedQuery(name="getPassengerDetailByPNR",query="From PassengerDetail where PNR=:PNR"),
	@NamedQuery(name="getPassengerDetailByPNRAndStatus",query="From PassengerDetail where PNR=:PNR and cancellation_status!=:cancellation_status")
})
public class PassengerDetail implements Serializable {
	
	@Id
	private int id;
	private String title;
	private String firstname;
	private String lastname;
	private String ticketnumber;
	private boolean isleadpassenger;
	private String contact;
	private String email;
	private String birth_date;
	private String cancellation_status;
	private String PNR;
	private String category;
	private String ticketid;
	
	public PassengerDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public PassengerDetail(String title, String firstname, String lastname, String ticketnumber,
			boolean isleadpassenger, String contact, String email, String birth_date, String cancellation_status,
			String pNR,String category,String ticketid) {
		super();
		this.title = title;
		this.firstname = firstname;
		this.lastname = lastname;
		this.ticketnumber = ticketnumber;
		this.isleadpassenger = isleadpassenger;
		this.contact = contact;
		this.email = email;
		this.birth_date = birth_date;
		this.cancellation_status = cancellation_status;
		PNR = pNR;
		this.category = category;
		this.ticketid = ticketid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getTicketnumber() {
		return ticketnumber;
	}
	public void setTicketnumber(String ticketnumber) {
		this.ticketnumber = ticketnumber;
	}
	public boolean isIsleadpassenger() {
		return isleadpassenger;
	}
	public void setIsleadpassenger(boolean isleadpassenger) {
		this.isleadpassenger = isleadpassenger;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public String getPNR() {
		return PNR;
	}
	public void setPNR(String pNR) {
		PNR = pNR;
	}
	public String getCancellation_status() {
		return cancellation_status;
	}
	public void setCancellation_status(String cancellation_status) {
		this.cancellation_status = cancellation_status;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getTicketid() {
		return ticketid;
	}


	public void setTicketid(String ticketid) {
		this.ticketid = ticketid;
	}
	
	
	
	

}
