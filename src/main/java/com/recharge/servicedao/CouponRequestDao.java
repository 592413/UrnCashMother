package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.CouponRequest;

public interface CouponRequestDao {
	
	public boolean insertCouponRequest(CouponRequest couponrequest);
	
	public boolean updateCouponRequest(CouponRequest couponrequest);
	
	public List<CouponRequest> getCouponRequestByNamedQuery(String query, Map<String, Object> param);

	public CouponRequest getcouponById(Integer id);

}
