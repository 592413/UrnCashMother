/**
 * 
 */
package com.recharge.businessimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.businessdao.CommissionService;
import com.recharge.businessdao.CustomQueryServiceLogic;
import com.recharge.customquery.CustomSqlQuery;
import com.recharge.model.AssignedPackage;
import com.recharge.model.Chargeset;
import com.recharge.model.Defaultcommission;
import com.recharge.model.Individualcharge;
import com.recharge.model.Individualcommission;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.Transactiondetails;
import com.recharge.model.User;
import com.recharge.servicedao.AssignedPackageDAO;
import com.recharge.servicedao.ChargesetDao;
import com.recharge.servicedao.DefaultcommissionDao;
import com.recharge.servicedao.IndividualChargeDao;
import com.recharge.servicedao.IndividualcommissionDao;
import com.recharge.servicedao.TransactiondetailsDao;
import com.recharge.servicedao.UserDao;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.RoundNumber;

/**
 * @author Md Rahim
 *
 */

@Service("commissionService")
public class CommissionServiceImpl implements CommissionService{
	private static final Logger logger_log = Logger.getLogger(CommissionService.class);
	@Autowired ChargesetDao chargesetDao;
	@Autowired UserDao userDao;
	@Autowired TransactiondetailsDao transactiondetailsDao;
	@Autowired CustomQueryServiceLogic customQueryServiceLogic;
	@Autowired IndividualcommissionDao individualcommissionDao;
	@Autowired DefaultcommissionDao defaultcommissionDao;
	@Autowired IndividualChargeDao individualChargeDao;
	@Autowired AssignedPackageDAO assignedPackage;
	
