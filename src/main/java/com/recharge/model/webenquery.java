package com.recharge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "webenquery")

@NamedQueries({ @NamedQuery(name = "GetwebenqueryReport", query = "from webenquery as we where we.date between :startDate and :endDate order by we.date desc")

})
public class webenquery  implements java.io.Serializable{
	
	private Integer Id;
    private String UserName;
    private String Address;
   /* private String district;
    private String state;
    private String pin;
    private String block;*/
    private String mail_id;
    private String mobile;
    /*private String dob;
    private String qualification;
    private String office_name;
    private String bank_name;
    private String branch_name;
    private String ifsc;*/
    private String remark;
    private String date;
    private String time;
	public webenquery() {
		super();
	}
	

	
	public webenquery(String userName, String address, String mail_id, String mobile, String remark,
			String date, String time) {
		super();
		
		UserName = userName;
		Address = address;
		this.mail_id = mail_id;
		this.mobile = mobile;
		this.remark = remark;
		this.date = date;
		this.time = time;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	public String getMail_id() {
		return mail_id;
	}
	public void setMail_id(String mail_id) {
		this.mail_id = mail_id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
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
   

}
