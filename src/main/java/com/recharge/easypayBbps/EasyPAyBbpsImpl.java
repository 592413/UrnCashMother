package com.recharge.easypayBbps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.businessdao.ApiResponseService;
import com.recharge.businessdao.CommissionService;
import com.recharge.businessdao.CustomQueryServiceLogic;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.model.BBPSLIST;
import com.recharge.model.Chargeset;
import com.recharge.model.EarningSurcharge;
import com.recharge.model.Operator;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.Rechargedetails;
import com.recharge.model.User;
import com.recharge.model.Utility;
import com.recharge.servicedao.BBPsListDao;
import com.recharge.servicedao.EarningSurchargeDao;
import com.recharge.servicedao.OperatorDao;
import com.recharge.servicedao.RechargedetailsDao;
import com.recharge.servicedao.UtilityDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.RoundNumber;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
@Service("EasyPayBbpsDao")
public class EasyPAyBbpsImpl implements EasyPayBbpsDao{
	private static final Logger logger_log = Logger.getLogger(EasyPayBbpsDao.class);
	@Autowired OperatorDao operatorDao;
	@Autowired CommissionService commissionService;
	@Autowired CustomQueryServiceLogic customQueryServiceLogic;
	@Autowired RechargedetailsDao rechargedetailsDao;
	@Autowired UtilityDao utilityDao;
	@Autowired EarningSurchargeDao earningSurchargeDao;
	@Autowired ApiResponseService ApiResponseService;
	@Autowired BBPsListDao BBPsListDao;
	
	
	@Override
	public Map<String, Object> eBillFetch(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Operator operator = new Operator();
		try {
			String output = "";
			String tId = GenerateRandomNumber.generatePtid(request.get("consumerMobile"));
			Map<String, Object> param = new HashMap<String, Object>();
			
			param.put("inCode", request.get("operator"));
			List<Operator> opList =  operatorDao.getOperatorByNamedQuery("getOperatorByInCode", param);
			operator=opList.get(0);
			System.out.println(operator.getOutCode());
			String url = "https://utility.myezypay.in/Ebillpay/Paybill";
			 Client restClient = Client.create();
			WebResource webResource = restClient.resource(url);
			
			String msg="B06003~b04e090c3fb34501af~"+tId+"~"+operator.getOutCode()+"~BD01BD38AGT000000002~"+userDetails.getUserId()+"~HEMRAJ~CWM5263524~123547852563~713310~23.6762,86.9914~"+request.get("consumerNumber")+"~"+request.get("consumerMobile")+"~NA~9867170438~NA~NA~NA~NA";
			logger_log.warn("BBPS msg:"+msg);
			MultivaluedMap formData = new MultivaluedMapImpl();
	         formData.add("msg",msg);
		
	         logger_log.warn(operator.getOutCode());
			
			 ClientResponse resp = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class,formData);
			
			//Response response=client.target(url).request(MediaType.APPLICATION_FORM_URLENCODED).headers(headers).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
			 logger_log.warn("resp:"+resp);
			 output = resp.getEntity(String.class);
			// output="B06004~184554343~133~Y~Sorry, We are unable to process your request. Please try again after sometime.~153159707~9804336652~RS031476~3458965745~20160512~20160525~15.00~N~NA~NA~NA";
			logger_log.warn("eBillFetch BBPS output::::"+output);
			String opp[]=output.split("~");
			if(opp.length==2){
				returnData.put("status", "0");
				returnData.put("message", output);
			}else{
			if(opp[3].equals("N")){
				returnData.put("status", "0");
				returnData.put("message", opp[4]);
			}else{
				returnData.put("status", "1");
				returnData.put("BillNumber", opp[8]);
				returnData.put("BillDate", opp[9]);
				returnData.put("BillDueDate", opp[10]);
				returnData.put("amount", opp[11]);
				returnData.put("PartialPayment", opp[12]);
				returnData.put("consumerMobile", request.get("consumerMobile"));
				returnData.put("consumerNumber", request.get("consumerNumber"));
				returnData.put("operator", request.get("operator"));
			}
			}
			
		}catch(Exception e){
			logger_log.error("eBillFetch ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;	
	}
	
	
	@Override
	public Map<String, Object> bbpsBillPAy(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		Operator operator = new Operator();
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		PackageWiseChargeComm pckapiu=new PackageWiseChargeComm();
		Map<String, Object> param = new HashMap<String, Object>();
		String status = "";			
		double comm = 0.0;
		double dComm = 0.0;
		double sdComm = 0.0;
		double resComm = 0.0;
		double retComm = 0.0;
		String operatorid="";
		double sdCharge = 0.0;
		double distCharge = 0.0;			
		double resCharge = 0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		String rId="";
		String response = "";
		Chargeset dCharge = null;
		String service_type = "";
		
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		
		double charge = 0.0;
		try{
			String mobileNo = request.get("consumerNumber");
			String source = request.get("source");
			double amount = Double.parseDouble(request.get("amount"));
			String output = "";
			param.put("inCode", request.get("operator"));
			List<Operator> opList =  operatorDao.getOperatorByNamedQuery("getOperatorByInCode", param);
			operator=opList.get(0);
			System.out.println(operator.getOutCode());
			String tId = GenerateRandomNumber.generatePtid(request.get("consumerMobile"));
			
			
			if (userDetails.getRoleId() == 5) {
				pckret = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId()) ;
				if (pckret.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
					charge = (pckret.getCharge() * amount) / 100;
				} else {
					charge = pckret.getCharge();
				}
			} else if (userDetails.getRoleId() == 4) {
				pckdis = commissionService.getPackagewiseCommisionOnOperator(
						userDetails.getUserId(), "Recharge", operator.getOperatorId());
				if (pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
					charge = (pckdis.getCharge() * amount) / 100;
				} else {
					charge = pckdis.getCharge();
				}

			} else if (userDetails.getRoleId() == 3) {
				pcksd = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),
						"Recharge", operator.getOperatorId());
				if (pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
					charge = (pcksd.getCharge() * amount) / 100;
				} else {
					charge = pcksd.getCharge();
				}

			}else if (userDetails.getRoleId() == 100) {
				pckapiu = commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),
						"Recharge", operator.getOperatorId());
				if (pckapiu.getCharge_type().equalsIgnoreCase("PERCENTAGE")) {
					charge = (pckapiu.getCharge() * amount) / 100;
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
				retComm =(pckret.getComm()*amount)/100;
				}else{
				retComm =pckret.getComm();	
				}
				comm = retComm;
			//	System.out.println("reseller=="+comm);
				pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Recharge",operator.getOperatorId()); 
				if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				dComm =(pckdis.getComm()*amount)/100;
				}else{
				dComm=pckdis.getComm();	
				}
			//	System.out.println("dComm=="+dComm);
				pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
				if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				sdComm =(pcksd.getComm()*amount)/100;
				}else{
				sdComm = pcksd.getComm();	
				}
			//	System.out.println("sdComm=="+sdComm);
				pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
				if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				resComm =(pckres.getComm()*amount)/100;
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
				
				pckdis=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId()); 
				if(pckdis.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				dComm =(pckdis.getComm()*amount)/100;
				}else{
				dComm=pckdis.getComm();	
				}
				comm = dComm;
			//	System.out.println("dComm="+dComm);
				pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Recharge",operator.getOperatorId());
				if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				sdComm =(pcksd.getComm()*amount)/100;
				}else{
				sdComm = pcksd.getComm();	
				}
				sdComm=sdComm-dComm;
				//System.out.println("sdComm="+sdComm);
				if(!resellerId.equals("admin")) {
				pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
				if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
				resComm =(pckres.getComm()*amount)/100;
				}else{
				resComm =pckres.getComm();	
				}
				resComm=resComm-sdComm;
				}
				//System.out.println("resComm="+resComm);
				
			} else if(userDetails.getRoleId() == 3) {
				resellerId = userDetails.getUplineId();
				pcksd=commissionService.getPackagewiseCommisionOnOperator(userDetails.getUserId(),"Recharge",operator.getOperatorId());
				if(pcksd.getComm_type().equalsIgnoreCase("PERCENTAGE")){
			     sdComm =(pcksd.getComm()*amount)/100;
				}else{
					sdComm = pcksd.getComm();	
				}
				
				System.out.println("sdComm:::::::::"+sdComm);
				if(!resellerId.equals("admin")) {
					pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Recharge",operator.getOperatorId());
					if(pckres.getComm_type().equalsIgnoreCase("PERCENTAGE")){
					resComm =(pckres.getComm()*amount)/100;
					}else{
					resComm =pckres.getComm();	
					}
					resComm=resComm-sdComm;
					}
	
			}
			String today = GenerateRandomNumber.getCurrentDate();	
			String currentTime = GenerateRandomNumber.getCurrentTime();
			double totalAmount = RoundNumber.roundDouble((amount + charge) - comm);
			parameters = new HashMap<String, String>();
			double adOpBal = customQueryServiceLogic.getTotalbalanceForAdmin(CustomSqlQuery.getTotalbalanceForAdmin, parameters);
			RoundNumber.roundDouble(adOpBal - totalAmount);
			parameters = new HashMap<String, String>();
			parameters.put("userId", userDetails.getUserId());	
			double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters );	
			
			System.out.println("op_bal"+op_bal);
			double cl_bal = RoundNumber.roundDouble(op_bal - totalAmount);
			if (cl_bal > 0) {
				Rechargedetails rechargedetails = new Rechargedetails(userDetails.getUserId(), mobileNo, operator.getOperatorId(), op_bal, amount, charge,comm, cl_bal, tId, tId, tId, "PENDING", "PENDING",source , operator.getApiId(), userDetails.getRoleId(), userDetails.getWlId(), today, currentTime,request.get("ip"));
				boolean flag = rechargedetailsDao.insertRechargeDetails(rechargedetails);	
			String url = "https://utility.myezypay.in/Ebillpay/Paybill";
			if (flag) {
				flag = commissionService.updateBalance(userDetails.getUserId(), "Recharge to "+mobileNo, "Recharge", totalAmount, "DEBIT",0);
				if (flag) {
					Utility utility = new Utility(tId, operator.getOperatorId(), userDetails.getUserId(), Integer.parseInt(operator.getServiceType()), operator.getServiceProvider(), request.get("consumerName"), request.get("consumerMobile"), request.get("consumerNumber"), request.get("dueDate"), amount, today, currentTime, "PENDING", userDetails.getWlId());
					boolean flag1 = utilityDao.insertUtility(utility);
					EarningSurcharge earningSurcharge = new EarningSurcharge(userDetails.getRoleId(), userDetails.getWlId(), userDetails.getUserId(), comm, charge, "RECHARGE FOR - "+mobileNo, today, currentTime);
					earningSurchargeDao.insertEarningSurcharge(earningSurcharge );
					
			 Client restClient = Client.create();
				WebResource webResource = restClient.resource(url);
				String opcd=operator.getOutCode();
				String validator2="";
				BBPSLIST bp=BBPsListDao.getBBPSLISTByApId(Integer.parseInt(opcd));
				if(bp.getValidator2().equals("NA")){
					validator2="NA";
				}else{
					validator2=request.get("consumerMobile");
				}
				
				String msg="B06005~b04e090c3fb34501af~"+tId+"~"+operator.getOutCode()+"~BD01BD38AGT000000002~"+userDetails.getUserId()+"~HEMRAJ~CWM5263524~123547852563~713310~23.6762,86.9914~"+request.get("consumerNumber")+"~"+validator2+"~NA~9867170438~"+request.get("amount")+"~"+request.get("BillDueDate")+"~NA~NA~NA~NA";
				System.out.println("msg:"+msg);
				MultivaluedMap formData = new MultivaluedMapImpl();
		         formData.add("msg",msg);
			
				System.out.println(operator.getOutCode());
				
				 ClientResponse resp = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class,formData);
				
				//Response response=client.target(url).request(MediaType.APPLICATION_FORM_URLENCODED).headers(headers).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
				System.out.println("resp:"+resp);
				 output = resp.getEntity(String.class);
				logger_log.warn("eBillFetch BBPS output::::"+output);
				String opp[]=output.split("~");
				if(opp[3].equals("SUCCESS")){
					Map<String,String> pam=new HashMap<>();
					pam.put("status", "SUCCESS");
					pam.put("OpratorId", opp[5]);
					pam.put("Transid", tId);
					ApiResponseService.CrowdfinchApiResponse(pam);
					status="SUCCESS";
				}else if(opp[3].equals("PENDING")){
					status="SUCCESS";
				}else{
					status="FAILED";
				}
				
				if (status.equals("SUCCESS")) {
					
					/*----------------------CHARGE & COMMISSION FOR THE RECHARGE-------------------------------*/
				
					if(userDetails.getRoleId() == 5) {
						if(pckdis.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
							distCharge = (pckdis.getCharge()*amount)/100;
						}else{
							distCharge = pckdis.getCharge();
						}
						if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
							sdCharge = (pcksd.getCharge()*amount)/100;
						}else{
							sdCharge = pcksd.getCharge();
						}
							if(!resellerId.equals("admin")) {
								if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
								resCharge = (pckres.getCharge()*amount)/100;
								}else{
								resCharge =	pckres.getCharge();
								}
								
							}
							if(distCharge==0){dcharge=0;}
							else{dcharge = charge - distCharge;}
							
							if(sdCharge==0){sCharge=0;}
							else{sCharge = distCharge - sdCharge;}
							
							
							commissionService.updateBalance(distId, "Charge for Recharge - "+mobileNo, "CHARGE", dcharge, "CREDIT",0);
							commissionService.updateBalance(sdId, "Charge for Recharge - "+mobileNo, "CHARGE", sCharge, "CREDIT",0);
							if(!resellerId.equals("admin")) {
								if(resCharge==0){reCharge=0;}
								else{
								reCharge = sdCharge - resCharge;
								}
								commissionService.updateBalance(resellerId, "Charge for Recharge - "+mobileNo, "Recharge Charge", reCharge, "CREDIT",0);
							}
							
							/*-------------------------------For earning and Surcharge ------------------*/
							// for DISTRIBUTOR 
							EarningSurcharge earningSurcharge1 = new EarningSurcharge(4, userDetails.getWlId(), distId, 0.0, dcharge, "Charge FOR - "+mobileNo, today, currentTime);
							earningSurchargeDao.insertEarningSurcharge(earningSurcharge1);
							
							// For Super Distributor
							EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, 0.0, sCharge, "Charge FOR - "+mobileNo, today, currentTime);
							earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
							
							// For RESELLER
							if(!resellerId.equals("admin")) {
								EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, 0.0, reCharge, "Charge FOR - "+mobileNo, today, currentTime);
								earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
							}
							/*----------------------------------------------------------------------*/
							
														
						/*//commissionService.updateBalance(distId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", dComm, "CREDIT",0);
						//commissionService.updateBalance(sdId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", sdComm, "CREDIT",0);
						if(!resellerId.equals("admin")) {
							//commissionService.updateBalance(resellerId, "COMMISSION for Recharge - "+mobileNo, "COMMISSION", resComm, "CREDIT",0);
						}*/
					} else if(userDetails.getRoleId() == 4) {
						
						if(!resellerId.equals("admin")) {
							if(pckres.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
							resCharge = (pckres.getCharge()*amount)/100;
							}else{
							resCharge =	pckres.getCharge();
							}
							
						}
						if(pcksd.getCharge_type().equalsIgnoreCase("PERCENTAGE")){
							sdCharge = (pcksd.getCharge()*amount)/100;
						}else{
							sdCharge = pcksd.getCharge();
						}
						if(sdCharge==0){sCharge=0;}
						else{sCharge = charge - sdCharge;}
																			
						commissionService.updateBalance(sdId, "Charge for Recharge - "+mobileNo, "CHARGE", sCharge, "CREDIT",0);
						if(!resellerId.equals("admin")) {
							if(resCharge==0){reCharge=0;}
						else{
						reCharge = sdCharge - resCharge;
						}
							commissionService.updateBalance(resellerId, "Charge for Recharge - "+mobileNo, "Recharge Charge", reCharge, "CREDIT",0);
						}														
						/*-------------------------------For earning and Surcharge ------------------*/
																				
						// For Super Distributor
						EarningSurcharge earningSurcharge2 = new EarningSurcharge(3, userDetails.getWlId(), sdId, 0.0, sCharge, "Charge FOR - "+mobileNo, today, currentTime);
						earningSurchargeDao.insertEarningSurcharge(earningSurcharge2);
						
						// For RESELLER
						if(!resellerId.equals("admin")) {
							EarningSurcharge earningSurcharge3 = new EarningSurcharge(4, userDetails.getWlId(), resellerId, 0.0, reCharge, "Charge FOR - "+mobileNo, today, currentTime);
							earningSurchargeDao.insertEarningSurcharge(earningSurcharge3);
						}
						/*----------------------------------------------------------------------*/
					
						} else if(userDetails.getRoleId() == 3) {

							if (!resellerId.equals("admin")) {
								if (pckres.getCharge_type()
										.equalsIgnoreCase("PERCENTAGE"))
									resCharge = (pckres.getCharge() * amount) / 100;
							} else {
								resCharge = pckres.getCharge();
							}
							resCharge = charge - resCharge;

							if (!resellerId.equals("admin")) {
								
								reCharge = sdCharge - charge;
								commissionService.updateBalance(resellerId,
										"Charge for Recharge - " + mobileNo,
										"Recharge Charge", reCharge, "CREDIT",0);
							}

							/*-------------------------------For earning and Surcharge ------------------*/

							// For RESELLER
							if (!resellerId.equals("admin")) {
								EarningSurcharge earningSurcharge3 = new EarningSurcharge(
										4, userDetails.getWlId(), resellerId, 0.0,
										reCharge, "Charge FOR - " + mobileNo, today,
										currentTime);
								earningSurchargeDao
										.insertEarningSurcharge(earningSurcharge3);
							}
							/*----------------------------------------------------------------------*/

						}
					rechargedetails.setStatus("SUCCESS");
				//	rechargedetails.setValidation("PENDING");
					rechargedetailsDao.updateRechargeDetails(rechargedetails);											
					returnData.put("status", "1");
					returnData.put("message", "Recharge Submit SUCCESSFUL. Transaction Id: "+tId);
					if(source.equals("APPS")){
						returnData.put("closingBalance", cl_bal);
					}
					if(source.equals("API")){
						returnData.put("TransactionId", tId);
					}
				} else if (status.equals("FAILED")) {
					rechargedetails.setStatus("FAILED");
					rechargedetails.setValidation("FAILED");
					rechargedetailsDao.updateRechargeDetails(rechargedetails);										
					commissionService.updateBalance(userDetails.getUserId(), "REFUND for Recharge - "+mobileNo, "REFUND", totalAmount, "CREDIT",0);
					
					returnData.put("status", "0");
					returnData.put("message", "Recharge FAILED. Transaction Id: "+tId);
					if(source.equals("API")){
						returnData.put("TransactionId", tId);
					}
					if(source.equals("APPS")){
						returnData.put("closingBalance", cl_bal);
					}
				}
				
				}else{
					returnData.put("status", "0");
					if(source.equals("APPS")){
						returnData.put("closingBalance", cl_bal);
					}
					returnData.put("message", "Recharge Failed. Technical Error!");
				}
			}else{
				returnData.put("status", "0");
				if(source.equals("APPS")){
					returnData.put("closingBalance", cl_bal);
				}
				returnData.put("message", "Recharge Failed. Technical Error!");
			}
			}
		}catch(Exception e){
			logger_log.error("bbpsBillPAy ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;	
	}
}
