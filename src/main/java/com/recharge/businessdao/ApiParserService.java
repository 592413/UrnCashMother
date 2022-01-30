package com.recharge.businessdao;

import java.util.Map;

import com.recharge.customModel.BringwayModel;
import com.recharge.customModel.DoopMeModel;
import com.recharge.customModel.InstantPayModel;
import com.recharge.model.PaymonkResponse;

public interface ApiParserService {
	public DoopMeModel doopMeParser(String response);

	/*public SuvidhaRechargeModel SuvidhaRechargeApiParser(String response1);

	public DigitalIndiaModel digitalIndiaApiParser(String response);*/
	public BringwayModel BringwayParsing(String xmlRecords);
	public PaymonkResponse paymonkParser(Map<String, Object> response);
	
	public PaymonkResponse paymonkParserupdated(Map<String, Object> response);

	public InstantPayModel instantpayParserFirst(String xml);

	public InstantPayModel instantpayParserSecond(String xml);

	
}
