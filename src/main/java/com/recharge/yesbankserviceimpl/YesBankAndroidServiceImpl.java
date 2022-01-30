package com.recharge.yesbankserviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.businessdao.AndroidService;
import com.recharge.businessdao.AuthenticationCommandCenter;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.model.AEPSLog;
import com.recharge.model.AEPSUserMap;
import com.recharge.model.MicroatmUser;
import com.recharge.model.MicroatmUserNew;
import com.recharge.model.User;
import com.recharge.servicedao.AEPSLogDao;
import com.recharge.servicedao.AEPSUserMapDao;
import com.recharge.servicedao.UserDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.UtilityClass;
import com.recharge.yesbankservice.YesBankBusiness;
import com.recharge.yesbankservicedao.YesBankAndroidService;
import com.recharge.servicedao.MicroatmuserNewDao;
@Service("yesbankandroidService")
public class YesBankAndroidServiceImpl implements YesBankAndroidService {
	private static final Logger logger_log = Logger.getLogger(YesBankAndroidService.class);
	
	@Autowired AuthenticationCommandCenter authenticationCommandCenter;
	@Autowired YesBankBusiness yesbankservice;
	@Autowired UserDao userDao;
	@Autowired AEPSUserMapDao aepsuserdao;
	@Autowired AEPSLogDao aepslogdao;
	@Autowired MicroatmuserNewDao  MicroatmuserNewDao;

