package com.encore.moneyapi;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.businessdao.AuthenticationCommandCenter;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.model.User;
import com.recharge.servicedao.UserDao;
import com.recharge.utill.UtilityClass;


@Service("MoneyAndroidBussinnessDao")
public class MoneyAndroidBussinnessImpl implements MoneyAndroidBussinnessDao{
	
	private static final Logger logger_log = Logger.getLogger(MoneyAndroidBussinnessDao.class);
	@Autowired AuthenticationCommandCenter authenticationCommandCenter;
	@Autowired UserDao userDao;
	@Autowired EncoreMoneyBusiness EncoreMoneyBusiness;
	
	@Override
	public Map<String, Object> checkuserPaytmAndroid(String requestJson) {
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
							if (requestInputJson.isNull("YesBankUser")) {
								returnData.put("status","0");
								returnData.put("message","YesBankUser Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesBankUser"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = EncoreMoneyBusiness.checkuserEncore(inputData);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("checkuserPaytm::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	

	
	
	@Override
	public Map<String, Object> PaytmRemitterRegisterAndroid(String requestJson) {
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
							if (requestInputJson.isNull("YesBankDMRRegister")) {
								returnData.put("status","0");
								returnData.put("message","YesBankDMRRegister Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesBankDMRRegister"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = EncoreMoneyBusiness.remmiterRegisterEncore(inputData);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("PaytmRemitterRegister::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> generatePaytmotp(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{/*

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
							if (requestInputJson.isNull("OTPGENERATE")) {
								returnData.put("status","0");
								returnData.put("message","OTPGENERATE Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("OTPGENERATE"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = EncoreMoneyBusiness.generatePaytmotpandroid(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		*/}catch (Exception e) {
			logger_log.error("PaytmRemitterRegister::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> PaytmremitterverifyAndroid(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{/*

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
							if (requestInputJson.isNull("YesBankDMRRegisterVerify")) {
								returnData.put("status","0");
								returnData.put("message","YesBankDMRRegisterVerify Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesBankDMRRegisterVerify"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = EncoreMoneyBusiness.PaytmremitterverifyAndroid(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		*/}catch (Exception e) {
			logger_log.error("PaytmremitterverifyAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	
	
	@Override
	public Map<String, Object> PaytmNewBeneficiaryAndroid(String requestJson) {
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
							if (requestInputJson.isNull("YesBankADDBene")) {
								returnData.put("status","0");
								returnData.put("message","YesBankADDBene Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesBankADDBene"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = EncoreMoneyBusiness.addBeneficiaryEncore(inputData);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("PaytmNewBeneficiaryAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> PaytmValidateBeneficiaryAndroid(String requestJson) {
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
							if (requestInputJson.isNull("YesBankValiBene")) {
								returnData.put("status","0");
								returnData.put("message","YesBankValiBene Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesBankValiBene"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = EncoreMoneyBusiness.ValidateBeneficiaryEncore(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("PaytmValidateBeneficiaryAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> PaytmVerifyBeneficiaryAndroid(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{/*

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
							if (requestInputJson.isNull("YesBankBeneVerify")) {
								returnData.put("status","0");
								returnData.put("message","YesBankBeneVerify Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesBankBeneVerify"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = EncoreMoneyBusiness.PaytmVerifyBeneficiaryAndroid(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		*/}catch (Exception e) {
			logger_log.error("PaytmVerifyBeneficiaryAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> deletePaytmbeneAndroid(String requestJson) {
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
							if (requestInputJson.isNull("YesBankDeleteBene")) {
								returnData.put("status","0");
								returnData.put("message","YesBankDeleteBene Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesBankDeleteBene"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = EncoreMoneyBusiness.deleteBeneficiaryEncore(inputData);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("deletePaytmbeneAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> VerifyDeletePaytmBaneAndroid(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{/*

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
							if (requestInputJson.isNull("YesBankVerifyDelBene")) {
								returnData.put("status","0");
								returnData.put("message","YesBankVerifyDelBene Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesBankVerifyDelBene"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = EncoreMoneyBusiness.VerifyDeletePaytmBaneAndroid(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		*/}catch (Exception e) {
			logger_log.error("VerifyDeletePaytmBaneAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> PaytmMoneytransferAndroid(String requestJson) {
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
							if (requestInputJson.isNull("YesMoneyTransfer")) {
								returnData.put("status","0");
								returnData.put("message","YesMoneyTransfer Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesMoneyTransfer"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = EncoreMoneyBusiness.impsMoneyTransferEncoreandroid(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("PaytmMoneytransferAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> checkImpsStatusPandroid(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{/*

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
							if (requestInputJson.isNull("YesMoneyTransferstatus")) {
								returnData.put("status","0");
								returnData.put("message","YesMoneyTransferstatus Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesMoneyTransferstatus"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = EncoreMoneyBusiness.checkImpsStatusP(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		*/}catch (Exception e) {
			logger_log.error("PaytmMoneytransferAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
}
