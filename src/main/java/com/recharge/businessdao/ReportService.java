package com.recharge.businessdao;

import java.util.List;
import java.util.Map;

import com.recharge.customModel.TransactionReport;
import com.recharge.customModel.UserWalletReport;
import com.recharge.customModel.APPReport;
import com.recharge.customModel.BalanceTransfer;
import com.recharge.customModel.EarningSurchargeReport;
import com.recharge.customModel.P2ATranferReport;
import com.recharge.customModel.RechargeReport;
import com.recharge.model.Ezpayapplicationform;
import com.recharge.model.NSDLPan;
import com.recharge.model.User;

public interface ReportService {
	public List<TransactionReport> getTransactionReport(Map<String, String> request, User userDetails);
	
	public List<RechargeReport> getRechargeReport(Map<String, String> request, User userDetails);
	public List<TransactionReport> viewBalRevReport(Map<String, String> request, User userDetails);
	public List<BalanceTransfer> viewPurchaseReport(Map<String, String> request, User userDetails);
	public List<BalanceTransfer> viewBalanceTransferReport(Map<String, String> request, User userDetails);
	public Map<String, Object> viewUtilityReport(Map<String, String> request, User userDetails, String string);
	public Map<String, Object> viewInsuranceReport(Map<String, String> request, User userDetails, String type);
	public List<TransactionReport> getRefundReport(Map<String, String> request, User userDetails);
	public List<EarningSurchargeReport> getEarningReport(Map<String, String> request, User userDetails);
	List<RechargeReport> getLatestTransactionReport(User userDetails);
	public List<NSDLPan> getnsdlReport(Map<String, String> request, User userDetails);
	public List<NSDLPan> fetchnsdlpan(User userDetails);
	public Map<String, Object> updateNSDLReport(Map<String, String> request, User userDetails, byte[] askbyte, String type);
	public Map<String, Object> updateNSDLReportFailed(Map<String, String> request, User userDetails);
	public Map<String, Object> updateNSDLHoldReport(Map<String, String> request, User userDetails);
	public List<P2ATranferReport> getp2aReport(Map<String, String> request, User userDetails);
	public List<APPReport> getAPPReport(Map<String, String> request, User userDetails);
	public List<UserWalletReport> getWALLETReport(Map<String, String> request, User userDetails);

	public Map<String, Object> viewuserbankdt( User userDetails);
}
