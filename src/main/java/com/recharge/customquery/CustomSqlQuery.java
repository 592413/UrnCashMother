package com.recharge.customquery;

public class CustomSqlQuery {
	public static String getClosingBalanceForsettlement = "select settleclbal from settlementreport where username = :userId order by id desc limit 1";

	/*--------------------------------------- DASH BOARD QUERY -------------------------------------------------------*/
	public static String totalSuccessPendingFailed = "select COALESCE(SUM(if(validation = 'SUCCESS',amount,0)),0) as SUCCESS, COALESCE(SUM(if(validation = 'PENDING',amount,0)),0) as PENDING, COALESCE(SUM(if(validation = 'FAILED',amount, 0)),0) as FAILED from rechargedetails where date = :date";
	public static String getTodayRechargeReport = "SELECT rd.*, u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller res where rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id desc limit 10";
	public static String getTotalUserTotalBalance = "SELECT count(userId) as UserId, COALESCE(SUM(balance),0) as BALANCE  FROM user where userId != 'admin' and delFlag='0'";

	public static String getTotalUserTotalBalanceByUser = "SELECT count(userId) as UserId, COALESCE(SUM(balance),0) as BALANCE  FROM user where uplineId = :uplineId and delFlag='0'";
	public static String totalSuccessPendingFailedByUser = "select COALESCE(SUM(if(validation = 'SUCCESS',amount,0)),0) as SUCCESS, COALESCE(SUM(if(validation = 'PENDING',amount,0)),0) as PENDING, COALESCE(SUM(if(validation = 'FAILED',amount, 0)),0) as FAILED from rechargedetails where userId = :userId and date = :date";
	public static String getTodayRechargeReportbyUser = "SELECT rd.*, u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where rd.userId = :userId and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id desc limit 10";

	public static String getTotalUserTotalBalanceByReseller = "SELECT COALESCE(count(userId),0) as UserId, COALESCE(SUM(balance),0) as BALANCE  FROM user where wl_id = :wlId and roleId != 2  and delFlag='0'";
	public static String totalSuccessPendingFailedByReseller = "select COALESCE(SUM(if(validation = 'SUCCESS',amount,0)),0) as SUCCESS, COALESCE(SUM(if(validation = 'PENDING',amount,0)),0) as PENDING, COALESCE(SUM(if(validation = 'FAILED',amount, 0)),0) as FAILED from rechargedetails where wl_id = :wlId and date = :date";
	public static String getTodayRechargeReportByReseller = "SELECT rd.*, u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where rd.wl_id = :wlId and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id desc limit 10";

	/*--------------------------------------------------------------------------------------------------------------------*/

	/*----------------------------------------------NOTIFICATION--------------------------------------------------------------*/
	public static String notificationForAdmin = "select (select count(*) as comp from complain as c where c.status = 'PENDING' and c.wl_id = :wlId) as complain,(select count(*) FROM balancerequest as br where br.status = 'PENDING' and br.request_user = :request_user) as balanceRequest, (select count(*) FROM utility as u where u.status = 'PENDING' and u.wl_id = :wlId) as utility, (select count(*) FROM insurance as i where i.status = 'PENDING' and i.wl_id = :wlId) as insurance;";
	public static String notificationForUpline = "select (select count(*) as comp from complain as c where c.status = '0') as complain,(select count(*) FROM balancerequest as br where br.status = 'PENDING' and br.request_user = :request_user) as balanceRequest, (select count(*) FROM utility as u where u.userName = '0') as utility, (select count(*) FROM insurance as i where i.status = '0') as insurance;";
	/*-------------------------------------------------------------------------------------------------------------------------*/
	public static String getAssignedPackage="select p.*,ap.user_id,u.name,u.mobile,u.roleId from package as p,assigned_package as ap,user as u where p.owner=:userId and p.package_id=ap.package_id and ap.user_id=u.userId";
	
	/*------------------------- USER RELATED QUERY --------------------------------------------------------------------------------------------------*/
	public static String checkLogin = "select userId from user where userId= :userId and password= :password and status = '0' and delFlag = '0'";
	public static String getUserIdByMobileEmail = "select userId from user where mobile= :mobile or email= :email";
	public static String getTotalbalanceForAdmin = "select sum(balance) as balance FROM user where userId != 'admin'";
	public static String getClosingBalanceForUser = "select closingBal from transactiondetails where userId = :userId order by transId desc limit 1";
	public static String getUplineIdByUserId = "select uplineId from user where userId= :userId";
	public static String getImpsChargeByUserIdAndAmount="select * from impscommission where userId=:userId and slab1=:slab1 and slab2=:slab2";
	/*------------------------------------------------------------------------------------------------------------------------------------------------*/

