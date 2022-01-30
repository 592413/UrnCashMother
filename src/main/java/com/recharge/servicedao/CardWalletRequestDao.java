package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.CardWalletRequest;

public interface CardWalletRequestDao {
	public List<CardWalletRequest> getAllCardWalletRequest();

	public CardWalletRequest getCardWalletRequestMapById(String id);

	public boolean insertCardholder(CardWalletRequest CardWalletRequest);

	public boolean updateCardWalletRequest(CardWalletRequest CardWalletRequest);

	public void deleteCardWalletRequest(int id);

	public List<CardWalletRequest> getCardWalletRequestByNamedQuery(String query, Map<String, Object> param);


}
