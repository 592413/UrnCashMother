package com.recharge.businessimpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recharge.businessdao.CustomQueryServiceLogic;
import com.recharge.customModel.TransactionReport;
import com.recharge.customModel.UserWalletReport;
import com.recharge.customModel.UtilityReport;
import com.recharge.customModel.ViewAssignPackage;
import com.recharge.customModel.ViewUser;
import com.recharge.customModel.AEPSUserDetail;
import com.recharge.customModel.APPReport;
import com.recharge.customModel.BalanceRequest;
import com.recharge.customModel.BalanceTransfer;
import com.recharge.customModel.ComplainDetails;
import com.recharge.customModel.DefaultCharge;
import com.recharge.customModel.DefaultCommission;
import com.recharge.customModel.EarningSurchargeReport;
import com.recharge.customModel.ImpsReport;
import com.recharge.customModel.IndividualCharge;
import com.recharge.customModel.IndividualCommission;
import com.recharge.customModel.InsuranceReport;
import com.recharge.customModel.Notification;
import com.recharge.customModel.P2ATranferReport;
import com.recharge.customModel.P2AUserdetail;
import com.recharge.customModel.PanCouponReport;
import com.recharge.customModel.PanReport;
import com.recharge.customModel.RechargeReport;
import com.recharge.customModel.ResellerDetails;
import com.recharge.model.Complain;
import com.recharge.model.FingerPayAEPSResponse;
import com.recharge.model.ImpsTransaction;
import com.recharge.model.Impscommission;
import com.recharge.model.MicroAtmResponse;
import com.recharge.model.MicroAtmResponseNew;
import com.recharge.model.NSDLPan;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.PaymonkResponse;
import com.recharge.model.Rechargedetails;
import com.recharge.model.SettlementReport;
import com.recharge.model.webenquery;
import com.recharge.utill.GenerateRandomNumber;
import com.recharge.utill.UtilityClass;
import com.recharge.yesbankmodel.YesBankAEPSResponse;
import com.bankopen.model.OpenPayout;
import com.recharge.businessdao.CustomQueryService;

@Service("customQueryServiceLogic")
public class CustomQueryServiceLogicImpl implements CustomQueryServiceLogic{
	private static final Logger logger_log = Logger.getLogger(CustomQueryServiceLogic.class);
	
	@Autowired private SessionFactory sessionFactory;
	@Autowired CustomQueryService customQueryService;
	