	/*-------------------------------------------------- DISCOUNT AND COMMISION --------------------------------------------------------*/
	public static String getMyDefaultDiscount = "select dc.commissionId, dc.userId, dc.operatorId, dc.superDistributor,dc.distributor, dc.retailer, o.serviceProvider from defaultcommission as dc, operator as o where dc.userId = :userId and dc.operatorId = o.operatorId";
	public static String getIndividualDiscount = "select ic.commissionId, ic.userName, ic.operatorId,o.serviceProvider, ic.commission FROM individualcommission as ic, operator as o where ic.userName = :userId and ic.operatorId = o.operatorId;";
	public static String getDefaultCharge = "select cs.chargeId, cs.wl_id, cs.resId, cs.operatorId, cs.sdist, cs.dist, cs.ret, cs.flag, o.serviceProvider FROM chargeset as cs, operator as o where cs.resId = :userId and o.operatorId = cs.operatorId;";
	public static String getIndividualCharge = "select ic.id, ic.userId, ic.rupees,ic.percentage, ic.flag, ic.wl_id, o.serviceProvider,ic.operatorId FROM individualcharge as ic, operator as o where ic.userId = :userId and ic.operatorId = o.operatorId;";
	/*---------------------------------------------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------- PACKAGE AND COMMISION --------------------------------------------------------*/
	public static String getPackagewisecharecom="select pc.package_id,pc.operator_id,pc.charge,pc.charge_type,pc.comm,pc.comm_type,o.serviceProvider from pck_charge_com as pc,operator as o where pc.package_id=:package_id and pc.operator_id = o.operatorId";
	public static String getPackageAndOperatorwisecharecom="select * from pck_charge_com where  package_id=:package_id and operator_id=:operator_id";
	
	/*---------------------------------------------------------------------------------------------------------------------------------*/

	/*-------------------------------------------------- PACKAGE AND COMMISION --------------------------------------------------------*/
	public static String getPackagewisecharecomaeps="select pc.package_id,pc.operator_id,pc.charge,pc.charge_type,pc.comm,pc.comm_type,aeps.slab1,aeps.slab2 from pck_charge_com as pc,aepscommission as aeps where pc.package_id=:package_id and pc.operator_id = aeps.id";
	public static String getPackagewisecharecomflight="select pc.package_id,pc.operator_id,pc.charge,pc.charge_type,pc.comm,pc.comm_type,s.airline_name from pck_charge_com as pc,airline as s where pc.package_id=:package_id and pc.operator_id = s.Id";
	public static String getPackagewisecharecombus="select pc.package_id,pc.operator_id,pc.charge,pc.charge_type,pc.comm,pc.comm_type from pck_charge_com as pc where pc.package_id=:package_id";
	public static String getPackagewisecharecomhotel="select pc.package_id,pc.operator_id,pc.charge,pc.charge_type,pc.comm,pc.comm_type from pck_charge_com as pc where pc.package_id=:package_id";
	public static String getPackagewisecharecomdmr="select pc.package_id,pc.operator_id,pc.charge,pc.charge_type,pc.comm,pc.comm_type,aeps.slab1,aeps.slab2 from pck_charge_com as pc,dmrcommission as aeps where pc.package_id=:package_id and pc.operator_id = aeps.id";

	/*---------------------------------------------------------------------------------------------------------------------------------*/

	
	/*---------------------------------------------------------- TRANSACTION REPORT QUERY --------------------------------------------------------------------------------*/
	public static String getTransactionReportForAll = "select td.*, u.name, u.firmName FROM transactiondetails as td, user as u where td.date between :startDate and :endDate and (td.credit != 0 or td.debit != 0) and td.userId = u.userId order by td.transId desc";
	public static String getTransactionReportForAllByReseller = "select td.*, u.name, u.firmName FROM transactiondetails as td, user as u where td.wl_id = :wlId and td.date between :startDate and :endDate and (td.credit != 0 or td.debit != 0) and td.userId = u.userId order by td.transId desc";
	public static String getTransactionReportForUser = "select td.*, u.name, u.firmName FROM transactiondetails as td, user as u where td.userId = :userId and td.wl_id = :wlId and td.date between :startDate and :endDate and (td.credit != 0 or td.debit != 0) and td.userId = u.userId order by td.transId desc";
	public static String getTransactionReportbyUser = "select td.*, u.name, u.firmName FROM transactiondetails as td, user as u where td.userId = :userId and td.date between :startDate and :endDate and (td.credit != 0 or td.debit != 0) and td.userId = u.userId  order by td.transId desc";
	/*-------------------------------------------------------------------------------------------------------------------------------------*/
	public static String getLatestRechargeDetails="SELECT rd.*, u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller res where rd.userId = :userId and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id desc limit 10";
	/*--------------------------------------- RECHARGE REPORT QUERY --------------------------------------------------------*/
	// for admin
	public static String getRechargeReportForAllByAdmin = "SELECT rd.*, u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where rd.date between :startDate and :endDate and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id desc";
	public static String getRechargeReportForOperatorByAdmin = "SELECT rd.*, u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy,res.poweredBy as whiteLebel as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where rd.operatorId = :operatorId and rd.date between :startDate and :endDate and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id desc";
	public static String getRechargeReportForStatusByAdmin = "SELECT rd.*,u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where rd.status = :status and rd.date between :startDate and :endDate and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id desc";
	public static String getRechargeReportFilterByAdmin = "SELECT rd.*,u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where rd.status = :status and rd.operatorId = :operatorId and rd.date between :startDate and :endDate and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id desc";

	// for reseller
	public static String getRechargeReportForAllByReseller = "SELECT rd.*, u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where rd.wl_id = :wlId and rd.date between :startDate and :endDate and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id";
	public static String getRechargeReportForOperatorByReseller = "SELECT rd.*, u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where rd.wl_id = :wlId and rd.operatorId = :operatorId and rd.date between :startDate and :endDate and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id";
	public static String getRechargeReportForStatusByReseller = "SELECT rd.*,u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where rd.wl_id = :wlId and rd.status = :status and rd.date between :startDate and :endDate and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id";
	public static String getRechargeReportFilterByReseller = "SELECT rd.*,u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where rd.wl_id = :wlId and rd.status = :status and rd.operatorId = :operatorId and rd.date between :startDate and :endDate and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id";

