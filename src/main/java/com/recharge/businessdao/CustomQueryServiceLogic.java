package com.recharge.businessdao;

import java.util.List;
import java.util.Map;

import com.recharge.customModel.TransactionReport;
import com.recharge.customModel.UserWalletReport;
import com.recharge.customModel.UtilityReport;
import com.recharge.customModel.ViewAssignPackage;
import com.recharge.customModel.ViewUser;
import com.recharge.model.FingerPayAEPSResponse;
import com.recharge.model.ImpsTransaction;
import com.recharge.model.Impscommission;
import com.recharge.model.MicroAtmResponse;
import com.recharge.model.MicroAtmResponseNew;
import com.recharge.model.NSDLPan;
import com.recharge.model.PackageWiseChargeComm;
import com.recharge.model.PaymonkResponse;
import com.recharge.model.SettlementReport;
import com.recharge.model.User;
import com.recharge.model.webenquery;
import com.recharge.yesbankmodel.YesBankAEPSResponse;
import com.bankopen.model.OpenPayout;
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

public interface CustomQueryServiceLogic {
	public double getTotalbalanceForAdmin(String query, Map<String, String> parameters);

	public List<YesBankAEPSResponse> getYesBankReport(String query, Map<String, String> parameters);

	public List<MicroAtmResponse> getMicroATMReport(String query, Map<String, String> parameters);

	public List<FingerPayAEPSResponse> getFingerAepsReport(String query, Map<String, String> parameters);

	public double getClosingBalanceForUser(String query, Map<String, String> parameters);

	public List<DefaultCommission> getMyDefaultCommission(String query, Map<String, String> parameters);

	public List<IndividualCommission> getIndividualCommission(String query, Map<String, String> parameters);

	public List<DefaultCharge> getMyDefaultCharge(String query, Map<String, String> parameters);

	public List<IndividualCharge> getIndividualCharge(String query, Map<String, String> parameters);

	public String getUplineIdByUserId(String query, Map<String, String> parameters);

	public List<PackageWiseChargeComm> getPackagewisecharecom(String query, Map<String, String> parameters,
			String service_type);

	public List<RechargeReport> getRechargeReport(String query, Map<String, String> parameters);

	public List<TransactionReport> getTransactionReport(String query, Map<String, String> parameters);

	public long getRechargeLock(String query, Map<String, String> parameters);

	public long getAddBalanceLock(String query, Map<String, String> parameters);

	public Notification getNotification(String query, Map<String, String> parameters);

	public ImpsTransaction getDmrTransactionByRecieptId(String query, Map<String, String> parameters);

	public List<ComplainDetails> viewComplain(String query, Map<String, String> parameters);

	public Impscommission getImpsChargeByUserIdAndAmount(String query, Map<String, String> parameters);

	public List<BalanceRequest> viewBalanceRequest(String query, Map<String, String> parameters);

	public List<BalanceTransfer> viewPurchaseReport(String query, Map<String, String> parameters);

	public List<BalanceTransfer> viewBalanceTransferReport(String query, Map<String, String> parameters);

	public List<UtilityReport> viewUtilityReport(String query, Map<String, String> parameters);

	public List<InsuranceReport> viewInsuranceReport(String query, Map<String, String> parameters);

	public List<ResellerDetails> getResellerDetails(String query, Map<String, String> parameters);

	public List<EarningSurchargeReport> getEarningReport(String query, Map<String, String> parameters);

	public List<RechargeReport> getLatestTransactionReport(String getLatestRechargeDetails, Map<String, String> param);

	public List<ImpsReport> getImpsReport(String query, Map<String, String> parameters);

	public double getTotalPurchaseAmount(String totalPurchaseByReseller, Map<String, String> param);

	public List<ViewAssignPackage> getAssignedPackage(String query, Map<String, String> parameters);

	public PackageWiseChargeComm getPackageAndOperatorwisecharecom(String query, Map<String, String> parameters);

	public List<PanReport> getpanReport(String query, Map<String, String> parameters);

	public List<PanCouponReport> getpanCouponReport(String query, Map<String, String> parameters);

	public List<ViewUser> getViewUserDetails(String query, Map<String, String> parameters);

	public List<NSDLPan> getNSDLPanDetails(String query, Map<String, String> parameters);

	public List<PaymonkResponse> getPaymonkRespons(String query, Map<String, String> param);

	public List<SettlementReport> getSettleReport(String query, Map<String, String> param);

	public List<AEPSUserDetail> getAEPSUserDetail(String query, Map<String, String> param);

	public List<P2AUserdetail> getP2AUserdetail(String query, Map<String, String> param);

	public double getTotalSettleWalletbalance(String query, Map<String, String> parameters);

	public List<P2ATranferReport> getP2ATranferReport(String getIMPSReportForAdmin, Map<String, String> param);

	public List<APPReport> getAPPReport(String query, Map<String, String> param);

	public List<UserWalletReport> getWALLETReport(String query, Map<String, String> param);

	List<AEPSUserDetail> getAEPSUserDetailwithbalance(String query, Map<String, String> param);

	public List<MicroAtmResponseNew> getMicroATMReportnew(String query, Map<String, String> parameters);

	public List<OpenPayout> getOpenPayoutreport(String getPayoutAdmin, Map<String, String> param);

	/*public List<ApiTransactionReport> getApiRefillReport(String query, Map<String, String> parameters); */

}
