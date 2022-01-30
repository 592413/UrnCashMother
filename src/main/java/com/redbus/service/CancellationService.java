package com.redbus.service;

import java.util.Map;

import com.recharge.model.User;
import com.redbus.model.CancellationRequest;

public interface CancellationService {

	Map<String, Object> getCancellationData(Map<String, String> request, User userDetails);

	Map<String, Object> doCancelTicket(CancellationRequest request, User userDetails);

	Map<String, Object> getCancellationInfo(Map<String, String> request, User userDetails);

}