	// for super distributor and distributor
	public static String getRechargeReportforAllBySD = "SELECT rd.*,u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a,reseller as res, user as u "
			+ "where rd.date between :startDate and :endDate and (rd.userId = :userId or "
			+ "rd.userId in (select userid from user as u1 where u1.uplineId  = :uplineId) or "
			+ "rd.userId in (select userid from user as u1 where u1.uplineId in (select userId from user as u2 where u2.uplineId = :uplineId))) and"
			+ " rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id desc;";

	public static String getRechargeReportforAllAndOperatorBySD = "SELECT rd.*,u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a,reseller as res, user as u "
			+ "where rd.operatorId = :operatorId and rd.date between :startDate and :endDate and (rd.userId = :userId or "
			+ "rd.userId in (select userid from user as u1 where u1.uplineId  = :uplineId) or "
			+ "rd.userId in (select userid from user as u1 where u1.uplineId in (select userId from user as u2 where u2.uplineId = :uplineId))) "
			+ "and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id desc;";

	public static String getRechargeReportforAllAndStatusBySD = "SELECT rd.*,u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a,reseller as res, user as u "
			+ "where rd.status = :status and rd.date between :startDate and :endDate and (rd.userId = :userId or "
			+ "rd.userId in (select userid from user as u1 where u1.uplineId  = :uplineId) or "
			+ "rd.userId in (select userid from user as u1 where u1.uplineId in (select userId from user as u2 where u2.uplineId = :uplineId))) "
			+ "and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id desc;";

	public static String getRechargeReportforAllFilterBySD = "SELECT rd.*,u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a,reseller as res, user as u "
			+ "where rd.status = :status and rd.operatorId = :operatorId and rd.date between :startDate and :endDate and (rd.userId = :userId or "
			+ "rd.userId in (select userid from user as u1 where u1.uplineId  = :uplineId) or "
			+ "rd.userId in (select userid from user as u1 where u1.uplineId in (select userId from user as u2 where u2.uplineId = :uplineId))) "
			+ "and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id desc;";

	// for retailer and own Report
	public static String getRechargeReportForAllbyUser = "SELECT rd.*, u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where rd.userId = :userId and rd.date between :startDate and :endDate and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id";
	public static String getRechargeReportForOperatorByUser = "SELECT rd.*, u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where rd.userId = :userId and rd.operatorId = :operatorId and date between :startDate and :endDate and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id";
	public static String getRechargeReportForStatusByUser = "SELECT rd.*, u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where rd.userId = :userId and rd.status = :status and date between :startDate and :endDate and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id";
	public static String getRechargeReportFilterByUser = "SELECT rd.*,u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where rd.userId = :userId and rd.status = :status and rd.operatorId = :operatorId and date between :startDate and :endDate and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id";
	/*-------------------------------------------------------------------------------------------------------------------------*/
	/*--------------------------------------- IMPS REPORT QUERY --------------------------------------------------------*/
	public static String getIMPSReportForAdmin="select dt.*,u.name,u.mobile from dmr_transaction as dt,user as u where dt.date between :start_date and :end_date and dt.username=u.userId";
	public static String getIMPSReportForUser="select dt.*,u.name,u.mobile from dmr_transaction as dt,user as u where dt.date between :start_date and :end_date and dt.username=:username and dt.username=u.userId"; 
	public static String getDmrTransactionByRecieptId="select dt.*,u.name,u.mobile from dmr_transaction as dt,user as u where dt.recieptId=:transId and dt.username=u.userId";
	public static String getDmrReport="select * from dmr_transaction where bene_mobile=:mobile";
	
	

	/*---------------------------------------P2A IMPS REPORT QUERY --------------------------------------------------------*/
	public static String getP2AReportForAdmin="select it.*,pm.BeneAccNo,pm.BeneIFSC,pm.RemName,pm.RemMobile,pm.RetailerCode,u.name,u.mobile from impssettlement as it,p2amoneyuser as pm,user as u where it.date between :start_date and :end_date and it.userId=u.userId and pm.userId=u.userId  order by it.id desc";
	public static String getP2AReportForUser="select it.*,pm.BeneAccNo,pm.BeneIFSC,pm.RemName,pm.RemMobile,pm.RetailerCode,u.name,u.mobile from impssettlement as it,p2amoneyuser as pm,user as u where it.date between :start_date and :end_date and it.userId=:username and it.userId=u.userId and pm.userId=u.userId  order by it.id desc";
	public static String getP2AUser="select pm.*,u.name,u.mobile from p2amoneyuser as pm,user as u where pm.userId=u.userId";
	public static String getP2AUserwlid="select pm.*,u.name,u.mobile from p2amoneyuser as pm,user as u where pm.userId=u.userId and u.wl_id=:wl_id";
	/*---------------------------------------APPLICATION REPORT --------------------------------------------------------*/
	public static String getAppReportForAdmin="select ap.*,u.name,u.mobile from ezpayapplicationform as ap,user as u where ap.date between :start_date and :end_date and ap.userId=u.userId";
	public static String getAppreportwlid="select pm.*,u.name,u.mobile from ezpayapplicationform as pm,user as u where pm.date between :start_date and :end_date and pm.userId=u.userId and u.wl_id=:wl_id";
	public static String getAppReportForUser="select ap.*,u.name,u.mobile from ezpayapplicationform as ap,user as u where ap.date between :start_date and :end_date and  ap.userId =:username and  ap.userId=u.userId ";
	/*---------------------------------------APPLICATION REPORT --------------------------------------------------------*/
	/*---------------------------------------WALLET REPORT --------------------------------------------------------*/
	public static String getWALLETReportForAdmin="select wl.*,u.name,u.mobile,el.cardreferencenumber,el.applicantmobileno from cardwalletrequest as wl,user as u,ezpayapplicationform as el  where wl.date between :start_date and :end_date and wl.userId=u.userId and el.applicationid=wl.applicationid";
	public static String getWALLETReportForUser="select wl.*,u.name,u.mobile from cardwalletrequest as wl,user as u where wl.date between :start_date and :end_date and  wl.userId =:username and  wl.userId=u.userId ";
	
