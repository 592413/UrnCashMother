package com.recharge.businessimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.businessdao.CommissionService;
import com.recharge.businessdao.CustomQueryServiceLogic;
import com.recharge.businessdao.ReportService;
import com.recharge.businessdao.RetailerService;
import com.recharge.customModel.TransactionReport;
import com.recharge.customModel.UserWalletReport;
import com.recharge.customModel.UtilityReport;
import com.recharge.customModel.APPReport;
import com.recharge.customModel.BalanceTransfer;
import com.recharge.customModel.ComplainDetails;
import com.recharge.customModel.EarningSurchargeReport;
import com.recharge.customModel.InsuranceReport;
import com.recharge.customModel.P2ATranferReport;
import com.recharge.customModel.RechargeReport;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.model.AckSlipAttchment;
import com.recharge.model.Ezpayapplicationform;
import com.recharge.model.NSDLPan;
import com.recharge.model.Operator;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.Transactiondetails;
import com.recharge.model.User;
import com.recharge.model.UserBankDetails;
import com.recharge.servicedao.AckSlipAttachmentDao;
import com.recharge.servicedao.NSDLpanDao;
import com.recharge.servicedao.OperatorDao;
import com.recharge.servicedao.TransactiondetailsDao;
import com.recharge.servicedao.UserBankDetailsDao;
import com.recharge.servicedao.UserDao;
import com.recharge.utill.UtilityClass;
import com.recharge.servicedao.ApplicationDao;
/**
 * @Class ReportServiceImpl which implements ReportService interface
 * Contains method for the Report Displaying o
 * @author Md Rahim
 *
 */
