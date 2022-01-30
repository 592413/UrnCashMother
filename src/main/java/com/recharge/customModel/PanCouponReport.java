package com.recharge.customModel;

public class PanCouponReport implements java.io.Serializable {
	
	private Integer id;
	private String UMobileNo;
	private String Password;
	private String panuser_id;
	private String panpassword;
	private String totalCoupon;
	private String requestId;
	private String status;
	private String date;
	private String time;
	private String userId;
	private String usernm;
	private String usermbl;
	public PanCouponReport() {
		super();
	}
	
	
	public PanCouponReport(Integer id, String uMobileNo, String password, String panuser_id, String panpassword,
			String totalCoupon, String requestId, String status, String date, String time, String userId, String usernm,
			String usermbl) {
		
		this.id = id;
		UMobileNo = uMobileNo;
		Password = password;
		this.panuser_id = panuser_id;
		this.panpassword = panpassword;
		this.totalCoupon = totalCoupon;
		this.requestId = requestId;
		this.status = status;
		this.date = date;
		this.time = time;
		this.userId = userId;
		this.usernm = usernm;
		this.usermbl = usermbl;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUMobileNo() {
		return UMobileNo;
	}
	public void setUMobileNo(String uMobileNo) {
		UMobileNo = uMobileNo;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getPanuser_id() {
		return panuser_id;
	}
	public void setPanuser_id(String panuser_id) {
		this.panuser_id = panuser_id;
	}
	public String getPanpassword() {
		return panpassword;
	}
	public void setPanpassword(String panpassword) {
		this.panpassword = panpassword;
	}
	public String getTotalCoupon() {
		return totalCoupon;
	}
	public void setTotalCoupon(String totalCoupon) {
		this.totalCoupon = totalCoupon;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsernm() {
		return usernm;
	}
	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}
	public String getUsermbl() {
		return usermbl;
	}
	public void setUsermbl(String usermbl) {
		this.usermbl = usermbl;
	}
	
	

}
