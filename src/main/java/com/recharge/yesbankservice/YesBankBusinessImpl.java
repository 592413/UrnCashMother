package com.recharge.yesbankservice;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.recharge.businessdao.CommissionService;
import com.recharge.businessdao.CustomQueryServiceLogic;
import com.recharge.businessdao.GlobalCommandCenter;
import com.recharge.businessdao.SendRechargeRequest;
import com.recharge.customquery.CustomSqlQuery;

import com.recharge.model.AEPSBankDetail;
import com.recharge.model.AEPSCommission;
import com.recharge.model.AEPSLog;
import com.recharge.model.AEPSUserMap;
import com.recharge.model.AssignedPackage;
import com.recharge.model.DMRCommission;
import com.recharge.model.FingerPayAEPSResponse;
import com.recharge.model.ImpsTransaction;
import com.recharge.model.Imps_BankDetails;
import com.recharge.model.MicroAtmResponse;
import com.recharge.model.MicroAtmResponseNew;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.PaymonkResponse;
import com.recharge.model.Reseller;
import com.recharge.model.SettlementBalance;
import com.recharge.model.SettlementReport;
import com.recharge.model.User;
import com.recharge.servicedao.AEPSBankDetailDao;
import com.recharge.servicedao.AEPSCommissionDao;
import com.recharge.servicedao.AEPSLogDao;
import com.recharge.servicedao.AEPSUserMapDao;
import com.recharge.servicedao.AssignedPackageDAO;
import com.recharge.servicedao.DMRCommissionDao;
import com.recharge.servicedao.FingerPayAEPSResponseDao;
import com.recharge.servicedao.ImpsBankDao;
import com.recharge.servicedao.ImpsTransactionService;
import com.recharge.servicedao.MicroAtmResponseDao;
import com.recharge.servicedao.MicroAtmResponseNewDao;
import com.recharge.servicedao.SettlementBalanceDao;
import com.recharge.servicedao.SettlementChargeDao;
import com.recharge.servicedao.SettlementReportDao;
import com.recharge.servicedao.UserBankDetailsDao;
import com.recharge.servicedao.UserDao;
import com.recharge.utill.GenerateCheckSum;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.RBLService;
import com.recharge.utill.RDServiceStartek;
import com.recharge.utill.RoundNumber;
import com.recharge.yesbankmodel.TransactionDetail;
import com.recharge.yesbankmodel.YesBankAEPSResponse;
import com.recharge.yesbankmodel.YesBankApiToken;
import com.recharge.yesbankmodel.YesbankCustomer;
import com.recharge.yesbankservicedao.YesBankAEPSResponseDao;
import com.recharge.yesbankservicedao.YesBankApiTokenDao;
import com.recharge.yesbankservicedao.YesbankCustomerDao;

@Service("yesbankservice")
public class YesBankBusinessImpl implements YesBankBusiness {
	@Autowired YesBankApiTokenDao yesbanktokendao;
	@Autowired YesbankCustomerDao yesbankcustomerdao;
	@Autowired AEPSUserMapDao aepsuserdao;
	@Autowired AEPSBankDetailDao aepsBankdetail;
	@Autowired YesBankAEPSResponseDao yesbankresponseDao;
	@Autowired SettlementBalanceDao settlementbalancedao;
	@Autowired SettlementReportDao settlementreportdao;
	@Autowired UserBankDetailsDao userbankdetailsdao;
    @Autowired SettlementChargeDao  settlementchargrdao;
    @Autowired AEPSCommissionDao aepscommissiondao;
    @Autowired CommissionService commissionService;
    @Autowired CustomQueryServiceLogic customQueryServiceLogic;
    @Autowired AEPSLogDao aepslogdao;
    @Autowired GlobalCommandCenter globalCommandCenter;
    @Autowired HttpSession session;
    @Autowired AssignedPackageDAO assignedPackage;
	@Autowired ImpsTransactionService impsTransactiondao;
	@Autowired DMRCommissionDao DMRCommissionDao;
	@Autowired ImpsBankDao impsbankdao;
	@Autowired SendRechargeRequest sendRechargeRequest;
	@Autowired UserDao userDao;
	@Autowired MicroAtmResponseDao microatmresponseDao;
	@Autowired FingerPayAEPSResponseDao fingerpayaepsresponseDao;
	@Autowired MicroAtmResponseNewDao microatmresponsenew;
	
	//test
	/*private static final String cpCode="JITPU3872";
	private static final String agentCode="RS00789";*/
	//live
	private static final String cpCode="EASENC2350";
//	private static final String agentCode="RS00789";
	
	private static final Logger logger_log = Logger.getLogger(YesBankBusiness.class);

	@Override
	public Map<String, Object> getyesbankdetails() {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			User userDetails = (User) session.getAttribute("UserDetails");
		
			if (userDetails != null) {
			/*List<YesBankApiToken> list=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken token = list.get(0);
			returnData.put("yesbankaccesstoken",token.getToken());*/
			List<AEPSBankDetail> aepslist =	aepsBankdetail.getAllAEPSBankDetail();	
			
			returnData.put("AEPSBankList",aepslist);
			Reseller reseller = globalCommandCenter.getResellerByWlId(userDetails.getWlId());
			returnData.put("reseller", reseller);
			returnData.put("userDetails", userDetails);
			returnData.put("status","1");
			}else {
				session.invalidate();
				returnData.put("message", "Session Expire! Please Login.");
				returnData.put("nextPage", "home");
				returnData.put("status", "0");
			}
		} catch (Exception e) {
			logger_log.error("getyesbankdetails::::::::::::::::::::" + e);
		}

