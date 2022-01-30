package com.recharge.Imps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.businessdao.CommissionService;
import com.recharge.businessdao.CustomQueryServiceLogic;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.model.AssignedPackage;
import com.recharge.model.DMRCommission;
import com.recharge.model.Ifsccode;
import com.recharge.model.ImpsTransaction;
import com.recharge.model.Imps_BankDetails;
import com.recharge.model.Operator;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.User;
import com.recharge.servicedao.AssignedPackageDAO;
import com.recharge.servicedao.DMRCommissionDao;
import com.recharge.servicedao.IfsccodeDao;
import com.recharge.servicedao.ImpsBankDao;
import com.recharge.servicedao.ImpsTransactionService;
import com.recharge.servicedao.ImpsverificationDao;
import com.recharge.servicedao.OperatorDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.RoundNumber;
import com.recharge.utill.UtilityClass;
import com.skypoint.moneyapi.BankDetails;
import com.skypoint.moneyapi.BeneficiaryDetails;
import com.skypoint.moneyapi.BeneficiaryListResult;
import com.skypoint.moneyapi.ConsumerDetailResult;
import com.skypoint.moneyapi.GetCustomerStatusResult;
import com.skypoint.moneyapi.GetKYCStatusAndLimitResult;
import com.skypoint.moneyapi.GetTransactionStatusResult;
import com.skypoint.moneyapi.RegisterBeneficiaryResult;
import com.skypoint.moneyapi.RegisterCustomerResult;
import com.skypoint.moneyapi.SubmitPaymentDetailsResult;
import com.skypoint.moneyapi.UpdateStatus;

@Service("impsDao")
public class ImpsDaoImpl implements ImpsDao {
	private static final Logger logger_log = Logger.getLogger(ImpsDao.class);
	@Autowired IfsccodeDao ifsccodeDao;
	@Autowired CustomQueryServiceLogic customQueryServiceLogic;
	@Autowired CommissionService commissionService;
	@Autowired ImpsverificationDao impsverificationDao;
	@Autowired ImpsTransactionService impsTransactiondao;
	@Autowired HttpSession session;
	@Autowired IfsccodeDao ifscdao;
	@Autowired OperatorDao operatorDao;
	@Autowired ImpsBankDao impsbankdao;
	@Autowired DMRCommissionDao DMRCommissionDao;
	@Autowired AssignedPackageDAO assignedPackage;
	
	private static String member_id="zzzz";
	private static String api_password="ayan123";
	private static String api_pin="21984";
	
	
	@Override
	public Map<String, Object> checkuserDoopme(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String remitter = request.get("mobile");
		returnData = MoneyApiDao.checkuserDoopme(remitter,member_id,api_password,api_pin);
		
		session.setAttribute("checkRemitterMobile", request.get("mobile"));
		
		return returnData;
		}
	
	@Override
	public Map<String, Object> doopmeRemitterRegister(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String customermobile = request.get("customermobile");
		String customername = request.get("customername");
		String Pincode = "700052";

		returnData = MoneyApiDao.doopmeRemitterRegister(customermobile,member_id,api_password,api_pin,customername,Pincode);
		
		
		return returnData;
		}
	
	@Override
	public Map<String, Object> doopmeNewBeneficiary(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String beneMobileNumber = request.get("beneMobileNumber");
		String bene_name = request.get("bene_name");
		String bene_type = "IMPS";
		String accountNumber = request.get("accountNumber");
		String acc_type = request.get("acc_type");
		String beneIFSCCode = request.get("IFSC_CODE");
		String remitterid = request.get("remitterid");
		String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");

		returnData = MoneyApiDao.doopmeNewBeneficiary(beneMobileNumber,checkRemitterMobile,member_id,api_password,api_pin,bene_name,bene_type,accountNumber,acc_type,beneIFSCCode,remitterid);
		
		return returnData;
		}
	
	@Override
	public Map<String, Object> appsdoopmeNewBeneficiary(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String beneMobileNumber = request.get("beneMobileNumber");
		String bene_name = request.get("bene_name");
		String bene_type = "IMPS";
		String accountNumber = request.get("accountNumber");
		String acc_type = request.get("acc_type");
		String beneIFSCCode = request.get("IFSC_CODE");
		String remitterid = request.get("remitterid");
		String checkRemitterMobile=request.get("checkRemitterMobile");
	
		returnData = MoneyApiDao.doopmeNewBeneficiary(beneMobileNumber,checkRemitterMobile,member_id,api_password,api_pin,bene_name,bene_type,accountNumber,acc_type,beneIFSCCode,remitterid);
		logger_log.warn("appsdoopmeNewBeneficiary:::"+returnData);
		return returnData;
		}
	