	/*---------------------------------------WALLET REPORT --------------------------------------------------------*/
	

	/*-------------------------------------------------------------Locked Amount-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	public static String getRechargeLock = "SELECT rd.time FROM rechargedetails as rd where rd.mobile = :mobile and rd.userId = :userId and rd.amount = :amount and rd.date = :date and rd.status = 'SUCCESS' order by rd.id desc limit 1";
	public static String getAddBalanceLock = "SELECT bt.time FROM balancetransafer as bt where bt.fromId = :fromId and bt.toId = :toId and bt.transferAmount = :transferAmount and bt.date = :date  order by bt.transferId desc limit 1";
	/*---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	/*-------------------------API SWITCHING-----------------------------------------------------------------------------------------------------------------------------------------*/
	public static String getOperatorForSwitching = "SELECT o.*, a.apiName FROM operator as o, api as a where o.apiId = a.apiId";
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	/*---------------------------------------------------------- REVERT BALANCE REPORT QUERY --------------------------------------------------------------------------------*/
	public static String getRevertBalanceReportForAdmin = "select td.*, u.name, u.firmName FROM transactiondetails as td, user as u where td.date between :startDate and :endDate and td.narration like '%REVERT BALANCE BY ADMIN%' and td.userId = u.userId order by td.transId desc";
	public static String getRevertBalanceReportForWLAdmin = "select td.*, u.name, u.firmName FROM transactiondetails as td, user as u where td.wl_id = :wlId and td.date between :startDate and :endDate and td.narration like '%REVERT BALANCE BY ADMIN%' and td.userId = u.userId order by td.transId desc";
	public static String getRevertBalanceReportForUser = "select td.*,u.name, u.firmName FROM transactiondetails as td, user as u where td.userId = :userId and td.wl_id = :wlId and td.date between :startDate and :endDate and td.narration like '%REVERT BALANCE%' and td.userId = u.userId order by td.transId desc";

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	/*----------------------------------------------VIEW USER QUERY ------------------------------------------------------------------------*/

	/*--------------------------------------------------------------------------------------------------------------------------------------*/

	/*-------------------------------------------------Complain Query----------------------------------------------------------------*/
	// latest Query
	public static String viewlatestComplainByAdmin = "SELECT c.*, u.mobile, u.name, u.firmName FROM complain as c, user as u where u.userId = c.userId order by id desc limit 10";
	public static String viewlatestComplainByReseller = "SELECT c.*, u.mobile,u.name, u.firmName FROM complain as c, user as u where c.wl_id = :wlId and u.userId = c.userId order by id desc limit 10";
	public static String viewlatestComplainByUser = "SELECT c.*, u.mobile,u.name, u.firmName FROM complain as c, user as u where c.userId = :userId and u.userId = c.userId order by id desc limit 10";

	// pending query

	public static String viewpendingComplainByAdmin = "SELECT c.*, u.mobile, u.name, u.firmName FROM complain as c, user as u where c.status = 'PENDING' and u.userId = c.userId order by id desc";
	public static String viewpendingComplainByReseller = "SELECT c.*, u.mobile, u.name, u.firmName FROM complain as c, user as u where c.wl_id = :wlId and c.status = 'PENDING' and u.userId = c.userId order by id desc";
	public static String viewpendingComplainByUser = "SELECT c.*, u.mobile, u.name, u.firmName FROM complain as c, user as u where c.userId = :userId and c.status = 'PENDING' and u.userId = c.userId order by id desc";

	// Query for date wise
	public static String viewComplainByAdmin = "SELECT c.*, u.mobile,u.name, u.firmName FROM complain as c, user as u where date between :startDate and :endDate and u.userId = c.userId order by id desc";
	public static String viewComplainByReseller = "SELECT c.*, u.mobile, u.name, u.firmName FROM complain as c, user as u where c.wl_id = :wlId and date between :startDate and :endDate and u.userId = c.userId order by id desc";
	public static String viewComplainByUser = "SELECT c.*, u.mobile, u.name, u.firmName FROM complain as c, user as u where c.userId = :userId and date between :startDate and :endDate and u.userId = c.userId order by id desc";
	/*--------------------------------------------------------------------------------------------------------------------------------------------*/

	/*-------------------------------------------------BALANCE REQUEST QUERY-------------------------------------------------------*/
	public static String viewPendingBalanceRequest = "SELECT br.*, u.name, u.mobile FROM balancerequest as br, user as u where br.request_user = :request_user and br.status = 'PENDING' and br.userName = u.userId order by requestId desc;";
	public static String viewBalanceRequest = "SELECT br.*, u.name, u.mobile FROM balancerequest as br, user as u where br.request_user = :request_user and br.date between :startDate and :endDate and br.userName = u.userId order by requestId desc;";
	/*--------------------------------------------------------------------------------------------------------------------------------*/

