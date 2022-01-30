package com.recharge.businessdao;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.recharge.customModel.DefaultCharge;
import com.recharge.customModel.DefaultCommission;
import com.recharge.customModel.IndividualCharge;
import com.recharge.customModel.IndividualCommission;
import com.recharge.customModel.Notification;
import com.recharge.customModel.RechargeReport;
import com.recharge.model.Api;
import com.recharge.model.Impscommission;
import com.recharge.model.News;
import com.recharge.model.Operator;
import com.recharge.model.Reseller;
import com.recharge.model.User;

public interface GlobalCommandCenter {
	public User getUserByUserId(String userId);

	public List<Operator> getAllOperator();

	public News getNews(String wlId);

	public List<Api> getAllApi();

	public Map<String, Object> editProfile(Map<String, String> request, User userDetails);
	
	public Map<String, Object> getIMPScharge(Map<String, String> request,User userDetails);

	public Map<String, Object> changePass(Map<String, String> request, User userDetails);

	public Map<String, Object> getNamebyUserId(Map<String, String> request, User userDetails);

	public Map<String, Object> addUser(Map<String, String> request, User userDetails);
	
	public Map<String, Object> microatmUser(Map<String, String> request, User userDetails);
	public Map<String, Object> applicationform(Map<String, String> request,MultipartFile panfile,MultipartFile photofile,MultipartFile aadharfile,MultipartFile voterfile,User userDetails);

	public Map<String, Object> getUserByUserType(Map<String, String> request, User userDetails);

	public Map<String, Object> addBalance(Map<String, String> request, User userDetails);
	
	public Map<String, Object> statuschange(Map<String, String> request, User userDetails);
	
	public Map<String, Object> statuschangeforcard(Map<String, String> request, User userDetails);
	public Map<String, Object> getMyDiscount(Map<String, String> request);

	public Map<String, Object> getDefaultCommission(Map<String, String> request);

	public Map<String, Object> setDefaultDiscount(DefaultCommission[] request, User userDetails);

	public Map<String, Object> getIndividualDiscount(Map<String, String> request, User userDetails);

	public Map<String, Object> setIndividualDiscount(IndividualCommission[] request, User userDetails);

	public Map<String, Object> getDefaultChargeSetting(Map<String, String> request, User userDetails);

	public Map<String, Object> setDefaultChargeSetting(DefaultCharge[] request, User userDetails);

	public Map<String, Object> getIndividualCharge(Map<String, String> request, User userDetails);

	public Map<String, Object> setIndividualCharge(IndividualCharge[] request, User userDetails);

	public Map<String, Object> getViewUserDetails(Map<String, String> request, User userDetails);

	public Map<String, Object> getBankDetailsByWlId(String wlId);

	public Map<String, Object> getTotalUserBalanceRechargeStatus(User userDetails);

	public List<RechargeReport> getDashBoardRechargeReport(User userDetails);

	public Map<String, Object> advancedSearchUser(Map<String, String> request, User userDetails);

	public boolean insertSigninHistory(String ip, String userId, String mobile, String deviceId);

	public Map<String, Object> getUserByUserType1(Map<String, String> request, User userDetails);

	public Notification getNotification(User userDetails);

	public Map<String, Object> viewComplain(Map<String, String> request, User userDetails, String type);

	public Map<String, Object> updateComplainDetails(Map<String, String> request, User userDetails);

	public Map<String, Object> forgotPassword(Map<String, String> request);

	public Map<String, Object> advancedCustomerNo(Map<String, String> request, User userDetails);

	public Map<String, Object> editProfileForUser(Map<String, String> request, User userDetail);

	public Map<String, Object> viewBalanceRequest(Map<String, String> request, User userDetails, String type);

	public Map<String, Object> updateBalanceRequest(Map<String, String> request, User userDetails);

	public Map<String, Object> getOperatorByServiceType(Map<String, String> inputData);

	public Map<String, Object> _GetUpline(Map<String, String> request, User userDetails);

	public Map<String, Object> updateUtilityReport(Map<String, String> request, User userDetails);

	public Map<String, Object> updateInsuranceReport(Map<String, String> request, User userDetails);

	public Map<String, Object> updateRechargeReport(Map<String, String> request, User userDetails);

	public Reseller getResellerByWlId(String wlId);
	
	public Map<String, Object> getImpsReport(Map<String, String> request,User userDetails);

	public void insertUserAttachment(String userId, MultipartFile panCard, MultipartFile adharCard,	MultipartFile photo);

	public Map<String, Object> updateRemark(Map<String, String> request, User userDetails);
	
	public Map<String, Object> setIMPScharge(Impscommission[] request,User userDetails);

	public Map<String, Object> addEnquery(Map<String, String> request);

	public Map<String, Object> packageCreate(Map<String, String> request, User userDetail);

	public Map<String, Object> assignPackage(Map<String, String> request, String username, User userDetails);

	public Map<String, Object> viewAssignPackage(User userDetails);

	public Map<String, Object> assignEditPackage(Map<String, String> request, String username);

	public Map<String, Object> assignedPackage(User userDetails);

	public Map<String, Object> updatemyPackage(Map<String, String> request,User user);

	public Map<String, Object> getPackagesDetails(User userDetails);

	public Map<String, Object> viewPackagewiseCommCharge(Map<String, String> request);

	public Map<String, Object> getpanReport(Map<String, String> request, User userDetails);

	public Map<String, Object> getpanCouponReport(Map<String, String> request, User userDetails);

	public Map<String, Object> DeleteMyPackageDetail(Map<String, String> request, User userDetails);


	public Map<String, Object> getRBLAEPSReport(Map<String, String> request, User userDetail);
		
	public Map<String, Object> getRBLSETTLEMENTReport(Map<String, String> request, User userDetail);

	public Map<String, Object> getViewInactiveUser(Map<String, String> request, User userDetails);

	public Map<String, Object> fetchaepsbankdt(User userDetail);

	public Map<String, Object> checkBalance(Map<String, String> request, User userDetail);

	public Map<String, Object> getUserByUserTypelatest(Map<String, String> request, User userDetails);

	public Map<String, Object> addmicroatmusernew(Map<String, String> request, User userDetails);

	public Map<String, Object> addgstdt(Map<String, String> request, User userDetails);
	
	
}