	@Override
	public Map<String, Object> fetchbank() {
		Map<String, Object> returnData = new HashMap<String, Object>();
		List<Imps_BankDetails> ifsc=impsbankdao.getAllBank();
		returnData.put("ifsc", ifsc);
	
		
		return returnData;
		}
	
	@Override
	public Map<String, Object> doopmeVerifyBeneficiary(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String MOBILENO = request.get("MOBILENO");
		String REQUESTNO = request.get("REQUESTNO");
		String OTP = request.get("OTP");
		String remitterid = request.get("REMID");
		String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
		
		returnData = MoneyApiDao.doopmeVerifyBeneficiary(MOBILENO,checkRemitterMobile,member_id,api_password,api_pin,REQUESTNO,OTP,remitterid);
		
		return returnData;
		}
	
	@Override
	public Map<String, Object> appsdoopmeVerifyBeneficiary(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String MOBILENO = request.get("MOBILENO");
		String REQUESTNO = request.get("REQUESTNO");
		String OTP = request.get("OTP");
		String remitterid = request.get("REMID");
		String checkRemitterMobile=request.get("checkRemitterMobile");
		
		returnData = MoneyApiDao.doopmeVerifyBeneficiary(MOBILENO,checkRemitterMobile,member_id,api_password,api_pin,REQUESTNO,OTP,remitterid);
		
		return returnData;
		}
	
	@Override
	public Map<String, Object> viewdoopmebene( User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		
		String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
		
		returnData = MoneyApiDao.viewdoopmebene(checkRemitterMobile,member_id,api_password,api_pin);
		
		return returnData;
		}
	
	@Override
	public Map<String, Object> viewdoopmebeneANDROID(Map<String, String> request,User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		
		String checkRemitterMobile=request.get("checkRemitterMobile");
		
		returnData = MoneyApiDao.viewdoopmebene(checkRemitterMobile,member_id,api_password,api_pin);
		
		return returnData;
		}
	