	/*-------------------------------------------------------Advanced Search Query---------------------------------------------------------------------*/
	public static String advancedSearchOnCustomerNoByAdmin = "SELECT rd.*,u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where (rd.mobile = :searchKeyword or rd.tId = :searchKeyword) and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id";
	public static String advancedSearchOnCustomerNoByReseller = "SELECT rd.*,u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where (rd.mobile = :searchKeyword or rd.tId = :searchKeyword) and rd.wl_id = :wlId and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id";
	public static String advancedSearchOnCustomerNoByUser = "SELECT rd.*, u.name, u.firmName,u.mobile as m, o.serviceProvider, a.apiName,res.poweredBy as whiteLebel FROM rechargedetails as rd, operator as o, api as a, user as u,reseller as res where (rd.mobile = :searchKeyword or rd.tId = :searchKeyword) and (rd.userId = :userId or rd.userId in (select userId from user as u1 where u1.uplineId = :userId or rd.userId in (select userId from user as u1 where u1.uplineId in (select userId from user as u2 where u2.uplineId = :userId)))) and rd.userId = u.userId and o.operatorId = rd.operatorId and a.apiId = rd.apiId and res.wl_id=rd.wl_id order by rd.id";

public static String advancedSearchUserByUser = "Select * from user As user where user.mobile = :mobile";
	
	public static String advancedSearchUserByAdmin="Select user.*,us.name as uplinename,us.mobile as uplineMobile,us.firmName as UplineFirmName from user As user,user as us where (user.mobile = :mobile or user.email = :mobile or user.name = :mobile) and user.roleId != 1 and user.delFlag = '0' and user.uplineId=us.userId";
	public static String advancedSearchUserByUsers="Select user.*,us.name as uplinename,us.mobile as uplineMobile,us.firmName as UplineFirmName from user As user,user as us where (user.mobile = :mobile or user.email = :mobile or user.name = :mobile) and (user.uplineId = :uplineId or user.uplineId in (select userId from user as u1 where u1.uplineId = :uplineId)) and user.roleId != 1 and user.delFlag = '0' and user.uplineId=us.userId";
/*----------------------------------------------------------------------------------------------------------------------------------------------------*/

	/*------------------------------------------------PURCHASE REPORT QUERY-----------------------------------------------------------------*/
	public static String getMyPurchaseReport = "select bt.*, u1.name as from_name from balancetransafer as bt, user as u1 where bt.toId = :userId and bt.date between :startDate and :endDate and bt.fromId = u1.userId;";
	
	public static String totalPurchase="select COALESCE(sum(transferAmount),0) as purchase_amount from balancetransafer where toId = :userId and date=:date";
	/*---------------------------------------------------------------------------------------------------------------------------*/
	/*-----------------------------------------------BALANCE TRANSFER---------------------------------------------------------------------*/
	public static String getMyBalanceTransferReport = "select bt.*, u1.name as to_name from balancetransafer as bt, user as u1 where bt.fromId = :userId and bt.date between :startDate and :endDate and bt.toId = u1.userId;";
	/*-------------------------------------------------------------------------------------------------------------------------------*/

	/*------------------------------------------------Utility Report---------------------------------------------------------------*/
	public static String viewPendingUtilityReportByAdmin = "SELECT ut.*, u.name, u.mobile FROM utility as ut, user as u where ut.wl_id = :wlId and ut.status = 'PENDING' and ut.userName = u.userId order by utilityId desc;";
	public static String viewUtilityReportByAdmin = "SELECT ut.*, u.name, u.mobile FROM utility as ut, user as u where ut.wl_id = :wlId and ut.submitDate between :startDate and :endDate and ut.userName = u.userId order by utilityId desc;";
	public static String viewUtilityReportByUser = "SELECT ut.*, u.name, u.mobile FROM utility as ut, user as u where ut.userName = :userId and ut.submitDate between :startDate and :endDate and ut.userName = u.userId order by utilityId desc;";
	/*--------------------------------------------------------------------------------------------------------------------------------*/

	/*------------------------------------------------Insurance Report---------------------------------------------------------------*/
	public static String viewPendingInsuranceReportByAdmin = "SELECT ins.*, u.name, u.mobile, op.serviceProvider FROM insurance as ins, user as u, operator as op where ins.wl_id = :wlId and ins.status = 'PENDING' and ins.userId = u.userId and op.operatorId = ins.operatorCode order by ins.id desc;";
	public static String viewInsuranceByAdmin = "SELECT ins.*, u.name, u.mobile, op.serviceProvider FROM insurance as ins, user as u, operator as op where ins.wl_id = :wlId and ins.submitDate between :startDate and :endDate and ins.userId = u.userId and op.operatorId = ins.operatorCode order by ins.id desc;";
	public static String viewInsuranceByUser = "SELECT ins.*, u.name, u.mobile, op.serviceProvider FROM insurance as ins, user as u, operator as op where ins.userId = :userId and ins.submitDate between :startDate and :endDate and ins.userId = u.userId and op.operatorId = ins.operatorCode order by ins.id desc;";
	/*--------------------------------------------------------------------------------------------------------------------------------*/

	/*----------------------------Get Reseller Details------------------------------------------------------------------------*/
	public static String getResellerDetails = "SELECT u.*, r.poweredBy,r.poweredByLink,r.pageTitle FROM user as u, reseller as r where u.wl_id = :wlId and u.roleId = 2 and u.wl_id = r.wl_id;";
	/*-----------------------------------------------------------------------------------------------------------------------------------*/

