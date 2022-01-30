/**
 * 
 */
package com.recharge.businessdao;

import com.recharge.model.Chargeset;
import com.recharge.model.Individualcharge;
import com.recharge.model.PackageWiseChargeComm;

/**
 * @author Md Rahim
 *
 */
public interface CommissionService {

	double getCommisionOnOperator(String userId, String wl_id, Integer roleId, Integer operatorId);

	Individualcharge getIndividualChargeOnOperator(String userId, Integer operatorId);

	Chargeset getDefaultChargeOnOperator(String wl_id, Integer operatorId);

	double calculateChargeOnOperator(String userId, Integer operatorId, String wl_id, Integer roleId, double amount);

	boolean updateBalance(String userId, String naration, String remarks, double amount, String type,double tds);

	public PackageWiseChargeComm getPackagewiseCommisionOnOperator(String userId, String service_type, int operatorID);
}
