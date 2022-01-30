package com.skyflight.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="flightdetail")
@NamedQueries({

	@NamedQuery(name="getFlightdetailbyPNR",query="From Flightdetail where PNR=:PNR")
	
	
})
public class Flightdetail {
	
@Id	
private int id;
private String PNR;
private String flightid;
private String flight_number;
private String origin;
private String destination;
private String depttime;
private String arrtime;
private String classcode;
private String classcodedesc;
private String sourceterminal;
private String destinationterminal;
private String duration;


public Flightdetail() {
	super();
	// TODO Auto-generated constructor stub
}

public Flightdetail(String pNR,String flightid, String flight_number, String origin, String destination, String depttime,
		String arrtime, String classcode, String classcodedesc, String sourceterminal, String destinationterminal,
		String duration) {
	super();
	PNR = pNR;
	this.flightid = flightid;
	this.flight_number = flight_number;
	this.origin = origin;
	this.destination = destination;
	this.depttime = depttime;
	this.arrtime = arrtime;
	this.classcode = classcode;
	this.classcodedesc = classcodedesc;
	this.sourceterminal = sourceterminal;
	this.destinationterminal = destinationterminal;
	this.duration = duration;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}
public String getPNR() {
	return PNR;
}
public void setPNR(String pNR) {
	PNR = pNR;
}
public String getFlight_number() {
	return flight_number;
}
public void setFlight_number(String flight_number) {
	this.flight_number = flight_number;
}
public String getOrigin() {
	return origin;
}
public void setOrigin(String origin) {
	this.origin = origin;
}
public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public String getDepttime() {
	return depttime;
}
public void setDepttime(String depttime) {
	this.depttime = depttime;
}
public String getArrtime() {
	return arrtime;
}
public void setArrtime(String arrtime) {
	this.arrtime = arrtime;
}
public String getClasscode() {
	return classcode;
}
public void setClasscode(String classcode) {
	this.classcode = classcode;
}
public String getClasscodedesc() {
	return classcodedesc;
}
public void setClasscodedesc(String classcodedesc) {
	this.classcodedesc = classcodedesc;
}
public String getSourceterminal() {
	return sourceterminal;
}
public void setSourceterminal(String sourceterminal) {
	this.sourceterminal = sourceterminal;
}
public String getDestinationterminal() {
	return destinationterminal;
}
public void setDestinationterminal(String destinationterminal) {
	this.destinationterminal = destinationterminal;
}
public String getDuration() {
	return duration;
}
public void setDuration(String duration) {
	this.duration = duration;
}

public String getFlightid() {
	return flightid;
}

public void setFlightid(String flightid) {
	this.flightid = flightid;
}



	
	
}