	@Override
	public Map<String, Object> apidoopmeMoneytransfer(Map<String, String> request,User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		double charge = 0.0;
		boolean flag;
		//Operator operator = new Operator();
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
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
		
		
			String checkRemitterMobile=request.get("checkRemitterMobile");
		
		String beneMobileNumber = request.get("mobile");
		String bene_name = request.get("name");
		String transfertype = request.get("transfertype");
		String accountNumber = request.get("account");
		String acc_type = request.get("accountType");
		String beneIFSCCode = request.get("ifsc");
		String beneid = request.get("id");
		String transactionAmount=request.get("amount");
		String referenceno = GenerateRandomNumber.referenceNO();
		double amount=0.0;
		double totalAmount=0.0;
		double transamount=Double.parseDouble(transactionAmount);
		int id=0;
		
		parameter.put("user_id", userDetails.getUserId());
		parameter.put("service_type", "DMR");
		List<AssignedPackage> lista = assignedPackage
				.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", parameter);
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
		// ------------------------------  User's Charge calculation for the Recharge -------------------------------------//
			
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

		}	else if (userDetails.getRoleId() == 100) {
			pckapiu = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"DMR",id);
			if (pckapiu.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
				charge = (pckapiu.getCharge() * transamount) / 100;
			} else {
				charge = pckapiu.getCharge();
			}

		}				
	/*-------------------------------------------------------------------------------------------------------------------*/
		String tId = "";
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
			System.out.println("sdComm:::::::::"+sdComm);
			
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
				if (temp > 5000) {
					temp = temp - 5000;
					remain = 5000;
				} else {
					remain = temp;
					temp = 0.0;
					for(DMRCommission comm2 : opList){
						if(remain>=comm2.getSlab1() && remain<=comm2.getSlab2()){
							id = comm2.getId();
							break;
						}
					}
				}
				double lockbalance=userDetails.getLockedAmount();
				totalAmount = RoundNumber.roundDouble((remain + charge) - comm);
				parameters = new HashMap<String, String>();
				double adOpBal = customQueryServiceLogic
						.getTotalbalanceForAdmin(CustomSqlQuery.getTotalbalanceForAdmin, parameters);
				RoundNumber.roundDouble(adOpBal - totalAmount);
				parameters = new HashMap<String, String>();
				parameters.put("userId", userDetails.getUserId());
				double op_bal = customQueryServiceLogic
						.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
				System.out.println("op_bal::::::"+op_bal);
				double cl_bal = RoundNumber.roundDouble(op_bal - totalAmount);
				System.out.println("cl_bal::::::"+cl_bal);
				if(lockbalance<=cl_bal){
				if (op_bal > totalAmount) {
					amount = remain;
					String amount3= Double.toString(remain);
					returnData = MoneyApiDao.doopmeMoneytransfer(checkRemitterMobile,member_id,api_password,api_pin,beneMobileNumber,bene_name,transfertype,accountNumber,acc_type,beneIFSCCode,beneid,amount3,referenceno);
					String FUNDTRANSNO=(String) returnData.get("FUNDTRANSNO");
					String status=(String) returnData.get("status");
					
					System.out.println("charge::::::::::::" + charge);
				//	totalAmount = charge + amount;
					totalAmount = RoundNumber.roundDouble((remain + charge) - comm);
					//System.out.println("totalAmount::::::::::::::::::::" + totalAmount);
					parameters = new HashMap<String, String>();
					
					System.out.println("cl_bal::::::"+cl_bal);
					if (cl_bal > 0) {
						String today = GenerateRandomNumber.getCurrentDate();	
						String currentTime = GenerateRandomNumber.getCurrentTime();
						String transId = GenerateRandomNumber.generateTransactionNumber();
						flag = commissionService.updateBalance(userDetails.getUserId(),
								"Money Transfer - " + accountNumber, "Money Transfer", totalAmount, "DEBIT",0);
						System.out.println("flag::::::"+flag);
						if (flag) {
							if (status.equals("1")) {
							ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, totalAmount, charge, cl_bal, today, currentTime, transId, "SUCCESS", FUNDTRANSNO,comm,transfertype,checkRemitterMobile,0.0,0.0,"DMR");
							flag=impsTransactiondao.insertImpsTransaction(imptrans);
							}else if(status.equals("0")){
								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, totalAmount, charge, cl_bal, today, currentTime, transId, "FAILED", FUNDTRANSNO,comm,transfertype,checkRemitterMobile,0.0,0.0,"DMR");
								flag=impsTransactiondao.insertImpsTransaction(imptrans);
							}else{
								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, totalAmount, charge, cl_bal, today, currentTime, transId, "PENDING", FUNDTRANSNO,comm,transfertype,checkRemitterMobile,0.0,0.0,"DMR");
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
										commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0);
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
								}
								//impsTransactiondao.updateDmrTransactionStatus(transId, status);
							
						}
					}	
						
					}

				} else {

					returnData.put("message", "Donot Have Sufficient Balance");
					returnData.put("status", "0");

				}
			} else {

				returnData.put("message", "Donot Have Sufficient Balance");
				returnData.put("status", "0");

			}
			}
		} else {
			returnData.put("message", "Amount should be between 10-5000");
			returnData.put("status", "0");
		}
		
	}else {
		returnData.put("status", "0");
		returnData.put("message", "Operator Is Not Available");
	}
		}else{
			returnData.put("message", "Please Assign Package");
			returnData.put("status", "0");
		}
		
		
		return returnData;
		}
	
	@Override
	public Map<String, Object> doopmeMoneytransfer(Map<String, String> request,User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		double charge = 0.0;
		boolean flag;
		//Operator operator = new Operator();
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
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
		
		String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
		
		String beneMobileNumber = request.get("mobile");
		String bene_name = request.get("name");
		String transfertype = request.get("transfertype");
		String accountNumber = request.get("account");
		String acc_type = request.get("accountType");
		String beneIFSCCode = request.get("ifsc");
		String beneid = request.get("id");
		String transactionAmount=request.get("amount");
		String referenceno = GenerateRandomNumber.referenceNO();
		double amount=0.0;
		double totalAmount=0.0;
		double transamount=Double.parseDouble(transactionAmount);
		int id=0;

		parameter = new HashMap<String, Object>();
		parameter.put("user_id", userDetails.getUserId());
		parameter.put("service_type", "DMR");
		List<AssignedPackage> lista = assignedPackage
				.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", parameter);
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
		// ------------------------------  User's Charge calculation for the Recharge -------------------------------------//
			
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
	/*-------------------------------------------------------------------------------------------------------------------*/
		String tId = "";
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
				if (temp > 5000) {
					temp = temp - 5000;
					remain = 5000;
				} else {
					remain = temp;
					temp = 0.0;
					for(DMRCommission comm2 : opList){
						if(remain>=comm2.getSlab1() && remain<=comm2.getSlab2()){
							id = comm2.getId();
							break;
						}
					}
				}
				double lockbalance=userDetails.getLockedAmount();
				totalAmount = RoundNumber.roundDouble((remain + charge) - comm);
				parameters = new HashMap<String, String>();
				parameters.put("userId", userDetails.getUserId());
				double op_bal = customQueryServiceLogic
						.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters);
				System.out.println("op_bal::::::"+op_bal);
				double cl_bal = RoundNumber.roundDouble(op_bal - totalAmount);
				System.out.println("cl_bal::::::"+cl_bal);
				if(lockbalance<=cl_bal){
				if (userDetails.getBalance() > totalAmount) {
					String trnsamnt=Double.toString(remain);
					amount = remain;
					returnData = MoneyApiDao.doopmeMoneytransfer(checkRemitterMobile,member_id,api_password,api_pin,beneMobileNumber,bene_name,transfertype,accountNumber,acc_type,beneIFSCCode,beneid,trnsamnt,referenceno);
					String FUNDTRANSNO=(String) returnData.get("FUNDTRANSNO");
					String status=(String) returnData.get("status");
					
					System.out.println("charge::::::::::::" + charge);
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
						System.out.println("flag::::::"+flag);
						if (flag) {
							if (status.equals("1")) {
							ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, totalAmount, charge, cl_bal, today, currentTime, transId, "SUCCESS", FUNDTRANSNO,comm,transfertype,checkRemitterMobile,0.0,0.0,"DMR");
							flag=impsTransactiondao.insertImpsTransaction(imptrans);
							}else if(status.equals("0")){
								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, totalAmount, charge, cl_bal, today, currentTime, transId, "FAILED", FUNDTRANSNO,comm,transfertype,checkRemitterMobile,0.0,0.0,"DMR");
								flag=impsTransactiondao.insertImpsTransaction(imptrans);
							}else{
								ImpsTransaction imptrans=new ImpsTransaction(userDetails.getUserName(), accountNumber, bene_name, beneMobileNumber, op_bal, totalAmount, charge, cl_bal, today, currentTime, transId, "PENDING", FUNDTRANSNO,comm,transfertype,checkRemitterMobile,0.0,0.0,"DMR");
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
										commissionService.updateBalance(sdId, "Money Transfer - "+accountNumber, "COMMISSION", sdisComm, "CREDIT",0);
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
								}
								//impsTransactiondao.updateDmrTransactionStatus(transId, status);
							
						}
					}	
						
					}

				} else {

					returnData.put("message", "Donot Have Sufficient Balance");
					returnData.put("status", "0");

				}
				} else {

					returnData.put("message", "Donot Have Sufficient Balance");
					returnData.put("status", "0");

				}
			}
		} else {
			returnData.put("message", "Amount should be between 10-5000");
			returnData.put("status", "0");
		}
		
	}else {
		returnData.put("status", "0");
		returnData.put("message", "Operator Is Not Available");
	}
	}else{
		returnData.put("message", "Please Assign Package");
		returnData.put("status", "0");
	}
		
		
		return returnData;
		}
	
	@Override
	public Map<String, Object> deletedoopmebene(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		System.out.println(request);
		String BenfId = request.get("id");
	
		String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
		
		returnData = MoneyApiDao.deletedoopmebene(request.get("remitterid"),member_id,api_password,api_pin,BenfId);
		
		return returnData;
		}
	
	
	@Override
	public Map<String, Object> VerifyDeleteBane(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		System.out.println(request);
		String BenfId = request.get("id");
		String otp = request.get("otp");
	
		//String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
		
		returnData = MoneyApiDao.VerifyDeleteBane(request.get("remitterid"),member_id,api_password,api_pin,BenfId,otp);
		
		return returnData;
		}
	
	@Override
	public Map<String, Object> doopmeValidateBeneficiary(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		System.out.println(request);
		String accountNumber = request.get("account");
		String beneIFSCCode = request.get("ifsc");
		String checkRemitterMobile=(String) session.getAttribute("checkRemitterMobile");
		String referenceno = GenerateRandomNumber.referenceNO();
		if(userDetails.getBalance()<4){
			returnData.put("status", "0");
			returnData.put("message", "Donot have sufficient Balance");
			
		}else{
			commissionService.updateBalance(userDetails.getUserId(), "IMPS Account Validate", "Charge", 4, "DEBT",0);
		returnData = MoneyApiDao.doopmeValidateBeneficiary(checkRemitterMobile,member_id,api_password,api_pin,accountNumber,beneIFSCCode,referenceno);
		}
		return returnData;
		}


}
