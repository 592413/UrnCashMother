package com.recharge.customModel;



public class APPReport {
	
	
	private int id;
	private String firstname; //1
	private String middlename;//
	
	private String lastname;//2
	private String address1;//3
	private String address2;//4
	private String city;//5
	private String state;//6
	private String pin;//7
	private String mothersname;//8
	private String mothersdateofbirth;//9
	private String yearofmarriage;//10
	private String yearofpassingssc;//11
	private String applicationid;//12
	
	private String applicantmobileno;//13
	private String applicantemail;//14
	private String userId;//15
	private String referencenumber;//16
	private String cardreferencenumber;//17
	private String cardnumber;//18
	private String cardvalidtill;//19
	private String officialname;//20
	private String status;//21
	private String remarks;//22
	private String date;//23
	private String time;//24
	private String uname;//25
	private String umobile;//26
	private String panimagename;
	private String aadharimagename;
	private String photoimagename;
	private String voterimagename;
	
	
	public String getVoterimagename() {
		return voterimagename;
	}

	public void setVoterimagename(String voterimagename) {
		this.voterimagename = voterimagename;
	}

	public String getPanimagename() {
		return panimagename;
	}

	public void setPanimagename(String panimagename) {
		this.panimagename = panimagename;
	}

	public String getAadharimagename() {
		return aadharimagename;
	}

	public void setAadharimagename(String aadharimagename) {
		this.aadharimagename = aadharimagename;
	}

	public String getPhotoimagename() {
		return photoimagename;
	}

	public void setPhotoimagename(String photoimagename) {
		this.photoimagename = photoimagename;
	}

	public APPReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public APPReport(int id,String firstname, String middlename, String lastname, String address1, String address2,
			String city, String state, String pin, String mothersname, String mothersdateofbirth, String yearofmarriage,
			String yearofpassingssc, String applicationid, String applicantmobileno, String applicantemail,
			String userId, String referencenumber, String cardreferencenumber, String cardnumber, String cardvalidtill,
			String officialname, String status, String remarks, String date, String time, String uname, String umobile) {
		super();
		this.id = id;
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
		this.uname = uname;
		this.umobile = umobile;
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
	

}
