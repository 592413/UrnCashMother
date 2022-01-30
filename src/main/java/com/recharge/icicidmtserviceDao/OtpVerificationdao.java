package com.recharge.icicidmtserviceDao;

import java.util.List;
import java.util.Map;

import com.recharge.icicidmtmodel.OtpVerification;
;

public interface OtpVerificationdao {


	public boolean insertOtpVerification(OtpVerification OtpVerification);

	public boolean updateOtpVerification(OtpVerification OtpVerification);

	public boolean deleteOtpVerification(int id);

	public List<OtpVerification> getOtpVerificationByNamedQuery(String query, Map<String, Object> param);







}
