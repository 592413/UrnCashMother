package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "ezpayapplicationform")
@NamedQueries({
	@NamedQuery(name = "getappformbyadmin", query = "from Ezpayapplicationform  where date between :start_date and :end_date"),
	@NamedQuery(name = "getappformbyusername", query = "from Ezpayapplicationform  where userId=:username and date between :start_date and :end_date"),
	@NamedQuery(name = "getappformstatusupdatebyapplicationid", query = "from Ezpayapplicationform  where applicationid=:applicationid"),
			@NamedQuery(name = "getcardholdernamebystatus",query = "from Ezpayapplicationform  where userId=:username and status=:status")
})
public class Ezpayapplicationform {
	
	@Id
	private int id;
	private String firstname;
	private String middlename;
	private String lastname;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String pin;
	private String mothersname;
	private String mothersdateofbirth;
	private String yearofmarriage;
	private String yearofpassingssc;
	private String applicationid;
	
	private String applicantmobileno;
	private String applicantemail;
	private String userId;
	private String referencenumber;
	private String cardreferencenumber;
	private String cardnumber;
	private String cardvalidtill;
	private String officialname;
	private String status;
	private String remarks;
	private String date;
	private String time;
	public Ezpayapplicationform() {
		super();
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getMothersname() {
		return mothersname;
	}
	public void setMothersname(String mothersname) {
		this.mothersname = mothersname;
	}
	public String getMothersdateofbirth() {
		return mothersdateofbirth;
	}
	public void setMothersdateofbirth(String mothersdateofbirth) {
		this.mothersdateofbirth = mothersdateofbirth;
	}
	public String getYearofmarriage() {
		return yearofmarriage;
	}
	public void setYearofmarriage(String yearofmarriage) {
		this.yearofmarriage = yearofmarriage;
	}
	public String getYearofpassingssc() {
		return yearofpassingssc;
	}
	public void setYearofpassingssc(String yearofpassingssc) {
		this.yearofpassingssc = yearofpassingssc;
	}
	public String getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}
	public String getApplicantmobileno() {
		return applicantmobileno;
	}
	public void setApplicantmobileno(String applicantmobileno) {
		this.applicantmobileno = applicantmobileno;
	}
	public String getApplicantemail() {
		return applicantemail;
	}
	public void setApplicantemail(String applicantemail) {
		this.applicantemail = applicantemail;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getReferencenumber() {
		return referencenumber;
	}
	public void setReferencenumber(String referencenumber) {
		this.referencenumber = referencenumber;
	}
	public String getCardreferencenumber() {
		return cardreferencenumber;
	}
	public void setCardreferencenumber(String cardreferencenumber) {
		this.cardreferencenumber = cardreferencenumber;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public String getCardvalidtill() {
		return cardvalidtill;
	}
	public void setCardvalidtill(String cardvalidtill) {
		this.cardvalidtill = cardvalidtill;
	}
	public String getOfficialname() {
		return officialname;
	}
	public void setOfficialname(String officialname) {
		this.officialname = officialname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Ezpayapplicationform(String firstname, String middlename, String lastname, String address1, String address2,
			String city, String state, String pin, String mothersname, String mothersdateofbirth, String yearofmarriage,
			String yearofpassingssc, String applicationid, String applicantmobileno, String applicantemail,
			String userId, String referencenumber, String cardreferencenumber, String cardnumber, String cardvalidtill,
			String officialname, String status, String remarks, String date, String time) {
		super();
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.mothersname = mothersname;
		this.mothersdateofbirth = mothersdateofbirth;
		this.yearofmarriage = yearofmarriage;
		this.yearofpassingssc = yearofpassingssc;
		this.applicationid = applicationid;
		this.applicantmobileno = applicantmobileno;
		this.applicantemail = applicantemail;
		this.userId = userId;
		this.referencenumber = referencenumber;
		this.cardreferencenumber = cardreferencenumber;
		this.cardnumber = cardnumber;
		this.cardvalidtill = cardvalidtill;
		this.officialname = officialname;
		this.status = status;
		this.remarks = remarks;
		this.date = date;
		this.time = time;
	}
	
	
	
	
	

}