	@Override
	public double getTotalbalanceForAdmin(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();		
		List<Object> list = new ArrayList<Object>();
		try {			
			list =(List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if(!list.isEmpty()){
				return Double.parseDouble(list.get(0).toString());
			}

		} catch (Exception e) {
			logger_log.error(" Error From  customQueryServiceLogic.getTotalbalanceForAdmin :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return 0;
	}
	
	@Override
	public ImpsTransaction getDmrTransactionByRecieptId(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		ImpsTransaction imptrans = null;
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			for (Object object : objlist) {
				Object[] obj = (Object[]) object;
				for(Object ob:obj){
					System.out.println("ob::::::::::::::::::::"+ob);
				}
				imptrans = new ImpsTransaction((Integer)obj[0],obj[1].toString(), obj[2].toString(), obj[3].toString(),
						obj[4].toString(), Double.parseDouble(obj[5].toString()), Double.parseDouble(obj[6].toString()),
						Double.parseDouble(obj[7].toString()), Double.parseDouble(obj[8].toString()), obj[9].toString(),
						obj[10].toString(), obj[11].toString(), obj[12].toString(), obj[13].toString(),Double.parseDouble(obj[14].toString()),obj[15].toString(),obj[16].toString(),Double.parseDouble(obj[17].toString()),Double.parseDouble(obj[18].toString()),obj[19].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getDmrTransactionByRecieptId :   ", e);
		} finally {
			session.close();
		}
		return imptrans;
	}
	
	
	
	@Override
	public Impscommission getImpsChargeByUserIdAndAmount(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<Impscommission> list = new ArrayList<Impscommission>();
		Impscommission imp = null;
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if (objlist.size() > 0) {
				for (Object object : objlist) {
					Object[] obj = (Object[]) object;
					imp = new Impscommission(obj[1].toString(), obj[2].toString(), obj[3].toString(), (Double) obj[4],
							(Double) obj[5], (Double) obj[6]);
					list.add(imp);
				}
			} else {
				imp = new Impscommission(parameters.get("userId"), "", "", 0.0, 0.0, 0.0);
			}

		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getImpsChargeByUserIdAndAmount :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return imp;
	}
	
	public List<PaymonkResponse> getPaymonkRespons(String query, Map<String, String> param) {
		List<PaymonkResponse> list = new ArrayList<>();
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query,param);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
					Object[] obj = (Object[]) object;
					PaymonkResponse pay = new PaymonkResponse(obj[1].toString(),obj[2].toString(),obj[3].toString(),obj[4].toString(),obj[5].toString(),obj[6].toString(),(Double)obj[7],obj[8].toString(),obj[9].toString(),obj[10].toString(),obj[11].toString(),obj[12].toString(),obj[13].toString(),obj[14].toString(),obj[15].toString(),obj[16].toString(),obj[17].toString(),obj[18].toString(),obj[19].toString(),obj[20].toString(),obj[21].toString(),obj[22].toString(),obj[23].toString(),obj[24].toString(),obj[25].toString(),(Double) obj[26] ,(Double) obj[27],(Double) obj[28] ,obj[29].toString(),obj[30].toString(),obj[31].toString(),obj[32].toString());
					pay.setUsername(obj[33].toString());
					list.add(pay);
				}
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getPaymonkRespons :   ", e);
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<SettlementReport> getSettleReport(String query, Map<String, String> param) {
		List<SettlementReport> list = new ArrayList<>();
		try{
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query,param);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
					
				}
				
			}
			
		}catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getSettleReport :   ", e);
			e.printStackTrace();
		}
		return list;
	}

	public double getTotalSettleWalletbalance(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();		
		List<Object> list = new ArrayList<Object>();
		try {			
			list =(List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if(!list.isEmpty()){
				if(list.get(0)!=null){
				return Double.parseDouble(list.get(0).toString());
				}else{
				return 0;	
				}
			}

		} catch (Exception e) {
			logger_log.error(" Error From  CustomQueryBusinessLogicImpl.getTotalSettleWalletbalance :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return 0;
	}
	
	
	@Override
	public List<AEPSUserDetail> getAEPSUserDetail(String query, Map<String, String> param) {
		List<AEPSUserDetail> list = new ArrayList<>();
		try{

			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query,param);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
					Object[] obj = (Object[]) object;
					

				AEPSUserDetail aeps = new AEPSUserDetail(obj[0].toString(),obj[1].toString(),Integer.parseInt(obj[2].toString()),obj[3].toString(),obj[4].toString(),obj[5].toString(),obj[6].toString(),obj[7].toString(),obj[8].toString(),obj[9].toString(),obj[10].toString(),obj[11].toString(),obj[12].toString(),obj[13].toString(),obj[14].toString(),obj[15].toString(),(Double)obj[16],obj[17].toString(),obj[18].toString(),obj[19].toString(),obj[20].toString(),(Double)obj[21],obj[22].toString(),obj[23].toString());
					list.add(aeps);
				}
			}
		
		}catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getAEPSUserDetail :   ", e);
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public List<AEPSUserDetail> getAEPSUserDetailwithbalance(String query, Map<String, String> param) {
		List<AEPSUserDetail> list = new ArrayList<>();
		try{

			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query,param);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
					Object[] obj = (Object[]) object;
					

				AEPSUserDetail aeps = new AEPSUserDetail(obj[0].toString(),obj[1].toString(),Integer.parseInt(obj[2].toString()),obj[3].toString(),obj[4].toString(),obj[5].toString(),obj[6].toString(),obj[7].toString(),obj[8].toString(),obj[9].toString(),obj[10].toString(),obj[11].toString(),obj[12].toString(),obj[13].toString(),obj[14].toString(),obj[15].toString(),(Double)obj[16],obj[17].toString(),obj[18].toString(),obj[19].toString(),obj[20].toString(),(Double)obj[21],obj[22].toString(),obj[23].toString(),(Double)obj[24]);
					list.add(aeps);
				}
			}
		
		}catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getAEPSUserDetail :   ", e);
			e.printStackTrace();
		}
		return list;
	}

	
	/*@Override
	public List<ImpsTransaction> getImpsReport(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<ImpsTransaction> list = new ArrayList<>();
		ImpsTransaction imptrans = null;
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			for (Object object : objlist) {
				Object[] obj = (Object[]) object;
				imptrans = new ImpsTransaction(obj[1].toString(), obj[2].toString(), obj[3].toString(),
						obj[4].toString(), Double.parseDouble(obj[5].toString()), Double.parseDouble(obj[6].toString()),
						Double.parseDouble(obj[7].toString()), Double.parseDouble(obj[8].toString()), obj[9].toString(),
						obj[10].toString(), obj[11].toString(), obj[12].toString(), obj[13].toString(),Double.parseDouble(obj[14].toString()));
				list.add(imptrans);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return list;
	}*/
	@Override
	public double getTotalPurchaseAmount(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();		
		List<Object> list = new ArrayList<Object>();
		try {			
			list =(List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			//System.out.println("purchase====="+ Double.parseDouble(list.get(0).toString()));
			if(!list.isEmpty()){
				return Double.parseDouble(list.get(0).toString());
			}else{
				return 0.0;
			}

		} catch (Exception e) {
			logger_log.error(" Error From  customQueryServiceLogic.getTotalPurchaseAmount :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return 0;
	}

	@Override
	public double getClosingBalanceForUser(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();		
		List<Object> list = new ArrayList<Object>();
		try {			
			list =(List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if(!list.isEmpty()){
				return Double.parseDouble(list.get(0).toString());
			}

		} catch (Exception e) {
			logger_log.error(" Error From  CustomQueryBusinessLogicImpl.getClosingBalanceForUser :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.recharge.businessdao.CustomQueryServiceLogic#getMyDefaultCommission(java.lang.String, java.util.Map)
	 */
	@Override
	public List<DefaultCommission> getMyDefaultCommission(String query, Map<String, String> parameters) {		
		Session session = sessionFactory.openSession();		
		List<DefaultCommission> list = new ArrayList<DefaultCommission>();
		try {			
			List<Object> objlist =(List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if(!objlist.isEmpty()){		

				for(Object object : objlist){	
					Object[] obj = (Object[]) object;
					DefaultCommission commission = new DefaultCommission((Integer)obj[0],obj[1].toString(),(Integer)obj[2], (Double)obj[3],(Double)obj[4], (Double)obj[5], obj[6].toString());
					list.add(commission);
				}
				return list;
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getMyDefaultCommission :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	
	}

	/* (non-Javadoc)
	 * @see com.recharge.businessdao.CustomQueryServiceLogic#getIndividualCommission(java.lang.String, java.util.Map)
	 */
	@Override
	public List<IndividualCommission> getIndividualCommission(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();		
		List<IndividualCommission> list = new ArrayList<IndividualCommission>();
		try {			
			List<Object> objlist =(List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if(!objlist.isEmpty()){		
				for(Object object : objlist){	
					Object[] obj = (Object[]) object;
					IndividualCommission commission = new IndividualCommission((Integer)obj[0], obj[1].toString(), (Integer)obj[2], obj[3].toString(), (Double)obj[4]);
					list.add(commission);
				}
				return list;
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getIndividualCommission :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.recharge.businessdao.CustomQueryServiceLogic#getMyDefaultCharge(java.lang.String, java.util.Map)
	 */
	@Override
	public List<DefaultCharge> getMyDefaultCharge(String query, Map<String, String> parameters) {		
		Session session = sessionFactory.openSession();		
		List<DefaultCharge> list = new ArrayList<DefaultCharge>();
		try {			
			List<Object> objlist =(List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if(!objlist.isEmpty()){	
				for(Object object : objlist){	
					Object[] obj = (Object[]) object;					
					DefaultCharge charge = new DefaultCharge((Integer)obj[0],obj[1].toString(),obj[2].toString(),(Integer)obj[3], (Double)obj[4],(Double)obj[5], (Double)obj[6], obj[7].toString(),obj[8].toString());
					list.add(charge);
				}
				return list;
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getMyDefaultCharge :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	
	}

	/* (non-Javadoc)
	 * @see com.recharge.businessdao.CustomQueryServiceLogic#getIndividualCharge(java.lang.String, java.util.Map)
	 */
	@Override
	public List<IndividualCharge> getIndividualCharge(String query, Map<String, String> parameters) {		
		Session session = sessionFactory.openSession();		
		List<IndividualCharge> list = new ArrayList<IndividualCharge>();
		try {			
			List<Object> objlist =(List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if(!objlist.isEmpty()){		

				for(Object object : objlist){	
					Object[] obj = (Object[]) object;
					IndividualCharge charge = new IndividualCharge((Integer)obj[0],obj[1].toString(),(Double)obj[2],(Double)obj[3], obj[4].toString(),obj[5].toString(),obj[6].toString(),(Integer)obj[7]);
					list.add(charge);
				}
				return list;
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getIndividualCharge :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	
	}

	/* (non-Javadoc)
	 * @see com.recharge.businessdao.CustomQueryServiceLogic#getUplineIdByUserId(java.lang.String, java.util.Map)
	 */
	@Override
	public String getUplineIdByUserId(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();	
		List<Object> list = new ArrayList<Object>();
		try {			
			list =(List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if(!list.isEmpty()){
				return list.get(0).toString();
			}
		} catch (Exception e) {
			logger_log.error(" Error From  CustomQueryBusinessLogicImpl.getUplineIdByUserId :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;		
	}	
	
	@Override
	public List<RechargeReport> getRechargeReport(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();		
		List<RechargeReport> list = new ArrayList<RechargeReport>();
		try {			
			List<Object> objlist =(List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if(!objlist.isEmpty()){	
				for(Object object : objlist){	
					Object[] obj = (Object[]) object;
					RechargeReport report = new RechargeReport((Integer)obj[0],obj[1].toString(),obj[2].toString(),(Integer)obj[3], (Double)obj[4], (Double)obj[5], (Double)obj[6], (Double)obj[7], (Double)obj[8],obj[9].toString(), obj[10].toString(), obj[11].toString(), obj[12].toString(), obj[13].toString(), 
							obj[14].toString(),(Integer)obj[15],(Integer)obj[16],obj[17].toString(), obj[18].toString(), obj[19].toString(),null,obj[21].toString(),obj[22].toString(),obj[23].toString(),obj[24].toString(),obj[25].toString(),obj[26].toString());
					list.add(report);
				}				
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getRechargeReport :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<RechargeReport> getLatestTransactionReport(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();		
		List<RechargeReport> list = new ArrayList<RechargeReport>();
		try {			
			List<Object> objlist =(List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if(!objlist.isEmpty()){	
				for(Object object : objlist){	
					Object[] obj = (Object[]) object;
					
					RechargeReport report = new RechargeReport((Integer)obj[0],obj[1].toString(),obj[2].toString(),(Integer)obj[3], (Double)obj[4], (Double)obj[5], (Double)obj[6], (Double)obj[7], (Double)obj[8],obj[9].toString(), obj[10].toString(), obj[11].toString(), obj[12].toString(), obj[13].toString(), 
							obj[14].toString(),(Integer)obj[15],(Integer)obj[16],obj[17].toString(), obj[18].toString(), obj[19].toString(),null,obj[21].toString(),obj[22].toString(),obj[23].toString(),obj[24].toString(),obj[25].toString(),obj[26].toString());
					list.add(report);
					System.out.println("list=="+list);
				}				
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getRechargeReport :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	
	@Override
	public List<TransactionReport> getTransactionReport(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<TransactionReport> list = new ArrayList<TransactionReport>();
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if (!objlist.isEmpty()) {

				for (Object object : objlist) {
					Object[] obj = (Object[]) object;
					TransactionReport report = new TransactionReport((Integer) obj[0], obj[1].toString(),
							(Double) obj[2], (Double) obj[3], (Double) obj[4], (Double) obj[5], obj[6].toString(),
							obj[7].toString(), obj[8].toString(), obj[9].toString(), (Double) obj[10], (Double) obj[11],
							obj[12].toString(), obj[13].toString(), obj[14].toString());
					list.add(report);
				}			
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getTransactionReport :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;

	}
	
	@Override
	public long getRechargeLock(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();		
		String startTime = "00:00:01 AM";
		long i = 0;
		try{
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if(!objlist.isEmpty()){
				startTime = objlist.get(0).toString();
			}			
			i = UtilityClass.timeDifference(startTime, GenerateRandomNumber.getCurrentTime());
		} catch (Exception e) {
			logger_log.error("Error From  getRechargeLock :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return i;
	}
	
	@Override
	public long getAddBalanceLock(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<Rechargedetails> list = new ArrayList<>();
		String startTime = "00:00:01 AM";
		long i = 0;
		try{
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if(!objlist.isEmpty()){
				startTime = objlist.get(0).toString();
			}			
			i = UtilityClass.timeDifference(startTime, GenerateRandomNumber.getCurrentTime());
		} catch (Exception e) {
			logger_log.error("Error From  getAddBalanceLock :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return i;
	}
	
	
	@Override
	public Notification getNotification(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();		
		Notification notification = new Notification();
		try {			
			List<Object> objlist =(List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if(!objlist.isEmpty()){		
				for(Object object : objlist){	
					Object[] obj = (Object[]) object;
					notification = new Notification(((BigInteger) obj[0]).intValue(), ((BigInteger) obj[1]).intValue(), ((BigInteger) obj[2]).intValue(),((BigInteger) obj[3]).intValue());
				}				
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getRechargeReport :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return notification;
	}
	
	
	@Override
	public List<ComplainDetails> viewComplain(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<ComplainDetails> list = new ArrayList<ComplainDetails>();
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
					Object[] obj = (Object[]) object;
					ComplainDetails complain = new ComplainDetails((Integer) obj[0], obj[1].toString(),
							obj[2].toString(), obj[3].toString(), obj[4].toString(), obj[5].toString(),
							obj[6].toString(), obj[7].toString(), obj[8].toString(), obj[9].toString(),
							obj[10].toString(), obj[11].toString(), obj[12].toString());
					list.add(complain);
				}			
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.viewComplain :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;

	}
	
	@Override
	public List<BalanceRequest> viewBalanceRequest(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<BalanceRequest> list = new ArrayList<BalanceRequest>();
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
					Object[] obj = (Object[]) object;
					BalanceRequest br = new BalanceRequest((Integer) obj[0], obj[1].toString(), obj[2].toString(),
							obj[3].toString(), obj[4].toString(), obj[5].toString(), obj[6].toString(),
							obj[7].toString(), obj[8].toString(), obj[9].toString(), obj[10].toString(),
							obj[11].toString(), obj[12].toString(), Double.parseDouble(obj[13].toString()),
							Double.parseDouble(obj[14].toString()), obj[15].toString(), obj[16].toString(),
							obj[17].toString(), obj[18].toString(), obj[19].toString());
					list.add(br);
				}			
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.viewBalanceRequest :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;

	}

	@Override
	public List<BalanceTransfer> viewPurchaseReport(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<BalanceTransfer> list = new ArrayList<BalanceTransfer>();
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
					Object[] obj = (Object[]) object;					
					BalanceTransfer bT = new BalanceTransfer((Integer) obj[0], obj[1].toString(),
							Double.parseDouble(obj[2].toString()), Double.parseDouble(obj[3].toString()),
							Double.parseDouble(obj[4].toString()), obj[5].toString(),
							Double.parseDouble(obj[6].toString()), Double.parseDouble(obj[7].toString()),
							Double.parseDouble(obj[8].toString()), obj[9].toString(), obj[10].toString(),
							obj[11].toString(), obj[12].toString(), obj[13].toString(), obj[14].toString(),
							obj[15].toString(), "");
					list.add(bT);
				}
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.viewPurchaseReport :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;

	}
	
	
	@Override
	public List<BalanceTransfer> viewBalanceTransferReport(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<BalanceTransfer> list = new ArrayList<BalanceTransfer>();
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
					Object[] obj = (Object[]) object;					
					BalanceTransfer bT = new BalanceTransfer((Integer) obj[0], obj[1].toString(),
							Double.parseDouble(obj[2].toString()), Double.parseDouble(obj[3].toString()),
							Double.parseDouble(obj[4].toString()), obj[5].toString(),
							Double.parseDouble(obj[6].toString()), Double.parseDouble(obj[7].toString()),
							Double.parseDouble(obj[8].toString()), obj[9].toString(), obj[10].toString(),
							obj[11].toString(), obj[12].toString(), obj[13].toString(), obj[14].toString(),
							"", obj[15].toString());
					list.add(bT);
				}
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.viewPurchaseReport :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;

	}

	@Override
	public List<UtilityReport> viewUtilityReport(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<UtilityReport> list = new ArrayList<UtilityReport>();
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
					Object[] obj = (Object[]) object;
					UtilityReport utilityReport = new UtilityReport((Integer) obj[0], obj[1].toString(), (Integer) obj[2],
							obj[3].toString(), (Integer) obj[4], obj[5].toString(), obj[6].toString(),
							obj[7].toString(), obj[8].toString(), obj[9].toString(),
							Double.parseDouble(obj[10].toString()), obj[11].toString(), obj[12].toString(),
							obj[13].toString(), obj[14].toString(), obj[15].toString(), obj[16].toString());
					list.add(utilityReport);
				}			
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.viewUtilityReport :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;

	}
	
	@Override
	public List<InsuranceReport> viewInsuranceReport(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<InsuranceReport> list = new ArrayList<InsuranceReport>();
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
					Object[] obj = (Object[]) object;					
					InsuranceReport insuranceReport = new InsuranceReport((Integer) obj[0], obj[1].toString(),
							obj[2].toString(), obj[3].toString(), obj[4].toString(), obj[5].toString(),
							obj[6].toString(), Double.parseDouble(obj[7].toString()), obj[8].toString(),
							obj[9].toString(), obj[10].toString(), obj[11].toString(), obj[12].toString(),
							obj[13].toString(), obj[14].toString(), obj[15].toString(), obj[16].toString(), obj[17].toString());
					list.add(insuranceReport);
				}			
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.viewUtilityReport :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;

	}
	
	
	@Override
	public List<ResellerDetails> getResellerDetails(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<ResellerDetails> list = new ArrayList<ResellerDetails>();
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
					Object[] obj = (Object[]) object;
					ResellerDetails resellerDetails = new ResellerDetails(obj[0].toString(), null, 0, obj[3].toString(),
							obj[4].toString(), obj[5].toString(), obj[6].toString(), obj[7].toString(),
							obj[8].toString(), obj[9].toString(), obj[10].toString(), obj[11].toString(),
							obj[12].toString(), obj[13].toString(), obj[14].toString(), null, null, null, null,  obj[19].toString(), null, 0.0,
							obj[22].toString(), obj[23].toString(), obj[24].toString());
					list.add(resellerDetails);
				}			
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getResellerDetails :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;

	}

	@Override
	public List<EarningSurchargeReport> getEarningReport(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<EarningSurchargeReport> list = new ArrayList<EarningSurchargeReport>();
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if (!objlist.isEmpty()) {

				for (Object object : objlist) {
					Object[] obj = (Object[]) object;					
					EarningSurchargeReport report = new EarningSurchargeReport((Integer) obj[0], (Integer) obj[1],
							obj[2].toString(), obj[3].toString(), (Double) obj[4], (Double) obj[5], obj[6].toString(),
							obj[7].toString(), obj[8].toString(), obj[9].toString(), obj[10].toString());
					
					list.add(report);
				}			
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getEarningReport :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;

	}
	
	@Override
	public List<ViewAssignPackage> getAssignedPackage(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<ViewAssignPackage> list = new ArrayList<ViewAssignPackage>();
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
					Object[] obj = (Object[]) object;
					ViewAssignPackage pack = new ViewAssignPackage((Integer) obj[0], obj[1].toString(),obj[2].toString(), obj[3].toString(), obj[4].toString(), obj[5].toString(), obj[6].toString(), obj[7].toString(), obj[8].toString());
					list.add(pack);
				}
			}

		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getAssignedPackage :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}
	
	@Override
	public List<PackageWiseChargeComm> getPackagewisecharecom(String query, Map<String, String> parameters,String service_type) {
		Session session = sessionFactory.openSession();
		List<PackageWiseChargeComm> list = new ArrayList<PackageWiseChargeComm>();
		PackageWiseChargeComm pack = null;
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
					Object[] obj = (Object[]) object;
					if(service_type.equalsIgnoreCase("Bus")){
						pack = new PackageWiseChargeComm(obj[0].toString(), (Integer) obj[1],
								(Double) obj[2], obj[3].toString(), (Double) obj[4], obj[5].toString(),"Bus");
					}else if(service_type.equalsIgnoreCase("Hotel")){
						pack = new PackageWiseChargeComm(obj[0].toString(), (Integer) obj[1],
								(Double) obj[2], obj[3].toString(), (Double) obj[4], obj[5].toString(),"Hotel");
					}else if(service_type.equalsIgnoreCase("RBL AEPS")){
						pack = new PackageWiseChargeComm(obj[0].toString(), (Integer) obj[1],
								(Double) obj[2], obj[3].toString(), (Double) obj[4], obj[5].toString(),"RBL AEPS");
						pack.setSlab1((Double) obj[6]);
						pack.setSlab2((Double) obj[7]);
					}else if(service_type.equalsIgnoreCase("YesBank AEPS")){
						pack = new PackageWiseChargeComm(obj[0].toString(), (Integer) obj[1],
								(Double) obj[2], obj[3].toString(), (Double) obj[4], obj[5].toString(),"YesBank AEPS");
						pack.setSlab1((Double) obj[6]);
						pack.setSlab2((Double) obj[7]);
					}else if(service_type.equalsIgnoreCase("MicroATM")){
						pack = new PackageWiseChargeComm(obj[0].toString(), (Integer) obj[1],
								(Double) obj[2], obj[3].toString(), (Double) obj[4], obj[5].toString(),"MicroATM");
						pack.setSlab1((Double) obj[6]);
						pack.setSlab2((Double) obj[7]);
					}else if(service_type.equalsIgnoreCase("Encore AEPS")){
						pack = new PackageWiseChargeComm(obj[0].toString(), (Integer) obj[1],
								(Double) obj[2], obj[3].toString(), (Double) obj[4], obj[5].toString(),"Encore AEPS");
						pack.setSlab1((Double) obj[6]);
						pack.setSlab2((Double) obj[7]);
					}else if(service_type.equalsIgnoreCase("AadharPay")){
						pack = new PackageWiseChargeComm(obj[0].toString(), (Integer) obj[1],
								(Double) obj[2], obj[3].toString(), (Double) obj[4], obj[5].toString(),"AadharPay");
						pack.setSlab1((Double) obj[6]);
						pack.setSlab2((Double) obj[7]);
					}
					
					
					
					else{
					 pack = new PackageWiseChargeComm(obj[0].toString(), (Integer) obj[1],
							(Double) obj[2], obj[3].toString(), (Double) obj[4], obj[5].toString(), obj[6].toString());
					}
					list.add(pack);
				}
			}

		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getPackagewisecharecom :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}
	
	@Override
	public PackageWiseChargeComm getPackageAndOperatorwisecharecom(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		PackageWiseChargeComm pack = new PackageWiseChargeComm();
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if (!objlist.isEmpty()) {
					Object[] obj = (Object[]) objlist.get(0);
					pack.setPackage_id(obj[1].toString());
					pack.setOperator_id((Integer)obj[2]);
					pack.setCharge((Double)obj[3]);
					pack.setCharge_type(obj[4].toString());
					pack.setComm((Double)obj[5]);
					pack.setComm_type(obj[6].toString());
			}

		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getPackagewisecharecom :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}

		return pack;
	}
	
	@Override
	public List<ImpsReport> getImpsReport(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<ImpsReport> list = new ArrayList<>();
		ImpsReport imptrans = null;
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			for (Object object : objlist) {
				Object[] obj = (Object[]) object;
				
				imptrans = new ImpsReport((Integer)obj[0], obj[1].toString(), obj[2].toString(), obj[3].toString(),
						obj[4].toString(), Double.parseDouble(obj[5].toString()), Double.parseDouble(obj[6].toString()),
						Double.parseDouble(obj[7].toString()), Double.parseDouble(obj[8].toString()), obj[9].toString(),
						obj[10].toString(), obj[11].toString(), obj[12].toString(), obj[13].toString(), Double.parseDouble(obj[14].toString()), obj[15].toString(), obj[16].toString(), Double.parseDouble(obj[17].toString()), Double.parseDouble(obj[18].toString()), obj[19].toString(), obj[20].toString());
				list.add(imptrans);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<PanReport> getpanReport(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<PanReport> list = new ArrayList<>();
		PanReport imptrans = null;
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			System.out.println("objlist::::"+objlist.size());
			for (Object object : objlist) {
				Object[] obj = (Object[]) object;
				imptrans = new PanReport((Integer)obj[0], obj[1].toString(), obj[2].toString(), obj[3].toString(),
						obj[4].toString(),obj[5].toString(), obj[6].toString(),
						obj[7].toString(), obj[8].toString(), obj[9].toString(),
						obj[10].toString(), obj[11].toString(), obj[12].toString(), obj[13].toString(), obj[14].toString(), obj[15].toString(), obj[16].toString(), obj[17].toString(), obj[18].toString(), obj[19].toString(), obj[20].toString(), obj[21].toString(), obj[22].toString());
				list.add(imptrans);
				System.out.println("list::::"+list.size());
			}
			System.out.println("objlist::::"+list.size());

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<PanCouponReport> getpanCouponReport(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<PanCouponReport> list = new ArrayList<>();
		PanCouponReport imptrans = null;
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			for (Object object : objlist) {
				Object[] obj = (Object[]) object;
				imptrans = new PanCouponReport((Integer)obj[0], obj[1].toString(), obj[2].toString(), obj[3].toString(),
						obj[4].toString(),obj[5].toString(), obj[6].toString(),
						obj[7].toString(), obj[8].toString(), obj[9].toString(),
						obj[10].toString(), obj[11].toString(),
						obj[12].toString());
				list.add(imptrans);
				//System.out.println("list::::"+list.size());
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<ViewUser> getViewUserDetails(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();		
		List<ViewUser> list = new ArrayList<ViewUser>();
		try {			
			List<Object> objlist =(List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if(!objlist.isEmpty()){		

				for(Object object : objlist){	
					Object[] obj = (Object[]) object;
					ViewUser viewu = new ViewUser(obj[0].toString(),obj[1].toString(),obj[2].toString(),obj[3].toString(), obj[4].toString(), obj[5].toString(), obj[6].toString(), obj[7].toString(), obj[8].toString(),obj[9].toString(), obj[10].toString(), obj[11].toString(), obj[12].toString(), obj[13].toString(), 
							obj[14].toString(),obj[15].toString(),(Double)obj[16],obj[17].toString(), obj[18].toString(), obj[19].toString(),obj[20].toString(),(Double)obj[21],obj[22].toString(),obj[23].toString(),obj[24].toString());
					list.add(viewu);
					
				}				
			}
			logger_log.warn("getViewUserDetails:::::::::::"+list);
			if(list.isEmpty()){
				list = new ArrayList<ViewUser>();
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getViewUserDetails :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<NSDLPan> getNSDLPanDetails(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();		
		List<NSDLPan> list = new ArrayList<NSDLPan>();
		try {			
			List<Object> objlist =(List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query, parameters);
			if(!objlist.isEmpty()){		

				for(Object object : objlist){	
					Object[] obj = (Object[]) object;
					NSDLPan viewu = new NSDLPan((Integer)obj[0],obj[1].toString(),obj[2].toString(),obj[3].toString(), obj[4].toString(), obj[5].toString(), obj[6].toString(), obj[7].toString(), obj[8].toString(),obj[9].toString(), obj[10].toString(), obj[11].toString(), obj[12].toString(), obj[13].toString(), 
							obj[14].toString(),obj[15].toString(),obj[16].toString(),obj[17].toString(), obj[18].toString(), obj[19].toString(),obj[20].toString(),obj[21].toString(),obj[22].toString(),obj[23].toString(),obj[24].toString(),obj[25].toString(),obj[26].toString(),obj[27].toString(),(double)obj[28],(double)obj[29],obj[30].toString(),obj[31].toString(),obj[32].toString(),obj[33].toString(),obj[34].toString(),obj[35].toString(),obj[36].toString(),obj[37].toString());
					list.add(viewu);
					
				}				
			}
			logger_log.warn("getNSDLPanDetails:::::::::::"+list);
			if(list.isEmpty()){
				list = new ArrayList<NSDLPan>();
			}
		} catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getViewUserDetails :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<P2ATranferReport> getP2ATranferReport(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<P2ATranferReport> list = new ArrayList<P2ATranferReport>();
		int i=0;
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query,parameters);	
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
				Object[] obj = (Object[]) object;	
			for(Object ob:obj){
				System.out.println("i::::::::::::::"+i);	
				System.out.println("ob::::::::::::::"+ob);
				i++;
				}
				P2ATranferReport pmt=new P2ATranferReport((int)obj[0],obj[1].toString(),obj[2].toString(),obj[3].toString(),(Double)obj[4],obj[5].toString(),obj[6].toString(),obj[7].toString(),obj[8].toString(),obj[9].toString(),obj[10].toString(),obj[11].toString(),obj[12].toString(),obj[13].toString(),obj[14].toString());
				list.add(pmt);
				}
			}
		}catch (Exception e) {
			logger_log.error("Error From getP2ATranferReport :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<P2AUserdetail> getP2AUserdetail(String query, Map<String, String> parameters) {
		Session session = sessionFactory.openSession();
		List<P2AUserdetail> list = new ArrayList<P2AUserdetail>();
		String status="";
		boolean flag=false; 
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query,parameters);	
			if (!objlist.isEmpty()) {
			for (Object object : objlist) {
			Object[] obj = (Object[]) object;	
			/*for(Object ob:obj){
			System.out.println("ob:::::::::::::::::::"+ob);
			
			}*/
			status=obj[6].toString();
			if(status.equalsIgnoreCase("1")){
			flag=true; 	
			}else{
			flag=false; 		
			}
			P2AUserdetail p2auser=new P2AUserdetail((int)obj[0],obj[1].toString(),obj[2].toString(),obj[3].toString(),obj[4].toString(),obj[5].toString(),flag,obj[7].toString(),obj[8].toString(),obj[9].toString());
			list.add(p2auser);
			}
			}
			
		}catch (Exception e) {
			logger_log.error("Error From getP2ATranferReport :   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<APPReport> getAPPReport(String query, Map<String, String> param){
		Session session = sessionFactory.openSession();
		List<APPReport> list = new ArrayList<APPReport>();
		try {
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query,param);	
			if (!objlist.isEmpty()) {
			for (Object object : objlist) {
			Object[] obj = (Object[]) object;	
			/*
			 * for(Object ob:obj){ System.out.println("ob:::::::::::::::::::"+ob);
			 * 
			 * }
			 */
			
			
			APPReport appreport=new APPReport((int)obj[0],obj[1].toString(),obj[2].toString(),obj[3].toString(),obj[4].toString(),obj[5].toString(),obj[6].toString(),obj[7].toString(),obj[8].toString(),obj[9].toString(),obj[10].toString(),obj[11].toString(),obj[12].toString(),obj[13].toString(),obj[14].toString(),obj[15].toString(),obj[16].toString(),obj[17].toString(),obj[18].toString(),obj[19].toString(),obj[20].toString(),obj[21].toString(),obj[22].toString(),obj[23].toString(),obj[24].toString(),obj[25].toString(),obj[26].toString(),obj[27].toString());
			String photoname=obj[13].toString()+"PHOTO";
			String imagename=obj[13].toString()+"AADHAR";
			String panname=obj[13].toString()+"PAN";
			String votername=obj[13].toString()+"VOTER";
			appreport.setAadharimagename(imagename);
			appreport.setPanimagename(panname);
			appreport.setPhotoimagename(photoname);
			appreport.setVoterimagename(votername);
			list.add(appreport);
			}
			}
			
		}catch (Exception e) {
			logger_log.error("Error From getAPPReport:   ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;

	}

	@Override
	public List<UserWalletReport> getWALLETReport(String query, Map<String, String> param) {
		Session session = sessionFactory.openSession();
		 List<UserWalletReport> list=new ArrayList<>();
		 double actualwallet=0.0;
		 try {
				List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query,param);	
				if (!objlist.isEmpty()) {
				for (Object object : objlist) {
				Object[] obj = (Object[]) object;	
				/*for(Object ob:obj){
				System.out.println("ob:::::::::::::::::::"+ob);
				
				}*/
				UserWalletReport userwalletreport=new UserWalletReport((int)obj[0],obj[1].toString(),obj[2].toString(),obj[3].toString(),(Double)obj[4],(Double)obj[5],obj[6].toString(),obj[7].toString(),obj[8].toString(),obj[9].toString(),obj[10].toString(),obj[11].toString(),obj[12].toString(),obj[13].toString());
				actualwallet=userwalletreport.getAmount()-userwalletreport.getCharge();
				userwalletreport.setActualwallet(actualwallet);
				//userwalletreport.setUmobile(umobile);
				list.add(userwalletreport);
				}
				}
				}catch (Exception e) {
					logger_log.error("Error From getAPPReport:   ", e);
					e.printStackTrace();
				} finally {
					session.close();
				}
				return list;
	
	}

	@Override
	public List<YesBankAEPSResponse> getYesBankReport(String query, Map<String, String> parameters) {
		List<YesBankAEPSResponse> list = new ArrayList<>();
		try{
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query,parameters);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
				Object[] obj = (Object[]) object;	
				YesBankAEPSResponse aeps = new YesBankAEPSResponse(obj[1].toString(),obj[2].toString(),obj[3].toString(),obj[4].toString(),obj[5].toString(),obj[6].toString(),obj[7].toString(),obj[8].toString(),obj[9].toString(),obj[10].toString(),obj[11].toString(),obj[12].toString(),obj[13].toString(),obj[14].toString() ,obj[15].toString(),obj[16].toString() ,obj[17].toString(),obj[18].toString() ,obj[19].toString() ,obj[20].toString() ,obj[21].toString() ,obj[22].toString() , obj[23].toString(),(Double)obj[24] ,(Double)obj[25],obj[26].toString() ,obj[27].toString() ,obj[28].toString(),obj[29].toString());
				aeps.setProcessingCode(obj[30].toString());
				aeps.setName(obj[32].toString());
				aeps.setMobile(obj[33].toString());
				list.add(aeps);
				}
			}
			
		}catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getYesBankReport :   ", e);
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MicroAtmResponse> getMicroATMReport(String query, Map<String, String> parameters) {
		List<MicroAtmResponse> list = new ArrayList<>();
		try{
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query,parameters);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
				Object[] obj = (Object[]) object;	
				MicroAtmResponse atm=new MicroAtmResponse(obj[1].toString(), obj[2].toString(), obj[3].toString(), obj[4].toString(), obj[5].toString(), obj[6].toString(), obj[7].toString(), obj[8].toString(),(double) obj[9], obj[10].toString(),obj[11].toString(),obj[12].toString());
				atm.setName(obj[13].toString());
				atm.setMobile(obj[14].toString());
				list.add(atm);
				}
			}
			
		}catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getYesBankReport :   ", e);
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<FingerPayAEPSResponse> getFingerAepsReport(String query, Map<String, String> parameters) {
		List<FingerPayAEPSResponse> list = new ArrayList<>();
		try{
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query,parameters);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
				Object[] obj = (Object[]) object;	
				FingerPayAEPSResponse fin=new FingerPayAEPSResponse(obj[1].toString(), obj[2].toString(), obj[3].toString(), obj[4].toString(), obj[5].toString(), obj[6].toString(), obj[7].toString(), (double) obj[8], (double) obj[9], obj[10].toString(), obj[11].toString());
				fin.setName(obj[12].toString());
				fin.setMobile(obj[13].toString());
				list.add(fin);
				}
			}
			
		}catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getFingerAepsReport :   ", e);
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MicroAtmResponseNew> getMicroATMReportnew(String query, Map<String, String> parameters) {
		List<MicroAtmResponseNew> list = new ArrayList<>();
		try{
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query,parameters);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
				Object[] obj = (Object[]) object;	
				MicroAtmResponseNew atm=new MicroAtmResponseNew(obj[1].toString(),obj[2].toString() ,obj[3].toString() ,obj[4].toString() ,obj[5].toString() ,obj[6].toString() ,obj[7].toString() ,obj[8].toString() ,(Double)obj[9],(Double)obj[10],obj[11].toString(),obj[12].toString(),obj[13].toString());
				atm.setName(obj[13].toString());
				atm.setMobile(obj[14].toString());
				list.add(atm);
				}
			}
			
		}catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getYesBankReport :   ", e);
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<OpenPayout> getOpenPayoutreport(String query, Map<String, String> param) {
		List<OpenPayout> list = new ArrayList<OpenPayout>();
		try{
			List<Object> objlist = (List<Object>) customQueryService.getDataByQueryUsingCustomQuery(query,param);
			if (!objlist.isEmpty()) {
				for (Object object : objlist) {
					Object[] obj = (Object[]) object;
					OpenPayout userattach=new OpenPayout(obj[1].toString(),obj[2].toString(),obj[3].toString(),obj[4].toString(), obj[5].toString(),obj[6].toString(),obj[7].toString(),obj[8].toString(),obj[9].toString(),obj[10].toString(),obj[11].toString(),obj[12].toString(),obj[13].toString(),obj[14].toString(),obj[15].toString(),obj[16].toString(),obj[17].toString());
					userattach.setId((int)obj[0]);
					userattach.setName(obj[18].toString());
					userattach.setMobile(obj[19].toString());
					list.add(userattach);
				}
					
				}
		}catch (Exception e) {
			logger_log.error("Error From  CustomQueryBusinessLogicImpl.getPinrequestreport :   ", e);
			e.printStackTrace();
		}
				return list;
	}
	
	
	
}