		return returnData;
	}

	@Override
	public Map<String, Object> searchCustomer(Map<String, Object> request,User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag =false;
		YesBankApiToken yestoken = null;
		String token = "";
		String input="";
		String AID ="";
		try {
			System.out.println(request);
			/*List<YesBankApiToken> list=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken token = list.get(0);
			returnData.put("yesbankaccesstoken",token.getToken());*/
			String mobile = request.get("mobile").toString();
			param.put("mobile",mobile);
			List<YesbankCustomer> list=yesbankcustomerdao.getYesbankCustomerByNamedQuery("getyesbankcustomerbyMobile",param);
			if(!list.isEmpty()){
				YesbankCustomer yesbankcustomer = list.get(0);
				returnData.put("yesbankcustomer",yesbankcustomer);
				returnData.put("status","1");
			}else{
				System.out.println("Payel:::::::::::::::::::::::::");
				List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
				if(!yeslist.isEmpty()){
				yestoken = yeslist.get(0);
				token =yestoken.getToken();
				param = new HashMap<String, Object>();
				param.put("username",user.getUserName());
				param.put("api","YesBank");
				List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
				AEPSUserMap aeps = list2.get(0);
				AID =aeps.getAgentcode();
				System.out.println("Payel:::::::::::::::::::::::::");
				input="{\"HEADER\": {\"ST\": \"BALANCEINFO\",\"AID\": \""+AID+"\",\"OP\": \"AEPS\"},\"DATA\": {\"CUSTOMER_MOBILE\": \""+mobile+"\"}}";
				
				returnData = YesBankwebservice.searchCustomer(token,input);
				System.out.println("returnData1:::::::::::::::::::"+returnData);
				
				if(returnData.containsKey("error_description")){
					String description = returnData.get("error").toString();
					if(description.contains("invalid_token")){
						token=YesBankwebservice.sendRequestToclientcredential()	;
						
						yestoken.setToken(token);
						flag =yesbanktokendao.updateYesBankApiToken(yestoken);
						if(flag){
					    input="{\"HEADER\": {\"ST\": \"BALANCEINFO\",\"AID\": \""+AID+"\",\"OP\": \"AEPS\"},\"DATA\": {\"CUSTOMER_MOBILE\": \""+mobile+"\"}}";
						
						returnData = YesBankwebservice.searchCustomer(token,input);
						System.out.println("returnData:::::::::::::::::::"+returnData);
						//return returnData;
						}
						
					}
				}
			//	System.out.println("returnData:::::::::::::::::::"+returnData);
				/*if(returnData.get("status").toString().equalsIgnoreCase("0")){
					//System.out.println("returnData:::::::::::::::::::"+returnData);
					if(returnData.get("message").toString().equalsIgnoreCase("customer doesn't exit")){
						System.out.println("returnData:::::::::::::::::::"+returnData);
						 input="{\"HEADER\": {\"ST\": \"BALANCEINFO\",\"AID\": \""+AID+"\",\"OP\": \"AEPS\"},\"DATA\": {\"CUSTOMER_MOBILE\": \""+mobile+"\", \"CUST_FNAME\": \"Test\"}}";
						 returnData =YesBankwebservice.Customerregister(token,input);
						 if(returnData.get("status").toString().equalsIgnoreCase("1")){
							 YesbankCustomer yes = new YesbankCustomer(mobile,"TEst");
							 flag=yesbankcustomerdao.insertYesbankCustomer(yes);
							 if(flag){
								 returnData.put("status","1"); 
								 returnData.put("yesbankcustomer",yes);
							 }else{
								 returnData.put("status","0"); 
							 }
						 }else{
							 returnData.put("status","0"); 
						 }
						
					}else{
						 returnData.put("status","0"); 
					}
					}
				*/
				
			}else{
				token=YesBankwebservice.sendRequestToclientcredential()	;
				yestoken= new YesBankApiToken();
				yestoken.setToken(token);
				flag =yesbanktokendao.updateYesBankApiToken(yestoken);
				if(flag){
			    input="{\"HEADER\": {\"ST\": \"BALANCEINFO\",\"AID\": \""+AID+"\",\"OP\": \"AEPS\"},\"DATA\": {\"CUSTOMER_MOBILE\": \""+mobile+"\"}}";
				
				returnData = YesBankwebservice.searchCustomer(token,input);	
			}
			}
			}
			return returnData;

		} catch (Exception e) {
			logger_log.error("searchCustomer::::::::::::::::::::" + e);
		}

		return returnData;
	}

	
	
	@Override
	public Map<String, Object> Customerregister(Map<String, Object> request,User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try{
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken=yeslist.get(0);
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			String mobile = request.get("mobile").toString();
			String name = request.get("name").toString();
			
			String input="{\"HEADER\": {\"ST\": \"BALANCEINFO\",\"AID\": \""+AID+"\",\"OP\": \"AEPS\"},\"DATA\": {\"CUSTOMER_MOBILE\": \""+mobile+"\", \"CUST_FNAME\": \""+name+"\"}}";
			 returnData =YesBankwebservice.Customerregister(yestoken.getToken(),input);
			 if(returnData.get("status").toString().equalsIgnoreCase("1")){
				 YesbankCustomer yes = new YesbankCustomer(mobile,name);
				 yesbankcustomerdao.insertYesbankCustomer(yes);
			 }
		}catch (Exception e) {
			logger_log.error("Customerregister::::::::::::::::::::" + e);
		}
		return returnData;
	}
	
	

	@Override
	public Map<String, Object> getDeviceByDeviceType(Map<String, Object> request,User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> returnData2 = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		String message ="";
		String pidOpt="";
		
		try{
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			String mobile =request.get("mobile").toString();
			String deviceType =request.get("deviceType").toString();
			/*if(request.get("deviceType").toString().equalsIgnoreCase("Startek")){*/
			   /* System.out.println("deviceType:::::::::::::::"+deviceType);
			    List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			    YesBankApiToken yestoken=yeslist.get(0);
			    String input="{\"HEADER\":{\"ST\":\"BALANCEINFO\",\"AID\":\""+AID+"\",\"OP\":\"AEPS\"},\"DATA\":{\"CUSTOMER_MOBILE\":\""+mobile+"\",\"DEVICE\":\""+deviceType+"\"}}}";
			    returnData =YesBankwebservice.getDevicePID(yestoken.getToken(),input);
			    System.out.println("pidOpt1:::::::::::::::::::"+returnData.get("pidOpt").toString());
			    if(deviceType.equalsIgnoreCase("STARTEK_PROTOBUF") || deviceType.equalsIgnoreCase("MANTRA_PROTOBUF")){
			    pidOpt=returnData.get("pidOpt").toString();
			    }else if(deviceType.equalsIgnoreCase("MORPHO_PROTOBUF")){
			    pidOpt="<PidOptions ver=\"1.0\"><Opts fCount=\"1\" fType=\"0\" iCount=\"\" iType=\"\" pCount=\"\" pType=\"\" format=\"0\" pidVer=\"2.0\" timeout=\"10000\" otp=\"\" wadh=\"\" posh=\"\"/></PidOptions>";	
			    System.out.println("pidOptmorpho:::::::::::::::::::::"+pidOpt);
			    }*/
			    //System.out.println("pidOpt:::::::::::::::::::"+pidOpt);
			    // System.out.println("returnData::::::::::::::::"+returnData);
			    //morpho
			//	String pidOpt="<PidOptions ver=\"1.0\"><Opts fCount=\"1\" fType=\"0\" iCount=\"\" iType=\"\" pCount=\"\" pType=\"\" format=\"0\" pidVer=\"2.0\" timeout=\"10000\" otp=\"\" wadh=\"\" posh=\"\"/></PidOptions>";
				//mantra
			//	String pidOpt="<?xml version=\"1.0\"?> <PidOptions ver=\"1.0\"> <Opts fCount=\"1\" fType=\"0\" iCount=\"0\" pCount=\"0\" pgCount=\"2\" format=\"0\"   pidVer=\"2.0\" timeout=\"10000\" pTimeout=\"20000\" posh=\"UNKNOWN\" env=\"PP\" /> <CustOpts><Param name=\"mantrakey\" value=\"\" /></CustOpts> </PidOptions>";
			    //startek
			   // String pidOpt="<PidOptions> <Opts fCount=\"1\" fType=\"0\" format=\"0\" pidVer=\"2.0\" timeout=\"20000\" otp=\"\" env=\"P\"/> <CustOpts> <Param name =\"ValidationKey\" value=\"\" /> </CustOpts> </PidOptions>";
				 System.out.println("pidOpt:::::::::::::::::::"+pidOpt);
			   // String pidOpt="<PidOptions><Opts fCount=\"1\" fType=\"0\" iCount=\"0\" pCount=\"0\" format=\"1\" pidVer=\"2.0\" timeout=\"20000\" otp=\"\" posh=\"LEFT_INDEX\" env=\"S\"wadh=\"\" /> <Demo></Demo> <CustOpts> <Param name=\"Param1\" value=\"\" /> </CustOpts></PidOptions>";
				String pidData=RDServiceStartek.getPidStartek2(pidOpt,"");
	//			String pidData="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><PidData>   <DeviceInfo dc=\"a7487c1c-de88-49e6-b7f3-f52fe3a04bb6\" dpId=\"Morpho.SmartChip\" mc=\"MIIEBzCCAu+gAwIBAgIGAWiSKTBwMA0GCSqGSIb3DQEBCwUAMIGjMSEwHwYDVQQDExhEUyBTTUFSVCBDSElQIFBWVCBMVEQgMTIxHjAcBgNVBDMTFUQgMjE2IFNlY3RvciA2MyBOb2lkYTEOMAwGA1UECRMFTm9pZGExFjAUBgNVBAgTDVVUVEFSIFBSQURFU0gxDDAKBgNVBAsTA0RTQTEbMBkGA1UEChMSU01BUlQgQ0hJUCBQVlQgTFREMQswCQYDVQQGEwJJTjAeFw0xOTAxMjgwMTUzMTlaFw0xOTAyMjcwMTUzMTlaMIHFMTcwNQYDVQQDDC5yZF9kZXZpY2VfYTc0ODdjMWMtZGU4OC00OWU2LWI3ZjMtZjUyZmUzYTA0YmI2MQswCQYDVQQGEwJpbjEWMBQGA1UECBMNVXR0YXIgUHJhZGVzaDEOMAwGA1UEBxMFTm9pZGExMTAvBgkqhkiG9w0BCQEWInBhbmthai5hZ2Fyd2FsQHNtYXJ0Y2hpcG9ubGluZS5jb20xDDAKBgNVBAsTA0RTQTEUMBIGA1UEChMLTUFSUEhPUkRQT0MwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDtdt7EdxWA9N9VhShmeyNDWL7m/Sb6/KvwX+f+vAN8oO4bFH4LxP/fUg1MUoZPAHZfXB46oh+ojbItgI5T7qtgpWhM7/5nEsxCjM/DgE5xwWzsPJa3uivFoIRyI02zEftpPW1FTm03cO1nz7ZJYyHe9EA+v0XS+JWUdO5afWikWd6F1h0uc6maUafb8SnOq8xrMPHJDCKhfOY/AZAjm7AOtpIB7Yd5WWi8PFf0mTrMyNnKYKkld8q743xYh39OfaOyaxr4CDqR8LVxH+YhZWPRsDRnd16PY8MJSxumc14OxOgi4GrV283dZ6SpkoK3yf/79i5vyTGK6PwuNreNco/fAgMBAAGjHTAbMAwGA1UdEwQFMAMBAf8wCwYDVR0PBAQDAgGGMA0GCSqGSIb3DQEBCwUAA4IBAQCLkLMjuZp+yEIss8T6VlKyFsxBBFW/liF2rmsiFjMZMrUnBQYAj8xT+A15OZxsmXfrjPlH0tmH1md6PwkgmB5+bCB1ZXARD3lLwQV7j+4O1aVqMD6ZNc+4a/Qx1zxQWROLNLFkHOm5lWJpbBcOJJ/K/Ls6+iGSj1r3oA1Jgb3IxmBl1ztRd7YbeSXeKk5ZupgxD9KRPyP3paqmEBWRmlfkSwLOqq+mAJ5eKebii69Cwx8wLvQ3bJGUt5ptQ9IISNuzH5tiujue3C24q1nAKaMD6e5j50mp9lbfxtUKKBA8LjftwGiO7l6HwctqVXBFT2aW3Uand1QqGYRjF5xTC+9w\" mi=\"MSO1300E2L0SW\" rdsId=\"SCPL.AND.001\" rdsVer=\"1.1.1\"/>   <Data type=\"X\">MjAxOS0wMi0xMlQxNzowMjozNSKdkZVRUo+2Go6CdJwgk01wZEHw8LUsdNuPqDz3puyEi8YfzRJ/F9KTnSbRU8nwGDKqQk2Ta3RIlw0vE8vXkXkFucioLSCf0iR9yBggcXmEyODfNuwsIZPWwSKMcM3wNj5YDBYmFRyAtRoDFsuTDqznMo7mUs/jjaUu+7mCxgz2hm305tE+rh50PIm4+E7UPz7vfE44rRStQDTcJmQkCDIc77S2GpV3FLmaD5Fyo+apCYLneN8b4C7mheKs16vxAdKImPadLld4jDPUpmdsCj9grhUrVwohMM9s2T1t3Xhwbr4jCTChgEpbhjIs2SUq79uX/g5mHbxUF2zHN38ifXQeDBLAoBc07uI9R0KJqiF2G7NhTFIUWwJaNjBtQ/Ro6/oSZGWF3SHeyl3DURQkncflZryMw0VZ0j1t8F0wHdb6nvDGaiGJXpsfAlxhAnLCsshR0pX8Tl744Y61OsTnFKIbHwYZ+9bFmICsswOFby6CeFxOmkKnB128kzneU0D6WEiOVg5NrQ5Htxbuc5mYyz4Ihsmm+XUSnIexoKwfYHy26XDwnZ7KJ1gnO5L3Qm4/MB1UB75CwhGRI8ZxwK+ZEOgCLexWkmr3CwMEd4soRicfX6vR8ZNIvpxXV/t1OgzZyuGQLrE9AWf0OGJ19TbW57xIi+T9K3AMDUhpodxWOkpKI6Z62Vf4T3dXTqgpLeMxpF5cb2msuh0DpmRXvl9sL+KSHll1PttjV0Y6LUGOZuXGqBVg3jPSmBTyauuuQnWuKk80zOqWtzcR7TdEw1nRnFxi0BLHB/CRyzqxGzAMw5+cL6hNPuznklDxxQ9jZN7RgUDXiw9gdNxNFb9pvD0lCQ4WdyOCPok1K2uoG9VyRNt+H9K6Q5MmK/gxl9lOo07dp3XW6hxxuGfTgzUhIZLI3iPsIdy7NIZVvaFGexzOWgVwoUg/UudvCL2ZzEO02TYs29GyfBck78HBuPYOmyuvs7oza9xvI5Scgi3F4Ju7eE3ygmX/WnFLveTpuJSiNuYVGmsYHy+FpRTmmdrE2EyA6kN2zBa+DXOWVb96a0p9NdNaijxqCETqv6+9Pu9y0EmAj0vb3UKDsUWNcSzOh7orq1CmfHAA37+O0dZcGUKzjHzZHDtctlfbELyljJ2hfSE8bn030HZbnE37IQiiknH7FMiy3WAW/vV6nzRHdxNhDM00eKylRzczLMKbgJiv01BvmfnsRj14EVnb3bXXCJakMHV1dENx</Data>   <Skey ci=\"20191230\">IeSd+Bq5mdS6CFUxKEhLHrvWh7LZ2BVVZrmxAxAvIRTBT5XjOwOT16NTOplkgeUf3/ySYSdsPcvTJ7A8Ny0Tyi+WYK29YzPGM12+pO6OaUDwpWZsXKNsRqYp8TVAtJb4NS1F8n8ocAMtRdGqYNhyo9e6mDBAAq1pPxZoWBmvClZtmzv8l+MueOOTL22HeJZk+JLy1JkBTVg2sb8boFLGPf/Lq17hZRHv0CDWVs+eY9DAvqdVosUX2Yx7kT0n87gtDqRICZQD/SztHynMTEdkB0rMwXO4xRrW7tiaKiyIdkZKIpmL1oWO6vCwFTkJoGrs41Q5uj9OLJ0gk04wPoLnsA==</Skey>   <Hmac>o6PJfHWu3ABRyjtKRH/dmUKttAd+J2XiPkXoAsKFSLAb3RCEHnchEx25XrO74a65</Hmac>   <Resp errCode=\"0\" fCount=\"1\" fType=\"0\" iCount=\"0\" iType=\"0\" nmPoints=\"38\" pCount=\"0\" pType=\"\" qScore=\"35\"/></PidData>";
				returnData2 = RDServiceStartek.getPIDParser(pidData);
				logger_log.warn("getDeviceByDeviceTypereturn::::::::::::::::"+returnData2);
				/*if(returnData2.get("errCode").toString().equalsIgnoreCase("0")){
				message ="<div class='row-fluid'><div class='span8'><div class='control-group'><div class='controls'><div class='input-prepend'><h5 style='color:red;'>Fingure Captured successfully.</h5></div></div></div></div></div>";
				String newresponse=StringEscapeUtils.escapeJava(pidData);
				returnData.put("pidData",newresponse);
				returnData.put("message",message);
				returnData.put("status","1");
				}else{
				message ="<div class='row-fluid'><div class='span8'><div class='control-group'><div class='controls'><div class='input-prepend'><h5 style='color:red;'>"+returnData2.get("errInfo").toString()+"</h5></div></div></div></div></div>";
				returnData.put("message",message);
				returnData.put("status","1");
				}*/
			/*}*/
		}catch (Exception e) {
			message ="<div class='row-fluid'><div class='span8'><div class='control-group'><div class='controls'><div class='input-prepend'><h5 style='color:red;'>Error Occurs</h5></div></div></div></div></div>";
			returnData.put("message",message);
			returnData.put("status","0");
			logger_log.error("getDeviceByDeviceType::::::::::::::::::::" + e);
		}
		
		return returnData;
	}
	
	
	@Override
	public Map<String, Object> getRDHashAndroid(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try{
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			String mobile =request.get("mobile").toString();
			String deviceType =request.get("deviceType").toString();
			System.out.println("deviceType:::::::::::::::"+deviceType);
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken=yeslist.get(0);
			String input="{\"HEADER\":{\"ST\":\"BALANCEINFO\",\"AID\":\""+AID+"\",\"OP\":\"AEPS\"},\"DATA\":{\"CUSTOMER_MOBILE\":\""+mobile+"\",\"DEVICE\":\""+deviceType+"\"}}}";
		    returnData =YesBankwebservice.getDevicePID(yestoken.getToken(),input);
			
		}catch (Exception e) {
			logger_log.error("getRDHashAndroid::::::::::::::::::::" + e);
		}
		return returnData;
	}
	

	@Override
	public Map<String, Object> yesBankTransAndroid(Map<String,String > request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		String checksum = "";
		String response = "";
		boolean flag=false;
		double amount =0.0;
		String processingCode="";
		boolean update = false;
		double settlementamount = 0.0;
		double settlementamountprev = 0.0;
		double settlementamountnew = 0.0;
		String username = "";
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		SettlementReport settle2=null;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		int id = 0;
		String token="";
		try{
			username = user.getUserName();
			String mobile = request.get("mobile").toString();
			String TXN_AMOUNT="0";
			param = new HashMap<String, Object>();
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			System.out.println("Payel:::::::::::::::::::::::::");
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken=yeslist.get(0);
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			
			System.out.println("returnData1:::::::::::::::::::"+returnData);
			
			
		//	System.out.println("returnData:::::::::::::::::::"+returnData);
			
	
			String ORDER_ID = GenerateRandomNumber.generatePtid(mobile);
			request.put("ORDER_ID",ORDER_ID);
			String ST=request.get("mode").toString();
		
			String bank = request.get("bank").toString();
			
			if(ST.equalsIgnoreCase("BALANCEINFO")){
				TXN_AMOUNT="0";
				processingCode="310000";
				}else if(ST.equalsIgnoreCase("WITHDRAWAL")){
				TXN_AMOUNT=request.get("amount").toString();
				processingCode="010000";
				}
			checksum=GenerateCheckSum.generateChecksumyesbankAnd(request,AID);
			String inputdata ="{\"HEADER\": {\"ST\": \""+ST+"\",\"TXN_AMOUNT\": \""+TXN_AMOUNT+"\",\"AID\": \""+AID+"\",\"OP\": \"AEPS\"},\"DATA\": {\"ORDER_ID\": \""+ORDER_ID+"\",\"IIN\": \""+bank+"\",\"AadharNumber\": \""+request.get("aadhar").toString()+"\",\"isAgree\": \"true\",\"mobileNumber\": \""+mobile+"\",\"BiometricData\": \""+StringEscapeUtils.escapeJava(request.get("pidData"))+"\"}}";
			/*System.out.println("inputdata:::::::::::::::::::"+inputdata);
			System.out.println("token:::::::::::::::::::"+yestoken.getToken());
			System.out.println("ST:::::::::::::::::::"+ST);*/
			
			response=YesBankwebservice.sendRequestToyessBankTransaction(yestoken.getToken(),inputdata,checksum,ST);
		    // response="{'RESP_CODE':'300','RESPONSE':'SUCCESS','RESP_MSG':'Transaction Successful (300)','DATA':{'STAN':'637988','RRN':'809515637988','Aadhar':'505306234265','IIN':'508534','TxnAmount':'100','ResponseCode':'00','AccountType':'NA','BalanceType':'NA','CurrancyCode':'356','BalanceIndicator':'NA','BalanceAmount':'1330513','AccountTypeLedger':'00','BalanceTypeLedger':'NA','CurrancyCodeLedger':'356','BalanceIndicatorLedger':'C','BalanceAmountLedger':'1330513','AccountTypeActual':'00','BalanceTypeActual':'NA','CurrancyCodeActual':'356','BalanceIndicatorActual':'C','BalanceAmountActual':'1330513','Status':2,'UIDAIAuthenticationCode':'8e06c4319a654a7690048b4b978b5053','RetailerTxnId':'1522923927706','TerminalId':'register','txnDate':'Thu Apr 05 15:55:32 IST 2018','txnCharge':0,'paidAmount':0}}";
			
			YesBankAEPSResponse yesres=YesbankWebserviceParser.yesBankaepsResponseParser(response);
			if(yesres.getRESP_CODE().equalsIgnoreCase("300")){
			yesres.setAgentcode(AID);
			yesres.setUsername(user.getUserName());
			yesres.setProcessingCode(processingCode);
			flag=yesbankresponseDao.insertYesBankAEPSResponse(yesres);
			if(flag){
			if(ST.equalsIgnoreCase("WITHDRAWAL"))	{
			amount=Double.parseDouble(yesres.getTxnAmount());	
			settlementamount=amount;
			param = new HashMap<String, Object>();
			param.put("api","YESBANK");
			
			List<AEPSCommission> aepscommlist = aepscommissiondao.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi",param);
			for(AEPSCommission comm2 : aepscommlist){
				if(settlementamount>=comm2.getSlab1() && settlementamount<=comm2.getSlab2()){
					id = comm2.getId();
					break;
				}
			}
			System.out.println("id:::::::::::::::::::::::::::::"+id);
			param = new HashMap<String, Object>();
			
			if(user.getRoleId() == 5) {
				pckret = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"YESBANK AEPS",id) ;
				//Retailer Id
				//rId=userDetails.getUserId();
				// Distributor Id
				distId = user.getUplineId();		
				
				// Super Distributor Id
				parameters = new HashMap<String, String>();
				parameters.put("userId", distId);	
				sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
				
				// Reseller Id
				parameters = new HashMap<String, String>();
				parameters.put("userId", sdId);							
				resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
				if(pckret.getPackage_id()!=null){
				if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				retComm =(pckret.getComm()*settlementamount)/100;
				}else{
				retComm =pckret.getComm();	
				}
				comm = retComm;
				}
				System.out.println("comm:::::::::::::::::::::::::::::"+comm);
			//	System.out.println("reseller=="+comm);
				pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"YESBANK AEPS",id); 
				if(pckdis.getPackage_id()!=null){
				if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				dComm =(pckdis.getComm()*settlementamount)/100;
				}else{
				dComm=pckdis.getComm();	
				}
				dComm=dComm-comm;
				}
				System.out.println("dComm:::::::::::::::::::::::::::::"+dComm);
			//	System.out.println("dComm=="+dComm);
				pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"YESBANK AEPS",id);
				if(pcksd.getPackage_id()!=null){
				if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				sdComm =(pcksd.getComm()*settlementamount)/100;
				}else{
				sdComm = pcksd.getComm();	
				}
				sdComm=sdComm-dComm;
				}
				
			//	System.out.println("sdComm=="+sdComm);
				pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"YESBANK AEPS",id);
				if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				resComm =(pckres.getComm()*settlementamount)/100;
				}else{
				resComm =pckres.getComm();	
				}
			//	System.out.println("resComm=="+resComm);
				commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
				commissionService.updateBalance(distId, "AEPS Commission For Amount - "+settlementamount, "Commission", dComm, "CREDIT",0);
				commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT",0);
				
			}else if(user.getRoleId() == 4) {

				//distId = userDetails.getUserId();	
				sdId = user.getUplineId();	
				
				parameters = new HashMap<String, String>();
				parameters.put("userId", sdId);							
				resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
				
				pckdis=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"YESBANK AEPS",id); 
				if(pckdis.getPackage_id()!=null){
				if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				dComm =(pckdis.getComm()*settlementamount)/100;
				}else{
				dComm=pckdis.getComm();	
				}
				}
				
				comm = dComm;
				
			//	System.out.println("dComm="+dComm);
				pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"YESBANK AEPS",id);
				System.out.println("pcksd:::::::::::::::::"+pcksd);
				if(pcksd.getPackage_id()!=null){
				if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				sdComm =(pcksd.getComm()*settlementamount)/100;
				}else{
				sdComm = pcksd.getComm();	
				}
				sdComm=sdComm-dComm;
				}
				
				//System.out.println("sdComm="+sdComm);
				if(!resellerId.equals("admin")) {
				pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"YESBANK AEPS",id);
				if(pckres.getPackage_id()!=null){
				if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				resComm =(pckres.getComm()*settlementamount)/100;
				}else{
				resComm =pckres.getComm();	
				}
				resComm=resComm-sdComm;
				}
				commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0);
				}
				//System.out.println("resComm="+resComm);
				
				commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
				commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT",0);
				
			}else if(user.getRoleId() == 3) {

				resellerId = user.getUplineId();
				pcksd=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"YESBANK AEPS",id);
				if(pcksd.getPackage_id()!=null){
				if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
			     sdComm =(pcksd.getComm()*settlementamount)/100;
				}else{
					sdComm = pcksd.getComm();	
				}
				}
				System.out.println("sdComm:::::::::"+sdComm);
				if(!resellerId.equals("admin")) {
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"YESBANK AEPS",id);
					if(pckres.getPackage_id()!=null){
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*settlementamount)/100;
					}else{
					resComm =pckres.getComm();	
					}
					resComm=resComm-sdComm;
					}
					commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0);
					}
				commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
				commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT",0); 
			
			}
			/*if(processingCode.equalsIgnoreCase("010000")){*/
				param.put("username",user.getUserName());
				List<SettlementBalance> settlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
				if(settlelist.isEmpty()){
					SettlementBalance SettlementBalance = new SettlementBalance(username,settlementamount);
					settlementbalancedao.insertSettlementBalance(SettlementBalance);	
					settle2 = new SettlementReport(user.getUserName(),0.0,settlementamount, settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
				}else{
					SettlementBalance SettlementBalance = settlelist.get(0);
					settlementamountprev = SettlementBalance.getSettlementbalance();
					settlementamountnew = settlementamountprev+settlementamount;
					SettlementBalance.setSettlementbalance(settlementamountnew);
					settlementbalancedao.updateSettlementBalance(SettlementBalance);
					settle2 = new SettlementReport(user.getUserName(),settlementamountprev,settlementamountnew,settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
				}
				    settlementreportdao.insertSettlementReport(settle2);
			/*}*/
			}
			returnData.put("YesBankResponse",yesres);
			returnData.put("status","1");
			}
		}else{
			
			if(yesres.getRESP_CODE().equalsIgnoreCase("301")){/*
				AEPSLog aepsLog = new AEPSLog(ORDER_ID,AID,"PENDING", "EDIT","EDIT", GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime(),"00");
				 aepslogdao.insertAEPSLog(aepsLog);
				 returnData.put("message","Your transaction is pending,please check the status after 15 minutes");
			*/}else{
				returnData.put("message",yesres.getRESPONSE());
			}
			//returnData.put("message",yesres.getRESPONSE());
			returnData.put("status","0");
		//	returnData.put("nextPage","aepspage");
		}
		}catch (Exception e) {
			logger_log.error("yesBankTransaction::::::::::::::::::::"+e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> yesBankTransaction(Map<String,Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		String checksum = "";
		String response = "";
		boolean flag=false;
		double amount =0.0;
		String processingCode="";
		boolean update = false;
		double settlementamount = 0.0;
		double settlementamountprev = 0.0;
		double settlementamountnew = 0.0;
		String username = "";
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		SettlementReport settle2=null;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		int id = 0;
		try{
			username = user.getUserName();
			String mobile = request.get("mobile").toString();
			String TXN_AMOUNT="0";
			String ORDER_ID ="";
			
			String ST=request.get("mode").toString();
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken=yeslist.get(0);
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			String bank = request.get("bank").toString();
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			System.out.println("listsize:::::::::::::::::::::::::"+list2.size());
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			if(ST.equalsIgnoreCase("BALANCEINFO")){
				TXN_AMOUNT="0";
				processingCode="310000";
				ORDER_ID= "AB"+GenerateRandomNumber.generatePtid(mobile);
				}else if(ST.equalsIgnoreCase("WITHDRAWAL")){
				TXN_AMOUNT=request.get("amount").toString();
				processingCode="010000";
				ORDER_ID="AW"+GenerateRandomNumber.generatePtid(mobile);
				}
			System.out.println("ORDER_ID::::::::"+ORDER_ID);
			Date date= new Date();
			 
			 long time = date.getTime();
			request.put("ORDER_ID",ORDER_ID);
			request.put("cpCode", cpCode);
			request.put("timestap", time);
			checksum=GenerateCheckSum.generateChecksumyesbank(request,AID);
			String inputdata ="{\"HEADER\": {\"ST\": \""+ST+"\",\"TXN_AMOUNT\": \""+TXN_AMOUNT+"\",\"AID\": \""+AID+"\",\"OP\": \"AEPS\"},\"DATA\": {\"ORDER_ID\": \""+ORDER_ID+"\",\"IIN\": \""+bank+"\",\"AadharNumber\": \""+request.get("aadhar").toString()+"\",\"isAgree\": \"true\",\"mobileNumber\": \""+mobile+"\",\"BiometricData\": \""+StringEscapeUtils.escapeJava(request.get("pidData").toString())+"\"}}";
			System.out.println("inputdata:::::::::::::::::::"+inputdata);
			System.out.println("pidData:::::::::::::::::::"+request.get("pidData").toString());
			System.out.println("token:::::::::::::::::::"+yestoken.getToken());
			System.out.println("ST:::::::::::::::::::"+ST);
			
		     response=YesBankwebservice.sendRequestToyessBankTransaction(yestoken.getToken(),inputdata,checksum,ST);
			//response="{'RESP_CODE':'300','RESPONSE':'SUCCESS','RESP_MSG':'Transaction Successful (300)','DATA':{'STAN':'582479','RRN':'907116582479','Aadhar':'XXXXXXXX7674','IIN':'508534','TxnAmount':100.0,'ResponseCode':'00','AccountType':'NA','BalanceType':'NA','CurrancyCode':'356','BalanceIndicator':'NA','BalanceAmount':'NA','AccountTypeLedger':'00','BalanceTypeLedger':'NA','CurrancyCodeLedger':'356','BalanceIndicatorLedger':'C','BalanceAmountLedger':25911.41,'AccountTypeActual':'00','BalanceTypeActual':'NA','CurrancyCodeActual':'356','BalanceIndicatorActual':'C','BalanceAmountActual':25911.41,'Status':2,'UIDAIAuthenticationCode':'2effc5d938d264bf29448e2c94f085ca','RetailerTxnId':'AW327589680481','TerminalId':'EAS0SAFE9122','txnDate':'Tue Mar 12 16:22:02 IST 2019','txnCharge':null,'paidAmount':100.0}}";
			System.out.println("response::::::::::::::::::::::::::"+response);
			YesBankAEPSResponse yesres=YesbankWebserviceParser.yesBankaepsResponseParser(response);
			if(yesres.getRESP_CODE().equalsIgnoreCase("300")){
			//yesres.setStatus("SUCCESS");	
			yesres.setAgentcode(AID);
			yesres.setUsername(user.getUserName());
			yesres.setProcessingCode(processingCode);
			flag=yesbankresponseDao.insertYesBankAEPSResponse(yesres);
			if(flag){
			if(ST.equalsIgnoreCase("WITHDRAWAL"))	{
			amount=Double.parseDouble(yesres.getTxnAmount());	
			settlementamount=amount;
			param = new HashMap<String, Object>();
			param.put("api","YESBANK");
			
			List<AEPSCommission> aepscommlist = aepscommissiondao.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi",param);
			for(AEPSCommission comm2 : aepscommlist){
				if(settlementamount>=comm2.getSlab1() && settlementamount<=comm2.getSlab2()){
					id = comm2.getId();
					break;
				}
			}
			System.out.println("id:::::::::::::::::::::::::::::"+id);
			param = new HashMap<String, Object>();
			
			if(user.getRoleId() == 5) {
				pckret = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"YESBANK AEPS",id) ;
				//Retailer Id
				//rId=userDetails.getUserId();
				// Distributor Id
				distId = user.getUplineId();		
				
				// Super Distributor Id
				parameters = new HashMap<String, String>();
				parameters.put("userId", distId);	
				sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
				
				// Reseller Id
				parameters = new HashMap<String, String>();
				parameters.put("userId", sdId);							
				resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
				if(pckret.getPackage_id()!=null){
				if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				retComm =(pckret.getComm()*settlementamount)/100;
				}else{
				retComm =pckret.getComm();	
				}
				comm = retComm;
				}
				System.out.println("comm:::::::::::::::::::::::::::::"+comm);
			//	System.out.println("reseller=="+comm);
				pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"YESBANK AEPS",id); 
				if(pckdis.getPackage_id()!=null){
				if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				dComm =(pckdis.getComm()*settlementamount)/100;
				}else{
				dComm=pckdis.getComm();	
				}
				dComm=dComm-comm;
				}
				System.out.println("dComm:::::::::::::::::::::::::::::"+dComm);
			//	System.out.println("dComm=="+dComm);
				pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"YESBANK AEPS",id);
				if(pcksd.getPackage_id()!=null){
				if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				sdComm =(pcksd.getComm()*settlementamount)/100;
				}else{
				sdComm = pcksd.getComm();	
				}
				sdComm=sdComm-dComm;
				}
				
			//	System.out.println("sdComm=="+sdComm);
				pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"YESBANK AEPS",id);
				if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				resComm =(pckres.getComm()*settlementamount)/100;
				}else{
				resComm =pckres.getComm();	
				}
			//	System.out.println("resComm=="+resComm);
				commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
				commissionService.updateBalance(distId, "AEPS Commission For Amount - "+settlementamount, "Commission", dComm, "CREDIT",0);
				commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT",0);
				
			}else if(user.getRoleId() == 4) {

				//distId = userDetails.getUserId();	
				sdId = user.getUplineId();	
				
				parameters = new HashMap<String, String>();
				parameters.put("userId", sdId);							
				resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
				
				pckdis=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"YESBANK AEPS",id); 
				if(pckdis.getPackage_id()!=null){
				if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				dComm =(pckdis.getComm()*settlementamount)/100;
				}else{
				dComm=pckdis.getComm();	
				}
				}
				
				comm = dComm;
				
			//	System.out.println("dComm="+dComm);
				pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"YESBANK AEPS",id);
				System.out.println("pcksd:::::::::::::::::"+pcksd);
				if(pcksd.getPackage_id()!=null){
				if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				sdComm =(pcksd.getComm()*settlementamount)/100;
				}else{
				sdComm = pcksd.getComm();	
				}
				sdComm=sdComm-dComm;
				}
				
				//System.out.println("sdComm="+sdComm);
				if(!resellerId.equals("admin")) {
				pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"YESBANK AEPS",id);
				if(pckres.getPackage_id()!=null){
				if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				resComm =(pckres.getComm()*settlementamount)/100;
				}else{
				resComm =pckres.getComm();	
				}
				resComm=resComm-sdComm;
				}
				commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0);
				}
				//System.out.println("resComm="+resComm);
				
				commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
				commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT",0);
				
			}else if(user.getRoleId() == 3) {

				resellerId = user.getUplineId();
				pcksd=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"YESBANK AEPS",id);
				if(pcksd.getPackage_id()!=null){
				if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
			     sdComm =(pcksd.getComm()*settlementamount)/100;
				}else{
					sdComm = pcksd.getComm();	
				}
				}
				System.out.println("sdComm:::::::::"+sdComm);
				if(!resellerId.equals("admin")) {
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"YESBANK AEPS",id);
					if(pckres.getPackage_id()!=null){
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*settlementamount)/100;
					}else{
					resComm =pckres.getComm();	
					}
					resComm=resComm-sdComm;
					}
					commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0);
					}
				commissionService.updateBalance(username, "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
				commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT",0); 
			
			}
			/*if(processingCode.equalsIgnoreCase("010000")){*/
				param.put("username",user.getUserName());
				List<SettlementBalance> settlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
				if(settlelist.isEmpty()){
					SettlementBalance SettlementBalance = new SettlementBalance(username,settlementamount);
					settlementbalancedao.insertSettlementBalance(SettlementBalance);	
					settle2 = new SettlementReport(user.getUserName(),0.0,settlementamount, settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
				}else{
					SettlementBalance SettlementBalance = settlelist.get(0);
					settlementamountprev = SettlementBalance.getSettlementbalance();
					settlementamountnew = settlementamountprev+settlementamount;
					SettlementBalance.setSettlementbalance(settlementamountnew);
					settlementbalancedao.updateSettlementBalance(SettlementBalance);
					settle2 = new SettlementReport(user.getUserName(),settlementamountprev,settlementamountnew,settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
				}
				    settlementreportdao.insertSettlementReport(settle2);
			/*}*/
			}
			returnData.put("YesBankResponse",yesres);
			returnData.put("status","1");
			}
		}else{
			yesres.setAgentcode(AID);
			yesres.setUsername(user.getUserName());
			yesres.setProcessingCode(processingCode);
			flag=yesbankresponseDao.insertYesBankAEPSResponse(yesres);
			returnData.put("YesBankResponse",yesres);
			/*if(yesres.getRESP_CODE().equalsIgnoreCase("301")){
				AEPSLog aepsLog = new AEPSLog(ORDER_ID,AID,"PENDING", "EDIT","EDIT", GenerateRandomNumber.getCurrentDate());
				 aepslogdao.insertAEPSLog(aepsLog);
				 returnData.put("message","Your transaction is pending,please check the status after 15 minutes");
			}else{
				returnData.put("message",yesres.getRESPONSE());
			}*/
			//returnData.put("message",yesres.getRESPONSE());
			returnData.put("status","0");
			//returnData.put("nextPage","aepspage");
		}
		}catch (Exception e) {
			logger_log.error("yesBankTransaction::::::::::::::::::::"+e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getYesBankAEPSReport(Map<String, String> request, User userDetail) {
		Map<String, Object> returndata = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try{
			if(userDetail.getRoleId()>2){
				//System.out.println("HI:::::::::::::::::::::"+userDetail.getUserName());
				param.put("username", userDetail.getUserName());
				param.put("api","YesBank" );
				List<AEPSUserMap> aepsuserlist = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
				if(!aepsuserlist.isEmpty()){
				
					param = new HashMap<String, Object>();
					param.put("username",userDetail.getUserName());
					param.put("start_date",request.get("startDate"));
					param.put("end_date",request.get("endDate"));
					List<YesBankAEPSResponse> rblreport = yesbankresponseDao.getYesBankAEPSResponseByNamedQuery("getyesbankaepsresponsebyusername",param);
					if(!rblreport.isEmpty()){
						returndata.put("status","1");
						returndata.put("rblReport",rblreport);	
					}else{
						returndata.put("status", "0");
						returndata.put("message", "No data available");		
					}
					
				}else{
					returndata.put("status", "0");
					returndata.put("message", "You are not YesBank REGISTERED USER");	
				}
			}else if(userDetail.getRoleId()==1){
				/*param.put("start_date",request.get("startDate"));
				param.put("end_date",request.get("endDate"));*/
				parameters.put("start_date",request.get("startDate"));
				parameters.put("end_date",request.get("endDate"));
				List<YesBankAEPSResponse> rblreport = customQueryServiceLogic.getYesBankReport(CustomSqlQuery.getYesAepsReportAll,parameters);
				
				//List<PaymonkResponse> rblreport = paymonkresponsedao.getPaymonkResponseByNamedQuery("getpaymonkresponsebyadmin",param);
				if(!rblreport.isEmpty()){
					returndata.put("status", "1");
					returndata.put("rblReport",rblreport);	
				}else{
					returndata.put("status", "0");
					returndata.put("message", "No data available");		
				}	
			}else if(userDetail.getRoleId()==2){
				parameters.put("wl_id",userDetail.getWlId());
				parameters.put("start_date",request.get("startDate"));
				parameters.put("end_date",request.get("endDate"));
				List<YesBankAEPSResponse> rblreport = customQueryServiceLogic.getYesBankReport(CustomSqlQuery.getYesAepsReportAllwl,parameters);
				if(!rblreport.isEmpty()){
					returndata.put("status", "1");
					returndata.put("rblReport",rblreport);	
				}else{
					returndata.put("status", "0");
					returndata.put("message", "No data available");		
				}	
				
			}
		}catch (Exception e) {
			logger_log.error("getRBLAEPSReport:::::::::::::::::::::" + e);
			returndata.put("message", e.getMessage());
		}
		return returndata;
	}

	
	@Override
	public Map<String, Object> getMicroATMReport(Map<String, String> request, User userDetail) {
		Map<String, Object> returndata = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			if (userDetail.getRoleId() > 2) {
				param = new HashMap<String, Object>();
				param.put("username", userDetail.getUserName());
				param.put("start_date", request.get("startDate"));
				param.put("end_date", request.get("endDate"));
				List<MicroAtmResponse> rblreport = microatmresponseDao
						.getMicroAtmResponseByNamedQuery("getMicroReportByusername", param);
				if (!rblreport.isEmpty()) {
					returndata.put("status", "1");
					returndata.put("microReport", rblreport);
				} else {
					returndata.put("status", "0");
					returndata.put("message", "No data available");
				}

			} else if (userDetail.getRoleId() == 1) {
				parameters.put("start_date", request.get("startDate"));
				parameters.put("end_date", request.get("endDate"));
				List<MicroAtmResponse> rblreport = customQueryServiceLogic
						.getMicroATMReport(CustomSqlQuery.getMicroAepsReportAll, parameters);
				if (!rblreport.isEmpty()) {
					returndata.put("status", "1");
					returndata.put("microReport", rblreport);
				} else {
					returndata.put("status", "0");
					returndata.put("message", "No data available");
				}
			} else if (userDetail.getRoleId() == 2) {
				parameters.put("wl_id", userDetail.getWlId());
				parameters.put("start_date", request.get("startDate"));
				parameters.put("end_date", request.get("endDate"));
				List<MicroAtmResponse> rblreport = customQueryServiceLogic
						.getMicroATMReport(CustomSqlQuery.getMicroAepsReportAllwl, parameters);
				if (!rblreport.isEmpty()) {
					returndata.put("status", "1");
					returndata.put("microReport", rblreport);
				} else {
					returndata.put("status", "0");
					returndata.put("message", "No data available");
				}

			}
		}catch (Exception e) {
			logger_log.error("getMicroATMReport:::::::::::::::::::::"+e);
			returndata.put("message", e.getMessage());
		}
		return returndata;
	}
	
	@Override
	public Map<String, Object> getFingerATMReport(Map<String, String> request, User userDetail) {
		Map<String, Object> returndata = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try{
			if(userDetail.getRoleId()>2){
				//System.out.println("HI:::::::::::::::::::::"+userDetail.getUserName());
				/*
				 * param.put("username", userDetail.getUserName()); param.put("api","YesBank" );
				 * List<AEPSUserMap> aepsuserlist =
				 * aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
				 * if(!aepsuserlist.isEmpty()){
				 */
				
					param = new HashMap<String, Object>();
					param.put("username",userDetail.getUserName());
					param.put("start_date",request.get("startDate"));
					param.put("end_date",request.get("endDate"));
					List<FingerPayAEPSResponse> rblreport =fingerpayaepsresponseDao.getFingerPayAEPSResponseByNamedQuery("getFingerPayReportByusername",param);
					if(!rblreport.isEmpty()){
						returndata.put("status","1");
						returndata.put("fingeraepsReport",rblreport);	
					}else{
						returndata.put("status", "0");
						returndata.put("message", "No data available");		
					}
					
				/*}else{
					returndata.put("status", "0");
					returndata.put("message", "You are not YesBank REGISTERED USER");	
				}*/
			}else if(userDetail.getRoleId()==1){
				parameters.put("start_date",request.get("startDate"));
				parameters.put("end_date",request.get("endDate"));
				List<FingerPayAEPSResponse> rblreport = customQueryServiceLogic.getFingerAepsReport(CustomSqlQuery.getFingerAepsReportAll,parameters);
				
				//List<PaymonkResponse> rblreport = paymonkresponsedao.getPaymonkResponseByNamedQuery("getpaymonkresponsebyadmin",param);
				if(!rblreport.isEmpty()){
					returndata.put("status", "1");
					returndata.put("fingeraepsReport",rblreport);	
				}else{
					returndata.put("status", "0");
					returndata.put("message", "No data available");		
				}	
			}else if(userDetail.getRoleId()==2){
				parameters.put("wl_id",userDetail.getWlId());
				parameters.put("start_date",request.get("startDate"));
				parameters.put("end_date",request.get("endDate"));
				List<FingerPayAEPSResponse> rblreport = customQueryServiceLogic.getFingerAepsReport(CustomSqlQuery.getFingerAepsReportAllwl,parameters);
				if(!rblreport.isEmpty()){
					returndata.put("status", "1");
					returndata.put("fingeraepsReport",rblreport);	
				}else{
					returndata.put("status", "0");
					returndata.put("message", "No data available");		
				}	
				
			}
		}catch (Exception e) {
			logger_log.error("getRBLAEPSReport:::::::::::::::::::::" + e);
			returndata.put("message", e.getMessage());
		}
		return returndata;
	}
	@Override
	public Map<String, Object> checkuserYesBank(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag =false;
		YesBankApiToken yestoken = null;
		String token ="";
		String input ="";
		String mobile ="";
		String AID="";
		try {
			
		    List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
		    if(!yeslist.isEmpty()){
		    yestoken = yeslist.get(0);
		
		    mobile = request.get("mobile").toString();
		    param.put("mobile",mobile);
			token = yestoken.getToken();
			param = new HashMap<String, Object>();
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			AID =aeps.getAgentcode();
		
			input="{\"ST\": \"REMDOMESTIC\",\"AID\": \""+AID+"\",\"OP\": \"DMTNUR\",\"CUSTOMER_MOBILE\": \""+mobile+"\"}";
			
			returnData = YesBankwebservice.checkuserYesBank(token,input);
			logger_log.warn("returnData1:::::::::::::::::::"+returnData);
		
			if(returnData.containsKey("error_description")){
				String description = returnData.get("error_description").toString();
				if(description.contains("Invalid access token")||description.contains("Access token expired")){
					token=YesBankwebservice.sendRequestToclientcredential()	;
					yeslist=yesbanktokendao.getYesBankApiToken();
					yestoken = yeslist.get(0);
					yestoken.setToken(token);
					flag =yesbanktokendao.updateYesBankApiToken(yestoken);
					if(flag){
				    input="{\"ST\": \"REMDOMESTIC\",\"AID\": \""+AID+"\",\"OP\": \"DMTNUR\",\"CUSTOMER_MOBILE\": \""+mobile+"\"}";
					
					returnData = YesBankwebservice.checkuserYesBank(token,input);
					}
					
				}
			}
			
			if(returnData.get("status").toString().equalsIgnoreCase("1")){
				returnData.put("remitter",mobile);
			}
			session.setAttribute("checkRemitterMobile",request.get("mobile"));
		
	}else{
		token=YesBankwebservice.sendRequestToclientcredential()	;
		//yeslist=new YesBankApiToken();
		yestoken = new YesBankApiToken();
		yestoken.setToken(token);
		flag =yesbanktokendao.updateYesBankApiToken(yestoken);
		if(flag){
		    input="{\"ST\": \"REMDOMESTIC\",\"AID\": \""+AID+"\",\"OP\": \"DMTNUR\",\"CUSTOMER_MOBILE\": \""+mobile+"\"}";
			
			returnData = YesBankwebservice.checkuserYesBank(token,input);
			}
		
	}
		return returnData;

	} catch (Exception e) {
			logger_log.error("checkuserYesBank::::::::::::::::::::" + e);
		}

		return returnData;
	}
	
	
	@Override
	public Map<String, Object> yesBankRemitterRegister(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag =false;
		YesBankApiToken yestoken = null;
		try {
			System.out.println(request);
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			 yestoken = yeslist.get(0);
			
			   String mobile = request.get("mobile").toString();
			   String customerfname = request.get("customerfname").toString();
			   String customerlname = request.get("customerlname").toString();
			
			
				System.out.println("Payel:::::::::::::::::::::::::");
				String token = yestoken.getToken();
				param = new HashMap<String, Object>();
				param.put("username",user.getUserName());
				param.put("api","YesBank");
				List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
				AEPSUserMap aeps = list2.get(0);
				String AID =aeps.getAgentcode();
				System.out.println("Payel:::::::::::::::::::::::::");
				String input="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\",\"CUSTOMER_MOBILE\":\""+mobile+"\",\"CUST_LNAME\":\""+customerlname+"\",\"STATE\":\"WEST BENGAL\",\"BENE_NAME\":\"TEST\",\"CUST_ADDRESS\":\"KOLKATA\",\"PINCODE\":\"700101\",\"BANK_ACCOUNTNO\":\"0000000000\",\"CUST_TITLE\":\"Miss\",\"CITY\":\"KOLKATA\",\"CUST_EMAIL\":\"teST@gmail.com\",\"CUST_FNAME\":\""+customerfname+"\",\"CUST_ALTMOBILENO\":\"\",\"BANKIFSC_CODE\":\"CORP0008954\",\"BENE_MOBILENO\":\"0000000000\",\"CUST_DOB\":\"10-12-1993\"}";
				
				returnData = YesBankwebservice.yesBankRemitterRegister(token,input);
				System.out.println("returnData1:::::::::::::::::::"+returnData);
				
			
				
				if(returnData.get("status").toString().equalsIgnoreCase("1")){
					returnData.put("remitter",mobile);
				}
				
				//session.setAttribute("checkRemitterMobile",request.get("mobile"));
			//	System.out.println("returnData:::::::::::::::::::"+returnData);
				/*if(returnData.get("status").toString().equalsIgnoreCase("0")){
					//System.out.println("returnData:::::::::::::::::::"+returnData);
					if(returnData.get("message").toString().equalsIgnoreCase("customer doesn't exit")){
						System.out.println("returnData:::::::::::::::::::"+returnData);
						 input="{\"HEADER\": {\"ST\": \"BALANCEINFO\",\"AID\": \""+AID+"\",\"OP\": \"AEPS\"},\"DATA\": {\"CUSTOMER_MOBILE\": \""+mobile+"\", \"CUST_FNAME\": \"Test\"}}";
						 returnData =YesBankwebservice.Customerregister(token,input);
						 if(returnData.get("status").toString().equalsIgnoreCase("1")){
							 YesbankCustomer yes = new YesbankCustomer(mobile,"TEst");
							 flag=yesbankcustomerdao.insertYesbankCustomer(yes);
							 if(flag){
								 returnData.put("status","1"); 
								 returnData.put("yesbankcustomer",yes);
							 }else{
								 returnData.put("status","0"); 
							 }
						 }else{
							 returnData.put("status","0"); 
						 }
						
					}else{
						 returnData.put("status","0"); 
					}
					}
				*/
			
			return returnData;

		} catch (Exception e) {
			logger_log.error("yesBankRemitterRegister::::::::::::::::::::" + e);
		}

		return returnData;
	}

	
	@Override
	public Map<String, Object> yesBankNewBeneficiary(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
	    try{
	    	String beneMobileNumber = request.get("beneMobileNumber").toString();
			String bene_name = request.get("bene_name").toString();
			String bene_type = "IMPS";
			String accountNumber = request.get("accountNumber").toString();
			String acc_type = request.get("acc_type").toString();
			String beneIFSCCode = request.get("IFSC_CODE").toString();
			param = new HashMap<String, Object>();
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			
			String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
			String input="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\",\"BANK_CITY\":\"\",\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"BENE_NAME\":\""+bene_name+"\",\"BANK_BRANCH\":\"\",\"BANKIFSC_CODE\":\""+beneIFSCCode+"\",\"BANK_ADDRESS\":\"\",\"BANK_ACCOUNTNO\":\""+accountNumber+"\",\"BANK_STATE\":\"\",\"BENE_MOBILENO\":\""+beneMobileNumber+"\"}";
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken = yeslist.get(0);
			returnData = YesBankwebservice.yesBankNewBeneficiary(yestoken.getToken(),input);
			if(returnData.get("status").toString().equalsIgnoreCase("1")){
				returnData.put("mobile",checkRemitterMobile);	
			}
	    	
	    }catch (Exception e) {
	    	logger_log.error("yesBankNewBeneficiary::::::::::::::::::::" + e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> addYesBaneAndroid(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
	    try{
	    	String beneMobileNumber = request.get("beneMobileNumber").toString();
			String bene_name = request.get("bene_name").toString();
			String bene_type = "IMPS";
			String accountNumber = request.get("accountNumber").toString();
			String acc_type = request.get("acc_type").toString();
			String beneIFSCCode = request.get("IFSC_CODE").toString();
			param = new HashMap<String, Object>();
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			
			String checkRemitterMobile=request.get("mobile").toString();
			String input="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\",\"BANK_CITY\":\"\",\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"BENE_NAME\":\""+bene_name+"\",\"BANK_BRANCH\":\"\",\"BANKIFSC_CODE\":\""+beneIFSCCode+"\",\"BANK_ADDRESS\":\"\",\"BANK_ACCOUNTNO\":\""+accountNumber+"\",\"BANK_STATE\":\"\",\"BENE_MOBILENO\":\""+beneMobileNumber+"\"}";
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken = yeslist.get(0);
			returnData = YesBankwebservice.yesBankNewBeneficiary(yestoken.getToken(),input);
			if(returnData.get("status").toString().equalsIgnoreCase("1")){
				returnData.put("mobile",checkRemitterMobile);	
			}
	    	
	    }catch (Exception e) {
	    	logger_log.error("yesBankNewBeneficiary::::::::::::::::::::" + e);
		}
		return returnData;
	}

	

	@Override
	public Map<String, Object> viewYesBankbene() {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try{
			String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
		}catch (Exception e) {
			logger_log.error("viewYesBankbene::::::::::::::::::::" + e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> yesBankMoneytransfer(Map<String, Object> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		
		double charge = 0.0;
		boolean flag;
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		double apiComm = 0.0;
		double sdCharge = 0.0;
		double distCharge = 0.0;			
		double resCharge = 0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		double disComm = 0.0;
		double sdisComm = 0.0;
		double reComm = 0.0;
		double slab1 = 0.0;
		double slab2 = 0.0;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
		
		String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
		try{
		String beneMobileNumber = request.get("mobile").toString();
		String KYC = request.get("custtype").toString();
		String bene_name = request.get("name").toString();
		String transfertype = request.get("transfertype").toString();
		String accountNumber = request.get("account").toString();
		String acc_type = request.get("accountType").toString();
		String beneIFSCCode = request.get("ifsc").toString();
		String beneid = request.get("id").toString();
		String transactionAmount=request.get("amount").toString();
		parameter = new HashMap<String, Object>();
		parameter.put("username",userDetails.getUserName());
		parameter.put("api","YesBank");
		List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",parameter);
		AEPSUserMap aeps = list2.get(0);
		String AID =aeps.getAgentcode();
		String referenceno = "";
		double amount=0.0;
		double totalAmount=0.0;
		double transamount=Double.parseDouble(transactionAmount);
		int id=0;
		/*parameter = new HashMap<String, Object>();
		parameter.put("user_id", userDetails.getUserId());
		parameter.put("service_type", "DMR");
		List<AssignedPackage> lista = assignedPackage
				.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", parameter);*/
	/*-------------------------------------------------------------------------------------------------------------------*/
		String tId = "";
		
		//if(!lista.isEmpty()){
			
			parameter = new HashMap<String, Object>();
			parameter.put("api", "DMR");
			List<DMRCommission> opList =  DMRCommissionDao.getDMRCommissionByNamedQuery("getDMRCommissionByApi", parameter);
			if((!opList.isEmpty())) {
				double ckbl=0;
				if(transamount>5000){
					ckbl=5000;
				}else{
					ckbl=transamount;
				}
				for(DMRCommission comm2 : opList){
					if(ckbl>=comm2.getSlab1() && ckbl<=comm2.getSlab2()){
						id = comm2.getId();
						break;
					}
				}	
				
				if (userDetails.getRoleId() == 5) {
					pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id) ;
					if (pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pckret.getCharge() * transamount) / 100;
					} else {
						charge = pckret.getCharge();
					}
					
					System.out.println("charge="+charge);
				} else if (userDetails.getRoleId() == 4) {
					pckdis = commissionService.getPackagewiseCommisionOnOperator(
							userDetails.getUserId(),"DMR",id);
					if (pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pckdis.getCharge() * transamount) / 100;
					} else {
						charge = pckdis.getCharge();
					}

				} else if (userDetails.getRoleId() == 3) {
					pcksd = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					
					if (pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pcksd.getCharge() * transamount) / 100;
					} else {
						charge = pcksd.getCharge();
					}
					logger_log.warn("charge:::::"+charge);

				}	else if (userDetails.getRoleId() == 100) {
					pckapiu = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					if (pckapiu.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pckapiu.getCharge() * transamount) / 100;
					} else {
						charge = pckapiu.getCharge();
					}

				}				
			
			/*-------------------------------------COMMISSION---------------------------------------------------------------------*/
				if(userDetails.getRoleId() == 5) {
					//Retailer Id
					//rId=userDetails.getUserId();
					// Distributor Id
					distId = userDetails.getUplineId();		
					
					// Super Distributor Id
					parameters = new HashMap<String, String>();
					parameters.put("userId", distId);	
					sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
					
					// Reseller Id
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
					if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					retComm =(pckret.getComm()*transamount)/100;
					}else{
					retComm =pckret.getComm();	
					}
					comm = retComm;
				//	System.out.println("reseller=="+comm);
					pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"DMR",id); 
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*transamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
				//	System.out.println("dComm=="+dComm);
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					sdComm =(pcksd.getComm()*transamount)/100;
					}else{
					sdComm = pcksd.getComm();	
					}
				//	System.out.println("sdComm=="+sdComm);
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*transamount)/100;
					}else{
					resComm =pckres.getComm();	
					}
				//	System.out.println("resComm=="+resComm);
					
				} else if(userDetails.getRoleId() == 4) {
					//distId = userDetails.getUserId();	
					sdId = userDetails.getUplineId();	
					
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					pckdis=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id); 
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*transamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
					comm = dComm;
				//	System.out.println("dComm="+dComm);
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					sdComm =(pcksd.getComm()*transamount)/100;
					}else{
					sdComm = pcksd.getComm();	
					}
					sdComm=sdComm-dComm;
					//System.out.println("sdComm="+sdComm);
					if(!resellerId.equals("admin")) {
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*transamount)/100;
					}else{
					resComm =pckres.getComm();	
					}
					resComm=resComm-sdComm;
					}
					//System.out.println("resComm="+resComm);
					
				} else if(userDetails.getRoleId() == 3) {
					resellerId = userDetails.getUplineId();
					pcksd=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				     sdComm =(pcksd.getComm()*transamount)/100;
					}else{
						sdComm = pcksd.getComm();	
					}
					comm=sdComm;
					logger_log.warn("sdComm:::::::::"+sdComm);
					
					/*sdComm = commissionService.getCommisionOnOperator(userDetails.getUplineId(), userDetails.getWlId(), 3, operator.getOperatorId());
					
					resComm = commissionService.getCommisionOnOperator(resellerId, userDetails.getWlId(), 2, operator.getOperatorId());			*/			
				}else if(userDetails.getRoleId() == 100) {
					pckapiu=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					if(pckapiu.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						apiComm =(pckapiu.getComm()*transamount)/100;
					}else{
						apiComm = pckapiu.getComm();	
					}
					comm = apiComm;
					System.out.println("apiComm:::::::::"+apiComm);
					
					}
		/*---------------------------------------------------------------------------------------------------------------*/
		/*if (Double.parseDouble(transactionAmount) >= 10 && Double.parseDouble(transactionAmount) <= 20000) {*/
			
			double temp = Double.parseDouble(transactionAmount);
			double remain = 0.0;
		/*	while (temp > 0) {*/
			    if(transfertype.equalsIgnoreCase("IMPS")){
				referenceno = "I"+GenerateRandomNumber.generateIPtid(checkRemitterMobile);
			    }else{
			    referenceno = "N"+GenerateRandomNumber.generateIPtid(checkRemitterMobile);	
			    }
				/*if (temp > 5000) {
					temp = temp - 5000;
					remain = 5000;
				} else {
					remain = temp;
					temp = 0.0;
				}*/
				parameter = new HashMap<String, Object>();
				parameter.put("IFSC_CODE",beneIFSCCode);
				List<Imps_BankDetails> impslist=impsbankdao.getImpsBankDetailsByNamedQuery("getBankDetailsByIFSC",parameter);
				Imps_BankDetails impsbank = impslist.get(0);
	              
				double lockbalance=userDetails.getLockedAmount();
				totalAmount = RoundNumber.roundDouble((transamount + charge) - comm) ;
				parameters = new HashMap<String, String>();
				parameters.put("userId", userDetails.getUserId());
				double op_bal = customQueryServiceLogic
						.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser,parameters);
				System.out.println("op_bal::::::"+op_bal);
				double cl_bal = RoundNumber.roundDouble(op_bal - totalAmount);
			System.out.println("cl_bal::::::"+cl_bal);
				if(lockbalance<=cl_bal){
				if (op_bal > totalAmount) {
					String trnsamnt=Double.toString(transamount);
					amount = transamount;
				//	String input="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\",\"BANK_CITY\":\"\",\"PAYABLE_AMOUNT\":\"100\",\"TXN_AMOUNT\":\"100\",\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"BENE_NAME\":\""+bene_name+"\",\"BANK_BRANCH\":\"\",\"BANKIFSC_CODE\":\""+beneIFSCCode+"\",\"BANK_ADDRESS\":\"\",\"BANK_ACCOUNTNO\":\""+accountNumber+"\",\"BANK_STATE\":\"\",\"BENE_MOBILENO\":\""+beneMobileNumber+"\"}";
					String input="{\"HEADER\":{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"PAYABLE_AMOUNT\":\""+trnsamnt+"\",\"TXN_AMOUNT\":\""+trnsamnt+"\",\"AID\":\""+AID+"\"},\"DATA\":{\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"ORDER_ID\":\""+referenceno+"\",\"KEY_KYC_STATUS\":\""+KYC+"\",\"BENE_BANKNAME\":\""+impsbank.getBank_Name()+"\",\"TRANSFER_TYPE\":\""+transfertype+"\",\"BENE_NAME\":\""+bene_name+"\",\"CN\":\""+checkRemitterMobile+"\",\"BANKIFSC_CODE\":\""+beneIFSCCode+"\",\"BANK_ACCOUNTNO\":\""+accountNumber+"\",\"BENE_MOBILENO\":\""+beneMobileNumber+"\",\"BENE_ID\":\""+beneid+"\"}}";
					List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
					YesBankApiToken yestoken = yeslist.get(0);

					
					returnData = YesBankwebservice.yesBankMoneytransfer(yestoken.getToken(),input);
					System.out.println("returnData::::::::::::"+returnData);
					if(returnData.get("status").toString().equalsIgnoreCase("1") || returnData.get("status").toString().equalsIgnoreCase("3")){
					String FUNDTRANSNO= returnData.get("CUSTOMER_REFERENCE_NO").toString();
					//System.out.println("FUNDTRANSNO::::::::::::" + FUNDTRANSNO);
					String status=(String) returnData.get("status");
					
					//System.out.println("charge::::::::::::" + charge);
				//	totalAmount = charge + amount;
					
					//System.out.println("totalAmount::::::::::::::::::::" + totalAmount);
					parameters = new HashMap<String, String>();
					double adOpBal = customQueryServiceLogic
							.getTotalbalanceForAdmin(CustomSqlQuery.getTotalbalanceForAdmin, parameters);
					RoundNumber.roundDouble(adOpBal - totalAmount);
					
					if (cl_bal > 0) {
						String today = GenerateRandomNumber.getCurrentDate();	
						String currentTime = GenerateRandomNumber.getCurrentTime();
						String transId = GenerateRandomNumber.generateTransactionNumber();
						flag = commissionService.updateBalance(userDetails.getUserId(),
								"Money Transfer - " + accountNumber, "Money Transfer", totalAmount, "DEBIT",0);
						//System.out.println("flag::::::"+flag);
						if (flag) {
							List<TransactionDetail> translist = (List<TransactionDetail>)returnData.get("transdetail");
							if (status.equals("1")) {
								
								for(TransactionDetail trans:translist){
							ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, trans.getTRANSFER_AMOUNT(), charge, cl_bal, today, currentTime, transId, "SUCCESS",trans.getREQUEST_REFERENCE_NO(),comm,transfertype,checkRemitterMobile,trans.getPAID_AMOUNT(),0.0,"DMR");
							flag=impsTransactiondao.insertImpsTransaction(imptrans);
								}
							}else if(status.equals("0")){
								for(TransactionDetail trans:translist){
								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, trans.getTRANSFER_AMOUNT(), charge, cl_bal, today, currentTime, transId, "FAILED",trans.getREQUEST_REFERENCE_NO(),comm,transfertype,checkRemitterMobile,trans.getPAID_AMOUNT(),0.0,"DMR");
								flag=impsTransactiondao.insertImpsTransaction(imptrans);
								}
							}else{
								for(TransactionDetail trans:translist){
								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal,trans.getTRANSFER_AMOUNT(), charge, cl_bal, today, currentTime, transId, "PENDING",trans.getREQUEST_REFERENCE_NO(),comm,transfertype,checkRemitterMobile,trans.getPAID_AMOUNT(),0.0,"DMR");
								flag=impsTransactiondao.insertImpsTransaction(imptrans);
								}
							}
							logger_log.warn("flag:::::::::::::::::"+flag);
							if (flag) {
									
									if (status.equals("1")) {
										status="SUCCESS";
									}
									else if(status.equals("0")){
										status="FAILED";
									}else{
										status="PENDING";
									}
								//}
								System.out.println("status::::::::::::"+status);
								if (status.equals("SUCCESS")) {
									
									double amount1 = amount;
									String dist = "";
									String s_dist = "";
									String reseller = "";
									double s_dis = 0.0;
									double dis = 0.0;
									double reseller_com = 0.0;
									double sd_com = 0.0;
									double d_com = 0.0;
									double ret_com = 0.0;
									if (userDetails.getRoleId() == 5) {
										if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
											distCharge = (pckdis.getCharge()*transamount)/100;
										}else{
											distCharge = pckdis.getCharge();
										}
										if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
											sdCharge = (pcksd.getCharge()*transamount)/100;
										}else{
											sdCharge = pcksd.getCharge();
										}
											if(!resellerId.equals("admin")) {
												if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
												resCharge = (pckres.getCharge()*transamount)/100;
												}else{
												resCharge =	pckres.getCharge();
												}
												
											}
											if(distCharge==0){dcharge=0;}
											else{dcharge = charge - distCharge;}
											System.out.println("dcharge:::"+dcharge);
											if(sdCharge==0){sCharge=0;}
											else{sCharge = distCharge - sdCharge;}
											System.out.println("sCharge:::"+sCharge);
											
											commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "CHARGE", dcharge, "CREDIT",0);
											commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0);
											if(!resellerId.equals("admin")) {
												reCharge = sdCharge - resCharge;
												commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0);
											}
											
											if(dComm==0){disComm=0;}
											else{
											disComm=dComm-retComm;
											}
											if(sdComm==0){sdisComm=0;}
											else{
											sdisComm = sdComm-dComm;
											}
											if(resComm==0){reComm=0;}
											else{
											reComm = resComm-sdComm;
											}								
											commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "COMMISSION", disComm, "CREDIT",0);
											commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0);
											if(!resellerId.equals("admin")) {
												commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0);
											}
											
									} else if (userDetails.getRoleId() == 4) {
										if(!resellerId.equals("admin")) {
										if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
										resCharge = (pckres.getCharge()*transamount)/100;
										}else{
										resCharge =	pckres.getCharge();
										}
										
									}
									if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
										sdCharge = (pcksd.getCharge()*transamount)/100;
									}else{
										sdCharge = pcksd.getCharge();
									}
									if(sdCharge==0){sCharge=0;}
									else{sCharge = charge - sdCharge;}
									System.out.println("sCharge:::"+sCharge);
																							
									commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0);
									if(!resellerId.equals("admin")) {
										reCharge = sdCharge - resCharge;
										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0);
									}
									
									if(sdComm==0){sdisComm=0;}
									else{
									sdisComm=sdComm-dComm;
									}
									if(resComm==0){reComm=0;}
									else{
									reComm = resComm-sdComm;
									}
									
									commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0);
									if(!resellerId.equals("admin")) {
										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0);
									}

									} else if (userDetails.getRoleId() == 3) {
										reseller = userDetails.getUplineId();
										if (!resellerId.equals("admin")) {
											if (pckres.getCharge_type()
													.equalsIgnoreCase("PERCENTAGE"))
												resCharge = (pckres.getCharge() * transamount) / 100;
										} else {
											resCharge = pckres.getCharge();
										}
										resCharge = charge - resCharge;

										if (!resellerId.equals("admin")) {
											reCharge = sdCharge - charge;
											commissionService.updateBalance(resellerId,
													"Money Transfer - " + accountNumber,
													"CHARGE", reCharge, "CREDIT",0);
										}
										
										if(resComm==0){reComm=0;}
										else{
										reComm=resComm-sdComm;
										}
										
									//	System.out.println("COMMISSION for Recharge sdcomm=="+sdisComm);
									//	System.out.println("COMMISSION for Recharge recomm=="+reComm);
										//commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0);
										if(!resellerId.equals("admin")) {
											commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0);
										}
									}
									//impsTransactiondao.updateDmrTransactionBankTransactionId(transId,trans.getRequestRetrievalNumber());
									returnData.put("message", "Transaction Successful :"+FUNDTRANSNO);
									returnData.put("status", "1");

								}
								if (status.equals("FAILED")) {
									commissionService.updateBalance(userDetails.getUserId(),
											"Money Transfer - " + accountNumber, "Money Transfer", totalAmount, "CREDIT",0);
									returnData.put("message", "Transaction Failed ");
									returnData.put("status", "0");
								}if (status.equals("PENDING")) {
									returnData.put("message", "Transaction Successful :"+FUNDTRANSNO);
									returnData.put("status", "1");
								}
								//impsTransactiondao.updateDmrTransactionStatus(transId, status);
							
						}
					}	
						
					}
					}else{
						returnData.put("message",returnData.get("message"));
						returnData.put("status", "0");	
					}

				} else {

					returnData.put("message", "Donot Have Sufficient Balance");
					returnData.put("status", "0");

				}
				} else {

					returnData.put("message", "Donot Have Sufficient Balance");
					returnData.put("status", "0");

				}
			/*}*/
		/*} else {
			returnData.put("message", "Amount should be between 10-5000");
			returnData.put("status", "0");
		}*/
		}else{
			returnData.put("message", "Please Assign Package");
			returnData.put("status", "0");
		}
		/*}else {
			returnData.put("status", "0");
			returnData.put("message", "Operator Is Not Available");
		}*/
		System.out.println();
		}catch (Exception e) {
		logger_log.error("Yesmoneytransfer::::::::::::::"+e);
		}
		return returnData;
		}
	
	
	public Map<String, Object> YesmoneytransferAndroid(Map<String, Object> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		
		double charge = 0.0;
		boolean flag;
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		double apiComm = 0.0;
		double sdCharge = 0.0;
		double distCharge = 0.0;			
		double resCharge = 0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		double disComm = 0.0;
		double sdisComm = 0.0;
		double reComm = 0.0;
		double slab1 = 0.0;
		double slab2 = 0.0;
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
		
		String checkRemitterMobile=request.get("mobile").toString();
		try{
		String beneMobileNumber = request.get("beneMobileNumber").toString();
		String bene_name = request.get("name").toString();
		String transfertype = request.get("transfertype").toString();
		String accountNumber = request.get("account").toString();
		String acc_type = request.get("accountType").toString();
		String beneIFSCCode = request.get("ifsc").toString();
		String beneid = request.get("id").toString();
		String transactionAmount=request.get("amount").toString();
		parameter = new HashMap<String, Object>();
		parameter.put("username",userDetails.getUserName());
		parameter.put("api","YesBank");
		List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",parameter);
		AEPSUserMap aeps = list2.get(0);
		String AID =aeps.getAgentcode();
		String referenceno = "";
		double amount=0.0;
		double totalAmount=0.0;
		double transamount=Double.parseDouble(transactionAmount);
		int id=0;
		parameter = new HashMap<String, Object>();
		parameter.put("user_id", userDetails.getUserId());
		parameter.put("service_type", "DMR");
		List<AssignedPackage> lista = assignedPackage
				.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", parameter);
	/*-------------------------------------------------------------------------------------------------------------------*/
		String tId = "";
		
		if(!lista.isEmpty()){
			
			parameter = new HashMap<String, Object>();
			parameter.put("api", "DMR");
			List<DMRCommission> opList =  DMRCommissionDao.getDMRCommissionByNamedQuery("getDMRCommissionByApi", parameter);
			if((!opList.isEmpty())) {
				double ckbl=0;
				if(transamount>5000){
					ckbl=5000;
				}else{
					ckbl=transamount;
				}
				for(DMRCommission comm2 : opList){
					if(ckbl>=comm2.getSlab1() && ckbl<=comm2.getSlab2()){
						id = comm2.getId();
						break;
					}
				}	
				
				if (userDetails.getRoleId() == 5) {
					pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id) ;
					if (pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pckret.getCharge() * transamount) / 100;
					} else {
						charge = pckret.getCharge();
					}
					
					System.out.println("charge="+charge);
				} else if (userDetails.getRoleId() == 4) {
					pckdis = commissionService.getPackagewiseCommisionOnOperator(
							userDetails.getUserId(),"DMR",id);
					if (pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pckdis.getCharge() * transamount) / 100;
					} else {
						charge = pckdis.getCharge();
					}

				} else if (userDetails.getRoleId() == 3) {
					pcksd = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					
					if (pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pcksd.getCharge() * transamount) / 100;
					} else {
						charge = pcksd.getCharge();
					}
					logger_log.warn("charge:::::"+charge);

				}	else if (userDetails.getRoleId() == 100) {
					pckapiu = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					if (pckapiu.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
						charge = (pckapiu.getCharge() * transamount) / 100;
					} else {
						charge = pckapiu.getCharge();
					}

				}				
			
			/*-------------------------------------COMMISSION---------------------------------------------------------------------*/
				if(userDetails.getRoleId() == 5) {
					//Retailer Id
					//rId=userDetails.getUserId();
					// Distributor Id
					distId = userDetails.getUplineId();		
					
					// Super Distributor Id
					parameters = new HashMap<String, String>();
					parameters.put("userId", distId);	
					sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
					
					// Reseller Id
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
					if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					retComm =(pckret.getComm()*transamount)/100;
					}else{
					retComm =pckret.getComm();	
					}
					comm = retComm;
				//	System.out.println("reseller=="+comm);
					pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"DMR",id); 
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*transamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
				//	System.out.println("dComm=="+dComm);
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					sdComm =(pcksd.getComm()*transamount)/100;
					}else{
					sdComm = pcksd.getComm();	
					}
				//	System.out.println("sdComm=="+sdComm);
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*transamount)/100;
					}else{
					resComm =pckres.getComm();	
					}
				//	System.out.println("resComm=="+resComm);
					
				} else if(userDetails.getRoleId() == 4) {
					//distId = userDetails.getUserId();	
					sdId = userDetails.getUplineId();	
					
					parameters = new HashMap<String, String>();
					parameters.put("userId", sdId);							
					resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
					
					pckdis=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id); 
					if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					dComm =(pckdis.getComm()*transamount)/100;
					}else{
					dComm=pckdis.getComm();	
					}
					comm = dComm;
				//	System.out.println("dComm="+dComm);
					pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"DMR",id);
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					sdComm =(pcksd.getComm()*transamount)/100;
					}else{
					sdComm = pcksd.getComm();	
					}
					sdComm=sdComm-dComm;
					//System.out.println("sdComm="+sdComm);
					if(!resellerId.equals("admin")) {
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"DMR",id);
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*transamount)/100;
					}else{
					resComm =pckres.getComm();	
					}
					resComm=resComm-sdComm;
					}
					//System.out.println("resComm="+resComm);
					
				} else if(userDetails.getRoleId() == 3) {
					resellerId = userDetails.getUplineId();
					pcksd=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				     sdComm =(pcksd.getComm()*transamount)/100;
					}else{
						sdComm = pcksd.getComm();	
					}
					comm=sdComm;
					logger_log.warn("sdComm:::::::::"+sdComm);
					
					/*sdComm = commissionService.getCommisionOnOperator(userDetails.getUplineId(), userDetails.getWlId(), 3, operator.getOperatorId());
					
					resComm = commissionService.getCommisionOnOperator(resellerId, userDetails.getWlId(), 2, operator.getOperatorId());			*/			
				}else if(userDetails.getRoleId() == 100) {
					pckapiu=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
					if(pckapiu.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						apiComm =(pckapiu.getComm()*transamount)/100;
					}else{
						apiComm = pckapiu.getComm();	
					}
					comm = apiComm;
					System.out.println("apiComm:::::::::"+apiComm);
					
					}
		/*---------------------------------------------------------------------------------------------------------------*/
		if (Double.parseDouble(transactionAmount) >= 10 && Double.parseDouble(transactionAmount) <= 20000) {
			
			double temp = Double.parseDouble(transactionAmount);
			double remain = 0.0;
			while (temp > 0) {
				referenceno = GenerateRandomNumber.referenceNO();
				if (temp > 5000) {
					temp = temp - 5000;
					remain = 5000;
				} else {
					remain = temp;
					temp = 0.0;
				}
				parameter = new HashMap<String, Object>();
				parameter.put("IFSC_CODE",beneIFSCCode);
				List<Imps_BankDetails> impslist=impsbankdao.getImpsBankDetailsByNamedQuery("getBankDetailsByIFSC",parameter);
				if(impslist.size()>0){
				Imps_BankDetails impsbank = impslist.get(0);
	
				double lockbalance=userDetails.getLockedAmount();
				totalAmount = RoundNumber.roundDouble((transamount + charge) - comm) ;
				parameters = new HashMap<String, String>();
				parameters.put("userId", userDetails.getUserId());
				double op_bal = customQueryServiceLogic
						.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser,parameters);
				System.out.println("op_bal::::::"+op_bal);
				double cl_bal = RoundNumber.roundDouble(op_bal - totalAmount);
				//System.out.println("cl_bal::::::"+cl_bal);
				if(lockbalance<=cl_bal){
				if (userDetails.getBalance() > totalAmount) {
					String trnsamnt=Double.toString(remain);
					amount = remain;
				//	String input="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\",\"BANK_CITY\":\"\",\"PAYABLE_AMOUNT\":\"100\",\"TXN_AMOUNT\":\"100\",\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"BENE_NAME\":\""+bene_name+"\",\"BANK_BRANCH\":\"\",\"BANKIFSC_CODE\":\""+beneIFSCCode+"\",\"BANK_ADDRESS\":\"\",\"BANK_ACCOUNTNO\":\""+accountNumber+"\",\"BANK_STATE\":\"\",\"BENE_MOBILENO\":\""+beneMobileNumber+"\"}";
					String input="{\"HEADER\":{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"PAYABLE_AMOUNT\":\""+trnsamnt+"\",\"TXN_AMOUNT\":\""+trnsamnt+"\",\"AID\":\""+AID+"\"},\"DATA\":{\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"ORDER_ID\":"+referenceno+",\"KEY_KYC_STATUS\":\"NON-KYC\",\"BENE_BANKNAME\":\""+impsbank.getBank_Name()+"\",\"TRANSFER_TYPE\":\""+transfertype+"\",\"BENE_NAME\":\""+bene_name+"\",\"CN\":\""+beneMobileNumber+"\",\"BANKIFSC_CODE\":\""+beneIFSCCode+"\",\"BANK_ACCOUNTNO\":\""+accountNumber+"\",\"BENE_MOBILENO\":\""+beneMobileNumber+"\",\"BENE_ID\":\""+beneid+"\"}}";
					List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
					YesBankApiToken yestoken = yeslist.get(0);

					
					returnData = YesBankwebservice.yesBankMoneytransfer(yestoken.getToken(),input);
					//returnData = InstantPayWebservice.instantPaymentTransfer(token,checkRemitterMobile,beneid,referenceno,trnsamnt,transfertype);
					if(returnData.get("status").toString().equalsIgnoreCase("1") || returnData.get("status").toString().equalsIgnoreCase("3")){
					String FUNDTRANSNO= returnData.get("ref_no").toString();
					//System.out.println("FUNDTRANSNO::::::::::::" + FUNDTRANSNO);
					String status=(String) returnData.get("status");
					
					//System.out.println("charge::::::::::::" + charge);
				//	totalAmount = charge + amount;
					
					//System.out.println("totalAmount::::::::::::::::::::" + totalAmount);
					parameters = new HashMap<String, String>();
					double adOpBal = customQueryServiceLogic
							.getTotalbalanceForAdmin(CustomSqlQuery.getTotalbalanceForAdmin, parameters);
					RoundNumber.roundDouble(adOpBal - totalAmount);
					
					if (cl_bal > 0) {
						String today = GenerateRandomNumber.getCurrentDate();	
						String currentTime = GenerateRandomNumber.getCurrentTime();
						String transId = GenerateRandomNumber.generateTransactionNumber();
						flag = commissionService.updateBalance(userDetails.getUserId(),
								"Money Transfer - " + accountNumber, "Money Transfer", totalAmount, "DEBIT",0);
						//System.out.println("flag::::::"+flag);
						if (flag) {
							if (status.equals("1")) {
							ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, totalAmount, charge, cl_bal, today, currentTime, transId, "SUCCESS", FUNDTRANSNO,comm,transfertype,checkRemitterMobile,Double.parseDouble(returnData.get("PAID_AMOUNT").toString()),0.0,"DMR");
							flag=impsTransactiondao.insertImpsTransaction(imptrans);
							}else if(status.equals("0")){
								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, totalAmount, charge, cl_bal, today, currentTime, transId, "FAILED", FUNDTRANSNO,comm,transfertype,checkRemitterMobile,Double.parseDouble(returnData.get("PAID_AMOUNT").toString()),0.0,"DMR");
								flag=impsTransactiondao.insertImpsTransaction(imptrans);
							}else{
								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, totalAmount, charge, cl_bal, today, currentTime, transId, "PENDING", referenceno,comm,transfertype,checkRemitterMobile,Double.parseDouble(returnData.get("PAID_AMOUNT").toString()),0.0,"DMR");
								flag=impsTransactiondao.insertImpsTransaction(imptrans);
							}
							logger_log.warn("flag:::::::::::::::::"+flag);
							if (flag) {
									
									if (status.equals("1")) {
										status="SUCCESS";
									}
									else if(status.equals("0")){
										status="FAILED";
									}else{
										status="PENDING";
									}
								//}
								System.out.println("status::::::::::::"+status);
								if (status.equals("SUCCESS")) {
									
									double amount1 = amount;
									String dist = "";
									String s_dist = "";
									String reseller = "";
									double s_dis = 0.0;
									double dis = 0.0;
									double reseller_com = 0.0;
									double sd_com = 0.0;
									double d_com = 0.0;
									double ret_com = 0.0;
									if (userDetails.getRoleId() == 5) {
										if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
											distCharge = (pckdis.getCharge()*transamount)/100;
										}else{
											distCharge = pckdis.getCharge();
										}
										if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
											sdCharge = (pcksd.getCharge()*transamount)/100;
										}else{
											sdCharge = pcksd.getCharge();
										}
											if(!resellerId.equals("admin")) {
												if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
												resCharge = (pckres.getCharge()*transamount)/100;
												}else{
												resCharge =	pckres.getCharge();
												}
												
											}
											if(distCharge==0){dcharge=0;}
											else{dcharge = charge - distCharge;}
											System.out.println("dcharge:::"+dcharge);
											if(sdCharge==0){sCharge=0;}
											else{sCharge = distCharge - sdCharge;}
											System.out.println("sCharge:::"+sCharge);
											
											commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "CHARGE", dcharge, "CREDIT",0);
											commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0);
											if(!resellerId.equals("admin")) {
												reCharge = sdCharge - resCharge;
												commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0);
											}
											
											if(dComm==0){disComm=0;}
											else{
											disComm=dComm-retComm;
											}
											if(sdComm==0){sdisComm=0;}
											else{
											sdisComm = sdComm-dComm;
											}
											if(resComm==0){reComm=0;}
											else{
											reComm = resComm-sdComm;
											}								
											commissionService.updateBalance(distId, "Money Transfer - "+accountNumber, "COMMISSION", disComm, "CREDIT",0);
											commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0);
											if(!resellerId.equals("admin")) {
												commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0);
											}
											
									} else if (userDetails.getRoleId() == 4) {
										if(!resellerId.equals("admin")) {
										if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
										resCharge = (pckres.getCharge()*transamount)/100;
										}else{
										resCharge =	pckres.getCharge();
										}
										
									}
									if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
										sdCharge = (pcksd.getCharge()*transamount)/100;
									}else{
										sdCharge = pcksd.getCharge();
									}
									if(sdCharge==0){sCharge=0;}
									else{sCharge = charge - sdCharge;}
									System.out.println("sCharge:::"+sCharge);
																							
									commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "CHARGE", sCharge, "CREDIT",0);
									if(!resellerId.equals("admin")) {
										reCharge = sdCharge - resCharge;
										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "CHARGE", reCharge, "CREDIT",0);
									}
									
									if(sdComm==0){sdisComm=0;}
									else{
									sdisComm=sdComm-dComm;
									}
									if(resComm==0){reComm=0;}
									else{
									reComm = resComm-sdComm;
									}
									
									commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0);
									if(!resellerId.equals("admin")) {
										commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0);
									}

									} else if (userDetails.getRoleId() == 3) {
										reseller = userDetails.getUplineId();
										if (!resellerId.equals("admin")) {
											if (pckres.getCharge_type()
													.equalsIgnoreCase("PERCENTAGE"))
												resCharge = (pckres.getCharge() * transamount) / 100;
										} else {
											resCharge = pckres.getCharge();
										}
										resCharge = charge - resCharge;

										if (!resellerId.equals("admin")) {
											reCharge = sdCharge - charge;
											commissionService.updateBalance(resellerId,
													"Money Transfer - " + accountNumber,
													"CHARGE", reCharge, "CREDIT",0);
										}
										
										if(resComm==0){reComm=0;}
										else{
										reComm=resComm-sdComm;
										}
										
									//	System.out.println("COMMISSION for Recharge sdcomm=="+sdisComm);
									//	System.out.println("COMMISSION for Recharge recomm=="+reComm);
										//commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0);
										if(!resellerId.equals("admin")) {
											commissionService.updateBalance(resellerId, "Money Transfer - "+accountNumber, "COMMISSION", reComm, "CREDIT",0);
										}
									}
									//impsTransactiondao.updateDmrTransactionBankTransactionId(transId,trans.getRequestRetrievalNumber());
									returnData.put("message", "Transaction Successful :"+FUNDTRANSNO);
									returnData.put("status", "1");

								}
								if (status.equals("FAILED")) {
									commissionService.updateBalance(userDetails.getUserId(),
											"Money Transfer - " + accountNumber, "Money Transfer", totalAmount, "CREDIT",0);
									returnData.put("message", "Transaction Failed ");
									returnData.put("status", "0");
								}if (status.equals("PENDING")) {
									returnData.put("message", "Transaction Successful :"+FUNDTRANSNO);
									returnData.put("status", "1");
								}
								//impsTransactiondao.updateDmrTransactionStatus(transId, status);
							
						}
					}	
						
					}
					}else{
						returnData.put("message",returnData.get("message"));
						returnData.put("status", "0");	
					}

				} else {

					returnData.put("message", "Donot Have Sufficient Balance");
					returnData.put("status", "0");

				}
				} else {

					returnData.put("message", "Donot Have Sufficient Balance");
					returnData.put("status", "0");

				}
				
			}else{


				returnData.put("message", "Invalid bank Details");
				returnData.put("status", "0");

			
			}
			}
		} else {
			returnData.put("message", "Amount should be between 10-5000");
			returnData.put("status", "0");
		}
		}else{
			returnData.put("message", "Please Assign Package");
			returnData.put("status", "0");
		}
		}else {
			returnData.put("status", "0");
			returnData.put("message", "Operator Is Not Available");
		}
		System.out.println();
		}catch (Exception e) {
		logger_log.error("instantPayMoneytransfer::::::::::::::"+e);
		}
		return returnData;
		}

	

	@Override
	public Map<String, Object> yesBankValidateBeneficiary(Map<String, Object> request,User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken=yeslist.get(0);
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			String input="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\",\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"REQUEST_FOR\":\"BENVERIFICATION\"}";
			returnData=YesBankwebservice.yesBankValidateBeneficiary(yestoken.getToken(),input);
			if(returnData.get("status").toString().equalsIgnoreCase("1")){
				returnData.put("id",request.get("id"));	
			}

		} catch (Exception e) {
			logger_log.error("yesBankValidateBeneficiary::::::::::::::" + e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> yesBankVerifyBeneficiary(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken=yeslist.get(0);
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			String input="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\",\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"REQUEST_CODE\":"+request.get("REQUESTNO")+",\"BENE_ID\":"+request.get("id")+",\"OTP\":\""+request.get("OTP").toString()+"\",\"REQUEST_FOR\":\"BENVERIFICATION\"}";
			returnData=YesBankwebservice.yesBankVerifyBeneficiary(yestoken.getToken(),input);
			if(returnData.get("status").toString().equalsIgnoreCase("1")){
				commissionService.updateBalance(user.getUserName(),"Beneficiary Verification - "+request.get("id"),"CHARGE", 1, "CREDIT",0);
				returnData.put("mobile",checkRemitterMobile);	
			}

		} catch (Exception e) {
			logger_log.error("yesBankVerifyBeneficiary::::::::::::::" + e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> verifyYesBaneAndroid(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			String checkRemitterMobile=request.get("mobile").toString();
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken=yeslist.get(0);
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			String input="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\",\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"REQUEST_CODE\":"+request.get("REQUESTNO")+",\"BENE_ID\":"+request.get("id")+",\"OTP\":\""+request.get("OTP").toString()+"\",\"REQUEST_FOR\":\"BENVERIFICATION\"}";
			returnData=YesBankwebservice.yesBankVerifyBeneficiary(yestoken.getToken(),input);
			if(returnData.get("status").toString().equalsIgnoreCase("1")){
				commissionService.updateBalance(user.getUserName(),"Beneficiary Verification - "+request.get("id"),"CHARGE", 1, "CREDIT",0);
				returnData.put("mobile",checkRemitterMobile);	
			}

		} catch (Exception e) {
			logger_log.error("yesBankVerifyBeneficiary::::::::::::::" + e);
		}
		return returnData;
	}

	
	@Override
	public Map<String, Object> remitterverify(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken=yeslist.get(0);
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			
			String input="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\",\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"REQUEST_CODE\":"+request.get("REQUESTNO")+",\"BENE_ID\":"+request.get("id")+",\"OTP\":\""+request.get("OTP").toString()+"\",\"REQUEST_FOR\":\"CUSTVERIFICATION\"}";
			System.out.println("input::::::::::::"+input);
			returnData=YesBankwebservice.yesBankVerifyBeneficiary(yestoken.getToken(),input);
			if(returnData.get("status").toString().equalsIgnoreCase("1")){
				returnData.put("mobile",checkRemitterMobile);	
			}

		} catch (Exception e) {
			logger_log.error("remitterverify::::::::::::::" + e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> remitterverifyAndroid(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			String checkRemitterMobile=request.get("mobile").toString();
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken=yeslist.get(0);
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			String input="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\",\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"REQUEST_CODE\":"+request.get("REQUESTNO")+",\"BENE_ID\":"+request.get("id")+",\"OTP\":\""+request.get("OTP").toString()+"\",\"REQUEST_FOR\":\"CUSTVERIFICATION\"}";
			
			System.out.println("input::::::::::::::::::"+input);
			returnData=YesBankwebservice.yesBankVerifyBeneficiary(yestoken.getToken(),input);
			if(returnData.get("status").toString().equalsIgnoreCase("1")){
				returnData.put("remitter",checkRemitterMobile);	
			}else{
			input="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\",\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"REQUEST_FOR\":\"CUSTVERIFICATION\"}";
				returnData=YesBankwebservice.verifyremitter(yestoken.getToken(),input);
				if(returnData.get("status").toString().equalsIgnoreCase("1")){
					int id =0;
					returnData.put("id",id);	
					returnData.put("status","2");
					returnData.put("remitter",checkRemitterMobile);
				}
	
			}

		} catch (Exception e) {
			logger_log.error("remitterverifyAndroid::::::::::::::" + e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> deleteyesBankbene(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken=yeslist.get(0);
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			String input="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\",\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"REQUEST_FOR\":\"BENDELETE\"}";
			returnData=YesBankwebservice.yesBankValidateBeneficiary(yestoken.getToken(),input);
			if(returnData.get("status").toString().equalsIgnoreCase("1")){
				returnData.put("id",request.get("id"));	
				returnData.put("mobile",checkRemitterMobile);
			}

		} catch (Exception e) {
			logger_log.error("deleteyesBankbene::::::::::::::" + e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> deleteyesBankbeneAndroid(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			String checkRemitterMobile=request.get("mobile").toString();
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken=yeslist.get(0);
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			String input="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\",\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"REQUEST_FOR\":\"BENDELETE\"}";
			returnData=YesBankwebservice.yesBankValidateBeneficiary(yestoken.getToken(),input);
			if(returnData.get("status").toString().equalsIgnoreCase("1")){
				returnData.put("id",request.get("id"));	
				returnData.put("mobile",checkRemitterMobile);
			}

		} catch (Exception e) {
			logger_log.error("deleteyesBankbeneAndroid::::::::::::::" + e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> VerifyDeleteYesBane(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken=yeslist.get(0);
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			String input="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\",\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"REQUEST_CODE\":"+request.get("REQUESTNO")+",\"BENE_ID\":"+request.get("id")+",\"OTP\":\""+request.get("OTP").toString()+"\"}";
			returnData=YesBankwebservice.VerifyDeleteYesBane(yestoken.getToken(),input);
			if(returnData.get("status").toString().equalsIgnoreCase("1")){
				returnData.put("id",request.get("id"));	
				returnData.put("mobile",checkRemitterMobile);
			}

		} catch (Exception e) {
			logger_log.error("yesBankValidateBeneficiary::::::::::::::" + e);
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> VerifyDeleteYesBaneAndroid(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			String checkRemitterMobile=request.get("mobile").toString();
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken=yeslist.get(0);
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			String input="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\",\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"REQUEST_CODE\":"+request.get("REQUESTNO")+",\"BENE_ID\":"+request.get("id")+",\"OTP\":\""+request.get("OTP").toString()+"\"}";
			returnData=YesBankwebservice.VerifyDeleteYesBane(yestoken.getToken(),input);
			if(returnData.get("status").toString().equalsIgnoreCase("1")){
				returnData.put("id",request.get("id"));	
				returnData.put("mobile",checkRemitterMobile);
			}

		} catch (Exception e) {
			logger_log.error("yesBankValidateBeneficiary::::::::::::::" + e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> verifyremitter(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken=yeslist.get(0);
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			String input="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\",\"CUSTOMER_MOBILE\":\""+checkRemitterMobile+"\",\"REQUEST_FOR\":\"CUSTVERIFICATION\"}";
			returnData=YesBankwebservice.verifyremitter(yestoken.getToken(),input);
			if(returnData.get("status").toString().equalsIgnoreCase("1")){
				returnData.put("id",request.get("id"));	
				returnData.put("mobile",checkRemitterMobile);
			}

		} catch (Exception e) {
			logger_log.error("verifyremitter::::::::::::::" + e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> yesBankpidAndroid(Map<String, String> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();

		try {
			param.put("username", user.getUserName());
			param.put("api", "YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi", param);
			AEPSUserMap aeps = list2.get(0);
			String AID = aeps.getAgentcode();
			String mobile = request.get("mobile");
			String deviceType = request.get("deviceType");

			List<YesBankApiToken> yeslist = yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken = yeslist.get(0);
			String input = "{\"HEADER\":{\"ST\":\"BALANCEINFO\",\"AID\":\"" + AID
					+ "\",\"OP\":\"AEPS\"},\"DATA\":{\"CUSTOMER_MOBILE\":\"" + mobile + "\",\"DEVICE\":\"" + deviceType
					+ "\"}}}";
			returnData = YesBankwebservice.getDevicePID(yestoken.getToken(), input);

		} catch (Exception e) {
			logger_log.error("getDeviceByDeviceType::::::::::::::::::::" + e);
		}

		return returnData;
	}

	@Override
	public Map<String, Object> checkImpsStatus(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean  flag = false;
		String token="";
        System.out.println("request::::::::::::::::::"+request);
		try {
			param.put("username", user.getUserName());
			param.put("api", "YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi", param);
			AEPSUserMap aeps = list2.get(0);
			String AID = aeps.getAgentcode();
			String mobile = request.get("remmobile").toString();

			List<YesBankApiToken> yeslist = yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken = yeslist.get(0);
			String input = "{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\"" + AID
					+ "\",\"CUSTOMER_MOBILE\":\"" + mobile + "\",\"REQUEST_REFERENCE_NO\":\"" + request.get("banktransactionId").toString()
					+ "\"}";
			returnData = YesBankwebservice.checkImpsStatus(yestoken.getToken(),input);
			System.out.println(returnData);
			if(returnData.containsKey("error")){
				
				if(returnData.get("error").toString().equalsIgnoreCase("invalid_token")){
					  token=YesBankwebservice.sendRequestToclientcredential()	;
						yeslist=yesbanktokendao.getYesBankApiToken();
						yestoken = yeslist.get(0);
						yestoken.setToken(token);
						flag =yesbanktokendao.updateYesBankApiToken(yestoken);
						if(flag){
							
							
							input = "{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\"" + AID
									+ "\",\"CUSTOMER_MOBILE\":\"" + mobile + "\",\"REQUEST_REFERENCE_NO\":\"" + request.get("banktransactionId").toString()
									+ "\"}";
							returnData = new HashMap<String, Object>();
							returnData = YesBankwebservice.checkImpsStatus(yestoken.getToken(),input);	
						}
					
				}
				
			}else if(returnData.get("status").toString().equalsIgnoreCase("SUCCESS")){
				
			    
			  
				/*if(flag){*/
				
				ImpsTransaction imps = new ImpsTransaction(user.getUserName(),request.get("account_no").toString(),request.get("bene_name").toString() ,request.get("bene_mobile").toString(),Double.parseDouble(request.get("op_bal").toString()),Double.parseDouble(request.get("amount").toString()) ,Double.parseDouble(request.get("charge").toString()) ,Double.parseDouble(request.get("cl_bal").toString()),request.get("date").toString(),request.get("time").toString(),request.get("recieptId").toString(),"SUCCESS",returnData.get("BANK_REFERENCE_NO").toString(),0.0,request.get("usermobile").toString(),request.get("remmobile").toString(),Double.parseDouble(returnData.get("PAID_AMOUNT").toString()),0.0,"DMR");
				
				System.out.println("imps:::::::::::::::::::::::"+imps);
				imps.setId(Integer.parseInt(request.get("id").toString()));
				flag=impsTransactiondao.updateImpsTransaction(imps);
				if(flag){
					returnData = new HashMap<String, Object>();
					returnData.put("nextPage","IMPS2");	
					returnData.put("message","Success");	
					returnData.put("status","1");	
				}
			}else if(returnData.get("status").toString().equalsIgnoreCase("FAILED")){
				
			}else{
				returnData = new HashMap<String, Object>();
				returnData.put("nextPage","IMPS2");	
				returnData.put("message","PENDING");	
				returnData.put("status","1");	
			}

		} catch (Exception e) {
			logger_log.error("checkImpsStatus::::::::::::::::::::" + e);
		}

		return returnData;
	}
	
	
	@Override
	public Map<String, Object> REFUNDImpsStatus(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean  flag = false;
		String token="";
        System.out.println("request::::::::::::::::::"+request);
		try {
			param.put("username", user.getUserName());
			param.put("api", "YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi", param);
			AEPSUserMap aeps = list2.get(0);
			String AID = aeps.getAgentcode();
			String mobile = request.get("remmobile").toString();

			List<YesBankApiToken> yeslist = yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken = yeslist.get(0);
			String input = "{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\"" + AID
					+ "\",\"CUSTOMER_MOBILE\":\"" + mobile + "\",\"REQUEST_FOR\":\"CUSTREFUND\"}";
			
			returnData = YesBankwebservice.yesBankValidateBeneficiary(yestoken.getToken(),input);
			System.out.println(returnData);
			if(returnData.containsKey("error")){
				
				if(returnData.get("error").toString().equalsIgnoreCase("invalid_token")){
					  token=YesBankwebservice.sendRequestToclientcredential()	;
						yeslist=yesbanktokendao.getYesBankApiToken();
						yestoken = yeslist.get(0);
						yestoken.setToken(token);
						flag =yesbanktokendao.updateYesBankApiToken(yestoken);
						if(flag){
							
							
							input ="{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\"" + AID
									+ "\",\"CUSTOMER_MOBILE\":\"" + mobile + "\",\"REQUEST_FOR\":\"CUSTREFUND\"}";
							
							returnData = new HashMap<String, Object>();
							returnData = YesBankwebservice.yesBankValidateBeneficiary(yestoken.getToken(),input);	
						}
					
				}
				
			}
				
				returnData.put("nextPage","IMPS2");	
				} catch (Exception e) {
			logger_log.error("REFUNDImpsStatus::::::::::::::::::::" + e);
		}

		return returnData;
	}
	
	
	
	@Override
	public Map<String, Object> refundOTP(Map<String, Object> request, User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean  flag = false;
		String token="";
        System.out.println("request::::::::::::::::::"+request);
		try {
			param.put("username", user.getUserName());
			param.put("api", "YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi", param);
			AEPSUserMap aeps = list2.get(0);
			String AID = aeps.getAgentcode();
			String mobile = request.get("remmobile").toString();

			List<YesBankApiToken> yeslist = yesbanktokendao.getYesBankApiToken();
			YesBankApiToken yestoken = yeslist.get(0);
			String input = "{\"HEADER\":{\"OP\":\"DMTNUR\",\"ST\":\"REMDOMESTIC\",\"AID\":\""+AID+"\"},\"DATA\":{\"CUSTOMER_MOBILE\":\""+mobile+"\",\"REQUEST_REFERENCE_NO\":\""+request.get("banktransactionId").toString()+"\",\"TRANSFER_AMOUNT\":"+request.get("amount").toString()+",\"PAID_AMOUNT\":"+request.get("paidamnt").toString()+",\"RESPONSE\":\"REFUNDED\",\"OTP\":"+request.get("OTP").toString()+",\"REQUEST_CODE\":"+request.get("REQUESTNO").toString()+"}}";
			
			returnData = YesBankwebservice.refundOTP(yestoken.getToken(),input);
			if(returnData.get("status").toString().equalsIgnoreCase("1")){
				param = new HashMap<String, Object>();	
				param.put("banktransactionId",request.get("banktransactionId").toString());
				List<ImpsTransaction> list=impsTransactiondao.getIMPSDetailsByNamedQuery("getImpsTransactionBybanktransactionId",param);
				ImpsTransaction imps = list.get(0);
				imps.setStatus("Refunded");
				flag = impsTransactiondao.updateImpsTransaction(imps);
				if(flag){
					commissionService.updateBalance(imps.getUsername(), "Money Transfer FAILED- "+request.get("banktransactionId").toString(), "REFUND", imps.getAmount()+imps.getCharge(), "CREDIT",0);	
				}
			}
			System.out.println(returnData);
			
				
				returnData.put("nextPage","IMPS2");	
				} catch (Exception e) {
			logger_log.error("refundOTP::::::::::::::::::::" + e);
		}

		return returnData;
	}

	
	
	@Override
	public String aepstransaction(Map<String, Object> request,User user) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag =false;
		YesBankApiToken yestoken = null;
		
		String input="";
		String url="";
		try {
			System.out.println(request);
			param.put("username",user.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			if(!list2.isEmpty()){
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			String mobile = request.get("mobile").toString();
			/*param.put("mobile",mobile);
			List<YesbankCustomer> list=yesbankcustomerdao.getYesbankCustomerByNamedQuery("getyesbankcustomerbyMobile",param);
			if(!list.isEmpty()){
				YesbankCustomer yesbankcustomer = list.get(0);
				returnData.put("yesbankcustomer",yesbankcustomer);
				returnData.put("status","1");
			}else{*/
				String TXN_AMOUNT="";
				String ORDER_ID="";
				String processingCode="";
				String ST=request.get("mode").toString();
				Date date= new Date();
				 
				 long time = date.getTime();
				     System.out.println("Time in Milliseconds: " + time);
				if(ST.equalsIgnoreCase("BALANCEINFO")){
					TXN_AMOUNT="0";
					if(request.get("servicetype").equals("AEPS")){
						processingCode="010";
						ORDER_ID= "AB"+time+GenerateRandomNumber.referenceNO().substring(1, 4);
					}else if(request.get("servicetype").equals("FINO")){
						processingCode="002";
						ORDER_ID= "AB"+time+GenerateRandomNumber.referenceNO().substring(1, 4);
					}else{
						processingCode="004";
						ORDER_ID= "MAB"+time+GenerateRandomNumber.referenceNO().substring(1, 4);
					}
					
					
					}else if(ST.equalsIgnoreCase("WITHDRAWAL")){
					TXN_AMOUNT=request.get("amount").toString();
					if(request.get("servicetype").equals("AEPS")){
						processingCode="011";
						ORDER_ID="AW"+time+GenerateRandomNumber.referenceNO().substring(1, 4);
					}else if(request.get("servicetype").equals("FINO")){
						processingCode="001";
						ORDER_ID="AW"+time+GenerateRandomNumber.referenceNO().substring(1, 4);
					}else{
						processingCode="003";
						ORDER_ID="MAW"+time+GenerateRandomNumber.referenceNO().substring(1, 4);
					}
					
					}
				
				request.put("cpCode", cpCode);
				request.put("timestap", time);
				request.put("ORDER_ID", ORDER_ID);
				logger_log.warn("ORDER_ID::"+ORDER_ID);
				logger_log.warn("time::"+time);
				String checksum=GenerateCheckSum.generateChecksumyesbank(request,AID);
				//String checksum=GenerateCheckSum.generateChecksumyesbankAnd(request,AID);
				AEPSLog aepslog=new AEPSLog(ORDER_ID, AID, "PENDING", ST, user.getUserId(), GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime(),processingCode,mobile,"0",Double.parseDouble(TXN_AMOUNT));
				flag=aepslogdao.insertAEPSLog(aepslog);
				// url="http://uat5yesmoney.easypay.co.in:5050/epyesbc/agent/login/fino?agentCode="+agentCode+"&cpCode="+cpCode+"&sscode="+time+"&amount="+TXN_AMOUNT+"&mobileNo="+mobile+"&st=010&orderId="+ORDER_ID+"&cs="+checksum;
			//	System.out.println("url::::"+url);
				session.setAttribute("ORDER_ID", ORDER_ID);
				session.setAttribute("checksum", checksum);
				session.setAttribute("time", String.valueOf(time));
				session.setAttribute("TXN_AMOUNT", TXN_AMOUNT);
				session.setAttribute("processingCode", processingCode);
				session.setAttribute("mobile", mobile);
				session.setAttribute("agentCode", AID);
				session.setAttribute("cpCode", cpCode);
			//}
				if(flag){
					url="1";
				}else{
					url="0";
				}
				
			return url;
			}else{
				url="0";
			}
		} catch (Exception e) {
			logger_log.error("searchCustomer::::::::::::::::::::" + e);
		}

		return url;
	}

	
	
	@Override
	public Map<String, Object> aepsstatuscheck(Map<String, Object> request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		boolean flag = false;
		String status  = "";
		String token="";
		YesBankApiToken yestoken = null;
		try {
			int id=Integer.parseInt((String) request.get("id"));
			//System.out.println(request);
			//param.put("referenceno", request.get("referenceno").toString());
			AEPSLog AEPSLog=aepslogdao.getAEPSLogById(id);
					//getAEPSLogByNamedQuery("getAEPSLogByrefno", param);
			//AEPSLog AEPSLog=paylist.get(0);
			List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();
			if(!yeslist.isEmpty()){
				yestoken = yeslist.get(0);
				token =yestoken.getToken();
				System.out.println(token);
			
			
				
			}else{
				token=YesBankwebservice.sendRequestToclientcredentialNew()	;
				//System.out.println("token::::"+token);
			yestoken= new YesBankApiToken();
			yestoken.setId(1);
			yestoken.setToken(token);
			flag =yesbanktokendao.updateYesBankApiToken(yestoken);
			}
			param = new HashMap<String, Object>();
			param.put("username",AEPSLog.getUserId());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			String AID =aeps.getAgentcode();
			
			String result = YesBankwebservice.aepsstatuscheck(token,AEPSLog,cpCode,AID);
			JSONObject obj=new JSONObject(result);
			if(obj.has("error")){
				if(obj.get("error").equals("invalid_token")){
					token=YesBankwebservice.sendRequestToclientcredentialNew()	;
					System.out.println("token::::"+token);
				yestoken= new YesBankApiToken();
				yestoken.setId(1);
				yestoken.setToken(token);
				flag =yesbanktokendao.updateYesBankApiToken(yestoken);
				 result = YesBankwebservice.aepsstatuscheck(token,AEPSLog,cpCode,AID);
				 JSONObject obj1=new JSONObject(result);
				 if(obj1.has("RESP_CODE")){
						System.out.println(obj1.get("RESP_CODE"));
						System.out.println(obj1.get("RESPONSE"));
						if(obj1.get("RESP_CODE").equals("203")){
							returnData.put("status", "0");
							returnData.put("message",obj1.get("RESPONSE"));
						}else if(obj1.get("RESP_CODE").equals("200")){
							/*returnData.put("status", "1");
							returnData.put("message","Update Successfully.");*/
							returnData=aepsstatusupdate(result);
							
						}
					}else{
						returnData.put("status", "0");
						returnData.put("message","FAILED");
					}
				}else{
					returnData.put("status", "0");
					returnData.put("message","FAILED");
				}
			}
			else{
				if(obj.has("RESP_CODE")){
					System.out.println(obj.get("RESP_CODE"));
					System.out.println(obj.get("RESPONSE"));
					if(obj.get("RESP_CODE").equals("203")){
						returnData.put("status", "0");
						returnData.put("message",obj.get("RESPONSE"));
					}else if(obj.get("RESP_CODE").equals("200")){
						/*returnData.put("status", "1");
						returnData.put("message","Update Successfully.");*/
						returnData=aepsstatusupdate(result);
						
					}else{
						returnData.put("status", "0");
						returnData.put("message",obj.get("RESPONSE"));
					}
				}else{
					returnData.put("status", "0");
					returnData.put("message","FAILED");
				}
				
			}
			
			
		/*	if(obj.get("RESP_CODE").equals("203")){
				returnData.put("status", "0");
				returnData.put("message",obj.get("RESPONSE"));
			}else if(obj.get("RESP_CODE").equals("200")){
				JsonObject data=(JsonObject) obj.get("DATA");
				if(data.get("TRANSACTION_STATUS").equals("FAILED")){
					AEPSLog.setStatus("FAILED");
					AEPSLog.setRemark(data.get("TRANSACTION_STATUSMESSAGE").toString());
				}else{
					AEPSLog.setStatus("SUCCESS");
				}
				aepslogdao.updateAEPSLog(AEPSLog);

				returnData.put("status", "1");
				returnData.put("message","Update Successfully.");
			
			}else{
				returnData.put("status", "0");
				returnData.put("message",obj.get("RESPONSE"));
			}*/
			
		} catch (Exception e) {
			logger_log.error("aepsstatuscheck::::::::::::::::::::" + e);
			returnData.put("status", "0");
			returnData.put("message","FAILED");
			returnData.put("nextPage","home");
		}

		return returnData;
	}
	
	public Map<String,Object> aepsstatusupdate(String result){
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		Map<String, String> parameters = new HashMap<String, String>();
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		SettlementReport settle2=null;
		int id=0;
		double settlementamountprev = 0.0;
		double settlementamountnew = 0.0;
		JSONObject obj=new JSONObject(result);
		JSONObject dataa=obj.getJSONObject("DATA");
		param = new HashMap<String, Object>();
		param.put("referenceno", dataa.get("REQUEST_REFERENCE_NO"));
		param.put("mobile", dataa.get("CUSTOMER_MOBILE"));
		List<AEPSLog> listran=aepslogdao.getAEPSLogByNamedQuery("getAEPSLogByrefnomobile", param);
		if(!listran.isEmpty()){
			AEPSLog AEPSLo=listran.get(0);
			AEPSLo.setStatus((String)dataa.get("TRANSACTION_STATUS"));
			AEPSLo.setRemark((String)dataa.get("TRANSACTION_STATUSMESSAGE"));
			aepslogdao.updateAEPSLog(AEPSLo);
			double txnamount=0.0;
			if(dataa.get("TXN_AMOUNT")  instanceof String){
				String txamnt=(String) dataa.get("TXN_AMOUNT");
				txnamount=Double.parseDouble(txamnt);
			}if(dataa.get("TXN_AMOUNT")  instanceof Double){
				Double txamnt=(Double) dataa.get("TXN_AMOUNT");
				txnamount=txamnt;
			}if(dataa.get("TXN_AMOUNT")  instanceof Integer){
				Integer txamnt=(Integer) dataa.get("TXN_AMOUNT");
				txnamount=(double)txamnt;
			}
			YesBankAEPSResponse yesr=new YesBankAEPSResponse("0", dataa.get("BANK_REFERENCE_NO").toString(), "-", "0", Double.toString(txnamount), obj.get("RESP_CODE").toString(), "-", "-", "INR", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", dataa.get("TRANSACTION_STATUS").toString(), dataa.get("REQUEST_REFERENCE_NO").toString(), 0.0,txnamount, GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(), AEPSLo.getAgentcode(), AEPSLo.getUserId(),AEPSLo.getProcessingCode(),dataa.get("TRANSACTION_STATUSMESSAGE").toString());
			yesbankresponseDao.insertYesBankAEPSResponse(yesr);
		if(dataa.get("TRANSACTION_STATUS").equals("SUCCESS")){
				if(AEPSLo.getType().equals("WITHDRAWAL")){
					double settlementamount=txnamount ;
					param = new HashMap<String, Object>();
					param.put("username",AEPSLo.getUserId());
					List<SettlementBalance> settlelist = settlementbalancedao.getSettlementBalanceByNamedQuery("getsettlementbyUsername",param); 
					if(settlelist.isEmpty()){
						SettlementBalance SettlementBalance = new SettlementBalance(AEPSLo.getUserId(),settlementamount);
						settlementbalancedao.insertSettlementBalance(SettlementBalance);	
						settle2 = new SettlementReport(AEPSLo.getUserId(),0.0,settlementamount, settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
					}else{
						SettlementBalance SettlementBalance = settlelist.get(0);
						settlementamountprev = SettlementBalance.getSettlementbalance();
						settlementamountnew = settlementamountprev+settlementamount;
						SettlementBalance.setSettlementbalance(settlementamountnew);
						settlementbalancedao.updateSettlementBalance(SettlementBalance);
						settle2 = new SettlementReport(AEPSLo.getUserId(),settlementamountprev,settlementamountnew,settlementamount,GenerateRandomNumber.getCurrentDate(), GenerateRandomNumber.getCurrentTime(),"TRANSACTION","SUCCESS","SUCCESS");
					}
					settlementreportdao.insertSettlementReport(settle2);
					param = new HashMap<String, Object>();
					param.put("api","YesBank");
					
					List<AEPSCommission> aepscommlist = aepscommissiondao.getAEPSCommissionByNamedQuery("getAEPSCommissionByApi",param);
					
					for(AEPSCommission comm2 : aepscommlist){
						if(settlementamount>=comm2.getSlab1() && settlementamount<=comm2.getSlab2()){
							id = comm2.getId();
							break;
						}
					}
					param = new HashMap<String, Object>();
					User user=userDao.getUserByUserId(AEPSLo.getUserId());
					if(user.getRoleId() == 5) {
						pckret = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"YesBank AEPS",id) ;
						//Retailer Id
						//rId=userDetails.getUserId();
						// Distributor Id
						distId = user.getUplineId();		
						
						// Super Distributor Id
						parameters = new HashMap<String, String>();
						parameters.put("userId", distId);	
						sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
						
						// Reseller Id
						parameters = new HashMap<String, String>();
						parameters.put("userId", sdId);							
						resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
						if(pckret.getPackage_id()!=null){
						if(pckret.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						retComm =(pckret.getComm()*settlementamount)/100;
						}else{
						retComm =pckret.getComm();	
						}
						comm = retComm;
						}
						System.out.println("comm:::::::::::::::::::::::::::::"+comm);
					//	System.out.println("reseller=="+comm);
						pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"YesBank AEPS",id); 
						if(pckdis.getPackage_id()!=null){
						if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						dComm =(pckdis.getComm()*settlementamount)/100;
						}else{
						dComm=pckdis.getComm();	
						}
						dComm=dComm-comm;
						}
						System.out.println("dComm:::::::::::::::::::::::::::::"+dComm);
					//	System.out.println("dComm=="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"YesBank AEPS",id);
						if(pcksd.getPackage_id()!=null){
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*settlementamount)/100;
						}else{
						sdComm = pcksd.getComm();	
						}
						sdComm=sdComm-dComm;
						}
						
					//	System.out.println("sdComm=="+sdComm);
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"YesBank AEPS",id);
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
					//	System.out.println("resComm=="+resComm);
						commissionService.updateBalance(user.getUserId(), "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
						commissionService.updateBalance(distId, "AEPS Commission For Amount - "+settlementamount, "Commission", dComm, "CREDIT",0);
						commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT",0);
						
					}else if(user.getRoleId() == 4) {

						//distId = userDetails.getUserId();	
						sdId = user.getUplineId();	
						
						parameters = new HashMap<String, String>();
						parameters.put("userId", sdId);							
						resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
						
						pckdis=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"YesBank AEPS",id); 
						if(pckdis.getPackage_id()!=null){
						if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						dComm =(pckdis.getComm()*settlementamount)/100;
						}else{
						dComm=pckdis.getComm();	
						}
						}
						
						comm = dComm;
						
					//	System.out.println("dComm="+dComm);
						pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"YesBank AEPS",id);
						System.out.println("pcksd:::::::::::::::::"+pcksd);
						if(pcksd.getPackage_id()!=null){
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						sdComm =(pcksd.getComm()*settlementamount)/100;
						}else{
						sdComm = pcksd.getComm();	
						}
						sdComm=sdComm-dComm;
						}
						
						//System.out.println("sdComm="+sdComm);
						if(!resellerId.equals("admin")) {
						pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"YesBank AEPS",id);
						if(pckres.getPackage_id()!=null){
						if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
						resComm =(pckres.getComm()*settlementamount)/100;
						}else{
						resComm =pckres.getComm();	
						}
						resComm=resComm-sdComm;
						}
						commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0);
						}
						//System.out.println("resComm="+resComm);
						
						commissionService.updateBalance(user.getUserId(), "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
						commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT",0);
						
					}else if(user.getRoleId() == 3) {

						resellerId = user.getUplineId();
						pcksd=commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"YesBank AEPS",id);
						if(pcksd.getPackage_id()!=null){
						if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					     sdComm =(pcksd.getComm()*settlementamount)/100;
						}else{
							sdComm = pcksd.getComm();	
						}
						}
						System.out.println("sdComm:::::::::"+sdComm);
						if(!resellerId.equals("admin")) {
							pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"YesBank AEPS",id);
							if(pckres.getPackage_id()!=null){
							if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
							resComm =(pckres.getComm()*settlementamount)/100;
							}else{
							resComm =pckres.getComm();	
							}
							resComm=resComm-sdComm;
							}
							commissionService.updateBalance(resellerId, "AEPS Commission For Amount - "+settlementamount, "Commission", resComm, "CREDIT",0);
							}
						commissionService.updateBalance(user.getUserId(), "AEPS Commission For Amount - "+settlementamount, "Commission", comm, "CREDIT",0);
						commissionService.updateBalance(sdId, "AEPS Commission For Amount - "+settlementamount, "Commission", sdComm, "CREDIT",0); 
					
					}
				}
				
				}
			returnData.put("status", "1");
			returnData.put("message", "Update Successfully");
			}
		
		
		return returnData;
	}

	@Override
	public Map<String, Object> miniStatementAndroid(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		YesBankApiToken yestoken = null;
		Map<String, Object> param = new HashMap<String, Object>();
		String token = "";
		String input="";
		String AID ="";
		String checksum="";
		boolean flag=false;
		
		try {
		List<YesBankApiToken> yeslist=yesbanktokendao.getYesBankApiToken();	
		
		if(!yeslist.isEmpty()){
			yestoken = yeslist.get(0);
			token =yestoken.getToken();
			param = new HashMap<String, Object>();
			param.put("username",userDetails.getUserName());
			param.put("api","YesBank");
			List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param);
			AEPSUserMap aeps = list2.get(0);
			AID =aeps.getAgentcode();
			checksum=GenerateCheckSum.generateChecksumMinistate(request,AID);
			System.out.println("Payel:::::::::::::::::::::::::");
			input="{\"HEADER\": {\"ST\": \"MINISTATEMENT\",\"AID\": \""+AID+"\",\"TXN_AMOUNT\":0,\"OP\": \"ICICIAEPS\"},\"DATA\": {\"ORDER_ID\": \""+request.get("ORDER_ID")+"\",\"IIN\":\""+request.get("IIN")+"\",\"AadharNumber\":\""+request.get("aadhar")+"\",\"isAgree\":\"true\",\"mobileNumber\":\""+request.get("mobile")+"\",\"BiometricData\":\""+StringEscapeUtils.escapeJava(request.get("pidData").toString())+"\"}}";	
			returnData = YesBankwebservice.miniSettlement(token,input,checksum);
			if(returnData.containsKey("error_description")){
			String description = returnData.get("error").toString();
			if(description.contains("invalid_token")){
			token=YesBankwebservice.sendRequestToclientcredential();
			yestoken.setToken(token);
			flag =yesbanktokendao.updateYesBankApiToken(yestoken);	
			if(flag){
			input="{\"HEADER\": {\"ST\": \"MINISTATEMENT\",\"AID\": \""+AID+"\",\"TXN_AMOUNT\":0,\"OP\": \"ICICIAEPS\"},\"DATA\": {\"ORDER_ID\": \""+request.get("ORDER_ID")+"\",\"IIN\":\""+request.get("IIN")+"\",\"AadharNumber\":\""+request.get("AadharNumber")+"\",\"isAgree\":\"true\",\"mobileNumber\":\""+request.get("mobile")+"\",\"BiometricData\":\""+StringEscapeUtils.escapeJava(request.get("pidData").toString())+"\"}}";	
			returnData = YesBankwebservice.miniSettlement(token,input,checksum);	
			}
			}
			}
		}
		}catch (Exception e) {
		logger_log.warn("miniSettlementAndroid:::::::::::::::::::::::"+e);
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getMicroATMReportNew(Map<String, String> request, User userDetail) {
		Map<String, Object> returndata = new HashMap<String, Object>();	
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		try {
			if (userDetail.getRoleId() > 2) {
				param = new HashMap<String, Object>();
				param.put("username", userDetail.getUserName());
				param.put("start_date", request.get("startDate"));
				param.put("end_date", request.get("endDate"));
				List<MicroAtmResponseNew> rblreport = microatmresponsenew
						.getMicroAtmResponseNewByNamedQuery("getMicroAtmResponseNewByusername", param);
				if (!rblreport.isEmpty()) {
					returndata.put("status", "1");
					returndata.put("microReport", rblreport);
				} else {
					returndata.put("status", "0");
					returndata.put("message", "No data available");
				}

			} else if (userDetail.getRoleId() == 1) {
				parameters.put("start_date", request.get("startDate"));
				parameters.put("end_date", request.get("endDate"));
				List<MicroAtmResponseNew> rblreport = customQueryServiceLogic
						.getMicroATMReportnew(CustomSqlQuery.getMicroAepsReportNewAll, parameters);
				if (!rblreport.isEmpty()) {
					returndata.put("status", "1");
					returndata.put("microReport", rblreport);
				} else {
					returndata.put("status", "0");
					returndata.put("message", "No data available");
				}
			} else if (userDetail.getRoleId() == 2) {
				parameters.put("wl_id", userDetail.getWlId());
				parameters.put("start_date", request.get("startDate"));
				parameters.put("end_date", request.get("endDate"));
				List<MicroAtmResponseNew> rblreport = customQueryServiceLogic
						.getMicroATMReportnew(CustomSqlQuery.getMicroAepsReportNewAllwl, parameters);
				if (!rblreport.isEmpty()) {
					returndata.put("status", "1");
					returndata.put("microReport", rblreport);
				} else {
					returndata.put("status", "0");
					returndata.put("message", "No data available");
				}

			}
		}catch (Exception e) {
			logger_log.error("getMicroATMReport:::::::::::::::::::::"+e);
			returndata.put("message", e.getMessage());
		}
		return returndata;
	}

	

}