	/*---------------------------------------------------------- REFUND REPORT QUERY --------------------------------------------------------------------------------*/
	public static String getRefundReportForAll = "select td.*, u.name, u.firmName FROM transactiondetails as td, user as u where td.date between :startDate and :endDate and (td.credit != 0 or td.debit != 0) and td.remarks = 'REFUND' and td.userId = u.userId order by td.transId desc";
	public static String getRefundReportForAllByReseller = "select td.*, u.name, u.firmName FROM transactiondetails as td, user as u where td.wl_id = :wlId and td.date between :startDate and :endDate and (td.credit != 0 or td.debit != 0) and td.remarks = 'REFUND' and td.userId = u.userId order by td.transId desc";
	public static String getRefundReportForUser = "select td.*, u.name, u.firmName FROM transactiondetails as td, user as u where td.userId = :userId and td.wl_id = :wlId and td.date between :startDate and :endDate and (td.credit != 0 or td.debit != 0) and td.remarks = 'REFUND' and td.userId = u.userId order by td.transId desc";
	public static String getRefundReportbyUser = "select td.*, u.name, u.firmName FROM transactiondetails as td, user as u where td.userId = :userId and td.wl_id = :wlId and td.date between :startDate and :endDate and (td.credit != 0 or td.debit != 0) and td.remarks = 'REFUND' and td.userId = u.userId  order by td.transId desc";
	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	/*--------------------------------------------------EARNING REPORT--------------------------------------------------------------------------*/

	public static String getEarningReportForAllByAdmin = "SELECT es.*, u.name, u.mobile FROM earning_surcharge as es, user as u where es.date between :startDate and :endDate and u.userId = es.userId order by id desc;";

	public static String getEarningReportForUserByAdmin = "SELECT es.*, u.name, u.mobile FROM earning_surcharge as es, user as u where es.roleId = :roleId and es.date between :startDate and :endDate and u.userId = es.userId order by id desc;";

	public static String getEarningReportForUserByReseller = "SELECT es.*, u.name, u.mobile FROM earning_surcharge as es, user as u where es.wl_id = :wlId and es.date between :startDate and :endDate and u.userId = es.userId order by id desc;";

	public static String getEarningReportForFirstDownline = "SELECT es.*, u.name, u.mobile FROM earning_surcharge as es, user as u where es.userId in (select userId from user as u1 where u1.uplineId = :uplineId) and es.date between :startDate and :endDate and u.userId = es.userId order by id desc;";

	public static String getEarningReportForSecondDownline = "SELECT es.*, u.name, u.mobile FROM earning_surcharge as es, user as u where es.userId in (select userId from user as u1 where u1.uplineId in (select userId from user as u1 where u1.uplineId = :uplineId)) and es.date between :startDate and :endDate and u.userId = es.userId order by id desc;";

	public static String getEarningReportForThirdDownLine = " SELECT es.*, u.name, u.mobile FROM earning_surcharge as es, user as u where es.userId in (select userId from user as u1 where u1.uplineId in (select userId from user as u1 where u1.uplineId in (select userId from user as u1 where u1.uplineId = :uplineId))) and es.date between :startDate and :endDate and u.userId = es.userId order by id desc;";

	public static String getEarningReportForOwn = "SELECT es.*, u.name, u.mobile FROM earning_surcharge as es, user as u where es.userId = :userId and es.date between :startDate and :endDate and u.userId = es.userId order by id desc;";
	
	public static String totalEarning="select COALESCE(sum(comm),0) as earning_amount from earning_surcharge where userId = :userId and date=:date";

