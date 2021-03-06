package com.recharge.model;
// Generated Aug 31, 2017 9:56:23 PM by Hibernate Tools 4.3.1



/**
 * Userservice generated by hbm2java
 */
public class Userservice  implements java.io.Serializable {


     private Integer id;
     private String wlId;
     private String userMob;
     private String userType;
     private String mb;
     private String dth;
     private String data;
     private String pp;
     private String ins;
     private String gb;
     private String eb;
     private String pan;
     private String hotel;
     private String irctc;
     private String dmr;
     private String bus;
     private String flight;
     private String shop;

    public Userservice() {
    }

	
    public Userservice(String hotel, String irctc, String dmr, String bus, String flight, String shop) {
        this.hotel = hotel;
        this.irctc = irctc;
        this.dmr = dmr;
        this.bus = bus;
        this.flight = flight;
        this.shop = shop;
    }
    public Userservice(String wlId, String userMob, String userType, String mb, String dth, String data, String pp, String ins, String gb, String eb, String pan, String hotel, String irctc, String dmr, String bus, String flight, String shop) {
       this.wlId = wlId;
       this.userMob = userMob;
       this.userType = userType;
       this.mb = mb;
       this.dth = dth;
       this.data = data;
       this.pp = pp;
       this.ins = ins;
       this.gb = gb;
       this.eb = eb;
       this.pan = pan;
       this.hotel = hotel;
       this.irctc = irctc;
       this.dmr = dmr;
       this.bus = bus;
       this.flight = flight;
       this.shop = shop;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getWlId() {
        return this.wlId;
    }
    
    public void setWlId(String wlId) {
        this.wlId = wlId;
    }
    public String getUserMob() {
        return this.userMob;
    }
    
    public void setUserMob(String userMob) {
        this.userMob = userMob;
    }
    public String getUserType() {
        return this.userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getMb() {
        return this.mb;
    }
    
    public void setMb(String mb) {
        this.mb = mb;
    }
    public String getDth() {
        return this.dth;
    }
    
    public void setDth(String dth) {
        this.dth = dth;
    }
    public String getData() {
        return this.data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    public String getPp() {
        return this.pp;
    }
    
    public void setPp(String pp) {
        this.pp = pp;
    }
    public String getIns() {
        return this.ins;
    }
    
    public void setIns(String ins) {
        this.ins = ins;
    }
    public String getGb() {
        return this.gb;
    }
    
    public void setGb(String gb) {
        this.gb = gb;
    }
    public String getEb() {
        return this.eb;
    }
    
    public void setEb(String eb) {
        this.eb = eb;
    }
    public String getPan() {
        return this.pan;
    }
    
    public void setPan(String pan) {
        this.pan = pan;
    }
    public String getHotel() {
        return this.hotel;
    }
    
    public void setHotel(String hotel) {
        this.hotel = hotel;
    }
    public String getIrctc() {
        return this.irctc;
    }
    
    public void setIrctc(String irctc) {
        this.irctc = irctc;
    }
    public String getDmr() {
        return this.dmr;
    }
    
    public void setDmr(String dmr) {
        this.dmr = dmr;
    }
    public String getBus() {
        return this.bus;
    }
    
    public void setBus(String bus) {
        this.bus = bus;
    }
    public String getFlight() {
        return this.flight;
    }
    
    public void setFlight(String flight) {
        this.flight = flight;
    }
    public String getShop() {
        return this.shop;
    }
    
    public void setShop(String shop) {
        this.shop = shop;
    }




}


