package com.recharge.businessdao;

import java.util.Map;

/**
 * @author Md Rahim
 *
 */
public interface AuthenticationCommandCenter {
	public String getUserNamebyMobileEmail(String query, Map<String, String> parameters);

	public String loginUser(String sql, Map<String, String> parameters);

	public Map<String, Object> getReseller(Map<String, Object> parameters);
}
