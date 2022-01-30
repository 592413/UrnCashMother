package com.recharge.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="nsdlpan")
@NamedQueries({@NamedQuery(name="getNSDLPanbyStatus", query="from NSDLPan as n where n.userId=:userId and n.status=:status and n.date between :startDate and :endDate order by id desc"),
	@NamedQuery(name="getNSDLPan", query="from NSDLPan as n where n.userId=:userId and n.date between :startDate and :endDate order by id desc"),
	@NamedQuery(name="getNSDLPanPending", query="from NSDLPan as n where n.userId=:userId and n.status=:status order by id desc"),
	@NamedQuery(name="getNSDLPanPendingHold", query="from NSDLPan as n where n.userId=:userId and n.status in ('PENDING','HOLD') order by id desc"),
	@NamedQuery(name="getNSDLPanAdmin", query="from NSDLPan as n where n.date between :startDate and :endDate order by id desc"),
	@NamedQuery(name="getNSDLPanPendingAd", query="from NSDLPan as n where n.status=:status and n.date between :startDate and :endDate order by id desc"),
	@NamedQuery(name="getNSDLPanPendingAdmin", query="from NSDLPan as n where n.status=:status order by id desc"),
	@NamedQuery(name="getNSDLPanPendingReport", query="from NSDLPan as n where n.status='PENDING' and n.date=:date and n.time=:time and n.mobile=:mobile and n.email=:email order by id desc"),
	@NamedQuery(name="getNSDLPanReportackno", query="from NSDLPan as n where n.ackno=:ackno  order by id desc")})
public class NSDLPan implements Serializable{
	@Id
	private int id;
	private String userId;
	private String name;
	private String mobile;
	private String email;
	private String dob;
	private String sex;
	private String fathername;
	private String add1;
	private String add2;
	private String pin;
	private String prooftype;
	private String idno;
	private String status;
	private String date;
	private String time;
	private String ackno;
	private String ackslip;
	private String invoiceno;
	private String remark;
	private String applicationType;
	private String middlename;
	private String lastname;
	private String state;
	private String fathernamem;
	private String fathernamel;
	private String oldpan;
	private String usernm;
	private double opnbl;
	private double clbl;
	private String namecard;
	private String district;
	private String addressproof;
	private String dobproof;
	private String filename;
	private String ackfilename;
	@Transient
	private String uname;
	@Transient
	private String umobile;
	
	public NSDLPan() {
		super();
	}
	
public NSDLPan(String userId, String name, String mobile, String email, String dob, String sex, String fathername,
			String add1, String add2, String pin, String prooftype, String idno, String status, String date,
			String time, String ackno, String ackslip, String invoiceno, String remark, String applicationType,
			String middlename, String lastname, String state, String fathernamem, String fathernamel, String oldpan,
			String usernm, double opnbl, double clbl, String namecard, String district, String addressproof,
			String dobproof,String filename,String ackfilename, String uname, String umobile) {
		
		this.userId = userId;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.dob = dob;
		this.sex = sex;
		this.fathername = fathername;
		this.add1 = add1;
		this.add2 = add2;
		this.pin = pin;
		this.prooftype = prooftype;
		this.idno = idno;
		this.status = status;
		this.date = date;
		this.time = time;
		this.ackno = ackno;
		this.ackslip = ackslip;
		this.invoiceno = invoiceno;
		this.remark = remark;
		this.applicationType = applicationType;
		this.middlename = middlename;
		this.lastname = lastname;
		this.state = state;
		this.fathernamem = fathernamem;
		this.fathernamel = fathernamel;
		this.oldpan = oldpan;
		this.usernm = usernm;
		this.opnbl = opnbl;
		this.clbl = clbl;
		this.namecard = namecard;
		this.district = district;
		this.addressproof = addressproof;
		this.dobproof = dobproof;
		this.filename = filename;
		this.ackfilename = ackfilename;
		this.uname = uname;
		this.umobile = umobile;
	}


	public NSDLPan(int id, String userId, String name, String mobile, String email, String dob, String sex,
		String fathername, String add1, String add2, String pin, String prooftype, String idno, String status,
		String date, String time, String ackno, String ackslip, String invoiceno, String remark, String applicationType,
		String middlename, String lastname, String state, String fathernamem, String fathernamel, String oldpan,
		String usernm, double opnbl, double clbl, String namecard, String district, String addressproof,
		String dobproof,String filename,String ackfilename, String uname, String umobile) {
	super();
	this.id = id;
	this.userId = userId;
	this.name = name;
	this.mobile = mobile;
	this.email = email;
	this.dob = dob;
	this.sex = sex;
	this.fathername = fathername;
	this.add1 = add1;
	this.add2 = add2;
	this.pin = pin;
	this.prooftype = prooftype;
	this.idno = idno;
	this.status = status;
	this.date = date;
	this.time = time;
	this.ackno = ackno;
	this.ackslip = ackslip;
	this.invoiceno = invoiceno;
	this.remark = remark;
	this.applicationType = applicationType;
	this.middlename = middlename;
	this.lastname = lastname;
	this.state = state;
	this.fathernamem = fathernamem;
	this.fathernamel = fathernamel;
	this.oldpan = oldpan;
	this.usernm = usernm;
	this.opnbl = opnbl;
	this.clbl = clbl;
	this.namecard = namecard;
	this.district = district;
	this.addressproof = addressproof;
	this.dobproof = dobproof;
	this.filename = filename;
	this.ackfilename = ackfilename;
	this.uname = uname;
	this.umobile = umobile;
}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getProoftype() {
		return prooftype;
	}
	public void setProoftype(String prooftype) {
		this.prooftype = prooftype;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
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
	public String getAckno() {
		return ackno;
	}
	public void setAckno(String ackno) {
		this.ackno = ackno;
	}
	public String getAckslip() {
		return ackslip;
	}
	public void setAckslip(String ackslip) {
		this.ackslip = ackslip;
	}
	public String getInvoiceno() {
		return invoiceno;
	}
	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getApplicationType() {
		return applicationType;
	}


	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFathernamem() {
		return fathernamem;
	}

	public void setFathernamem(String fathernamem) {
		this.fathernamem = fathernamem;
	}

	public String getFathernamel() {
		return fathernamel;
	}

	public void setFathernamel(String fathernamel) {
		this.fathernamel = fathernamel;
	}

	public String getOldpan() {
		return oldpan;
	}

	public void setOldpan(String oldpan) {
		this.oldpan = oldpan;
	}


	public String getUsernm() {
		return usernm;
	}


	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}


	public double getOpnbl() {
		return opnbl;
	}


	public void setOpnbl(double opnbl) {
		this.opnbl = opnbl;
	}


	public double getClbl() {
		return clbl;
	}


	public void setClbl(double clbl) {
		this.clbl = clbl;
	}



	public String getUname() {
		return uname;
	}



	public void setUname(String uname) {
		this.uname = uname;
	}



	public String getUmobile() {
		return umobile;
	}



	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}

	public String getNamecard() {
		return namecard;
	}

	public void setNamecard(String namecard) {
		this.namecard = namecard;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddressproof() {
		return addressproof;
	}

	public void setAddressproof(String addressproof) {
		this.addressproof = addressproof;
	}

	public String getDobproof() {
		return dobproof;
	}

	public void setDobproof(String dobproof) {
		this.dobproof = dobproof;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getAckfilename() {
		return ackfilename;
	}

	public void setAckfilename(String ackfilename) {
		this.ackfilename = ackfilename;
	}
	
	
	
	
}