@Service("reportService")
public class ReportServiceImpl implements ReportService {
	private static final Logger logger_log = Logger.getLogger(ReportService.class);
	@Autowired TransactiondetailsDao transactiondetailsDao;
	@Autowired CustomQueryServiceLogic customQueryServiceLogic;
	@Autowired NSDLpanDao NSDLpanDao;
	@Autowired AckSlipAttachmentDao AckSlipAttachmentDao;
	@Autowired UserDao userDao;
	@Autowired CommissionService commissionService;
	@Autowired OperatorDao operatorDao;
	@Autowired ApplicationDao ApplicationDao;
	@Autowired UserBankDetailsDao UserBankDetailsDao;
	
	
	@Override
	public List<TransactionReport> getTransactionReport(Map<String, String> request, User userDetails) {
		List<TransactionReport> list = new ArrayList<TransactionReport>();
		Map<String, String> param = null;
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1) {
					// Admin check the report for the user by roleId or particular result
					if(request.get("userType").equals("0")) {
						param = new HashMap<String, String>();
						//param.put("wlId", "ADMIN");
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));
						// get Transaction Report of all User.
						//list = transactiondetailsDao.getTransactionDetailsByNamedQuery("getTransactionReportForAll", param );
						list = customQueryServiceLogic.getTransactionReport(CustomSqlQuery.getTransactionReportForAll, param);
					} else {						
						// get Transaction Report for particular User
						param = new HashMap<String, String>();
						param.put("userId", request.get("userId"));
						param.put("wlId", "ADMIN");
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));
						list = customQueryServiceLogic.getTransactionReport(CustomSqlQuery.getTransactionReportForUser, param);
					}

				} else if (userDetails.getRoleId() == 2) {
					// Admin check the report for the user by roleId or particular result
					if(request.get("userType").equals("0")) {
						param = new HashMap<String, String>();
						param.put("wlId", userDetails.getWlId());
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));
						// get Transaction Report of all User.
						//list = transactiondetailsDao.getTransactionDetailsByNamedQuery("getTransactionReportForAll", param );
						list = customQueryServiceLogic.getTransactionReport(CustomSqlQuery.getTransactionReportForAllByReseller, param);
					} else {						
						// get Transaction Report for particular User
						param = new HashMap<String, String>();
						param.put("userId", request.get("userId"));
						param.put("wlId", userDetails.getWlId());
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));
						list = customQueryServiceLogic.getTransactionReport(CustomSqlQuery.getTransactionReportForUser, param);
					}

				} else {
					param = new HashMap<String, String>();
					param.put("userId", userDetails.getUserId());
					param.put("wlId", userDetails.getWlId());
					param.put("startDate", request.get("startDate"));
					param.put("endDate", request.get("endDate"));
					list = customQueryServiceLogic.getTransactionReport(CustomSqlQuery.getTransactionReportForUser, param);
				}
				if(list == null){
					list = new ArrayList<TransactionReport>();
				}
			}else {
				list = new ArrayList<TransactionReport>();	
			}
		}
		catch (Exception e) {
			logger_log.error("getTransactionReport :::::::::::: "+e);
			list = new ArrayList<TransactionReport>();			
		}
		return list;
	}

	@Override
	public List<RechargeReport> getRechargeReport(Map<String, String> request, User userDetails) {
		List<RechargeReport> list = new ArrayList<RechargeReport>();
		Map<String, String> param = null;
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1) {
					if (request.get("userType").equals("0")) {
						if (request.get("status").equals("All") && request.get("operator").equals("0")) {
							// get Recharge Report for Status and Operator All based on the date
							param = new HashMap<String, String>();							
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForAllByAdmin, param);
						} else if (request.get("status").equals("All") && !request.get("operator").equals("0")) {
							param = new HashMap<String, String>();							
							param.put("operatorId", request.get("operator"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForOperatorByAdmin, param);
						} else if (!request.get("status").equals("All") && request.get("operator").equals("0")) {
							param = new HashMap<String, String>();							
							param.put("status", request.get("status"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForStatusByAdmin, param);
						} else {
							param = new HashMap<String, String>();							
							param.put("status", request.get("status"));
							param.put("operatorId", request.get("operator"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportFilterByAdmin, param);
						}
					} else {

						if (request.get("status").equals("All") && request.get("operator").equals("0")) {
							// get Recharge Report for Status and Operator All based on the date
							param = new HashMap<String, String>();
							param.put("userId", request.get("userId"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForAllbyUser, param);						
						} else if(request.get("status").equals("All") && !request.get("operator").equals("0")){
							param = new HashMap<String, String>();
							param.put("userId", request.get("userId"));
							param.put("operatorId", request.get("operator"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForOperatorByUser, param);
						} else if(!request.get("status").equals("All") && request.get("operator").equals("0")){
							param = new HashMap<String, String>();
							param.put("userId", request.get("userId"));
							param.put("status", request.get("status"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForStatusByUser, param);
						} else {
							param = new HashMap<String, String>();
							param.put("userId", request.get("userId"));
							param.put("status", request.get("status"));
							param.put("operatorId", request.get("operator"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForStatusByUser, param);						
						}					
					}
				} else if (userDetails.getRoleId() == 2) {
					if (request.get("userType").equals("0")) {
						if (request.get("status").equals("All") && request.get("operator").equals("0")) {
							// get Recharge Report for Status and Operator All based on the date
							param = new HashMap<String, String>();	
							param.put("wlId", userDetails.getWlId());
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForAllByReseller, param);
						} else if (request.get("status").equals("All") && !request.get("operator").equals("0")) {
							param = new HashMap<String, String>();							
							param.put("operatorId", request.get("operator"));
							param.put("wlId", userDetails.getWlId());
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForOperatorByReseller, param);
						} else if (!request.get("status").equals("All") && request.get("operator").equals("0")) {
							param = new HashMap<String, String>();
							param.put("wlId", userDetails.getWlId());
							param.put("status", request.get("status"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForStatusByReseller, param);
						} else {
							param = new HashMap<String, String>();	
							param.put("wlId", userDetails.getWlId());
							param.put("status", request.get("status"));
							param.put("operatorId", request.get("operator"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportFilterByReseller, param);
						}
					} else {
						if (request.get("status").equals("All") && request.get("operator").equals("0")) {
							// get Recharge Report for Status and Operator All based on the date
							param = new HashMap<String, String>();
							param.put("userId", request.get("userId"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForAllbyUser, param);						
						} else if(request.get("status").equals("All") && !request.get("operator").equals("0")){
							param = new HashMap<String, String>();
							param.put("userId", request.get("userId"));
							param.put("operatorId", request.get("operator"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForOperatorByUser, param);
						} else if(!request.get("status").equals("All") && request.get("operator").equals("0")){
							param = new HashMap<String, String>();
							param.put("userId", request.get("userId"));
							param.put("status", request.get("status"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForStatusByUser, param);
						} else {
							param = new HashMap<String, String>();
							param.put("userId", request.get("userId"));
							param.put("status", request.get("status"));
							param.put("operatorId", request.get("operator"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForStatusByUser, param);						
						}					
					}
				} else if (userDetails.getRoleId() == 3 || userDetails.getRoleId() == 4) {
					if(request.get("userType").equals("0")){
						if (request.get("status").equals("All") && request.get("operator").equals("0")) {
							// get Recharge Report for Status and Operator All based on the date
							param = new HashMap<String, String>();
							param.put("userId", userDetails.getUserId());
							param.put("uplineId", userDetails.getUserId());							
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportforAllBySD, param);
							
						} else if (request.get("status").equals("All") && !request.get("operator").equals("0")) {
							param = new HashMap<String, String>();							
							param.put("operatorId", request.get("operator"));
							param.put("userId", userDetails.getUserId());
							param.put("uplineId", userDetails.getUserId());
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportforAllAndOperatorBySD, param);
						} else if (!request.get("status").equals("All") && request.get("operator").equals("0")) {
							param = new HashMap<String, String>();							
							param.put("status", request.get("status"));
							param.put("userId", userDetails.getUserId());
							param.put("uplineId", userDetails.getUserId());
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportforAllAndStatusBySD, param);
						} else {
							param = new HashMap<String, String>();							
							param.put("status", request.get("status"));
							param.put("operatorId", request.get("operator"));
							param.put("userId", userDetails.getUserId());
							param.put("uplineId", userDetails.getUserId());
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportforAllFilterBySD, param);
						}
					} else if(request.get("userType").equals("10")){
						if (request.get("status").equals("All") && request.get("operator").equals("0")) {
							// get Recharge Report for Status and Operator All based on the date
							param = new HashMap<String, String>();
							param.put("userId", userDetails.getUserId());
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForAllbyUser, param);						
						} else if(request.get("status").equals("All") && !request.get("operator").equals("0")){
							param = new HashMap<String, String>();
							param.put("userId", userDetails.getUserId());
							param.put("operatorId", request.get("operator"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForOperatorByUser, param);
						} else if(!request.get("status").equals("All") && request.get("operator").equals("0")){
							param = new HashMap<String, String>();
							param.put("userId", userDetails.getUserId());
							param.put("status", request.get("status"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForStatusByUser, param);
						} else {
							param = new HashMap<String, String>();
							param.put("userId", userDetails.getUserId());
							param.put("status", request.get("status"));
							param.put("operatorId", request.get("operator"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForStatusByUser, param);
						
						}
					} else {
						if (request.get("status").equals("All") && request.get("operator").equals("0")) {
							// get Recharge Report for Status and Operator All based on the date
							param = new HashMap<String, String>();
							param.put("userId", request.get("userId"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForAllbyUser, param);						
						} else if(request.get("status").equals("All") && !request.get("operator").equals("0")){
							param = new HashMap<String, String>();
							param.put("userId", userDetails.getUserId());
							param.put("operatorId", request.get("operator"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForOperatorByUser, param);
						} else if(!request.get("status").equals("All") && request.get("operator").equals("0")){
							param = new HashMap<String, String>();
							param.put("userId", userDetails.getUserId());
							param.put("status", request.get("status"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForStatusByUser, param);
						} else {
							param = new HashMap<String, String>();
							param.put("userId", userDetails.getUserId());
							param.put("status", request.get("status"));
							param.put("operatorId", request.get("operator"));
							param.put("startDate", request.get("startDate"));
							param.put("endDate", request.get("endDate"));
							list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForStatusByUser, param);
						
						}
					}
				
				} else if (userDetails.getRoleId() == 5 || userDetails.getRoleId() == 100) {
					if (request.get("status").equals("All") && request.get("operator").equals("0")) {
						// get Recharge Report for Status and Operator All based on the date
						param = new HashMap<String, String>();
						param.put("userId", userDetails.getUserId());
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));
						list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForAllbyUser, param);						
					} else if(request.get("status").equals("All") && !request.get("operator").equals("0")){
						param = new HashMap<String, String>();
						param.put("userId", userDetails.getUserId());
						param.put("operatorId", request.get("operator"));
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));
						list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForOperatorByUser, param);
					} else if(!request.get("status").equals("All") && request.get("operator").equals("0")){
						param = new HashMap<String, String>();
						param.put("userId", userDetails.getUserId());
						param.put("status", request.get("status"));
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));
						list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForStatusByUser, param);
					} else {
						param = new HashMap<String, String>();
						param.put("userId", userDetails.getUserId());
						param.put("status", request.get("status"));
						param.put("operatorId", request.get("operator"));
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));
						list = customQueryServiceLogic.getRechargeReport(CustomSqlQuery.getRechargeReportForStatusByUser, param);
					
					}
				}
				if(list == null){
					list = new ArrayList<RechargeReport>();
				}
			} else {
				list = new ArrayList<RechargeReport>();	
			}
			
		} catch (Exception e) {
			logger_log.error("getRechargeReport :::::::::::: "+e);
			list = new ArrayList<RechargeReport>();			
		}
		return list;
	}

	@Override
	public List<TransactionReport> viewBalRevReport(Map<String, String> request, User userDetails) {
		List<TransactionReport> list = new ArrayList<TransactionReport>();
		Map<String, String> param = null;
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1) {
					param = new HashMap<String, String>();
					//param.put("wlId", userDetails.getWlId());					
					param.put("startDate", request.get("startDate"));
					param.put("endDate", request.get("endDate"));
					list = customQueryServiceLogic.getTransactionReport(CustomSqlQuery.getRevertBalanceReportForAdmin, param);
				}
				else if(userDetails.getRoleId() == 2){
						param = new HashMap<String, String>();
						param.put("wlId", userDetails.getWlId());					
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));
						list = customQueryServiceLogic.getTransactionReport(CustomSqlQuery.getRevertBalanceReportForWLAdmin, param);
					
				}
				else {
					param = new HashMap<String, String>();
					param.put("userId", userDetails.getUserId());					
					param.put("startDate", request.get("startDate"));
					param.put("endDate", request.get("endDate"));
					list = customQueryServiceLogic.getTransactionReport(CustomSqlQuery.getRevertBalanceReportForUser, param);
				}
				if(list == null){
					list = new ArrayList<TransactionReport>();
				}
			}else {
				list = new ArrayList<TransactionReport>();	
			}
		}
		catch (Exception e) {
			logger_log.error("viewBalRevReport :::::::::::: "+e);
			list = new ArrayList<TransactionReport>();			
		}
		return list;
	}

	@Override
	public List<BalanceTransfer> viewPurchaseReport(Map<String, String> request, User userDetails) {
		List<BalanceTransfer> list = new ArrayList<BalanceTransfer>();
		Map<String, String> param = null;
		try {
			if (UtilityClass.checkParameterIsNull(request)) {

				param = new HashMap<String, String>();
				param.put("userId", userDetails.getUserId());					
				param.put("startDate", request.get("startDate"));
				param.put("endDate", request.get("endDate"));
				list = customQueryServiceLogic.viewPurchaseReport(CustomSqlQuery.getMyPurchaseReport, param);
			
				if(list == null){
					list = new ArrayList<BalanceTransfer>();
				}
			}else {
				list = new ArrayList<BalanceTransfer>();	
			}
		}
		catch (Exception e) {
			logger_log.error("viewBalRevReport :::::::::::: "+e);
			list = new ArrayList<BalanceTransfer>();			
		}
		return list;
	}

	@Override
	public List<BalanceTransfer> viewBalanceTransferReport(Map<String, String> request, User userDetails) {
		List<BalanceTransfer> list = new ArrayList<BalanceTransfer>();
		Map<String, String> param = null;
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				param = new HashMap<String, String>();
				param.put("userId", userDetails.getUserId());					
				param.put("startDate", request.get("startDate"));
				param.put("endDate", request.get("endDate"));
				list = customQueryServiceLogic.viewBalanceTransferReport(CustomSqlQuery.getMyBalanceTransferReport, param);			
				if(list == null){
					list = new ArrayList<BalanceTransfer>();
				}
			}else {
				list = new ArrayList<BalanceTransfer>();	
			}
		}
		catch (Exception e) {
			logger_log.error("viewBalRevReport :::::::::::: "+e);
			list = new ArrayList<BalanceTransfer>();			
		}
		return list;
	}

	@Override
	public Map<String, Object> viewUtilityReport(Map<String, String> request, User userDetails, String type) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = null;	
		List<UtilityReport> list = new ArrayList<>();
		try {
			if (userDetails.getRoleId() == 1) {
				if(type.equals("ALL")){
					parameters = new HashMap<>();
					parameters.put("wlId", userDetails.getWlId());
					parameters.put("startDate", request.get("startDate"));
					parameters.put("endDate", request.get("endDate"));
					list = customQueryServiceLogic.viewUtilityReport(CustomSqlQuery.viewUtilityReportByAdmin, parameters);
				} else if(type.equals("PENDING")){
					parameters = new HashMap<>();	
					parameters.put("wlId", userDetails.getWlId());
					list = customQueryServiceLogic.viewUtilityReport(CustomSqlQuery.viewPendingUtilityReportByAdmin, parameters);
				} 				
			} else if(userDetails.getRoleId() == 2){
				if(type.equals("ALL")){
					parameters = new HashMap<>();
					parameters.put("wlId", userDetails.getWlId());
					parameters.put("startDate", request.get("startDate"));
					parameters.put("endDate", request.get("endDate"));
					list = customQueryServiceLogic.viewUtilityReport(CustomSqlQuery.viewUtilityReportByAdmin, parameters);
				} else if(type.equals("PENDING")){
					parameters = new HashMap<>();	
					parameters.put("wlId", userDetails.getWlId());
					list = customQueryServiceLogic.viewUtilityReport(CustomSqlQuery.viewPendingUtilityReportByAdmin, parameters);
				} 	
				
			} else {
				if(type.equals("ALL")){
					parameters = new HashMap<>();
					parameters.put("userId", userDetails.getUserId());
					parameters.put("startDate", request.get("startDate"));
					parameters.put("endDate", request.get("endDate"));
					list = customQueryServiceLogic.viewUtilityReport(CustomSqlQuery.viewUtilityReportByUser, parameters);
				} else if(type.equals("PENDING")){
					list = new ArrayList<>();
				} 					
			}
			
			if(list.isEmpty()){
				list = new ArrayList<>();
				returnData.put("utility", list);	
				returnData.put("message", "No Record Found!");	
				returnData.put("status", "0");
			} else {
				returnData.put("utility", list);		
				returnData.put("status", "1");
			}
		} catch (Exception e) {
			logger_log.error("viewUtilityReport ::::::::::: "+e);
			returnData.put("message", "Exception! Please try again!");		
			returnData.put("status", "0");
		}
		return returnData;
	}

	@Override
	public Map<String, Object> viewInsuranceReport(Map<String, String> request, User userDetails, String type) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, String> parameters = null;	
		List<InsuranceReport> list = new ArrayList<>();
		try {
			if (userDetails.getRoleId() == 1) {
				if(type.equals("ALL")){
					parameters = new HashMap<>();
					parameters.put("wlId", userDetails.getWlId());
					parameters.put("startDate", request.get("startDate"));
					parameters.put("endDate", request.get("endDate"));
					list = customQueryServiceLogic.viewInsuranceReport(CustomSqlQuery.viewInsuranceByAdmin, parameters);
				} else if(type.equals("PENDING")){
					parameters = new HashMap<>();	
					parameters.put("wlId", userDetails.getWlId());
					list = customQueryServiceLogic.viewInsuranceReport(CustomSqlQuery.viewPendingInsuranceReportByAdmin, parameters);
				} 				
			} else if(userDetails.getRoleId() == 2){
				if(type.equals("ALL")){
					parameters = new HashMap<>();
					parameters.put("wlId", userDetails.getWlId());
					parameters.put("startDate", request.get("startDate"));
					parameters.put("endDate", request.get("endDate"));
					list = customQueryServiceLogic.viewInsuranceReport(CustomSqlQuery.viewInsuranceByAdmin, parameters);
				} else if(type.equals("PENDING")){
					parameters = new HashMap<>();	
					parameters.put("wlId", userDetails.getWlId());
					list = customQueryServiceLogic.viewInsuranceReport(CustomSqlQuery.viewPendingInsuranceReportByAdmin, parameters);
				} 	
				
			} else {
				if(type.equals("ALL")){
					parameters = new HashMap<>();
					parameters.put("userId", userDetails.getUserId());
					parameters.put("startDate", request.get("startDate"));
					parameters.put("endDate", request.get("endDate"));
					list = customQueryServiceLogic.viewInsuranceReport(CustomSqlQuery.viewInsuranceByUser, parameters);
				} else if(type.equals("PENDING")){
					list = new ArrayList<>();
				} 					
			}
			
			if(list.isEmpty()){
				list = new ArrayList<>();
				returnData.put("insurance", list);	
				returnData.put("message", "No Record Found!");	
				returnData.put("status", "0");
			} else {
				returnData.put("insurance", list);		
				returnData.put("status", "1");
			}
		} catch (Exception e) {
			logger_log.error("viewInsuranceReport ::::::::::: "+e);
			returnData.put("message", "Exception! Please try again!");		
			returnData.put("status", "0");
		}
		return returnData;
	}

	@Override
	public List<TransactionReport> getRefundReport(Map<String, String> request, User userDetails) {
		List<TransactionReport> list = new ArrayList<TransactionReport>();
		Map<String, String> param = null;
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1) {
					// Admin check the report for the user by roleId or particular result
					if(request.get("userType").equals("0")) {
						param = new HashMap<String, String>();
						//param.put("wlId", "ADMIN");
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));
						// get Transaction Report of all User.
						//list = transactiondetailsDao.getTransactionDetailsByNamedQuery("getTransactionReportForAll", param );
						list = customQueryServiceLogic.getTransactionReport(CustomSqlQuery.getRefundReportForAll, param);
					} else {						
						// get Transaction Report for particular User
						param = new HashMap<String, String>();
						param.put("userId", request.get("userId"));
						param.put("wlId", "ADMIN");
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));
						list = customQueryServiceLogic.getTransactionReport(CustomSqlQuery.getRefundReportForUser, param);
					}

				} else if (userDetails.getRoleId() == 2) {
					// Admin check the report for the user by roleId or particular result
					if(request.get("userType").equals("0")) {
						param = new HashMap<String, String>();
						param.put("wlId", userDetails.getWlId());
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));
						// get Transaction Report of all User.
						//list = transactiondetailsDao.getTransactionDetailsByNamedQuery("getTransactionReportForAll", param );
						list = customQueryServiceLogic.getTransactionReport(CustomSqlQuery.getRefundReportForAllByReseller, param);
					} else {						
						// get Transaction Report for particular User
						param = new HashMap<String, String>();
						param.put("userId", request.get("userId"));
						param.put("wlId", userDetails.getWlId());
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));
						list = customQueryServiceLogic.getTransactionReport(CustomSqlQuery.getRefundReportForUser, param);
					}

				} else {
					param = new HashMap<String, String>();
					param.put("userId", userDetails.getUserId());
					param.put("wlId", userDetails.getWlId());
					param.put("startDate", request.get("startDate"));
					param.put("endDate", request.get("endDate"));
					list = customQueryServiceLogic.getTransactionReport(CustomSqlQuery.getRefundReportbyUser, param);
				}
				if(list == null){
					list = new ArrayList<TransactionReport>();
				}
			}else {
				list = new ArrayList<TransactionReport>();	
			}
		}
		catch (Exception e) {
			logger_log.error("getRefundReport :::::::::::: "+e);
			list = new ArrayList<TransactionReport>();			
		}
		return list;
	}

	@Override
	public List<EarningSurchargeReport> getEarningReport(Map<String, String> request, User userDetails) {
		List<EarningSurchargeReport> list = new ArrayList<EarningSurchargeReport>();
		Map<String, String> param = null;
		try {
			if (UtilityClass.checkParameterIsNull(request)) {
				if (userDetails.getRoleId() == 1) {
					// Admin check the report for the user by roleId or particular result
					if(request.get("userType").equals("0")) {
						param = new HashMap<String, String>();						
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));						
						list = customQueryServiceLogic.getEarningReport(CustomSqlQuery.getEarningReportForAllByAdmin, param);
					} else {						
						// get Transaction Report for particular User
						param = new HashMap<String, String>();
						param.put("roleId", request.get("userType"));						
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));
						list = customQueryServiceLogic.getEarningReport(CustomSqlQuery.getEarningReportForUserByAdmin, param);
					}

				} else if (userDetails.getRoleId() == 2) {
					// Admin check the report for the user by roleId or particular result
					if(request.get("userType").equals("0")) {
						param = new HashMap<String, String>();
						param.put("wlId", userDetails.getWlId());
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));						
						list = customQueryServiceLogic.getEarningReport(CustomSqlQuery.getEarningReportForUserByReseller, param);
						
					} else if (request.get("userType").equals("10")){
						param = new HashMap<String, String>();
						param.put("userId", userDetails.getUserId());
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));						
						list = customQueryServiceLogic.getEarningReport(CustomSqlQuery.getEarningReportForOwn, param);
						
					} else if (request.get("userType").equals("3")){
						param = new HashMap<String, String>();
						param.put("uplineId", userDetails.getUserId());
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));						
						list = customQueryServiceLogic.getEarningReport(CustomSqlQuery.getEarningReportForFirstDownline, param);
						
					} else if (request.get("userType").equals("4")){
						param = new HashMap<String, String>();
						param.put("uplineId", userDetails.getUserId());
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));						
						list = customQueryServiceLogic.getEarningReport(CustomSqlQuery.getEarningReportForSecondDownline, param);
						
					} else if (request.get("userType").equals("5")){
						param = new HashMap<String, String>();
						param.put("uplineId", userDetails.getUserId());
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));						
						list = customQueryServiceLogic.getEarningReport(CustomSqlQuery.getEarningReportForThirdDownLine, param);
						
					} 
				} else if (userDetails.getRoleId() == 3){
					if (request.get("userType").equals("10")){
						param = new HashMap<String, String>();
						param.put("userId", userDetails.getUserId());
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));						
						list = customQueryServiceLogic.getEarningReport(CustomSqlQuery.getEarningReportForOwn, param);
						
					} else if (request.get("userType").equals("4")){
						param = new HashMap<String, String>();
						param.put("uplineId", userDetails.getUserId());
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));						
						list = customQueryServiceLogic.getEarningReport(CustomSqlQuery.getEarningReportForFirstDownline, param);
						
					} else if (request.get("userType").equals("5")){
						param = new HashMap<String, String>();
						param.put("uplineId", userDetails.getUserId());
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));						
						list = customQueryServiceLogic.getEarningReport(CustomSqlQuery.getEarningReportForSecondDownline, param);
						
					} 
				} else if (userDetails.getRoleId() == 4) {
					if (request.get("userType").equals("10")){
						param = new HashMap<String, String>();
						param.put("userId", userDetails.getUserId());
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));						
						list = customQueryServiceLogic.getEarningReport(CustomSqlQuery.getEarningReportForOwn, param);
						
					}else if (request.get("userType").equals("5")){
						param = new HashMap<String, String>();
						param.put("uplineId", userDetails.getUserId());
						param.put("startDate", request.get("startDate"));
						param.put("endDate", request.get("endDate"));						
						list = customQueryServiceLogic.getEarningReport(CustomSqlQuery.getEarningReportForFirstDownline, param);
						
					} 
				} else {
					param = new HashMap<String, String>();
					param.put("userId", userDetails.getUserId());
					param.put("startDate", request.get("startDate"));
					param.put("endDate", request.get("endDate"));						
					list = customQueryServiceLogic.getEarningReport(CustomSqlQuery.getEarningReportForOwn, param);
					
				}
				if(list == null){
					list = new ArrayList<EarningSurchargeReport>();
				}
			}else {
				list = new ArrayList<EarningSurchargeReport>();	
			}
		}
		catch (Exception e) {
			logger_log.error("getEarningReport :::::::::::: "+e);
			list = new ArrayList<EarningSurchargeReport>();			
		}
		return list;
	}
	
	@Override
	public List<RechargeReport> getLatestTransactionReport(User userDetails) {
		List<RechargeReport> list = new ArrayList<RechargeReport>();
		Map<String, String> param = null;
		try {

			param = new HashMap<String, String>();
			param.put("userId", userDetails.getUserId());
			// System.out.println("ReportService emtry");
			list = customQueryServiceLogic.getLatestTransactionReport(CustomSqlQuery.getLatestRechargeDetails, param);

		} catch (Exception e) {
			logger_log.error("getLatestTransactionReport :::::::::::: " + e);
			list = new ArrayList<RechargeReport>();
		}
		return list;

	}
	
	@Override
	public List<NSDLPan> getnsdlReport(Map<String, String> request, User userDetails) {
		List<NSDLPan> list = new ArrayList<NSDLPan>();
		Map<String, Object> param = null;
		Map<String, String> param1 = new HashMap<String, String>();
		try {
			if(userDetails.getRoleId()==1){
				param1 = new HashMap<String, String>();
				//param.put("userId", userDetails.getUserId());
				param1.put("startDate", request.get("startDate"));
				param1.put("endDate", request.get("endDate"));
				if(request.get("status").equals("All")){
				 list=customQueryServiceLogic.getNSDLPanDetails(CustomSqlQuery.GetNSDLPanforamin, param1);
				}else{
					param1.put("status", request.get("status"));
					 list=customQueryServiceLogic.getNSDLPanDetails(CustomSqlQuery.getNSDLPanPendingAdminstatus, param1);
				}
			}else{
				
			param1 = new HashMap<String, String>();
			param1.put("userId", userDetails.getUserId());
			param1.put("startDate", request.get("startDate"));
			param1.put("endDate", request.get("endDate"));
			if(request.get("status").equals("All")){
			// list=NSDLpanDao.getNSDLPanByNamedQuery("getNSDLPan", param);
			 list=customQueryServiceLogic.getNSDLPanDetails(CustomSqlQuery.GetNSDLPanforuser, param1);
			}else{
				param1.put("status", request.get("status"));
				list=customQueryServiceLogic.getNSDLPanDetails(CustomSqlQuery.GetNSDLPanforuserstatus, param1);
				//list=NSDLpanDao.getNSDLPanByNamedQuery("getNSDLPanbyStatus", param);
			}

			}

		} catch (Exception e) {
			logger_log.error("getnsdlReport :::::::::::: " + e);
			list = new ArrayList<NSDLPan>();
		}
		System.out.println(list.size());
		return list;

	}
	
	@Override
	public List<NSDLPan> fetchnsdlpan(User userDetails) {
		List<NSDLPan> list = new ArrayList<NSDLPan>();
		Map<String, Object> param = null;
		Map<String, String> param1 = new HashMap<String, String>();
		try {
			if(userDetails.getRoleId()==1){
				param1 = new HashMap<String, String>();
			//	param.put("userId", userDetails.getUserId());
				//param1.put("status", "PENDING");
				 list=customQueryServiceLogic.getNSDLPanDetails(CustomSqlQuery.getNSDLPanPendingHoldAdmin, param1);

				//	list=NSDLpanDao.getNSDLPanByNamedQuery("getNSDLPanPendingAdmin", param);
			}else{
			param = new HashMap<String, Object>();
			param.put("userId", userDetails.getUserId());
		//	param.put("status", "PENDING");
				list=NSDLpanDao.getNSDLPanByNamedQuery("getNSDLPanPendingHold", param);
			
			}
		} catch (Exception e) {
			logger_log.error("getnsdlReport :::::::::::: " + e);
			list = new ArrayList<NSDLPan>();
		}
		return list;

	}
	
	@Override
	public Map<String, Object> updateNSDLReport(Map<String, String> request, User userDetails,byte[] askbyte,String type) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		
		try {
			NSDLPan NSDLPan=NSDLpanDao.getNSDLPanById(Integer.parseInt(request.get("id")));
			NSDLPan.setAckno(request.get("ackno"));
			NSDLPan.setRemark(request.get("remark"));
			NSDLPan.setStatus(request.get("status"));
			NSDLPan.setAckfilename(type);
			boolean flag=NSDLpanDao.updateNSDLPan(NSDLPan);
			if(flag){
				
					
					returnData.put("status", "1");
					returnData.put("message", "Update Successful");
				
			}else{
				returnData.put("status", "0");
				returnData.put("message", "Update Failed");
			}

		} catch (Exception e) {
			logger_log.error("updateNSDLReport :::::::::::: " + e);
			returnData.put("status", "0");
		}
		return returnData;

	}
	
	@Override
	public Map<String, Object> updateNSDLReportFailed(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, String> parameters = new HashMap<String, String>();	
		Operator operator = new Operator();
		PackageWiseChargeComm pckret=new PackageWiseChargeComm();
		PackageWiseChargeComm pcksd=new PackageWiseChargeComm();
		PackageWiseChargeComm pckdis=new PackageWiseChargeComm();
		PackageWiseChargeComm pckres=new PackageWiseChargeComm();
		Map<String, Object> param = new HashMap<String, Object>();
		double charge=0.0;
		String resellerId = "";
		String sdId = "";
		String distId = "";	
		String rId="";
		double discharge=0.0;
		double spdischarge=0.0;
		double rescharge=0.0;
		double dcharge = 0.0;
		double sCharge = 0.0;
		double reCharge = 0.0;
		try {
			NSDLPan NSDLPan=NSDLpanDao.getNSDLPanById(Integer.parseInt(request.get("id")));
			NSDLPan.setAckno(request.get("ackno"));
			NSDLPan.setRemark(request.get("remark"));
			NSDLPan.setStatus(request.get("status"));
			boolean flag=NSDLpanDao.updateNSDLPan(NSDLPan);
			if(flag){
				System.out.println(":::"+request);
					if(request.get("status").equals("FAILED")){
						param.put("serviceProvider", "NSDL");
						List<Operator> opList =  operatorDao.getOperatorByNamedQuery("getOperatorByName", param );
						if((!opList.isEmpty())) {
							operator=opList.get(0);	
						}
						User user=userDao.getUserByUserId(NSDLPan.getUserId());
						if (user.getRoleId() == 5) {
							pckret = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Pan",operator.getOperatorId()) ;
							charge = pckret.getCharge();
							System.out.println("coupon charge::::"+charge);
							distId = user.getUplineId();
							pckdis=commissionService.getPackagewiseCommisionOnOperator(distId,"Pan",operator.getOperatorId()); 
							discharge=pckdis.getCharge();
							System.out.println("coupon charge:discharge:::"+discharge);
							// Super Distributor Id
							parameters = new HashMap<String, String>();
							parameters.put("userId", distId);	
							sdId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);	
							pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Pan",operator.getOperatorId());
							spdischarge=pcksd.getCharge();
							System.out.println("coupon charge:spdischarge:::"+spdischarge);
							// Reseller Id
							parameters = new HashMap<String, String>();
							parameters.put("userId", sdId);							
							resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
							if(!resellerId.equals("admin")) {
								pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Pan",operator.getOperatorId());
								rescharge=pckres.getCharge();
								System.out.println("coupon charge:rescharge:::"+rescharge);
							}
							
							if(discharge==0){dcharge=0;}
							else{dcharge = charge - discharge;}
							
							if(spdischarge==0){sCharge=0;}
							else{sCharge = discharge - spdischarge;}
							
							if(rescharge==0){reCharge=0;}
							else{
							reCharge = spdischarge - rescharge;
							}
							commissionService.updateBalance(user.getUserId(), "Charge for NSDL Pan Charge", "CHARGE REVERT", charge, "CREDIT",0);
							commissionService.updateBalance(distId, "Charge for NSDL Pan Charge", "CHARGE REVERT", dcharge, "DEBIT",0);
							commissionService.updateBalance(sdId, "Charge for NSDL Pan Charge", "CHARGE REVERT", sCharge, "DEBIT",0);
							
							if(!resellerId.equals("admin")) {
								commissionService.updateBalance(resellerId, "Charge for NSDL Pan Charge", "Pan Charge REVERT", reCharge, "DEBIT",0);
							}
							
							
							
						}else if (user.getRoleId() == 4) {
							pckdis = commissionService.getPackagewiseCommisionOnOperator(
									user.getUserId(), "Pan", operator.getOperatorId());
							
								charge = pckdis.getCharge();
								sdId = user.getUplineId();
								parameters = new HashMap<String, String>();
								parameters.put("userId", sdId);							
								resellerId = customQueryServiceLogic.getUplineIdByUserId(CustomSqlQuery.getUplineIdByUserId, parameters);
								pcksd=commissionService.getPackagewiseCommisionOnOperator(sdId,"Pan",operator.getOperatorId());
								spdischarge=pcksd.getCharge();
								if(!resellerId.equals("admin")) {
									pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Pan",operator.getOperatorId());
									rescharge=pckres.getCharge();
								}
								if(spdischarge==0){sCharge=0;}
								else{sCharge = charge - spdischarge;}
								if(rescharge==0){reCharge=0;}
								else{
								reCharge = spdischarge - rescharge;
								}
								commissionService.updateBalance(user.getUserId(), "Charge for NSDL Pan Charge", "CHARGE REVERT", charge, "CREDIT",0);
								commissionService.updateBalance(sdId, "Charge for NSDL Pan Charge", "CHARGE REVERT", sCharge, "DEBIT",0);
								if(!resellerId.equals("admin")) {
									commissionService.updateBalance(resellerId, "Charge for NSDL Pan Charge", "Pan Charge REVERT", reCharge, "DEBIT",0);
								}

						} else if (user.getRoleId() == 3) {
							pcksd = commissionService.getPackagewiseCommisionOnOperator(user.getUserId(),"Pan", operator.getOperatorId());
							
								charge = pcksd.getCharge();
								resellerId = user.getUplineId();
								commissionService.updateBalance(user.getUserId(), "Charge for NSDL Pan Charge", "CHARGE REVERT", charge, "CREDIT",0);
								if(!resellerId.equals("admin")) {
									pckres=commissionService.getPackagewiseCommisionOnOperator(resellerId,"Pan",operator.getOperatorId());
									rescharge=pckres.getCharge();
									
									if(rescharge==0){reCharge=0;}
									else{
									reCharge = charge - rescharge;
									}
									commissionService.updateBalance(resellerId, "Charge for NSDL Pan Charge", "Pan Charge REVERT", reCharge, "DEBIT",0);
								}
								
						}
					
					}
					returnData.put("status", "1");
					returnData.put("message", "Update Successful");
				
			}else{
				returnData.put("status", "0");
				returnData.put("message", "Update Failed");
			}

		} catch (Exception e) {
			logger_log.error("updateNSDLReport :::::::::::: " + e);
			returnData.put("status", "0");
		}
		return returnData;

	}

	@Override
	public Map<String, Object> updateNSDLHoldReport(Map<String, String> request, User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();	
		Map<String, String> parameters = new HashMap<String, String>();	
		
		try {
			NSDLPan NSDLPan=NSDLpanDao.getNSDLPanById(Integer.parseInt(request.get("id")));
		//	NSDLPan.setAckno(request.get("ackno"));
			NSDLPan.setRemark(request.get("remark"));
			NSDLPan.setStatus(request.get("status"));
			boolean flag=NSDLpanDao.updateNSDLPan(NSDLPan);
			if(flag){
				System.out.println(":::"+request);
				
					returnData.put("status", "1");
					returnData.put("message", "Update Successful");
				
			}else{
				returnData.put("status", "0");
				returnData.put("message", "Update Failed");
			}

		} catch (Exception e) {
			logger_log.error("updateNSDLReport :::::::::::: " + e);
			returnData.put("status", "0");
		}
		return returnData;

	}

	@Override
	public List<P2ATranferReport> getp2aReport(Map<String, String> request, User userDetails) {
		List<P2ATranferReport> list=new ArrayList<>();
		Map<String, String> param = new HashMap<String, String>();
		try{
			if (userDetails.getRoleId() == 1) {
				param = new HashMap<String, String>();
				param.put("start_date", request.get("startDate"));
				param.put("end_date", request.get("endDate"));
				list = customQueryServiceLogic.getP2ATranferReport(CustomSqlQuery.getP2AReportForAdmin, param);	
			}else if (userDetails.getRoleId() == 2) {
				
			}else{
				param = new HashMap<String, String>();
				param.put("start_date", request.get("startDate"));
				param.put("end_date", request.get("endDate"));
				param.put("username", userDetails.getUserId());
				list = customQueryServiceLogic.getP2ATranferReport(CustomSqlQuery.getP2AReportForUser, param);		
			}
		}catch (Exception e) {
		logger_log.error("getp2aReport:::::::::::: "+e);
		}
		return list;
	}

	@Override
	public List<APPReport> getAPPReport(Map<String, String> request, User userDetails) {
		List<APPReport> list=new ArrayList<>();
		System.out.println("request::::::::::::::::::"+request);
		System.out.println("RoleId::::::::::::::::::"+userDetails.getRoleId());
		Map<String, String> param = new HashMap<String, String>();
		String start_date=request.get("start_date").substring(0, request.get("start_date").indexOf("T"));
		System.out.println("start_date::::::::::::::::::"+start_date);
		String end_date=request.get("end_date").substring(0, request.get("end_date").indexOf("T"));
		try{if (userDetails.getRoleId() == 1) {
			param = new HashMap<String, String>();
			param.put("start_date", start_date);
			param.put("end_date",end_date);
			list = customQueryServiceLogic.getAPPReport(CustomSqlQuery.getAppReportForAdmin, param);	
		}else if (userDetails.getRoleId() == 2) {
			param = new HashMap<String, String>();
			param.put("start_date", start_date);
			param.put("end_date",end_date);
			param.put("wl_id", userDetails.getWlId());
			list = customQueryServiceLogic.getAPPReport(CustomSqlQuery.getAppreportwlid, param);	
			
		}else{
			param = new HashMap<String, String>();
			param.put("start_date", start_date);
			param.put("end_date",end_date);
			param.put("username", userDetails.getUserId());
			list = customQueryServiceLogic.getAPPReport(CustomSqlQuery.getAppReportForUser, param);		
		}
	}catch (Exception e) {
	logger_log.error("getAPPReport:::::::::::: "+e);
	}
	return list;
	}

	@Override
	public List<UserWalletReport> getWALLETReport(Map<String, String> request, User userDetails) {
		List<UserWalletReport> list=new ArrayList<>();
		System.out.println("request::::::::::::::::::"+request);
		Map<String, String> param = new HashMap<String, String>();
		String start_date=request.get("start_date").substring(0, request.get("start_date").indexOf("T"));
		System.out.println("start_date::::::::::::::::::"+start_date);
		String end_date=request.get("end_date").substring(0, request.get("end_date").indexOf("T"));
		try{if (userDetails.getRoleId() == 1) {
			param = new HashMap<String, String>();
			param.put("start_date", start_date);
			param.put("end_date",end_date);
			list = customQueryServiceLogic.getWALLETReport(CustomSqlQuery.getWALLETReportForAdmin, param);	
		}else if (userDetails.getRoleId() == 2) {
			
		}else{
			param = new HashMap<String, String>();
			param.put("start_date", start_date);
			param.put("end_date",end_date);
			param.put("username", userDetails.getUserId());
			list = customQueryServiceLogic.getWALLETReport(CustomSqlQuery.getWALLETReportForUser, param);		
		}
	}catch (Exception e) {
	logger_log.error("getWALLETReport:::::::::::: "+e);
	}
	return list;
	}

	@Override
	public Map<String, Object> viewuserbankdt( User userDetails) {
		Map<String, Object> returnData = new HashMap<String, Object>();		
		List<UserBankDetails> list=new ArrayList<UserBankDetails>();
		try{
			list=UserBankDetailsDao.getAllUserBankDetails();
			returnData.put("list", list);
		}catch(Exception e){
			logger_log.error("viewuserbankdt:::::::::::: "+e);
		}
		return returnData;
	}
}