	/*--------------------------------------- PAN REPORT QUERY --------------------------------------------------------*/
	public static String getpanReportForAdmin="select dt.*,u.name as usernm,u.mobile as usermbl from createagent as dt,user as u where dt.date between :start_date and :end_date and dt.userId=u.userId order by dt.id desc";
	public static String getpanReportForReseller="select dt.*,u.name as usernm,u.mobile as usermbl from createagent as dt,user as u where dt.date between :start_date and :end_date and dt.userId in (select u1.userid from user as u1 where u1.wl_id=:username) and dt.userId=u.userId order by dt.id desc";
	public static String getpanReportForUser="select dt.*,u.name as usernm,u.mobile as usermbl from createagent as dt,user as u where dt.date between :start_date and :end_date and dt.userId=:username and dt.userId=u.userId order by dt.id desc"; 
	public static String getpanReportForSD="select dt.*,u.name as usernm,u.mobile as usermbl from createagent as dt,user as u where dt.date between :start_date and :end_date and "+
	"(dt.userId=:username or dt.userId in (select userid from user as u1 where u1.uplineId  = :username) or "+
    "dt.userId in (select userid from user as u1 where u1.uplineId in "+
    "(select userId from user as u2 where u2.uplineId = :username))) and dt.userId=u.userId order by dt.id desc";
	/*--------------------------------------- PAN Coupon REPORT QUERY --------------------------------------------------------*/
	public static String getpanCouponReportForAdmin="select dt.*,u.name as usernm,u.mobile as usermbl from couponrequest as dt,user as u where dt.date between :start_date and :end_date and dt.userId=u.userId order by dt.id desc";
	public static String getpanCouponReportForReseller="select dt.*,u.name as usernm,u.mobile as usermbl from couponrequest as dt,user as u where dt.date between :start_date and :end_date and dt.userId in (select u1.userid from user as u1 where u1.wl_id=:username) and dt.userId=u.userId order by dt.id desc";
	public static String getpanCouponReportForUser="select dt.*,u.name as usernm,u.mobile as usermbl from couponrequest as dt,user as u where dt.date between :start_date and :end_date and dt.userId=:username and dt.userId=u.userId order by dt.id desc"; 
	public static String getpanCouponReportForSD="select dt.*,u.name as usernm,u.mobile as usermbl from couponrequest as dt,user as u where dt.date between :start_date and :end_date and "+
			"(dt.userId=:username or dt.userId in (select userid from user as u1 where u1.uplineId  = :username) or "+
		    "dt.userId in (select userid from user as u1 where u1.uplineId in "+
		    "(select userId from user as u2 where u2.uplineId = :username))) and dt.userId=u.userId order by dt.id desc";
	
	
	/*---------------------------------------------------VIEW USER--------------------------------------------------------------------------*/
	public static String GetAllUserByAdmin="select user.*,us.name as uplinename,us.mobile as uplineMobile,us.firmName as UplineFirmName from user as user,user as us where user.userId != 'admin' and user.roleId != 2 and user.delFlag = '0' and user.uplineId=us.userId order by user.name";
	public static String getResellerByAdmin="select user.*,us.name as uplinename,us.mobile as uplineMobile,us.firmName as UplineFirmName from user as user,user as us where user.roleId = 2 and user.delFlag = '0' and user.uplineId=us.userId order by user.name";
	public static String GetUserByRoleIdAndWlId="select user.*,us.name as uplinename,us.mobile as uplineMobile,us.firmName as UplineFirmName from user as user,user as us where user.roleId=:roleId and user.wl_id=:wlId and user.delFlag = '0' and user.uplineId=us.userId order by user.name";
	public static String GetAllUserByReseller="select user.*,us.name as uplinename,us.mobile as uplineMobile,us.firmName as UplineFirmName from user as user,user as us where user.roleId != 2 and user.wl_id=:wlId and user.delFlag = '0' and user.uplineId=us.userId order by user.name";
	public static String getMyUserForSD="select user.*,us.name as uplinename,us.mobile as uplineMobile,us.firmName as UplineFirmName from user as user,user as us where user.uplineId = :userId or user.uplineId in (select userId from user as u1 where u1.uplineId = :userId) and user.delFlag = '0' and user.uplineId=us.userId order by user.name";
	public static String getMyRetailerForSD="select user.*,us.name as uplinename,us.mobile as uplineMobile,us.firmName as UplineFirmName from user as user,user as us where user.uplineId in (select userId from user as u1 where u1.uplineId = :userId) and user.delFlag = '0' and user.uplineId=us.userId order by user.name";
	public static String GetUserByUplineId="select user.*,us.name as uplinename,us.mobile as uplineMobile,us.firmName as UplineFirmName from user as user,user as us where user.uplineId=:uplineId and user.delFlag = '0' and user.uplineId=us.userId order by user.name";
	
	
	public static String GetAllInactiveUserByAdmin="select user.*,us.name as uplinename,us.mobile as uplineMobile,us.firmName as UplineFirmName from user as user,user as us where user.userId != 'admin' and user.roleId != 2 and user.delFlag = '0' and user.status='1' and user.uplineId=us.userId order by user.name";
	public static String getInActiveResellerByAdmin="select user.*,us.name as uplinename,us.mobile as uplineMobile,us.firmName as UplineFirmName from user as user,user as us where user.roleId = 2 and user.delFlag = '0' and user.uplineId=us.userId and user.status='1' order by user.name";
	public static String GetInactiveUserByRoleId="select user.*,us.name as uplinename,us.mobile as uplineMobile,us.firmName as UplineFirmName from user as user,user as us where user.roleId=:roleId and user.status='1' and user.delFlag = '0' and user.uplineId=us.userId order by user.name";

/*-------------------------------------------NSDL PAN------------------------------------------*/
	public static String GetNSDLPanforamin="select n.*,u.name as uname,u.mobile as umobile from nsdlpan as n,user as u where n.userId=u.userId and n.date between :startDate and :endDate order by id desc";
	public static String getNSDLPanReportackno="select n.*,u.name as uname,u.mobile as umobile from nsdlpan as n,user as u where n.userId=u.userId and n.ackno=:ackno order by id desc";
	public static String getNSDLPanPendingAdminstatus="select n.*,u.name as uname,u.mobile as umobile from nsdlpan as n,user as u where n.userId=u.userId and n.date between :startDate and :endDate and n.status=:status order by id desc";
	public static String GetNSDLPanforuser="select n.*,u.name as uname,u.mobile as umobile from nsdlpan as n,user as u where n.userId=u.userId and n.date between :startDate and :endDate and n.userId=:userId order by id desc";
	public static String GetNSDLPanforuserstatus="select n.*,u.name as uname,u.mobile as umobile from nsdlpan as n,user as u where n.userId=u.userId and n.date between :startDate and :endDate and n.userId=:userId and n.status=:status order by id desc";

	public static String getNSDLPanPendingAdmin="select n.*,u.name as uname,u.mobile as umobile from nsdlpan as n,user as u where n.userId=u.userId and n.status=:status order by id desc";
	public static String getNSDLPanPendingHoldAdmin="select n.*,u.name as uname,u.mobile as umobile from nsdlpan as n,user as u where n.userId=u.userId and n.status in('PENDING','HOLD') order by id desc";
	
