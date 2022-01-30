/**
 * 
 */
package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Individualcharge;

/**
 * @author Md Rahim
 *
 */
public interface IndividualChargeDao {
	
		public List<Individualcharge> getAllIndividualcharge();

		public Individualcharge getIndividualchargeById(Integer Id);

		public boolean insertIndividualcharge(Individualcharge individualcharge);

		public boolean updateIndividualcharge(Individualcharge individualcharge);

		public void deleteIndividualcharge(Integer Id);

		public List<Individualcharge> getIndividualchargeByNamedQuery(String query, Map<String, Object> param);
	}


