package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.Balancetransafer;

public interface BalancetransaferDao {
	public List<Balancetransafer> getAllBalanceTransfer();

	public Balancetransafer getBalanceTransferByRequestId(Integer transferId);

	public boolean insertBalanceTransfer(Balancetransafer balancetransafer);

	public boolean updateBalanceTransfer(Balancetransafer balancetransafer);

	public void deleteBalanceTransfer(Integer transferId);

	public List<Balancetransafer> getBalanceRequestByNamedQuery(String query, Map<String, Object> param);
}
