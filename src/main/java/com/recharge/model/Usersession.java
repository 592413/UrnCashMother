package com.recharge.model;
// Generated Aug 31, 2017 9:56:23 PM by Hibernate Tools 4.3.1



/**
 * Usersession generated by hbm2java
 */
public class Usersession  implements java.io.Serializable {


     private int sessionid;
     private String username;
     private String ipaddress;
     private String status;
     private String sessionStartTime;
     private String sessionEndTime;

    public Usersession() {
    }

	
    public Usersession(int sessionid) {
        this.sessionid = sessionid;
    }
    public Usersession(int sessionid, String username, String ipaddress, String status, String sessionStartTime, String sessionEndTime) {
       this.sessionid = sessionid;
       this.username = username;
       this.ipaddress = ipaddress;
       this.status = status;
       this.sessionStartTime = sessionStartTime;
       this.sessionEndTime = sessionEndTime;
    }
   
    public int getSessionid() {
        return this.sessionid;
    }
    
    public void setSessionid(int sessionid) {
        this.sessionid = sessionid;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getIpaddress() {
        return this.ipaddress;
    }
    
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public String getSessionStartTime() {
        return this.sessionStartTime;
    }
    
    public void setSessionStartTime(String sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }
    public String getSessionEndTime() {
        return this.sessionEndTime;
    }
    
    public void setSessionEndTime(String sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }




}


