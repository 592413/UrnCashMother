package com.recharge.businessimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encorenew.moneyapi.EncoreMoneyBusinessNew;
import com.fingerpay.businessDao.FingerPayService;
import com.recharge.Imps.ImpsDao;
import com.recharge.businessdao.AdminService;
import com.recharge.businessdao.AndroidService;
import com.recharge.businessdao.ApiParserService;
import com.recharge.businessdao.ApiResponseService;
import com.recharge.businessdao.AuthenticationCommandCenter;
import com.recharge.businessdao.GlobalCommandCenter;
import com.recharge.businessdao.ReportService;
import com.recharge.businessdao.RetailerService;
import com.recharge.customModel.BalanceTransfer;
import com.recharge.customModel.Notification;
import com.recharge.customModel.P2ATranferReport;
import com.recharge.customModel.RechargeReport;
import com.recharge.customModel.TransactionReport;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.icicidmtservicce.ICICIDmtService;
import com.recharge.model.AEPSBankDetail;
import com.recharge.model.AEPSUserMap;
import com.recharge.model.MicroatmUser;
import com.recharge.model.PaymonkResponse;
import com.recharge.model.Reseller;
import com.recharge.model.SMSApiparameters;
import com.recharge.model.Signinhistory;
import com.recharge.model.SmsCredential;
import com.recharge.model.User;
import com.recharge.servicedao.AEPSBankDetailDao;
import com.recharge.servicedao.AEPSUserMapDao;
import com.recharge.servicedao.IfsccodeDao;
import com.recharge.servicedao.PaymonkResponseDao;
import com.recharge.servicedao.SMSApiparametersDao;
import com.recharge.servicedao.SigninhistoryDao;
import com.recharge.servicedao.SmsCredentialDao;
import com.recharge.servicedao.UserDao;
import com.recharge.utill.SMS;
import com.recharge.utill.UtilityClass;
import com.recharge.yesbankservice.YesBankBusiness;