	@Override
	public Map<String, Object> yesBankTransactionAndroid(String requestJson) {
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
							if (requestInputJson.isNull("YesBankTranaction")) {
								returnData.put("status", "0");
								returnData.put("message", "YesBankTranaction Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("YesBankTranaction"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = yesbankservice.yesBankTransAndroid(inputData,userDetails);
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
	public Map<String, Object> yesBanksearchAndroid(String requestJson) {
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
							if (requestInputJson.isNull("YesBankSearch")) {
								returnData.put("status", "0");
								returnData.put("message", "YesBankTranaction Object");
							} else {
								Map<String, Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesBankSearch"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = yesbankservice.searchCustomer(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message", "Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("yesBanksearchAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> yesBankregisterAndroid(String requestJson) {
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
							if (requestInputJson.isNull("YesBankRegistration")) {
								returnData.put("status", "0");
								returnData.put("message", "YesBankTranaction Object");
							} else {
								Map<String, Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesBankRegistration"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = yesbankservice.Customerregister(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("yesBankregisterAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> yesBankrddatahashAndroid(String requestJson) {
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
							if (requestInputJson.isNull("YesBankPID")) {
								returnData.put("status", "0");
								returnData.put("message", "YesBankPID Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("YesBankPID"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = yesbankservice.yesBankpidAndroid(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("yesBankrddatahashAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getYesBankAEPSReportAndroid(String requestJson) {
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
							if (requestInputJson.isNull("YesBankReport")) {
								returnData.put("status", "0");
								returnData.put("message", "YesBankReport Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("YesBankReport"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = yesbankservice.getYesBankAEPSReport(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("yesBankrddatahashAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> checkuserYesBankAndroid(String requestJson) {
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
								returnData = yesbankservice.checkuserYesBank(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("yesBankrddatahashAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> yesBankdmrregisterAndroid(String requestJson) {
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
								returnData = yesbankservice.yesBankRemitterRegister(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("yesBankrddatahashAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> yesBankremitterverifyAndroid(String requestJson) {
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
							if (requestInputJson.isNull("YesBankRemitterVerify")) {
								returnData.put("status","0");
								returnData.put("message","YesBankRemitterVerify Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesBankRemitterVerify"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = yesbankservice.remitterverifyAndroid(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("yesBankremitterverifyAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> yesBankdeletebeneAndroid(String requestJson) {
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
								returnData = yesbankservice.deleteyesBankbeneAndroid(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("yesBankremitterverifyAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> VerifyDeleteYesBaneAndroid(String requestJson) {
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
							if (requestInputJson.isNull("YesBankDeleteBeneVerify")) {
								returnData.put("status","0");
								returnData.put("message","YesBankDeleteBeneVerify Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesBankDeleteBeneVerify"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = yesbankservice.VerifyDeleteYesBaneAndroid(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("VerifyDeleteYesBaneAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> addYesBaneAndroid(String requestJson) {
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
							if (requestInputJson.isNull("YesBankAddBene")) {
								returnData.put("status","0");
								returnData.put("message","YesBankAddBene Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesBankAddBene"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = yesbankservice.addYesBaneAndroid(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("addYesBaneAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> verifyYesBaneAndroid(String requestJson) {
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
							if (requestInputJson.isNull("YesBankVerifyBene")) {
								returnData.put("status","0");
								returnData.put("message","YesBankVerifyBene Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesBankVerifyBene"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = yesbankservice.verifyYesBaneAndroid(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("verifyYesBaneAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> YesmoneytransferAndroid(String requestJson) {
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
								returnData = yesbankservice.YesmoneytransferAndroid(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("YesmoneytransferAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> yesBankRDHashAndroid(String requestJson) {
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
							if (requestInputJson.isNull("YesRDHash")) {
								returnData.put("status","0");
								returnData.put("message","YesRDHash Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("YesRDHash"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = yesbankservice.YesmoneytransferAndroid(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("YesmoneytransferAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	
	@Override
	public Map<String, Object> fetchagentcode(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		Map<String, Object> pam = new HashMap<>();
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
							if (requestInputJson.isNull("fetchagentcode")) {
								returnData.put("status","0");
								returnData.put("message","fetchagentcode Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("fetchagentcode"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								pam.put("username",userDetails.getUserId());
								List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusername",pam);
								List<MicroatmUserNew> list3 = MicroatmuserNewDao.getMicroatmUserNewByNamedQuery("getmicroatmnewUserbyUserid",pam);
								if(list2.isEmpty()){
									returnData.put("status", "0");
									returnData.put("message","Donot Have AgentCode");
								}else{
									returnData.put("status", "1");
									returnData.put("agencode",list2);
									returnData.put("micratmuser",list3);
								}
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("fetchagentcode::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> sendaepsreq(String requestJson) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		Map<String, Object> pam = new HashMap<>();
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
							if (requestInputJson.isNull("sendaepsreq")) {
								returnData.put("status","0");
								returnData.put("message","sendaepsreq Object");
							} else {
								Map<String,Object> inputData = UtilityClass
										.toMapObj(requestInputJson.getJSONObject("sendaepsreq"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								
								AEPSLog aepslog=new AEPSLog(inputData.get("ORDER_ID").toString(), inputData.get("AID").toString(), "PENDING", inputData.get("ST").toString(), userDetails.getUserId(), GenerateRandomNumber.getCurrentDate(),GenerateRandomNumber.getCurrentTime(),inputData.get("processingCode").toString(),inputData.get("mobile").toString(),"0",Double.parseDouble(inputData.get("TXN_AMOUNT").toString()));
								boolean flag=aepslogdao.insertAEPSLog(aepslog);
								if(flag){
									returnData.put("status", "1");
								}else{
									returnData.put("status", "0");
								}
								
								/*pam.put("username",userDetails.getUserId());
								pam.put("api","YesBank");
								List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",pam);
								if(list2.isEmpty()){
									returnData.put("status", "0");
									returnData.put("message","Donot Have AgentCode");
								}else{
									returnData.put("status", "1");
									returnData.put("agencode",list2);
								}*/
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("fetchagentcode::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> miniStatementAndroid(String requestJson) {
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
							if (requestInputJson.isNull("MiniStatement")) {
								returnData.put("status","0");
								returnData.put("message","MiniStatement Object");
							} else {
								Map<String,String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("MiniStatement"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								
								returnData = yesbankservice.miniStatementAndroid(inputData,userDetails);
								
								
								/*pam.put("username",userDetails.getUserId());
								pam.put("api","YesBank");
								List<AEPSUserMap> list2 = aepsuserdao.getAEPSUserMapByNamedQuery("getAEPSbyusernameandapi",pam);
								if(list2.isEmpty()){
									returnData.put("status", "0");
									returnData.put("message","Donot Have AgentCode");
								}else{
									returnData.put("status", "1");
									returnData.put("agencode",list2);
								}*/
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("miniSettlementAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getMicroAEPSReportAndroid(String request) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{

			JSONObject requestObj = new JSONObject(request);
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
							if (requestInputJson.isNull("MicroAEPSReport")) {
								returnData.put("status", "0");
								returnData.put("message", "MicroAEPSReport Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("MicroAEPSReport"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = yesbankservice.getMicroATMReport(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("getMicroATMReport::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}
	
	@Override
	public Map<String, Object> getFingerAEPSReportAndroid(String request) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{

			JSONObject requestObj = new JSONObject(request);
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
							if (requestInputJson.isNull("FingerAEPSReport")) {
								returnData.put("status", "0");
								returnData.put("message", "FingerAEPSReport Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("FingerAEPSReport"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = yesbankservice.getFingerATMReport(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("getFingerAEPSReportAndroid::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> getMicroAEPSReportAndroidnew(String request) {
		Map<String, Object> returnData = new HashMap<>();
		Map<String, String> param = new HashMap<String, String>();
		try{

			JSONObject requestObj = new JSONObject(request);
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
							if (requestInputJson.isNull("MicroAEPSReport")) {
								returnData.put("status", "0");
								returnData.put("message", "MicroAEPSReportNew Object");
							} else {
								Map<String, String> inputData = UtilityClass
										.toMap(requestInputJson.getJSONObject("MicroAEPSReport"));
								User userDetails = userDao.getUserByUserId(authJson.get("username").toString());
								returnData = yesbankservice.getMicroATMReportNew(inputData,userDetails);
							}

						} else {
							returnData.put("status", "0");
							returnData.put("message","Invalid Authentication");
						}
					}

				}

			}
			
		}catch (Exception e) {
			logger_log.error("getMicroATMReport::::::::::::::: " + e);
			returnData.put("status", "0");
			returnData.put("message", "Exception!");
		}
		return returnData;
	}

	


}