	/* (non-Javadoc)
	 * @see com.recharge.businessdao.CommissionService#getCommision(com.recharge.model.User)
	 */
	@Override
	public double getCommisionOnOperator(String userId, String wl_id,Integer roleId,Integer operatorId) {
		try {
			/**
			 * 1. first check individual is done or not 
			 * 2. if not repeat till admin			  
			 */
			User user = userDao.getUserByUserId(userId);			
			if(roleId == 5) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userId", user.getUserId());
				param.put("operatorId", operatorId);
				// retailer individual commisions
				
				
				List<Individualcommission> individualcommissions = individualcommissionDao.getIndividualCommissionByNamedQuery("GetIndividualcommissionByOperatorAndUserId", param);
				if(individualcommissions.isEmpty()) {
					param = new HashMap<String, Object>();
					param.put("userId", user.getUplineId());
					param.put("operatorId", operatorId);
					// default commission set by Distributor
					List<Defaultcommission> defaultcommissions = defaultcommissionDao.getDefaultCommissionByNamedQuery("GetDefaultcommissionByOperatorAndUserId", param);
					if(defaultcommissions.isEmpty()) {
						// if not found any default commission set by distributor it will check for the default commission set by Super Distributor
						user = userDao.getUserByUserId(user.getUplineId());
						param = new HashMap<String, Object>();
						param.put("userId", user.getUplineId());
						param.put("operatorId", operatorId);
						defaultcommissions = defaultcommissionDao.getDefaultCommissionByNamedQuery("GetDefaultcommissionByOperatorAndUserId", param);
						if(defaultcommissions.isEmpty()) {
							// if not found any default commission set by Super Distributor it will check for the default commission set by RESELLER
							user = userDao.getUserByUserId(user.getUplineId());
							param = new HashMap<String, Object>();
							param.put("userId", user.getUplineId());
							param.put("operatorId", operatorId);
							defaultcommissions = defaultcommissionDao.getDefaultCommissionByNamedQuery("GetDefaultcommissionByOperatorAndUserId", param);
							if(defaultcommissions.isEmpty()) {
								return 0;
							} else {
								return defaultcommissions.get(0).getRetailer();
							}
						} else {
							return defaultcommissions.get(0).getRetailer();
						}
					} else {
						return defaultcommissions.get(0).getRetailer();
					}
				} else {
					return individualcommissions.get(0).getCommission();
				}				
				
			} else if(roleId == 4) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userId", user.getUserId());
				param.put("operatorId", operatorId);
				List<Individualcommission> individualcommissions = individualcommissionDao.getIndividualCommissionByNamedQuery("GetIndividualcommissionByOperatorAndUserId", param);
				if(individualcommissions.isEmpty()) {
					param = new HashMap<String, Object>();
					param.put("userId", user.getUplineId());
					param.put("operatorId", operatorId);					
					List<Defaultcommission> defaultcommissions = defaultcommissionDao.getDefaultCommissionByNamedQuery("GetDefaultcommissionByOperatorAndUserId", param);
					if(defaultcommissions.isEmpty()) {						
						user = userDao.getUserByUserId(user.getUplineId());
						param = new HashMap<String, Object>();
						param.put("userId", user.getUplineId());
						param.put("operatorId", operatorId);
						defaultcommissions = defaultcommissionDao.getDefaultCommissionByNamedQuery("GetDefaultcommissionByOperatorAndUserId", param);
						if(defaultcommissions.isEmpty()) {
							return 0;
						} else {
							return defaultcommissions.get(0).getDistributor();
						}
					} else {
						return defaultcommissions.get(0).getDistributor();
					}
				} else {
					return individualcommissions.get(0).getCommission();
				}			
			} else if(roleId == 3) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userId", user.getUserId());
				param.put("operatorId", operatorId);
				List<Individualcommission> individualcommissions = individualcommissionDao.getIndividualCommissionByNamedQuery("GetIndividualcommissionByOperatorAndUserId", param);
				if(individualcommissions.isEmpty()) {
					param = new HashMap<String, Object>();
					param.put("userId", user.getUplineId());
					param.put("operatorId", operatorId);					
					List<Defaultcommission> defaultcommissions = defaultcommissionDao.getDefaultCommissionByNamedQuery("GetDefaultcommissionByOperatorAndUserId", param);
					if(defaultcommissions.isEmpty()) {			
						return 0;
					} else {
						return defaultcommissions.get(0).getSuperDistributor();
					}
				} else {
					return individualcommissions.get(0).getCommission();
				}
			} else if(roleId == 2) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userId", user.getUserId());
				param.put("operatorId", operatorId);
				List<Individualcommission> individualcommissions = individualcommissionDao.getIndividualCommissionByNamedQuery("GetIndividualcommissionByOperatorAndUserId", param);
				if(individualcommissions.isEmpty()) {
					return 0;
				} else {
					return individualcommissions.get(0).getCommission();
				}
			}
			
		}catch (Exception e) {
			logger_log.error("getCommisionOnOperator ::::::::::::::: "+e);
		}
		return 0;
	}
	
	@Override
	public Individualcharge getIndividualChargeOnOperator(String userId, Integer operatorId) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", userId);
			param.put("operatorId", operatorId);
			List<Individualcharge> charges = individualChargeDao.getIndividualchargeByNamedQuery("GetIndividualchargeByUserIdAndOperatorId", param);
			if(!charges.isEmpty()) {
				return charges.get(0);
			}
		} catch (Exception e) {
			logger_log.error("getIndividualChargeOnOperator ::::::::::::::: "+e);
		}
		return null;
	}
	
	@Override
	public Chargeset getDefaultChargeOnOperator(String wl_id, Integer operatorId) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("wlId", wl_id);
			param.put("operatorId", operatorId);
			List<Chargeset> charges = chargesetDao.getChargeSetByNamedQuery("GetChargesByWlIdAndOperatorId", param);
			if(!charges.isEmpty()) {
				return charges.get(0);
			}
		} catch (Exception e) {
			logger_log.error("getDefaultChargeOnOperator ::::::::::::::: "+e);
		}
		return null;
	}
	
	@Override
	public double calculateChargeOnOperator(String userId, Integer operatorId, String wl_id, Integer roleId, double amount) {
		double charge = 0.0;
		try {
			Individualcharge iCharge = getIndividualChargeOnOperator(userId, operatorId);
		//	System.out.println("CommissionServiceIMP calculateChargeOn===");
			if(iCharge != null) {
				if(iCharge.getFlag().equals("1")) {
					charge = RoundNumber.roundDouble((iCharge.getPercentage() * amount) / 100);
					//System.out.println("iChargegetFlag=="+charge);
				} else {
					charge = iCharge.getRupees();
				}
			} else {
				Chargeset dCharge =  getDefaultChargeOnOperator(wl_id, operatorId);
				if(dCharge != null) {
					if (dCharge.getFlag().equals("0")) {
						if (roleId == 5) {
							charge = dCharge.getRet();
							
							//System.out.println("roleId=5="+charge);
						} else if (roleId == 4) {
							charge = dCharge.getDist();
							//System.out.println("roleId=4="+charge);
						} else if (roleId == 3) {
							charge = dCharge.getSdist();
							//System.out.println("roleId=3="+charge);
						}
					} else {
						if (roleId == 5) {
							charge = RoundNumber.roundDouble((dCharge.getRet()*amount)/100);
						//	System.out.println("roleId=5=="+charge);
						} else if (roleId == 4) {
							charge =RoundNumber.roundDouble((dCharge.getDist()*amount)/100);
						//	System.out.println("roleId=4=="+charge);
						} else if (roleId == 3) {
							charge = RoundNumber.roundDouble((dCharge.getSdist()*amount)/100);
						//	System.out.println("roleId=3=="+charge);
						}
					}
				} else {
					charge = 0.0;
				}
			}
		} catch (Exception e) {
			charge = 0;
			logger_log.error("calculateChargeOnOperator ::::::::::::::: "+e);
		}		
		return charge;
	}
	
	@Override 	
	public synchronized boolean updateBalance(String userId, String naration, String remarks, double amount, String type,double tds) {
		double cl_bal = 0.0;
		double adClBal = 0.0;
		boolean flag;
		Transactiondetails transaction = null;
		String today = GenerateRandomNumber.getCurrentDate();	
		String currentTime = GenerateRandomNumber.getCurrentTime();
		try {
			User userDetails = userDao.getUserByUserId(userId);
			Map<String, String> parameters = new HashMap<String, String>();
			double adOpBal = customQueryServiceLogic.getTotalbalanceForAdmin(CustomSqlQuery.getTotalbalanceForAdmin, parameters);			
			parameters = new HashMap<String, String>();
			parameters.put("userId", userDetails.getUserId());	
			double op_bal = customQueryServiceLogic.getClosingBalanceForUser(CustomSqlQuery.getClosingBalanceForUser, parameters );	
			if(type.equalsIgnoreCase("CREDIT")) {				
				adClBal = RoundNumber.roundDouble(adOpBal + amount);
				cl_bal = RoundNumber.roundDouble(op_bal + amount);
				if(tds==0){
					transaction = new Transactiondetails(userId,op_bal, amount, 0.0, cl_bal, naration, remarks, today, currentTime, adOpBal, adClBal, userDetails.getWlId());				
				}else{
					double amnt=tds;
					double td=amount;
					cl_bal = RoundNumber.roundDouble(op_bal + amnt);
					transaction = new Transactiondetails(userId,op_bal, amnt, td, cl_bal, naration, remarks, today, currentTime, adOpBal, adClBal, userDetails.getWlId());				
				}
				
			} else {
				adClBal = RoundNumber.roundDouble(adOpBal - amount);
				cl_bal = RoundNumber.roundDouble(op_bal - amount);
				transaction = new Transactiondetails(userId,op_bal, 0.0, amount, cl_bal, naration, remarks, today, currentTime, adOpBal, adClBal, userDetails.getWlId());
			}
			transactiondetailsDao.insertTransactionDetails(transaction);
			userDetails.setBalance(cl_bal);
			userDao.updateUser(userDetails);
			flag = true;
		} catch (Exception e) {
			flag = false;
			logger_log.error("updateBalance ::::::::::::::: "+e);
		}
		return flag;
	}
	
	@Override
	public PackageWiseChargeComm getPackagewiseCommisionOnOperator(String userId, String service_type, int operatorID) {
		PackageWiseChargeComm pck = new PackageWiseChargeComm();
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> parameters = new HashMap<String, String>();
		AssignedPackage asp = new AssignedPackage();
		param.put("user_id", userId);
		param.put("service_type", service_type);
		List<AssignedPackage> list = assignedPackage
				.getAssignedPackageByNamedQuery("getAssignedPackageByUserAndservice", param);
		if (list.size() > 0) {
			asp = list.get(0);

			parameters.put("package_id", asp.getPackage_id());
			parameters.put("operator_id", String.valueOf(operatorID));
			pck = customQueryServiceLogic
					.getPackageAndOperatorwisecharecom(CustomSqlQuery.getPackageAndOperatorwisecharecom, parameters);

		} else {
			pck = new PackageWiseChargeComm("", operatorID, 0.0, "", 0.0, "", "");
		}

		return pck;
	}

}