	public static String getAEPSReport = "select a.*,aeps.username from rblresponse as a,aepsusermap  as aeps where a.date between :start_date AND :end_date and a.agentcode=aeps.agentcode order by a.id desc";
	public static String getAEPSUserdetail = "select u.*,aeps.api,aeps.agentcode,COALESCE(s.settlementbalance,0) as settlementbalance from user as u inner Join aepsusermap  as aeps on  aeps.username=u.userId LEFT JOIN settlementwallet as s on aeps.username=s.username";
	public static String getAEPSUserdetailByWlid = "select u.*,aeps.api,aeps.agentcode,COALESCE(s.settlementbalance,0) as settlementbalance from user as u inner Join aepsusermap  as aeps on  aeps.username=u.userId LEFT JOIN settlementwallet as s on aeps.username=s.username where  u.wl_id=:wl_id";

	public static String getTotalSettleWalletbalance = "select sum(settlementbalance) as balance FROM settlementwallet";
	
	public static String getYesAepsReportAll="select a.*,user.name,user.mobile from yesbankaepsresponse as a,user  where a.date between :start_date AND :end_date and user.username=a.username  order by a.id desc";
	
	public static String getMicroAepsReportAll="select a.*,user.name,user.mobile from microatmresponse as a,user  where a.date between :start_date AND :end_date and user.username=a.username  order by a.id desc";
	
	public static String getMicroAepsReportNewAll="select a.*,user.name,user.mobile from microatmresponsenew as a,user  where a.date between :start_date AND :end_date and user.username=a.username  order by a.id desc";
	
	public static String getFingerAepsReportAll="select a.*,user.name,user.mobile from fingerpayaepsresponse as a,user  where a.date between :start_date AND :end_date and user.username=a.username  order by a.id desc";
	
	
	
	public static String getYesAepsReportAllwl="select a.*,user.name,user.mobile from yesbankaepsresponse as a,user  where a.date between :start_date AND :end_date and user.username=a.username and user.wl_id=:wl_id  order by a.id desc";
	
	public static String getMicroAepsReportAllwl="select a.*,user.name,user.mobile from microatmresponse as a,user  where a.date between :start_date AND :end_date and user.username=a.username and user.wl_id=:wl_id  order by a.id desc";
	
	public static String getMicroAepsReportNewAllwl="select a.*,user.name,user.mobile from microatmresponsenew as a,user  where a.date between :start_date AND :end_date and user.username=a.username and user.wl_id=:wl_id  order by a.id desc";
	
	public static String getFingerAepsReportAllwl="select a.*,user.name,user.mobile from fingerpayaepsresponse as a,user  where a.date between :start_date AND :end_date and user.username=a.username and user.wl_id=:wl_id  order by a.id desc";
	
	public static String getAepsDetailsForAdmin="select COALESCE(SUM(transactionamount),0)  from fingerpayaepsresponse where transactionType='WITHDRAWAL' AND STATUS <> 'failed' AND date = :date";
	
	public static String getMicroatmDetailsForAdmin="select COALESCE(SUM(amount),0)  from microatmresponse where  STATUS <> 'Failed' AND date = :date";
	
	public static String getDmrDetailsForAdmin="select COALESCE(SUM(amount),0)  from dmr_transaction where  STATUS <> 'FAILED' AND date = :date";
	
	public static String getFlightBookingForAdmin="select COALESCE(SUM(totalamount),0) from flight_fare ff inner join flight_booking fb on ff.traceId=fb.traceid where date=:date";
	
	public static String getRechargeDetailsForAdmin="select COALESCE(SUM(amount),0)  from rechargedetails where  STATUS <> 'FAILED' AND date = :date";
	
	public static String getTotalWhiteLevelUser="SELECT count(userId) as UserId, COALESCE(SUM(balance),0) as BALANCE  FROM user where userId != 'admin' and roleId=2 and  delFlag='0'";
	
	public static String getAepsDetailsForOthers="select COALESCE(SUM(transactionamount),0) from fingerpayaepsresponse fp inner join user u on fp.username=u.userName where transactionType='WITHDRAWAL' AND fp.STATUS <> 'failed' AND u.uplineId=:userId AND date = :date ";
	
	public static String getMicroatmDetailsForOthers="select COALESCE(SUM(amount),0) from microatmresponse ma inner join user u on ma.username=u.userName where  ma.STATUS <> 'Failed' AND u.uplineId=:userId AND date = :date";
	
	public static String getDmrDetailsForOthers="select COALESCE(SUM(amount),0)  from dmr_transaction dt inner join user u on dt.username=u.userName where  dt.STATUS <> 'FAILED' AND  u.uplineId=:userId AND date = :date";
	
	public static String getPayoutAdmin="select dt.*,u.name,u.mobile from openpayout as dt,user as u where dt.date between :startdate and :enddate and dt.userId=u.userId order by dt.id desc";
	public static String getPayoutwl="select dt.*,u.name,u.mobile from openpayout as dt,user as u where dt.date between :startdate and :enddate and dt.userId=u.userId and dt.wild=:wild order by dt.id desc";

	public static String getPayoutAdminmanual="select dt.*,u.name,u.mobile from openpayout as dt,user as u where dt.manudate between :startdate and :enddate and dt.userId=u.userId and dt.remark='MANUAL' order by dt.id desc";

	public static String getPayoutuser="select dt.*,u.name,u.mobile from openpayout as dt,user as u where dt.date between :startdate and :enddate and dt.userId=:userId and dt.userId=u.userId order by dt.id desc";

	
}