@Service("androidService")
public class AndroidServiceImpl implements AndroidService {
	private static final Logger logger_log = Logger.getLogger(AndroidService.class);
	
	
	@Autowired AuthenticationCommandCenter authenticationCommandCenter;
	@Autowired GlobalCommandCenter globalCommandCenter;
	@Autowired RetailerService retailerService;
	@Autowired ReportService reportService;	
	@Autowired SigninhistoryDao signinhistoryDao;
	@Autowired UserDao userDao;
	@Autowired SmsCredentialDao smsCredentialDao;
	@Autowired IfsccodeDao ifsccodeDao;
	@Autowired ImpsDao impsDao;
	@Autowired SMSApiparametersDao smsapiparamsdao;
	@Autowired AEPSUserMapDao aepsuserdao;
	@Autowired ApiParserService apiParserService;
	@Autowired PaymonkResponseDao paymonkresponsedao;
	@Autowired ApiResponseService apiResponseService;
	@Autowired AdminService adminService;
	@Autowired AEPSBankDetailDao aepsBankdetail;
	@Autowired YesBankBusiness yesbankservice;
	@Autowired ICICIDmtService icicidmtService;
	@Autowired FingerPayService fingerpayservice;
	@Autowired EncoreMoneyBusinessNew encoremoneyservicenew;
	@Autowired com.recharge.servicedao.MicroatmuserDao MicroatmuserDao;
	@Override
	public Map<String, Object> tokenizer(String deviceId) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		List<Signinhistory> list = new ArrayList<>();
		try{
			if (deviceId != null) {
				Map<String, Object> param = new HashMap<>();
				param.put("deviceId", deviceId);
				list = signinhistoryDao.getSigninhistoryByNamedQuery("GetSigninhistoryDeviceId", param);
				if(list == null||list.isEmpty()){
					returnData.put("status", "0");
					returnData.put("message", "Invalid Token");
				} else {
					returnData.put("status", "1");
					returnData.put("message", "Valid Token");
				}
			} else {
				returnData.put("status", "0");
				returnData.put("message", "Invalid Token");
			}
		} catch(Exception e) {
			logger_log.error("tokenizer ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	



	@Override
	public Map<String, Object> androidLogin(String requestJson, String ip) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		Map<String, String> param1 = new HashMap<String, String>();
		Map<String, Object> param3 = new HashMap<String, Object>();
		
		try {
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param1 = new HashMap<String, String>();
						param1.put("mobile", authJson.get("username").toString());
						param1.put("email", authJson.get("password").toString());
						String userId = authenticationCommandCenter.getUserNamebyMobileEmail(CustomSqlQuery.getUserIdByMobileEmail, param1);
						if (userId != null) {
							param.put("userId", userId);
							param.put("password", authJson.get("password").toString());
							String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
								User userDetails = globalCommandCenter.getUserByUserId(username);
								if (!requestInputJson.isNull("Token")) {
									
									JSONObject tokenJson = requestInputJson.getJSONObject("Token");									
									Map<String, Object> param2 = new HashMap<>();
									param2.put("userId", username);
									param2.put("deviceId", tokenJson.get("tokenId"));									
									List<Signinhistory> list = signinhistoryDao.getSigninhistoryByNamedQuery("GetSigninhistoryByUserIdAndDeviceId", param2);
									if(list.isEmpty()){
										
										boolean flag = globalCommandCenter.insertSigninHistory(ip, userDetails.getUserId(),	userDetails.getMobile(), tokenJson.get("tokenId").toString());
									}									
									if (userDetails != null) {
										
										if(userDetails.getWlId().equals("MSR8TDF")){
											returnData.put("status", "0");
											returnData.put("message", "Login Failed");
										}else{
										
										userDetails.setPassword(null);
										userDetails.setTranPin(null);
										userDetails.setDelFlag(null);
										userDetails.setStatus(null);
										returnData.put("message", "Login Successfully!");
										returnData.put("UserDetails", userDetails);
										param3 = new HashMap<String, Object>();
										param3.put("username",userDetails.getUserName());
										param3.put("api","YesBank");
										List<AEPSUserMap> aepsuserlist=aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",param3);
										//List<Operator> oplist = globalCommandCenter.getAllOperator();
										/*if(!oplist.isEmpty()) {					
											returnData.put("operator", oplist);
										}*/
										if(aepsuserlist.isEmpty()){
											returnData.put("agentcode", "No data available");	
											}else{
											returnData.put("agentcode",aepsuserlist.get(0).getAgentcode());		
											}
										param3 = new HashMap<String, Object>();
										param3.put("userId",userId);
										List<MicroatmUser> microatmlist=MicroatmuserDao.getMicroatmUserByNamedQuery("getmicroatmUserbyUserid", param3);
										if(!microatmlist.isEmpty()){
										returnData.put("microatmlist",microatmlist);	
										}
										
										if (userDetails.getRoleId() != 5 || userDetails.getRoleId() != 100) {											
											Notification notification = globalCommandCenter.getNotification(userDetails);
											if (notification != null) {
												returnData.put("notification", notification);
											}
										}		
										/*Reseller reseller = globalCommandCenter.getResellerByWlId(userDetails.getWlId());
										Reseller reseller2 = new Reseller();
										reseller2.setImage(reseller.getImage());
										returnData.put("reseller", reseller2);*/
										returnData.put("bankDetails",globalCommandCenter.getBankDetailsByWlId(userDetails.getWlId()).get("bankDetails"));
										returnData.put("status", "1");
										
									}
									} else {
										returnData.put("status", "0");
										returnData.put("message", "Login failed!");
									}
									
								} else {
									returnData.put("status", "0");
									returnData.put("message", "Invalid Token");								
								}
							} else {
								returnData.put("status", "0");
								returnData.put("message", "Login failed!");
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Login failed!");
						}
					}
				}
			}

		} catch (Exception e) {
			logger_log.error("androidLogin ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		System.out.println(returnData);
		return returnData;
	}
	
	@Override
	public Map<String, Object> editProfile(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("EditProfile")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid EditProfile Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("EditProfile"));
								User userDetails = globalCommandCenter.getUserByUserId(username);
								returnData = globalCommandCenter.editProfile(inputData, userDetails);
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}			
		} catch (Exception e) {
			logger_log.error("editProfile ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData ;
	}	
	
	@Override
	public Map<String, Object> changePassword(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("ChangePassword")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid ChangePassword Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("ChangePassword"));
								User userDetails = globalCommandCenter.getUserByUserId(username);
								returnData = globalCommandCenter.changePass(inputData, userDetails);
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}		
		} catch (Exception e) {
			logger_log.error("changePassword ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData ;
	}

	@Override
	public Map<String, Object> myDiscount(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("MyDiscount")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid MyDiscount Object");
							} else {								
								if(authJson.get("username").equals(requestInputJson.getJSONObject("MyDiscount").get("userId"))){
									Map<String, String> inputData = new HashMap<String, String>();
									inputData = UtilityClass.toMap(requestInputJson.getJSONObject("MyDiscount"));
									returnData = globalCommandCenter.getMyDiscount(inputData);
								} else {
									returnData.put("status", "0");
									returnData.put("message", "Forbidden User");
								}
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("myDiscount ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> balanceRequest(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("BalanceRequest")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid BalanceRequest Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("BalanceRequest"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = retailerService.balanceRequest(inputData, userDetails);
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("myDiscount ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> rechargeReport(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try {
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("RechargeReport")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid RechargeReport Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("RechargeReport"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								List<RechargeReport> list  = reportService.getRechargeReport(inputData, userDetails);
								if(!list.isEmpty()) {
									returnData.put("RechargeReport", list);
									returnData.put("status", "1");					
								} else {
									returnData.put("status", "0");
									returnData.put("message", "No Records Found!");
								}
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("rechargeReport ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> transactionReport(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("TransactionReport")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid TransactionReport Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("TransactionReport"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								List<TransactionReport> list = reportService.getTransactionReport(inputData, userDetails);		
								if(!list.isEmpty()) {
									returnData.put("TransactionReport", list);
									returnData.put("status", "1");					
								} else {
									returnData.put("status", "0");
									returnData.put("message", "No Records Found!");
								}
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("TransactionReport ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> purchaseReport(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("PurchaseReport")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid PurchaseReport Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("PurchaseReport"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								List<BalanceTransfer> list = reportService.viewPurchaseReport(inputData, userDetails);		
								if(!list.isEmpty()) {
									returnData.put("PurchaseReport", list);
									returnData.put("status", "1");					
								} else {
									returnData.put("status", "0");
									returnData.put("message", "No Records Found!");
								}
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("purchaseReport ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> forgetPassword(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, Object> param = new HashMap<String, Object>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if(requestInputJson.isNull("mobile")){
					returnData.put("status", "0");
					returnData.put("message", "Invalid Mobile Number.");
				} else {
					String mobile = requestInputJson.getString("mobile");
					HashMap<String, String> param1 = new HashMap<String, String>();
					param1.put("mobile", mobile);
					param1.put("email", mobile);
					String userId = authenticationCommandCenter.getUserNamebyMobileEmail(CustomSqlQuery.getUserIdByMobileEmail, param1);
					if (userId != null) {
						User userDetails = globalCommandCenter.getUserByUserId(userId);
						param = new HashMap<String, Object>();
						param.put("wlId", userDetails.getWlId());
						SmsCredential credential = smsCredentialDao.getSmsCredentialByNamedQuery("GetSmsCredentialByWlId", param).get(0);
						String sms = "Welcome again to "+credential.getCompany()+",Your Mobile number is :"+userDetails.getMobile()+" & Password:"+userDetails.getPassword()+" Log on to :"+credential.getDomain()+"";
						List<SMSApiparameters> params =  smsapiparamsdao.getAllSMSApiparameters();
						new SMS().sendSMS(userDetails.getMobile(), sms, credential.getSenderId(), credential.getSmsUsername(), credential.getSmsPassword(),params);
						returnData.put("message", "Password send Successfully!");
						returnData.put("status", "1");	
					} else {
						returnData.put("status", "0");
						returnData.put("message", "Invalid Mobile Number.");
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("forgetPassword ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> sendRechargeRequst(String requestJson, String ip) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("Recharge")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Recharge Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("Recharge"));
								inputData.put("source", "APPS");
								inputData.put("ip", ip);
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = retailerService.webRecharge(inputData, userDetails);
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("sendRechargeRequst ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> addComplain(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("Complain")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Complain Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("Complain"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = retailerService.addComplains(inputData, userDetails);
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("addComplain ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> viewComplain(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("ComplainReport")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Complain Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("ComplainReport"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = globalCommandCenter.viewComplain(inputData, userDetails,"ALL");
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("addComplain ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getOperator(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("Getoperator")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Getoperator Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("Getoperator"));								
								returnData = globalCommandCenter.getOperatorByServiceType(inputData);
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("getOperator ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> addUser(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("UserDetails")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid UserDetails Object");
							} else {
								Map<String, String> inputData = UtilityClass.toMap(requestInputJson.getJSONObject("UserDetails"));	
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = globalCommandCenter.addUser(inputData, userDetails);
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("addUser ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> viewUser(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("ViewUser")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid ViewUser Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("ViewUser"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = globalCommandCenter.getViewUserDetails(inputData, userDetails);
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("ViewUser ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> addBalance(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("AddBalance")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid ViewUser Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("AddBalance"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = globalCommandCenter.addBalance(inputData, userDetails);
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("addBalance ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> viewBalanceTransfer(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("BalanceTransfer")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid BalanceTransfer Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("BalanceTransfer"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								List<BalanceTransfer> list = reportService.viewBalanceTransferReport(inputData, userDetails);		
								if(!list.isEmpty()) {
									returnData.put("BalanceTransfer", list);
									returnData.put("status", "1");					
								} else {
									returnData.put("status", "0");
									returnData.put("message", "No Records Found!");
								}
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("purchaseReport ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> utilityBillPayment(String requestJson, String ip) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("UtilityPayment")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid UtilityPayment Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("UtilityPayment"));
								inputData.put("source", "APPS");
								inputData.put("ip", ip);
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = retailerService.utilityBillPayment(inputData, userDetails);
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("utilityBillPayment ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> utilityReport(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("UtilityReport")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid UtilityReport Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("UtilityReport"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = reportService.viewUtilityReport(inputData,userDetails,"ALL");								
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}
		} catch (Exception e) {
			logger_log.error("utilityReport :::::::::::::::"+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> doopmecheckRemitter(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try {
			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
						//	JSONObject remitterJson = requestInputJson.getJSONObject("remitter");
							Map<String, String> inputData = new HashMap<String, String>();
							inputData = UtilityClass.toMap(requestInputJson.getJSONObject("remitter"));
							User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
							returnData = impsDao.checkuserDoopme(inputData, userDetails);
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}

				}

			}
		} catch (Exception e) {
			logger_log.error("checkRemitter ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> doopmeRgisterRemitter(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try {
			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("RemitterRegistration")) {
								returnData.put("status", "0");
								returnData.put("message", "RemitterRegistration Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("RemitterRegistration"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = impsDao.doopmeRemitterRegister(inputData, userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");

						}
					}

				}

			}

		} catch (Exception e) {
			logger_log.error("doopmeRemitterRegister ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}

		return returnData;
	}
	
	@Override
	public Map<String, Object> doopmeNewBeneficiary(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try {
			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("BeneficiaryAdd")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid BeneficiaryAdd Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("BeneficiaryAdd"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = impsDao.appsdoopmeNewBeneficiary(inputData, userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");

						}

					}

				}

			}

		} catch (Exception e) {
			logger_log.error("doopmeNewBeneficiary ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> doopmeVerifyBeneficiary(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try {
			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("BeneficiaryVerify")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid BeneficiaryAdd Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("BeneficiaryVerify"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = impsDao.appsdoopmeVerifyBeneficiary(inputData, userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");

						}

					}

				}

			}

		} catch (Exception e) {
			logger_log.error("doopmeVerifyBeneficiary ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> doopmeViewBeneficiary(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try {
			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("BeneficiaryView")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid BeneficiaryAdd Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("BeneficiaryView"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = impsDao.viewdoopmebeneANDROID(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");

						}

					}

				}

			}

		} catch (Exception e) {
			logger_log.error("doopmeViewBeneficiary ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> fetchbank(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try {
			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("fetchbank")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid BeneficiaryAdd Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("fetchbank"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = impsDao.fetchbank();
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");

						}

					}

				}

			}

		} catch (Exception e) {
			logger_log.error("fetchbank ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> doopmeMoneyTransfer(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try {
			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("MoneyTransfer")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid BeneficiaryAdd Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("MoneyTransfer"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = impsDao.apidoopmeMoneytransfer(inputData, userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");

						}

					}

				}

			}

		} catch (Exception e) {
			logger_log.error("doopmeMoneyTransfer ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> agencyrequest(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{

			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("AgencyRequest")) {
								returnData.put("status", "0");
								returnData.put("message", "AgencyRequest Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("AgencyRequest"));
						        System.out.println("inputData::::::::::::::::::"+inputData);
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								System.out.println(userDetails.getUplineId());
								returnData = retailerService.agencyrequest(inputData, userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");

						}
					}

				}

			}

		
			
		}catch (Exception e) {
			logger_log.error("agencyrequest ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> Couponrequest(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{

			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("CouponRequest")) {
								returnData.put("status", "0");
								returnData.put("message", "CouponRequest Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("CouponRequest"));
						        System.out.println("inputData::::::::::::::::::"+inputData);
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								System.out.println(userDetails.getUplineId());
								returnData = retailerService.couponrequest(inputData, userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");

						}
					}

				}

			}

		
			
		}catch (Exception e) {
			logger_log.error("CouponRequest ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

//aeps
	@Override
	public Map<String, Object> aepsAndroid(Map<String, Object> request) {
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> returnData = new HashMap<String, Object>();
		String username = "";
		boolean flag = false;
		
		try {
			
			PaymonkResponse paymonk = apiParserService.paymonkParserupdated(request);
			param.put("referenceNo",paymonk.getReferenceNo());
			List<PaymonkResponse> pay = paymonkresponsedao.getPaymonkResponseByNamedQuery("getpaymonkresponsebyrefId",param);
			if(pay.isEmpty()){
			flag = paymonkresponsedao.insertPaymonkResponse(paymonk);
			if(flag){
			String agentcode = paymonk.getAgentcode();	
			param = new HashMap<String, Object>();
			param.put("agentcode",agentcode);
			param.put("api","RBL");
			List<AEPSUserMap> list = aepsuserdao.getAEPSUserMapByNamedQuery("getUsernamebyagentcode",param);
			if(!list.isEmpty()){
				AEPSUserMap aeps = list.get(0);
				username = aeps.getUsername();
				User userDetails = globalCommandCenter.getUserByUserId(username);
				userDetails.setPassword(null);
				userDetails.setTranPin(null);
				userDetails.setDelFlag(null);
				userDetails.setStatus(null);
				apiResponseService.payaepsresponse(paymonk);
				returnData.put("message","Success");
			   
			}
			}
			}else{
				param = new HashMap<String, Object>();
				param.put("agentcode",paymonk.getAgentcode());
				param.put("api","RBL");
				List<AEPSUserMap> list = aepsuserdao.getAEPSUserMapByNamedQuery("getUsernamebyagentcode",param);
				AEPSUserMap aeps = list.get(0);
				username = aeps.getUsername();
				User userDetails = globalCommandCenter.getUserByUserId(username);
				returnData.put("message","Same reference number transaction again");
			}
			//logger_log.warn("skyReturn::::::::::::::::::::::::::"+request);
		} catch (Exception e) {
			logger_log.error("aepsAndroid::::::::::::::::::::::::::" + e);
		}
		return returnData;
	}


	@Override
	public Map<String, Object> aepsReportAndroid(Map<String, Object> requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{

			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("AEPSReport")) {
								returnData.put("status", "0");
								returnData.put("message", "AEPSReport Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("AEPSReport"));
						        System.out.println("inputData::::::::::::::::::"+inputData);
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								System.out.println(userDetails.getUplineId());
								returnData = yesbankservice.getYesBankAEPSReport(inputData, userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");

						}
					}

				}

			}

		
			
		}catch (Exception e) {
			logger_log.error("aepsReportAndroid ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}


	@Override
	public Map<String, Object> getAEPSBankDetail(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);			
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
						List<AEPSBankDetail> list =	aepsBankdetail.getAllAEPSBankDetail();	
						returnData.put("AEPSBankList",list);
							
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}
				}
			}			
		} catch (Exception e) {
			logger_log.error("editProfile ::::::::::::::: "+e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData ;
	}


	@Override
	public Map<String, Object> getSettlementBalanceAndroid(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{

			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								System.out.println(userDetails.getUplineId());
								returnData = adminService.getSettlementBalance(userDetails);
							

						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");

						}
					}

				}

			}

		
			
		}catch (Exception e) {
			logger_log.error("getSettlementBalanceAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}


	@Override
	public Map<String, Object> settlementReqAndroid(Map<String, Object> requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{

			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("SettlementRequest")) {
								returnData.put("status", "0");
								returnData.put("message", "SettlementRequest Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("SettlementRequest"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								System.out.println(userDetails.getUplineId());
								returnData = retailerService.settlerequest(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");

						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("settlementReqAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> addRblBankAndroid(Map<String, Object> requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{

			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("AddRBLBank")) {
								returnData.put("status", "0");
								returnData.put("message", "AddRBLBank Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("AddRBLBank"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								inputData.put("source","apps");
								returnData = retailerService.addRBLBank(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("settlementReqAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getSettlementReportAndroid(Map<String, Object> requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{

			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("SettlementReport")) {
								returnData.put("status", "0");
								returnData.put("message", "SettlementReport Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("SettlementReport"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = globalCommandCenter.getRBLSETTLEMENTReport(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("settlementReqAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}




	
	@Override
	public Map<String, Object> advancedSearchUser(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else{
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				}else{
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					}else{
						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("advancedSearchUser")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid advancedSearchUser Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("advancedSearchUser"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = globalCommandCenter.advancedSearchUser(inputData, userDetails);
								
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					
					}
				}
			}
		}catch (Exception e) {
			logger_log.error("advancedSearchUser ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
		}
	
	@Override
	public Map<String, Object> advancedCustomerNo(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			System.out.println(requestJson);
			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else{
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				}else{
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					}else{
						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("advancedCustomerNo")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid advancedCustomerNo Object");
							} else {
								Map<String, String> inputData = new HashMap<String, String>();
								inputData = UtilityClass.toMap(requestInputJson.getJSONObject("advancedCustomerNo"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = globalCommandCenter.advancedCustomerNo(inputData, userDetails);
								
							}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					
					}
				}
			}
		}catch (Exception e) {
			logger_log.error("advancedCustomerNo ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
		
	
	}


	@Override
	public Map<String, Object> checkBalance(Map<String, Object> requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{

			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("CheckBalance")) {
								returnData.put("status", "0");
								returnData.put("message", "CheckBalance Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("CheckBalance"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = globalCommandCenter.checkBalance(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("checkBalance::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> viewRblBankAndroid(Map<String, Object> request) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			JSONObject requestObj = new JSONObject(request);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else{
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				}else{
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					}else{
						
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());						
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
						/*	if (requestInputJson.isNull("advancedCustomerNo")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid advancedCustomerNo Object");
							} else {*/
							
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = globalCommandCenter.fetchaepsbankdt(userDetails);
								
						//	}
						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					
					}
				}
			}
		}catch (Exception e) {
			logger_log.error("viewRblBankAndroid ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
		
	
	}

	
	@Override
	public Map<String, Object> appgetImpsReport(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try {
			JSONObject requestObj = new JSONObject(requestJson);
			if (requestObj.isNull("RequestInput")) {
				returnData.put("status", "0");
				returnData.put("message", "Empty RequestInput Passed.");
			} else {
				JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
				if (requestInputJson.isNull("Authentication")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty Authentication Passed.");
				} else {
					JSONObject authJson = requestInputJson.getJSONObject("Authentication");
					if (authJson.isNull("username") || authJson.isNull("password")) {
						returnData.put("status", "0");
						returnData.put("message", "Username and Password Cannot be null.");
					} else {
						param.put("userId", authJson.get("username").toString());
						param.put("password", authJson.get("password").toString());
						String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
						if (username != null) {
							if (requestInputJson.isNull("Report")) {
								returnData.put("status", "0");
								returnData.put("message", "Invalid ImpsReport Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("Report"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = globalCommandCenter.getImpsReport(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");

						}

					}

				}

			}

		} catch (Exception e) {
			logger_log.error("appgetImpsReport ::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	// New ICICI

		@Override
		public Map<String, Object> p2aregisterAndroid(String requestJson) {
			Map<String, Object> returnData = new HashMap<>();
			Map<String, String> param = new HashMap<String, String>();
			try{
			//	System.out.println(requestJson);
				JSONObject requestObj = new JSONObject(requestJson);
				if (requestObj.isNull("RequestInput")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty RequestInput Passed.");
				} else{
					JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
					if (requestInputJson.isNull("Authentication")) {
						returnData.put("status", "0");
						returnData.put("message", "Empty Authentication Passed.");
					}else{
						JSONObject authJson = requestInputJson.getJSONObject("Authentication");
						if (authJson.isNull("username") || authJson.isNull("password")) {
							returnData.put("status", "0");
							returnData.put("message", "Username and Password Cannot be null.");
						}else{
							
							param.put("userId", authJson.get("username").toString());
							param.put("password", authJson.get("password").toString());						
							String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
							if (username != null) {
								if (requestInputJson.isNull("P2ARegister")) {
									returnData.put("status", "0");
									returnData.put("message", "Invalid P2ARegister Object");
								} else {
									Map<String, String> inputData = new HashMap<String, String>();
									inputData = UtilityClass.toMap(requestInputJson.getJSONObject("P2ARegister"));
									User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
									returnData = icicidmtService.p2aregistration(inputData, userDetails);
									
								}
							} else {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Authentication");
							}
						
						}
					}
				}
			}catch (Exception e) {
				logger_log.error("p2aregisterAndroid::::::::::::::: " + e);
				returnData.put("status", "0");
				returnData.put("message", "Exception!");
			}
			return returnData;
			
		
		}





		@Override
		public Map<String, Object> p2amoneytransferAndroid(String requestJson) {
			Map<String, Object> returnData = new HashMap<>();
			Map<String, String> param = new HashMap<String, String>();
			try {
			//	System.out.println(requestJson);
				JSONObject requestObj = new JSONObject(requestJson);
				if (requestObj.isNull("RequestInput")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty RequestInput Passed.");
				} else{
					JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
					if (requestInputJson.isNull("Authentication")) {
						returnData.put("status", "0");
						returnData.put("message", "Empty Authentication Passed.");
					}else{
						JSONObject authJson = requestInputJson.getJSONObject("Authentication");
						if (authJson.isNull("username") || authJson.isNull("password")) {
							returnData.put("status", "0");
							returnData.put("message", "Username and Password Cannot be null.");
						}else{
							
							param.put("userId", authJson.get("username").toString());
							param.put("password", authJson.get("password").toString());						
							String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
							if (username != null) {
								if (requestInputJson.isNull("P2AMoneyTransfer")) {
									returnData.put("status", "0");
									returnData.put("message", "Invalid P2AMoneyTransfer Object");
								} else {
									Map<String, String> inputData = new HashMap<String, String>();
									inputData = UtilityClass.toMap(requestInputJson.getJSONObject("P2AMoneyTransfer"));
									User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
									returnData = icicidmtService.p2amoneytransferNew(inputData,userDetails);
									
								}
							} else {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Authentication");
							}
						
						}
					}
				}
			}catch (Exception e) {
				logger_log.error("p2amoneytransferAndroid::::::::::::::::::::"+e);
			}
			return returnData;
		}



		@Override
		public Map<String, Object> getp2aReportAndroid(String requestJson) {
			Map<String, Object> returnData = new HashMap<>();
			Map<String, String> param = new HashMap<String, String>();
			try {
			//	System.out.println(requestJson);
				JSONObject requestObj = new JSONObject(requestJson);
				if (requestObj.isNull("RequestInput")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty RequestInput Passed.");
				} else{
					JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
					if (requestInputJson.isNull("Authentication")) {
						returnData.put("status", "0");
						returnData.put("message", "Empty Authentication Passed.");
					}else{
						JSONObject authJson = requestInputJson.getJSONObject("Authentication");
						if (authJson.isNull("username") || authJson.isNull("password")) {
							returnData.put("status", "0");
							returnData.put("message", "Username and Password Cannot be null.");
						}else{
							
							param.put("userId", authJson.get("username").toString());
							param.put("password", authJson.get("password").toString());						
							String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
							if (username != null) {
								if (requestInputJson.isNull("P2AReport")) {
									returnData.put("status", "0");
									returnData.put("message", "Invalid P2AReport Object");
								} else {
									Map<String, String> inputData = new HashMap<String, String>();
									inputData = UtilityClass.toMap(requestInputJson.getJSONObject("P2AReport"));
									User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
									List<P2ATranferReport> list = icicidmtService.getp2aReport(inputData,userDetails);
									returnData.put("P2ATranferReport",list);
									
								}
							} else {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Authentication");
							}
						
						}
					}
				}
			}catch (Exception e) {
				logger_log.error("ICICIPaymentAndroid::::::::::::::::::::"+e);
			}
			return returnData;
		}
		
		
		@Override
		public Map<String, Object> checkP2AUser(String requestJson) {
			Map<String, Object> returnData = new HashMap<>();
			Map<String, String> param = new HashMap<String, String>();
			try {
			//	System.out.println(requestJson);
				JSONObject requestObj = new JSONObject(requestJson);
				if (requestObj.isNull("RequestInput")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty RequestInput Passed.");
				} else{
					JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
					if (requestInputJson.isNull("Authentication")) {
						returnData.put("status", "0");
						returnData.put("message", "Empty Authentication Passed.");
					}else{
						JSONObject authJson = requestInputJson.getJSONObject("Authentication");
						if (authJson.isNull("username") || authJson.isNull("password")) {
							returnData.put("status", "0");
							returnData.put("message", "Username and Password Cannot be null.");
						}else{
							
							param.put("userId", authJson.get("username").toString());
							param.put("password", authJson.get("password").toString());						
							String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
							if (username != null) {
								
									User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
									returnData = icicidmtService.checkP2AUser(userDetails);	
									
								
							} else {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Authentication");
							}
						
						}
					}
				}
			}catch (Exception e) {
				logger_log.error("ICICIPaymentAndroid::::::::::::::::::::"+e);
			}
			return returnData;
		}




		@Override
		public Map<String, Object> encoreaepstransactionAndroid(String requestJson) {
			Map<String, Object> returnData = new HashMap<>();
			Map<String, String> param = new HashMap<String, String>();
			try {
			//	System.out.println(requestJson);
				JSONObject requestObj = new JSONObject(requestJson);
				if (requestObj.isNull("RequestInput")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty RequestInput Passed.");
				} else{
					JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
					if (requestInputJson.isNull("Authentication")) {
						returnData.put("status", "0");
						returnData.put("message", "Empty Authentication Passed.");
					}else{
						JSONObject authJson = requestInputJson.getJSONObject("Authentication");
						if (authJson.isNull("username") || authJson.isNull("password")) {
							returnData.put("status", "0");
							returnData.put("message", "Username and Password Cannot be null.");
						}else{
							
							param.put("userId", authJson.get("username").toString());
							param.put("password", authJson.get("password").toString());						
							String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
							if (username != null) {
								if (requestInputJson.isNull("EncoreAepsTransaction")) {
									returnData.put("status", "0");
									returnData.put("message", "Invalid EncoreAepsTransaction Object");
								} else {
									Map<String, String> inputData = new HashMap<String, String>();
									inputData = UtilityClass.toMap(requestInputJson.getJSONObject("EncoreAepsTransaction"));
									User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
									
									returnData =fingerpayservice.encorepayaeps(inputData,userDetails);
									
								}
							} else {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Authentication");
							}
						
						}
					}
				}
			}catch (Exception e) {
				logger_log.error("p2amoneytransferAndroid::::::::::::::::::::"+e);
			}
			return returnData;
		}




		@Override
		public Map<String, Object> encoreaadhartransactionAndroid(String requestJson) {
			Map<String, Object> returnData = new HashMap<>();
			Map<String, String> param = new HashMap<String, String>();
			try {
			//	System.out.println(requestJson);
				JSONObject requestObj = new JSONObject(requestJson);
				if (requestObj.isNull("RequestInput")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty RequestInput Passed.");
				} else{
					JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
					if (requestInputJson.isNull("Authentication")) {
						returnData.put("status", "0");
						returnData.put("message", "Empty Authentication Passed.");
					}else{
						JSONObject authJson = requestInputJson.getJSONObject("Authentication");
						if (authJson.isNull("username") || authJson.isNull("password")) {
							returnData.put("status", "0");
							returnData.put("message", "Username and Password Cannot be null.");
						}else{
							
							param.put("userId", authJson.get("username").toString());
							param.put("password", authJson.get("password").toString());						
							String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
							if (username != null) {
								if (requestInputJson.isNull("EncoreAadharTransaction")) {
									returnData.put("status", "0");
									returnData.put("message", "Invalid EncoreAadharTransaction Object");
								} else {
									Map<String, String> inputData = new HashMap<String, String>();
									inputData = UtilityClass.toMap(requestInputJson.getJSONObject("EncoreAadharTransaction"));
									User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
									
									returnData =fingerpayservice.encorepayaadhar(inputData,userDetails);
									
								}
							} else {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Authentication");
							}
						
						}
					}
				}
			}catch (Exception e) {
				logger_log.error("p2amoneytransferAndroid::::::::::::::::::::"+e);
			}
			return returnData;
		}




		@Override
		public Map<String, Object> checkuserEncoreNewAndroid(String request) {
			Map<String, Object> returnData = new HashMap<>();
			Map<String, String> param = new HashMap<String, String>();
			try {
			//	System.out.println(requestJson);
				JSONObject requestObj = new JSONObject(request);
				if (requestObj.isNull("RequestInput")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty RequestInput Passed.");
				} else{
					JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
					if (requestInputJson.isNull("Authentication")) {
						returnData.put("status", "0");
						returnData.put("message", "Empty Authentication Passed.");
					}else{
						JSONObject authJson = requestInputJson.getJSONObject("Authentication");
						if (authJson.isNull("username") || authJson.isNull("password")) {
							returnData.put("status", "0");
							returnData.put("message", "Username and Password Cannot be null.");
						}else{
							
							param.put("userId", authJson.get("username").toString());
							param.put("password", authJson.get("password").toString());						
							String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
							if (username != null) {
								if (requestInputJson.isNull("RemitterCheck")) {
									returnData.put("status", "0");
									returnData.put("message", "Invalid RemitterCheck Object");
								} else {
									Map<String, String> inputData = new HashMap<String, String>();
									inputData = UtilityClass.toMap(requestInputJson.getJSONObject("RemitterCheck"));
									returnData =encoremoneyservicenew.checkuserEncoreNew(inputData);
									
								}
							} else {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Authentication");
							}
						
						}
					}
				}
			}catch (Exception e) {
				logger_log.error("checkuserEncoreNewAndroid::::::::::::::::::::"+e);
			}
			return returnData;
		}




		@Override
		public Map<String, Object> remmiterRegisterAndroid(String request) {
			Map<String, Object> returnData = new HashMap<>();
			Map<String, String> param = new HashMap<String, String>();
			try {
			//	System.out.println(requestJson);
				JSONObject requestObj = new JSONObject(request);
				if (requestObj.isNull("RequestInput")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty RequestInput Passed.");
				} else{
					JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
					if (requestInputJson.isNull("Authentication")) {
						returnData.put("status", "0");
						returnData.put("message", "Empty Authentication Passed.");
					}else{
						JSONObject authJson = requestInputJson.getJSONObject("Authentication");
						if (authJson.isNull("username") || authJson.isNull("password")) {
							returnData.put("status", "0");
							returnData.put("message", "Username and Password Cannot be null.");
						}else{
							
							param.put("userId", authJson.get("username").toString());
							param.put("password", authJson.get("password").toString());						
							String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
							if (username != null) {
								if (requestInputJson.isNull("RemitterRegister")) {
									returnData.put("status", "0");
									returnData.put("message", "Invalid RemitterRegister Object");
								} else {
									Map<String, String> inputData = new HashMap<String, String>();
									inputData = UtilityClass.toMap(requestInputJson.getJSONObject("RemitterRegister"));
									returnData =encoremoneyservicenew.remmiterRegisterEncoreNew(inputData);
									
								}
							} else {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Authentication");
							}
						
						}
					}
				}
			}catch (Exception e) {
				logger_log.error("p2amoneytransferAndroid::::::::::::::::::::"+e);
			}
			return returnData;
		}




		@Override
		public Map<String, Object> OtpverifyAndroid(String request) {
			Map<String, Object> returnData = new HashMap<>();
			Map<String, String> param = new HashMap<String, String>();
			try {
			//	System.out.println(requestJson);
				JSONObject requestObj = new JSONObject(request);
				if (requestObj.isNull("RequestInput")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty RequestInput Passed.");
				} else{
					JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
					if (requestInputJson.isNull("Authentication")) {
						returnData.put("status", "0");
						returnData.put("message", "Empty Authentication Passed.");
					}else{
						JSONObject authJson = requestInputJson.getJSONObject("Authentication");
						if (authJson.isNull("username") || authJson.isNull("password")) {
							returnData.put("status", "0");
							returnData.put("message", "Username and Password Cannot be null.");
						}else{
							
							param.put("userId", authJson.get("username").toString());
							param.put("password", authJson.get("password").toString());						
							String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
							if (username != null) {
								if (requestInputJson.isNull("OtpVerify")) {
									returnData.put("status", "0");
									returnData.put("message", "Invalid OtpVerify Object");
								} else {
									Map<String, String> inputData = new HashMap<String, String>();
									inputData = UtilityClass.toMap(requestInputJson.getJSONObject("OtpVerify"));
									returnData =encoremoneyservicenew.OTPVERIFICATION(inputData);
									
								}
							} else {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Authentication");
							}
						
						}
					}
				}
			}catch (Exception e) {
				logger_log.error("p2amoneytransferAndroid::::::::::::::::::::"+e);
			}
			return returnData;
		}




		@Override
		public Map<String, Object> addBeneficiaryAndroid(String request) {
			Map<String, Object> returnData = new HashMap<>();
			Map<String, String> param = new HashMap<String, String>();
			try {
			//	System.out.println(requestJson);
				JSONObject requestObj = new JSONObject(request);
				if (requestObj.isNull("RequestInput")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty RequestInput Passed.");
				} else{
					JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
					if (requestInputJson.isNull("Authentication")) {
						returnData.put("status", "0");
						returnData.put("message", "Empty Authentication Passed.");
					}else{
						JSONObject authJson = requestInputJson.getJSONObject("Authentication");
						if (authJson.isNull("username") || authJson.isNull("password")) {
							returnData.put("status", "0");
							returnData.put("message", "Username and Password Cannot be null.");
						}else{
							
							param.put("userId", authJson.get("username").toString());
							param.put("password", authJson.get("password").toString());						
							String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
							if (username != null) {
								if (requestInputJson.isNull("AddBeneficiary")) {
									returnData.put("status", "0");
									returnData.put("message", "Invalid AddBeneficiary Object");
								} else {
									Map<String, String> inputData = new HashMap<String, String>();
									inputData = UtilityClass.toMap(requestInputJson.getJSONObject("AddBeneficiary"));
									returnData =encoremoneyservicenew.addBeneficiaryEncoreNew(inputData);
									
								}
							} else {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Authentication");
							}
						
						}
					}
				}
			}catch (Exception e) {
				logger_log.error("addBeneficiaryAndroid::::::::::::::::::::"+e);
			}
			return returnData;
		}




		@Override
		public Map<String, Object> deleteBeneficiaryAndroid(String request) {
			Map<String, Object> returnData = new HashMap<>();
			Map<String, String> param = new HashMap<String, String>();
			try {
			//	System.out.println(requestJson);
				JSONObject requestObj = new JSONObject(request);
				if (requestObj.isNull("RequestInput")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty RequestInput Passed.");
				} else{
					JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
					if (requestInputJson.isNull("Authentication")) {
						returnData.put("status", "0");
						returnData.put("message", "Empty Authentication Passed.");
					}else{
						JSONObject authJson = requestInputJson.getJSONObject("Authentication");
						if (authJson.isNull("username") || authJson.isNull("password")) {
							returnData.put("status", "0");
							returnData.put("message", "Username and Password Cannot be null.");
						}else{
							
							param.put("userId", authJson.get("username").toString());
							param.put("password", authJson.get("password").toString());						
							String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
							if (username != null) {
								if (requestInputJson.isNull("DeleteBeneficiary")) {
									returnData.put("status", "0");
									returnData.put("message", "Invalid DeleteBeneficiary Object");
								} else {
									Map<String, String> inputData = new HashMap<String, String>();
									inputData = UtilityClass.toMap(requestInputJson.getJSONObject("DeleteBeneficiary"));
									
									
									returnData =encoremoneyservicenew.deleteBeneficiaryEncoreNew(inputData);
									
								}
							} else {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Authentication");
							}
						
						}
					}
				}
			}catch (Exception e) {
				logger_log.error("deleteBeneficiaryAndroid::::::::::::::::::::"+e);
			}
			return returnData;
		}




		@Override
		public Map<String, Object> ValidateBeneficiaryAndroid(String request) {
			Map<String, Object> returnData = new HashMap<>();
			Map<String, String> param = new HashMap<String, String>();
			try {
			//	System.out.println(requestJson);
				JSONObject requestObj = new JSONObject(request);
				if (requestObj.isNull("RequestInput")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty RequestInput Passed.");
				} else{
					JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
					if (requestInputJson.isNull("Authentication")) {
						returnData.put("status", "0");
						returnData.put("message", "Empty Authentication Passed.");
					}else{
						JSONObject authJson = requestInputJson.getJSONObject("Authentication");
						if (authJson.isNull("username") || authJson.isNull("password")) {
							returnData.put("status", "0");
							returnData.put("message", "Username and Password Cannot be null.");
						}else{
							
							param.put("userId", authJson.get("username").toString());
							param.put("password", authJson.get("password").toString());						
							String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
							if (username != null) {
								if (requestInputJson.isNull("ValidateBeneficiary")) {
									returnData.put("status", "0");
									returnData.put("message", "Invalid ValidateBeneficiary Object");
								} else {
									Map<String, String> inputData = new HashMap<String, String>();
									inputData = UtilityClass.toMap(requestInputJson.getJSONObject("ValidateBeneficiary"));
									User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
									
									returnData =encoremoneyservicenew.ValidateBeneficiaryEncoreNew(inputData,userDetails);
									
								}
							} else {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Authentication");
							}
						
						}
					}
				}
			}catch (Exception e) {
				logger_log.error("ValidateBeneficiaryAndroid::::::::::::::::::::"+e);
			}
			return returnData;
		}




		@Override
		public Map<String, Object> MoneyTransferAndroid(String request) {
			Map<String, Object> returnData = new HashMap<>();
			Map<String, String> param = new HashMap<String, String>();
			try {
			//	System.out.println(requestJson);
				JSONObject requestObj = new JSONObject(request);
				if (requestObj.isNull("RequestInput")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty RequestInput Passed.");
				} else{
					JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
					if (requestInputJson.isNull("Authentication")) {
						returnData.put("status", "0");
						returnData.put("message", "Empty Authentication Passed.");
					}else{
						JSONObject authJson = requestInputJson.getJSONObject("Authentication");
						if (authJson.isNull("username") || authJson.isNull("password")) {
							returnData.put("status", "0");
							returnData.put("message", "Username and Password Cannot be null.");
						}else{
							
							param.put("userId", authJson.get("username").toString());
							param.put("password", authJson.get("password").toString());						
							String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
							if (username != null) {
								if (requestInputJson.isNull("MoneyTransfer")) {
									returnData.put("status", "0");
									returnData.put("message", "Invalid MoneyTransfer Object");
								} else {
									Map<String, String> inputData = new HashMap<String, String>();
									inputData = UtilityClass.toMap(requestInputJson.getJSONObject("MoneyTransfer"));
									User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
									
									returnData =encoremoneyservicenew.impsMoneyTransferEncoreandroid(inputData,userDetails);
									
								}
							} else {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Authentication");
							}
						
						}
					}
				}
			}catch (Exception e) {
				logger_log.error("ValidateBeneficiaryAndroid::::::::::::::::::::"+e);
			}
			return returnData;
		}




		@Override
		public Map<String, Object> ResendOtpAndroid(String request) {
			Map<String, Object> returnData = new HashMap<>();
			Map<String, String> param = new HashMap<String, String>();
			try {
			//	System.out.println(requestJson);
				JSONObject requestObj = new JSONObject(request);
				if (requestObj.isNull("RequestInput")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty RequestInput Passed.");
				} else{
					JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
					if (requestInputJson.isNull("Authentication")) {
						returnData.put("status", "0");
						returnData.put("message", "Empty Authentication Passed.");
					}else{
						JSONObject authJson = requestInputJson.getJSONObject("Authentication");
						if (authJson.isNull("username") || authJson.isNull("password")) {
							returnData.put("status", "0");
							returnData.put("message", "Username and Password Cannot be null.");
						}else{
							
							param.put("userId", authJson.get("username").toString());
							param.put("password", authJson.get("password").toString());						
							String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
							if (username != null) {
								if (requestInputJson.isNull("ResendOtp")) {
									returnData.put("status", "0");
									returnData.put("message", "Invalid ResendOtp Object");
								} else {
									Map<String, String> inputData = new HashMap<String, String>();
									inputData = UtilityClass.toMap(requestInputJson.getJSONObject("ResendOtp"));
									
									
									returnData =encoremoneyservicenew.resendOtp(inputData);
									
								}
							} else {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Authentication");
							}
						
						}
					}
				}
			}catch (Exception e) {
				logger_log.error("ValidateBeneficiaryAndroid::::::::::::::::::::"+e);
			}
			return returnData;
		}




		@Override
		public Map<String, Object> minitransactionNewAndroid(String requestJson) {
			Map<String, Object> returnData = new HashMap<>();
			Map<String, String> param = new HashMap<String, String>();
			try {
			//	System.out.println(requestJson);
				JSONObject requestObj = new JSONObject(requestJson);
				if (requestObj.isNull("RequestInput")) {
					returnData.put("status", "0");
					returnData.put("message", "Empty RequestInput Passed.");
				} else{
					JSONObject requestInputJson = requestObj.getJSONObject("RequestInput");
					if (requestInputJson.isNull("Authentication")) {
						returnData.put("status", "0");
						returnData.put("message", "Empty Authentication Passed.");
					}else{
						JSONObject authJson = requestInputJson.getJSONObject("Authentication");
						if (authJson.isNull("username") || authJson.isNull("password")) {
							returnData.put("status", "0");
							returnData.put("message", "Username and Password Cannot be null.");
						}else{
							
							param.put("userId", authJson.get("username").toString());
							param.put("password", authJson.get("password").toString());						
							String username = authenticationCommandCenter.loginUser(CustomSqlQuery.checkLogin, param);
							if (username != null) {
								if (requestInputJson.isNull("MiniStatement")) {
									returnData.put("status", "0");
									returnData.put("message", "Invalid MiniStatement Object");
								} else {
									Map<String, String> inputData = new HashMap<String, String>();
									inputData = UtilityClass.toMap(requestInputJson.getJSONObject("MiniStatement"));
									User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
									
									returnData =fingerpayservice.ministatementandroid(inputData,userDetails);
									
								}
							} else {
								returnData.put("status", "0");
								returnData.put("message", "Invalid Authentication");
							}
						
						}
					}
				}
			}catch (Exception e) {
				logger_log.error("encoreaadhartransactionAndroid::::::::::::::::::::"+e);
			}
			return returnData;
		}

}
